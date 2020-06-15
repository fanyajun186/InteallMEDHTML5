package com.inteall.image.service;

import java.util.HashMap;
import java.util.List;

import com.inteall.image.pojo.Intervention;
import com.inteall.image.pojo.InterventionConferee;
import com.inteall.image.pojo.InterventionWithBLOBs;

public interface InterventionService {

	int getMedicalCount(HashMap<String, Object> queryMap);

	List<InterventionWithBLOBs> getRecordByCreateUser(HashMap<String, Object> queryMap);

	int delByStuuid(HashMap<String, String> getParamMap);

	int updateByInterventionKey(InterventionWithBLOBs intervention);

	List<InterventionWithBLOBs> getRecordByDoctor(HashMap<String, Object> queryMap);

	int getCountByDoctorUser(HashMap<String, Object> queryMap);

	List<InterventionWithBLOBs> getRecordByDoctorUser(HashMap<String, Object> queryMap);

	int saveById(Intervention intervention);

	int updateById(Intervention intervention);

	Intervention getById(Intervention intervention);

	List<String> getStateByStuuid(String string, String sysuserKey);

	int save(InterventionWithBLOBs interventionWithBLOBs);

	int getByStuuid(HashMap<String, String> getParamMap);

	int getMedicalRecordBystuuid(String string);

}
