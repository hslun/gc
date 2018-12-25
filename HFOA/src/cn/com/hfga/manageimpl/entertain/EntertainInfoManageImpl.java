package cn.com.hfga.manageimpl.entertain;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.hfga.dao.entertain.EntertainAnnualBudgetDAO;
import cn.com.hfga.dao.entertain.EntertainApplyInfoDAO;
import cn.com.hfga.dao.entertain.EntertainRegisterInfoDAO;
import cn.com.hfga.dao.user.UserDAO;
import cn.com.hfga.dto.entertain.EntertainAnnualInfoDTO;
import cn.com.hfga.dto.entertain.EntertainInfoDTO;
import cn.com.hfga.dto.entertain.EntertainSearchInfoDTO;
import cn.com.hfga.dto.entertain.EntertainSumInfoDTO;
import cn.com.hfga.entity.entertain.EntertainAnnualBudgetEntity;
import cn.com.hfga.entity.entertain.EntertainApplyInfoEntity;
import cn.com.hfga.entity.entertain.EntertainInfoEntity;
import cn.com.hfga.entity.entertain.EntertainRegisterInfoEntity;
import cn.com.hfga.manager.entertain.EntertainInfoManage;

import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@Service("entertainInfoManageImpl")
public class EntertainInfoManageImpl implements EntertainInfoManage {

	@Autowired
	private EntertainApplyInfoDAO entertainApplyInfoDAO;

	@Autowired
	private EntertainRegisterInfoDAO entertainRegisterInfoDAO;

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private EntertainAnnualBudgetDAO entertainAnnualBudgetDAO;

	// 获取用户密码
	public String getPassword(String username) {
		return userDAO.getPassword(username);
	}

	// 通过查询信息，获得一条事前内容
	@Transactional
	@Override
	public EntertainApplyInfoEntity getSearchApplyInfo1(EntertainSearchInfoDTO entertainSearchInfoDTO) {
		String department = entertainSearchInfoDTO.getDepartment();// 获得部门名称
		entertainSearchInfoDTO.setManager("%" + entertainSearchInfoDTO.getManager() + "%");
		entertainSearchInfoDTO.setEntertainObject("%" + entertainSearchInfoDTO.getEntertainObject() + "%");
		if (department.equals("全部")) {
			return entertainApplyInfoDAO.getSearchInfo11(entertainSearchInfoDTO.getEntertainObject(),
					entertainSearchInfoDTO.getStartTime(), entertainSearchInfoDTO.getEndTime(),
					entertainSearchInfoDTO.getManager());
		} else {
			return entertainApplyInfoDAO.getSearchInfo00(department, entertainSearchInfoDTO.getEntertainObject(),
					entertainSearchInfoDTO.getStartTime(), entertainSearchInfoDTO.getEndTime(),
					entertainSearchInfoDTO.getManager());
		}
	}

	// 通过事前审批单号获得对应事后通过信息
	@Transactional
	@Override
	public List<EntertainRegisterInfoEntity> getSearchRegisterInfo(String number) {

		return entertainRegisterInfoDAO.getSearchRegisterInfo(number);
	}

	// 通过 事前审批单号获得对应事后待审核信息
	@Transactional
	@Override
	public List<EntertainRegisterInfoEntity> getReadyRegisterInfo(String number) {
		return entertainRegisterInfoDAO.getReadyRegisterInfo(number);
	}
	
	// 查询申请
	@Transactional
	@Override
	public EntertainInfoEntity getSearchInfo1(EntertainSearchInfoDTO entertainSearchInfoDTO) {
		EntertainApplyInfoEntity apply = getSearchApplyInfo1(entertainSearchInfoDTO);
		String number = apply.getNumber();
		List<EntertainRegisterInfoEntity> register = getSearchRegisterInfo(number);
		EntertainInfoEntity entertain = new EntertainInfoEntity();
		entertain.setID(apply.getID());
		entertain.setNumber(apply.getNumber());
		entertain.setDepartment(apply.getDepartment());
		entertain.setApplyTime(apply.getApplyTime());
		entertain.setEntertainObject(apply.getEntertainObject());
		entertain.setEntertainReason(apply.getEntertainReason());
		entertain.setEntertainNum(apply.getEntertainNum());
		entertain.setAccompanyNum(apply.getAccompanyNum());
		entertain.setPerBudget(apply.getPerBudget());
		entertain.setMasterBudget(apply.getMasterBudget());
		entertain.setEntertainCategory(apply.getEntertainCategory());
		entertain.setManager(apply.getManager());
		entertain.setApprover(apply.getApprover());
		entertain.setStatus(apply.getStatus());
		entertain.setRemark(apply.getRemark());
		entertain.setIfWine(apply.getIfWine());
		entertain.setWineType(apply.getWineType());
		entertain.setWineNum(apply.getWineNum());
		entertain.setIfBefore(apply.getIfBefore());
		entertain.setBeforeDate(apply.getBeforeDate());
		if(register!=null&&!register.isEmpty()){
			entertain.setWineSum(register.get(0).getWineSum());
			entertain.setEnterSum(register.get(0).getEnterSum());
			entertain.setPersonSum(register.get(0).getPersonSum());
		}
		entertain.setList(register);

		return entertain;

	}
	//
	/*
	 * public List<EntertainInfoEntity> getAllApproved(int start, int number) {
	 * List<EntertainInfoEntity> list = new ArrayList<EntertainInfoEntity>();
	 * EntertainInfoEntity entertain = new EntertainInfoEntity(); //取出事前表中已完成的值
	 * List<EntertainApplyInfoEntity> apply = entertainApplyInfoDAO.getAll();
	 * //循环遍历apply中的条目 取出对应的事后内容存到entertain中
	 * 
	 * //将符合条件的值存入list中 list.add(entertain); return null; }
	 */

