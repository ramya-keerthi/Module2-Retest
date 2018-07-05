package com.cg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;









import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;







import com.capgemini.bank.exception.DemandDraftException;
import com.capgemini.bank.util.DemanDraftDBConnection;
import com.cg.bean.DonorBean;
import com.cg.exception.CabApplicationException;
import com.cg.util.CabAppDBConnection;

public class CabRequestDAO implements IDonorDAO{
	Logger logger=Logger.getRootLogger();
	public CabRequestDAO() {
		PropertyConfigurator.configure("resources//log4j.properties");
	}
	StringBuilder s2=new StringBuilder("");
	
	@Override
	public int addCabRequestDetails(DonorBean cabRequest) throws CabApplicationException {
		Connection con;
		String requestId=null;
		
			con = CabAppDBConnection.getInstance().getConnection();
		
		PreparedStatement p=null;		
		ResultSet rs = null;
		String val=null;
		int queryresult=0;
		try {
			p=con.prepareStatement(IQueryMapper.insertDetailsQuery);
			
			p.setString(1, cabRequest.getCustomerName());
			p.setString(2,cabRequest.getPhoneNum());
			p.setString(3,cabRequest.getRequestStatus());
			p.setString(5,cabRequest.getAddress());	
			p.setString(6,cabRequest.getPincode());
			p.setString(4, cabRequest.getCabNumber());
			queryresult=p.executeUpdate();
			PreparedStatement p2= con.prepareStatement(IQueryMapper.seqQuery);
			rs=p2.executeQuery();
			if(rs.next())
			{
				val=rs.getString(1);
			}
			if(queryresult==0)
			{
				logger.error("Insertion failed ");
				throw new CabApplicationException("Inserting donor details failed ");
			}
			else
			{
				con.commit();
				logger.info("Donor details added successfully:");
				return Integer.parseInt(val);
			}
			return Integer.parseInt(val);
			
		}
		catch(SQLException e)
		{
			s2.append(e.getMessage());
			System.out.println(s2);
			logger.warn("Invalid details");
		}
		
	}

	@Override
	public DonorBean getRequestDetails(int requestId) throws SQLException {
		Connection connection=CabAppDBConnection.getInstance().getConnection();	
		PreparedStatement p=null;		
		ResultSet rs = null;
		String val=null;
		int queryresult=0;
		try
		{
			
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean isValidDetails(DonorBean cabRequest) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isValidRequestId(String requestId1) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	

}
