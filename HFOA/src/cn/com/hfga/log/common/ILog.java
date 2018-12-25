package cn.com.hfga.log.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * hfga's log interface.
 * @author zhang.haifeng
 * since 2014-10-15
 */
public interface ILog {
	
	/**
	 * logger
	 */
	public static Logger logger = LoggerFactory.getLogger(ILog.class);
}
