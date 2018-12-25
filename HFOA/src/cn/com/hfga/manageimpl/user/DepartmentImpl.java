package cn.com.hfga.manageimpl.user;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.hfga.dao.user.DepartmentDAO;
import cn.com.hfga.entity.user.DepartmentEntity;
import cn.com.hfga.log.common.ILog;
import cn.com.hfga.manager.user.DepartmentManager;

@Service("departmentManager")
public class DepartmentImpl implements DepartmentManager,ILog{
	

	@Autowired
	private DepartmentDAO departmentDAO;
	
	@Transactional
	@Override
	public List<DepartmentEntity> getAll()
	{
		return departmentDAO.findAll(); 
	}

	//根据部门名称获取ID
	@Transactional
	@Override
	public List<DepartmentEntity> getByName(String departmentName) {
		return departmentDAO.getByName(departmentName);
	}

	/**
	 * 获取所有部门
	 */
	@Override
	public List<DepartmentEntity> getAllDept(int start, int number) {
		return departmentDAO.getAllDept(start,number);
	}

	/**
	 * 获取部门总数
	 */
	@Override
	public int getAllDeptCount() {
		return departmentDAO.getAllDeptCount();
	}

	/**
	 * 保存
	 */
	@Transactional
	@Override
	public int saveDept(DepartmentEntity dept) {
		int id = departmentDAO.getMaxId()+1;
		dept.setID(id);
		dept.setDepartId(String.valueOf(id));
		return departmentDAO.saveDept(dept.getID(), dept.getDepartmentName(), dept.getDepartId());
	}

	/**
	 * 修改
	 */
	@Transactional
	@Override
	public int updateDept(DepartmentEntity dept) {
		return departmentDAO.updateDept(String.valueOf(dept.getID()), dept.getDepartmentName());
	}
	
}
