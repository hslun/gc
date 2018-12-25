package cn.com.hfga.controller.common;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/entertainWebLogin")
@ResponseBody
public class EntertainWebLoginController {
	
/*	// 登录
	@RequestMapping(value = "checkLogin")
	@ResponseBody
	// 自动将放回list对象的结果转换为json
	public List<UserEntity> checkLogin(UserDTO userDTO) {
		return userManager.checkLogin();
	}*/
	
	/*	// 用户登录日志存储
	@RequestMapping(value = "/loginSave")
	@ResponseBody
	// 自动将放回list对象的结果转换为json
	public int loginSave(String userName) {
		return userManager.loginSave(userName,"登录");
	}*/
	
	/*	// 用户退出日志存储
	@RequestMapping(value = "/logoutSave")
	@ResponseBody
	// 自动将放回list对象的结果转换为json
	public int logoutSave(String userName) {
		return userManager.logoutSav(userName,"退出");
	}*/
}
