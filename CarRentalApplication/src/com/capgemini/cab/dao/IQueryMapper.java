package com.capgemini.cab.dao;

public interface IQueryMapper {
	public static final String insertDetailsQuery= "INSERT INTO cabRequest VALUES(seq_request_id.nextval,?,?,SYSDATE,?,?,?,?)";
	public static final String ViewCustDetailsQuery= "SELECT customerName,requestStaus,cabNumber,addressOfPickup FROM cabRequest WHERE requestId=?";
	public static final String seqQuery="SELECT seq_request_id.nextval FROM DUAL";
	
}
