package cn.com.hfga.manageimpl.car;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.hfga.dao.car.CarBaseInfoDAO;
import cn.com.hfga.entity.car.CarBaseInfoEntity;
import cn.com.hfga.manager.car.CarBaseInfoManage;

/**
 * 
 * @author xinyc	
 * @since 2014-11-17
 * ��������Ϣ�ӿ�ʵ����
 */
@Service("carBaseInfoManage")
public class CarBaseInfoManageImpl implements  CarBaseInfoManage{

	@Autowired
	private CarBaseInfoDAO carBaseInfoDAO;
	
	
	@Override
	public List<CarBaseInfoEntity> getInfo() {
		// TODO Auto-generated method stub
		return carBaseInfoDAO.findAll();
	}
	
	@Transactional
	@Override
	public int modifyLen(String carnum,String cardistance) {
		// TODO Auto-generated method stub
		return carBaseInfoDAO.modifyLen(cardistance, carnum);
	}
	
    @Transactional
	@Override
	public int modifyState(String carstate, String carnum) {
		// TODO Auto-generated method stub
		return carBaseInfoDAO.modifyState(carstate, carnum);
	}

	@Override
	public List<CarBaseInfoEntity> getBeginlength(String carnum) {
		// TODO Auto-generated method stub
		return carBaseInfoDAO.getBeginLength(carnum);
	}

	@Override
	public List<CarBaseInfoEntity> getOneInfo(String carnum) {
		// TODO Auto-generated method stub
		return carBaseInfoDAO.getOneInfo(carnum);
	}

	@Override
	public List<String> getCarNum() {
		List<CarBaseInfoEntity> listCars=carBaseInfoDAO.findAll();
		List<String> listCarCodes=new ArrayList<String>();
		for(int i=0;i<listCars.size();i++){
			listCarCodes.add(listCars.get(i).getCarNum());
		}
		return listCarCodes;
	}

	@Override
	public List<CarBaseInfoEntity> carDisplay(int start, int number) {
		return carBaseInfoDAO.carDisplay(start, number);
	}

	@Override
	public int getAllCount() {
		return carBaseInfoDAO.getAllCount();
	}

	@Transactional
	@Override
	public int updateBaseInfo(CarBaseInfoEntity car) {
		return carBaseInfoDAO.updateBaseInfo(car.getCarCode(), car.getCarType(), car.getCarUnit(), 
				car.getCarDetailInfo(),
				String.valueOf(car.getCardVale()), String.valueOf(car.getCarDistance()), car.getCarInsuranceInfoDetal(),
				car.getCarState(), car.getCarNum(),car.getSuspendDay(),String.valueOf(car.getPeasonNum()), String.valueOf(car.getID()));
	}

	@Transactional
	@Override
	public int insertBaseInfo(CarBaseInfoEntity car) {
		int id = carBaseInfoDAO.getMaxId()+1;
		car.setID(id);
		return carBaseInfoDAO.insertBaseInfo(car.getCarCode(), car.getCarType(), car.getCarUnit(), car.getCarDetailInfo(), 
				String.valueOf(car.getCardVale()), String.valueOf(car.getCarDistance()), car.getCarInsuranceInfoDetal(), car.getCarState(), car.getCarNum(), 
				car.getSuspendDay(), String.valueOf(car.getPeasonNum()), String.valueOf(car.getID()));
	}

    
	

}
