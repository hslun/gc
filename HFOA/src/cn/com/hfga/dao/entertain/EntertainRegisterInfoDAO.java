package cn.com.hfga.dao.entertain;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.hfga.dao.common.SpringDataDAO;
import cn.com.hfga.entity.entertain.EntertainApplyInfoEntity;
import cn.com.hfga.entity.entertain.EntertainRegisterInfoEntity;

@Repository("entertainRegisterInfoDAO")
public interface EntertainRegisterInfoDAO extends SpringDataDAO<EntertainRegisterInfoEntity> {

	// �º�-����Ǽ�
	@Modifying
	@Query(nativeQuery = true, value = "insert into B_EntertainRegisterInfo(Number,InvoiceDate,InvoiceContent,InvoiceSum,InvoiceNum,InvoiceUnit,Status,RegisterMan,Remark,InvoiceNumber,WineSum,EnterSum,PersonSum)  VALUES(:Number,:InvoiceDate,:InvoiceContent,:InvoiceSum,:InvoiceNum,:InvoiceUnit,:Status,:RegisterMan,:Remark,:InvoiceNumber,:WineSum,:EnterSum,:PersonSum)")
	int insert(@Param(value = "Number") String number, @Param(value = "InvoiceDate") String invoiceDate,
			@Param(value = "InvoiceContent") String invoiceContent, @Param(value = "InvoiceSum") String invoiceSum,
			@Param(value = "InvoiceNum") String invoiceNum, 
			@Param(value = "InvoiceUnit") String invoiceUnit,@Param(value = "Status") String status, 
			@Param(value = "RegisterMan") String RegisterMan,@Param(value = "Remark") String remark,
			@Param(value = "InvoiceNumber") String invoiceNumber,
			@Param(value = "WineSum") String WineSum,
			@Param(value = "EnterSum") String EnterSum,
			@Param(value = "PersonSum") String PersonSum);

	// �º�-��ȡ���еĵǼ���Ϣ
	@Query(nativeQuery = true, value = "select * from B_EntertainRegisterInfo order by ID desc")
	public List<EntertainRegisterInfoEntity> getAllRegister();

	// �º�-��ȡ���д������Ϣ
	@Query(nativeQuery = true, value = "select * from B_EntertainRegisterInfo where Status!='ͨ��' order by ID desc")
	public List<EntertainRegisterInfoEntity> getAllReady();

	// �º�-����Status ��
	@Modifying
	@Query(nativeQuery = true, value = "update B_EntertainRegisterInfo set Status=:Status where Number=:Number")
	public int updateStatus(@Param(value = "Status") String Status, @Param(value = "Number") String number);
	// �º�-����Status ��
	@Modifying
	@Query(nativeQuery = true, value = "update B_EntertainRegisterInfo set Status=:Status where ID=:ID")
	public int updateStatusID(@Param(value = "Status") String Status, @Param(value = "ID") String ID);

	// �º�-�޸ĵǼ���Ϣ ��
	@Modifying
	@Query(nativeQuery = true, value = "update B_EntertainRegisterInfo set PersonSum=:PersonSum,WineSum=:WineSum,EnterSum=:EnterSum,Number=:Number,InvoiceDate=:InvoiceDate,InvoiceContent=:InvoiceContent,InvoiceSum=:InvoiceSum,InvoiceNum=:InvoiceNum,InvoiceUnit=:InvoiceUnit,Status=:Status,RegisterMan=:RegisterMan,Remark=:Remark,InvoiceNumber=:InvoiceNumber where ID=:ID")
	public int modifyRegister(@Param(value = "ID") int ID, @Param(value = "Number") String Number,
			@Param(value = "InvoiceDate") String InvoiceDate, @Param(value = "InvoiceContent") String InvoiceContent,
			@Param(value = "InvoiceSum") String InvoiceSum, @Param(value = "InvoiceNum") String InvoiceNum,
			@Param(value = "InvoiceUnit") String InvoiceUnit, @Param(value = "Status") String Status,
			@Param(value = "RegisterMan") String RegisterMan, @Param(value = "Remark") String Remark,
			@Param(value = "InvoiceNumber") String invoiceNumber,
			@Param(value = "WineSum") String WineSum,
			@Param(value = "EnterSum") String EnterSum,
			@Param(value = "PersonSum") String PersonSum);

