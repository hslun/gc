package cn.com.hfga.dao.privatecar;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cn.com.hfga.dao.common.SpringDataDAO;
import cn.com.hfga.dto.privatecar.PrivateCarApplyDTO;
import cn.com.hfga.dto.privatecar.PrivateCarSearchDTO;
import cn.com.hfga.entity.privatecar.PrivateCarApplyEntity;

@Repository("privateCarApplyDAO")
public interface PrivateCarApplyDAO extends SpringDataDAO<PrivateCarApplyEntity>{
	
	//保存
	@Modifying
	@Query(nativeQuery=true,value="insert into b_privatecar ("
			+ "ApplyId,Department,"
			+ "ApplyMan,UserCarTime,"
			+ "Reason,BeginAddress,PassAddress,"
			+ "Status,Destination,"
			+ "SingleLength,SureLength,CountLength,ApproveMan,ApplyTime,WayModel,WayDetail,DoubleLength,EndLength,IfPass,IfBefore,BeforeDate)"
			+ "VALUES(:ApplyId,:Department,:ApplyMan,:UserCarTime,:Reason,"
			+ ":BeginAddress,:PassAddress,:Status,"
			+ ":Destination,:SingleLength,"
			+ ":SureLength,:CountLength,:ApproveMan,:ApplyTime,:WayModel,:WayDetail,:DoubleLength,:EndLength,:IfPass,:IfBefore,:BeforeDate)")
	public int insertEntity(@Param(value="ApplyId")String ApplyId,
			@Param(value="Department")String Department,
			@Param(value="ApplyMan")String ApplyMan,
			@Param(value="UserCarTime")String UserCarTime,
			@Param(value="Reason")String Reason,
			@Param(value="BeginAddress")String BeginAddress,
			@Param(value="PassAddress")String PassAddress,
			@Param(value="Status")String Status,
			@Param(value="Destination")String Destination,
			@Param(value="SingleLength")Double SingleLength,
			@Param(value="SureLength")Double SureLength,
			@Param(value="CountLength")Double CountLength,
			@Param(value="ApproveMan")String ApproveMan,
			@Param(value="ApplyTime")String ApplyTime,
			@Param(value="WayModel")String WayModel,
			@Param(value="WayDetail")String WayDetail,
			@Param(value="DoubleLength")String DoubleLength,
			@Param(value="EndLength")String EndLength,
			@Param(value="IfPass")String IfPass,
			@Param(value="IfBefore")String IfBefore,
			@Param(value="BeforeDate")String BeforeDate
	);
	
	//保存
	@Modifying
	@Query(nativeQuery=true,value="insert into b_privatecar ("
			+ "ApplyId,Department,"
			+ "ApplyMan,UserCarTime,"
			+ "Reason,BeginAddress,PassAddress,"
			+ "Status,Destination,"
			+ "SingleLength,SureLength,CountLength,ApproveMan,ApplyTime,ApproveTime,WayModel,WayDetail)"
			+ "VALUES(:ApplyId,:Department,:ApplyMan,:UserCarTime,:Reason,"
			+ ":BeginAddress,:PassAddress,:Status,"
			+ ":Destination,:SingleLength,"
			+ ":SureLength,:CountLength,:ApproveMan,:ApplyTime,:ApproveTime,:WayModel,:WayDetail)")
	public int insertEntity1(@Param(value="ApplyId")String ApplyId,
			@Param(value="Department")String Department,
			@Param(value="ApplyMan")String ApplyMan,
			@Param(value="UserCarTime")String UserCarTime,
			@Param(value="Reason")String Reason,
			@Param(value="BeginAddress")String BeginAddress,
			@Param(value="PassAddress")String PassAddress,
			@Param(value="Status")String Status,
			@Param(value="Destination")String Destination,
			@Param(value="SingleLength")Double SingleLength,
			@Param(value="SureLength")Double SureLength,
			@Param(value="CountLength")Double CountLength,
			@Param(value="ApproveMan")String ApproveMan,
			@Param(value="ApplyTime")String ApplyTime,
			@Param(value="ApproveTime")String ApproveTime,
			@Param(value="WayModel")String WayModel,
			@Param(value="WayDetail")String WayDetail
	);
	
