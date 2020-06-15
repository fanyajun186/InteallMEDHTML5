package com.inteall.image.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.inteall.image.dao.SysRoleDao;
import com.inteall.image.pojo.SysRole;
import com.inteall.image.service.SysRoleService;

/** 
 * @author 韩明君  
 * @date 创建时间：2016年7月9日 上午10:21:52 
 * @version 1.0 
 * @parameter  
 */
@Service("SysRoleService")
public class SysRoleServiceImpl implements SysRoleService {
	@Resource
	private SysRoleDao sysroleDao;

	@Override
	public int save(SysRole SysRole) {
		// TODO Auto-generated method stub
		return sysroleDao.save(SysRole);
	}

	@Override
	public SysRole getById(String id) {
		// TODO Auto-generated method stub
		return sysroleDao.getById(id);
	}

	@Override
	public int delById(String id) {
		// TODO Auto-generated method stub
		
		return sysroleDao.delById(id);
	}

	@Override
	public List<SysRole> getAll(SysRole SysRole) {
		// TODO Auto-generated method stub
		return sysroleDao.getAll(SysRole);
	}

	@Override
	public int updateById(SysRole SysRole) {
		// TODO Auto-generated method stub
		return sysroleDao.updateById(SysRole);
	}

	@Override
	public int getCount(SysRole sysRole) {
	  // TODO Auto-generated method stub
	  return sysroleDao.getCount(sysRole);
	}

	@Override
	public List<SysRole> getRole() {
	  // TODO Auto-generated method stub
	  return sysroleDao.getRole();
	}

}
