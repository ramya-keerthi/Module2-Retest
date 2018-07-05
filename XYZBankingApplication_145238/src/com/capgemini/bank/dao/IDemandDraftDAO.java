package com.capgemini.bank.dao;

import com.capgemini.bank.bean.DemandDraft;
import com.capgemini.bank.exception.DemandDraftException;

public interface IDemandDraftDAO {
	
	int addDemandDraftDetails(DemandDraft demandDraft) throws DemandDraftException;
	DemandDraft getDemandDraftDetails(int transactionId) throws DemandDraftException;
}
