package cn.com.hfga.entity.entertain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;

public class EntertainInfoEntity implements Serializable {

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
	
	private String RemainingBudget;//剩余预算

	private String EntertainCategory; // 招待类别

	private String Manager; // 经办人

	private String Approver; // 审批人
	
	private String ApproveTime; //审批时间

	private String Status; // 状态

	private String Remark; // 备注
	
	private String InvoiceNumber; // 发票号
	
	private String IfWine;
	
	private String WineType;
	
	private String WineNum;
	
	private String WineSum;
	
	private String EnterSum;
	
	private String PersonSum;
	
	private String IfBefore;
	
	private String BeforeDate;

	private List<EntertainRegisterInfoEntity> list; // 事后审批list

	private static final long serialVersionUID = 1L;

	public String getID() {
		return ID;
	}

	public void setID(String string) {
		ID = string;
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

	public String getRemainingBudget() {
		return RemainingBudget;
	}

	public void setRemainingBudget(String remainingBudget) {
		RemainingBudget = remainingBudget;
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

	public String getApproveTime() {
		return ApproveTime;
	}

	public void setApproveTime(String approveTime) {
		ApproveTime = approveTime;
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

	public List<EntertainRegisterInfoEntity> getList() {
		return list;
	}

	public void setList(List<EntertainRegisterInfoEntity> list) {
		this.list = list;
	}

	public String getInvoiceNumber() {
		return InvoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		InvoiceNumber = invoiceNumber;
	}


	public String getWineSum() {
		return WineSum;
	}

	public void setWineSum(String wineSum) {
		WineSum = wineSum;
	}

	public String getEnterSum() {
		return EnterSum;
	}

	public void setEnterSum(String enterSum) {
		EnterSum = enterSum;
	}

	public String getPersonSum() {
		return PersonSum;
	}

	public void setPersonSum(String personSum) {
		PersonSum = personSum;
	}

	public String getIfWine() {
		return IfWine;
	}

	public void setIfWine(String ifWine) {
		IfWine = ifWine;
	}

	public String getWineType() {
		return WineType;
	}

	public void setWineType(String wineType) {
		WineType = wineType;
	}

	public String getWineNum() {
		return WineNum;
	}

	public void setWineNum(String wineNum) {
		WineNum = wineNum;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public EntertainInfoEntity() {
		super();
		// TODO Auto-generated constructor stub
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


	
}
