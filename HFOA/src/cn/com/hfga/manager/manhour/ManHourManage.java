package cn.com.hfga.manager.manhour;

import java.util.List;

import cn.com.hfga.dto.manhour.ManHourDTO;
import cn.com.hfga.dto.manhour.ManhourSearchDTO;
import cn.com.hfga.entity.manhour.ManHourEntity;

public interface ManHourManage {
	
	//����ʵ��
	public int insetOne(ManHourDTO manHourDTO);
	
	//�ж��Ƿ��Ѿ��ύ��
	public List<ManHourEntity> isExist(String day);
	
	/**
	 * ���˹�ʱ��ѯ
	 * 110 ����  
	 * �ʼ1 Ϊ���˲�ѯ 0Ϊ���ϲ�ѯ 
	 * �ڶ���1Ϊ������ȫ�� 0���Ų���ȫ�� 
	 * ������1������Ϊȫ�� 0���಻��ȫ��
	 */
	public List<ManHourEntity> get100(ManhourSearchDTO manhourSearchDTO);
	
	public List<ManHourEntity> get110(ManhourSearchDTO manhourSearchDTO);
	
	public List<ManHourEntity> get101(ManhourSearchDTO manhourSearchDTO);
	
	public List<ManHourEntity> get111(ManhourSearchDTO manhourSearchDTO);
	
	/**
	 * ȫ����ʱ��ѯ 
	 */
	public List<ManHourEntity> get000(ManhourSearchDTO manhourSearchDTO);
	
	public List<ManHourEntity> get010(ManhourSearchDTO manhourSearchDTO);
	
	public List<ManHourEntity> get001(ManhourSearchDTO manhourSearchDTO);
	
	public List<ManHourEntity> get011(ManhourSearchDTO manhourSearchDTO);
	
	/**
	 * �õ�һ����¼
	 */
	public List<ManHourEntity> getOne(String id);
}
