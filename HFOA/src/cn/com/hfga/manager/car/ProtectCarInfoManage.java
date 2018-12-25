package cn.com.hfga.manager.car;

import java.util.List;

import cn.com.hfga.entity.car.ProtectCarInfoEntity;

public interface ProtectCarInfoManage {

	
	public List<ProtectCarInfoEntity> getAll(String carnum);
}
