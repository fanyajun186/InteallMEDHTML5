package com.inteall.image.dao;

import java.util.List;

import com.inteall.image.pojo.WPACSImages;

public interface WPACSImagesDao {
    int deleteByPrimaryKey(String imguid);

    int insert(WPACSImages record);

    int insertSelective(WPACSImages record);

    WPACSImages selectByPrimaryKey(String imguid);

    int updateByPrimaryKeySelective(WPACSImages record);

    int updateByPrimaryKey(WPACSImages record);
    
    // 根据序列号查询影像文件列表
    List<WPACSImages> selectBySrsUID(String srsuid);
    
    int deleteByExcept(WPACSImages record);
}