package cn.com.hfga.manager.car;

import java.util.List;

import cn.com.hfga.entity.car.PunishCarInfoEntity;

public interface PunishCarInfoManage {

	//��ȡ������Ϣ
	public List<PunishCarInfoEntity> getAll(String carcode);
}
