package cn.com.hfga.manager.gz;

import java.util.List;
import cn.com.hfga.dto.gz.GZApplyInfoDTO;
import cn.com.hfga.dto.gz.GZSearchInfoDTO;
import cn.com.hfga.entity.gz.GZApplyInfoEntity;

public interface GZApplyInfoManage {

	public String CreateId(String department);

	public String getDateTime();

	public String getDepartmentId(String department);

	// 保存申请
	public int saveGZApplyInfo(GZApplyInfoDTO gzApplyInfoDTO);

	// 删除 一个申请
	public int delete(String id);

	// 获取需要王川审批
	public List<GZApplyInfoEntity> getNeedChuan();

	// 需要谭总确认
	public List<GZApplyInfoEntity> getNeedTan();

	// 获取需要审批的信息---根据审批人
	public List<GZApplyInfoEntity> getByApprove(String approveMan);

	// 获取所有的
	public List<GZApplyInfoEntity> getAll();

	// 小殷姐
	public List<GZApplyInfoEntity> getNeedYin();

	// 审批
	public int updateApprove(String Status, String ApproveMan, String ID);

	// 确认
	public int updateSure(String Status, String ConfirmMan, String ID);

	// 完成
	public int updateStatus(String Status, String ID);

	public int modifyOne(GZApplyInfoDTO gzApplyInfoDTO);

	// 获取个人借用信息
	public List<GZApplyInfoEntity> getPersonal(String ApplyUserName);

	// 查询操作
	public List<GZApplyInfoEntity> getSearchInfo(GZSearchInfoDTO gzSearchInfoDTO);
	
	//获取一个实体
	public List<GZApplyInfoEntity> getOne(String ID);
	
	//Web-获取公章相关信息
	List<GZApplyInfoEntity> GZManageDisplay(int start, int number);

}
