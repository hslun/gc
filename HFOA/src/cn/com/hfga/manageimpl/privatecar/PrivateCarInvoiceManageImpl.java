package cn.com.hfga.manageimpl.privatecar;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.com.hfga.dao.privatecar.PrivateCarApplyDAO;
import cn.com.hfga.dao.privatecar.PrivateCarInvoiceDAO;
import cn.com.hfga.dao.user.DepartmentDAO;
import cn.com.hfga.dto.privatecar.ApproveDTO;
import cn.com.hfga.dto.privatecar.PrivateCarApplyDTO;
import cn.com.hfga.dto.privatecar.PrivateCarInvoiceDTO;
import cn.com.hfga.dto.privatecar.PrivateCarSearchDTO;
import cn.com.hfga.dto.privatecar.PrivateCarUseDetailDTO;
import cn.com.hfga.dto.privatecar.getApproveDTO;
import cn.com.hfga.entity.entertain.EntertainApplyInfoEntity;
import cn.com.hfga.entity.entertain.EntertainInfoEntity;
import cn.com.hfga.entity.privatecar.PrivateCarApplyEntity;
import cn.com.hfga.entity.privatecar.PrivateCarInvoiceEntity;
import cn.com.hfga.entity.user.DepartmentEntity;
import cn.com.hfga.manager.privatecar.PrivateCarApplyManage;
import cn.com.hfga.manager.privatecar.PrivateCarInvoiceManage;
import cn.com.hfga.util.CommonUtil;

/**
 * 
 * @author ysy
 *
 */
@Service("privateCarInvoiceManage")
public class PrivateCarInvoiceManageImpl implements PrivateCarInvoiceManage{


	@Autowired
	private PrivateCarInvoiceDAO  privateCarInvoiceDAO;
	
	@Autowired
	private PrivateCarApplyDAO  privateCarApplyDAO;
	
	@Autowired
	private DepartmentDAO departmentDAO;

	@Transactional
	@Override
	public Object Save(PrivateCarInvoiceDTO pto) {
		String applyids = pto.getApplyids();
		String[] ids = applyids.split(",");
		for(String id:ids){
			PrivateCarApplyEntity en = privateCarApplyDAO.getOne(id);
			if("已驳回".equals(en.getIfPass())){
				privateCarApplyDAO.updatePrivateCarUnIfPass(id);
			}
			//更新提交的私车状态为已提交
			privateCarApplyDAO.updatePrivateCarIfSub(id);
//			int j = privateCarApplyDAO.updatePrivateCarIfPass(id);
		}
		
		String applyid = new SimpleDateFormat("yyyyMMddHHmmssSSS") .format(new Date() );
		pto.setApplyid(applyid);
		pto.setApplytime(CommonUtil.getInstance().getTime());
		pto.setStatus("待审核");
		int i = privateCarInvoiceDAO.insertEntity(
				pto.getApplyid(), pto.getApplyman(), 
				pto.getApproveman(), pto.getApplytime(), 
				pto.getSum(), pto.getSurelength(), 
				pto.getVouchernum(), pto.getStatus(), 
				pto.getPaidtime(), pto.getApplyids());
		
		List<PrivateCarApplyEntity> list = privateCarApplyDAO.getApplyListUnSub(pto.getApplyman());
		if(i>0){
			return list;
		}
		return 0;
	}

	@Override
	public List<PrivateCarInvoiceEntity> selectAllUnPass() {
		return privateCarInvoiceDAO.selectAllUnPass();
	}


	@Override
	public List<PrivateCarInvoiceEntity> selectAllPassed() {
		return privateCarInvoiceDAO.selectAllPassed();
	}
	

	/**
	 * 
	 */
	@Transactional
	@Override
	public int updateInvoice(PrivateCarInvoiceDTO pto) {
		return privateCarInvoiceDAO.modifyInvoice(pto.getApplyid(), pto.getApproveman(), 
				pto.getVouchernum(), pto.getPaidtime());
	}


	/**
	 * 查询一条记录
	 */
	@Override
	public PrivateCarInvoiceEntity selectByApplyId(String applyid) {
		return privateCarInvoiceDAO.selectByApplyId(applyid);
	}
	
	@Override
    public String getApplyidsByid(String id){
    	return privateCarInvoiceDAO.selectByApplyId(id).getApplyIds();
    }
	