	//修改申请
	@Modifying
	@Query(nativeQuery=true,value="update b_privatecar "
			+ "set Department=:Department,"
			+ "ApplyMan=:ApplyMan,"
			+ "UserCarTime=:UserCarTime,"
			+ "Reason=:Reason,"
			+ "BeginAddress=:BeginAddress,"
			+ "PassAddress=:PassAddress,"
			+ "Destination=:Destination,"
			+ "SingleLength=:SingleLength,"
			+ "SureLength=:SureLength,"
			+ "CountLength=:CountLength,"
			+ "DoubleLength=:DoubleLength,"
			+ "EndLength=:EndLength,"
			+ "WayModel=:WayModel,"
			+ "WayDetail=:WayDetail,"
			+ "ApproveMan=:ApproveMan, "
			+ "IfBefore=:IfBefore, "
			+ "BeforeDate=:BeforeDate "
			+ "where ApplyId=:ApplyId")
	public int modifyEntity(@Param(value="ApplyId")String ApplyId,
			@Param(value="Department")String Department,
			@Param(value="ApplyMan")String ApplyMan,
			@Param(value="UserCarTime")String UserCarTime,
			@Param(value="Reason")String Reason,
			@Param(value="BeginAddress")String BeginAddress,
			@Param(value="PassAddress")String PassAddress,
			@Param(value="Destination")String Destination,
			@Param(value="SingleLength")Double SingleLength,
			@Param(value="SureLength")Double SureLength,
			@Param(value="CountLength")Double CountLength,
			@Param(value="ApproveMan")String ApproveMan,
			@Param(value="WayModel")String WayModel,
			@Param(value="WayDetail")String WayDetail,
			@Param(value="DoubleLength")String DoubleLength,
			@Param(value="EndLength")String EndLength,
			@Param(value="IfBefore")String IfBefore,
			@Param(value="BeforeDate")String BeforeDate
	);
	
	//产生申请表的Id
	@Query(nativeQuery=true,value="select max(ApplyId) as ApplyId from b_privatecar where Department=:department and ApplyId like :applyid")
	public List<Object> getMaxId(@Param(value="department") String department,@Param(value="applyid") String applyid);
	
	
	
	//审批状态修改方法
	@Modifying
	@Query(nativeQuery=true,value="update b_privatecar set Status=:status,ApproveMan=:ApproveMan,ApproveTime=:ApproveTime where ApplyId=:applyid")
	public int  Approve(@Param(value="status")String status,
			@Param(value="ApproveMan") String ApproveMan,
			@Param(value="applyid")String applyid,
			@Param(value="ApproveTime")String approveTime);
	
	
	
	//确认操作
	@Modifying
	@Query(nativeQuery=true,value="update b_privatecar set Status=:status,SureTime=:suretime where ApplyId=:applyid")
	public int  Sure(@Param(value="status")String status,@Param(value="applyid")String applyid,@Param(value="suretime")String suretime);
	
	
	//获取本部门待审批信息
	@Query(nativeQuery=true,value="select * from b_privatecar where Status=:approvestate and Department=:department and ApproveMan=:approveman order by ApplyId desc")
	public List<PrivateCarApplyEntity> getApproveInfo(@Param(value="approvestate") String approvestate,@Param(value="department") String department,@Param(value="approveman") String approveman);

	//获取本人待确认的操作
	@Query(nativeQuery=true,value="select * from b_privatecar where Status=:approvestate and Department=:department and ApproveMan=:approveman order by ApplyId desc")
	public List<PrivateCarApplyEntity> getSureInfo(@Param(value="approvestate") String approvestate,@Param(value="department") String department,@Param(value="approveman") String approveman);

	
	//部门全部  人是全部
	@Query(nativeQuery=true,value="select *from b_privatecar where  ApplyTime >:starttime and ApplyTime<:endtime  and ApproveState='通过' and  Status='已确认'")
	public  List<PrivateCarApplyEntity> getDetail11(@Param(value="starttime") String starttime,@Param(value="endtime") String endtime);
	
	//部门不是全部  人是全部
	@Query(nativeQuery=true,value="select *from b_privatecar where  ApplyTime >:starttime and ApplyTime<:endtime and Department=:department and  Status='已确认'")
	public  List<PrivateCarApplyEntity> getDetail01(@Param(value="starttime") String starttime,@Param(value="endtime") String endtime,@Param(value="department") String department);
	
	
	//部门是全部  人不是全部
	@Query(nativeQuery=true,value="select * from b_privatecar where  ApplyTime >:starttime and ApplyTime<:endtime and ApplyMan=:applyman and  Status='已确认'")
	public List<PrivateCarApplyEntity> getDetail10(@Param(value="starttime")String starttime,@Param(value="endtime") String endtime,@Param(value="applyman") String applyman);
	
