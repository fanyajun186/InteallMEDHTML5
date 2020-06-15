package com.inteall.image.service;

import com.inteall.image.pojo.MedicalRecord;

/**
 * @author 韩明君
 * @date 2018年4月4日 上午11:58:37
 * @version 1.0 
 * @parameter 
 */

public interface MedicalRecordService {

  int save(MedicalRecord medicalRecord);

  MedicalRecord getById(String stuuid);

  int getCountByStudy(String string);

  MedicalRecord getByImageId(String imageId);

}
