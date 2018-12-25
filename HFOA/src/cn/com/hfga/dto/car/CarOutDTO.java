package cn.com.hfga.dto.car;

/**
 * 
 * @author xinyc
 * 借车出库接收的实体类
 *
 */
public class CarOutDTO {

	private String applyid;
	
	private String begintime ;

	public String getApplyid() {
		return applyid;
	}

	public void setApplyid(String applyid) {
		this.applyid = applyid;
	}

	public String getBegintime() {
		return begintime;
	}

	public void setBegintime(String begintime) {
		this.begintime = begintime;
	}
}