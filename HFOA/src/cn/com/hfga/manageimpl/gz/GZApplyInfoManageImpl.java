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

	// ����id���ĺ���λ
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

	// ������Ҫȷ��
	@Transactional
	@Override
	public List<GZApplyInfoEntity> getNeedChuan() {
		// TODO Auto-generated method stub
		return gZApplyInfoDAO.getNeedChuan();
	}

	// ��Ҫ̷��ȷ��
	@Transactional
	@Override
	public List<GZApplyInfoEntity> getNeedTan() {
		// TODO Auto-generated method stub
		return gZApplyInfoDAO.getNeedTan();
	}

	// ��ȡ��Ҫ������
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

	// ��ҪС��
	@Transactional
	@Override
	public List<GZApplyInfoEntity> getNeedYin() {
		// TODO Auto-generated method stub
		return gZApplyInfoDAO.getNeedYin();
	}

	// ����
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
	// ��ȡ����������Ϣ
	@Override
	public List<GZApplyInfoEntity> getPersonal(String ApplyUserName) {
		return gZApplyInfoDAO.getPersonal(ApplyUserName);
	}

	@Override
	public List<GZApplyInfoEntity> getSearchInfo(GZSearchInfoDTO gzSearchInfoDTO) {
		// TODO Auto-generated method stub
		String departname = gzSearchInfoDTO.getDepartmentName();// ��ò�������
		String gzkind = gzSearchInfoDTO.getGzKind();
		gzSearchInfoDTO.setSendTo("%" + gzSearchInfoDTO.getSendTo() + "%");
		gzSearchInfoDTO.setUserName("%" + gzSearchInfoDTO.getUserName() + "%");
		if (departname.equals("ȫ��") && gzkind.equals("ȫ��")) {
			return gZApplyInfoDAO.getSearchInfo11(gzSearchInfoDTO.getUserName(), gzSearchInfoDTO.getStartTime(),
					gzSearchInfoDTO.getEndTime(), gzSearchInfoDTO.getSendTo());
		} else if (departname.equals("ȫ��") && !gzkind.equals("ȫ��")) {
			return gZApplyInfoDAO.getSearchInfo10(gzSearchInfoDTO.getUserName(), gzSearchInfoDTO.getStartTime(),
					gzSearchInfoDTO.getEndTime(), gzSearchInfoDTO.getGzKind(), gzSearchInfoDTO.getSendTo());
		} else if (!departname.equals("ȫ��") && gzkind.equals("ȫ��")) {
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

	//Web-��ȡ���������Ϣ
	@Override
	public List<GZApplyInfoEntity> GZManageDisplay(int start, int number) {
		return gZApplyInfoDAO.GZManageDisplay(start,number);
	}

	//Web-��ȡ������Ϣ������
	public int getAllCount() {
		return gZApplyInfoDAO.getAllCount();
	}

	//Web-��ȡ��������
	public List<GZKindEntity> getType() {
		List<GZKindEntity> list = new ArrayList<GZKindEntity>();
		list = gZKindDAO.getType();	
		GZKindEntity all = new GZKindEntity();	
		all.setGZKind("ȫ��");
		all.setID("11");
		list.add(0, all);
		return list;
		//return gZKindDAO.getType();
	}

	//Web-��ѯ
	public List<GZApplyInfoEntity> wGetSearchInfo(GZSearchInfoDTO gzSearchInfoDTO) {

				String departname = gzSearchInfoDTO.getDepartmentName();// ��ò�������
				String gzkind = gzSearchInfoDTO.getGzKind();
				//gzSearchInfoDTO.setSendTo("%" + gzSearchInfoDTO.getSendTo() + "%");
				String user = gzSearchInfoDTO.getUserName();
				String start = gzSearchInfoDTO.getStartTime();
				String end = gzSearchInfoDTO.getEndTime();
				String username = "%"+gzSearchInfoDTO.getUserName()+"%";
				if (departname.equals("ȫ��") && gzkind.equals("ȫ��")) {
					return gZApplyInfoDAO.wGetSearchInfo11(username, start, end);
				} else if (departname.equals("ȫ��") && !gzkind.equals("ȫ��")) {
					return gZApplyInfoDAO.wGetSearchInfo10(gzkind, username, start, end);
				} else if (!departname.equals("ȫ��") && gzkind.equals("ȫ��")) {
					return gZApplyInfoDAO.wGetSearchInfo01(departname, username, start, end);
				} else {
					return gZApplyInfoDAO.wGetSearchInfo00(departname, gzkind, username, start, end);
				}
	}
	//Web-��ѯ
	public List<GZApplyInfoEntity> wGetSearchInfoByPage(GZSearchInfoDTO gzSearchInfoDTO, int s, int e) {

				String departname = gzSearchInfoDTO.getDepartmentName();// ��ò�������
				String gzkind = gzSearchInfoDTO.getGzKind();
				//gzSearchInfoDTO.setSendTo("%" + gzSearchInfoDTO.getSendTo() + "%");
				String user = gzSearchInfoDTO.getUserName();
				String start = gzSearchInfoDTO.getStartTime();
				String end = gzSearchInfoDTO.getEndTime();
				String username = "%"+gzSearchInfoDTO.getUserName()+"%";
				if (departname.equals("ȫ��") && gzkind.equals("ȫ��")) {
					return gZApplyInfoDAO.wGetSearchInfo11ByPage(username, start, end, s, e);
				} else if (departname.equals("ȫ��") && !gzkind.equals("ȫ��")) {
					return gZApplyInfoDAO.wGetSearchInfo10ByPage(gzkind, username, start, end, s, e);
				} else if (!departname.equals("ȫ��") && gzkind.equals("ȫ��")) {
					return gZApplyInfoDAO.wGetSearchInfo01ByPage(departname, username, start, end, s, e);
				} else {
					return gZApplyInfoDAO.wGetSearchInfo00ByPage(departname, gzkind, username, start, end, s, e);
				}
	}
	public int wGetSearchInfoTotal(GZSearchInfoDTO gzSearchInfoDTO, int s, int e){
		String departname = gzSearchInfoDTO.getDepartmentName();// ��ò�������
		String gzkind = gzSearchInfoDTO.getGzKind();
		//gzSearchInfoDTO.setSendTo("%" + gzSearchInfoDTO.getSendTo() + "%");
		String user = gzSearchInfoDTO.getUserName();
		String start = gzSearchInfoDTO.getStartTime();
		String end = gzSearchInfoDTO.getEndTime();
		String username = "%"+gzSearchInfoDTO.getUserName()+"%";
		if (departname.equals("ȫ��") && gzkind.equals("ȫ��")) {
			return gZApplyInfoDAO.wGetSearchInfo11Total(username, start, end);
		} else if (departname.equals("ȫ��") && !gzkind.equals("ȫ��")) {
			return gZApplyInfoDAO.wGetSearchInfo10Total(gzkind, username, start, end);
		} else if (!departname.equals("ȫ��") && gzkind.equals("ȫ��")) {
			return gZApplyInfoDAO.wGetSearchInfo01Total(departname, username, start, end);
		} else {
			return gZApplyInfoDAO.wGetSearchInfo00Total(departname, gzkind, username, start, end);
		}
	}

	//Web-������Ϣ����
	public int export(String[] nlist, String filePath) {
			// �õ����ݼ���
		List<GZApplyInfoEntity> gzList = new ArrayList<GZApplyInfoEntity>();
		gzList = getPrivateListByNum(nlist);
		return exportExcel(gzList, filePath);
		}

	private List<GZApplyInfoEntity> getPrivateListByNum(String[] nlist) {
		List<GZApplyInfoEntity> list = gZApplyInfoDAO.getAll();// ���շ��ص�list
//		for (int t = 0;t<nlist.length;t++)//����numberȡ��Ҫ��������Ϣ
//		{
//			GZApplyInfoEntity p = new GZApplyInfoEntity();
//			p = gZApplyInfoDAO.getGZById(nlist[t]);
//			list.add(p);
//		}
		return list;
	}

	private int exportExcel(List<GZApplyInfoEntity> gzList, String filePath) {
		XSSFWorkbook workbook;
		String sheetName = "����ʹ����Ϣ";
		try {
			workbook = new XSSFWorkbook();
			// ���һ��sheet,sheet��
			XSSFSheet sheet = workbook.createSheet(sheetName);
			// �ϲ���Ԫ�� ������˼�� ��һ�С����һ�С���һ�С����һ��
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 10));
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
			cell.setCellValue("��������");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(5);// ��6��
			cell.setCellValue("����ʱ��");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(6);// ��7��
			cell.setCellValue("������λ");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(7);// ��8��
			cell.setCellValue("��������");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(8);// ��9��
			cell.setCellValue("��ӡ����");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(9);// ��10��
			cell.setCellValue("�Ƿ�����");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(10);// ��11��
			cell.setCellValue("������");
			cell.setCellStyle(style);			
			
			// ��ͷ���------------------
			int index = 1;// �кţ�Ӧ�ӵ����п�ʼ��ÿ��ѭ������++
			GZApplyInfoEntity gz = new GZApplyInfoEntity();
			// �������Ͻ�����д��excel��
			if (gzList.size() > 0) {
				for (int i = 0; i < gzList.size(); i++) {
					// �к�++��2��ʼ
					index++;
					
					// ����
					gz = gzList.get(i);
					
					// �õ���������
					int hs = gzList.size();
					
					// ������Ӧ������
					XSSFRow row = sheet.createRow(index);
					
					XSSFCell rowCell = row.createCell(0);// ��1��(���)
					rowCell.setCellValue(i + 1 + "");
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(1);// ��2��(����-��������)
					rowCell.setCellValue(gz.getID());
					rowCell.setCellStyle(style);

					rowCell = row.createCell(2);// ��3��(����-����)
					rowCell.setCellValue(gz.getDepartment());
					rowCell.setCellStyle(style);

					rowCell = row.createCell(3);// ��4��(����-������)
					rowCell.setCellValue(gz.getApplyUserName());
					rowCell.setCellStyle(style);

					rowCell = row.createCell(4);// ��5��(����-��������)
					rowCell.setCellValue(gz.getReason());
					rowCell.setCellStyle(style);

					rowCell = row.createCell(5);// ��6��(����-����ʱ��)
					rowCell.setCellValue(gz.getApplyTime());
					rowCell.setCellStyle(style);

					rowCell = row.createCell(6);// ��7��(����-������λ)
					rowCell.setCellValue(gz.getSendTo());
					rowCell.setCellStyle(style);

					rowCell = row.createCell(7);// ��8��(����-��������)
					rowCell.setCellValue(gz.getGZKind());
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(8);// ��9��(����-��ӡ����)
					rowCell.setCellValue(gz.getCopies());
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(9);// ��10��(����-�Ƿ�����)
					rowCell.setCellValue(gz.getIsSecret());
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(10);// ��11��(����-������)
					rowCell.setCellValue(gz.getApproveMan());
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
	

}
