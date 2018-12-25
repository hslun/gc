package cn.com.hfga.dao.car;

import java.util.List;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import cn.com.hfga.dao.common.SpringDataDAO;
import cn.com.hfga.dto.car.CarUseDetailDTO;
import cn.com.hfga.entity.car.CarApplyInfoEntity;
import cn.com.hfga.entity.privatecar.PrivateCarApplyEntity;

@Repository("carApplyInfoDAO")
public interface CarApplyInfoDAO extends SpringDataDAO<CarApplyInfoEntity> {
	
	
	
	
	
	
	@Query(nativeQuery=true,value="select * from B_CarApplyInfo  where ApplyMan=:applyman and State='已预约'  order by ApplyId desc")
	public List<CarApplyInfoEntity> findByApplyMan(@Param(value = "applyman") String applyman);
	

	@Query(nativeQuery=true,value="select * from B_CarApplyInfo  where State=:state and ApproveState=:approvestate")
	public List<CarApplyInfoEntity> findByInState(@Param(value = "state") String state,@Param(value = "approvestate") String approvestate);
	
	@Query(nativeQuery=true,value="select * from B_CarApplyInfo  where  ApproveState=:approvestate")
	public List<CarApplyInfoEntity> findByOutState(@Param(value = "approvestate") String approvestate);
	
	
	//产生申请表的Id
	@Query(nativeQuery=true,value="select max(ApplyId) as ApplyId from B_CarApplyInfo where Department=:department and ApplyId like :applyid")
	public List<Object> getMaxId(@Param(value="department") String department,@Param(value="applyid") String applyid);
	
	
	//保存
	@Modifying
	@Query(nativeQuery=true,value="insert into B_CarApplyInfo (ApplyId,Department,ApplyUserName,ApplyMan,CarId,CarType,CarCode,Driver,LengthBegin,BeginTimePlan,EndTimePlan,UseCarReason,ApplyTime,ApproveState,StartAddress,EndAddress,AccountPlanTime,CompareManNum,AccountRealTime,RealApproveMan,AccountLength,CarBeginExamin,CarEndExamin,State,ApproveMan) VALUES(:applyid,:department,:applyusername,:applyman,:carid,:cartype,:carcode,:driver,:lengtbegin,:begintimeplan,:endtimeplan,:usercarreason,:applytime,:approvestate,:startaddress,:endAddress,:accountplantime,:comparemannum,:accountrealtime,:realapproveman,:accountlength,:carbeginexamin,:carendexamin,:state,:approveman)")
	public int insert(@Param(value="applyid")String applyid,@Param(value="department")String department,@Param(value="applyusername")String applyusername,
			@Param(value="applyman")String applyman,@Param(value="carid")String carid,@Param(value="cartype")String cartype,@Param(value="carcode")String carcode,
			@Param(value="driver")String driver,@Param(value="lengtbegin")String lengthend,@Param(value="begintimeplan")String begintimeplan,
			@Param(value="endtimeplan")String endtimeplan,@Param(value="usercarreason")String usercarreason,@Param(value="applytime")String applytime,
			@Param(value="approvestate")String approvestate,@Param(value="startaddress")String startaddress,@Param(value="endAddress")String endAddress,
			@Param(value="accountplantime")String accountplantime,
			@Param(value="comparemannum")String comparemannum,
			@Param(value="accountrealtime") String accountrealtime,
			@Param(value="realapproveman") String realapproveman,
			@Param(value="accountlength")String accountlength ,
			@Param(value="carbeginexamin")String carbeginexamin,
			@Param(value="carendexamin")String carendexamin,
			@Param(value="state") String state,
			@Param(value="approveman")String approveman
			);
	//修改借车申请的信息
		@Modifying
		@Query(nativeQuery=true,value="update B_CarApplyInfo set Department=:department,ApplyMan=:applyman,ApplyUserName=:applyusername,CarId=:carid,CarType=:cartype,CarCode=:carcode,Driver=:driver,LengthBegin=:lengthbegin,BeginTimePlan=:begintimeplan,EndTimePlan=:endtimeplan,UseCarReason=:usecarreason,ApplyTime=:applytime,ApproveState=:approvestate,StartAddress=:startaddress,EndAddress=:endaddress,AccountPlanTime=:accountplantime,CompareManNum=:comparemannum,AccountRealTime=:accountrealtime,AccountLength=:accountlength,CarBeginExamin=:carbeginexamin,CarEndExamin=:carendexamin,State=:state,ApproveMan=:approveman where ApplyId=:applyid")
		public int modifyOne(@Param(value="applyid")String applyid,
				@Param(value="department")String department,
				@Param(value="applyusername")String applyusername,
				@Param(value="applyman")String applyman,
				@Param(value="carid")String carid,
				@Param(value="cartype")String cartype,
				@Param(value="carcode")String carcode,
				@Param(value="driver")String driver,
				@Param(value="lengthbegin")String lengthend,
				@Param(value="begintimeplan")String begintimeplan,
				@Param(value="endtimeplan")String endtimeplan,
				@Param(value="usecarreason")String usercarreason,
				@Param(value="applytime")String applytime,
				@Param(value="approvestate")String approvestate,
				@Param(value="startaddress")String startaddress,
				@Param(value="endaddress")String endAddress,
				@Param(value="accountplantime")String accountplantime,
				@Param(value="comparemannum")String comparemannum,
				@Param(value="accountrealtime") String accountrealtime,
				@Param(value="accountlength")String accountlength ,
				@Param(value="carbeginexamin")String carbeginexamin,
				@Param(value="carendexamin")String carendexamin,
				@Param(value="state") String state,
				@Param(value="approveman")String approveman);
	
