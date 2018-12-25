package cn.com.hfga.manager.gz;

import java.util.List;
import cn.com.hfga.dto.gz.GZApplyInfoDTO;
import cn.com.hfga.dto.gz.GZSearchInfoDTO;
import cn.com.hfga.entity.gz.GZApplyInfoEntity;

public interface GZApplyInfoManage {

	public String CreateId(String department);

	public String getDateTime();

	public String getDepartmentId(String department);

	// ��������
	public int saveGZApplyInfo(GZApplyInfoDTO gzApplyInfoDTO);

	// ɾ�� һ������
	public int delete(String id);

	// ��ȡ��Ҫ��������
	public List<GZApplyInfoEntity> getNeedChuan();

	// ��Ҫ̷��ȷ��
	public List<GZApplyInfoEntity> getNeedTan();

	// ��ȡ��Ҫ��������Ϣ---����������
	public List<GZApplyInfoEntity> getByApprove(String approveMan);

	// ��ȡ���е�
	public List<GZApplyInfoEntity> getAll();

	// С���
	public List<GZApplyInfoEntity> getNeedYin();

	// ����
	public int updateApprove(String Status, String ApproveMan, String ID);

	// ȷ��
	public int updateSure(String Status, String ConfirmMan, String ID);

	// ���
	public int updateStatus(String Status, String ID);

	public int modifyOne(GZApplyInfoDTO gzApplyInfoDTO);

	// ��ȡ���˽�����Ϣ
	public List<GZApplyInfoEntity> getPersonal(String ApplyUserName);

	// ��ѯ����
	public List<GZApplyInfoEntity> getSearchInfo(GZSearchInfoDTO gzSearchInfoDTO);
	
	//��ȡһ��ʵ��
	public List<GZApplyInfoEntity> getOne(String ID);
	
	//Web-��ȡ���������Ϣ
	List<GZApplyInfoEntity> GZManageDisplay(int start, int number);

}
