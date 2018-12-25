package cn.com.hfga.dao.travelExpenses;

import cn.com.hfga.dao.common.SpringDataDAO;
import cn.com.hfga.entity.travelExpenses.ApplyExpensesEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("applyExpensesDAO")
public interface ApplyExpensesDAO extends SpringDataDAO<ApplyExpensesEntity> {
  //移动端用户插入数据
  @Modifying
  @Query(nativeQuery = true, value = "" +
      "INSERT INTO b_travelexpenses (ID,Department,Travelers,Cause,TripDetails,BeginTime,EndTime," +
      "TravelDays,TotalBudget,IsTest,ApplyTime,ApplyMan,ApproveMan,Remarks,ApproveState,TripMode) " +
      "VALUES (:ID,:Department,:Travelers,:Cause,:TripDetails,:BeginTime,:EndTime,:TravelDays," +
      ":TotalBudget,:IsTest,:ApplyTime,:ApplyMan,:ApproveMan,:Remarks,:ApproveState,:TripMode)")
  int insertApplyExpenses(@Param(value="ID")String ID, @Param(value="Department")String Department,
                          @Param(value="Travelers")String Travelers, @Param(value="Cause")String Cause,
                          @Param(value="TripDetails")String TripDetails, @Param(value="BeginTime")String BeginTime,
                          @Param(value="EndTime")String EndTime, @Param(value="TravelDays")int TravelDays,
                          @Param(value="TotalBudget")float TotalBudget, @Param(value="IsTest")String IsTest,
                          @Param(value="ApplyTime")String ApplyTime, @Param(value="ApplyMan")String ApplyMan,
                          @Param(value="ApproveMan")String ApproveMan, @Param(value="Remarks")String Remarks,
                          @Param(value="ApproveState")String ApproveState,
                          @Param(value="TripMode")String TripMode);

  //移动端用户修改数据
  @Modifying
  @Query(nativeQuery = true, value = "" +
      "UPDATE b_travelexpenses SET Department=:Department,Travelers=:Travelers,Cause=:Cause," +
      "TripDetails=:TripDetails,BeginTime=:BeginTime,EndTime=:EndTime,TravelDays=:TravelDays," +
      "TotalBudget=:TotalBudget,IsTest=:IsTest,ApplyTime=:ApplyTime,ApplyMan=:ApplyMan,ApproveMan=:ApproveMan," +
      "Remarks=:Remarks WHERE ID=:ID")
  int modifyApplyExpenses(@Param(value="ID")String ID, @Param(value="Department")String Department,
                          @Param(value="Travelers")String Travelers, @Param(value="Cause")String Cause,
                          @Param(value="TripDetails")String TripDetails, @Param(value="BeginTime")String BeginTime,
                          @Param(value="EndTime")String EndTime, @Param(value="TravelDays")int TravelDays,
                          @Param(value="TotalBudget")float TotalBudget, @Param(value="IsTest")String IsTest,
                          @Param(value="ApplyTime")String ApplyTime, @Param(value="ApplyMan")String ApplyMan,
                          @Param(value="ApproveMan")String ApproveMan, @Param(value="Remarks")String Remarks);

  //移动端部门审核通过
  @Modifying
  @Query(nativeQuery = true, value = "UPDATE b_travelexpenses SET ApproveState='待执行' WHERE ID=:ID")
  int departmentSuccess(@Param(value = "ID") String ID);

  //移动端部门审核不通过
  @Modifying
  @Query(nativeQuery = true, value = "UPDATE b_travelexpenses SET ApproveState='待修改' WHERE ID=:ID")
  int departmentFail(@Param(value = "ID") String ID);

  //移动端申请执行
  @Modifying
  @Query(nativeQuery = true, value = "UPDATE b_travelexpenses SET ApproveState='待财务审批' WHERE ID=:ID")
  int applyImplement(@Param(value = "ID") String ID);

