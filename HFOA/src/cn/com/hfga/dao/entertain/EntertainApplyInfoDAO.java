package cn.com.hfga.dao.entertain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cn.com.hfga.dao.common.SpringDataDAO;
import cn.com.hfga.dto.entertain.EntertainSearchInfoDTO;
import cn.com.hfga.dto.entertain.EntertainSumInfoDTO;
import cn.com.hfga.entity.entertain.EntertainApplyInfoEntity;

@Repository("entertainApplyInfoDAO")
public interface EntertainApplyInfoDAO extends SpringDataDAO<EntertainApplyInfoEntity> {

	// �������� ��
	@Modifying
	@Query(nativeQuery = true, value = "insert into B_EntertainApplyInfo(Number,Department,ApplyTime,EntertainObject,EntertainReason,EntertainNum,AccompanyNum,PerBudget,MasterBudget,RemainingBudget,EntertainCategory,Manager,Approver,Status,Remark,IfWine,WineType,WineNum,WineOther,IfBefore,BeforeDate)  VALUES(:Number,:Department,:ApplyTime,:EntertainObject,:EntertainReason,:EntertainNum,:AccompanyNum,:PerBudget,:MasterBudget,:MasterBudget,:EntertainCategory,:Manager,:Approver,:Status,:Remark,:IfWine,:WineType,:WineNum,:WineOther,:IfBefore,:BeforeDate)")
	public int insert(@Param(value = "Number") String Number, @Param(value = "Department") String Department,
			@Param(value = "ApplyTime") String ApplyTime, @Param(value = "EntertainObject") String EntertainObject,
			@Param(value = "EntertainReason") String EntertainReason,
			@Param(value = "EntertainNum") String EntertainNum, @Param(value = "AccompanyNum") String AccompanyNum,
			@Param(value = "PerBudget") String PerBudget, @Param(value = "MasterBudget") String MasterBudget,
			@Param(value = "EntertainCategory") String EntertainCategory, @Param(value = "Manager") String Manager,
			@Param(value = "Approver") String Approver, @Param(value = "Status") String Status,
			@Param(value = "Remark") String Remark,
			@Param(value = "IfWine") String IfWine,
			@Param(value = "WineType") String WineType,
			@Param(value = "WineNum") String WineNum,
			@Param(value = "WineOther") String WineOther,@Param(value = "IfBefore") String IfBefore,
			@Param(value = "BeforeDate") String BeforeDate);

	// ��ȡ���ݿ������һ�����ݵ��������� ��
	@Query(nativeQuery = true, value = "select Number from B_EntertainApplyInfo where ID = (select max(ID) from B_EntertainApplyInfo)")
	public String getSqlLast();

	
	
	// ɾ������ ��
	@Modifying
	@Query(nativeQuery = true, value = "delete from B_EntertainApplyInfo where ID=:ID")
	public int delete(@Param(value = "ID") String ID);

	// ��ȡ���е�������Ϣ ��
	@Query(nativeQuery = true, value = "select * from B_EntertainApplyInfo order by ApplyTime desc")
	public List<EntertainApplyInfoEntity> getAll();
	
	// ��ȡ���е�������Ϣ ��
	@Query(nativeQuery = true, value = "select * from B_EntertainApplyInfo where Status='ͨ��' and RegisterNum='0' order by ApplyTime desc limit ?1,?2")
	public List<EntertainApplyInfoEntity> getUnSubRecord(int start,int number);
	
	// ��ȡ���е�������Ϣcount ��
	@Query(nativeQuery = true, value = "select count(*) from B_EntertainApplyInfo where Status='ͨ��' and RegisterNum='0' order by ApplyTime desc ")
	public int getUnSubRecordCount();

	// ��ȡ���в����µĶ�Ӧ��Ϣ
	@Query(nativeQuery = true, value = "select * from B_EntertainApplyInfo,B_EntertainRegisterInfo where B_EntertainApplyInfo.Number = B_EntertainRegisterInfo.Number and InvoiceUnit like:invoiceunit and (ApplyTime<=:endTime and ApplyTime>=:startTime) and Manager like :manager  and B_EntertainRegisterInfo.Status='�����'  order by ApplyTime desc")
	public List<EntertainApplyInfoEntity> getSearchInfo1(@Param(value = "invoiceunit") String invoiceunit,
			@Param(value = "startTime") String startTime, @Param(value = "endTime") String endTime,
			@Param(value = "manager") String manager);

