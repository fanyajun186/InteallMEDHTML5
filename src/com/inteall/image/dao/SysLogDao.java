package com.inteall.image.dao;

import java.util.HashMap;
import java.util.List;

import com.inteall.image.pojo.SysLog;

/**
 * @author 韩明君
 * @date 2018年3月28日 上午11:55:30
 * @version 1.0 
 * @parameter 
 */

public interface SysLogDao {

  int getCount(HashMap<String, Object> queryMap);

  List<SysLog> getAll(HashMap<String, Object> queryMap);

  int save(SysLog sysLog);

}
