package cn.com.hfga.dao.gz;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cn.com.hfga.dao.common.SpringDataDAO;
import cn.com.hfga.entity.gz.GZApplyInfoEntity;

@Repository("gZApplyInfoDAO")
public interface GZApplyInfoDAO extends SpringDataDAO<GZApplyInfoEntity> {

	// 查找最大的ID
	@Query(nativeQuery = true, value = "select max(ID) as ID from B_GZApplyInfo where Department=:department and ID like:ID")
	public List<Object> getMaxId(@Param(value = "department") String department, @Param(value = "ID") String ID);

	// 获取所有的申请信息
	@Query(nativeQuery = true, value = "select * from B_GZApplyInfo where Status = '已完成' order by ApplyTime desc")
	public List<GZApplyInfoEntity> getAll();

	// 获取需要审批的信息---根据审批人
	@Query(nativeQuery = true, value = "select * from B_GZApplyInfo where ApproveMan=:approveMan and Status='待审批' order by ApplyTime desc")
	public List<GZApplyInfoEntity> getByApprove(@Param(value = "approveMan") String approveMan);

	// 获取待确认的
	/**
	 * 公章流程分为待审批、通过/否决、待确认、已确认、已完成 小殷 如果是合同专用章 -》已确认 法人章》已确认 公章和营业执照-》都需要获取通过
	 */
	@Query(nativeQuery = true, value = "select * from B_GZApplyInfo where ((GZKind='公章（合同授权委托书）' or  GZKind='法人章')and Status='已确认') or ((GZKind='公章' or GZKind='营业执照' or GZKind='公章（其他）' or GZKind='合同专用章') and Status='通过') order by ApplyTime desc")
	public List<GZApplyInfoEntity> getNeedYin();

	// 王川专用 合同章需要王传确认
	@Query(nativeQuery = true, value = "select * from B_GZApplyInfo where GZKind='公章（合同授权委托书）' and Status='通过' order by ApplyTime desc")
	public List<GZApplyInfoEntity> getNeedChuan();

	// 谭总专用 法人章需要谭总确认
	@Query(nativeQuery = true, value = "select * from B_GZApplyInfo where GZKind='法人章' and Status='通过' order by ApplyTime desc")
	public List<GZApplyInfoEntity> getNeedTan();

	// 写一个总的根据GZKind和ApproveMan
	@Query(nativeQuery = true, value = "select * from B_GZApplyInfo where GZKind=:GZKind and Status=:Status  order by ApplyTime desc")
	public List<GZApplyInfoEntity> getNeeded(@Param(value = "GZKind") String GZKind,
			@Param(value = "Status") String Status);

	// 更改状态--用在审批上
	@Modifying
	@Query(nativeQuery = true, value = "update B_GZApplyInfo set Status=:Status,ApproveMan=:ApproveMan where ID=:ID ")
	public int updateApprove(@Param(value = "Status") String Status, @Param(value = "ApproveMan") String ApproveMan,
			@Param(value = "ID") String ID);

	// 更改状态-用在确认上
	@Modifying
	@Query(nativeQuery = true, value = "update B_GZApplyInfo set Status=:Status,ConfirmMan=:ConfirmMan where ID=:ID ")
	public int updateSure(@Param(value = "Status") String Status, @Param(value = "ConfirmMan") String ConfirmMan,
			@Param(value = "ID") String ID);

	// 更改状态--用在已完成上
	@Modifying
	@Query(nativeQuery = true, value = "update B_GZApplyInfo set Status=:Status  where ID=:ID")
	public int updateStatus(@Param(value = "Status") String Status, @Param(value = "ID") String ID);

	// 保存
	@Modifying
	@Query(nativeQuery = true, value = "insert into B_GZApplyInfo (ID,Department,ApplyUserName,Reason,ApplyTime,SendTo,GZKind,Copies,IsSecret,Status,ApproveMan) VALUES(:ID,:Department,:ApplyUserName,:Reason,:ApplyTime,:SendTo,:GZKind,:Copies,:IsSecret,:Status,:ApproveMan)")
	public int insert(@Param(value = "ID") String ID, @Param(value = "Department") String Department,
			@Param(value = "ApplyUserName") String ApplyUserName, @Param(value = "Reason") String Reason,
			@Param(value = "ApplyTime") String ApplyTime, @Param(value = "SendTo") String SendTo,
			@Param(value = "GZKind") String GZKind, @Param(value = "Copies") String Copies,
			@Param(value = "IsSecret") String IsSecret, @Param(value = "Status") String Status,
			@Param(value = "ApproveMan") String ApproveMan);

