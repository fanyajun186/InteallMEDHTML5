package com.inteall.image.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.inteall.image.dao.ReadImage_ConfereeDao;
import com.inteall.image.pojo.ReadImage_Conferee;
import com.inteall.image.pojo.SysUser;
import com.inteall.image.service.ReadImage_ConfereeService;

/**
 * @author 李进刚
 * @date 2018年3月7日 下午2:29:34
 * @version 1.0 
 * @parameter 
 */

@Service("ReadImage_ConfereeService")
public class ReadImage_ConfereeServiceImpl implements ReadImage_ConfereeService {
  @Resource
  private ReadImage_ConfereeDao ReadImage_ConfereeDao;
  
  @Override
  public int save(ReadImage_Conferee readImage_Conferee) {
	// TODO Auto-generated method stub
	return ReadImage_ConfereeDao.save(readImage_Conferee);
  }

  @Override
  public int deleteById(ReadImage_Conferee readImage_Conferee) {
	// TODO Auto-generated method stub
	return ReadImage_ConfereeDao.deleteById(readImage_Conferee);
  }

  @Override
  public ReadImage_Conferee getById(ReadImage_Conferee readImage_Conferee) {
	// TODO Auto-generated method stub
	return ReadImage_ConfereeDao.getById(readImage_Conferee);
  }

  @Override
  public List<SysUser> getByReadimageKey(ReadImage_Conferee readImage_Conferee) {
	// TODO Auto-generated method stub
	return ReadImage_ConfereeDao.getByReadimageKey(readImage_Conferee);
  }

  @Override
  public int saveList(List<ReadImage_Conferee> conferees) {
	// TODO Auto-generated method stub
	return ReadImage_ConfereeDao.saveList(conferees);
  }

  @Override
  public int deleteByReadimageKey(String readimagekey) {
	// TODO Auto-generated method stub
	return ReadImage_ConfereeDao.deleteByReadimageKey(readimagekey);
  }
}
