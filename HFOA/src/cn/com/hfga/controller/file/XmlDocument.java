package cn.com.hfga.controller.file;

import java.util.List;
import java.util.Map;

public interface XmlDocument {
	/** 
	* 建立XML文档 
	* @param fileName 文件全路径名称 
	*/ 
	public void createXml(String fileName,VersionEntity versionEntity); 
	/** 
	* 解析XML文档 
	* @param fileName 文件全路径名称 
	* @return VersionEntity 版本信息实体
	*/ 
	public List<Map<String,Object>> parserXml(String fileName); 
	/** 
	* 解析XML文档 
	* @param filename 文件全路径名称
	* @param versionEntity 版本信息实体
	*/
	public void  modifyXml(String filename,VersionEntity versionEntity);
}
