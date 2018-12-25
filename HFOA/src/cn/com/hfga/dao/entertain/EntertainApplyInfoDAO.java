package cn.com.hfga.dao.entertain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cn.com.hfga.dao.common.SpringDataDAO;
import cn.com.hfga.dto.entertain.EntertainSearchInfoDTO;
import cn.com.hfga.dto.entertain.EntertainSumInfoDTO;
import cn.com.hfga.entity.entertain.EntertainApplyInfoEntity;

@Repository("entertainApplyInfoDAO")
public interface EntertainApplyInfoDAO extends SpringDataDAO<EntertainApplyInfoEntity> {

	// 保存申请 √
	@Modifying
	@Query(nativeQuery = true, value = "insert into B_EntertainApplyInfo(Number,Department,ApplyTime,EntertainObject,EntertainReason,EntertainNum,AccompanyNum,PerBudget,MasterBudget,RemainingBudget,EntertainCategory,Manager,Approver,Status,Remark,IfWine,WineType,WineNum,WineOther,IfBefore,BeforeDate)  VALUES(:Number,:Department,:ApplyTime,:EntertainObject,:EntertainReason,:EntertainNum,:AccompanyNum,:PerBudget,:MasterBudget,:MasterBudget,:EntertainCategory,:Manager,:Approver,:Status,:Remark,:IfWine,:WineType,:WineNum,:WineOther,:IfBefore,:BeforeDate)")
	public int insert(@Param(value = "Number") String Number, @Param(value = "Department") String Department,
			@Param(value = "ApplyTime") String ApplyTime, @Param(value = "EntertainObject") String EntertainObject,
			@Param(value = "EntertainReason") String EntertainReason,
			@Param(value = "EntertainNum") String EntertainNum, @Param(value = "AccompanyNum") String AccompanyNum,
			@Param(value = "PerBudget") String PerBudget, @Param(value = "MasterBudget") String MasterBudget,
			@Param(value = "EntertainCategory") String EntertainCategory, @Param(value = "Manager") String Manager,
			@Param(value = "Approver") String Approver, @Param(value = "Status") String Status,
			@Param(value = "Remark") String Remark,
			@Param(value = "IfWine") String IfWine,
			@Param(value = "WineType") String WineType,
			@Param(value = "WineNum") String WineNum,
			@Param(value = "WineOther") String WineOther,@Param(value = "IfBefore") String IfBefore,
			@Param(value = "BeforeDate") String BeforeDate);

	// 获取数据库中最后一条数据的审批单号 √
	@Query(nativeQuery = true, value = "select Number from B_EntertainApplyInfo where ID = (select max(ID) from B_EntertainApplyInfo)")
	public String getSqlLast();

	
	
	// 删除申请 √
	@Modifying
	@Query(nativeQuery = true, value = "delete from B_EntertainApplyInfo where ID=:ID")
	public int delete(@Param(value = "ID") String ID);

	// 获取所有的申请信息 √
	@Query(nativeQuery = true, value = "select * from B_EntertainApplyInfo order by ApplyTime desc")
	public List<EntertainApplyInfoEntity> getAll();
	
	// 获取所有的申请信息 √
	@Query(nativeQuery = true, value = "select * from B_EntertainApplyInfo where Status='通过' and RegisterNum='0' order by ApplyTime desc limit ?1,?2")
	public List<EntertainApplyInfoEntity> getUnSubRecord(int start,int number);
	
	// 获取所有的申请信息count √
	@Query(nativeQuery = true, value = "select count(*) from B_EntertainApplyInfo where Status='通过' and RegisterNum='0' order by ApplyTime desc ")
	public int getUnSubRecordCount();

	// 获取所有部门下的对应信息
	@Query(nativeQuery = true, value = "select * from B_EntertainApplyInfo,B_EntertainRegisterInfo where B_EntertainApplyInfo.Number = B_EntertainRegisterInfo.Number and InvoiceUnit like:invoiceunit and (ApplyTime<=:endTime and ApplyTime>=:startTime) and Manager like :manager  and B_EntertainRegisterInfo.Status='已完成'  order by ApplyTime desc")
	public List<EntertainApplyInfoEntity> getSearchInfo1(@Param(value = "invoiceunit") String invoiceunit,
			@Param(value = "startTime") String startTime, @Param(value = "endTime") String endTime,
			@Param(value = "manager") String manager);

