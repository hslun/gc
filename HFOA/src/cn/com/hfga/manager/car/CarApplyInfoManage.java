package cn.com.hfga.manager.car;


import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cn.com.hfga.dto.car.ApproveDTO;
import cn.com.hfga.dto.car.CarApplyInfoDTO;
import cn.com.hfga.dto.car.CarApporveInfoDTO;
import cn.com.hfga.dto.car.CarCollectInfoDTO;
import cn.com.hfga.dto.car.CarKindDTO;
import cn.com.hfga.dto.car.CarUseDetailDTO;
import cn.com.hfga.dto.car.SearchResultDTO;
import cn.com.hfga.entity.car.CarApplyInfoEntity;

/**
 * @author xinyc
 * @since 2014-11-14
 * ����������Ϣ����ӿ�
 * */

public interface CarApplyInfoManage {
	
	public List<CarApplyInfoEntity> getInfo(String ApplyMan);
	
	public int getDepartmentId(String  department);
	
	public String CreateId(String department);
	
	public List<CarApplyInfoEntity> getInofGargeInfo(String state,String approvestate);
	
	public List<CarApplyInfoEntity> getOutofGargeInfo(String approvestate);
	
	public int  insertCarApplyInfo(CarApplyInfoDTO carApplyInfoDTO);
	
	public double countplantime(String startTime,String endTime);
	
	public int ApproveInfo(ApproveDTO approveDTO);
	
	//�賵����
	public int outgarage(String ApplyId,String begintime,String legnthbegin);
	
	//�������
	public int ingarage(String ApplyId,String endtime,String reallength,String realtime,String lengthend);
	
	//�賵������Ϣ
	
	public List<CarApplyInfoEntity> getOIInfo(String State);
	
	//���ص�ǰ���ڵ��ַ���
	public String getDateTime();
	
	//��ѯĳ���� ʹ����ϸ
	public List<CarApplyInfoEntity> getDetailInfo(CarUseDetailDTO carUseDetailDTO);
	
	//��ѯĳ�����ŵ�ʹ�����
	public  List<Object []> getCollectInfo1(CarCollectInfoDTO carCollectInfoDTO);
	
	//��ѯ������ȫ��  cartype ����ȫ�� 
	public List<Object []> getCollectInfo2(CarCollectInfoDTO carCollectInfoDTO);
	
	//��ѯ ����ȫ��    ���� �ǵ�������
	public  List<Object []> getCollectInfo3(CarCollectInfoDTO carCollectInfoDTO);
	
	
	//����ȫ��    ������ȫ��
	public List<Object []>  getColectInfo4(CarCollectInfoDTO carCollectInfoDTO);
	
	//��������
	public  List<CarApplyInfoEntity>  getApproveInfo1(CarApporveInfoDTO carApporveInfoDTO);
	
	//�����쵼����
	public List<CarApplyInfoEntity> getApproveInfo2(CarApporveInfoDTO carApporveInfoDTO);
	
	//��˾�쵼�����������ֵģ���ҵ����������ģ�
	public List<CarApplyInfoEntity> getApproveInfo3(CarApporveInfoDTO carApporveInfoDTO);
	
	//��ȡȫ��������Ϣ
	public List<CarApplyInfoEntity> getAllApply(String carid);
	
	//��ȡȫ��������Ϣ
		public List<CarApplyInfoEntity> getAllApplyDetail(String carnum);
	
	//��ȡ������Ϣ
	public List<CarApplyInfoEntity> getAllOrder(String carid);
	
	// ��ǰ������1
	public String addDay();
	
	//��ǰ������1
	public String delDay();
	
	//�������
	public  int denyApprove(ApproveDTO approveDTO);
	
	//������ʱ����
	//��ȡһ��ʵ����Ϣ
	
	public List<CarApplyInfoEntity> getOne(String applyid);
		
	
	//�޸����뵥
	public int  modifyInfo(CarApplyInfoDTO carApplyInfoDTO);
	
	
	//����ȫ�� ����ȫ��--����
    public List<CarApplyInfoEntity> getDetail1(CarUseDetailDTO carUseDetailDTO);
		
	//������ȫ��  ����ȫ��--����
	public List<CarApplyInfoEntity> getDetail2(CarUseDetailDTO carUseDetailDTO);
		
	//���Ų���ȫ��  ����ȫ��--����
	public List<CarApplyInfoEntity> getDetail3(CarUseDetailDTO carUseDetailDTO);
	
	
	//�ж����ʱ������Ƿ�������
	public  List<CarApplyInfoEntity> getExist(CarApplyInfoDTO carApplyInfoDTO);
	
	//���������¼������
	public int  getCount();
	
	//��ȡ����һ����¼
	public List<CarApplyInfoEntity> getTopOne();
	
	/**
	 * ��д����
	 */
	public List<CarApplyInfoEntity> getDetail00(CarUseDetailDTO carUseDetailDTO);
	
	public List<CarApplyInfoEntity> getDetail01(CarUseDetailDTO carUseDetailDTO);
	
	public List<CarApplyInfoEntity> getDetail10(CarUseDetailDTO carUseDetailDTO);
	
	public List<CarApplyInfoEntity> getDetail11(CarUseDetailDTO carUseDetailDTO);
	
	//��ȡȫ���賵������Ϣ
    public List<CarApplyInfoEntity> getAll();
    
    //���Ľ賵�� ȥ�����еĿո�
    public int modifyApplyMan(String applyman,String applyid);
    
    //accountlength
    public int modifyLength();
    
    //accountTime
    public int modifyTime();
    
    //ɾ��һ������
    public int delApply(String applyid);

    //Web-���ݲ��š������ˡ����ơ�����ʱ���ѯ
	public List<CarApplyInfoEntity> getSearchInfo(CarUseDetailDTO carUseDetailDTO);

	//Web-��ʾ���������Ϣ
	public List<CarApplyInfoEntity> carDisplay(int start,int number);

	//Web-��ù�����Ϣ��������
	public int getAllCount();

	List<CarKindDTO> getCarKind();
	
	List<CarApplyInfoEntity> getSearchInfoByPage(CarUseDetailDTO carUseDetailDTO,int start,int number);
	
	int getSearchInfoCount(CarUseDetailDTO carUseDetailDTO);

	int selectIfReturn(String applyman);
	
}
