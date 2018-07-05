package com.cg.service;

import java.sql.SQLException;

import com.cg.bean.DonorBean;
import com.cg.exception.CabApplicationException;

public interface ICabService {
	int addCabRequestDetails(DonorBean cabRequest) throws SQLException, CabApplicationException;
	DonorBean getRequestDetails(int requestId) throws SQLException;
	
	public boolean isValidDetails(DonorBean cabRequest) throws SQLException, CabApplicationException;
	public boolean isValidRequestId(String requestId1) throws SQLException, CabApplicationException;
	

}