	// 获取已完成条目总数
	/*
	 * public int getAllApprovedCount() { return
	 * entertainApplyInfoDAO.getAllApprovedCount();
	 */

	// 根据查询条件获得列表
	@Transactional
	@Override
	public List<EntertainInfoEntity> getList(EntertainSearchInfoDTO entertainSearchInfoDTO) {
		List<EntertainInfoEntity> list = new ArrayList<EntertainInfoEntity>();// 最终返回的list
		List<EntertainApplyInfoEntity> apply = new ArrayList<EntertainApplyInfoEntity>();
		String department = entertainSearchInfoDTO.getDepartment();
		String entertainObject = entertainSearchInfoDTO.getEntertainObject();
		if(!entertainObject.equals("全部"))
		{entertainObject = "%"+entertainObject+"%";}
		String m = entertainSearchInfoDTO.getManager();
		String manager = "%"+m+"%";
		if (department.equals("全部") && entertainObject.equals("全部")){
			apply = entertainApplyInfoDAO.getSearchApproved11(manager,
					entertainSearchInfoDTO.getStartTime(),entertainSearchInfoDTO.getEndTime());
		}
		else if (!department.equals("全部") && entertainObject.equals("全部")){
			apply = entertainApplyInfoDAO.getSearchApproved01(department,manager,
					entertainSearchInfoDTO.getStartTime(),entertainSearchInfoDTO.getEndTime());
		}
		else if (department.equals("全部") && !entertainObject.equals("全部")){
			apply = entertainApplyInfoDAO.getSearchApproved10(manager,
					entertainSearchInfoDTO.getStartTime(),entertainSearchInfoDTO.getEndTime(),entertainObject);
		}
		else {
			apply = entertainApplyInfoDAO.getSearchApproved00(entertainSearchInfoDTO.getDepartment(),manager,
				entertainSearchInfoDTO.getStartTime(),entertainSearchInfoDTO.getEndTime(),entertainObject);
		}
		/*List<EntertainApplyInfoEntity> apply = entertainApplyInfoDAO.getApplyList(
				entertainSearchInfoDTO.getDepartment(), entertainSearchInfoDTO.getStartTime(),
				entertainSearchInfoDTO.getEndTime(), entertainSearchInfoDTO.getManager(),
				entertainSearchInfoDTO.getEntertainObject()); // 取出已审批的事前list
*/		for (int i = 0; i < apply.size(); i++) { // 遍历apply表
			EntertainInfoEntity entertain = new EntertainInfoEntity(); // 存储变量
			String number = apply.get(i).getNumber(); // 取出事前表中的审批单号，用于查询对应的事后表单
			List<EntertainRegisterInfoEntity> register = getSearchRegisterInfo(number); // 取出对应的已完成的事后表单
			if (register.size() == apply.get(i).getRegisterNum()) { // 如果事后表单有值，则为有效信息，存储到要提交的list中
				entertain.setID(apply.get(i).getID());
				entertain.setNumber(apply.get(i).getNumber());
				entertain.setDepartment(apply.get(i).getDepartment());
				entertain.setApplyTime(apply.get(i).getApplyTime());
				entertain.setEntertainObject(apply.get(i).getEntertainObject());
				entertain.setEntertainReason(apply.get(i).getEntertainReason());
				entertain.setEntertainNum(apply.get(i).getEntertainNum());
				entertain.setAccompanyNum(apply.get(i).getAccompanyNum());
				entertain.setPerBudget(apply.get(i).getPerBudget());
				entertain.setMasterBudget(apply.get(i).getMasterBudget());
				entertain.setEntertainCategory(apply.get(i).getEntertainCategory());
				entertain.setManager(apply.get(i).getManager());
				entertain.setApprover(apply.get(i).getApprover());
				entertain.setStatus(apply.get(i).getStatus());
				entertain.setRemark(apply.get(i).getRemark());
				entertain.setIfWine(apply.get(i).getIfWine());
				entertain.setWineType(apply.get(i).getWineType());
				entertain.setWineNum(apply.get(i).getWineNum());
				entertain.setIfBefore(apply.get(i).getIfBefore());
				entertain.setBeforeDate(apply.get(i).getBeforeDate());
				if(register!=null&&!register.isEmpty()){
					entertain.setWineSum(register.get(0).getWineSum());
					entertain.setEnterSum(register.get(0).getEnterSum());
					entertain.setPersonSum(register.get(0).getPersonSum());
				}
				entertain.setList(register);
				list.add(entertain);
			} else {
				continue;
			} // 如果事后表单没有值，继续向下进行遍历
		}
		return list;

	}

