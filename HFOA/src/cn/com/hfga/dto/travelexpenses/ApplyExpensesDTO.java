package cn.com.hfga.dto.travelexpenses;

import javax.persistence.Column;
import java.util.List;

public class ApplyExpensesDTO {
  private String id;
  private String department;//部门
  private String travelers;//出差人
  private String cause;//事由
  private String tripDetails;//[{出发地、目的地、出行方式}]
  private String beginTime;//出发日期
  private String endTime;//返回日期
  private int travelDays;//出差天数
  private float totalBudget;//总预算
  private String isTest;//是否属于试验
  private String applyTime;//申请时间
  private String applyMan;//申请人
  private String approveMan;//审批人
  private String remarks;//备注
  private String approveState;//待部门审批；待修改（部门审批不通过）；待执行（部门审批通过）；待财务审批（执行）；驳回待修改（财务驳回）；已登记（财务登记）


  private String tripMode;//出行方式
  private String startAddress;//出发地
  private String middAddress;//途径地
  private String endAddress;//目的地

  //不在数据库字段
  private List<TravelAddressDTO> tripDetails_list;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  public String getTravelers() {
    return travelers;
  }

  public void setTravelers(String travelers) {
    this.travelers = travelers;
  }

  public String getCause() {
    return cause;
  }

  public void setCause(String cause) {
    this.cause = cause;
  }

  public String getTripDetails() {
    return tripDetails;
  }

  public void setTripDetails(String tripDetails) {
    this.tripDetails = tripDetails;
  }

  public String getBeginTime() {
    return beginTime;
  }

  public void setBeginTime(String beginTime) {
    this.beginTime = beginTime;
  }

  public String getEndTime() {
    return endTime;
  }

  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }

  public int getTravelDays() {
    return travelDays;
  }

  public void setTravelDays(int travelDays) {
    this.travelDays = travelDays;
  }

  public float getTotalBudget() {
    return totalBudget;
  }

  public void setTotalBudget(float totalBudget) {
    this.totalBudget = totalBudget;
  }

  public String getIsTest() {
    return isTest;
  }

  public void setIsTest(String isTest) {
    this.isTest = isTest;
  }

  public String getApplyTime() {
    return applyTime;
  }

  public void setApplyTime(String applyTime) {
    this.applyTime = applyTime;
  }

  public String getApplyMan() {
    return applyMan;
  }

  public void setApplyMan(String applyMan) {
    this.applyMan = applyMan;
  }

  public String getApproveMan() {
    return approveMan;
  }

  public void setApproveMan(String approveMan) {
    this.approveMan = approveMan;
  }

  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  public String getApproveState() {
    return approveState;
  }

  public void setApproveState(String approveState) {
    this.approveState = approveState;
  }

  public String getTripMode() {
    return tripMode;
  }

  public void setTripMode(String tripMode) {
    this.tripMode = tripMode;
  }

  public String getStartAddress() {
    return startAddress;
  }

  public void setStartAddress(String startAddress) {
    this.startAddress = startAddress;
  }

  public String getMiddAddress() {
    return middAddress;
  }

  public void setMiddAddress(String middAddress) {
    this.middAddress = middAddress;
  }

  public String getEndAddress() {
    return endAddress;
  }

  public void setEndAddress(String endAddress) {
    this.endAddress = endAddress;
  }

  public List<TravelAddressDTO> getTripDetails_list() {
    return tripDetails_list;
  }

  public void setTripDetails_list(List<TravelAddressDTO> tripDetails_list) {
    this.tripDetails_list = tripDetails_list;
  }

@Override
public String toString() {
	return "ApplyExpensesDTO [id=" + id + ", department=" + department + ", travelers=" + travelers + ", cause=" + cause
			+ ", tripDetails=" + tripDetails + ", beginTime=" + beginTime + ", endTime=" + endTime + ", travelDays="
			+ travelDays + ", totalBudget=" + totalBudget + ", isTest=" + isTest + ", applyTime=" + applyTime
			+ ", applyMan=" + applyMan + ", approveMan=" + approveMan + ", remarks=" + remarks + ", approveState="
			+ approveState + ", tripMode=" + tripMode + ", startAddress=" + startAddress + ", middAddress="
			+ middAddress + ", endAddress=" + endAddress + ", tripDetails_list=" + tripDetails_list + "]";
}
  
  
  
}
