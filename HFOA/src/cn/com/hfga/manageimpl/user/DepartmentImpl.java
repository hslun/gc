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

	//���ݲ������ƻ�ȡID
	@Transactional
	@Override
	public List<DepartmentEntity> getByName(String departmentName) {
		return departmentDAO.getByName(departmentName);
	}

	/**
	 * ��ȡ���в���
	 */
	@Override
	public List<DepartmentEntity> getAllDept(int start, int number) {
		return departmentDAO.getAllDept(start,number);
	}

	/**
	 * ��ȡ��������
	 */
	@Override
	public int getAllDeptCount() {
		return departmentDAO.getAllDeptCount();
	}

	/**
	 * ����
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
	 * �޸�
	 */
	@Transactional
	@Override
	public int updateDept(DepartmentEntity dept) {
		return departmentDAO.updateDept(String.valueOf(dept.getID()), dept.getDepartmentName());
	}
	
}