	//审批通过
	@Modifying
	@Query(nativeQuery=true,value="update B_CarApplyInfo set ApproveState=:state,RealApproveMan=:realapprove where ApplyId=:applyid")
	public int updateState(@Param(value="state") String state,@Param(value="realapprove") String realapprove,@Param(value="applyid") String applyid);
	
	//借车出库  操作
	@Modifying
	@Query(nativeQuery=true,value="update B_CarApplyInfo set State=:state,BeginTime=:begintime,LengthBegin=:lengthbegin where ApplyId=:applyid")
	public int outState(@Param(value="state") String state,@Param(value="applyid") String applyid,@Param(value="begintime")String begintime,@Param(value="lengthbegin")String lengthbegin);

	
	//借车或者出库出库信息
	@Query(nativeQuery=true,value="select * from B_CarApplyInfo where State=:state order by  ApplyId desc")
	public List<CarApplyInfoEntity> getOutInfo(@Param(value="state") String state);
	
	//还车入库  操作
	@Modifying
	@Query(nativeQuery=true,value="update B_CarApplyInfo set State=:state,EndTime=:endtime,AccountRealTime=:realtime,AccountLength=:reallength,LengthEnd=:lengthend where ApplyId=:applyid")
	public int inState(@Param(value="state") String state,@Param(value="endtime")String endtime,@Param(value="realtime") String realtime,@Param(value="reallength") String reallength,@Param(value="applyid")String appylid,@Param(value="lengthend") String lengthend);
	
	//查询某个部门某辆车某个时间段
	@Query(nativeQuery=true,value="select Department,CarCode,sum(AccountLength)as AccountLength,sum(AccountRealTime)as AccountRealTime from B_CarApplyInfo where CarCode=:carcode and Department=:department and ApplyTime >:starttime and ApplyTime<:endtime group by Department,CarCode")
	public List<Object []> getCollectInfo1(@Param(value="carcode") String carcode,@Param(value="department")String department,@Param(value="starttime")String starttime,@Param(value="endtime") String endtime);
	
	//查询某个部门某辆车某个时间段 某个人的详细试用明细
	@Query(nativeQuery=true,value="select * from B_CarApplyInfo where CarCode=:carcode and Department=:department and ApplyTime >:starttime and ApplyTime<:endtime and ApplyMan=:applyman and ApproveState='通过' and State='已归还'")
	public List<CarApplyInfoEntity> getDetailInfo1(@Param(value="carcode") String carcode,@Param(value="department")String department,@Param(value="starttime")String starttime,@Param(value="endtime") String endtime,@Param(value="applyman") String applyman);
	
