package com.capgemini.bank.dao;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.capgemini.bank.bean.DemandDraft;
import com.capgemini.bank.exception.DemandDraftException;


public class TestDemanDraftDAO {

		IDemandDraftDAO demandDraftDAO;
		
		@Before
		public void setUP(){
			demandDraftDAO=new DemandDraftDAO();
		}
		
		 @Test
	        public void testAddDetails1() throws DemandDraftException {
			  DemandDraft demandDraft = new DemandDraft();
			  demandDraft.setCustomerName("John");
				demandDraft.setPhoneNumber("9768587350");
				demandDraft.setInFavourOf("Capgemini");
				demandDraft.setDemandDraftAmount("45000");
				demandDraft.setDemandDraftDescription("DD taken in favour of Capgemini");
			  
				try {
					int transactionId = demandDraftDAO.addDemandDraftDetails(demandDraft);
					assertNotEquals(10001, transactionId);
				} catch (DemandDraftException exception) {
					throw new DemandDraftException(exception.getMessage());
				}
			}
		 
		  @Test
		 public void testAddDetails2() throws DemandDraftException {
			  DemandDraft demandDraft = new DemandDraft();
			  demandDraft.setCustomerName("John");
				demandDraft.setPhoneNumber("9768587350");
				demandDraft.setInFavourOf("Capgemini");
				demandDraft.setDemandDraftAmount("45000");
				demandDraft.setDemandDraftDescription("DD taken in favour of Capgemini");
			  
				try {
					int transactionId = demandDraftDAO.addDemandDraftDetails(demandDraft);
					assertSame(10014, transactionId);
				} catch (DemandDraftException exception) {
					throw new DemandDraftException(exception.getMessage());
				}
			}
		  @Test
	        public void testgetDemandDraftDetails() {
	                        try {
	                        	int transactionId =10006;
	                        	 DemandDraft demandDraft = demandDraftDAO.getDemandDraftDetails(transactionId);
	                                        assertNotNull(demandDraft);
	                        } catch (DemandDraftException exception) {
	                                        fail(" exception occured "+exception.getMessage());
	                        }
	        }
		 
	


}