	// 测试
	@Transactional
	@Override
	public List<EntertainApplyInfoEntity> testList(EntertainSearchInfoDTO entertainSearchInfoDTO) {
		List<EntertainApplyInfoEntity> apply = entertainApplyInfoDAO.getApplyList(
				entertainSearchInfoDTO.getDepartment(), entertainSearchInfoDTO.getStartTime(),
				entertainSearchInfoDTO.getEndTime(), entertainSearchInfoDTO.getManager(),
				entertainSearchInfoDTO.getEntertainObject()); // 取出已审批的事前list
		return apply;
	}

	// Web-首页-已审核招待明细-显示
	@Transactional
	@Override
	public List<EntertainApplyInfoEntity> wGetAllApproved(int start, int number) {
		List<EntertainApplyInfoEntity> list1 = new ArrayList<EntertainApplyInfoEntity>();
		List<EntertainApplyInfoEntity> list = entertainApplyInfoDAO.getApplyCompleted(start, number);
		if(list.size()>0){
			for(EntertainApplyInfoEntity app:list){
				String invoiceNumber = "";
				double invoiceSum = 0;
				List<EntertainRegisterInfoEntity> reg = entertainRegisterInfoDAO.get(app.getNumber());
				for(int i=0;i<reg.size();i++){
					String numStr = reg.get(i).getInvoiceNumber();
					String sum = reg.get(i).getInvoiceSum();
					if(numStr!=null&&!"".equals(numStr)){
						invoiceNumber += numStr;
						if(i!=reg.size()-1){
							invoiceNumber += ",";
						}
					}
					if(sum!=null&&!"".equals(sum)){
						invoiceSum += Double.parseDouble(sum);
					}
				}
				app.setInvoiceNumber(invoiceNumber);
				app.setInvoiceSum(String.valueOf(invoiceSum));
				list1.add(app);
			}
		}
		return list1;
	}
	/*
	 * @Transactional
	 * 
	 * @Override public List<EntertainInfoEntity> getList(EntertainSearchInfoDTO
	 * entertainSearchInfoDTO){ List<EntertainInfoEntity> list = new
	 * ArrayList<EntertainInfoEntity>();//最终返回的list
	 * List<EntertainApplyInfoEntity> apply =
	 * entertainApplyInfoDAO.getApplyCompleted(); //取出已完成的事前list for(int i = 0;
	 * i < apply.size(); i++){ // 遍历apply表 EntertainInfoEntity entertain = new
	 * EntertainInfoEntity(); //存储变量 String number = apply.get(i).getNumber();
	 * //取出事前表中的审批单号，用于查询对应的事后表单 List<EntertainRegisterInfoEntity> register =
	 * getSearchRegisterInfo(number); //取出对应的已完成的事后表单
	 * 
	 * entertain.setID(apply.get(i).getID());
	 * entertain.setNumber(apply.get(i).getNumber());
	 * entertain.setDepartment(apply.get(i).getDepartment());
	 * entertain.setApplyTime(apply.get(i).getApplyTime());
	 * entertain.setEntertainObject(apply.get(i).getEntertainObject());
	 * entertain.setEntertainReason(apply.get(i).getEntertainReason());
	 * entertain.setEntertainNum(apply.get(i).getEntertainNum());
	 * entertain.setAccompanyNum(apply.get(i).getAccompanyNum());
	 * entertain.setPerBudget(apply.get(i).getPerBudget());
	 * entertain.setMasterBudget(apply.get(i).getMasterBudget());
	 * entertain.setEntertainCategory(apply.get(i).getEntertainCategory());
	 * entertain.setManager(apply.get(i).getManager());
	 * entertain.setApprover(apply.get(i).getApprover());
	 * entertain.setStatus(apply.get(i).getStatus());
	 * entertain.setRemark(apply.get(i).getRemark());
	 * entertain.setList(register); list.add(entertain); } return list; }
	 */
	
	//获得已完成的招待明细总数
	@Transactional
	@Override
	public int getAllCompletedCount() {
		// TODO Auto-generated method stub
		return entertainApplyInfoDAO.getAllCompletedCount();
	}
	
	//Web-已审核招待明细-导出表单
	@Transactional
	@Override
	public int export(String[] nlist,String path) {
		// 得到数据集合
		List<EntertainInfoEntity> entertainList = new ArrayList<EntertainInfoEntity>();
		entertainList = getEntertainListByNum(nlist);
			return exportExcel(entertainList, path);
		}
	