	// 获取某一部门下的对应信息
	@Query(nativeQuery = true, value = "select * from B_EntertainApplyInfo,B_EntertainRegisterInfo where B_EntertainApplyInfo.Number = B_EntertainRegisterInfo.Number and Department=:Department and InvoiceUnit like:invoiceunit and (ApplyTime<=:endTime and ApplyTime>=:startTime) and Manager like :manager and B_EntertainRegisterInfo.Status='已完成' order by ApplyTime desc")
	public List<EntertainApplyInfoEntity> getSearchInfo0(@Param(value = "Department") String Department,
			@Param(value = "invoiceunit") String invoiceunit, @Param(value = "startTime") String startTime,
			@Param(value = "endTime") String endTime, @Param(value = "manager") String manager);
	
	// 获取状态为通过的事前信息
	@Query(nativeQuery = true, value = "select * from B_EntertainApplyInfo where Department like :department and ApplyTime<=:endTime and ApplyTime>=:startTime  and Manager like :manager and EntertainObject like :entertainObject and Status='已完成' and RegisterNum !=0 order by ApplyTime desc")
	 public List<EntertainApplyInfoEntity> getApplyList(@Param(value = "department") String Department, 
			@Param(value = "startTime") String startTime, @Param(value = "endTime") String endTime,
			@Param(value = "manager") String manager,@Param(value = "entertainObject") String entertainObject);
	
	// 获取一条部门下的对应信息
	@Query(nativeQuery = true, value = "select * from B_EntertainApplyInfo,B_EntertainRegisterInfo where B_EntertainApplyInfo.Number = B_EntertainRegisterInfo.Number and entertainObject like:entertainObject and (ApplyTime<=:endTime and ApplyTime>=:startTime) and Manager like :manager  and B_EntertainRegisterInfo.Status='已完成'  order by ApplyTime desc")
	public EntertainApplyInfoEntity getSearchInfo11(@Param(value = "entertainObject") String entertainObject,
			@Param(value = "startTime") String startTime, @Param(value = "endTime") String endTime,
			@Param(value = "manager") String manager);

	// 获取一条某一部门下的对应信息
	@Query(nativeQuery = true, value = "select * from B_EntertainApplyInfo,B_EntertainRegisterInfo where B_EntertainApplyInfo.Number = B_EntertainRegisterInfo.Number and Department=:Department and entertainObject like:entertainObject and (ApplyTime<=:endTime and ApplyTime>=:startTime) and Manager like :manager and B_EntertainRegisterInfo.Status='已完成' order by ApplyTime desc")
	public EntertainApplyInfoEntity getSearchInfo00(@Param(value = "Department") String Department,
			@Param(value = "entertainObject") String entertainObject, @Param(value = "startTime") String startTime,
			@Param(value = "endTime") String endTime, @Param(value = "manager") String manager);
	
	// 获取需要审批的信息（根据不同审批人） √
	@Query(nativeQuery = true, value = "select * from B_EntertainApplyInfo where Approver=:Approver and Status='待审批' order by ID desc")
	public List<EntertainApplyInfoEntity> getByApprove(@Param(value = "Approver") String Approver);

	// 审批 √
	@Modifying
	@Query(nativeQuery = true, value = "update B_EntertainApplyInfo set Status=:Status,Approver=:Approver,ApproveTime=:ApproveTime where Number=:Number ")
	public int updateApprove(@Param(value = "Status") String status, @Param(value = "Approver") String approver,
			@Param(value = "Number") String number, @Param(value = "ApproveTime") String approveTime);// 状态，审批人，审批单号
	
	@Modifying
	@Query(nativeQuery = true, value = "update B_EntertainApplyInfo set Status=:Status where Number=:Number ")
	public int updateStatus2(@Param(value = "Status") String status, @Param(value = "Number") String number);// 状态，审批人，审批单号
	
