package com.inteall.image.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.inteall.image.dao.AccessoryDao;
import com.inteall.image.pojo.Accessory;
import com.inteall.image.service.AccessoryService;

/** 
 * @author 韩明君  
 * @date 创建时间：2016年7月9日 上午10:21:52 
 * @version 1.0 
 * @parameter  
 */
@Service("accessoryService")
public class AccessoryServiceImpl implements AccessoryService {
	@Resource
	private AccessoryDao accessoryDao;

	@Override
	public int save(Accessory Accessory) {
		// TODO Auto-generated method stub
		return accessoryDao.save(Accessory);
	}

	@Override
	public List<Accessory> getById(String id) {
		// TODO Auto-generated method stub
		return accessoryDao.getById(id);
	}

	@Override
	public int delById(String id) {
		// TODO Auto-generated method stub
		
		return accessoryDao.delById(id);
	}

	@Override
	public List<Accessory> getAll(Accessory Accessory) {
		// TODO Auto-generated method stub
		return accessoryDao.getAll(Accessory);
	}

	@Override
	public int updateById(Accessory Accessory) {
		// TODO Auto-generated method stub
		return accessoryDao.updateById(Accessory);
	}

	@Override
	public int getByAccessoryOldName(String myFileName,String stuuid) {
		// TODO Auto-generated method stub
		return accessoryDao.getByAccessoryOldName(myFileName,stuuid);
	}

}
