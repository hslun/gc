package cn.com.hfga.manager.car;

import java.util.List;

import cn.com.hfga.entity.car.TrafficCarInfoEntity;

public interface TrafficCarInfoManage {

	public  List<TrafficCarInfoEntity> getAll(String carnum);
}
