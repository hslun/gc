package cn.com.hfga.manager.entertain;

import java.util.List;

import cn.com.hfga.entity.entertain.EntertainInvoiceUnitEntity;

public interface EntertainInvoiceUnitManage {

	// 存储新的发票出具单位名称
	int saveInvoiceUnitType(String InvoiceUnitName);

	// 获取发票出具单位名称
	List<EntertainInvoiceUnitEntity> getInvoiceUnitType();

}