	//Text测试一下
	//部门是全部   车不是全部
	@Query(nativeQuery=true,value="select Department,CarCode,sum(AccountLength)as AccountLength,sum(AccountRealTime)as AccountRealTime from B_CarApplyInfo where CarCode=:carcode  and ApplyTime >:starttime and ApplyTime<:endtime and ApproveState='通过' and State='已归还' group by CarCode,Department")
	public List<Object []> getCollectInfo2(@Param(value="carcode") String carcode,@Param(value="starttime")String starttime,@Param(value="endtime") String endtime);
	
	//部门是个别 车是全部
	@Query(nativeQuery=true,value="select Department,CarCode,sum(AccountLength)as AccountLength,sum(AccountRealTime)as AccountRealTime from B_CarApplyInfo where Department=:department  and ApplyTime >:starttime and ApplyTime<:endtime and ApproveState='通过' and State='已归还' group by Department,CarCode")
	public List<Object []> getCollectInfo3(@Param(value="department") String department,@Param(value="starttime")String starttime,@Param(value="endtime") String endtime);
	
	//部门是全部 车市全部
	@Query(nativeQuery=true,value="select Department,CarCode,sum(AccountLength)as AccountLength,sum(AccountRealTime)as AccountRealTime from B_CarApplyInfo where  ApplyTime >:starttime and ApplyTime<:endtime and ApproveState='通过' and State='已归还' group by Department,CarCode")
	public List<Object []> getCollectInfo4(@Param(value="starttime")String starttime,@Param(value="endtime") String endtime);
	
	//管理员审批所有的权限
	@Query(nativeQuery=true,value="select * from B_CarApplyInfo where ApproveState=:approvestate order by ApplyId desc")
	public List<CarApplyInfoEntity> getApproveInfo1(@Param(value="approvestate") String approvestate);
	
	//部门领导 审批
	@Query(nativeQuery=true,value="select * from B_CarApplyInfo where ApproveState=:approvestate and Department=:department and ApproveMan=:approveman order by ApplyId desc")
	public List<CarApplyInfoEntity> getApproveInfo2(@Param(value="approvestate") String approvestate,@Param(value="department") String department,@Param(value="approveman") String approveman);
	
	//公司领导审批其他部分的（由业务主管申请的）
	@Query(nativeQuery=true,value="select * from B_CarApplyInfo where ApproveState=:approvestate and ApproveMan=:approveman order by ApplyId desc")
	public List<CarApplyInfoEntity> getApproveInfo3(@Param(value="approvestate") String approvestate,@Param(value="approveman") String approveman);
		
	
	//获取所有的申请信息
	@Query(nativeQuery=true,value="select * from B_CarApplyInfo where carid=:carid and ApproveState=:approvestate and State !=:state")
	public List<CarApplyInfoEntity> getAllApply(@Param(value="carid") String carid,@Param(value="approvestate") String approvestate,@Param(value="state") String state);
	
	//获取所有的申请信息
	@Query(nativeQuery=true,value="select * from B_CarApplyInfo order by  ApplyId desc")
	public List<CarApplyInfoEntity> getAllApply1();
		
		
	//获取所有的申请信息
	@Query(nativeQuery=true,value="select * from B_CarApplyInfo where CarCode=:carcode and ApproveState=:approvestate and State!=:state")
	public List<CarApplyInfoEntity> getAllApplyDetail(@Param(value="carcode") String carcode,@Param(value="approvestate") String approvestate,@Param(value="state") String state);
	
	
	//获取所有的申请信息
	@Query(nativeQuery=true,value="select * from B_CarApplyInfo where CarId=:carid and ApproveState=:approvestate and State!=:state and BeginTimePlan<:addday and BeginTimePlan >:delday")
	public List<CarApplyInfoEntity> getAllOrder(@Param(value="carid") String carid,@Param(value="approvestate") String approvestate,@Param(value="state") String state,@Param(value="addday") String addday,@Param(value="delday") String delday);
	
	//更改实体信息进行保存
	//@Modifying
	//@Query(nativeQuery=true,value="")
	
	//获取一个实体信息
	@Query(nativeQuery=true,value="select * from B_CarApplyInfo where ApplyId=:applyid")
	public List<CarApplyInfoEntity> getOne(@Param(value="applyid") String applyid);
	
	
	
	
	
