package com.inteall.image.service;

import java.util.List;

import com.inteall.image.pojo.ReadImage;
import com.inteall.image.pojo.Video;

public interface VideoService {

	List<Video> getByTutorials(Video video);

	int videoSave(Video video);

	Video getById(Video video);

	int updateById(Video video);

	List<Video> getByTutorialsById(Video video);


}
