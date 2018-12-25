package cn.com.hfga.manageimpl.gz;

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

import cn.com.hfga.dao.gz.GZApplyInfoDAO;
import cn.com.hfga.dao.gz.GZKindDAO;
import cn.com.hfga.dao.user.DepartmentDAO;
import cn.com.hfga.dto.gz.GZApplyInfoDTO;
import cn.com.hfga.dto.gz.GZSearchInfoDTO;
import cn.com.hfga.entity.car.CarApplyInfoEntity;
import cn.com.hfga.entity.entertain.EntertainDepartmentEntity;
import cn.com.hfga.entity.gz.GZApplyInfoEntity;
import cn.com.hfga.entity.gz.GZKindEntity;
import cn.com.hfga.entity.user.DepartmentEntity;
import cn.com.hfga.manager.gz.GZApplyInfoManage;

@Service("gZApplyInfoManageImpl")
public class GZApplyInfoManageImpl implements GZApplyInfoManage {

	@Autowired
	private GZApplyInfoDAO gZApplyInfoDAO;

	@Autowired
	private GZKindDAO gZKindDAO;
	
	@Autowired
	private DepartmentDAO departmentDAO;

	@Transactional
	@Override
	public String CreateId(String department) {
		String resultString = "";

		List<Object> i = gZApplyInfoDAO.getMaxId(department, getDateTime() + "%");
		resultString = getDateTime() + getDepartmentId(department) + "001";

		if (i.get(0) == null) {
			return resultString;
		} else {
			String idString = i.get(0).toString();
			int id1 = Integer.valueOf(idString.substring(idString.length() - 3, idString.length()));
			String idsubString = idString.substring(0, idString.length() - 3);
			return idsubString.concat(addZero(id1));
		}
	}

	// 生成id最大的后三位
	public String addZero(int id) {
		String reString = String.valueOf(id + 1);
		if ((id + 1) < 10) {
			reString = "00" + reString;
		} else if ((id + 1) >= 10 && id < 100) {
			reString = "0" + reString;
		} else {
		}
		return reString;
	}

	@Transactional
	@Override
	public String getDateTime() {
		Date dtDate = new Date();
		SimpleDateFormat sm = new SimpleDateFormat("yyyyMMdd");
		return sm.format(dtDate);
	}

	@Transactional
	@Override
	public String getDepartmentId(String department) {
		// TODO Auto-generated method stub
		List<DepartmentEntity> departmentEntities = departmentDAO.getByName(department);
		if (departmentEntities.size() == 0) {
			return "0";
		} else {
			int id = Integer.valueOf(departmentEntities.get(0).getDepartId());
			if (id < 10) {
				return "0" + String.valueOf(id);
			} else {
				return String.valueOf(id);
			}
		}
	}

	@Transactional
	@Override
	public int saveGZApplyInfo(GZApplyInfoDTO gzApplyInfoDTO) {
		return gZApplyInfoDAO.insert(CreateId(gzApplyInfoDTO.getDepartment()), gzApplyInfoDTO.getDepartment(),
				gzApplyInfoDTO.getApplyUserName(), gzApplyInfoDTO.getReason(), gzApplyInfoDTO.getApplyTime(),
				gzApplyInfoDTO.getSendTo(), gzApplyInfoDTO.getGZKind(), gzApplyInfoDTO.getCopies(),
				gzApplyInfoDTO.getIsSecret(), gzApplyInfoDTO.getStatus(), gzApplyInfoDTO.getApproveMan());
	}

	@Transactional
	@Override
	public int delete(String id) {
		return gZApplyInfoDAO.delete(id);
	}

	// 王川需要确认
	@Transactional
	@Override
	public List<GZApplyInfoEntity> getNeedChuan() {
		// TODO Auto-generated method stub
		return gZApplyInfoDAO.getNeedChuan();
	}

	// 需要谭总确认
	@Transactional
	@Override
	public List<GZApplyInfoEntity> getNeedTan() {
		// TODO Auto-generated method stub
		return gZApplyInfoDAO.getNeedTan();
	}

	// 获取需要审批的
	@Transactional
	@Override
	public List<GZApplyInfoEntity> getByApprove(String approveMan) {
		// TODO Auto-generated method stub
		return gZApplyInfoDAO.getByApprove(approveMan);
	}

