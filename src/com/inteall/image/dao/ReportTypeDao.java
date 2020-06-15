package com.inteall.image.dao;

import java.util.HashMap;
import java.util.List;

import com.inteall.image.pojo.ReportType;

/**
 * @author 李进刚
 * @date 2018年2月27日 下午5:24:30
 * @version 1.0 
 * @parameter 
 */

public interface ReportTypeDao {

  ReportType getById(String id);

  List<ReportType> getAll(HashMap<String, Object> queryMap);

  int save(ReportType reportType);

  int updateById(ReportType reportType);

  int delById(String id);

  int hasChildren(String clicked_id);

}
