package com.inteall.image.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.inteall.image.dao.InterventionDao;
import com.inteall.image.pojo.Intervention;
import com.inteall.image.pojo.InterventionConferee;
import com.inteall.image.pojo.InterventionWithBLOBs;
import com.inteall.image.service.InterventionService;
@Service("interventiondicService")
public class InterventionServiceImpl implements InterventionService{
	@Resource
	private InterventionDao interventionDao;
	
	@Override
	public int getMedicalCount(HashMap<String, Object> queryMap) {
		
		return interventionDao.getMedicalCount(queryMap);
	}
	@Override
	public List<InterventionWithBLOBs> getRecordByCreateUser(HashMap<String, Object> queryMap) {
		
		return interventionDao.getRecordByCreateUser(queryMap);
	}
	@Override
	public int delByStuuid(HashMap<String, String> getParamMap) {
		
		return interventionDao.delByStuuid(getParamMap);
	}
	@Override
	public int updateByInterventionKey(InterventionWithBLOBs intervention) {
		
		return interventionDao.updateByInterventionKey(intervention);
	}
	@Override
	public List<InterventionWithBLOBs> getRecordByDoctor(HashMap<String, Object> queryMap) {

		return interventionDao.getRecordByDoctor(queryMap);
	}
	@Override
	public int getCountByDoctorUser(HashMap<String, Object> queryMap) {
		
		return interventionDao.getCountByDoctorUser(queryMap);
	}
	@Override
	public List<InterventionWithBLOBs> getRecordByDoctorUser(HashMap<String, Object> queryMap) {
		
		return interventionDao.getRecordByDoctorUser(queryMap);
	}
	@Override
	public int saveById(Intervention intervention) {
		
		return interventionDao.saveById(intervention);
	}
	@Override
	public int updateById(Intervention intervention) {
		
		return interventionDao.updateById(intervention);
	}
	@Override
	public Intervention getById(Intervention intervention) {
		
		return interventionDao.getById(intervention);
	}
	@Override
	public List<String> getStateByStuuid(String stuuid, String sysuserKey) {
		
		return interventionDao.getStateByStuuid(stuuid,sysuserKey);
	}
	@Override
	public int save(InterventionWithBLOBs interventionWithBLOBs) {
		
		return interventionDao.save(interventionWithBLOBs);
	}
	@Override
	public int getByStuuid(HashMap<String, String> getParamMap) {
		
		return interventionDao.getByStuuid(getParamMap);
	}
	@Override
	public int getMedicalRecordBystuuid(String stuuid) {
		
		return interventionDao.getMedicalRecordBystuuid(stuuid);
	}

}
