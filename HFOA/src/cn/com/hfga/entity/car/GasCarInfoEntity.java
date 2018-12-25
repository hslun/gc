package cn.com.hfga.entity.car;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import cn.com.hfga.entity.common.CommonIntIdEntity;


/*
 * 加油信息实体类
 * 
 */
@Entity
@Table(name="B_CarGasInfo")
public class GasCarInfoEntity extends CommonIntIdEntity{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Column(name="ID",nullable=true)
	private int ID;
	
	
	@Column(name="CarID",nullable=true)
	private int CarID;
	
	
	@Column(name="CardBalance",nullable=true)
	private float CardBalance;
	
	@Column(name="ExecuteTime",nullable=true)
	private String ExecuteTime;
	
	
	@Column(name="ChangeValue",nullable=true)
	private float ChangeValue;
	
	
	@Column(name="ChangeType",nullable=true)
	private String ChangeType;
	
	
	@Column(name="Remark",nullable=true)
	private String Remark;


	public int getID() {
		return ID;
	}


	public void setID(int iD) {
		ID = iD;
	}


	public int getCarID() {
		return CarID;
	}


	public void setCarID(int carID) {
		CarID = carID;
	}


	public float getCardBalance() {
		return CardBalance;
	}


	public void setCardBalance(float cardBalance) {
		CardBalance = cardBalance;
	}


	public String getExecuteTime() {
		return ExecuteTime;
	}


	public void setExecuteTime(String executeTime) {
		ExecuteTime = executeTime;
	}


	public float getChangeValue() {
		return ChangeValue;
	}


	public void setChangeValue(float changeValue) {
		ChangeValue = changeValue;
	}


	public String getChangeType() {
		return ChangeType;
	}


	public void setChangeType(String changeType) {
		ChangeType = changeType;
	}


	public String getRemark() {
		return Remark;
	}


	public void setRemark(String remark) {
		Remark = remark;
	}
	
	
}
