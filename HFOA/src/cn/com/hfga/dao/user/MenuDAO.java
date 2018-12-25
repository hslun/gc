package cn.com.hfga.dao.user;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cn.com.hfga.dao.common.SpringDataDAO;
import cn.com.hfga.entity.user.MenuEntity;


@Repository("menuDAO")

public interface MenuDAO extends SpringDataDAO<MenuEntity>{
	
//	@Query(nativeQuery=true,value="select * from Department")
//	public List<MenuEntity> findAll();
	
	//根据部门名称获取ID
	@Query(nativeQuery=true,value="select * from department_menu where DepartmentId=:DepartmentId")
	public List<MenuEntity> getByDeptId(@Param(value="DepartmentId") String DepartmentId);
}
