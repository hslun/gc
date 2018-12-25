package cn.com.hfga.dto.entertain;

/**
 * 招待事前审批
 * 
 * @author ymx
 *
 */
public class EntertainApplyInfoDTO {

	private int ID;

	private String Number; // 审批单号

	private String Department; // 部门

	private String ApplyTime; // 申请时间

	private String EntertainObject; // 招待对象

	private String EntertainReason; // 招待事由

	private String EntertainNum; // 招待人数

	private String AccompanyNum; // 陪同人数

	private String PerBudget; // 人均预算

	private String MasterBudget; // 总预算
	
	private String RemainingBudget; //剩余预算

	private String EntertainCategory; // 招待类别

	private String Manager; // 经办人

	private String Approver; // 审批人

	private String Status; // 状态

	private String Remark; // 备注
	
	private int RegisterNum; // 关联的事后表数量
	
	private String PaidTime;// 报销时间
	
	private String IfWine;// 是否需要酒水
	
	private String WineType;// 酒水类型（详细）
	
	private String WineNum;// 酒水数量
	
	private String WineOther;// 酒水其他
	
	private String IfBefore;// 是否后补提交
	
	private String BeforeDate;// 原申请日期

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

	public EntertainApplyInfoDTO() {
		super();
		// TODO Auto-generated constructor stub
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

	public String getWineOther() {
		return WineOther;
	}

	public void setWineOther(String wineOther) {
		WineOther = wineOther;
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
		return "EntertainApplyInfoDTO [ID=" + ID + ", Number=" + Number + ", Department=" + Department + ", ApplyTime="
				+ ApplyTime + ", EntertainObject=" + EntertainObject + ", EntertainReason=" + EntertainReason
				+ ", EntertainNum=" + EntertainNum + ", AccompanyNum=" + AccompanyNum + ", PerBudget=" + PerBudget
				+ ", MasterBudget=" + MasterBudget + ", RemainingBudget=" + RemainingBudget + ", EntertainCategory="
				+ EntertainCategory + ", Manager=" + Manager + ", Approver=" + Approver + ", Status=" + Status
				+ ", Remark=" + Remark + ", RegisterNum=" + RegisterNum + ", PaidTime=" + PaidTime + ", IfWine="
				+ IfWine + ", WineType=" + WineType + ", WineNum=" + WineNum + ", WineOther=" + WineOther
				+ ", IfBefore=" + IfBefore + ", BeforeDate=" + BeforeDate + "]";
	}

	public EntertainApplyInfoDTO(int iD, String number, String department, String applyTime, String entertainObject,
			String entertainReason, String entertainNum, String accompanyNum, String perBudget, String masterBudget,
			String remainingBudget, String entertainCategory, String manager, String approver, String status,
			String remark, int registerNum, String paidTime, String ifWine, String wineType, String wineNum,
			String wineOther, String ifBefore, String beforeDate) {
		super();
		ID = iD;
		Number = number;
		Department = department;
		ApplyTime = applyTime;
		EntertainObject = entertainObject;
		EntertainReason = entertainReason;
		EntertainNum = entertainNum;
		AccompanyNum = accompanyNum;
		PerBudget = perBudget;
		MasterBudget = masterBudget;
		RemainingBudget = remainingBudget;
		EntertainCategory = entertainCategory;
		Manager = manager;
		Approver = approver;
		Status = status;
		Remark = remark;
		RegisterNum = registerNum;
		PaidTime = paidTime;
		IfWine = ifWine;
		WineType = wineType;
		WineNum = wineNum;
		WineOther = wineOther;
		IfBefore = ifBefore;
		BeforeDate = beforeDate;
	}

	

	

	

}