	// ��ȡĳһ�����µĶ�Ӧ��Ϣ
	@Query(nativeQuery = true, value = "select * from B_EntertainApplyInfo,B_EntertainRegisterInfo where B_EntertainApplyInfo.Number = B_EntertainRegisterInfo.Number and Department=:Department and InvoiceUnit like:invoiceunit and (ApplyTime<=:endTime and ApplyTime>=:startTime) and Manager like :manager and B_EntertainRegisterInfo.Status='�����' order by ApplyTime desc")
	public List<EntertainApplyInfoEntity> getSearchInfo0(@Param(value = "Department") String Department,
			@Param(value = "invoiceunit") String invoiceunit, @Param(value = "startTime") String startTime,
			@Param(value = "endTime") String endTime, @Param(value = "manager") String manager);
	
	// ��ȡ״̬Ϊͨ������ǰ��Ϣ
	@Query(nativeQuery = true, value = "select * from B_EntertainApplyInfo where Department like :department and ApplyTime<=:endTime and ApplyTime>=:startTime  and Manager like :manager and EntertainObject like :entertainObject and Status='�����' and RegisterNum !=0 order by ApplyTime desc")
	 public List<EntertainApplyInfoEntity> getApplyList(@Param(value = "department") String Department, 
			@Param(value = "startTime") String startTime, @Param(value = "endTime") String endTime,
			@Param(value = "manager") String manager,@Param(value = "entertainObject") String entertainObject);
	
	// ��ȡһ�������µĶ�Ӧ��Ϣ
	@Query(nativeQuery = true, value = "select * from B_EntertainApplyInfo,B_EntertainRegisterInfo where B_EntertainApplyInfo.Number = B_EntertainRegisterInfo.Number and entertainObject like:entertainObject and (ApplyTime<=:endTime and ApplyTime>=:startTime) and Manager like :manager  and B_EntertainRegisterInfo.Status='�����'  order by ApplyTime desc")
	public EntertainApplyInfoEntity getSearchInfo11(@Param(value = "entertainObject") String entertainObject,
			@Param(value = "startTime") String startTime, @Param(value = "endTime") String endTime,
			@Param(value = "manager") String manager);

	// ��ȡһ��ĳһ�����µĶ�Ӧ��Ϣ
	@Query(nativeQuery = true, value = "select * from B_EntertainApplyInfo,B_EntertainRegisterInfo where B_EntertainApplyInfo.Number = B_EntertainRegisterInfo.Number and Department=:Department and entertainObject like:entertainObject and (ApplyTime<=:endTime and ApplyTime>=:startTime) and Manager like :manager and B_EntertainRegisterInfo.Status='�����' order by ApplyTime desc")
	public EntertainApplyInfoEntity getSearchInfo00(@Param(value = "Department") String Department,
			@Param(value = "entertainObject") String entertainObject, @Param(value = "startTime") String startTime,
			@Param(value = "endTime") String endTime, @Param(value = "manager") String manager);
	
	// ��ȡ��Ҫ��������Ϣ�����ݲ�ͬ�����ˣ� ��
	@Query(nativeQuery = true, value = "select * from B_EntertainApplyInfo where Approver=:Approver and Status='������' order by ID desc")
	public List<EntertainApplyInfoEntity> getByApprove(@Param(value = "Approver") String Approver);

	// ���� ��
	@Modifying
	@Query(nativeQuery = true, value = "update B_EntertainApplyInfo set Status=:Status,Approver=:Approver,ApproveTime=:ApproveTime where Number=:Number ")
	public int updateApprove(@Param(value = "Status") String status, @Param(value = "Approver") String approver,
			@Param(value = "Number") String number, @Param(value = "ApproveTime") String approveTime);// ״̬�������ˣ���������
	
	@Modifying
	@Query(nativeQuery = true, value = "update B_EntertainApplyInfo set Status=:Status where Number=:Number ")
	public int updateStatus2(@Param(value = "Status") String status, @Param(value = "Number") String number);// ״̬�������ˣ���������
	
	// ��ȡ����������Ϣ ��
	@Query(nativeQuery = true, value = "select * from B_EntertainApplyInfo where Manager=:Manager and Status NOT IN ('�����') order by ApplyTime desc")
	public List<EntertainApplyInfoEntity> getPersonal(@Param(value = "Manager") String Manager);

