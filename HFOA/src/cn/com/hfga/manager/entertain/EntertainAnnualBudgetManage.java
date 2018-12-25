package cn.com.hfga.manager.entertain;

import java.util.List;

import cn.com.hfga.dto.entertain.EntertainAnnualInfoDTO;
import cn.com.hfga.entity.entertain.EntertainAnnualBudgetEntity;

public interface EntertainAnnualBudgetManage {
	
	// web-招待年度预算-显示
	List<EntertainAnnualInfoDTO> wGetAnnualBudget();
	
	List<EntertainAnnualBudgetEntity> wGetNowAnnual1(String year);

	// web-插入修改的年度预算值
	int wSetAdjust(String param);

	// web-显示预算执行情况列表
	List<EntertainAnnualBudgetEntity> wGetNowAnnual();

	// 根据部门获取该部门剩余的金额
	Object getLastSum(String department);

}
