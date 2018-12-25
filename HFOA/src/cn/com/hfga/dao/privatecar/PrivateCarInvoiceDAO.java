package cn.com.hfga.dao.privatecar;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cn.com.hfga.dao.common.SpringDataDAO;
import cn.com.hfga.entity.privatecar.PrivateCarApplyEntity;
import cn.com.hfga.entity.privatecar.PrivateCarInvoiceEntity;

@Repository("privateCarInvoiceDAO")
public interface PrivateCarInvoiceDAO extends SpringDataDAO<PrivateCarInvoiceEntity>{
	
	//����
	@Modifying
	@Query(nativeQuery=true,value="insert into b_privatecarinvoice ("
			+ "ApplyId,ApplyMan,"
			+ "ApproveMan,ApplyTime,Sum,SureLength,VoucherNum,Status,PaidTime,ApplyIds)"
			+ "VALUES(:ApplyId,:ApplyMan,"
			+ ":ApproveMan,:ApplyTime,:Sum,:SureLength,:VoucherNum,:Status,:PaidTime,:ApplyIds)")
	public int insertEntity(@Param(value="ApplyId")String ApplyId,
			@Param(value="ApplyMan")String ApplyMan,
			@Param(value="ApproveMan")String ApproveMan,
			@Param(value="ApplyTime")String ApplyTime,
			@Param(value="Sum")String Sum,
			@Param(value="SureLength")String SureLength,
			@Param(value="VoucherNum")String VoucherNum,
			@Param(value="Status")String Status,
			@Param(value="PaidTime")String PaidTime,
			@Param(value="ApplyIds")String ApplyIds
	);
	
	//�޸�����
	@Modifying
	@Query(nativeQuery=true,value="update b_privatecarinvoice "
			+ "set ApplyMan=:ApplyMan,"
			+ "ApproveMan=:ApproveMan,"
			+ "ApplyTime=:ApplyTime,"
			+ "Sum=:Sum,"
			+ "SureLength=:SureLength,"
			+ "VoucherNum=:VoucherNum,"
			+ "Status=:Status,"
			+ "PaidTime=:PaidTime,"
			+ "ApplyIds=:ApplyIds "
			+ "where ApplyId=:ApplyId")
	public int modifyEntity(@Param(value="ApplyId")String ApplyId,
			@Param(value="ApplyMan")String ApplyMan,
			@Param(value="ApproveMan")String ApproveMan,
			@Param(value="ApplyTime")String ApplyTime,
			@Param(value="Sum")String Sum,
			@Param(value="SureLength")String SureLength,
			@Param(value="VoucherNum")String VoucherNum,
			@Param(value="Status")String Status,
			@Param(value="PaidTime")String PaidTime,
			@Param(value="ApplyIds")String ApplyIds
	);
	@Modifying
	@Query(nativeQuery = true, value = "update b_privatecar set IfPass = 'δ����' where ApplyId =?1")
	public int updatePrivateCarIfPass(String applyid);
	
	@Query(nativeQuery = true, value = "select * from b_privatecarinvoice where Status = '�����'")
	public List<PrivateCarInvoiceEntity> selectAllUnPass();
	
	@Modifying
	@Query(nativeQuery = true, value = "update b_privatecarinvoice set Status = '�ѱ���' where ApplyId =?1")
	public int updateStatus(String applyid);
	
	@Query(nativeQuery = true, value = "select * from b_privatecarinvoice where Status = '�ѱ���'")
	public List<PrivateCarInvoiceEntity> selectAllPassed();
	
	//�޸�����
	@Modifying
	@Query(nativeQuery=true,value="update b_privatecarinvoice "
			+ "set Status='�����',ApproveMan=:ApproveMan,"
			+ "VoucherNum=:VoucherNum,"
			+ "PaidTime=:PaidTime "
			+ "where ApplyId=:ApplyId")
	public int modifyInvoice(@Param(value="ApplyId")String ApplyId,
			@Param(value="ApproveMan")String ApproveMan,
			@Param(value="VoucherNum")String VoucherNum,
			@Param(value="PaidTime")String PaidTime
	);
	
	@Query(nativeQuery = true, value="select * from b_privatecarinvoice where ApplyId =?1")
	public PrivateCarInvoiceEntity selectByApplyId(String applyid);
	
	@Query(nativeQuery = true, value="select * from b_privatecarinvoice where ApplyIds like ?1")
	public PrivateCarInvoiceEntity selectByApplyIds(String applyid);
	
