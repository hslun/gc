package cn.com.hfga.manager.entertain;

import java.util.List;

import cn.com.hfga.entity.entertain.EntertainObjectTypeEntity;

public interface EntertainObjectTypeManage {

	// ��ȡ�д��ͻ�����
	public List<EntertainObjectTypeEntity> getType();

	// �洢�µ��д��ͻ�����
	public int saveType(String OName);
}
