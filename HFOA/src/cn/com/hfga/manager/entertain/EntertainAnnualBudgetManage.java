package cn.com.hfga.manager.entertain;

import java.util.List;

import cn.com.hfga.dto.entertain.EntertainAnnualInfoDTO;
import cn.com.hfga.entity.entertain.EntertainAnnualBudgetEntity;

public interface EntertainAnnualBudgetManage {
	
	// web-�д����Ԥ��-��ʾ
	List<EntertainAnnualInfoDTO> wGetAnnualBudget();
	
	List<EntertainAnnualBudgetEntity> wGetNowAnnual1(String year);

	// web-�����޸ĵ����Ԥ��ֵ
	int wSetAdjust(String param);

	// web-��ʾԤ��ִ������б�
	List<EntertainAnnualBudgetEntity> wGetNowAnnual();

	// ���ݲ��Ż�ȡ�ò���ʣ��Ľ��
	Object getLastSum(String department);

}
