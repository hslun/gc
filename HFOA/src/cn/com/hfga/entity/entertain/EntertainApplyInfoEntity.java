package cn.com.hfga.entity.entertain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "B_EntertainApplyInfo")
public class EntertainApplyInfoEntity implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	private String ID;

	@Column(name = "Number")
	private String Number;

	@Column(name = "Department")
	private String Department;

	@Column(name = "ApplyTime")
	private String ApplyTime;

	@Column(name = "EntertainObject")
	private String EntertainObject;

	@Column(name = "EntertainReason")
	private String EntertainReason;

	@Column(name = "EntertainNum")
	private String EntertainNum;

	@Column(name = "AccompanyNum")
	private String AccompanyNum;

	@Column(name = "PerBudget")
	private String PerBudget;

	@Column(name = "MasterBudget")
	private String MasterBudget;
	
	@Column(name = "RemainingBudget")
	private String RemainingBudget;

	@Column(name = "EntertainCategory")
	private String EntertainCategory;

	@Column(name = "Manager")
	private String Manager;

	@Column(name = "Approver")
	private String Approver;
	
	@Column(name = "ApproveTime")
	private String ApproveTime;

	@Column(name = "Status")
	private String Status;

	@Column(name = "Remark")
	private String Remark;
	
	@Column(name = "RegisterNum")
	private int RegisterNum;

	@Column(name = "PaidTime")
	private String PaidTime;
	
	@Column(name = "IfWine")
	private String IfWine;
	
	@Column(name = "WineType")
	private String WineType;
	
	@Column(name = "WineNum")
	private String WineNum;
	
	@Column(name = "IfBefore")
	private String IfBefore;
	
	@Column(name = "BeforeDate")
	private String BeforeDate;
	
	@Transient
	private String InvoiceNumber;
	@Transient
	private String InvoiceSum;
	@Transient
	private String WineSum;
	@Transient
	private String EnterSum;
	@Transient
	private String PersonSum;
	
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

	public int getRegisterNum() {
		return RegisterNum;
	}

	public void setRegisterNum(int registerNum) {
		RegisterNum = registerNum;
	}

	public String getPaidTime() {
		return PaidTime;
	}

	public void setPaidTime(String paidTime) {
		PaidTime = paidTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getInvoiceNumber() {
		return InvoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		InvoiceNumber = invoiceNumber;
	}

	public String getInvoiceSum() {
		return InvoiceSum;
	}

	public void setInvoiceSum(String invoiceSum) {
		InvoiceSum = invoiceSum;
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
