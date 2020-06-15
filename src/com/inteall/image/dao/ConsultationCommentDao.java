package com.inteall.image.dao;

import com.inteall.image.pojo.ConsultationCommentWithBLOBs;

public interface ConsultationCommentDao {
    
    int deleteById(String commentKey);

    int insert(ConsultationCommentWithBLOBs record);

    ConsultationCommentWithBLOBs getById(String commentKey);

    int updateById(ConsultationCommentWithBLOBs record);

}