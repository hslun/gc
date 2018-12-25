package cn.com.hfga.entity.entertain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "B_EntertainRegisterInfo")
public class EntertainRegisterInfoEntity implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	private String ID;

	@Column(name = "Number")
	private String Number;

	@Column(name = "InvoiceDate")
	private String InvoiceDate;

	@Column(name = "InvoiceContent")
	private String InvoiceContent;

	@Column(name = "InvoiceSum")
	private String InvoiceSum;

	@Column(name = "InvoiceNum")
	private String InvoiceNum;

	@Column(name = "PaidTime")
	private String PaidTime;

	@Column(name = "VoucherNum")
	private String VoucherNum;

	@Column(name = "InvoiceUnit")
	private String InvoiceUnit;

	@Column(name = "Status")
	private String Status;

	@Column(name = "RegisterMan")
	private String RegisterMan;

	@Column(name = "Remark")
	private String Remark;
	
	@Column(name = "InvoiceNumber")
	private String InvoiceNumber;
	
	@Column(name = "WineSum")
	private String WineSum;
	
	@Column(name = "EnterSum")
	private String EnterSum;
	
	@Column(name = "PersonSum")
	private String PersonSum;
	
	@Transient
	private String IfWine;
	@Transient
	private String EntertainNum;
	@Transient
	private String AccompanyNum;

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

	public String getInvoiceDate() {
		return InvoiceDate;
	}

	public void setInvoiceDate(String invoiceDate) {
		InvoiceDate = invoiceDate;
	}

	public String getInvoiceContent() {
		return InvoiceContent;
	}

	public void setInvoiceContent(String invoiceContent) {
		InvoiceContent = invoiceContent;
	}

	public String getInvoiceSum() {
		return InvoiceSum;
	}

	public void setInvoiceSum(String invoiceSum) {
		InvoiceSum = invoiceSum;
	}

	public String getInvoiceNum() {
		return InvoiceNum;
	}

	public void setInvoiceNum(String invoiceNum) {
		InvoiceNum = invoiceNum;
	}

	public String getPaidTime() {
		return PaidTime;
	}

	public void setPaidTime(String paidTime) {
		PaidTime = paidTime;
	}

	public String getVoucherNum() {
		return VoucherNum;
	}

	public void setVoucherNum(String voucherNum) {
		VoucherNum = voucherNum;
	}

	public String getInvoiceUnit() {
		return InvoiceUnit;
	}

	public void setInvoiceUnit(String invoiceUnit) {
		InvoiceUnit = invoiceUnit;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getRegisterMan() {
		return RegisterMan;
	}

	public void setRegisterMan(String registerMan) {
		RegisterMan = registerMan;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

	
	public String getInvoiceNumber() {
		return InvoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		InvoiceNumber = invoiceNumber;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

}