	//部门不是全部  车不是全部
	@Query(nativeQuery=true,value="select * from b_privatecar where  ApplyTime >:starttime and ApplyTime<:endtime and ApplyMan=:applyman and Department=:department and  Status='已确认' ")
	public List<PrivateCarApplyEntity> getDetail00(@Param(value="starttime")String starttime,@Param(value="endtime") String endtime,@Param(value="applyman") String applyman,@Param(value="department")String department);
	
	/**
	 * 汇总分析
	 */
	//查询某个部门某辆车某个时间段
	@Query(nativeQuery=true,value="select Department,ApplyMan,sum(SingleLength)as SingleLength,count(*)as times from b_privatecar where ApplyMan=:applyman and Department=:department and ApplyTime >:starttime and ApplyTime<:endtime  and Status='已确认'group by Department,ApplyMan")
	public List<Object []> getCollectInfo1(@Param(value="applyman") String applyman,@Param(value="department")String department,@Param(value="starttime")String starttime,@Param(value="endtime") String endtime);
	
	//部门是全部   人不是全部
	@Query(nativeQuery=true,value="select Department,ApplyMan,sum(SingleLength)as SingleLength,count(*)as times from b_privatecar where ApplyMan=:applyman  and ApplyTime >:starttime and ApplyTime<:endtime and Status='已确认' group by ApplyMan,Department")
	public List<Object []> getCollectInfo2(@Param(value="applyman") String applyman,@Param(value="starttime")String starttime,@Param(value="endtime") String endtime);
	
	//部门是个别  人是全部
	@Query(nativeQuery=true,value="select Department,ApplyMan,sum(SingleLength)as SingleLength,count(*)as times from b_privatecar where Department=:department  and ApplyTime >:starttime and ApplyTime<:endtime and Status='已确认' group by Department,ApplyMan")
	public List<Object []> getCollectInfo3(@Param(value="department") String department,@Param(value="starttime")String starttime,@Param(value="endtime") String endtime);
	
	//部门是全部 人是全部
	@Query(nativeQuery=true,value="select Department,ApplyMan,sum(SingleLength)as SingleLength,count(*) as times from b_privatecar where  ApplyTime >:starttime and ApplyTime<:endtime and Status='已确认'  group by Department,ApplyMan")
	public List<Object []> getCollectInfo4(@Param(value="starttime")String starttime,@Param(value="endtime") String endtime);
		
	//本人获取申请信息
	@Query(nativeQuery=true,value="select * from b_privatecar where Status!=:status and ApplyMan=:applyman")
	public List<PrivateCarApplyEntity> getPersonal(@Param(value="status")String status,@Param(value="applyman") String applyman);


	
	//获取一条信息
	@Query(nativeQuery=true,value="select * from b_privatecar where ApplyId=:applyid")
	public PrivateCarApplyEntity  getOne(@Param(value="applyid")String applyid);
	
	//获取状态为待报销的信息
	@Query(nativeQuery=true,value="select * from b_privatecar where IfPass='待报销' and ApplyId=:applyid")
	public PrivateCarApplyEntity  getOneWaitForPass(@Param(value="applyid")String applyid);
	
	//获取状态为未提交到财务new
	@Query(nativeQuery=true,value="select * from b_privatecar where IfSub='0' and ApplyId=:applyid")
	public PrivateCarApplyEntity  getOneWaitForPassNew(@Param(value="applyid")String applyid);
	
	//获取状态为待报销的信息
	@Query(nativeQuery=true,value="select * from b_privatecar where IfPass='已报销' and ApplyId=:applyid")
	public PrivateCarApplyEntity  getOnePass(@Param(value="applyid")String applyid);
	
	//获取状态为已提交到财务new
	@Query(nativeQuery=true,value="select * from b_privatecar where IfSub='1' and ApplyId=:applyid")
	public PrivateCarApplyEntity  getOnePassNew(@Param(value="applyid")String applyid);
	
	//获取状态为已提交到财务new
	@Query(nativeQuery=true,value="select * from b_privatecar where ApplyId=:applyid")
	public PrivateCarApplyEntity  getOnePassNew1(@Param(value="applyid")String applyid);
	
	//获取状态为已提交到财务new
	@Query(nativeQuery=true,value="select * from b_privatecar where ApplyId=:applyid and (IfPass='已通过' or IfPass='未报销' or IfPass='待审核 ')")
	public PrivateCarApplyEntity  getOnePassNew11(@Param(value="applyid")String applyid);

	//个人获取未通过列表
	@Query(nativeQuery=true,value="select * from b_privatecar where Status='已通过' and IfPerform='待执行' and ApplyMan=?1 order by ApplyId desc")
	public List<PrivateCarApplyEntity> getPersonalReady(String applyman);

