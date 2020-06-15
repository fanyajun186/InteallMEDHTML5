package com.inteall.image.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.inteall.image.dao.SysLogDao;
import com.inteall.image.pojo.SysLog;
import com.inteall.image.service.SysLogService;

/**
 * @author 韩明君
 * @date 2018年3月28日 上午11:46:27
 * @version 1.0 
 * @parameter 
 */
@Service("syslogService")
public class SysLogServiceImpl implements SysLogService{

  @Resource
  private SysLogDao sysLogDao;
  @Override
  public int getCount(HashMap<String, Object> queryMap) {
	// TODO Auto-generated method stub
	return sysLogDao.getCount(queryMap);
  }

  @Override
  public List<SysLog> getAll(HashMap<String, Object> queryMap) {
	// TODO Auto-generated method stub
	return sysLogDao.getAll(queryMap);
  }

  @Override
  public int save(SysLog sysLog) {
	// TODO Auto-generated method stub
	return sysLogDao.save(sysLog);
  }

}
