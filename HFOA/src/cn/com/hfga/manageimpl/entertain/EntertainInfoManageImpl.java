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

	// ��ȡ�û�����
	public String getPassword(String username) {
		return userDAO.getPassword(username);
	}

	// ͨ����ѯ��Ϣ�����һ����ǰ����
	@Transactional
	@Override
	public EntertainApplyInfoEntity getSearchApplyInfo1(EntertainSearchInfoDTO entertainSearchInfoDTO) {
		String department = entertainSearchInfoDTO.getDepartment();// ��ò�������
		entertainSearchInfoDTO.setManager("%" + entertainSearchInfoDTO.getManager() + "%");
		entertainSearchInfoDTO.setEntertainObject("%" + entertainSearchInfoDTO.getEntertainObject() + "%");
		if (department.equals("ȫ��")) {
			return entertainApplyInfoDAO.getSearchInfo11(entertainSearchInfoDTO.getEntertainObject(),
					entertainSearchInfoDTO.getStartTime(), entertainSearchInfoDTO.getEndTime(),
					entertainSearchInfoDTO.getManager());
		} else {
			return entertainApplyInfoDAO.getSearchInfo00(department, entertainSearchInfoDTO.getEntertainObject(),
					entertainSearchInfoDTO.getStartTime(), entertainSearchInfoDTO.getEndTime(),
					entertainSearchInfoDTO.getManager());
		}
	}

	// ͨ����ǰ�������Ż�ö�Ӧ�º�ͨ����Ϣ
	@Transactional
	@Override
	public List<EntertainRegisterInfoEntity> getSearchRegisterInfo(String number) {

		return entertainRegisterInfoDAO.getSearchRegisterInfo(number);
	}

	// ͨ�� ��ǰ�������Ż�ö�Ӧ�º�������Ϣ
	@Transactional
	@Override
	public List<EntertainRegisterInfoEntity> getReadyRegisterInfo(String number) {
		return entertainRegisterInfoDAO.getReadyRegisterInfo(number);
	}
	
	// ��ѯ����
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
	 * EntertainInfoEntity entertain = new EntertainInfoEntity(); //ȡ����ǰ��������ɵ�ֵ
	 * List<EntertainApplyInfoEntity> apply = entertainApplyInfoDAO.getAll();
	 * //ѭ������apply�е���Ŀ ȡ����Ӧ���º����ݴ浽entertain��
	 * 
	 * //������������ֵ����list�� list.add(entertain); return null; }
	 */

	// ��ȡ�������Ŀ����
	/*
	 * public int getAllApprovedCount() { return
	 * entertainApplyInfoDAO.getAllApprovedCount();
	 */

	// ���ݲ�ѯ��������б�
	@Transactional
	@Override
	public List<EntertainInfoEntity> getList(EntertainSearchInfoDTO entertainSearchInfoDTO) {
		List<EntertainInfoEntity> list = new ArrayList<EntertainInfoEntity>();// ���շ��ص�list
		List<EntertainApplyInfoEntity> apply = new ArrayList<EntertainApplyInfoEntity>();
		String department = entertainSearchInfoDTO.getDepartment();
		String entertainObject = entertainSearchInfoDTO.getEntertainObject();
		if(!entertainObject.equals("ȫ��"))
		{entertainObject = "%"+entertainObject+"%";}
		String m = entertainSearchInfoDTO.getManager();
		String manager = "%"+m+"%";
		if (department.equals("ȫ��") && entertainObject.equals("ȫ��")){
			apply = entertainApplyInfoDAO.getSearchApproved11(manager,
					entertainSearchInfoDTO.getStartTime(),entertainSearchInfoDTO.getEndTime());
		}
		else if (!department.equals("ȫ��") && entertainObject.equals("ȫ��")){
			apply = entertainApplyInfoDAO.getSearchApproved01(department,manager,
					entertainSearchInfoDTO.getStartTime(),entertainSearchInfoDTO.getEndTime());
		}
		else if (department.equals("ȫ��") && !entertainObject.equals("ȫ��")){
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
				entertainSearchInfoDTO.getEntertainObject()); // ȡ������������ǰlist
*/		for (int i = 0; i < apply.size(); i++) { // ����apply��
			EntertainInfoEntity entertain = new EntertainInfoEntity(); // �洢����
			String number = apply.get(i).getNumber(); // ȡ����ǰ���е��������ţ����ڲ�ѯ��Ӧ���º��
			List<EntertainRegisterInfoEntity> register = getSearchRegisterInfo(number); // ȡ����Ӧ������ɵ��º��
			if (register.size() == apply.get(i).getRegisterNum()) { // ����º����ֵ����Ϊ��Ч��Ϣ���洢��Ҫ�ύ��list��
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
			} // ����º��û��ֵ���������½��б���
		}
		return list;

	}

	// ����
	@Transactional
	@Override
	public List<EntertainApplyInfoEntity> testList(EntertainSearchInfoDTO entertainSearchInfoDTO) {
		List<EntertainApplyInfoEntity> apply = entertainApplyInfoDAO.getApplyList(
				entertainSearchInfoDTO.getDepartment(), entertainSearchInfoDTO.getStartTime(),
				entertainSearchInfoDTO.getEndTime(), entertainSearchInfoDTO.getManager(),
				entertainSearchInfoDTO.getEntertainObject()); // ȡ������������ǰlist
		return apply;
	}

	// Web-��ҳ-������д���ϸ-��ʾ
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
	 * ArrayList<EntertainInfoEntity>();//���շ��ص�list
	 * List<EntertainApplyInfoEntity> apply =
	 * entertainApplyInfoDAO.getApplyCompleted(); //ȡ������ɵ���ǰlist for(int i = 0;
	 * i < apply.size(); i++){ // ����apply�� EntertainInfoEntity entertain = new
	 * EntertainInfoEntity(); //�洢���� String number = apply.get(i).getNumber();
	 * //ȡ����ǰ���е��������ţ����ڲ�ѯ��Ӧ���º�� List<EntertainRegisterInfoEntity> register =
	 * getSearchRegisterInfo(number); //ȡ����Ӧ������ɵ��º��
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
	
	//�������ɵ��д���ϸ����
	@Transactional
	@Override
	public int getAllCompletedCount() {
		// TODO Auto-generated method stub
		return entertainApplyInfoDAO.getAllCompletedCount();
	}
	
	//Web-������д���ϸ-������
	@Transactional
	@Override
	public int export(String[] nlist,String path) {
		// �õ����ݼ���
		List<EntertainInfoEntity> entertainList = new ArrayList<EntertainInfoEntity>();
		entertainList = getEntertainListByNum(nlist);
			return exportExcel(entertainList, path);
		}
	
	//���ȫ������ɵ��д���Ϣ
	  public List<EntertainInfoEntity> getEntertainList() {
		List<EntertainInfoEntity> list = new ArrayList<EntertainInfoEntity>();// ���շ��ص�list
		List<EntertainApplyInfoEntity> apply = entertainApplyInfoDAO.getApplyCompleted(); // ȡ������ɵ���ǰlist
		for (int i = 0; i < apply.size(); i++) { // ����apply��
			EntertainInfoEntity entertain = new EntertainInfoEntity(); // �洢����
			String number = apply.get(i).getNumber(); // ȡ����ǰ���е��������ţ����ڲ�ѯ��Ӧ���º��
			List<EntertainRegisterInfoEntity> register = getSearchRegisterInfo(number); // ȡ����Ӧ������ɵ��º��
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
	
	//���ȫ������ɵ��д���Ϣ
	  public List<EntertainInfoEntity> getEntertainListByNum(String[] nlist) {
		List<EntertainApplyInfoEntity> listAll = entertainApplyInfoDAO.getApplyCompleted();// ���շ��ص�
		//��ֻȡ��ǰҳ��¼����ȡ��ȫ�� nlist����ǰҳ��¼����->nlist1��ȫ����   3/27
		String[] nlist1 = new String[listAll.size()];
		int z=0;
		for(EntertainApplyInfoEntity en:listAll){
			nlist1[z] = en.getNumber();
			z++;
		}
		List<EntertainInfoEntity> list = new ArrayList<EntertainInfoEntity>();// ���շ��ص�list
		//List<EntertainApplyInfoEntity> apply = new ArrayList<EntertainApplyInfoEntity>(); // ������ǰ����list
		for (int t = 0;t<nlist1.length;t++)//����numberȡ��Ҫ��������ǰ��Ϣ
		{
			EntertainApplyInfoEntity app = new EntertainApplyInfoEntity(); //�洢����
			EntertainInfoEntity entertain = new EntertainInfoEntity(); // �洢����
			app = entertainApplyInfoDAO.applyDetail(nlist1[t]);
			/*apply.add(app);*/
			List<EntertainRegisterInfoEntity> register = getSearchRegisterInfo(nlist1[t]); // ȡ����Ӧ������ɵ��º��
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
	  
	
	 // ����excel���巽��
	@Transactional
	@Override
	public int exportExcel(List<EntertainInfoEntity> entertainList, String path) {
		// ����һ��������
		XSSFWorkbook workbook;
		String sheetName = "ҵ���д���ϸ��";
		try {
			workbook = new XSSFWorkbook();
			// ���һ��sheet,sheet��
			XSSFSheet sheet = workbook.createSheet(sheetName);
			// �ϲ���Ԫ�� ������˼�� ��һ�С����һ�С���һ�С����һ��
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 22));
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
			sheet.autoSizeColumn(18);
			sheet.autoSizeColumn(19);
			sheet.autoSizeColumn(20);
			sheet.autoSizeColumn(21);
			sheet.autoSizeColumn(22);
			
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
			cell.setCellValue("����ʱ��");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(4);// ��5��
			cell.setCellValue("�д�����");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(5);// ��6��
			cell.setCellValue("����");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(6);// ��7��
			cell.setCellValue("�д�����");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(7);// ��8��
			cell.setCellValue("��ͬ����");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(8);// ��9��
			cell.setCellValue("�˾���׼");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(9);// ��10��
			cell.setCellValue("��Ԥ��");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(10);// ��11��
			cell.setCellValue("�д����");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(11);// ��12��
			cell.setCellValue("������");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(12);// ��13��
			cell.setCellValue("������");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(13);// ��14��
			cell.setCellValue("��Ʊ��������");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(14);// ��15��
			cell.setCellValue("��Ʊ����");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(15);// ��16��
			cell.setCellValue("��Ʊ���");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(16);// ��17��
			cell.setCellValue("��Ʊ����");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(17);// ��18��
			cell.setCellValue("����ʱ��");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(18);// ��19��
			cell.setCellValue("ƾ֤��");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(19);// ��20��
			cell.setCellValue("��Ʊ���ߵ�λ");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(20);// ��21��
			cell.setCellValue("��ע");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(21);// ��22��
			cell.setCellValue("�Ƿ�¼");
			cell.setCellStyle(style);
			cell = rowHeader.createCell(22);// ��22��
			cell.setCellValue("ʵ����������");
			cell.setCellStyle(style);
			
			// ��ͷ���------------------
			int index = 1;// �кţ�Ӧ�ӵ����п�ʼ��ÿ��ѭ������++
			EntertainInfoEntity entertain = new EntertainInfoEntity();
			// �������Ͻ�����д��excel��
			if (entertainList.size() > 0) {
				for (int i = 0; i < entertainList.size(); i++) {
					// �к�++��2��ʼ
					index++;
					
					// ����
					entertain = entertainList.get(i);
					
					// �õ���������
					int hs = entertain.getList().size();
					
					// ������Ӧ������
					for(int r=0; r<hs; r++){
					XSSFRow row = sheet.createRow(r+index);
					
					// �ϲ���Ӧ��
					for(int j=0; j < 13; j++){
					sheet.addMergedRegion(new CellRangeAddress(index, index+hs-1, j, j));//���С����һ�С����С����һ��
					}
					
					XSSFCell rowCell = row.createCell(0);// ��1��
					rowCell.setCellValue(i + 1 + "");
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(1);// ��2��(��ǰ-��������)
					rowCell.setCellValue(entertain.getNumber());
					rowCell.setCellStyle(style);

					rowCell = row.createCell(2);// ��3��(��ǰ-����)
					rowCell.setCellValue(entertain.getDepartment());
					rowCell.setCellStyle(style);

					rowCell = row.createCell(3);// ��4��(��ǰ-����ʱ��)
					rowCell.setCellValue(entertain.getApplyTime());
					rowCell.setCellStyle(style);

					rowCell = row.createCell(4);// ��5��(��ǰ-�д�����)
					rowCell.setCellValue(entertain.getEntertainObject());
					rowCell.setCellStyle(style);

					rowCell = row.createCell(5);// ��6��(��ǰ-����)
					rowCell.setCellValue(entertain.getEntertainReason());
					rowCell.setCellStyle(style);

					rowCell = row.createCell(6);// ��7��(��ǰ-�д�����)
					rowCell.setCellValue(entertain.getEntertainNum());
					rowCell.setCellStyle(style);

					rowCell = row.createCell(7);// ��8��(��ǰ-��ͬ����)
					rowCell.setCellValue(entertain.getAccompanyNum());
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(8);// ��9��(��ǰ-�˾�Ԥ��)
					rowCell.setCellValue(entertain.getPerBudget());
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(9);// ��10��(��ǰ-��Ԥ��)
					rowCell.setCellValue(entertain.getMasterBudget());
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(10);// ��11��(��ǰ-�д����)
					rowCell.setCellValue(entertain.getEntertainCategory());
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(11);// ��12��(��ǰ-������)
					rowCell.setCellValue(entertain.getManager());
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(12);// ��13��(��ǰ-������)
					rowCell.setCellValue(entertain.getApprover());
					rowCell.setCellStyle(style);
				 
				 // �º��ĵ���
				  	
					rowCell = row.createCell(13);// ��14��(�º�-��Ʊ����)
					rowCell.setCellValue(entertain.getList().get(r).getInvoiceDate());
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(14);// ��15��(�º�-��Ʊ����)
					rowCell.setCellValue(entertain.getList().get(r).getInvoiceContent());
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(15);// ��16��(�º�-��Ʊ���)
					rowCell.setCellValue(entertain.getList().get(r).getInvoiceSum());
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(16);// ��17��(�º�-��Ʊ����)
					rowCell.setCellValue(entertain.getList().get(r).getInvoiceNum());
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(17);// ��18��(�º�-����ʱ��)
					rowCell.setCellValue(entertain.getList().get(r).getPaidTime());
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(18);// ��19��(�º�-��������)
					rowCell.setCellValue(entertain.getList().get(r).getVoucherNum());
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(19);// ��20��(�º�-��Ʊ���ߵ�λ)
					rowCell.setCellValue(entertain.getList().get(r).getInvoiceUnit());
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(20);// ��21��(�º�-��ע)
					rowCell.setCellValue(entertain.getList().get(r).getRemark());
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(21);// ��21��(�Ƿ�¼)
					rowCell.setCellValue("1".equals(entertain.getIfBefore())?"��":"��");
					rowCell.setCellStyle(style);
					
					rowCell = row.createCell(22);// ��21��(�Ƿ�¼)
					rowCell.setCellValue(entertain.getBeforeDate());
					rowCell.setCellStyle(style);
				}
					index=index+hs-1;
				}
			}
			
			// ���ļ����浽ָ��λ��
			FileOutputStream out = new FileOutputStream(path);
			workbook.write(out);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	// web-��������-������ĵ�����
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
							newPass += obj.get("name1")+""+obj.get("value")+"ƿ  ";
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

/*	// web-��ý�ʮ������
	public Object getYear() {
		List<Map<String,Integer>>  list = new ArrayList<Map<String,Integer>>();
		 Calendar a=Calendar.getInstance();
		 Integer y = a.get(Calendar.YEAR);
		 for(int i=0;i<10;i++){
			 Map<String,Integer> jsonMap = new HashMap<String,Integer>();// ����map
			 jsonMap.put("id", i);
			 jsonMap.put("year", y-i);
			 list.add(jsonMap);}
		return list;
	}*/
	
	// web-��ö�Ӧ������б�
		public Object getYear() {
			List<Map<String,Integer>>  list = new ArrayList<Map<String,Integer>>();
			 Calendar a=Calendar.getInstance();
			 Integer y = a.get(Calendar.YEAR);
			 List<String> ylist = new ArrayList<String>(); 
			 ylist = entertainAnnualBudgetDAO.getYear(y+2);
			 for(int i=0;i<ylist.size();i++){
				 Map<String,Integer> jsonMap = new HashMap<String,Integer>();// ����map
				 jsonMap.put("id", i);
				 jsonMap.put("year", Integer.parseInt(ylist.get(i)));
				 list.add(jsonMap);}
			return list;
		}
	
	// ��ȡ��ǰͨ�����º����˵�List
	@Transactional
	@Override
	public List<EntertainInfoEntity> getAPassList() {
		List<EntertainInfoEntity> list = new ArrayList<EntertainInfoEntity>();// ���շ��ص�list
		List<EntertainApplyInfoEntity> apply = new ArrayList<EntertainApplyInfoEntity>();
		apply = entertainApplyInfoDAO.getAllPassApply();
		for (int i = 0; i < apply.size(); i++) { // ����apply��
			EntertainInfoEntity entertain = new EntertainInfoEntity(); // �洢����
			String number = apply.get(i).getNumber(); // ȡ����ǰ���е��������ţ����ڲ�ѯ��Ӧ���º��
			List<EntertainRegisterInfoEntity> register = getReadyRegisterInfo(number); // ȡ����Ӧ������ɵ��º��
			if (register.size() == apply.get(i).getRegisterNum()) { // ����º����ֵ����Ϊ��Ч��Ϣ���洢��Ҫ�ύ��list��
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
			} // ����º��û��ֵ���������½��б���
		}
		return list;
	}
	
	public Object wGetAllUsed() {
		//List<Map<String,String>> sumList = new ArrayList<Map<String,String>>(); // ����ǰ�˵�list
		//��ȡ��ǰ���
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
		
		Map<String,String> jsonMap = new HashMap<String,String>();// ����map
		for(int i=0;i<list.size();i++){
			Object[] objects=(Object[])list.get(i);
			String department = objects[2].toString();
			String sum = objects[3].toString();
			
			if(department.equals("��Ϣ������")){
				xx = xx + Integer.parseInt(sum);
			}
			else if(department.equals("�ۺϰ칫��")){
				zh = zh + Integer.parseInt(sum);	
			}
			else if(department.equals("����")){
				cw = cw + Integer.parseInt(sum);	
			}
			else if(department.equals("������")){
				zl = zl + Integer.parseInt(sum);	
			}
			else if(department.equals("ͨ����Ŀ��")){
				th = th + Integer.parseInt(sum);	
			}
			else if(department.equals("�����Ʒ��")){
				jd = jd + Integer.parseInt(sum);	
			} 
			else if(department.equals("�г���")){
				sc = sc + Integer.parseInt(sum);	
			}
			else if(department.equals("�滮Ͷ�ʲ�")){
				gh = gh + Integer.parseInt(sum);	 
			}
			else{
				wrj = wrj + Integer.parseInt(sum);
			}
		}
		jsonMap.put("�ۺϰ칫��", Integer.toString(zh));
		jsonMap.put("�ƻ�����", Integer.toString(cw));
		jsonMap.put("�滮Ͷ�ʲ�", Integer.toString(gh));
		jsonMap.put("������ȫ��", Integer.toString(zl));
		jsonMap.put("�г�������", Integer.toString(sc));
		jsonMap.put("��Ϣ������", Integer.toString(xx));
		jsonMap.put("�����Ʒ��", Integer.toString(jd));
		jsonMap.put("ͨ����Ŀ��", Integer.toString(th));
		jsonMap.put("���˻���Ŀ��", Integer.toString(wrj));
		
		return jsonMap;
	}

	// web-������ݲ�ѯ����Ϣ
	@Transactional
	@Override
	public Object wGetSearchAnnual(String year) {
		
		List<EntertainAnnualInfoDTO> newlist= new ArrayList<EntertainAnnualInfoDTO>();
    	List<EntertainAnnualBudgetEntity> list= new ArrayList<EntertainAnnualBudgetEntity>();
    	
    	list = entertainAnnualBudgetDAO.wGetAnnualBudget(year);// ��õ���ȫ������
    	for(int i=0;i<list.size();i++){
    	EntertainAnnualInfoDTO aNewList = new EntertainAnnualInfoDTO(); // ���ձ�Ĵ洢����
        EntertainAnnualBudgetEntity aList = new EntertainAnnualBudgetEntity(); // �м��Ĵ洢����
    	String time=list.get(i).getTime(); //��õ�i���ı��ƴ���
    	aList=list.get(i); //��õ��������еĵ�i��
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
		return newlist; //�������ձ�
		//return entertainAnnualBudgetDAO.wGetSearchAnnual(year);
	}

	// web-���ѡ�����ÿ�����ŵķ�����
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
		
		Map<String,String> jsonMap = new HashMap<String,String>();// ����map
		for(int i=0;i<list.size();i++){
			Object[] objects=(Object[])list.get(i);
			String department = objects[2].toString();
			String sum = objects[3].toString();
			
			if(department.equals("��Ϣ������")){
				xx = xx + Double.parseDouble(sum);
			}
			else if(department.equals("�ۺϰ칫��")){
				zh = zh + Double.parseDouble(sum);	
			}
			else if(department.equals("����")){
				cw = cw + Double.parseDouble(sum);	
			}
			else if(department.equals("������")){
				zl = zl + Double.parseDouble(sum);	
			}
			else if(department.equals("ͨ����Ŀ��")){
				th = th + Double.parseDouble(sum);	
			}
			else if(department.equals("�����Ʒ��")){
				jd = jd + Double.parseDouble(sum);	
			} 
			else if(department.equals("�г���")){
				sc = sc + Double.parseDouble(sum);	
			}
			else if(department.equals("�滮Ͷ�ʲ�")){
				gh = gh + Double.parseDouble(sum);	 
			}
			else if(department.equals("���˻���Ŀ��")){
				wrj = wrj + Double.parseDouble(sum);
			}
			else if(department.equals("��������")){
				cp = cp + Double.parseDouble(sum);
			}
			else {
				gsld=gsld + Double.parseDouble(sum);
			}
		}
		jsonMap.put("�ۺϰ칫��", Double.toString(zh));
		jsonMap.put("�ƻ�����", Double.toString(cw));
		jsonMap.put("�滮Ͷ�ʲ�", Double.toString(gh));
		jsonMap.put("������ȫ��", Double.toString(zl));
		jsonMap.put("�г�������", Double.toString(sc));
		jsonMap.put("��Ϣ������", Double.toString(xx));
		jsonMap.put("�����Ʒ��", Double.toString(jd));
		jsonMap.put("ͨ����Ŀ��", Double.toString(th));
 		jsonMap.put("���˻���Ŀ��", Double.toString(wrj));
 		jsonMap.put("��˾�쵼", Double.toString(gsld));
 		jsonMap.put("��������", Double.toString(cp));
		
		return jsonMap;
	}

}


