package cn.com.hfga.controller.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.hfga.entity.car.CarApplyInfoEntity;
import cn.com.hfga.manager.car.CarApplyInfoManage;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;

/**
 * ��ʱ������---�鿴���ݿ��Ƿ����
 * @author xyc
 *
 */
public class Task {
	
	@Autowired
	private CarApplyInfoManage carApplyInfoManage;
	
	public int count=0;
	
	public int countbefore=0;
	
	public int times=0;
	
	public void run() throws APIConnectionException, APIRequestException
	{
		//�ж��Ƿ������ݲ��� ����еĻ��ͽ�������
		countbefore=count;
		count=carApplyInfoManage.getCount();
		if(times==0)
		{
			
		}
		else {
			//�����ݲ���
			if (count>countbefore) {
				List<CarApplyInfoEntity> list=carApplyInfoManage.getTopOne();
				for (CarApplyInfoEntity carApplyInfoEntity:list)
				{
					if(!carApplyInfoEntity.getApproveState().equals("ͨ��"))
					{
						//JpushMethod.tuiSong(carApplyInfoEntity.getApproveMan(), "����һ���µ�������Ϣ");
					}
					break;
				}
			}
		}
		times++;
		if (times>100) {
			times=1;
		}
		
	}

}
