package cn.com.hfga.dto.privatecar;

/**
 * 
 * 私车公用的审批dto
 * @author xinyc
 * @since 2017-02-28
 *
 */
public class ApproveDTO {

	private String applyid;  //ID
	
	private String approveman;  //审批人
	
	//不用传
	private String approvetime;  //审批时间

	public String getApplyid() {
		return applyid;
	}

	public void setApplyid(String applyid) {
		this.applyid = applyid;
	}

	public String getApproveman() {
		return approveman;
	}

	public void setApproveman(String approveman) {
		this.approveman = approveman;
	}

	public String getApprovetime() {
		return approvetime;
	}

	public void setApprovetime(String approvetime) {
		this.approvetime = approvetime;
	}
	
	
}
