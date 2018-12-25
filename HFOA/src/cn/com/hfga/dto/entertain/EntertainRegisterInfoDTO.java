package cn.com.hfga.dto.entertain;

/**
 * �д��º�Ǽ�
 * 
 * @author ymx
 *
 */
public class EntertainRegisterInfoDTO {

	private int ID;

	private String Number; // ��������

	private String InvoiceDate; // ��Ʊ����

	private String InvoiceContent; // ��Ʊ����

	private String InvoiceSum; // ��Ʊ���
	
	private String PerInvoiceSum; // �޸�ǰ�ķ�Ʊ���м�����

	private String InvoiceNum; // ��Ʊ����

	private String PaidTime; // ����ʱ��

	private String VoucherNum; // ƾ֤��

	private String InvoiceUnit; // ��Ʊ��λ

	private String Status; // ״̬

	private String RegisterMan; // �Ǽ���

	private String Remark; // ��ע

	private String InvoiceNumber; //��Ʊ��
	
	private String WineSum; //��ˮ���
	
	private String EnterSum; //�д��ܽ��
	
	private String PersonSum; //�˾����
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

	public String getPerInvoiceSum() {
		return PerInvoiceSum;
	}

	public void setPerInvoiceSum(String perInvoiceSum) {
		this.PerInvoiceSum = perInvoiceSum;
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

	public EntertainRegisterInfoDTO() {
		super();
		// TODO Auto-generated constructor stub
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

	@Override
	public String toString() {
		return "EntertainRegisterInfoDTO [ID=" + ID + ", Number=" + Number + ", InvoiceDate=" + InvoiceDate
				+ ", InvoiceContent=" + InvoiceContent + ", InvoiceSum=" + InvoiceSum + ", PerInvoiceSum="
				+ PerInvoiceSum + ", InvoiceNum=" + InvoiceNum + ", PaidTime=" + PaidTime + ", VoucherNum=" + VoucherNum
				+ ", InvoiceUnit=" + InvoiceUnit + ", Status=" + Status + ", RegisterMan=" + RegisterMan + ", Remark="
				+ Remark + ", InvoiceNumber=" + InvoiceNumber + ", WineSum=" + WineSum + ", EnterSum=" + EnterSum
				+ ", PersonSum=" + PersonSum + "]";
	}

	public EntertainRegisterInfoDTO(int iD, String number, String invoiceDate, String invoiceContent, String invoiceSum,
			String perInvoiceSum, String invoiceNum, String paidTime, String voucherNum, String invoiceUnit,
			String status, String registerMan, String remark, String invoiceNumber, String wineSum, String enterSum,
			String personSum) {
		super();
		ID = iD;
		Number = number;
		InvoiceDate = invoiceDate;
		InvoiceContent = invoiceContent;
		InvoiceSum = invoiceSum;
		PerInvoiceSum = perInvoiceSum;
		InvoiceNum = invoiceNum;
		PaidTime = paidTime;
		VoucherNum = voucherNum;
		InvoiceUnit = invoiceUnit;
		Status = status;
		RegisterMan = registerMan;
		Remark = remark;
		InvoiceNumber = invoiceNumber;
		WineSum = wineSum;
		EnterSum = enterSum;
		PersonSum = personSum;
	}



	

}
