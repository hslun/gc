package cn.com.hfga.dto.car;


/**
 * 
 * @author xinyc
 * 汇总分析 接收实体类
 *
 */
public class CarSearchInfo {
	
	private String  deparment;
	
	private String starttime;
	
	private String cartype;
	
	private String endtime;

	public String getDeparment() {
		return deparment;
	}

	public void setDeparment(String deparment) {
		this.deparment = deparment;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getCartype() {
		return cartype;
	}

	public void setCartype(String cartype) {
		this.cartype = cartype;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	
}
