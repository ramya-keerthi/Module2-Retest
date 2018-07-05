package com.capgemini.bank.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;





import com.capgemini.bank.bean.DemandDraft;
import com.capgemini.bank.dao.IDemandDraftDAO;
import com.capgemini.bank.dao.DemandDraftDAO;
import com.capgemini.bank.exception.DemandDraftException;

public class DemandDraftService  implements IDemandDraftService{
	IDemandDraftDAO iDemandDraftDAO; 
	@Override
	public int addDemandDraftDetails(DemandDraft demandDraft) throws DemandDraftException {
		int transactionId=0;
		iDemandDraftDAO= new DemandDraftDAO();
		int amount = Integer.parseInt(demandDraft.getDemandDraftAmount());
		int commission=0;
		if(amount>0&&amount<=5000){
			commission=10;
		}
		else if(amount>5000&&amount<=10000){
			commission=41;
		}
		else if(amount>10000&&amount<=100000){
			commission=51;
		}
		else if(amount>100000&&amount<=500000){
			commission=306;
		}
		else{
			throw new DemandDraftException("Amount is out of limits.");
		}
		demandDraft.setDemandDraftCommission(commission);
		
				transactionId = iDemandDraftDAO.addDemandDraftDetails(demandDraft);
		return transactionId;
	}

	@Override
	public DemandDraft getDemandDraftDetails(int transactionId)  throws DemandDraftException {
		DemandDraft demandDraft = new DemandDraft();
		iDemandDraftDAO= new DemandDraftDAO();
		demandDraft =  iDemandDraftDAO.getDemandDraftDetails(transactionId);
		double totalAmount= demandDraft.getDemandDraftCommission() + Double.parseDouble(demandDraft.getDemandDraftAmount());
		demandDraft.setTotalAmount(totalAmount);
		
		return demandDraft;
	}

	public boolean isValidDetails(DemandDraft demandDraft) throws DemandDraftException {
	

		String errorMessage = "";
		//Validating Customer Name
		
		String customerName=demandDraft.getCustomerName();
		Pattern cnamePattern = Pattern.compile("^[A-Z][A-Za-z\\s]{1,19}$");
		Matcher cnameMatcher = cnamePattern.matcher(customerName);
	
		if(!cnameMatcher.find()){
			errorMessage+="\n 'Customer' Name Should Be In Alphabits and start with Uppercase.";
		}
		
		
		//Validating Phone number
				String phoneNumber = demandDraft.getPhoneNumber();
				Pattern phoneNumberPattern = Pattern.compile("^\\d{10}$");
				Matcher phoneNumberMatcher = phoneNumberPattern.matcher(phoneNumber);
				
				if(!phoneNumberMatcher.find()){
					errorMessage+="\n 'Phone number' Must be 10 digits.";	
				}
				
		//Validating amount
				String amount = demandDraft.getDemandDraftAmount();
				Pattern amountPattern = Pattern.compile("^\\d+(\\.\\d{1})?$");
				Matcher amountMatcher = amountPattern.matcher(amount);
				if(!amountMatcher.find()){
					errorMessage+="\n 'Demand Draft Amount' must be in digits.";
				}
			
		//Validating In favour of
				
				String inFavourOf=demandDraft.getInFavourOf();
				Pattern inFavourOfPattern = Pattern.compile("^[A-Za-z\\s]{1,20}$");
				Matcher inFavourOfMatcher = inFavourOfPattern.matcher(inFavourOf);
			
				if(!inFavourOfMatcher.find()){
					errorMessage+="\n 'In favour of' Should Be In Alphabits.";
				}
				
		//Validating Demand Draft Description
				
				String remarks=demandDraft.getDemandDraftDescription();
				Pattern remarksPattern = Pattern.compile("^[A-Za-z\\s]{0,50}$");
				Matcher remarksMatcher = remarksPattern.matcher(remarks);
			
				if(!remarksMatcher.find()){
					errorMessage+="\n 'Remarks' Should Be In Alphabits.";
				}	
				
		if(!errorMessage.isEmpty())
		{
			throw new DemandDraftException(errorMessage);
		}
		
		
		return true;
	
	}
	
	
	public boolean isValidTransactionID(String transactionId) throws DemandDraftException {
		
		//Validating transactionID 
		
		Pattern transactionIdPattern = Pattern.compile("^\\d{5}$");
		Matcher transactionIdMatcher = transactionIdPattern.matcher(transactionId);
		if(!transactionIdMatcher.find()){
			throw new DemandDraftException("Enter Valid Transaction ID !!");
		}
		return true;
	}

}
