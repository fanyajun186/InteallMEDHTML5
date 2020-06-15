package com.inteall.image.service;

import java.util.HashMap;
import java.util.List;

import com.inteall.image.pojo.ZoomManage;
import com.inteall.image.pojo.ZoomManageWithBLOBs;

/**
 * @author 李进刚
 * @date 2018年2月27日 上午11:17:47
 * @version 1.0 
 * @parameter 
 */

public interface ZoomManageService {
  public int zoomcreate(ZoomManage zoomManage);
  
  public int zoomget(HashMap<String, String> getParamMap);
  
  public ZoomManageWithBLOBs getById(String zoom_key);
  
  public int updateById(ZoomManage zoomManage);
  
  public int updateStateById(HashMap<String, String> updateParamMap);
  
  public int zoomupdate(ZoomManage zoomManage);
  
  public int zoomdelete(ZoomManage zoomManage);
  
  public int zoomend(ZoomManage zoomManage);
  
  public List<ZoomManage> getByUserID(ZoomManage zoomManage);

  public ZoomManage getByReadimageKey(String id);

  public int delById(String zoomKey);

  public ZoomManage getByVideoKey(String id);

  public ZoomManage getByZoomKey(String caseId);

  public ZoomManage getZoomKeyByintervention(String interventionKey);

  public ZoomManage getByInterventionZoomKey(String interventionKey);
}
