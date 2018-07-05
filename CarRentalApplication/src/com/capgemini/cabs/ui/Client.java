package com.capgemini.cabs.ui;

import java.sql.SQLException;
import java.util.Scanner;







import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.capgemini.cab.exception.CabApplicationException;
import com.capgemini.cab.service.CabService;
import com.capgemini.cab.service.ICabService;
import com.capgemini.cabs.bean.CabRequest;

public class Client {
	 static Logger logger =Logger.getRootLogger();
	
	public Client() {
		PropertyConfigurator.configure("resources//log4j.properties");
	}

	public static void main(String[] args) throws NumberFormatException, SQLException, CabApplicationException {
		PropertyConfigurator.configure("resources//log4j.properties");
		CabRequest cabRequest= new CabRequest();
		ICabService cabservice=new CabService();
		//@SuppressWarnings("resource")
		Scanner sc=new Scanner(System.in);
		String choice="-1";
		int request_id=-1;
		System.out.println("\n Cab Application Services");
		
		System.out.println("\n select from menu :");
		System.out.println("1)Raise Cab Request");
		System.out.println("2)view Cab Request");
		System.out.println("3)Exit");
		choice=sc.next();
		switch(choice)
		{
		case "1":
		System.out.println("enter username");
		String name=sc.next();
		cabRequest.setCustomerName(name);
		System.out.println("enter mobile number");
		String mobileNum=sc.next();
		while (true)
		{
			if (cabservice.validatePhoneNum(mobileNum))
			{
				logger.info("mobile number validates sucessfully");
				break;
			}
			else
			{
				System.err.println("Please enter valid mobile number");
				mobileNum= sc.next();
			}
		}
		long mobilenum=Long.parseLong(mobileNum);
		cabRequest.setPhoneNum(mobilenum);
		System.out.println("enter address");
		
		
		System.out.println("enter pincode");
		String pincode=sc.next();
		//long pinCode=Long.parseLong(pincode);
		cabRequest.setPinCode(pincode);
		String address=sc.next();
		cabRequest.setAddress(address);
		
		
		case "2":
			System.out.println("enter req id:");
			String requestId1 =sc.next();
			CabService cabserv= new CabService();
			try {
				if(cabserv.isValidRequestId(requestId1))
				{
					cabservice = new CabService();
					cabRequest = cabservice.getRequestDetails(Integer.parseInt(requestId1));
					if(cabRequest != null)
					{
						System.out.println("Name of the customer : "+cabRequest.getCustomerName());
						System.out.println("Request Status :"+cabRequest.getRequestStatus());
						System.out.println("Cab number :"+cabRequest.getCabNumber());
						System.out.println("Pick up Address : "+cabRequest.getAddress());
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "3":
			System.out.println("Exit");
			System.exit(0);
			break;
		default:
				System.out.println("Enter corect input");
			}
			
	}
	
	

}
