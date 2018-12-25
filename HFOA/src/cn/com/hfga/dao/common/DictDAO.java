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

@Repository("dictDAO")
public interface DictDAO extends SpringDataDAO<DictEntity>{
	
	//����
	@Modifying
	@Query(nativeQuery=true,value="insert into b_dict ( id,text,info,parent_id,state ) VALUES ( :id,:text,:info,:parent_id,:state )")
	public int save(
			@Param(value="id")String id,@Param(value="text")String text,@Param(value="info")String info,
			@Param(value="parent_id")String parent_id,@Param(value="state")String state
	);
	
	//���ݸ���ȡ��list
	@Query(nativeQuery=true,value="select * from b_dict where parent_id=:parent_id")
	public List<DictEntity> getByNodeType(@Param(value="parent_id")String parent_id);
	
	//���ݸ���ȡ��list�����в˵���
	@Query(nativeQuery=true,value="select * from b_menu where parentId=:parent_id")
	public List<Object> getMenuByNodeType(@Param(value="parent_id")String parent_id);
	
	@Query(nativeQuery=true,value="select * from b_menu where parentId=:parent_id")
	public List<MenuEntity1> getMenuByNodeTypeReturnEn(@Param(value="parent_id")String parent_id);
	
	//ɾ��
	@Modifying
	@Query(nativeQuery = true, value="delete from b_dict where id =?1")
	public int delete(String id);
	
	//����
	@Modifying
	@Query(nativeQuery=true,value="update b_dict set text=:text,info=:info where id=:id")
	public int update(@Param(value="id")String id,@Param(value="text")String text,@Param(value="info")String info);
	
}
