package cn.com.hfga.dto.privatecar;

/**
 * 
 * 本部门领导获取待审批的信息dto
 * @author xinyc
 * @since 2017-02-28
 *
 */
public class getApproveDTO {

	
	private String department;
	
	private String approveman;
	
	//不用传
	private String status;

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getApproveman() {
		return approveman;
	}

	public void setApproveman(String approveman) {
		this.approveman = approveman;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
