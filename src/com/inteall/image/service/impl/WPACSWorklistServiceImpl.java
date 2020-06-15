package com.inteall.image.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.inteall.image.dao.WPACSWorklistDao;
import com.inteall.image.pojo.WPACSWorklist;
import com.inteall.image.service.WPACSWorklistService;

/**
 * @author 李进刚
 * @date 2018年3月21日
 * @version 1.0 
 * @parameter 
 */

@Service("WPACSWorklistService")
public class WPACSWorklistServiceImpl implements WPACSWorklistService {
  @Resource
  private WPACSWorklistDao WPACSWorklistDao;
  
  @Override
  public int insert(WPACSWorklist record) {
    // TODO 自动生成的方法存根
    return WPACSWorklistDao.insert(record);
  }

  @Override
  public int insertSelective(WPACSWorklist record) {
    // TODO 自动生成的方法存根
    return WPACSWorklistDao.insertSelective(record);
  }
}
