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
		return userDAO.findZhang("张霓");
	}

	
	@Override
	public List<UserEntity> NeedDeng(String gender, String username) {
		// TODO Auto-generated method stub
		return userDAO.NeedDeng(gender, username);
	}
	@Override
	public List<UserEntity> findDeng(String department) {
		// TODO Auto-generated method studb
		return userDAO.findDeng(department, "2", "邓荔进");
	}
	
	//临时添加
	@Override
	public List<UserEntity> findJZ() {
		// TODO Auto-generated method stub
		return userDAO.findJZ();
	}
	//Web-查询用户分页
	@Override
	public List<UserEntity> getByPage(int start, int number) {
		return userDAO.getByPage(start, number);
	}
	
	//Web-获取用户总条数
	public int getAllCount(){
		return userDAO.getAllCount();
	}
	//Web-获取最大id
	@Override
	public int getMaxId() {
		List<UserEntity> list = userDAO.getMaxId();
		if(list.size()==0){
			
			return 1;
		}else{
			return list.get(0).getId()+1;
		}
	}
	//Web-保存
	@Transactional
	@Override
	public int saveUser(UserDTO UserDTO) {
		UserDTO.setId(this.getMaxId());
		UserDTO.setPassword("21218cca77804d2ba1922c33e0151105");
		UserDTO.setDepartmentId(departmentDAO.getByName(UserDTO.getDepartmentName()).get(0).getDepartId());
		String RoleId = "";
		if("普通员工".equals(UserDTO.getDuty())){
			RoleId = "1";
		}else if("部门经理".equals(UserDTO.getDuty())){
			RoleId = "2";
		}else if("公司领导".equals(UserDTO.getDuty())){
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
		String CompanyName = "海丰通航科技有限公司";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String CreateOn = sdf.format(new Date());
		int flag = userDAO.saveUser(Id,Code,UserName,RealName,UserPassword,DepartmentId,DepartmentName,Duty,CompanyId,CompanyName,CreateOn,RoleId,"0");
		return flag;
	}
	//Web-修改
	@Transactional
	@Override
	public int updateUser(UserDTO UserDTO) {
		String RoleId = "";
		if("普通员工".equals(UserDTO.getDuty())){
			RoleId = "1";
		}else if("部门经理".equals(UserDTO.getDuty())){
			RoleId = "2";
		}else if("公司领导".equals(UserDTO.getDuty())){
			RoleId = "3";
		}
		UserDTO.setDepartmentId(departmentDAO.getByName(UserDTO.getDepartmentName()).get(0).getDepartId());
		return userDAO.updateUser(String.valueOf(UserDTO.getId()), UserDTO.getCode(), UserDTO.getUsername(), 
				UserDTO.getRealName(), UserDTO.getDepartmentId(), UserDTO.getDepartmentName(), UserDTO.getDuty(),RoleId);
	}
	//Web-删除
	@Transactional
	@Override
	public int deleteUser(String Id) {
		int flag = userDAO.deleteUser(Id);
		return flag;
	}
	//Web-重置密码
	@Transactional
	@Override
	public int ResetPasswordById(String Id) {
		int flag = userDAO.ResetPasswordById("21218cca77804d2ba1922c33e0151105", Id);
		return flag;
	}
	//Web-查询用户
	@Override
	public List<UserEntity> searchUser(UserDTO UserDTO) {
		String code = "%"+UserDTO.getCode()+"%";
		String departmentName = "%"+UserDTO.getDepartmentName()+"%";
		return userDAO.searchUser(code, departmentName);
	}
	//Web-查询用户/用户名
	@Override
	public List<UserEntity> searchUserByCode(UserDTO UserDTO) {
		String code = "%"+UserDTO.getCode()+"%";
		return userDAO.searchUserByCode(code);
	}
	//Web-查询用户/部门
	@Override
	public List<UserEntity> searchUserByDepartmentName(UserDTO UserDTO,int start,int number) {
		return userDAO.searchUserByDepartmentName(UserDTO.getDepartmentName(),start,number);
	}
	//Web-已审核招待明细-导出表单
	@Transactional
	@Override
	public int export(String[] nlist,String path,String depart) {
		// 得到数据集合
		List<UserEntity> userList = new ArrayList<UserEntity>();
		userList = getUserListByNum(nlist,depart);
			return exportExcel(userList, path);
		}
	//export下方法-根据id数组查询出所有要导出用户
	public List<UserEntity> getUserListByNum(String[] nlist,String depart){
		List<UserEntity> listAll = new ArrayList<UserEntity>();
		if(!"".equals(depart)){
			//查询某部门下所有用户
			listAll = userDAO.searchUserByDepartmentName(depart, 0, 999999);
		}else{
			//查询所有用户
			listAll = userDAO.getAll();
		}
		Integer[] nlist1 = new Integer[listAll.size()];
		int z=0;
		for(UserEntity user:listAll){
			nlist1[z] = user.getId();
			z++;
		}
		List<UserEntity> list = new ArrayList<UserEntity>();// 最终返回的list
		UserEntity user = new UserEntity();
		//List<EntertainApplyInfoEntity> apply = new ArrayList<EntertainApplyInfoEntity>(); // 存贮事前表单的list
		for (int t = 0;t<nlist1.length;t++)//根据number取出要导出的事前信息
		{
			user = userDAO.findById(String.valueOf(nlist1[t])).get(0);
			list.add(user);
		}
		return list;
	}
	//export下方法
	@Override
	public int exportExcel(List<UserEntity> userList, String path) {
		// 创建一个工作簿
		XSSFWorkbook workbook;
		String sheetName = "用户列表";
		try{
			workbook = new XSSFWorkbook();
			// 添加一个sheet,sheet名
			XSSFSheet sheet = workbook.createSheet(sheetName);
			// 合并单元格 参数意思是 第一行、最后一行、第一列、最后一列
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));
			// 创建第一行
			XSSFRow titleRow = sheet.createRow(0);
			// 创建标题单元格格式
			XSSFCellStyle titleStyle = workbook.createCellStyle();
			// 创建一个居中格式
			titleStyle.setAlignment(HorizontalAlignment.CENTER);
			// 创建一个字体
			XSSFFont titleFont = workbook.createFont();
			titleFont.setFontHeight(16);// 标题字体为16号
			// 将字体应用到当前的格式中
			titleStyle.setFont(titleFont);
			// 在第一行中创建一个单元格
			XSSFCell titleCell = titleRow.createCell(0);
			// 设置值和样式，标题
			titleCell.setCellValue(sheetName);
			titleCell.setCellStyle(titleStyle);
			// ------------以上为第一行------------
			// 在合适位置调整自适应
			sheet.autoSizeColumn(0);
			sheet.autoSizeColumn(1);
			sheet.autoSizeColumn(2);
			sheet.autoSizeColumn(3);
			sheet.autoSizeColumn(4);
			
			// 设置其他正文单元格格式
			XSSFCellStyle style = workbook.createCellStyle();
			style.setAlignment(HorizontalAlignment.CENTER);
			// 设置第二行表头
			XSSFRow rowHeader = sheet.createRow(1);
			XSSFCell cell = rowHeader.createCell(0);// 第1列
			cell.setCellValue("序号");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(1);// 第2列
			cell.setCellValue("部门");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(2);// 第3列
			cell.setCellValue("用户名");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(3);// 第4列
			cell.setCellValue("真实姓名");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(4);// 第5列
			cell.setCellValue("职位");
			cell.setCellStyle(style);
			// 表头完成------------------
			int index = 1;// 行号，应从第三行开始，每次循环进行++
			UserEntity user = new UserEntity();
			// 遍历集合将数据写到excel中
			if(userList.size()>0){
				for(int i=0;i<userList.size();i++){
					
					// 行号++，2开始
					index++;
					
					// 对象
					user = userList.get(i);
					
					XSSFRow row = sheet.createRow(index);
					
					XSSFCell rowCell = row.createCell(0);// 第1列
					rowCell.setCellValue(i + 1 + "");
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(1);// 第2列(部门)
					rowCell.setCellValue(user.getDepartmentName());
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(2);// 第3列(用户名)
					rowCell.setCellValue(user.getCode());
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(3);// 第4列(真实姓名)
					rowCell.setCellValue(user.getRealName());
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(4);// 第5列(职责)
					rowCell.setCellValue(user.getDuty());
					rowCell.setCellStyle(style);
				}
			}
			// 将文件保存到指定位置
			FileOutputStream out = new FileOutputStream(path);
			workbook.write(out);
			return 1;
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	//Web-查询用户/根据部门查询用户数量
	@Override
	public int searchUserCountByDepartmentName(UserDTO UserDTO) {
		return userDAO.searchUserCountByDepartmentName(UserDTO.getDepartmentName());
	}
	//Web-查询名字为该员工时用户数量
	@Override
	public int searchUserCountByCode(UserDTO UserDTO) {
		return userDAO.searchUserCountByCode(UserDTO.getCode());
	}
	//Web-获取职位
	@Override
	public List<DutyEntity> getDuty() {
		return userDAO.getDuty();
	}
	//Web-获取菜单
	@Override
	public List<MenuEntity> getMenu(String id) {
		return userDAO.getMenu(id);
	}
	//判断是否可以调整日期
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
	//web-更改私车权限
	@Transactional
	@Override
	public int updatePrivate(String id) {
		return userDAO.updatePrivate(id);
	}
	/**
	 * 根据用户名和部门查询用户
	 */
	@Override
	public List<UserEntity> findUserByNameAndDept(String department, String username) {
		return userDAO.findUserByNameAndDept(department, username);
	}
	/**
	 * 根据用户名和部门查询用户
	 */
	public List<UserEntity> findUserByNameAndId(String id, String username) {
		return userDAO.findUserByNameAndId(id, username);
	}
}
