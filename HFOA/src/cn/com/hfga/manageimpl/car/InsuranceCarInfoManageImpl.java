package cn.com.hfga.manageimpl.car;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.hfga.dao.car.InsuranceCarInfoDAO;
import cn.com.hfga.entity.car.InsuranceCarInfoEntity;
import cn.com.hfga.manager.car.InsuranceCarInfoManage;
@Service("insuranceCarInfoManage")
public class InsuranceCarInfoManageImpl implements InsuranceCarInfoManage{

	@Autowired
	private InsuranceCarInfoDAO insuranceCarInfoDAO;
	@Override
	public List<InsuranceCarInfoEntity> getAll(String carnum) {
		// TODO Auto-generated method stub
		return insuranceCarInfoDAO.getInfo(carnum);
	}



}
