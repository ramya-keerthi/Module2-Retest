package com.capgemini.cab.service;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.capgemini.cab.dao.CabRequestDAO;
import com.capgemini.cab.dao.ICabRequestDAO;
import com.capgemini.cab.exception.CabApplicationException;
import com.capgemini.cabs.bean.CabRequest;

public class CabService implements ICabService{

	


	@Override
	public int addCabRequestDetails(CabRequest cabRequest) throws SQLException, CabApplicationException {
		ICabRequestDAO det= new CabRequestDAO();
		int requestId=0;
		String cabNumber=null;
		String pincode;
		pincode=cabRequest.getPincode();
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
		else 
		{
			throw new CabApplicationException("not available");
		}
		
		cabRequest.setCabNumber(cabNumber);
		requestId = det.addCabRequestDetails(cabRequest);
		return requestId;
		}

	@Override
	public CabRequest getRequestDetails(int requestId) throws SQLException, CabApplicationException {
		ICabRequestDAO det= new CabRequestDAO();
		return det.getRequestDetails(requestId);
	}

	


	public boolean isValidRequestId(String requestId1) throws CabApplicationException {
		Pattern requestIdPattern = Pattern.compile("^\\d{5}$");
		Matcher requestIdMatcher = requestIdPattern.matcher(requestId1);
		if(!requestIdMatcher.find()){
			throw new CabApplicationException("Enter Valid Request ID !!");
		}
		return true;
	}

	@Override
	public boolean isValidDetails(CabRequest cabRequest) throws SQLException,
			CabApplicationException {
		String errorMessage = "";
		String CustomerName=cabRequest.getCustomerName();
		Pattern cnamePattern = Pattern.compile("^[A-Z][A-Za-z\\s]{1,19}$");
		Matcher cnameMatcher = cnamePattern.matcher(CustomerName);
		
		if(!cnameMatcher.find()){
			errorMessage+="\n 'Customer' Name Should Be In Alphabits and start with Uppercase.";
		}
	
	String phoneNumber = cabRequest.getPhoneNum();
	Pattern phoneNumberPattern = Pattern.compile("^\\d{10}$");
	Matcher phoneNumberMatcher = phoneNumberPattern.matcher(phoneNumber);
	
	if(!phoneNumberMatcher.find()){
		errorMessage+="\n 'Phone number' Must be 10 digits.";	
	}
	return true;
	
	}
	

}
