package cn.com.hfga.manager.user;

import java.util.List;

import cn.com.hfga.entity.user.DepartmentEntity;

//部门管理类

public  interface DepartmentManager {
	
	public List<DepartmentEntity> getAll();
	
	public List<DepartmentEntity> getByName(String departmentName);
	
	public List<DepartmentEntity> getAllDept(int start, int number);
	
	public int getAllDeptCount();
	
	public int saveDept(DepartmentEntity dept);
	
	public int updateDept(DepartmentEntity dept);
}
