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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.inteall.image.pojo.SysRight;
import com.inteall.image.pojo.SysRole;
import com.inteall.image.pojo.SysRoleRight;
import com.inteall.image.service.SysRoleRightService;
import com.inteall.image.service.SysRoleService;
import com.inteall.image.util.DbcontextHolder;
import com.inteall.image.util.UUid;


/** 
* @author 韩明君  
* @date 创建时间：2018年2月7日 下午1:17:38 
* @version 1.0 
* @parameter  
*/
@Controller
@RequestMapping("/sysrole")
public class SysRoleController {
	private static Logger log = Logger.getLogger(SysRoleController.class.getName());
	@Resource
	private SysRoleService sysroleService;
	@Resource
	private SysRoleRightService sysRoleRightService;
	
	
	/**
	 * 跳转到添加角色界面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/redirectAdd.do",method=RequestMethod.GET)
	public Object redirectAdd(HttpServletRequest request,Model model){
		return "xitongshezhi/sysrole_add";
	}
	/**
	 * 根据id查询角色信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/getById.do",method=RequestMethod.GET)
	public Object getById(@RequestParam String id,HttpServletRequest request,Model model){
		log.info("getById");
		DbcontextHolder.setDbType("imagedb");
		SysRole sysRole = sysroleService.getById(id);
		List<SysRight> sysRights = sysRoleRightService.getRoleRight(id);
		
		Gson gson = new Gson();
		model.addAttribute("sysRole",gson.toJson(sysRole));
		model.addAttribute("sysRights", gson.toJson(sysRights));
		return "xitongshezhi/sysrole_edit";
	}
	
	/**
	 * 根据角色的id查询角色信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/getAll.do",method=RequestMethod.POST)
	@ResponseBody
	public Object getAll(HttpServletRequest request,Model model){
		log.info("getAll");
		DbcontextHolder.setDbType("imagedb");
		Map<String,Object> map = new HashMap<String,Object>();
		int page = Integer.parseInt(request.getParameter("page"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		String role_name = request.getParameter("role_name");
		
		SysRole sysRole = new SysRole();
		sysRole.setRoleName(role_name);
		//先查询满足条件的数据有多少条
		int count = sysroleService.getCount(sysRole);
				
		//再查当前页中的数据
		sysRole.setLimit(limit);
		sysRole.setCurr((page-1)*limit);
		List<SysRole> sysRoleList = sysroleService.getAll(sysRole);
		if(sysRoleList!=null&&sysRoleList.size()>0){
    		map.put("code", 0);
    		map.put("msg", "");
    		map.put("count", count);
    		map.put("data", sysRoleList);
		}else{
			map.put("code", 1);
			map.put("msg", "<img style='width:228px;height:228px' src='img/noneicon.png'>");
		}
		return map;
	}
	
	/**
	 * 根据角色的id查询角色信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/getRole.do",method=RequestMethod.POST)
	@ResponseBody
	public Object getRole(HttpServletRequest request,Model model){
		log.info("getRole");
		
		Map<String,Object> map = new HashMap<String,Object>();
		DbcontextHolder.setDbType("imagedb");
				
		List<SysRole> sysRoleList = sysroleService.getRole();
		if(sysRoleList!=null&&sysRoleList.size()>0){
    		map.put("code", 0);
    		map.put("msg", "");
    		map.put("sysRoleList", sysRoleList);
		}else{
			map.put("code", 1);
			map.put("msg", "<img style='width:228px;height:228px' src='img/noneicon.png'>");
		}
		return map;
	}
	
	/**
	 * 根据角色id删除角色
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delById.do",method=RequestMethod.GET)
	@ResponseBody
	public Object delById(@RequestParam String id,HttpServletRequest request,Model model){
		log.info("delById");
		Map<String,Object> map = new HashMap<String,Object>();
		SysRole sysRole = new SysRole();
		sysRole.setSysRoleKey(id);
		sysRole.setIsDel("1");
		sysRole.setDelTime(new Date());
		DbcontextHolder.setDbType("imagedb");
		int roleCount = sysroleService.updateById(sysRole);
		sysRoleRightService.delRoleRight(id);
		if(roleCount>0){
    		map.put("code", 0);
    		map.put("msg", "删除成功");
		}else{
			map.put("code", 1);
			map.put("msg", "删除失败，请稍后重试！");
		}
		return map;
	}
	
	/**
	 * 新增角色信息
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/save.do",method=RequestMethod.POST)
	@ResponseBody
	public Object save(HttpServletRequest request,Model model){
		log.info("save");
		Map<String,Object> map = new HashMap<String,Object>();
		DbcontextHolder.setDbType("imagedb");
		String roleName = request.getParameter("roleName");
		String rights = request.getParameter("rights");
		
		
		
		SysRole sysRole = new SysRole();
		String roleid = UUid.getUUID();
		sysRole.setIsDel("0");
		sysRole.setCreateTime(new Date());
		sysRole.setRoleName(roleName);
		sysRole.setSysRoleKey(roleid);
		
		
		rights = rights.substring(1, rights.length()-1);
		rights = rights.replace("\"", "");
		String[] rightlist = rights.split(",");
		List<SysRoleRight> addRights = new ArrayList<SysRoleRight>();
		for(int i=0;i<rightlist.length;i++){
			SysRoleRight sysRoleRight = new SysRoleRight();
			sysRoleRight.setSys_role_right_key(UUid.getUUID());
			sysRoleRight.setCreate_time(new Date());
			sysRoleRight.setSys_role_key(roleid);
			sysRoleRight.setSys_right_key(rightlist[i]);
			sysRoleRight.setIs_del("0");
			addRights.add(sysRoleRight);
		}
		
		int roleCount = sysroleService.save(sysRole);
		int rightCount = sysRoleRightService.addRoleRight(addRights);
		if(roleCount>0&&rightCount>0){
    		map.put("code", 0);
    		map.put("msg", "");
		}else{
			map.put("code", 1);
			map.put("msg", "新增角色失败！");
		}
		return map;
	}
	
	/**
	 * 根据角色id修改角色信息
	 * @param id
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/updateById.do",method=RequestMethod.POST)
	@ResponseBody
	public Object updateById(HttpServletRequest request,Model model){
		log.info("updateById");
		Map<String,Object> map = new HashMap<String,Object>();
		DbcontextHolder.setDbType("imagedb");
		String roleName = request.getParameter("roleName");
		String rights = request.getParameter("rights");
		String sysRoleKey = request.getParameter("sysRoleKey");
		
		
		SysRole sysRole = new SysRole();
		sysRole.setModifyTime(new Date());
		sysRole.setRoleName(roleName);
		sysRole.setSysRoleKey(sysRoleKey);
		
		
		rights = rights.substring(1, rights.length()-1);
		rights = rights.replace("\"", "");
		String[] rightlist = rights.split(",");
		List<SysRoleRight> addRights = new ArrayList<SysRoleRight>();
		for(int i=0;i<rightlist.length;i++){
			SysRoleRight sysRoleRight = new SysRoleRight();
			sysRoleRight.setSys_role_right_key(UUid.getUUID());
			sysRoleRight.setCreate_time(new Date());
			sysRoleRight.setSys_role_key(sysRoleKey);
			sysRoleRight.setSys_right_key(rightlist[i]);
			sysRoleRight.setIs_del("0");
			addRights.add(sysRoleRight);
		}
		sysRoleRightService.delRoleRight(sysRoleKey);
		int roleCount = sysroleService.updateById(sysRole);
		int rightCount = sysRoleRightService.addRoleRight(addRights);
		if(roleCount>0&&rightCount>0){
    		map.put("code", 0);
    		map.put("msg", "");
		}else{
			map.put("code", 1);
			map.put("msg", "修改角色失败！");
		}
		return map;
	}
}
