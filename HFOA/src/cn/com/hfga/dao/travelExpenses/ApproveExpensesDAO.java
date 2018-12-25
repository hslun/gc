package cn.com.hfga.dao.travelExpenses;

import cn.com.hfga.dao.common.SpringDataDAO;
import cn.com.hfga.dto.travelexpenses.ApproveExpensesDTO;
import cn.com.hfga.entity.travelExpenses.ApproveExpensesEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("approveExpensesDAO")
public interface ApproveExpensesDAO extends SpringDataDAO<ApproveExpensesEntity> {
  //财务审核--登记
//  @Modifying
//  @Query(nativeQuery = true, value = "" +
//      "INSERT INTO b_travelexpensesapprove (ID,TravelExpenseId,VehicleCost,FoodAllowance,HotelExpense," +
//      "ParValueAllowance,UrbanTraffic,OtherCost,RepayCost,SupplementalCost,InputTax,TotalExpenses," +
//      "PaidTime,FundSource,VoucherNum,IsTestCost,TestSite,Illustration) " +
//      "VALUES (:ID,:TravelExpenseId,:VehicleCost,:FoodAllowance,:HotelExpense,:ParValueAllowance," +
//      ":UrbanTraffic,:OtherCost,:RepayCost,:SupplementalCost,:InputTax,:TotalExpenses,:PaidTime," +
//      ":FundSource,:VoucherNum,:IsTestCost,:TestSite,:Illustration)")
//  int insertFinanceReview(@Param(value="ID")String ID,@Param(value="TravelExpenseId")String TravelExpenseId,
//                          @Param(value="VehicleCost")float VehicleCost,@Param(value="FoodAllowance")float FoodAllowance,
//                          @Param(value="HotelExpense")float HotelExpense,@Param(value="ParValueAllowance")float ParValueAllowance,
//                          @Param(value="UrbanTraffic")float UrbanTraffic,@Param(value="OtherCost")float OtherCost,
//                          @Param(value="RepayCost")float RepayCost,@Param(value="SupplementalCost")float SupplementalCost,
//                          @Param(value="InputTax")float InputTax,@Param(value="TotalExpenses")float TotalExpenses,
//                          @Param(value="PaidTime")String PaidTime,@Param(value="FundSource")String FundSource,
//                          @Param(value="VoucherNum")String VoucherNum,@Param(value="IsTestCost")String IsTestCost,
//                          @Param(value="TestSite")String TestSite,@Param(value="Illustration")String Illustration);

  //财务审核--登记后修改
  @Modifying
  @Query(nativeQuery = true, value = "" +
      "UPDATE b_travelexpensesapprove SET VehicleCost=:VehicleCost,FoodAllowance=:FoodAllowance," +
      "HotelExpense=:HotelExpense,ParValueAllowance=:ParValueAllowance,UrbanTraffic=:UrbanTraffic," +
      "OtherCost=:OtherCost,RepayCost=:RepayCost,SupplementalCost=:SupplementalCost,InputTax=:InputTax," +
      "TotalExpenses=:TotalExpenses,PaidTime=:PaidTime,FundSource=:FundSource,VoucherNum=:VoucherNum," +
      "IsTestCost=:IsTestCost,TestSite=:TestSite,Illustration=:Illustration WHERE ID=:ID")
  int modifyFinanceReview(@Param(value="ID")String ID, @Param(value="VehicleCost")float VehicleCost,
                          @Param(value="FoodAllowance")float FoodAllowance, @Param(value="HotelExpense")float HotelExpense,
                          @Param(value="ParValueAllowance")float ParValueAllowance,
                          @Param(value="UrbanTraffic")float UrbanTraffic,@Param(value="OtherCost")float OtherCost,
                          @Param(value="RepayCost")float RepayCost,@Param(value="SupplementalCost")float SupplementalCost,
                          @Param(value="InputTax")float InputTax,@Param(value="TotalExpenses")float TotalExpenses,
                          @Param(value="PaidTime")String PaidTime,@Param(value="FundSource")String FundSource,
                          @Param(value="VoucherNum")String VoucherNum,@Param(value="IsTestCost")String IsTestCost,
                          @Param(value="TestSite")String TestSite,@Param(value="Illustration")String Illustration);

  //财务审核--根据TravelExpenseId查看
  @Query(nativeQuery = true, value = "" +
      "SELECT * FROM b_travelexpensesapprove WHERE TravelExpenseId=:TravelExpenseId")
  List<ApproveExpensesDTO> getApproveExpensesByTId(@Param("TravelExpenseId") String TravelExpenseId);

  //财务审核--根据id查看
  @Query(nativeQuery = true, value = "" +
      "SELECT * FROM b_travelexpensesapprove WHERE ID=:ID")
  List<ApproveExpensesDTO> getApproveExpensesById(@Param("ID") String ID);
  
  
  @Modifying
  @Query(nativeQuery = true, value = "" +
      "UPDATE b_travelexpensesapprove SET VehicleCost=:VehicleCost,FoodAllowance=:FoodAllowance," +
      "HotelExpense=:HotelExpense,ParValueAllowance=:ParValueAllowance,UrbanTraffic=:UrbanTraffic," +
      "OtherCost=:OtherCost,RepayCost=:RepayCost,SupplementalCost=:SupplementalCost,InputTax=:InputTax," +
      "TotalExpenses=:TotalExpenses,PaidTime=:PaidTime,FundSource=:FundSource,VoucherNum=:VoucherNum," +
      "IsTestCost=:IsTestCost,TestSite=:TestSite,Illustration=:Illustration WHERE ID=:ID")
  int updateFinanceReview(@Param(value="ID")String ID, @Param(value="VehicleCost")float VehicleCost,
                          @Param(value="FoodAllowance")float FoodAllowance, @Param(value="HotelExpense")float HotelExpense,
                          @Param(value="ParValueAllowance")float ParValueAllowance,
                          @Param(value="UrbanTraffic")float UrbanTraffic,@Param(value="OtherCost")float OtherCost,
                          @Param(value="RepayCost")float RepayCost,@Param(value="SupplementalCost")float SupplementalCost,
                          @Param(value="InputTax")float InputTax,@Param(value="TotalExpenses")float TotalExpenses,
                          @Param(value="PaidTime")String PaidTime,@Param(value="FundSource")String FundSource,
                          @Param(value="VoucherNum")String VoucherNum,@Param(value="IsTestCost")String IsTestCost,
                          @Param(value="TestSite")String TestSite,@Param(value="Illustration")String Illustration);
  
  	//Web-删除用户
	@Modifying
	@Query(nativeQuery = true,value=" delete from b_travelexpensesapprove where Id=:Id ")
	public int deleteApproveExpenses(@Param(value="Id") String Id);
	
  
}
