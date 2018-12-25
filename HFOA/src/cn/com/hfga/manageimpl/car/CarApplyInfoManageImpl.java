package cn.com.hfga.manageimpl.car;


import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import cn.com.hfga.dao.car.CarApplyInfoDAO;
import cn.com.hfga.dao.car.CarBaseInfoDAO;
import cn.com.hfga.dto.car.ApproveDTO;
import cn.com.hfga.dto.car.CarApplyInfoDTO;
import cn.com.hfga.dto.car.CarApporveInfoDTO;
import cn.com.hfga.dto.car.CarCollectInfoDTO;
import cn.com.hfga.dto.car.CarKindDTO;
import cn.com.hfga.dto.car.CarUseDetailDTO;
import cn.com.hfga.entity.car.CarApplyInfoEntity;
import cn.com.hfga.entity.entertain.EntertainDepartmentEntity;
import cn.com.hfga.entity.privatecar.PrivateCarApplyEntity;
import cn.com.hfga.manager.car.CarApplyInfoManage;

/**
 * 
 * @author xinyc	
 * @since 2014-11-17
 * 公车申请信息接口实现类
 */
@Service("carApplyInfoManage")
public class CarApplyInfoManageImpl implements CarApplyInfoManage {

	@Autowired
	private  CarApplyInfoDAO carApplyInfoDAO;
	
