package com.inteall.image.dao;

import java.util.List;
import java.util.Map;

import com.inteall.image.pojo.Notice;

public interface NoticeDao {
    int deleteByPrimaryKey(String noticeid);

    int insert(Notice record);

    int insertSelective(Notice record);

    Notice selectByPrimaryKey(String noticeid);

    int updateByPrimaryKeySelective(Notice record);

    int updateByPrimaryKey(Notice record);

	int getCount(Notice notice);

	List<Notice> getNoticeAll(Notice notice);

	int add(Notice notice);

	int delNoticeById(int parseInt);

	Notice getnoticeById(String id);

	int update(Notice notice);

	Notice noticeOne();

	Long getTotal(Map<String, Object> querymap);

	List<Notice> list(Map<String, Object> querymap);
}