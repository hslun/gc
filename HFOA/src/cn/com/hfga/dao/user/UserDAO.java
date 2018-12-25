package cn.com.hfga.dao.user;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cn.com.hfga.dao.common.SpringDataDAO;
import cn.com.hfga.entity.user.DutyEntity;
import cn.com.hfga.entity.user.MenuEntity;
import cn.com.hfga.entity.user.UserEntity;
import java.util.List;

@Repository("userDAO")
public interface UserDAO extends SpringDataDAO<UserEntity> {
	
	
	/*public UserEntity findByCode(String userCode);*/
	
/*	public List<UserEntity> findByUserName(String userName);*/
	
	@Query(nativeQuery=true,value="select * from U_User where UserName=:username and UserPassword=:password")
	public List<UserEntity> findByUserName(@Param(value = "username") String userName,@Param(value="password")String password);
	
	@Query(nativeQuery=true,value="select * from U_User where UserName=:username and UserPassword=:password")
	public List<UserEntity> findByUserNameAndUserPassword(@Param(value = "username") String userName,@Param(value="password")String password);
	
	
	@Query(nativeQuery=true,value="select * from U_User where Id=:id")
	public  List<UserEntity> findById(@Param(value="id") String id);
	
	@Query(nativeQuery=true,value="select * from U_User where DepartmentName=:department and RoleId=:roleid")
	public List<UserEntity> findleader(@Param(value="department") String department,@Param(value="roleid") String roleid);
	
	@Query(nativeQuery=true,value="select * from U_User where DepartmentName=:department and RoleId=:roleid and UserName<>:username")
	public List<UserEntity> findleader1(@Param(value="department") String department,@Param(value="roleid") String roleid,
			@Param(value="username") String username);
	
	@Query(nativeQuery=true,value="select * from U_User where DepartmentName=:department and UserName=:username")
	public List<UserEntity> findUserByNameAndDept(@Param(value="department") String department,@Param(value="username") String username);
	
	@Query(nativeQuery=true,value="select * from U_User where Id=:Id and UserName=:username")
	public List<UserEntity> findUserByNameAndId(@Param(value="Id") String Id,@Param(value="username") String username);
	
	//部门经理返回他的业务主管
	@Query(nativeQuery=true,value="select * from U_User where Id=:workgroupid")
	public List<UserEntity> findleader2(@Param(value="workgroupid") String workgroupid);
	
	
	@Modifying
	@Query(nativeQuery=true,value="update U_User set UserPassword=:userpassword where Id=:id")
	public int changeInfo(@Param(value="userpassword") String userpassword,@Param(value="id")String id);
	
	
	@Modifying
	@Query(nativeQuery=true,value="update U_User set UserPassword=:userpassword where UserName=:username")
	public int  changeGuo(@Param(value="userpassword") String userpassword,@Param(value="username") String username);
	
	//按照id更改每个人的密码
	@Modifying
	@Query(nativeQuery=true,value="update U_User set UserPassword=:userpassword where Id=:id")
	public int  passwordToMD5(@Param(value="userpassword")String userpassword,@Param(value="id")String id);
	
	@Query(nativeQuery=true,value="select * from U_User")
	public  List<UserEntity> getAll();
	
	
	@Modifying
	@Query(nativeQuery=true,value="update U_User set UserPassword=:userpassword where UserName=:username")
	public int ResetPassword(@Param(value="userpassword") String userpassword,@Param(value="username") String username);
	
	// 招待-根据用户名获取密码
//	@Query(nativeQuery=true,value="select UserPassword from U_User where UserName=:username and (DepartmentName='财务部' or RealName='张霓')")
//	public String getPassword(@Param(value="username") String username);
	
	@Query(nativeQuery=true,value="select UserPassword from U_User where UserName=:username ")
	public String getPassword(@Param(value="username") String username);

	// 保存更改的密码
	@Modifying
	@Query(nativeQuery=true,value="update U_User set UserPassword=?1 where UserName=?2")
	public int updatePassword(String password, String name);
	
	@Query(nativeQuery=true,value="select * from U_User where UserName=?1")
	public List<UserEntity> findZhang(String username);
	
	//特殊活动的负责人 比如篮球等  需要综合办公室邓姐审批
	@Query(nativeQuery=true,value="select * from U_User where Gender=?1 and UserName=?2")
	public List<UserEntity> NeedDeng(String Gender,String username);
	
	@Query(nativeQuery=true,value="select * from U_User where (DepartmentName=?1 and RoleId=?2) or (UserName=?3)")
	public List<UserEntity> findDeng(String department,String roleid,String deng);

