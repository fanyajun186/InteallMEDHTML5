package com.inteall.image.dao;

import com.inteall.image.pojo.WPACSSeries;

public interface WPACSSeriesDao {
    int deleteByPrimaryKey(String srsuid);

    int insert(WPACSSeries record);

    int insertSelective(WPACSSeries record);

    WPACSSeries selectByPrimaryKey(String srsuid);

    int updateByPrimaryKeySelective(WPACSSeries record);

    int updateByPrimaryKey(WPACSSeries record);
    
    String getMaxSrsuidByStuuid(String stuuid);
    
    int deleteByExcept(WPACSSeries record);
}