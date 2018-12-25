package cn.com.hfga.controller.common;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 该类用来自动生成事前审批表中的审批单编号
 * 
 * @author ymx
 * @param start为编号开头
 * @param num为数据库表中查询出的最大编号
 * @return
 */
public class AutoNum {

	public String getNum(String num) { // num为数据库表中查询出的最大编号
		String n = num.substring(4); // 截取当前表中最大编号的后四位流水号

		String number = ""; // 定义审批单编号变量，初始为空

		// 截取当前日期中的年份后两位
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String str = sdf.format(new Date());
		String year = str.substring(2, 4);

		// 如果表中没有记录，给出初始编号
		if (n.equals("")) {
			number = "ZD" + year + "0001";
		} else {
			String s2 = ""; // 在当前表中最大流水号的基础上+1
			int lg = Integer.parseInt(n); // 将当前表中最大流水号转为整数

			// 对流水号结尾的四位数字进行判断，分情况进行增加
			if (lg > 0 && lg < 9) {
				s2 = "000" + (lg + 1);
			} else if (lg >= 9 && lg < 99) {
				s2 = "00" + (lg + 1);
			} else if (lg >= 99 && lg < 999) {
				s2 = "0" + (lg + 1);
			} else if (lg >= 999 && lg < 9999) {
				s2 = "" + (lg + 1);
			}
			number = "ZD" + year + s2;
		}
		return number;
	}
}
