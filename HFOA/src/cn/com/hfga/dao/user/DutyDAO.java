package cn.com.hfga.dao.user;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cn.com.hfga.dao.common.SpringDataDAO;
import cn.com.hfga.entity.user.DutyEntity;


@Repository("dutyDAO")

public interface DutyDAO extends SpringDataDAO<DutyEntity>{
	
	//Web-��ѯ/������/��¼��
	@Query(nativeQuery = true, value = " select * from duty ")
	public List<DutyEntity> getDuty();
}