  //移动端修改某些字段后需重新审核
  @Modifying
  @Query(nativeQuery = true, value = "UPDATE b_travelexpenses SET ApproveState='待部门审批' WHERE ID=:ID")
  int reApplyImplement(@Param(value = "ID") String ID);

  //移动端根据id获取数据
  @Query(nativeQuery = true, value = "SELECT * FROM b_travelexpenses WHERE ID=:ID")
  List<ApplyExpensesEntity> getApplyExpenseById(@Param(value = "ID") String ID);

//web端获取某个人驳回待修改待审批的数据
  @Query(nativeQuery = true, value = "" +
      "SELECT * FROM b_travelexpenses WHERE ID=:id")
  ApplyExpensesEntity getApplyExpense(@Param(value = "id") String id);
  //移动端获取某个人待审批的数据
  @Query(nativeQuery = true, value = "" +
      "SELECT * FROM b_travelexpenses WHERE ApplyMan=:ApplyMan AND ApproveState='待部门审批'")
  List<ApplyExpensesEntity> getWaitApplyExpenseByName(@Param(value = "ApplyMan") String ApplyMan);

  //移动端获取经理待审批的数据
  @Query(nativeQuery = true, value = "" +
      "SELECT * FROM b_travelexpenses WHERE ApproveMan=:ApproveMan AND ApproveState='待部门审批'")
  List<ApplyExpensesEntity> getWaitApplyExpenseByManager(@Param(value = "ApproveMan") String ApproveMan);

  //移动端获取某个人部门审批通过后待执行的数据
  @Query(nativeQuery = true, value = "" +
      "SELECT * FROM b_travelexpenses WHERE ApplyMan=:ApplyMan AND (ApproveState='待执行')")
  List<ApplyExpensesEntity> getPassApplyExpenseByName(@Param(value = "ApplyMan") String ApplyMan);

  //移动端获取某个人部门审批不通过待修改的数据
  @Query(nativeQuery = true, value = "" +
      "SELECT * FROM b_travelexpenses WHERE ApplyMan=:ApplyMan AND ApproveState='待修改'")
  List<ApplyExpensesEntity> getNoPassApplyExpenseByName(@Param(value = "ApplyMan") String ApplyMan);

  //移动端获取某个人待财务审批的数据
  @Query(nativeQuery = true, value = "" +
      "SELECT * FROM b_travelexpenses WHERE ApplyMan=:ApplyMan AND ApproveState='待财务审批'")
  List<ApplyExpensesEntity> getReviewFail(@Param(value = "ApplyMan") String ApplyMan);

  //移动端获取某个人审批登记的数据
  @Query(nativeQuery = true, value = "" +
      "SELECT * FROM b_travelexpenses WHERE ApplyMan=:ApplyMan AND ApproveState='已登记'")
  List<ApplyExpensesEntity> getReviewSuccessByName(@Param(value = "ApplyMan") String ApplyMan);

  //移动端获取某个人申请所有数据
  @Query(nativeQuery = true, value = "SELECT * FROM b_travelexpenses WHERE ApplyMan=:ApplyMan AND ApproveState!='已登记'")
  List<ApplyExpensesEntity> getAllState(@Param(value = "ApplyMan") String ApplyMan);

  //移动端根据id撤销申请
  @Modifying
  @Query(nativeQuery = true, value = "delete from b_travelexpenses where ID=:ID")
  int deleteApply(@Param(value = "ID") String ID);

  //web端财务审核--驳回
  @Modifying
  @Query(nativeQuery = true, value = "UPDATE b_travelexpenses SET ApproveState='驳回待修改' WHERE ID=:ID")
  int financeReviewFail(@Param(value = "ID") String ID);

  //web端财务审核--登记
  @Modifying
  @Query(nativeQuery = true, value = "UPDATE b_travelexpenses SET ApproveState='已登记' WHERE ID=:ID")
  int financeReviewSuccess(@Param(value = "ID") String ID);

}
