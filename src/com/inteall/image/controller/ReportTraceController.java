package com.inteall.image.controller;

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

import com.inteall.image.pojo.ReportTraceWithBLOBs;
import com.inteall.image.service.ReportTraceService;
import com.inteall.image.util.UUid;


/** 
* @author 韩明君  
* @date 创建时间：2018年2月7日 下午1:17:38 
* @version 1.0 
* @parameter  
*/
@Controller
@RequestMapping("/reporttrace")
public class ReportTraceController {
	private static Logger log = Logger.getLogger(ReportTraceController.class.getName());
	@Resource
	private ReportTraceService reportTraceService;
	
	/**
	 * 根据报告明细的id查询报告明细信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/getById.do",method=RequestMethod.GET)
	@ResponseBody
	public Object getById(@RequestParam String id){
		log.info("getById");
		Map<String,Object> map = new HashMap<String,Object>();
		
		ReportTraceWithBLOBs reportTrace = reportTraceService.getById(id);
		map.put("reportTrace", reportTrace);
		return map;
	}
	
	/**
	 * 根据报告明细的id查询报告明细信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/getAll.do",method=RequestMethod.GET)
	@ResponseBody
	public Object getAll(HttpServletRequest request,Model model){
		log.info("getAll");
		Map<String,Object> map = new HashMap<String,Object>();
		
		ReportTraceWithBLOBs reportTrace = new ReportTraceWithBLOBs();
		
		List<ReportTraceWithBLOBs> reportTraceList = reportTraceService.getAll(reportTrace);
		map.put("报告明细数量", reportTraceList.size());
		map.put("reportTraceList", reportTraceList);
		return map;
	}
	
	/**
	 * 根据报告明细id删除报告明细
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delById.do",method=RequestMethod.GET)
	@ResponseBody
	public Object delById(@RequestParam String id){
		log.info("delById");
		Map<String,Object> map = new HashMap<String,Object>();
		
		int row = reportTraceService.delById(id);
		map.put("删除了的行数：", row);
		return map;
	}
	
	/**
	 * 新增报告明细信息
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/save.do",method=RequestMethod.GET)
	@ResponseBody
	public Object save(HttpServletRequest request,Model model){
		log.info("save");
		Map<String,Object> map = new HashMap<String,Object>();
		
		ReportTraceWithBLOBs reportTrace = new ReportTraceWithBLOBs();
//		reportTrace.setCheckView("食道钡剂下流通畅、未见充盈缺损、未见病理性狭窄及粘膜破坏。胃形态呈：鱼钩型。胃张力：中等。    胃角切迹在髂嵴：上。胃内潴留液：少量。    胃轮廓：正常。胃粘膜纹：正常。      胃动力：正常。胃蠕动：正常。       胃排空：正常。十二指肠球部：正常。十二指肠框部：正常。小肠运动增强，0.5小时结肠显影。");
//		reportTrace.setDiagnosisResult("小肠运动亢进，请结合临床。");
//		reportTrace.setUserId("1");
//		reportTrace.setUserName("hmj");
//		reportTrace.setIsDel("0");
//		reportTrace.setReportTraceKey(UUid.getUUID());
//		reportTrace.setCreatePerson("hmj");
//		reportTrace.setCreateTime(new Date());
//		
//		
//		int row = reportTraceService.save(reportTrace);
//		
//		map.put("新增的行数", row);
//		map.put("保存的信息", reportTrace);
		return map;
	}
	
	/**
	 * 根据报告明细id修改报告明细信息
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
		
		ReportTraceWithBLOBs reportTrace = new ReportTraceWithBLOBs();
		
		int row = reportTraceService.updateById(reportTrace);
		
		map.put("修改的行数", row);
		map.put("修改的信息", reportTrace);
		return map;
	}
}