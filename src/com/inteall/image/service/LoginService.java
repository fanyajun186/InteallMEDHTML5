package com.inteall.image.service;

import com.inteall.image.pojo.SysUser;

/**
 * @author 韩明君
 * @date 2018年3月29日 上午11:00:06
 * @version 1.0 
 * @parameter 
 */

public interface LoginService {

  SysUser login(String loginname);

}
