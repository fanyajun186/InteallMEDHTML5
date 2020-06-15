package com.inteall.image.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.inteall.image.dao.MedicalRecordDao;
import com.inteall.image.pojo.MedicalRecord;
import com.inteall.image.service.MedicalRecordService;

/**
 * @author 韩明君
 * @date 2018年4月4日 上午11:59:55
 * @version 1.0 
 * @parameter 
 */
@Service("medicalRecordService")
public class MedicalRecordServiceImpl implements MedicalRecordService {

	@Resource
	private MedicalRecordDao medicalRecordDao;

	@Override
	public int save(MedicalRecord medicalRecord) {
		// TODO Auto-generated method stub
		return medicalRecordDao.save(medicalRecord);
	}

	@Override
	public MedicalRecord getById(String stuuid) {
		// TODO Auto-generated method stub
		return medicalRecordDao.getById(stuuid);
	}

	@Override
	public int getCountByStudy(String string) {
	  // TODO Auto-generated method stub
	  return medicalRecordDao.getCountByStudy(string);
	}

	@Override
	public MedicalRecord getByImageId(String imageId) {
		// TODO Auto-generated method stub
		return medicalRecordDao.getByImageId(imageId);
	}

}
