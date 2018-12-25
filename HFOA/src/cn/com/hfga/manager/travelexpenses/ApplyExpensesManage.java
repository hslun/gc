package cn.com.hfga.manager.travelexpenses;

import cn.com.hfga.dto.travelexpenses.ApplyExpensesDTO;
import cn.com.hfga.entity.travelExpenses.ApplyExpensesEntity;
import org.springframework.stereotype.Component;

import java.util.List;

public interface ApplyExpensesManage {

  //移动端用户插入数据
  int insertTravelExpenses(ApplyExpensesDTO applyExpense);

  //移动端用户修改数据
  int modifyTravelExpenses(ApplyExpensesDTO applyExpense,String RoleId);

  //移动端部门审核通过
  int departmentSuccess(String id);

  //移动端部门审核不通过
  int departmentFail(String id);

  //移动端申请执行
  int applyImplement(String id);

  //移动端修改某些字段后需重新审核
  int reApplyImplement(String id);

  //移动端根据id获取数据
  List<ApplyExpensesEntity> getApplyExpenseById(String id);

  //移动端获取某个人待审批的数据
  List<ApplyExpensesEntity> getWaitApplyExpenseByName(String applyMan);

  //移动端获取经理待审批的数据
  List<ApplyExpensesEntity> getWaitApplyExpenseByManager(String approveMan);

  //移动端获取某个人部门审批通过后待执行的数据
  List<ApplyExpensesEntity> getPassApplyExpenseByName(String applyMan);

  //移动端获取某个人部门审批不通过待修改的数据
  List<ApplyExpensesEntity> getNoPassApplyExpenseByName(String applyMan);

  //移动端获取某个人待财务审批的数据
  List<ApplyExpensesEntity> getReviewFail(String applyMan);

  //移动端获取某个人审批登记的数据
  List<ApplyExpensesEntity> getReviewSuccessByName(String applyMan);

  //移动端获取某个人申请所有数据
  List<ApplyExpensesEntity> getAllState(String applyMan);

  //移动端根据id撤销申请
  int deleteApply(String id);

  //web端财务审核--驳回
  int financeReviewFail(String id);

  //web端财务审核--登记
  int financeReviewSuccess(String id);

  //web端待登记列表、已登记列表初始化及其相关查询
  List<ApplyExpensesDTO> searchTravelExpenses(Integer state, ApplyExpensesDTO applyExpense);

  List<ApplyExpensesDTO> listTravelExpense( ApplyExpensesDTO applyExpense);
  
  //导出excel--差旅费待登记列表
  int exportApplyExpenses(ApplyExpensesDTO applyExpense, String path);

}
