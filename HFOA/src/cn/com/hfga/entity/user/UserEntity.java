package cn.com.hfga.entity.user;




import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Table;


import cn.com.hfga.entity.common.CommonIntIdEntity;




/**
 *  HFOA 用户实体类
 * @author xinyc	
 * @since 2014-11-12
 */

@Entity
@Table(name="U_User")
public class UserEntity extends CommonIntIdEntity{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	

	
	@Column(name="UserName")
	private String UserName;
	
	@Column(name="Code")
	private String Code;
	
	@Column(name="RealName")
	private String RealName;
	
	@Column(name="RoleId")
	private Integer RoleId;
	
	
	@Column(name="UserFrom")
	private String  UserFrom;
	
	/**
	 * 现用该字段判断是否为部门副经理，1是
	 */
	@Column(name="WorkCategory")
	private String  WorkCategory;
	
	
	@Column(name="CompanyName")
	private String  CompanyName;
	
	@Column(name="CompanyId")
	private Integer  CompanyId;
	
	@Column(name="DepartmentId")
	private Integer  DepartmentId;
	
	/**
	 * 现用该字段存储上级审批人名称（仅限公司领导）
	 */
	@Column(name="WorkgroupName")
	private String  WorkgroupName;
	
	@Column(name="DepartmentName")
	private String  DepartmentName;
	
	@Column(name="Gender")
	private String  Gender;
	
	/**
	 * 现用该字段存储上级审批人id（仅限公司领导）
	 */
	@Column(name="Mobile")
	private String  Mobile;
	
	
	@Column(name="Telephone")
	private String  Telephone;
	
	
	@Column(name="OfficeAddr")
	private String  OfficeAddr;
	
	@Column(name="Birthday")
	private String  Birthday;
	
	@Column(name="Duty")
	private String  Duty;
	
	@Column(name="Title")
	private String  Title;
	
	
	@Column(name="UserPassword")
	private String  UserPassword;
	
	@Column(name="ChangePasswordDate")
	private String  ChangePasswordDate;
	
	@Column(name="QICQ")
	private String  QICQ;
	
	@Column(name="Email")
	private String  Email;
	
	@Column(name="Lang")
	private String  Lang;
	
	@Column(name="Theme")
	private String  Theme;
	
	@Column(name="AllowStartTime")
	private String  AllowStartTime;
	
	@Column(name="AllowEndTime")
	private String  AllowEndTime;
	
	@Column(name="LockStartDate")
	private String  LockStartDate;
	
	
	@Column(name="LockEndDate")
	private String  LockEndDate;
	
	@Column(name="LastVisit")
	private String  LastVisit;
	
	@Column(name="FirstVisit")
	private String  FirstVisit;
	
	@Column(name="PreviousVisit")
	private String  PreviousVisit;
	
	
	@Column(name="CreateOn")
	private String  CreateOn;
	
	@Column(name="IsStaff")
	private String  IsStaff;
	
	@Column(name="AuditStatus")
	private String  AuditStatus;
	
	@Column(name="IsVisible")
	private String  IsVisible;
	
	
	@Column(name="UserOnLine")
	private String  UserOnLine;
	
	@Column(name="IPAddress")
	private String  IPAddress;
	
	@Column(name="MACAddress")
	private String  MACAddress;
	
	@Column(name="HomeAddress")
	private String  HomeAddress;
	
	@Column(name="OpenId")
	private String  OpenId;
	
	@Column(name="Question")
	private String  Question;
	
	@Column(name="AnswerQuestion")
	private String  AnswerQuestion;
	
	@Column(name="UserAddress")
	private String  UserAddress;
	
	@Column(name="DeleteMark")
	private String  DeleteMark;
	
	@Column(name="Enabled")
	private String  Enabled;
	
	@Column(name="SortCode")
	private String  SortCode;
	
	@Column(name="Description")
	private String  Description;
	
	@Column(name="LogOnCount")
	private String  LogOnCount;
	
	@Column(name="CreateUserId")
	private String  CreateUserId;
	
	@Column(name="CreateBy")
	private String  CreateBy;
	
	@Column(name="ModifiedOn")
	private String  ModifiedOn;
	
	@Column(name="ModifyUserId")
	private String  ModifyUserId;
	
	@Column(name="ModifiedBy")
	private String  ModifiedBy;
	
	@Column(name="WorkgroupId")
	private String  WorkgroupId;
	
	

	public String getWorkgroupId() {
		return WorkgroupId;
	}

