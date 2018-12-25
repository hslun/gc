			package cn.com.hfga.manageimpl.travelexpenses;

import cn.com.hfga.dao.travelExpenses.ApproveExpensesDAO;
import cn.com.hfga.dto.travelexpenses.ApplyExpensesDTO;
import cn.com.hfga.dto.travelexpenses.ApproveExpensesDTO;
import cn.com.hfga.dto.travelexpenses.TravelAddressDTO;
import cn.com.hfga.manager.travelexpenses.ApproveExpensesManage;
import net.sf.json.JSONArray;

import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.bouncycastle.mail.smime.handlers.pkcs7_mime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@Service("approveExpensesManage")
public class ApproveExpensesManageImpl implements ApproveExpensesManage {
  @Autowired
  private ApproveExpensesDAO approveExpensesDAO;
  @Autowired
  private JdbcTemplate jdbcTemplate;

  /**
   * �������--�Ǽ�
   *
   * @param approveExpense
   * @return
   */
  @Override
  public int insertFinanceReview(ApproveExpensesDTO approveExpense) {
	 System.out.println("���������Ϊ"+approveExpense);
	 if(null==approveExpense.getTestSite()||approveExpense.getTestSite().equals("")){
		 approveExpense.setTestSite("");
	 }
	 if(null==approveExpense.getIllustration()||approveExpense.getIllustration().equals("null")){
		 approveExpense.setIllustration("");
	 }
    approveExpense.setId(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
    StringBuffer sql = new StringBuffer("INSERT INTO b_travelexpensesapprove (ID,TravelExpenseId,VehicleCost," +
        "FoodAllowance,HotelExpense,ParValueAllowance,UrbanTraffic,OtherCost,RepayCost,SupplementalCost,InputTax," +
        "TotalExpenses,PaidTime,FundSource,VoucherNum,IsTestCost,TestSite,Illustration) VALUES (");
    sql.append("'" + approveExpense.getId() + "','" + approveExpense.getTravelExpenseId() + "',");
    sql.append("" + approveExpense.getVehicleCost() + "," + approveExpense.getFoodAllowance() + ",");
    sql.append("" + approveExpense.getHotelExpense() + "," + approveExpense.getParValueAllowance() + ",");
    sql.append("" + approveExpense.getUrbanTraffic() + "," + approveExpense.getOtherCost() + ",");
    sql.append("" + approveExpense.getRepayCost() + "," + approveExpense.getSupplementalCost() + ",");
    sql.append("" + approveExpense.getInputTax() + "," + approveExpense.getTotalExpenses() + ",");
    sql.append("'" + approveExpense.getPaidTime() + "','" + approveExpense.getFundSource() + "',");
    sql.append("'" + approveExpense.getVoucherNum() + "','" + approveExpense.getIsTestCost() + "',");
    sql.append("'" + approveExpense.getTestSite() + "','" + approveExpense.getIllustration() + "')");
    System.out.println(sql);
    int result;
    result = jdbcTemplate.update(sql.toString());
    return result;
  }

  /**
   * web�˲������--�ǼǺ��޸�
   *
   * @param approveExpense
   * @return
   */
  @Transactional
  @Override
  public int modifyFinanceReview(ApproveExpensesDTO approveExpense) {
    return approveExpensesDAO.modifyFinanceReview(approveExpense.getId(), approveExpense.getVehicleCost(),
        approveExpense.getFoodAllowance(), approveExpense.getHotelExpense(), approveExpense.getParValueAllowance(),
        approveExpense.getUrbanTraffic(), approveExpense.getOtherCost(), approveExpense.getRepayCost(),
        approveExpense.getSupplementalCost(), approveExpense.getInputTax(), approveExpense.getTotalExpenses(),
        approveExpense.getPaidTime(), approveExpense.getFundSource(), approveExpense.getVoucherNum(),
        approveExpense.getIsTestCost(), approveExpense.getTestSite(), approveExpense.getIllustration());
  }

  /**
   * �������--����TravelExpenseId�鿴
   *
   * @param travelExpenseId
   * @return
   */
  @Override
  public List<ApproveExpensesDTO> getApproveExpensesByTId(String travelExpenseId) {
	  System.out.println("����"+approveExpensesDAO.getApproveExpensesByTId(travelExpenseId));
    return approveExpensesDAO.getApproveExpensesByTId(travelExpenseId);
  }

  /**
   * �������--����id�鿴
   *
   * @param id
   * @return
   */
  @Override
  public List<ApproveExpensesDTO> getApproveExpensesById(String id) {
    return approveExpensesDAO.getApproveExpensesById(id);
  }

  /**
   * web�˲���Ǽ����ݳ�ʼ��������ز�ѯ
   *
   * @param approveExpense
   * @return
   */
  @Override
  public List<ApproveExpensesDTO> searchApproveExpenses(ApproveExpensesDTO approveExpense) {
    StringBuffer sql = new StringBuffer("select * from b_travelexpensesapprove where 1=1");
    sql.append(" and travelExpenseId = " + approveExpense.getTravelExpenseId() + "");
    if (approveExpense.getFundSource() != null)
      if (!"".equals(approveExpense.getFundSource())) {
        sql.append(" and FundSource like '%" + approveExpense.getFundSource() + "%'");
      }
    if (approveExpense.getVoucherNum() != null)
      if (!"".equals(approveExpense.getVoucherNum())) {
        sql.append(" and VoucherNum like '%" + approveExpense.getVoucherNum() + "%'");
      }
    if (approveExpense.getIsTestCost() != null)
      if (!"".equals(approveExpense.getIsTestCost())) {
        sql.append(" and IsTestCost like '%" + approveExpense.getIsTestCost() + "%'");
      }
    if (approveExpense.getTestSite() != null)
      if (!"".equals(approveExpense.getTestSite())) {
        sql.append(" and TestSite like '%" + approveExpense.getTestSite() + "%'");
      }
    if (approveExpense.getSearchBeginTime() != null)
      if (!"".equals(approveExpense.getSearchBeginTime())) {
        sql.append(" and PaidTime >= " +"'"+ approveExpense.getSearchBeginTime()+"'" + "");
      }
    if (approveExpense.getSearchEndTime() != null)
      if (!"".equals(approveExpense.getSearchEndTime())) {
        sql.append(" and PaidTime <= " +"'"+ approveExpense.getSearchEndTime()+"'" + "");
      }
    sql.append(" order by ID desc");

    System.out.println(sql);
    List<Map<String, Object>> queryForList = jdbcTemplate.queryForList(sql.toString());

    List<ApproveExpensesDTO> list = new ArrayList<ApproveExpensesDTO>();
    for (Map<String, Object> map : queryForList) {
      ApproveExpensesDTO aE = new ApproveExpensesDTO();
      aE.setId(map.get("ID").toString());
      aE.setTravelExpenseId(map.get("TravelExpenseId").toString());
      aE.setVehicleCost(map.get("VehicleCost") == null ? 0.0f : Float.parseFloat(map.get("VehicleCost").toString()));
      aE.setFoodAllowance(map.get("FoodAllowance") == null ? 0.0f : Float.parseFloat(map.get("FoodAllowance")
          .toString()));
      aE.setHotelExpense(map.get("HotelExpense") == null ? 0.0f : Float.parseFloat(map.get("HotelExpense").toString()));
      aE.setParValueAllowance(map.get("ParValueAllowance") == null ? 0.0f : Float.parseFloat(map.get
          ("ParValueAllowance").toString()));
      aE.setUrbanTraffic(map.get("UrbanTraffic") == null ? 0.0f : Float.parseFloat(map.get("UrbanTraffic").toString()));
      aE.setOtherCost(map.get("OtherCost") == null ? 0.0f : Float.parseFloat(map.get("OtherCost").toString()));
      aE.setRepayCost(map.get("RepayCost") == null ? 0.0f : Float.parseFloat(map.get("RepayCost").toString()));
      aE.setSupplementalCost(map.get("SupplementalCost") == null ? 0.0f : Float.parseFloat(map.get
          ("SupplementalCost").toString()));
      aE.setInputTax(map.get("InputTax") == null ? 0.0f : Float.parseFloat(map.get("InputTax").toString()));
      aE.setTotalExpenses(map.get("TotalExpenses") == null ? 0.0f : Float.parseFloat(map.get("TotalExpenses")
          .toString()));
      aE.setPaidTime(map.get("PaidTime") == null ? "" : map.get("PaidTime").toString());
      aE.setFundSource(map.get("FundSource") == null ? "" : map.get("FundSource").toString());
      aE.setVoucherNum(map.get("VoucherNum") == null ? "" : map.get("VoucherNum").toString());
      aE.setIsTestCost(map.get("IsTestCost") == null ? "" : map.get("IsTestCost").toString());
      aE.setTestSite(map.get("TestSite") == null ? "" : map.get("TestSite").toString());
      aE.setIllustration(map.get("Illustration") == null ? "" : map.get("Illustration").toString());
      list.add(aE);
    }
    return list;
  }

  @Override
  public List<ApproveExpensesDTO> listApproveExpenses(String q) {
    StringBuffer sql = new StringBuffer("select * from b_travelexpensesapprove where 1=1");
      if (!"".equals(q)) {
        sql.append(" and TestSite like '%" + q + "%'");
      }
    sql.append(" order by ID desc");

    System.out.println(sql);
    List<Map<String, Object>> queryForList = jdbcTemplate.queryForList(sql.toString());
    System.out.println("������"+queryForList.size());
    for(int i=0;i<queryForList.size()-1;i++){
    	for(int j=queryForList.size()-1;j>i;j--){
    		System.out.println("j��"+j);
    		 if  (queryForList.get(j).get("TestSite").equals(queryForList.get(i).get("TestSite")))  {       
    			 queryForList.remove(j);       
               }        
    	}
    }
    List<ApproveExpensesDTO> list = new ArrayList<ApproveExpensesDTO>();
    for (Map<String, Object> map : queryForList) {
      ApproveExpensesDTO aE = new ApproveExpensesDTO();
      aE.setId(map.get("ID").toString());
      aE.setTravelExpenseId(map.get("TravelExpenseId").toString());
      aE.setVehicleCost(map.get("VehicleCost") == null ? 0.0f : Float.parseFloat(map.get("VehicleCost").toString()));
      aE.setFoodAllowance(map.get("FoodAllowance") == null ? 0.0f : Float.parseFloat(map.get("FoodAllowance")
          .toString()));
      aE.setHotelExpense(map.get("HotelExpense") == null ? 0.0f : Float.parseFloat(map.get("HotelExpense").toString()));
      aE.setParValueAllowance(map.get("ParValueAllowance") == null ? 0.0f : Float.parseFloat(map.get
          ("ParValueAllowance").toString()));
      aE.setUrbanTraffic(map.get("UrbanTraffic") == null ? 0.0f : Float.parseFloat(map.get("UrbanTraffic").toString()));
      aE.setOtherCost(map.get("OtherCost") == null ? 0.0f : Float.parseFloat(map.get("OtherCost").toString()));
      aE.setRepayCost(map.get("RepayCost") == null ? 0.0f : Float.parseFloat(map.get("RepayCost").toString()));
      aE.setSupplementalCost(map.get("SupplementalCost") == null ? 0.0f : Float.parseFloat(map.get
          ("SupplementalCost").toString()));
      aE.setInputTax(map.get("InputTax") == null ? 0.0f : Float.parseFloat(map.get("InputTax").toString()));
      aE.setTotalExpenses(map.get("TotalExpenses") == null ? 0.0f : Float.parseFloat(map.get("TotalExpenses")
          .toString()));
      aE.setPaidTime(map.get("PaidTime") == null ? "" : map.get("PaidTime").toString());
      aE.setFundSource(map.get("FundSource") == null ? "" : map.get("FundSource").toString());
      aE.setVoucherNum(map.get("VoucherNum") == null ? "" : map.get("VoucherNum").toString());
      aE.setIsTestCost(map.get("IsTestCost") == null ? "" : map.get("IsTestCost").toString());
      aE.setTestSite(map.get("TestSite") == null ? "" : map.get("TestSite").toString());
      aE.setIllustration(map.get("Illustration") == null ? "" : map.get("Illustration").toString());
      list.add(aE);
    }
    return list;
  }

private int exportapproveExpenses(List<ApplyExpensesDTO> applyExpenses, String path) {
	    // ����һ��������
	    XSSFWorkbook workbook;
	    try {
	      workbook = new XSSFWorkbook();
	      // ���һ��sheet,sheet��
	      XSSFSheet sheet = workbook.createSheet("���÷Ѵ��Ǽ��б�");
	      // �ϲ���Ԫ�� ������˼�� ��һ�С����һ�С���һ�С����һ��
	      sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 31));
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
	      titleCell.setCellValue("���÷��ѵǼ��б�");
	      titleCell.setCellStyle(titleStyle);
	      // ------------����Ϊ��һ��------------
	      // �ں���λ�õ�������Ӧ
	      sheet.setColumnWidth(0, 4700);
	      sheet.setColumnWidth(1, 4000);
	      sheet.autoSizeColumn(2);
	      sheet.autoSizeColumn(3);
	      sheet.setColumnWidth(4, 3300);
	      sheet.setColumnWidth(5, 3300);
	      sheet.setColumnWidth(6, 4000);
	      sheet.setColumnWidth(7, 3300);
	      sheet.setColumnWidth(8, 3300);
	      sheet.autoSizeColumn(9);
	      sheet.autoSizeColumn(10);
	      sheet.setColumnWidth(11, 3700);
	      sheet.setColumnWidth(12, 3300);
	      sheet.autoSizeColumn(13);
	      sheet.autoSizeColumn(14);
	      sheet.autoSizeColumn(15);
	      // �����������ĵ�Ԫ���ʽ
	      XSSFCellStyle style = workbook.createCellStyle();
	      style.setAlignment(HorizontalAlignment.CENTER);
	      // ���õڶ��б�ͷ
	      XSSFRow rowHeader = sheet.createRow(1);
	      XSSFCell cell = rowHeader.createCell(0);// ��һ��
	      cell.setCellValue("��������");
	      cell.setCellStyle(style);
	      cell = rowHeader.createCell(1);// �ڶ���
	      cell.setCellValue("����");
	      cell.setCellStyle(style);
	      cell = rowHeader.createCell(2);// ������
	      cell.setCellValue("������");
	      cell.setCellStyle(style);
	      cell = rowHeader.createCell(3);// ������
	      cell.setCellValue("����");
	      cell.setCellStyle(style);
	      cell = rowHeader.createCell(4);// ������
	      cell.setCellValue("������");
	      cell.setCellStyle(style);
	      cell = rowHeader.createCell(5);// ������
	      cell.setCellValue("�����");
	      cell.setCellStyle(style);
	      cell = rowHeader.createCell(6);// ������
	      cell.setCellValue("��ͨ��ʽ");
	      cell.setCellStyle(style);
	      cell = rowHeader.createCell(7);// �ڰ���
	      cell.setCellValue("��������");
	      cell.setCellStyle(style);
	      cell = rowHeader.createCell(8);// �ھ���
	      cell.setCellValue("��������");
	      cell.setCellStyle(style);
	      cell = rowHeader.createCell(9);// ��ʮ��
	      cell.setCellValue("��������");
	      cell.setCellStyle(style);
	      cell = rowHeader.createCell(10);// ��ʮһ��
	      cell.setCellValue("��Ԥ��");
	      cell.setCellStyle(style);
	      cell = rowHeader.createCell(11);// ��ʮ����
	      cell.setCellValue("�Ƿ���������");
	      cell.setCellStyle(style);
	      cell = rowHeader.createCell(12);// ��ʮ����
	      cell.setCellValue("����ʱ��");
	      cell.setCellStyle(style);
	      cell = rowHeader.createCell(13);// ��ʮ����
	      cell.setCellValue("������");
	      cell.setCellStyle(style);
	      cell = rowHeader.createCell(14);// ��ʮ����
	      cell.setCellValue("������");
	      cell.setCellStyle(style);
	      cell = rowHeader.createCell(15);// ��ʮ����
	      cell.setCellValue("��ע");
	      cell.setCellStyle(style);
	      cell = rowHeader.createCell(16);// ��ʮ����
	      cell.setCellValue("��ͨ���߷�");
	      cell.setCellStyle(style);
	      cell = rowHeader.createCell(17);// ��ʮ����
	      cell.setCellValue("��ʳ������");
	      cell.setCellStyle(style);
	      cell = rowHeader.createCell(18);// ��ʮ����
	      cell.setCellValue("ס�޷�");
	      cell.setCellStyle(style);
	      cell = rowHeader.createCell(19);// �ڶ�ʮ��
	      cell.setCellValue("Ʊ�油����");
	      cell.setCellStyle(style);
	      cell = rowHeader.createCell(20);// �ڶ�ʮһ��
	      cell.setCellValue("���ڽ�ͨ��");
	      cell.setCellStyle(style);
	      cell = rowHeader.createCell(21);// �ڶ�ʮ����
	      cell.setCellValue("��������");
	      cell.setCellStyle(style);
	      cell = rowHeader.createCell(22);// �ڶ�ʮ����
	      cell.setCellValue("������");
	      cell.setCellStyle(style);
	      cell = rowHeader.createCell(23);// �ڶ�ʮ����
	      cell.setCellValue("������");
	      cell.setCellStyle(style);
	      cell = rowHeader.createCell(24);// �ڶ�ʮ����
	      cell.setCellValue("����˰��");
	      cell.setCellStyle(style);
	      cell = rowHeader.createCell(25);// �ڶ�ʮ����
	      cell.setCellValue("�����ܶ�");
	      cell.setCellStyle(style);
	      cell = rowHeader.createCell(26);// �ڶ�ʮ����
	      cell.setCellValue("����ʱ��");
	      cell.setCellStyle(style);
	      /*cell = rowHeader.createCell(27);// �ڶ�ʮ����
	      cell.setCellValue("������Դ");
	      cell.setCellStyle(style);*/
	      cell = rowHeader.createCell(27);// �ڶ�ʮ����
	      cell.setCellValue("ƾ֤��");
	      cell.setCellStyle(style);
	      cell = rowHeader.createCell(28);// ����ʮ��
	      cell.setCellValue("�Ƿ�Ϊ�����");
	      cell.setCellStyle(style);
	      cell = rowHeader.createCell(29);// ����ʮһ��
	      cell.setCellValue("�����ֳ�����");
	      cell.setCellStyle(style);
	      cell = rowHeader.createCell(30);// ����ʮ����
	      cell.setCellValue("˵��");
	      cell.setCellStyle(style);
	      // ��ͷ���------------------
	      int index = 1;// ����ţ�Ӧ�ӵ����п�ʼ��ÿ��ѭ������++
	      // �������Ͻ�����д��excel��
	      if (applyExpenses.size() > 0) {
	        for (ApplyExpensesDTO applyExpense : applyExpenses) {
	        	System.out.println("ʹ�õ�������"+applyExpense);
	        	ApproveExpensesDTO approveExpenses = new ApproveExpensesDTO();
	        	approveExpenses.setTravelExpenseId(applyExpense.getId());
	        	List<ApproveExpensesDTO> searchApproveExpenses = searchApproveExpenses(approveExpenses);
	        	List<TravelAddressDTO> tripDetails_list = applyExpense.getTripDetails_list();
	        	String place="";
	        	String destination="";
	        	String transportation= "";
	        	for(int i=0;i<tripDetails_list.size();i++){
	        		place  = tripDetails_list.get(0).getBeginAddress();
	        		System.out.println("������="+applyExpense.getTripMode());
	        		if(applyExpense.getTripMode().equals("2")){
	        			if(i==tripDetails_list.size()-1){
	        				break;
	        			}
	        			destination+=tripDetails_list.get(i).getEndAddress()+";";
	        		}else{
	        			destination = tripDetails_list.get(0).getEndAddress();
	        		}
	        		
	        		transportation+=tripDetails_list.get(i).getVehicle()+";";
	        	}
	        	/*for(ApproveExpensesDTO app:searchApproveExpenses){
	        		sumVeicleCost+=app.getVehicleCost();
	        		sumFoodAllowance+=app.getFoodAllowance();
	        		sumHoteExpense+=app.getHotelExpense();
	        		sumParValueAllowance+=app.getParValueAllowance();
	        		sumUrbanTraffic+=app.getUrbanTraffic();
	        		sumOtherCost+=app.getOtherCost();
	        		sumRepayCost+=app.getRepayCost();
	        		sumSupplementalCost+=app.getSupplementalCost();
	        		sumInputTax+=app.getInputTax();
	        		sumToalExpenses+=app.getTotalExpenses();
	        		paidTime=app.getPaidTime();
	        		
	        		fundSource+=app.getFundSource()+";";
	        		if(app.getFundSource().equals("")||null==app.getFundSource())fundSource="��";
	        		vouvherNum+=app.getVoucherNum()+";";
	        		isTestCost+=app.getIsTestCost()+";";
	        		testSite+=app.getTestSite()+",";
	        		illustration+=app.getIllustration()+",";
	        	}*/
	        	
	          // �к�++��2��ʼ
	          index++;
	          // ����һ������
	          XSSFRow row = sheet.createRow(index);
	          XSSFCell rowCell = row.createCell(0);// ��һ��
	          rowCell.setCellValue(applyExpense.getId());
	          rowCell.setCellStyle(style);

	          rowCell = row.createCell(1);// �ڶ���
	          rowCell.setCellValue(applyExpense.getDepartment());
	          rowCell.setCellStyle(style);

	          rowCell = row.createCell(2);// ������
	          rowCell.setCellValue(applyExpense.getTravelers());
	          rowCell.setCellStyle(style);

	          rowCell = row.createCell(3);// ������
	          rowCell.setCellValue(applyExpense.getCause());
	          rowCell.setCellStyle(style);

	          rowCell = row.createCell(4);// ������
	          rowCell.setCellValue(place);
	          rowCell.setCellStyle(style);
	          
	          rowCell = row.createCell(5);// ������
	          rowCell.setCellValue(destination);
	          rowCell.setCellStyle(style);
	          
	          rowCell = row.createCell(6);// ������
	          rowCell.setCellValue(transportation);
	          rowCell.setCellStyle(style);
	          
	          rowCell = row.createCell(7);// �ڰ���
	          rowCell.setCellValue(applyExpense.getBeginTime());
	          rowCell.setCellStyle(style);

	          rowCell = row.createCell(8);// �ھ���
	          rowCell.setCellValue(applyExpense.getEndTime());
	          rowCell.setCellStyle(style);

	          rowCell = row.createCell(9);// ��ʮ��
	          rowCell.setCellValue(applyExpense.getTravelDays());
	          rowCell.setCellStyle(style);

	          rowCell = row.createCell(10);// ��ʮһ��
	          rowCell.setCellValue(applyExpense.getTotalBudget());
	          rowCell.setCellStyle(style);

	          rowCell = row.createCell(11);// ��ʮ����
	          rowCell.setCellValue(applyExpense.getIsTest());
	          rowCell.setCellStyle(style);

	          rowCell = row.createCell(12);// ��ʮ����
	          rowCell.setCellValue(applyExpense.getApplyTime());
	          rowCell.setCellStyle(style);

	          rowCell = row.createCell(13);// ��ʮ����
	          rowCell.setCellValue(applyExpense.getApplyMan());
	          rowCell.setCellStyle(style);

	          rowCell = row.createCell(14);// ��ʮ����
	          rowCell.setCellValue(applyExpense.getApproveMan());
	          rowCell.setCellStyle(style);

	          rowCell = row.createCell(15);// ��ʮ����
	          rowCell.setCellValue(applyExpense.getRemarks());
	          rowCell.setCellStyle(style);
	          /*rowCell = row.createCell(16);// ��ʮ����
	          rowCell.setCellValue(sumVeicleCost);
	          rowCell.setCellStyle(style);
	          rowCell = row.createCell(17);// ��ʮ����
	          rowCell.setCellValue(sumFoodAllowance);
	          rowCell.setCellStyle(style);
	          rowCell = row.createCell(18);// ��ʮ����
	          rowCell.setCellValue(sumHoteExpense);
	          rowCell.setCellStyle(style);
	          rowCell = row.createCell(19);// �ڶ�ʮ��
	          rowCell.setCellValue(sumParValueAllowance);
	          rowCell.setCellStyle(style);
	          rowCell = row.createCell(20);// �ڶ�ʮһ��
	          rowCell.setCellValue(sumUrbanTraffic);
	          rowCell.setCellStyle(style);
	          rowCell = row.createCell(21);// �ڶ�ʮ����
	          rowCell.setCellValue(sumOtherCost);
	          rowCell.setCellStyle(style);
	          rowCell = row.createCell(22);// �ڶ�ʮ����
	          rowCell.setCellValue(sumRepayCost);
	          rowCell.setCellStyle(style);
	          rowCell = row.createCell(23);// �ڶ�ʮ����
	          rowCell.setCellValue(sumSupplementalCost);
	          rowCell.setCellStyle(style);
	          rowCell = row.createCell(24);// �ڶ�ʮ����
	          rowCell.setCellValue(sumInputTax);
	          rowCell.setCellStyle(style);
	          rowCell = row.createCell(25);// �ڶ�ʮ����
	          rowCell.setCellValue(sumToalExpenses);
	          rowCell.setCellStyle(style);
	          rowCell = row.createCell(26);// �ڶ�ʮ����
	          rowCell.setCellValue(paidTime);
	          rowCell.setCellStyle(style);
	          rowCell = row.createCell(27);// �ڶ�ʮ����
	          rowCell.setCellValue(fundSource);
	          rowCell.setCellStyle(style);
	          rowCell = row.createCell(28);// �ڶ�ʮ����
	          rowCell.setCellValue(vouvherNum);
	          rowCell.setCellStyle(style);
	          rowCell = row.createCell(29);// ����ʮ��
	          rowCell.setCellValue(isTestCost);
	          rowCell.setCellStyle(style);
	          rowCell = row.createCell(30);// ����ʮһ��
	          rowCell.setCellValue(testSite);
	          rowCell.setCellStyle(style);
	          rowCell = row.createCell(31);// ����ʮ����
	          rowCell.setCellValue(illustration);
	          rowCell.setCellStyle(style);*/


	          if (searchApproveExpenses.size() > 0) {
	            rowCell = row.createCell(16);// ������
	            rowCell.setCellValue(searchApproveExpenses.get(0).getVehicleCost());
	            rowCell.setCellStyle(style);

	            rowCell = row.createCell(17);// ������
	            rowCell.setCellValue(searchApproveExpenses.get(0).getFoodAllowance());
	            rowCell.setCellStyle(style);

	            rowCell = row.createCell(18);// ������
	            rowCell.setCellValue(searchApproveExpenses.get(0).getHotelExpense());
	            rowCell.setCellStyle(style);
	            rowCell = row.createCell(19);// ������
	            rowCell.setCellValue(searchApproveExpenses.get(0).getParValueAllowance());
	            rowCell.setCellStyle(style);
	            rowCell = row.createCell(20);// ������
	            rowCell.setCellValue(searchApproveExpenses.get(0).getUrbanTraffic());
	            rowCell.setCellStyle(style);
	            rowCell = row.createCell(21);// ������
	            rowCell.setCellValue(searchApproveExpenses.get(0).getOtherCost());
	            rowCell.setCellStyle(style);
	            rowCell = row.createCell(22);// ������
	            rowCell.setCellValue(searchApproveExpenses.get(0).getRepayCost());
	            rowCell.setCellStyle(style);
	            rowCell = row.createCell(23);// ������
	            rowCell.setCellValue(searchApproveExpenses.get(0).getSupplementalCost());
	            rowCell.setCellStyle(style);
	            rowCell = row.createCell(24);// ������
	            rowCell.setCellValue(searchApproveExpenses.get(0).getInputTax());
	            rowCell.setCellStyle(style);
	            rowCell = row.createCell(25);// ������
	            rowCell.setCellValue(searchApproveExpenses.get(0).getTotalExpenses());
	            rowCell.setCellStyle(style);
	            rowCell = row.createCell(26);// ������
	            rowCell.setCellValue(searchApproveExpenses.get(0).getPaidTime());
	            rowCell.setCellStyle(style);
	           /* rowCell = row.createCell(27);// ������
	            rowCell.setCellValue(searchApproveExpenses.get(0).getFundSource());
	            rowCell.setCellStyle(style);*/
	            rowCell = row.createCell(27);// ������
	            rowCell.setCellValue(searchApproveExpenses.get(0).getVoucherNum());
	            rowCell.setCellStyle(style);
	            rowCell = row.createCell(28);// ������
	            rowCell.setCellValue(searchApproveExpenses.get(0).getIsTestCost());
	            rowCell.setCellStyle(style);
	            rowCell = row.createCell(29);// ������
	            rowCell.setCellValue(searchApproveExpenses.get(0).getTestSite());
	            rowCell.setCellStyle(style);
	            rowCell = row.createCell(30);// ������
	            rowCell.setCellValue(searchApproveExpenses.get(0).getIllustration());
	            rowCell.setCellStyle(style);
	          } else {
	            rowCell = row.createCell(16);// ������
	            rowCell.setCellValue("");
	            rowCell.setCellStyle(style);

	            rowCell = row.createCell(17);// ������
	            rowCell.setCellValue("");
	            rowCell.setCellStyle(style);

	            rowCell = row.createCell(18);// ������
	            rowCell.setCellValue("");
	            rowCell.setCellStyle(style);
	            rowCell = row.createCell(19);// ������
	            rowCell.setCellValue("");
	            rowCell.setCellStyle(style);
	            rowCell = row.createCell(20);// ������
	            rowCell.setCellValue("");
	            rowCell.setCellStyle(style);
	            rowCell = row.createCell(21);// ������
	            rowCell.setCellValue("");
	            rowCell.setCellStyle(style);
	            rowCell = row.createCell(22);// ������
	            rowCell.setCellValue("");
	            rowCell.setCellStyle(style);
	            rowCell = row.createCell(23);// ������
	            rowCell.setCellValue("");
	            rowCell.setCellStyle(style);
	            rowCell = row.createCell(24);// ������
	            rowCell.setCellValue("");
	            rowCell.setCellStyle(style);
	            rowCell = row.createCell(25);// ������
	            rowCell.setCellValue("");
	            rowCell.setCellStyle(style);
	            rowCell = row.createCell(26);// ������
	            rowCell.setCellValue("");
	            rowCell.setCellStyle(style);
	            /*rowCell = row.createCell(27);// ������
	            rowCell.setCellValue("");
	            rowCell.setCellStyle(style);*/
	            rowCell = row.createCell(27);// ������
	            rowCell.setCellValue("");
	            rowCell.setCellStyle(style);
	            rowCell = row.createCell(28);// ������
	            rowCell.setCellValue("");
	            rowCell.setCellStyle(style);
	            rowCell = row.createCell(29);// ������
	            rowCell.setCellValue("");
	            rowCell.setCellStyle(style);
	            rowCell = row.createCell(30);// ������
	            rowCell.setCellValue("");
	            rowCell.setCellStyle(style);
	          }
	          int rowNum = 0;
	          for (int i = 1; i < searchApproveExpenses.size(); i++) {
	            rowNum = i;
	            index++;
	            // ����һ������
	            XSSFRow newRow = sheet.createRow(index);
	            rowCell = newRow.createCell(16);// ������
	            rowCell.setCellValue(searchApproveExpenses.get(i).getVehicleCost());
	            rowCell.setCellStyle(style);

	            rowCell = newRow.createCell(17);// ������
	            rowCell.setCellValue(searchApproveExpenses.get(i).getFoodAllowance());
	            rowCell.setCellStyle(style);

	            rowCell = newRow.createCell(18);// ������
	            rowCell.setCellValue(searchApproveExpenses.get(i).getHotelExpense());
	            rowCell.setCellStyle(style);
	            
	            rowCell = newRow.createCell(19);// ������
	            rowCell.setCellValue(searchApproveExpenses.get(i).getParValueAllowance());
	            rowCell.setCellStyle(style);
	            
	            rowCell = newRow.createCell(20);// ������
	            rowCell.setCellValue(searchApproveExpenses.get(i).getUrbanTraffic());
	            rowCell.setCellStyle(style);
	            
	            rowCell = newRow.createCell(21);// ������
	            rowCell.setCellValue(searchApproveExpenses.get(i).getOtherCost());
	            rowCell.setCellStyle(style);
	            
	            rowCell = newRow.createCell(22);// ������
	            rowCell.setCellValue(searchApproveExpenses.get(i).getRepayCost());
	            rowCell.setCellStyle(style);
	            
	            rowCell = newRow.createCell(23);// ������
	            rowCell.setCellValue(searchApproveExpenses.get(i).getSupplementalCost());
	            rowCell.setCellStyle(style);
	            
	            rowCell = newRow.createCell(24);// ������
	            rowCell.setCellValue(searchApproveExpenses.get(i).getInputTax());
	            rowCell.setCellStyle(style);
	            
	            rowCell = newRow.createCell(25);// ������
	            rowCell.setCellValue(searchApproveExpenses.get(i).getTotalExpenses());
	            rowCell.setCellStyle(style);
	            
	            rowCell = newRow.createCell(26);// ������
	            rowCell.setCellValue(searchApproveExpenses.get(i).getPaidTime());
	            rowCell.setCellStyle(style);
	            
	            /*rowCell = newRow.createCell(27);// ������
	            rowCell.setCellValue(searchApproveExpenses.get(i).getFundSource());
	            rowCell.setCellStyle(style);*/
	            
	            rowCell = newRow.createCell(27);// ������
	            rowCell.setCellValue(searchApproveExpenses.get(i).getVoucherNum());
	            rowCell.setCellStyle(style);
	            
	            rowCell = newRow.createCell(28);// ������
	            rowCell.setCellValue(searchApproveExpenses.get(i).getIsTestCost());
	            rowCell.setCellStyle(style);
	            
	            rowCell = newRow.createCell(29);// ������
	            rowCell.setCellValue(searchApproveExpenses.get(i).getTestSite());
	            rowCell.setCellStyle(style);
	            
	            rowCell = newRow.createCell(30);// ������
	            rowCell.setCellValue(searchApproveExpenses.get(i).getIllustration());
	            rowCell.setCellStyle(style);
	          }
	          if (rowNum > 0) {
	            for (int i = 0; i < 31; i++) {
	              if (i != 16 && i != 17 && i != 18 && i!= 19 && i!= 20
	            		  && i!= 21 && i!= 22 && i!= 23 && i!= 24 && i!= 25
	            		  && i!= 26 && i!= 27 && i!= 28 && i!= 29 && i!= 30 && i!= 30)
	                sheet.addMergedRegion(new CellRangeAddress((index - rowNum), index, i, i));
	            }
	          }
	        }
	      }
	      // ���ļ����浽ָ��λ��
	      FileOutputStream out = new FileOutputStream(path);
	      workbook.write(out);
	      out.close();
	      return 1;
	    } catch (Exception e) {
	      e.printStackTrace();
	      return 0;
	    }
	  }