	//部门全部 车不全部--个人
	@Query(nativeQuery=true,value="select * from B_CarApplyInfo where CarCode=:carcode  and ApplyTime >:starttime and ApplyTime<:endtime and ApplyMan=:applyman and ApproveState='通过' and State='已归还'")
	public List<CarApplyInfoEntity> getDetail1(@Param(value="carcode") String carcode,@Param(value="starttime")String starttime,@Param(value="endtime") String endtime,@Param(value="applyman") String applyman);
	
	//部门是全部  车是全部--个人
	@Query(nativeQuery=true,value="select * from B_CarApplyInfo where  ApplyTime >:starttime and ApplyTime<:endtime and ApplyMan=:applyman and ApproveState='通过' and State='已归还'")
	public List<CarApplyInfoEntity> getDetail2(@Param(value="starttime")String starttime,@Param(value="endtime") String endtime,@Param(value="applyman") String applyman);
	
	//部门是全部 车不是全部-个人
	//@Query(nativeQuery=true,value="select * from B_CarApplyInfo where CarCode=:carcode  and ApplyTime >:starttime and ApplyTime<:endtime and ApplyMan=:applyman and ApproveState='通过' and State='已归还'"")
	
	//部门不是全部  车是全部--个人
	@Query(nativeQuery=true,value="select * from B_CarApplyInfo where  ApplyTime >:starttime and ApplyTime<:endtime and ApplyMan=:applyman and Department=:department and ApproveState='通过' and State='已归还' ")
	public List<CarApplyInfoEntity> getDetail12(@Param(value="starttime")String starttime,@Param(value="endtime") String endtime,@Param(value="applyman") String applyman,@Param(value="department")String department);

	//看看某个时间段是否有申请
	@Query(nativeQuery=true,value="select * from B_CarApplyInfo where CarCode=:carcode and (ApproveState='通过' and State!='已归还') and ((BeginTimePlan>=:begintime and BeginTimePlan<=:endtime) or(EndTimePlan>=:begintime and EndTimePlan<=:endtime) or(BeginTimePlan<=:begintime and EndTimePlan>=:endtime))")
	public List<CarApplyInfoEntity> getExist(@Param(value="carcode") String carcode,@Param(value="begintime")String begintime,@Param(value="endtime")String endtime);
	
	//查看申请记录条数
	@Query(nativeQuery=true,value="select count(*) from B_CarApplyInfo")
	public int getCount();
	
	//获取最新插入的某条记录
//	@Query(nativeQuery=true,value="select top 1 * from B_CarApplyInfo order by ApplyTime desc")
	@Query(nativeQuery=true,value="select * from B_CarApplyInfo order by ApplyTime desc limit 1")
	public List<CarApplyInfoEntity> getTopOne();
	
	//部门全部  车是全部
	@Query(nativeQuery=true,value="select *from B_CarApplyInfo where  ApplyTime >:starttime and ApplyTime<:endtime  and ApproveState='通过' and State='已归还'")
	public  List<CarApplyInfoEntity> getDetail11(@Param(value="starttime") String starttime,@Param(value="endtime") String endtime);
	
	//部门不是全部  车是全部
	@Query(nativeQuery=true,value="select *from B_CarApplyInfo where  ApplyTime >:starttime and ApplyTime<:endtime and Department=:department and ApproveState='通过' and State='已归还'")
	public  List<CarApplyInfoEntity> getDetail10(@Param(value="starttime") String starttime,@Param(value="endtime") String endtime,@Param(value="department") String department);
	
	//部门不是全部  车不是全部
	@Query(nativeQuery=true,value="select *from B_CarApplyInfo where  ApplyTime >:starttime and ApplyTime<:endtime and Department=:department and ApproveState='通过' and State='已归还' and CarCode=:carcode")
	public  List<CarApplyInfoEntity> getDetail00(@Param(value="starttime") String starttime,@Param(value="endtime") String endtime,@Param(value="department") String department,@Param(value="carcode") String carcode);
	
