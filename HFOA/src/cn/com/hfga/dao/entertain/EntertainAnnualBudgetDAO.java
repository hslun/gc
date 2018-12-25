package cn.com.hfga.dao.entertain;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cn.com.hfga.dao.common.SpringDataDAO;
import cn.com.hfga.entity.entertain.EntertainAnnualBudgetEntity;

@Repository("entertainAnnualBudgetDAO")
public interface EntertainAnnualBudgetDAO extends SpringDataDAO<EntertainAnnualBudgetEntity>{

/*	// web-招待年度预算-显示
	@Query(nativeQuery = true, value = "select * from B_EntertainAnnualBudget")
	List<EntertainAnnualBudgetEntity> wGetAnnualBudget();
*/
	@Query(nativeQuery = true, value = "select * from B_EntertainAnnualBudget where Year=?1 order by `Order` desc")
	List<EntertainAnnualBudgetEntity> wGetAnnualBudget(String year);

	// 根据id获得Time的值
	@Query(nativeQuery = true, value = "select Time from B_EntertainAnnualBudget where `Order`=?1 and `Year`=?2")
	String getTimeById(String order, String year);
	
	// time=0的时候
	@Modifying
	@Query(nativeQuery = true, value = "update B_EntertainAnnualBudget set BudgetSum0=?2,CopileTime0=?3,Time=1 where `Year`=?4 and `Order`=?1")
	int updateBudgetSum(String order, String adjustSum, String df, String year);
	
	// time=1的时候
	@Modifying
	@Query(nativeQuery = true, value = "update B_EntertainAnnualBudget set BudgetSum1=?2,CopileTime1=?3,Time=2 where `Year`=?4 and `Order`=?1")
	int updateBudgetSum0(String order, String adjustSum, String df, String year);

	// time=2的时候
	@Modifying
	@Query(nativeQuery = true, value = "update B_EntertainAnnualBudget set BudgetSum2=?2,CopileTime2=?3,Time=3 where `Year`=?4 and `Order`=?1")
	int updateBudgetSum1(String order, String adjustSum, String df, String year);

	// time=3的时候
	@Modifying
	@Query(nativeQuery = true, value = "update B_EntertainAnnualBudget set BudgetSum3=?2,CopileTime3=?3,Time=4 where `Year`=?4 and `Order`=?1")
	int updateBudgetSum2(String order, String adjustSum, String df, String year);

	// time=4的时候
	@Modifying
	@Query(nativeQuery = true, value = "update B_EntertainAnnualBudget set BudgetSum4=?2,CopileTime4=?3,Time=5 where `Year`=?4 and `Order`=?1")
	int updateBudgetSum3(String order, String adjustSum, String df, String year);
	
	// time=5的时候
	@Modifying
	@Query(nativeQuery = true, value = "update B_EntertainAnnualBudget set BudgetSum5=?2,CopileTime5=?3,Time=6 where `Year`=?4 and `Order`=?1")
	int updateBudgetSum4(String order, String adjustSum, String df, String year);
	
	// 获得相关的年份	
	@Query(nativeQuery = true, value = "select DISTINCT YEAR FROM b_entertainannualbudget WHERE `Year`<?1")
	List<String> getYear(Integer y);
	
	// web-按照年份查询相信息
	@Query(nativeQuery = true, value = "select * from B_EntertainAnnualBudget where Year=?1")
	List<EntertainAnnualBudgetEntity> wGetSearchAnnual(String year);

	// 获得某一部门当年的财务信息
	@Query(nativeQuery = true, value = "select * from B_EntertainAnnualBudget where Department=?1 and Year=?2")
	EntertainAnnualBudgetEntity getEntity(String department, String year);
	
}
