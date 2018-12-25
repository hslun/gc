package cn.com.hfga.manageimpl.car;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.hfga.dao.car.GasCarInfoDAO;
import cn.com.hfga.entity.car.GasCarInfoEntity;
import cn.com.hfga.manager.car.GasCarInfoManage;
@Service("gasCarInfoManage")
public class GasCarInfoManageImpl implements GasCarInfoManage {

	@Autowired
	private GasCarInfoDAO gasCarInfoDAO;
	@Override
	public List<GasCarInfoEntity> getAll(String carnum) {
		// TODO Auto-generated method stub
		return  gasCarInfoDAO.getInfo(carnum);
	}

}
