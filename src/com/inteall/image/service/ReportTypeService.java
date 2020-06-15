package com.inteall.image.service;

import java.util.HashMap;
import java.util.List;

import com.inteall.image.pojo.ReportType;

/**
 * @author 李进刚
 * @date 2018年2月27日 上午11:11:35
 * @version 1.0 
 * @parameter 
 */

public interface ReportTypeService {

  public ReportType getById(String id);

  public int delById(String id);

  public int updateById(ReportType reportType);

  public int save(ReportType reportType);

  public List<ReportType> getAll(HashMap<String, Object> queryMap);

  public int hasChildren(String clicked_id);

}
