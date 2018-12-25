package cn.com.hfga.dao.common;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

@JsonIgnoreType
public interface SpringDataDAO <T> extends JpaRepository<T, Serializable>, IDAO, JpaSpecificationExecutor<T>{

	@Modifying
	@Query("delete from  #{#entityName} t where t.id=:id ")
	public void doDeleteById(@Param("id") Long id);
	
}
