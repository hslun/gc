package cn.com.hfga.manager.common;

import java.util.List;
import java.util.Map;

import cn.com.hfga.entity.common.DictEntity;

/**
 * 
 * @author ysy
 *
 */

public interface DictManage {
	
	int save(String text,String parentid,String info);
	
	List<DictEntity> getByNodeType(String nodeType);
	
	int delete(String id);
	
	int update(DictEntity dict);
	
	List<Map<String,Object>> getMenuByNodeType(String nodeType,String userId);
	
}
