package com.capgemini.bank.dao;

public class SQLFinalQueries {

	public static final String VIEW_DEMAND_DRAFT_DETAILS_QUERY="SELECT dd_amount,dd_commission,dd_description from demand_draft WHERE transaction_id=?";
	public static final String INSERT_QUERY="INSERT INTO demand_draft(transaction_id, customer_name,in_favor_of, phone_number, date_of_transaction ,dd_amount , dd_commission , dd_description ) VALUES (Transaction_Id_Seq.NEXTVAL,?,?,?,SYSDATE,?,?,?)";
	public static final String TRANSACTIONID_QUERY_SEQUENCE="SELECT Transaction_Id_Seq.CURRVAL from DUAL";
}
