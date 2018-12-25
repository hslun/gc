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
  //�ƶ����û���������
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

  //�ƶ����û��޸�����
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

  //�ƶ��˲������ͨ��
  @Modifying
  @Query(nativeQuery = true, value = "UPDATE b_travelexpenses SET ApproveState='��ִ��' WHERE ID=:ID")
  int departmentSuccess(@Param(value = "ID") String ID);

  //�ƶ��˲�����˲�ͨ��
  @Modifying
  @Query(nativeQuery = true, value = "UPDATE b_travelexpenses SET ApproveState='���޸�' WHERE ID=:ID")
  int departmentFail(@Param(value = "ID") String ID);

  //�ƶ�������ִ��
  @Modifying
  @Query(nativeQuery = true, value = "UPDATE b_travelexpenses SET ApproveState='����������' WHERE ID=:ID")
  int applyImplement(@Param(value = "ID") String ID);

  //�ƶ����޸�ĳЩ�ֶκ����������
  @Modifying
  @Query(nativeQuery = true, value = "UPDATE b_travelexpenses SET ApproveState='����������' WHERE ID=:ID")
  int reApplyImplement(@Param(value = "ID") String ID);

  //�ƶ��˸���id��ȡ����
  @Query(nativeQuery = true, value = "SELECT * FROM b_travelexpenses WHERE ID=:ID")
  List<ApplyExpensesEntity> getApplyExpenseById(@Param(value = "ID") String ID);

//web�˻�ȡĳ���˲��ش��޸Ĵ�����������
  @Query(nativeQuery = true, value = "" +
      "SELECT * FROM b_travelexpenses WHERE ID=:id")
  ApplyExpensesEntity getApplyExpense(@Param(value = "id") String id);
  //�ƶ��˻�ȡĳ���˴�����������
  @Query(nativeQuery = true, value = "" +
      "SELECT * FROM b_travelexpenses WHERE ApplyMan=:ApplyMan AND ApproveState='����������'")
  List<ApplyExpensesEntity> getWaitApplyExpenseByName(@Param(value = "ApplyMan") String ApplyMan);

  //�ƶ��˻�ȡ���������������
  @Query(nativeQuery = true, value = "" +
      "SELECT * FROM b_travelexpenses WHERE ApproveMan=:ApproveMan AND ApproveState='����������'")
  List<ApplyExpensesEntity> getWaitApplyExpenseByManager(@Param(value = "ApproveMan") String ApproveMan);

  //�ƶ��˻�ȡĳ���˲�������ͨ�����ִ�е�����
  @Query(nativeQuery = true, value = "" +
      "SELECT * FROM b_travelexpenses WHERE ApplyMan=:ApplyMan AND (ApproveState='��ִ��')")
  List<ApplyExpensesEntity> getPassApplyExpenseByName(@Param(value = "ApplyMan") String ApplyMan);

  //�ƶ��˻�ȡĳ���˲���������ͨ�����޸ĵ�����
  @Query(nativeQuery = true, value = "" +
      "SELECT * FROM b_travelexpenses WHERE ApplyMan=:ApplyMan AND ApproveState='���޸�'")
  List<ApplyExpensesEntity> getNoPassApplyExpenseByName(@Param(value = "ApplyMan") String ApplyMan);

  //�ƶ��˻�ȡĳ���˴���������������
  @Query(nativeQuery = true, value = "" +
      "SELECT * FROM b_travelexpenses WHERE ApplyMan=:ApplyMan AND ApproveState='����������'")
  List<ApplyExpensesEntity> getReviewFail(@Param(value = "ApplyMan") String ApplyMan);

  //�ƶ��˻�ȡĳ���������Ǽǵ�����
  @Query(nativeQuery = true, value = "" +
      "SELECT * FROM b_travelexpenses WHERE ApplyMan=:ApplyMan AND ApproveState='�ѵǼ�'")
  List<ApplyExpensesEntity> getReviewSuccessByName(@Param(value = "ApplyMan") String ApplyMan);

  //�ƶ��˻�ȡĳ����������������
  @Query(nativeQuery = true, value = "SELECT * FROM b_travelexpenses WHERE ApplyMan=:ApplyMan AND ApproveState!='�ѵǼ�'")
  List<ApplyExpensesEntity> getAllState(@Param(value = "ApplyMan") String ApplyMan);

  //�ƶ��˸���id��������
  @Modifying
  @Query(nativeQuery = true, value = "delete from b_travelexpenses where ID=:ID")
  int deleteApply(@Param(value = "ID") String ID);

  //web�˲������--����
  @Modifying
  @Query(nativeQuery = true, value = "UPDATE b_travelexpenses SET ApproveState='���ش��޸�' WHERE ID=:ID")
  int financeReviewFail(@Param(value = "ID") String ID);

  //web�˲������--�Ǽ�
  @Modifying
  @Query(nativeQuery = true, value = "UPDATE b_travelexpenses SET ApproveState='�ѵǼ�' WHERE ID=:ID")
  int financeReviewSuccess(@Param(value = "ID") String ID);

}
