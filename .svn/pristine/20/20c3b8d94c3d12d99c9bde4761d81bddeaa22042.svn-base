package com.inteall.image.dao;

import java.util.List;
import java.util.Map;

import com.inteall.image.pojo.MedicalLog;

public interface MedicalLogDao {
    int deleteByPrimaryKey(String syslogKey);

    int insert(MedicalLog record);

    int insertSelective(MedicalLog record);

    MedicalLog selectByPrimaryKey(String syslogKey);

    int updateByPrimaryKeySelective(MedicalLog record);

    int updateByPrimaryKey(MedicalLog record);

	int getCount(Map<String,Object> medicalLog);

	List<MedicalLog> getByStuuid(Map<String,Object> medicalLog);

	int save(MedicalLog medicalLog);
}