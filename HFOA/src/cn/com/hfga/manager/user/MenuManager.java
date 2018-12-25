package cn.com.hfga.manager.user;

import java.util.List;

import cn.com.hfga.entity.common.SysMenuEntity;
import cn.com.hfga.entity.user.MenuEntity;

//菜单管理类

public  interface MenuManager {
	
	public   List<MenuEntity> getByDeptId(String departmentId);
	
	public List<SysMenuEntity> getAll();
}
