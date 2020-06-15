package com.inteall.image.dao;

import java.util.List;

import com.inteall.image.pojo.SysRole;

/** 
* @author 韩明君  
* @date 创建时间：2018年2月9日 下午5:06:38 
* @version 1.0 
* @parameter  
*/
public interface SysRoleDao {

	int save(SysRole sysRole);

	SysRole getById(String id);

	int delById(String id);

	List<SysRole> getAll(SysRole sysRole);

	int updateById(SysRole sysRole);

	int getCount(SysRole sysRole);

	List<SysRole> getRole();

}
