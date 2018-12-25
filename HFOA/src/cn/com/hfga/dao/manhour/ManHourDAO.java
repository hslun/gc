package cn.com.hfga.dao.manhour;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cn.com.hfga.dao.common.SpringDataDAO;
import cn.com.hfga.entity.manhour.ManHourEntity;
import cn.com.hfga.entity.user.UserEntity;

@Repository("manHourDAO")
public interface ManHourDAO extends SpringDataDAO<ManHourEntity>{

	@Modifying
	@Query(nativeQuery=true,value="insert into t_manhour (RealName,DepartmentName,DepartmentId,Kdescrib,Kind,State,Day_,FillTime,Describe) VALUES(:realName,:departmentName,:departmentId,:hour,:kind,:state,:day,:fillTime,:describe)")
	public int insertOne(@Param(value="realName")String realName,
						 @Param(value="departmentName")String departmentName,
						 @Param(value="departmentId")String departmentId,
						 @Param(value="hour")String hour,
						 @Param(value="kind")String kind,
						 @Param(value="state")String state,
						 @Param(value="day")String day,
						 @Param(value="fillTime")String fillTime,
                         @Param(value="describe")String describe);
	
	@Query(nativeQuery=true,value="select * from t_manhour where Day_=:day")
	public List<ManHourEntity> isExist(@Param(value="day") String day);
	
	/**
	 * Ա����Ϊ��
	 * @param departmentname
	 * @param realname
	 * @param starttime
	 * @param endtime
	 * @param kind
	 * @return
	 */
	//  ����   ���඼����ȫ��
	@Query(nativeQuery=true,value="select * from t_manhour where DepartmentName=:departmentname and RealName=:realname and Day_>=:starttime and Day_<=:endtime and Kind=:kind order by Day_  desc,FillTime desc")
	public  List<ManHourEntity> get100(@Param(value="departmentname")String departmentname,
			@Param(value="realname")String realname ,@Param(value="starttime")String starttime,
			@Param(value="endtime")String endtime,@Param(value="kind")String kind);
	
	//���Ų���ȫ�� ������ȫ��
	@Query(nativeQuery=true,value="select * from t_manhour where  RealName=:realname and Day_>=:starttime and Day_<=:endtime and  DepartmentName=:departmentname order by Day_ desc,FillTime desc")
	public  List<ManHourEntity> get101(@Param(value="realname")String realname ,@Param(value="starttime")String starttime,
			@Param(value="endtime")String endtime,@Param(value="departmentname")String departmentname);
	
	
	//������ȫ�� ������ȫ��
	@Query(nativeQuery=true,value="select * from t_manhour where  RealName=:realname and Day_>=:starttime and Day_<=:endtime order by Day_ desc,FillTime desc")
	public List<ManHourEntity> get111(@Param(value="realname")String realname ,@Param(value="starttime")String starttime,
			@Param(value="endtime")String endtime);
	
	
	//������ȫ�� ���಻��ȫ��
	@Query(nativeQuery=true,value="select * from t_manhour where Kind=:kind and RealName=:realname and Day_>=:starttime and Day_<=:endtime order by Day_ desc,FillTime desc")
	public List<ManHourEntity> get110(@Param(value="kind")String kind,@Param(value="realname")String realname ,@Param(value="starttime")String starttime,
			@Param(value="endtime")String endtime);
	/**
	 * Ա��Ϊ��
	 */
	//����ȫ��   ����Ϊȫ��
	@Query(nativeQuery=true,value="select * from t_manhour where  Day_>=:starttime and Day_<=:endtime order by Day_ desc,FillTime desc")
	public List<ManHourEntity> get011(@Param(value="starttime")String starttime,@Param(value="endtime")String endtime);
	//����ȫ��   ���಻��ȫ��
	@Query(nativeQuery=true,value="select * from t_manhour where Kind=:kind and Day_>=:starttime and Day_<=:endtime order by Day_ desc,FillTime desc")
	public List<ManHourEntity> get010(@Param(value="kind") String kind ,@Param(value="starttime")String starttime,@Param(value="endtime")String endtime);
	//���Ų���ȫ��  ������ȫ��
	@Query(nativeQuery=true,value="select * from t_manhour where DepartmentName=:departmentname and Day_>=:starttime and Day_<=:endtime  order by Day_ desc,FillTime desc")
	public List<ManHourEntity> get001(@Param(value="departmentname") String departmentname,@Param(value="starttime")String starttime,@Param(value="endtime")String endtime);
	//�������඼����ȫ��
	@Query(nativeQuery=true,value="select * from t_manhour where Kind=:kind and DepartmentName=:departmentname and Day_>=:starttime and Day_<=:endtime order by Day_ desc,FillTime desc")
	public List<ManHourEntity> get000(@Param(value="kind") String kind,@Param(value="departmentname") String departmentname,@Param(value="starttime")String starttime,@Param(value="endtime")String endtime);
	//�õ�һ��ʵ��
	@Query(nativeQuery=true,value="select * from t_manhour where Id=:id")
	public List<ManHourEntity> getOne(@Param(value="id") String id);
}
