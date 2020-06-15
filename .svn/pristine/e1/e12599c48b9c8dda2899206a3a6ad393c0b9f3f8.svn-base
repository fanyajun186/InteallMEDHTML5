package com.inteall.image.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.inteall.image.dao.CommitteeIntroductionDao;
import com.inteall.image.pojo.CommitteeIntroduction;
import com.inteall.image.service.CommitteeIntroductionService;

/**
 * @author 韩明君
 * @date 2018年9月10日 上午11:57:49
 * @version 1.0 
 * @parameter 
 */
@Service("committeeIntroductionService")
public class CommitteeIntroductionServiceImpl implements CommitteeIntroductionService {

    @Resource
    private CommitteeIntroductionDao committeeIntroductionDao;
    @Override
    public int getCountCommittee(HashMap<String, Object> queryMap) {
        // TODO Auto-generated method stub
        return committeeIntroductionDao.getCountCommittee(queryMap);
    }

    @Override
    public List<CommitteeIntroduction> getCommittee(HashMap<String, Object> queryMap) {
        // TODO Auto-generated method stub
        return committeeIntroductionDao.getCommittee(queryMap);
    }

    @Override
    public int save(CommitteeIntroduction cIntroduction) {
        // TODO Auto-generated method stub
        return committeeIntroductionDao.save(cIntroduction);
    }

    @Override
    public int updateById(CommitteeIntroduction cIntroduction) {
        // TODO Auto-generated method stub
        return committeeIntroductionDao.updateById(cIntroduction);
    }

    @Override
    public CommitteeIntroduction getById(String id) {
        // TODO Auto-generated method stub
        return committeeIntroductionDao.getById(id);
    }

    @Override
    public int delById(String id) {
        // TODO Auto-generated method stub
        return committeeIntroductionDao.delById(id);
    }

    @Override
    public List<CommitteeIntroduction> findShow() {
        // TODO Auto-generated method stub
        return committeeIntroductionDao.findShow();
    }

}
