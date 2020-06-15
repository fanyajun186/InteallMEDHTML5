package com.inteall.image.service;

import java.util.List;

import com.inteall.image.pojo.Courseware;

public interface CoursewareService {
	 public int deleteByPrimaryKey(String coursewareKey);

	 public int insert(Courseware courseware);

	 public int insertSelective(Courseware courseware);

	 public Courseware selectByPrimaryKey(String coursewareKey);

	 public int updateByPrimaryKeySelective(Courseware courseware);

	 public int updateByPrimaryKey(Courseware courseware);
	 
	 public List<Courseware> getAll(Courseware courseware);
}
