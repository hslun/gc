package cn.com.hfga.dto.car;
/**
 * 接收审批信息DTO
 * @author xinyc
 *
 */
public class CarApporveInfoDTO {
		
	private String  userid;
	
	private  String  username;
	
	private String department;

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}



	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
