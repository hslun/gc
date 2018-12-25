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

	// 获取招待客户名称
	@Query(nativeQuery = true, value = "select * from B_EntertainObjectType ORDER BY convert(OName USING gbk)")
	public List<EntertainObjectTypeEntity> getType();

	// 存储新的招待客户名称
	@Modifying
	@Query(nativeQuery = true, value = "insert into B_EntertainObjectType(OName)  VALUES(:OName)")
	public int saveType(@Param(value = "OName") String OName);

	
	// web-获得招待对象列表
	@Query(nativeQuery = true, value = "select ID,OName from B_EntertainObjectType ORDER BY convert(OName USING gbk)")
	public List<EntertainObjectTypeEntity> getAllObject();
	
}
