package com.hfoa.entity.travelExpenses;

import java.io.Serializable;
import java.util.List;


public class ApproveExpensesEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	private String travelExpenseId;//���ʷ����뵥id
	private float vehicleCost;//��ͨ���߽��
	private float foodAllowance;//��ʳ�Ѳ���
	private float hotelExpense;//ס�޷�
	private float parValueAllowance;//Ʊ�油��
	private float urbanTraffic;//���ڽ�ͨ
	private float otherCost;//��������
	private float repayCost;//������
	private float supplementalCost;//������
	private float inputTax;//����˰��
	private float totalExpenses;//�����ܶ�
	private String paidTime;//����ʱ��
	private String fundSource;//������Դ
	private String voucherNum;//ƾ֤��
	private String isTestCost;//�Ƿ�Ϊ�����
	private String testSite;//�����ֳ�
	private String illustration;//˵��

	  //�������ݿ��ֶ�
	private String searchBeginTime;//��ѯʱ��ʼʱ���
	private String searchEndTime;//��ѯʱ����ʱ���
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTravelExpenseId() {
		return travelExpenseId;
	}
	public void setTravelExpenseId(String travelExpenseId) {
		this.travelExpenseId = travelExpenseId;
	}
	public float getVehicleCost() {
		return vehicleCost;
	}
	public void setVehicleCost(float vehicleCost) {
		this.vehicleCost = vehicleCost;
	}
	public float getFoodAllowance() {
		return foodAllowance;
	}
	public void setFoodAllowance(float foodAllowance) {
		this.foodAllowance = foodAllowance;
	}
	public float getHotelExpense() {
		return hotelExpense;
	}
	public void setHotelExpense(float hotelExpense) {
		this.hotelExpense = hotelExpense;
	}
	public float getParValueAllowance() {
		return parValueAllowance;
	}
	public void setParValueAllowance(float parValueAllowance) {
		this.parValueAllowance = parValueAllowance;
	}
	public float getUrbanTraffic() {
		return urbanTraffic;
	}
	public void setUrbanTraffic(float urbanTraffic) {
		this.urbanTraffic = urbanTraffic;
	}
	public float getOtherCost() {
		return otherCost;
	}
	public void setOtherCost(float otherCost) {
		this.otherCost = otherCost;
	}
	public float getRepayCost() {
		return repayCost;
	}
	public void setRepayCost(float repayCost) {
		this.repayCost = repayCost;
	}
	public float getSupplementalCost() {
		return supplementalCost;
	}
	public void setSupplementalCost(float supplementalCost) {
		this.supplementalCost = supplementalCost;
	}
	public float getInputTax() {
		return inputTax;
	}
	public void setInputTax(float inputTax) {
		this.inputTax = inputTax;
	}
	public float getTotalExpenses() {
		return totalExpenses;
	}
	public void setTotalExpenses(float totalExpenses) {
		this.totalExpenses = totalExpenses;
	}
	public String getPaidTime() {
		return paidTime;
	}
	public void setPaidTime(String paidTime) {
		this.paidTime = paidTime;
	}
	public String getFundSource() {
		return fundSource;
	}
	public void setFundSource(String fundSource) {
		this.fundSource = fundSource;
	}
	public String getVoucherNum() {
		return voucherNum;
	}
	public void setVoucherNum(String voucherNum) {
		this.voucherNum = voucherNum;
	}
	public String getIsTestCost() {
		return isTestCost;
	}
	public void setIsTestCost(String isTestCost) {
		this.isTestCost = isTestCost;
	}
	public String getTestSite() {
		return testSite;
	}
	public void setTestSite(String testSite) {
		this.testSite = testSite;
	}
	public String getIllustration() {
		return illustration;
	}
	public void setIllustration(String illustration) {
		this.illustration = illustration;
	}
	public String getSearchBeginTime() {
		return searchBeginTime;
	}
	public void setSearchBeginTime(String searchBeginTime) {
		this.searchBeginTime = searchBeginTime;
	}
	public String getSearchEndTime() {
		return searchEndTime;
	}
	public void setSearchEndTime(String searchEndTime) {
		this.searchEndTime = searchEndTime;
	}
	@Override
	public String toString() {
		return "ApproveExpensesEntity [id=" + id + ", travelExpenseId=" + travelExpenseId + ", vehicleCost="
				+ vehicleCost + ", foodAllowance=" + foodAllowance + ", hotelExpense=" + hotelExpense
				+ ", parValueAllowance=" + parValueAllowance + ", urbanTraffic=" + urbanTraffic + ", otherCost="
				+ otherCost + ", repayCost=" + repayCost + ", supplementalCost=" + supplementalCost + ", inputTax="
				+ inputTax + ", totalExpenses=" + totalExpenses + ", paidTime=" + paidTime + ", fundSource="
				+ fundSource + ", voucherNum=" + voucherNum + ", isTestCost=" + isTestCost + ", testSite=" + testSite
				+ ", illustration=" + illustration + ", searchBeginTime=" + searchBeginTime + ", searchEndTime="
				+ searchEndTime + "]";
	}
	
	

	


	
	
	
		
}
