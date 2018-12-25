package cn.com.hfga.util.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.xml.bind.JAXBException;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import cn.com.hfga.log.common.ILog;


/**
 * the util class of Spring,which can hold the ApplicationContext of Spring.
 * @author zhang.haifeng
 * since 2014-10-13
 */
public class SpringUtil implements ApplicationContextAware, DisposableBean, ILog {

	/**
	 * spring's ApplicationContext.
	 */
	private static ApplicationContext applicationContext = null;
	
	/**
	 * get the ApplicationContext.
	 */
	public static ApplicationContext getApplicationContext() {
		checkApplicationContextInjected();
		return applicationContext;
	}

	/**
	 * get the bean from the ApplicationContext of SpringHelper by bean's name.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBeanByName(String name) {
		checkApplicationContextInjected();
		return (T) applicationContext.getBean(name);
	}
	
	/**
	 * get the bean from the ApplicationContext of SpringHelper by bean's type.
	 */
	public static <T> T getBeanByType(Class<T> requiredType) {
		checkApplicationContextInjected();
		return applicationContext.getBean(requiredType);
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	@Override
	public void setApplicationContext(ApplicationContext nowApplicationContext)
			throws BeansException {
		if (SpringUtil.applicationContext != null) {
			logger.warn("SpringHelper's ApplicationContext will be overrided, original ApplicationContext:" + applicationContext);
		} else {
			logger.info("Spring Helper init ApplicationContext");
		}

		SpringUtil.applicationContext = nowApplicationContext;
		
		//load properties
		try {
			PropertyUtil.loadProperties();
		} catch (IOException e) {
			logger.error("properties load failture");
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		//load query
		/*try {
			QueryUtil.loadQuerys();
		} catch (FileNotFoundException e) {
			logger.error("query config:file not find");
			e.printStackTrace();
			throw new RuntimeException();
		} catch (IOException e) {
			logger.error("query config:IO exception");
			e.printStackTrace();
			throw new RuntimeException();
		} catch (JAXBException e) {
			logger.error("query config:jaxb exception");
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		try {
			TaskUtil.doExecuteTask();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}*/
	}

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.DisposableBean#destroy()
	 */
	@Override
	public void destroy() throws Exception {
		SpringUtil.clearHolder();
		
	}
	
	/**
	 * clear the applicationContext of SpringHelper to null.
	 */
	public static void clearHolder() {
		logger.debug("clear the applicationContext of SpringHelper:" + applicationContext);
		applicationContext = null;
	}

	/**
	 * check if ApplicationContext is null
	 */
	private static void checkApplicationContextInjected() {
		Validate.validState(applicationContext != null, "applicaitonContext not injected, please define SpringContextHolder at applicationContext.xml.");
	}

}
