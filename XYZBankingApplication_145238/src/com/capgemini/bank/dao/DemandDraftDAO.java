package com.capgemini.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.capgemini.bank.bean.DemandDraft;
import com.capgemini.bank.exception.DemandDraftException;
import com.capgemini.bank.util.DemanDraftDBConnection;

public class DemandDraftDAO implements IDemandDraftDAO {

	private static Logger LOGGER = Logger.getLogger(DemandDraftDAO.class);
	
	
	
		/*******************************************************************************************************
		 - Function Name	:	addDemandDraftDetails(DemandDraft demandDraft)
		 - Input Parameters	:	DemandDraft demandDraft
		 - Return Type		:	int
		 - Throws			:  	DemandDraftException
		 - Author			:	Naresh
		 - Creation Date	:	27/03/2018
		 - Description		:	Adding Demand Draft
		 ********************************************************************************************************/
	
	@SuppressWarnings("resource")
	@Override
	public int addDemandDraftDetails(DemandDraft demandDraft)throws DemandDraftException {

		Connection connection = DemanDraftDBConnection.getConnection();	
		
		PreparedStatement preparedStatement=null;		
		ResultSet resultSet = null;
		
		String transactionId=null;
		
		int queryResult=0;
		
		try
		{		connection.setAutoCommit(false);
			preparedStatement=connection.prepareStatement(SQLFinalQueries.INSERT_QUERY);

			preparedStatement.setString(1,demandDraft.getCustomerName());			
			preparedStatement.setString(2,demandDraft.getInFavourOf());
			preparedStatement.setString(3,demandDraft.getPhoneNumber());
			preparedStatement.setString(4,demandDraft.getDemandDraftAmount());	
			preparedStatement.setDouble(5,demandDraft.getDemandDraftCommission());
			preparedStatement.setString(6,demandDraft.getDemandDraftDescription());
			
			queryResult=preparedStatement.executeUpdate();
		
			preparedStatement = connection.prepareStatement(SQLFinalQueries.TRANSACTIONID_QUERY_SEQUENCE);
			resultSet=preparedStatement.executeQuery();

			if(resultSet.next())
			{
				transactionId=resultSet.getString(1);
			}
	
			if(queryResult==0)
			{
				LOGGER.error("Insertion failed ");
				throw new DemandDraftException("Inserting donor details failed ");

			}
			else
			{	connection.commit();
				LOGGER.info("Donor details added successfully:");
				return Integer.parseInt(transactionId);
			}

		}
		catch(SQLException sqlException)
		{
			try {
				connection.rollback();
			} catch (SQLException sqlException2) {
				LOGGER.error(sqlException2.getMessage());
				throw new DemandDraftException("Tehnical problem occured refer log");
			}
			LOGGER.error(sqlException.getMessage());
			throw new DemandDraftException("Tehnical problem occured refer log");
		}

		finally
		{
			try 
			{
				resultSet.close();
				preparedStatement.close();
				connection.close();
			}
			catch (SQLException sqlException) 
			{
				LOGGER.error(sqlException.getMessage());
				throw new DemandDraftException("Error in closing db connection");

			}
		}
	}

	/*******************************************************************************************************
	 - Function Name	:	getDemandDraftDetails(int transactionId)
	 - Input Parameters	:	int transactionId
	 - Return Type		:	DemandDraft
	 - Throws			:  	DemandDraftException
	 - Author			:	Naresh
	 - Creation Date	:	27/03/2018
	 - Description		:	Getting details Demand Draft
	 ********************************************************************************************************/
	@Override
	public DemandDraft getDemandDraftDetails(int transactionId) throws DemandDraftException {
		Connection connection=DemanDraftDBConnection.getConnection();	
		
		DemandDraft demandDraft = null;
		PreparedStatement preparedStatement=null;
		ResultSet resultset = null;
		try
		{
			preparedStatement=connection.prepareStatement(SQLFinalQueries.VIEW_DEMAND_DRAFT_DETAILS_QUERY);
			preparedStatement.setInt(1,transactionId);
			resultset=preparedStatement.executeQuery();
			
			if(resultset.next())
			{
				demandDraft = new DemandDraft();
				demandDraft.setDemandDraftAmount(resultset.getString(1));
				demandDraft.setDemandDraftCommission(resultset.getDouble(2));
				demandDraft.setDemandDraftDescription(resultset.getString(3));
			}
			
			if( demandDraft != null)
			{
				LOGGER.info("Record Found Successfully");
				return demandDraft;
			}
			else
			{
				LOGGER.info("Record Not Found");
				return null;
			}
			
		}
		catch(SQLException sqlException)
		{
			LOGGER.error(sqlException.getMessage());
			throw new DemandDraftException("Technical problem occured.");
		}
		finally
		{
			try 
			{
				resultset.close();
				preparedStatement.close();
				connection.close();
			} 
			catch (SQLException e) 
			{
				LOGGER.error(e.getMessage());
				throw new DemandDraftException("Error in closing db connection");

			}
		}
		
	}

}
