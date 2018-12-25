package cn.com.hfga.dto.travelexpenses;

import javax.persistence.Column;
import java.util.List;

public class ApplyExpensesDTO {
  private String id;
  private String department;//����
  private String travelers;//������
  private String cause;//����
  private String tripDetails;//[{�����ء�Ŀ�ĵء����з�ʽ}]
  private String beginTime;//��������
  private String endTime;//��������
  private int travelDays;//��������
  private float totalBudget;//��Ԥ��
  private String isTest;//�Ƿ���������
  private String applyTime;//����ʱ��
  private String applyMan;//������
  private String approveMan;//������
  private String remarks;//��ע
  private String approveState;//���������������޸ģ�����������ͨ��������ִ�У���������ͨ������������������ִ�У������ش��޸ģ����񲵻أ����ѵǼǣ�����Ǽǣ�


  private String tripMode;//���з�ʽ
  private String startAddress;//������
  private String middAddress;//;����
  private String endAddress;//Ŀ�ĵ�

  //�������ݿ��ֶ�
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