	// �����д�����  ��
	@Modifying
	@Query(nativeQuery = true, value = "update B_EntertainApplyInfo set IfBefore=:IfBefore,BeforeDate=:BeforeDate, IfWine=:IfWine,WineType=:WineType,WineNum=:WineNum,WineOther=:WineOther,Number=:Number,Department=:Department,ApplyTime=:ApplyTime,EntertainObject=:EntertainObject,EntertainReason=:EntertainReason,EntertainNum=:EntertainNum,AccompanyNum=:AccompanyNum,PerBudget=:PerBudget,MasterBudget=:MasterBudget,RemainingBudget=:MasterBudget,EntertainCategory=:EntertainCategory,Manager=:Manager,Approver=:Approver,Status=:Status,Remark=:Remark where ID=:ID")
	public int modifyOne(@Param(value = "ID") int ID, @Param(value = "Number") String Number,
			@Param(value = "Department") String Department, @Param(value = "ApplyTime") String ApplyTime, 
			@Param(value = "EntertainObject") String EntertainObject, @Param(value = "EntertainReason") String EntertainReason,
			@Param(value = "EntertainNum") String EntertainNum, @Param(value = "AccompanyNum") String AccompanyNum,
			@Param(value = "PerBudget") String PerBudget, @Param(value = "MasterBudget") String MasterBudget,
			@Param(value = "EntertainCategory") String EntertainCategory, @Param(value = "Manager") String Manager,
			@Param(value = "Approver") String Approver, @Param(value = "Status") String Status,
			@Param(value = "Remark") String Remark,
			@Param(value = "IfWine") String IfWine,
			@Param(value = "WineType") String WineType,
			@Param(value = "WineNum") String WineNum,
			@Param(value = "WineOther") String WineOther,@Param(value = "IfBefore") String IfBefore,
			@Param(value = "BeforeDate") String BeforeDate);

	// ��ǰ-����Status ��
	@Modifying
	@Query(nativeQuery = true, value = "update B_EntertainApplyInfo set Status=:Status where ID=:ID")
	public int updateStatus(@Param(value = "Status")String Status, @Param(value = "ID") String ID);

	// ��ǰ-RegisterNum+1
	@Modifying
	@Query(nativeQuery = true, value = "update B_EntertainApplyInfo set RegisterNum=RegisterNum +1 where Number=:Number")
	public int plus(@Param(value = "Number") String Number);

	
	// Web-��ҳ-������д���ϸ-��ʾ
	@Query(nativeQuery = true, value = "select DISTINCT(app.ID),app.*,re.InvoiceNumber from B_EntertainApplyInfo as app LEFT JOIN b_entertainregisterinfo as re on app.Number = re.Number where app.Status='�����' and app.RegisterNum!=0 order by ApplyTime desc")
	public List<EntertainApplyInfoEntity> wGetApplyApproved();

	//ȡ���������Ŷ�Ӧ����ǰ���RegisterNumֵ
	@Query(nativeQuery = true, value = "select RegisterNum from B_EntertainApplyInfo where Number=:Number")
	public int getRegisterNum(@Param(value = "Number")String Number);

	//�����Ҫ�޸�״̬��ǰ���ID
	@Query(nativeQuery = true, value = "select ID from B_EntertainApplyInfo where Number=:Number")
	public String getID(@Param(value = "Number")String Number);
	
	//ȡ������ɵ���ǰlist����ҳ��
	@Query(nativeQuery = true, value = "select * from B_EntertainApplyInfo where Status='�����' order by ApproveTime desc limit ?1,?2")
	List<EntertainApplyInfoEntity> getApplyCompleted(int start, int number);

	//ȡ������ɵ���ǰlist
	@Query(nativeQuery = true, value = "select * from B_EntertainApplyInfo t1 LEFT JOIN b_entertainregisterinfo t2 ON t1.Number = t2.Number where t1.Status='�����'  AND t2.Status = 'ͨ��'")
	List<EntertainApplyInfoEntity> getApplyCompleted();
	
	//�������ɵ��д���ϸ����
	@Query(nativeQuery = true, value = "select count(*) from B_EntertainApplyInfo where Status='�����'")
	public int getAllCompletedCount();

