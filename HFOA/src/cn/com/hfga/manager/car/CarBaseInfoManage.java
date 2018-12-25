package cn.com.hfga.manager.car;

import java.util.List;

import cn.com.hfga.dto.car.CarKindDTO;
import cn.com.hfga.entity.car.CarApplyInfoEntity;
import cn.com.hfga.entity.car.CarBaseInfoEntity;

/**
 * @author xinyc
 * @since 2014-11-17
 * 锟斤拷锟斤拷锟斤拷锟斤拷息锟斤拷锟斤拷涌锟�
 * */
public interface CarBaseInfoManage {
	
	public List<CarBaseInfoEntity> getInfo();
	
	public int modifyLen(String carnum,String cardistance);
	
	public int modifyState(String carstate,String carnum);
	
	public List<CarBaseInfoEntity> getBeginlength(String carnum);
	
	public List<CarBaseInfoEntity> getOneInfo(String carnum);
	
	public List<String> getCarNum();
	
	//Web-分页
	public List<CarBaseInfoEntity> carDisplay(int start,int number);
	
	//Web-条数
	public int getAllCount();
	
	public int updateBaseInfo(CarBaseInfoEntity car);
	
	public int insertBaseInfo(CarBaseInfoEntity car);
	
}
