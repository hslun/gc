package cn.com.hfga.manager.user;

import java.util.List;

import cn.com.hfga.dto.user.UserDTO;
import cn.com.hfga.dto.user.UserModifyDTO;
import cn.com.hfga.entity.user.DutyEntity;
import cn.com.hfga.entity.user.MenuEntity;
import cn.com.hfga.entity.user.UserEntity;

/**
 * 
 * @author xinyc
 *用户管理接口
 */

public interface UserManager {
	
	public boolean doLogin(UserDTO UserDTO);
	
	//服务端返回
	public List<UserEntity> findOne(UserDTO UserDTO);
	
	//根据用户姓名 查询roleid
	public List<UserEntity> findroleid(String id);
	
	//查找本部门的领导
	public  List<UserEntity> findLeader(String department);
	
	//查找本部门的领导(部门副经理使用)
	public  List<UserEntity> findLeader1(String department,String username);
	
	//查找部门经理的主管
	public List<UserEntity> findLeader2(String workgroupid);
	
	//查找本部门的领导
	public  List<UserEntity> findUserByNameAndDept(String department,String username);
	
	public List<UserEntity> findUserByNameAndId(String id, String username);
	
	//更改用户名和密码
	public int changeInfo(UserModifyDTO userModifyDTO);
	
	//根据用户名和密码返回 本人的id
	
	public  List<UserEntity> getId(UserModifyDTO userModifyDTO);

	public int changeGuo(String password,String username);
	
	//更改所有人的密码MD5加密
	public int passwordToMD5(String userpassword,String id);
	
	//获取所有的用户列表进行MD5更改密码
	public List<UserEntity> getAll();
	
	//重置用户密码
	public int ResetPassword(String  username);
	
	//谭总专用
	public List<UserEntity> findZhang();
	
	//活动经费涉审批资格校验
	public List<UserEntity> NeedDeng(String gender,String username);
	
	//活动经费审批
	public List<UserEntity> findDeng(String department);

	//临时添加
	public List<UserEntity> findJZ();
	
	//Web-查询用户分页
	public List<UserEntity> getByPage(int start, int end);
	
	//Web-获取用户总条数
	public int getAllCount();
	
	//Web-获取最大id
	public int getMaxId();
	
	//Web-保存
	public int saveUser(UserDTO UserDTO);
	
	//Web-修改
	public int updateUser(UserDTO UserDTO);
	
	//Web-删除用户
	public int deleteUser(String Id);
	
	//Web-重置密码
	public int ResetPasswordById(String Id);
	
	//Web-查询用户
	public List<UserEntity> searchUser(UserDTO UserDTO);
	
	//Web-查询用户/用户名
	public List<UserEntity> searchUserByCode(UserDTO UserDTO);
	
	//Web-查询用户数量
	public int searchUserCountByCode(UserDTO UserDTO);
	
	//Web-查询用户/部门
	public List<UserEntity> searchUserByDepartmentName(UserDTO UserDTO,int start,int number);
	
	//Web-查询用户/根据部门/用户数量
	public int searchUserCountByDepartmentName(UserDTO UserDTO);
	
	//Web-导出用户
	int export(String[] nlist, String path, String depart);
	
	//Web-导出用户
	int exportExcel(List<UserEntity> userList, String path);
	
	//Web-获取职位
	public List<DutyEntity> getDuty();
	
	//Web-获取菜单
	public List<MenuEntity> getMenu(String id);
	
	//判断有无前调日期权限
	public boolean findByIdDate(String id);
	//更改私车权限
	public int updatePrivate(String id);
}
