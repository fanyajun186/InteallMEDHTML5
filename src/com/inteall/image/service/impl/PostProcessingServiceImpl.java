package com.inteall.image.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.inteall.image.dao.PostProcessingDao;
import com.inteall.image.pojo.PostProcessing;
import com.inteall.image.service.PostProcessingService;

/**
 * @author 李进刚
 * @date 2018年3月19日
 * @version 1.0 
 * @parameter 
 */

@Service("PostProcessingService")
public class PostProcessingServiceImpl implements PostProcessingService {
  @Resource
  private PostProcessingDao PostProcessingDao;

  @Override
  public int deleteByPrimaryKey(String logid) {
    // TODO 自动生成的方法存根
    return PostProcessingDao.deleteByPrimaryKey(logid);
  }

  @Override
  public int insert(PostProcessing record) {
    // TODO 自动生成的方法存根
    return PostProcessingDao.insert(record);
  }

  @Override
  public int insertSelective(PostProcessing record) {
    // TODO 自动生成的方法存根
    return PostProcessingDao.insertSelective(record);
  }

  @Override
  public PostProcessing selectByPrimaryKey(String logid) {
    // TODO 自动生成的方法存根
    return PostProcessingDao.selectByPrimaryKey(logid);
  }

  @Override
  public int updateByPrimaryKeySelective(PostProcessing record) {
    // TODO 自动生成的方法存根
    return PostProcessingDao.updateByPrimaryKeySelective(record);
  }

  @Override
  public int updateByPrimaryKey(PostProcessing record) {
    // TODO 自动生成的方法存根
    return PostProcessingDao.updateByPrimaryKey(record);
  }

  @Override
  public List<PostProcessing> selectByStuuid(String Stuuid) {
    // TODO 自动生成的方法存根
    return PostProcessingDao.selectByStuuid(Stuuid);
  }

  @Override
  public List<PostProcessing> selectBySrsuid(String Srsuid) {
    // TODO 自动生成的方法存根
    return PostProcessingDao.selectBySrsuid(Srsuid);
  }
}
