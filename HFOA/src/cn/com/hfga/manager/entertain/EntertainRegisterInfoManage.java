package cn.com.hfga.manager.entertain;

import java.util.List;

import cn.com.hfga.dto.entertain.EntertainRegisterInfoDTO;
import cn.com.hfga.entity.entertain.EntertainRegisterInfoEntity;

public interface EntertainRegisterInfoManage {

	// �º�-����Ǽ�
	int saveEntertainRegisterInfo(EntertainRegisterInfoDTO entertainRegisterInfoDTO);

	// �º�-��ȡ���еĵǼ���Ϣ
	List<EntertainRegisterInfoEntity> getAllRegister();

	// �º�-��ȡ���д������Ϣ
	List<EntertainRegisterInfoEntity> getAllReady();

	// �º�-�������ͨ�� ��Ȩ�ޡ�������
	int updateStatus(String Status, String applyId);

	// �º�-�޸ĵǼ���Ϣ
	int modifyRegister(EntertainRegisterInfoDTO entertainRegisterInfoDTO);

	// �º�-��ȡ����������Ϣ
	List<EntertainRegisterInfoEntity> getPersonalRegister(String registerman);

	// web-�����������Ų�ѯ�����Ϣ
	List<EntertainRegisterInfoEntity> registerDetail(String number);

	// �º�-¼��ƾ֤��
	int insertVoucherNum(String iD, String paidTime, String voucherNum);

	// web-��ҳ-δ����д���ϸ-��ҳ��ʾ-��ȡ�б�
	List<EntertainRegisterInfoEntity> wGetAllUnapprovedRegister(int start, int number);
	
	// web-��ҳ-δ����д���ϸ-��ҳ��ʾ-���������
	int getAllUnapprovedRegisterCount();

	// �º�-ɾ���Ǽ���Ϣ
	int deleteRegister(String iD,String invoiceSum);

	// �º�-�������δͨ�� 
	int updateStatusUnapproved(String applyId);

	// �º�-����Number�ı��º��״̬
	int updateStatusByN(String Status, String number);
	
	// �º�-¼��ƾ֤�š�����ʱ�䣬���ͨ��-����Number��Ȩ�ޡ�������
	int insertAllVoucherNum(String number, String paidTime, String voucherNum);

	public int updateStatusByN1(String Status, String number);

	//��ȡһ��ʵ��
	List<EntertainRegisterInfoEntity> get(String number);
	

}
