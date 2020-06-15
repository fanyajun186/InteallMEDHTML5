package com.inteall.image.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.inteall.image.dao.ReadImage_RecordDao;
import com.inteall.image.pojo.MedicalRecord;
import com.inteall.image.pojo.ReadImage_Record;
import com.inteall.image.service.ReadImage_RecordService;

/**
 * @author 李进刚
 * @date 2018年3月7日 下午2:33:53
 * @version 1.0 
 * @parameter 
 */

@Service("ReadImage_RecordService")
public class ReadImage_RecordServiceImpl implements ReadImage_RecordService {
  @Resource
  private ReadImage_RecordDao ReadImage_RecordDao;
  
  @Override
  public int save(ReadImage_Record readImage_Record) {
	// TODO Auto-generated method stub
	return ReadImage_RecordDao.save(readImage_Record);
  }

  @Override
  public int deleteById(ReadImage_Record readImage_Record) {
	// TODO Auto-generated method stub
	return ReadImage_RecordDao.deleteById(readImage_Record);
  }

  @Override
  public ReadImage_Record getById(ReadImage_Record readImage_Record) {
	// TODO Auto-generated method stub
	return ReadImage_RecordDao.getById(readImage_Record);
  }

  @Override
  public List<MedicalRecord> getByReadImageKey(ReadImage_Record readImage_Record) {
	// TODO Auto-generated method stub
	return ReadImage_RecordDao.getByReadImageKey(readImage_Record);
  }

@Override
public int getCount(ReadImage_Record getImage_Record) {
	// TODO Auto-generated method stub
	return ReadImage_RecordDao.getCount(getImage_Record);
}

@Override
public int delByStuuid(HashMap<String, String> getParamMap) {
  // TODO Auto-generated method stub
  return ReadImage_RecordDao.delByStuuid(getParamMap);
}

@Override
public int deleteByReadimageKey(String readimagekey) {
  // TODO Auto-generated method stub
  return ReadImage_RecordDao.deleteByReadimageKey(readimagekey);
}

@Override
public int getByStuuid(HashMap<String, String> getParamMap) {
  // TODO Auto-generated method stub
  return ReadImage_RecordDao.getByStuuid(getParamMap);
}

@Override
public String getRecordById(ReadImage_Record readImageRecord) {
	// TODO Auto-generated method stub
	return ReadImage_RecordDao.getRecordById(readImageRecord);
}

}
