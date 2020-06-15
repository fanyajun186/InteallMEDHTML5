package com.inteall.image.service;

import java.util.List;

import com.inteall.image.pojo.ReportTraceWithBLOBs;

/**
 * @author 韩明君
 * @date 2018年3月5日 下午5:15:34
 * @version 1.0 
 * @parameter 
 */

public interface ReportTraceService {

  ReportTraceWithBLOBs getById(String id);

  List<ReportTraceWithBLOBs> getAll(ReportTraceWithBLOBs reportTrace);

  int delById(String id);

  int save(ReportTraceWithBLOBs reportTrace);

  int updateById(ReportTraceWithBLOBs reportTrace);

}
