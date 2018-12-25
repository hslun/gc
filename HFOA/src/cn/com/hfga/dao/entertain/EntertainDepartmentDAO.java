package cn.com.hfga.dao.entertain;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cn.com.hfga.dao.common.SpringDataDAO;
import cn.com.hfga.entity.entertain.EntertainDepartmentEntity;
import cn.com.hfga.entity.user.DutyEntity;

@Repository("entertainDepartmentDAO")
public interface EntertainDepartmentDAO extends SpringDataDAO<EntertainDepartmentEntity> {
	
	//web-获得部门列表
	@Query(nativeQuery = true, value = "select * from Department")
	List<EntertainDepartmentEntity> getAllDepartment();
	
}
