package cn.com.hfga.manager.entertain;

import java.util.List;

import cn.com.hfga.dto.entertain.EntertainRegisterInfoDTO;
import cn.com.hfga.entity.entertain.EntertainRegisterInfoEntity;

public interface EntertainRegisterInfoManage {

	// 事后-保存登记
	int saveEntertainRegisterInfo(EntertainRegisterInfoDTO entertainRegisterInfoDTO);

	// 事后-获取所有的登记信息
	List<EntertainRegisterInfoEntity> getAllRegister();

	// 事后-获取所有待审核信息
	List<EntertainRegisterInfoEntity> getAllReady();

	// 事后-财务审核通过 （权限→刘静）
	int updateStatus(String Status, String applyId);

	// 事后-修改登记信息
	int modifyRegister(EntertainRegisterInfoDTO entertainRegisterInfoDTO);

	// 事后-获取个人申请信息
	List<EntertainRegisterInfoEntity> getPersonalRegister(String registerman);

	// web-根据审批单号查询相关信息
	List<EntertainRegisterInfoEntity> registerDetail(String number);

	// 事后-录入凭证号
	int insertVoucherNum(String iD, String paidTime, String voucherNum);

	// web-首页-未审核招待明细-分页显示-获取列表
	List<EntertainRegisterInfoEntity> wGetAllUnapprovedRegister(int start, int number);
	
	// web-首页-未审核招待明细-分页显示-获得总条数
	int getAllUnapprovedRegisterCount();

	// 事后-删除登记信息
	int deleteRegister(String iD,String invoiceSum);

	// 事后-财务审核未通过 
	int updateStatusUnapproved(String applyId);

	// 事后-根据Number改变事后表状态
	int updateStatusByN(String Status, String number);
	
	// 事后-录入凭证号、报销时间，审核通过-根据Number（权限→刘静）
	int insertAllVoucherNum(String number, String paidTime, String voucherNum);

	public int updateStatusByN1(String Status, String number);

	//获取一个实体
	List<EntertainRegisterInfoEntity> get(String number);
	

}