	//部门是全部 车是全部
	@Query(nativeQuery=true,value="select *from B_CarApplyInfo where  ApplyTime >:starttime and ApplyTime<:endtime and CarCode=:carcode and ApproveState='通过' and State='已归还'")
	public  List<CarApplyInfoEntity> getDetail01(@Param(value="starttime") String starttime,@Param(value="endtime") String endtime,@Param(value="carcode") String carcode);
	
	
	//获取全部申请信息
	@Query(nativeQuery=true,value="select *from B_CarApplyInfo")
	public List<CarApplyInfoEntity> getAll();
	
	//获取全部申请信息
	@Query(nativeQuery=true,value="select *from B_CarApplyInfo where ApproveState = '通过' order by ApplyId desc")
	public List<CarApplyInfoEntity> getAllPassed();
	
	//去除applyman中的空格
	@Modifying
	@Query(nativeQuery=true,value="update B_CarApplyInfo set ApplyMan=:applyman where ApplyId=:applyid")
	public int modifyApplyMan(@Param(value="applyman") String applyman,@Param(value="applyid") String applyid);
	
	//修改AccountLength=null 的
	@Modifying
	@Query(nativeQuery=true,value="update B_CarApplyInfo set AccountLength='0' where AccountLength is null")
	public int modifyAccountLength();
	
	//修改AccountRealTime=null 的
	@Modifying
	@Query(nativeQuery=true,value="update B_CarApplyInfo set AccountRealTime='0' where AccountRealTime is null")
	public int modifyAccountRealTime();
	
	
	//删除一个申请
	@Modifying
	@Query(nativeQuery=true,value="delete from B_CarApplyInfo where ApplyId=:applyid")
	public int delApply(@Param(value="applyid") String applyid);

	//预约车前查询是否还车
	@Query(nativeQuery = true, value = "select count(*) from B_CarApplyInfo where State='已借出' and ApplyMan=:applyman ")
	public int selectIfReturn(@Param(value="applyman") String applyman);
	
	//Web-根据部门、申请人、车牌、申请时间查询
	@Query(nativeQuery=true,value="select *from B_CarApplyInfo where Department like ?1 and ApplyMan like ?2 and CarCode=?3 and (DATE_FORMAT(BeginTime,'%Y/%m/%d')<=?5 and DATE_FORMAT(BeginTime,'%Y/%m/%d')>=?4)")
    public List<CarApplyInfoEntity> getSearchInfoD(String department, String applyman, String carcode, String start,
			String end);

	//Web-显示公车相关信息
	@Query(nativeQuery = true, value = "select * from B_CarApplyInfo where ApproveState='通过' order by ApplyId desc limit ?1,?2 ")
	public List<CarApplyInfoEntity> carDisplay(int start, int number);
	
	//Web-显示公车相关信息
	@Query(nativeQuery = true, value = "select * from B_CarApplyInfo where ApproveState='通过' order by ApplyId desc")
	public List<CarApplyInfoEntity> carDisplayExport();

	//Web-获得公车信息的总条数
	@Query(nativeQuery = true, value = "select count(*) from B_CarApplyInfo where ApproveState='通过'")
	public int getAllCount();


	//Web-查询-部门和车辆都为全部
	@Query(nativeQuery=true,value="select * from B_CarApplyInfo where ApplyMan like ?1 and (ApplyTime>=?2 and ApplyTime<=?3) and ApproveState='通过' and State='已归还'")
	public List<CarApplyInfoEntity> getSearchInfo11(String applyman, String start, String end);

	//Web-查询-仅部门为全部
	@Query(nativeQuery=true,value="select * from B_CarApplyInfo where ApplyMan like ?1 and CarCode = ?2 and ApplyTime>=?3 and ApplyTime<=?4 and ApproveState='通过' and State='已归还'")
	public List<CarApplyInfoEntity> getSearchInfo10(String applyman, String carcode, String start, String end);

	//Web-查询-仅车辆为全部
	@Query(nativeQuery=true,value="select * from B_CarApplyInfo where Department like ?1 and ApplyMan like ?2 and ApplyTime >?3 and ApplyTime<?4 and ApproveState='通过' and State='已归还'")
	public List<CarApplyInfoEntity> getSearchInfo01(String department, String applyman, String start, String end);

