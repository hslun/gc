package cn.com.hfga.entity.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="department_menu")
public class MenuEntity{

	@Id
	@Column(name="ID")
	private int ID;
	
	@Column(name="Name")
	private String Name;
	
	@Column(name="Url")
	private String Url;
	
	@Column(name="DepartmentId")
	private String DepartmentId;
	
	
	@Column(name="ParentId")
	private String  ParentId;
	
	@Column(name="IsParent")
	private String  IsParent;
	
	

	public int getID() {
		return ID;
	}



	public void setID(int iD) {
		ID = iD;
	}



	public String getName() {
		return Name;
	}



	public void setName(String name) {
		Name = name;
	}



	public String getUrl() {
		return Url;
	}



	public void setUrl(String url) {
		Url = url;
	}



	public String getDepartmentId() {
		return DepartmentId;
	}



	public void setDepartmentId(String departmentId) {
		DepartmentId = departmentId;
	}



	public String getParentId() {
		return ParentId;
	}



	public void setParentId(String parentId) {
		ParentId = parentId;
	}



	public String getIsParent() {
		return IsParent;
	}



	public void setIsParent(String isParent) {
		IsParent = isParent;
	}


	public MenuEntity() {
		super();
	}



	@Override
	public String toString() {
		return "MenuEntity [ID=" + ID + ", Name=" + Name + ", Url=" + Url + ", DepartmentId=" + DepartmentId
				+ ", ParentId=" + ParentId + ", IsParent=" + IsParent + "]";
	}
	
	
	
	
	
}
