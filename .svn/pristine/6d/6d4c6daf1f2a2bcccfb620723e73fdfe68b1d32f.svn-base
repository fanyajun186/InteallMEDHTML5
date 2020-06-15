package com.inteall.image.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.inteall.image.dao.MedicalLogDao;
import com.inteall.image.pojo.MedicalLog;
import com.inteall.image.service.MedicalLogService;
@Service("medicallog")
public class MedicalLogServiceImpl implements MedicalLogService{
	@Resource
	private MedicalLogDao medicalLogdao;
	@Override
	public int getCount(Map<String,Object> medicalLog) {
		// TODO Auto-generated method stub
		return medicalLogdao.getCount(medicalLog);
	}

	@Override
	public List<MedicalLog> getByStuuid(Map<String,Object> medicalLog) {
		// TODO Auto-generated method stub
		return medicalLogdao.getByStuuid(medicalLog);
	}

	@Override
	public int save(MedicalLog medicalLog) {
		// TODO Auto-generated method stub
		return medicalLogdao.save(medicalLog);
	}

}
