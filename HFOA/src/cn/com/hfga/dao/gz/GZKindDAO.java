package cn.com.hfga.dao.gz;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cn.com.hfga.dao.common.SpringDataDAO;
import cn.com.hfga.entity.gz.GZKindEntity;

@Repository("gZKindDAO")
public interface GZKindDAO extends SpringDataDAO<GZKindEntity>{

	//Web-获取公章类型
	@Query(nativeQuery = true, value = "select * from B_GZKind")
	public List<GZKindEntity> getType();

}
