package com.inteall.image.dao;

import java.util.HashMap;
import java.util.List;

import com.inteall.image.pojo.ZoomManage;
import com.inteall.image.pojo.ZoomManageWithBLOBs;

/**
 * @author 李进刚
 * @date 2018年2月27日 上午11:11:02
 * @version 1.0
 * @parameter
 */

public interface ZoomManageDao {
  int zoomcreate(ZoomManage zoomManage);
  
  int zoomget(HashMap<String, String> getParamMap);
  
  ZoomManageWithBLOBs getById(String zoom_key);
  
  int updateById(ZoomManage zoomManage);
  
  int updateStateById(HashMap<String, String> updateParamMap);
  
  int zoomupdate(ZoomManage zoomManage);
  
  int zoomdelete(ZoomManage zoomManage);
  
  int zoomend(ZoomManage zoomManage);
  
  List<ZoomManage> getByUserID(ZoomManage zoomManage);

  ZoomManage getByReadimageKey(String id);

  int delById(String zoomKey);

  ZoomManage getByVideoKey(String id);

  ZoomManage getByZoomKey(String caseId);

  ZoomManage getZoomKeyByintervention(String interventionKey);

ZoomManage getByInterventionZoomKey(String interventionKey);
  
}
