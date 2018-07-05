package com.capgemini.bank.ui;

import java.util.Scanner;






import com.capgemini.bank.bean.DemandDraft;
import com.capgemini.bank.exception.DemandDraftException;
import com.capgemini.bank.service.DemandDraftService;
import com.capgemini.bank.service.IDemandDraftService;

public class Client {

	static IDemandDraftService iDemandDraftService;
	
	public static void main(String[] args) throws DemandDraftException {
		
		Scanner scanner = new Scanner(System.in);
		DemandDraft demandDraft;
		String choice = "-1";
		int transactionId=-1;
		
		String customerName;
		String inFavourOf;
		String phoneNumber;
		String demandDraftAmount;
		String demandDraftDescription;
		
		
		System.out.println("\n  XYZ Banking Application ");
	
			
			System.out.println("\nSelect from Menu :");
			System.out.println("	1) Enter Demand Draft Details ");
			System.out.println("	2) Print Demand Draft ");
			System.out.println("	3) Exit");
			System.out.println("\nEnter your option:");
			
			choice=scanner.next();
			
			switch (choice)
			{
				case "1":
					System.out.print("Enter name of the customer :");
					customerName = scanner.next();
					System.out.print("Enter customer phone numbner :");
					phoneNumber =scanner.next();
					System.out.print("In favour of :");
					inFavourOf = scanner.next();
					System.out.print("Enter Demand Draft amount (in Rs) :");
					demandDraftAmount = scanner.next();
					System.out.print("Enter Remarks :");
					demandDraftDescription = scanner.next();
					
					// create DemandDraft object and set values into DemandDraft object.
					demandDraft= new DemandDraft();
					
					demandDraft.setCustomerName(customerName);
					demandDraft.setPhoneNumber(phoneNumber);
					demandDraft.setInFavourOf(inFavourOf);
					demandDraft.setDemandDraftAmount(demandDraftAmount);
					demandDraft.setDemandDraftDescription(demandDraftDescription);
					
					DemandDraftService demandDraftService = new DemandDraftService();
					
				try {
					if(demandDraftService.isValidDetails(demandDraft)){
						iDemandDraftService = new DemandDraftService();
						transactionId= iDemandDraftService.addDemandDraftDetails(demandDraft);
						if(transactionId!=-1){
							System.out.println("Your Demand Draft request has been successfully registered aalong with the " + transactionId);
							
						}
						else{
							System.out.println("Unable to Insert details and generate Purchase ID.");
						}
						
					}
					else{
						System.out.println("Please Enter valid Data.");
					}
					
				} catch (DemandDraftException exception) {
					System.err.println(exception.getMessage());
				}
					
					
					break;
					
				case "2":
					System.out.println("Enter transcation ID :");
					String transactionId1=scanner.next();
					 DemandDraftService demandDraftService1 = new DemandDraftService();
				
					if(demandDraftService1.isValidTransactionID(transactionId1)){
					iDemandDraftService = new DemandDraftService();
					demandDraft= iDemandDraftService.getDemandDraftDetails(Integer.parseInt(transactionId1));
					if(demandDraft!=null){
						System.out.println("Name of the bank	: XYZ");
						System.out.println("DD Amount 		:Rs. "+demandDraft.getDemandDraftAmount());
						System.out.println("DD Commission 		:Rs. "+demandDraft.getDemandDraftCommission());
						System.out.println("Total Amount  		:Rs. "+demandDraft.getTotalAmount());
						System.out.println("Remarks 		: "+demandDraft.getDemandDraftDescription());
					}
					}
				
					break;
					
				case "3":
					System.out.println("Exit.");
					System.exit(0);
					
					break;
				default:
					System.out.println("Enter appropriate Input.!!");
					
			}
			
			
	
		
	}

}
