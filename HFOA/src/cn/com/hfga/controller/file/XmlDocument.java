package cn.com.hfga.controller.file;

import java.util.List;
import java.util.Map;

public interface XmlDocument {
	/** 
	* ����XML�ĵ� 
	* @param fileName �ļ�ȫ·������ 
	*/ 
	public void createXml(String fileName,VersionEntity versionEntity); 
	/** 
	* ����XML�ĵ� 
	* @param fileName �ļ�ȫ·������ 
	* @return VersionEntity �汾��Ϣʵ��
	*/ 
	public List<Map<String,Object>> parserXml(String fileName); 
	/** 
	* ����XML�ĵ� 
	* @param filename �ļ�ȫ·������
	* @param versionEntity �汾��Ϣʵ��
	*/
	public void  modifyXml(String filename,VersionEntity versionEntity);
}
