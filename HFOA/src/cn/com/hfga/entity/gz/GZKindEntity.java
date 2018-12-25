package cn.com.hfga.entity.gz;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "B_GZKind")
public class GZKindEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	private String ID;

	@Column(name = "GZKind")
	private String GZKind;

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getGZKind() {
		return GZKind;
	}

	public void setGZKind(String gZKind) {
		GZKind = gZKind;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