	// 更改申请
	@Modifying
	@Query(nativeQuery = true, value = "update B_GZApplyInfo set Department=:Department,ApplyUserName=:ApplyUserName,Reason=:Reason,ApplyTime=:ApplyTime,SendTo=:SendTo,GZKind=:GZKind,Copies=:Copies,IsSecret=:IsSecret,Status=:Status,ApproveMan=:ApproveMan where ID=:ID")
	public int modifyOne(@Param(value = "ID") String ID, @Param(value = "Department") String Department,
			@Param(value = "ApplyUserName") String ApplyUserName, @Param(value = "Reason") String Reason,
			@Param(value = "ApplyTime") String ApplyTime, @Param(value = "SendTo") String SendTo,
			@Param(value = "GZKind") String GZKind, @Param(value = "Copies") String Copies,
			@Param(value = "IsSecret") String IsSecret, @Param(value = "Status") String Status,
			@Param(value = "ApproveMan") String ApproveMan);

	// 删除一条申请
	@Modifying
	@Query(nativeQuery = true, value = "delete from B_GZApplyInfo where ID=:ID")
	public int delete(@Param(value = "ID") String ID);

	// 否决申请
	@Modifying
	@Query(nativeQuery = true, value = "update B_GZApplyInfo set Status=:Status where ID=:ID")
	public int denyApply(@Param(value = "Status") String Status, @Param(value = "ID") String ID);

	/*
	 * 下列查询函数1 代表全部 0代表具体
	 */
	// 查询所用--全部部门信息和具体印章
	@Query(nativeQuery = true, value = "select * from B_GZApplyInfo where ApplyUserName like:ApplyUserName and (ApplyTime<=:endTime and ApplyTime>=:startTime) and GZKind=:GZKind  and SendTo like :sendTo   and Status='已完成'  order by ApplyTime desc")
	public List<GZApplyInfoEntity> getSearchInfo10(@Param(value = "ApplyUserName") String ApplyUserName,
			@Param(value = "startTime") String startTime, @Param(value = "endTime") String endTime,
			@Param(value = "GZKind") String GZKind, @Param(value = "sendTo") String sendTo);

	// 查询具体部门 和具体印章
	@Query(nativeQuery = true, value = "select * from B_GZApplyInfo where Department=:Department and ApplyUserName like:ApplyUserName and (ApplyTime<=:endTime and ApplyTime>=:startTime) and GZKind=:GZKind and SendTo like :sendTo   and Status='已完成' order by ApplyTime desc")
	public List<GZApplyInfoEntity> getSearchInfo00(@Param(value = "Department") String Department,
			@Param(value = "ApplyUserName") String ApplyUserName, @Param(value = "startTime") String startTime,
			@Param(value = "endTime") String endTime, @Param(value = "GZKind") String GZKind,
			@Param(value = "sendTo") String sendTo);

	// 查询具体部门和全部印章
	@Query(nativeQuery = true, value = "select * from B_GZApplyInfo where Department=:Department and ApplyUserName like:ApplyUserName and (ApplyTime<=:endTime and ApplyTime>=:startTime)  and SendTo like :sendTo   and Status='已完成'  order by ApplyTime desc")
	public List<GZApplyInfoEntity> getSearchInfo01(@Param(value = "Department") String Department,
			@Param(value = "ApplyUserName") String ApplyUserName, @Param(value = "startTime") String startTime,
			@Param(value = "endTime") String endTime, @Param(value = "sendTo") String sendTo);

	// 查询全部部门和全部印章
	@Query(nativeQuery = true, value = "select * from B_GZApplyInfo where ApplyUserName like:ApplyUserName and (ApplyTime<=:endTime and ApplyTime>=:startTime)  and SendTo like :sendTo and Status='已完成' order by ApplyTime desc")
	public List<GZApplyInfoEntity> getSearchInfo11(@Param(value = "ApplyUserName") String ApplyUserName,
			@Param(value = "startTime") String startTime, @Param(value = "endTime") String endTime,
			@Param(value = "sendTo") String sendTo);

	// 获取个人申请信息
	@Query(nativeQuery = true, value = "select * from B_GZApplyInfo where ApplyUserName=:ApplyUserName and Status!='已完成' order by ApplyTime desc")
	public List<GZApplyInfoEntity> getPersonal(@Param(value = "ApplyUserName") String ApplyUserName);
	
	
	//获取个人申请信息
	@Query(nativeQuery = true, value = "select * from B_GZApplyInfo where ID=:ID")
	public List<GZApplyInfoEntity> getOne(@Param(value = "ID") String ID);

	//Web-获取公章相关信息
	@Query(nativeQuery = true, value = "select * from B_GZApplyInfo where Status='已完成' limit ?1,?2")
	public List<GZApplyInfoEntity> GZManageDisplay(int start, int number);

	//Web-获取公章信息总条数
	@Query(nativeQuery = true, value = "select count(*) from B_GZApplyInfo where Status='已完成'")
	public int getAllCount();