	// 获取个人申请信息 √
	@Query(nativeQuery = true, value = "select * from B_EntertainApplyInfo where Manager=:Manager and Status NOT IN ('已完成') order by ApplyTime desc")
	public List<EntertainApplyInfoEntity> getPersonal(@Param(value = "Manager") String Manager);

	// 更改招待申请  √
	@Modifying
	@Query(nativeQuery = true, value = "update B_EntertainApplyInfo set IfBefore=:IfBefore,BeforeDate=:BeforeDate, IfWine=:IfWine,WineType=:WineType,WineNum=:WineNum,WineOther=:WineOther,Number=:Number,Department=:Department,ApplyTime=:ApplyTime,EntertainObject=:EntertainObject,EntertainReason=:EntertainReason,EntertainNum=:EntertainNum,AccompanyNum=:AccompanyNum,PerBudget=:PerBudget,MasterBudget=:MasterBudget,RemainingBudget=:MasterBudget,EntertainCategory=:EntertainCategory,Manager=:Manager,Approver=:Approver,Status=:Status,Remark=:Remark where ID=:ID")
	public int modifyOne(@Param(value = "ID") int ID, @Param(value = "Number") String Number,
			@Param(value = "Department") String Department, @Param(value = "ApplyTime") String ApplyTime, 
			@Param(value = "EntertainObject") String EntertainObject, @Param(value = "EntertainReason") String EntertainReason,
			@Param(value = "EntertainNum") String EntertainNum, @Param(value = "AccompanyNum") String AccompanyNum,
			@Param(value = "PerBudget") String PerBudget, @Param(value = "MasterBudget") String MasterBudget,
			@Param(value = "EntertainCategory") String EntertainCategory, @Param(value = "Manager") String Manager,
			@Param(value = "Approver") String Approver, @Param(value = "Status") String Status,
			@Param(value = "Remark") String Remark,
			@Param(value = "IfWine") String IfWine,
			@Param(value = "WineType") String WineType,
			@Param(value = "WineNum") String WineNum,
			@Param(value = "WineOther") String WineOther,@Param(value = "IfBefore") String IfBefore,
			@Param(value = "BeforeDate") String BeforeDate);

	// 事前-更改Status √
	@Modifying
	@Query(nativeQuery = true, value = "update B_EntertainApplyInfo set Status=:Status where ID=:ID")
	public int updateStatus(@Param(value = "Status")String Status, @Param(value = "ID") String ID);

	// 事前-RegisterNum+1
	@Modifying
	@Query(nativeQuery = true, value = "update B_EntertainApplyInfo set RegisterNum=RegisterNum +1 where Number=:Number")
	public int plus(@Param(value = "Number") String Number);

	
	// Web-首页-已审核招待明细-显示
	@Query(nativeQuery = true, value = "select DISTINCT(app.ID),app.*,re.InvoiceNumber from B_EntertainApplyInfo as app LEFT JOIN b_entertainregisterinfo as re on app.Number = re.Number where app.Status='已完成' and app.RegisterNum!=0 order by ApplyTime desc")
	public List<EntertainApplyInfoEntity> wGetApplyApproved();

	//取出审批单号对应的事前表的RegisterNum值
	@Query(nativeQuery = true, value = "select RegisterNum from B_EntertainApplyInfo where Number=:Number")
	public int getRegisterNum(@Param(value = "Number")String Number);

	//获得需要修改状态事前表的ID
	@Query(nativeQuery = true, value = "select ID from B_EntertainApplyInfo where Number=:Number")
	public String getID(@Param(value = "Number")String Number);
	
	//取出已完成的事前list（分页）
	@Query(nativeQuery = true, value = "select * from B_EntertainApplyInfo where Status='已完成' order by ApproveTime desc limit ?1,?2")
	List<EntertainApplyInfoEntity> getApplyCompleted(int start, int number);

	//取出已完成的事前list
	@Query(nativeQuery = true, value = "select * from B_EntertainApplyInfo t1 LEFT JOIN b_entertainregisterinfo t2 ON t1.Number = t2.Number where t1.Status='已完成'  AND t2.Status = '通过'")
	List<EntertainApplyInfoEntity> getApplyCompleted();
	
