package cn.com.hfga.entity.travelExpenses;

import cn.com.hfga.entity.common.IEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="b_travelexpensesapprove")
public class ApproveExpensesEntity implements IEntity {
  @Id
  @Column(name="ID")
  private String ID;

  @Column(name="TravelExpenseId",nullable=true)
  private String TravelExpenseId;

  @Column(name="VehicleCost",nullable=true)
  private float VehicleCost;

  @Column(name="FoodAllowance",nullable=true)
  private float FoodAllowance;

  @Column(name="HotelExpense",nullable=true)
  private float HotelExpense;

  @Column(name="ParValueAllowance",nullable=true)
  private float ParValueAllowance;

  @Column(name="UrbanTraffic",nullable=true)
  private float UrbanTraffic;

  @Column(name="OtherCost",nullable=true)
  private float OtherCost;

  @Column(name="RepayCost",nullable=true)
  private float RepayCost;

  @Column(name="SupplementalCost",nullable=true)
  private float SupplementalCost;

  @Column(name="InputTax",nullable=true)
  private float InputTax;

  @Column(name="TotalExpenses",nullable=true)
  private float TotalExpenses;

  @Column(name="PaidTime",nullable=true)
  private String PaidTime;

  @Column(name="FundSource",nullable=true)
  private String FundSource;

  @Column(name="VoucherNum",nullable=true)
  private String VoucherNum;

  @Column(name="IsTestCost",nullable=true)
  private String IsTestCost;

  @Column(name="TestSite",nullable=true)
  private String TestSite;

  @Column(name="Illustration",nullable=true)
  private String Illustration;

  public String getID() {
    return ID;
  }

  public void setID(String ID) {
    this.ID = ID;
  }

  public String getTravelExpenseId() {
    return TravelExpenseId;
  }

  public void setTravelExpenseId(String travelExpenseId) {
    TravelExpenseId = travelExpenseId;
  }

  public float getVehicleCost() {
    return VehicleCost;
  }

  public void setVehicleCost(float vehicleCost) {
    VehicleCost = vehicleCost;
  }

  public float getFoodAllowance() {
    return FoodAllowance;
  }

  public void setFoodAllowance(float foodAllowance) {
    FoodAllowance = foodAllowance;
  }

  public float getHotelExpense() {
    return HotelExpense;
  }

  public void setHotelExpense(float hotelExpense) {
    HotelExpense = hotelExpense;
  }

  public float getParValueAllowance() {
    return ParValueAllowance;
  }

  public void setParValueAllowance(float parValueAllowance) {
    ParValueAllowance = parValueAllowance;
  }

  public float getUrbanTraffic() {
    return UrbanTraffic;
  }

  public void setUrbanTraffic(float urbanTraffic) {
    UrbanTraffic = urbanTraffic;
  }

  public float getOtherCost() {
    return OtherCost;
  }

  public void setOtherCost(float otherCost) {
    OtherCost = otherCost;
  }

  public float getRepayCost() {
    return RepayCost;
  }

  public void setRepayCost(float repayCost) {
    RepayCost = repayCost;
  }

  public float getSupplementalCost() {
    return SupplementalCost;
  }

  public void setSupplementalCost(float supplementalCost) {
    SupplementalCost = supplementalCost;
  }

  public float getInputTax() {
    return InputTax;
  }

  public void setInputTax(float inputTax) {
    InputTax = inputTax;
  }

  public float getTotalExpenses() {
    return TotalExpenses;
  }

  public void setTotalExpenses(float totalExpenses) {
    TotalExpenses = totalExpenses;
  }

  public String getPaidTime() {
    return PaidTime;
  }

  public void setPaidTime(String paidTime) {
    PaidTime = paidTime;
  }

  public String getFundSource() {
    return FundSource;
  }

  public void setFundSource(String fundSource) {
    FundSource = fundSource;
  }

  public String getVoucherNum() {
    return VoucherNum;
  }

  public void setVoucherNum(String voucherNum) {
    VoucherNum = voucherNum;
  }

  public String getIsTestCost() {
    return IsTestCost;
  }

  public void setIsTestCost(String isTestCost) {
    IsTestCost = isTestCost;
  }

  public String getTestSite() {
    return TestSite;
  }

  public void setTestSite(String testSite) {
    TestSite = testSite;
  }

  public String getIllustration() {
    return Illustration;
  }

  public void setIllustration(String illustration) {
    Illustration = illustration;
  }

  @Override
  public String toString() {
    return "ApproveExpensesEntity{" +
        "ID='" + ID + '\'' +
        ", TravelExpenseId='" + TravelExpenseId + '\'' +
        ", VehicleCost=" + VehicleCost +
        ", FoodAllowance=" + FoodAllowance +
        ", HotelExpense=" + HotelExpense +
        ", ParValueAllowance=" + ParValueAllowance +
        ", UrbanTraffic=" + UrbanTraffic +
        ", OtherCost=" + OtherCost +
        ", RepayCost=" + RepayCost +
        ", SupplementalCost=" + SupplementalCost +
        ", InputTax=" + InputTax +
        ", TotalExpenses=" + TotalExpenses +
        ", PaidTime='" + PaidTime + '\'' +
        ", FundSource='" + FundSource + '\'' +
        ", VoucherNum='" + VoucherNum + '\'' +
        ", IsTestCost='" + IsTestCost + '\'' +
        ", TestSite='" + TestSite + '\'' +
        ", Illustration='" + Illustration + '\'' +
        '}';
  }
}
