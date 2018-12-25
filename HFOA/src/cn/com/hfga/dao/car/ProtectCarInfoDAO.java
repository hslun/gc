package cn.com.hfga.dao.car;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cn.com.hfga.dao.common.SpringDataDAO;
import cn.com.hfga.entity.car.ProtectCarInfoEntity;

@Repository("protectCarInfoDAO")
public interface ProtectCarInfoDAO extends SpringDataDAO<ProtectCarInfoEntity>{

	@Query(nativeQuery=true,value="select * from B_ProtectCar where CarNum=:carnum")
	public List<ProtectCarInfoEntity> getInfo(@Param(value="carnum")String carnum);
}