	//Web-查询-部门和盖章种类均为全部
	@Query(nativeQuery = true, value = "select * from B_GZApplyInfo where ApplyUserName like ?1 and (ApplyTime<=?3 and ApplyTime>=?2) and Status='已完成' order by ApplyTime desc")
	public List<GZApplyInfoEntity> wGetSearchInfo11(String username, String start, String end);

	//Web-查询-仅部门为全部
	@Query(nativeQuery = true, value = "select * from B_GZApplyInfo where GZKind=?1 and ApplyUserName like ?2 and (ApplyTime<=?4 and ApplyTime>=?3)  and Status='已完成' order by ApplyTime desc")
	public List<GZApplyInfoEntity> wGetSearchInfo10(String gzkind, String username, String start, String end);

	//Web-查询-仅盖章种类为全部
	@Query(nativeQuery = true, value = "select * from B_GZApplyInfo where Department=?1 and ApplyUserName like ?2 and (ApplyTime<=?4 and ApplyTime>=?3) and Status='已完成' order by ApplyTime desc")
	public List<GZApplyInfoEntity> wGetSearchInfo01(String departname, String username, String start, String end);

	//Web-查询-都不为全部
	@Query(nativeQuery = true, value = "select * from B_GZApplyInfo where where Department=?1 and GZKind = ?2 and ApplyUserName like ?3 and (ApplyTime<=?5 and ApplyTime>=?4) and Status='已完成' order by ApplyTime desc")
	public List<GZApplyInfoEntity> wGetSearchInfo00(String departname, String gzkind, String username, String start,
			String end);

	//根据ID获得公章相关信息
	@Query(nativeQuery=true,value="select * from B_GZApplyInfo where ID=?1 and Status='已完成'")
	public GZApplyInfoEntity getGZById(String string);

	//Web-查询-部门和盖章种类均为全部
	@Query(nativeQuery = true, value = "select * from B_GZApplyInfo where ApplyUserName like ?1 and (ApplyTime<=?3 and ApplyTime>=?2) and Status='已完成' order by ApplyTime desc limit ?4,?5")
	public List<GZApplyInfoEntity> wGetSearchInfo11ByPage(String username, String start, String end,int s, int e);

	//Web-查询-仅部门为全部
	@Query(nativeQuery = true, value = "select * from B_GZApplyInfo where GZKind=?1 and ApplyUserName like ?2 and (ApplyTime<=?4 and ApplyTime>=?3)  and Status='已完成' order by ApplyTime desc limit ?5,?6")
	public List<GZApplyInfoEntity> wGetSearchInfo10ByPage(String gzkind, String username, String start, String end,int s, int e);

	//Web-查询-仅盖章种类为全部
	@Query(nativeQuery = true, value = "select * from B_GZApplyInfo where Department=?1 and ApplyUserName like ?2 and (ApplyTime<=?4 and ApplyTime>=?3) and Status='已完成' order by ApplyTime desc limit ?5,?6")
	public List<GZApplyInfoEntity> wGetSearchInfo01ByPage(String departname, String username, String start, String end,int s, int e);

	//Web-查询-都不为全部
	@Query(nativeQuery = true, value = "select * from B_GZApplyInfo where where Department=?1 and GZKind = ?2 and ApplyUserName like ?3 and (ApplyTime<=?5 and ApplyTime>=?4) and Status='已完成' order by ApplyTime desc limit ?6,?7")
	public List<GZApplyInfoEntity> wGetSearchInfo00ByPage(String departname, String gzkind, String username, String start,
			String end,int s, int e);
	
	//Web-查询-部门和盖章种类均为全部
	@Query(nativeQuery = true, value = "select count(*) from B_GZApplyInfo where ApplyUserName like ?1 and (ApplyTime<=?3 and ApplyTime>=?2) and Status='已完成' order by ApplyTime desc")
	public int wGetSearchInfo11Total(String username, String start, String end);

	//Web-查询-仅部门为全部
	@Query(nativeQuery = true, value = "select count(*) from B_GZApplyInfo where GZKind=?1 and ApplyUserName like ?2 and (ApplyTime<=?4 and ApplyTime>=?3)  and Status='已完成' order by ApplyTime desc")
	public int wGetSearchInfo10Total(String gzkind, String username, String start, String end);

	//Web-查询-仅盖章种类为全部
	@Query(nativeQuery = true, value = "select count(*) from B_GZApplyInfo where Department=?1 and ApplyUserName like ?2 and (ApplyTime<=?4 and ApplyTime>=?3) and Status='已完成' order by ApplyTime desc")
	public int wGetSearchInfo01Total(String departname, String username, String start, String end);

	//Web-查询-都不为全部
	@Query(nativeQuery = true, value = "select count(*) from B_GZApplyInfo where where Department=?1 and GZKind = ?2 and ApplyUserName like ?3 and (ApplyTime<=?5 and ApplyTime>=?4) and Status='已完成' order by ApplyTime desc")
	public int wGetSearchInfo00Total(String departname, String gzkind, String username, String start,
			String end);
	
}
