package cn.com.hfga.entity.user;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Duty")
public class DutyEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	private String ID;

	@Column(name = "DutyName")
	private String DutyName;
	
	@Column(name = "DutyId")
	private String DutyId;

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getDutyName() {
		return DutyName;
	}

	public void setDutyName(String dutyName) {
		DutyName = dutyName;
	}

	public String getDutyId() {
		return DutyId;
	}

	public void setDutyId(String dutyId) {
		DutyId = dutyId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
