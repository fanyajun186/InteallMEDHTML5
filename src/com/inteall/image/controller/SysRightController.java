package com.inteall.image.controller;

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

import com.inteall.image.pojo.SysRight;
import com.inteall.image.service.SysRightService;
import com.inteall.image.util.DbcontextHolder;


/** 
* @author 韩明君  
* @date 创建时间：2018年2月7日 下午1:17:38 
* @version 1.0 
* @parameter  
*/
@Controller
@RequestMapping("/sysright")
public class SysRightController {
	private static Logger log = Logger.getLogger(SysRightController.class.getName());
	@Resource
	private SysRightService sysRightService;
	
	/**
	 * 根据id查询权限信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/getById.do",method=RequestMethod.GET)
	@ResponseBody
	public Object getById(@RequestParam String id){
		log.info("getById");
		Map<String,Object> map = new HashMap<String,Object>();
		DbcontextHolder.setDbType("imagedb");
		SysRight sysRight = sysRightService.getById(id);
		map.put("sysRight", sysRight);
		return map;
	}
	
	/**
	 * 查询所有权限信息
	 * @return
	 */
	@RequestMapping(value="/getRight.do",method=RequestMethod.GET)
	@ResponseBody
	public Object getRight(HttpServletRequest request,Model model){
		log.info("getRight");
		Map<String,Object> map = new HashMap<String,Object>();
		DbcontextHolder.setDbType("imagedb");
		
		List<SysRight> sysRightList = sysRightService.getRight();
		map.put("sysRightList", sysRightList);
		return map;
	}
	
	/**
	 * 根据id删除权限
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delById.do",method=RequestMethod.GET)
	@ResponseBody
	public Object delById(@RequestParam String id){
		log.info("delById");
		Map<String,Object> map = new HashMap<String,Object>();
		DbcontextHolder.setDbType("imagedb");
		int row = sysRightService.delById(id);
		map.put("删除了的行数：", row);
		return map;
	}
	
	/**
	 * 新增权限信息
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/save.do",method=RequestMethod.GET)
	@ResponseBody
	public Object save(HttpServletRequest request,Model model){
		log.info("save");
		Map<String,Object> map = new HashMap<String,Object>();
		DbcontextHolder.setDbType("imagedb");
		SysRight sysRight = new SysRight();
		int row = sysRightService.save(sysRight);
		
		map.put("新增的行数", row);
		map.put("保存的信息", sysRight);
		return map;
	}
	
	/**
	 * 根据用户id修改信息
	 * @param id
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/updateById.do",method=RequestMethod.GET)
	@ResponseBody
	public Object updateById(@RequestParam String id,HttpServletRequest request,Model model){
		log.info("updateById");
		Map<String,Object> map = new HashMap<String,Object>();
		DbcontextHolder.setDbType("imagedb");
		SysRight sysRight = new SysRight();
		int row = sysRightService.updateById(sysRight);
		
		map.put("修改的行数", row);
		map.put("修改的信息", sysRight);
		return map;
	}
}
