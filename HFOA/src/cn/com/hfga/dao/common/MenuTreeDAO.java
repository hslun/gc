package cn.com.hfga.dao.common;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cn.com.hfga.dao.common.SpringDataDAO;
import cn.com.hfga.entity.common.DictEntity;
import cn.com.hfga.entity.menu.MenuEntity1;

@Repository("menuTreeDAO")
public interface MenuTreeDAO extends SpringDataDAO<MenuEntity1>{
	
	@Query(nativeQuery=true,value="select * from b_menu where parentId=:parent_id")
	public List<MenuEntity1> getMenuByNodeTypeReturnEn(@Param(value="parent_id")String parent_id);
	
}
