package com.inteall.image.dao;

import java.util.List;

import com.inteall.image.pojo.InterventionConferee;
import com.inteall.image.pojo.SysUser;

public interface InterventionConfereeDao {
    int deleteByPrimaryKey(String interventionConfereeKey);

    int insert(InterventionConferee record);

    int insertSelective(InterventionConferee record);

    InterventionConferee selectByPrimaryKey(String interventionConfereeKey);

    int updateByPrimaryKeySelective(InterventionConferee record);

    int updateByPrimaryKey(InterventionConferee record);

	List<SysUser> getByInterventionKey(InterventionConferee interventionConferee);

	int saveList(List<InterventionConferee> conferees);

	int deleteById(InterventionConferee interventionConferee);

}