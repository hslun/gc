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

	// ��ȡ�д��ͻ�����
	@Transactional
	@Override
	public List<EntertainObjectTypeEntity> getType() {
		return entertainObjectTypeDAO.getType();
	}

	// �洢�µ��д��ͻ�����
	
	@Transactional
	@Override
	public int saveType(String OName) {
		if(OName!=null&&!"".equals(OName)){
			return entertainObjectTypeDAO.saveType(OName);
		}else{
			return 1;
		}
	}

	 // web-����д������б�
	public List<EntertainObjectTypeEntity> getAllObject() {
		List<EntertainObjectTypeEntity> list = new ArrayList<EntertainObjectTypeEntity>();
		list = entertainObjectTypeDAO.getAllObject();
		EntertainObjectTypeEntity all = new EntertainObjectTypeEntity();
		all.setOName("ȫ��");
		list.add(0, all);
		return list;
	}
}
