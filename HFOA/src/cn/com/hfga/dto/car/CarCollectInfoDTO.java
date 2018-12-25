package cn.com.hfga.dto.car;


/**
 * »ã×Ü·ÖÎö DTO
 * @author xinyc
 *
 */
public class CarCollectInfoDTO {

	private String department;
	
	private String carinfo;
	
	private String starttime;
	
	private String endtime;
	
	private String Kind;

	public String getKind() {
		return Kind;
	}

	public void setKind(String kind) {
		Kind = kind;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getCarinfo() {
		return carinfo;
	}

	public void setCarinfo(String carinfo) {
		this.carinfo = carinfo;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	
	
}
