package com.inteall.image.service;

import java.util.List;

import com.inteall.image.pojo.ReportTemplateWithBLOBs;

/**
 * @author 李进刚
 * @date 2018年2月28日 下午5:46:37
 * @version 1.0 
 * @parameter 
 */

public interface ReportTemplateService {

  public List<ReportTemplateWithBLOBs> getAll(ReportTemplateWithBLOBs reportTemplate);

  public int save(ReportTemplateWithBLOBs reportTemplate);

  public int updateById(ReportTemplateWithBLOBs reportTemplate);

  public int delById(String id);

  public ReportTemplateWithBLOBs getById(String id);

}
