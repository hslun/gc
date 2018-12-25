package cn.com.hfga.entity.log;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="b_operaterecord")
public class OperationRecord {
	@Id
	@Column(name="ID")
	private Integer ID;
	
	@Column(name="RealName")
	private String RealName;
	
	@Column(name="OperateTime")
	private String OperateTime;
	
	@Column(name="OperateInfo")
	private String OperateInfo;
	
	@Column(name="OperateDevice")
	private String  OperateDevice;

	
	
	public OperationRecord()
	{
		super();
	}



	public Integer getID() {
		return ID;
	}



	public void setID(Integer iD) {
		ID = iD;
	}



	public String getRealName() {
		return RealName;
	}



	public void setRealName(String realName) {
		RealName = realName;
	}



	public String getOperateTime() {
		return OperateTime;
	}



	public void setOperateTime(String operteTime) {
		OperateTime = operteTime;
	}



	public String getOperateInfo() {
		return OperateInfo;
	}



	public void setOperateInfo(String operateInfo) {
		OperateInfo = operateInfo;
	}



	public String getOperateDevice() {
		return OperateDevice;
	}



	public void setOperateDevice(String operateDevice) {
		OperateDevice = operateDevice;
	}



	@Override
	public String toString() {
		return "OperationRecord [ID=" + ID + ", RealName=" + RealName + ", OperateTime=" + OperateTime
				+ ", OperateInfo=" + OperateInfo + ", OperateDevice=" + OperateDevice + "]";
	}



	

}
