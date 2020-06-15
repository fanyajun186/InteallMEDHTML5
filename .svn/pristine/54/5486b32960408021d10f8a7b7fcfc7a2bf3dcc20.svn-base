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

import com.inteall.image.pojo.SysRoleRight;
import com.inteall.image.service.SysRoleRightService;
import com.inteall.image.util.DbcontextHolder;
import com.inteall.image.util.UUid;


/** 
* @author 韩明君  
* @date 创建时间：2018年2月7日 下午1:17:38 
* @version 1.0 
* @parameter  
*/
@Controller
@RequestMapping("/sysroleright")
public class SysRoleRightController {
	private static Logger log = Logger.getLogger(SysRoleRightController.class.getName());
	@Resource
	private SysRoleRightService sysRoleRightService;
	
	
	
	/**
	 * 
	 * @param uid
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/addRoleRight.do",method=RequestMethod.GET)
	@ResponseBody
	@Transactional  
	public Object addRoleRight(@RequestParam String roleid,@RequestParam List<String> rights,HttpServletRequest request,Model model){
		log.info("addRoleRight");
		Map<String,Object> map = new HashMap<String,Object>();
		DbcontextHolder.setDbType("imagedb");
		List<SysRoleRight> addRights = new ArrayList<SysRoleRight>();
		for(int i=0;i<rights.size();i++){
			SysRoleRight sysRoleRight = new SysRoleRight();
			sysRoleRight.setSys_role_right_key(UUid.getUUID());
			sysRoleRight.setCreate_time(new Date());
			sysRoleRight.setCreate_person("1");
			sysRoleRight.setSys_role_key(roleid);
			sysRoleRight.setSys_right_key(rights.get(i));
			addRights.add(sysRoleRight);
		}
		
		//添加用户角色之前先删除所有的角色，然后再添加
		sysRoleRightService.delRoleRight(roleid);
		int row = sysRoleRightService.addRoleRight(addRights);
		
		map.put("角色赋权", row);
		map.put("角色", addRights);
		return map;
	}
	
	/**
	 * 
	 * @param uid
	 * @param request
	 * @param model
	 * @return
	 */

	@RequestMapping(value="/getRoleRight.do",method=RequestMethod.GET)
	@ResponseBody
	public Object getRoleRight(@RequestParam String roleid,HttpServletRequest request,Model model){
		log.info("getRoleRight");
		Map<String,Object> map = new HashMap<String,Object>();
		
		
		return map;
	}
}
