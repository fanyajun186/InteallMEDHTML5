package com.inteall.image.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.inteall.image.dao.WPACSSeriesDao;
import com.inteall.image.pojo.WPACSSeries;
import com.inteall.image.service.WPACSSeriesService;

/**
 * @author 李进刚
 * @date 2018年3月21日
 * @version 1.0 
 * @parameter 
 */

@Service("WPACSSeriesService")
public class WPACSSeriesServiceImpl implements WPACSSeriesService{
  @Resource
  private WPACSSeriesDao WPACSSeriesDao;
  
  @Override
  public int deleteByPrimaryKey(String srsuid) {
    // TODO 自动生成的方法存根
    return WPACSSeriesDao.deleteByPrimaryKey(srsuid);
  }

  @Override
  public int insert(WPACSSeries record) {
    // TODO 自动生成的方法存根
    return WPACSSeriesDao.insert(record);
  }

  @Override
  public int insertSelective(WPACSSeries record) {
    // TODO 自动生成的方法存根
    return WPACSSeriesDao.insertSelective(record);
  }

  @Override
  public WPACSSeries selectByPrimaryKey(String srsuid) {
    // TODO 自动生成的方法存根
    return WPACSSeriesDao.selectByPrimaryKey(srsuid);
  }

  @Override
  public int updateByPrimaryKeySelective(WPACSSeries record) {
    // TODO 自动生成的方法存根
    return WPACSSeriesDao.updateByPrimaryKeySelective(record);
  }

  @Override
  public int updateByPrimaryKey(WPACSSeries record) {
    // TODO 自动生成的方法存根
    return WPACSSeriesDao.updateByPrimaryKey(record);
  }

  @Override
  public String getMaxSrsuidByStuuid(String stuuid) {
    // TODO 自动生成的方法存根
    return WPACSSeriesDao.getMaxSrsuidByStuuid(stuuid);
  }

  @Override
  public int deleteByExcept(WPACSSeries record) {
	// TODO Auto-generated method stub
	return WPACSSeriesDao.deleteByExcept(record);
  }
}
