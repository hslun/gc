package cn.com.hfga.util.common;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

public class StringUtil {
	
	/**
	 * 之前的
	 */
	public static final int BEFORE = 0;
			
	/**
	 * 之后的
	 */
	public static final int AFTER = 1;

	
	/**
	 * 获取指定分隔符分割的最后一个元素
	 * 如果sourceStr和separator不为空且该字符串中存在指定的separator，则返回separator分割的最后一个元素，否则返回sourceStr。
	 * @param sourceStr
	 * @param separator
	 * @return String
	 */
	public static String getLastSpecifiedString(String sourceStr, String separator) {
		return getLastSpecifiedString(sourceStr, separator, AFTER);
	}
	
	/**
	 * 获取指定分隔符前/后的元素
	 * 如果sourceStr和separator不为空且该字符串中存在指定的separator，则返回separator最后一个位置前/后的元素，否则返回sourceStr。
	 * @param sourceStr
	 * @param separator
	 * @param flag 【0：之前，1：之前后】
	 */
	public static String getLastSpecifiedString(String sourceStr, String separator, int flag) {
		if(sourceStr!=null&&separator!=null&&sourceStr.indexOf(separator) != -1) {
			String resultStr = "";
			if(AFTER == flag) {
				resultStr = sourceStr.substring(sourceStr.lastIndexOf(separator)+1);
			} else if(BEFORE == flag){
				resultStr = sourceStr.substring(0, sourceStr.lastIndexOf(separator));
			} 
			return resultStr;
		}  else {
			return sourceStr;
		}
	}
	
	/**
	 * 获得用户远程地址
	 * @param request
	 * @return String
	 */
	public static String getRemoteAddr(HttpServletRequest request){
		String remoteAddr = request.getHeader("X-Real-IP");
        if (StringUtils.isNotBlank(remoteAddr)) {
        	remoteAddr = request.getHeader("X-Forwarded-For");
        }else if (StringUtils.isNotBlank(remoteAddr)) {
        	remoteAddr = request.getHeader("Proxy-Client-IP");
        }else if (StringUtils.isNotBlank(remoteAddr)) {
        	remoteAddr = request.getHeader("WL-Proxy-Client-IP");
        }
        return remoteAddr != null ? remoteAddr : request.getRemoteAddr();
	}
	
}
