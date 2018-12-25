package cn.com.hfga.manager.log;

import java.util.List;

import cn.com.hfga.entity.log.OperationRecord;

public interface OperateRecordManage {
	//获取最大id
	public int getMaxId();
	//保存
	public int insert(OperationRecord operationRecord);
	//获取列表
	public List<OperationRecord> logDisplay(int start,int number);
	//获取总记录数
	public int logAllCount();
	//删除记录
	public int deleteRecord(int id);
	//条件查询
	public List<OperationRecord> searchRecord(String realName,String startTime,String endTime);
	
	public List<OperationRecord> searchRecordByPage(String realName, String startTime, String endTime,int start, int number);
	
	public int getSearchInfoCount(String realName,String startTime,String endTime);
}
