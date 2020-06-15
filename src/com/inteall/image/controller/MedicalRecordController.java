package com.inteall.image.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.dcm4che2.data.DicomObject;
import org.dcm4che2.data.Tag;
import org.dcm4che2.io.DicomInputStream;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.inteall.image.pojo.MedicalRecord;
import com.inteall.image.pojo.ReadImage;
import com.inteall.image.pojo.ReadImage_Conferee;
import com.inteall.image.pojo.ReadImage_Record;
import com.inteall.image.pojo.SysUser;
import com.inteall.image.pojo.ZoomManage;
import com.inteall.image.service.MedicalRecordService;
import com.inteall.image.service.ReadImageService;
import com.inteall.image.service.ReadImage_ConfereeService;
import com.inteall.image.service.ZoomManageService;
import com.inteall.image.util.DbcontextHolder;
import com.inteall.image.util.UUid;
import com.inteall.image.util.Util;


/** 
* @author 韩明君  
* @date 创建时间：2018年2月7日 下午1:17:38 
* @version 1.0 
* @parameter  
*/
@Controller
@RequestMapping("/medicalrecord")
public class MedicalRecordController {
	private static Logger log = Logger.getLogger(MedicalRecordController.class.getName());
	@Resource
	private MedicalRecordService medicalRecordService;
	 @Resource
	  private ReadImageService readImageService;
	  @Resource
	  private ZoomManageService ZoomManageService;
	  @Resource
	  private ReadImage_ConfereeService ReadImage_ConfereeService;
	/**
	 * 读片会详情：根据用户的id查询用户信息
	 * @param id
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value="/getreadimageById.do",method=RequestMethod.GET)
	public Object getreadimageById(@RequestParam String stuuid,String readimagekey,HttpServletRequest request,Model model) throws ParseException{
		log.info("getreadimageById");
		DbcontextHolder.setDbType("imagedb");
		MedicalRecord medicalRecord = medicalRecordService.getById(stuuid);
		model.addAttribute("stuuid", stuuid);
		model.addAttribute("medicalRecord", medicalRecord);
		return "readimage/bingli/index";
	}
	/**
	 * 读片会详情跳转到上传附件界面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/bingliUpload.do",method=RequestMethod.GET)
	public Object bingliUpload(@RequestParam String stuuid,HttpServletRequest request,Model model){
	    model.addAttribute("stuuid", stuuid);
		return "readimage/bingli/upload_progress";
	}
	/**
	 * 群讨论详情：根据用户的id查询用户信息
	 * @param id
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value="/getqunxqById.do",method=RequestMethod.GET)
	public Object getqunxqById(@RequestParam String stuuid,HttpServletRequest request,Model model) throws ParseException{
		log.info("getqunxqById");
		DbcontextHolder.setDbType("imagedb");
		MedicalRecord medicalRecord = medicalRecordService.getById(stuuid);
		model.addAttribute("stuuid", stuuid);
		model.addAttribute("medicalRecord", medicalRecord);
		return "quntaolun/xiangqing/index";
	}
	/**
	 * 群详情：跳转到上传附件界面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/qunxqUpload.do",method=RequestMethod.GET)
	public Object qunxqUpload(@RequestParam String stuuid,HttpServletRequest request,Model model){
		model.addAttribute("stuuid", stuuid);
		return "quntaolun/xiangqing/upload_progress";
	}
	
}
