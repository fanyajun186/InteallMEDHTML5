package com.inteall.image.dao;

import java.util.HashMap;
import java.util.List;

import com.inteall.image.pojo.CommitteeIntroduction;

public interface CommitteeIntroductionDao {
    int deleteByPrimaryKey(String committeeid);

    int save(CommitteeIntroduction record);

    CommitteeIntroduction selectByPrimaryKey(String committeeid);

    int updateByPrimaryKeySelective(CommitteeIntroduction record);

    int getCountCommittee(HashMap<String, Object> queryMap);

    List<CommitteeIntroduction> getCommittee(HashMap<String, Object> queryMap);

    int updateById(CommitteeIntroduction cIntroduction);

    CommitteeIntroduction getById(String id);

    int delById(String id);

    List<CommitteeIntroduction> findShow();

}