	//获得已完成的招待明细总数
	@Query(nativeQuery = true, value = "select count(*) from B_EntertainApplyInfo where Status='已完成'")
	public int getAllCompletedCount();

	// web-根据审批单号查询相关信息
	@Query(nativeQuery = true, value = "select * from B_EntertainApplyInfo where Number=:Number")
	public EntertainApplyInfoEntity applyDetail(@Param(value = "Number")String Number);

	// web-已审核招待明细-查询
	@Query(nativeQuery = true, value = "select * from B_EntertainApplyInfo where Number=:Number")
	public List<EntertainApplyInfoEntity> searchApply(int start, int number, String department, String manager,
			String startTime, String endTime, String entertainObject);

	// web-已审核招待明细-查询-获得符合查询条件的条目数
	@Query(nativeQuery = true, value = "select count(*) from B_EntertainApplyInfo where Number=:Number")
	public int getAllCompletedApplyCount(EntertainSearchInfoDTO entertainSearchInfoDTO);

	// web-根据Number恢复事前表的状态
	@Modifying
	@Query(nativeQuery = true, value = "update B_EntertainApplyInfo set Status='通过' where Number=:Number")
	public void updateStatusByN(@Param(value = "Number")String number);

	// web-根据Number恢复事前表的状态
	@Modifying
	@Query(nativeQuery = true, value = "update B_EntertainApplyInfo set Status='已完成' where Number=:Number")
	public void updateStatusByN1(@Param(value = "Number")String number);
	
	@Modifying
	@Query(nativeQuery = true, value = "update B_EntertainApplyInfo set Status='已完成', ApproveTime=:ApproveTime where Number=:Number")
	public void updateStatusByN2(@Param(value = "Number")String number,@Param(value = "ApproveTime")String approveTime);
	
	// 事前-RegisterNum- 1
	@Modifying
	@Query(nativeQuery = true, value = "update B_EntertainApplyInfo set RegisterNum=RegisterNum -1 where Number=:Number")
	public int minus(@Param(value = "Number") String Number);
	
	    // web-已审核招待明细-查询-获取列表信息11
		@Query(nativeQuery = true, value = "select * from B_EntertainApplyInfo where Manager like ?1 and (DATE_FORMAT(PaidTime,'%Y-%m-%d')<=?3 and DATE_FORMAT(PaidTime,'%Y-%m-%d')>=?2) and Status='已完成'")
		public List<EntertainApplyInfoEntity> wGetSearchApproved11(String manager, String startTime,String endTime);
		
		// web-已审核招待明细-查询-获取列表信息01
		@Query(nativeQuery = true, value = "select * from B_EntertainApplyInfo where Department=?1 and Manager like ?2 and (DATE_FORMAT(PaidTime,'%Y-%m-%d')<=?4 and DATE_FORMAT(PaidTime,'%Y-%m-%d')>=?3) and Status='已完成'")
		public List<EntertainApplyInfoEntity> wGetSearchApproved01(String department, String manager,
				String startTime, String endTime);
		
		// web-已审核招待明细-查询-获取列表信息10
		@Query(nativeQuery = true, value = "select * from B_EntertainApplyInfo where Manager like ?1 and (DATE_FORMAT(PaidTime,'%Y-%m-%d')<=?3 and DATE_FORMAT(PaidTime,'%Y-%m-%d')>=?2) and EntertainObject like ?4 and Status='已完成'")
		public List<EntertainApplyInfoEntity> wGetSearchApproved10(String manager, String startTime,
				String endTime, String entertainObject);
		
		// web-已审核招待明细-查询-获取列表信息00
		@Query(nativeQuery = true, value = "select * from B_EntertainApplyInfo where Department=?1  and Manager like ?2 and (DATE_FORMAT(PaidTime,'%Y-%m-%d')<=?4 and DATE_FORMAT(PaidTime,'%Y-%m-%d')>=?3) and EntertainObject like ?5 and Status='已完成'")
		public List<EntertainApplyInfoEntity> wGetSearchApproved00(String department, String manager,
				String startTime, String endTime, String entertainObject);
		
