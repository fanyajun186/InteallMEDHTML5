package com.inteall.image.service;

import com.inteall.image.pojo.WPACSSeries;

/**
 * @author 李进刚
 * @date 2018年3月21日
 * @version 1.0 
 * @parameter 
 */

public interface WPACSSeriesService {
  public int deleteByPrimaryKey(String srsuid);

  public int insert(WPACSSeries record);

  public int insertSelective(WPACSSeries record);

  public WPACSSeries selectByPrimaryKey(String srsuid);

  public int updateByPrimaryKeySelective(WPACSSeries record);

  public int updateByPrimaryKey(WPACSSeries record);
  
  public String getMaxSrsuidByStuuid(String stuuid);
  
  public int deleteByExcept(WPACSSeries record);
}
