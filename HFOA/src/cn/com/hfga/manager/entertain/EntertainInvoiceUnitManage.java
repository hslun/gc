package cn.com.hfga.manager.entertain;

import java.util.List;

import cn.com.hfga.entity.entertain.EntertainInvoiceUnitEntity;

public interface EntertainInvoiceUnitManage {

	// �洢�µķ�Ʊ���ߵ�λ����
	int saveInvoiceUnitType(String InvoiceUnitName);

	// ��ȡ��Ʊ���ߵ�λ����
	List<EntertainInvoiceUnitEntity> getInvoiceUnitType();

}