		//------------------------
		 // web-已审核招待明细-查询-获取列表信息11
		@Query(nativeQuery = true, value = "select * from B_EntertainApplyInfo where Manager like ?1 and (DATE_FORMAT(ApproveTime,'%Y-%m-%d')<=?3 and DATE_FORMAT(ApproveTime,'%Y-%m-%d')>=?2) and Status='已完成' order by ApproveTime desc limit ?4,?5")
		public List<EntertainApplyInfoEntity> wGetSearchApproved11ByPage(String manager, String startTime,String endTime,int start,int end);
		
		// web-已审核招待明细-查询-获取列表信息01
		@Query(nativeQuery = true, value = "select * from B_EntertainApplyInfo where Department=?1 and Manager like ?2 and (DATE_FORMAT(ApproveTime,'%Y-%m-%d')<=?4 and DATE_FORMAT(ApproveTime,'%Y-%m-%d')>=?3) and Status='已完成' order by ApproveTime desc limit ?5,?6")
		public List<EntertainApplyInfoEntity> wGetSearchApproved01ByPage(String department, String manager,
				String startTime, String endTime,int start,int end);
		
		// web-已审核招待明细-查询-获取列表信息10
		@Query(nativeQuery = true, value = "select * from B_EntertainApplyInfo where Manager like ?1 and (DATE_FORMAT(ApproveTime,'%Y-%m-%d')<=?3 and DATE_FORMAT(ApproveTime,'%Y-%m-%d')>=?2) and EntertainObject like ?4 and Status='已完成' order by ApproveTime desc limit ?5,?6")
		public List<EntertainApplyInfoEntity> wGetSearchApproved10ByPage(String manager, String startTime,
				String endTime, String entertainObject,int start,int end);
		
		// web-已审核招待明细-查询-获取列表信息00
		@Query(nativeQuery = true, value = "select * from B_EntertainApplyInfo where Department=?1  and Manager like ?2 and (DATE_FORMAT(ApproveTime,'%Y-%m-%d')<=?4 and DATE_FORMAT(ApproveTime,'%Y-%m-%d')>=?3) and EntertainObject like ?5 and Status='已完成' order by ApproveTime desc limit ?6,?7")
		public List<EntertainApplyInfoEntity> wGetSearchApproved00ByPage(String department, String manager,
				String startTime, String endTime, String entertainObject,int start,int end);
		//------------------------
		// web-已审核招待明细-查询-获取列表信息11
		@Query(nativeQuery = true, value = "select count(*) from B_EntertainApplyInfo where Manager like ?1 and (DATE_FORMAT(ApproveTime,'%Y-%m-%d')<=?3 and DATE_FORMAT(ApproveTime,'%Y-%m-%d')>=?2) and Status='已完成'")
		public int wGetSearchApproved11Count(String manager, String startTime,String endTime);
		
		// web-已审核招待明细-查询-获取列表信息01
		@Query(nativeQuery = true, value = "select count(*) from B_EntertainApplyInfo where Department=?1 and Manager like ?2 and (DATE_FORMAT(ApproveTime,'%Y-%m-%d')<=?4 and DATE_FORMAT(ApproveTime,'%Y-%m-%d')>=?3) and Status='已完成'")
		public int wGetSearchApproved01Count(String department, String manager,
				String startTime, String endTime);
		
		// web-已审核招待明细-查询-获取列表信息10
		@Query(nativeQuery = true, value = "select count(*) from B_EntertainApplyInfo where Manager like ?1 and (DATE_FORMAT(ApproveTime,'%Y-%m-%d')<=?3 and DATE_FORMAT(ApproveTime,'%Y-%m-%d')>=?2) and EntertainObject like ?4 and Status='已完成'")
		public int wGetSearchApproved10Count(String manager, String startTime,
				String endTime, String entertainObject);
		
		// web-已审核招待明细-查询-获取列表信息00
		@Query(nativeQuery = true, value = "select count(*) from B_EntertainApplyInfo where Department=?1  and Manager like ?2 and (DATE_FORMAT(ApproveTime,'%Y-%m-%d')<=?4 and DATE_FORMAT(ApproveTime,'%Y-%m-%d')>=?3) and EntertainObject like ?5 and Status='已完成'")
		public int wGetSearchApproved00Count(String department, String manager,
				String startTime, String endTime, String entertainObject);
		//---------------------------------

