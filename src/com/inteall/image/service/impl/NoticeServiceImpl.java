package com.inteall.image.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.inteall.image.dao.NoticeDao;
import com.inteall.image.pojo.Notice;
import com.inteall.image.service.NoticeService;
@Service("NoticeService")
public class NoticeServiceImpl implements NoticeService {
	@Resource
	private NoticeDao noticeDao;
	@Override
	public int getCount(Notice notice) {
		// TODO Auto-generated method stub
		return noticeDao.getCount(notice);
	}

	@Override
	public List<Notice> getNoticeAll(Notice notice) {
		// TODO Auto-generated method stub
		return noticeDao.getNoticeAll(notice);
	}

	@Override
	public int add(Notice notice) {
		// TODO Auto-generated method stub
		return noticeDao.add(notice);
	}

	@Override
	public int delNoticeById(int parseInt) {
		// TODO Auto-generated method stub
		return noticeDao.delNoticeById(parseInt);
	}

	@Override
	public Notice getnoticeById(String id) {
		// TODO Auto-generated method stub
		return noticeDao.getnoticeById(id);
	}

	@Override
	public int update(Notice notice) {
		// TODO Auto-generated method stub
		return noticeDao.update(notice);
	}

	@Override
	public Notice noticeOne() {
		// TODO Auto-generated method stub
		return noticeDao.noticeOne();
	}

	@Override
	public Long getTotal(Map<String, Object> querymap) {
		// TODO Auto-generated method stub
		return noticeDao.getTotal(querymap);
	}

	@Override
	public List<Notice> list(Map<String, Object> querymap) {
		// TODO Auto-generated method stub
		return noticeDao.list(querymap);
	}

}
