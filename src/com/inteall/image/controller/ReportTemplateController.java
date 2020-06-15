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

import com.inteall.image.pojo.ReportTemplateWithBLOBs;
import com.inteall.image.service.ReportTemplateService;
import com.inteall.image.util.DbcontextHolder;


/** 
* @author 韩明君  
* @date 创建时间：2018年2月7日 下午1:17:38 
* @version 1.0 
* @parameter  
*/
@Controller
@RequestMapping("/reportTemplate")
public class ReportTemplateController {
	private static Logger log = Logger.getLogger(ReportTemplateController.class.getName());
	@Resource
	private ReportTemplateService ReportTemplateService;
	
	/**
	 * 根据报告模板的id查询报告模板信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/getById.do",method=RequestMethod.GET)
	@ResponseBody
	public Object getById(@RequestParam String id,HttpServletRequest request,Model model){
		log.info("getById");
		Map<String,Object> map = new HashMap<String,Object>();
		DbcontextHolder.setDbType("imagedb");
		
		ReportTemplateWithBLOBs ReportTemplate = ReportTemplateService.getById(id);
		if(ReportTemplate!=null){
		    map.put("code", 0);
		    map.put("ReportTemplate", ReportTemplate);
		}else{
		    map.put("code",1);
		    map.put("msg","获取病历模板详情时出错，请稍后重试或者联系管理员！");
		}
		
		return map;
	}
	
	/**
	 * 根据报告模板的id查询报告模板信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/getAll.do",method=RequestMethod.GET)
	@ResponseBody
	public Object getAll(HttpServletRequest request,Model model){
		log.info("getAll");
		Map<String,Object> map = new HashMap<String,Object>();
		DbcontextHolder.setDbType("imagedb");
		ReportTemplateWithBLOBs reportTemplate = new ReportTemplateWithBLOBs();
		
		List<ReportTemplateWithBLOBs> ReportTemplateList = ReportTemplateService.getAll(reportTemplate);
		map.put("报告模板数量", ReportTemplateList.size());
		map.put("ReportTemplateList", ReportTemplateList);
		return map;
	}
	
	/**
	 * 根据报告模板id删除报告模板
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delById.do",method=RequestMethod.GET)
	@ResponseBody
	public Object delById(@RequestParam String id){
		log.info("delById");
		Map<String,Object> map = new HashMap<String,Object>();
		 DbcontextHolder.setDbType("imagedb");
		int row = ReportTemplateService.delById(id);
		map.put("删除了的行数：", row);
		return map;
	}
	
	/**
	 * 新增报告模板信息
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
		ReportTemplateWithBLOBs reportTemplate = new ReportTemplateWithBLOBs();
		
		int row = ReportTemplateService.save(reportTemplate);
		
		map.put("新增的行数", row);
		map.put("保存的患者信息", reportTemplate);
		return map;
	}
	
	/**
	 * 根据报告模板id修改报告模板信息
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
		
		ReportTemplateWithBLOBs reportTemplate = new ReportTemplateWithBLOBs();
		reportTemplate.setModifyPerson("hmj修改");
		reportTemplate.setReportTemplateKey(id);
		reportTemplate.setModifyTime(new Date());
		int row = ReportTemplateService.updateById(reportTemplate);
		
		map.put("修改的行数", row);
		map.put("修改的患者信息", reportTemplate);
		return map;
	}
}
