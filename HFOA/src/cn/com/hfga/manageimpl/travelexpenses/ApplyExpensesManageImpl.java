package cn.com.hfga.manageimpl.travelexpenses;

import cn.com.hfga.dao.travelExpenses.ApplyExpensesDAO;
import cn.com.hfga.dto.travelexpenses.ApplyExpensesDTO;
import cn.com.hfga.dto.travelexpenses.TravelAddressDTO;
import cn.com.hfga.entity.travelExpenses.ApplyExpensesEntity;
import cn.com.hfga.manager.travelexpenses.ApplyExpensesManage;
import net.sf.json.JSONArray;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.apache.zookeeper.Op.Check;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("applyExpensesManage")
public class ApplyExpensesManageImpl implements ApplyExpensesManage {
  @Autowired
  private ApplyExpensesDAO applyExpensesDAO;
  @Autowired
  private JdbcTemplate jdbcTemplate;

  /**
   * �ƶ����û���������
   *
   * @param applyExpense
   * @return
   */
  @Transactional
  @Override
  public int insertTravelExpenses(ApplyExpensesDTO applyExpense) {
    applyExpense.setId(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
    /*applyExpense.getIsTest().substring(applyExpense.getIsTest().lastIndexOf(","));*/
    applyExpense.setApproveState("����������");
    return applyExpensesDAO.insertApplyExpenses(applyExpense.getId(), applyExpense.getDepartment(),
        applyExpense.getTravelers(), applyExpense.getCause(), applyExpense.getTripDetails(),
        applyExpense.getBeginTime(), applyExpense.getEndTime(), applyExpense.getTravelDays(),
        applyExpense.getTotalBudget(), applyExpense.getIsTest(), applyExpense.getApplyTime(),
        applyExpense.getApplyMan(), applyExpense.getApproveMan(), applyExpense.getRemarks(),
        applyExpense.getApproveState(),applyExpense.getTripMode());
  }

  /**
   * �ƶ����û��޸�����
   *
   * @param applyExpense
   * @return
   */
  @Transactional
  @Override
  public int modifyTravelExpenses(ApplyExpensesDTO applyExpense,String RoleId) {
	  int flag=1;
	  String id = applyExpense.getId();
	  ApplyExpensesEntity expense = applyExpensesDAO.getApplyExpense(id);
	  if(expense.getApproveState().equals("���ش��޸�")){
		  applyExpensesDAO.applyImplement(id);//����������
	  }

	  //�жϳ��з�ʽ�Ƿ�ı�Ŀ�ĵ��Ƿ�ı�
	  List<TravelAddressDTO> travelAddress = (List<TravelAddressDTO>) JSONArray.toCollection(JSONArray.fromObject(applyExpense
	          .getTripDetails()), TravelAddressDTO.class);
	  List<TravelAddressDTO> aftertravelAddress = (List<TravelAddressDTO>) JSONArray.toCollection(JSONArray.fromObject(expense
	          .getTripDetails()), TravelAddressDTO.class);
	  String star ="";
	  for(TravelAddressDTO add:travelAddress){
		  star+=add.getEndAddress()+";";
	  }
	  String end="";
	  for(TravelAddressDTO after:aftertravelAddress){
		  end+=after.getEndAddress()+";";
	  }
	  System.out.println("���������Ϊ"+star);
	  System.out.println("�鵽��������"+end);
	  String [] ends = end.split(";");
	  for(int j=0;j<ends.length;j++){
		  if(star.indexOf(ends[j])==-1){
			  applyExpensesDAO.reApplyImplement(id);
			  flag=2;
		  }
	  }
	  System.out.println(travelAddress.size()==aftertravelAddress.size());
	  if(travelAddress.size()==aftertravelAddress.size()){
		  System.out.println("���з�ʽ�ж�");
		  if(RoleId.equals("3")){
			  for(int i=0;i<travelAddress.size();i++){
				  System.out.println("��˾���ܵĳ��з�ʽ");
				  if(proIDcheck(travelAddress.get(i).getVehicle(),aftertravelAddress.get(i).getVehicle())){
					  applyExpensesDAO.reApplyImplement(id);
					  flag=2;
				  }
			  } 
		  }else{
			  for(int i=0;i<travelAddress.size();i++){
				  System.out.println("���з�ʽ"+travelAddress.get(i).getVehicle());
				  System.out.println("�������ĳ��з�ʽ"+aftertravelAddress.get(i).getVehicle());
				  if(chkeck(travelAddress.get(i).getVehicle(),aftertravelAddress.get(i).getVehicle())){
					  applyExpensesDAO.reApplyImplement(id);
					  flag=2;
				  }
			  } 
		  }
	  }
	  if(datechkeck(expense.getBeginTime(), expense.getEndTime(), applyExpense.getBeginTime(), applyExpense.getEndTime(),expense.getTravelDays(),applyExpense.getTravelDays())
			  ){
		  applyExpensesDAO.reApplyImplement(id);
		  flag=2;
	  }
	  applyExpensesDAO.modifyApplyExpenses(applyExpense.getId(), applyExpense.getDepartment(),
		        applyExpense.getTravelers(), applyExpense.getCause(), applyExpense.getTripDetails(),
		        applyExpense.getBeginTime(), applyExpense.getEndTime(), applyExpense.getTravelDays(),
		        applyExpense.getTotalBudget(), applyExpense.getIsTest(), applyExpense.getApplyTime(),
		        applyExpense.getApplyMan(), applyExpense.getApproveMan(), applyExpense.getRemarks());
    return flag;
  }
  //�ж�ʱ��
  private boolean datechkeck(String bengingdate,String enddate,String afterdate,String afterenddate,int date,int endDate){
	  SimpleDateFormat smf = new SimpleDateFormat("yyyy-MM-dd");
	  Date bening = null;
	  Date ending = null; 
	  Date afterbening = null;
	  Date afterending = null;
	try {
		bening = smf.parse(bengingdate);
		ending = smf.parse(enddate);
		afterbening = smf.parse(afterdate);
		afterending = smf.parse(afterenddate);
	} catch (ParseException e) {
		e.printStackTrace();
	}
	  System.out.println("��ʼʱ��"+bengingdate);
	  System.out.println("����ʱ��"+enddate);
	  System.out.println("�޸�ʱ��"+afterdate);
	  System.out.println("�޸Ľ���ʱ��"+afterenddate);
	  long change = (bening.getTime()-afterbening.getTime())/(1000*3600*24);
	  long endchange = (ending.getTime()-afterending.getTime())/(1000*3600*24);
	  long start = (bening.getTime()-afterbening.getTime())/(1000*3600*24);
	  long end = (ending.getTime()-afterending.getTime())/(1000*3600*24);
	  
	  if(change-endchange>3){
		  return true;
	  }else if(start>3){
		  return true;
	  }else if(end>3){
		  return true;
	  }
	  
	  
	  return false;
	  
	  
  }
  
  
  
  //��˾�쵼�������ȼ��ж�
  private boolean proIDcheck(String address,String afteradderss){
	  int a =0;
	  int b =0;
	  if(address.equals("�ɻ������")){
		  a=1;
	  }
	  if(afteradderss.equals("�ɻ������")){
		  b=1;
	  }
	  if(address.equals("�ɻ����ò�")){
		  a=0;
	  }
	  if(afteradderss.equals("�ɻ����ò�")){
		  b=0;
	  }
	  if(address.equals("�𳵸���һ����")||address.equals("�𳵶���һ����")){
		  a=0;
	  }
	  if(afteradderss.equals("�𳵸���һ����")||address.equals("�𳵶���һ����")){
		  b=0;
	  }
	  
	  if(address.equals("�𳵸���������")||address.equals("�𳵶�������")||address.equals("�𳵶���������")||address.equals("�������г�����")||address.equals("�������г�Ӳ��")
			  ||address.equals("�������г�Ӳ��")){
		  a=0;
	  }
	  if(afteradderss.equals("�𳵸���������")||address.equals("�𳵶�������")||address.equals("�𳵶���������")||address.equals("�������г�����")||address.equals("�������г�Ӳ��")
			  ||address.equals("�������г�Ӳ��")){
		  b=0;
	  }
	  
	  if(address.equals("�ִ����Ȳ�")||address.equals("�ִ����Ȳ�")||address.equals("�⳵")||address.equals("�Լ�")||address.equals("����")){
		  a=0;
	  }
      if(afteradderss.equals("�ִ����Ȳ�")||address.equals("�ִ����Ȳ�")||address.equals("�⳵")||address.equals("�Լ�")||address.equals("����")){
		  b=0;
	  }
      
      if(a>b){
    	  return true;
      }else if(a==b){
    	  return false;
      }else if(a<b){
    	  return false;
      }
      
	  
	  return false;
  }
  
  
  //һ����Ա������з�ʽ�ж����ȼ�
  private boolean chkeck(String address,String afteradderss){
	  int a =0;
	  int b =0;
	  if(address.equals("�ɻ������")){
		  a=4;
	  }
	  if(afteradderss.equals("�ɻ������")){
		  b=4;
	  }
	  if(address.equals("�ɻ����ò�")){
		  a=3;
	  }
	  if(afteradderss.equals("�ɻ����ò�")){
		  b=3;
	  }
	  if(address.equals("�𳵸���һ����")||address.equals("�𳵶���һ����")){
		  a=2;
	  }
	  if(afteradderss.equals("�𳵸���һ����")||address.equals("�𳵶���һ����")){
		  b=2;
	  }
	  
	  if(address.equals("�𳵸���������")||address.equals("�𳵶�������")||address.equals("�𳵶���������")||address.equals("�������г�����")||address.equals("�������г�Ӳ��")
			  ||address.equals("�������г�Ӳ��")){
		  a=1;
	  }
	  if(afteradderss.equals("�𳵸���������")||address.equals("�𳵶�������")||address.equals("�𳵶���������")||address.equals("�������г�����")||address.equals("�������г�Ӳ��")
			  ||address.equals("�������г�Ӳ��")){
		  b=1;
	  }
	  
	  if(address.equals("�ִ����Ȳ�")||address.equals("�ִ����Ȳ�")||address.equals("�⳵")||address.equals("�Լ�")||address.equals("����")){
		  a=0;
	  }
      if(afteradderss.equals("�ִ����Ȳ�")||address.equals("�ִ����Ȳ�")||address.equals("�⳵")||address.equals("�Լ�")||address.equals("����")){
		  b=0;
	  }
      
      if(a>b){
    	  return true;
      }else if(a==b){
    	  return false;
      }else if(a<b){
    	  return false;
      }
      
	  
	  return false;
  }

  
  /**
   * �ƶ��˲������ͨ��
   *
   * @param id
   * @return
   */
  @Transactional
  @Override
  public int departmentSuccess(String id) {
    return applyExpensesDAO.departmentSuccess(id);
  }

  /**
   * �ƶ��˲�����˲�ͨ��
   *
   * @param id
   * @return
   */
  @Transactional
  @Override
  public int departmentFail(String id) {
    return applyExpensesDAO.departmentFail(id);
  }

  /**
   * �ƶ�������ִ��
   *
   * @param id
   * @return
   */
  @Transactional
  @Override
  public int applyImplement(String id) {
    return applyExpensesDAO.applyImplement(id);
  }

  /**
   * �ƶ����޸�ĳЩ�ֶκ����������
   *
   * @param id
   * @return
   */
  @Transactional
  @Override
  public int reApplyImplement(String id) {
    return applyExpensesDAO.reApplyImplement(id);
  }

  /**
   * �ƶ��˸���id��ȡ����
   *
   * @param id
   * @return
   */
  @Transactional
  @Override
  public List<ApplyExpensesEntity> getApplyExpenseById(String id) {
    return applyExpensesDAO.getApplyExpenseById(id);
  }

  /**
   * �ƶ��˻�ȡĳ���˴�����������
   *
   * @param applyMan
   * @return
   */
  @Transactional
  @Override
  public List<ApplyExpensesEntity> getWaitApplyExpenseByName(String applyMan) {
    return applyExpensesDAO.getWaitApplyExpenseByName(applyMan);
  }

  /**
   * �ƶ��˻�ȡ���������������
   *
   * @param approveMan
   * @return
   */
  public List<ApplyExpensesEntity> getWaitApplyExpenseByManager(String approveMan) {
    return applyExpensesDAO.getWaitApplyExpenseByManager(approveMan);
  }

  /**
   * �ƶ��˻�ȡĳ���˲�������ͨ�����ִ�е�����
   *
   * @param applyMan
   * @return
   */
  @Transactional
  @Override
  public List<ApplyExpensesEntity> getPassApplyExpenseByName(String applyMan) {
    return applyExpensesDAO.getPassApplyExpenseByName(applyMan);
  }

  /**
   * �ƶ��˻�ȡĳ���˲���������ͨ�����޸ĵ�����
   *
   * @param applyMan
   * @return
   */
  @Transactional
  @Override
  public List<ApplyExpensesEntity> getNoPassApplyExpenseByName(String applyMan) {
    return applyExpensesDAO.getNoPassApplyExpenseByName(applyMan);
  }

  /**
   * �ƶ��˻�ȡĳ���˴���������������
   *
   * @param applyMan
   * @return
   */
  @Transactional
  @Override
  public List<ApplyExpensesEntity> getReviewFail(String applyMan) {
    return applyExpensesDAO.getReviewFail(applyMan);
  }

  /**
   * �ƶ��˻�ȡĳ���������Ǽǵ�����
   *
   * @param applyMan
   * @return
   */
  @Transactional
  @Override
  public List<ApplyExpensesEntity> getReviewSuccessByName(String applyMan) {
    return applyExpensesDAO.getReviewSuccessByName(applyMan);
  }

  /**
   * �ƶ��˻�ȡĳ����������������
   *
   * @param applyMan
   * @return
   */
  @Transactional
  @Override
  public List<ApplyExpensesEntity> getAllState(String applyMan) {
    return applyExpensesDAO.getAllState(applyMan);
  }

  /**
   * �ƶ��˸���id��������
   *
   * @param id
   * @return
   */
  @Transactional
  @Override
  public int deleteApply(String id) {
    return applyExpensesDAO.deleteApply(id);
  }

  /**
   * web�˲������--����
   *
   * @param id
   * @return
   */
  @Transactional
  @Override
  public int financeReviewFail(String id) {
    return applyExpensesDAO.financeReviewFail(id);
  }

  /**
   * web�˲������--�Ǽ�
   *
   * @param id
   * @return
   */
  @Transactional
  @Override
  public int financeReviewSuccess(String id) {
    return applyExpensesDAO.financeReviewSuccess(id);
  }
  
  

  /**
   *�ƶ��˲�ѯ
   *
   * @param state
   * @param applyExpense
   * @return
   */
  @Override
  public List<ApplyExpensesDTO> listTravelExpense( ApplyExpensesDTO applyExpense) {
    StringBuffer sql = new StringBuffer("select * from b_travelexpenses   where 1=1 and ApproveState = '�ѵǼ�'");
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
    sql.append(" order by ID desc");

    System.out.println(sql);
    List<Map<String, Object>> queryForList = jdbcTemplate.queryForList(sql.toString());

    List<ApplyExpensesDTO> list = new ArrayList<ApplyExpensesDTO>();
    for (Map<String, Object> map : queryForList) {
      ApplyExpensesDTO tE = new ApplyExpensesDTO();
      tE.setId(map.get("ID").toString());
      tE.setDepartment(map.get("Department") == null ? "" : map.get("Department").toString());
      tE.setTravelers(map.get("Travelers") == null ? "" : map.get("Travelers").toString());
      tE.setCause(map.get("Cause") == null ? "" : map.get("Cause").toString());
//    tE.setTripMode(map.get("TripMode") == null ? "" : map.get("TripMode").toString());
//    tE.setStartAddress(map.get("StartAddress") == null ? "" : map.get("StartAddress").toString());
//    tE.setMiddAddress(map.get("MiddAddress") == null ? "" : map.get("MiddAddress").toString());
//    tE.setEndAddress(map.get("EndAddress") == null ? "" : map.get("EndAddress").toString());
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

      /*//json�ַ���ת��list
      List<TravelAddressDTO> travelAddress = (List<TravelAddressDTO>) JSONArray.toCollection(JSONArray.fromObject(tE
          .getTripDetails()), TravelAddressDTO.class);
      tE.setTripDetails_list(travelAddress);
      //listת��string
      tE.setTripDetails(transformToString(travelAddress));*/

      list.add(tE);
    }
    return list;
  }
  

  /**
   * web�˴��Ǽ��б��ѵǼ��б��ʼ��������ز�ѯ
   *
   * @param state
   * @param applyExpense
   * @return
   */
  @Override
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
    if (state == 0)
      sql.append(" and ApproveState = '����������'");
    else if (state == 1)
      sql.append(" and ApproveState = '�ѵǼ�'");

    sql.append(" order by ID desc");

    System.out.println(sql);
    List<Map<String, Object>> queryForList = jdbcTemplate.queryForList(sql.toString());

    List<ApplyExpensesDTO> list = new ArrayList<ApplyExpensesDTO>();
    for (Map<String, Object> map : queryForList) {
      ApplyExpensesDTO tE = new ApplyExpensesDTO();
      tE.setId(map.get("ID").toString());
      tE.setDepartment(map.get("Department") == null ? "" : map.get("Department").toString());
      tE.setTravelers(map.get("Travelers") == null ? "" : map.get("Travelers").toString());
      tE.setCause(map.get("Cause") == null ? "" : map.get("Cause").toString());
//      tE.setTripMode(map.get("TripMode") == null ? "" : map.get("TripMode").toString());
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
      System.out.println("jsoת��list����"+travelAddress);
      tE.setTripDetails(transformToString(travelAddress));
      System.out.println("�ַ��޸���"+transformToString(travelAddress));
      list.add(tE);
    }
    return list;
  }

