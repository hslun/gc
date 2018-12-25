package cn.com.hfga.manageimpl.manhour;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.hfga.dao.manhour.ManHourDAO;
import cn.com.hfga.dto.manhour.ManHourDTO;
import cn.com.hfga.dto.manhour.ManhourSearchDTO;
import cn.com.hfga.entity.manhour.ManHourEntity;
import cn.com.hfga.manager.manhour.ManHourManage;

@Service("manHourManage")
public class ManHourManageImpl implements ManHourManage{

	@Autowired
	private ManHourDAO manHourDAO;
	@Transactional
	@Override
	public int insetOne(ManHourDTO manHourDTO) {
		// TODO Auto-generated method stub
		return manHourDAO.insertOne(manHourDTO.getRealName(), 
				manHourDTO.getDepartmentName(), 
				manHourDTO.getDepartmentId(),
				manHourDTO.getKdescrib(), 
				manHourDTO.getKind(),
				manHourDTO.getState(), 
				manHourDTO.getDay(), 
				manHourDTO.getFillTime(),
				manHourDTO.getDescribe());
	}
	@Override
	public List<ManHourEntity> isExist(String day) {
		// TODO Auto-generated method stub
		return manHourDAO.isExist(day);
	}
	@Override
	public List<ManHourEntity> get100(ManhourSearchDTO manhourSearchDTO) {
		// TODO Auto-generated method stub
		return manHourDAO.get100(manhourSearchDTO.getDepartment(), manhourSearchDTO.getName(), manhourSearchDTO.getStarttime(), manhourSearchDTO.getEndtime(), manhourSearchDTO.getKind());
	}
	@Override
	public List<ManHourEntity> get110(ManhourSearchDTO manhourSearchDTO) {
		// TODO Auto-generated method stub
		return manHourDAO.get110(manhourSearchDTO.getKind(), manhourSearchDTO.getName(), manhourSearchDTO.getStarttime(), manhourSearchDTO.getEndtime());
	}
	@Override
	public List<ManHourEntity> get101(ManhourSearchDTO manhourSearchDTO) {
		// TODO Auto-generated method stub
		return manHourDAO.get101(manhourSearchDTO.getName(), manhourSearchDTO.getStarttime(), manhourSearchDTO.getEndtime(), manhourSearchDTO.getDepartment());
	}
	@Override
	public List<ManHourEntity> get111(ManhourSearchDTO manhourSearchDTO) {
		// TODO Auto-generated method stub
		return manHourDAO.get111(manhourSearchDTO.getName(), manhourSearchDTO.getStarttime(), manhourSearchDTO.getEndtime());
	}
	@Override
	public List<ManHourEntity> get000(ManhourSearchDTO manhourSearchDTO) {
		// TODO Auto-generated method stub
		return manHourDAO.get000(manhourSearchDTO.getKind(), manhourSearchDTO.getDepartment(), manhourSearchDTO.getStarttime(), manhourSearchDTO.getEndtime());
	}
	@Override
	public List<ManHourEntity> get010(ManhourSearchDTO manhourSearchDTO) {
		// TODO Auto-generated method stub
		return manHourDAO.get010(manhourSearchDTO.getKind(), manhourSearchDTO.getStarttime(), manhourSearchDTO.getEndtime());
	}
	@Override
	public List<ManHourEntity> get001(ManhourSearchDTO manhourSearchDTO) {
		// TODO Auto-generated method stub
		return manHourDAO.get001(manhourSearchDTO.getDepartment(), manhourSearchDTO.getStarttime(), manhourSearchDTO.getEndtime());
	}
	@Override
	public List<ManHourEntity> get011(ManhourSearchDTO manhourSearchDTO) {
		// TODO Auto-generated method stub
		return manHourDAO.get011(manhourSearchDTO.getStarttime(), manhourSearchDTO.getEndtime());
	}
	@Override
	public List<ManHourEntity> getOne(String id) {
		// TODO Auto-generated method stub
		return manHourDAO.getOne(id);
	}

}