	@Transactional
	@Override
	public List<GZApplyInfoEntity> getAll() {
		// TODO Auto-generated method stub
		return gZApplyInfoDAO.getAll();
	}

	// 需要小银
	@Transactional
	@Override
	public List<GZApplyInfoEntity> getNeedYin() {
		// TODO Auto-generated method stub
		return gZApplyInfoDAO.getNeedYin();
	}

	// 审批
	@Transactional
	@Override
	public int updateApprove(String Status, String ApproveMan, String ID) {
		// TODO Auto-generated method stub
		return gZApplyInfoDAO.updateApprove(Status, ApproveMan, ID);
	}

	@Transactional
	@Override
	public int updateSure(String Status, String ConfirmMan, String ID) {
		// TODO Auto-generated method stub
		return gZApplyInfoDAO.updateSure(Status, ConfirmMan, ID);
	}

	@Transactional
	@Override
	public int updateStatus(String Status, String ID) {
		// TODO Auto-generated method stub
		return gZApplyInfoDAO.updateStatus(Status, ID);
	}

	@Transactional
	@Override
	public int modifyOne(GZApplyInfoDTO gzApplyInfoDTO) {
		// TODO Auto-generated method stub
		return gZApplyInfoDAO.modifyOne(gzApplyInfoDTO.getID(), gzApplyInfoDTO.getDepartment(),
				gzApplyInfoDTO.getApplyUserName(), gzApplyInfoDTO.getReason(), gzApplyInfoDTO.getApplyTime(),
				gzApplyInfoDTO.getSendTo(), gzApplyInfoDTO.getGZKind(), gzApplyInfoDTO.getCopies(),
				gzApplyInfoDTO.getIsSecret(), gzApplyInfoDTO.getStatus(), gzApplyInfoDTO.getApproveMan());
	}
	// 获取个人申请信息
	@Override
	public List<GZApplyInfoEntity> getPersonal(String ApplyUserName) {
		return gZApplyInfoDAO.getPersonal(ApplyUserName);
	}

	@Override
	public List<GZApplyInfoEntity> getSearchInfo(GZSearchInfoDTO gzSearchInfoDTO) {
		// TODO Auto-generated method stub
		String departname = gzSearchInfoDTO.getDepartmentName();// 获得部门名称
		String gzkind = gzSearchInfoDTO.getGzKind();
		gzSearchInfoDTO.setSendTo("%" + gzSearchInfoDTO.getSendTo() + "%");
		gzSearchInfoDTO.setUserName("%" + gzSearchInfoDTO.getUserName() + "%");
		if (departname.equals("全部") && gzkind.equals("全部")) {
			return gZApplyInfoDAO.getSearchInfo11(gzSearchInfoDTO.getUserName(), gzSearchInfoDTO.getStartTime(),
					gzSearchInfoDTO.getEndTime(), gzSearchInfoDTO.getSendTo());
		} else if (departname.equals("全部") && !gzkind.equals("全部")) {
			return gZApplyInfoDAO.getSearchInfo10(gzSearchInfoDTO.getUserName(), gzSearchInfoDTO.getStartTime(),
					gzSearchInfoDTO.getEndTime(), gzSearchInfoDTO.getGzKind(), gzSearchInfoDTO.getSendTo());
		} else if (!departname.equals("全部") && gzkind.equals("全部")) {
			return gZApplyInfoDAO.getSearchInfo01(departname, gzSearchInfoDTO.getUserName(),
					gzSearchInfoDTO.getStartTime(), gzSearchInfoDTO.getEndTime(), gzSearchInfoDTO.getSendTo());
		} else {
			return gZApplyInfoDAO.getSearchInfo00(departname, gzSearchInfoDTO.getUserName(),
					gzSearchInfoDTO.getStartTime(), gzSearchInfoDTO.getEndTime(), gzkind, gzSearchInfoDTO.getSendTo());
		}
	}

	@Override
	public List<GZApplyInfoEntity> getOne(String ID) {
		// TODO Auto-generated method stub
		return gZApplyInfoDAO.getOne(ID);
	}

	//Web-获取公章相关信息
	@Override
	public List<GZApplyInfoEntity> GZManageDisplay(int start, int number) {
		return gZApplyInfoDAO.GZManageDisplay(start,number);
	}

