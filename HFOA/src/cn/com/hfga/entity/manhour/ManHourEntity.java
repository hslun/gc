package cn.com.hfga.entity.manhour;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


import cn.com.hfga.entity.common.IntIdEntity;

@Entity
@Table(name="t_manhour")
public class ManHourEntity extends IntIdEntity{

	/**
	 * 工时填报尸体类
	 * @author xyc
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="RealName",nullable=true)
	private String RealName;
	
	@Column(name="DepartmentName",nullable=true)
	private String DepartmentName;
	
	@Column(name="DepartmentId",nullable=true)
	private String DepartmentId;
	
	@Column(name="Kdescrib",nullable=true)
	private String Kdescrib;
	
	@Column(name="State",nullable=true)
	private String State;
	
	@Column(name="Kind",nullable=true)
	private String Kind;
	
	
	@Column(name="Day_",nullable=true)
	private String Day;
	
	@Column(name="FillTime",nullable=true)
	private String FillTime;
	
	@Column(name="Describe",nullable=true)
	private String Describe;

	public String getRealName() {
		return RealName;
	}

	public void setRealName(String realName) {
		RealName = realName;
	}

	public String getDepartmentName() {
		return DepartmentName;
	}

	public void setDepartmentName(String departmentName) {
		DepartmentName = departmentName;
	}

	public String getDepartmentId() {
		return DepartmentId;
	}

	public void setDepartmentId(String departmentId) {
		DepartmentId = departmentId;
	}


	public String getKdescrib() {
		return Kdescrib;
	}

	public void setKdescrib(String kdescrib) {
		Kdescrib = kdescrib;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public String getKind() {
		return Kind;
	}

	public void setKind(String kind) {
		Kind = kind;
	}

	public String getDay() {
		return Day;
	}

	public void setDay(String day) {
		Day = day;
	}

	public String getFillTime() {
		return FillTime;
	}

	public void setFillTime(String fillTime) {
		FillTime = fillTime;
	}

	public String getDescribe() {
		return Describe;
	}

	public void setDescribe(String describe) {
		Describe = describe;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
}