@Override
public int exportapproveExpenses(ApplyExpensesDTO applyExpense, String path,String starTime,String searendTime,String voucherNum) {
	List<ApplyExpensesDTO> applyExpenses = new ArrayList<ApplyExpensesDTO>();
    applyExpenses = searchApplyExpense(applyExpense,starTime,searendTime,voucherNum);
    System.out.println("����������"+applyExpenses);
    // �����Ϊnull
    if (applyExpenses.size() > 0) {
      return exportapproveExpenses(applyExpenses, path);
    } else {
      return 0;
    }
}

public List<ApplyExpensesDTO> searchTravelExpenses(Integer state, ApplyExpensesDTO applyExpense) {
    StringBuffer sql = new StringBuffer("select * from b_travelexpenses  where 1=1");
    if (applyExpense.getDepartment() != null)
      if (!"".equals(applyExpense.getDepartment())) {
        sql.append(" and Department like '%" + applyExpense.getDepartment() + "%'");
      }
    if (applyExpense.getTravelers() != null)
      if (!"".equals(applyExpense.getTravelers())) {
        sql.append(" and Travelers like '%" + applyExpense.getTravelers() + "%'");
      }
    if (applyExpense.getTripDetails() != null)
      if (!"".equals(applyExpense.getTripDetails())) {
        sql.append(" and TripDetails like '%" + applyExpense.getTripDetails() + "%'");
      }
    if (applyExpense.getBeginTime() != null)
      if (!"".equals(applyExpense.getBeginTime())) {
        sql.append(" and BeginTime like '%" + applyExpense.getBeginTime() + "%'");
      }
    if (applyExpense.getEndTime() != null)
      if (!"".equals(applyExpense.getEndTime())) {
        sql.append(" and EndTime like '%" + applyExpense.getEndTime() + "%'");
      }
    if (applyExpense.getApplyMan() != null)
      if (!"".equals(applyExpense.getApplyMan())) {
        sql.append(" and ApplyMan like '%" + applyExpense.getApplyMan() + "%'");
      }
    if (state == 0)
      sql.append(" and ApproveState = '����������'");
    else if (state == 1)
      sql.append(" and ApproveState = '�ѵǼ�'");

    sql.append(" order by ID desc");

    System.out.println(sql);
    List<Map<String, Object>> queryForList = jdbcTemplate.queryForList(sql.toString());
    /*for(int i=0;i<queryForList.size()-1;i++){
    	for(int j=queryForList.size()-1;j>i;j--){
    		System.out.println("j��"+j);
    		 if  (queryForList.get(j).get("ID").equals(queryForList.get(i).get("ID")))  {       
    			 queryForList.remove(j);       
               }        
    	}
    }	*/				
    List<ApplyExpensesDTO> list = new ArrayList<ApplyExpensesDTO>();
    for (Map<String, Object> map : queryForList) {
      ApplyExpensesDTO tE = new ApplyExpensesDTO();
      tE.setId(map.get("ID").toString());
      tE.setDepartment(map.get("Department") == null ? "" : map.get("Department").toString());
      tE.setTravelers(map.get("Travelers") == null ? "" : map.get("Travelers").toString());
      tE.setCause(map.get("Cause") == null ? "" : map.get("Cause").toString());
      tE.setTripMode(map.get("TripMode") == null ? "" : map.get("TripMode").toString());
//      tE.setStartAddress(map.get("StartAddress") == null ? "" : map.get("StartAddress").toString());
//      tE.setMiddAddress(map.get("MiddAddress") == null ? "" : map.get("MiddAddress").toString());
//      tE.setEndAddress(map.get("EndAddress") == null ? "" : map.get("EndAddress").toString());
      tE.setTripDetails(map.get("TripDetails") == null ? "" : map.get("TripDetails").toString());
      tE.setBeginTime(map.get("BeginTime") == null ? "" : map.get("BeginTime").toString());
      tE.setEndTime(map.get("EndTime") == null ? "" : map.get("EndTime").toString());
      tE.setTravelDays(map.get("TravelDays") == null ? 0 : Integer.valueOf(map.get("TravelDays").toString()));
      tE.setTotalBudget(map.get("TotalBudget") == null ? 0.0f : Float.parseFloat(map.get("TotalBudget").toString()));
      tE.setIsTest(map.get("IsTest") == null ? "" : map.get("IsTest").toString());
      tE.setApplyTime(map.get("ApplyTime") == null ? "" : map.get("ApplyTime").toString());
      tE.setApplyMan(map.get("ApplyMan") == null ? "" : map.get("ApplyMan").toString());
      tE.setApproveMan(map.get("ApproveMan") == null ? "" : map.get("ApproveMan").toString());
      tE.setRemarks(map.get("Remarks") == null ? "" : map.get("Remarks").toString());
      tE.setApproveState(map.get("ApproveState") == null ? "" : map.get("ApproveState").toString());

      //json�ַ���ת��list
      List<TravelAddressDTO> travelAddress = (List<TravelAddressDTO>) JSONArray.toCollection(JSONArray.fromObject(tE
          .getTripDetails()), TravelAddressDTO.class);
      tE.setTripDetails_list(travelAddress);
      //listת��string
      tE.setTripDetails(transformToString(travelAddress));

      list.add(tE);
    }
    return list;
  }


