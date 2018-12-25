package cn.com.hfga.manageimpl.car;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.hfga.dao.car.ProtectCarInfoDAO;
import cn.com.hfga.entity.car.ProtectCarInfoEntity;
import cn.com.hfga.manager.car.ProtectCarInfoManage;
@Service("protectCarInfoManage")
public class ProtectCarInfoManageImpl implements ProtectCarInfoManage{

	@Autowired
	private ProtectCarInfoDAO protectCarInfoDAO;
	@Override
	public List<ProtectCarInfoEntity> getAll(String carnum) {
		// TODO Auto-generated method stub
		return protectCarInfoDAO.getInfo(carnum);
	}

}
