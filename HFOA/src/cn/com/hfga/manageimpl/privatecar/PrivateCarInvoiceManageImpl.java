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
			if("�Ѳ���".equals(en.getIfPass())){
				privateCarApplyDAO.updatePrivateCarUnIfPass(id);
			}
			//�����ύ��˽��״̬Ϊ���ύ
			privateCarApplyDAO.updatePrivateCarIfSub(id);
//			int j = privateCarApplyDAO.updatePrivateCarIfPass(id);
		}
		
		String applyid = new SimpleDateFormat("yyyyMMddHHmmssSSS") .format(new Date() );
		pto.setApplyid(applyid);
		pto.setApplytime(CommonUtil.getInstance().getTime());
		pto.setStatus("�����");
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
	 * ��ѯһ����¼
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
	 * ����ƾ��id��ѯ����˽����Ϣ
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
	 * ��ѯ���������ƾ��
	 */
	@Override
	public List<PrivateCarInvoiceEntity> selectPassed() {
		return privateCarInvoiceDAO.selectPessed();
	}
	/**
	 * ��ѯ�ѱ���������ɼ�¼
	 */
	@Override
    public List<PrivateCarInvoiceEntity> invoiceDisplay(int start,int number){
    	return privateCarInvoiceDAO.invoiceDisplay(start, number);
    }
	/**
	 * ��ѯ�ѱ���������ɼ�¼�� 
	 */
	@Override
    public int getAllCount(){
    	return privateCarInvoiceDAO.getAllCount();
    }
	/**
	 * ��ѯ����δ�Ǽ�
	 */
	@Override
	public List<PrivateCarInvoiceEntity> unSignInvoiceDisplay(int start, int number) {
		return privateCarInvoiceDAO.UnSignInvoiceDisplay(start, number);
	}
	/**
	 * ��ѯ����δ�ǼǼ�¼�� 
	 */
	@Override
	public int getAllUnSignInvoiceCount() {
		return privateCarInvoiceDAO.getAllUnSignInvoiceCount();
	}

	/**
	 * ��ѯ��������¼
	 */
	@Override
    public List<PrivateCarInvoiceEntity> uninvoiceDisplay(int start,int number){
    	return privateCarInvoiceDAO.uninvoiceDisplay(start, number);
    }
	/**
	 * ��ѯ��������¼��
	 */
	@Override
    public int ungetAllCount(){
    	return privateCarInvoiceDAO.ungetAllCount();
    }
	//����˽��������Ϣ
	@Override
	public int export(String[] nlist, String filePath) {
		// �õ����ݼ���
		List<PrivateCarInvoiceEntity> privateList = new ArrayList<PrivateCarInvoiceEntity>();
		privateList = getPrivateListByNum(nlist);
		return exportExcel(privateList, filePath);
	}
	//�����ص�˽��������Ϣ
	private List<PrivateCarInvoiceEntity> getPrivateListByNum(String[] nlist) {
		List<PrivateCarInvoiceEntity> list = privateCarInvoiceDAO.selectPessed();// ���շ��ص�list
		return list;
	}
	private int exportExcel(List<PrivateCarInvoiceEntity> privateList, String filePath) {
		// ����һ��������
		XSSFWorkbook workbook;
		String sheetName = "˽��������ϸ��";
		try {
			workbook = new XSSFWorkbook();
			// ���һ��sheet,sheet��
			XSSFSheet sheet = workbook.createSheet(sheetName);
			// �ϲ���Ԫ�� ������˼�� ��һ�С����һ�С���һ�С����һ��
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 18));
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
			sheet.autoSizeColumn(15);
			sheet.autoSizeColumn(16);
			sheet.autoSizeColumn(17);
			// �����������ĵ�Ԫ���ʽ
			XSSFCellStyle style = workbook.createCellStyle();
			style.setAlignment(HorizontalAlignment.CENTER);
			style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
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
			cell = rowHeader.createCell(11);// ��11��
			cell.setCellValue("�˶��۸�");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(12);// ��12��
			cell.setCellValue("������");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(13);// ��13��
			cell.setCellValue("������");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(14);// ��14��
			cell.setCellValue("����ʱ��");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(15);// ��15��
			cell.setCellValue("����ʱ��");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(16);// ��16��
			cell.setCellValue("ִ��״��");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(17);// ��16��
			cell.setCellValue("ƾƱ�������");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(18);// ��16��
			cell.setCellValue("ƾ֤��");
			cell.setCellStyle(style);
			
			// ��ͷ���------------------
			int index = 1;// �кţ�Ӧ�ӵ����п�ʼ��ÿ��ѭ������++
			int z = 0;
			PrivateCarInvoiceEntity invoice = new PrivateCarInvoiceEntity();
			// �������Ͻ�����д��excel��
			if (privateList.size() > 0) {
				for (int i = 0; i < privateList.size(); i++) {
					
					// ����
					invoice = privateList.get(i);
					String[] size = invoice.getApplyIds().split(",");
					// �õ���������
					int hs = size.length;
					
					for(String id:size){
						// �к�++��2��ʼ
						index++;
						PrivateCarApplyEntity priv = privateCarApplyDAO.getOne(id);
						//�ϲ�����ʱ�䡢��ƾ֤��
						sheet.addMergedRegion(new CellRangeAddress(index, index+hs-1, 15, 15));//���С����һ�С����С����һ��
						sheet.addMergedRegion(new CellRangeAddress(index, index+hs-1, 17, 17));//���С����һ�С����С����һ��
						sheet.addMergedRegion(new CellRangeAddress(index, index+hs-1, 18, 18));//���С����һ�С����С����һ��
						//��;����jsonתΪ���Ÿ������ַ���
						String pass ="";
						if(priv!=null){
							pass = priv.getPassAddress();
						}
						String newPass = "";
						if(pass!=null&&!"".equals(pass)&&pass.startsWith("[")){
							try {
								//��json����Ϊstring
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
							XSSFCell rowCell = row.createCell(0);// ��1��(���)
							rowCell.setCellValue(z + 1 + "");
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
							rowCell.setCellValue(newPass);
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
							
							rowCell = row.createCell(11);// ��12��(˽��-�Ƽ����)
							rowCell.setCellValue(priv.getSureLength());
							rowCell.setCellStyle(style);
							
							rowCell = row.createCell(12);// ��13��(˽��-������)
							rowCell.setCellValue(priv.getApplyMan());
							rowCell.setCellStyle(style);
							
							rowCell = row.createCell(13);// ��14��(˽��-������)
							rowCell.setCellValue(priv.getApproveMan());
							rowCell.setCellStyle(style);
							
							rowCell = row.createCell(14);// ��15��(˽��-����ʱ��)
							rowCell.setCellValue(priv.getApplyTime());
							rowCell.setCellStyle(style);
							
							rowCell = row.createCell(15);// ��16��(˽��-����ʱ��)
							rowCell.setCellValue(invoice.getPaidTime());
							rowCell.setCellStyle(style);
							
							rowCell = row.createCell(16);// ��17��(˽��-ִ��״��)
							rowCell.setCellValue(priv.getIfPerform());
							rowCell.setCellStyle(style);
							
							rowCell = row.createCell(17);// ��18��(˽��-ִ��״��)
							rowCell.setCellValue(invoice.getSum());
							rowCell.setCellStyle(style);
							
							rowCell = row.createCell(18);// ��19��(˽��-ִ��״��)
							rowCell.setCellValue(invoice.getVoucherNum());
							rowCell.setCellStyle(style);
							z++;
						}
					}
					
//					XSSFRow row = sheet.createRow(index);
					// �ϲ���Ӧ��
//					for(int j=0; j < 18; j++){
//						sheet.addMergedRegion(new CellRangeAddress(index, index+hs-1, j, j));//���С����һ�С����С����һ��
//					}
					
//					XSSFCell rowCell = row.createCell(0);// ��1��(���)
//					rowCell.setCellValue(i + 1 + "");
//					rowCell.setCellStyle(style);
					

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
	@Transactional
	@Override
	public int importPrivateCarInvoiceExcel(String fileName) {
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
//			PrivateCarApplyEntity pricar = new PrivateCarApplyEntity();
			PrivateCarInvoiceEntity in = new PrivateCarInvoiceEntity();
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
				
				in.setApplyMan(s[0]);
				in.setApproveMan(s[1]);
				in.setApplyTime(s[2]);
				in.setPaidTime(s[3]);
				in.setSum(s[4]);
				in.setVoucherNum(s[5]);
				in.setStatus("�����");
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
				//�����ó����ڣ������ˣ�����ʱ�䣬�жϿ����Ƿ���ڸ�����
//				int d = privateCarApplyDAO.getPrivateBy3(pricar.getUserCarTime(), pricar.getApplyTime(), pricar.getApplyMan());
//				if(d==0){
//					flag = privateCarApplyDAO.insertEntity1(pricar.getApplyId(), pricar.getDepartment(), pricar.getApplyMan(), pricar.getUserCarTime(), 
//							pricar.getReason(), pricar.getBeginAddress(), pricar.getPassAddress(), pricar.getStatus(), pricar.getDestination(), 
//							pricar.getSingleLength(), pricar.getSureLength(), pricar.getCountLength(), pricar.getApproveMan(), pricar.getApplyTime(), 
//							pricar.getApproveTime(),pricar.getInvoiceSum(), pricar.getVoucherNum());
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
	public String getDateTime() {
	    Date dtDate=new Date();
		SimpleDateFormat sm=new SimpleDateFormat("yyyyMMdd");
		return sm.format(dtDate);
	}
	
	@Override
	public List<PrivateCarInvoiceEntity> selectBackList(String username) {
		return privateCarInvoiceDAO.selectBackList(username);
	}

	//ɾ��
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

	 //�Ǽ�
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
