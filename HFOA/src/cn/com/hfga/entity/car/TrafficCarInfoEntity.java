package cn.com.hfga.entity.car;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import cn.com.hfga.entity.common.CommonIntIdEntity;


@Entity
@Table(name="B_TroubleLogin")
public class TrafficCarInfoEntity extends CommonIntIdEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@Column(name="CarApply",nullable=true)
	private String CarApply;
	
	@Column(name="Driver",nullable=true)
	private String Driver;
	
	@Column(name="TroubleSport",nullable=true)
	private String TroubleSport;
	
	@Column(name="TroubleTime",nullable=true)
	private String TroubleTime;
	
	@Column(name="ResponseDescrib",nullable=true)
	private String ResponseDescrib;
	
	@Column(name="TroubleDescribe",nullable=true)
	private String TroubleDescribe;
	
	@Column(name="LoginTime",nullable=true)
	private String LoginTime;
	
	@Column(name="ApplyMan",nullable=true)
	private String ApplyMan;
	
	@Column(name="Department",nullable=true)
	private String Department;
	
	@Column(name="CarCode",nullable=true)
	private String CarCode;
	
	@Column(name="CarNum",nullable=true)
	private String CarNum;

	public String getCarApply() {
		return CarApply;
	}

	public void setCarApply(String carApply) {
		CarApply = carApply;
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

	public String getResponseDescrib() {
		return ResponseDescrib;
	}

	public void setResponseDescrib(String responseDescrib) {
		ResponseDescrib = responseDescrib;
	}

	public String getTroubleDescribe() {
		return TroubleDescribe;
	}

	public void setTroubleDescribe(String troubleDescribe) {
		TroubleDescribe = troubleDescribe;
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

	public String getCarNum() {
		return CarNum;
	}

	public void setCarNum(String carNum) {
		CarNum = carNum;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
}
