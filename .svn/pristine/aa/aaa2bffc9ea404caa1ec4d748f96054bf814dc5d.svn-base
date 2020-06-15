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
import org.springframework.web.bind.annotation.ResponseBody;

import com.inteall.image.pojo.SysLog;
import com.inteall.image.service.SysLogService;
import com.inteall.image.util.DbcontextHolder;


/** 
* @author 韩明君  
* @date 创建时间：2018年3月28日 下午1:17:38 
* @version 1.0 
* @parameter  
*/
@Controller
@RequestMapping("/syslog")
public class SysLogController {
	private static Logger log = Logger.getLogger(SysLogController.class.getName());
	@Resource
	private SysLogService syslogService;
	
	
	/**
	 * 查询日志信息
	 * @return
	 */
	@RequestMapping(value="/getAll.do",method=RequestMethod.POST)
	@ResponseBody
	public Object getAll(HttpServletRequest request,Model model){
		log.info("getAll");
		int page = Integer.parseInt(request.getParameter("page"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		String syslogtime = request.getParameter("syslogtime");
		String[] time = syslogtime.split("~");
		String beginDate = null;
		String endDate = null;
		if(time.length==2){
			beginDate = time[0];
			endDate = time[1];
		}
		
		DbcontextHolder.setDbType("imagedb");
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		HashMap<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("beginDate", beginDate);
		queryMap.put("endDate", endDate);
		
		//先查询满足条件的数据有多少条
		int count = syslogService.getCount(queryMap);
		
		
		queryMap.put("curr", (page-1)*limit);
		queryMap.put("limit",limit);
		List<SysLog> SysLogList = syslogService.getAll(queryMap);
		if(SysLogList!=null&&SysLogList.size()>0){
    		map.put("code", 0);
    		map.put("msg", "");
    		map.put("count", count);
    		map.put("data", SysLogList);
		}else{
			map.put("code", 1);
			map.put("msg", "<img style='width:228px;height:228px' src='img/noneicon.png'>");
		}
		return map;
	}
	
}