  private String transformToString(List<TravelAddressDTO> list) {
    String s = "";
    if (list.size() == 2) {
      s += "��㣺" + list.get(0).getBeginAddress() + "������أ�" +
          list.get(0).getEndAddress() + "����ͨ��ʽ��" + list.get(0).getVehicle() + "<br>";
      s += "����أ�" + list.get(1).getBeginAddress() + "���յ㣺" +
          list.get(1).getEndAddress() + "����ͨ��ʽ��" + list.get(1).getVehicle() + "<br>";
    } else if (list.size() > 2) {
      s += "��㣺" + list.get(0).getBeginAddress() + "�������1��" +
          list.get(0).getEndAddress() + "����ͨ��ʽ��" + list.get(0).getVehicle() + "<br> ";
      for (int i = 1; i < list.size(); i++) {
        s += "�����" + i + "��" + list.get(i).getBeginAddress() + "�������" + (i + 1) + "��" +
            list.get(i).getEndAddress() + "����ͨ��ʽ��" + list.get(i).getVehicle() + "<br> ";
      }
    }
    return s;
  }

  /**
   * ����excel--���÷Ѵ��Ǽ��б�
   *
   * @param applyExpense
   * @param path
   * @return
   */
  @Override
  public int exportApplyExpenses(ApplyExpensesDTO applyExpense, String path) {
    // �õ����ݼ���
    List<ApplyExpensesDTO> applyExpenses = new ArrayList<ApplyExpensesDTO>();
    applyExpenses = searchTravelExpenses(0, applyExpense);
    // �����Ϊnull
    if (applyExpenses.size() > 0) {
      return exportApplyExpensesExcel(applyExpenses, path);
    } else {
      return 0;
    }
  }

