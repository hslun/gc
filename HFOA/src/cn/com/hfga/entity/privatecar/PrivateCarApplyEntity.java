package cn.com.hfga.entity.privatecar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.com.hfga.entity.common.IEntity;

@Entity
@Table(name="b_privatecar")
public class PrivateCarApplyEntity implements IEntity{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ApplyId")
	private String ApplyId;
	

	@Column(name="Department",nullable=true)
	private String Department;
	

	@Column(name="ApplyMan",nullable=true)
	private String ApplyMan;
	
	@Column(name="ApproveMan",nullable=true)
	private String ApproveMan;
	
	
	@Column(name="UserCarTime",nullable=true)
	private String UserCarTime;
	
	@Column(name="Reason",nullable=true)
	private String Reason;

	@Column(name="BeginAddress",nullable=true)
	private String BeginAddress;
	
	@Column(name="PassAddress",nullable=true)
	private String PassAddress;

	@Column(name="Destination",nullable=true)
	private String Destination;
	
	@Column(name="Status",nullable=true)
	private String Status;
	
	@Column(name="SingleLength",nullable=true)
	private Double  SingleLength;
	
	@Column(name="SureLength",nullable=true)
	private Double SureLength;

	@Column(name="CountLength",nullable=true)
	private Double CountLength;

	@Column(name="ApplyTime",nullable=true)
	private String ApplyTime;
	
	@Column(name="SureTime",nullable=true)
	private String SureTime;
	
	@Column(name="ApproveTime",nullable=true)
	private String ApproveTime;
	
	@Column(name="IfPerform",nullable=true)
	private String IfPerform;
	
	@Column(name="WayModel",nullable=true)
	private String WayModel;
	
	@Column(name="WayDetail",nullable=true)
	private String WayDetail;
	
	@Column(name="DoubleLength",nullable=true)
	private String DoubleLength;
	
	@Column(name="EndLength",nullable=true)
	private String EndLength;
	
	@Column(name="IfPass",nullable=true)
	private String IfPass;
	
	@Column(name="IfSub",nullable=true)
	private String IfSub;
	
	@Column(name="IfBefore",nullable=true)
	private String IfBefore;
	
	@Column(name="BeforeDate",nullable=true)
	private String BeforeDate;
	
	//ÐÂ¼Ó
	@Column(name="danhao",nullable=true)
	private String danhao;
	
	@Column(name="Sum",nullable=true)
	private String Sum;
	
	@Column(name="PaidTime",nullable=true)
	private String PaidTime;
	
	@Column(name="ApproveMan2",nullable=true)
	private String ApproveMan2;
	
	@Column(name="SubTime",nullable=true)
	private String SubTime;
	
	public String getSubTime() {
		return SubTime;
	}

	public void setSubTime(String subTime) {
		SubTime = subTime;
	}

	public String getSum() {
		return Sum;
	}

	public void setSum(String sum) {
		Sum = sum;
	}

	public String getPaidTime() {
		return PaidTime;
	}

	public void setPaidTime(String paidTime) {
		PaidTime = paidTime;
	}

	public String getApproveMan2() {
		return ApproveMan2;
	}

	public void setApproveMan2(String approveMan2) {
		ApproveMan2 = approveMan2;
	}

	public String getDanhao() {
		return danhao;
	}

	public void setDanhao(String danhao) {
		this.danhao = danhao;
	}

	public String getApplyId() {
		return ApplyId;
	}

	public void setApplyId(String applyId) {
		ApplyId = applyId;
	}

	public String getDepartment() {
		return Department;
	}

	public void setDepartment(String department) {
		Department = department;
	}

	public String getApplyMan() {
		return ApplyMan;
	}

	public void setApplyMan(String applyMan) {
		ApplyMan = applyMan;
	}

	public String getApproveMan() {
		return ApproveMan;
	}

	public void setApproveMan(String approveMan) {
		ApproveMan = approveMan;
	}

	public String getUserCarTime() {
		return UserCarTime;
	}

