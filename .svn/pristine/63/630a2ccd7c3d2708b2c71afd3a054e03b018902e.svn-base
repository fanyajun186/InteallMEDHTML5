package com.inteall.image.service;

import java.util.List;

import com.inteall.image.pojo.WPACSImages;

/**
 * @author 李进刚
 * @date 2018年3月21日
 * @version 1.0 
 * @parameter 
 */

public interface WPACSImagesService {
  public int deleteByPrimaryKey(String imguid);

  public int insert(WPACSImages record);

  public int insertSelective(WPACSImages record);

  public WPACSImages selectByPrimaryKey(String imguid);

  public int updateByPrimaryKeySelective(WPACSImages record);

  public int updateByPrimaryKey(WPACSImages record);
  
  public List<WPACSImages> selectBySrsUID(String srsuid);
  
  public int deleteByExcept(WPACSImages record);
}
