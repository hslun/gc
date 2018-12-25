package cn.com.hfga.controller.common;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.com.hfga.manager.common.UserMenuManage;
import cn.com.hfga.manager.user.MenuManager;
/**
 * 
 * @author ysy
 *
 */
@Controller
public class SysMenuController {

	@Autowired
	private MenuManager sysMenuManage;
	@Autowired
	private UserMenuManage userMenuManage;
	
	//获取全部菜单
	@RequestMapping(value="/Menu/getAllMenus")
	@ResponseBody
	public Object getAllMenus(){
		return sysMenuManage.getAll();
	}
	
	//保存用户菜单
	@RequestMapping(value="/Menu/saveUserMenus")
	@ResponseBody
	public Object saveUserMenus(String menuids,String userid){
		//保存前先删除
		userMenuManage.delete(userid);
		int q=0;
		String[] menus = menuids.split(",");
		for(String menuid:menus){
			q = userMenuManage.save(userid, menuid);
		}
		return q;
	}
	
	@RequestMapping(value="/Menu/getByUserId")
	@ResponseBody
	public Object getByUserId(String userid){
		return userMenuManage.getByUserId(userid);
	}
	
}
