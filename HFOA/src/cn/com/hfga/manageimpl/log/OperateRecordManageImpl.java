package cn.com.hfga.manageimpl.log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.hfga.dao.log.OperateRecordDAO;
import cn.com.hfga.entity.log.OperationRecord;
import cn.com.hfga.manager.log.OperateRecordManage;

@Service("operateRecordManageImpl")
public class OperateRecordManageImpl implements OperateRecordManage {
	
	@Autowired
	private OperateRecordDAO operateRecordDAO;
	/**
	 * ��ȡid
	 */
	@Override
	public int getMaxId() {
		List<OperationRecord> list = operateRecordDAO.getMaxId();
		if(list.size()==0){
			return 1;
		}else{
			return list.get(0).getID()+1;
		}
	}
	/**
	 * ����
	 */
	@Transactional
	@Override
	public int insert(OperationRecord operationRecord) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String date = sdf.format(new Date());
		operationRecord.setOperateTime(date);
		operationRecord.setID(this.getMaxId());
		operationRecord.setOperateDevice("Web��¼");
		return 1;
//		return operateRecordDAO.insert(Integer.toString(operationRecord.getID()), operationRecord.getOperateTime(), operationRecord.getRealName(), operationRecord.getOperateInfo(), operationRecord.getOperateDevice());
	}
	/**
	 * ��ȡ�б�
	 */
	@Override
	public List<OperationRecord> logDisplay(int start, int number) {
		return operateRecordDAO.logDisplay(start, number);
	}
	/**
	 * ��ȡ�ܼ�¼��
	 */
	@Override
	public int logAllCount() {
		return operateRecordDAO.logAllCount();
	}
	/**
	 * ɾ��
	 */
	@Transactional
	@Override
	public int deleteRecord(int id) {
		return operateRecordDAO.deleteRecord(id);
	}
	/**
	 * ������ѯ
	 */
	@Override
	public List<OperationRecord> searchRecord(String realName, String startTime, String endTime) {
		String name = "%"+realName+"%";
		return operateRecordDAO.getSearchInfo(name, endTime, startTime);
	}
	@Override
	public List<OperationRecord> searchRecordByPage(String realName, String startTime, String endTime,int start, int number) {
		String name = "%"+realName+"%";
		return operateRecordDAO.getSearchInfoByPage(name, endTime, startTime, start, number);
	}
	@Override
	public int getSearchInfoCount(String realName, String startTime, String endTime) {
		String name = "%"+realName+"%";
		return operateRecordDAO.getSearchInfoCount(name, endTime, startTime);
	}
	
}
