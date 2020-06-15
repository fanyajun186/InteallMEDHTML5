package com.inteall.image.dao;

import java.util.List;

import com.inteall.image.pojo.MedicalRecord;
import com.inteall.image.pojo.Video;
import com.inteall.image.pojo.VideoTutorials;

public interface VideoDao {

	List<Video> getByTutorials(Video video);

	int videoSave(Video video);

	Video getById(Video video);

	int updateById(Video video);

	List<Video> getByTutorialsById(Video video);
	
	
}