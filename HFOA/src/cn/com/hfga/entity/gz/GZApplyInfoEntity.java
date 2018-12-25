package cn.com.hfga.entity.gz;

import javax.persistence.Table;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Table(name = "B_GZApplyInfo")
public class GZApplyInfoEntity implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	private String ID;

	@Column(name = "Department")
	private String Department;

	@Column(name = "ApplyUserName")
	private String ApplyUserName;

	@Column(name = "Reason")
	private String Reason;

	@Column(name = "ApplyTime")
	private String ApplyTime;

	@Column(name = "SendTo")
	private String SendTo;

	@Column(name = "GZKind")
	private String GZKind;

	@Column(name = "Copies")
	private String Copies;

	@Column(name = "IsSecret")
	private String IsSecret;

	@Column(name = "Status")
	private String Status;

	@Column(name = "ApproveMan")
	private String ApproveMan;

	@Column(name = "ConfirmMan")
	private String ConfirmMan;

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

	public String getConfirmMan() {
		return ConfirmMan;
	}

	public void setConfirmMan(String confirmMan) {
		ConfirmMan = confirmMan;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
