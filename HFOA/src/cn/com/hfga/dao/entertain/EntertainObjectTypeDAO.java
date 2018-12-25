package cn.com.hfga.dao.entertain;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cn.com.hfga.dao.common.SpringDataDAO;
import cn.com.hfga.entity.entertain.EntertainObjectTypeEntity;

@Repository("entertainObjectTypeDAO")
public interface EntertainObjectTypeDAO extends SpringDataDAO<EntertainObjectTypeEntity>{

	// ��ȡ�д��ͻ�����
	@Query(nativeQuery = true, value = "select * from B_EntertainObjectType ORDER BY convert(OName USING gbk)")
	public List<EntertainObjectTypeEntity> getType();

	// �洢�µ��д��ͻ�����
	@Modifying
	@Query(nativeQuery = true, value = "insert into B_EntertainObjectType(OName)  VALUES(:OName)")
	public int saveType(@Param(value = "OName") String OName);

	
	// web-����д������б�
	@Query(nativeQuery = true, value = "select ID,OName from B_EntertainObjectType ORDER BY convert(OName USING gbk)")
	public List<EntertainObjectTypeEntity> getAllObject();
	
}