	//设置执行状态
	@Modifying
	@Query(nativeQuery=true,value="update b_privatecar set IfSub ='0', IfPerform=?1 where ApplyId=?2")
	public int setIfPerform(String ifPerform, String applyid);

	//本人主动撤销申请
	@Modifying
	@Query(nativeQuery=true,value="delete from b_privatecar where ApplyId=:applyid")
	public int delPersonal(@Param(value="applyid")String applyid);

	//获取个人已通过列表
	@Query(nativeQuery=true,value="select * from b_privatecar where Status='已通过' and ApplyMan=?1 order by ApplyId desc")
	public List<PrivateCarApplyEntity> getPersonalApprove(String applyman);

	//根据部门、姓名、开始时间、结束时间查询(部门!=全部)
	@Query(nativeQuery=true,value="select * from b_privatecar where Department=?1 and Status='已通过' and IfPerform!='待执行' and IfPass='已报销' and ApplyMan like ?2 and (DATE_FORMAT(UserCarTime,'%Y-%m-%d')<=?4 and DATE_FORMAT(UserCarTime,'%Y-%m-%d')>=?3)")
	public List<PrivateCarApplyEntity> getSearchInfo(String department, String applyman, String startTime,
			String endTime);
	
	//根据部门、姓名、开始时间、结束时间查询(部门=全部)
	//@Query(nativeQuery = true, value = "select * from B_EntertainApplyInfo where Manager like ?1 and (DATE_FORMAT(PaidTime,'%Y-%m-%d')<=?3 and DATE_FORMAT(PaidTime,'%Y-%m-%d')>=?2) and Status='已完成'")
	@Query(nativeQuery=true,value="select * from b_privatecar where ApplyMan like ?1 and Status='已通过' and IfPerform!='待执行' and IfPass='已报销' and (DATE_FORMAT(UserCarTime,'%Y-%m-%d')<=?3 and DATE_FORMAT(UserCarTime,'%Y-%m-%d')>=?2)")
	public List<PrivateCarApplyEntity> getSearchInfo1(String applyman, String startTime, String endTime);
	
	//个人获得待审批和被否决的表单
	@Query(nativeQuery=true,value="select * from b_privatecar where Status!='已通过' and ApplyMan=?1 order by ApplyId desc")
	public List<PrivateCarApplyEntity> getUnapproved(String applyman);
	
	//Web-获取私车公用信息总条数
	@Query(nativeQuery = true, value = "select count(*) from b_privatecar where Status='已通过'")
	public int getAllCount();
	
	//Web-获取私车公用信息总条数
	@Query(nativeQuery = true, value = "select count(*) from b_privatecar where Status='已通过' and SureTime='1'")
	public int getAllCount1();
	
	//Web-获取私车公用信息总条数
	@Query(nativeQuery = true, value = "select count(*) from b_privatecar where Status='已通过' and SureTime='0'")
	public int ungetAllCount();
	
	//Web-获取私车公用信息
	@Query(nativeQuery = true, value = "select * from b_privatecar where Status='已通过' order by ApplyId desc")
	public List<PrivateCarApplyEntity> getAllPassed();
	
	//Web-获取私车公用信息
	@Query(nativeQuery = true, value = "select * from b_privatecar where Status='已通过' order by ApplyId desc limit ?1,?2")
	public List<PrivateCarApplyEntity> carDisplay(int start, int number);
	
	//Web-获取私车公用信息（财务已审核）
	@Query(nativeQuery = true, value = "select * from b_privatecar where Status='已通过' and SureTime='1' order by ApplyId desc limit ?1,?2")
	public List<PrivateCarApplyEntity> carDisplay1(int start, int number);
	
	//Web-获取私车公用信息（财务未审核）
	@Query(nativeQuery = true, value = "select * from b_privatecar where Status='已通过' and SureTime='0' order by ApplyId desc limit ?1,?2")
	public List<PrivateCarApplyEntity> uncarDisplay(int start, int number);

	//Web-查询-获得私车公用相关信息(部门==全部)
	@Query(nativeQuery = true, value = "select * from b_privatecar where ApplyMan like ?3 and (DATE_FORMAT(ApplyTime,'%Y-%m-%d')<=?5 and DATE_FORMAT(ApplyTime,'%Y-%m-%d')>=?4) and Status='已通过' limit ?1,?2")
	public List<PrivateCarApplyEntity> searchPrivate(int start, int number, String applyman, String startTime,
			String endTime);
	//Web-查询-获得私车公用相关信息(部门！=全部)
	@Query(nativeQuery = true, value = "select * from b_privatecar where Department=?3 and ApplyMan like ?4 and (DATE_FORMAT(ApplyTime,'%Y-%m-%d')<=?6 and DATE_FORMAT(ApplyTime,'%Y-%m-%d')>=?5) and Status='已通过' limit ?1,?2")
	public List<PrivateCarApplyEntity> searchPrivateD(int start, int number, String department, String applyman,
			String startTime, String endTime);
	