	//Web-获取公章信息总条数
	public int getAllCount() {
		return gZApplyInfoDAO.getAllCount();
	}

	//Web-获取公章类型
	public List<GZKindEntity> getType() {
		List<GZKindEntity> list = new ArrayList<GZKindEntity>();
		list = gZKindDAO.getType();	
		GZKindEntity all = new GZKindEntity();	
		all.setGZKind("全部");
		all.setID("11");
		list.add(0, all);
		return list;
		//return gZKindDAO.getType();
	}

	//Web-查询
	public List<GZApplyInfoEntity> wGetSearchInfo(GZSearchInfoDTO gzSearchInfoDTO) {

				String departname = gzSearchInfoDTO.getDepartmentName();// 获得部门名称
				String gzkind = gzSearchInfoDTO.getGzKind();
				//gzSearchInfoDTO.setSendTo("%" + gzSearchInfoDTO.getSendTo() + "%");
				String user = gzSearchInfoDTO.getUserName();
				String start = gzSearchInfoDTO.getStartTime();
				String end = gzSearchInfoDTO.getEndTime();
				String username = "%"+gzSearchInfoDTO.getUserName()+"%";
				if (departname.equals("全部") && gzkind.equals("全部")) {
					return gZApplyInfoDAO.wGetSearchInfo11(username, start, end);
				} else if (departname.equals("全部") && !gzkind.equals("全部")) {
					return gZApplyInfoDAO.wGetSearchInfo10(gzkind, username, start, end);
				} else if (!departname.equals("全部") && gzkind.equals("全部")) {
					return gZApplyInfoDAO.wGetSearchInfo01(departname, username, start, end);
				} else {
					return gZApplyInfoDAO.wGetSearchInfo00(departname, gzkind, username, start, end);
				}
	}
	//Web-查询
	public List<GZApplyInfoEntity> wGetSearchInfoByPage(GZSearchInfoDTO gzSearchInfoDTO, int s, int e) {

				String departname = gzSearchInfoDTO.getDepartmentName();// 获得部门名称
				String gzkind = gzSearchInfoDTO.getGzKind();
				//gzSearchInfoDTO.setSendTo("%" + gzSearchInfoDTO.getSendTo() + "%");
				String user = gzSearchInfoDTO.getUserName();
				String start = gzSearchInfoDTO.getStartTime();
				String end = gzSearchInfoDTO.getEndTime();
				String username = "%"+gzSearchInfoDTO.getUserName()+"%";
				if (departname.equals("全部") && gzkind.equals("全部")) {
					return gZApplyInfoDAO.wGetSearchInfo11ByPage(username, start, end, s, e);
				} else if (departname.equals("全部") && !gzkind.equals("全部")) {
					return gZApplyInfoDAO.wGetSearchInfo10ByPage(gzkind, username, start, end, s, e);
				} else if (!departname.equals("全部") && gzkind.equals("全部")) {
					return gZApplyInfoDAO.wGetSearchInfo01ByPage(departname, username, start, end, s, e);
				} else {
					return gZApplyInfoDAO.wGetSearchInfo00ByPage(departname, gzkind, username, start, end, s, e);
				}
	}
	public int wGetSearchInfoTotal(GZSearchInfoDTO gzSearchInfoDTO, int s, int e){
		String departname = gzSearchInfoDTO.getDepartmentName();// 获得部门名称
		String gzkind = gzSearchInfoDTO.getGzKind();
		//gzSearchInfoDTO.setSendTo("%" + gzSearchInfoDTO.getSendTo() + "%");
		String user = gzSearchInfoDTO.getUserName();
		String start = gzSearchInfoDTO.getStartTime();
		String end = gzSearchInfoDTO.getEndTime();
		String username = "%"+gzSearchInfoDTO.getUserName()+"%";
		if (departname.equals("全部") && gzkind.equals("全部")) {
			return gZApplyInfoDAO.wGetSearchInfo11Total(username, start, end);
		} else if (departname.equals("全部") && !gzkind.equals("全部")) {
			return gZApplyInfoDAO.wGetSearchInfo10Total(gzkind, username, start, end);
		} else if (!departname.equals("全部") && gzkind.equals("全部")) {
			return gZApplyInfoDAO.wGetSearchInfo01Total(departname, username, start, end);
		} else {
			return gZApplyInfoDAO.wGetSearchInfo00Total(departname, gzkind, username, start, end);
		}
	}

