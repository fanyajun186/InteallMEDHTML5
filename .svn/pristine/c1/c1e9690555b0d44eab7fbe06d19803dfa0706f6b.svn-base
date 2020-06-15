package com.inteall.image.service;

import java.util.List;

import com.inteall.image.pojo.SysUser;

/** 
* @author 韩明君  
* @date 创建时间：2018年2月7日 下午2:22:48 
* @version 1.0 
* @parameter  
*/
public interface SysUserService {
	/**
	 * 新增用户
	 * @param sysuer
	 */
	public int save(SysUser sysuer);

	public SysUser getById(String id);

	public int delById(String id);

	public List<SysUser> getAll(SysUser sysuser);

	public int updateById(SysUser sysUser);

	public int getCount(SysUser sysUser);

	public int checkLoginName(String loginName);

	public SysUser login(String loginname);

	public SysUser getByLoginname(String user_login);

	public int updatePassword(SysUser user);

}
