package cn.com.hfga.entity.car;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.com.hfga.entity.common.IEntity;

@Entity
@Table(name="B_CarApplyInfo")
public class CarApplyInfoEntity implements IEntity{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ApplyId")
	private String ApplyId;
	
	@Column(name="CarEndExamin",nullable=true)
	private int CarEndExamin;
	
	public int getCarEndExamin() {
		return CarEndExamin;
	}

	public void setCarEndExamin(int carEndExamin) {
		CarEndExamin = carEndExamin;
	}

	@Column(name="Department",nullable=true)
	private String Department;
	
	@Column(name="ApplyUserName",nullable=true)
	private String ApplyUserName;

	@Column(name="ApplyMan",nullable=true)
	private String ApplyMan;
	
	@Column(name="ApproveMan",nullable=true)
	private String ApproveMan;
	
	
	@Column(name="CarId",nullable=true)
	private String CarId;
	
	@Column(name="CarType",nullable=true)
	private String CarType;

	@Column(name="CarCode",nullable=true)
	private String CarCode;
	
	@Column(name="Driver",nullable=true)
	private String Driver;
	

	@Column(name="CompareManNum",nullable=true)
	private int  CompareManNum=0;
	
	@Column(name="LengthBegin",nullable=true)
	private String LengthBegin;

	@Column(name="LengthEnd",nullable=true)
	private String LengthEnd;
	
	@Column(name="AccountLength",nullable=true)
	private double AccountLength=0;
	
	@Column(name="BeginTimePlan",nullable=true)
	private String  BeginTimePlan;
	
	@Column(name="EndTimePlan",nullable=true)
	private String EndTimePlan;

	@Column(name="BeginTime",nullable=true)
	private String BeginTime;
	
	@Column(name="EndTime",nullable=true)
	private String EndTime;
	
	@Column(name="UseCarReason",nullable=true)
	private String  UseCarReason;
	
	public String getApplyId() {
		return ApplyId;
	}

	public void setApplyId(String applyId) {
		ApplyId = applyId;
	}

	public String getDepartment() {
		return Department;
	}

	public void setDepartment(String department) {
		Department = department;
	}

	public String getApplyUserName() {
		return ApplyUserName;
	}

	public void setApplyUserName(String applyUserName) {
		ApplyUserName = applyUserName;
	}

	public String getApplyMan() {
		return ApplyMan;
	}

	public void setApplyMan(String applyMan) {
		ApplyMan = applyMan;
	}

	public String getApproveMan() {
		return ApproveMan;
	}

	public void setApproveMan(String approveMan) {
		ApproveMan = approveMan;
	}

	public String getCarId() {
		return CarId;
	}

	public void setCarId(String carId) {
		CarId = carId;
	}

	public String getCarType() {
		return CarType;
	}

	public void setCarType(String carType) {
		CarType = carType;
	}

	public String getCarCode() {
		return CarCode;
	}

	public void setCarCode(String carCode) {
		CarCode = carCode;
	}

	public String getDriver() {
		return Driver;
	}

	public void setDriver(String driver) {
		Driver = driver;
	}

	public int getCompareManNum() {
		return CompareManNum;
	}

	public void setCompareManNum(int compareManNum) {
		CompareManNum = compareManNum;
	}

	public String getLengthBegin() {
		return LengthBegin;
	}

	public void setLengthBegin(String lengthBegin) {
		LengthBegin = lengthBegin;
	}

	public String getLengthEnd() {
		return LengthEnd;
	}

	public void setLengthEnd(String lengthEnd) {
		LengthEnd = lengthEnd;
	}

	public double getAccountLength() {
		return AccountLength;
	}

	public void setAccountLength(double accountLength) {
		AccountLength = accountLength;
	}

	public String getBeginTimePlan() {
		return BeginTimePlan;
	}

	public void setBeginTimePlan(String beginTimePlan) {
		BeginTimePlan = beginTimePlan;
	}

	public String getEndTimePlan() {
		return EndTimePlan;
	}

	public void setEndTimePlan(String endTimePlan) {
		EndTimePlan = endTimePlan;
	}

