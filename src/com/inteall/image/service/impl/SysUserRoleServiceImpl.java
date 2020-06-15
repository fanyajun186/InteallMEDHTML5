package com.inteall.image.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.inteall.image.dao.SysUserRoleDao;
import com.inteall.image.pojo.SysRole;
import com.inteall.image.pojo.SysUserRole;
import com.inteall.image.service.SysUserRoleService;

/** 
* @author 韩明君  
* @date 创建时间：2018年2月22日 下午4:11:10 
* @version 1.0 
* @parameter  
*/
@Service("sysUserRoleService")
public class SysUserRoleServiceImpl implements SysUserRoleService {

	@Resource
	private SysUserRoleDao sysroleDao;
	
	@Override
	public List<SysRole> getUserRole(String uid) {
		// TODO Auto-generated method stub
		return sysroleDao.getUserRole(uid);
	}

	@Override
	public void delUserRole(String uid) {
		// TODO Auto-generated method stub
		sysroleDao.delUserRole(uid);
	}

	@Override
	public int addUserRole(List<SysUserRole> sysUserRole) {
		// TODO Auto-generated method stub
		return sysroleDao.addUserRole(sysUserRole);
	}

}
