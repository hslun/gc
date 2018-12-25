package cn.com.hfga.manager.car;

import java.util.List;

import cn.com.hfga.entity.car.PunishCarInfoEntity;

public interface PunishCarInfoManage {

	//获取罚款信息
	public List<PunishCarInfoEntity> getAll(String carcode);
}
