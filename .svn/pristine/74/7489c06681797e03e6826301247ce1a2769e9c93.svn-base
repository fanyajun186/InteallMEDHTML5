package com.inteall.image.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inteall.image.pojo.CollectionMedicalRecord;
import com.inteall.image.pojo.MedicalRecord;
import com.inteall.image.pojo.ReadImage;
import com.inteall.image.pojo.SysUser;
import com.inteall.image.pojo.Video;
import com.inteall.image.pojo.VideoMedicalRecord;
import com.inteall.image.pojo.VideoTutorials;
import com.inteall.image.service.CollectionMedicalRecordService;
import com.inteall.image.service.VideoMedicalRecordService;
import com.inteall.image.service.VideoTutorialsService;
import com.inteall.image.util.DbcontextHolder;
import com.inteall.image.util.UUid;


@Controller
@RequestMapping("/videoTutorials")
public class VideoTutorialsController {
	private Logger logger = Logger.getLogger(VideoTutorialsController.class);
	@Resource
	private VideoTutorialsService videoTutorialsService;
	@Resource
	private VideoMedicalRecordService videoMedicalRecordService;
	@Resource
	private CollectionMedicalRecordService collectionMedicalRecordService;
	@RequestMapping(value="getAll.do",method=RequestMethod.GET)
	@ResponseBody
	public Object getAll(HttpServletRequest request,Model model){
		logger.info("getAll");
		Map<String,Object> map = new HashMap<String,Object>();
		VideoTutorials videoTutorials = new VideoTutorials();
		List<VideoTutorials> list = videoTutorialsService.getAll(videoTutorials);
		map.put("条数", list.size());
		map.put("list", list);
		return map;
	}
}
