package cn.com.hfga.dto.entertain;

import antlr.collections.List;

public class EntertainInfoDTO {

	private String ID;

	private String Number; // ��������

	private String Department; // ����

	private String ApplyTime; // ����ʱ��

	private String EntertainObject; // �д�����

	private String EntertainReason; // �д�����

	private String EntertainNum; // �д�����

	private String AccompanyNum; // ��ͬ����

	private String PerBudget; // �˾�Ԥ��

	private String MasterBudget; // ��Ԥ��

	private String EntertainCategory; // �д����

	private String Manager; // ������

	private String Approver; // ������

	private String Status; // ״̬

	private String Remark; // ��ע

	private List list; // �º�����list

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