	//获得全部已完成的招待信息
	  public List<EntertainInfoEntity> getEntertainList() {
		List<EntertainInfoEntity> list = new ArrayList<EntertainInfoEntity>();// 最终返回的list
		List<EntertainApplyInfoEntity> apply = entertainApplyInfoDAO.getApplyCompleted(); // 取出已完成的事前list
		for (int i = 0; i < apply.size(); i++) { // 遍历apply表
			EntertainInfoEntity entertain = new EntertainInfoEntity(); // 存储变量
			String number = apply.get(i).getNumber(); // 取出事前表中的审批单号，用于查询对应的事后表单
			List<EntertainRegisterInfoEntity> register = getSearchRegisterInfo(number); // 取出对应的已完成的事后表单
				entertain.setID(apply.get(i).getID());
				entertain.setNumber(apply.get(i).getNumber());
				entertain.setDepartment(apply.get(i).getDepartment());
				entertain.setApplyTime(apply.get(i).getApplyTime());
				entertain.setEntertainObject(apply.get(i).getEntertainObject());
				entertain.setEntertainReason(apply.get(i).getEntertainReason());
				entertain.setEntertainNum(apply.get(i).getEntertainNum());
				entertain.setAccompanyNum(apply.get(i).getAccompanyNum());
				entertain.setPerBudget(apply.get(i).getPerBudget());
				entertain.setMasterBudget(apply.get(i).getMasterBudget());
				entertain.setEntertainCategory(apply.get(i).getEntertainCategory());
				entertain.setManager(apply.get(i).getManager());
				entertain.setApprover(apply.get(i).getApprover());
				entertain.setStatus(apply.get(i).getStatus());
				entertain.setRemark(apply.get(i).getRemark());
				entertain.setIfWine(apply.get(i).getIfWine());
				entertain.setWineType(apply.get(i).getWineType());
				entertain.setWineNum(apply.get(i).getWineNum());
				entertain.setIfBefore(apply.get(i).getIfBefore());
				entertain.setBeforeDate(apply.get(i).getBeforeDate());
				if(register!=null&&!register.isEmpty()){
					entertain.setWineSum(register.get(0).getWineSum());
					entertain.setEnterSum(register.get(0).getEnterSum());
					entertain.setPersonSum(register.get(0).getPersonSum());
				}
				entertain.setList(register);
				list.add(entertain);
		}
		return list;

	}
	
	//获得全部已完成的招待信息
	  public List<EntertainInfoEntity> getEntertainListByNum(String[] nlist) {
		List<EntertainApplyInfoEntity> listAll = entertainApplyInfoDAO.getApplyCompleted();// 最终返回的
		//不只取当前页记录数，取出全部 nlist（当前页记录数）->nlist1（全部）   3/27
		String[] nlist1 = new String[listAll.size()];
		int z=0;
		for(EntertainApplyInfoEntity en:listAll){
			nlist1[z] = en.getNumber();
			z++;
		}
		List<EntertainInfoEntity> list = new ArrayList<EntertainInfoEntity>();// 最终返回的list
		//List<EntertainApplyInfoEntity> apply = new ArrayList<EntertainApplyInfoEntity>(); // 存贮事前表单的list
		for (int t = 0;t<nlist1.length;t++)//根据number取出要导出的事前信息
		{
			EntertainApplyInfoEntity app = new EntertainApplyInfoEntity(); //存储变量
			EntertainInfoEntity entertain = new EntertainInfoEntity(); // 存储变量
			app = entertainApplyInfoDAO.applyDetail(nlist1[t]);
			/*apply.add(app);*/
			List<EntertainRegisterInfoEntity> register = getSearchRegisterInfo(nlist1[t]); // 取出对应的已完成的事后表
			entertain.setID(app.getID());
			entertain.setNumber(app.getNumber());
			entertain.setDepartment(app.getDepartment());
			entertain.setApplyTime(app.getApplyTime());
			entertain.setEntertainObject(app.getEntertainObject());
			entertain.setEntertainReason(app.getEntertainReason());
			entertain.setEntertainNum(app.getEntertainNum());
			entertain.setAccompanyNum(app.getAccompanyNum());
			entertain.setPerBudget(app.getPerBudget());
			entertain.setMasterBudget(app.getMasterBudget());
			entertain.setEntertainCategory(app.getEntertainCategory());
			entertain.setManager(app.getManager());
			entertain.setApprover(app.getApprover());
			entertain.setStatus(app.getStatus());
			entertain.setRemark(app.getRemark());
			entertain.setIfWine(app.getIfWine());
			entertain.setWineType(app.getWineType());
			entertain.setWineNum(app.getWineNum());
			entertain.setIfBefore(app.getIfBefore());
			entertain.setBeforeDate(app.getBeforeDate());
			if(register!=null&&!register.isEmpty()){
				entertain.setWineSum(register.get(0).getWineSum());
				entertain.setEnterSum(register.get(0).getEnterSum());
				entertain.setPersonSum(register.get(0).getPersonSum());
			}
			entertain.setList(register);
			list.add(entertain);
		}
		return list;

	}
	  
	
	 // 导出excel具体方法
	@Transactional
	@Override
	public int exportExcel(List<EntertainInfoEntity> entertainList, String path) {
		// 创建一个工作簿
		XSSFWorkbook workbook;
		String sheetName = "业务招待明细表";
		try {
			workbook = new XSSFWorkbook();
			// 添加一个sheet,sheet名
			XSSFSheet sheet = workbook.createSheet(sheetName);
			// 合并单元格 参数意思是 第一行、最后一行、第一列、最后一列
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 22));
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
			sheet.autoSizeColumn(5);
			sheet.autoSizeColumn(6);
			sheet.autoSizeColumn(7);
			sheet.autoSizeColumn(8);
			sheet.autoSizeColumn(9);
			sheet.autoSizeColumn(10);
			sheet.autoSizeColumn(11);
			sheet.autoSizeColumn(12);
			sheet.autoSizeColumn(13);
			sheet.autoSizeColumn(14);
			sheet.autoSizeColumn(15);
			sheet.autoSizeColumn(16);
			sheet.autoSizeColumn(17);
			sheet.autoSizeColumn(18);
			sheet.autoSizeColumn(19);
			sheet.autoSizeColumn(20);
			sheet.autoSizeColumn(21);
			sheet.autoSizeColumn(22);
			
