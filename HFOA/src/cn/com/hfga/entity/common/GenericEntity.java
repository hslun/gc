package cn.com.hfga.entity.common;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 * hfga's GenericEntity,which contains the id and version.
 * 含有id和version的公共entity
 * @author zhang.haifeng
 * since 2013-9-9
 */
@MappedSuperclass
public abstract class GenericEntity implements IEntity {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The primary key.
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	/**
	 * The version.
	 */
	@Version
	@Column(name="version")
	private Long version;

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
	
}