	@Modifying
	@Query(nativeQuery = true, value="update b_privatecarinvoice set ApplyIds =?1 where ApplyId =?2")
	public int updateApplyIds(String applyids,String applyid);
	
	@Modifying
	@Query(nativeQuery = true, value="delete from b_privatecarinvoice where ApplyId =?1")
	public int deleteByApplyid(String applyid);
	
	@Query(nativeQuery = true, value="select * from b_privatecarinvoice where Status = '�����' order by ApplyId desc")
	public List<PrivateCarInvoiceEntity> selectPessed();
	
	//web��ȡ�������ύ������������
	@Query(nativeQuery = true, value = "select * from b_privatecarinvoice where Status = '�����' order by ApplyId desc limit ?1,?2")
	public List<PrivateCarInvoiceEntity> invoiceDisplay(int start, int number);
	
	//web��ȡ�������ύ����������������
	@Query(nativeQuery = true, value = "select count(*) from b_privatecarinvoice where Status = '�����'")
	public int getAllCount();
	
	//web��ȡ����δ�Ǽǻ���
	@Query(nativeQuery = true, value = "select * from b_privatecarinvoice where Status = '�ѱ���' order by ApplyId desc limit ?1,?2")
	public List<PrivateCarInvoiceEntity> UnSignInvoiceDisplay(int start, int number);
	
	//web��ȡ����δ�Ǽǻ�������
	@Query(nativeQuery = true, value = "select count(*) from b_privatecarinvoice where Status = '�ѱ���'")
	public int getAllUnSignInvoiceCount();
	
	//web��ȡ�������ύ������������
	@Query(nativeQuery = true, value = "select * from b_privatecarinvoice where Status='�����'  order by ApplyId desc limit ?1,?2")
	public List<PrivateCarInvoiceEntity> uninvoiceDisplay(int start, int number);
	
	//web��ȡ�������ύ����������������
	@Query(nativeQuery = true, value = "select count(*) from b_privatecarinvoice where Status='�����'")
	public int ungetAllCount();
	
	@Modifying
	@Query(nativeQuery = true, value = "update b_privatecarinvoice set Status = '�Ѳ���' where ApplyId =?1")
	public int updateStatusBack(String applyid);
	
	//�û��鿴�Ѳ���ƾ��
	@Query(nativeQuery = true, value = "select * from b_privatecarinvoice where Status = '�Ѳ���' and ApplyMan =:ApplyMan")
	public List<PrivateCarInvoiceEntity> selectBackList(@Param(value="ApplyMan")String ApplyMan);
	
	@Query(nativeQuery=true, value="select * from b_privatecarinvoice where Status = '�����' and ApplyIds like ?1")
	public PrivateCarInvoiceEntity getByNumber(String paramString);
	  
	@Modifying
	@Query(nativeQuery=true, value="delete from b_privatecarinvoice where ApplyIds like ?1")
	public abstract void deleteByApplyIds(String paramString);

	//������˵��Ż�ȡ���н�����������
	@Query(nativeQuery = true, value = "select count(*) from b_privatecarinvoice where VoucherNum like ?1")
	public int getCountByVoucher(String varnum);

	//�޸Ľ���
	@Modifying
	@Query(nativeQuery=true,value="update b_privatecarinvoice "
			+ "set ApplyMan=:ApplyMan,"
			+ "ApproveMan=:ApproveMan,"
			+ "ApplyTime=:ApplyTime,"
			+ "Sum=:Sum,"
			+ "SureLength=:SureLength,"
			+ "VoucherNum=:VoucherNum,"
			+ "Status=:Status,"
			+ "PaidTime=:PaidTime,"
			+ "ApplyIds=:ApplyIds "
			+ "where ApplyId=:ApplyId")
	public void updateEntity(@Param(value="ApplyId")String ApplyId,
			@Param(value="ApplyMan")String ApplyMan,
			@Param(value="ApproveMan")String ApproveMan,
			@Param(value="ApplyTime")String ApplyTime,
			@Param(value="Sum")String Sum,
			@Param(value="SureLength")String SureLength,
			@Param(value="VoucherNum")String VoucherNum,
			@Param(value="Status")String Status,
			@Param(value="PaidTime")String PaidTime,
			@Param(value="ApplyIds")String ApplyIds);

	@Query(nativeQuery=true, value="select * from b_privatecarinvoice where ApplyIds like ?1")
	public PrivateCarInvoiceEntity getByNumberes(String applyids);
}
