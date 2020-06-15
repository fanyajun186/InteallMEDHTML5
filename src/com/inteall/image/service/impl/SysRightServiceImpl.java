package com.inteall.image.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.inteall.image.dao.SysRightDao;
import com.inteall.image.pojo.SysRight;
import com.inteall.image.service.SysRightService;

/** 
 * @author 韩明君  
 * @date 创建时间：2016年7月9日 上午10:21:52 
 * @version 1.0 
 * @parameter  
 */
@Service("SysRightService")
public class SysRightServiceImpl implements SysRightService {
	@Resource
	private SysRightDao SysRightDao;

	@Override
	public int save(SysRight SysRight) {
		// TODO Auto-generated method stub
		return SysRightDao.save(SysRight);
	}

	@Override
	public SysRight getById(String id) {
		// TODO Auto-generated method stub
		return SysRightDao.getById(id);
	}

	@Override
	public int delById(String id) {
		// TODO Auto-generated method stub
		
		return SysRightDao.delById(id);
	}

	@Override
	public List<SysRight> getAll(SysRight SysRight) {
		// TODO Auto-generated method stub
		return SysRightDao.getAll(SysRight);
	}

	@Override
	public int updateById(SysRight SysRight) {
		// TODO Auto-generated method stub
		return SysRightDao.updateById(SysRight);
	}

	@Override
	public List<SysRight> getRight() {
	  // TODO Auto-generated method stub
	  return SysRightDao.getRight();
	}

}
