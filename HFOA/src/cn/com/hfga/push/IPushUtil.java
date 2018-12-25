package cn.com.hfga.push;



/**
 * ÍÆËÍ½Ó¿Ú
 * @author xinyuancai
 * 
 * 
 * */

public interface IPushUtil {
	
	public void sendBroadcast();
	
	public void sendUnicast();
	
	public void sendGroupcast();
	
	public void sendCustomizedcast();
	
	public void sendCustomizedcastFile();
	
	public void sendFilecast() ;
}
