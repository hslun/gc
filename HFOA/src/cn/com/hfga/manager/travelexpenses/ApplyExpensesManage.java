package cn.com.hfga.manager.travelexpenses;

import cn.com.hfga.dto.travelexpenses.ApplyExpensesDTO;
import cn.com.hfga.entity.travelExpenses.ApplyExpensesEntity;
import org.springframework.stereotype.Component;

import java.util.List;

public interface ApplyExpensesManage {

  //�ƶ����û���������
  int insertTravelExpenses(ApplyExpensesDTO applyExpense);

  //�ƶ����û��޸�����
  int modifyTravelExpenses(ApplyExpensesDTO applyExpense,String RoleId);

  //�ƶ��˲������ͨ��
  int departmentSuccess(String id);

  //�ƶ��˲�����˲�ͨ��
  int departmentFail(String id);

  //�ƶ�������ִ��
  int applyImplement(String id);

  //�ƶ����޸�ĳЩ�ֶκ����������
  int reApplyImplement(String id);

  //�ƶ��˸���id��ȡ����
  List<ApplyExpensesEntity> getApplyExpenseById(String id);

  //�ƶ��˻�ȡĳ���˴�����������
  List<ApplyExpensesEntity> getWaitApplyExpenseByName(String applyMan);

  //�ƶ��˻�ȡ���������������
  List<ApplyExpensesEntity> getWaitApplyExpenseByManager(String approveMan);

  //�ƶ��˻�ȡĳ���˲�������ͨ�����ִ�е�����
  List<ApplyExpensesEntity> getPassApplyExpenseByName(String applyMan);

  //�ƶ��˻�ȡĳ���˲���������ͨ�����޸ĵ�����
  List<ApplyExpensesEntity> getNoPassApplyExpenseByName(String applyMan);

  //�ƶ��˻�ȡĳ���˴���������������
  List<ApplyExpensesEntity> getReviewFail(String applyMan);

  //�ƶ��˻�ȡĳ���������Ǽǵ�����
  List<ApplyExpensesEntity> getReviewSuccessByName(String applyMan);

  //�ƶ��˻�ȡĳ����������������
  List<ApplyExpensesEntity> getAllState(String applyMan);

  //�ƶ��˸���id��������
  int deleteApply(String id);

  //web�˲������--����
  int financeReviewFail(String id);

  //web�˲������--�Ǽ�
  int financeReviewSuccess(String id);

  //web�˴��Ǽ��б��ѵǼ��б��ʼ��������ز�ѯ
  List<ApplyExpensesDTO> searchTravelExpenses(Integer state, ApplyExpensesDTO applyExpense);

  List<ApplyExpensesDTO> listTravelExpense( ApplyExpensesDTO applyExpense);
  
  //����excel--���÷Ѵ��Ǽ��б�
  int exportApplyExpenses(ApplyExpensesDTO applyExpense, String path);

}