			// 设置其他正文单元格格式
			XSSFCellStyle style = workbook.createCellStyle();
			style.setAlignment(HorizontalAlignment.CENTER);
			// 设置第二行表头
			XSSFRow rowHeader = sheet.createRow(1);
			XSSFCell cell = rowHeader.createCell(0);// 第1列
			cell.setCellValue("序号");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(1);// 第2列
			cell.setCellValue("审批单号");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(2);// 第3列
			cell.setCellValue("部门");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(3);// 第4列
			cell.setCellValue("申请时间");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(4);// 第5列
			cell.setCellValue("招待对象");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(5);// 第6列
			cell.setCellValue("事由");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(6);// 第7列
			cell.setCellValue("招待人数");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(7);// 第8列
			cell.setCellValue("陪同人数");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(8);// 第9列
			cell.setCellValue("人均标准");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(9);// 第10列
			cell.setCellValue("总预算");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(10);// 第11列
			cell.setCellValue("招待类别");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(11);// 第12列
			cell.setCellValue("经办人");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(12);// 第13列
			cell.setCellValue("审批人");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(13);// 第14列
			cell.setCellValue("发票开具日期");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(14);// 第15列
			cell.setCellValue("开票内容");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(15);// 第16列
			cell.setCellValue("发票金额");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(16);// 第17列
			cell.setCellValue("发票张数");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(17);// 第18列
			cell.setCellValue("报销时间");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(18);// 第19列
			cell.setCellValue("凭证号");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(19);// 第20列
			cell.setCellValue("发票开具单位");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(20);// 第21列
			cell.setCellValue("备注");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(21);// 第22列
			cell.setCellValue("是否补录");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(22);// 第22列
			cell.setCellValue("实际申请日期");
			cell.setCellStyle(style);
			
			// 表头完成------------------
			int index = 1;// 行号，应从第三行开始，每次循环进行++
			EntertainInfoEntity entertain = new EntertainInfoEntity();
			// 遍历集合将数据写到excel中
			if (entertainList.size() > 0) {
				for (int i = 0; i < entertainList.size(); i++) {
					// 行号++，2开始
					index++;
					
					// 对象
					entertain = entertainList.get(i);
					
					// 得到数据行数
					int hs = entertain.getList().size();
					
					// 生成相应的行数
					for(int r=0; r<hs; r++){
					XSSFRow row = sheet.createRow(r+index);
					
					// 合并对应行
					for(int j=0; j < 13; j++){
					sheet.addMergedRegion(new CellRangeAddress(index, index+hs-1, j, j));//首行、最后一行、首列、最后一列
					}
					
					XSSFCell rowCell = row.createCell(0);// 第1列
					rowCell.setCellValue(i + 1 + "");
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(1);// 第2列(事前-审批单号)
					rowCell.setCellValue(entertain.getNumber());
					rowCell.setCellStyle(style);

					rowCell = row.createCell(2);// 第3列(事前-部门)
					rowCell.setCellValue(entertain.getDepartment());
					rowCell.setCellStyle(style);

					rowCell = row.createCell(3);// 第4列(事前-申请时间)
					rowCell.setCellValue(entertain.getApplyTime());
					rowCell.setCellStyle(style);

					rowCell = row.createCell(4);// 第5列(事前-招待对象)
					rowCell.setCellValue(entertain.getEntertainObject());
					rowCell.setCellStyle(style);

					rowCell = row.createCell(5);// 第6列(事前-事由)
					rowCell.setCellValue(entertain.getEntertainReason());
					rowCell.setCellStyle(style);

					rowCell = row.createCell(6);// 第7列(事前-招待人数)
					rowCell.setCellValue(entertain.getEntertainNum());
					rowCell.setCellStyle(style);

					rowCell = row.createCell(7);// 第8列(事前-陪同人数)
					rowCell.setCellValue(entertain.getAccompanyNum());
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(8);// 第9列(事前-人均预算)
					rowCell.setCellValue(entertain.getPerBudget());
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(9);// 第10列(事前-总预算)
					rowCell.setCellValue(entertain.getMasterBudget());
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(10);// 第11列(事前-招待类别)
					rowCell.setCellValue(entertain.getEntertainCategory());
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(11);// 第12列(事前-经办人)
					rowCell.setCellValue(entertain.getManager());
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(12);// 第13列(事前-审批人)
					rowCell.setCellValue(entertain.getApprover());
					rowCell.setCellStyle(style);
				 
				 // 事后表的导出
				  	
					rowCell = row.createCell(13);// 第14列(事后-开票日期)
					rowCell.setCellValue(entertain.getList().get(r).getInvoiceDate());
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(14);// 第15列(事后-开票内容)
					rowCell.setCellValue(entertain.getList().get(r).getInvoiceContent());
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(15);// 第16列(事后-发票金额)
					rowCell.setCellValue(entertain.getList().get(r).getInvoiceSum());
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(16);// 第17列(事后-发票张数)
					rowCell.setCellValue(entertain.getList().get(r).getInvoiceNum());
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(17);// 第18列(事后-报销时间)
					rowCell.setCellValue(entertain.getList().get(r).getPaidTime());
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(18);// 第19列(事后-审批单号)
					rowCell.setCellValue(entertain.getList().get(r).getVoucherNum());
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(19);// 第20列(事后-发票开具单位)
					rowCell.setCellValue(entertain.getList().get(r).getInvoiceUnit());
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(20);// 第21列(事后-备注)
					rowCell.setCellValue(entertain.getList().get(r).getRemark());
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(21);// 第21列(是否补录)
					rowCell.setCellValue("1".equals(entertain.getIfBefore())?"是":"否");
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(22);// 第21列(是否补录)
					rowCell.setCellValue(entertain.getBeforeDate());
					rowCell.setCellStyle(style);
				}
					index=index+hs-1;
				}
			}
			
