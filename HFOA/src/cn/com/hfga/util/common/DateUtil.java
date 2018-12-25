package cn.com.hfga.util.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.time.DateUtils;

/**
 * the util class of Date, date conversion
 * @author zhang.haifeng
 * since 2014-9-24
 */
public class DateUtil {
	
	public static Date parseRssTtimeToDate(String dateStr) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH);
		return simpleDateFormat.parse(dateStr);
		
	}
	
	public static Date parseStringToDate(String dateStr, String pattern) throws ParseException {
		return DateUtils.parseDate(dateStr, pattern);
	}
	
	public static Date parseStringToDate(String dateStr) throws ParseException {
		return DateUtils.parseDate(dateStr, "yyyy-MM-dd");
	}
	
	public static String parseDateToString(Date date) throws ParseException {
		return parseDateToString(date,"yyyy-MM-dd hh:mm:ss");
	}
	
	public static String parseDateToString(Date date, String pattern) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		return simpleDateFormat.format(date);
	}
	

}
