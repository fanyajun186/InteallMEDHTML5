package com.inteall.image.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.inteall.image.dao.ConsultationConfereeDao;
import com.inteall.image.pojo.ConsultationConferee;
import com.inteall.image.pojo.SysUser;
import com.inteall.image.service.ConsultationConfereeService;

@Service("consultationConfereeService")
public class ConsultationConfereeServiceImpl implements ConsultationConfereeService{
	@Resource
	private ConsultationConfereeDao consultationConfereeDao;

	@Override
	public int saveList(List<ConsultationConferee> conferees) {
		// TODO Auto-generated method stub
		return consultationConfereeDao.saveList(conferees);
	}

	@Override
	public List<SysUser> getByCaseIdKey(ConsultationConferee consultationConferee) {
		// TODO Auto-generated method stub
		return consultationConfereeDao.getByCaseIdKey(consultationConferee);
	}

	@Override
	public int deleteById(ConsultationConferee consultationConferee) {
		
		return consultationConfereeDao.deleteById(consultationConferee);
	}
	
}