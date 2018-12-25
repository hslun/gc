package cn.com.hfga.dao.car;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cn.com.hfga.dao.common.SpringDataDAO;
import cn.com.hfga.entity.car.InsuranceCarInfoEntity;


@Repository("insuranceCarInfoDAO")
public interface InsuranceCarInfoDAO extends SpringDataDAO<InsuranceCarInfoEntity>{

	
	@Query(nativeQuery=true,value="select * from B_Insurance where CarNum=:carnum")
	public List<InsuranceCarInfoEntity> getInfo(@Param(value="carnum")String carnum);
}
