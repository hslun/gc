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

	// ��������ID
	@Query(nativeQuery = true, value = "select max(ID) as ID from B_GZApplyInfo where Department=:department and ID like:ID")
	public List<Object> getMaxId(@Param(value = "department") String department, @Param(value = "ID") String ID);

	// ��ȡ���е�������Ϣ
	@Query(nativeQuery = true, value = "select * from B_GZApplyInfo where Status = '�����' order by ApplyTime desc")
	public List<GZApplyInfoEntity> getAll();

	// ��ȡ��Ҫ��������Ϣ---����������
	@Query(nativeQuery = true, value = "select * from B_GZApplyInfo where ApproveMan=:approveMan and Status='������' order by ApplyTime desc")
	public List<GZApplyInfoEntity> getByApprove(@Param(value = "approveMan") String approveMan);

	// ��ȡ��ȷ�ϵ�
	/**
	 * �������̷�Ϊ��������ͨ��/�������ȷ�ϡ���ȷ�ϡ������ С�� ����Ǻ�ͬר���� -����ȷ�� �����¡���ȷ�� ���º�Ӫҵִ��-������Ҫ��ȡͨ��
	 */
	@Query(nativeQuery = true, value = "select * from B_GZApplyInfo where ((GZKind='���£���ͬ��Ȩί���飩' or  GZKind='������')and Status='��ȷ��') or ((GZKind='����' or GZKind='Ӫҵִ��' or GZKind='���£�������' or GZKind='��ͬר����') and Status='ͨ��') order by ApplyTime desc")
	public List<GZApplyInfoEntity> getNeedYin();

	// ����ר�� ��ͬ����Ҫ����ȷ��
	@Query(nativeQuery = true, value = "select * from B_GZApplyInfo where GZKind='���£���ͬ��Ȩί���飩' and Status='ͨ��' order by ApplyTime desc")
	public List<GZApplyInfoEntity> getNeedChuan();

	// ̷��ר�� ��������Ҫ̷��ȷ��
	@Query(nativeQuery = true, value = "select * from B_GZApplyInfo where GZKind='������' and Status='ͨ��' order by ApplyTime desc")
	public List<GZApplyInfoEntity> getNeedTan();

	// дһ���ܵĸ���GZKind��ApproveMan
	@Query(nativeQuery = true, value = "select * from B_GZApplyInfo where GZKind=:GZKind and Status=:Status  order by ApplyTime desc")
	public List<GZApplyInfoEntity> getNeeded(@Param(value = "GZKind") String GZKind,
			@Param(value = "Status") String Status);

	// ����״̬--����������
	@Modifying
	@Query(nativeQuery = true, value = "update B_GZApplyInfo set Status=:Status,ApproveMan=:ApproveMan where ID=:ID ")
	public int updateApprove(@Param(value = "Status") String Status, @Param(value = "ApproveMan") String ApproveMan,
			@Param(value = "ID") String ID);

	// ����״̬-����ȷ����
	@Modifying
	@Query(nativeQuery = true, value = "update B_GZApplyInfo set Status=:Status,ConfirmMan=:ConfirmMan where ID=:ID ")
	public int updateSure(@Param(value = "Status") String Status, @Param(value = "ConfirmMan") String ConfirmMan,
			@Param(value = "ID") String ID);

	// ����״̬--�����������
	@Modifying
	@Query(nativeQuery = true, value = "update B_GZApplyInfo set Status=:Status  where ID=:ID")
	public int updateStatus(@Param(value = "Status") String Status, @Param(value = "ID") String ID);

	// ����
	@Modifying
	@Query(nativeQuery = true, value = "insert into B_GZApplyInfo (ID,Department,ApplyUserName,Reason,ApplyTime,SendTo,GZKind,Copies,IsSecret,Status,ApproveMan) VALUES(:ID,:Department,:ApplyUserName,:Reason,:ApplyTime,:SendTo,:GZKind,:Copies,:IsSecret,:Status,:ApproveMan)")
	public int insert(@Param(value = "ID") String ID, @Param(value = "Department") String Department,
			@Param(value = "ApplyUserName") String ApplyUserName, @Param(value = "Reason") String Reason,
			@Param(value = "ApplyTime") String ApplyTime, @Param(value = "SendTo") String SendTo,
			@Param(value = "GZKind") String GZKind, @Param(value = "Copies") String Copies,
			@Param(value = "IsSecret") String IsSecret, @Param(value = "Status") String Status,
			@Param(value = "ApproveMan") String ApproveMan);

	// ��������
	@Modifying
	@Query(nativeQuery = true, value = "update B_GZApplyInfo set Department=:Department,ApplyUserName=:ApplyUserName,Reason=:Reason,ApplyTime=:ApplyTime,SendTo=:SendTo,GZKind=:GZKind,Copies=:Copies,IsSecret=:IsSecret,Status=:Status,ApproveMan=:ApproveMan where ID=:ID")
	public int modifyOne(@Param(value = "ID") String ID, @Param(value = "Department") String Department,
			@Param(value = "ApplyUserName") String ApplyUserName, @Param(value = "Reason") String Reason,
			@Param(value = "ApplyTime") String ApplyTime, @Param(value = "SendTo") String SendTo,
			@Param(value = "GZKind") String GZKind, @Param(value = "Copies") String Copies,
			@Param(value = "IsSecret") String IsSecret, @Param(value = "Status") String Status,
			@Param(value = "ApproveMan") String ApproveMan);

	// ɾ��һ������
	@Modifying
	@Query(nativeQuery = true, value = "delete from B_GZApplyInfo where ID=:ID")
	public int delete(@Param(value = "ID") String ID);

	// �������
	@Modifying
	@Query(nativeQuery = true, value = "update B_GZApplyInfo set Status=:Status where ID=:ID")
	public int denyApply(@Param(value = "Status") String Status, @Param(value = "ID") String ID);

	/*
	 * ���в�ѯ����1 ����ȫ�� 0�������
	 */
	// ��ѯ����--ȫ��������Ϣ�;���ӡ��
	@Query(nativeQuery = true, value = "select * from B_GZApplyInfo where ApplyUserName like:ApplyUserName and (ApplyTime<=:endTime and ApplyTime>=:startTime) and GZKind=:GZKind  and SendTo like :sendTo   and Status='�����'  order by ApplyTime desc")
	public List<GZApplyInfoEntity> getSearchInfo10(@Param(value = "ApplyUserName") String ApplyUserName,
			@Param(value = "startTime") String startTime, @Param(value = "endTime") String endTime,
			@Param(value = "GZKind") String GZKind, @Param(value = "sendTo") String sendTo);

	// ��ѯ���岿�� �;���ӡ��
	@Query(nativeQuery = true, value = "select * from B_GZApplyInfo where Department=:Department and ApplyUserName like:ApplyUserName and (ApplyTime<=:endTime and ApplyTime>=:startTime) and GZKind=:GZKind and SendTo like :sendTo   and Status='�����' order by ApplyTime desc")
	public List<GZApplyInfoEntity> getSearchInfo00(@Param(value = "Department") String Department,
			@Param(value = "ApplyUserName") String ApplyUserName, @Param(value = "startTime") String startTime,
			@Param(value = "endTime") String endTime, @Param(value = "GZKind") String GZKind,
			@Param(value = "sendTo") String sendTo);

	// ��ѯ���岿�ź�ȫ��ӡ��
	@Query(nativeQuery = true, value = "select * from B_GZApplyInfo where Department=:Department and ApplyUserName like:ApplyUserName and (ApplyTime<=:endTime and ApplyTime>=:startTime)  and SendTo like :sendTo   and Status='�����'  order by ApplyTime desc")
	public List<GZApplyInfoEntity> getSearchInfo01(@Param(value = "Department") String Department,
			@Param(value = "ApplyUserName") String ApplyUserName, @Param(value = "startTime") String startTime,
			@Param(value = "endTime") String endTime, @Param(value = "sendTo") String sendTo);

	// ��ѯȫ�����ź�ȫ��ӡ��
	@Query(nativeQuery = true, value = "select * from B_GZApplyInfo where ApplyUserName like:ApplyUserName and (ApplyTime<=:endTime and ApplyTime>=:startTime)  and SendTo like :sendTo and Status='�����' order by ApplyTime desc")
	public List<GZApplyInfoEntity> getSearchInfo11(@Param(value = "ApplyUserName") String ApplyUserName,
			@Param(value = "startTime") String startTime, @Param(value = "endTime") String endTime,
			@Param(value = "sendTo") String sendTo);

	// ��ȡ����������Ϣ
	@Query(nativeQuery = true, value = "select * from B_GZApplyInfo where ApplyUserName=:ApplyUserName and Status!='�����' order by ApplyTime desc")
	public List<GZApplyInfoEntity> getPersonal(@Param(value = "ApplyUserName") String ApplyUserName);
	
	
	//��ȡ����������Ϣ
	@Query(nativeQuery = true, value = "select * from B_GZApplyInfo where ID=:ID")
	public List<GZApplyInfoEntity> getOne(@Param(value = "ID") String ID);

	//Web-��ȡ���������Ϣ
	@Query(nativeQuery = true, value = "select * from B_GZApplyInfo where Status='�����' limit ?1,?2")
	public List<GZApplyInfoEntity> GZManageDisplay(int start, int number);

	//Web-��ȡ������Ϣ������
	@Query(nativeQuery = true, value = "select count(*) from B_GZApplyInfo where Status='�����'")
	public int getAllCount();

	//Web-��ѯ-���ź͸��������Ϊȫ��
	@Query(nativeQuery = true, value = "select * from B_GZApplyInfo where ApplyUserName like ?1 and (ApplyTime<=?3 and ApplyTime>=?2) and Status='�����' order by ApplyTime desc")
	public List<GZApplyInfoEntity> wGetSearchInfo11(String username, String start, String end);

	//Web-��ѯ-������Ϊȫ��
	@Query(nativeQuery = true, value = "select * from B_GZApplyInfo where GZKind=?1 and ApplyUserName like ?2 and (ApplyTime<=?4 and ApplyTime>=?3)  and Status='�����' order by ApplyTime desc")
	public List<GZApplyInfoEntity> wGetSearchInfo10(String gzkind, String username, String start, String end);

	//Web-��ѯ-����������Ϊȫ��
	@Query(nativeQuery = true, value = "select * from B_GZApplyInfo where Department=?1 and ApplyUserName like ?2 and (ApplyTime<=?4 and ApplyTime>=?3) and Status='�����' order by ApplyTime desc")
	public List<GZApplyInfoEntity> wGetSearchInfo01(String departname, String username, String start, String end);

	//Web-��ѯ-����Ϊȫ��
	@Query(nativeQuery = true, value = "select * from B_GZApplyInfo where where Department=?1 and GZKind = ?2 and ApplyUserName like ?3 and (ApplyTime<=?5 and ApplyTime>=?4) and Status='�����' order by ApplyTime desc")
	public List<GZApplyInfoEntity> wGetSearchInfo00(String departname, String gzkind, String username, String start,
			String end);

	//����ID��ù��������Ϣ
	@Query(nativeQuery=true,value="select * from B_GZApplyInfo where ID=?1 and Status='�����'")
	public GZApplyInfoEntity getGZById(String string);

	//Web-��ѯ-���ź͸��������Ϊȫ��
	@Query(nativeQuery = true, value = "select * from B_GZApplyInfo where ApplyUserName like ?1 and (ApplyTime<=?3 and ApplyTime>=?2) and Status='�����' order by ApplyTime desc limit ?4,?5")
	public List<GZApplyInfoEntity> wGetSearchInfo11ByPage(String username, String start, String end,int s, int e);

	//Web-��ѯ-������Ϊȫ��
	@Query(nativeQuery = true, value = "select * from B_GZApplyInfo where GZKind=?1 and ApplyUserName like ?2 and (ApplyTime<=?4 and ApplyTime>=?3)  and Status='�����' order by ApplyTime desc limit ?5,?6")
	public List<GZApplyInfoEntity> wGetSearchInfo10ByPage(String gzkind, String username, String start, String end,int s, int e);

	//Web-��ѯ-����������Ϊȫ��
	@Query(nativeQuery = true, value = "select * from B_GZApplyInfo where Department=?1 and ApplyUserName like ?2 and (ApplyTime<=?4 and ApplyTime>=?3) and Status='�����' order by ApplyTime desc limit ?5,?6")
	public List<GZApplyInfoEntity> wGetSearchInfo01ByPage(String departname, String username, String start, String end,int s, int e);

	//Web-��ѯ-����Ϊȫ��
	@Query(nativeQuery = true, value = "select * from B_GZApplyInfo where where Department=?1 and GZKind = ?2 and ApplyUserName like ?3 and (ApplyTime<=?5 and ApplyTime>=?4) and Status='�����' order by ApplyTime desc limit ?6,?7")
	public List<GZApplyInfoEntity> wGetSearchInfo00ByPage(String departname, String gzkind, String username, String start,
			String end,int s, int e);
	
	//Web-��ѯ-���ź͸��������Ϊȫ��
	@Query(nativeQuery = true, value = "select count(*) from B_GZApplyInfo where ApplyUserName like ?1 and (ApplyTime<=?3 and ApplyTime>=?2) and Status='�����' order by ApplyTime desc")
	public int wGetSearchInfo11Total(String username, String start, String end);

	//Web-��ѯ-������Ϊȫ��
	@Query(nativeQuery = true, value = "select count(*) from B_GZApplyInfo where GZKind=?1 and ApplyUserName like ?2 and (ApplyTime<=?4 and ApplyTime>=?3)  and Status='�����' order by ApplyTime desc")
	public int wGetSearchInfo10Total(String gzkind, String username, String start, String end);

	//Web-��ѯ-����������Ϊȫ��
	@Query(nativeQuery = true, value = "select count(*) from B_GZApplyInfo where Department=?1 and ApplyUserName like ?2 and (ApplyTime<=?4 and ApplyTime>=?3) and Status='�����' order by ApplyTime desc")
	public int wGetSearchInfo01Total(String departname, String username, String start, String end);

	//Web-��ѯ-����Ϊȫ��
	@Query(nativeQuery = true, value = "select count(*) from B_GZApplyInfo where where Department=?1 and GZKind = ?2 and ApplyUserName like ?3 and (ApplyTime<=?5 and ApplyTime>=?4) and Status='�����' order by ApplyTime desc")
	public int wGetSearchInfo00Total(String departname, String gzkind, String username, String start,
			String end);
	
}
