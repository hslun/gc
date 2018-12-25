package cn.com.hfga.controller.file;


import java.io.IOException;   
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
  
import org.jdom.Document;  
import org.jdom.Element;  
import org.jdom.JDOMException;  
import org.jdom.input.SAXBuilder;  
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * �汾��Ϣ��
 * @author xyc
 * ��ʽ ����DOM���ɺͽ���xml�ĵ�
 */
@Service("versionInfo")
public class VersionInfo  implements XmlDocument{

	@Override
	public List<Map<String, Object>> parserXml(String filename)
	{
		List<Map<String, Object>> list=null;
		SAXBuilder builder = new SAXBuilder(false);  
        try {  
        	list=new ArrayList<Map<String, Object>>();
            Document document = builder.build(filename);
            Element employees = document.getRootElement();  
            List employeeList = employees.getChildren();  
            // ��ȡemployee�ڵ�  
            for (int i = 0; i < employeeList.size(); i++) {  
                Element employee = (Element) employeeList.get(i);  
                    Map<String,Object> map=new HashMap<String, Object>();
					map.put(employee.getName().toString(), employee.getText());
					list.add(map);
                }  
        } catch (JDOMException  e) {  
            System.out.println(e.getMessage());  
        } catch (IOException e) {  
            System.out.println(e.getMessage());  
        }   
		return list;
	}    
	
	@Override
	public void modifyXml(String filename, VersionEntity versionEntity) {
		// TODO Auto-generated method stub
	}
	@Override
	public void createXml(String fileName, VersionEntity versionEntity) {
		// TODO Auto-generated method stub
		
	}

	public List<Map<String, Object>> parserXmlLogin(HttpServletRequest httpServletRequest)
	{
    String path = httpServletRequest.getSession().getServletContext().getRealPath("/version.xml");
//		String filename = "c:\\version.xml";
		List<Map<String, Object>> list=null;
		SAXBuilder builder = new SAXBuilder(false);  
        try {  
        	list=new ArrayList<Map<String, Object>>();
            Document document = builder.build(path);
            Element employees = document.getRootElement();  
            List employeeList = employees.getChildren();  
            // ��ȡemployee�ڵ�  
            for (int i = 0; i < employeeList.size(); i++) {  
                Element employee = (Element) employeeList.get(i);  
                    Map<String,Object> map=new HashMap<String, Object>();
					map.put(employee.getName().toString(), employee.getText());
					list.add(map);
                }  
        } catch (JDOMException  e) {  
            System.out.println(e.getMessage());  
        } catch (IOException e) {  
            System.out.println(e.getMessage());  
        }   
        System.out.println("list��"+list);
		return list;
	}

}