	//Web-公章信息导出
	public int export(String[] nlist, String filePath) {
			// 得到数据集合
		List<GZApplyInfoEntity> gzList = new ArrayList<GZApplyInfoEntity>();
		gzList = getPrivateListByNum(nlist);
		return exportExcel(gzList, filePath);
		}

	private List<GZApplyInfoEntity> getPrivateListByNum(String[] nlist) {
		List<GZApplyInfoEntity> list = gZApplyInfoDAO.getAll();// 最终返回的list
//		for (int t = 0;t<nlist.length;t++)//根据number取出要导出的信息
//		{
//			GZApplyInfoEntity p = new GZApplyInfoEntity();
//			p = gZApplyInfoDAO.getGZById(nlist[t]);
//			list.add(p);
//		}
		return list;
	}

	private int exportExcel(List<GZApplyInfoEntity> gzList, String filePath) {
		XSSFWorkbook workbook;
		String sheetName = "公章使用信息";
		try {
			workbook = new XSSFWorkbook();
			// 添加一个sheet,sheet名
			XSSFSheet sheet = workbook.createSheet(sheetName);
			// 合并单元格 参数意思是 第一行、最后一行、第一列、最后一列
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 10));
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
			cell.setCellValue("申请人");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(4);// 第5列
			cell.setCellValue("申请事由");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(5);// 第6列
			cell.setCellValue("申请时间");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(6);// 第7列
			cell.setCellValue("发往单位");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(7);// 第8列
			cell.setCellValue("公章类型");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(8);// 第9列
			cell.setCellValue("用印份数");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(9);// 第10列
			cell.setCellValue("是否涉密");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(10);// 第11列
			cell.setCellValue("审批人");
			cell.setCellStyle(style);			
			
			// 表头完成------------------
			int index = 1;// 行号，应从第三行开始，每次循环进行++
			GZApplyInfoEntity gz = new GZApplyInfoEntity();
			// 遍历集合将数据写到excel中
			if (gzList.size() > 0) {
				for (int i = 0; i < gzList.size(); i++) {
					// 行号++，2开始
					index++;
					
					// 对象
					gz = gzList.get(i);
					
					// 得到数据行数
					int hs = gzList.size();
					
					// 生成相应的行数
					XSSFRow row = sheet.createRow(index);
					
					XSSFCell rowCell = row.createCell(0);// 第1列(序号)
					rowCell.setCellValue(i + 1 + "");
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(1);// 第2列(公章-审批单号)
					rowCell.setCellValue(gz.getID());
					rowCell.setCellStyle(style);

					rowCell = row.createCell(2);// 第3列(公章-部门)
					rowCell.setCellValue(gz.getDepartment());
					rowCell.setCellStyle(style);

					rowCell = row.createCell(3);// 第4列(公章-申请人)
					rowCell.setCellValue(gz.getApplyUserName());
					rowCell.setCellStyle(style);

					rowCell = row.createCell(4);// 第5列(公章-申请事由)
					rowCell.setCellValue(gz.getReason());
					rowCell.setCellStyle(style);

					rowCell = row.createCell(5);// 第6列(公章-申请时间)
					rowCell.setCellValue(gz.getApplyTime());
					rowCell.setCellStyle(style);

					rowCell = row.createCell(6);// 第7列(公章-发往单位)
					rowCell.setCellValue(gz.getSendTo());
					rowCell.setCellStyle(style);

					rowCell = row.createCell(7);// 第8列(公章-公章类型)
					rowCell.setCellValue(gz.getGZKind());
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(8);// 第9列(公章-用印分数)
					rowCell.setCellValue(gz.getCopies());
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(9);// 第10列(公章-是否涉密)
					rowCell.setCellValue(gz.getIsSecret());
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(10);// 第11列(公章-审批人)
					rowCell.setCellValue(gz.getApproveMan());
					rowCell.setCellStyle(style);
					
					
				}
					//index=index+1; 
				}
			//}
			
			// 将文件保存到指定位置
			FileOutputStream out = new FileOutputStream(filePath);
			workbook.write(out);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}     
	}
	

}
