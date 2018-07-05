package com.capgemini.bank.bean;

public class DemandDraft {
	
	
	private String customerName;
	private String inFavourOf;
	private String phoneNumber;
	private String dateOfTransaction;
	private String demandDraftAmount;
	private double demandDraftCommission;
	private String demandDraftDescription;
	private String transactionId;
	private double totalAmount;
	
	
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getInFavourOf() {
		return inFavourOf;
	}
	public void setInFavourOf(String inFavourOf) {
		this.inFavourOf = inFavourOf;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getDateOfTransaction() {
		return dateOfTransaction;
	}
	public void setDateOfTransaction(String dateOfTransaction) {
		this.dateOfTransaction = dateOfTransaction;
	}
	public String getDemandDraftAmount() {
		return demandDraftAmount;
	}
	public void setDemandDraftAmount(String demandDraftAmount) {
		this.demandDraftAmount = demandDraftAmount;
	}
	public double getDemandDraftCommission() {
		return demandDraftCommission;
	}
	public void setDemandDraftCommission(double demandDraftCommission) {
		this.demandDraftCommission = demandDraftCommission;
	}
	public String getDemandDraftDescription() {
		return demandDraftDescription;
	}
	public void setDemandDraftDescription(String demandDraftDescription) {
		this.demandDraftDescription = demandDraftDescription;
	}
	
	
	
	
	
}
