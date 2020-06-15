package com.inteall.image.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.inteall.image.dao.ModalityDao;
import com.inteall.image.pojo.Modality;
import com.inteall.image.service.ModalityService;

/** 
 * @author 韩明君  
 * @date 创建时间：2016年7月9日 上午10:21:52 
 * @version 1.0 
 * @parameter  
 */
@Service("modalityService")
public class ModalityServiceImpl implements ModalityService {
	@Resource
	private ModalityDao modalityDao;

	@Override
	public List<Modality> getAll() {
	  // TODO Auto-generated method stub
	  return modalityDao.getAll();
	}

	@Override
	public List<Modality> getmodalityName() {
		// TODO Auto-generated method stub
		return modalityDao.getmodalityName();
	}




}
