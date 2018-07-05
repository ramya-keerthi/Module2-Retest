package com.capgemini.cabs.bean;

public class CabRequest {
	private String CustomerName;
	private String phoneNum;
	private String Address;
	private String RequestStatus;
	private String cabNumber;
	private String pincode;

	public String getCustomerName() {
		return CustomerName;
	}
	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String mobileno) {
		this.phoneNum = mobileno;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	
	public String getRequestStatus() {
		return RequestStatus;
	}
	public void setRequestStatus(String requestStatus) {
		RequestStatus = requestStatus;
	}
	public String getCabNumber() {
		return cabNumber;
	}
	public void setCabNumber(String cabNumber) {
		this.cabNumber = cabNumber;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPinCode(String pincode) {
		this.pincode = pincode;
	}


}