	/**
	 * 根据凭单id查询所有私车信息
	 */
	@Override
    public List<PrivateCarApplyEntity> selectChildByApplyId(String applyid){
		PrivateCarInvoiceEntity en = privateCarInvoiceDAO.selectByApplyId(applyid);
		String applyids="";
		if(en!=null){
			applyids = en.getApplyIds();
		}
		String[] ids = applyids.split(",");
		List<PrivateCarApplyEntity> list = new ArrayList<PrivateCarApplyEntity>();
		for(String id:ids){
			PrivateCarApplyEntity p = privateCarApplyDAO.getOneWaitForPassNew(id);
			if(p!=null&&!"".equals(p)){
				list.add(p);
			}
		}
		return list;
    }
	
	@Override
    public List<PrivateCarApplyEntity> selectChildByApplyIdFinish(String applyid){
		PrivateCarInvoiceEntity en = privateCarInvoiceDAO.selectByApplyId(applyid);
		String applyids="";
		if(en!=null){
			applyids = en.getApplyIds();
		}
		String[] ids = applyids.split(",");
		List<PrivateCarApplyEntity> list = new ArrayList<PrivateCarApplyEntity>();
		for(String id:ids){
			PrivateCarApplyEntity p = privateCarApplyDAO.getOnePassNew1(id);
			if(p!=null&&!"".equals(p)){
				list.add(p);
			}
		}
		return list;
    }

	@Override
    public List<PrivateCarApplyEntity> selectChildByApplyIdFinish1(String applyid){
		PrivateCarInvoiceEntity en = privateCarInvoiceDAO.selectByApplyId(applyid);
		String applyids="";
		if(en!=null){
			applyids = en.getApplyIds();
		}
		String[] ids = applyids.split(",");
		List<PrivateCarApplyEntity> list = new ArrayList<PrivateCarApplyEntity>();
		for(String id:ids){
			PrivateCarApplyEntity p = privateCarApplyDAO.getOnePassNew(id);
			if(p!=null&&!"".equals(p)){
				list.add(p);
			}
		}
		return list;
    }
	/**
	 * 查询所有已完成凭单
	 */
	@Override
	public List<PrivateCarInvoiceEntity> selectPassed() {
		return privateCarInvoiceDAO.selectPessed();
	}
	/**
	 * 查询已报销和已完成记录
	 */
	@Override
    public List<PrivateCarInvoiceEntity> invoiceDisplay(int start,int number){
    	return privateCarInvoiceDAO.invoiceDisplay(start, number);
    }
	/**
	 * 查询已报销和已完成记录数 
	 */
	@Override
    public int getAllCount(){
    	return privateCarInvoiceDAO.getAllCount();
    }
	/**
	 * 查询所有未登记
	 */
	@Override
	public List<PrivateCarInvoiceEntity> unSignInvoiceDisplay(int start, int number) {
		return privateCarInvoiceDAO.UnSignInvoiceDisplay(start, number);
	}
	/**
	 * 查询所有未登记记录数 
	 */
	@Override
	public int getAllUnSignInvoiceCount() {
		return privateCarInvoiceDAO.getAllUnSignInvoiceCount();
	}

	/**
	 * 查询待报销记录
	 */
	@Override
    public List<PrivateCarInvoiceEntity> uninvoiceDisplay(int start,int number){
    	return privateCarInvoiceDAO.uninvoiceDisplay(start, number);
    }
	/**
	 * 查询待报销记录数
	 */
	@Override
    public int ungetAllCount(){
    	return privateCarInvoiceDAO.ungetAllCount();
    }
	//导出私车公用信息
	@Override
	public int export(String[] nlist, String filePath) {
		// 得到数据集合
		List<PrivateCarInvoiceEntity> privateList = new ArrayList<PrivateCarInvoiceEntity>();
		privateList = getPrivateListByNum(nlist);
		return exportExcel(privateList, filePath);
	}
	//获得相关的私车公用信息
	private List<PrivateCarInvoiceEntity> getPrivateListByNum(String[] nlist) {
		List<PrivateCarInvoiceEntity> list = privateCarInvoiceDAO.selectPessed();// 最终返回的list
		return list;
	}
	private int exportExcel(List<PrivateCarInvoiceEntity> privateList, String filePath) {
		// 创建一个工作簿
		XSSFWorkbook workbook;
		String sheetName = "私车公用明细表";
		try {
			workbook = new XSSFWorkbook();
			// 添加一个sheet,sheet名
			XSSFSheet sheet = workbook.createSheet(sheetName);
			// 合并单元格 参数意思是 第一行、最后一行、第一列、最后一列
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 18));
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
			// 设置其他正文单元格格式
			XSSFCellStyle style = workbook.createCellStyle();
			style.setAlignment(HorizontalAlignment.CENTER);
			style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
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
			cell.setCellValue("用车时间");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(4);// 第5列
			cell.setCellValue("事由");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(5);// 第6列
			cell.setCellValue("出发地");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(6);// 第7列
			cell.setCellValue("途径地");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(7);// 第8列
			cell.setCellValue("目的地");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(8);// 第9列
			cell.setCellValue("状态");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(9);// 第10列
			cell.setCellValue("单程里程");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(10);// 第11列
			cell.setCellValue("计价里程");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(11);// 第11列
			cell.setCellValue("核定价格");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(12);// 第12列
			cell.setCellValue("申请人");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(13);// 第13列
			cell.setCellValue("审批人");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(14);// 第14列
			cell.setCellValue("申请时间");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(15);// 第15列
			cell.setCellValue("审批时间");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(16);// 第16列
			cell.setCellValue("执行状况");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(17);// 第16列
			cell.setCellValue("凭票报销金额");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(18);// 第16列
			cell.setCellValue("凭证号");
			cell.setCellStyle(style);
			
