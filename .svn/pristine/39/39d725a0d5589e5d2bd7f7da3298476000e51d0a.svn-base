package com.inteall.image.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.inteall.image.dao.SysHospitalDao;
import com.inteall.image.pojo.SysHospital;
import com.inteall.image.service.SysHospitalService;

/** 
 * @author 韩明君  
 * @date 创建时间：2016年7月9日 上午10:21:52 
 * @version 1.0 
 * @parameter  
 */
@Service("sysHospitalService")
public class SysHospitalServiceImpl implements SysHospitalService {
	@Resource
	private SysHospitalDao sysHospitalDao;

	@Override
	public int save(SysHospital sysHospital) {
		// TODO Auto-generated method stub
		return sysHospitalDao.save(sysHospital);
	}

	@Override
	public SysHospital getById(String id) {
		// TODO Auto-generated method stub
		return sysHospitalDao.getById(id);
	}

	@Override
	public int delById(String id) {
		// TODO Auto-generated method stub
		
		return sysHospitalDao.delById(id);
	}

	@Override
	public List<SysHospital> getAll(SysHospital sysHospital) {
		// TODO Auto-generated method stub
		return sysHospitalDao.getAll(sysHospital);
	}

	@Override
	public int updateById(SysHospital sysHospital) {
		// TODO Auto-generated method stub
		return sysHospitalDao.updateById(sysHospital);
	}

	@Override
	public int getCount(SysHospital sysHospital) {
	  // TODO Auto-generated method stub
	  return sysHospitalDao.getCount(sysHospital);
	}

	@Override
	public List<SysHospital> getHospital() {
	  // TODO Auto-generated method stub
	  return sysHospitalDao.getHospital();
	}

}
