package cn.com.hfga.dao.car;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cn.com.hfga.dao.common.SpringDataDAO;
import cn.com.hfga.entity.car.GasCarInfoEntity;
@Repository("gasCarInfoDAO")
public interface GasCarInfoDAO extends SpringDataDAO<GasCarInfoEntity>{

	@Query(nativeQuery=true,value="select * from B_CarGasInfo where  CarID=:carid ")
	public List<GasCarInfoEntity> getInfo(@Param(value="carid")  String carid);
	
}
