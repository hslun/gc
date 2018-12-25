package cn.com.hfga.manageimpl.car;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.hfga.dao.car.PunishCarInfoDAO;
import cn.com.hfga.entity.car.PunishCarInfoEntity;
import cn.com.hfga.manager.car.PunishCarInfoManage;

@Service("punishCarInfoManage")
public class PunishCarInfoManageImpl implements PunishCarInfoManage{

	@Autowired
	private PunishCarInfoDAO punishCarInfoDAO;
	@Override
	public List<PunishCarInfoEntity> getAll(String carcode) {
		// TODO Auto-generated method stub
		return punishCarInfoDAO.getInfo(carcode) ;
	}

}