	@Autowired
	private CarBaseInfoDAO carBaseInfoDAO;
	
	
	@Transactional
	@Override
	public List<CarApplyInfoEntity> getInfo(String ApplyMan) {
		// TODO Auto-generated method stub
		List<CarApplyInfoEntity> list=carApplyInfoDAO.findByApplyMan(ApplyMan);
		return list;
	}
	/**
	 * 
	 * 产生申请的Id
	 */
	@Transactional
	@Override
	public String CreateId(String department) {
		// TODO Auto-generated method stub
		//String maxId="";
		String resultString="";
		
		List<Object> i= carApplyInfoDAO.getMaxId(department,getDateTime()+"%");
		resultString=getDateTime()+"0"+String.valueOf(getDepartmentId(department))+"001";
		
		if (i.get(0)==null) {
			return resultString;
		}
		else {
			String idString=i.get(0).toString();
			System.out.print(i.size());
//			int id=Integer.valueOf(i.get(0).toString().substring(idString.length()-3,idString.length()-1 ))+1;
//			String idsubString=idString.substring(0, idString.length()-4);
//		   //return String.valueOf(Long.valueOf(i.get(0).toString())+1);
//			return idsubString+String.valueOf(id);
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
	public int getDepartmentId(String  department)
	{
		int id=0;
		System.out.print(department);
		if("信息技术部".equals(department)){
			id=7;
		}else if("财务部".equals(department)){
			id=3;
		}else if("综合办公室".equals(department)){
			id=2;
		}else if("通航项目部".equals(department)){
			id=5;
		}else if("机电产品部".equals(department)){
			id=6;
		}else if("无人机项目部".equals(department)){
			id=8;
		}else if("公司领导".equals(department)){
			id=1;
		}else if("市场部".equals(department)){
			id=9;
		}else if("规划投资部".equals(department)){
			id=10;
		}
		return id;
	}
	
	@Transactional
	@Override
	public List<CarApplyInfoEntity> getInofGargeInfo(String state,
			String approvestate) {
		// TODO Auto-generated method stub
		return carApplyInfoDAO.findByInState(state, approvestate);
	}

	@Transactional
	@Override
	public List<CarApplyInfoEntity> getOutofGargeInfo(String approvestate) {
		// TODO Auto-generated method stub
		return carApplyInfoDAO.findByOutState(approvestate);
	}
	/**
	 * 
	 * 增加实体
	 */
	@Transactional
	@Override
	public int  insertCarApplyInfo(CarApplyInfoDTO carApplyInfoDTO) {
		// TODO Auto-generated method stub
		String applyid=CreateId(carApplyInfoDTO.getDepartment());
		String carIdString=String.valueOf(carBaseInfoDAO.getBeginLength(carApplyInfoDTO.getCarcode()).get(0).getID());
		String carcodeString=String.valueOf(carBaseInfoDAO.getBeginLength(carApplyInfoDTO.getCarcode()).get(0).getCarCode());
		
		return carApplyInfoDAO.insert(applyid, carApplyInfoDTO.getDepartment(), 
				carApplyInfoDTO.getApplyusername(),carApplyInfoDTO.getAppplyman(),
				carIdString, 
				carcodeString, 
				carApplyInfoDTO.getCarcode(), carApplyInfoDTO.getDriverString(),String.valueOf(carBaseInfoDAO.getBeginLength(carApplyInfoDTO.getCarcode()).get(0).getCarDistance()), 
				carApplyInfoDTO.getBegintimeplan(), carApplyInfoDTO.getEndtimeplan(), carApplyInfoDTO.getUsecarreason(), carApplyInfoDTO.getApplytime(), "待审批", 
				carApplyInfoDTO.getStartaddress(), carApplyInfoDTO.getEndaddress(), String.valueOf(countplantime(carApplyInfoDTO.getBegintimeplan(),
						carApplyInfoDTO.getEndtimeplan())),carApplyInfoDTO.getComparemannum(),"0",carApplyInfoDTO.getRealapprove(),"0","0","0","已预约",carApplyInfoDTO.getApproveman());
	}
	
	/**
	 * 预约前查询是否归还车辆
	 */
	@Override
	public int selectIfReturn(String applyman){
		return carApplyInfoDAO.selectIfReturn(applyman);
	}
	
	@Transactional
	@Override
	public double countplantime(String startTime, String endTime) {
		// TODO Auto-generated method stub
		startTime+=":00"; endTime+=":00";
		DateFormat dFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		double result=0;
		try {
			Date d1=dFormat.parse(startTime);
			Date d2=dFormat.parse(endTime);
			result=(d2.getTime()-d1.getTime())/(1000*60*60);
			
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	@Transactional
	@Override
	public int ApproveInfo(ApproveDTO approveDTO) {
		// TODO Auto-generated method stub
		return carApplyInfoDAO.updateState("通过",approveDTO.getApproveman(),approveDTO.getApplyid());
	}
	@Transactional
	@Override
	public int outgarage(String ApplyId,String begintime,String lengthbegin) {
		// TODO Auto-generated method stub
		return carApplyInfoDAO.outState("已借出", ApplyId,begintime,lengthbegin);
	}
	@Transactional
	@Override
	public int ingarage(String ApplyId,String endtime,String reallength,String realtime,String lengthend) {
		// TODO Auto-generated method stub
		return carApplyInfoDAO.inState("已归还",endtime,realtime,reallength, ApplyId,lengthend);
	}
	@Transactional
	@Override
	public List<CarApplyInfoEntity> getOIInfo(String State) {
		// TODO Auto-generated method stub
		return carApplyInfoDAO.getOutInfo(State);
	}
	@Transactional
	@Override
	public String getDateTime() {
		// TODO Auto-generated method stub
	    Date dtDate=new Date();
		SimpleDateFormat sm=new SimpleDateFormat("yyyyMMdd");
		return sm.format(dtDate);
	}
	@Transactional
	@Override
	public List<CarApplyInfoEntity> getDetailInfo(CarUseDetailDTO carUseDetailDTO) {
		return carApplyInfoDAO.getDetailInfo1(carUseDetailDTO.getCarinfo(),carUseDetailDTO.getDepatment(), carUseDetailDTO.getStarttime(), carUseDetailDTO.getEndtime(), carUseDetailDTO.getApplyman());
	}
	@Transactional
	@Override
	public List<Object []> getCollectInfo1(
			CarCollectInfoDTO carCollectInfoDTO) {
		return carApplyInfoDAO.getCollectInfo1(carCollectInfoDTO.getCarinfo(), carCollectInfoDTO.getDepartment(), carCollectInfoDTO.getStarttime(), carCollectInfoDTO.getEndtime());
	}
	//部门全部  车不是全部
	@Transactional
	@Override
	public List<Object []> getCollectInfo2(
			CarCollectInfoDTO carCollectInfoDTO) {
		// TODO Auto-generated method stu
		return carApplyInfoDAO.getCollectInfo2(carCollectInfoDTO.getCarinfo(), carCollectInfoDTO.getStarttime(), carCollectInfoDTO.getEndtime());
	}
	@Transactional
	@Override
	public List<Object []> getCollectInfo3(
			CarCollectInfoDTO carCollectInfoDTO) {
		// 
		return carApplyInfoDAO.getCollectInfo3(carCollectInfoDTO.getDepartment(), carCollectInfoDTO.getStarttime(), carCollectInfoDTO.getEndtime());
	}
	@Transactional
	@Override
	public List<Object []> getColectInfo4(
			CarCollectInfoDTO carCollectInfoDTO) {
		// TODO Auto-generated method stub
		return  carApplyInfoDAO.getCollectInfo4(carCollectInfoDTO.getStarttime(), carCollectInfoDTO.getEndtime());
	}
	@Transactional
	@Override
	public List<CarApplyInfoEntity> getApproveInfo1(
			CarApporveInfoDTO carApporveInfoDTO) {
		// TODO Auto-generated method stub
		return carApplyInfoDAO.getApproveInfo1("待审批");
	}
	@Transactional
	@Override
	public List<CarApplyInfoEntity> getApproveInfo2(
			CarApporveInfoDTO carApporveInfoDTO) {
		// TODO Auto-generated method stub
		return carApplyInfoDAO.getApproveInfo2("待审批", carApporveInfoDTO.getDepartment(),carApporveInfoDTO.getUsername());
	}
	
	@Transactional
	@Override
	public List<CarApplyInfoEntity> getApproveInfo3(CarApporveInfoDTO carApporveInfoDTO) {
		// TODO Auto-generated method stub
		return carApplyInfoDAO.getApproveInfo3("待审批",carApporveInfoDTO.getUsername());
	}
	
	@Transactional
	@Override
	public List<CarApplyInfoEntity> getAllApply(String carid) {
		// TODO Auto-generated method stub
		try {
			return carApplyInfoDAO.getAllApply(carid, "通过", "已归还");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	@Transactional
	@Override
	public List<CarApplyInfoEntity> getAllOrder(String carid) {
		// TODO Auto-generated method stub
		return carApplyInfoDAO.getAllOrder(carid, "通过", "已归还", addDay(), delDay());
	}
	@Transactional
	@Override
	public String addDay() {
		// TODO Auto-generated method stub
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		Date beginDate = new Date();
		Calendar date = Calendar.getInstance();
		date.setTime(beginDate);
		date.set(Calendar.DATE, date.get(Calendar.DATE) + 1);
		return dft.format(date.getTime());
	}
	@Transactional
	@Override
	public String delDay() {
		// TODO Auto-generated method stub
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		Date beginDate = new Date();
		Calendar date = Calendar.getInstance();
		date.setTime(beginDate);
		date.set(Calendar.DATE, date.get(Calendar.DATE) -1);
		return dft.format(date.getTime());
	}
	@Transactional
	@Override
	public int denyApprove(ApproveDTO approveDTO) {
		// TODO Auto-generated method stub
		return carApplyInfoDAO.updateState("被否决",approveDTO.getApproveman(),approveDTO.getApplyid());
	}
	@Transactional
	@Override
	public List<CarApplyInfoEntity> getOne(String applyid) {
		// TODO Auto-generated method stub
		return carApplyInfoDAO.getOne(applyid);
	}
	@Transactional
	@Override
	public int modifyInfo(CarApplyInfoDTO carApplyInfoDTO) {
		return carApplyInfoDAO.modifyOne(carApplyInfoDTO.getApplyid(), carApplyInfoDTO.getDepartment(), 
				carApplyInfoDTO.getApplyusername(),
				carApplyInfoDTO.getAppplyman(),
				String.valueOf(carBaseInfoDAO.getBeginLength(carApplyInfoDTO.getCarcode()).get(0).getID()), 
				String.valueOf(carBaseInfoDAO.getBeginLength(carApplyInfoDTO.getCarcode()).get(0).getCarCode()), 
				carApplyInfoDTO.getCarcode(), 
				carApplyInfoDTO.getDriverString(),
				String.valueOf(carBaseInfoDAO.getBeginLength(carApplyInfoDTO.getCarcode()).get(0).getCarDistance()), 
				carApplyInfoDTO.getBegintimeplan(), 
				carApplyInfoDTO.getEndtimeplan(),
				carApplyInfoDTO.getUsecarreason(),
				carApplyInfoDTO.getApplytime(),
				"待审批", 
				carApplyInfoDTO.getStartaddress(), 
				carApplyInfoDTO.getEndaddress(), 
				String.valueOf(countplantime(carApplyInfoDTO.getBegintimeplan(),
						carApplyInfoDTO.getEndtimeplan())),
						carApplyInfoDTO.getComparemannum(),"0","0","0","0","已预约",carApplyInfoDTO.getApproveman());
	}
	@Override
	public List<CarApplyInfoEntity> getAllApplyDetail(String carnum) {
		// TODO Auto-generated method stub
	   return carApplyInfoDAO.getAllApplyDetail(carnum, "通过", "已归还");
	}
	@Override
	public List<CarApplyInfoEntity> getDetail1(CarUseDetailDTO carUseDetailDTO) {
		// TODO Auto-generated method stub
		return carApplyInfoDAO.getDetail1(carUseDetailDTO.getCarinfo(),carUseDetailDTO.getStarttime(), carUseDetailDTO.getEndtime(), carUseDetailDTO.getApplyman());
	}
	@Override
	public List<CarApplyInfoEntity> getDetail2(CarUseDetailDTO carUseDetailDTO) {
		// TODO Auto-generated method stub
		return carApplyInfoDAO.getDetail2(carUseDetailDTO.getStarttime(), carUseDetailDTO.getEndtime(), carUseDetailDTO.getApplyman());
	}
	
	@Transactional
	@Override
	public List<CarApplyInfoEntity> getDetail3(CarUseDetailDTO carUseDetailDTO) {
		// TODO Auto-generated method stub
		return carApplyInfoDAO.getDetail12(carUseDetailDTO.getStarttime(), carUseDetailDTO.getEndtime(), carUseDetailDTO.getApplyman(),carUseDetailDTO.getDepatment());
	}
	
	
	@Transactional
	@Override
	public List<CarApplyInfoEntity> getExist(CarApplyInfoDTO carApplyInfoDTO) {
		// TODO Auto-generated method stub
		return carApplyInfoDAO.getExist(carApplyInfoDTO.getCarcode(),carApplyInfoDTO.getBegintimeplan(),carApplyInfoDTO.getEndtimeplan());
	}
	
	
	@Transactional
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return carApplyInfoDAO.getCount();
	}
	@Override
	public List<CarApplyInfoEntity> getTopOne() {
		// TODO Auto-generated method stub
		return carApplyInfoDAO.getTopOne();
	}
	@Override
	public List<CarApplyInfoEntity> getDetail00(CarUseDetailDTO carUseDetailDTO) {
		// TODO Auto-generated method stub
		return carApplyInfoDAO.getDetail00(carUseDetailDTO.getStarttime(), carUseDetailDTO.getEndtime(), carUseDetailDTO.getDepatment(), carUseDetailDTO.getCarinfo());
	}
	@Override
	public List<CarApplyInfoEntity> getDetail01(CarUseDetailDTO carUseDetailDTO) {
		// TODO Auto-generated method stub
		return carApplyInfoDAO.getDetail01(carUseDetailDTO.getStarttime(), carUseDetailDTO.getEndtime(), carUseDetailDTO.getCarinfo());
	}
	@Override
	public List<CarApplyInfoEntity> getDetail10(CarUseDetailDTO carUseDetailDTO) {
		// TODO Auto-generated method stub
		return carApplyInfoDAO.getDetail10(carUseDetailDTO.getStarttime(), carUseDetailDTO.getEndtime(), carUseDetailDTO.getDepatment());
	}
	@Override
	public List<CarApplyInfoEntity> getDetail11(CarUseDetailDTO carUseDetailDTO) {
		// TODO Auto-generated method stub
		return carApplyInfoDAO.getDetail11(carUseDetailDTO.getStarttime(), carUseDetailDTO.getEndtime());
	}
	@Override
	public List<CarApplyInfoEntity> getAll() {
		// TODO Auto-generated method stub
		return carApplyInfoDAO.getAll();
	}
	@Transactional
	@Override
	public int modifyApplyMan(String applyman, String applyid) {
		// TODO Auto-generated method stub
		return carApplyInfoDAO.modifyApplyMan(applyman, applyid);
	}
	@Transactional
	@Override
	public int modifyLength() {
		// TODO Auto-generated method stub
		return carApplyInfoDAO.modifyAccountLength();
	}
	@Transactional
	@Override
	public int modifyTime() {
		// TODO Auto-generated method stub
		return carApplyInfoDAO.modifyAccountRealTime();
	}
	@Transactional
	@Override
	public int delApply(String applyid) {
		// TODO Auto-generated method stub
		return carApplyInfoDAO.delApply(applyid);
	}
	
	
	//Web-根据部门、申请人、车牌、申请时间查询
	@Override
	public List<CarApplyInfoEntity> getSearchInfo(CarUseDetailDTO carUseDetailDTO) {

		String dep = carUseDetailDTO.getDepatment();
		String department = "%"+carUseDetailDTO.getDepatment()+"%";
		String applyman = "%"+carUseDetailDTO.getApplyman()+"%";
		String carcode = carUseDetailDTO.getCarinfo();
		String start = carUseDetailDTO.getStarttime();
		String end = carUseDetailDTO.getEndtime();
		if(dep.equals("全部")&&carcode.equals("全部")){
			return carApplyInfoDAO.getSearchInfo11(applyman,start,end);
		}
		else if(dep.equals("全部")&&!carcode.equals("全部")){
			return carApplyInfoDAO.getSearchInfo10(applyman,carcode,start,end);
		}
		else if(!dep.equals("全部")&&carcode.equals("全部")){
			return carApplyInfoDAO.getSearchInfo01(department,applyman,start,end);
		}
		else{
			return carApplyInfoDAO.getSearchInfo00(department,applyman,carcode,start,end);
		}
	}
	//Web-根据部门、申请人、车牌、申请时间查询
	public List<CarApplyInfoEntity> getSearchInfoByPage(CarUseDetailDTO carUseDetailDTO,int sta,int number) {

		String dep = carUseDetailDTO.getDepatment();
		String department = "%"+carUseDetailDTO.getDepatment()+"%";
		String applyman = "%"+carUseDetailDTO.getApplyman()+"%";
		String carcode = carUseDetailDTO.getCarinfo();
		String start = carUseDetailDTO.getStarttime();
		String end = carUseDetailDTO.getEndtime();
		if(dep.equals("全部")&&carcode.equals("全部")){
			return carApplyInfoDAO.getSearchInfo11ByPage(applyman,start,end,sta,number);
		}
		else if(dep.equals("全部")&&!carcode.equals("全部")){
			return carApplyInfoDAO.getSearchInfo10ByPage(applyman,carcode,start,end,sta,number);
		}
		else if(!dep.equals("全部")&&carcode.equals("全部")){
			return carApplyInfoDAO.getSearchInfo01ByPage(department,applyman,start,end,sta,number);
		}
		else{
			return carApplyInfoDAO.getSearchInfo00ByPage(department,applyman,carcode,start,end,sta,number);
		}
	}
	
	//Web-显示公车相关信息
	@Override
	public List<CarApplyInfoEntity> carDisplay(int start,int number) {
		return carApplyInfoDAO.carDisplay(start, number);
	}
	
	//Web-获得公车信息的总条数
	@Override
	public int getAllCount() {
		return carApplyInfoDAO.getAllCount();
	}
	
	//Web-获得公车信息的总条数
	@Override
	public List<CarKindDTO> getCarKind(){
		List<CarKindDTO> list=new ArrayList<CarKindDTO>();
		List<Object> a = carBaseInfoDAO.getCarKind();
		CarKindDTO all = new CarKindDTO();
		all.setID("10");all.setKind("全部");
		list.add(0, all);
		for(int i=0;i<=a.size()-1;i++){
			CarKindDTO b = new CarKindDTO();
			b.setID(Integer.toString(i));
			//System.out.println(Integer.toString(i));
			b.setKind(a.get(i).toString());
			list.add(i+1,b);
			//clear(b);
		}
		/*list.add(1,a.get(0).toString());
		list.add(2,);
		list.add(3,);
		list.add(4,);
		list.add(5,);
		list.add(6,);
		list.add(7,);*/
		return list;
	
	
	}
	
	//导出公车使用信息
	public int export(String[] nlist, String filePath) {
		// 得到数据集合
	List<CarApplyInfoEntity> commonList = new ArrayList<CarApplyInfoEntity>();
	commonList = getPrivateListByNum(nlist);
	return exportExcel(commonList, filePath);
	}
	
	
	private int exportExcel(List<CarApplyInfoEntity> commonList, String filePath) {
		XSSFWorkbook workbook;
		String sheetName = "公车使用信息";
		try {
			workbook = new XSSFWorkbook();
			// 添加一个sheet,sheet名
			XSSFSheet sheet = workbook.createSheet(sheetName);
			// 合并单元格 参数意思是 第一行、最后一行、第一列、最后一列
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 20));
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
			sheet.autoSizeColumn(17);
			sheet.autoSizeColumn(18);
			sheet.autoSizeColumn(19);
			sheet.autoSizeColumn(20);
			sheet.autoSizeColumn(21);
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
			cell.setCellValue("审批人");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(5);// 第6列
			cell.setCellValue("车型");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(6);// 第7列
			cell.setCellValue("车牌号");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(7);// 第8列
			cell.setCellValue("驾驶人");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(8);// 第9列
			cell.setCellValue("同行人数");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(9);// 第10列
			cell.setCellValue("出车里程");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(10);// 第11列
			cell.setCellValue("还车里程");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(11);// 第12列
			cell.setCellValue("计费里程");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(12);// 第13列
			cell.setCellValue("计划借车时间");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(13);// 第14列
			cell.setCellValue("计划还车时间");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(14);// 第15列
			cell.setCellValue("出车时间");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(15);// 第16列
			cell.setCellValue("还车时间");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(16);// 第17列
			cell.setCellValue("借车事由");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(17);// 第18列
			cell.setCellValue("出发地");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(18);// 第19列
			cell.setCellValue("目的地");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(19);// 第20列
			cell.setCellValue("计划用车时间");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(20);// 第21列
			cell.setCellValue("实际用车时间");
			cell.setCellStyle(style);
			
			
			// 表头完成------------------
			int index = 1;// 行号，应从第三行开始，每次循环进行++
			CarApplyInfoEntity common = new CarApplyInfoEntity();
			// 遍历集合将数据写到excel中
			if (commonList.size() > 0) {
				for (int i = 0; i < commonList.size(); i++) {
					// 行号++，2开始
					index++;
					
					// 对象
					common = commonList.get(i);
					
					// 得到数据行数
					int hs = commonList.size();
					
					// 生成相应的行数
					XSSFRow row = sheet.createRow(index);
					
					XSSFCell rowCell = row.createCell(0);// 第1列(序号)
					rowCell.setCellValue(i + 1 + "");
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(1);// 第2列(公车-审批单号)
					rowCell.setCellValue(common.getApplyId());
					rowCell.setCellStyle(style);

					rowCell = row.createCell(2);// 第3列(公车-部门)
					rowCell.setCellValue(common.getDepartment());
					rowCell.setCellStyle(style);

					rowCell = row.createCell(3);// 第4列(公车-申请人)
					rowCell.setCellValue(common.getApplyMan());
					rowCell.setCellStyle(style);

					rowCell = row.createCell(4);// 第5列(公车-审批人)
					rowCell.setCellValue(common.getApproveMan());
					rowCell.setCellStyle(style);

					rowCell = row.createCell(5);// 第6列(公车-车型)
					rowCell.setCellValue(common.getCarType());
					rowCell.setCellStyle(style);

					rowCell = row.createCell(6);// 第7列(公车-车牌号)
					rowCell.setCellValue(common.getCarCode());
					rowCell.setCellStyle(style);

					rowCell = row.createCell(7);// 第8列(公车-驾驶人)
					rowCell.setCellValue(common.getDriver());
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(8);// 第9列(公车-同行人数)
					rowCell.setCellValue(common.getCompareManNum());
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(9);// 第10列(公车-出车里程)
					rowCell.setCellValue(common.getLengthBegin());
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(10);// 第11列(公车-还车里程)
					rowCell.setCellValue(common.getLengthEnd());
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(11);// 第12列(公车-计费里程)
					rowCell.setCellValue(common.getAccountLength());
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(12);// 第13列(公车-计划借车时间)
					rowCell.setCellValue(common.getBeginTimePlan());
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(13);// 第14列(公车-计划还车时间)
					rowCell.setCellValue(common.getEndTimePlan());
					rowCell.setCellStyle(style);
				  	
					rowCell = row.createCell(14);// 第15列(公车-出车时间)
					rowCell.setCellValue(common.getBeginTime());
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(15);// 第16列(公车-还车时间)
					rowCell.setCellValue(common.getEndTime());
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(16);// 第17列(公车-借车事由)
					rowCell.setCellValue(common.getUseCarReason());
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(17);// 第18列(公车-出发地)
					rowCell.setCellValue(common.getStartAddress());
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(18);// 第19列(公车-目的地)
					rowCell.setCellValue(common.getEndAddress());
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(19);// 第20列(公车-计划用车时间)
					rowCell.setCellValue(common.getAccountPlanTime());
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(20);// 第21列(公车-实际用车时间)
					rowCell.setCellValue(common.getAccountRealTime());
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
	
	
	private List<CarApplyInfoEntity> getPrivateListByNum(String[] nlist) {
		List<CarApplyInfoEntity> list = carApplyInfoDAO.getAllPassed();// 最终返回的list
//		for (int t = 0;t<nlist.length;t++)//根据number取出要导出的信息
//		{
//			CarApplyInfoEntity p = new CarApplyInfoEntity();
//			p = carApplyInfoDAO.getPrivateById(nlist[t]);
//			if(p!=null&&!"".equals(p)){
//				list.add(p);
//			}
//		}
		return list;
	}
	@Override
	public int getSearchInfoCount(CarUseDetailDTO carUseDetailDTO) {
		String dep = carUseDetailDTO.getDepatment();
		String department = "%"+carUseDetailDTO.getDepatment()+"%";
		String applyman = "%"+carUseDetailDTO.getApplyman()+"%";
		String carcode = carUseDetailDTO.getCarinfo();
		String start = carUseDetailDTO.getStarttime();
		String end = carUseDetailDTO.getEndtime();
		if(dep.equals("全部")&&carcode.equals("全部")){
			return carApplyInfoDAO.getSearchInfo11Count(applyman,start,end);
		}
		else if(dep.equals("全部")&&!carcode.equals("全部")){
			return carApplyInfoDAO.getSearchInfo10Count(applyman,carcode,start,end);
		}
		else if(!dep.equals("全部")&&carcode.equals("全部")){
			return carApplyInfoDAO.getSearchInfo01Count(department,applyman,start,end);
		}
		else{
			return carApplyInfoDAO.getSearchInfo00Count(department,applyman,carcode,start,end);
		}
	}
	
}
	
