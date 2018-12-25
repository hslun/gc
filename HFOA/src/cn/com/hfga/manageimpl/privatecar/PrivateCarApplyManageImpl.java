package cn.com.hfga.manageimpl.privatecar;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
import cn.com.hfga.entity.privatecar.PrivateCarEntity;
import cn.com.hfga.entity.privatecar.PrivateCarInvoiceEntity;
import cn.com.hfga.entity.user.DepartmentEntity;
import cn.com.hfga.manager.privatecar.PrivateCarApplyManage;
import cn.com.hfga.util.CommonUtil;

/**
 * 
 * @author xinyc	
 * @since 2014-11-17
 * 公车申请信息接口实现类
 */
@Service("privateCarApplyManage")
public class PrivateCarApplyManageImpl implements PrivateCarApplyManage{


	@Autowired
	private PrivateCarApplyDAO  privateCarApplyDAO;
	
	@Autowired
	private PrivateCarInvoiceDAO  privateCarInvoiceDAO;
	
	@Autowired
	private DepartmentDAO departmentDAO;
	
	@Transactional
	@Override
	public String getDateTime() {
	    Date dtDate=new Date();
		SimpleDateFormat sm=new SimpleDateFormat("yyyyMMdd");
		return sm.format(dtDate);
	}
	/* 
	 * 产生申请的Id
	 */
	@Transactional
	@Override
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
	
	
    
    @Transactional
	@Override
	public int Save(PrivateCarApplyDTO pto) {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	pto.setStatus("待审批");
    	pto.setIfpass("未报销");
    	pto.setApplyid(CreateId(pto.getDepartment()));
    	pto.setApplytime(CommonUtil.getInstance().getTime());
    	if(pto.getIfbefore()==null){
    		pto.setIfbefore("0");
    		pto.setBeforedate("");
		}
    	String bDate = pto.getBeforedate();
    	String aDate = pto.getUsercartime();
    	if("1".equals(pto.getIfbefore())){
    		Date bDate1;
    		Date aDate1;
			try {
				bDate1 = sdf.parse(bDate);
				aDate1 = sdf.parse(aDate);
				String newBDate = sdf.format(bDate1);
				String newADate = sdf.format(aDate1);
				if(newADate.equals(newBDate)){
					pto.setIfbefore("0");
		    		pto.setBeforedate("");
	    		}
			} catch (ParseException e) {
				e.printStackTrace();
			}
    	}
		return privateCarApplyDAO.insertEntity(pto.getApplyid(),
				pto.getDepartment(), pto.getApplyman(), 
				pto.getUsercartime(), pto.getReason(), 
				pto.getBeginaddress(), pto.getPassaddress(), 
				pto.getStatus(), pto.getDestination(),
				pto.getSinglelength(), pto.getSurelength(),
				pto.getCountlength(), pto.getApproveman(),
				pto.getApplytime(),pto.getWaymodel(),
				pto.getWaydetail(),pto.getDoublelength(),
				pto.getEndlength(),pto.getIfpass(),
				pto.getIfbefore(),pto.getBeforedate());
	}
    
    //审批通过
    @Transactional
	@Override
	public int Approve(ApproveDTO adt) {
    	adt.setApprovetime(CommonUtil.getInstance().getTime());
		return privateCarApplyDAO.Approve("已通过",adt.getApproveman(),adt.getApplyid(),adt.getApprovetime());
	}
    
    //审批否决
    @Transactional
	@Override
	public int Deny(ApproveDTO adt) {
		adt.setApprovetime(CommonUtil.getInstance().getTime());
		return privateCarApplyDAO.Approve("已否决",adt.getApproveman(),adt.getApplyid(),adt.getApprovetime());
	}
    
    @Transactional
	@Override
	public int Sure(String applyId) {
		return privateCarApplyDAO.Sure("已确认", applyId,CommonUtil.getInstance().getTime());
	}
    
    //领导获取待审批列表
    @Transactional
	@Override
	public List<PrivateCarApplyEntity> getApprove(getApproveDTO getDTO) {
		return privateCarApplyDAO.getApproveInfo("待审批", getDTO.getDepartment(),getDTO.getApproveman());
	}
    
    //个人获取已通过且待执行的列表
    @Transactional
	@Override
  	public List<PrivateCarApplyEntity> getPersonalReady(String applyman) {
  		return privateCarApplyDAO.getPersonalReady(applyman);
  	}
    
    @Transactional
	@Override
	public List<PrivateCarApplyEntity> getSure(getApproveDTO getDTO) {
		return privateCarApplyDAO.getSureInfo("待确认", getDTO.getDepartment(), getDTO.getApproveman());
	}
    
    @Transactional
	@Override
	public List<PrivateCarApplyEntity> getUseDetailInfo(PrivateCarUseDetailDTO pdt) {
		String applyman=pdt.getApplyman();
		String department=pdt.getDepatment();
		//人是全部
		if("".equals(applyman)){
			if("全部".equals(department)){
				return privateCarApplyDAO.getDetail11(pdt.getStarttime(),pdt.getEndtime());		
			}
			else{
				return privateCarApplyDAO.getDetail01(pdt.getStarttime(),pdt.getEndtime(), pdt.getDepatment());
			}
		}
		else{
			if(!"全部".equals(department)){
				return privateCarApplyDAO.getDetail00(pdt.getStarttime(),pdt.getEndtime(), pdt.getApplyman(), pdt.getDepatment());
			}
			else{
				return privateCarApplyDAO.getDetail10(pdt.getStarttime(), pdt.getEndtime(), pdt.getApplyman());
			}
		}
		
	}
    