	//Web-查询-获得私车公用信息条数（部门==全部）
	@Query(nativeQuery = true, value = "select count(*) from b_privatecar where ApplyMan like ?1 and (DATE_FORMAT(ApplyTime,'%Y-%m-%d')<=?2 and DATE_FORMAT(ApplyTime,'%Y-%m-%d')>=?3) and Status='已通过'")
	public int getSearchAllCount(String applyman, String endTime, String startTime);
	
	//Web-查询-获得私车公用信息条数（部门!=全部）
	@Query(nativeQuery = true, value = "select count(*) from b_privatecar where Department= ?1 and ApplyMan like ?2 and (DATE_FORMAT(ApplyTime,'%Y-%m-%d')<=?3 and DATE_FORMAT(ApplyTime,'%Y-%m-%d')>=?4) and Status='已通过'")
	public int getSearchAllCountD(String department, String applyman, String endTime, String startTime);

	//根据ID获取相关的私车公用信息
	@Query(nativeQuery = true, value = "select * from b_privatecar where ApplyId=?1 and Status='已通过' and IfPerform ='已执行'")
	public PrivateCarApplyEntity getPrivateById(String string);
	
	//根据ID获取相关的私车公用信息
	@Query(nativeQuery = true, value = "select count(*) from b_privatecar where UserCarTime=?1 and ApplyTime=?2 and ApplyMan =?3 ")
	public int getPrivateBy3(String usercartime,String applytime,String applyman);
	
	//用户查询已执行，未报销和已驳回私车记录
	@Query(nativeQuery = true, value = "select * from b_privatecar where (IfPass = '未报销' or IfPass = '已驳回' or IfPass = '已通过') and IfPerform = '已执行' and ApplyMan =:ApplyMan order by IfPass asc")
	public List<PrivateCarApplyEntity> getApplyList(@Param(value="ApplyMan")String ApplyMan);
	
	//用户查询已执行，未提交私车记录
	@Query(nativeQuery = true, value = "select * from b_privatecar where IfSub='0' and IfPerform = '已执行' and ApplyMan =:ApplyMan order by IfPass asc")
	public List<PrivateCarApplyEntity> getApplyListUnSub(@Param(value="ApplyMan")String ApplyMan);
	
	@Modifying
	@Query(nativeQuery = true, value = "update b_privatecar set IfPass = '待报销' where ApplyId =?1")
	public int updatePrivateCarIfPass(String applyid);
	
	@Modifying
	@Query(nativeQuery = true, value = "update b_privatecar set IfPass = '已报销' where ApplyId =?1")
	public int updatePrivateCarIfPassed(String applyid);
	
	@Modifying
	@Query(nativeQuery = true, value = "update b_privatecar set IfPass = '已驳回' where ApplyId =?1")
	public int updatePrivateCarIfUnPass(String applyid);
	
	@Modifying
	@Query(nativeQuery = true, value = "update b_privatecar set IfPass = '已通过' where ApplyId =?1")
	public int updatePrivateCarPass(String applyid);
	
	@Modifying
	@Query(nativeQuery = true, value = "update b_privatecar set IfPass = '待审核' where ApplyId =?1")
	public int updatePrivateCarUnIfPass(String applyid);
	
	@Modifying
	@Query(nativeQuery = true, value = "update b_privatecar set IfSub = '1' where ApplyId =?1")
	public int updatePrivateCarIfSub(String applyid);
	
	//设置为未提交状态
	@Modifying
	@Query(nativeQuery = true, value = "update b_privatecar set IfSub = '0' where ApplyId =?1")
	public int updatePrivateCarUnIfSub(String applyid);
	
