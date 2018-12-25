package cn.com.hfga.manageimpl.car;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.hfga.dao.car.TrafficCarInfoDAO;
import cn.com.hfga.entity.car.TrafficCarInfoEntity;
import cn.com.hfga.manager.car.TrafficCarInfoManage;
@Service("trafficCarInfoManage")
public class TrafficCarInfoManageImpl implements TrafficCarInfoManage{

	@Autowired
	private TrafficCarInfoDAO trafficCarInfoDAO;
	@Override
	public List<TrafficCarInfoEntity> getAll(String carnum) {
		// TODO Auto-generated method stub
		return trafficCarInfoDAO.getInfo(carnum);
	}

}
