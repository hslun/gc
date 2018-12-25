package cn.com.hfga.manager.log;

import java.util.List;

import cn.com.hfga.entity.log.OperationRecord;

public interface OperateRecordManage {
	//��ȡ���id
	public int getMaxId();
	//����
	public int insert(OperationRecord operationRecord);
	//��ȡ�б�
	public List<OperationRecord> logDisplay(int start,int number);
	//��ȡ�ܼ�¼��
	public int logAllCount();
	//ɾ����¼
	public int deleteRecord(int id);
	//������ѯ
	public List<OperationRecord> searchRecord(String realName,String startTime,String endTime);
	
	public List<OperationRecord> searchRecordByPage(String realName, String startTime, String endTime,int start, int number);
	
	public int getSearchInfoCount(String realName,String startTime,String endTime);
}
