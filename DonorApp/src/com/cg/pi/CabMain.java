package com.cg.pi;

import java.sql.SQLException;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;






import com.cg.bean.DonorBean;
import com.cg.service.CabService;
import com.cg.service.ICabService;



public class CabMain {
	
	 static Logger logger =Logger.getRootLogger();
	 public CabMain() {
			PropertyConfigurator.configure("resources//log4j.properties");
		}
	 public static void main(String[] args) throws NumberFormatException, SQLException {
			PropertyConfigurator.configure("resources//log4j.properties");
			DonorBean cabRequest= new DonorBean();
			ICabService cabservice=new CabService();
			Scanner sc=new Scanner(System.in);
			String choice="-1";
			int request_id=-1;
			String pincode;
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
			cabRequest.setPhoneNum(mobileNum);
		
			System.out.println("Enter pincode");
			pincode=sc.next();
			
			cabRequest.setPincode(pincode);
			System.out.println("enter address");
			String address=sc.next();
			cabRequest.setAddress(address);
			break;
			
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

