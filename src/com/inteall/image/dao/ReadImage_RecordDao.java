package com.inteall.image.dao;

import java.util.HashMap;
import java.util.List;

import com.inteall.image.pojo.MedicalRecord;
import com.inteall.image.pojo.ReadImage_Record;

/**
 * @author 李进刚
 * @date 2018年3月7日 上午9:29:21
 * @version 1.0 
 * @parameter 
 */

public interface ReadImage_RecordDao {
  int save(ReadImage_Record readImage_Record);
  
  int deleteById(ReadImage_Record readImage_Record);
  
  ReadImage_Record getById(ReadImage_Record readImage_Record);
  
  List<MedicalRecord> getByReadImageKey(ReadImage_Record readImage_Record);

  int getCount(ReadImage_Record getImage_Record);

  int delByStuuid(HashMap<String, String> getParamMap);

  int deleteByReadimageKey(String readimagekey);

  int getByStuuid(HashMap<String, String> getParamMap);

  String getRecordById(ReadImage_Record readImageRecord);


}
