package com.inteall.image.controller;

import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inteall.image.pojo.CollectionMedicalRecord;
import com.inteall.image.pojo.GroupMeidicalRecord;
import com.inteall.image.pojo.MedicalRecord;
import com.inteall.image.pojo.ReadImage_Record;
import com.inteall.image.pojo.SysUser;
import com.inteall.image.pojo.VideoMedicalRecord;
import com.inteall.image.service.BigAntService;
import com.inteall.image.service.CollectionMedicalRecordService;
import com.inteall.image.service.ReadImage_RecordService;
import com.inteall.image.service.VideoMedicalRecordService;
import com.inteall.image.util.BigAntUtil;
import com.inteall.image.util.DbcontextHolder;
import com.inteall.image.util.UUid;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/videoMedicalRecordController")
public class VideoMedicalRecordController {
	private static Logger log = Logger.getLogger(VideoMedicalRecordController.class.getName());
	@Resource
	private VideoMedicalRecordService videoMedicalRecordService;
	@Resource
	private CollectionMedicalRecordService collectionMedicalRecordService;
	@Resource
	private ReadImage_RecordService readImage_RecordService;
	@Resource
	private BigAntService bigAntService;
	private String ssid = "E6300170-E5B0-B072-E18D-74A870934D48";
	private String uid = "116";
	private String uname = "电子客服";
	private BigAntUtil bigAntUtil = new BigAntUtil(ssid, uid, uname);
	/**
	   * 查询读片会的病历列表
	   * @param 
	   * @return
	   */
	  @RequestMapping(value="/getRecordVideoKey.do",method=RequestMethod.POST)
	  @ResponseBody
	  public Object getRecordVideoKey(HttpServletRequest request,HttpServletResponse response){
	    	DbcontextHolder.setDbType("imagedb");
	    	String videoKey = request.getParameter("videoKey");
	    	int page = Integer.parseInt(request.getParameter("page"));
	    	int limit = Integer.parseInt(request.getParameter("limit"));
	    	String username = request.getParameter("username");
			String sex = request.getParameter("sex");
			String modality = request.getParameter("modality");
			String studytime = request.getParameter("studytime");
			String beginDate = null;
			String endDate = null;
			if(studytime!=null){
	    		String[] time = studytime.split("~");
	    		
	    		if(time.length==2){
	    			beginDate = time[0];
	    			endDate = time[1];
	    		}
			}
			HashMap<String, Object> queryMap = new HashMap<String, Object>();
			queryMap.put("beginDate", beginDate);
			queryMap.put("endDate", endDate);
			queryMap.put("username", username);
			queryMap.put("sex", sex);
			queryMap.put("modality", modality);
			queryMap.put("videoKey", videoKey);
			
	    	//先查询满足条件的数据有多少条
	    	int count = videoMedicalRecordService.getRecordCount(queryMap);
	    	queryMap.put("curr", (page-1)*limit);
			queryMap.put("limit",limit);
	    	List<MedicalRecord> Video_RecordList = videoMedicalRecordService.getRecordByVideoKey(queryMap);
	    	Map<String,Object> map = new HashMap<String,Object>();
	    	if(Video_RecordList!=null&&Video_RecordList.size()>0){
	    		map.put("code", 0);
	    		map.put("msg", "");
	    		map.put("count", count);
	    		map.put("data", Video_RecordList);
	    	}else{
	    		map.put("code", 1);
	    		map.put("msg", "<img style='width:228px;height:228px' src='img/noneicon.png'>");
	    	}
			return map;
	    }
	  /**
	   * 跳转到群讨论分享界面
	   * @param 
	   * @return
	   */
	  @RequestMapping(value="/getByShareGroupId.do", method=RequestMethod.GET)
	  public Object getByShareGroupId(@RequestParam String medicalRecordKey, HttpServletRequest request, Model model){
		  log.info("getByShareGroupId");
		DbcontextHolder.setDbType("imagedb");
		model.addAttribute("medicalRecordKey", medicalRecordKey);
		return "shipinjiaoxue/share/quntaolunshare";
	  }
	  @RequestMapping(value="/getSaveShareGroup.do", method=RequestMethod.POST)
	  @ResponseBody
	  public Object getSaveShareGroup(HttpServletRequest request, Model model){
		  log.info("getSaveShareGroup");
		  Map<String,Object> map = new HashMap<String,Object>();
		  DbcontextHolder.setDbType("imagedb");
		  HttpSession session = request.getSession();
		  SysUser sysUser =  (SysUser) session.getAttribute("SysUser");
		  String json = request.getParameter("json");
		  JSONObject returnData = JSONObject.fromObject(json);
		  String medicalRecordKey = returnData.getString("medicalRecordKey");
		  String group_id = returnData.getString("group");
		  GroupMeidicalRecord groupMeidicalRecord = new GroupMeidicalRecord();
		  groupMeidicalRecord.setGroupMeidicalRecordKey(UUid.getUUID());
		  groupMeidicalRecord.setGroupKey(group_id);
		  groupMeidicalRecord.setMedicalRecordKey(medicalRecordKey);
		  groupMeidicalRecord.setCreatePerson(sysUser.getSysuserKey());
		  String MeidicalRecord = bigAntService.getRecordById(groupMeidicalRecord);
		  if(MeidicalRecord.equals(medicalRecordKey)){
			 map.put("code", 1);
			 map.put("msg", "该病例已存在!");
		  }else{
			  int groupMeidicalRecordCount = bigAntService.save(groupMeidicalRecord);
			  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  	  String content = "【汇智精英】"+sysUser.getUserName()+"老师，于"+sdf.format(new Date())+"分享了一个病历到群讨论，请您参与讨论！";
		  	  bigAntUtil.send_group("service", group_id, "群讨论通知", content);
			  if(groupMeidicalRecordCount>0){
				map.put("code", 0);
			  }else{
				map.put("code", 1);
				map.put("msg", "保存失败");
			  }
		  }
		  return map;
	  }
	  /**
	   * 跳转到分享读片会界面
	   * @param 
	   * @return
	   */
	  @RequestMapping(value="/getByShareRreadimageId.do", method=RequestMethod.GET)
	  public Object getByShareRreadimageId(@RequestParam String medicalRecordKey, HttpServletRequest request, Model model){
		log.info("getByShareRreadimageId");
		DbcontextHolder.setDbType("imagedb");
		model.addAttribute("medicalRecordKey", medicalRecordKey);
		return "shipinjiaoxue/share/dupianhuishare";
	  }
	  @RequestMapping(value="/getSaveShareRreadimage.do", method=RequestMethod.POST)
	  @ResponseBody
	  public Object getSaveShareRreadimage(HttpServletRequest request, Model model){
		  log.info("getSaveShareRreadimage");
		  Map<String,Object> map = new HashMap<String,Object>();
		  DbcontextHolder.setDbType("imagedb");
		  HttpSession session = request.getSession();
		  SysUser sysUser =  (SysUser) session.getAttribute("SysUser");
		  String json = request.getParameter("json");
		  JSONObject returnData = JSONObject.fromObject(json);
		  String medicalRecordKey = returnData.getString("medicalRecordKey");
		  String readimagekey = returnData.getString("readimagekey");
		  ReadImage_Record readImageRecord = new ReadImage_Record();
		  readImageRecord.setReadimage_record_key(UUid.getUUID());
		  readImageRecord.setReadimage_key(readimagekey);
		  readImageRecord.setRecord_key(medicalRecordKey);
		  readImageRecord.setAppend_time(new Date());
		  readImageRecord.setSysuser_key(sysUser.getSysuserKey());
		  String MeidicalRecord = readImage_RecordService.getRecordById(readImageRecord);
		  if(MeidicalRecord!=null&&MeidicalRecord.equals(medicalRecordKey)){
				 map.put("code", 1);
				 map.put("msg", "该病例已存在!");
			  }else{
				  int readImageRecordCount = readImage_RecordService.save(readImageRecord);
				  if(readImageRecordCount>0){
					map.put("code", 0);
				  }else{
					map.put("code", 1);
					map.put("msg", "保存失败!");
				  }
		  }
		  return map;
	  }
	  /**
	   * 跳转到分享收藏夹界面
	   * @param 
	   * @return
	   */
	  @RequestMapping(value="/getByShareCollectionId.do", method=RequestMethod.GET)
	  public Object getByShareCollectionId(@RequestParam String medicalRecordKey, HttpServletRequest request, Model model){
		  DbcontextHolder.setDbType("imagedb");
		  model.addAttribute("medicalRecordKey", medicalRecordKey);
		  return "shipinjiaoxue/share/shoucangjiashare";
	  }
	  @RequestMapping(value="/getSaveShareCollection.do", method=RequestMethod.POST)
	  @ResponseBody
	  public Object getSaveShareCollection(HttpServletRequest request, Model model){
		  Map<String,Object> map = new HashMap<String,Object>();
		  DbcontextHolder.setDbType("imagedb");
		  HttpSession session = request.getSession();
		  SysUser sysUser =  (SysUser) session.getAttribute("SysUser");
		  String json = request.getParameter("json");
		  JSONObject returnData = JSONObject.fromObject(json);
		  String medicalRecordKey = returnData.getString("medicalRecordKey");
		  String collectionKey = returnData.getString("collectionKey");
		  CollectionMedicalRecord collectionMedicalRecord = new CollectionMedicalRecord();
		  collectionMedicalRecord.setCollection_medical_record_key(UUid.getUUID());
		  collectionMedicalRecord.setCollection_key(collectionKey);
		  collectionMedicalRecord.setMedical_record_key(medicalRecordKey);
		  collectionMedicalRecord.setCreate_time(new Date());
		  collectionMedicalRecord.setCreate_person(sysUser.getLoginName());
		  collectionMedicalRecord.setIs_del("0");
		  String MeidicalRecord = collectionMedicalRecordService.getRecordById(collectionMedicalRecord);
		  if(MeidicalRecord!=null&&MeidicalRecord.equals(medicalRecordKey)){
				 map.put("code", 1);
				 map.put("msg", "该病例已存在!");
			  }else{
				  int CollectionCount= collectionMedicalRecordService.insertCollectionMedicalRecord(collectionMedicalRecord);
				  if(CollectionCount>0){
					map.put("code", 0);
				  }else{
					map.put("code", 1);
					map.put("msg", "保存失败!");
				  }
		  }
		  return map;
	  }
}
