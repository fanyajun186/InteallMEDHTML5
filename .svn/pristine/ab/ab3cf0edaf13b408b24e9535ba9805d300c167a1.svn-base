package com.inteall.image.dao;

import java.util.List;

import com.inteall.image.pojo.ConsultationConferee;
import com.inteall.image.pojo.SysUser;

public interface ConsultationConfereeDao {
    int deleteByPrimaryKey(String consultationConfereeKey);

    int insert(ConsultationConferee record);

    int insertSelective(ConsultationConferee record);

    ConsultationConferee selectByPrimaryKey(String consultationConfereeKey);

    int updateByPrimaryKeySelective(ConsultationConferee record);

    int updateByPrimaryKey(ConsultationConferee record);

	int saveList(List<ConsultationConferee> conferees);

	List<SysUser> getByCaseIdKey(ConsultationConferee consultationConferee);

	int deleteById(ConsultationConferee consultationConferee);
}