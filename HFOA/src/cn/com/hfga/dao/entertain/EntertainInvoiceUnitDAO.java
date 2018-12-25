package cn.com.hfga.dao.entertain;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cn.com.hfga.dao.common.SpringDataDAO;
import cn.com.hfga.entity.entertain.EntertainInvoiceUnitEntity;

@Repository("entertainInvoiceUnitDAO")
public interface EntertainInvoiceUnitDAO extends SpringDataDAO<EntertainInvoiceUnitEntity>{

	// �洢�µķ�Ʊ���ߵ�λ����
	@Modifying
	@Query(nativeQuery = true, value = "insert into B_EntertainInvoiceUnit(ID,InvoiceUnit)  VALUES(:ID,:InvoiceUnit)")
	int saveType(@Param(value = "ID") String ID,@Param(value = "InvoiceUnit") String InvoiceUnit);

	// ��ȡ��Ʊ���ߵ�λ����
	@Query(nativeQuery = true, value = "select * from B_EntertainInvoiceUnit ORDER BY convert(InvoiceUnit USING gbk)")
	List<EntertainInvoiceUnitEntity> getInvoiceUnitType();


	
	
}
