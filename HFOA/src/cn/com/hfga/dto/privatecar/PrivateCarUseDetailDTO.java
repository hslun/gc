package cn.com.hfga.dto.privatecar;


/**
 * 
 * @author hfga
 * 私车公用使用明细
 *
 */
public class PrivateCarUseDetailDTO {
	
	private String depatment;
	
	private String starttime;
	
	private  String endtime;
	
	private String applyman;
	

	public String getDepatment() {
		return depatment;
	}

	public void setDepatment(String depatment) {
		this.depatment = depatment;
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

	public String getApplyman() {
		return applyman;
	}

	public void setApplyman(String applyman) {
		this.applyman = applyman;
	}
}
