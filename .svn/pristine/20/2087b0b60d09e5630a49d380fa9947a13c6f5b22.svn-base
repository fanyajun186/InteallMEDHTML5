package com.inteall.image.dao;

import java.util.List;

import com.inteall.image.pojo.Report;
import com.inteall.image.pojo.ReportWithBLOBs;

/**
 * @author 韩明君
 * @date 2018年3月5日 下午4:28:24
 * @version 1.0 
 * @parameter 
 */

public interface ReportDao {

  int updateById(ReportWithBLOBs report);

  int save(ReportWithBLOBs report);

  int delById(String id);

  List<ReportWithBLOBs> getAll(Report report);

  ReportWithBLOBs getById(String id);

  //查询电子签名路径
  String selqmUrl(String baogaoName);

}