	// web-已审核招待明细-查询-获取总数11
	@Query(nativeQuery = true, value = "select count(*) from B_EntertainApplyInfo where Manager like ?1 and (DATE_FORMAT(ApproveTime,'%Y-%m-%d')<=?3 and DATE_FORMAT(ApproveTime,'%Y-%m-%d')>=?2) and EntertainObject like ?4")
	public int getAllSearchCount11(String manager, String startTime,String endTime, String entertainObject);
	
	// web-已审核招待明细-查询-获取总数01
	@Query(nativeQuery = true, value = "select count(*) from B_EntertainApplyInfo where Department=?1 and Manager like ?2 and (DATE_FORMAT(ApproveTime,'%Y-%m-%d')<=?4 and DATE_FORMAT(ApproveTime,'%Y-%m-%d')>=?3)")
	public int getAllSearchCount01(String department, String manager,String startTime, String endTime);
	
	// web-已审核招待明细-查询-获取总数10
	@Query(nativeQuery = true, value = "select count(*) from B_EntertainApplyInfo where Manager like ?1 and (DATE_FORMAT(ApproveTime,'%Y-%m-%d')<=?3 and DATE_FORMAT(ApproveTime,'%Y-%m-%d')>=?2) and EntertainObject like ?4 ")
	public int getAllSearchCount10(String manager, String startTime,String endTime, String entertainObject);
	
	// web-已审核招待明细-查询-获取总数00
	@Query(nativeQuery = true, value = "select count(*) from B_EntertainApplyInfo where Department=?1  and Manager like ?2 and (DATE_FORMAT(ApproveTime,'%Y-%m-%d')<=?4 and DATE_FORMAT(ApproveTime,'%Y-%m-%d')>=?3) and EntertainObject like ?5")
	public int getAllSearchCount00(String department, String manager,String startTime, String endTime, String entertainObject);

	// 已审核招待明细-查询-获取列表信息11
	@Query(nativeQuery = true, value = "select * from B_EntertainApplyInfo where Manager like ?1 and (DATE_FORMAT(ApproveTime,'%Y-%m-%d')<=?3 and DATE_FORMAT(ApproveTime,'%Y-%m-%d')>=?2) and Status='已完成'")
	public List<EntertainApplyInfoEntity> getSearchApproved11(String manager, String startTime, String endTime);

	// 已审核招待明细-查询-获取列表信息01
	@Query(nativeQuery = true, value = "select * from B_EntertainApplyInfo where Department=?1 and Manager like ?2 and (DATE_FORMAT(ApproveTime,'%Y-%m-%d')<=?4 and DATE_FORMAT(ApproveTime,'%Y-%m-%d')>=?3) and Status='已完成'")
	public List<EntertainApplyInfoEntity> getSearchApproved01(String department, String manager, String startTime,String endTime);
	
	// web-已审核招待明细-查询-获取列表信息10
	@Query(nativeQuery = true, value = "select * from B_EntertainApplyInfo where Manager like ?1 and (DATE_FORMAT(ApproveTime,'%Y-%m-%d')<=?3 and DATE_FORMAT(ApproveTime,'%Y-%m-%d')>=?2) and EntertainObject like ?4 and Status='已完成'")
	public List<EntertainApplyInfoEntity> getSearchApproved10(String manager, String startTime, String endTime,String entertainObject);

	// web-已审核招待明细-查询-获取列表信息00
	@Query(nativeQuery = true, value = "select * from B_EntertainApplyInfo where Department=?1  and Manager like ?2 and (DATE_FORMAT(ApproveTime,'%Y-%m-%d')<=?4 and DATE_FORMAT(ApproveTime,'%Y-%m-%d')>=?3) and EntertainObject like ?5 and Status='已完成'")
	public List<EntertainApplyInfoEntity> getSearchApproved00(String department, String manager, String startTime,String endTime, String entertainObject);

	// 事前-获取个人通过的事前信息
	@Query(nativeQuery = true, value = "select * from B_EntertainApplyInfo where Status='通过' and Manager=?1 order by ID desc")
	public List<EntertainApplyInfoEntity> getPassApply(String manager);

