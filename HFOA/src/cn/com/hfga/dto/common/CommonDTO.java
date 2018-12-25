package cn.com.hfga.dto.common;


/**
 * common DTO.
 * @author zhang.haifeng
 * since 2014-9-23
 */
public abstract class CommonDTO implements IDTO {
	
	/**
	 * id
	 */
	private Long id;
	
	/**
	 * version
	 */
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

