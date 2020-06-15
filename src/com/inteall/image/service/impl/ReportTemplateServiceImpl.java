package com.inteall.image.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.inteall.image.dao.ReportTemplateDao;
import com.inteall.image.pojo.ReportTemplateWithBLOBs;
import com.inteall.image.service.ReportTemplateService;

/**
 * @author 韩明君
 * @date 2018年3月1日 上午9:50:41
 * @version 1.0 
 * @parameter 
 */
@Service("ReportTemplateService")
public class ReportTemplateServiceImpl implements ReportTemplateService {

  @Resource
  private ReportTemplateDao reportTemplateDao;


  @Override
  public int save(ReportTemplateWithBLOBs reportTemplate) {
	// TODO Auto-generated method stub
	return reportTemplateDao.save(reportTemplate);
  }

  @Override
  public int updateById(ReportTemplateWithBLOBs reportTemplate) {
	// TODO Auto-generated method stub
	return reportTemplateDao.updateById(reportTemplate);
  }

  @Override
  public List<ReportTemplateWithBLOBs> getAll(ReportTemplateWithBLOBs reportTemplate) {
	// TODO Auto-generated method stub
	return reportTemplateDao.getAll(reportTemplate);
  }

  @Override
  public int delById(String id) {
	// TODO Auto-generated method stub
	return reportTemplateDao.delById(id);
  }

  @Override
  public ReportTemplateWithBLOBs getById(String id) {
	// TODO Auto-generated method stub
	return reportTemplateDao.getById(id);
  }

}