	//临时添加
	@Query(nativeQuery=true,value="select * from U_User WHERE UserName='张荣' OR UserName='蒋祥玉'")
	public List<UserEntity> findJZ();
	
	//Web-获取用户信息列表
	@Query(nativeQuery=true,value="select * from U_User order by DepartmentId asc limit ?1,?2 ")
	public List<UserEntity> getByPage(int start, int number);
	
	//Web-获取用户总条数
	@Query(nativeQuery = true, value = "select count(*) from U_User ")
	public int getAllCount();
	
	//Web-获取最大id
	@Query(nativeQuery = true, value = " select * from u_user where Id = (select MAX(Id) from U_User where UserName<>'admin') ")
	public List<UserEntity> getMaxId();
	
	//Web-新增用户
	@Modifying
	@Query(nativeQuery = true, value = " insert into u_user (Id,Code,UserName,RealName,UserPassword,DepartmentId,DepartmentName,Duty,CompanyId,CompanyName,CreateOn,RoleId,QICQ) VALUES (:Id,:Code,:UserName,:RealName,:UserPassword,:DepartmentId,:DepartmentName,:Duty,:CompanyId,:CompanyName,:CreateOn,:RoleId,:QICQ) ")
	public int saveUser(@Param(value="Id") String Id,@Param(value="Code") String Code,
			@Param(value="UserName") String UserName,@Param(value="RealName") String RealName,
			@Param(value="UserPassword") String UserPassword,
			@Param(value="DepartmentId") String DepartmentId,@Param(value="DepartmentName") String DepartmentName,
			@Param(value="Duty") String Duty,
			@Param(value="CompanyId") String CompanyId,@Param(value="CompanyName") String CompanyName,
			@Param(value="CreateOn") String CreateOn,@Param(value="RoleId") String RoleId,@Param(value="QICQ") String QICQ);
	//Web-修改
	@Modifying
	@Query(nativeQuery = true, value = " update U_User set Code=:Code,UserName=:UserName,RealName=:RealName,DepartmentId=:DepartmentId,DepartmentName=:DepartmentName,Duty=:Duty,RoleId=:RoleId where Id=:Id")
	public int updateUser(@Param(value="Id") String Id,@Param(value="Code") String Code,
			@Param(value="UserName") String UserName,@Param(value="RealName") String RealName,
			@Param(value="DepartmentId") String DepartmentId,@Param(value="DepartmentName") String DepartmentName,
			@Param(value="Duty") String Duty,@Param(value="RoleId") String RoleId);
	//Web-删除用户
	@Modifying
	@Query(nativeQuery = true,value=" delete from u_user where Id=:Id ")
	public int deleteUser(@Param(value="Id") String Id);
	
	//Web-重置密码123456
	@Modifying
	@Query(nativeQuery=true,value="update U_User set UserPassword=:userpassword where Id=:Id")
	public int ResetPasswordById(@Param(value="userpassword") String userpassword,@Param(value="Id") String Id);
	
	//Web-查询用户/用户名-部门名
	@Query(nativeQuery = true, value = " select * from u_user where Code like ?1 and DepartmentName like ?2 ")
	public List<UserEntity> searchUser(String code,String departmentname);
	
	//Web-查询用户/用户名
	@Query(nativeQuery = true, value = " select * from u_user where Code like ?1 ")
	public List<UserEntity> searchUserByCode(String code);
	
	//Web-查询用户/用户名
	@Query(nativeQuery = true, value = " select count(*) from u_user where Code like ?1 ")
	public int searchUserCountByCode(String code);
	
	//Web-查询用户/部门名
	@Query(nativeQuery = true, value = " select * from u_user where DepartmentName like ?1 limit ?2,?3")
	public List<UserEntity> searchUserByDepartmentName(String departmentname,int start,int number);
	
	//Web-查询/部门名/记录数
	@Query(nativeQuery = true, value = " select count(*) from u_user where DepartmentName like ?1 ")
	public int searchUserCountByDepartmentName(String departmentname);
	
	//Web-查询/部门名/记录数
	@Query(nativeQuery = true, value = " select * from duty ")
	public List<DutyEntity> getDuty();
	
	//Web-查询/部门名/记录数
	@Query(nativeQuery = true, value = " select * from department_menu where DepartmentId = :Id ")
	public List<MenuEntity> getMenu(@Param(value="Id") String Id);
	
	//Web-私车权限
	@Modifying
	@Query(nativeQuery=true,value="update U_User set QICQ='1' where Id=:Id")
	public int updatePrivate(@Param(value="Id") String Id);
}
