package cn.com.hfga.dao.log;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import cn.com.hfga.dao.common.SpringDataDAO;
import cn.com.hfga.entity.log.OperationRecord;

@Repository("operateRecordDAO")
public interface OperateRecordDAO extends SpringDataDAO<OperationRecord> {
	
	//Web-��������ID
	@Query(nativeQuery = true, value = " select * from b_operaterecord where ID = (select max(ID) as ID from b_operaterecord) ")
	public List<OperationRecord> getMaxId();
	//Web-����
	@Modifying
	@Query(nativeQuery=true,value="insert into b_operaterecord (ID,OperateTime,RealName,OperateInfo,OperateDevice) VALUES(:ID,:OperateTime,:RealName,:OperateInfo,:OperateDevice)")
	public int insert(@Param(value="ID")String ID,@Param(value="OperateTime")String OperateTime,
			@Param(value="RealName")String RealName,@Param(value="OperateInfo")String OperateInfo,
			@Param(value="OperateDevice")String OperateDevice);

	//Web-��ȡ�б�
	@Query(nativeQuery = true, value = "select * from b_operaterecord order by ID desc limit ?1,?2")
	public List<OperationRecord> logDisplay(int start, int number);
	
	//Web-��ȡ�ܼ�¼��
	@Query(nativeQuery = true, value = "select count(*) from b_operaterecord ")
	public int logAllCount();
	
	//Web-ɾ����¼
	@Modifying
	@Query(nativeQuery = true, value = "delete from b_operaterecord where ID = ?1")
	public int deleteRecord(int id);
	
	//Web-������ѯ
	@Query(nativeQuery = true, value = "select * from b_operaterecord where RealName like:RealName and (OperateTime<=:endTime and OperateTime>=:startTime) order by ID desc")
	public List<OperationRecord> getSearchInfo(@Param(value = "RealName") String RealName,
			@Param(value = "endTime") String endTime, @Param(value = "startTime") String startTime);
	//Web-������ѯ
	@Query(nativeQuery = true, value = "select * from b_operaterecord where RealName like:RealName and (OperateTime<=:endTime and OperateTime>=:startTime) order by ID desc limit :start,:number")
	public List<OperationRecord> getSearchInfoByPage(@Param(value = "RealName") String RealName,
			@Param(value = "endTime") String endTime, @Param(value = "startTime") String startTime,
			@Param(value = "start") int start, @Param(value = "number") int number);
	//Web-������ѯ
	@Query(nativeQuery = true, value = "select count(*) from b_operaterecord where RealName like:RealName and (OperateTime<=:endTime and OperateTime>=:startTime) order by ID desc ")
	public int getSearchInfoCount(@Param(value = "RealName") String RealName,
			@Param(value = "endTime") String endTime, @Param(value = "startTime") String startTime);
}
