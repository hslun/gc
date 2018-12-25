package cn.com.hfga.manageimpl.entertain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.hfga.dao.entertain.EntertainObjectTypeDAO;
import cn.com.hfga.entity.entertain.EntertainDepartmentEntity;
import cn.com.hfga.entity.entertain.EntertainObjectTypeEntity;
import cn.com.hfga.manager.entertain.EntertainObjectTypeManage;
@Service("entertainObjectTypeImpl")
public class EntertainObjectTypeManageImpl implements EntertainObjectTypeManage{
	@Autowired
	private EntertainObjectTypeDAO entertainObjectTypeDAO;

	// 获取招待客户名称
	@Transactional
	@Override
	public List<EntertainObjectTypeEntity> getType() {
		return entertainObjectTypeDAO.getType();
	}

	// 存储新的招待客户名称
	
	@Transactional
	@Override
	public int saveType(String OName) {
		if(OName!=null&&!"".equals(OName)){
			return entertainObjectTypeDAO.saveType(OName);
		}else{
			return 1;
		}
	}

	 // web-获得招待对象列表
	public List<EntertainObjectTypeEntity> getAllObject() {
		List<EntertainObjectTypeEntity> list = new ArrayList<EntertainObjectTypeEntity>();
		list = entertainObjectTypeDAO.getAllObject();
		EntertainObjectTypeEntity all = new EntertainObjectTypeEntity();
		all.setOName("全部");
		list.add(0, all);
		return list;
	}
}
