package com.inteall.image.service;

import java.util.HashMap;
import java.util.List;

import com.inteall.image.pojo.MedicalRecord;
import com.inteall.image.pojo.VideoMedicalRecord;

public interface VideoMedicalRecordService {
	
		int deleteByPrimaryKey(String videoMedicalRecordKey);

	    int insertVideoMedicalRecord(VideoMedicalRecord record);

	    int insertSelective(VideoMedicalRecord record);

	    VideoMedicalRecord selectByPrimaryKey(String videoMedicalRecordKey);

	    int updateByPrimaryKeySelective(VideoMedicalRecord record);

	    int updateByPrimaryKey(VideoMedicalRecord record);
	    
	    List<VideoMedicalRecord> getMedicalRecordList(VideoMedicalRecord videoMedicalRecord);

		int getCount(VideoMedicalRecord getvideoMedicalRecord);

		List<MedicalRecord> getByVideoKey(VideoMedicalRecord getvideoMedicalRecord);

		int deleteByVideoKey(String videoKey);

		int getByStuuid(HashMap<String, String> getParamMap);

		int save(VideoMedicalRecord videoMedicalRecord);

		int delByStuuid(HashMap<String, String> getParamMap);

		int getRecordCount(HashMap<String, Object> queryMap);

		List<MedicalRecord> getRecordByVideoKey(HashMap<String, Object> queryMap);

		String getRecordById(VideoMedicalRecord videoMedicalRecord);




}
