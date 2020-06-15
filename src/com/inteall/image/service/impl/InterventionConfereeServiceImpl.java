package com.inteall.image.service.impl;

import java.util.List;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.inteall.image.dao.InterventionConfereeDao;
import com.inteall.image.pojo.InterventionConferee;
import com.inteall.image.pojo.SysUser;
import com.inteall.image.service.InterventionConfereeService;

@Service("interventionConfereeService")
public class InterventionConfereeServiceImpl implements InterventionConfereeService{

	@Resource
	private InterventionConfereeDao confereeDao;

	@Override
	public List<SysUser> getByInterventionKey(InterventionConferee interventionConferee) {
		
		return confereeDao.getByInterventionKey(interventionConferee);
	}

	@Override
	public int saveList(List<InterventionConferee> conferees) {
		
		return confereeDao.saveList(conferees);
	}

	@Override
	public int deleteById(InterventionConferee interventionConferee) {
		
		return confereeDao.deleteById(interventionConferee);
	}


}
