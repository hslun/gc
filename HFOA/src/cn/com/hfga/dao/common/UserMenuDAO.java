package cn.com.hfga.dao.common;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cn.com.hfga.dao.common.SpringDataDAO;
import cn.com.hfga.entity.common.DictEntity;
import cn.com.hfga.entity.common.UserMenuEntity;

@Repository("userMenuDAO")
public interface UserMenuDAO extends SpringDataDAO<UserMenuEntity>{
	
	//����
	@Modifying
	@Query(nativeQuery=true,value="insert into b_usermenu (UserId,MenuId ) VALUES (:UserId,:MenuId )")
	public int save(@Param(value="UserId")String UserId,@Param(value="MenuId")int MenuId
	);
	
	//�����û�idȡ�˵�id list
	@Query(nativeQuery=true,value="select * from b_usermenu where userId=:userId")
	public List<UserMenuEntity> getByUserId(@Param(value="userId")String userId);
	
	//����
	@Modifying
	@Query(nativeQuery=true,value="delete from b_usermenu where UserId=:UserId")
	public int delete(@Param(value="UserId")String UserId);
}
