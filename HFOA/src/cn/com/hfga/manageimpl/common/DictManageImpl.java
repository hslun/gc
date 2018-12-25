package cn.com.hfga.manageimpl.common;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.hfga.dao.common.DictDAO;
import cn.com.hfga.dao.common.MenuTreeDAO;
import cn.com.hfga.dao.common.UserMenuDAO;
import cn.com.hfga.entity.common.DictEntity;
import cn.com.hfga.entity.common.UserMenuEntity;
import cn.com.hfga.entity.menu.MenuEntity1;
import cn.com.hfga.manager.common.DictManage;

/**
 * 
 * @author ysy
 *
 */
@Service("dictManage")
public class DictManageImpl implements DictManage{


	@Autowired
	private DictDAO  dictDAO;
	@Autowired
	private UserMenuDAO userMenuDao;
	@Autowired
	private MenuTreeDAO menuDao;

	@Transactional
	@Override
	public int save(String text,String parentid,String info) {
		DictEntity dict = new DictEntity();
		String id = new SimpleDateFormat("yyyyMMddHHmmssSSS") .format(new Date() );
		dict.setId(id);
		dict.setText(text);
		dict.setInfo(info);
		dict.setParent_id(parentid);
		return dictDAO.save(dict.getId(), dict.getText(), dict.getInfo(), dict.getParent_id(), dict.getState());
	}

	@Override
	public List<DictEntity> getByNodeType(String nodeType) {
		List<DictEntity> list=dictDAO.getByNodeType(nodeType);
		//锟斤拷锟藉返锟截碉拷TypeDTO锟斤拷锟斤拷
		List<DictEntity> listDTO=new ArrayList<DictEntity>();
		for(int i=0;i<list.size();i++){
			DictEntity type=list.get(i);
			if(getChildsByParentId(type.getId()).size()>0){
				type.setState("closed");
			}else{
				type.setState("open");
			}
			listDTO.add(type);
		}
		return listDTO;
	}
	
	@Override
	public List<Map<String, Object>> getMenuByNodeType(String nodeType,String userId) {
		List<Object> list=dictDAO.getMenuByNodeType(nodeType);
		List<UserMenuEntity> byUserId = userMenuDao.getByUserId(userId);
		List<Object> arrayList = new ArrayList<>();
		for(UserMenuEntity en:byUserId){
			arrayList.add(en.getMenuId());
		}
		List<Map<String, Object>> listDTO=new ArrayList<Map<String, Object>>();
		for(Object row : list){
			List<Map<String, Object>> listDTO1=new ArrayList<Map<String, Object>>();
			Object[] cells = (Object[]) row;
			Map<String, Object> menuTree = new HashMap<>();
			List<Object> list1=getChildsMenuByParentId(cells[0].toString());
			if(list1.size()>0){
				for(Object row1 : list1){
					List<Map<String, Object>> listDTO2=new ArrayList<Map<String, Object>>();
					Object[] cells1 = (Object[]) row1;
					Map<String, Object> menuTree1 = new HashMap<>();
					List<Object> list2=getChildsMenuByParentId(cells1[0].toString());
					if(list2.size()>0){
						for(Object row2:list2){
							Object[] cells2 = (Object[]) row2;
							Map<String, Object> menuTree2 = new HashMap<>();
							menuTree2.put("id", cells2[0]);
							menuTree2.put("text", cells2[1]);
							if(arrayList.contains(cells2[0])){
								menuTree2.put("checked", true);
							}
							listDTO2.add(menuTree2);
						}
						menuTree1.put("children", listDTO2);
						menuTree1.put("state", "open");
					}
					menuTree1.put("id", cells1[0]);
					menuTree1.put("text", cells1[1]);
					listDTO1.add(menuTree1);
				}
				menuTree.put("children", listDTO1);
				menuTree.put("state", "open");
			}
			menuTree.put("id", cells[0]);
			menuTree.put("text", cells[1]);
//			menuTree.put("parent_id", type.get("parentId"));
			listDTO.add(menuTree);
		}
		//鎸塱d鎺掍釜搴�
		Collections.sort(listDTO, new Comparator<Map<String,Object>>(){  
            public int compare(Map<String,Object> o1, Map<String,Object> o2) {  
                if(Integer.valueOf(o1.get("id").toString()) > Integer.valueOf(o2.get("id").toString())){  
                    return 1;  
                }  
                if(Integer.valueOf(o1.get("id").toString()) == Integer.valueOf(o2.get("id").toString())){  
                    return 0;  
                }  
                return -1;  
            }  
        });   
		return listDTO;
	}

	public List<Map<String, Object>> getNode1(String nodeType, String userId, List<Map<String, Object>> menuList) {
		
//		List<MenuEntity1> list=menuDao.getMenuByNodeTypeReturnEn(nodeType);
//		for(MenuEntity1 en:list){
//			en.getId();
//		}
//		List<Map<String, Object>> Node = new ArrayList<>();
//		Node = courseDao.getOneCourse(id); //得到该节点课程
//		for(Course course:Node){
//			courseOfType.add(course);
//		}
//		List<Type> types=typeMapper.getChildByParentId(id);
//		//该节点就是叶子节点
//		if(types.size()==0){
//			return courseOfType;
//		}else{
//			//取出孩子
//			for(int i=0;i<types.size();i++){
//				
//				int cid=types.get(i).getId();//保存临时变量
//				
//				getNode1(cid,teacherid,courseOfType);
//			}
//		//System.out.println(courseOfType);
//		return courseOfType;
//	   }
		return null;
	}
	
	public List<Object> getChildsMenuByParentId(String id) {
		return dictDAO.getMenuByNodeType(id);
	}
	
	public List<DictEntity> getChildsByParentId(String id) {
		return dictDAO.getByNodeType(id);
	}

	@Transactional
	@Override
	public int delete(String id) {
		return dictDAO.delete(id);
	}

	@Transactional
	@Override
	public int update(DictEntity dict) {
		return dictDAO.update(dict.getId(), dict.getText(), dict.getInfo());
	}
	
}
