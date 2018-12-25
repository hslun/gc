package cn.com.hfga.dao.car;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cn.com.hfga.dao.common.SpringDataDAO;
import cn.com.hfga.entity.car.TrafficCarInfoEntity;
@Repository("trafficCarInfoDAO")
public interface TrafficCarInfoDAO extends SpringDataDAO<TrafficCarInfoEntity>{

	@Query(nativeQuery=true,value="select * from B_TroubleLogin where CarNum=:carnum order by LoginTime desc")
	public List<TrafficCarInfoEntity> getInfo(@Param(value="carnum")String carnum);
	
}
