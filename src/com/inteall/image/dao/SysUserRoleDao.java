package com.inteall.image.dao;

import java.util.List;

import com.inteall.image.pojo.SysRole;
import com.inteall.image.pojo.SysUserRole;

/** 
* @author 韩明君  
* @date 创建时间：2018年2月22日 下午4:12:49 
* @version 1.0 
* @parameter  
*/
public interface SysUserRoleDao {

	List<SysRole> getUserRole(String uid);

	void delUserRole(String uid);

	int addUserRole(List<SysUserRole> sysUserRole);

}
