package cn.com.hfga.webservice.server.serviceimpl;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import org.springframework.stereotype.Component;

import cn.com.hfga.dao.file.FileDAO;
import cn.com.hfga.entity.file.FileInfoEntity;
import cn.com.hfga.log.common.ILog;
import cn.com.hfga.util.common.SpringUtil;
import cn.com.hfga.webservice.server.service.ArithmeticService;

@Component("ArithmeticService")
public class ArithmeticServiceImpl implements ArithmeticService,ILog{

	/**
	 * 閿欒浠ｇ爜鏍囩ず
	 * 4001鍞竴鏍囩ず锛坈ode锛夐敊璇�鎴栬�瑭叉湇鍔″仠姝腑
	 * 4002鏃燡AR鏂囦欢
	 * 4003鏃犳晥绫昏矾寰勬垨鏂规硶
	 */
	@Override
	public String getArithmeticInfo(String code,String value) {
		return null;
	}

	 /**
     * 璋冪敤jar鍖呬腑鐨勬柟娉曪紝骞跺悜鍏朵紶鍏ュ弬鏁皃arameter锛屽苟杩斿洖杩斿洖鍊�
     * @param jarPlace  jar鐨勮矾寰�
	 * @param className 鍏朵腑绫诲湴鍧�
	 * @param methodName 鏂规硶鍚嶇О
	 * @param parameter 鍙傛暟
	 * @return
	 * @throws MalformedURLException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws InvocationTargetException
     */
	public String getValue(String jarPlace, String className,
			                 String methodName, String parameter) throws Exception {
		URL[] urls = new URL[] {};
        MyClassLoader classLoader = new MyClassLoader(urls, null);
	    classLoader.addJar(new File(jarPlace).toURI().toURL());
	    Class<?> clazz = classLoader.loadClass(className);		
	    Object  implementor = clazz.newInstance();

	    Method n = clazz.getDeclaredMethod(methodName,String.class); 
	    
		Object[] args1 ={new String(parameter)};
		
	    n.setAccessible(true);
	   
		String result=(String)n.invoke(implementor,args1) ;				
		return result;
	}
	
	public static class MyClassLoader extends URLClassLoader {

        public MyClassLoader(URL[] urls) {
            super(urls);
        }
        public MyClassLoader(URL[] urls, ClassLoader parent) {
            super(urls, parent);
        }
        public void addJar(URL url) {
            this.addURL(url);
        }

    }
}
