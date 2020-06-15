package com.inteall.image.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.inteall.image.dao.ZoomManageDao;
import com.inteall.image.pojo.ZoomManage;
import com.inteall.image.pojo.ZoomManageWithBLOBs;
import com.inteall.image.service.ZoomManageService;

/**
 * @author 李进刚
 * @date 2018年2月27日 上午11:18:08
 * @version 1.0 
 * @parameter 
 */

@Service("ZoomManageService")
public class ZoomManageServiceImpl implements ZoomManageService {
  @Resource
  private ZoomManageDao ZoomManageDao;
  
  @Override
  public int zoomcreate(ZoomManage zoomManage) {
	// TODO Auto-generated method stub
	return ZoomManageDao.zoomcreate(zoomManage);
  }

  @Override
  public int zoomget(HashMap<String, String> getParamMap) {
	// TODO 自动生成的方法存根
	return ZoomManageDao.zoomget(getParamMap);
  }

  @Override
  public ZoomManageWithBLOBs getById(String zoom_key) {
	// TODO 自动生成的方法存根
	return ZoomManageDao.getById(zoom_key);
  }
  
  @Override
  public int updateById(ZoomManage zoomManage) {
	// TODO 自动生成的方法存根
	return ZoomManageDao.updateById(zoomManage);
  }
  
  @Override
  public int updateStateById(HashMap<String, String> updateParamMap) {
	// TODO 自动生成的方法存根
	return ZoomManageDao.updateStateById(updateParamMap);
  }

  @Override
  public int zoomupdate(ZoomManage zoomManage) {
	// TODO Auto-generated method stub
	return ZoomManageDao.zoomupdate(zoomManage);
  }

  @Override
  public int zoomdelete(ZoomManage zoomManage) {
	// TODO Auto-generated method stub
	return ZoomManageDao.zoomdelete(zoomManage);
  }

  @Override
  public int zoomend(ZoomManage zoomManage) {
	// TODO 自动生成的方法存根
	return ZoomManageDao.zoomend(zoomManage);
  }

  @Override
  public List<ZoomManage> getByUserID(ZoomManage zoomManage) {
	// TODO 自动生成的方法存根
	return ZoomManageDao.getByUserID(zoomManage);
  }

  @Override
  public ZoomManage getByReadimageKey(String id) {
	// TODO Auto-generated method stub
	return ZoomManageDao.getByReadimageKey(id);
  }

  @Override
  public int delById(String zoomKey) {
	// TODO Auto-generated method stub
	return ZoomManageDao.delById(zoomKey);
  }

@Override
public ZoomManage getByVideoKey(String id) {
	// TODO Auto-generated method stub
	return ZoomManageDao.getByVideoKey(id);
}

@Override
public ZoomManage getByZoomKey(String caseId) {
	// TODO Auto-generated method stub
	return ZoomManageDao.getByZoomKey(caseId);
}

@Override
public ZoomManage getZoomKeyByintervention(String interventionKey) {
	
	return ZoomManageDao.getZoomKeyByintervention(interventionKey);
}

@Override
public ZoomManage getByInterventionZoomKey(String interventionKey) {
	
	return ZoomManageDao.getByInterventionZoomKey(interventionKey);
}
}
