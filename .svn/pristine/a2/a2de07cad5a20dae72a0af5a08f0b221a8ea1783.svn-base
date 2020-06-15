package com.inteall.image.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inteall.image.pojo.SysRole;
import com.inteall.image.pojo.SysUserRole;
import com.inteall.image.service.SysUserRoleService;
import com.inteall.image.util.DbcontextHolder;
import com.inteall.image.util.UUid;


/** 
* @author 韩明君  
* @date 创建时间：2018年2月7日 下午1:17:38 
* @version 1.0 
* @parameter  
*/
@Controller
@RequestMapping("/sysuserrole")
public class SysUserRoleController {
	private static Logger log = Logger.getLogger(SysUserRoleController.class.getName());
	@Resource
	private SysUserRoleService sysUserRoleService;
	
	
	
	/**
	 * 
	 * @param uid
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/addUserRole.do",method=RequestMethod.GET)
	@ResponseBody
	@Transactional  
	public Object addUserRole(@RequestParam String uid,@RequestParam List<String> roles,HttpServletRequest request,Model model){
		log.info("addUserRole");
		Map<String,Object> map = new HashMap<String,Object>();
		DbcontextHolder.setDbType("imagedb");
		List<SysUserRole> addRoles = new ArrayList<SysUserRole>();
		for(int i=0;i<roles.size();i++){
			SysUserRole sysUserRole = new SysUserRole();
			sysUserRole.setSys_user_role_key(UUid.getUUID());
			sysUserRole.setCreate_time(new Date());
			sysUserRole.setCreate_person("1");
			sysUserRole.setSys_role_key(roles.get(i));
			sysUserRole.setSys_user_key(uid);
			addRoles.add(sysUserRole);
		}
		
		//添加用户角色之前先删除所有的角色，然后再添加
		sysUserRoleService.delUserRole(uid);
		int row = sysUserRoleService.addUserRole(addRoles);
		
		map.put("用户新增角色", row);
		map.put("用户新增角色信息", roles);
		return map;
	}
	
	/**
	 * 
	 * @param uid
	 * @param request
	 * @param model
	 * @return
	 */

	@RequestMapping(value="/getUserRole.do",method=RequestMethod.GET)
	@ResponseBody
	public Object getUserRole(@RequestParam String uid,HttpServletRequest request,Model model){
		log.info("getUserRole");
		Map<String,Object> map = new HashMap<String,Object>();
		DbcontextHolder.setDbType("imagedb");
		List<SysRole> roles= sysUserRoleService.getUserRole(uid);
		
		map.put("用户角色信息", roles);
		return map;
	}
}
