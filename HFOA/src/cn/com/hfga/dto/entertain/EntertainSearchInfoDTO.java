package cn.com.hfga.dto.entertain;

/**
 * 招待事前审批查询
 * 
 * @author ymx
 *
 */
public class EntertainSearchInfoDTO {

	private String department; // 部门

	private String manager; // 经办人

	private String startTime; // 起始时间

	private String endTime; // 结束时间

	private String entertainObject; // 招待对象
	
	private String invoiceNumber; // 发票号
	
	private String invoiceSum; // 发票金额

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
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
	
	public String getEntertainObject() {
		return entertainObject;
	}

	public void setEntertainObject(String entertainObject) {
		this.entertainObject = entertainObject;
	}

	public EntertainSearchInfoDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getInvoiceSum() {
		return invoiceSum;
	}

	public void setInvoiceSum(String invoiceSum) {
		this.invoiceSum = invoiceSum;
	}

	



}
