package cn.com.hfga.manager.privatecar;

import java.util.List;
import java.util.Map;

import cn.com.hfga.dto.privatecar.ApproveDTO;
import cn.com.hfga.dto.privatecar.PrivateCarApplyDTO;
import cn.com.hfga.dto.privatecar.PrivateCarSearchDTO;
import cn.com.hfga.dto.privatecar.PrivateCarUseDetailDTO;
import cn.com.hfga.dto.privatecar.getApproveDTO;
import cn.com.hfga.entity.privatecar.PrivateCarApplyEntity;
import cn.com.hfga.entity.privatecar.PrivateCarEntity;

/**
 * @author xinyc
 * @since 2017-02-28
 * ˽����������ӿ�
 * */

public interface PrivateCarApplyManage {
	
	
	//����˽������
	int Save(PrivateCarApplyDTO pto);
	
	
	//����˽������
	int Approve(ApproveDTO adt);
	
	int Deny(ApproveDTO adt);
	
	
	//ȷ��˽������
	int Sure(String applyid);
	
	//��ȡ�õ�����Id
	public String CreateId(String department);
	
	
	//��ȡ�������ɵ�ʱ����������ID
	public String getDateTime();
	
	
	//��ȡ�����б�
	public List<PrivateCarApplyEntity> getApprove(getApproveDTO getDTO);
	
	
	//��ȡȷ���б�
	public List<PrivateCarApplyEntity> getSure(getApproveDTO getDTO);
	
	
	//��ȡ���˽�����Ϣ
	public List<PrivateCarApplyEntity> getPersonal(String applyman);
	
	//������������
	public int  delPersonal(String applyid);
	
	//�����޸�����
	public int modifyEntity(PrivateCarApplyDTO pto);
	
	
	//��ȡһ��������Ϣ
	public PrivateCarApplyEntity getOne(String applyid);
	/**
	 * ������ʹ����ϸ��ѯ
	 */
	
	public List<PrivateCarApplyEntity> getUseDetailInfo(PrivateCarUseDetailDTO pdt);
	
	
	
	/**
	 * ���ܷ�����
	 * 
	 */
	
	public List<Map<String, Object>>  getCollectInfo(PrivateCarUseDetailDTO pdt);


	//��ִ��
	int setPerformed(String applyid);

    //δִ��
	int setUnperformed(String applyid);

	//����
	int deleteApprove(String applyid);

    //���˻�ȡ��ͨ���Ҵ�ִ�е��б�
	List<PrivateCarApplyEntity> getPersonalReady(String applyman);

	//���ݲ��š���������ʼʱ�䡢����ʱ���ѯ
	List<PrivateCarApplyEntity> getSearchInfo(PrivateCarSearchDTO privateCarSearchDTO);

	//���˻�ȡ�������ͱ�������б�
	List<PrivateCarApplyEntity> getUnapproved(String applyman);

	//Web-��ȡ˽�����������Ϣ������ˣ�
	List<PrivateCarApplyEntity> carDisplay(int start, int number);
	
	//Web-��ȡ˽�����������Ϣ������ˣ�
	List<PrivateCarApplyEntity> carDisplay1(int start, int number);

	//Web-��ȡ˽�����������Ϣ��δ��ˣ�
	List<PrivateCarApplyEntity> uncarDisplay(int start, int number);
/*    //�޸�
	int modifyApprove(String applyid);*/
	
	int importPrivateCarExcel(String fileName);
	
	public List<PrivateCarApplyEntity> getApplyList(String username);
	//������
	public int updatePrivateCarIfPass(String applyid);
	//�ѱ���
	public int updatePrivateCarIfPassed(String id);
	//�Ѳ���
	public Object updatePrivateCarIfUnPass(String parentid, String applyid);
	//�ύ����ǰ����˽����Ϣ
	public int update(PrivateCarApplyDTO pto);
	//�ύ����ǰ����˽����Ϣ
	public int updatePrivateCarStatusBack(PrivateCarApplyDTO pto);
	//���ض���˽����������
	public Object backPrivateCar(String applyid);
	//ͨ������˽����������
	public Object passPrivateCar(String applyid,String applyids);
}
