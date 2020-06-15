package com.inteall.image.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.inteall.image.dao.WPACSImagesDao;
import com.inteall.image.pojo.WPACSImages;
import com.inteall.image.service.WPACSImagesService;

/**
 * @author 李进刚
 * @date 2018年3月21日
 * @version 1.0 
 * @parameter 
 */

@Service("WPACSImagesService")
public class WPACSImagesServiceImpl implements WPACSImagesService {
  @Resource
  private WPACSImagesDao WPACSImagesDao;
  
  @Override
  public int deleteByPrimaryKey(String imguid) {
    // TODO 自动生成的方法存根
    return WPACSImagesDao.deleteByPrimaryKey(imguid);
  }

  @Override
  public int insert(WPACSImages record) {
    // TODO 自动生成的方法存根
    return WPACSImagesDao.insert(record);
  }

  @Override
  public int insertSelective(WPACSImages record) {
    // TODO 自动生成的方法存根
    return WPACSImagesDao.insertSelective(record);
  }

  @Override
  public WPACSImages selectByPrimaryKey(String imguid) {
    // TODO 自动生成的方法存根
    return WPACSImagesDao.selectByPrimaryKey(imguid);
  }

  @Override
  public int updateByPrimaryKeySelective(WPACSImages record) {
    // TODO 自动生成的方法存根
    return WPACSImagesDao.updateByPrimaryKeySelective(record);
  }

  @Override
  public int updateByPrimaryKey(WPACSImages record) {
    // TODO 自动生成的方法存根
    return WPACSImagesDao.updateByPrimaryKey(record);
  }

  @Override
  public List<WPACSImages> selectBySrsUID(String srsuid) {
    // TODO 自动生成的方法存根
    return WPACSImagesDao.selectBySrsUID(srsuid);
  }

  @Override
  public int deleteByExcept(WPACSImages record) {
	// TODO Auto-generated method stub
	return WPACSImagesDao.deleteByExcept(record);
  }
}
