package com.hfoa.dao.privatecar;

import java.util.List;
import java.util.Map;

import com.hfoa.entity.privatecar.PrivateCarEntity;
import com.hfoa.entity.vacation.LearYear;


public interface PrivateMapper {
	
	int insertPrivateCar(PrivateCarEntity privateCarEntity);
	
	int updatePrivateCar(PrivateCarEntity privateCarEntity);
	
	int updateOpenId(PrivateCarEntity privateCarEntity);
	
	int updateApproveOpenId(PrivateCarEntity privateCarEntity);
	
	
	int deletePrivateCar(String applyId);
	
	PrivateCarEntity getPrivateCar(String applyId);

	List<Object> getMaxId(String department,String applyid);
	
	List<PrivateCarEntity> searPrivateCar(PrivateCarEntity privateCarEntity);
	
	List<Map<String,Object>> countPrivate(String applyman,String applyTime);

}