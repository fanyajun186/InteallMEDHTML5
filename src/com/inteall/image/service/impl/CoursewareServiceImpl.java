package com.inteall.image.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.inteall.image.dao.CoursewareDao;
import com.inteall.image.pojo.Courseware;
import com.inteall.image.service.CoursewareService;
@Service("coursewareService")
public class CoursewareServiceImpl implements CoursewareService {
	@Resource
	private CoursewareDao coursewareDao;
	@Override
	public int deleteByPrimaryKey(String coursewareKey) {
		// TODO 自动生成的方法存根
		return coursewareDao.deleteByPrimaryKey(coursewareKey);
	}

	@Override
	public int insert(Courseware courseware) {
		// TODO 自动生成的方法存根
		return coursewareDao.insert(courseware);
	}

	@Override
	public int insertSelective(Courseware courseware) {
		// TODO 自动生成的方法存根
		return coursewareDao.insertSelective(courseware);
	}

	@Override
	public Courseware selectByPrimaryKey(String coursewareKey) {
		// TODO 自动生成的方法存根
		return coursewareDao.selectByPrimaryKey(coursewareKey);
	}

	@Override
	public int updateByPrimaryKeySelective(Courseware courseware) {
		// TODO 自动生成的方法存根
		return coursewareDao.updateByPrimaryKeySelective(courseware);
	}

	@Override
	public int updateByPrimaryKey(Courseware courseware) {
		// TODO 自动生成的方法存根
		return coursewareDao.updateByPrimaryKey(courseware);
	}

	@Override
	public List<Courseware> getAll(Courseware courseware) {
		// TODO 自动生成的方法存根
		return coursewareDao.getAll(courseware);
	}

}