	//Web-查询-部门和车辆都不为全部
	@Query(nativeQuery=true,value="select * from B_CarApplyInfo where Department like ?1 and ApplyMan like ?2 and CarCode=?3 and ApplyTime >=?4 and ApplyTime<=?5 and ApproveState='通过' and State='已归还'")
	public List<CarApplyInfoEntity> getSearchInfo00(String department, String applyman, String carcode, String start,
			String end);


	//Web-根据id获得公车相关信息
	@Query(nativeQuery=true,value="select * from B_CarApplyInfo where ApplyId=?1 and ApproveState='通过' and State='已归还'")
	public CarApplyInfoEntity getPrivateById(String string);
	
	//Web-根据id获得公车相关信息
	@Query(nativeQuery=true,value="select * from B_CarApplyInfo where ApproveState='通过' and State='已归还'")
	public List<CarApplyInfoEntity> getPrivateAll();

	//-------------------
	//Web-查询-部门和车辆都为全部
	@Query(nativeQuery=true,value="select * from B_CarApplyInfo where ApplyMan like ?1 and (ApplyTime>=?2 and ApplyTime<=?3) and ApproveState='通过' and State='已归还' order by ApplyId desc limit ?4,?5")
	public List<CarApplyInfoEntity> getSearchInfo11ByPage(String applyman, String start, String end,int sta,int number);

	//Web-查询-仅部门为全部
	@Query(nativeQuery=true,value="select * from B_CarApplyInfo where ApplyMan like ?1 and CarCode = ?2 and ApplyTime>=?3 and ApplyTime<=?4 and ApproveState='通过' and State='已归还' order by ApplyId desc limit ?5,?6")
	public List<CarApplyInfoEntity> getSearchInfo10ByPage(String applyman, String carcode, String start, String end,int sta,int number);

	//Web-查询-仅车辆为全部
	@Query(nativeQuery=true,value="select * from B_CarApplyInfo where Department like ?1 and ApplyMan like ?2 and ApplyTime >?3 and ApplyTime<?4 and ApproveState='通过' and State='已归还' order by ApplyId desc limit ?5,?6")
	public List<CarApplyInfoEntity> getSearchInfo01ByPage(String department, String applyman, String start, String end,int sta,int number);

	//Web-查询-部门和车辆都不为全部
	@Query(nativeQuery=true,value="select * from B_CarApplyInfo where Department like ?1 and ApplyMan like ?2 and CarCode=?3 and ApplyTime >=?4 and ApplyTime<=?5 and ApproveState='通过' and State='已归还' order by ApplyId desc limit ?6,?7")
	public List<CarApplyInfoEntity> getSearchInfo00ByPage(String department, String applyman, String carcode, String start,
			String end,int sta,int number);
	//--------------------
	//Web-查询-部门和车辆都为全部
	@Query(nativeQuery=true,value="select count(*) from B_CarApplyInfo where ApplyMan like ?1 and (ApplyTime>=?2 and ApplyTime<=?3) and ApproveState='通过' and State='已归还'")
	public int getSearchInfo11Count(String applyman, String start, String end);

	//Web-查询-仅部门为全部
	@Query(nativeQuery=true,value="select count(*) from B_CarApplyInfo where ApplyMan like ?1 and CarCode = ?2 and ApplyTime>=?3 and ApplyTime<=?4 and ApproveState='通过' and State='已归还'")
	public int getSearchInfo10Count(String applyman, String carcode, String start, String end);

	//Web-查询-仅车辆为全部
	@Query(nativeQuery=true,value="select count(*) from B_CarApplyInfo where Department like ?1 and ApplyMan like ?2 and ApplyTime >?3 and ApplyTime<?4 and ApproveState='通过' and State='已归还'")
	public int getSearchInfo01Count(String department, String applyman, String start, String end);

	//Web-查询-部门和车辆都不为全部
	@Query(nativeQuery=true,value="select count(*) from B_CarApplyInfo where Department like ?1 and ApplyMan like ?2 and CarCode=?3 and ApplyTime >=?4 and ApplyTime<=?5 and ApproveState='通过' and State='已归还'")
	public int getSearchInfo00Count(String department, String applyman, String carcode, String start,
			String end);

}