private String transformToString(List<TravelAddressDTO> list) {
    String s = "";
    if (list.size() == 2) {
      s += "��㣺" + list.get(0).getBeginAddress() + "������أ�" +
          list.get(0).getEndAddress() + "����ͨ��ʽ��" + list.get(0).getVehicle() + "�� ";
      s += "����أ�" + list.get(1).getBeginAddress() + "���յ㣺" +
          list.get(1).getEndAddress() + "����ͨ��ʽ��" + list.get(1).getVehicle() + "�� ";
    } else if (list.size() > 2) {
      s += "��㣺" + list.get(0).getBeginAddress() + "�������1��" +
          list.get(0).getEndAddress() + "����ͨ��ʽ��" + list.get(0).getVehicle() + "�� ";
      for (int i = 1; i < list.size(); i++) {
        s += "�����" + i + "��" + list.get(i).getBeginAddress() + "�������" + (i + 1) + "��" +
            list.get(i).getEndAddress() + "����ͨ��ʽ��" + list.get(i).getVehicle() + "�� ";
      }
      int last = list.size() - 1;
      s += "�����" + last + "��" + list.get(last).getBeginAddress() + "���յ㣺" +
          list.get(last).getEndAddress() + "����ͨ��ʽ��" + list.get(last).getVehicle() + "�� ";
    }
    return s;
  }