	//修改申请
	@Modifying
	@Query(nativeQuery=true,value="update b_privatecar "
			+ "set Department=:Department,"
			+ "ApplyMan=:ApplyMan,"
			+ "UserCarTime=:UserCarTime,"
			+ "Reason=:Reason,"
			+ "BeginAddress=:BeginAddress,"
			+ "PassAddress=:PassAddress,"
			+ "Destination=:Destination,"
			+ "SingleLength=:SingleLength,"
			+ "SureLength=:SureLength,"
			+ "CountLength=:CountLength,"
			+ "DoubleLength=:DoubleLength,"
			+ "EndLength=:EndLength,"
			+ "WayModel=:WayModel,"
			+ "WayDetail=:WayDetail,"
			+ "ApproveTime=:ApproveTime,"
			+ "ApproveMan=:ApproveMan "
			+ "where ApplyId=:ApplyId")
	public int update(@Param(value="ApplyId")String ApplyId,
			@Param(value="Department")String Department,
			@Param(value="ApplyMan")String ApplyMan,
			@Param(value="UserCarTime")String UserCarTime,
			@Param(value="Reason")String Reason,
			@Param(value="BeginAddress")String BeginAddress,
			@Param(value="PassAddress")String PassAddress,
			@Param(value="Destination")String Destination,
			@Param(value="SingleLength")Double SingleLength,
			@Param(value="SureLength")Double SureLength,
			@Param(value="CountLength")Double CountLength,
			@Param(value="ApproveMan")String ApproveMan,
			@Param(value="DoubleLength")String DoubleLength,
			@Param(value="EndLength")String EndLength,
			@Param(value="ApproveTime")String ApproveTime,
			@Param(value="WayModel")String WayModel,
			@Param(value="WayDetail")String WayDetail
	);
	//修改申请
	@Modifying
	@Query(nativeQuery=true,value="update b_privatecar "
			+ "set Department=:Department,"
			+ "ApplyMan=:ApplyMan,"
			+ "UserCarTime=:UserCarTime,"
			+ "Reason=:Reason,"
			+ "BeginAddress=:BeginAddress,"
			+ "PassAddress=:PassAddress,"
			+ "Destination=:Destination,"
			+ "SingleLength=:SingleLength,"
			+ "SureLength=:SureLength,"
			+ "CountLength=:CountLength,"
			+ "DoubleLength=:DoubleLength,"
			+ "EndLength=:EndLength,"
			+ "ApproveTime=:ApproveTime,"
			+ "Status=:Status,"
			+ "IfPerform=:IfPerform,"
			+ "IfPass=:IfPass,"
			+ "WayModel=:WayModel,"
			+ "WayDetail=:WayDetail,"
			+ "ApproveMan=:ApproveMan "
			+ "where ApplyId=:ApplyId")
	public int updatePrivateCarStatusBack(@Param(value="ApplyId")String ApplyId,
			@Param(value="Department")String Department,
			@Param(value="ApplyMan")String ApplyMan,
			@Param(value="UserCarTime")String UserCarTime,
			@Param(value="Reason")String Reason,
			@Param(value="BeginAddress")String BeginAddress,
			@Param(value="PassAddress")String PassAddress,
			@Param(value="Destination")String Destination,
			@Param(value="SingleLength")Double SingleLength,
			@Param(value="SureLength")Double SureLength,
			@Param(value="CountLength")Double CountLength,
			@Param(value="ApproveMan")String ApproveMan,
			@Param(value="DoubleLength")String DoubleLength,
			@Param(value="EndLength")String EndLength,
			@Param(value="ApproveTime")String ApproveTime,
			@Param(value="Status")String Status,
			@Param(value="IfPerform")String IfPerform,
			@Param(value="IfPass")String IfPass,
			@Param(value="WayModel")String WayModel,
			@Param(value="WayDetail")String WayDetail
	);
	@Query(nativeQuery=true, value="select * from b_privatecar order by ApplyId desc limit ?1,?2")
  public List<PrivateCarApplyEntity> allPrivateCarDisplay(int paramInt1, int paramInt2);
  
  @Query(nativeQuery=true, value="select count(*) from b_privatecar")
  public int getAllDataCount();
  
  @Query(nativeQuery=true, value="select * from b_privatecar where ApplyTime >=?1 and ApplyTime<=?2 order by ApplyId desc limit ?3,?4")
  public List<PrivateCarApplyEntity> searchByTime(String paramString1, String paramString2, int paramInt1, int paramInt2);
  
  @Query(nativeQuery=true, value="select * from b_privatecar where Department =?1 and(ApplyTime >=?2 and ApplyTime<=?3) order by ApplyId desc limit ?4,?5")
  public List<PrivateCarApplyEntity> searchByDepartment(String department, String startTime, String endTime, int start, int number);
  
  @Query(nativeQuery=true, value="select * from b_privatecar where ApplyMan like ?1 and(ApplyTime >=?2 and ApplyTime<=?3) order by ApplyId desc limit ?4,?5")
  public List<PrivateCarApplyEntity> searchByApplyman(String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2);
  
  @Query(nativeQuery=true, value="select * from b_privatecar where Status=?1 and(ApplyTime >=?2 and ApplyTime<=?3) order by ApplyId desc limit ?4,?5")
  public List<PrivateCarApplyEntity> searchByStatus(String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2);
  
