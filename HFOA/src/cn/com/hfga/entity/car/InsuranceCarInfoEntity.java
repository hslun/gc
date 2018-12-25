package cn.com.hfga.entity.car;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.com.hfga.entity.common.IEntity;

@Entity
@Table(name="B_Insurance")
public class InsuranceCarInfoEntity implements IEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="Id")
	private String id;
	
	@Column(name="CarNum",nullable=true)
	private String CarNum;
	
	@Column(name="InsuranceType",nullable=true)
	private String InsuranceType;
	
	@Column(name="InsuranceSum",nullable=true)
	private Float InsuranceSum;
	
	@Column(name="InsuranceFare",nullable=true)
	private Float InsuranceFare;
	
	@Column(name="SuspendTime",nullable=true)
	private String SuspendTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCarNum() {
		return CarNum;
	}

	public void setCarNum(String carNum) {
		CarNum = carNum;
	}

	public String getInsuranceType() {
		return InsuranceType;
	}

	public void setInsuranceType(String insuranceType) {
		InsuranceType = insuranceType;
	}

	public float getInsuranceSum() {
		return InsuranceSum;
	}

	public void setInsuranceSum(float insuranceSum) {
		InsuranceSum = insuranceSum;
	}

	public float getInsuranceFare() {
		return InsuranceFare;
	}

	public void setInsuranceFare(float insuranceFare) {
		InsuranceFare = insuranceFare;
	}

	public String getSuspendTime() {
		return SuspendTime;
	}

	public void setSuspendTime(String suspendTime) {
		SuspendTime = suspendTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
