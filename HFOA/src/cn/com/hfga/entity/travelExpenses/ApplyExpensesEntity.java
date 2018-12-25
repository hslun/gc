package cn.com.hfga.entity.travelExpenses;

import cn.com.hfga.entity.common.IEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="b_travelexpenses")
public class ApplyExpensesEntity implements IEntity {
  @Id
  @Column(name="ID")
  private String ID;

  @Column(name="Department",nullable=true)
  private String Department;

  @Column(name="Travelers",nullable=true)
  private String Travelers;

  @Column(name="Cause",nullable=true)
  private String Cause;

  @Column(name="TripDetails",nullable=true)
  private String TripDetails;

  @Column(name="BeginTime",nullable=true)
  private String BeginTime;

  @Column(name="EndTime",nullable=true)
  private String EndTime;

  @Column(name="TravelDays",nullable=true)
  private int TravelDays;

  @Column(name="TotalBudget",nullable=true)
  private float TotalBudget;

  @Column(name="IsTest",nullable=true)
  private String IsTest;

  @Column(name="ApplyTime",nullable=true)
  private String ApplyTime;

  @Column(name="ApplyMan",nullable=true)
  private String ApplyMan;

  @Column(name="ApproveMan",nullable=true)
  private String ApproveMan;

  @Column(name="Remarks",nullable=true)
  private String Remarks;

  @Column(name="ApproveState",nullable=true)
  private String ApproveState;

  public String getID() {
    return ID;
  }

  public void setID(String ID) {
    this.ID = ID;
  }

  public String getDepartment() {
    return Department;
  }

  public void setDepartment(String department) {
    Department = department;
  }

  public String getTravelers() {
    return Travelers;
  }

  public void setTravelers(String travelers) {
    Travelers = travelers;
  }

  public String getCause() {
    return Cause;
  }

  public void setCause(String cause) {
    Cause = cause;
  }

  public String getTripDetails() {
    return TripDetails;
  }

  public void setTripDetails(String tripDetails) {
    TripDetails = tripDetails;
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

  public int getTravelDays() {
    return TravelDays;
  }

  public void setTravelDays(int travelDays) {
    TravelDays = travelDays;
  }

  public float getTotalBudget() {
    return TotalBudget;
  }

  public void setTotalBudget(float totalBudget) {
    TotalBudget = totalBudget;
  }

  public String getIsTest() {
    return IsTest;
  }

  public void setIsTest(String isTest) {
    IsTest = isTest;
  }

  public String getApplyTime() {
    return ApplyTime;
  }

  public void setApplyTime(String applyTime) {
    ApplyTime = applyTime;
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

  public String getRemarks() {
    return Remarks;
  }

  public void setRemarks(String remarks) {
    Remarks = remarks;
  }

  public String getApproveState() {
    return ApproveState;
  }

  public void setApproveState(String approveState) {
    ApproveState = approveState;
  }

  @Override
  public String toString() {
    return "ApplyExpensesEntity{" +
        "ID='" + ID + '\'' +
        ", Department='" + Department + '\'' +
        ", Travelers='" + Travelers + '\'' +
        ", Cause='" + Cause + '\'' +
        ", TripDetails='" + TripDetails + '\'' +
        ", BeginTime='" + BeginTime + '\'' +
        ", EndTime='" + EndTime + '\'' +
        ", TravelDays=" + TravelDays +
        ", TotalBudget=" + TotalBudget +
        ", IsTest='" + IsTest + '\'' +
        ", ApplyTime='" + ApplyTime + '\'' +
        ", ApplyMan='" + ApplyMan + '\'' +
        ", ApproveMan='" + ApproveMan + '\'' +
        ", Remarks='" + Remarks + '\'' +
        ", ApproveState='" + ApproveState + '\'' +
        '}';
  }
}
