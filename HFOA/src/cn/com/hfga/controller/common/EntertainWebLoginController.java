package cn.com.hfga.controller.common;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/entertainWebLogin")
@ResponseBody
public class EntertainWebLoginController {
	
/*	// ��¼
	@RequestMapping(value = "checkLogin")
	@ResponseBody
	// �Զ����Ż�list����Ľ��ת��Ϊjson
	public List<UserEntity> checkLogin(UserDTO userDTO) {
		return userManager.checkLogin();
	}*/
	
	/*	// �û���¼��־�洢
	@RequestMapping(value = "/loginSave")
	@ResponseBody
	// �Զ����Ż�list����Ľ��ת��Ϊjson
	public int loginSave(String userName) {
		return userManager.loginSave(userName,"��¼");
	}*/
	
	/*	// �û��˳���־�洢
	@RequestMapping(value = "/logoutSave")
	@ResponseBody
	// �Զ����Ż�list����Ľ��ת��Ϊjson
	public int logoutSave(String userName) {
		return userManager.logoutSav(userName,"�˳�");
	}*/
}
