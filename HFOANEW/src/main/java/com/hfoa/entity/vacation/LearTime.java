package com.hfoa.entity.vacation;

import java.io.Serializable;

public class LearTime implements Serializable{

	/**
	 * ��ٸ����ֱ�
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;//����
	
	private String leave_id;//���ID
	
	private String beingTime;//��ʼʱ��
	
	private String endTime;//����ʱ��
	
	private String days;//����
	
	private String state;//������Чת̬
	
	private String sfyc;//�Ƿ��쳣
	
	private String status;
	
	
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLeave_id() {
		return leave_id;
	}

	public void setLeave_id(String leave_id) {
		this.leave_id = leave_id;
	}

	public String getBeingTime() {
		return beingTime;
	}

	public void setBeingTime(String beingTime) {
		this.beingTime = beingTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getSfyc() {
		return sfyc;
	}

	public void setSfyc(String sfyc) {
		this.sfyc = sfyc;
	}

	
	
	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "LearTime [id=" + id + ", leave_id=" + leave_id + ", beingTime=" + beingTime + ", endTime=" + endTime
				+ ", days=" + days + ", state=" + state + ", sfyc=" + sfyc + ", status=" + status + "]";
	}



	
	

}
