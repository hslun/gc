package cn.com.hfga.dao.car;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cn.com.hfga.dao.common.SpringDataDAO;
import cn.com.hfga.entity.car.PunishCarInfoEntity;


@Repository("punishCarInfoDAO")
public interface PunishCarInfoDAO extends SpringDataDAO<PunishCarInfoEntity> {

	@Query(nativeQuery=true,value="select * from B_CarPunish where CarCode=:carcode1 order by LoginTime desc")
	public List<PunishCarInfoEntity> getInfo(@Param(value="carcode1") String carcode1); 
	
	
	
}
