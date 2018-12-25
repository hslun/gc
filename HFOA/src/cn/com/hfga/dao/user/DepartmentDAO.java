package cn.com.hfga.dao.user;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cn.com.hfga.dao.common.SpringDataDAO;
import cn.com.hfga.entity.user.DepartmentEntity;


@Repository("departmentDAO")

public interface DepartmentDAO extends SpringDataDAO<DepartmentEntity>{
	
	@Query(nativeQuery=true,value="select * from Department")
	public List<DepartmentEntity> findAll();
	
	//���ݲ������ƻ�ȡID
	@Query(nativeQuery=true,value="select * from department where DepartmentName=:departmentName")
	public List<DepartmentEntity> getByName(@Param(value="departmentName") String departmentName);
	
	//��ȡ���в���
	@Query(nativeQuery=true,value="select * from department order by ID asc limit ?1,?2")
	public List<DepartmentEntity> getAllDept(int start, int number);
	
	//��ȡ��������
	@Query(nativeQuery=true,value="select count(*) from department ")
	public int getAllDeptCount();
	
	//Web-��������
	@Modifying
	@Query(nativeQuery = true, value = " insert into department (ID,DepartmentName,DepartId) VALUES (:ID,:DepartmentName,:DepartId) ")
	public int saveDept(@Param(value="ID") int ID,@Param(value="DepartmentName") String DepartmentName,@Param(value="DepartId") String DepartId);
	
	//Web-�޸�
	@Modifying
	@Query(nativeQuery = true, value = " update department set DepartmentName=:DepartmentName where ID=:ID ")
	public int updateDept(@Param(value="ID") String ID,@Param(value="DepartmentName") String DepartmentName);
	
	@Query(nativeQuery=true,value="select MAX(ID) from Department")
	public int getMaxId();
}