	// web-�����������Ų�ѯ�����Ϣ
	@Query(nativeQuery = true, value = "select * from B_EntertainApplyInfo where Number=:Number")
	public EntertainApplyInfoEntity applyDetail(@Param(value = "Number")String Number);

	// web-������д���ϸ-��ѯ
	@Query(nativeQuery = true, value = "select * from B_EntertainApplyInfo where Number=:Number")
	public List<EntertainApplyInfoEntity> searchApply(int start, int number, String department, String manager,
			String startTime, String endTime, String entertainObject);

	// web-������д���ϸ-��ѯ-��÷��ϲ�ѯ��������Ŀ��
	@Query(nativeQuery = true, value = "select count(*) from B_EntertainApplyInfo where Number=:Number")
	public int getAllCompletedApplyCount(EntertainSearchInfoDTO entertainSearchInfoDTO);

	// web-����Number�ָ���ǰ���״̬
	@Modifying
	@Query(nativeQuery = true, value = "update B_EntertainApplyInfo set Status='ͨ��' where Number=:Number")
	public void updateStatusByN(@Param(value = "Number")String number);

	// web-����Number�ָ���ǰ���״̬
	@Modifying
	@Query(nativeQuery = true, value = "update B_EntertainApplyInfo set Status='�����' where Number=:Number")
	public void updateStatusByN1(@Param(value = "Number")String number);
	
	@Modifying
	@Query(nativeQuery = true, value = "update B_EntertainApplyInfo set Status='�����', ApproveTime=:ApproveTime where Number=:Number")
	public void updateStatusByN2(@Param(value = "Number")String number,@Param(value = "ApproveTime")String approveTime);
	
	// ��ǰ-RegisterNum- 1
	@Modifying
	@Query(nativeQuery = true, value = "update B_EntertainApplyInfo set RegisterNum=RegisterNum -1 where Number=:Number")
	public int minus(@Param(value = "Number") String Number);
	
	    // web-������д���ϸ-��ѯ-��ȡ�б���Ϣ11
		@Query(nativeQuery = true, value = "select * from B_EntertainApplyInfo where Manager like ?1 and (DATE_FORMAT(PaidTime,'%Y-%m-%d')<=?3 and DATE_FORMAT(PaidTime,'%Y-%m-%d')>=?2) and Status='�����'")
		public List<EntertainApplyInfoEntity> wGetSearchApproved11(String manager, String startTime,String endTime);
		
		// web-������д���ϸ-��ѯ-��ȡ�б���Ϣ01
		@Query(nativeQuery = true, value = "select * from B_EntertainApplyInfo where Department=?1 and Manager like ?2 and (DATE_FORMAT(PaidTime,'%Y-%m-%d')<=?4 and DATE_FORMAT(PaidTime,'%Y-%m-%d')>=?3) and Status='�����'")
		public List<EntertainApplyInfoEntity> wGetSearchApproved01(String department, String manager,
				String startTime, String endTime);
		
		// web-������д���ϸ-��ѯ-��ȡ�б���Ϣ10
		@Query(nativeQuery = true, value = "select * from B_EntertainApplyInfo where Manager like ?1 and (DATE_FORMAT(PaidTime,'%Y-%m-%d')<=?3 and DATE_FORMAT(PaidTime,'%Y-%m-%d')>=?2) and EntertainObject like ?4 and Status='�����'")
		public List<EntertainApplyInfoEntity> wGetSearchApproved10(String manager, String startTime,
				String endTime, String entertainObject);
		
		// web-������д���ϸ-��ѯ-��ȡ�б���Ϣ00
		@Query(nativeQuery = true, value = "select * from B_EntertainApplyInfo where Department=?1  and Manager like ?2 and (DATE_FORMAT(PaidTime,'%Y-%m-%d')<=?4 and DATE_FORMAT(PaidTime,'%Y-%m-%d')>=?3) and EntertainObject like ?5 and Status='�����'")
		public List<EntertainApplyInfoEntity> wGetSearchApproved00(String department, String manager,
				String startTime, String endTime, String entertainObject);
		
