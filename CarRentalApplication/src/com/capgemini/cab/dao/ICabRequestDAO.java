package com.capgemini.cab.dao;

import java.sql.SQLException;

import com.capgemini.cab.exception.CabApplicationException;
import com.capgemini.cabs.bean.CabRequest;

public interface ICabRequestDAO {
	int addCabRequestDetails(CabRequest cabRequest) throws SQLException, CabApplicationException;
	CabRequest getRequestDetails(int requestId) throws SQLException, CabApplicationException;
	public boolean isValidDetails(CabRequest cabRequest) throws SQLException, CabApplicationException;
	public boolean isValidRequestId(String requestId1) throws CabApplicationException;
}
