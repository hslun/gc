package com.hfoa.dao.privatecar;

import java.util.List;
import java.util.Map;

import com.hfoa.entity.privatecar.PrivateCarEntity;
import com.hfoa.entity.privatecar.PrivatecarinvoiceEntity;
import com.hfoa.entity.vacation.LearYear;


public interface PrivatecarinvoiceMapper {
	
	int insertPrivateCarinvoice(PrivatecarinvoiceEntity privatecarinvoiceEntity);
	
	int updatePrivateCarinvoice(PrivatecarinvoiceEntity privatecarinvoiceEntity);
	
	int deletePrivateCarinvoice(String applyId);
	
	List<PrivatecarinvoiceEntity> selectPrivatecarinvoiceEntity();
	
	List<PrivatecarinvoiceEntity> unSignInvoiceDisplay();
	
	List<PrivatecarinvoiceEntity> invoiceDisplay();
	
	List<PrivatecarinvoiceEntity> invoiceDisplayApplyTime(String beingTime,String endTime);
	
	PrivatecarinvoiceEntity getApplyIdsPrivatecarinvoiceEntity(String applyIds);
	
	PrivatecarinvoiceEntity getApplyIdPrivatecarinvoiceEntity(String applyId);
	
	List<PrivatecarinvoiceEntity> applymanPrivatecarinvoice(String applyMan,String applyTime);

}