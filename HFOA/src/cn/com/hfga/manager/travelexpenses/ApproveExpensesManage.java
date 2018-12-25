package cn.com.hfga.manager.travelexpenses;

import cn.com.hfga.dto.travelexpenses.ApplyExpensesDTO;
import cn.com.hfga.dto.travelexpenses.ApproveExpensesDTO;
import cn.com.hfga.entity.travelExpenses.ApproveExpensesEntity;

import java.util.List;

public interface ApproveExpensesManage {

  //�������--�Ǽ�
  int insertFinanceReview(ApproveExpensesDTO approveExpense);
  

  //web�˲������--web�˲������--�ǼǺ��޸�
  int modifyFinanceReview(ApproveExpensesDTO approveExpense);

  //�������--����TravelExpenseId�鿴
  List<ApproveExpensesDTO> getApproveExpensesByTId(String travelExpenseId);

  //�������--����id�鿴
  List<ApproveExpensesDTO> getApproveExpensesById(String id);

  //web�˲���Ǽ����ݳ�ʼ��������ز�ѯ
  List<ApproveExpensesDTO> searchApproveExpenses(ApproveExpensesDTO approveExpense);
  
  //web�˲�ѯ
  List<ApplyExpensesDTO> searchApplyExpense(ApplyExpensesDTO applyExpense,String starTime,String endTime,String voucherNum);
  
  List<ApproveExpensesDTO>listApproveExpenses(String q);

  //web��ɾ��һ����ӵ�����
  int deleteApproveExpenses(String ID);
  
  //����excel--���÷��ѵǼ��б�
  int exportapproveExpenses(ApplyExpensesDTO applyExpense, String path,String starTime,String searendTime, String voucherNum);
}
