package cn.com.hfga.webservice.server.service;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;


@WebService(name="ArithmeticService", targetNamespace=IService.NS)
public interface ArithmeticService {

	@WebMethod(operationName="getArithmeticInfo")
	public @WebResult(name="result")String getArithmeticInfo(String code,String value);
	
}
