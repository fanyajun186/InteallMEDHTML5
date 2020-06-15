package com.inteall.image.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.inteall.image.dao.ReadImageDao;
import com.inteall.image.pojo.ReadImage;
import com.inteall.image.service.ReadImageService;

/**
 * @author 李进刚
 * @date 2018年3月7日 上午9:49:19
 * @version 1.0 
 * @parameter 
 */

@Service("ReadImageService")
public class ReadImageServiceImpl implements ReadImageService {
  @Resource
  private ReadImageDao ReadImageDao;

  @Override
  public int save(ReadImage readImage) {
	// TODO Auto-generated method stub
	return ReadImageDao.save(readImage);
  }

  @Override
  public int updateById(ReadImage readImage) {
	// TODO Auto-generated method stub
	return ReadImageDao.updateById(readImage);
  }

  @Override
  public int deleteById(ReadImage readImage) {
	// TODO Auto-generated method stub
	return ReadImageDao.deleteById(readImage);
  }

  @Override
  public ReadImage getById(ReadImage readImage) {
	// TODO Auto-generated method stub
	return ReadImageDao.getById(readImage);
  }

  @Override
  public List<ReadImage> getByCreater(ReadImage readImage) {
	// TODO Auto-generated method stub
	return ReadImageDao.getByCreater(readImage);
  }

  @Override
  public List<ReadImage> getByConferee(ReadImage readImage) {
	// TODO Auto-generated method stub
	return ReadImageDao.getByConferee(readImage);
  }
}
