package cn.com.hfga.util.common;

import java.text.ParseException;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

/**
 * the util class of Spring£¬ time conversion
 * @author zhang.haifeng
 * since 2014-9-24
 */
public class SpringStringToDate implements Converter<String, Date> {
	
	private String parttern;
	
	public SpringStringToDate(String parttern) {
		this.parttern = parttern;
	}
	
	@Override
	public Date convert(String source) {
		try {
			if(StringUtils.isEmpty(source)) {
				return null;
			}
			String [] arr = source.split("-");
			String tempParttern = "yyyy-MM-dd";
			if(arr.length==2) {
				tempParttern = "yyyy-MM";
			}
			if(arr.length ==1) {
				tempParttern = "yyyy";
			}
			return DateUtil.parseStringToDate(source, tempParttern);
		} catch(ParseException parseException) {
			throw new RuntimeException("ÈÕÆÚ½âÎö´íÎó¡£");
		}
	}

	public String getParttern() {
		return parttern;
	}

	public void setParttern(String parttern) {
		this.parttern = parttern;
	}
	

}