	public String getBeginTime() {
		return BeginTime;
	}

	public void setBeginTime(String beginTime) {
		BeginTime = beginTime;
	}

	public String getEndTime() {
		return EndTime;
	}

	public void setEndTime(String endTime) {
		EndTime = endTime;
	}

	public String getUseCarReason() {
		return UseCarReason;
	}

	public void setUseCarReason(String useCarReason) {
		UseCarReason = useCarReason;
	}

	public String getCarRoutin() {
		return CarRoutin;
	}

	public void setCarRoutin(String carRoutin) {
		CarRoutin = carRoutin;
	}

	public int getCarBeginExamin() {
		return CarBeginExamin;
	}

//	public int getId() {
//		return Id;
//	}
//
//	public void setId(int id) {
//		Id = id;
//	}

	public void setCarBeginExamin(int carBeginExamin) {
		CarBeginExamin = carBeginExamin;
	}

	public String getApplyTime() {
		return ApplyTime;
	}

	public void setApplyTime(String applyTime) {
		ApplyTime = applyTime;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public String getCarBeginProblem() {
		return CarBeginProblem;
	}

	public void setCarBeginProblem(String carBeginProblem) {
		CarBeginProblem = carBeginProblem;
	}

	public String getCarEndProblem() {
		return CarEndProblem;
	}

	public void setCarEndProblem(String carEndProblem) {
		CarEndProblem = carEndProblem;
	}

	public String getApproveState() {
		return ApproveState;
	}

	public void setApproveState(String approveState) {
		ApproveState = approveState;
	}

	public String getStartAddress() {
		return StartAddress;
	}

	public void setStartAddress(String startAddress) {
		StartAddress = startAddress;
	}

	public String getEndAddress() {
		return EndAddress;
	}

	public void setEndAddress(String endAddress) {
		EndAddress = endAddress;
	}

	public String getMiddAddress1() {
		return MiddAddress1;
	}

	public void setMiddAddress1(String middAddress1) {
		MiddAddress1 = middAddress1;
	}

	public String getMiddAddress2() {
		return MiddAddress2;
	}

	public void setMiddAddress2(String middAddress2) {
		MiddAddress2 = middAddress2;
	}

	public double getAccountPlanTime() {
		return AccountPlanTime;
	}

	public void setAccountPlanTime(double accountPlanTime) {
		AccountPlanTime = accountPlanTime;
	}

	public double getAccountRealTime() {
		return AccountRealTime;
	}

	public void setAccountRealTime(double accountRealTime) {
		AccountRealTime = accountRealTime;
	}

	public String getRealApproveMan() {
		return RealApproveMan;
	}

	public void setRealApproveMan(String realApproveMan) {
		RealApproveMan = realApproveMan;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Column(name="CarRoutin",nullable=true)
	private String CarRoutin;

	@Column(name="CarBeginExamin",nullable=true)
	private int CarBeginExamin=0;
	
//	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	@Column(name="Id",nullable=true)
//	private int Id;
	
	@Column(name="ApplyTime",nullable=true)
	private String ApplyTime;
	
	@Column(name="State",nullable=true)
	private String  State;
	
	@Column(name="CarBeginProblem",nullable=true)
	private String CarBeginProblem;

	@Column(name="CarEndProblem",nullable=true)
	private String CarEndProblem;
	
	@Column(name="ApproveState",nullable=true)
	private String ApproveState;
	
	
	@Column(name="StartAddress",nullable=true)
	private String StartAddress;
	
	@Column(name="EndAddress",nullable=true)
	private String  EndAddress;
	
	@Column(name="MiddAddress1",nullable=true)
	private String MiddAddress1;

	@Column(name="MiddAddress2",nullable=true)
	private String MiddAddress2;
	
	@Column(name="AccountPlanTime",nullable=true)
	private double AccountPlanTime=0;
	
	@Column(name="AccountRealTime",nullable=true)
	private double AccountRealTime=0;
	
	@Column(name="RealApproveMan",nullable=true)
	private String RealApproveMan;
	
	
	
	
	
	
}
