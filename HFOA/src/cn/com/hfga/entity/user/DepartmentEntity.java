package cn.com.hfga.entity.user;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.com.hfga.entity.common.GenericEntity;



@Entity
@Table(name="Department")
public class DepartmentEntity implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private int ID;
	
	@Column(name="DepartmentName")
	private String DepartmentName;

	
	
	@Column(name="DepartId")
	private String DepartId;



	public int getID() {
		return ID;
	}



	public void setID(int iD) {
		ID = iD;
	}



	public String getDepartmentName() {
		return DepartmentName;
	}



	public void setDepartmentName(String departmentName) {
		DepartmentName = departmentName;
	}



	public String getDepartId() {
		return DepartId;
	}



	public void setDepartId(String departId) {
		DepartId = departId;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
}
