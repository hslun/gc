package cn.com.hfga.manager.car;

import java.util.List;

import cn.com.hfga.entity.car.GasCarInfoEntity;

public interface GasCarInfoManage {
	
	//��ȡĳ�����ļ�����Ϣ
	public List<GasCarInfoEntity> getAll(String carnum);

}