  private int exportApplyExpensesExcel(List<ApplyExpensesDTO> applyExpenses, String path) {
    // ����һ��������
    XSSFWorkbook workbook;
    try {
      workbook = new XSSFWorkbook();
      // ���һ��sheet,sheet��
      XSSFSheet sheet = workbook.createSheet("���÷Ѵ��Ǽ��б�");
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
      titleCell.setCellValue("���÷Ѵ��Ǽ��б�");
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
      cell.setCellValue("Ŀ�ĵ�");
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
      // ��ͷ���------------------
      int index = 1;// ����ţ�Ӧ�ӵ����п�ʼ��ÿ��ѭ������++
      // �������Ͻ�����д��excel��
      if (applyExpenses.size() > 0) {
        for (ApplyExpensesDTO applyExpense : applyExpenses) {
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

          if (applyExpense.getTripDetails_list().size() > 0) {
            rowCell = row.createCell(4);// ������
            rowCell.setCellValue(applyExpense.getTripDetails_list().get(0).getBeginAddress());
            rowCell.setCellStyle(style);

            rowCell = row.createCell(5);// ������
            rowCell.setCellValue(applyExpense.getTripDetails_list().get(0).getEndAddress());
            rowCell.setCellStyle(style);

            rowCell = row.createCell(6);// ������
            rowCell.setCellValue(applyExpense.getTripDetails_list().get(0).getVehicle());
            rowCell.setCellStyle(style);
          } else {
            rowCell = row.createCell(4);// ������
            rowCell.setCellValue("");
            rowCell.setCellStyle(style);

            rowCell = row.createCell(5);// ������
            rowCell.setCellValue("");
            rowCell.setCellStyle(style);

            rowCell = row.createCell(6);// ������
            rowCell.setCellValue("");
            rowCell.setCellStyle(style);
          }
          int rowNum = 0;
          for (int i = 1; i < applyExpense.getTripDetails_list().size(); i++) {
            rowNum = i;
            index++;
            // ����һ������
            XSSFRow newRow = sheet.createRow(index);
            rowCell = newRow.createCell(4);// ������
            rowCell.setCellValue(applyExpense.getTripDetails_list().get(i).getBeginAddress());
            rowCell.setCellStyle(style);

            rowCell = newRow.createCell(5);// ������
            rowCell.setCellValue(applyExpense.getTripDetails_list().get(i).getEndAddress());
            rowCell.setCellStyle(style);

            rowCell = newRow.createCell(6);// ������
            rowCell.setCellValue(applyExpense.getTripDetails_list().get(i).getVehicle());
            rowCell.setCellStyle(style);
          }
          if (rowNum > 0) {
            for (int i = 0; i < 16; i++) {
              if (i != 4 && i != 5 && i != 6)
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
}