	public void setUserCarTime(String userCarTime) {
		UserCarTime = userCarTime;
	}

	public String getReason() {
		return Reason;
	}

	public void setReason(String reason) {
		Reason = reason;
	}

	public String getBeginAddress() {
		return BeginAddress;
	}

	public void setBeginAddress(String beginAddress) {
		BeginAddress = beginAddress;
	}

	public String getPassAddress() {
		return PassAddress;
	}

	public void setPassAddress(String passAddress) {
		PassAddress = passAddress;
	}

	public String getDestination() {
		return Destination;
	}

	public void setDestination(String destination) {
		Destination = destination;
	}

	public Double getSingleLength() {
		return SingleLength;
	}

		
	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public void setSingleLength(Double singleLength) {
		SingleLength = singleLength;
	}

	public Double getSureLength() {
		return SureLength;
	}

	public void setSureLength(Double sureLength) {
		SureLength = sureLength;
	}

	public Double getCountLength() {
		return CountLength;
	}

	public void setCountLength(Double countLength) {
		CountLength = countLength;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getApplyTime() {
		return ApplyTime;
	}

	public void setApplyTime(String applyTime) {
		ApplyTime = applyTime;
	}

	public String getSureTime() {
		return SureTime;
	}

	public void setSureTime(String sureTime) {
		SureTime = sureTime;
	}

	public String getApproveTime() {
		return ApproveTime;
	}

	public void setApproveTime(String approveTime) {
		ApproveTime = approveTime;
	}

	public String getIfPerform() {
		return IfPerform;
	}

	public void setIfPerform(String ifPerform) {
		IfPerform = ifPerform;
	}


	public String getWayModel() {
		return WayModel;
	}

	public void setWayModel(String wayModel) {
		WayModel = wayModel;
	}

	public String getWayDetail() {
		return WayDetail;
	}

	public void setWayDetail(String wayDetail) {
		WayDetail = wayDetail;
	}

	public String getDoubleLength() {
		return DoubleLength;
	}

	public void setDoubleLength(String doubleLength) {
		DoubleLength = doubleLength;
	}

	public String getEndLength() {
		return EndLength;
	}

	public void setEndLength(String endLength) {
		EndLength = endLength;
	}

	public String getIfPass() {
		return IfPass;
	}

	public void setIfPass(String ifPass) {
		IfPass = ifPass;
	}

	public String getIfSub() {
		return IfSub;
	}

	public void setIfSub(String ifSub) {
		IfSub = ifSub;
	}

	public String getIfBefore() {
		return IfBefore;
	}

	public void setIfBefore(String ifBefore) {
		IfBefore = ifBefore;
	}

	public String getBeforeDate() {
		return BeforeDate;
	}

	public void setBeforeDate(String beforeDate) {
		BeforeDate = beforeDate;
	}

	@Override
	public String toString() {
		return "PrivateCarApplyEntity [ApplyId=" + ApplyId + ", Department=" + Department + ", ApplyMan=" + ApplyMan
				+ ", ApproveMan=" + ApproveMan + ", UserCarTime=" + UserCarTime + ", Reason=" + Reason
				+ ", BeginAddress=" + BeginAddress + ", PassAddress=" + PassAddress + ", Destination=" + Destination
				+ ", Status=" + Status + ", SingleLength=" + SingleLength + ", SureLength=" + SureLength
				+ ", CountLength=" + CountLength + ", ApplyTime=" + ApplyTime + ", SureTime=" + SureTime
				+ ", ApproveTime=" + ApproveTime + ", IfPerform=" + IfPerform + ", WayModel=" + WayModel
				+ ", WayDetail=" + WayDetail + ", DoubleLength=" + DoubleLength + ", EndLength=" + EndLength
				+ ", IfPass=" + IfPass + ", IfSub=" + IfSub + ", IfBefore=" + IfBefore + ", BeforeDate=" + BeforeDate
				+ "]";
	}




	
	
}
