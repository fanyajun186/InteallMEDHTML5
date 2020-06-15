package com.inteall.image.service;

import java.util.List;

import com.inteall.image.pojo.ReportWithBLOBs;

/**
 * @author 韩明君
 * @date 2018年3月5日 下午2:22:33
 * @version 1.0 
 * @parameter 
 */

public interface ReportService {

  public ReportWithBLOBs getById(String id);


  public List<ReportWithBLOBs> getAll(ReportWithBLOBs report);


  public int updateById(ReportWithBLOBs report);


  public int delById(String id);


  public int save(ReportWithBLOBs report);

  //查询电子签名路径
  public String selqmUrl(String baogaoName);


}
