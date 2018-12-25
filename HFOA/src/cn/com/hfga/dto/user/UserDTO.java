package cn.com.hfga.dto.user;

/**
 * 
 * @author xinyc
 * @since 2014-11-14
 * µÇÂ¼ÐÅÏ¢
 *
 */


public class UserDTO {
	private String username;
	
	private String password;
	
	private int Id;
	
	private String Code;
	
	private String RealName;
	
	private String DepartmentId;
	
	private String DepartmentName;
	
	private String Duty;
	
	private String AllowStartTime;
	
	private String LockStartDate;

	@Override
	public String toString() {
		return "UserDTO [username=" + username + ", password=" + password + ", Id=" + Id + ", Code=" + Code
				+ ", RealName=" + RealName + ", DepartmentId=" + DepartmentId + ", DepartmentName=" + DepartmentName
				+ ", Duty=" + Duty + ", AllowStartTime=" + AllowStartTime + ", LockStartDate=" + LockStartDate + "]";
	}

	public String getAllowStartTime() {
		return AllowStartTime;
	}

	public void setAllowStartTime(String allowStartTime) {
		AllowStartTime = allowStartTime;
	}

	public String getLockStartDate() {
		return LockStartDate;
	}

	public void setLockStartDate(String lockStartDate) {
		LockStartDate = lockStartDate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	
	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

	public String getRealName() {
		return RealName;
	}

	public void setRealName(String realName) {
		RealName = realName;
	}

	
	public String getDepartmentId() {
		return DepartmentId;
	}

	public void setDepartmentId(String departmentId) {
		DepartmentId = departmentId;
	}

	public String getDepartmentName() {
		return DepartmentName;
	}

	public void setDepartmentName(String departmentName) {
		DepartmentName = departmentName;
	}

	public String getDuty() {
		return Duty;
	}

	public void setDuty(String duty) {
		Duty = duty;
	}

}
