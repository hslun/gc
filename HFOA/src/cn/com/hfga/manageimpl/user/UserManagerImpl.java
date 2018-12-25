package cn.com.hfga.manageimpl.user;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.hfga.dao.user.DepartmentDAO;
import cn.com.hfga.dao.user.UserDAO;
import cn.com.hfga.dto.user.UserDTO;
import cn.com.hfga.dto.user.UserModifyDTO;
import cn.com.hfga.entity.user.DutyEntity;
import cn.com.hfga.entity.user.MenuEntity;
import cn.com.hfga.entity.user.UserEntity;
import cn.com.hfga.log.common.ILog;
import cn.com.hfga.manager.user.UserManager;
import cn.com.hfga.util.common.MD5Util;
@Service("userManager")
public class UserManagerImpl  implements UserManager,ILog{
	
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private DepartmentDAO departmentDAO;
	@Transactional
	@Override
	public boolean doLogin(UserDTO UserDTO) {
		// TODO Auto-generated method 
		List<UserEntity> l = userDAO.findByUserName(UserDTO.getUsername(),UserDTO.getPassword());
		if(l.size()==0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	@Transactional
	@Override
	public List<UserEntity> findOne(UserDTO UserDTO) {
		// TODO Auto-generated method stub
		List<UserEntity> list=userDAO.findByUserNameAndUserPassword(UserDTO.getUsername(),UserDTO.getPassword());
		return list;
	}
	@Transactional
	@Override
	public List<UserEntity> findroleid(String id) {
		// TODO Auto-generated method stub
		return userDAO.findById(id);
	}
	@Transactional
	@Override
	public List<UserEntity> findLeader(String department) {
		// TODO Auto-generated method stub
		return userDAO.findleader(department,"2");
	}
	@Transactional
	@Override
	public List<UserEntity> findLeader1(String department,String username) {
		// TODO Auto-generated method stub
		return userDAO.findleader1(department,"2",username);
	}
	@Transactional
	@Override
	public List<UserEntity> findLeader2(String workgroupid) {
		
		return userDAO.findleader2(workgroupid);
	}
	@Transactional
	@Override
	public int changeInfo(UserModifyDTO userModifyDTO) {
		// TODO Auto-generated method stub
		return userDAO.changeInfo(userModifyDTO.getNewpassword(), userDAO.findByUserName(userModifyDTO.getUsername(),MD5Util.toMD5(userModifyDTO.getPassword())).get(0).getId().toString());
	}
	@Transactional
	@Override
	public List<UserEntity> getId(UserModifyDTO userModifyDTO) {
		// TODO Auto-generated method stub
		return userDAO.findByUserName(userModifyDTO.getUsername(),MD5Util.toMD5(userModifyDTO.getPassword()) );
	}
	
	@Transactional
	@Override
	public int changeGuo(String password, String username) {
		// TODO Auto-generated method stub
		return userDAO.changeGuo(password, username);
	}
	
	@Transactional
	@Override
	public int passwordToMD5(String userpassword,String id) {
		// TODO Auto-generated method stub
		return userDAO.passwordToMD5(userpassword,id);
	}
	
	
	@Transactional
	@Override
	public List<UserEntity> getAll() {
		// TODO Auto-generated method stub
		return userDAO.getAll();
	}
	
	@Transactional
	@Override
	public int ResetPassword(String username)
	{
		return userDAO.ResetPassword("21218cca77804d2ba1922c33e0151105", username);
	}
	@Override
	public List<UserEntity> findZhang() {
		// TODO Auto-generated method stub
		return userDAO.findZhang("����");
	}

	
	@Override
	public List<UserEntity> NeedDeng(String gender, String username) {
		// TODO Auto-generated method stub
		return userDAO.NeedDeng(gender, username);
	}
	@Override
	public List<UserEntity> findDeng(String department) {
		// TODO Auto-generated method studb
		return userDAO.findDeng(department, "2", "�����");
	}
	
	//��ʱ���
	@Override
	public List<UserEntity> findJZ() {
		// TODO Auto-generated method stub
		return userDAO.findJZ();
	}
	//Web-��ѯ�û���ҳ
	@Override
	public List<UserEntity> getByPage(int start, int number) {
		return userDAO.getByPage(start, number);
	}
	
	//Web-��ȡ�û�������
	public int getAllCount(){
		return userDAO.getAllCount();
	}
	//Web-��ȡ���id
	@Override
	public int getMaxId() {
		List<UserEntity> list = userDAO.getMaxId();
		if(list.size()==0){
			
			return 1;
		}else{
			return list.get(0).getId()+1;
		}
	}
	//Web-����
	@Transactional
	@Override
	public int saveUser(UserDTO UserDTO) {
		UserDTO.setId(this.getMaxId());
		UserDTO.setPassword("21218cca77804d2ba1922c33e0151105");
		UserDTO.setDepartmentId(departmentDAO.getByName(UserDTO.getDepartmentName()).get(0).getDepartId());
		String RoleId = "";
		if("��ͨԱ��".equals(UserDTO.getDuty())){
			RoleId = "1";
		}else if("���ž���".equals(UserDTO.getDuty())){
			RoleId = "2";
		}else if("��˾�쵼".equals(UserDTO.getDuty())){
			RoleId = "3";
		}
		
		String UserName = UserDTO.getUsername();
		String UserPassword = UserDTO.getPassword();
		
		String Id = String.valueOf(UserDTO.getId());
		String Code = UserDTO.getCode();
		String RealName = UserDTO.getRealName();
		String DepartmentId = UserDTO.getDepartmentId();
		String DepartmentName = UserDTO.getDepartmentName(); 
		String Duty = UserDTO.getDuty();
		String CompanyId = "1";
		String CompanyName = "����ͨ���Ƽ����޹�˾";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String CreateOn = sdf.format(new Date());
		int flag = userDAO.saveUser(Id,Code,UserName,RealName,UserPassword,DepartmentId,DepartmentName,Duty,CompanyId,CompanyName,CreateOn,RoleId,"0");
		return flag;
	}
	//Web-�޸�
	@Transactional
	@Override
	public int updateUser(UserDTO UserDTO) {
		String RoleId = "";
		if("��ͨԱ��".equals(UserDTO.getDuty())){
			RoleId = "1";
		}else if("���ž���".equals(UserDTO.getDuty())){
			RoleId = "2";
		}else if("��˾�쵼".equals(UserDTO.getDuty())){
			RoleId = "3";
		}
		UserDTO.setDepartmentId(departmentDAO.getByName(UserDTO.getDepartmentName()).get(0).getDepartId());
		return userDAO.updateUser(String.valueOf(UserDTO.getId()), UserDTO.getCode(), UserDTO.getUsername(), 
				UserDTO.getRealName(), UserDTO.getDepartmentId(), UserDTO.getDepartmentName(), UserDTO.getDuty(),RoleId);
	}
	//Web-ɾ��
	@Transactional
	@Override
	public int deleteUser(String Id) {
		int flag = userDAO.deleteUser(Id);
		return flag;
	}
	//Web-��������
	@Transactional
	@Override
	public int ResetPasswordById(String Id) {
		int flag = userDAO.ResetPasswordById("21218cca77804d2ba1922c33e0151105", Id);
		return flag;
	}
	//Web-��ѯ�û�
	@Override
	public List<UserEntity> searchUser(UserDTO UserDTO) {
		String code = "%"+UserDTO.getCode()+"%";
		String departmentName = "%"+UserDTO.getDepartmentName()+"%";
		return userDAO.searchUser(code, departmentName);
	}
	//Web-��ѯ�û�/�û���
	@Override
	public List<UserEntity> searchUserByCode(UserDTO UserDTO) {
		String code = "%"+UserDTO.getCode()+"%";
		return userDAO.searchUserByCode(code);
	}
	//Web-��ѯ�û�/����
	@Override
	public List<UserEntity> searchUserByDepartmentName(UserDTO UserDTO,int start,int number) {
		return userDAO.searchUserByDepartmentName(UserDTO.getDepartmentName(),start,number);
	}
	//Web-������д���ϸ-������
	@Transactional
	@Override
	public int export(String[] nlist,String path,String depart) {
		// �õ����ݼ���
		List<UserEntity> userList = new ArrayList<UserEntity>();
		userList = getUserListByNum(nlist,depart);
			return exportExcel(userList, path);
		}
	//export�·���-����id�����ѯ������Ҫ�����û�
	public List<UserEntity> getUserListByNum(String[] nlist,String depart){
		List<UserEntity> listAll = new ArrayList<UserEntity>();
		if(!"".equals(depart)){
			//��ѯĳ�����������û�
			listAll = userDAO.searchUserByDepartmentName(depart, 0, 999999);
		}else{
			//��ѯ�����û�
			listAll = userDAO.getAll();
		}
		Integer[] nlist1 = new Integer[listAll.size()];
		int z=0;
		for(UserEntity user:listAll){
			nlist1[z] = user.getId();
			z++;
		}
		List<UserEntity> list = new ArrayList<UserEntity>();// ���շ��ص�list
		UserEntity user = new UserEntity();
		//List<EntertainApplyInfoEntity> apply = new ArrayList<EntertainApplyInfoEntity>(); // ������ǰ����list
		for (int t = 0;t<nlist1.length;t++)//����numberȡ��Ҫ��������ǰ��Ϣ
		{
			user = userDAO.findById(String.valueOf(nlist1[t])).get(0);
			list.add(user);
		}
		return list;
	}
	//export�·���
	@Override
	public int exportExcel(List<UserEntity> userList, String path) {
		// ����һ��������
		XSSFWorkbook workbook;
		String sheetName = "�û��б�";
		try{
			workbook = new XSSFWorkbook();
			// ���һ��sheet,sheet��
			XSSFSheet sheet = workbook.createSheet(sheetName);
			// �ϲ���Ԫ�� ������˼�� ��һ�С����һ�С���һ�С����һ��
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));
			// ������һ��
			XSSFRow titleRow = sheet.createRow(0);
			// �������ⵥԪ���ʽ
			XSSFCellStyle titleStyle = workbook.createCellStyle();
			// ����һ�����и�ʽ
			titleStyle.setAlignment(HorizontalAlignment.CENTER);
			// ����һ������
			XSSFFont titleFont = workbook.createFont();
			titleFont.setFontHeight(16);// ��������Ϊ16��
			// ������Ӧ�õ���ǰ�ĸ�ʽ��
			titleStyle.setFont(titleFont);
			// �ڵ�һ���д���һ����Ԫ��
			XSSFCell titleCell = titleRow.createCell(0);
			// ����ֵ����ʽ������
			titleCell.setCellValue(sheetName);
			titleCell.setCellStyle(titleStyle);
			// ------------����Ϊ��һ��------------
			// �ں���λ�õ�������Ӧ
			sheet.autoSizeColumn(0);
			sheet.autoSizeColumn(1);
			sheet.autoSizeColumn(2);
			sheet.autoSizeColumn(3);
			sheet.autoSizeColumn(4);
			
			// �����������ĵ�Ԫ���ʽ
			XSSFCellStyle style = workbook.createCellStyle();
			style.setAlignment(HorizontalAlignment.CENTER);
			// ���õڶ��б�ͷ
			XSSFRow rowHeader = sheet.createRow(1);
			XSSFCell cell = rowHeader.createCell(0);// ��1��
			cell.setCellValue("���");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(1);// ��2��
			cell.setCellValue("����");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(2);// ��3��
			cell.setCellValue("�û���");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(3);// ��4��
			cell.setCellValue("��ʵ����");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(4);// ��5��
			cell.setCellValue("ְλ");
			cell.setCellStyle(style);
			// ��ͷ���------------------
			int index = 1;// �кţ�Ӧ�ӵ����п�ʼ��ÿ��ѭ������++
			UserEntity user = new UserEntity();
			// �������Ͻ�����д��excel��
			if(userList.size()>0){
				for(int i=0;i<userList.size();i++){
					
					// �к�++��2��ʼ
					index++;
					
					// ����
					user = userList.get(i);
					
					XSSFRow row = sheet.createRow(index);
					
					XSSFCell rowCell = row.createCell(0);// ��1��
					rowCell.setCellValue(i + 1 + "");
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(1);// ��2��(����)
					rowCell.setCellValue(user.getDepartmentName());
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(2);// ��3��(�û���)
					rowCell.setCellValue(user.getCode());
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(3);// ��4��(��ʵ����)
					rowCell.setCellValue(user.getRealName());
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(4);// ��5��(ְ��)
					rowCell.setCellValue(user.getDuty());
					rowCell.setCellStyle(style);
				}
			}
			// ���ļ����浽ָ��λ��
			FileOutputStream out = new FileOutputStream(path);
			workbook.write(out);
			return 1;
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	//Web-��ѯ�û�/���ݲ��Ų�ѯ�û�����
	@Override
	public int searchUserCountByDepartmentName(UserDTO UserDTO) {
		return userDAO.searchUserCountByDepartmentName(UserDTO.getDepartmentName());
	}
	//Web-��ѯ����Ϊ��Ա��ʱ�û�����
	@Override
	public int searchUserCountByCode(UserDTO UserDTO) {
		return userDAO.searchUserCountByCode(UserDTO.getCode());
	}
	//Web-��ȡְλ
	@Override
	public List<DutyEntity> getDuty() {
		return userDAO.getDuty();
	}
	//Web-��ȡ�˵�
	@Override
	public List<MenuEntity> getMenu(String id) {
		return userDAO.getMenu(id);
	}
	//�ж��Ƿ���Ե�������
	@Override
	public boolean findByIdDate(String id) {
		List<UserEntity> list = userDAO.findById(id);
		UserEntity user = list.get(0);
		if(user!=null){
			if("1".equals(user.getTitle())){
				return true;
			}
		}
		return false;
	}
	//web-����˽��Ȩ��
	@Transactional
	@Override
	public int updatePrivate(String id) {
		return userDAO.updatePrivate(id);
	}
	/**
	 * �����û����Ͳ��Ų�ѯ�û�
	 */
	@Override
	public List<UserEntity> findUserByNameAndDept(String department, String username) {
		return userDAO.findUserByNameAndDept(department, username);
	}
	/**
	 * �����û����Ͳ��Ų�ѯ�û�
	 */
	public List<UserEntity> findUserByNameAndId(String id, String username) {
		return userDAO.findUserByNameAndId(id, username);
	}
}
