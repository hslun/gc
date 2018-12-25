package cn.com.hfga.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;



/**
 * ������ �������ģʽ
 * @author xyc
 *
 */
public  class CommonUtil {

	private static CommonUtil commonUtil=null;
	
	private CommonUtil()
	{
		
	}
	
	public static   CommonUtil getInstance()
	{
		if(commonUtil==null)
		{
			synchronized (CommonUtil.class)
			{
				if(null==commonUtil)
				{
					commonUtil=new CommonUtil();
				}
			}
		}
		return commonUtil;
	}
	
	//������������Ϊ�ַ���
	public String combineInt(int i,int j)
	{
		StringBuffer sb=new StringBuffer("");
		sb.append(i);
		sb.append(j);
		return sb.toString();
	}
	
	public InputStream toChinese(ByteArrayOutputStream by,String info)
	{
		try {
			by.write(info.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ByteArrayInputStream(by.toByteArray()); 
	}
	
	//��ȡ�ļ�·������չ����
	public String getPostfix(String path)
	{
		if(path==""||path==null)
		{
			return "";
		}
		if(path.contains("."))
		{
			return path.substring(path.lastIndexOf(".")+1,path.length());
		}
		return "";
	}
	
	
	//ȥ��.0
	public  String getNum(String cell)
	{
		String result="";
		if(cell.contains("."))
		{
			result=cell.replace(".0", "");
		}
		else
		{
			result=cell;
		}
		return result;
	}
	
	//��һ���ַ����ĳ�MD5����ʽ
	public String bytesToMD5(byte[] input) {
		String md5str = null;
		try {
			//����һ���ṩ��ϢժҪ�㷨�Ķ��󣬳�ʼ��Ϊmd5�㷨����
			MessageDigest md = MessageDigest.getInstance("MD5");
			//��������ֽ�����
			byte[] buff = md.digest(input);
			//������ÿһ�ֽڻ���16��������md5�ַ���
			md5str = bytesToHex(buff);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return md5str;
	}
	
	public String bytesToHex(byte[] bytes) {
		StringBuffer md5str = new StringBuffer();
		//������ÿһ�ֽڻ���16��������md5�ַ���
		int digital;
		for (int i = 0; i < bytes.length; i++) {
			 digital = bytes[i];
			if(digital < 0) {
				digital += 256;
			}
			if(digital < 16){
				md5str.append("0");
			}
			md5str.append(Integer.toHexString(digital));
		}
		return md5str.toString();
	}
	
	
	//��ȡ�ļ�·�����̺�
	public String  getP(String filename)
	{
		String  result="";
		if(filename==""){
			return "";
		}
		else{
			String[] array=filename.split("\\\\");
			for(int i=0;i<array.length-2;i++)	{
				if(i==array.length-3){
					result=result+array[i];
				}
				else{
					result=result+array[i]+"\\\\";
				}
			}
			return result;
		}
	}
	
	
	//��ȡ�ļ�·���ĳ�ȥ�ļ�����
	public String  getPath(String filename)
	{
		String  result="";
		if(filename==""){
			return "";
		}
		else{
			String[] array=new String[]{};
			if(filename.contains("/")){
				array=filename.split("/");
						
			}
			else
			{
				array=filename.split("\\\\");
			}
			
			for(int i=0;i<array.length-1;i++)	{
				result=result+array[i]+"\\\\";
			}
			return result;
		}
	}
	
	//��ȡ�ļ�����
	public String getFilename(String filepath)
	{
		String  result="";
		if(filepath==""){
			return "";
		}
		else{
			String[] array=new String[]{};
			if(filepath.contains("/")){
				array=filepath.split("/");
						
			}
			else
			{
				array=filepath.split("\\\\");
			}
			return array[array.length-1];
		}
		
		
	}
	//ȡ�����һ��id
	public String getParentId(String parentids)
	{
		String []ids=parentids.split("/");
		return ids[ids.length-1];
	}
	
	//��ȡ��ǰʱ����·�
	public String getMonth()
	{
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("MM");
		return simpleDateFormat.format(new Date());
	}
	
	//�����ַ���[fileName] ȥ����ʼ�ͽ�����]
	public  String  replaceD(String filename)
	{
		return filename.replace("[", "").replace("]", "");
		
	}
	
	//��ȡ��ǰ��ʱ���
	public  String getTime(){
		SimpleDateFormat simple=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return simple.format(new Date());
	}
	
	
	//���Դ���
	/*
	public static void main(String []args){
		System.out.println(getTime());
	}
	*/
	
}
