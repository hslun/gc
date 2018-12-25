package cn.com.hfga.manageimpl.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.hfga.dao.common.SysMenuDAO;
import cn.com.hfga.dao.user.MenuDAO;
import cn.com.hfga.entity.common.SysMenuEntity;
import cn.com.hfga.entity.user.MenuEntity;
import cn.com.hfga.log.common.ILog;
import cn.com.hfga.manager.user.MenuManager;

@Service("menuManager")
public class MenuImpl implements MenuManager,ILog{

	@Autowired
	private MenuDAO menuDAO;
	@Autowired
	private SysMenuDAO sysMenuDAO;
	
	@Transactional
	@Override
	public List<MenuEntity> getByDeptId(String departmentId) {
		return menuDAO.getByDeptId(departmentId);
	}

	@Override
	public List<SysMenuEntity> getAll() {
		return sysMenuDAO.getAll();
	}
	
}