	// ͨ����ǰ�������Ż�ö�Ӧ�º�����
	@Query(nativeQuery = true, value = "select * from B_EntertainRegisterInfo where Number=:Number and Status='ͨ��' order by PaidTime desc")
	public List<EntertainRegisterInfoEntity>  getSearchRegisterInfo(@Param(value = "Number") String Number);

	// �º�-��ȡ����������Ϣ ��
	@Query(nativeQuery = true, value = "select * from B_EntertainRegisterInfo where Status NOT IN ('�����' ,'ͨ��') and  RegisterMan=:RegisterMan  order by ID desc")
	List<EntertainRegisterInfoEntity> getPersonalRegister(@Param(value = "RegisterMan") String RegisterMan);
	
	//����ID����º����������ţ�Number��
	@Query(nativeQuery = true, value = "select Number from B_EntertainRegisterInfo where ID=:ID")
	String getNumber(@Param(value = "ID") String ID);

	// web-�����������Ų�ѯ�����Ϣ
	@Query(nativeQuery = true, value = "select * from B_EntertainRegisterInfo where Number=:Number")
	List<EntertainRegisterInfoEntity> registerDetail(@Param(value = "Number")String Number);

	// �º�-¼��ƾ֤��,ͨ����ˣ������� ��
	@Modifying
	@Query(nativeQuery = true, value = "update B_EntertainRegisterInfo set VoucherNum=?3, PaidTime=?2, Status='ͨ��' where ID=?1")
	int insertVoucherNum(String iD, String paidTime, String voucherNum);

	// web-��ҳ-δ����д���ϸ-��ҳ��ʾ-��ȡ�б�
	@Query(nativeQuery = true, value = "select * from B_EntertainRegisterInfo where Status='�����' limit ?1,?2 ")
	List<EntertainRegisterInfoEntity> wGetAllUnapprovedRegister(int start, int number);

	// web-��ҳ-δ����д���ϸ-��ҳ��ʾ-���������
	@Query(nativeQuery = true, value = "select count(*) from B_EntertainRegisterInfo where Status='�����'")
	int getAllUnapprovedRegisterCount();

	// �º�-ɾ���Ǽ���Ϣ
	@Modifying
	@Query(nativeQuery = true, value = "delete from B_EntertainRegisterInfo where ID=:ID")
	int deleteRegister(@Param(value = "ID")String ID);

	// �º�-����Number�ı��º��״̬
	@Modifying
	@Query(nativeQuery = true, value = "update B_EntertainRegisterInfo set Status=:Status where Number=:Number")
	int updateStatusByN(@Param(value = "Status")String status, @Param(value = "Number")String number);

	// �º�-¼��ƾ֤�š�����ʱ�䣬���ͨ��-����Number��Ȩ�ޡ�������
	@Modifying
	@Query(nativeQuery = true, value = "update B_EntertainRegisterInfo set VoucherNum=?3, PaidTime=?2, Status='ͨ��' where Number=?1")
	int insertVoucherNumByN(String number, String paidTime, String voucherNum);
	
	// ͨ����ǰ�������Ż�ö�Ӧ������º�����
	@Query(nativeQuery = true, value = "select * from B_EntertainRegisterInfo where Number=?1 and Status='�����' order by PaidTime desc")
	List<EntertainRegisterInfoEntity> getReadyRegisterInfo(String number);

	@Query(nativeQuery = true, value = "select * from B_EntertainRegisterInfo where Number=?1")
	public List<EntertainRegisterInfoEntity> get(String number);

}
