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
	
	//���ž���������ҵ������
	@Query(nativeQuery=true,value="select * from U_User where Id=:workgroupid")
	public List<UserEntity> findleader2(@Param(value="workgroupid") String workgroupid);
	
	
	@Modifying
	@Query(nativeQuery=true,value="update U_User set UserPassword=:userpassword where Id=:id")
	public int changeInfo(@Param(value="userpassword") String userpassword,@Param(value="id")String id);
	
	
	@Modifying
	@Query(nativeQuery=true,value="update U_User set UserPassword=:userpassword where UserName=:username")
	public int  changeGuo(@Param(value="userpassword") String userpassword,@Param(value="username") String username);
	
	//����id����ÿ���˵�����
	@Modifying
	@Query(nativeQuery=true,value="update U_User set UserPassword=:userpassword where Id=:id")
	public int  passwordToMD5(@Param(value="userpassword")String userpassword,@Param(value="id")String id);
	
	@Query(nativeQuery=true,value="select * from U_User")
	public  List<UserEntity> getAll();
	
	
	@Modifying
	@Query(nativeQuery=true,value="update U_User set UserPassword=:userpassword where UserName=:username")
	public int ResetPassword(@Param(value="userpassword") String userpassword,@Param(value="username") String username);
	
	// �д�-�����û�����ȡ����
//	@Query(nativeQuery=true,value="select UserPassword from U_User where UserName=:username and (DepartmentName='����' or RealName='����')")
//	public String getPassword(@Param(value="username") String username);
	
	@Query(nativeQuery=true,value="select UserPassword from U_User where UserName=:username ")
	public String getPassword(@Param(value="username") String username);

	// ������ĵ�����
	@Modifying
	@Query(nativeQuery=true,value="update U_User set UserPassword=?1 where UserName=?2")
	public int updatePassword(String password, String name);
	
	@Query(nativeQuery=true,value="select * from U_User where UserName=?1")
	public List<UserEntity> findZhang(String username);
	
	//�����ĸ����� ���������  ��Ҫ�ۺϰ칫�ҵ˽�����
	@Query(nativeQuery=true,value="select * from U_User where Gender=?1 and UserName=?2")
	public List<UserEntity> NeedDeng(String Gender,String username);
	
	@Query(nativeQuery=true,value="select * from U_User where (DepartmentName=?1 and RoleId=?2) or (UserName=?3)")
	public List<UserEntity> findDeng(String department,String roleid,String deng);

	//��ʱ���
	@Query(nativeQuery=true,value="select * from U_User WHERE UserName='����' OR UserName='������'")
	public List<UserEntity> findJZ();
	
	//Web-��ȡ�û���Ϣ�б�
	@Query(nativeQuery=true,value="select * from U_User order by DepartmentId asc limit ?1,?2 ")
	public List<UserEntity> getByPage(int start, int number);
	
	//Web-��ȡ�û�������
	@Query(nativeQuery = true, value = "select count(*) from U_User ")
	public int getAllCount();
	
	//Web-��ȡ���id
	@Query(nativeQuery = true, value = " select * from u_user where Id = (select MAX(Id) from U_User where UserName<>'admin') ")
	public List<UserEntity> getMaxId();
	
	//Web-�����û�
	@Modifying
	@Query(nativeQuery = true, value = " insert into u_user (Id,Code,UserName,RealName,UserPassword,DepartmentId,DepartmentName,Duty,CompanyId,CompanyName,CreateOn,RoleId,QICQ) VALUES (:Id,:Code,:UserName,:RealName,:UserPassword,:DepartmentId,:DepartmentName,:Duty,:CompanyId,:CompanyName,:CreateOn,:RoleId,:QICQ) ")
	public int saveUser(@Param(value="Id") String Id,@Param(value="Code") String Code,
			@Param(value="UserName") String UserName,@Param(value="RealName") String RealName,
			@Param(value="UserPassword") String UserPassword,
			@Param(value="DepartmentId") String DepartmentId,@Param(value="DepartmentName") String DepartmentName,
			@Param(value="Duty") String Duty,
			@Param(value="CompanyId") String CompanyId,@Param(value="CompanyName") String CompanyName,
			@Param(value="CreateOn") String CreateOn,@Param(value="RoleId") String RoleId,@Param(value="QICQ") String QICQ);
	//Web-�޸�
	@Modifying
	@Query(nativeQuery = true, value = " update U_User set Code=:Code,UserName=:UserName,RealName=:RealName,DepartmentId=:DepartmentId,DepartmentName=:DepartmentName,Duty=:Duty,RoleId=:RoleId where Id=:Id")
	public int updateUser(@Param(value="Id") String Id,@Param(value="Code") String Code,
			@Param(value="UserName") String UserName,@Param(value="RealName") String RealName,
			@Param(value="DepartmentId") String DepartmentId,@Param(value="DepartmentName") String DepartmentName,
			@Param(value="Duty") String Duty,@Param(value="RoleId") String RoleId);
	//Web-ɾ���û�
	@Modifying
	@Query(nativeQuery = true,value=" delete from u_user where Id=:Id ")
	public int deleteUser(@Param(value="Id") String Id);
	
	//Web-��������123456
	@Modifying
	@Query(nativeQuery=true,value="update U_User set UserPassword=:userpassword where Id=:Id")
	public int ResetPasswordById(@Param(value="userpassword") String userpassword,@Param(value="Id") String Id);
	
	//Web-��ѯ�û�/�û���-������
	@Query(nativeQuery = true, value = " select * from u_user where Code like ?1 and DepartmentName like ?2 ")
	public List<UserEntity> searchUser(String code,String departmentname);
	
	//Web-��ѯ�û�/�û���
	@Query(nativeQuery = true, value = " select * from u_user where Code like ?1 ")
	public List<UserEntity> searchUserByCode(String code);
	
	//Web-��ѯ�û�/�û���
	@Query(nativeQuery = true, value = " select count(*) from u_user where Code like ?1 ")
	public int searchUserCountByCode(String code);
	
	//Web-��ѯ�û�/������
	@Query(nativeQuery = true, value = " select * from u_user where DepartmentName like ?1 limit ?2,?3")
	public List<UserEntity> searchUserByDepartmentName(String departmentname,int start,int number);
	
	//Web-��ѯ/������/��¼��
	@Query(nativeQuery = true, value = " select count(*) from u_user where DepartmentName like ?1 ")
	public int searchUserCountByDepartmentName(String departmentname);
	
	//Web-��ѯ/������/��¼��
	@Query(nativeQuery = true, value = " select * from duty ")
	public List<DutyEntity> getDuty();
	
	//Web-��ѯ/������/��¼��
	@Query(nativeQuery = true, value = " select * from department_menu where DepartmentId = :Id ")
	public List<MenuEntity> getMenu(@Param(value="Id") String Id);
	
	//Web-˽��Ȩ��
	@Modifying
	@Query(nativeQuery=true,value="update U_User set QICQ='1' where Id=:Id")
	public int updatePrivate(@Param(value="Id") String Id);
}
