package cn.com.hfga.manager.entertain;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.transaction.annotation.Transactional;

import cn.com.hfga.dto.entertain.EntertainApplyInfoDTO;
import cn.com.hfga.entity.entertain.EntertainApplyInfoEntity;
import cn.com.hfga.dto.entertain.EntertainSearchInfoDTO;

public interface EntertainApplyInfoManage {

	// ������������
	public String getNum(String num);

	// ��������
	public int saveEntertainApplyInfo(EntertainApplyInfoDTO entertainApplyInfoDTO,HttpServletRequest request) throws Exception;
	// ɾ������
	
	public int delete(String id);

	// ��ѯ����
	public List<EntertainApplyInfoEntity> getSearchInfo(EntertainSearchInfoDTO gzSearchInfoDTO);

	// ��ȡ���е�������Ϣ
	public List<EntertainApplyInfoEntity> getAll();
	
	// ��ȡ����δ�ύ��������Ϣ
	public List<EntertainApplyInfoEntity> getUnSubRecord(int start, int number);
	
	public int getUnSubRecordCount();

	// ��ȡ��Ҫ��������Ϣ�����ݲ�ͬ�����ˣ�
	public List<EntertainApplyInfoEntity> getByApprove(String approver);

	// ����
	public int updateApprove(String Status, String Approver, String Number, String Time);

	// ��ȡ����������Ϣ
	public List<EntertainApplyInfoEntity> getPersonal(String Approver);
	
	//���ݵ��Ż�ȡ����������Ϣ
	public List<EntertainApplyInfoEntity> get(String Number);

	//�����д�����
	int modifyOne(EntertainApplyInfoDTO entertainApplyInfoDTO) throws Exception;

	// ��ǰ-����Status 
	int updateStatus(String Status, String ID);

	// web-�����������Ų�ѯ�����Ϣ
	EntertainApplyInfoEntity applyDetail(String number);
	
	// web-������д���ϸ-��ѯ
	List<EntertainApplyInfoEntity> searchApply(int start, int number, EntertainSearchInfoDTO entertainSearchInfoDTO);

	// web-������д���ϸ-��ѯ-��÷��ϲ�ѯ��������Ŀ��
	int getAllCompletedApplyCount(EntertainSearchInfoDTO entertainSearchInfoDTO);

	// web-������д���ϸ-��ѯ-��ȡ�б���Ϣ
/*	List<EntertainApplyInfoEntity> wGetSearchApproved(int start, int number,
			EntertainSearchInfoDTO entertainSearchInfoDTO);*/
	
	// web-������д���ϸ-��ѯ-��ȡ����
	int getAllSearchCount(EntertainSearchInfoDTO entertainSearchInfoDTO);

	List<EntertainApplyInfoEntity> wGetSearchApproved(EntertainSearchInfoDTO entertainSearchInfoDTO);

	// ��ȡ��ǰ�������ݿ�������һ�����ݵ��������� ��
	String getSqlLast();

	// ������ͨ��
	int updateStatusDeny(String string, String applyId, String time);

	
	
	}
