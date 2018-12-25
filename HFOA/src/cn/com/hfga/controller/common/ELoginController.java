   package cn.com.hfga.controller.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.hfga.dto.user.UserDTO;
import cn.com.hfga.entity.common.SysMenuEntity;
import cn.com.hfga.entity.log.OperationRecord;
import cn.com.hfga.entity.user.MenuEntity;
import cn.com.hfga.entity.user.UserEntity;
import cn.com.hfga.manageimpl.entertain.EntertainInfoManageImpl;
import cn.com.hfga.manager.common.UserMenuManage;
import cn.com.hfga.manager.log.OperateRecordManage;
import cn.com.hfga.manager.user.MenuManager;
import cn.com.hfga.manager.user.UserManager;
import cn.com.hfga.util.CommonUtil;
import cn.com.hfga.util.common.MD5Util;

@Controller
public class ELoginController {
	@Autowired
	private EntertainInfoManageImpl entertainInfoManageImpl;
	@Autowired
	private OperateRecordManage operateRecordManage;
	@Autowired
	private UserManager userManager;
	@Autowired
	private UserMenuManage userMenuManage;
	
//	@Autowired
//	private MenuManager menuManager;
	@RequestMapping(value = "/elogin")
	@ResponseBody
	public String login(HttpServletRequest request) {
		String flag;
		
		String username = request.getParameter("username");// 获取用户名
		String pword = entertainInfoManageImpl.getPassword(username);
		
		UserEntity user = new UserEntity();
		UserDTO userDto = new UserDTO();
		userDto.setCode(username);
		List<UserEntity> users = userManager.searchUserByCode(userDto);
		if(users!=null&&users.get(0)!=null){
			user = users.get(0);
		}
		int deptId = user.getDepartmentId();
		String description = user.getDescription();//admin标识字段
		String qicq = user.getQICQ();//私车菜单字段
		
		
		List<SysMenuEntity> menus = userMenuManage.getByUserId(String.valueOf(user.getId()));
		System.out.println("menus是"+menus);
		if (pword==null){pword="";}
		String password = request.getParameter("password");// 获取密码
		String p = MD5Util.toMD5(password);
		if (pword.equals(p)) {
			if("9".equals(description)){
				flag = String.valueOf(description);
			}else if("1".equals(qicq)){
				flag = String.valueOf("3");
			}else{
				flag = String.valueOf(deptId);// 跳转
			}
			OperationRecord operationRecord = new OperationRecord();
			operationRecord.setOperateInfo(username+"登录了系统");
			operationRecord.setRealName(username);
			operateRecordManage.insert(operationRecord);
			request.getSession().setAttribute("username", username);
			request.getSession().setAttribute("user", user);
			request.getSession().setAttribute("titles", menus);
			request.getSession().setAttribute("menus", menus);
		} else {
			flag = "0";// 用户名或密码错误！
		}
		return flag;
	}
	
	//个人中心-用户密码匹配
	 @RequestMapping(value = "/matchPassword")
	 @ResponseBody
		public Object updateSelfAdminInfo(String input,String name) {
		String inputPassword = CommonUtil.getInstance().bytesToMD5(input.getBytes());
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
		jsonMap.put("inputPassword", inputPassword);
		jsonMap.put("myPassword", entertainInfoManageImpl.getPassword(name));
		return jsonMap;
	} 
	 
	 // 保存密码
	@RequestMapping("/savePassword")
	@ResponseBody
	public Object savePassword(String parameter) {
		String[] aa = parameter.split(",");
		String name = aa[1];
		String upassword=CommonUtil.getInstance().bytesToMD5(aa[0].getBytes());
		return entertainInfoManageImpl.updatePassword(upassword,name);
	}
	@RequestMapping("/booleanTrimDate")
	@ResponseBody
	public Object findByIdDate(HttpServletRequest request){
		UserEntity user = (UserEntity)request.getSession().getAttribute("user");
		return userManager.findByIdDate(user.getId().toString());
	}
}
