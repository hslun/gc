package cn.com.hfga.dto.gz;

/**
 * π´’¬…Í«ÎDTO¿‡
 * @author xyc
 *
 */
public class GZApplyInfoDTO {

	private String ID;
	
	private String Department;
	
	private String ApplyUserName;
	
	private String Reason;
	
	private String ApplyTime;
	
	private String SendTo;
	
	private String GZKind;
	
	private String Copies;
	
	private String IsSecret;
	
	private String Status;
	
	private String ApproveMan;

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getDepartment() {
		return Department;
	}

	public void setDepartment(String department) {
		Department = department;
	}

	public String getApplyUserName() {
		return ApplyUserName;
	}

	public void setApplyUserName(String applyUserName) {
		ApplyUserName = applyUserName;
	}

	public String getReason() {
		return Reason;
	}

	public void setReason(String reason) {
		Reason = reason;
	}

	public String getApplyTime() {
		return ApplyTime;
	}

	public void setApplyTime(String applyTime) {
		ApplyTime = applyTime;
	}

	public String getSendTo() {
		return SendTo;
	}

	public void setSendTo(String sendTo) {
		SendTo = sendTo;
	}

	public String getGZKind() {
		return GZKind;
	}

	public void setGZKind(String gZKind) {
		GZKind = gZKind;
	}

	public String getCopies() {
		return Copies;
	}

	public void setCopies(String copies) {
		Copies = copies;
	}

	public String getIsSecret() {
		return IsSecret;
	}

	public void setIsSecret(String isSecret) {
		IsSecret = isSecret;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getApproveMan() {
		return ApproveMan;
	}

	public void setApproveMan(String approveMan) {
		ApproveMan = approveMan;
	}

	@Override
	public String toString() {
		return "GZApplyInfoDTO [ID=" + ID + ", Department=" + Department + ", ApplyUserName=" + ApplyUserName
				+ ", Reason=" + Reason + ", ApplyTime=" + ApplyTime + ", SendTo=" + SendTo + ", GZKind=" + GZKind
				+ ", Copies=" + Copies + ", IsSecret=" + IsSecret + ", Status=" + Status + ", ApproveMan=" + ApproveMan
				+ "]";
	}

	public GZApplyInfoDTO(String iD, String department, String applyUserName, String reason, String applyTime,
			String sendTo, String gZKind, String copies, String isSecret, String status, String approveMan) {
		super();
		ID = iD;
		Department = department;
		ApplyUserName = applyUserName;
		Reason = reason;
		ApplyTime = applyTime;
		SendTo = sendTo;
		GZKind = gZKind;
		Copies = copies;
		IsSecret = isSecret;
		Status = status;
		ApproveMan = approveMan;
	}
	
	
	public GZApplyInfoDTO()
	{
		
	}
}
