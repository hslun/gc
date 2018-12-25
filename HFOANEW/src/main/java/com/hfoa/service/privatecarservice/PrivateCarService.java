package com.hfoa.service.privatecarservice;

import java.util.List;
import java.util.Map;

import com.hfoa.entity.permission.BPermissionEntity;
import com.hfoa.entity.privatecar.PrivateCarEntity;
import com.hfoa.entity.privatecar.PrivatecarinvoiceEntity;

/**
 * 
 * @author dzh
 * 私车service接口
 */
public interface PrivateCarService {

	int insertPrivateCar(PrivateCarEntity privateCarEntity);
	
	List<PrivateCarEntity> privateApplytion(String openId);
	
	int updatePrivateCar(PrivateCarEntity privateCarEntity);
	
	int deletePrivateCar(String applyId,String taskId);
	
	PrivateCarEntity getPrivateCar(String applyId);
	
	List<PrivateCarEntity> listOpendIdPrivateCar(String openId);
	
	int approvalPrivateCar(String applyId,String taskId,String result,String comment);
	
	List<PrivateCarEntity> staffPrivateCar(String openId);
	
	int staffPrivateCarApprove(String applyId,String taskId,String staffresult);
	
	List<PrivateCarEntity> reimbursementPrivateCar(String openId);
	
	int reimbursementPrivateCarApprove(PrivatecarinvoiceEntity privatecarinvoiceEntity,String taskId);
	
	List<PrivatecarinvoiceEntity> financePrivateCar(String openId);
	
	List<PrivateCarEntity> financePrivatreCartaskId(String openId,String applyIds,String canUpdate);
	
	int financePrivateCarApprove(String taskId,String applyId,String finaceresult,String applyIdinvoice,String voucherNum,String paidTime,String comment);
	
	int privateRegistervoucher(String taskId,String applyId,String voucherNum,String paidTime);
	
	List<PrivatecarinvoiceEntity> uninvoiceDisplay();
	
	List<PrivatecarinvoiceEntity> unSignInvoiceDisplay();
	
	List<PrivatecarinvoiceEntity> invoiceDisplay();
	
	List<PrivateCarEntity> selectByApplyIds(String applyId);
	
	List<PrivateCarEntity> selectByApplyIds(String applyId,String openId);
	
	Map<String,Object> searPrivateCar(PrivateCarEntity privateCarEntity,Integer nowPage,Integer pageSize);
	
	int passPrivateCars(String applyid,String applyId,String taskId);
	
	int backPrivateCars(String applyid,String taskId);
	
	int privateCarInvoiceexportExcel(String number,String filePath);
	
	int updateInvoice(PrivatecarinvoiceEntity privatecarinvoiceEntity);
	
	PrivatecarinvoiceEntity getOneRegister(String applyid);
	
	List<Map<String,Object>> countPrivate(String openId);
	
}