  @Query(nativeQuery=true, value="select * from b_privatecar where Department =?1 and ApplyMan like ?2 and(ApplyTime >=?3 and ApplyTime<=?4) order by ApplyId desc limit ?5,?6")
  public List<PrivateCarApplyEntity> searchByDepartApp(String paramString1, String paramString2, String paramString3, String paramString4, int paramInt1, int paramInt2);
  
  @Query(nativeQuery=true, value="select * from b_privatecar where Department =?1 and Status= ?2 and(ApplyTime >=?3 and ApplyTime<=?4) order by ApplyId desc limit ?5,?6")
  public List<PrivateCarApplyEntity> searchByDepartStatus(String paramString1, String paramString2, String paramString3, String paramString4, int paramInt1, int paramInt2);
  
  @Query(nativeQuery=true, value="select * from b_privatecar where ApplyMan like ?1 and Status= ?2 and(ApplyTime >=?3 and ApplyTime<=?4) order by ApplyId desc limit ?5,?6")
  public List<PrivateCarApplyEntity> searchByAppStatus(String paramString1, String paramString2, String paramString3, String paramString4, int paramInt1, int paramInt2);
  
  @Query(nativeQuery=true, value="select * from b_privatecar where Department =?1 and ApplyMan like ?2 and Status= ?3 and(ApplyTime >=?4 and ApplyTime<=?5) order by ApplyId desc limit ?6,?7")
  public List<PrivateCarApplyEntity> searchByDepartAppStatus(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, int paramInt1, int paramInt2);
  
  @Query(nativeQuery=true, value="select count(*) from b_privatecar where ApplyTime >=?1 and ApplyTime<=?2")
  public int getCountByTime(String paramString1, String paramString2);
  
  @Query(nativeQuery=true, value="select count(*) from b_privatecar where Department =?1 and (ApplyTime >=?2 and ApplyTime<=?3)")
  public int getCountByDepartment(String paramString1, String paramString2, String paramString3);
  
  @Query(nativeQuery=true, value="select count(*) from b_privatecar where ApplyMan like ?1 and (ApplyTime >=?2 and ApplyTime<=?3)")
  public int getCountByApplyman(String paramString1, String paramString2, String paramString3);
  
  @Query(nativeQuery=true, value="select count(*) from b_privatecar where Status= ?1 and (ApplyTime >=?2 and ApplyTime<=?3)")
  public int getCountByStatus(String paramString1, String paramString2, String paramString3);
  
  @Query(nativeQuery=true, value="select count(*) from b_privatecar where Department =?1 and ApplyMan like ?2 and (ApplyTime >=?3 and ApplyTime<=?4)")
  public int getCountByDepartApp(String paramString1, String paramString2, String paramString3, String paramString4);
  
  @Query(nativeQuery=true, value="select count(*) from b_privatecar where Department =?1 and Status= ?2 and (ApplyTime >=?3 and ApplyTime<=?4)")
  public int getCountByDepartStatus(String paramString1, String paramString2, String paramString3, String paramString4);
  
  @Query(nativeQuery=true, value="select count(*) from b_privatecar where ApplyMan like ?1 and Status= ?2 and (ApplyTime >=?3 and ApplyTime<=?4)")
  public int getCountByAppStatus(String paramString1, String paramString2, String paramString3, String paramString4);
  
  @Query(nativeQuery=true, value="select count(*) from b_privatecar where Department =?1 and ApplyMan like ?2 and Status= ?3 and (ApplyTime >=?4 and ApplyTime<=?5)")
  public int getCountByDepartAppStatus(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5);

