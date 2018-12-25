package cn.com.hfga.manager.car;

import java.util.List;

import cn.com.hfga.entity.car.InsuranceCarInfoEntity;

public interface InsuranceCarInfoManage {

	public List<InsuranceCarInfoEntity> getAll(String carnum);
}
