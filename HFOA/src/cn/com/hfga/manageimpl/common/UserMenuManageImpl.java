package cn.com.hfga.manageimpl.common;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.hfga.dao.common.SysMenuDAO;
import cn.com.hfga.dao.common.UserMenuDAO;
import cn.com.hfga.entity.common.SysMenuEntity;
import cn.com.hfga.entity.common.UserMenuEntity;
import cn.com.hfga.entity.user.MenuEntity;
import cn.com.hfga.manager.common.UserMenuManage;

/**
 * 
 * @author ysy
 *
 */
@Service("userMenuManage")
public class UserMenuManageImpl implements UserMenuManage{


	@Autowired
	private UserMenuDAO  userMenuDAO;
	@Autowired
	private SysMenuDAO  sysMenuDAO;
	
	@Transactional
	@Override
	public int save(String userid, String menuid) {
		return userMenuDAO.save(userid, Integer.parseInt(menuid));
	}

	@Override
	public List<SysMenuEntity> getByUserId(String userId) {
		List<SysMenuEntity> menus = new ArrayList<SysMenuEntity>();
		List<UserMenuEntity> list = userMenuDAO.getByUserId(userId);
		for(UserMenuEntity us:list){
			SysMenuEntity menu = sysMenuDAO.getById(us.getMenuId());
			menus.add(menu);
		}
		return menus;
	}

	@Transactional
	@Override
	public int delete(String userId) {
		return userMenuDAO.delete(userId);
	}

}
