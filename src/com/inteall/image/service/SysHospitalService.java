package com.inteall.image.service;

import java.util.List;

import com.inteall.image.pojo.SysHospital;

/**
 * @author 李进刚
 * @date 2018年2月26日 下午3:27:54
 * @version 1.0 
 * @parameter 
 */

public interface SysHospitalService {

  SysHospital getById(String id);

  int updateById(SysHospital sysHospital);

  int save(SysHospital sysHospital);

  int delById(String id);

  List<SysHospital> getAll(SysHospital sysHospital);

  int getCount(SysHospital sysHospital);

  List<SysHospital> getHospital();


}
