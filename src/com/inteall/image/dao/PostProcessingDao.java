package com.inteall.image.dao;

import java.util.List;

import com.inteall.image.pojo.PostProcessing;

/**
 * @author 李进刚
 * @date 2018年3月19日
 * @version 1.0
 * @parameter
 */

public interface PostProcessingDao {
  int deleteByPrimaryKey(String logid);
  
  int insert(PostProcessing record);
  
  int insertSelective(PostProcessing record);
  
  PostProcessing selectByPrimaryKey(String logid);
  
  int updateByPrimaryKeySelective(PostProcessing record);
  
  int updateByPrimaryKey(PostProcessing record);
  
  List<PostProcessing> selectByStuuid(String Stuuid);
  
  List<PostProcessing> selectBySrsuid(String Srsuid);
}