    @Transactional
	@Override
	public List<Map<String, Object>> getCollectInfo(PrivateCarUseDetailDTO pdt) {
    	List<Map<String, Object>> o = new ArrayList<Map<String,Object>>();
		List<Object[]> list = new ArrayList<Object[]>();
		String department=pdt.getDepatment();
		String applyman=pdt.getApplyman();
		
		if("".equals(applyman)){
			if("全部".equals(department)){
				list=privateCarApplyDAO.getCollectInfo4(pdt.getStarttime(), pdt.getEndtime());
			}
			else{
				list=privateCarApplyDAO.getCollectInfo3(department, pdt.getStarttime(), pdt.getEndtime());
			}
		}
		else{
			if("全部".equals(department)){
				list=privateCarApplyDAO.getCollectInfo2(applyman,pdt.getStarttime(), pdt.getEndtime());
			}
			else{
				list=privateCarApplyDAO.getCollectInfo1(applyman, department,pdt.getStarttime(), pdt.getEndtime());
			}
		}
		if (list.size() != 0) {
			o = new ArrayList<Map<String, Object>>();
			for (Object[] ob : list) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("department", ob[0]);
				map.put("applyman", ob[1]);
				map.put("singleLength", ob[2]);
				map.put("times", ob[3]);
				o.add(map);
			}
		} else {
			//首先获取所有部门 如果为空就直接根据部门获取就行了
			List<DepartmentEntity> ds=departmentDAO.findAll();
			for (DepartmentEntity ob : ds) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("department", ob.getDepartmentName());
				map.put("applyman", "无");
				map.put("singleLength",0);
				map.put("times", 0);
				o.add(map);
			}
		}

		return o;
	}


    
    @Transactional
  	@Override
  	public List<PrivateCarApplyEntity> getPersonal(String  applyman) {
  		return privateCarApplyDAO.getPersonal("已确认",applyman);
  	}
    
    @Transactional
	@Override
	public int delPersonal(String applyid) {
		return privateCarApplyDAO.delPersonal(applyid);
	}
    
    //修改个人申请信息
    @Transactional
	@Override
	public int modifyEntity(PrivateCarApplyDTO pto) {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	if(pto.getIfbefore()==null){
    		pto.setIfbefore("0");
    		pto.setBeforedate("");
		}
    	String bDate = pto.getBeforedate();
    	String aDate = pto.getUsercartime();
    	if("1".equals(pto.getIfbefore())){
    		Date bDate1;
    		Date aDate1;
			try {
				bDate1 = sdf.parse(bDate);
				aDate1 = sdf.parse(aDate);
				String newBDate = sdf.format(bDate1);
				String newADate = sdf.format(aDate1);
				if(newADate.equals(newBDate)){
					pto.setIfbefore("0");
		    		pto.setBeforedate("");
	    		}
			} catch (ParseException e) {
				e.printStackTrace();
			}
    	}
		return privateCarApplyDAO.modifyEntity(pto.getApplyid(),
				pto.getDepartment(), pto.getApplyman(), 
				pto.getUsercartime(), pto.getReason(),
				pto.getBeginaddress(), pto.getPassaddress(), 
				pto.getDestination(), pto.getSinglelength(), 
				pto.getSurelength(), pto.getCountlength(),
				pto.getApproveman(),pto.getWaymodel(),
				pto.getWaydetail(),pto.getDoublelength(),
				pto.getEndlength(),pto.getIfbefore(),
				pto.getBeforedate());
	}
	@Override
	public PrivateCarApplyEntity getOne(String applyid) {
		return privateCarApplyDAO.getOne(applyid);
	}
	
	//已执行
	@Transactional
	@Override
	public int setPerformed(String applyid) {
		return privateCarApplyDAO.setIfPerform("已执行",applyid);
	}
	
	//未执行
	@Transactional
	@Override
	public int setUnperformed(String applyid) {
		return privateCarApplyDAO.setIfPerform("未执行",applyid);
	}
	
	//撤销
	@Transactional
	@Override
	public int deleteApprove(String applyid) {
		return privateCarApplyDAO.delPersonal(applyid);
	}
	
	//获取个人已通过列表
	public List<PrivateCarApplyEntity> getPersonalApprove(String applyman) {
		return privateCarApplyDAO.getPersonalApprove(applyman);
	}
	
	//根据部门、姓名、开始时间、结束时间查询
	@Transactional
	@Override
	public List<PrivateCarApplyEntity> getSearchInfo(PrivateCarSearchDTO privateCarSearchDTO) {
		String department = privateCarSearchDTO.getDepartment();
		String a= privateCarSearchDTO.getApplyman();
		String startTime = privateCarSearchDTO.getStartTime();
		String endTime = privateCarSearchDTO.getEndTime();
		String applyman= "%"+a+"%";
		List<PrivateCarApplyEntity> list = new ArrayList<PrivateCarApplyEntity>();
		if(department.equals("全部")){
			list = privateCarApplyDAO.getSearchInfo1(applyman,startTime,endTime);
		}
		else{
			list = privateCarApplyDAO.getSearchInfo(department,applyman,startTime,endTime);
		}
		
		return list;
	}
	
	//个人获取待审批和被否决的列表
	@Transactional
	@Override
	public List<PrivateCarApplyEntity> getUnapproved(String applyman) {
		return privateCarApplyDAO.getUnapproved(applyman);
	}
	
	//Web-获取私车公用相关信息
	@Override
	public List<PrivateCarApplyEntity> carDisplay(int start, int number) {
		List<PrivateCarApplyEntity> nl = new ArrayList<PrivateCarApplyEntity>();
		List<PrivateCarApplyEntity> list = privateCarApplyDAO.carDisplay(start,number);
		for(PrivateCarApplyEntity p:list){
			String pass = p.getPassAddress();
			if(pass!=null&&!"".equals(pass)&&pass.startsWith("[")){
				try {
					JSONArray json = new JSONArray(pass);
					int iSize = json.length();
					if(iSize!=0){
						String newPass = "";
						for(int i=0;i<iSize;i++){
							JSONObject obj = json.getJSONObject(i);
							newPass += obj.get("addressName")+":"+obj.get("addressValue")+" ";
							p.setPassAddress(newPass);
						}
					}else{
						p.setPassAddress("");
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			nl.add(p);
		}
		return nl;
	}
	
	//Web-获取私车公用相关信息（已审核）
	@Override
	public List<PrivateCarApplyEntity> carDisplay1(int start, int number) {
		return privateCarApplyDAO.carDisplay1(start,number);
	}
	
	//Web-获取私车公用相关信息（未审核）
	@Override
	public List<PrivateCarApplyEntity> uncarDisplay(int start, int number) {
		return privateCarApplyDAO.uncarDisplay(start,number);
	}
	
	//Web-获取私车公用信息总条数
	public int getAllCount() {
		return privateCarApplyDAO.getAllCount();
	}
	
	//Web-获取私车公用信息总条数
	public int getAllCount1() {
		return privateCarApplyDAO.getAllCount1();
	}
	
	//Web-获取私车公用信息总条数
	public int ungetAllCount() {
		return privateCarApplyDAO.ungetAllCount();
	}
	
	//Web-查询-获得私车公用相关信息
	public List<PrivateCarApplyEntity> searchPrivate(int start, int number, PrivateCarSearchDTO privateCarSearchDTO) {
		String department = privateCarSearchDTO.getDepartment();
		String app = privateCarSearchDTO.getApplyman();
		String applyman = "%"+app+"%";
		String startTime = privateCarSearchDTO.getEndTime();
		String endTime = privateCarSearchDTO.getStartTime();
		if(department.equals("全部")){
			return privateCarApplyDAO.searchPrivate(start,number,applyman,startTime,endTime);
		}
		else{
			return privateCarApplyDAO.searchPrivateD(start,number,department,applyman,startTime,endTime);
		}

	}
	
	//Web-查询-获得私车公用总条数
	public int getSearchAllCount(PrivateCarSearchDTO privateCarSearchDTO) {
		String department = privateCarSearchDTO.getDepartment();
		String app = privateCarSearchDTO.getApplyman();
		String applyman = "%"+app+"%";
		String endTime = privateCarSearchDTO.getEndTime();
		String startTime = privateCarSearchDTO.getStartTime();
		if(department.equals("全部")){
			return privateCarApplyDAO.getSearchAllCount(applyman,endTime,startTime);
		}
		else{
			return privateCarApplyDAO.getSearchAllCountD(department,applyman,endTime,startTime);
		}
	}
	
	//导出私车公用信息
	public int export(String[] nlist, String filePath) {
		// 得到数据集合
		List<PrivateCarApplyEntity> privateList = new ArrayList<PrivateCarApplyEntity>();
		privateList = getPrivateListByNum(nlist);
		return exportExcel(privateList, filePath);
	}
	
	//获得相关的私车公用信息
	private List<PrivateCarApplyEntity> getPrivateListByNum(String[] nlist) {
		List<PrivateCarApplyEntity> list = privateCarApplyDAO.getAllPassed();// 最终返回的list
//		for (int t = 0;t<nlist.length;t++)//根据number取出要导出的信息
//		{
//			PrivateCarApplyEntity p = new PrivateCarApplyEntity();
//			p = privateCarApplyDAO.getPrivateById(nlist[t]);
//			if(p!=null&&!"".equals(p)){
//				list.add(p);
//			}
//		}
		return list;
	}
	

	private int exportExcel(List<PrivateCarApplyEntity> privateList, String filePath) {
		// 创建一个工作簿
		XSSFWorkbook workbook;
		String sheetName = "私车公用信息";
		try {
			workbook = new XSSFWorkbook();
			// 添加一个sheet,sheet名
			XSSFSheet sheet = workbook.createSheet(sheetName);
			// 合并单元格 参数意思是 第一行、最后一行、第一列、最后一列
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 15));
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
			sheet.autoSizeColumn(16);
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
			cell = rowHeader.createCell(11);// 第12列
			cell.setCellValue("申请人");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(12);// 第13列
			cell.setCellValue("审批人");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(13);// 第14列
			cell.setCellValue("申请时间");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(14);// 第15列
			cell.setCellValue("审批时间");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(15);// 第16列
			cell.setCellValue("执行状况");
			cell.setCellStyle(style);
			
			// 表头完成------------------
			int index = 1;// 行号，应从第三行开始，每次循环进行++
			PrivateCarApplyEntity priv = new PrivateCarApplyEntity();
			// 遍历集合将数据写到excel中
			if (privateList.size() > 0) {
				for (int i = 0; i < privateList.size(); i++) {
					// 行号++，2开始
					index++;
					
					// 对象
					priv = privateList.get(i);
					
					// 得到数据行数
					int hs = privateList.size();
					
					// 生成相应的行数
					//for(int r=0; r<hs; r++){
					//XSSFRow row = sheet.createRow(r+index);
					XSSFRow row = sheet.createRow(index);
					/*// 合并对应行
					for(int j=0; j < 16; j++){
					sheet.addMergedRegion(new CellRangeAddress(index, index+hs-1, j, j));//首行、最后一行、首列、最后一列
					}*/
					
					XSSFCell rowCell = row.createCell(0);// 第1列(序号)
					rowCell.setCellValue(i + 1 + "");
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
					rowCell.setCellValue(priv.getPassAddress());
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
					
					rowCell = row.createCell(11);// 第12列(私车-申请人)
					rowCell.setCellValue(priv.getApplyMan());
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(12);// 第13列(私车-审批人)
					rowCell.setCellValue(priv.getApproveMan());
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(13);// 第14列(私车-申请时间)
					rowCell.setCellValue(priv.getApplyTime());
					rowCell.setCellStyle(style);
				  	
					rowCell = row.createCell(14);// 第15列(私车-审批时间)
					rowCell.setCellValue(priv.getApproveTime());
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(15);// 第16列(私车-执行状况)
					rowCell.setCellValue(priv.getIfPerform());
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
	/*	//Web-已审核招待明细-导出表单
	@Transactional
	@Override
	public int export(String[] nlist,String path) {
		// 得到数据集合
		List<EntertainInfoEntity> entertainList = new ArrayList<EntertainInfoEntity>();
		entertainList = getEntertainListByNum(nlist);
			return exportExcel(entertainList, path);
		}*/
	@Transactional
	@Override
	public int importPrivateCarExcel(String fileName) {
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
			PrivateCarApplyEntity pricar = new PrivateCarApplyEntity();
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
				
				pricar.setDepartment(s[0]);
				pricar.setUserCarTime(s[1]);
				pricar.setReason(s[2]);
				pricar.setBeginAddress(s[3]);
				pricar.setPassAddress(s[4]);
				pricar.setDestination(s[5]);
				pricar.setStatus(s[6]);
				pricar.setSingleLength(Double.parseDouble(s[7]));
				pricar.setCountLength(Double.parseDouble(s[8]));
				pricar.setSureLength(Double.parseDouble(s[8])*3);
				pricar.setApplyMan(s[10]);
				pricar.setApproveMan(s[11]);
				pricar.setApplyTime(s[12]);
				pricar.setApproveTime(s[13]);
				pricar.setIfPerform(s[14]);
				pricar.setApplyId(s[15]);
//				pricar.setInvoiceSum(s[15]);
//				pricar.setVoucherNum(s[16]);
				//根据用车日期，申请人，申请时间，判断库中是否存在该数据
//				int d = privateCarApplyDAO.getPrivateBy3(pricar.getUserCarTime(), pricar.getApplyTime(), pricar.getApplyMan());
//				if(d==0){
					flag = privateCarApplyDAO.insertEntity1(pricar.getApplyId(), pricar.getDepartment(), pricar.getApplyMan(), pricar.getUserCarTime(), 
							pricar.getReason(), pricar.getBeginAddress(), pricar.getPassAddress(), pricar.getStatus(), pricar.getDestination(), 
							pricar.getSingleLength(), pricar.getSureLength(), pricar.getCountLength(), pricar.getApproveMan(), pricar.getApplyTime(), 
							pricar.getApproveTime(),pricar.getWayModel(), pricar.getWayDetail());
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
	@Override
	public List<PrivateCarApplyEntity> getApplyList(String username) {
//		PrivateCarEntity en = new PrivateCarEntity();
		List<PrivateCarApplyEntity> carList = privateCarApplyDAO.getApplyListUnSub(username);
//		List<PrivateCarInvoiceEntity> inList = privateCarInvoiceDAO.selectBackList(username);
//		en.setCar(carList);
//		en.setInvoice(inList);
		return carList;
	}
	@Override
	public int updatePrivateCarIfPass(String applyid){
		return privateCarApplyDAO.updatePrivateCarIfPass(applyid);
	}
	@Transactional
	@Override
	public int updatePrivateCarIfPassed(String applyid) {
		String applyids = privateCarInvoiceDAO.selectByApplyId(applyid).getApplyIds();
		String[] ids = applyids.split(",");
		for(String id:ids){
			int j = privateCarApplyDAO.updatePrivateCarIfPassed(id);
			if(j!=1){
				return 0;
			}
		}
		return privateCarInvoiceDAO.updateStatus(applyid);
	}
	/*@Transactional
	@Override
	public Object updatePrivateCarIfUnPass(String parentid, String applyid) {
		String applyids = privateCarInvoiceDAO.selectByApplyId(parentid).getApplyIds();
		String[] ids = applyids.split(",");
		List<String> list = Arrays.asList(ids);
		String newids="";
		if(list.contains(applyid)){
			List<String> arrayList=new ArrayList<String>(list);//转换为ArrayLsit调用相关的remove方法
	        arrayList.remove(applyid);
	        for(int i=0;i<arrayList.size();i++){
	        	if(i==arrayList.size()-1){
	        		newids+=arrayList.get(i);
	        	}else{
	        		newids+=arrayList.get(i)+",";
	        	}
	        }
		}
		if(newids==null || "".equals(newids)){
			privateCarInvoiceDAO.deleteByApplyid(parentid);
		}else{
			privateCarInvoiceDAO.updateApplyIds(newids, parentid);
		}
		
		privateCarApplyDAO.updatePrivateCarIfUnPass(applyid);
		
		String[] ids1 = newids.split(",");
		List<PrivateCarApplyEntity> list1 = new ArrayList<PrivateCarApplyEntity>();
		for(String id:ids1){
			PrivateCarApplyEntity p = privateCarApplyDAO.getOne(id);
			if(p!=null&&!"".equals(p)){
				list1.add(p);
			}
		}
		return list1;
	}*/

	@Transactional
	@Override
	public Object updatePrivateCarIfUnPass(String parentid, String applyid) {
		String applyids = privateCarInvoiceDAO.selectByApplyId(parentid).getApplyIds();
		String[] ids = applyids.split(",");
		List<String> list = Arrays.asList(ids);
		String newids="";
		if(list.contains(applyid)){
			List<String> arrayList=new ArrayList<String>(list);//转换为ArrayLsit调用相关的remove方法
	        arrayList.remove(applyid);
	        for(int i=0;i<arrayList.size();i++){
	        	if(i==arrayList.size()-1){
	        		newids+=arrayList.get(i);
	        	}else{
	        		newids+=arrayList.get(i)+",";
	        	}
	        }
		}
		if(newids==null || "".equals(newids)){
			privateCarInvoiceDAO.deleteByApplyid(parentid);
		}else{
			privateCarInvoiceDAO.updateApplyIds(newids, parentid);
		}
		
		privateCarApplyDAO.updatePrivateCarIfUnPass(applyid);
		
//		privateCarInvoiceDAO.updateStatusBack(parentid);
		
		String[] ids1 = newids.split(",");
		List<PrivateCarApplyEntity> list1 = new ArrayList<PrivateCarApplyEntity>();
		for(String id:ids1){
			PrivateCarApplyEntity p = privateCarApplyDAO.getOne(id);
			if(p!=null&&!"".equals(p)){
				list1.add(p);
			}
		}
		return list1;
	}
	
	/**
	 * 提交报销前更新私车信息
	 */
	@Transactional
	@Override
	public int update(PrivateCarApplyDTO pto){
		return privateCarApplyDAO.update(pto.getApplyid(), pto.getDepartment(),
				pto.getApplyman(), pto.getUsercartime(), 
				pto.getReason(), pto.getBeginaddress(), 
				pto.getPassaddress(), pto.getDestination(), 
				pto.getSinglelength(), pto.getSurelength(), 
				pto.getCountlength(), pto.getApproveman(), 
				pto.getDoublelength(), pto.getEndlength(), 
				pto.getApprovetime(),pto.getWaymodel(),
				pto.getWaydetail());
	}

	/**
	 * 提交报销前更新私车信息
	 */
	@Transactional
	@Override
	public int updatePrivateCarStatusBack(PrivateCarApplyDTO pto){
		pto.setStatus("待审批");
		pto.setIfperform("待执行");
		pto.setIfpass("未报销");
		return privateCarApplyDAO.updatePrivateCarStatusBack(pto.getApplyid(), pto.getDepartment(),
				pto.getApplyman(), pto.getUsercartime(), 
				pto.getReason(), pto.getBeginaddress(), 
				pto.getPassaddress(), pto.getDestination(), 
				pto.getSinglelength(), pto.getSurelength(), 
				pto.getCountlength(), pto.getApproveman(), 
				pto.getDoublelength(), pto.getEndlength(), 
				pto.getApprovetime(),pto.getStatus(),
				pto.getIfperform(),pto.getIfpass(),
				pto.getWaymodel(),pto.getWaydetail());
	}
	/**
	 * 驳回多条申请
	 */
	 @Transactional
	 public Object backPrivateCar(String applyid)
	 {
	   int flag = 0;
	   flag = privateCarApplyDAO.updatePrivateCarIfUnPass(applyid);
	   privateCarApplyDAO.updatePrivateCarUnIfSub(applyid);
	   
	   String id = "%" + applyid + "%";
	   PrivateCarInvoiceEntity en = this.privateCarInvoiceDAO.selectByApplyIds(id);
	   
	
	   int z = 0;
	   PrivateCarApplyEntity car = new PrivateCarApplyEntity();
	   List<PrivateCarApplyEntity> list1 = new ArrayList();
	   if (en != null)
	   {
	     String[] ids = en.getApplyIds().split(",");
	     String pid = en.getApplyId();
	     for (String i : ids)
	     {
	       car = privateCarApplyDAO.getOne(i);
	       if ((car != null) && 
	         ("已驳回".equals(car.getIfPass()))) {
	         z++;
	       }
	     }
	     if (z == ids.length) {
	       this.privateCarInvoiceDAO.deleteByApplyid(pid);
	     }
	     for (String i : ids)
	     {
	       PrivateCarApplyEntity p = privateCarApplyDAO.getOnePassNew(i);
	       if ((p != null) && (!"".equals(p))) {
	         list1.add(p);
	       }
	     }
	   }
	   return Integer.valueOf(flag);
	 }
	/**
	 * 通过多条申请
	 */
	 @Transactional
	  public Object passPrivateCar(String applyid, String applyids)
	  {
	    String[] ids = applyids.split(",");
	    PrivateCarInvoiceEntity en = privateCarInvoiceDAO.selectByApplyId(applyid);
	    if (applyids.length() == en.getApplyIds().length())
	    {
	      for (String id : ids) {
	        if ((id != null) && (!"".equals(id))) {
	          privateCarApplyDAO.updatePrivateCarIfPassed(id);
	        }
	      }
	      return Integer.valueOf(this.privateCarInvoiceDAO.updateStatus(applyid));
	    }
	    for (String id : ids) {
	      if ((id != null) && (!"".equals(id)))
	      {
	        privateCarApplyDAO.updatePrivateCarPass(id);
	        privateCarApplyDAO.updatePrivateCarUnIfSub(id);
	      }
	    }
	    return Integer.valueOf(privateCarInvoiceDAO.deleteByApplyid(applyid));
	  }
	  
	  public List<PrivateCarApplyEntity> allPrivateCarDisplay(int start, int number)
	  {
		List<PrivateCarApplyEntity> list = privateCarApplyDAO.allPrivateCarDisplay(start, number);
	    List<PrivateCarApplyEntity> nl = new ArrayList();
	    
	    	    for (PrivateCarApplyEntity p : list)
	    {
	      String pass = p.getPassAddress();
	      if ((pass != null) && (!"".equals(pass)) && (pass.startsWith("["))) {
	        try
	        {
	          JSONArray json = new JSONArray(pass);
	          int iSize = json.length();
	          if (iSize != 0)
	          {
	            String newPass = "";
	            for (int i = 0; i < iSize; i++)
	            {
	              JSONObject obj = json.getJSONObject(i);
	              newPass = newPass + obj.get("addressName") + ":" + obj.get("addressValue") + " ";
	              p.setPassAddress(newPass);
	            }
	          }
	          else
	          {
	            p.setPassAddress("");
	          }
	        }
	        catch (JSONException e)
	        {
	          e.printStackTrace();
	        }
	      }
	      nl.add(p);
	    }
	    return nl;
	  }
	  
	  public int getAllDataCount()
	  {
	    return privateCarApplyDAO.getAllDataCount();
	  }
	  
	  public List<PrivateCarApplyEntity> searchByTime(String startTime, String endTime, int start, int number)
	  {
	    return privateCarApplyDAO.searchByTime(startTime, endTime, start, number);
	  }
	  
	  public List<PrivateCarApplyEntity> searchByDepartment(String department, String startTime, String endTime, int start, int number)
	  {
	    return privateCarApplyDAO.searchByDepartment(department, startTime, endTime, start, number);
	  }
	  
	  public List<PrivateCarApplyEntity> searchByApplyman(String applyMan, String startTime, String endTime, int start, int number)
	  {
	    return privateCarApplyDAO.searchByApplyman(applyMan, startTime, endTime, start, number);
	  }
	  
	  public List<PrivateCarApplyEntity> searchByStatus(String status, String startTime, String endTime, int start, int number)
	  {
	    return privateCarApplyDAO.searchByStatus(status, startTime, endTime, start, number);
	  }
	  
	  public List<PrivateCarApplyEntity> searchByDepartApp(String department, String applyman, String startTime, String endTime, int start, int number)
	  {
	    return privateCarApplyDAO.searchByDepartApp(department, applyman, startTime, endTime, start, number);
	  }
	  
	  public List<PrivateCarApplyEntity> searchByDepartStatus(String department, String status, String startTime, String endTime, int start, int number)
	  {
	    return privateCarApplyDAO.searchByDepartStatus(department, status, startTime, endTime, start, number);
	  }
	  
	  public List<PrivateCarApplyEntity> searchByAppStatus(String applyman, String status, String startTime, String endTime, int start, int number)
	  {
	    return privateCarApplyDAO.searchByAppStatus(applyman, status, startTime, endTime, start, number);
	  }
	  
	  public List<PrivateCarApplyEntity> searchByDepartAppStatus(String department, String applyman, String status, String startTime, String endTime, int start, int number)
	  {
	    return privateCarApplyDAO.searchByDepartAppStatus(department, applyman, status, startTime, endTime, start, number);
	  }
	  
	  public int getCountByTime(String startTime, String endTime)
	  {
	    return privateCarApplyDAO.getCountByTime(startTime, endTime);
	  }
	  
	  public int getCountByDepartment(String department, String startTime, String endTime)
	  {
	    return privateCarApplyDAO.getCountByDepartment(department, startTime, endTime);
	  }
	  
	  public int getCountByApplyman(String applyman, String startTime, String endTime)
	  {
	    return privateCarApplyDAO.getCountByApplyman(applyman, startTime, endTime);
	  }
	  
	  public int getCountByStatus(String status, String startTime, String endTime)
	  {
	    return privateCarApplyDAO.getCountByStatus(status, startTime, endTime);
	  }
	  
	  public int getCountByDepartApp(String department, String applyman, String startTime, String endTime)
	  {
	    return privateCarApplyDAO.getCountByDepartApp(department, applyman, startTime, endTime);
	  }
	  
	  public int getCountByDepartStatus(String department, String status, String startTime, String endTime)
	  {
	    return privateCarApplyDAO.getCountByDepartStatus(department, status, startTime, endTime);
	  }
	  
	  public int getCountByAppStatus(String applyman, String status, String startTime, String endTime)
	  {
	    return privateCarApplyDAO.getCountByAppStatus(applyman, status, startTime, endTime);
	  }
	  
	  public int getCountByDepartAppStatus(String department, String applyman, String status, String startTime, String endTime)
	  {
	    return privateCarApplyDAO.getCountByDepartAppStatus(department, applyman, status, startTime, endTime);
	  }
	  @Transactional
	  public int SaveNew(PrivateCarApplyDTO p){
		  p.setApplyid(CreateId(p.getDepartment()));
		  PrivateCarInvoiceDTO pto=new PrivateCarInvoiceDTO();
		  if("待审批".equals(p.getStatus())){
			  p.setApprovetime("");
//			  p.setIfbefore("");
			  p.setIfpass("未报销");
		  }else if("被否决".equals(p.getStatus())){
//			  p.setIfbefore("");
			  p.setIfpass("未报销");
		  }
    	if(p.getIfbefore()==null){
    		p.setIfbefore("0");
    		p.setBeforedate("");
		}
		  if("待执行".equals(p.getIfperform())){
			  p.setIfsub("0");
		  }else{
			  p.setIfsub("1");
		  }
		  if("已通过".equals(p.getStatus())||"已完成".equals(p.getStatus())){
			  if("已执行".equals(p.getIfperform())){
			  //添加到金额表
			  pto.setApplyids(p.getApplyid());
			  pto.setApplyman(p.getApplyman());
			  pto.setSurelength(String.valueOf(p.getSurelength()));
			  String applyid = new SimpleDateFormat("yyyyMMddHHmmssSSS") .format(new Date() );
				pto.setApplyid(applyid);
				pto.setApplytime(p.getSubtime());
				pto.setSum(p.getSum());
				if("未报销".equals(p.getIfpass())){
					pto.setStatus("待审核");
				}else{
					pto.setStatus("已完成");
					pto.setPaidtime(p.getPaidtime());
					pto.setVouchernum(p.getDanhao());
					pto.setApproveman(p.getApproveman2());
				}
				privateCarInvoiceDAO.insertEntity(pto.getApplyid(), pto.getApplyman(), pto.getApproveman(), pto.getApplytime(), pto.getSum(), pto.getSurelength(), pto.getVouchernum(), pto.getStatus(), pto.getPaidtime(), pto.getApplyids());
			  }
			
	}
		  
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    if (p.getIfbefore() == null||"0".equals(p.getIfbefore()))
	    {
	      p.setIfbefore("0");
	      p.setBeforedate("");
	    }
	    String bDate = p.getBeforedate();
	    String aDate = p.getUsercartime();
	    if ("1".equals(p.getIfbefore())) {
	      try
	      {
	        Date bDate1 = sdf.parse(bDate);
	        Date aDate1 = sdf.parse(aDate);
	        String newBDate = sdf.format(bDate1);
	        String newADate = sdf.format(aDate1);
	        if (newADate.equals(newBDate))
	        {
	          p.setIfbefore("0");
	          p.setBeforedate("");
	        }
	      }
	      catch (ParseException e)
	      {
	        e.printStackTrace();
	      }
	    }
	    return privateCarApplyDAO.insertEntityNew(p.getApplyid(), 
	      p.getDepartment(), p.getApplyman(), 
	      p.getUsercartime(), p.getReason(), 
	      p.getBeginaddress(), p.getPassaddress(), 
	      p.getStatus(), p.getDestination(), 
	      p.getSinglelength(), p.getSurelength(), 
	      p.getCountlength(), p.getApproveman(), 
	      p.getApplytime(), p.getWaymodel(), 
	      p.getWaydetail(), p.getDoublelength(), 
	      p.getEndlength(), p.getIfpass(), 
	      p.getIfbefore(), p.getBeforedate(),p.getIfperform(),p.getApprovetime(),p.getIfsub(),p.getSum(),p.getPaidtime(),p.getApproveman2(),p.getDanhao(),p.getSubtime());
	  }
	  //删除时涉及到的修改
	@Transactional
	public void updateForDelete(PrivateCarInvoiceEntity carinvoice) {
		privateCarInvoiceDAO.updateEntity(carinvoice.getApplyId(), carinvoice.getApplyMan(), carinvoice.getApproveMan(), carinvoice.getApplyTime(), carinvoice.getSum(), carinvoice.getSureLength(), carinvoice.getVoucherNum(), carinvoice.getStatus(), carinvoice.getPaidTime(), carinvoice.getApplyIds());
	}
	  //财务修改私车申请信息
	@Transactional
	public int updateNew(PrivateCarApplyDTO p) {
		 PrivateCarInvoiceEntity carinvoice = privateCarInvoiceDAO.getByNumber("%" + p.getApplyid() + "%");
		 PrivateCarInvoiceDTO pto=new PrivateCarInvoiceDTO();
		 if("待审批".equals(p.getStatus())){
			  p.setApprovetime("");
			  p.setIfperform("");
			  p.setIfpass("未报销");
			  p.setApproveman("");
			  p.setSubtime("");
			  p.setSum("");
			  p.setPaidtime("");
			  p.setApproveman2("");
			  p.setDanhao("");
			  //判断一个审核单号里边有几个审批单号
			  if(carinvoice!=null){
				  if(carinvoice.getApplyIds().split(",").length==1){
					  privateCarInvoiceDAO.deleteByApplyid(carinvoice.getApplyId());
				  }else if(carinvoice.getApplyIds().split(",").length>1){
					  String applyIds="";
					  if(carinvoice.getApplyIds().split(",")[0].equals(p.getApplyid())){
						  applyIds=carinvoice.getApplyIds().replace(p.getApplyid()+",","");
					  }else{
						  applyIds=carinvoice.getApplyIds().replace(","+p.getApplyid(),"");
					  }
					  carinvoice.setApplyIds(applyIds);
				  }
				  privateCarInvoiceDAO.updateEntity(carinvoice.getApplyId(), carinvoice.getApplyMan(),carinvoice.getApproveMan(), carinvoice.getApplyTime(), carinvoice.getSum(), carinvoice.getSureLength(), carinvoice.getVoucherNum(), carinvoice.getStatus(), carinvoice.getPaidTime(),carinvoice.getApplyIds());
			  }
		  }else if("被否决".equals(p.getStatus())){
			  p.setIfperform("");
			  p.setIfpass("未报销");
			  p.setApproveman("");
			  p.setSubtime("");
			  p.setSum("");
			  p.setPaidtime("");
			  p.setApproveman2("");
			  p.setDanhao("");
			  if(carinvoice!=null){
				  if(carinvoice.getApplyIds().split(",").length==1){
					  privateCarInvoiceDAO.deleteByApplyid(carinvoice.getApplyId());
				  }else if(carinvoice.getApplyIds().split(",").length>1){
					  String applyIds="";
					  if(carinvoice.getApplyIds().split(",")[0].equals(p.getApplyid())){
						  applyIds=carinvoice.getApplyIds().replace(p.getApplyid()+",","");
					  }else{
						  applyIds=carinvoice.getApplyIds().replace(","+p.getApplyid(),"");
					  }
					  carinvoice.setApplyIds(applyIds);
				  }
				  privateCarInvoiceDAO.updateEntity(carinvoice.getApplyId(), carinvoice.getApplyMan(),carinvoice.getApproveMan(), carinvoice.getApplyTime(), carinvoice.getSum(), carinvoice.getSureLength(), carinvoice.getVoucherNum(), carinvoice.getStatus(), carinvoice.getPaidTime(),carinvoice.getApplyIds());
			  }
			  
		  }else if("已通过".equals(p.getStatus())||"已完成".equals(p.getStatus())){
			  if("待执行".equals(p.getIfperform())){
				  p.setIfsub("0");
				  p.setDanhao("");
				  p.setSum("");
				  p.setApproveman2("");
				  p.setPaidtime("");
				  p.setSubtime("");
				  p.setIfpass("未报销");
				  if(carinvoice!=null){
					  if(carinvoice.getApplyIds().split(",").length==1){
						  privateCarInvoiceDAO.deleteByApplyid(carinvoice.getApplyId());
					  }else if(carinvoice.getApplyIds().split(",").length>1){
						  String applyIds="";
						  if(carinvoice.getApplyIds().split(",")[0].equals(p.getApplyid())){
							  applyIds=carinvoice.getApplyIds().replace(p.getApplyid()+",","");
						  }else{
							  applyIds=carinvoice.getApplyIds().replace(","+p.getApplyid(),"");
						  }
						  carinvoice.setApplyIds(applyIds);
					  }
					  privateCarInvoiceDAO.updateEntity(carinvoice.getApplyId(), carinvoice.getApplyMan(),carinvoice.getApproveMan(), carinvoice.getApplyTime(), carinvoice.getSum(), carinvoice.getSureLength(), carinvoice.getVoucherNum(), carinvoice.getStatus(), carinvoice.getPaidTime(),carinvoice.getApplyIds());
				  }
				 
			  }else{
				  if("未报销".equals(p.getIfpass())){
					  p.setPaidtime("");
					  p.setApproveman2("");
					  p.setDanhao("");
				  }
				  p.setIfsub("1");
				  
				  
				  //添加到金额表
				  if(carinvoice==null){
					  pto.setApplyids(p.getApplyid());
					  pto.setApplyman(p.getApplyman());
					  pto.setSurelength(String.valueOf(p.getSurelength()));
					  pto.setApproveman(p.getApproveman2());
					  String applyid = new SimpleDateFormat("yyyyMMddHHmmssSSS") .format(new Date() );
					pto.setApplyid(applyid);
					pto.setApplytime(p.getSubtime());
					pto.setSum(p.getSum());
					if("未报销".equals(p.getIfpass())){
						pto.setStatus("待审核");
					}else{
						pto.setStatus("已完成");
						pto.setPaidtime(p.getPaidtime());
						pto.setVouchernum(p.getDanhao());
					}
					privateCarInvoiceDAO.insertEntity(pto.getApplyid(), pto.getApplyman(), pto.getApproveman(), pto.getApplytime(), pto.getSum(), pto.getSurelength(), pto.getVouchernum(), pto.getStatus(), pto.getPaidtime(), pto.getApplyids());
				  }else{
					//判断该单号下所有
					 String[] split = carinvoice.getApplyIds().split(",");
					 if(split.length>=1&&p.getDanhao()!=null&&!"".equals(p.getDanhao())){
						 //全修改
						 Double sum=0.0;
						 for(int i=0;i<split.length;i++){
							 PrivateCarApplyEntity one = privateCarApplyDAO.getOne(split[i]);
							 if(!one.getApplyId().equals(p.getApplyid())){
								 sum+=one.getSureLength();
							 }
							 if(one.getApplyId().equals(p.getApplyid())){
								 one.setSureLength(Double.parseDouble(p.getSum()));
							 }
							 privateCarApplyDAO.updateNew(one.getApplyId(), one.getDepartment(), p.getApplyman(), one.getUserCarTime(), one.getReason(), one.getBeginAddress(), one.getPassAddress(), one.getStatus(), one.getDestination(), one.getSingleLength(), one.getSureLength(), one.getCountLength(), one.getApproveMan(), one.getWayModel(), one.getWayDetail(), one.getDoubleLength(), one.getEndLength(), one.getIfPass(), one.getIfBefore(), one.getBeforeDate(), one.getIfPerform(), one.getApproveTime(), one.getIfSub(), p.getSum(), p.getPaidtime(), p.getApproveman2(), p.getDanhao(),p.getSubtime(),one.getApplyTime());
						 }
						 sum+=Double.parseDouble(p.getSum());
						  pto.setApplyids(carinvoice.getApplyIds());
						  pto.setApplyman(p.getApplyman());
						  pto.setSurelength(String.valueOf(p.getSurelength()));
						  pto.setApproveman(p.getApproveman2());
						  pto.setApplyid(carinvoice.getApplyId());
						  pto.setApplytime(p.getSubtime());
						  pto.setSum(String.valueOf(sum));
						if("未报销".equals(p.getIfpass())){
							pto.setStatus("待审核");
						}else{
							pto.setStatus("已完成");
							pto.setPaidtime(p.getPaidtime());
							pto.setVouchernum(p.getDanhao());
							}
						privateCarInvoiceDAO.updateEntity(pto.getApplyid(), p.getApplyman(), pto.getApproveman(), pto.getApplytime(), pto.getSum(), pto.getSurelength(), pto.getVouchernum(), pto.getStatus(), pto.getPaidtime(), pto.getApplyids()); 
					 }else if(split.length>1&&(p.getDanhao()==null||"".equals(p.getDanhao()))){
						 String applyIds="";
						  if(carinvoice.getApplyIds().split(",")[0].equals(p.getApplyid())){
							  applyIds=carinvoice.getApplyIds().replace(p.getApplyid()+",","");
						  }else{
							  applyIds=carinvoice.getApplyIds().replace(","+p.getApplyid(),"");
						  }
						  carinvoice.setApplyIds(applyIds);
						  privateCarInvoiceDAO.updateEntity(carinvoice.getApplyId(), carinvoice.getApplyMan(), carinvoice.getApproveMan(), carinvoice.getApplyTime(), carinvoice.getSum(), carinvoice.getSureLength(), carinvoice.getVoucherNum(), carinvoice.getStatus(), carinvoice.getPaidTime(), carinvoice.getApplyIds());
						  pto.setApplyids(p.getApplyid());
						  pto.setApplyman(p.getApplyman());
						  pto.setSurelength(String.valueOf(p.getSurelength()));
						  pto.setApproveman(p.getApproveman2());
						  pto.setApplyid(carinvoice.getApplyId());
						  pto.setApplytime(p.getSubtime());
						  pto.setSum(p.getSum());
						if("未报销".equals(p.getIfpass())){
							pto.setStatus("待审核");
						}else{
							pto.setStatus("已完成");
							pto.setPaidtime(p.getPaidtime());
							if(carinvoice.getVoucherNum()==null||"".equals(carinvoice.getVoucherNum())){
								pto.setVouchernum(p.getDanhao());
							}}
						privateCarInvoiceDAO.insertEntity(pto.getApplyid(), pto.getApplyman(), pto.getApproveman(), pto.getApplytime(), pto.getSum(), pto.getSurelength(), pto.getVouchernum(), pto.getStatus(), pto.getPaidtime(), pto.getApplyids()); 
					 }
					  
				  } 
				  
				  
			  }
		  }
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
//		  if("待执行".equals(p.getIfperform())){
//			  p.setIfsub("0");
//			  p.setDanhao("");
//			  p.setSum("");
//			  p.setApproveman2("");
//			  p.setPaidtime("");
//			  p.setSubtime("");
//			  p.setIfpass("未报销");
//			  if(carinvoice!=null){
//				  if(carinvoice.getApplyIds().split(",").length==1){
//					  privateCarInvoiceDAO.deleteByApplyid(carinvoice.getApplyId());
//				  }else if(carinvoice.getApplyIds().split(",").length>1){
//					  String applyIds="";
//					  if(carinvoice.getApplyIds().split(",")[0].equals(p.getApplyid())){
//						  applyIds=carinvoice.getApplyIds().replace(p.getApplyid()+",","");
//					  }else{
//						  applyIds=carinvoice.getApplyIds().replace(","+p.getApplyid(),"");
//					  }
//					  carinvoice.setApplyIds(applyIds);
//				  }
//				  privateCarInvoiceDAO.updateEntity(carinvoice.getApplyId(), carinvoice.getApplyMan(),carinvoice.getApproveMan(), carinvoice.getApplyTime(), carinvoice.getSum(), carinvoice.getSureLength(), carinvoice.getVoucherNum(), carinvoice.getStatus(), carinvoice.getPaidTime(),carinvoice.getApplyIds());
//			  }
//			 
//		  }else{
//			  if("未报销".equals(p.getIfpass())){
//				  p.setPaidtime("");
//				  p.setApproveman2("");
//				  p.setDanhao("");
//			  }
//			  p.setIfsub("1");
//			  
//		  }
		  
//		  PrivateCarInvoiceDTO pto=new PrivateCarInvoiceDTO();
//		  if("已通过".equals(p.getStatus())&&"已执行".equals(p.getIfperform())){
//			  //添加到金额表
//			  if(carinvoice==null){
//				  pto.setApplyids(p.getApplyid());
//				  pto.setApplyman(p.getApplyman());
//				  pto.setSurelength(String.valueOf(p.getSurelength()));
//				  pto.setApproveman(p.getApproveman2());
//				  String applyid = new SimpleDateFormat("yyyyMMddHHmmssSSS") .format(new Date() );
//				pto.setApplyid(applyid);
//				pto.setApplytime(p.getSubtime());
//				pto.setSum(p.getSum());
//				if("未报销".equals(p.getIfpass())){
//					pto.setStatus("待审核");
//				}else{
//					pto.setStatus("已完成");
//					pto.setPaidtime(p.getPaidtime());
//					pto.setVouchernum(p.getDanhao());
//				}
//				privateCarInvoiceDAO.insertEntity(pto.getApplyid(), pto.getApplyman(), pto.getApproveman(), pto.getApplytime(), pto.getSum(), pto.getSurelength(), pto.getVouchernum(), pto.getStatus(), pto.getPaidtime(), pto.getApplyids());
//			  }else{
//				//判断该单号下所有
//				 String[] split = carinvoice.getApplyIds().split(",");
//				 if(split.length>=1&&p.getDanhao()!=null&&!"".equals(p.getDanhao())){
//					 //全修改
//					 for(int i=0;i<split.length;i++){
//						 PrivateCarApplyEntity one = privateCarApplyDAO.getOne(split[i]);
//					     privateCarApplyDAO.updateNew(one.getApplyId(), one.getDepartment(), p.getApplyman(), one.getUserCarTime(), one.getUserCarTime(), one.getBeginAddress(), one.getPassAddress(), one.getStatus(), one.getDestination(), one.getSingleLength(), one.getSureLength(), one.getCountLength(), one.getApproveMan(), one.getWayModel(), one.getWayDetail(), one.getDoubleLength(), one.getEndLength(), one.getIfPass(), one.getIfBefore(), one.getBeforeDate(), one.getIfPerform(), one.getApproveTime(), one.getIfSub(), p.getSum(), p.getPaidtime(), p.getApproveman2(), p.getDanhao(),p.getSubtime(),one.getApplyTime());
//					 }
//					  pto.setApplyids(carinvoice.getApplyIds());
//					  pto.setApplyman(p.getApplyman());
//					  pto.setSurelength(String.valueOf(p.getSurelength()));
//					  pto.setApproveman(p.getApproveman2());
//					  pto.setApplyid(carinvoice.getApplyId());
//					  pto.setApplytime(p.getSubtime());
//					  pto.setSum(p.getSum());
//					if("未报销".equals(p.getIfpass())){
//						pto.setStatus("待审核");
//					}else{
//						pto.setStatus("已完成");
//						pto.setPaidtime(p.getPaidtime());
//						pto.setVouchernum(p.getDanhao());
//						}
//					privateCarInvoiceDAO.updateEntity(pto.getApplyid(), p.getApplyman(), pto.getApproveman(), pto.getApplytime(), pto.getSum(), pto.getSurelength(), pto.getVouchernum(), pto.getStatus(), pto.getPaidtime(), pto.getApplyids()); 
//				 }else if(split.length>1&&(p.getDanhao()==null||"".equals(p.getDanhao()))){
//					 String applyIds="";
//					  if(carinvoice.getApplyIds().split(",")[0].equals(p.getApplyid())){
//						  applyIds=carinvoice.getApplyIds().replace(p.getApplyid()+",","");
//					  }else{
//						  applyIds=carinvoice.getApplyIds().replace(","+p.getApplyid(),"");
//					  }
//					  carinvoice.setApplyIds(applyIds);
//					  privateCarInvoiceDAO.updateEntity(carinvoice.getApplyId(), carinvoice.getApplyMan(), carinvoice.getApproveMan(), carinvoice.getApplyTime(), carinvoice.getSum(), carinvoice.getSureLength(), carinvoice.getVoucherNum(), carinvoice.getStatus(), carinvoice.getPaidTime(), carinvoice.getApplyIds());
//					  pto.setApplyids(p.getApplyid());
//					  pto.setApplyman(p.getApplyman());
//					  pto.setSurelength(String.valueOf(p.getSurelength()));
//					  pto.setApproveman(p.getApproveman2());
//					  pto.setApplyid(carinvoice.getApplyId());
//					  pto.setApplytime(p.getSubtime());
//					  pto.setSum(p.getSum());
//					if("未报销".equals(p.getIfpass())){
//						pto.setStatus("待审核");
//					}else{
//						pto.setStatus("已完成");
//						pto.setPaidtime(p.getPaidtime());
//						if(carinvoice.getVoucherNum()==null||"".equals(carinvoice.getVoucherNum())){
//							pto.setVouchernum(p.getDanhao());
//						}}
//					privateCarInvoiceDAO.insertEntity(pto.getApplyid(), pto.getApplyman(), pto.getApproveman(), pto.getApplytime(), pto.getSum(), pto.getSurelength(), pto.getVouchernum(), pto.getStatus(), pto.getPaidtime(), pto.getApplyids()); 
//				 }
//				  
//			  }
//
//	}
		return privateCarApplyDAO.updateNew(p.getApplyid(), 
			      p.getDepartment(), p.getApplyman(), 
			      p.getUsercartime(), p.getReason(), 
			      p.getBeginaddress(), p.getPassaddress(), 
			      p.getStatus(), p.getDestination(), 
			      p.getSinglelength(), p.getSurelength(), 
			      p.getCountlength(), p.getApproveman(), 
			      p.getWaymodel(),p.getWaydetail(), p.getDoublelength(), 
			      p.getEndlength(), p.getIfpass(), 
			      p.getIfbefore(), p.getBeforedate(),p.getIfperform(),p.getApprovetime(),p.getIfsub(),p.getSum(),p.getPaidtime(),p.getApproveman2(),p.getDanhao(),p.getSubtime(),p.getApplytime());
	}
	//修改私车申请信息
	@Transactional
	public void updatePrivateCar(PrivateCarApplyDTO p) {
		privateCarApplyDAO.updateNew(p.getApplyid(), 
			      p.getDepartment(), p.getApplyman(), 
			      p.getUsercartime(), p.getReason(), 
			      p.getBeginaddress(), p.getPassaddress(), 
			      p.getStatus(), p.getDestination(), 
			      p.getSinglelength(), p.getSurelength(), 
			      p.getCountlength(), p.getApproveman(), 
			      p.getWaymodel(),p.getWaydetail(), p.getDoublelength(), 
			      p.getEndlength(), p.getIfpass(), 
			      p.getIfbefore(), p.getBeforedate(),p.getIfperform(),p.getApprovetime(),p.getIfsub(),p.getSum(),p.getPaidtime(),p.getApproveman2(),p.getDanhao(),p.getSubtime(),p.getApplytime());
		
	}
	}
