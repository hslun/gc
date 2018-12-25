package cn.com.hfga.util.shiro;


import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Service;

@Service
public class JsonAuthenticationFilter extends org.apache.shiro.web.filter.authc.FormAuthenticationFilter {


	protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
		String username = request.getParameter("userName");
		String password = request.getParameter("password");
		if (password==null){
			password = "";
		}
		boolean rememberMe = isRememberMe(request);
		String host = getHost(request);
		return new UsernamePasswordToken(username, password.toCharArray(), rememberMe, host);
	}
	
}