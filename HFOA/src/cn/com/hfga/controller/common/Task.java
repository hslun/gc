package cn.com.hfga.controller.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.hfga.entity.car.CarApplyInfoEntity;
import cn.com.hfga.manager.car.CarApplyInfoManage;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;

/**
 * 定时任务类---查看数据库是否更新
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
		//判断是否有数据插入 如果有的话就进行推送
		countbefore=count;
		count=carApplyInfoManage.getCount();
		if(times==0)
		{
			
		}
		else {
			//有数据插入
			if (count>countbefore) {
				List<CarApplyInfoEntity> list=carApplyInfoManage.getTopOne();
				for (CarApplyInfoEntity carApplyInfoEntity:list)
				{
					if(!carApplyInfoEntity.getApproveState().equals("通过"))
					{
						//JpushMethod.tuiSong(carApplyInfoEntity.getApproveMan(), "您有一条新的审批信息");
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
