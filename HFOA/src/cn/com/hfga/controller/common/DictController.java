package cn.com.hfga.controller.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.hfga.entity.common.TravelEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.hfga.entity.common.DictEntity;
import cn.com.hfga.entity.common.WineEntity;
import cn.com.hfga.manager.common.DictManage;
import cn.com.hfga.util.Constants;

/**
 * @author ysy
 */
@Controller
public class DictController {

  @Autowired
  private DictManage dictManage;

  //淇濆瓨
  @RequestMapping(value = "/saveDict")
  @ResponseBody
  public Object saveDict(String text, String id, String info) {
    return dictManage.save(text, id, info);
  }

  //鏍规嵁鐖秈d鑾峰彇瀛恖ist
  @RequestMapping(value = "/getByNodeType")
  @ResponseBody
  public Object getByNodeType(String id) {
    String parentId;
    if ("".equals(id) || id == null) {
      parentId = "0";
    } else {
      parentId = id;
    }
    return dictManage.getByNodeType(parentId);
  }

  @RequestMapping(value = "/getMenuByNodeType")
  @ResponseBody
  public Object getMenuByNodeType(String id, String userId) {
    String parentId;
    if ("".equals(id) || id == null) {
      parentId = "100";
    } else {
      parentId = id;
    }
    return dictManage.getMenuByNodeType(parentId, userId);
  }

  //鍒犻櫎涓�鏉¤褰�
  @RequestMapping(value = "/deleteDict")
  @ResponseBody
  public Object deleteDict(String id) {
    List<DictEntity> list = dictManage.getByNodeType(id);
    if (list.size() > 0) {
      return 0;
    }
    return dictManage.delete(id);
  }

  //鏇存柊涓�鏉¤褰�
  @RequestMapping(value = "/updateDict")
  @ResponseBody
  public Object updateDict(DictEntity dict) {
    return dictManage.update(dict);
  }

  //鑾峰彇瀛楀吀鏁版嵁
  @RequestMapping(value = "/selectDict")
  @ResponseBody
  public Object getList(String type) {

    if ("local".equals(type)) {

      type = Constants.COMMON_LOCATIONS;

    } else if ("gz".equals(type)) {

      type = Constants.GZ_TYPE;

    } else if ("bt".equals(type)) {

      type = Constants.HDBTJG;

    } else if ("wine".equals(type)) {

      type = Constants.WINE_TYPE;

    }

    return dictManage.getByNodeType(type);

  }

  //鑾峰彇鎵�鏈夐厭
  @RequestMapping(value = "/selectWine")
  @ResponseBody
  public Object getWine() {
    List<DictEntity> dict = dictManage.getByNodeType(Constants.WINE_TYPE);
    List<WineEntity> ls = new ArrayList<WineEntity>();
    for (DictEntity d : dict) {
      WineEntity wine = new WineEntity();
      wine.setDict(d);
      wine.setListDict(dictManage.getByNodeType(d.getId()));
      ls.add(wine);
    }
    return ls;
  }

  //出行方式
  @RequestMapping(value = "/selectTravel")
  @ResponseBody
  public Object getTravel() {
    List<DictEntity> dict = dictManage.getByNodeType(Constants.TRAVEL_TYPE);
    List<TravelEntity> ls = new ArrayList<TravelEntity>();
    List<DictEntity> dictEntities = new ArrayList<DictEntity>();
    for(DictEntity d : dict) {
      TravelEntity travel = new TravelEntity();
      travel.setDict(d);
      if(dictManage.getByNodeType(d.getId()).size()>0)
        travel.setListDict(dictManage.getByNodeType(d.getId()));
      else
        travel.setListDict(dictEntities);
      ls.add(travel);
    }
    Map<String, Object> jsonMap = new HashMap<String, Object>();
    jsonMap.put("data", ls);
    return jsonMap;
  }
}
