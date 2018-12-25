package cn.com.hfga.entity.privatecar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.com.hfga.entity.common.IEntity;

@Entity
@Table(name="b_privatecarinvoice")
public class PrivateCarInvoiceEntity implements IEntity{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ApplyId")
	private String ApplyId;

	@Column(name="ApplyMan",nullable=true)
	private String ApplyMan;
	
	@Column(name="ApproveMan",nullable=true)
	private String ApproveMan;
	
	@Column(name="ApplyTime",nullable=true)
	private String ApplyTime;
	
	@Column(name="Sum",nullable=true)
	private String Sum;
	
	@Column(name="SureLength",nullable=true)
	private String SureLength;
	
	@Column(name="VoucherNum",nullable=true)
	private String VoucherNum;
	
	@Column(name="Status",nullable=true)
	private String Status;
	
	@Column(name="PaidTime",nullable=true)
	private String PaidTime;
	
	@Column(name="ApplyIds",nullable=true)
	private String ApplyIds;

	public String getApplyId() {
		return ApplyId;
	}

	public void setApplyId(String applyId) {
		ApplyId = applyId;
	}

	public String getApplyMan() {
		return ApplyMan;
	}

	public void setApplyMan(String applyMan) {
		ApplyMan = applyMan;
	}

	public String getApproveMan() {
		return ApproveMan;
	}

	public void setApproveMan(String approveMan) {
		ApproveMan = approveMan;
	}

	public String getApplyTime() {
		return ApplyTime;
	}

	public void setApplyTime(String applyTime) {
		ApplyTime = applyTime;
	}

	public String getSum() {
		return Sum;
	}

	public void setSum(String sum) {
		Sum = sum;
	}

	public String getSureLength() {
		return SureLength;
	}

	public void setSureLength(String sureLength) {
		SureLength = sureLength;
	}

	public String getVoucherNum() {
		return VoucherNum;
	}

	public void setVoucherNum(String voucherNum) {
		VoucherNum = voucherNum;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getPaidTime() {
		return PaidTime;
	}

	public void setPaidTime(String paidTime) {
		PaidTime = paidTime;
	}

	public String getApplyIds() {
		return ApplyIds;
	}

	public void setApplyIds(String applyIds) {
		ApplyIds = applyIds;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "PrivateCarInvoiceEntity [ApplyId=" + ApplyId + ", ApplyMan=" + ApplyMan + ", ApproveMan=" + ApproveMan
				+ ", ApplyTime=" + ApplyTime + ", Sum=" + Sum + ", SureLength=" + SureLength + ", VoucherNum="
				+ VoucherNum + ", Status=" + Status + ", PaidTime=" + PaidTime + ", ApplyIds=" + ApplyIds + "]";
	}
	
	
	
}
