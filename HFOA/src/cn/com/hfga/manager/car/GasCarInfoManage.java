package cn.com.hfga.manager.car;

import java.util.List;

import cn.com.hfga.entity.car.GasCarInfoEntity;

public interface GasCarInfoManage {
	
	//获取某亮车的加油信息
	public List<GasCarInfoEntity> getAll(String carnum);

}
