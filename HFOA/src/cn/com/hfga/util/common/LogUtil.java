package cn.com.hfga.util.common;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 *	��־������  
 * @author xyc
 *
 */

public class LogUtil {
	
	//�ļ���д���� ����д����־
	public  static void writeFile(String filename,String content)
	{
		try {
			FileWriter fileWriter=new FileWriter(filename, true);
			if(fileWriter!=null)
			{
				java.util.Date date=new java.util.Date();
				
				fileWriter.write(content+"  "+getDateTime()+System.getProperty("line.separator"));
				fileWriter.flush();
				fileWriter.close();
			}
			else
			{
				System.out.print("�ļ�����");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // true����׷��
	}
	public static String getDateTime() {
		// TODO Auto-generated method stub
	    Date dtDate=new Date();
		SimpleDateFormat sm=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sm.format(dtDate);
	}

}