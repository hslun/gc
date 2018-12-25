package cn.com.hfga.manager.privatecar;

import java.util.List;
import java.util.Map;

import cn.com.hfga.dto.privatecar.ApproveDTO;
import cn.com.hfga.dto.privatecar.PrivateCarApplyDTO;
import cn.com.hfga.dto.privatecar.PrivateCarSearchDTO;
import cn.com.hfga.dto.privatecar.PrivateCarUseDetailDTO;
import cn.com.hfga.dto.privatecar.getApproveDTO;
import cn.com.hfga.entity.privatecar.PrivateCarApplyEntity;
import cn.com.hfga.entity.privatecar.PrivateCarEntity;

/**
 * @author xinyc
 * @since 2017-02-28
 * 私车公用申请接口
 * */

public interface PrivateCarApplyManage {
	
	
	//保存私车申请
	int Save(PrivateCarApplyDTO pto);
	
	
	//审批私车申请
	int Approve(ApproveDTO adt);
	
	int Deny(ApproveDTO adt);
	
	
	//确认私车申请
	int Sure(String applyid);
	
	//获取得到最大的Id
	public String CreateId(String department);
	
	
	//获取当天生成的时间用来生成ID
	public String getDateTime();
	
	
	//获取审批列表
	public List<PrivateCarApplyEntity> getApprove(getApproveDTO getDTO);
	
	
	//获取确认列表
	public List<PrivateCarApplyEntity> getSure(getApproveDTO getDTO);
	
	
	//获取个人借用信息
	public List<PrivateCarApplyEntity> getPersonal(String applyman);
	
	//本人主动申请
	public int  delPersonal(String applyid);
	
	//本人修改申请
	public int modifyEntity(PrivateCarApplyDTO pto);
	
	
	//获取一条申请信息
	public PrivateCarApplyEntity getOne(String applyid);
	/**
	 * 以下是使用明细查询
	 */
	
	public List<PrivateCarApplyEntity> getUseDetailInfo(PrivateCarUseDetailDTO pdt);
	
	
	
	/**
	 * 汇总分析用
	 * 
	 */
	
	public List<Map<String, Object>>  getCollectInfo(PrivateCarUseDetailDTO pdt);


	//已执行
	int setPerformed(String applyid);

    //未执行
	int setUnperformed(String applyid);

	//撤销
	int deleteApprove(String applyid);

    //个人获取已通过且待执行的列表
	List<PrivateCarApplyEntity> getPersonalReady(String applyman);

	//根据部门、姓名、开始时间、结束时间查询
	List<PrivateCarApplyEntity> getSearchInfo(PrivateCarSearchDTO privateCarSearchDTO);

	//个人获取待审批和被否决的列表
	List<PrivateCarApplyEntity> getUnapproved(String applyman);

	//Web-获取私车公用相关信息（已审核）
	List<PrivateCarApplyEntity> carDisplay(int start, int number);
	
	//Web-获取私车公用相关信息（已审核）
	List<PrivateCarApplyEntity> carDisplay1(int start, int number);

	//Web-获取私车公用相关信息（未审核）
	List<PrivateCarApplyEntity> uncarDisplay(int start, int number);
/*    //修改
	int modifyApprove(String applyid);*/
	
	int importPrivateCarExcel(String fileName);
	
	public List<PrivateCarApplyEntity> getApplyList(String username);
	//待报销
	public int updatePrivateCarIfPass(String applyid);
	//已报销
	public int updatePrivateCarIfPassed(String id);
	//已驳回
	public Object updatePrivateCarIfUnPass(String parentid, String applyid);
	//提交报销前更新私车信息
	public int update(PrivateCarApplyDTO pto);
	//提交报销前更新私车信息
	public int updatePrivateCarStatusBack(PrivateCarApplyDTO pto);
	//驳回多条私车报销申请
	public Object backPrivateCar(String applyid);
	//通过多条私车报销申请
	public Object passPrivateCar(String applyid,String applyids);
}
