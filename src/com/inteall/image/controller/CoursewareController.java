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

import com.inteall.image.pojo.Courseware;
import com.inteall.image.pojo.VideoTutorials;
import com.inteall.image.service.CoursewareService;
import com.inteall.image.service.VideoTutorialsService;
import com.inteall.image.util.UUid;


@Controller
@RequestMapping("/courseware")
public class CoursewareController {
	private Logger logger = Logger.getLogger(CoursewareController.class);
	@Resource
	private CoursewareService coursewareService;
	@Resource
	private VideoTutorialsService videoTutorialsService;
	@RequestMapping(value="/selectByPrimaryKey.do",method=RequestMethod.GET)
	@ResponseBody
	public Object selectByPrimaryKey(@RequestParam String id){
		logger.info("selectByPrimaryKey");
		Map<String, Object> map = new HashMap<String,Object>();
		Courseware courseware = coursewareService.selectByPrimaryKey(id);
		map.put("courseware", courseware);
		return map;
	}
	@RequestMapping(value="getAll.do",method=RequestMethod.GET)
	@ResponseBody
	public Object getAll(HttpServletRequest request,Model model){
		logger.info("getAll");
		Map<String, Object> map = new HashMap<String,Object>();
		Courseware courseware = new Courseware();
		List<Courseware> list = coursewareService.getAll(courseware);
		map.put("条数", list.size());
		map.put("collectionList", list);
		return map;
		
	}
	@RequestMapping(value="/updateByPrimaryKey.do",method=RequestMethod.GET)
	@ResponseBody
	public Object updateByPrimaryKey(@RequestParam String id,HttpServletRequest request,Model model){
		logger.info("updateByPrimaryKey");
		Map<String,Object> map = new HashMap<String,Object>();
		Courseware courseware = new Courseware();
		courseware.setCoursewareKey(id);
		courseware.setCoursewareName("aab修改");
	    courseware.setCoursewareDesc("bba修改");
	    courseware.setCoursewareSize((long) 23423);
	    courseware.setCoursewareCreateTime(new Date());
	    courseware.setCoursewareCreatePerson("cbb");
	    courseware.setCoursewareModifyTime(new Date());
	    courseware.setCoursewareModifyPerson("bbc");
	    courseware.setVideoTurorialsKey("22");
	    courseware.setCreatePerson("ccd");
	    courseware.setCreateTime(new Date());
	    courseware.setModifyTime(new Date());
	    courseware.setModifyPerson("dde");
	    courseware.setDelTime(new Date());
	    courseware.setDelPerson("eef");
	    courseware.setCoursewareRemark1("ffg修改");
	    courseware.setCoursewareRemark2("ggh修改");
	    courseware.setCoursewareRemark3("hhi修改");
	    int row = coursewareService.updateByPrimaryKey(courseware);
	    map.put("修改行数", row);
	    map.put("修改的信息", courseware);
		return map;
	}
	
	
	@RequestMapping(value="/updateByPrimaryKeySelective.do",method=RequestMethod.GET)
	@ResponseBody
	public Object updateByPrimaryKeySelective(@RequestParam String id,HttpServletRequest request,Model model){
		logger.info("updateByPrimaryKeySelective");
		Map<String,Object> map = new HashMap<String,Object>();
		Courseware courseware = coursewareService.selectByPrimaryKey(id);
		//Courseware courseware = new Courseware();
		courseware.setCoursewareKey(id);
		//courseware.setCoursewareName("aab修改");
	   // courseware.setCoursewareDesc("bba修改");
	    //courseware.setCoursewareSize((long) 23423);
	   // courseware.setCoursewareCreateTime(new Date());
	   // courseware.setCoursewareCreatePerson("cbb");
	   // courseware.setCoursewareModifyTime(new Date());
	   // courseware.setCoursewareModifyPerson("bbc");
	   // courseware.setVideoTurorialsKey(22);
	   // courseware.setCreatePerson("ccd");
	  //  courseware.setCreateTime(new Date());
	  //  courseware.setModifyTime(new Date());
	  //  courseware.setModifyPerson("dde");
	   // courseware.setDelTime(new Date());
	  //  courseware.setDelPerson("eef");
	  //  courseware.setCoursewareRemark1("ffg修改");
	  //  courseware.setCoursewareRemark2("ggh修改");
	    courseware.setCoursewareRemark3("hhi修改");
	    int row = coursewareService.updateByPrimaryKeySelective(courseware);
	    map.put("修改行数", row);
	    map.put("修改的信息", courseware);
		return map;
	}
	
}
