package cn.com.hfga.manager.user;

import java.util.List;

import cn.com.hfga.entity.user.DepartmentEntity;
import cn.com.hfga.entity.user.DutyEntity;

//职位管理类

public  interface DutyManager {
	
	//Web-获取职位
	public List<DutyEntity> getDuty();
}
