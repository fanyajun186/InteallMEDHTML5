package com.inteall.image.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.inteall.image.dao.ReportTypeDao;
import com.inteall.image.pojo.ReportType;
import com.inteall.image.service.ReportTypeService;

/**
 * @author 李进刚
 * @date 2018年2月27日 下午5:22:28
 * @version 1.0 
 * @parameter 
 */
@Service("reportTypeService")
public class ReportTypeImpl implements ReportTypeService {
  
  @Resource
  private ReportTypeDao reportTypeDao;
  @Override
  public ReportType getById(String id) {
	// TODO Auto-generated method stub
	return reportTypeDao.getById(id);
  }

  @Override
  public int delById(String id) {
	// TODO Auto-generated method stub
	return reportTypeDao.delById(id);
  }

  @Override
  public int updateById(ReportType reportType) {
	// TODO Auto-generated method stub
	return reportTypeDao.updateById(reportType);
  }

  @Override
  public int save(ReportType reportType) {
	// TODO Auto-generated method stub
	return reportTypeDao.save(reportType);
  }

  @Override
  public List<ReportType> getAll(HashMap<String, Object> queryMap) {
	// TODO Auto-generated method stub
	return reportTypeDao.getAll(queryMap);
  }

  @Override
  public int hasChildren(String clicked_id) {
    // TODO Auto-generated method stub
    return reportTypeDao.hasChildren(clicked_id);
  }
  
}
