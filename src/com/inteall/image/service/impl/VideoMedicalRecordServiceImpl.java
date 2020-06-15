package com.inteall.image.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.inteall.image.dao.VideoMedicalRecordDao;
import com.inteall.image.pojo.MedicalRecord;
import com.inteall.image.pojo.VideoMedicalRecord;
import com.inteall.image.service.VideoMedicalRecordService;
@Service("videoMedicalRecordService")
public class VideoMedicalRecordServiceImpl implements VideoMedicalRecordService {
	@Resource
	private VideoMedicalRecordDao videoMedicalRecordDao;
	@Override
	public int deleteByPrimaryKey(String videoMedicalRecordKey) {
		// TODO 自动生成的方法存根
		return videoMedicalRecordDao.deleteByPrimaryKey(videoMedicalRecordKey);
	}

	@Override
	public int insertVideoMedicalRecord(VideoMedicalRecord record) {
		// TODO 自动生成的方法存根
		return videoMedicalRecordDao.insertVideoMedicalRecord(record);
	}

	@Override
	public int insertSelective(VideoMedicalRecord record) {
		// TODO 自动生成的方法存根
		return videoMedicalRecordDao.insertSelective(record);
	}

	@Override
	public VideoMedicalRecord selectByPrimaryKey(String videoMedicalRecordKey) {
		// TODO 自动生成的方法存根
		return videoMedicalRecordDao.selectByPrimaryKey(videoMedicalRecordKey);
	}

	@Override
	public int updateByPrimaryKeySelective(VideoMedicalRecord record) {
		// TODO 自动生成的方法存根
		return videoMedicalRecordDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(VideoMedicalRecord record) {
		// TODO 自动生成的方法存根
		return videoMedicalRecordDao.updateByPrimaryKey(record);
	}

	@Override
	public List<VideoMedicalRecord> getMedicalRecordList(VideoMedicalRecord videoMedicalRecord) {
		// TODO 自动生成的方法存根
		return videoMedicalRecordDao.getMedicalRecordList(videoMedicalRecord);
	}

	@Override
	public int getCount(VideoMedicalRecord getvideoMedicalRecord) {
		
		return videoMedicalRecordDao.getCount(getvideoMedicalRecord);
	}

	@Override
	public List<MedicalRecord> getByVideoKey(VideoMedicalRecord getvideoMedicalRecord) {
		
		return videoMedicalRecordDao.getByVideoKey(getvideoMedicalRecord);
	}

	@Override
	public int deleteByVideoKey(String videoKey) {
		
		return videoMedicalRecordDao.deleteByVideoKey(videoKey);
	}

	@Override
	public int getByStuuid(HashMap<String, String> getParamMap) {
		
		return videoMedicalRecordDao.getByStuuid(getParamMap);
	}

	@Override
	public int save(VideoMedicalRecord videoMedicalRecord) {
		
		return videoMedicalRecordDao.save(videoMedicalRecord);
	}

	@Override
	public int delByStuuid(HashMap<String, String> getParamMap) {
		
		return videoMedicalRecordDao.delByStuuid(getParamMap);
	}

	@Override
	public int getRecordCount(HashMap<String, Object> queryMap) {
		
		return videoMedicalRecordDao.getRecordCount(queryMap);
	}

	@Override
	public List<MedicalRecord> getRecordByVideoKey(HashMap<String, Object> queryMap) {

		return videoMedicalRecordDao.getRecordByVideoKey(queryMap);
	}

	@Override
	public String getRecordById(VideoMedicalRecord videoMedicalRecord) {
		// TODO Auto-generated method stub
		return videoMedicalRecordDao.getRecordById(videoMedicalRecord);
	}



}
