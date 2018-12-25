package cn.com.hfga.entity.common;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

/**
 * hfga's Common Entity,which contains the createUser,createDate,lastCreateUser and so on.
 * 含有一些常用属性的公共entity
 * author zhang.haifeng
 * since 2014-10-11
 */
@MappedSuperclass
public class CommonEntity extends GenericEntity {
	
	/**
	 * serialVersionUID
	 */
	protected static final long serialVersionUID = 1L;

	
	/**
	 * The createDate.
	 */
	@Column(name="createDate")
	@CreatedDate
	private Date createDate;
	
	
	/**
	 * The lastModifyDate.
	 */
	@Column(name="lastModifyDate")
	@LastModifiedDate
	private Date lastModifyDate;

	@PrePersist
	public void preSave() {
		if(this.getId() == null) {
			this.createDate = new Date();
			this.lastModifyDate = new Date();
			
		} else {
			this.lastModifyDate = new Date();
			
		}
	}
	
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getLastModifyDate() {
		return lastModifyDate;
	}

	public void setLastModifyDate(Date lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}
	
}
