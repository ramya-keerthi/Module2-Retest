package com.capgemini.cab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;





import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.capgemini.cab.exception.CabApplicationException;
import com.capgemini.cab.util.CabAppDBConnection;
import com.capgemini.cabs.bean.CabRequest;

public class CabRequestDAO implements ICabRequestDAO{

	Logger logger=Logger.getRootLogger();
	
	public CabRequestDAO() {
		PropertyConfigurator.configure("resources//log4j.properties");
	}

	@SuppressWarnings("resource")
	@Override
	public int addCabRequestDetails(CabRequest cabRequest) throws SQLException, CabApplicationException {
		Connection connection = CabAppDBConnection.getInstance().getConnection();
		PreparedStatement preparedStatement=null;		
		ResultSet resultSet = null;
		String requestId=null;
		int queryResult;
		try
		{
			//connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(IQueryMapper.insertDetailsQuery);
			
			preparedStatement.setString(1,cabRequest.getCustomerName() );
			preparedStatement.setString(2,cabRequest.getPhoneNum() );
			preparedStatement.setString(3,cabRequest.getRequestStatus() );
			preparedStatement.setString(4,cabRequest.getCabNumber() );
			preparedStatement.setString(5,cabRequest.getAddress() );
			//preparedStatement.setString(1,cabRequest.getPincode() );
			
			queryResult=preparedStatement.executeUpdate();
			preparedStatement = connection.prepareStatement(IQueryMapper.seqQuery);
			resultSet=preparedStatement.executeQuery();
			
			if(resultSet.next())
			{
				requestId=resultSet.getString(1);
			}
			if(queryResult==0)
			{
				 logger.info("Insertion failed ");
				throw new CabApplicationException("Insertion failed");
			}
			else
			{
				logger.info("Details added succesfully");
				return Integer.parseInt(requestId);
			}
		}
		catch(SQLException sqlException)
		{
			logger.info(sqlException.getMessage());
			throw new CabApplicationException("problem in database connectivity");
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
				logger.error(sqlException.getMessage());
				throw new CabApplicationException("error in closing database connectivity");
		}
		}
	}

	@Override
	public CabRequest getRequestDetails(int requestId) throws SQLException, CabApplicationException {
		Connection connection = CabAppDBConnection.getInstance().getConnection();
		CabRequest cabRequest =null;
		PreparedStatement preparedStatement=null;		
		ResultSet resultSet = null;
		StringBuilder s = new StringBuilder("");
		try
		{
			preparedStatement=connection.prepareStatement(IQueryMapper.ViewCustDetailsQuery);
			preparedStatement.setInt(1,requestId);
			resultSet=preparedStatement.executeQuery();
			
			if(resultSet.next())
			{
				
				cabRequest.setCustomerName(resultSet.getString(1));
				cabRequest.setRequestStatus(resultSet.getString(2));
				cabRequest.setCabNumber(resultSet.getString(3));
				cabRequest.setAddress(resultSet.getString(4));
			}
			if(cabRequest != null)
			{
				logger.info("record found successfully");
				return cabRequest;
			}
			else
			{
				logger.info("record not found");
						return null;
			}
		}
		catch(SQLException sqlException)
		{
			logger.error(sqlException.getMessage());
			throw new CabApplicationException("Technical problem");
		}
		/*finally
		{
			try
			{
			resultSet.close();
			preparedStatement.close();
			connection.close();
			}
			catch (SQLException e) 
			{
				logger.error(e.getMessage());
				throw new CabApplicationException("Error in closing db connection");
			}
		}*/
			
	}

	@Override
	public boolean isValidRequestId(String requestId1)
			throws CabApplicationException {
		StringBuilder s2 = new StringBuilder("");
		try{
		Connection connection = CabAppDBConnection.getInstance().getConnection();
		PreparedStatement s=connection.prepareStatement(IQueryMapper.seqQuery);
		s.setString(1, requestId1);
		ResultSet r=s.executeQuery();
		int count=0;
		
		while(r.next())
		{
			count=r.getInt(1);
		}
		if(count==0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
		catch(SQLException e)
		{
			s2.append(e.getMessage());
			System.out.println(s2);
			logger.warn("Invalid recharge id");
		}
		return false;
	}

	@Override
	public boolean isValidDetails(CabRequest cabRequest) throws SQLException,
			CabApplicationException {
		// TODO Auto-generated method stub
		return false;
	}
}


