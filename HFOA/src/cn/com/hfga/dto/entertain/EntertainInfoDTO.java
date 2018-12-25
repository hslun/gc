package cn.com.hfga.dto.entertain;

import antlr.collections.List;

public class EntertainInfoDTO {

	private String ID;

	private String Number; // 审批单号

	private String Department; // 部门

	private String ApplyTime; // 申请时间

	private String EntertainObject; // 招待对象

	private String EntertainReason; // 招待事由

	private String EntertainNum; // 招待人数

	private String AccompanyNum; // 陪同人数

	private String PerBudget; // 人均预算

	private String MasterBudget; // 总预算

	private String EntertainCategory; // 招待类别

	private String Manager; // 经办人

	private String Approver; // 审批人

	private String Status; // 状态

	private String Remark; // 备注

	private List list; // 事后审批list

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getNumber() {
		return Number;
	}

	public void setNumber(String number) {
		Number = number;
	}

	public String getDepartment() {
		return Department;
	}

	public void setDepartment(String department) {
		Department = department;
	}

	public String getApplyTime() {
		return ApplyTime;
	}

	public void setApplyTime(String applyTime) {
		ApplyTime = applyTime;
	}

	public String getEntertainObject() {
		return EntertainObject;
	}

	public void setEntertainObject(String entertainObject) {
		EntertainObject = entertainObject;
	}

	public String getEntertainReason() {
		return EntertainReason;
	}

	public void setEntertainReason(String entertainReason) {
		EntertainReason = entertainReason;
	}

	public String getEntertainNum() {
		return EntertainNum;
	}

	public void setEntertainNum(String entertainNum) {
		EntertainNum = entertainNum;
	}

	public String getAccompanyNum() {
		return AccompanyNum;
	}

	public void setAccompanyNum(String accompanyNum) {
		AccompanyNum = accompanyNum;
	}

	public String getPerBudget() {
		return PerBudget;
	}

	public void setPerBudget(String perBudget) {
		PerBudget = perBudget;
	}

	public String getMasterBudget() {
		return MasterBudget;
	}

	public void setMasterBudget(String masterBudget) {
		MasterBudget = masterBudget;
	}

	public String getEntertainCategory() {
		return EntertainCategory;
	}

	public void setEntertainCategory(String entertainCategory) {
		EntertainCategory = entertainCategory;
	}

	public String getManager() {
		return Manager;
	}

	public void setManager(String manager) {
		Manager = manager;
	}

	public String getApprover() {
		return Approver;
	}

	public void setApprover(String approver) {
		Approver = approver;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

}