	// 将报销时间写入事前数据库
	@Modifying
	@Query(nativeQuery = true, value = "update B_EntertainApplyInfo set PaidTime=?2 where Number=?1")
	public void setPaidTime(String number,String paidTime);

	@Query(nativeQuery = true, value = "select DISTINCT(sq.ID),sq.* from B_EntertainApplyInfo as sq LEFT JOIN b_entertainregisterinfo as sh on sq.Number=sh.Number where sq.Status='通过' and sq.RegisterNum!=0 and sh.Status='待审核' order by sq.ApproveTime desc limit ?1,?2")
	public List<EntertainApplyInfoEntity> getUncompletedApply(int start, int number);
	
	@Query(nativeQuery = true, value = "select sq.* from B_EntertainApplyInfo as sq left join b_entertainregisterinfo as re on re.Number = sq.Number where re.Status='未通过' order by sq.ApproveTime desc limit ?1,?2")
	public List<EntertainApplyInfoEntity> getAllEntertain(int start, int number);
	
	@Query(nativeQuery = true, value = "select count(*) from B_EntertainApplyInfo as sq left join b_entertainregisterinfo as re on re.Number = sq.Number where re.Status='未通过' ")
	public int getAllEntertainCount();

	@Query(nativeQuery = true, value = "select count(DISTINCT(sq.ID)) from B_EntertainApplyInfo as sq LEFT JOIN b_entertainregisterinfo as sh on sq.Number=sh.Number where sq.Status='通过' and sq.RegisterNum!=0 and sh.Status='待审核'")
	public int getAllRCompletedCount();

	// 事前-获取所有通过的事前信息
	@Query(nativeQuery = true, value = "select * from B_EntertainApplyInfo where Status='通过' and RegisterNum!=0 order by ApplyTime desc")
	public List<EntertainApplyInfoEntity> getAllPassApply();

	// 获得事前审批表中的内容
	@Query(nativeQuery = true, value = "select count(*) from B_EntertainApplyInfo")
	public int getApplyObject();

	//根据Number获取数据库中的剩余预算值
	@Query(nativeQuery = true, value = "select RemainingBudget from B_EntertainApplyInfo where Number=?1")
	public String getRemainingBudget(String number);

	//根据修改更新剩余预算
	//TODO zy：修改参数类型为double，需要判断是否有影响 
	@Modifying
	@Query(nativeQuery = true, value = "update B_EntertainApplyInfo set RemainingBudget=?2 where Number=?1")
	public void updateRemainingBudget(String number, double newRemainingBudget);
	
	
/*	// 获取状态为已完成的信息
	@Query(nativeQuery = true, value = "select * from B_EntertainApplyInfo where Status='已完成' order by ApplyTime desc")
	public List<EntertainApplyInfoEntity> getApplyList(EntertainSearchInfoDTO entertainSearchInfoDTO);
*/
	@Query(nativeQuery = true, value = "select t1.ID, t1.Number, Department, InvoiceSum from b_entertainapplyinfo t1,b_entertainregisterinfo t2 where t1.Number=t2.Number and t2.Status='通过' and t2.PaidTime like ?1 ORDER BY t1.Department")
	public List<Object[]> getEntertainSum(String year);

	
	// 获得某一部门本年度已使用经费信息
	@Query(nativeQuery = true, value = "select t1.ID, t1.Number, Department, InvoiceSum from b_entertainapplyinfo t1,b_entertainregisterinfo t2 where t1.Number=t2.Number and InvoiceDate like ?2 and t1.Department=?1")
	public List<Object[]> getOneDepartmentEntertainSum(String department, String year);
	
	@Query(nativeQuery = true, value = "select * from B_EntertainApplyInfo where Number=?1")
	public List<EntertainApplyInfoEntity> get(String number);

	// 事前-审批否决 √
	@Modifying
	@Query(nativeQuery = true, value = "update B_EntertainApplyInfo set Status=?1 , ApproveTime=?3 where ID=?2")
	public int updateStatus(String string, String applyId, String time);
	
}
