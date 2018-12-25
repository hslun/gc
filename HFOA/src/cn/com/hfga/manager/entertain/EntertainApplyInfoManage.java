package cn.com.hfga.manager.entertain;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.transaction.annotation.Transactional;

import cn.com.hfga.dto.entertain.EntertainApplyInfoDTO;
import cn.com.hfga.entity.entertain.EntertainApplyInfoEntity;
import cn.com.hfga.dto.entertain.EntertainSearchInfoDTO;

public interface EntertainApplyInfoManage {

	// 生成审批单号
	public String getNum(String num);

	// 保存申请
	public int saveEntertainApplyInfo(EntertainApplyInfoDTO entertainApplyInfoDTO,HttpServletRequest request) throws Exception;
	// 删除申请
	
	public int delete(String id);

	// 查询申请
	public List<EntertainApplyInfoEntity> getSearchInfo(EntertainSearchInfoDTO gzSearchInfoDTO);

	// 获取所有的申请信息
	public List<EntertainApplyInfoEntity> getAll();
	
	// 获取所有未提交的申请信息
	public List<EntertainApplyInfoEntity> getUnSubRecord(int start, int number);
	
	public int getUnSubRecordCount();

	// 获取需要审批的信息（根据不同审批人）
	public List<EntertainApplyInfoEntity> getByApprove(String approver);

	// 审批
	public int updateApprove(String Status, String Approver, String Number, String Time);

	// 获取个人申请信息
	public List<EntertainApplyInfoEntity> getPersonal(String Approver);
	
	//根据单号获取个人申请信息
	public List<EntertainApplyInfoEntity> get(String Number);

	//更改招待申请
	int modifyOne(EntertainApplyInfoDTO entertainApplyInfoDTO) throws Exception;

	// 事前-更改Status 
	int updateStatus(String Status, String ID);

	// web-根据审批单号查询相关信息
	EntertainApplyInfoEntity applyDetail(String number);
	
	// web-已审核招待明细-查询
	List<EntertainApplyInfoEntity> searchApply(int start, int number, EntertainSearchInfoDTO entertainSearchInfoDTO);

	// web-已审核招待明细-查询-获得符合查询条件的条目数
	int getAllCompletedApplyCount(EntertainSearchInfoDTO entertainSearchInfoDTO);

	// web-已审核招待明细-查询-获取列表信息
/*	List<EntertainApplyInfoEntity> wGetSearchApproved(int start, int number,
			EntertainSearchInfoDTO entertainSearchInfoDTO);*/
	
	// web-已审核招待明细-查询-获取总数
	int getAllSearchCount(EntertainSearchInfoDTO entertainSearchInfoDTO);

	List<EntertainApplyInfoEntity> wGetSearchApproved(EntertainSearchInfoDTO entertainSearchInfoDTO);

	// 获取事前审批数据库表中最后一条数据的审批单号 √
	String getSqlLast();

	// 审批不通过
	int updateStatusDeny(String string, String applyId, String time);

	
	
	}
