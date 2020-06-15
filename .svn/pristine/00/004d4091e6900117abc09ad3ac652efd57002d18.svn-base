package com.inteall.image.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.inteall.image.dao.VideoTutorialsDao;
import com.inteall.image.pojo.MedicalRecord;
import com.inteall.image.pojo.SysUser;
import com.inteall.image.pojo.VideoTutorials;
import com.inteall.image.service.VideoTutorialsService;
@Service("videoTutorialsService")
public class VideoTutorialsServiceImpl implements VideoTutorialsService {
	@Resource
	private VideoTutorialsDao videoTutorialsDao;

	@Override
	public List<VideoTutorials> getAll(VideoTutorials videoTutorials) {
		// TODO 自动生成的方法存根
		return videoTutorialsDao.getAll(videoTutorials);
	}

	@Override
	public int insertMedicalRecord(MedicalRecord medicalRecord) {
		// TODO 自动生成的方法存根
		return videoTutorialsDao.insertMedicalRecord(medicalRecord);
	}

	@Override
	public int saveList(List<VideoTutorials> videoTutorials) {
		
		return videoTutorialsDao.saveList(videoTutorials);
	}

	@Override
	public List<SysUser> getByVideoKey(VideoTutorials videoTutorials) {
		
		return videoTutorialsDao.getByVideoKey(videoTutorials);
	}

	@Override
	public int deleteById(VideoTutorials videoTutorials) {
		
		return videoTutorialsDao.deleteById(videoTutorials);
	}



}