			// 表头完成------------------
			int index = 1;// 行号，应从第三行开始，每次循环进行++
			int z = 0;
			PrivateCarInvoiceEntity invoice = new PrivateCarInvoiceEntity();
			// 遍历集合将数据写到excel中
			if (privateList.size() > 0) {
				for (int i = 0; i < privateList.size(); i++) {
					
					// 对象
					invoice = privateList.get(i);
					String[] size = invoice.getApplyIds().split(",");
					// 得到数据行数
					int hs = size.length;
					
					for(String id:size){
						// 行号++，2开始
						index++;
						PrivateCarApplyEntity priv = privateCarApplyDAO.getOne(id);
						//合并审批时间、金额、凭证号
						sheet.addMergedRegion(new CellRangeAddress(index, index+hs-1, 15, 15));//首行、最后一行、首列、最后一列
						sheet.addMergedRegion(new CellRangeAddress(index, index+hs-1, 17, 17));//首行、最后一行、首列、最后一列
						sheet.addMergedRegion(new CellRangeAddress(index, index+hs-1, 18, 18));//首行、最后一行、首列、最后一列
						//将途径地json转为逗号隔开的字符串
						String pass ="";
						if(priv!=null){
							pass = priv.getPassAddress();
						}
						String newPass = "";
						if(pass!=null&&!"".equals(pass)&&pass.startsWith("[")){
							try {
								//将json解析为string
								JSONArray json = new JSONArray(pass);
								int iSize = json.length();
								if(iSize!=0){
									for(int x=0;x<iSize;x++){
										JSONObject obj = json.getJSONObject(x);
										if(x==iSize-1){
											newPass += obj.get("addressName");
										}else{
											newPass += obj.get("addressName")+",";
										}
									}
								}
							} catch (JSONException e) {
								e.printStackTrace();
							}
						}
						
						XSSFRow row = sheet.createRow(index);
						
						if(priv!=null){
							XSSFCell rowCell = row.createCell(0);// 第1列(序号)
							rowCell.setCellValue(z + 1 + "");
							rowCell.setCellStyle(style);
							
							rowCell = row.createCell(1);// 第2列(私车-审批单号)
							rowCell.setCellValue(priv.getApplyId());
							rowCell.setCellStyle(style);
		
							rowCell = row.createCell(2);// 第3列(私车-部门)
							rowCell.setCellValue(priv.getDepartment());
							rowCell.setCellStyle(style);
		
							rowCell = row.createCell(3);// 第4列(私车-用车时间)
							rowCell.setCellValue(priv.getUserCarTime());
							rowCell.setCellStyle(style);
		
							rowCell = row.createCell(4);// 第5列(私车-事由)
							rowCell.setCellValue(priv.getReason());
							rowCell.setCellStyle(style);
		
							rowCell = row.createCell(5);// 第6列(私车-出发地)
							rowCell.setCellValue(priv.getBeginAddress());
							rowCell.setCellStyle(style);
		
							rowCell = row.createCell(6);// 第7列(私车-途径地)
							rowCell.setCellValue(newPass);
							rowCell.setCellStyle(style);
		
							rowCell = row.createCell(7);// 第8列(私车-目的地)
							rowCell.setCellValue(priv.getDestination());
							rowCell.setCellStyle(style);
							
							rowCell = row.createCell(8);// 第9列(私车-状态)
							rowCell.setCellValue(priv.getStatus());
							rowCell.setCellStyle(style);
							
							rowCell = row.createCell(9);// 第10列(私车-单程里程)
							rowCell.setCellValue(priv.getSingleLength());
							rowCell.setCellStyle(style);
							
							rowCell = row.createCell(10);// 第11列(私车-计价里程)
							rowCell.setCellValue(priv.getCountLength());
							rowCell.setCellStyle(style);
							
							rowCell = row.createCell(11);// 第12列(私车-计价里程)
							rowCell.setCellValue(priv.getSureLength());
							rowCell.setCellStyle(style);
							
							rowCell = row.createCell(12);// 第13列(私车-申请人)
							rowCell.setCellValue(priv.getApplyMan());
							rowCell.setCellStyle(style);
							
							rowCell = row.createCell(13);// 第14列(私车-审批人)
							rowCell.setCellValue(priv.getApproveMan());
							rowCell.setCellStyle(style);
							
							rowCell = row.createCell(14);// 第15列(私车-申请时间)
							rowCell.setCellValue(priv.getApplyTime());
							rowCell.setCellStyle(style);
							
							rowCell = row.createCell(15);// 第16列(私车-审批时间)
							rowCell.setCellValue(invoice.getPaidTime());
							rowCell.setCellStyle(style);
							
							rowCell = row.createCell(16);// 第17列(私车-执行状况)
							rowCell.setCellValue(priv.getIfPerform());
							rowCell.setCellStyle(style);
							
							rowCell = row.createCell(17);// 第18列(私车-执行状况)
							rowCell.setCellValue(invoice.getSum());
							rowCell.setCellStyle(style);
							
							rowCell = row.createCell(18);// 第19列(私车-执行状况)
							rowCell.setCellValue(invoice.getVoucherNum());
							rowCell.setCellStyle(style);
							z++;
						}
					}
					
//					XSSFRow row = sheet.createRow(index);
					// 合并对应行
//					for(int j=0; j < 18; j++){
//						sheet.addMergedRegion(new CellRangeAddress(index, index+hs-1, j, j));//首行、最后一行、首列、最后一列
//					}
					
//					XSSFCell rowCell = row.createCell(0);// 第1列(序号)
//					rowCell.setCellValue(i + 1 + "");
//					rowCell.setCellStyle(style);
					

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
	@Transactional
	@Override
	public int importPrivateCarInvoiceExcel(String fileName) {
		// 文件流
		int flag = 0;
		InputStream inputStream;
		try {
			inputStream = new FileInputStream(fileName);
			// 创建XSSFWorkbook对象
			XSSFWorkbook xssFWorkbook = new XSSFWorkbook(inputStream);
			// 读取工作薄第一个sheet里的内容，规定第一个sheet里面为信息
			XSSFSheet sheet = xssFWorkbook.getSheetAt(0);
			// 定义行对象
			XSSFRow row;
			// 获取总共多少行
			int rowCount = sheet.getPhysicalNumberOfRows();
			// 获取每一行有多少列，以第二行的列数减一位基准，因为第二行有提示用户的注意事项
			int cellCount = sheet.getRow(1).getPhysicalNumberOfCells() ;
			// 每一行为一个对象
//			PrivateCarApplyEntity pricar = new PrivateCarApplyEntity();
			PrivateCarInvoiceEntity in = new PrivateCarInvoiceEntity();
			// 循环遍历每一行，从第三行开始才为数据，所以i从2开始
			for (int i = 1; i < rowCount; i++) {
				// 获取每一行
				row = sheet.getRow(i);
				// 定义一个单元格变量
				XSSFCell cell = null;
				// 单元格对象的值
				String cellValue = null;
				// 单元格值的类型
				int cellType;
				// 定义一个存放一行数据的字符串
				String[] s = new String[cellCount];
				// 对每一行进行遍历，由于第一行为行号，不用添加到数据库，所以从第二行开始遍历
				for (int j = 0; j < cellCount; j++) {
					cell = row.getCell(j);
					// 获取每一行的单元格
					if(cell!=null){
						cellType = cell.getCellType();
						switch (cellType) {
						case Cell.CELL_TYPE_STRING:// 文本类型
							cellValue = cell.getStringCellValue();
							break;
						case Cell.CELL_TYPE_NUMERIC:// 如果是数字的话首先也要转换为文本类型
							cellValue = String.valueOf((int) cell.getNumericCellValue());
							break;
						}
					}
					// 将内容存放到字符串数组内
					s[j] = cellValue;
				}
				// 将获取到的一行数据的值转换为PrivateCarApplyEntity对象
				
				in.setApplyMan(s[0]);
				in.setApproveMan(s[1]);
				in.setApplyTime(s[2]);
				in.setPaidTime(s[3]);
				in.setSum(s[4]);
				in.setVoucherNum(s[5]);
				in.setStatus("已完成");
				in.setApplyId(s[6]);
				in.setApplyIds(s[7]);
				
				flag = privateCarInvoiceDAO.insertEntity(
						in.getApplyId(), in.getApplyMan(), 
						in.getApproveMan(), in.getApplyTime(), 
						in.getSum(), in.getSureLength(), 
						in.getVoucherNum(), in.getStatus(), 
						in.getPaidTime(), in.getApplyIds());
				
//				pricar.setInvoiceSum(s[15]);
//				pricar.setVoucherNum(s[16]);
				//根据用车日期，申请人，申请时间，判断库中是否存在该数据
//				int d = privateCarApplyDAO.getPrivateBy3(pricar.getUserCarTime(), pricar.getApplyTime(), pricar.getApplyMan());
//				if(d==0){
//					flag = privateCarApplyDAO.insertEntity1(pricar.getApplyId(), pricar.getDepartment(), pricar.getApplyMan(), pricar.getUserCarTime(), 
//							pricar.getReason(), pricar.getBeginAddress(), pricar.getPassAddress(), pricar.getStatus(), pricar.getDestination(), 
//							pricar.getSingleLength(), pricar.getSureLength(), pricar.getCountLength(), pricar.getApproveMan(), pricar.getApplyTime(), 
//							pricar.getApproveTime(),pricar.getInvoiceSum(), pricar.getVoucherNum());
//				}
				// 如果没有保存成功返回0则直接退出
//				if (flag == 0) {
//					return flag;
//				}
			}
//			xssFWorkbook.close();// 关闭
			return flag;
		} catch (Exception e) {
			e.printStackTrace();
			return flag;
		}
	}
	public String CreateId(String department) {
		//String maxId="";
		String resultString="";
		
		List<Object> i= privateCarApplyDAO.getMaxId(department,getDateTime()+"%");
		resultString=getDateTime()+"0"+String.valueOf(departmentDAO.getByName(department).get(0).getID())+"001";
		
		if (i.get(0)==null) {
			return resultString;
		}
		else {
			String idString=i.get(0).toString();
			System.out.print(i.size());
			int id1=Integer.valueOf(idString.substring(idString.length()-3,idString.length() ));
			String idsubString=idString.substring(0, idString.length()-3);
			return idsubString.concat(addZero(id1)) ;
		}
	}
	//生成id最大的后三位
	public  String addZero(int id)
	{
		String reString=String.valueOf(id+1);
		if((id+1)<10)
		{
			reString="00"+reString;
		}
		else if((id+1)>=10&&id<100) {
			reString="0"+reString;
		}
		else {
		}
		return reString;
	}
	public String getDateTime() {
	    Date dtDate=new Date();
		SimpleDateFormat sm=new SimpleDateFormat("yyyyMMdd");
		return sm.format(dtDate);
	}
	
	@Override
	public List<PrivateCarInvoiceEntity> selectBackList(String username) {
		return privateCarInvoiceDAO.selectBackList(username);
	}

	//删除
	@Transactional
	@Override
	public int deleteByApplyid(String applyid) {
		return privateCarInvoiceDAO.deleteByApplyid(applyid);
	}
	@Transactional
	 public PrivateCarInvoiceEntity getByNumber(String applyid)
	  {
	    return this.privateCarInvoiceDAO.getByNumber(applyid);
	  }
	 @Transactional
	  public void deleteByApplyIds(String applyids)
	  {
	    this.privateCarInvoiceDAO.deleteByApplyIds(applyids);
	  }

	 //登记
	@Transactional
	@Override
	public Object regist(PrivateCarInvoiceDTO pto) {
		int i = privateCarInvoiceDAO.insertEntity(
				pto.getApplyid(), pto.getApplyman(), 
				pto.getApproveman(), pto.getApplytime(), 
				pto.getSum(), pto.getSurelength(), 
				pto.getVouchernum(), pto.getStatus(), 
				pto.getPaidtime(), pto.getApplyids());
		
		return i;
	}

	@Override
	public PrivateCarInvoiceEntity getByNumberes(String applyids) {
		return privateCarInvoiceDAO.getByNumberes(applyids);
	}
}
