package com.inteall.image.dao;

import com.inteall.image.pojo.WPACSStudys;

public interface WPACSStudysDao {
    int deleteByPrimaryKey(String stuid);

    int insert(WPACSStudys record);

    int insertSelective(WPACSStudys record);

    WPACSStudys selectByPrimaryKey(String stuid);

    int updateByPrimaryKeySelective(WPACSStudys record);

    int updateByPrimaryKey(WPACSStudys record);
}