			// 将文件保存到指定位置
			FileOutputStream out = new FileOutputStream(path);
			workbook.write(out);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	// web-个人中心-保存更改的密码
	@Transactional
	@Override
	public int updatePassword(String password, String name) {
		return userDAO.updatePassword(password,name);
	}
	
	@Override
	public List<EntertainApplyInfoEntity> wRGetAllApproved(int start, int number) {
		List<EntertainApplyInfoEntity> newls = new ArrayList<>();
		List<EntertainApplyInfoEntity> list = entertainApplyInfoDAO.getUncompletedApply(start, number);
		for(EntertainApplyInfoEntity en:list){
			String wine = en.getWineType();
			if(wine!=null&&!"".equals(wine)&&wine.startsWith("[")){
				try {
					JSONArray json = new JSONArray(wine);
					int iSize = json.length();
					if(iSize!=0){
						String newPass = "";
						for(int i=0;i<iSize;i++){
							JSONObject obj = json.getJSONObject(i);
							newPass += obj.get("name1")+""+obj.get("value")+"瓶  ";
						}
						en.setWineType(newPass);
					}else{
						en.setWineType("");
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			newls.add(en);
		}
		return list;
	}
	
	@Transactional
	@Override
	public List<EntertainApplyInfoEntity> wRGetAllEntertain(int start, int number) {
		return entertainApplyInfoDAO.getAllEntertain(start, number);
	}

	public int getAllEntertainCount(){
		return entertainApplyInfoDAO.getAllEntertainCount();
	}
	
	public int getAllRCompletedCount() {
		return entertainApplyInfoDAO.getAllRCompletedCount();
	}

/*	// web-获得近十年的年份
	public Object getYear() {
		List<Map<String,Integer>>  list = new ArrayList<Map<String,Integer>>();
		 Calendar a=Calendar.getInstance();
		 Integer y = a.get(Calendar.YEAR);
		 for(int i=0;i<10;i++){
			 Map<String,Integer> jsonMap = new HashMap<String,Integer>();// 定义map
			 jsonMap.put("id", i);
			 jsonMap.put("year", y-i);
			 list.add(jsonMap);}
		return list;
	}*/
	
	// web-获得对应的年份列表
		public Object getYear() {
			List<Map<String,Integer>>  list = new ArrayList<Map<String,Integer>>();
			 Calendar a=Calendar.getInstance();
			 Integer y = a.get(Calendar.YEAR);
			 List<String> ylist = new ArrayList<String>(); 
			 ylist = entertainAnnualBudgetDAO.getYear(y+2);
			 for(int i=0;i<ylist.size();i++){
				 Map<String,Integer> jsonMap = new HashMap<String,Integer>();// 定义map
				 jsonMap.put("id", i);
				 jsonMap.put("year", Integer.parseInt(ylist.get(i)));
				 list.add(jsonMap);}
			return list;
		}
	
	// 获取事前通过，事后待审核的List
	@Transactional
	@Override
	public List<EntertainInfoEntity> getAPassList() {
		List<EntertainInfoEntity> list = new ArrayList<EntertainInfoEntity>();// 最终返回的list
		List<EntertainApplyInfoEntity> apply = new ArrayList<EntertainApplyInfoEntity>();
		apply = entertainApplyInfoDAO.getAllPassApply();
		for (int i = 0; i < apply.size(); i++) { // 遍历apply表
			EntertainInfoEntity entertain = new EntertainInfoEntity(); // 存储变量
			String number = apply.get(i).getNumber(); // 取出事前表中的审批单号，用于查询对应的事后表单
			List<EntertainRegisterInfoEntity> register = getReadyRegisterInfo(number); // 取出对应的已完成的事后表单
			if (register.size() == apply.get(i).getRegisterNum()) { // 如果事后表单有值，则为有效信息，存储到要提交的list中
				entertain.setID(apply.get(i).getID());
				entertain.setNumber(apply.get(i).getNumber());
				entertain.setDepartment(apply.get(i).getDepartment());
				entertain.setApplyTime(apply.get(i).getApplyTime());
				entertain.setEntertainObject(apply.get(i).getEntertainObject());
				entertain.setEntertainReason(apply.get(i).getEntertainReason());
				entertain.setEntertainNum(apply.get(i).getEntertainNum());
				entertain.setAccompanyNum(apply.get(i).getAccompanyNum());
				entertain.setPerBudget(apply.get(i).getPerBudget());
				entertain.setMasterBudget(apply.get(i).getMasterBudget());
				entertain.setRemainingBudget(apply.get(i).getRemainingBudget());
				entertain.setEntertainCategory(apply.get(i).getEntertainCategory());
				entertain.setManager(apply.get(i).getManager());
				entertain.setApprover(apply.get(i).getApprover());
				entertain.setApproveTime(apply.get(i).getApproveTime());
				entertain.setStatus(apply.get(i).getStatus());
				entertain.setRemark(apply.get(i).getRemark());
				entertain.setIfWine(apply.get(i).getIfWine());
				entertain.setWineType(apply.get(i).getWineType());
				entertain.setWineNum(apply.get(i).getWineNum());
				entertain.setIfBefore(apply.get(i).getIfBefore());
				entertain.setBeforeDate(apply.get(i).getBeforeDate());
				if(register!=null&&!register.isEmpty()){
					entertain.setWineSum(register.get(0).getWineSum());
					entertain.setEnterSum(register.get(0).getEnterSum());
					entertain.setPersonSum(register.get(0).getPersonSum());
				}
				entertain.setList(register);
				list.add(entertain);
			} else {
				continue;
			} // 如果事后表单没有值，继续向下进行遍历
		}
		return list;
	}
	
	public Object wGetAllUsed() {
		//List<Map<String,String>> sumList = new ArrayList<Map<String,String>>(); // 返给前端的list
		//获取当前年份
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        String year = sdf.format(date); 
		year = year+"%";
		List<Object[]> list = entertainApplyInfoDAO.getEntertainSum(year);
		int zh = 0;
		int cw = 0;
		int gh = 0;
		int zl = 0;
		int sc = 0;
		int xx = 0;
		int jd = 0;
		int th = 0;
		int wrj = 0;
		
		Map<String,String> jsonMap = new HashMap<String,String>();// 定义map
		for(int i=0;i<list.size();i++){
			Object[] objects=(Object[])list.get(i);
			String department = objects[2].toString();
			String sum = objects[3].toString();
			
			if(department.equals("信息技术部")){
				xx = xx + Integer.parseInt(sum);
			}
			else if(department.equals("综合办公室")){
				zh = zh + Integer.parseInt(sum);	
			}
			else if(department.equals("财务部")){
				cw = cw + Integer.parseInt(sum);	
			}
			else if(department.equals("质量部")){
				zl = zl + Integer.parseInt(sum);	
			}
			else if(department.equals("通航项目部")){
				th = th + Integer.parseInt(sum);	
			}
			else if(department.equals("机电产品部")){
				jd = jd + Integer.parseInt(sum);	
			} 
			else if(department.equals("市场部")){
				sc = sc + Integer.parseInt(sum);	
			}
			else if(department.equals("规划投资部")){
				gh = gh + Integer.parseInt(sum);	 
			}
			else{
				wrj = wrj + Integer.parseInt(sum);
			}
		}
		jsonMap.put("综合办公室", Integer.toString(zh));
		jsonMap.put("计划财务部", Integer.toString(cw));
		jsonMap.put("规划投资部", Integer.toString(gh));
		jsonMap.put("质量安全部", Integer.toString(zl));
		jsonMap.put("市场开发部", Integer.toString(sc));
		jsonMap.put("信息技术部", Integer.toString(xx));
		jsonMap.put("机电产品部", Integer.toString(jd));
		jsonMap.put("通航项目部", Integer.toString(th));
		jsonMap.put("无人机项目部", Integer.toString(wrj));
		
		return jsonMap;
	}

	// web-按照年份查询相信息
	@Transactional
	@Override
	public Object wGetSearchAnnual(String year) {
		
		List<EntertainAnnualInfoDTO> newlist= new ArrayList<EntertainAnnualInfoDTO>();
    	List<EntertainAnnualBudgetEntity> list= new ArrayList<EntertainAnnualBudgetEntity>();
    	
    	list = entertainAnnualBudgetDAO.wGetAnnualBudget(year);// 获得当年全部数据
    	for(int i=0;i<list.size();i++){
    	EntertainAnnualInfoDTO aNewList = new EntertainAnnualInfoDTO(); // 最终表的存储变量
        EntertainAnnualBudgetEntity aList = new EntertainAnnualBudgetEntity(); // 中间表的存储变量
    	String time=list.get(i).getTime(); //获得第i条的编制次数
    	aList=list.get(i); //获得当年数据中的第i条
    	if(time.equals("0")||time.equals("1")){
    		aNewList.setID(i);
    		aNewList.setDepartment(aList.getDepartment());
    		aNewList.setBudgetSum(aList.getBudgetSum0());
    		aNewList.setCopileTime(aList.getCopileTime0());
    		newlist.add(aNewList);
    	}
    	else if(time.equals("2")){
    		aNewList.setID(i);
    		aNewList.setDepartment(aList.getDepartment());
    		Double sum=Double.parseDouble(aList.getBudgetSum0())+Double.parseDouble(aList.getBudgetSum1());
    		aNewList.setBudgetSum(Double.toString(sum));
    		aNewList.setCopileTime(aList.getCopileTime1());
    		newlist.add(aNewList);
    	}
    	else if(time.equals("3")){
    		aNewList.setID(i);
    		aNewList.setDepartment(aList.getDepartment());
    		Double sum=Double.parseDouble(aList.getBudgetSum0())+Double.parseDouble(aList.getBudgetSum1())+Double.parseDouble(aList.getBudgetSum2());
    		aNewList.setBudgetSum(Double.toString(sum));
    		aNewList.setCopileTime(aList.getCopileTime2());
    		newlist.add(aNewList);
    	}
    	else if(time.equals("4")){
    		aNewList.setID(i);
    		aNewList.setDepartment(aList.getDepartment());
    		Double sum=Double.parseDouble(aList.getBudgetSum0())+Double.parseDouble(aList.getBudgetSum1())+Double.parseDouble(aList.getBudgetSum2())+Double.parseDouble(aList.getBudgetSum3());
    		aNewList.setBudgetSum(Double.toString(sum));
    		aNewList.setCopileTime(aList.getCopileTime3());
    		newlist.add(aNewList);
    	}
    	else if(time.equals("5")){
    		aNewList.setID(i);
    		aNewList.setDepartment(aList.getDepartment());
    		Double sum=Double.parseDouble(aList.getBudgetSum0())+Double.parseDouble(aList.getBudgetSum1())+Double.parseDouble(aList.getBudgetSum2())+Double.parseDouble(aList.getBudgetSum3())+Double.parseDouble(aList.getBudgetSum4());
    		aNewList.setBudgetSum(Double.toString(sum));
    		aNewList.setCopileTime(aList.getCopileTime4());
    		newlist.add(aNewList);
    	}
    	else{
    		aNewList.setID(i);
    		aNewList.setDepartment(aList.getDepartment());
    		Double sum=Double.parseDouble(aList.getBudgetSum0())+Double.parseDouble(aList.getBudgetSum1())+Double.parseDouble(aList.getBudgetSum2())+Double.parseDouble(aList.getBudgetSum3())+Double.parseDouble(aList.getBudgetSum4())+Double.parseDouble(aList.getBudgetSum5());
    		aNewList.setBudgetSum(Double.toString(sum));
    		aNewList.setCopileTime(aList.getCopileTime5());
    		newlist.add(aNewList);
    	}
    	}
		return newlist; //返回最终表
		//return entertainAnnualBudgetDAO.wGetSearchAnnual(year);
	}

	// web-获得选中年份每个部门的发生额
	@Transactional
	@Override
	public Object wGetSelectedUsed(String year) {
		year = year+"%";
       List<Object[]> list = entertainApplyInfoDAO.getEntertainSum(year);
		Double zh = 0.0d;
		Double cw = 0.0d;
		Double gh = 0.0d;                             
		Double zl = 0.0d;
		Double sc = 0.0d;
		Double xx = 0.0d;
		Double jd = 0.0d;                       
		Double th = 0.0d;
		Double wrj = 0.0d;
		Double gsld=0.0d;
		Double cp = 0.0d;
		
		Map<String,String> jsonMap = new HashMap<String,String>();// 定义map
		for(int i=0;i<list.size();i++){
			Object[] objects=(Object[])list.get(i);
			String department = objects[2].toString();
			String sum = objects[3].toString();
			
			if(department.equals("信息技术部")){
				xx = xx + Double.parseDouble(sum);
			}
			else if(department.equals("综合办公室")){
				zh = zh + Double.parseDouble(sum);	
			}
			else if(department.equals("财务部")){
				cw = cw + Double.parseDouble(sum);	
			}
			else if(department.equals("质量部")){
				zl = zl + Double.parseDouble(sum);	
			}
			else if(department.equals("通航项目部")){
				th = th + Double.parseDouble(sum);	
			}
			else if(department.equals("机电产品部")){
				jd = jd + Double.parseDouble(sum);	
			} 
			else if(department.equals("市场部")){
				sc = sc + Double.parseDouble(sum);	
			}
			else if(department.equals("规划投资部")){
				gh = gh + Double.parseDouble(sum);	 
			}
			else if(department.equals("无人机项目部")){
				wrj = wrj + Double.parseDouble(sum);
			}
			else if(department.equals("测评中心")){
				cp = cp + Double.parseDouble(sum);
			}
			else {
				gsld=gsld + Double.parseDouble(sum);
			}
		}
		jsonMap.put("综合办公室", Double.toString(zh));
		jsonMap.put("计划财务部", Double.toString(cw));
		jsonMap.put("规划投资部", Double.toString(gh));
		jsonMap.put("质量安全部", Double.toString(zl));
		jsonMap.put("市场开发部", Double.toString(sc));
		jsonMap.put("信息技术部", Double.toString(xx));
		jsonMap.put("机电产品部", Double.toString(jd));
		jsonMap.put("通航项目部", Double.toString(th));
 		jsonMap.put("无人机项目部", Double.toString(wrj));
 		jsonMap.put("公司领导", Double.toString(gsld));
 		jsonMap.put("测评中心", Double.toString(cp));
		
		return jsonMap;
	}

}


