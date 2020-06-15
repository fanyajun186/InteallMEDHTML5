package com.inteall.image.dao;

import java.util.List;

import com.inteall.image.pojo.MedicalRecord;
import com.inteall.image.pojo.SysUser;
import com.inteall.image.pojo.VideoTutorials;

public interface VideoTutorialsDao {
    int deleteByPrimaryKey(String videoTutorialsKey);

    int insert(VideoTutorials record);

    int insertSelective(VideoTutorials record);

    VideoTutorials selectByPrimaryKey(String videoTutorialsKey);

    int updateByPrimaryKeySelective(VideoTutorials record);

    int updateByPrimaryKey(VideoTutorials record);
    
    List<VideoTutorials> getAll(VideoTutorials videoTutorials);
    
    int insertMedicalRecord(MedicalRecord medicalRecord);

	int saveList(List<VideoTutorials> videoTutorials);

	List<SysUser> getByVideoKey(VideoTutorials videoTutorials);

	int deleteById(VideoTutorials videoTutorials);
}