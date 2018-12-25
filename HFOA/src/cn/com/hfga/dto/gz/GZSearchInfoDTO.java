package cn.com.hfga.dto.gz;

/**
 * ¹«ÕÂ 
 * @author xyc
 *
 */
public class GZSearchInfoDTO {

	private String  departmentName;
	
	private String  userName;
	
	private String  gzKind;
	
	private String startTime;
	
	private String endTime;
	
	private String sendTo;
	
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGzKind() {
		return gzKind;
	}

	public void setGzKind(String gzKind) {
		this.gzKind = gzKind;
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

	public GZSearchInfoDTO()
	{
		
	}

	public String getSendTo() {
		return sendTo;
	}

	public void setSendTo(String sendTo) {
		this.sendTo = sendTo;
	}

	@Override
	public String toString() {
		return "GZSearchInfoDTO [departmentName=" + departmentName + ", userName=" + userName + ", gzKind=" + gzKind
				+ ", startTime=" + startTime + ", endTime=" + endTime + ", sendTo=" + sendTo + "]";
	}

	public GZSearchInfoDTO(String departmentName, String userName, String gzKind, String startTime, String endTime,
			String sendTo) {
		super();
		this.departmentName = departmentName;
		this.userName = userName;
		this.gzKind = gzKind;
		this.startTime = startTime;
		this.endTime = endTime;
		this.sendTo = sendTo;
	}

	
	 
}
