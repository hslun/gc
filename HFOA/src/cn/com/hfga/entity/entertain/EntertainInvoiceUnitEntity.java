package cn.com.hfga.entity.entertain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "B_EntertainInvoiceUnit")
public class EntertainInvoiceUnitEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	private String ID;
	
	@Column(name = "InvoiceUnit")
	private String InvoiceUnit;

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getInvoiceUnit() {
		return InvoiceUnit;
	}

	public void setInvoiceUnit(String invoiceUnit) {
		InvoiceUnit = invoiceUnit;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	


}