/**
 * ɾ������
 */
@Override
@Transactional
public int deleteApproveExpenses(String ID) {
	return approveExpensesDAO.deleteApproveExpenses(ID);
}

@Override
public List<ApplyExpensesDTO> searchApplyExpense(ApplyExpensesDTO applyExpense,String starTime,String searendTime,String voucherNum) {
	
	StringBuffer sql = new StringBuffer("select t.ID,t.Department,t.Travelers,t.Cause,t.TripDetails,t.BeginTime,t.EndTime,t.TravelDays,t.TotalBudget,t.IsTest,t.ApplyTime,t.ApplyMan,t.ApproveMan,t.Remarks,t.ApproveState,t.TripMode from b_travelexpenses t join b_travelexpensesapprove t1 on t.ID=t1.TravelExpenseId where 1=1 ");
    if (applyExpense.getDepartment() != null)
      if (!"".equals(applyExpense.getDepartment())) {
        sql.append(" and Department like '%" + applyExpense.getDepartment() + "%'");
      }
    if (applyExpense.getTravelers() != null)
      if (!"".equals(applyExpense.getTravelers())) {
        sql.append(" and Travelers like '%" + applyExpense.getTravelers() + "%'");
      }
    if (applyExpense.getTripDetails() != null)
      if (!"".equals(applyExpense.getTripDetails())) {
        sql.append(" and TripDetails like '%" + applyExpense.getTripDetails() + "%'");
      }
    if (applyExpense.getBeginTime() != null)
      if (!"".equals(applyExpense.getBeginTime())) {
        sql.append(" and BeginTime >= "+"'" + applyExpense.getBeginTime()+"'" +"");
      }
    if (applyExpense.getEndTime() != null)
      if (!"".equals(applyExpense.getEndTime())) {
        sql.append(" and EndTime <= " +"'"+ applyExpense.getEndTime()+"'" + "");
      }
    if (applyExpense.getApplyMan() != null)
      if (!"".equals(applyExpense.getApplyMan())) {
        sql.append(" and ApplyMan like '%" + applyExpense.getApplyMan() + "%'");
      }
    if(null!=starTime&&!starTime.equals("")){
    	sql.append(" and PaidTime >="+"'"+starTime+"'"+"");
    }
    if(null!=searendTime&&!searendTime.equals("")){
    	sql.append(" and PaidTime<="+"'"+searendTime+"'"+"");
    }
    if(null!=voucherNum&&!voucherNum.equals("")){
    	sql.append("and TestSite like '%"+voucherNum+"%'");
    }
      sql.append(" and ApproveState = '�ѵǼ�'");
    sql.append(" order by t.ID desc");

    System.out.println(sql);
    List<Map<String, Object>> queryForList = jdbcTemplate.queryForList(sql.toString());
    for(int i=0;i<queryForList.size()-1;i++){
    	for(int j=queryForList.size()-1;j>i;j--){
    		System.out.println("j��"+j);
    		 if  (queryForList.get(j).get("ID").equals(queryForList.get(i).get("ID")))  {       
    			 queryForList.remove(j);       
               }        
    	}
    }
    List<ApplyExpensesDTO> list = new ArrayList<ApplyExpensesDTO>();
    for (Map<String, Object> map : queryForList) {
      ApplyExpensesDTO tE = new ApplyExpensesDTO();
      tE.setId(map.get("ID").toString());
      tE.setDepartment(map.get("Department") == null ? "" : map.get("Department").toString());
      tE.setTravelers(map.get("Travelers") == null ? "" : map.get("Travelers").toString());
      tE.setCause(map.get("Cause") == null ? "" : map.get("Cause").toString());
      tE.setTripMode(map.get("TripMode") == null ? "" : map.get("TripMode").toString());
//      tE.setStartAddress(map.get("StartAddress") == null ? "" : map.get("StartAddress").toString());
//      tE.setMiddAddress(map.get("MiddAddress") == null ? "" : map.get("MiddAddress").toString());
//      tE.setEndAddress(map.get("EndAddress") == null ? "" : map.get("EndAddress").toString());
      tE.setTripDetails(map.get("TripDetails") == null ? "" : map.get("TripDetails").toString());
      tE.setBeginTime(map.get("BeginTime") == null ? "" : map.get("BeginTime").toString());
      tE.setEndTime(map.get("EndTime") == null ? "" : map.get("EndTime").toString());
      tE.setTravelDays(map.get("TravelDays") == null ? 0 : Integer.valueOf(map.get("TravelDays").toString()));
      tE.setTotalBudget(map.get("TotalBudget") == null ? 0.0f : Float.parseFloat(map.get("TotalBudget").toString()));
      tE.setIsTest(map.get("IsTest") == null ? "" : map.get("IsTest").toString());
      tE.setApplyTime(map.get("ApplyTime") == null ? "" : map.get("ApplyTime").toString());
      tE.setApplyMan(map.get("ApplyMan") == null ? "" : map.get("ApplyMan").toString());
      tE.setApproveMan(map.get("ApproveMan") == null ? "" : map.get("ApproveMan").toString());
      tE.setRemarks(map.get("Remarks") == null ? "" : map.get("Remarks").toString());
      tE.setApproveState(map.get("ApproveState") == null ? "" : map.get("ApproveState").toString());

      //json�ַ���ת��list
      List<TravelAddressDTO> travelAddress = (List<TravelAddressDTO>) JSONArray.toCollection(JSONArray.fromObject(tE
          .getTripDetails()), TravelAddressDTO.class);
      tE.setTripDetails_list(travelAddress);
      //listת��string
      tE.setTripDetails(transformToString(travelAddress));
      list.add(tE);
    }
    return list;
}


}

