package cn.com.hfga.util.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import cn.com.hfga.log.common.ILog;

/**
 * properties util class.
 * @author zhang.haifeng
 * since TODO
 */
public class PropertyUtil implements ILog {
	
	private final static PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();

	private static Properties properties;
	
	private static final String propertiesSearchPath = "conf/application.properties";
	
	public static void loadProperties() throws IOException {
		Resource [] resources = pathMatchingResourcePatternResolver.getResources(propertiesSearchPath);
		properties = new Properties();
		for(Resource resource : resources) {
			logger.info("begin load properties:"+resource.getURI());
			properties.load(resource.getInputStream());
			logger.info("end load properties:"+resource.getURI());
		}
	}
	
	public static String getPropertyInfo(String propertyKey) throws IOException{
		List <String> list = new ArrayList<String>();
		list.add(propertyKey);
		Map<String,String> resultmap =  getPropertyInfo(list);
		return resultmap.get(propertyKey);
	}
	
	public static Map<String,String> getPropertyInfo(List <String> propertyKeys) throws IOException {
		Resource [] resources = pathMatchingResourcePatternResolver.getResources(propertiesSearchPath);
		properties = new Properties();
		for(Resource resource : resources) {
			logger.info("begin load properties:"+resource.getURI());
			properties.load(resource.getInputStream());
			logger.info("end load properties:"+resource.getURI());
		}	
		Map <String, String> resultInfos = new HashMap<String, String>();
		for(String propertyKey : propertyKeys) {
			resultInfos.put(propertyKey,properties.get(propertyKey).toString());
		}
		
		return resultInfos;
		
	}
}
