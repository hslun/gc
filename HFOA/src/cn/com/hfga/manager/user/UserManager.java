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
 *�û�����ӿ�
 */

public interface UserManager {
	
	public boolean doLogin(UserDTO UserDTO);
	
	//����˷���
	public List<UserEntity> findOne(UserDTO UserDTO);
	
	//�����û����� ��ѯroleid
	public List<UserEntity> findroleid(String id);
	
	//���ұ����ŵ��쵼
	public  List<UserEntity> findLeader(String department);
	
	//���ұ����ŵ��쵼(���Ÿ�����ʹ��)
	public  List<UserEntity> findLeader1(String department,String username);
	
	//���Ҳ��ž��������
	public List<UserEntity> findLeader2(String workgroupid);
	
	//���ұ����ŵ��쵼
	public  List<UserEntity> findUserByNameAndDept(String department,String username);
	
	public List<UserEntity> findUserByNameAndId(String id, String username);
	
	//�����û���������
	public int changeInfo(UserModifyDTO userModifyDTO);
	
	//�����û��������뷵�� ���˵�id
	
	public  List<UserEntity> getId(UserModifyDTO userModifyDTO);

	public int changeGuo(String password,String username);
	
	//���������˵�����MD5����
	public int passwordToMD5(String userpassword,String id);
	
	//��ȡ���е��û��б����MD5��������
	public List<UserEntity> getAll();
	
	//�����û�����
	public int ResetPassword(String  username);
	
	//̷��ר��
	public List<UserEntity> findZhang();
	
	//������������ʸ�У��
	public List<UserEntity> NeedDeng(String gender,String username);
	
	//���������
	public List<UserEntity> findDeng(String department);

	//��ʱ���
	public List<UserEntity> findJZ();
	
	//Web-��ѯ�û���ҳ
	public List<UserEntity> getByPage(int start, int end);
	
	//Web-��ȡ�û�������
	public int getAllCount();
	
	//Web-��ȡ���id
	public int getMaxId();
	
	//Web-����
	public int saveUser(UserDTO UserDTO);
	
	//Web-�޸�
	public int updateUser(UserDTO UserDTO);
	
	//Web-ɾ���û�
	public int deleteUser(String Id);
	
	//Web-��������
	public int ResetPasswordById(String Id);
	
	//Web-��ѯ�û�
	public List<UserEntity> searchUser(UserDTO UserDTO);
	
	//Web-��ѯ�û�/�û���
	public List<UserEntity> searchUserByCode(UserDTO UserDTO);
	
	//Web-��ѯ�û�����
	public int searchUserCountByCode(UserDTO UserDTO);
	
	//Web-��ѯ�û�/����
	public List<UserEntity> searchUserByDepartmentName(UserDTO UserDTO,int start,int number);
	
	//Web-��ѯ�û�/���ݲ���/�û�����
	public int searchUserCountByDepartmentName(UserDTO UserDTO);
	
	//Web-�����û�
	int export(String[] nlist, String path, String depart);
	
	//Web-�����û�
	int exportExcel(List<UserEntity> userList, String path);
	
	//Web-��ȡְλ
	public List<DutyEntity> getDuty();
	
	//Web-��ȡ�˵�
	public List<MenuEntity> getMenu(String id);
	
	//�ж�����ǰ������Ȩ��
	public boolean findByIdDate(String id);
	//����˽��Ȩ��
	public int updatePrivate(String id);
}
