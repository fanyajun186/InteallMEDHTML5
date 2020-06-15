package com.inteall.image.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.inteall.image.dao.VideoDao;
import com.inteall.image.pojo.ReadImage;
import com.inteall.image.pojo.Video;
import com.inteall.image.service.VideoService;
@Service("videoService")
public class VideoServiceImpl implements VideoService {
	@Resource
	private VideoDao videoDao;

	@Override
	public List<Video> getByTutorials(Video video) {
		
		return videoDao.getByTutorials(video);
	}

	@Override
	public int videoSave(Video video) {
		
		return videoDao.videoSave(video);
	}

	@Override
	public Video getById(Video video) {
		
		return videoDao.getById(video);
	}

	@Override
	public int updateById(Video video) {
		// TODO Auto-generated method stub
		return videoDao.updateById(video);
	}

	@Override
	public List<Video> getByTutorialsById(Video video) {
		// TODO Auto-generated method stub
		return videoDao.getByTutorialsById(video);
	}
	
}
