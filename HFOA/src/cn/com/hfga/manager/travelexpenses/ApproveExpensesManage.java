package cn.com.hfga.manager.travelexpenses;

import cn.com.hfga.dto.travelexpenses.ApplyExpensesDTO;
import cn.com.hfga.dto.travelexpenses.ApproveExpensesDTO;
import cn.com.hfga.entity.travelExpenses.ApproveExpensesEntity;

import java.util.List;

public interface ApproveExpensesManage {

  //财务审核--登记
  int insertFinanceReview(ApproveExpensesDTO approveExpense);
  

  //web端财务审核--web端财务审核--登记后修改
  int modifyFinanceReview(ApproveExpensesDTO approveExpense);

  //财务审核--根据TravelExpenseId查看
  List<ApproveExpensesDTO> getApproveExpensesByTId(String travelExpenseId);

  //财务审核--根据id查看
  List<ApproveExpensesDTO> getApproveExpensesById(String id);

  //web端财务登记内容初始化及其相关查询
  List<ApproveExpensesDTO> searchApproveExpenses(ApproveExpensesDTO approveExpense);
  
  //web端查询
  List<ApplyExpensesDTO> searchApplyExpense(ApplyExpensesDTO applyExpense,String starTime,String endTime,String voucherNum);
  
  List<ApproveExpensesDTO>listApproveExpenses(String q);

  //web端删除一条添加的数据
  int deleteApproveExpenses(String ID);
  
  //导出excel--差旅费已登记列表
  int exportapproveExpenses(ApplyExpensesDTO applyExpense, String path,String starTime,String searendTime, String voucherNum);
}
