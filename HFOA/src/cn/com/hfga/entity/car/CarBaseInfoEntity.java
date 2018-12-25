package cn.com.hfga.entity.car;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.com.hfga.entity.common.IEntity;
@Entity
@Table(name="B_CarBaseInfo")
public class CarBaseInfoEntity implements IEntity {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="CarNum")
	private String CarNum;
	
	@Column(name="ID")
	private int ID;
	
	@Column(name="CarCode")
	private String CarCode;
	
	@Column(name="CarType")
	private String CarType;
	
	@Column(name="CarUnit")
	private String CarUnit;
	
	@Column(name="CarBuyTime")
	private String CarBuyTime;
	
	@Column(name="CarDetailInfo")
	private String CarDetailInfo;
	
	@Column(name="CarInsuranceInfo1")
	private String CarInsuranceInfo1;
	
	@Column(name="CarInsuranceInfo")
	private String CarInsuranceInfo;
	
	@Column(name="CardVale")
	private float CardVale;
	
	@Column(name="CarDistance")
	private float CarDistance;
	
	@Column(name="CarInsuranceInfoDetal")
	private String CarInsuranceInfoDetal;
	
	@Column(name="others")
	private String others;
	
	@Column(name="CarState")
	private String CarState;
	
	public String getCarNum() {
		return CarNum;
	}

	public void setCarNum(String carNum) {
		CarNum = carNum;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getCarType() {
		return CarType;
	}

	public void setCarType(String carType) {
		CarType = carType;
	}

	public String getCarUnit() {
		return CarUnit;
	}

	public void setCarUnit(String carUnit) {
		CarUnit = carUnit;
	}

	public String getCarCode() {
		return CarCode;
	}

	public void setCarCode(String carCode) {
		CarCode = carCode;
	}

	public String getCarBuyTime() {
		return CarBuyTime;
	}

	public void setCarBuyTime(String carBuyTime) {
		CarBuyTime = carBuyTime;
	}

	public String getCarDetailInfo() {
		return CarDetailInfo;
	}

	public void setCarDetailInfo(String carDetailInfo) {
		CarDetailInfo = carDetailInfo;
	}

	public String getCarInsuranceInfo1() {
		return CarInsuranceInfo1;
	}

	public void setCarInsuranceInfo1(String carInsuranceInfo1) {
		CarInsuranceInfo1 = carInsuranceInfo1;
	}

	public String getCarInsuranceInfo() {
		return CarInsuranceInfo;
	}

	public void setCarInsuranceInfo(String carInsuranceInfo) {
		CarInsuranceInfo = carInsuranceInfo;
	}

	public float getCardVale() {
		return CardVale;
	}

	public void setCardVale(float cardVale) {
		CardVale = cardVale;
	}

	public float getCarDistance() {
		return CarDistance;
	}

	public void setCarDistance(float carDistance) {
		CarDistance = carDistance;
	}

	public String getCarInsuranceInfoDetal() {
		return CarInsuranceInfoDetal;
	}

	public void setCarInsuranceInfoDetal(String carInsuranceInfoDetal) {
		CarInsuranceInfoDetal = carInsuranceInfoDetal;
	}

	public String getOthers() {
		return others;
	}

	public void setOthers(String others) {
		this.others = others;
	}

	public String getCarState() {
		return CarState;
	}

	public void setCarState(String carState) {
		CarState = carState;
	}

	public float getPeasonNum() {
		return PeasonNum;
	}

	public void setPeasonNum(float peasonNum) {
		PeasonNum = peasonNum;
	}

	public String getSuspendDay() {
		return SuspendDay;
	}

	public void setSuspendDay(String suspendDay) {
		SuspendDay = suspendDay;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Column(name="PeasonNum")
	private float PeasonNum;
	
	@Column(name="SuspendDay")
	private String SuspendDay;
	
	
	
	
	
}	
