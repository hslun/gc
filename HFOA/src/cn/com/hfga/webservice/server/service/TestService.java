package cn.com.hfga.webservice.server.service;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;


@WebService(name="TestService", targetNamespace=IService.NS)
public interface TestService extends IService{
	
	
}
