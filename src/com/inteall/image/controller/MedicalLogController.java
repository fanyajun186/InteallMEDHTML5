package com.inteall.image.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inteall.image.pojo.MedicalLog;
import com.inteall.image.pojo.ReadImage_Record;
import com.inteall.image.pojo.SysUser;
import com.inteall.image.service.MedicalLogService;
import com.inteall.image.util.DbcontextHolder;
import com.inteall.image.util.UUid;

@Controller
@RequestMapping("medicallog")
public class MedicalLogController {
	private Logger log =Logger.getLogger(CollectionController.class);
	@Resource
	private MedicalLogService medicalLogService;
	/**
	   * 查询详情日志列表
	   * @param 
	   * @return
	   */
	  @RequestMapping(value="/getByStuuid.do", method=RequestMethod.POST)
	  @ResponseBody
	  public Object getByStuuid(HttpServletRequest request, Model model) {
		log.info("getByStuuid");
		Map<String,Object> queryMap = new HashMap<String,Object>();
		
		String stuuid = request.getParameter("stuuid");
		
		DbcontextHolder.setDbType("imagedb");
		//MedicalLog medicalLog = new MedicalLog();
		//medicalLog.setStudyId(stuuid);
		int page = Integer.parseInt(request.getParameter("page"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		
		queryMap.put("studyId", stuuid);
		
		//先查询满足条件的数据有多少条
		int count = medicalLogService.getCount(queryMap);
		
		queryMap.put("start", (page-1)*limit);
		queryMap.put("pageSize",limit);
		
		List<MedicalLog> MedicalLogList = medicalLogService.getByStuuid(queryMap);
		
		Map<String,Object> map = new HashMap<String,Object>();
		if(MedicalLogList!=null&&MedicalLogList.size()>0){
			map.put("code", 0);
			map.put("msg", "");
			map.put("count", count);
			map.put("data", MedicalLogList);
		}else{
			map.put("code", 1);
			map.put("msg", "<img style='width:228px;height:228px' src='../img/noneicon.png'>");
		}
		return map;
	  }
	  @RequestMapping("save.do")
	  @ResponseBody
	  public Object save(HttpServletRequest request, Model model) {
		  log.info("save");
		  Map<String,Object> map = new HashMap<String,Object>();
		  HttpSession session = request.getSession();
	      SysUser sysUser =  (SysUser) session.getAttribute("SysUser");
	      String stuuid = request.getParameter("stuuid");
		  MedicalLog medicalLog = new MedicalLog();
	      	medicalLog.setSyslogKey(UUid.getUUID());
	      	medicalLog.setLogtime(new Date());
	      	medicalLog.setLogtype("报告打印");
	      	medicalLog.setLoguser(sysUser.getUserName());
	      	medicalLog.setLoguserCode(sysUser.getLoginName());
	      	medicalLog.setCreatePerson(sysUser.getSysuserKey());
	      	medicalLog.setCreateTime(new Date());
	      	medicalLog.setStudyId(stuuid);
	      	medicalLog.setIsDel("0");
	      	int n=medicalLogService.save(medicalLog);
	      	if(n > 0){
	      		map.put("code", 0);
	      	}else{
	      		map.put("code", 1);
	              map.put("msg", "插入日志出错，请稍后重试或者联系管理员！");
	      	}
		return model;
	  }
}
