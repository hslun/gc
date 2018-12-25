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
 * ����������Ϣ�ӿ�ʵ����
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
	 * ���������Id
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
	//����id���ĺ���λ
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
		if("��Ϣ������".equals(department)){
			id=7;
		}else if("����".equals(department)){
			id=3;
		}else if("�ۺϰ칫��".equals(department)){
			id=2;
		}else if("ͨ����Ŀ��".equals(department)){
			id=5;
		}else if("�����Ʒ��".equals(department)){
			id=6;
		}else if("���˻���Ŀ��".equals(department)){
			id=8;
		}else if("��˾�쵼".equals(department)){
			id=1;
		}else if("�г���".equals(department)){
			id=9;
		}else if("�滮Ͷ�ʲ�".equals(department)){
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
	 * ����ʵ��
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
				carApplyInfoDTO.getBegintimeplan(), carApplyInfoDTO.getEndtimeplan(), carApplyInfoDTO.getUsecarreason(), carApplyInfoDTO.getApplytime(), "������", 
				carApplyInfoDTO.getStartaddress(), carApplyInfoDTO.getEndaddress(), String.valueOf(countplantime(carApplyInfoDTO.getBegintimeplan(),
						carApplyInfoDTO.getEndtimeplan())),carApplyInfoDTO.getComparemannum(),"0",carApplyInfoDTO.getRealapprove(),"0","0","0","��ԤԼ",carApplyInfoDTO.getApproveman());
	}
	
	/**
	 * ԤԼǰ��ѯ�Ƿ�黹����
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
		return carApplyInfoDAO.updateState("ͨ��",approveDTO.getApproveman(),approveDTO.getApplyid());
	}
	@Transactional
	@Override
	public int outgarage(String ApplyId,String begintime,String lengthbegin) {
		// TODO Auto-generated method stub
		return carApplyInfoDAO.outState("�ѽ��", ApplyId,begintime,lengthbegin);
	}
	@Transactional
	@Override
	public int ingarage(String ApplyId,String endtime,String reallength,String realtime,String lengthend) {
		// TODO Auto-generated method stub
		return carApplyInfoDAO.inState("�ѹ黹",endtime,realtime,reallength, ApplyId,lengthend);
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
	//����ȫ��  ������ȫ��
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
		return carApplyInfoDAO.getApproveInfo1("������");
	}
	@Transactional
	@Override
	public List<CarApplyInfoEntity> getApproveInfo2(
			CarApporveInfoDTO carApporveInfoDTO) {
		// TODO Auto-generated method stub
		return carApplyInfoDAO.getApproveInfo2("������", carApporveInfoDTO.getDepartment(),carApporveInfoDTO.getUsername());
	}
	
	@Transactional
	@Override
	public List<CarApplyInfoEntity> getApproveInfo3(CarApporveInfoDTO carApporveInfoDTO) {
		// TODO Auto-generated method stub
		return carApplyInfoDAO.getApproveInfo3("������",carApporveInfoDTO.getUsername());
	}
	
	@Transactional
	@Override
	public List<CarApplyInfoEntity> getAllApply(String carid) {
		// TODO Auto-generated method stub
		try {
			return carApplyInfoDAO.getAllApply(carid, "ͨ��", "�ѹ黹");
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
		return carApplyInfoDAO.getAllOrder(carid, "ͨ��", "�ѹ黹", addDay(), delDay());
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
		return carApplyInfoDAO.updateState("�����",approveDTO.getApproveman(),approveDTO.getApplyid());
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
				"������", 
				carApplyInfoDTO.getStartaddress(), 
				carApplyInfoDTO.getEndaddress(), 
				String.valueOf(countplantime(carApplyInfoDTO.getBegintimeplan(),
						carApplyInfoDTO.getEndtimeplan())),
						carApplyInfoDTO.getComparemannum(),"0","0","0","0","��ԤԼ",carApplyInfoDTO.getApproveman());
	}
	@Override
	public List<CarApplyInfoEntity> getAllApplyDetail(String carnum) {
		// TODO Auto-generated method stub
	   return carApplyInfoDAO.getAllApplyDetail(carnum, "ͨ��", "�ѹ黹");
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
	
	
	//Web-���ݲ��š������ˡ����ơ�����ʱ���ѯ
	@Override
	public List<CarApplyInfoEntity> getSearchInfo(CarUseDetailDTO carUseDetailDTO) {

		String dep = carUseDetailDTO.getDepatment();
		String department = "%"+carUseDetailDTO.getDepatment()+"%";
		String applyman = "%"+carUseDetailDTO.getApplyman()+"%";
		String carcode = carUseDetailDTO.getCarinfo();
		String start = carUseDetailDTO.getStarttime();
		String end = carUseDetailDTO.getEndtime();
		if(dep.equals("ȫ��")&&carcode.equals("ȫ��")){
			return carApplyInfoDAO.getSearchInfo11(applyman,start,end);
		}
		else if(dep.equals("ȫ��")&&!carcode.equals("ȫ��")){
			return carApplyInfoDAO.getSearchInfo10(applyman,carcode,start,end);
		}
		else if(!dep.equals("ȫ��")&&carcode.equals("ȫ��")){
			return carApplyInfoDAO.getSearchInfo01(department,applyman,start,end);
		}
		else{
			return carApplyInfoDAO.getSearchInfo00(department,applyman,carcode,start,end);
		}
	}
	//Web-���ݲ��š������ˡ����ơ�����ʱ���ѯ
	public List<CarApplyInfoEntity> getSearchInfoByPage(CarUseDetailDTO carUseDetailDTO,int sta,int number) {

		String dep = carUseDetailDTO.getDepatment();
		String department = "%"+carUseDetailDTO.getDepatment()+"%";
		String applyman = "%"+carUseDetailDTO.getApplyman()+"%";
		String carcode = carUseDetailDTO.getCarinfo();
		String start = carUseDetailDTO.getStarttime();
		String end = carUseDetailDTO.getEndtime();
		if(dep.equals("ȫ��")&&carcode.equals("ȫ��")){
			return carApplyInfoDAO.getSearchInfo11ByPage(applyman,start,end,sta,number);
		}
		else if(dep.equals("ȫ��")&&!carcode.equals("ȫ��")){
			return carApplyInfoDAO.getSearchInfo10ByPage(applyman,carcode,start,end,sta,number);
		}
		else if(!dep.equals("ȫ��")&&carcode.equals("ȫ��")){
			return carApplyInfoDAO.getSearchInfo01ByPage(department,applyman,start,end,sta,number);
		}
		else{
			return carApplyInfoDAO.getSearchInfo00ByPage(department,applyman,carcode,start,end,sta,number);
		}
	}
	
	//Web-��ʾ���������Ϣ
	@Override
	public List<CarApplyInfoEntity> carDisplay(int start,int number) {
		return carApplyInfoDAO.carDisplay(start, number);
	}
	
	//Web-��ù�����Ϣ��������
	@Override
	public int getAllCount() {
		return carApplyInfoDAO.getAllCount();
	}
	
	//Web-��ù�����Ϣ��������
	@Override
	public List<CarKindDTO> getCarKind(){
		List<CarKindDTO> list=new ArrayList<CarKindDTO>();
		List<Object> a = carBaseInfoDAO.getCarKind();
		CarKindDTO all = new CarKindDTO();
		all.setID("10");all.setKind("ȫ��");
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
	
	//��������ʹ����Ϣ
	public int export(String[] nlist, String filePath) {
		// �õ����ݼ���
	List<CarApplyInfoEntity> commonList = new ArrayList<CarApplyInfoEntity>();
	commonList = getPrivateListByNum(nlist);
	return exportExcel(commonList, filePath);
	}
	
	
	private int exportExcel(List<CarApplyInfoEntity> commonList, String filePath) {
		XSSFWorkbook workbook;
		String sheetName = "����ʹ����Ϣ";
		try {
			workbook = new XSSFWorkbook();
			// ���һ��sheet,sheet��
			XSSFSheet sheet = workbook.createSheet(sheetName);
			// �ϲ���Ԫ�� ������˼�� ��һ�С����һ�С���һ�С����һ��
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 20));
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
			// �����������ĵ�Ԫ���ʽ
			XSSFCellStyle style = workbook.createCellStyle();
			style.setAlignment(HorizontalAlignment.CENTER);
			// ���õڶ��б�ͷ
			XSSFRow rowHeader = sheet.createRow(1);
			XSSFCell cell = rowHeader.createCell(0);// ��1��
			cell.setCellValue("���");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(1);// ��2��
			cell.setCellValue("��������");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(2);// ��3��
			cell.setCellValue("����");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(3);// ��4��
			cell.setCellValue("������");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(4);// ��5��
			cell.setCellValue("������");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(5);// ��6��
			cell.setCellValue("����");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(6);// ��7��
			cell.setCellValue("���ƺ�");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(7);// ��8��
			cell.setCellValue("��ʻ��");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(8);// ��9��
			cell.setCellValue("ͬ������");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(9);// ��10��
			cell.setCellValue("�������");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(10);// ��11��
			cell.setCellValue("�������");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(11);// ��12��
			cell.setCellValue("�Ʒ����");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(12);// ��13��
			cell.setCellValue("�ƻ��賵ʱ��");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(13);// ��14��
			cell.setCellValue("�ƻ�����ʱ��");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(14);// ��15��
			cell.setCellValue("����ʱ��");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(15);// ��16��
			cell.setCellValue("����ʱ��");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(16);// ��17��
			cell.setCellValue("�賵����");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(17);// ��18��
			cell.setCellValue("������");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(18);// ��19��
			cell.setCellValue("Ŀ�ĵ�");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(19);// ��20��
			cell.setCellValue("�ƻ��ó�ʱ��");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(20);// ��21��
			cell.setCellValue("ʵ���ó�ʱ��");
			cell.setCellStyle(style);
			
			
			// ��ͷ���------------------
			int index = 1;// �кţ�Ӧ�ӵ����п�ʼ��ÿ��ѭ������++
			CarApplyInfoEntity common = new CarApplyInfoEntity();
			// �������Ͻ�����д��excel��
			if (commonList.size() > 0) {
				for (int i = 0; i < commonList.size(); i++) {
					// �к�++��2��ʼ
					index++;
					
					// ����
					common = commonList.get(i);
					
					// �õ���������
					int hs = commonList.size();
					
					// ������Ӧ������
					XSSFRow row = sheet.createRow(index);
					
					XSSFCell rowCell = row.createCell(0);// ��1��(���)
					rowCell.setCellValue(i + 1 + "");
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(1);// ��2��(����-��������)
					rowCell.setCellValue(common.getApplyId());
					rowCell.setCellStyle(style);

					rowCell = row.createCell(2);// ��3��(����-����)
					rowCell.setCellValue(common.getDepartment());
					rowCell.setCellStyle(style);

					rowCell = row.createCell(3);// ��4��(����-������)
					rowCell.setCellValue(common.getApplyMan());
					rowCell.setCellStyle(style);

					rowCell = row.createCell(4);// ��5��(����-������)
					rowCell.setCellValue(common.getApproveMan());
					rowCell.setCellStyle(style);

					rowCell = row.createCell(5);// ��6��(����-����)
					rowCell.setCellValue(common.getCarType());
					rowCell.setCellStyle(style);

					rowCell = row.createCell(6);// ��7��(����-���ƺ�)
					rowCell.setCellValue(common.getCarCode());
					rowCell.setCellStyle(style);

					rowCell = row.createCell(7);// ��8��(����-��ʻ��)
					rowCell.setCellValue(common.getDriver());
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(8);// ��9��(����-ͬ������)
					rowCell.setCellValue(common.getCompareManNum());
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(9);// ��10��(����-�������)
					rowCell.setCellValue(common.getLengthBegin());
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(10);// ��11��(����-�������)
					rowCell.setCellValue(common.getLengthEnd());
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(11);// ��12��(����-�Ʒ����)
					rowCell.setCellValue(common.getAccountLength());
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(12);// ��13��(����-�ƻ��賵ʱ��)
					rowCell.setCellValue(common.getBeginTimePlan());
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(13);// ��14��(����-�ƻ�����ʱ��)
					rowCell.setCellValue(common.getEndTimePlan());
					rowCell.setCellStyle(style);
				  	
					rowCell = row.createCell(14);// ��15��(����-����ʱ��)
					rowCell.setCellValue(common.getBeginTime());
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(15);// ��16��(����-����ʱ��)
					rowCell.setCellValue(common.getEndTime());
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(16);// ��17��(����-�賵����)
					rowCell.setCellValue(common.getUseCarReason());
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(17);// ��18��(����-������)
					rowCell.setCellValue(common.getStartAddress());
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(18);// ��19��(����-Ŀ�ĵ�)
					rowCell.setCellValue(common.getEndAddress());
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(19);// ��20��(����-�ƻ��ó�ʱ��)
					rowCell.setCellValue(common.getAccountPlanTime());
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(20);// ��21��(����-ʵ���ó�ʱ��)
					rowCell.setCellValue(common.getAccountRealTime());
					rowCell.setCellStyle(style);
				}
					//index=index+1; 
				}
			//}
			
			// ���ļ����浽ָ��λ��
			FileOutputStream out = new FileOutputStream(filePath);
			workbook.write(out);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}     
	}
	
	
	private List<CarApplyInfoEntity> getPrivateListByNum(String[] nlist) {
		List<CarApplyInfoEntity> list = carApplyInfoDAO.getAllPassed();// ���շ��ص�list
//		for (int t = 0;t<nlist.length;t++)//����numberȡ��Ҫ��������Ϣ
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
		if(dep.equals("ȫ��")&&carcode.equals("ȫ��")){
			return carApplyInfoDAO.getSearchInfo11Count(applyman,start,end);
		}
		else if(dep.equals("ȫ��")&&!carcode.equals("ȫ��")){
			return carApplyInfoDAO.getSearchInfo10Count(applyman,carcode,start,end);
		}
		else if(!dep.equals("ȫ��")&&carcode.equals("ȫ��")){
			return carApplyInfoDAO.getSearchInfo01Count(department,applyman,start,end);
		}
		else{
			return carApplyInfoDAO.getSearchInfo00Count(department,applyman,carcode,start,end);
		}
	}
	
}
	
