package cn.com.hfga.manager.entertain;

import java.util.List;

import cn.com.hfga.dto.entertain.EntertainSearchInfoDTO;
import cn.com.hfga.entity.entertain.EntertainApplyInfoEntity;
import cn.com.hfga.entity.entertain.EntertainInfoEntity;
import cn.com.hfga.entity.entertain.EntertainRegisterInfoEntity;

public interface EntertainInfoManage {

	// 获得一条查询内容
	EntertainInfoEntity getSearchInfo1(EntertainSearchInfoDTO entertainSearchInfoDTO);

	// 获得事前查询内容一条
	EntertainApplyInfoEntity getSearchApplyInfo1(EntertainSearchInfoDTO entertainSearchInfoDTO);
		
	// 获得事后查询内容
	List<EntertainRegisterInfoEntity> getSearchRegisterInfo(String number);

	// 根据查询条件获得列表
	List<EntertainInfoEntity> getList(EntertainSearchInfoDTO entertainSearchInfoDTO);

	List<EntertainApplyInfoEntity> testList(EntertainSearchInfoDTO entertainSearchInfoDTO);

	List<EntertainApplyInfoEntity> wGetAllApproved(int start, int number);

	// //获得已完成的招待明细总数
	int getAllCompletedCount();

	int exportExcel(List<EntertainInfoEntity> entertainList, String path);

	//int export(String path);

	// web-个人中心-保存更改的密码
	int updatePassword(String password, String name);

	List<EntertainApplyInfoEntity> wRGetAllApproved(int start, int number);

	public List<EntertainApplyInfoEntity> wRGetAllEntertain(int start, int number);
	
	int export(String[] nlist, String path);

	// 通过 事前审批单号获得对应事后待审核信息
	List<EntertainRegisterInfoEntity> getReadyRegisterInfo(String number);

	// 获取事前通过，事后待审核的List
	List<EntertainInfoEntity> getAPassList();

	// web-按照年份查询相信息
	Object wGetSearchAnnual(String year);

	// web-获得选中年份每个部门的发生额
	Object wGetSelectedUsed(String year);
}
