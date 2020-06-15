package com.inteall.image.dao;

import java.util.List;

import com.inteall.image.pojo.SysUser;

/** 
 * 
 * @author 韩明君  
 * @date 创建时间：2016年7月10日 上午10:21:52 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  
 */
public interface SysUserDao {
	/**
	 * 新增用户信息
	 * @author 韩明君
	 * @date 创建时间: 2016年7月10日 上午10:21:52 
	 * @param sysuer
	 */
	int save(SysUser sysuer);
	
	/**
	 * 根据ID查找用户信息
	 * @author 韩明君
	 * @date 创建时间: 2016年7月10日 上午10:21:52 
	 * @param id
	 * @return
	 */
	SysUser getById(String id);
	
	/**
	 * 根据ID删除用户信息
	 * @author 韩明君
	 * @date 创建时间: 2016年7月10日 上午10:21:52 
	 * @param id
	 * @return 返回受影响的行数，判断是否删除成功
	 */
	int delById(String id);
	
	/**
	 * 查询所有用户信息
	 * @author 韩明君
	 * @date 创建时间: 2016年7月10日 上午10:21:52 
	 * @param archives
	 */
	List<SysUser> getAll(SysUser sysuser);

	int updateById(SysUser sysUser);

	int getCount(SysUser sysUser);

	int checkLoginName(String loginName);

	SysUser login(String loginName);

	SysUser getByLoginname(String user_login);

	int updatePassword(SysUser user);
	
	
}
