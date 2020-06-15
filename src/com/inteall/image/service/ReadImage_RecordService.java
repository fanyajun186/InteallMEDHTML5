package com.inteall.image.service;

import java.util.HashMap;
import java.util.List;

import com.inteall.image.pojo.MedicalRecord;
import com.inteall.image.pojo.ReadImage_Record;

/**
 * @author 李进刚
 * @date 2018年3月7日 上午9:38:36
 * @version 1.0 
 * @parameter 
 */

public interface ReadImage_RecordService {
  public int save(ReadImage_Record readImage_Record);
  
  public int deleteById(ReadImage_Record readImage_Record);
  
  public ReadImage_Record getById(ReadImage_Record readImage_Record);
  
  public List<MedicalRecord> getByReadImageKey(ReadImage_Record readImage_Record);

public int getCount(ReadImage_Record getImage_Record);

public int delByStuuid(HashMap<String, String> getParamMap);

public int deleteByReadimageKey(String readimagekey);

public int getByStuuid(HashMap<String, String> getParamMap);

public String getRecordById(ReadImage_Record readImageRecord);

}
