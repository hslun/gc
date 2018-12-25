package cn.com.hfga.entity.common;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="b_menu")
public class  SysMenuEntity implements IEntity{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id")
	private String id;
	
	@Column(name="parentId",nullable=true)
	private String parentId;
	
	@Column(name="titleName",nullable=true)
	private String titleName;
	
	@Column(name="type",nullable=true)
	private String type;
	
	@Column(name="url",nullable=true)
	private String url;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "SysMenuEntity [id=" + id + ", parentId=" + parentId + ", titleName=" + titleName + ", type=" + type
				+ ", url=" + url + "]";
	}
	



	
	
}
