package cn.com.hfga.entity.privatecar;

import java.util.List;

import cn.com.hfga.entity.privatecar.PrivateCarApplyEntity;
import cn.com.hfga.entity.privatecar.PrivateCarInvoiceEntity;


public class  PrivateCarEntity{
	

	private List<PrivateCarApplyEntity> car;
	
	private List<PrivateCarInvoiceEntity> invoice;

	public List<PrivateCarApplyEntity> getCar() {
		return car;
	}

	public void setCar(List<PrivateCarApplyEntity> car) {
		this.car = car;
	}

	public List<PrivateCarInvoiceEntity> getInvoice() {
		return invoice;
	}

	public void setInvoice(List<PrivateCarInvoiceEntity> invoice) {
		this.invoice = invoice;
	}
	
	
	
}
