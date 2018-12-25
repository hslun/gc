package cn.com.hfga.util.common;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 *	日志操作类  
 * @author xyc
 *
 */

public class LogUtil {
	
	//文件读写函数 用于写入日志
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
				System.out.print("文件存在");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // true代表追加
	}
	public static String getDateTime() {
		// TODO Auto-generated method stub
	    Date dtDate=new Date();
		SimpleDateFormat sm=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sm.format(dtDate);
	}

}