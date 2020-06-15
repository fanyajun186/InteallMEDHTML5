package com.inteall.image.dao;

import java.util.List;

import com.inteall.image.pojo.Courseware;

public interface CoursewareDao {
    int deleteByPrimaryKey(String coursewareKey);

    int insert(Courseware courseware);

    int insertSelective(Courseware courseware);

    Courseware selectByPrimaryKey(String coursewareKey);

    int updateByPrimaryKeySelective(Courseware courseware);

    int updateByPrimaryKey(Courseware courseware);
    
    List<Courseware> getAll(Courseware courseware);
}