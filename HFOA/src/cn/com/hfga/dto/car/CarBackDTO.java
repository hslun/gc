package cn.com.hfga.dto.car;
/**
 * 
 * @author xinyc
 * 接收还车入库的参数
 *
 */


public class CarBackDTO {
	
	public String getLengthend() {
		return lengthend;
	}

	public void setLengthend(String lengthend) {
		this.lengthend = lengthend;
	}

	private String applyid;
	private String endtime;
	private String reallength;
	
	private String realtime;
	
	private String lengthend;

	public String getApplyid() {
		return applyid;
	}

	public void setApplyid(String applyid) {
		this.applyid = applyid;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getReallength() {
		return reallength;
	}

	public void setReallength(String reallength) {
		this.reallength = reallength;
	}

	public String getRealtime() {
		return realtime;
	}

	public void setRealtime(String realtime) {
		this.realtime = realtime;
	}
}
