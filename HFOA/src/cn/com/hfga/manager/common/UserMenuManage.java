package cn.com.hfga.manager.common;

import java.util.List;

import cn.com.hfga.entity.common.SysMenuEntity;
import cn.com.hfga.entity.common.UserMenuEntity;

/**
 * 
 * @author ysy
 *
 */

public interface UserMenuManage {
	
//	List<UserMenuEntity> getByUserId(String userid);
	
	public int save(String userid, String menuid);
	
	public List<SysMenuEntity> getByUserId(String userId);
	
	public int delete(String userId);
}
