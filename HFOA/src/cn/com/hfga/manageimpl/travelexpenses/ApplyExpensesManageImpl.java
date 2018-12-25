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
   * 移动端用户插入数据
   *
   * @param applyExpense
   * @return
   */
  @Transactional
  @Override
  public int insertTravelExpenses(ApplyExpensesDTO applyExpense) {
    applyExpense.setId(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
    /*applyExpense.getIsTest().substring(applyExpense.getIsTest().lastIndexOf(","));*/
    applyExpense.setApproveState("待部门审批");
    return applyExpensesDAO.insertApplyExpenses(applyExpense.getId(), applyExpense.getDepartment(),
        applyExpense.getTravelers(), applyExpense.getCause(), applyExpense.getTripDetails(),
        applyExpense.getBeginTime(), applyExpense.getEndTime(), applyExpense.getTravelDays(),
        applyExpense.getTotalBudget(), applyExpense.getIsTest(), applyExpense.getApplyTime(),
        applyExpense.getApplyMan(), applyExpense.getApproveMan(), applyExpense.getRemarks(),
        applyExpense.getApproveState(),applyExpense.getTripMode());
  }

  /**
   * 移动端用户修改数据
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
	  if(expense.getApproveState().equals("驳回待修改")){
		  applyExpensesDAO.applyImplement(id);//待财务审批
	  }

	  //判断出行方式是否改变目的地是否改变
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
	  System.out.println("传入的数据为"+star);
	  System.out.println("查到的数据是"+end);
	  String [] ends = end.split(";");
	  for(int j=0;j<ends.length;j++){
		  if(star.indexOf(ends[j])==-1){
			  applyExpensesDAO.reApplyImplement(id);
			  flag=2;
		  }
	  }
	  System.out.println(travelAddress.size()==aftertravelAddress.size());
	  if(travelAddress.size()==aftertravelAddress.size()){
		  System.out.println("出行方式判断");
		  if(RoleId.equals("3")){
			  for(int i=0;i<travelAddress.size();i++){
				  System.out.println("公司老总的出行方式");
				  if(proIDcheck(travelAddress.get(i).getVehicle(),aftertravelAddress.get(i).getVehicle())){
					  applyExpensesDAO.reApplyImplement(id);
					  flag=2;
				  }
			  } 
		  }else{
			  for(int i=0;i<travelAddress.size();i++){
				  System.out.println("出行方式"+travelAddress.get(i).getVehicle());
				  System.out.println("传过来的出行方式"+aftertravelAddress.get(i).getVehicle());
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
  //判断时间
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
	  System.out.println("开始时间"+bengingdate);
	  System.out.println("结束时间"+enddate);
	  System.out.println("修改时间"+afterdate);
	  System.out.println("修改结束时间"+afterenddate);
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
  
  
  
  //公司领导出差优先级判断
  private boolean proIDcheck(String address,String afteradderss){
	  int a =0;
	  int b =0;
	  if(address.equals("飞机商务舱")){
		  a=1;
	  }
	  if(afteradderss.equals("飞机商务舱")){
		  b=1;
	  }
	  if(address.equals("飞机经济舱")){
		  a=0;
	  }
	  if(afteradderss.equals("飞机经济舱")){
		  b=0;
	  }
	  if(address.equals("火车高铁一等座")||address.equals("火车动车一等座")){
		  a=0;
	  }
	  if(afteradderss.equals("火车高铁一等座")||address.equals("火车动车一等座")){
		  b=0;
	  }
	  
	  if(address.equals("火车高铁二等座")||address.equals("火车动车软卧")||address.equals("火车动车二等座")||address.equals("火车其他列车软卧")||address.equals("火车其他列车硬卧")
			  ||address.equals("火车其他列车硬座")){
		  a=0;
	  }
	  if(afteradderss.equals("火车高铁二等座")||address.equals("火车动车软卧")||address.equals("火车动车二等座")||address.equals("火车其他列车软卧")||address.equals("火车其他列车硬卧")
			  ||address.equals("火车其他列车硬座")){
		  b=0;
	  }
	  
	  if(address.equals("轮船二等舱")||address.equals("轮船三等舱")||address.equals("租车")||address.equals("自驾")||address.equals("汽车")){
		  a=0;
	  }
      if(afteradderss.equals("轮船二等舱")||address.equals("轮船三等舱")||address.equals("租车")||address.equals("自驾")||address.equals("汽车")){
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
  
  
  //一般人员传入出行方式判断优先级
  private boolean chkeck(String address,String afteradderss){
	  int a =0;
	  int b =0;
	  if(address.equals("飞机商务舱")){
		  a=4;
	  }
	  if(afteradderss.equals("飞机商务舱")){
		  b=4;
	  }
	  if(address.equals("飞机经济舱")){
		  a=3;
	  }
	  if(afteradderss.equals("飞机经济舱")){
		  b=3;
	  }
	  if(address.equals("火车高铁一等座")||address.equals("火车动车一等座")){
		  a=2;
	  }
	  if(afteradderss.equals("火车高铁一等座")||address.equals("火车动车一等座")){
		  b=2;
	  }
	  
	  if(address.equals("火车高铁二等座")||address.equals("火车动车软卧")||address.equals("火车动车二等座")||address.equals("火车其他列车软卧")||address.equals("火车其他列车硬卧")
			  ||address.equals("火车其他列车硬座")){
		  a=1;
	  }
	  if(afteradderss.equals("火车高铁二等座")||address.equals("火车动车软卧")||address.equals("火车动车二等座")||address.equals("火车其他列车软卧")||address.equals("火车其他列车硬卧")
			  ||address.equals("火车其他列车硬座")){
		  b=1;
	  }
	  
	  if(address.equals("轮船二等舱")||address.equals("轮船三等舱")||address.equals("租车")||address.equals("自驾")||address.equals("汽车")){
		  a=0;
	  }
      if(afteradderss.equals("轮船二等舱")||address.equals("轮船三等舱")||address.equals("租车")||address.equals("自驾")||address.equals("汽车")){
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
   * 移动端部门审核通过
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
   * 移动端部门审核不通过
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
   * 移动端申请执行
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
   * 移动端修改某些字段后需重新审核
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
   * 移动端根据id获取数据
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
   * 移动端获取某个人待审批的数据
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
   * 移动端获取经理待审批的数据
   *
   * @param approveMan
   * @return
   */
  public List<ApplyExpensesEntity> getWaitApplyExpenseByManager(String approveMan) {
    return applyExpensesDAO.getWaitApplyExpenseByManager(approveMan);
  }

  /**
   * 移动端获取某个人部门审批通过后待执行的数据
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
   * 移动端获取某个人部门审批不通过待修改的数据
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
   * 移动端获取某个人待财务审批的数据
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
   * 移动端获取某个人审批登记的数据
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
   * 移动端获取某个人申请所有数据
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
   * 移动端根据id撤销申请
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
   * web端财务审核--驳回
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
   * web端财务审核--登记
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
   *移动端查询
   *
   * @param state
   * @param applyExpense
   * @return
   */
  @Override
  public List<ApplyExpensesDTO> listTravelExpense( ApplyExpensesDTO applyExpense) {
    StringBuffer sql = new StringBuffer("select * from b_travelexpenses   where 1=1 and ApproveState = '已登记'");
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

      /*//json字符串转成list
      List<TravelAddressDTO> travelAddress = (List<TravelAddressDTO>) JSONArray.toCollection(JSONArray.fromObject(tE
          .getTripDetails()), TravelAddressDTO.class);
      tE.setTripDetails_list(travelAddress);
      //list转成string
      tE.setTripDetails(transformToString(travelAddress));*/

      list.add(tE);
    }
    return list;
  }
  

  /**
   * web端待登记列表、已登记列表初始化及其相关查询
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
      sql.append(" and ApproveState = '待财务审批'");
    else if (state == 1)
      sql.append(" and ApproveState = '已登记'");

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

      //json字符串转成list
      List<TravelAddressDTO> travelAddress = (List<TravelAddressDTO>) JSONArray.toCollection(JSONArray.fromObject(tE
          .getTripDetails()), TravelAddressDTO.class);
      tE.setTripDetails_list(travelAddress);
      //list转成string
      System.out.println("jso转换list数据"+travelAddress);
      tE.setTripDetails(transformToString(travelAddress));
      System.out.println("字符修改是"+transformToString(travelAddress));
      list.add(tE);
    }
    return list;
  }

  private String transformToString(List<TravelAddressDTO> list) {
    String s = "";
    if (list.size() == 2) {
      s += "起点：" + list.get(0).getBeginAddress() + "，出差地：" +
          list.get(0).getEndAddress() + "，交通方式：" + list.get(0).getVehicle() + "<br>";
      s += "出差地：" + list.get(1).getBeginAddress() + "，终点：" +
          list.get(1).getEndAddress() + "，交通方式：" + list.get(1).getVehicle() + "<br>";
    } else if (list.size() > 2) {
      s += "起点：" + list.get(0).getBeginAddress() + "，出差地1：" +
          list.get(0).getEndAddress() + "，交通方式：" + list.get(0).getVehicle() + "<br> ";
      for (int i = 1; i < list.size(); i++) {
        s += "出差地" + i + "：" + list.get(i).getBeginAddress() + "，出差地" + (i + 1) + "：" +
            list.get(i).getEndAddress() + "，交通方式：" + list.get(i).getVehicle() + "<br> ";
      }
    }
    return s;
  }

  /**
   * 导出excel--差旅费待登记列表
   *
   * @param applyExpense
   * @param path
   * @return
   */
  @Override
  public int exportApplyExpenses(ApplyExpensesDTO applyExpense, String path) {
    // 得到数据集合
    List<ApplyExpensesDTO> applyExpenses = new ArrayList<ApplyExpensesDTO>();
    applyExpenses = searchTravelExpenses(0, applyExpense);
    // 如果不为null
    if (applyExpenses.size() > 0) {
      return exportApplyExpensesExcel(applyExpenses, path);
    } else {
      return 0;
    }
  }

  private int exportApplyExpensesExcel(List<ApplyExpensesDTO> applyExpenses, String path) {
    // 创建一个工作簿
    XSSFWorkbook workbook;
    try {
      workbook = new XSSFWorkbook();
      // 添加一个sheet,sheet名
      XSSFSheet sheet = workbook.createSheet("差旅费待登记列表");
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
      titleCell.setCellValue("差旅费待登记列表");
      titleCell.setCellStyle(titleStyle);
      // ------------以上为第一行------------
      // 在合适位置调整自适应
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
      // 设置其他正文单元格格式
      XSSFCellStyle style = workbook.createCellStyle();
      style.setAlignment(HorizontalAlignment.CENTER);
      // 设置第二行表头
      XSSFRow rowHeader = sheet.createRow(1);
      XSSFCell cell = rowHeader.createCell(0);// 第一列
      cell.setCellValue("审批单号");
      cell.setCellStyle(style);
      cell = rowHeader.createCell(1);// 第二列
      cell.setCellValue("部门");
      cell.setCellStyle(style);
      cell = rowHeader.createCell(2);// 第三列
      cell.setCellValue("出差人");
      cell.setCellStyle(style);
      cell = rowHeader.createCell(3);// 第四列
      cell.setCellValue("事由");
      cell.setCellStyle(style);
      cell = rowHeader.createCell(4);// 第五列
      cell.setCellValue("出发地");
      cell.setCellStyle(style);
      cell = rowHeader.createCell(5);// 第六列
      cell.setCellValue("目的地");
      cell.setCellStyle(style);
      cell = rowHeader.createCell(6);// 第七列
      cell.setCellValue("交通方式");
      cell.setCellStyle(style);
      cell = rowHeader.createCell(7);// 第八列
      cell.setCellValue("出发日期");
      cell.setCellStyle(style);
      cell = rowHeader.createCell(8);// 第九列
      cell.setCellValue("返回日期");
      cell.setCellStyle(style);
      cell = rowHeader.createCell(9);// 第十列
      cell.setCellValue("出差天数");
      cell.setCellStyle(style);
      cell = rowHeader.createCell(10);// 第十一列
      cell.setCellValue("总预算");
      cell.setCellStyle(style);
      cell = rowHeader.createCell(11);// 第十二列
      cell.setCellValue("是否属于试验");
      cell.setCellStyle(style);
      cell = rowHeader.createCell(12);// 第十三列
      cell.setCellValue("申请时间");
      cell.setCellStyle(style);
      cell = rowHeader.createCell(13);// 第十四列
      cell.setCellValue("申请人");
      cell.setCellStyle(style);
      cell = rowHeader.createCell(14);// 第十五列
      cell.setCellValue("审批人");
      cell.setCellStyle(style);
      cell = rowHeader.createCell(15);// 第十六列
      cell.setCellValue("备注");
      cell.setCellStyle(style);
      // 表头完成------------------
      int index = 1;// 行序号，应从第三行开始，每次循环进行++
      // 遍历集合将数据写到excel中
      if (applyExpenses.size() > 0) {
        for (ApplyExpensesDTO applyExpense : applyExpenses) {
          // 行号++，2开始
          index++;
          // 生成一个新行
          XSSFRow row = sheet.createRow(index);
          XSSFCell rowCell = row.createCell(0);// 第一列
          rowCell.setCellValue(applyExpense.getId());
          rowCell.setCellStyle(style);

          rowCell = row.createCell(1);// 第二列
          rowCell.setCellValue(applyExpense.getDepartment());
          rowCell.setCellStyle(style);

          rowCell = row.createCell(2);// 第三列
          rowCell.setCellValue(applyExpense.getTravelers());
          rowCell.setCellStyle(style);

          rowCell = row.createCell(3);// 第四列
          rowCell.setCellValue(applyExpense.getCause());
          rowCell.setCellStyle(style);

          rowCell = row.createCell(7);// 第八列
          rowCell.setCellValue(applyExpense.getBeginTime());
          rowCell.setCellStyle(style);

          rowCell = row.createCell(8);// 第九列
          rowCell.setCellValue(applyExpense.getEndTime());
          rowCell.setCellStyle(style);

          rowCell = row.createCell(9);// 第十列
          rowCell.setCellValue(applyExpense.getTravelDays());
          rowCell.setCellStyle(style);

          rowCell = row.createCell(10);// 第十一列
          rowCell.setCellValue(applyExpense.getTotalBudget());
          rowCell.setCellStyle(style);

          rowCell = row.createCell(11);// 第十二列
          rowCell.setCellValue(applyExpense.getIsTest());
          rowCell.setCellStyle(style);

          rowCell = row.createCell(12);// 第十三列
          rowCell.setCellValue(applyExpense.getApplyTime());
          rowCell.setCellStyle(style);

          rowCell = row.createCell(13);// 第十四列
          rowCell.setCellValue(applyExpense.getApplyMan());
          rowCell.setCellStyle(style);

          rowCell = row.createCell(14);// 第十五列
          rowCell.setCellValue(applyExpense.getApproveMan());
          rowCell.setCellStyle(style);

          rowCell = row.createCell(15);// 第十六列
          rowCell.setCellValue(applyExpense.getRemarks());
          rowCell.setCellStyle(style);

          if (applyExpense.getTripDetails_list().size() > 0) {
            rowCell = row.createCell(4);// 第五列
            rowCell.setCellValue(applyExpense.getTripDetails_list().get(0).getBeginAddress());
            rowCell.setCellStyle(style);

            rowCell = row.createCell(5);// 第六列
            rowCell.setCellValue(applyExpense.getTripDetails_list().get(0).getEndAddress());
            rowCell.setCellStyle(style);

            rowCell = row.createCell(6);// 第七列
            rowCell.setCellValue(applyExpense.getTripDetails_list().get(0).getVehicle());
            rowCell.setCellStyle(style);
          } else {
            rowCell = row.createCell(4);// 第五列
            rowCell.setCellValue("");
            rowCell.setCellStyle(style);

            rowCell = row.createCell(5);// 第六列
            rowCell.setCellValue("");
            rowCell.setCellStyle(style);

            rowCell = row.createCell(6);// 第七列
            rowCell.setCellValue("");
            rowCell.setCellStyle(style);
          }
          int rowNum = 0;
          for (int i = 1; i < applyExpense.getTripDetails_list().size(); i++) {
            rowNum = i;
            index++;
            // 生成一个新行
            XSSFRow newRow = sheet.createRow(index);
            rowCell = newRow.createCell(4);// 第五列
            rowCell.setCellValue(applyExpense.getTripDetails_list().get(i).getBeginAddress());
            rowCell.setCellStyle(style);

            rowCell = newRow.createCell(5);// 第六列
            rowCell.setCellValue(applyExpense.getTripDetails_list().get(i).getEndAddress());
            rowCell.setCellStyle(style);

            rowCell = newRow.createCell(6);// 第七列
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
      // 将文件保存到指定位置
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
