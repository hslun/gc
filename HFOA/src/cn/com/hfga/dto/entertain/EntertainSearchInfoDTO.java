package cn.com.hfga.dto.entertain;

/**
 * �д���ǰ������ѯ
 * 
 * @author ymx
 *
 */
public class EntertainSearchInfoDTO {

	private String department; // ����

	private String manager; // ������

	private String startTime; // ��ʼʱ��

	private String endTime; // ����ʱ��

	private String entertainObject; // �д�����
	
	private String invoiceNumber; // ��Ʊ��
	
	private String invoiceSum; // ��Ʊ���

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
