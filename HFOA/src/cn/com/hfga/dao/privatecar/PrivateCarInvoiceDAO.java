package cn.com.hfga.dao.privatecar;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cn.com.hfga.dao.common.SpringDataDAO;
import cn.com.hfga.entity.privatecar.PrivateCarApplyEntity;
import cn.com.hfga.entity.privatecar.PrivateCarInvoiceEntity;

@Repository("privateCarInvoiceDAO")
public interface PrivateCarInvoiceDAO extends SpringDataDAO<PrivateCarInvoiceEntity>{
	
	//保存
	@Modifying
	@Query(nativeQuery=true,value="insert into b_privatecarinvoice ("
			+ "ApplyId,ApplyMan,"
			+ "ApproveMan,ApplyTime,Sum,SureLength,VoucherNum,Status,PaidTime,ApplyIds)"
			+ "VALUES(:ApplyId,:ApplyMan,"
			+ ":ApproveMan,:ApplyTime,:Sum,:SureLength,:VoucherNum,:Status,:PaidTime,:ApplyIds)")
	public int insertEntity(@Param(value="ApplyId")String ApplyId,
			@Param(value="ApplyMan")String ApplyMan,
			@Param(value="ApproveMan")String ApproveMan,
			@Param(value="ApplyTime")String ApplyTime,
			@Param(value="Sum")String Sum,
			@Param(value="SureLength")String SureLength,
			@Param(value="VoucherNum")String VoucherNum,
			@Param(value="Status")String Status,
			@Param(value="PaidTime")String PaidTime,
			@Param(value="ApplyIds")String ApplyIds
	);
	
	//修改申请
	@Modifying
	@Query(nativeQuery=true,value="update b_privatecarinvoice "
			+ "set ApplyMan=:ApplyMan,"
			+ "ApproveMan=:ApproveMan,"
			+ "ApplyTime=:ApplyTime,"
			+ "Sum=:Sum,"
			+ "SureLength=:SureLength,"
			+ "VoucherNum=:VoucherNum,"
			+ "Status=:Status,"
			+ "PaidTime=:PaidTime,"
			+ "ApplyIds=:ApplyIds "
			+ "where ApplyId=:ApplyId")
	public int modifyEntity(@Param(value="ApplyId")String ApplyId,
			@Param(value="ApplyMan")String ApplyMan,
			@Param(value="ApproveMan")String ApproveMan,
			@Param(value="ApplyTime")String ApplyTime,
			@Param(value="Sum")String Sum,
			@Param(value="SureLength")String SureLength,
			@Param(value="VoucherNum")String VoucherNum,
			@Param(value="Status")String Status,
			@Param(value="PaidTime")String PaidTime,
			@Param(value="ApplyIds")String ApplyIds
	);
	@Modifying
	@Query(nativeQuery = true, value = "update b_privatecar set IfPass = '未报销' where ApplyId =?1")
	public int updatePrivateCarIfPass(String applyid);
	
	@Query(nativeQuery = true, value = "select * from b_privatecarinvoice where Status = '待审核'")
	public List<PrivateCarInvoiceEntity> selectAllUnPass();
	
	@Modifying
	@Query(nativeQuery = true, value = "update b_privatecarinvoice set Status = '已报销' where ApplyId =?1")
	public int updateStatus(String applyid);
	
	@Query(nativeQuery = true, value = "select * from b_privatecarinvoice where Status = '已报销'")
	public List<PrivateCarInvoiceEntity> selectAllPassed();
	
	//修改申请
	@Modifying
	@Query(nativeQuery=true,value="update b_privatecarinvoice "
			+ "set Status='已完成',ApproveMan=:ApproveMan,"
			+ "VoucherNum=:VoucherNum,"
			+ "PaidTime=:PaidTime "
			+ "where ApplyId=:ApplyId")
	public int modifyInvoice(@Param(value="ApplyId")String ApplyId,
			@Param(value="ApproveMan")String ApproveMan,
			@Param(value="VoucherNum")String VoucherNum,
			@Param(value="PaidTime")String PaidTime
	);
	
	@Query(nativeQuery = true, value="select * from b_privatecarinvoice where ApplyId =?1")
	public PrivateCarInvoiceEntity selectByApplyId(String applyid);
	
	@Query(nativeQuery = true, value="select * from b_privatecarinvoice where ApplyIds like ?1")
	public PrivateCarInvoiceEntity selectByApplyIds(String applyid);
	