  @Modifying
	@Query(nativeQuery=true,value="insert into b_privatecar ("
			+ "ApplyId,Department,"
			+ "ApplyMan,UserCarTime,"
			+ "Reason,BeginAddress,PassAddress,"
			+ "Status,Destination,"
			+ "SingleLength,SureLength,CountLength,ApproveMan,ApplyTime,WayModel,WayDetail,DoubleLength,EndLength,IfPass,IfBefore,BeforeDate,IfPerform,ApproveTime,IfSub,Sum,PaidTime,ApproveMan2,danhao,SubTime)"
			+ "VALUES(:ApplyId,:Department,:ApplyMan,:UserCarTime,:Reason,"
			+ ":BeginAddress,:PassAddress,:Status,"
			+ ":Destination,:SingleLength,"
			+ ":SureLength,:CountLength,:ApproveMan,:ApplyTime,:WayModel,:WayDetail,:DoubleLength,:EndLength,:IfPass,:IfBefore,:BeforeDate,:IfPerform,:ApproveTime,:IfSub,:Sum,:PaidTime,:ApproveMan2,:danhao,:SubTime)")
  public int insertEntityNew(@Param(value="ApplyId")String ApplyId,
			@Param(value="Department")String Department,
			@Param(value="ApplyMan")String ApplyMan,
			@Param(value="UserCarTime")String UserCarTime,
			@Param(value="Reason")String Reason,
			@Param(value="BeginAddress")String BeginAddress,
			@Param(value="PassAddress")String PassAddress,
			@Param(value="Status")String Status,
			@Param(value="Destination")String Destination,
			@Param(value="SingleLength")Double SingleLength,
			@Param(value="SureLength")Double SureLength,
			@Param(value="CountLength")Double CountLength,
			@Param(value="ApproveMan")String ApproveMan,
			@Param(value="ApplyTime")String ApplyTime,
			@Param(value="WayModel")String WayModel,
			@Param(value="WayDetail")String WayDetail,
			@Param(value="DoubleLength")String DoubleLength,
			@Param(value="EndLength")String EndLength,
			@Param(value="IfPass")String IfPass,
			@Param(value="IfBefore")String IfBefore,
			@Param(value="BeforeDate")String BeforeDate,
			@Param(value="IfPerform")String IfPerform,
			@Param(value="ApproveTime")String ApproveTime,
			@Param(value="IfSub")String IfSub,
			@Param(value="Sum")String Sum,
			@Param(value="PaidTime")String PaidTime,
			@Param(value="ApproveMan2")String ApproveMan2,
			@Param(value="danhao")String danhao,
			@Param(value="SubTime")String SubTime
		  );

  //财务修改私车申请信息
  @Modifying
  @Query(nativeQuery=true,value="update b_privatecar "
			+ "set Department=:Department,"
			+ "ApplyMan=:ApplyMan,"
			+ "UserCarTime=:UserCarTime,"
			+ "Reason=:Reason,"
			+ "BeginAddress=:BeginAddress,"
			+ "PassAddress=:PassAddress,"
			+ "Status=:Status,"
			+ "Destination=:Destination,"
			+ "SingleLength=:SingleLength,"
			+ "SureLength=:SureLength,"
			+ "CountLength=:CountLength,"
			+ "DoubleLength=:DoubleLength,"
			+ "EndLength=:EndLength,"
			+ "WayModel=:WayModel,"
			+ "WayDetail=:WayDetail,"
			+ "ApproveTime=:ApproveTime,"
			+ "ApproveMan=:ApproveMan,"
			+ "IfBefore=:IfBefore,"
			+ "IfPass=:IfPass,"
			+ "BeforeDate=:BeforeDate,"
			+ "IfPerform=:IfPerform,"
			+ "IfSub=:IfSub,"
			+ "Sum=:Sum,"
			+ "PaidTime=:PaidTime,"
			+ "ApproveMan2=:ApproveMan2,"
			+ "danhao=:danhao,"
			+ "SubTime=:SubTime,"
			+ "ApplyTime=:ApplyTime "
			+ "where ApplyId=:ApplyId")
  public int updateNew(@Param(value="ApplyId")String ApplyId,
		  @Param(value="Department")String Department,
			@Param(value="ApplyMan")String ApplyMan,
			@Param(value="UserCarTime")String UserCarTime,
			@Param(value="Reason")String Reason,
			@Param(value="BeginAddress")String BeginAddress,
			@Param(value="PassAddress")String PassAddress,
			@Param(value="Status")String Status,
			@Param(value="Destination")String Destination,
			@Param(value="SingleLength")Double SingleLength,
			@Param(value="SureLength")Double SureLength,
			@Param(value="CountLength")Double CountLength,
			@Param(value="ApproveMan")String ApproveMan,
			@Param(value="WayModel")String WayModel,
			@Param(value="WayDetail")String WayDetail,
			@Param(value="DoubleLength")String DoubleLength,
			@Param(value="EndLength")String EndLength,
			@Param(value="IfPass")String IfPass,
			@Param(value="IfBefore")String IfBefore,
			@Param(value="BeforeDate")String BeforeDate,
			@Param(value="IfPerform")String IfPerform,
			@Param(value="ApproveTime")String ApproveTime,
			@Param(value="IfSub")String IfSub,
			@Param(value="Sum")String Sum,
			@Param(value="PaidTime")String PaidTime,
			@Param(value="ApproveMan2")String ApproveMan2,
			@Param(value="danhao")String danhao,
			@Param(value="SubTime")String SubTime,
			@Param(value="ApplyTime")String ApplyTime
			);

}
