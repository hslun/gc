package cn.com.hfga.dao.entertain;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.hfga.dao.common.SpringDataDAO;
import cn.com.hfga.entity.entertain.EntertainApplyInfoEntity;
import cn.com.hfga.entity.entertain.EntertainRegisterInfoEntity;

@Repository("entertainRegisterInfoDAO")
public interface EntertainRegisterInfoDAO extends SpringDataDAO<EntertainRegisterInfoEntity> {

	// 事后-保存登记
	@Modifying
	@Query(nativeQuery = true, value = "insert into B_EntertainRegisterInfo(Number,InvoiceDate,InvoiceContent,InvoiceSum,InvoiceNum,InvoiceUnit,Status,RegisterMan,Remark,InvoiceNumber,WineSum,EnterSum,PersonSum)  VALUES(:Number,:InvoiceDate,:InvoiceContent,:InvoiceSum,:InvoiceNum,:InvoiceUnit,:Status,:RegisterMan,:Remark,:InvoiceNumber,:WineSum,:EnterSum,:PersonSum)")
	int insert(@Param(value = "Number") String number, @Param(value = "InvoiceDate") String invoiceDate,
			@Param(value = "InvoiceContent") String invoiceContent, @Param(value = "InvoiceSum") String invoiceSum,
			@Param(value = "InvoiceNum") String invoiceNum, 
			@Param(value = "InvoiceUnit") String invoiceUnit,@Param(value = "Status") String status, 
			@Param(value = "RegisterMan") String RegisterMan,@Param(value = "Remark") String remark,
			@Param(value = "InvoiceNumber") String invoiceNumber,
			@Param(value = "WineSum") String WineSum,
			@Param(value = "EnterSum") String EnterSum,
			@Param(value = "PersonSum") String PersonSum);

	// 事后-获取所有的登记信息
	@Query(nativeQuery = true, value = "select * from B_EntertainRegisterInfo order by ID desc")
	public List<EntertainRegisterInfoEntity> getAllRegister();

	// 事后-获取所有待审核信息
	@Query(nativeQuery = true, value = "select * from B_EntertainRegisterInfo where Status!='通过' order by ID desc")
	public List<EntertainRegisterInfoEntity> getAllReady();

	// 事后-更改Status √
	@Modifying
	@Query(nativeQuery = true, value = "update B_EntertainRegisterInfo set Status=:Status where Number=:Number")
	public int updateStatus(@Param(value = "Status") String Status, @Param(value = "Number") String number);
	// 事后-更改Status √
	@Modifying
	@Query(nativeQuery = true, value = "update B_EntertainRegisterInfo set Status=:Status where ID=:ID")
	public int updateStatusID(@Param(value = "Status") String Status, @Param(value = "ID") String ID);

	// 事后-修改登记信息 √
	@Modifying
	@Query(nativeQuery = true, value = "update B_EntertainRegisterInfo set PersonSum=:PersonSum,WineSum=:WineSum,EnterSum=:EnterSum,Number=:Number,InvoiceDate=:InvoiceDate,InvoiceContent=:InvoiceContent,InvoiceSum=:InvoiceSum,InvoiceNum=:InvoiceNum,InvoiceUnit=:InvoiceUnit,Status=:Status,RegisterMan=:RegisterMan,Remark=:Remark,InvoiceNumber=:InvoiceNumber where ID=:ID")
	public int modifyRegister(@Param(value = "ID") int ID, @Param(value = "Number") String Number,
			@Param(value = "InvoiceDate") String InvoiceDate, @Param(value = "InvoiceContent") String InvoiceContent,
			@Param(value = "InvoiceSum") String InvoiceSum, @Param(value = "InvoiceNum") String InvoiceNum,
			@Param(value = "InvoiceUnit") String InvoiceUnit, @Param(value = "Status") String Status,
			@Param(value = "RegisterMan") String RegisterMan, @Param(value = "Remark") String Remark,
			@Param(value = "InvoiceNumber") String invoiceNumber,
			@Param(value = "WineSum") String WineSum,
			@Param(value = "EnterSum") String EnterSum,
			@Param(value = "PersonSum") String PersonSum);

	// 通过事前审批单号获得对应事后内容
	@Query(nativeQuery = true, value = "select * from B_EntertainRegisterInfo where Number=:Number and Status='通过' order by PaidTime desc")
	public List<EntertainRegisterInfoEntity>  getSearchRegisterInfo(@Param(value = "Number") String Number);

	// 事后-获取个人申请信息 √
	@Query(nativeQuery = true, value = "select * from B_EntertainRegisterInfo where Status NOT IN ('已完成' ,'通过') and  RegisterMan=:RegisterMan  order by ID desc")
	List<EntertainRegisterInfoEntity> getPersonalRegister(@Param(value = "RegisterMan") String RegisterMan);
	
	//根据ID获得事后表的审批单号（Number）
	@Query(nativeQuery = true, value = "select Number from B_EntertainRegisterInfo where ID=:ID")
	String getNumber(@Param(value = "ID") String ID);

	// web-根据审批单号查询相关信息
	@Query(nativeQuery = true, value = "select * from B_EntertainRegisterInfo where Number=:Number")
	List<EntertainRegisterInfoEntity> registerDetail(@Param(value = "Number")String Number);

	// 事后-录入凭证号,通过审核（刘静） √
	@Modifying
	@Query(nativeQuery = true, value = "update B_EntertainRegisterInfo set VoucherNum=?3, PaidTime=?2, Status='通过' where ID=?1")
	int insertVoucherNum(String iD, String paidTime, String voucherNum);

	// web-首页-未审核招待明细-分页显示-获取列表
	@Query(nativeQuery = true, value = "select * from B_EntertainRegisterInfo where Status='待审核' limit ?1,?2 ")
	List<EntertainRegisterInfoEntity> wGetAllUnapprovedRegister(int start, int number);

	// web-首页-未审核招待明细-分页显示-获得总条数
	@Query(nativeQuery = true, value = "select count(*) from B_EntertainRegisterInfo where Status='待审核'")
	int getAllUnapprovedRegisterCount();

	// 事后-删除登记信息
	@Modifying
	@Query(nativeQuery = true, value = "delete from B_EntertainRegisterInfo where ID=:ID")
	int deleteRegister(@Param(value = "ID")String ID);

	// 事后-根据Number改变事后表状态
	@Modifying
	@Query(nativeQuery = true, value = "update B_EntertainRegisterInfo set Status=:Status where Number=:Number")
	int updateStatusByN(@Param(value = "Status")String status, @Param(value = "Number")String number);

	// 事后-录入凭证号、报销时间，审核通过-根据Number（权限→刘静）
	@Modifying
	@Query(nativeQuery = true, value = "update B_EntertainRegisterInfo set VoucherNum=?3, PaidTime=?2, Status='通过' where Number=?1")
	int insertVoucherNumByN(String number, String paidTime, String voucherNum);
	
	// 通过事前审批单号获得对应待审核事后内容
	@Query(nativeQuery = true, value = "select * from B_EntertainRegisterInfo where Number=?1 and Status='待审核' order by PaidTime desc")
	List<EntertainRegisterInfoEntity> getReadyRegisterInfo(String number);

	@Query(nativeQuery = true, value = "select * from B_EntertainRegisterInfo where Number=?1")
	public List<EntertainRegisterInfoEntity> get(String number);

}
