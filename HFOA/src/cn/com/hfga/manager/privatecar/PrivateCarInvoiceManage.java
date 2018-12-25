package cn.com.hfga.manager.privatecar;

import java.util.List;

import cn.com.hfga.dto.privatecar.PrivateCarInvoiceDTO;
import cn.com.hfga.entity.privatecar.PrivateCarApplyEntity;
import cn.com.hfga.entity.privatecar.PrivateCarInvoiceEntity;

/**
 * 
 * @author ysy
 *
 */

public interface PrivateCarInvoiceManage {
	
	Object Save(PrivateCarInvoiceDTO pto);
	
	//获取待报销列表
	List<PrivateCarInvoiceEntity> selectAllUnPass();
	//获取已报销列表
	List<PrivateCarInvoiceEntity> selectAllPassed();
	//财务添加凭证号登记
	int updateInvoice(PrivateCarInvoiceDTO pto);
	
	PrivateCarInvoiceEntity selectByApplyId(String applyid);
	
	public String getApplyidsByid(String id);
	
	public List<PrivateCarApplyEntity> selectChildByApplyId(String applyid);
	
	public List<PrivateCarInvoiceEntity> selectPassed();
	
	public List<PrivateCarInvoiceEntity> invoiceDisplay(int start,int number);
	
	public int getAllCount();
	
	public List<PrivateCarInvoiceEntity> unSignInvoiceDisplay(int start,int number);
	
	public int getAllUnSignInvoiceCount();
	
	public List<PrivateCarInvoiceEntity> uninvoiceDisplay(int start,int number);
	
	public int ungetAllCount();
	
	public int importPrivateCarInvoiceExcel(String fileName);
	
	public List<PrivateCarInvoiceEntity> selectBackList(String username);
	
	public int deleteByApplyid(String applyid);
	
	public int export(String[] nlist, String filePath);
	
	public List<PrivateCarApplyEntity> selectChildByApplyIdFinish(String applyid);
	
	public List<PrivateCarApplyEntity> selectChildByApplyIdFinish1(String applyid);
	public abstract PrivateCarInvoiceEntity getByNumber(String paramString);
	  
	public abstract void deleteByApplyIds(String paramString);

	Object regist(PrivateCarInvoiceDTO p);

	PrivateCarInvoiceEntity getByNumberes(String string);
}
