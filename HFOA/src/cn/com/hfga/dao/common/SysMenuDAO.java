package cn.com.hfga.dao.common;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import cn.com.hfga.dao.common.SpringDataDAO;
import cn.com.hfga.entity.common.SysMenuEntity;

@Repository("sysMenuDAO")

public interface SysMenuDAO extends SpringDataDAO<SysMenuEntity>{
	
	//根据ID获取菜单
	@Query(nativeQuery = true, value = "select * from b_menu where id=:id ")
	public SysMenuEntity getById(@Param(value = "id") int id);
	
	//获取全部菜单
	@Query(nativeQuery = true, value = "select * from b_menu order by id asc ")
	public List<SysMenuEntity> getAll();
}
