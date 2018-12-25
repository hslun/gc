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
 * ����������Ϣ�ӿ�ʵ����
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
	 * ���������Id
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
	public int Save(PrivateCarApplyDTO pto) {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	pto.setStatus("������");
    	pto.setIfpass("δ����");
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
    
    //����ͨ��
    @Transactional
	@Override
	public int Approve(ApproveDTO adt) {
    	adt.setApprovetime(CommonUtil.getInstance().getTime());
		return privateCarApplyDAO.Approve("��ͨ��",adt.getApproveman(),adt.getApplyid(),adt.getApprovetime());
	}
    
    //�������
    @Transactional
	@Override
	public int Deny(ApproveDTO adt) {
		adt.setApprovetime(CommonUtil.getInstance().getTime());
		return privateCarApplyDAO.Approve("�ѷ��",adt.getApproveman(),adt.getApplyid(),adt.getApprovetime());
	}
    
    @Transactional
	@Override
	public int Sure(String applyId) {
		return privateCarApplyDAO.Sure("��ȷ��", applyId,CommonUtil.getInstance().getTime());
	}
    
    //�쵼��ȡ�������б�
    @Transactional
	@Override
	public List<PrivateCarApplyEntity> getApprove(getApproveDTO getDTO) {
		return privateCarApplyDAO.getApproveInfo("������", getDTO.getDepartment(),getDTO.getApproveman());
	}
    
    //���˻�ȡ��ͨ���Ҵ�ִ�е��б�
    @Transactional
	@Override
  	public List<PrivateCarApplyEntity> getPersonalReady(String applyman) {
  		return privateCarApplyDAO.getPersonalReady(applyman);
  	}
    
    @Transactional
	@Override
	public List<PrivateCarApplyEntity> getSure(getApproveDTO getDTO) {
		return privateCarApplyDAO.getSureInfo("��ȷ��", getDTO.getDepartment(), getDTO.getApproveman());
	}
    
    @Transactional
	@Override
	public List<PrivateCarApplyEntity> getUseDetailInfo(PrivateCarUseDetailDTO pdt) {
		String applyman=pdt.getApplyman();
		String department=pdt.getDepatment();
		//����ȫ��
		if("".equals(applyman)){
			if("ȫ��".equals(department)){
				return privateCarApplyDAO.getDetail11(pdt.getStarttime(),pdt.getEndtime());		
			}
			else{
				return privateCarApplyDAO.getDetail01(pdt.getStarttime(),pdt.getEndtime(), pdt.getDepatment());
			}
		}
		else{
			if(!"ȫ��".equals(department)){
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
			if("ȫ��".equals(department)){
				list=privateCarApplyDAO.getCollectInfo4(pdt.getStarttime(), pdt.getEndtime());
			}
			else{
				list=privateCarApplyDAO.getCollectInfo3(department, pdt.getStarttime(), pdt.getEndtime());
			}
		}
		else{
			if("ȫ��".equals(department)){
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
			//���Ȼ�ȡ���в��� ���Ϊ�վ�ֱ�Ӹ��ݲ��Ż�ȡ������
			List<DepartmentEntity> ds=departmentDAO.findAll();
			for (DepartmentEntity ob : ds) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("department", ob.getDepartmentName());
				map.put("applyman", "��");
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
  		return privateCarApplyDAO.getPersonal("��ȷ��",applyman);
  	}
    
    @Transactional
	@Override
	public int delPersonal(String applyid) {
		return privateCarApplyDAO.delPersonal(applyid);
	}
    
    //�޸ĸ���������Ϣ
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
	
	//��ִ��
	@Transactional
	@Override
	public int setPerformed(String applyid) {
		return privateCarApplyDAO.setIfPerform("��ִ��",applyid);
	}
	
	//δִ��
	@Transactional
	@Override
	public int setUnperformed(String applyid) {
		return privateCarApplyDAO.setIfPerform("δִ��",applyid);
	}
	
	//����
	@Transactional
	@Override
	public int deleteApprove(String applyid) {
		return privateCarApplyDAO.delPersonal(applyid);
	}
	
	//��ȡ������ͨ���б�
	public List<PrivateCarApplyEntity> getPersonalApprove(String applyman) {
		return privateCarApplyDAO.getPersonalApprove(applyman);
	}
	
	//���ݲ��š���������ʼʱ�䡢����ʱ���ѯ
	@Transactional
	@Override
	public List<PrivateCarApplyEntity> getSearchInfo(PrivateCarSearchDTO privateCarSearchDTO) {
		String department = privateCarSearchDTO.getDepartment();
		String a= privateCarSearchDTO.getApplyman();
		String startTime = privateCarSearchDTO.getStartTime();
		String endTime = privateCarSearchDTO.getEndTime();
		String applyman= "%"+a+"%";
		List<PrivateCarApplyEntity> list = new ArrayList<PrivateCarApplyEntity>();
		if(department.equals("ȫ��")){
			list = privateCarApplyDAO.getSearchInfo1(applyman,startTime,endTime);
		}
		else{
			list = privateCarApplyDAO.getSearchInfo(department,applyman,startTime,endTime);
		}
		
		return list;
	}
	
	//���˻�ȡ�������ͱ�������б�
	@Transactional
	@Override
	public List<PrivateCarApplyEntity> getUnapproved(String applyman) {
		return privateCarApplyDAO.getUnapproved(applyman);
	}
	
	//Web-��ȡ˽�����������Ϣ
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
	
	//Web-��ȡ˽�����������Ϣ������ˣ�
	@Override
	public List<PrivateCarApplyEntity> carDisplay1(int start, int number) {
		return privateCarApplyDAO.carDisplay1(start,number);
	}
	
	//Web-��ȡ˽�����������Ϣ��δ��ˣ�
	@Override
	public List<PrivateCarApplyEntity> uncarDisplay(int start, int number) {
		return privateCarApplyDAO.uncarDisplay(start,number);
	}
	
	//Web-��ȡ˽��������Ϣ������
	public int getAllCount() {
		return privateCarApplyDAO.getAllCount();
	}
	
	//Web-��ȡ˽��������Ϣ������
	public int getAllCount1() {
		return privateCarApplyDAO.getAllCount1();
	}
	
	//Web-��ȡ˽��������Ϣ������
	public int ungetAllCount() {
		return privateCarApplyDAO.ungetAllCount();
	}
	
	//Web-��ѯ-���˽�����������Ϣ
	public List<PrivateCarApplyEntity> searchPrivate(int start, int number, PrivateCarSearchDTO privateCarSearchDTO) {
		String department = privateCarSearchDTO.getDepartment();
		String app = privateCarSearchDTO.getApplyman();
		String applyman = "%"+app+"%";
		String startTime = privateCarSearchDTO.getEndTime();
		String endTime = privateCarSearchDTO.getStartTime();
		if(department.equals("ȫ��")){
			return privateCarApplyDAO.searchPrivate(start,number,applyman,startTime,endTime);
		}
		else{
			return privateCarApplyDAO.searchPrivateD(start,number,department,applyman,startTime,endTime);
		}

	}
	
	//Web-��ѯ-���˽������������
	public int getSearchAllCount(PrivateCarSearchDTO privateCarSearchDTO) {
		String department = privateCarSearchDTO.getDepartment();
		String app = privateCarSearchDTO.getApplyman();
		String applyman = "%"+app+"%";
		String endTime = privateCarSearchDTO.getEndTime();
		String startTime = privateCarSearchDTO.getStartTime();
		if(department.equals("ȫ��")){
			return privateCarApplyDAO.getSearchAllCount(applyman,endTime,startTime);
		}
		else{
			return privateCarApplyDAO.getSearchAllCountD(department,applyman,endTime,startTime);
		}
	}
	
	//����˽��������Ϣ
	public int export(String[] nlist, String filePath) {
		// �õ����ݼ���
		List<PrivateCarApplyEntity> privateList = new ArrayList<PrivateCarApplyEntity>();
		privateList = getPrivateListByNum(nlist);
		return exportExcel(privateList, filePath);
	}
	
	//�����ص�˽��������Ϣ
	private List<PrivateCarApplyEntity> getPrivateListByNum(String[] nlist) {
		List<PrivateCarApplyEntity> list = privateCarApplyDAO.getAllPassed();// ���շ��ص�list
//		for (int t = 0;t<nlist.length;t++)//����numberȡ��Ҫ��������Ϣ
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
		// ����һ��������
		XSSFWorkbook workbook;
		String sheetName = "˽��������Ϣ";
		try {
			workbook = new XSSFWorkbook();
			// ���һ��sheet,sheet��
			XSSFSheet sheet = workbook.createSheet(sheetName);
			// �ϲ���Ԫ�� ������˼�� ��һ�С����һ�С���һ�С����һ��
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 15));
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
			cell.setCellValue("�ó�ʱ��");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(4);// ��5��
			cell.setCellValue("����");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(5);// ��6��
			cell.setCellValue("������");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(6);// ��7��
			cell.setCellValue(";����");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(7);// ��8��
			cell.setCellValue("Ŀ�ĵ�");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(8);// ��9��
			cell.setCellValue("״̬");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(9);// ��10��
			cell.setCellValue("�������");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(10);// ��11��
			cell.setCellValue("�Ƽ����");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(11);// ��12��
			cell.setCellValue("������");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(12);// ��13��
			cell.setCellValue("������");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(13);// ��14��
			cell.setCellValue("����ʱ��");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(14);// ��15��
			cell.setCellValue("����ʱ��");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(15);// ��16��
			cell.setCellValue("ִ��״��");
			cell.setCellStyle(style);
			
			// ��ͷ���------------------
			int index = 1;// �кţ�Ӧ�ӵ����п�ʼ��ÿ��ѭ������++
			PrivateCarApplyEntity priv = new PrivateCarApplyEntity();
			// �������Ͻ�����д��excel��
			if (privateList.size() > 0) {
				for (int i = 0; i < privateList.size(); i++) {
					// �к�++��2��ʼ
					index++;
					
					// ����
					priv = privateList.get(i);
					
					// �õ���������
					int hs = privateList.size();
					
					// ������Ӧ������
					//for(int r=0; r<hs; r++){
					//XSSFRow row = sheet.createRow(r+index);
					XSSFRow row = sheet.createRow(index);
					/*// �ϲ���Ӧ��
					for(int j=0; j < 16; j++){
					sheet.addMergedRegion(new CellRangeAddress(index, index+hs-1, j, j));//���С����һ�С����С����һ��
					}*/
					
					XSSFCell rowCell = row.createCell(0);// ��1��(���)
					rowCell.setCellValue(i + 1 + "");
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(1);// ��2��(˽��-��������)
					rowCell.setCellValue(priv.getApplyId());
					rowCell.setCellStyle(style);

					rowCell = row.createCell(2);// ��3��(˽��-����)
					rowCell.setCellValue(priv.getDepartment());
					rowCell.setCellStyle(style);

					rowCell = row.createCell(3);// ��4��(˽��-�ó�ʱ��)
					rowCell.setCellValue(priv.getUserCarTime());
					rowCell.setCellStyle(style);

					rowCell = row.createCell(4);// ��5��(˽��-����)
					rowCell.setCellValue(priv.getReason());
					rowCell.setCellStyle(style);

					rowCell = row.createCell(5);// ��6��(˽��-������)
					rowCell.setCellValue(priv.getBeginAddress());
					rowCell.setCellStyle(style);

					rowCell = row.createCell(6);// ��7��(˽��-;����)
					rowCell.setCellValue(priv.getPassAddress());
					rowCell.setCellStyle(style);

					rowCell = row.createCell(7);// ��8��(˽��-Ŀ�ĵ�)
					rowCell.setCellValue(priv.getDestination());
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(8);// ��9��(˽��-״̬)
					rowCell.setCellValue(priv.getStatus());
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(9);// ��10��(˽��-�������)
					rowCell.setCellValue(priv.getSingleLength());
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(10);// ��11��(˽��-�Ƽ����)
					rowCell.setCellValue(priv.getCountLength());
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(11);// ��12��(˽��-������)
					rowCell.setCellValue(priv.getApplyMan());
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(12);// ��13��(˽��-������)
					rowCell.setCellValue(priv.getApproveMan());
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(13);// ��14��(˽��-����ʱ��)
					rowCell.setCellValue(priv.getApplyTime());
					rowCell.setCellStyle(style);
				  	
					rowCell = row.createCell(14);// ��15��(˽��-����ʱ��)
					rowCell.setCellValue(priv.getApproveTime());
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(15);// ��16��(˽��-ִ��״��)
					rowCell.setCellValue(priv.getIfPerform());
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
	/*	//Web-������д���ϸ-������
	@Transactional
	@Override
	public int export(String[] nlist,String path) {
		// �õ����ݼ���
		List<EntertainInfoEntity> entertainList = new ArrayList<EntertainInfoEntity>();
		entertainList = getEntertainListByNum(nlist);
			return exportExcel(entertainList, path);
		}*/
	@Transactional
	@Override
	public int importPrivateCarExcel(String fileName) {
		// �ļ���
		int flag = 0;
		InputStream inputStream;
		try {
			inputStream = new FileInputStream(fileName);
			// ����XSSFWorkbook����
			XSSFWorkbook xssFWorkbook = new XSSFWorkbook(inputStream);
			// ��ȡ��������һ��sheet������ݣ��涨��һ��sheet����Ϊ��Ϣ
			XSSFSheet sheet = xssFWorkbook.getSheetAt(0);
			// �����ж���
			XSSFRow row;
			// ��ȡ�ܹ�������
			int rowCount = sheet.getPhysicalNumberOfRows();
			// ��ȡÿһ���ж����У��Եڶ��е�������һλ��׼����Ϊ�ڶ�������ʾ�û���ע������
			int cellCount = sheet.getRow(1).getPhysicalNumberOfCells() ;
			// ÿһ��Ϊһ������
			PrivateCarApplyEntity pricar = new PrivateCarApplyEntity();
			// ѭ������ÿһ�У��ӵ����п�ʼ��Ϊ���ݣ�����i��2��ʼ
			for (int i = 1; i < rowCount; i++) {
				// ��ȡÿһ��
				row = sheet.getRow(i);
				// ����һ����Ԫ�����
				XSSFCell cell = null;
				// ��Ԫ������ֵ
				String cellValue = null;
				// ��Ԫ��ֵ������
				int cellType;
				// ����һ�����һ�����ݵ��ַ���
				String[] s = new String[cellCount];
				// ��ÿһ�н��б��������ڵ�һ��Ϊ�кţ�������ӵ����ݿ⣬���Դӵڶ��п�ʼ����
				for (int j = 0; j < cellCount; j++) {
					cell = row.getCell(j);
					// ��ȡÿһ�еĵ�Ԫ��
					if(cell!=null){
						cellType = cell.getCellType();
						switch (cellType) {
						case Cell.CELL_TYPE_STRING:// �ı�����
							cellValue = cell.getStringCellValue();
							break;
						case Cell.CELL_TYPE_NUMERIC:// ��������ֵĻ�����ҲҪת��Ϊ�ı�����
							cellValue = String.valueOf((int) cell.getNumericCellValue());
							break;
						}
					}
					// �����ݴ�ŵ��ַ���������
					s[j] = cellValue;
				}
				// ����ȡ����һ�����ݵ�ֵת��ΪPrivateCarApplyEntity����
				
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
				//�����ó����ڣ������ˣ�����ʱ�䣬�жϿ����Ƿ���ڸ�����
//				int d = privateCarApplyDAO.getPrivateBy3(pricar.getUserCarTime(), pricar.getApplyTime(), pricar.getApplyMan());
//				if(d==0){
					flag = privateCarApplyDAO.insertEntity1(pricar.getApplyId(), pricar.getDepartment(), pricar.getApplyMan(), pricar.getUserCarTime(), 
							pricar.getReason(), pricar.getBeginAddress(), pricar.getPassAddress(), pricar.getStatus(), pricar.getDestination(), 
							pricar.getSingleLength(), pricar.getSureLength(), pricar.getCountLength(), pricar.getApproveMan(), pricar.getApplyTime(), 
							pricar.getApproveTime(),pricar.getWayModel(), pricar.getWayDetail());
//				}
				// ���û�б���ɹ�����0��ֱ���˳�
//				if (flag == 0) {
//					return flag;
//				}
			}
//			xssFWorkbook.close();// �ر�
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
			List<String> arrayList=new ArrayList<String>(list);//ת��ΪArrayLsit������ص�remove����
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
			List<String> arrayList=new ArrayList<String>(list);//ת��ΪArrayLsit������ص�remove����
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
	 * �ύ����ǰ����˽����Ϣ
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
	 * �ύ����ǰ����˽����Ϣ
	 */
	@Transactional
	@Override
	public int updatePrivateCarStatusBack(PrivateCarApplyDTO pto){
		pto.setStatus("������");
		pto.setIfperform("��ִ��");
		pto.setIfpass("δ����");
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
	 * ���ض�������
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
	         ("�Ѳ���".equals(car.getIfPass()))) {
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
	 * ͨ����������
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
		  if("������".equals(p.getStatus())){
			  p.setApprovetime("");
//			  p.setIfbefore("");
			  p.setIfpass("δ����");
		  }else if("�����".equals(p.getStatus())){
//			  p.setIfbefore("");
			  p.setIfpass("δ����");
		  }
    	if(p.getIfbefore()==null){
    		p.setIfbefore("0");
    		p.setBeforedate("");
		}
		  if("��ִ��".equals(p.getIfperform())){
			  p.setIfsub("0");
		  }else{
			  p.setIfsub("1");
		  }
		  if("��ͨ��".equals(p.getStatus())||"�����".equals(p.getStatus())){
			  if("��ִ��".equals(p.getIfperform())){
			  //��ӵ�����
			  pto.setApplyids(p.getApplyid());
			  pto.setApplyman(p.getApplyman());
			  pto.setSurelength(String.valueOf(p.getSurelength()));
			  String applyid = new SimpleDateFormat("yyyyMMddHHmmssSSS") .format(new Date() );
				pto.setApplyid(applyid);
				pto.setApplytime(p.getSubtime());
				pto.setSum(p.getSum());
				if("δ����".equals(p.getIfpass())){
					pto.setStatus("�����");
				}else{
					pto.setStatus("�����");
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
	  //ɾ��ʱ�漰�����޸�
	@Transactional
	public void updateForDelete(PrivateCarInvoiceEntity carinvoice) {
		privateCarInvoiceDAO.updateEntity(carinvoice.getApplyId(), carinvoice.getApplyMan(), carinvoice.getApproveMan(), carinvoice.getApplyTime(), carinvoice.getSum(), carinvoice.getSureLength(), carinvoice.getVoucherNum(), carinvoice.getStatus(), carinvoice.getPaidTime(), carinvoice.getApplyIds());
	}
	  //�����޸�˽��������Ϣ
	@Transactional
	public int updateNew(PrivateCarApplyDTO p) {
		 PrivateCarInvoiceEntity carinvoice = privateCarInvoiceDAO.getByNumber("%" + p.getApplyid() + "%");
		 PrivateCarInvoiceDTO pto=new PrivateCarInvoiceDTO();
		 if("������".equals(p.getStatus())){
			  p.setApprovetime("");
			  p.setIfperform("");
			  p.setIfpass("δ����");
			  p.setApproveman("");
			  p.setSubtime("");
			  p.setSum("");
			  p.setPaidtime("");
			  p.setApproveman2("");
			  p.setDanhao("");
			  //�ж�һ����˵�������м�����������
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
		  }else if("�����".equals(p.getStatus())){
			  p.setIfperform("");
			  p.setIfpass("δ����");
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
			  
		  }else if("��ͨ��".equals(p.getStatus())||"�����".equals(p.getStatus())){
			  if("��ִ��".equals(p.getIfperform())){
				  p.setIfsub("0");
				  p.setDanhao("");
				  p.setSum("");
				  p.setApproveman2("");
				  p.setPaidtime("");
				  p.setSubtime("");
				  p.setIfpass("δ����");
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
				  if("δ����".equals(p.getIfpass())){
					  p.setPaidtime("");
					  p.setApproveman2("");
					  p.setDanhao("");
				  }
				  p.setIfsub("1");
				  
				  
				  //��ӵ�����
				  if(carinvoice==null){
					  pto.setApplyids(p.getApplyid());
					  pto.setApplyman(p.getApplyman());
					  pto.setSurelength(String.valueOf(p.getSurelength()));
					  pto.setApproveman(p.getApproveman2());
					  String applyid = new SimpleDateFormat("yyyyMMddHHmmssSSS") .format(new Date() );
					pto.setApplyid(applyid);
					pto.setApplytime(p.getSubtime());
					pto.setSum(p.getSum());
					if("δ����".equals(p.getIfpass())){
						pto.setStatus("�����");
					}else{
						pto.setStatus("�����");
						pto.setPaidtime(p.getPaidtime());
						pto.setVouchernum(p.getDanhao());
					}
					privateCarInvoiceDAO.insertEntity(pto.getApplyid(), pto.getApplyman(), pto.getApproveman(), pto.getApplytime(), pto.getSum(), pto.getSurelength(), pto.getVouchernum(), pto.getStatus(), pto.getPaidtime(), pto.getApplyids());
				  }else{
					//�жϸõ���������
					 String[] split = carinvoice.getApplyIds().split(",");
					 if(split.length>=1&&p.getDanhao()!=null&&!"".equals(p.getDanhao())){
						 //ȫ�޸�
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
						if("δ����".equals(p.getIfpass())){
							pto.setStatus("�����");
						}else{
							pto.setStatus("�����");
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
						if("δ����".equals(p.getIfpass())){
							pto.setStatus("�����");
						}else{
							pto.setStatus("�����");
							pto.setPaidtime(p.getPaidtime());
							if(carinvoice.getVoucherNum()==null||"".equals(carinvoice.getVoucherNum())){
								pto.setVouchernum(p.getDanhao());
							}}
						privateCarInvoiceDAO.insertEntity(pto.getApplyid(), pto.getApplyman(), pto.getApproveman(), pto.getApplytime(), pto.getSum(), pto.getSurelength(), pto.getVouchernum(), pto.getStatus(), pto.getPaidtime(), pto.getApplyids()); 
					 }
					  
				  } 
				  
				  
			  }
		  }
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
//		  if("��ִ��".equals(p.getIfperform())){
//			  p.setIfsub("0");
//			  p.setDanhao("");
//			  p.setSum("");
//			  p.setApproveman2("");
//			  p.setPaidtime("");
//			  p.setSubtime("");
//			  p.setIfpass("δ����");
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
//			  if("δ����".equals(p.getIfpass())){
//				  p.setPaidtime("");
//				  p.setApproveman2("");
//				  p.setDanhao("");
//			  }
//			  p.setIfsub("1");
//			  
//		  }
		  
//		  PrivateCarInvoiceDTO pto=new PrivateCarInvoiceDTO();
//		  if("��ͨ��".equals(p.getStatus())&&"��ִ��".equals(p.getIfperform())){
//			  //��ӵ�����
//			  if(carinvoice==null){
//				  pto.setApplyids(p.getApplyid());
//				  pto.setApplyman(p.getApplyman());
//				  pto.setSurelength(String.valueOf(p.getSurelength()));
//				  pto.setApproveman(p.getApproveman2());
//				  String applyid = new SimpleDateFormat("yyyyMMddHHmmssSSS") .format(new Date() );
//				pto.setApplyid(applyid);
//				pto.setApplytime(p.getSubtime());
//				pto.setSum(p.getSum());
//				if("δ����".equals(p.getIfpass())){
//					pto.setStatus("�����");
//				}else{
//					pto.setStatus("�����");
//					pto.setPaidtime(p.getPaidtime());
//					pto.setVouchernum(p.getDanhao());
//				}
//				privateCarInvoiceDAO.insertEntity(pto.getApplyid(), pto.getApplyman(), pto.getApproveman(), pto.getApplytime(), pto.getSum(), pto.getSurelength(), pto.getVouchernum(), pto.getStatus(), pto.getPaidtime(), pto.getApplyids());
//			  }else{
//				//�жϸõ���������
//				 String[] split = carinvoice.getApplyIds().split(",");
//				 if(split.length>=1&&p.getDanhao()!=null&&!"".equals(p.getDanhao())){
//					 //ȫ�޸�
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
//					if("δ����".equals(p.getIfpass())){
//						pto.setStatus("�����");
//					}else{
//						pto.setStatus("�����");
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
//					if("δ����".equals(p.getIfpass())){
//						pto.setStatus("�����");
//					}else{
//						pto.setStatus("�����");
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
	//�޸�˽��������Ϣ
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