		//------------------------
		 // web-������д���ϸ-��ѯ-��ȡ�б���Ϣ11
		@Query(nativeQuery = true, value = "select * from B_EntertainApplyInfo where Manager like ?1 and (DATE_FORMAT(ApproveTime,'%Y-%m-%d')<=?3 and DATE_FORMAT(ApproveTime,'%Y-%m-%d')>=?2) and Status='�����' order by ApproveTime desc limit ?4,?5")
		public List<EntertainApplyInfoEntity> wGetSearchApproved11ByPage(String manager, String startTime,String endTime,int start,int end);
		
		// web-������д���ϸ-��ѯ-��ȡ�б���Ϣ01
		@Query(nativeQuery = true, value = "select * from B_EntertainApplyInfo where Department=?1 and Manager like ?2 and (DATE_FORMAT(ApproveTime,'%Y-%m-%d')<=?4 and DATE_FORMAT(ApproveTime,'%Y-%m-%d')>=?3) and Status='�����' order by ApproveTime desc limit ?5,?6")
		public List<EntertainApplyInfoEntity> wGetSearchApproved01ByPage(String department, String manager,
				String startTime, String endTime,int start,int end);
		
		// web-������д���ϸ-��ѯ-��ȡ�б���Ϣ10
		@Query(nativeQuery = true, value = "select * from B_EntertainApplyInfo where Manager like ?1 and (DATE_FORMAT(ApproveTime,'%Y-%m-%d')<=?3 and DATE_FORMAT(ApproveTime,'%Y-%m-%d')>=?2) and EntertainObject like ?4 and Status='�����' order by ApproveTime desc limit ?5,?6")
		public List<EntertainApplyInfoEntity> wGetSearchApproved10ByPage(String manager, String startTime,
				String endTime, String entertainObject,int start,int end);
		
		// web-������д���ϸ-��ѯ-��ȡ�б���Ϣ00
		@Query(nativeQuery = true, value = "select * from B_EntertainApplyInfo where Department=?1  and Manager like ?2 and (DATE_FORMAT(ApproveTime,'%Y-%m-%d')<=?4 and DATE_FORMAT(ApproveTime,'%Y-%m-%d')>=?3) and EntertainObject like ?5 and Status='�����' order by ApproveTime desc limit ?6,?7")
		public List<EntertainApplyInfoEntity> wGetSearchApproved00ByPage(String department, String manager,
				String startTime, String endTime, String entertainObject,int start,int end);
		//------------------------
		// web-������д���ϸ-��ѯ-��ȡ�б���Ϣ11
		@Query(nativeQuery = true, value = "select count(*) from B_EntertainApplyInfo where Manager like ?1 and (DATE_FORMAT(ApproveTime,'%Y-%m-%d')<=?3 and DATE_FORMAT(ApproveTime,'%Y-%m-%d')>=?2) and Status='�����'")
		public int wGetSearchApproved11Count(String manager, String startTime,String endTime);
		
		// web-������д���ϸ-��ѯ-��ȡ�б���Ϣ01
		@Query(nativeQuery = true, value = "select count(*) from B_EntertainApplyInfo where Department=?1 and Manager like ?2 and (DATE_FORMAT(ApproveTime,'%Y-%m-%d')<=?4 and DATE_FORMAT(ApproveTime,'%Y-%m-%d')>=?3) and Status='�����'")
		public int wGetSearchApproved01Count(String department, String manager,
				String startTime, String endTime);
		
		// web-������д���ϸ-��ѯ-��ȡ�б���Ϣ10
		@Query(nativeQuery = true, value = "select count(*) from B_EntertainApplyInfo where Manager like ?1 and (DATE_FORMAT(ApproveTime,'%Y-%m-%d')<=?3 and DATE_FORMAT(ApproveTime,'%Y-%m-%d')>=?2) and EntertainObject like ?4 and Status='�����'")
		public int wGetSearchApproved10Count(String manager, String startTime,
				String endTime, String entertainObject);
		
		// web-������д���ϸ-��ѯ-��ȡ�б���Ϣ00
		@Query(nativeQuery = true, value = "select count(*) from B_EntertainApplyInfo where Department=?1  and Manager like ?2 and (DATE_FORMAT(ApproveTime,'%Y-%m-%d')<=?4 and DATE_FORMAT(ApproveTime,'%Y-%m-%d')>=?3) and EntertainObject like ?5 and Status='�����'")
		public int wGetSearchApproved00Count(String department, String manager,
				String startTime, String endTime, String entertainObject);
		//---------------------------------

