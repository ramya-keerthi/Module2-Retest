package com.capgemini.cab.service;

import java.sql.SQLException;

import com.capgemini.cab.exception.CabApplicationException;
import com.capgemini.cabs.bean.CabRequest;

public interface ICabService {

	int addCabRequestDetails(CabRequest cabRequest) throws SQLException, CabApplicationException;
	CabRequest getRequestDetails(int requestId) throws SQLException, CabApplicationException;
	
	public boolean isValidDetails(CabRequest cabRequest) throws SQLException, CabApplicationException;
	public boolean isValidRequestId(String requestId1) throws CabApplicationException;
	
}
