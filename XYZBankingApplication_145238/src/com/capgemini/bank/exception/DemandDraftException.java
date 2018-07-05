package com.capgemini.bank.exception;

public class DemandDraftException extends Exception {


	private static final long serialVersionUID = -7555244474278408146L;  // for serializable class
	
	public DemandDraftException() {
		super();
	}	
	public DemandDraftException(String message) 
	{
		super(message);
		
	}
}