	// web-������д���ϸ-��ѯ-��ȡ����11
	@Query(nativeQuery = true, value = "select count(*) from B_EntertainApplyInfo where Manager like ?1 and (DATE_FORMAT(ApproveTime,'%Y-%m-%d')<=?3 and DATE_FORMAT(ApproveTime,'%Y-%m-%d')>=?2) and EntertainObject like ?4")
	public int getAllSearchCount11(String manager, String startTime,String endTime, String entertainObject);
	
	// web-������д���ϸ-��ѯ-��ȡ����01
	@Query(nativeQuery = true, value = "select count(*) from B_EntertainApplyInfo where Department=?1 and Manager like ?2 and (DATE_FORMAT(ApproveTime,'%Y-%m-%d')<=?4 and DATE_FORMAT(ApproveTime,'%Y-%m-%d')>=?3)")
	public int getAllSearchCount01(String department, String manager,String startTime, String endTime);
	
	// web-������д���ϸ-��ѯ-��ȡ����10
	@Query(nativeQuery = true, value = "select count(*) from B_EntertainApplyInfo where Manager like ?1 and (DATE_FORMAT(ApproveTime,'%Y-%m-%d')<=?3 and DATE_FORMAT(ApproveTime,'%Y-%m-%d')>=?2) and EntertainObject like ?4 ")
	public int getAllSearchCount10(String manager, String startTime,String endTime, String entertainObject);
	
	// web-������д���ϸ-��ѯ-��ȡ����00
	@Query(nativeQuery = true, value = "select count(*) from B_EntertainApplyInfo where Department=?1  and Manager like ?2 and (DATE_FORMAT(ApproveTime,'%Y-%m-%d')<=?4 and DATE_FORMAT(ApproveTime,'%Y-%m-%d')>=?3) and EntertainObject like ?5")
	public int getAllSearchCount00(String department, String manager,String startTime, String endTime, String entertainObject);

	// ������д���ϸ-��ѯ-��ȡ�б���Ϣ11
	@Query(nativeQuery = true, value = "select * from B_EntertainApplyInfo where Manager like ?1 and (DATE_FORMAT(ApproveTime,'%Y-%m-%d')<=?3 and DATE_FORMAT(ApproveTime,'%Y-%m-%d')>=?2) and Status='�����'")
	public List<EntertainApplyInfoEntity> getSearchApproved11(String manager, String startTime, String endTime);

	// ������д���ϸ-��ѯ-��ȡ�б���Ϣ01
	@Query(nativeQuery = true, value = "select * from B_EntertainApplyInfo where Department=?1 and Manager like ?2 and (DATE_FORMAT(ApproveTime,'%Y-%m-%d')<=?4 and DATE_FORMAT(ApproveTime,'%Y-%m-%d')>=?3) and Status='�����'")
	public List<EntertainApplyInfoEntity> getSearchApproved01(String department, String manager, String startTime,String endTime);
	
	// web-������д���ϸ-��ѯ-��ȡ�б���Ϣ10
	@Query(nativeQuery = true, value = "select * from B_EntertainApplyInfo where Manager like ?1 and (DATE_FORMAT(ApproveTime,'%Y-%m-%d')<=?3 and DATE_FORMAT(ApproveTime,'%Y-%m-%d')>=?2) and EntertainObject like ?4 and Status='�����'")
	public List<EntertainApplyInfoEntity> getSearchApproved10(String manager, String startTime, String endTime,String entertainObject);

	// web-������д���ϸ-��ѯ-��ȡ�б���Ϣ00
	@Query(nativeQuery = true, value = "select * from B_EntertainApplyInfo where Department=?1  and Manager like ?2 and (DATE_FORMAT(ApproveTime,'%Y-%m-%d')<=?4 and DATE_FORMAT(ApproveTime,'%Y-%m-%d')>=?3) and EntertainObject like ?5 and Status='�����'")
	public List<EntertainApplyInfoEntity> getSearchApproved00(String department, String manager, String startTime,String endTime, String entertainObject);

	// ��ǰ-��ȡ����ͨ������ǰ��Ϣ
	@Query(nativeQuery = true, value = "select * from B_EntertainApplyInfo where Status='ͨ��' and Manager=?1 order by ID desc")
	public List<EntertainApplyInfoEntity> getPassApply(String manager);

