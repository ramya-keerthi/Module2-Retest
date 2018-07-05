package com.cg.service;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cg.bean.DonorBean;
import com.cg.dao.CabRequestDAO;
import com.cg.dao.IDonorDAO;
import com.cg.exception.CabApplicationException;

public class CabService implements ICabService{

	@Override
	public int addCabRequestDetails(DonorBean cabRequest) throws SQLException,
			CabApplicationException {
		IDonorDAO i = new CabRequestDAO();
		int reqId=0;
		String pincode= cabRequest.getPincode();
		String cabNumber= null;
		if(pincode.equals("40009"))
				{
			cabNumber = "XYZ123";
				}
		else if(pincode.equals("400789"))
		{
			cabNumber = "XYZ321";
		}
		else if(pincode.equals("400789"))
		{
			cabNumber = "XYZ746";
		}
		else {
			throw new CabApplicationException("Enter valid pin");
		}
		cabRequest.setCabNumber(cabNumber);
		reqId=i.addCabRequestDetails(cabRequest);
		return reqId;
	}

	@Override
	public DonorBean getRequestDetails(int requestId) throws SQLException {
		IDonorDAO i = new CabRequestDAO();
		DonorBean d= new DonorBean();
		d= i.getRequestDetails(requestId);
		return d;
	}

	@Override
	public boolean isValidDetails(DonorBean cabRequest) throws SQLException, CabApplicationException {
		IDonorDAO i = new CabRequestDAO();
		String errorMessage = "";
		//Validating Customer Name
		String customerName=cabRequest.getCustomerName();
		Pattern cnamePattern = Pattern.compile("^[A-Z][A-Za-z\\s]{1,19}$");
		Matcher cnameMatcher = cnamePattern.matcher(customerName);
	
		if(!cnameMatcher.find()){
			errorMessage+="\n 'Customer' Name Should Be In Alphabits and start with Uppercase.";
		}
		
		//Validating Phone number
		String phoneNumber = cabRequest.getPhoneNum();
		Pattern phoneNumberPattern = Pattern.compile("^\\d{10}$");
		Matcher phoneNumberMatcher = phoneNumberPattern.matcher(phoneNumber);
		
		if(!phoneNumberMatcher.find()){
			errorMessage+="\n 'Phone number' Must be 10 digits.";	
		}
		
		String pincode = cabRequest.getPincode();
		Pattern pincodePattern = Pattern.compile("^\\d+(\\.\\d{1})?$");
		Matcher pincodeMatcher = pincodePattern.matcher(pincode);
		if(!pincodeMatcher.find()){
			errorMessage+="\n 'Demand Draft Amount' must be in digits.";
		}
		
		if(!errorMessage.isEmpty())
		{
			throw new CabApplicationException(errorMessage);
		}
		return true;
	}

	@Override
	public boolean isValidRequestId(String requestId1) throws SQLException, CabApplicationException {
		Pattern requestId1Pattern = Pattern.compile("^\\d{5}$");
		Matcher requestId1Matcher = requestId1Pattern.matcher(requestId1);
		if(!requestId1Matcher.find()){
			throw new CabApplicationException("Enter Valid Transaction ID !!");
		}
		return true;
	}

}
