package com.inteall.image.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.inteall.image.dao.ConsultationCommentDao;
import com.inteall.image.pojo.ConsultationCommentWithBLOBs;
import com.inteall.image.service.ConsultationCommentService;

/**
 * @author 韩明君
 * @date 2018年10月9日 上午10:58:11
 * @version 1.0 
 * @parameter 
 */
@Service("consultationCommentService")
public class ConsultationCommentServiceImpl implements ConsultationCommentService {

    @Resource
    private ConsultationCommentDao consultationCommentDao;
    @Override
    public ConsultationCommentWithBLOBs getById(String caseId) {
        // TODO Auto-generated method stub
        return consultationCommentDao.getById(caseId);
    }
    @Override
    public int insert(ConsultationCommentWithBLOBs consultationCommentWithBLOBs) {
        // TODO Auto-generated method stub
        return consultationCommentDao.insert(consultationCommentWithBLOBs);
    }
    @Override
    public int updateById(ConsultationCommentWithBLOBs consultationCommentWithBLOBs) {
        // TODO Auto-generated method stub
        return consultationCommentDao.updateById(consultationCommentWithBLOBs);
    }


}