	// ������ʱ��д����ǰ���ݿ�
	@Modifying
	@Query(nativeQuery = true, value = "update B_EntertainApplyInfo set PaidTime=?2 where Number=?1")
	public void setPaidTime(String number,String paidTime);

	@Query(nativeQuery = true, value = "select DISTINCT(sq.ID),sq.* from B_EntertainApplyInfo as sq LEFT JOIN b_entertainregisterinfo as sh on sq.Number=sh.Number where sq.Status='ͨ��' and sq.RegisterNum!=0 and sh.Status='�����' order by sq.ApproveTime desc limit ?1,?2")
	public List<EntertainApplyInfoEntity> getUncompletedApply(int start, int number);
	
	@Query(nativeQuery = true, value = "select sq.* from B_EntertainApplyInfo as sq left join b_entertainregisterinfo as re on re.Number = sq.Number where re.Status='δͨ��' order by sq.ApproveTime desc limit ?1,?2")
	public List<EntertainApplyInfoEntity> getAllEntertain(int start, int number);
	
	@Query(nativeQuery = true, value = "select count(*) from B_EntertainApplyInfo as sq left join b_entertainregisterinfo as re on re.Number = sq.Number where re.Status='δͨ��' ")
	public int getAllEntertainCount();

	@Query(nativeQuery = true, value = "select count(DISTINCT(sq.ID)) from B_EntertainApplyInfo as sq LEFT JOIN b_entertainregisterinfo as sh on sq.Number=sh.Number where sq.Status='ͨ��' and sq.RegisterNum!=0 and sh.Status='�����'")
	public int getAllRCompletedCount();

	// ��ǰ-��ȡ����ͨ������ǰ��Ϣ
	@Query(nativeQuery = true, value = "select * from B_EntertainApplyInfo where Status='ͨ��' and RegisterNum!=0 order by ApplyTime desc")
	public List<EntertainApplyInfoEntity> getAllPassApply();

	// �����ǰ�������е�����
	@Query(nativeQuery = true, value = "select count(*) from B_EntertainApplyInfo")
	public int getApplyObject();

	//����Number��ȡ���ݿ��е�ʣ��Ԥ��ֵ
	@Query(nativeQuery = true, value = "select RemainingBudget from B_EntertainApplyInfo where Number=?1")
	public String getRemainingBudget(String number);

	//�����޸ĸ���ʣ��Ԥ��
	//TODO zy���޸Ĳ�������Ϊdouble����Ҫ�ж��Ƿ���Ӱ�� 
	@Modifying
	@Query(nativeQuery = true, value = "update B_EntertainApplyInfo set RemainingBudget=?2 where Number=?1")
	public void updateRemainingBudget(String number, double newRemainingBudget);
	
	
/*	// ��ȡ״̬Ϊ����ɵ���Ϣ
	@Query(nativeQuery = true, value = "select * from B_EntertainApplyInfo where Status='�����' order by ApplyTime desc")
	public List<EntertainApplyInfoEntity> getApplyList(EntertainSearchInfoDTO entertainSearchInfoDTO);
*/
	@Query(nativeQuery = true, value = "select t1.ID, t1.Number, Department, InvoiceSum from b_entertainapplyinfo t1,b_entertainregisterinfo t2 where t1.Number=t2.Number and t2.Status='ͨ��' and t2.PaidTime like ?1 ORDER BY t1.Department")
	public List<Object[]> getEntertainSum(String year);

	
	// ���ĳһ���ű������ʹ�þ�����Ϣ
	@Query(nativeQuery = true, value = "select t1.ID, t1.Number, Department, InvoiceSum from b_entertainapplyinfo t1,b_entertainregisterinfo t2 where t1.Number=t2.Number and InvoiceDate like ?2 and t1.Department=?1")
	public List<Object[]> getOneDepartmentEntertainSum(String department, String year);
	
	@Query(nativeQuery = true, value = "select * from B_EntertainApplyInfo where Number=?1")
	public List<EntertainApplyInfoEntity> get(String number);

	// ��ǰ-������� ��
	@Modifying
	@Query(nativeQuery = true, value = "update B_EntertainApplyInfo set Status=?1 , ApproveTime=?3 where ID=?2")
	public int updateStatus(String string, String applyId, String time);
	
}
