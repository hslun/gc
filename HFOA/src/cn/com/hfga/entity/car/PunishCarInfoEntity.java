package cn.com.hfga.entity.car;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import cn.com.hfga.entity.common.CommonIntIdEntity;


@Entity
@Table(name="B_CarPunish")
public class PunishCarInfoEntity extends CommonIntIdEntity{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	@Column(name="CarApplyId",nullable=true)
	private String CarApplyId;

	@Column(name="Driver",nullable=true)
	private String Driver;
	
	@Column(name="TroubleSport",nullable=true)
	private String TroubleSport;
	
	@Column(name="TroubleTime",nullable=true)
	private String TroubleTime;
	
	@Column(name="TroubleDescribe",nullable=true)
	private String TroubleDescribe;
	
	@Column(name="DescentCode",nullable=true)
	private String DescentCode;
	
	
	@Column(name="PunishFare",nullable=true)
	private float PunishFare;
	
	@Column(name="LoginTime",nullable=true)
	private String LoginTime;
	
	@Column(name="ApplyMan",nullable=true)
	private String ApplyMan;
	
	@Column(name="Department",nullable=true)
	private String Department;
	
	@Column(name="CarCode",nullable=true)
	private String CarCode;
	
	@Column(name="CarType",nullable=true)
	private String CarType;


	public String getCarApplyId() {
		return CarApplyId;
	}

	public void setCarApplyId(String carApplyId) {
		CarApplyId = carApplyId;
	}

	public String getDriver() {
		return Driver;
	}

	public void setDriver(String driver) {
		Driver = driver;
	}

	public String getTroubleSport() {
		return TroubleSport;
	}

	public void setTroubleSport(String troubleSport) {
		TroubleSport = troubleSport;
	}

	public String getTroubleTime() {
		return TroubleTime;
	}

	public void setTroubleTime(String troubleTime) {
		TroubleTime = troubleTime;
	}

	public String getTroubleDescribe() {
		return TroubleDescribe;
	}

	public void setTroubleDescribe(String troubleDescribe) {
		TroubleDescribe = troubleDescribe;
	}

	public String getDescentCode() {
		return DescentCode;
	}

	public void setDescentCode(String descentCode) {
		DescentCode = descentCode;
	}

	public float getPunishFare() {
		return PunishFare;
	}

	public void setPunishFare(float punishFare) {
		PunishFare = punishFare;
	}

	public String getLoginTime() {
		return LoginTime;
	}

	public void setLoginTime(String loginTime) {
		LoginTime = loginTime;
	}

	public String getApplyMan() {
		return ApplyMan;
	}

	public void setApplyMan(String applyMan) {
		ApplyMan = applyMan;
	}

	public String getDepartment() {
		return Department;
	}

	public void setDepartment(String department) {
		Department = department;
	}

	public String getCarCode() {
		return CarCode;
	}

	public void setCarCode(String carCode) {
		CarCode = carCode;
	}

	public String getCarType() {
		return CarType;
	}

	public void setCarType(String carType) {
		CarType = carType;
	}
	
	
}
