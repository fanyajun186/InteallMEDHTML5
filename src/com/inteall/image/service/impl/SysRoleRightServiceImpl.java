package com.inteall.image.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.inteall.image.dao.SysRoleRightDao;
import com.inteall.image.pojo.SysRight;
import com.inteall.image.pojo.SysRoleRight;
import com.inteall.image.service.SysRoleRightService;

/** 
* @author 韩明君  
* @date 创建时间：2018年2月22日 下午4:11:10 
* @version 1.0 
* @parameter  
*/
@Service("sysRoleRightService")
public class SysRoleRightServiceImpl implements SysRoleRightService {

	@Resource
	private SysRoleRightDao sysRoleRightDao;
	
	@Override
	public List<SysRight> getRoleRight(String id) {
		// TODO Auto-generated method stub
		return sysRoleRightDao.getRoleRight(id);
	}

	@Override
	public void delRoleRight(String roleid) {
		// TODO Auto-generated method stub
		sysRoleRightDao.delRoleRight(roleid);
	}

	@Override
	public int addRoleRight(List<SysRoleRight> sysRoleRight) {
		// TODO Auto-generated method stub
		return sysRoleRightDao.addRoleRight(sysRoleRight);
	}

}
