package cn.com.hfga.manager.entertain;

import java.util.List;

import cn.com.hfga.entity.entertain.EntertainObjectTypeEntity;

public interface EntertainObjectTypeManage {

	// 获取招待客户名称
	public List<EntertainObjectTypeEntity> getType();

	// 存储新的招待客户名称
	public int saveType(String OName);
}
