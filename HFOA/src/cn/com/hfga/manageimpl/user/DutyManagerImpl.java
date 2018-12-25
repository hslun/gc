package cn.com.hfga.manageimpl.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.hfga.dao.user.DutyDAO;
import cn.com.hfga.entity.user.DutyEntity;
import cn.com.hfga.log.common.ILog;
import cn.com.hfga.manager.user.DutyManager;

@Service("dutyManager")
public class DutyManagerImpl implements DutyManager,ILog{
	

	@Autowired
	private DutyDAO dutyDAO;
	@Transactional
	@Override
	public List<DutyEntity> getDuty() {
		return dutyDAO.getDuty();
	}
	
	
}