	@Modifying
	@Query(nativeQuery = true, value="update b_privatecarinvoice set ApplyIds =?1 where ApplyId =?2")
	public int updateApplyIds(String applyids,String applyid);
	
	@Modifying
	@Query(nativeQuery = true, value="delete from b_privatecarinvoice where ApplyId =?1")
	public int deleteByApplyid(String applyid);
	
	@Query(nativeQuery = true, value="select * from b_privatecarinvoice where Status = '已完成' order by ApplyId desc")
	public List<PrivateCarInvoiceEntity> selectPessed();
	
	//web获取所有已提交，待报销汇总
	@Query(nativeQuery = true, value = "select * from b_privatecarinvoice where Status = '已完成' order by ApplyId desc limit ?1,?2")
	public List<PrivateCarInvoiceEntity> invoiceDisplay(int start, int number);
	
	//web获取所有已提交，待报销汇总总数
	@Query(nativeQuery = true, value = "select count(*) from b_privatecarinvoice where Status = '已完成'")
	public int getAllCount();
	
	//web获取所有未登记汇总
	@Query(nativeQuery = true, value = "select * from b_privatecarinvoice where Status = '已报销' order by ApplyId desc limit ?1,?2")
	public List<PrivateCarInvoiceEntity> UnSignInvoiceDisplay(int start, int number);
	
	//web获取所有未登记汇总总数
	@Query(nativeQuery = true, value = "select count(*) from b_privatecarinvoice where Status = '已报销'")
	public int getAllUnSignInvoiceCount();
	
	//web获取所有已提交，待报销汇总
	@Query(nativeQuery = true, value = "select * from b_privatecarinvoice where Status='待审核'  order by ApplyId desc limit ?1,?2")
	public List<PrivateCarInvoiceEntity> uninvoiceDisplay(int start, int number);
	
	//web获取所有已提交，待报销汇总总数
	@Query(nativeQuery = true, value = "select count(*) from b_privatecarinvoice where Status='待审核'")
	public int ungetAllCount();
	
	@Modifying
	@Query(nativeQuery = true, value = "update b_privatecarinvoice set Status = '已驳回' where ApplyId =?1")
	public int updateStatusBack(String applyid);
	
	//用户查看已驳回凭单
	@Query(nativeQuery = true, value = "select * from b_privatecarinvoice where Status = '已驳回' and ApplyMan =:ApplyMan")
	public List<PrivateCarInvoiceEntity> selectBackList(@Param(value="ApplyMan")String ApplyMan);
	
	@Query(nativeQuery=true, value="select * from b_privatecarinvoice where Status = '已完成' and ApplyIds like ?1")
	public PrivateCarInvoiceEntity getByNumber(String paramString);
	  
	@Modifying
	@Query(nativeQuery=true, value="delete from b_privatecarinvoice where ApplyIds like ?1")
	public abstract void deleteByApplyIds(String paramString);

	//根据审核单号获取所有今天办理的数量
	@Query(nativeQuery = true, value = "select count(*) from b_privatecarinvoice where VoucherNum like ?1")
	public int getCountByVoucher(String varnum);

	//修改金额表
	@Modifying
	@Query(nativeQuery=true,value="update b_privatecarinvoice "
			+ "set ApplyMan=:ApplyMan,"
			+ "ApproveMan=:ApproveMan,"
			+ "ApplyTime=:ApplyTime,"
			+ "Sum=:Sum,"
			+ "SureLength=:SureLength,"
			+ "VoucherNum=:VoucherNum,"
			+ "Status=:Status,"
			+ "PaidTime=:PaidTime,"
			+ "ApplyIds=:ApplyIds "
			+ "where ApplyId=:ApplyId")
	public void updateEntity(@Param(value="ApplyId")String ApplyId,
			@Param(value="ApplyMan")String ApplyMan,
			@Param(value="ApproveMan")String ApproveMan,
			@Param(value="ApplyTime")String ApplyTime,
			@Param(value="Sum")String Sum,
			@Param(value="SureLength")String SureLength,
			@Param(value="VoucherNum")String VoucherNum,
			@Param(value="Status")String Status,
			@Param(value="PaidTime")String PaidTime,
			@Param(value="ApplyIds")String ApplyIds);

	@Query(nativeQuery=true, value="select * from b_privatecarinvoice where ApplyIds like ?1")
	public PrivateCarInvoiceEntity getByNumberes(String applyids);
}
