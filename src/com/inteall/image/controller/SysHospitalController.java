package com.inteall.image.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.inteall.image.pojo.SysHospital;
import com.inteall.image.service.MessageLogService;
import com.inteall.image.service.SysHospitalService;
import com.inteall.image.util.DbcontextHolder;
import com.inteall.image.util.UUid;


/** 
* @author 韩明君  
* @date 创建时间：2018年2月26日 下午1:17:38 
* @version 1.0 
* @parameter  
*/
@Controller
@RequestMapping("/syshospital")
public class SysHospitalController {
	private static Logger log = Logger.getLogger(SysHospitalController.class.getName());
	@Resource
	private SysHospitalService sysHospitalService;
	
	@Resource
	private MessageLogService messageLogService;
	
	@RequestMapping(value="/redirectAdd.do",method=RequestMethod.GET)
	public Object redirectAdd(HttpServletRequest request,Model model){
		return "xitongshezhi/syshospital_add";
	}
	
	/**
	 * 根据医院的id查询医院信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/getById.do",method=RequestMethod.GET)
	public Object getById(@RequestParam String id,HttpServletRequest request,Model model){
		log.info("getById");
		
		Gson gson = new Gson();
		DbcontextHolder.setDbType("imagedb");
		SysHospital sysHospital = sysHospitalService.getById(id);
		model.addAttribute("sysHospital", gson.toJson(sysHospital));
		return "xitongshezhi/syshospital_edit";
	}
	/**
	 * 根据医院的id查询医院信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/getHospital.do",method=RequestMethod.POST)
	@ResponseBody
	public Object getHospital(HttpServletRequest request,Model model){
		log.info("getHospital");
		Map<String,Object> map = new HashMap<String,Object>();
		DbcontextHolder.setDbType("imagedb");
		
		List<SysHospital> sysHospitalList = sysHospitalService.getHospital();
		if(sysHospitalList!=null&&sysHospitalList.size()>0){
    		map.put("code", 0);
    		map.put("msg", "");
    		map.put("sysHospitalList", sysHospitalList);
		}else{
			map.put("code", 1);
			map.put("msg", "<img style='width:228px;height:228px' src='img/noneicon.png'>");
		}
		return map;
	}
	/**
	 * 根据医院的id查询医院信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/getAll.do",method=RequestMethod.POST)
	@ResponseBody
	public Object getAll(HttpServletRequest request,Model model){
		log.info("getAll");
		int page = Integer.parseInt(request.getParameter("page"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		String hospitalname = request.getParameter("hospitalname");
		Map<String,Object> map = new HashMap<String,Object>();
		DbcontextHolder.setDbType("imagedb");
		SysHospital sysHospital = new SysHospital();
		
		sysHospital.setHospName(hospitalname);
		//先查询满足条件的数据有多少条
		int count = sysHospitalService.getCount(sysHospital);
		
		//再查当前页中的数据
		sysHospital.setLimit(limit);
		sysHospital.setCurr((page-1)*limit);
		List<SysHospital> sysHospitalList = sysHospitalService.getAll(sysHospital);
		if(sysHospitalList!=null&&sysHospitalList.size()>0){
    		map.put("code", 0);
    		map.put("msg", "");
    		map.put("count", count);
    		map.put("data", sysHospitalList);
		}else{
			map.put("code", 1);
			map.put("msg", "<img style='width:228px;height:228px' src='img/noneicon.png'>");
		}
		return map;
	}
	
	/**
	 * 根据医院id删除医院
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delById.do",method=RequestMethod.GET)
	@ResponseBody
	public Object delById(@RequestParam String id){
		log.info("delById");
		Map<String,Object> map = new HashMap<String,Object>();
		DbcontextHolder.setDbType("imagedb");
		SysHospital sysHospital = new SysHospital();
		sysHospital.setSysHospitalKey(id);
		sysHospital.setIsDel("1");
		sysHospital.setDelTime(new Date());
		
		int count = sysHospitalService.updateById(sysHospital);
		if(count>0){
    		map.put("code", 0);
    		map.put("msg", "删除成功！");
		}else{
			map.put("code", 1);
			map.put("msg", "删除失败！");
		}
		return map;
	}
	
	/**
	 * 新增医院信息
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/save.do",method=RequestMethod.POST)
	@ResponseBody
	public Object save(HttpServletRequest request,Model model){
		log.info("save");
		
		String json = request.getParameter("json");
		Gson gson = new Gson();
		SysHospital sysHospital = gson.fromJson(json, SysHospital.class);
		sysHospital.setSysHospitalKey(UUid.getUUID());
		sysHospital.setCreateTime(new Date());
		sysHospital.setIsDel("0");
		
		Map<String,Object> map = new HashMap<String,Object>();
		DbcontextHolder.setDbType("imagedb");
		
		int count = sysHospitalService.save(sysHospital);
		
		if(count>0){
    		map.put("code", 0);
    		map.put("msg", "");
		}else{
			map.put("code", 1);
			map.put("msg", "保存失败！");
		}
		return map;
	}
	
	/**
	 * 根据医院id修改医院信息
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
		String json = request.getParameter("json");
		Gson gson = new Gson();
		SysHospital sysHospital = gson.fromJson(json, SysHospital.class);
		
		sysHospital.setModifyTime(new Date());
		int count = sysHospitalService.updateById(sysHospital);
		
		if(count>0){
    		map.put("code", 0);
    		map.put("msg", "");
		}else{
			map.put("code", 1);
			map.put("msg", "保存失败！");
		}
		return map;
	}
	
	//查询日志信息列表
	@RequestMapping(value="/messageLogList.do")
	@ResponseBody
	public Map<String,Object> messageLogList(HttpServletRequest request,int page,int limit){
		Map<String,Object> map = new HashMap<String, Object>();
		Map<String,Object> search = new HashMap<String, Object>();
		DbcontextHolder.setDbType("imagedb");
		
		String name_phone = request.getParameter("name_phone");
		String logtime = request.getParameter("infoLogtime");
		String startTime = "";
		String endTime = "";
		if(!StringUtils.isEmpty(logtime)){
			startTime = logtime.split("~")[0].replace(" ", "");
			endTime = logtime.split("~")[1].replace(" ", "");
		}
		search.put("name_phone", name_phone);
		search.put("startTime", startTime);
		search.put("endTime", endTime);
		
		//先查询满足条件的数据有多少条
		int count = messageLogService.getMessageCount(search);
		//再查当前页中的数据
		search.put("start", (page-1)*limit);
		search.put("pageSize",limit);
		List<Map> list = messageLogService.getMessageList(search);
    	
		map.put("code", 0);
    	map.put("count", count);
    	map.put("data", list);
		
		return map;
	} 
	
	//删除短息日志
	@RequestMapping(value="/delMessageInfo.do")
	@ResponseBody
	public Map<String,Object> delMessageInfo(@RequestParam("infoId") int infoId){
		Map<String,Object> result = new HashMap<String, Object>();
		int row = messageLogService.delMessageInfo(infoId);
		if(row>0){
			result.put("result", true);
			result.put("msg", "操作成功！");
		}else {
			result.put("result", false);
			result.put("msg", "操作失败！");
		}
		return result;
	}
	
	
}
