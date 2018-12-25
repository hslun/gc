package cn.com.hfga.manageimpl.entertain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.hfga.dao.entertain.EntertainDepartmentDAO;
import cn.com.hfga.entity.entertain.EntertainDepartmentEntity;
import cn.com.hfga.entity.user.DutyEntity;
import cn.com.hfga.manager.entertain.EntertainDepartmentManage;
@Service("entertainDepartmentManageImpl")
public class EntertainDepartmentManageImpl implements EntertainDepartmentManage{

	@Autowired
	private EntertainDepartmentDAO entertainDepartmentDAO;

	// web-获得部门列表
	@Transactional
	@Override
	public List<EntertainDepartmentEntity> getAllDepartment() {
		List<EntertainDepartmentEntity> list = new ArrayList<EntertainDepartmentEntity>();
		list = entertainDepartmentDAO.getAllDepartment();
		EntertainDepartmentEntity all = new EntertainDepartmentEntity();
//		all.setDepartmentName("全部");all.setID("10");all.setDepartId("11");
//		list.add(0, all);
		return list;
	}

}