	public void setWorkgroupId(String workgroupId) {
		WorkgroupId = workgroupId;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

	public String getRealName() {
		return RealName;
	}

	public void setRealName(String realName) {
		RealName = realName;
	}

	public Integer getRoleId() {
		return RoleId;
	}

	public void setRoleId(Integer roleId) {
		RoleId = roleId;
	}

	public String getUserFrom() {
		return UserFrom;
	}

	public void setUserFrom(String userFrom) {
		UserFrom = userFrom;
	}

	public String getWorkCategory() {
		return WorkCategory;
	}

	public void setWorkCategory(String workCategory) {
		WorkCategory = workCategory;
	}

	public String getCompanyName() {
		return CompanyName;
	}

	public void setCompanyName(String companyName) {
		CompanyName = companyName;
	}

	public Integer getCompanyId() {
		return CompanyId;
	}

	public void setCompanyId(Integer companyId) {
		CompanyId = companyId;
	}

	public Integer getDepartmentId() {
		return DepartmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		DepartmentId = departmentId;
	}

	public String getWorkgroupName() {
		return WorkgroupName;
	}

	public void setWorkgroupName(String workgroupName) {
		WorkgroupName = workgroupName;
	}

	public String getDepartmentName() {
		return DepartmentName;
	}

	public void setDepartmentName(String departmentName) {
		DepartmentName = departmentName;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public String getMobile() {
		return Mobile;
	}

	public void setMobile(String mobile) {
		Mobile = mobile;
	}

	public String getTelephone() {
		return Telephone;
	}

	public void setTelephone(String telephone) {
		Telephone = telephone;
	}

	public String getOfficeAddr() {
		return OfficeAddr;
	}

	public void setOfficeAddr(String officeAddr) {
		OfficeAddr = officeAddr;
	}

	public String getBirthday() {
		return Birthday;
	}

	public void setBirthday(String birthday) {
		Birthday = birthday;
	}

	public String getDuty() {
		return Duty;
	}

	public void setDuty(String duty) {
		Duty = duty;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getUserPassword() {
		return UserPassword;
	}

	public void setUserPassword(String userPassword) {
		UserPassword = userPassword;
	}

	public String getChangePasswordDate() {
		return ChangePasswordDate;
	}

	public void setChangePasswordDate(String changePasswordDate) {
		ChangePasswordDate = changePasswordDate;
	}

	public String getQICQ() {
		return QICQ;
	}

	public void setQICQ(String qICQ) {
		QICQ = qICQ;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getLang() {
		return Lang;
	}

	public void setLang(String lang) {
		Lang = lang;
	}

	public String getTheme() {
		return Theme;
	}

	public void setTheme(String theme) {
		Theme = theme;
	}

	public String getAllowStartTime() {
		return AllowStartTime;
	}

	public void setAllowStartTime(String allowStartTime) {
		AllowStartTime = allowStartTime;
	}

	public String getAllowEndTime() {
		return AllowEndTime;
	}

	public void setAllowEndTime(String allowEndTime) {
		AllowEndTime = allowEndTime;
	}

	public String getLockStartDate() {
		return LockStartDate;
	}

	public void setLockStartDate(String lockStartDate) {
		LockStartDate = lockStartDate;
	}

	public String getLockEndDate() {
		return LockEndDate;
	}

	public void setLockEndDate(String lockEndDate) {
		LockEndDate = lockEndDate;
	}

	public String getLastVisit() {
		return LastVisit;
	}

	public void setLastVisit(String lastVisit) {
		LastVisit = lastVisit;
	}

	public String getFirstVisit() {
		return FirstVisit;
	}

	public void setFirstVisit(String firstVisit) {
		FirstVisit = firstVisit;
	}

	public String getPreviousVisit() {
		return PreviousVisit;
	}

	public void setPreviousVisit(String previousVisit) {
		PreviousVisit = previousVisit;
	}

	public String getCreateOn() {
		return CreateOn;
	}

	public void setCreateOn(String createOn) {
		CreateOn = createOn;
	}

	public String getIsStaff() {
		return IsStaff;
	}

	public void setIsStaff(String isStaff) {
		IsStaff = isStaff;
	}

	public String getAuditStatus() {
		return AuditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		AuditStatus = auditStatus;
	}

	public String getIsVisible() {
		return IsVisible;
	}

	public void setIsVisible(String isVisible) {
		IsVisible = isVisible;
	}

	public String getUserOnLine() {
		return UserOnLine;
	}

	public void setUserOnLine(String userOnLine) {
		UserOnLine = userOnLine;
	}

	public String getIPAddress() {
		return IPAddress;
	}

	public void setIPAddress(String iPAddress) {
		IPAddress = iPAddress;
	}

	public String getMACAddress() {
		return MACAddress;
	}

	public void setMACAddress(String mACAddress) {
		MACAddress = mACAddress;
	}

	public String getHomeAddress() {
		return HomeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		HomeAddress = homeAddress;
	}

	public String getOpenId() {
		return OpenId;
	}

	public void setOpenId(String openId) {
		OpenId = openId;
	}

	public String getQuestion() {
		return Question;
	}

	public void setQuestion(String question) {
		Question = question;
	}

	public String getAnswerQuestion() {
		return AnswerQuestion;
	}

	public void setAnswerQuestion(String answerQuestion) {
		AnswerQuestion = answerQuestion;
	}

	public String getUserAddress() {
		return UserAddress;
	}

	public void setUserAddress(String userAddress) {
		UserAddress = userAddress;
	}

	public String getDeleteMark() {
		return DeleteMark;
	}

	public void setDeleteMark(String deleteMark) {
		DeleteMark = deleteMark;
	}

	public String getEnabled() {
		return Enabled;
	}

	public void setEnabled(String enabled) {
		Enabled = enabled;
	}

	public String getSortCode() {
		return SortCode;
	}

	public void setSortCode(String sortCode) {
		SortCode = sortCode;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getLogOnCount() {
		return LogOnCount;
	}

	public void setLogOnCount(String logOnCount) {
		LogOnCount = logOnCount;
	}

	public String getCreateUserId() {
		return CreateUserId;
	}

	public void setCreateUserId(String createUserId) {
		CreateUserId = createUserId;
	}

	public String getCreateBy() {
		return CreateBy;
	}

	public void setCreateBy(String createBy) {
		CreateBy = createBy;
	}

	public String getModifiedOn() {
		return ModifiedOn;
	}

	public void setModifiedOn(String modifiedOn) {
		ModifiedOn = modifiedOn;
	}

	public String getModifyUserId() {
		return ModifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		ModifyUserId = modifyUserId;
	}

	public String getModifiedBy() {
		return ModifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		ModifiedBy = modifiedBy;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "UserEntity [UserName=" + UserName + ", Code=" + Code + ", RealName=" + RealName + ", RoleId=" + RoleId
				+ ", UserFrom=" + UserFrom + ", WorkCategory=" + WorkCategory + ", CompanyName=" + CompanyName
				+ ", CompanyId=" + CompanyId + ", DepartmentId=" + DepartmentId + ", WorkgroupName=" + WorkgroupName
				+ ", DepartmentName=" + DepartmentName + ", Gender=" + Gender + ", Mobile=" + Mobile + ", Telephone="
				+ Telephone + ", OfficeAddr=" + OfficeAddr + ", Birthday=" + Birthday + ", Duty=" + Duty + ", Title="
				+ Title + ", UserPassword=" + UserPassword + ", ChangePasswordDate=" + ChangePasswordDate + ", QICQ="
				+ QICQ + ", Email=" + Email + ", Lang=" + Lang + ", Theme=" + Theme + ", AllowStartTime="
				+ AllowStartTime + ", AllowEndTime=" + AllowEndTime + ", LockStartDate=" + LockStartDate
				+ ", LockEndDate=" + LockEndDate + ", LastVisit=" + LastVisit + ", FirstVisit=" + FirstVisit
				+ ", PreviousVisit=" + PreviousVisit + ", CreateOn=" + CreateOn + ", IsStaff=" + IsStaff
				+ ", AuditStatus=" + AuditStatus + ", IsVisible=" + IsVisible + ", UserOnLine=" + UserOnLine
				+ ", IPAddress=" + IPAddress + ", MACAddress=" + MACAddress + ", HomeAddress=" + HomeAddress
				+ ", OpenId=" + OpenId + ", Question=" + Question + ", AnswerQuestion=" + AnswerQuestion
				+ ", UserAddress=" + UserAddress + ", DeleteMark=" + DeleteMark + ", Enabled=" + Enabled + ", SortCode="
				+ SortCode + ", Description=" + Description + ", LogOnCount=" + LogOnCount + ", CreateUserId="
				+ CreateUserId + ", CreateBy=" + CreateBy + ", ModifiedOn=" + ModifiedOn + ", ModifyUserId="
				+ ModifyUserId + ", ModifiedBy=" + ModifiedBy + ", WorkgroupId=" + WorkgroupId + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
}
