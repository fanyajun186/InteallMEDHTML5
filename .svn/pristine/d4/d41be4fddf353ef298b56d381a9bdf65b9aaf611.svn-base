package com.inteall.image.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.inteall.image.dao.SysUserDao;
import com.inteall.image.pojo.SysUser;
import com.inteall.image.service.SysUserService;

/** 
 * @author 韩明君  
 * @date 创建时间：2016年7月9日 上午10:21:52 
 * @version 1.0 
 * @parameter  
 */
@Service("sysuserService")
public class SysUserServiceImpl implements SysUserService {
	@Resource
	private SysUserDao sysuserDao;

	@Override
	public int save(SysUser sysuser) {
		// TODO Auto-generated method stub
		return sysuserDao.save(sysuser);
	}

	@Override
	public SysUser getById(String id) {
		// TODO Auto-generated method stub
		return sysuserDao.getById(id);
	}

	@Override
	public int delById(String id) {
		// TODO Auto-generated method stub
		
		return sysuserDao.delById(id);
	}

	@Override
	public List<SysUser> getAll(SysUser sysuser) {
		// TODO Auto-generated method stub
		return sysuserDao.getAll(sysuser);
	}

	@Override
	public int updateById(SysUser sysUser) {
		// TODO Auto-generated method stub
		return sysuserDao.updateById(sysUser);
	}

	@Override
	public int getCount(SysUser sysUser) {
	  // TODO Auto-generated method stub
	  return sysuserDao.getCount(sysUser);
	}

	@Override
	public int checkLoginName(String loginName) {
	  // TODO Auto-generated method stub
	  return sysuserDao.checkLoginName(loginName);
	}

	@Override
	public SysUser login(String loginname) {
	  // TODO Auto-generated method stub
	  return sysuserDao.login(loginname);
	}

	@Override
	public SysUser getByLoginname(String user_login) {
	  // TODO Auto-generated method stub
	  return sysuserDao.getByLoginname(user_login);
	}

	@Override
	public int updatePassword(SysUser user) {
		// TODO Auto-generated method stub
		return sysuserDao.updatePassword(user);
	}

}
