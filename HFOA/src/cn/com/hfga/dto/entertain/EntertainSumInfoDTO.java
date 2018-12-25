package cn.com.hfga.dto.entertain;

public class EntertainSumInfoDTO {

	private int ID;
	private String Number;
	private String Department;
	private String InvoiceSum;
	
	
	public EntertainSumInfoDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EntertainSumInfoDTO(int iD, String number, String department, String invoiceSum) {
		super();
		ID = iD;
		Number = number;
		Department = department;
		InvoiceSum = invoiceSum;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
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
	public String getInvoiceSum() {
		return InvoiceSum;
	}
	public void setInvoiceSum(String invoiceSum) {
		InvoiceSum = invoiceSum;
	}
	
}
