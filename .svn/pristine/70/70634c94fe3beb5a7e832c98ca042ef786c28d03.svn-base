package com.inteall.image.dao;

import java.util.HashMap;
import java.util.List;

import com.inteall.image.pojo.Intervention;
import com.inteall.image.pojo.InterventionWithBLOBs;

public interface InterventionDao {
    int deleteByPrimaryKey(String interventionKey);

    int insert(InterventionWithBLOBs record);

    int insertSelective(InterventionWithBLOBs record);

    InterventionWithBLOBs selectByPrimaryKey(String interventionKey);

    int updateByPrimaryKeySelective(InterventionWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(InterventionWithBLOBs record);

    int updateByPrimaryKey(Intervention record);

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

	int save(InterventionWithBLOBs interventionWithBLOBs);

	List<String> getStateByStuuid(String stuuid, String sysuserKey);

	int getByStuuid(HashMap<String, String> getParamMap);

	int getMedicalRecordBystuuid(String stuuid);
}