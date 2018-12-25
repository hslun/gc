package cn.com.hfga.entity.car;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.com.hfga.entity.common.CommonIntIdEntity;
import cn.com.hfga.entity.common.IEntity;

@Entity
@Table(name="B_ProtectCar")
public class ProtectCarInfoEntity implements IEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="Id")
	private int Id;
	
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getCarApplyId() {
		return CarApplyId;
	}

	public void setCarApplyId(String carApplyId) {
		CarApplyId = carApplyId;
	}

	public String getProtectContent() {
		return ProtectContent;
	}

	public void setProtectContent(String protectContent) {
		ProtectContent = protectContent;
	}

	public String getPtotectUnit() {
		return PtotectUnit;
	}

	public void setPtotectUnit(String ptotectUnit) {
		PtotectUnit = ptotectUnit;
	}

	public Float getPtotectFare() {
		return PtotectFare;
	}

	public void setPtotectFare(Float ptotectFare) {
		PtotectFare = ptotectFare;
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

	@Column(name="CarApplyId",nullable=true)
	private String CarApplyId;
	
	@Column(name="ProtectContent",nullable=true)
	private String ProtectContent;
	
	@Column(name="PtotectUnit",nullable=true)
	private String PtotectUnit;
	
	@Column(name="PtotectFare",nullable=true)
	private Float PtotectFare;
	
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
	
}
