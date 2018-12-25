package cn.com.hfga.dto.privatecar;

public class PrivateCarSearchDTO {

	private String department;   //部门
	
	private String applyman; // 申请人
	
	private String startTime; // 起始时间

	private String endTime; // 结束时间

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getApplyman() {
		return applyman;
	}

	public void setApplyman(String applyman) {
		this.applyman = applyman;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "PrivateCarSearchDTO [department=" + department + ", applyman=" + applyman + ", startTime=" + startTime
				+ ", endTime=" + endTime + "]";
	}

	public PrivateCarSearchDTO(String department, String applyman, String startTime, String endTime) {
		super();
		this.department = department;
		this.applyman = applyman;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public PrivateCarSearchDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
