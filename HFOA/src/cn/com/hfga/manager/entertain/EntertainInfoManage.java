package cn.com.hfga.manager.entertain;

import java.util.List;

import cn.com.hfga.dto.entertain.EntertainSearchInfoDTO;
import cn.com.hfga.entity.entertain.EntertainApplyInfoEntity;
import cn.com.hfga.entity.entertain.EntertainInfoEntity;
import cn.com.hfga.entity.entertain.EntertainRegisterInfoEntity;

public interface EntertainInfoManage {

	// ���һ����ѯ����
	EntertainInfoEntity getSearchInfo1(EntertainSearchInfoDTO entertainSearchInfoDTO);

	// �����ǰ��ѯ����һ��
	EntertainApplyInfoEntity getSearchApplyInfo1(EntertainSearchInfoDTO entertainSearchInfoDTO);
		
	// ����º��ѯ����
	List<EntertainRegisterInfoEntity> getSearchRegisterInfo(String number);

	// ���ݲ�ѯ��������б�
	List<EntertainInfoEntity> getList(EntertainSearchInfoDTO entertainSearchInfoDTO);

	List<EntertainApplyInfoEntity> testList(EntertainSearchInfoDTO entertainSearchInfoDTO);

	List<EntertainApplyInfoEntity> wGetAllApproved(int start, int number);

	// //�������ɵ��д���ϸ����
	int getAllCompletedCount();

	int exportExcel(List<EntertainInfoEntity> entertainList, String path);

	//int export(String path);

	// web-��������-������ĵ�����
	int updatePassword(String password, String name);

	List<EntertainApplyInfoEntity> wRGetAllApproved(int start, int number);

	public List<EntertainApplyInfoEntity> wRGetAllEntertain(int start, int number);
	
	int export(String[] nlist, String path);

	// ͨ�� ��ǰ�������Ż�ö�Ӧ�º�������Ϣ
	List<EntertainRegisterInfoEntity> getReadyRegisterInfo(String number);

	// ��ȡ��ǰͨ�����º����˵�List
	List<EntertainInfoEntity> getAPassList();

	// web-������ݲ�ѯ����Ϣ
	Object wGetSearchAnnual(String year);

	// web-���ѡ�����ÿ�����ŵķ�����
	Object wGetSelectedUsed(String year);
}
