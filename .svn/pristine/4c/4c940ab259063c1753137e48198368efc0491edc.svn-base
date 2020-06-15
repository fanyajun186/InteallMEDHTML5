package com.inteall.image.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.inteall.image.pojo.SysUser;
import com.inteall.image.service.LoginService;
import com.inteall.image.service.SysUserService;

/**
 * @author 韩明君
 * @date 2018年3月29日 下午2:54:41
 * @version 1.0 
 * @parameter 
 */
@Service("loginService")
public class LoginServiceImpl implements LoginService{
  
  @Resource
  private SysUserService sysUserService;
  @Override
  public SysUser login(String loginname) {
	// TODO Auto-generated method stub
	return sysUserService.login(loginname);
  }
  
}
