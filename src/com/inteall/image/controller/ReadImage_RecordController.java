package com.inteall.image.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
import com.inteall.image.service.VideoService;
import com.inteall.image.util.BigAntUtil;
import com.inteall.image.util.DbcontextHolder;
import com.inteall.image.util.UUid;

import net.sf.json.JSONObject;



/**
 * @author 李进刚
 * @date 2018年3月9日 上午8:59:42
 * @version 1.0 
 * @parameter 
 */

@Controller
@RequestMapping("/readimage_record")
public class ReadImage_RecordController {
  private static Logger log = Logger.getLogger(ReadImage_RecordController.class.getName());
  @Resource
  private ReadImage_RecordService readImage_RecordService;
  @Resource
  private VideoMedicalRecordService videoMedicalRecordService;
  @Resource
  private CollectionMedicalRecordService collectionMedicalRecordService;
  @Resource
  private BigAntService bigAntService;
  private String ssid = "E6300170-E5B0-B072-E18D-74A870934D48";
  private String uid = "116";
  private String uname = "电子客服";
  private BigAntUtil bigAntUtil = new BigAntUtil(ssid, uid, uname);
  @RequestMapping(value="/redirectAdd", method=RequestMethod.POST)
  public Object redirectAdd(HttpServletRequest request, Model model){
	log.info("redirectAdd");
	
	
	return "readimage/add";
  }
  
  
  
  /**
   * 根据stuyid删除读片会中的病历
   * 删的是关联表，不是病历表
   * @param 
   * @return
   */
  @RequestMapping(value="/delByStuuid.do", method=RequestMethod.GET)
  @ResponseBody
  public Object delByStuuid(@RequestParam String stuuid,@RequestParam String readimagekey, HttpServletRequest request, Model model) throws ParseException {
	log.info("delByStuuid");
	HashMap<String, String> getParamMap = new HashMap<String, String>();
	DbcontextHolder.setDbType("imagedb");
	getParamMap.put("stuuid", stuuid);
	getParamMap.put("readimagekey", readimagekey);
	int row = readImage_RecordService.delByStuuid(getParamMap);
	
	Map<String,Object> map = new HashMap<String,Object>();
	if(row>0){
	  map.put("code", 0);
	}else{
	  map.put("code", 1);
	  map.put("msg", "删除失败，请稍后重试！");
	}
	return map;
  }
  
    
  /**
   * 查询读片会的病历列表
   * @param 
   * @return
   */
  @RequestMapping(value="/getByReadImageKey.do", method=RequestMethod.POST)
  @ResponseBody
  public Object getByReadImageKey(HttpServletRequest request, Model model) {
	log.info("getByReadImageKey");
	String readimagekey = request.getParameter("readimagekey");
	int page = Integer.parseInt(request.getParameter("page"));
	int limit = Integer.parseInt(request.getParameter("limit"));
	
	DbcontextHolder.setDbType("imagedb");
	ReadImage_Record getImage_Record = new ReadImage_Record();
	getImage_Record.setReadimage_key(readimagekey);
	
	//先查询满足条件的数据有多少条
	int count = readImage_RecordService.getCount(getImage_Record);
	
	getImage_Record.setLimit(limit);
	getImage_Record.setCurr((page-1)*limit);
    
	List<MedicalRecord> ReadImage_RecordList = readImage_RecordService.getByReadImageKey(getImage_Record);
	
	Map<String,Object> map = new HashMap<String,Object>();
	if(ReadImage_RecordList!=null&&ReadImage_RecordList.size()>0){
		map.put("code", 0);
		map.put("msg", "");
		map.put("count", count);
		map.put("data", ReadImage_RecordList);
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
	return "readimage/share/quntaolunshare";
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
	  if(MeidicalRecord!=null&&MeidicalRecord.equals(medicalRecordKey)){
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
   * 跳转到分享视频教学界面
   * @param 
   * @return
   */
  @RequestMapping(value="/getByShareVideoId.do", method=RequestMethod.GET)
  public Object getByShareVideoId(@RequestParam String medicalRecordKey, HttpServletRequest request, Model model){
	log.info("getByShareVideoId");
	DbcontextHolder.setDbType("imagedb");
	model.addAttribute("medicalRecordKey", medicalRecordKey);
	return "readimage/share/shipinjiaoxueshare";
  }
  @RequestMapping(value="/getSaveShareVideo.do", method=RequestMethod.POST)
  @ResponseBody
  public Object getSaveShareVideo(HttpServletRequest request, Model model){
	  log.info("getSaveShareVideo");
	  Map<String,Object> map = new HashMap<String,Object>();
	  DbcontextHolder.setDbType("imagedb");
	  HttpSession session = request.getSession();
	  SysUser sysUser =  (SysUser) session.getAttribute("SysUser");
	  String json = request.getParameter("json");
	  JSONObject returnData = JSONObject.fromObject(json);
	  String medicalRecordKey = returnData.getString("medicalRecordKey");
	  String videoKey = returnData.getString("videoKey");
	  VideoMedicalRecord videoMedicalRecord = new VideoMedicalRecord();
	  videoMedicalRecord.setVideoMedicalRecordKey(UUid.getUUID());
	  videoMedicalRecord.setVideoKey(videoKey);
	  videoMedicalRecord.setRecordKey(medicalRecordKey);
	  videoMedicalRecord.setAppendTime(new Date());
	  videoMedicalRecord.setSysuserKey(sysUser.getSysuserKey());
	  String MeidicalRecord = videoMedicalRecordService.getRecordById(videoMedicalRecord);
	  if(MeidicalRecord!=null&&MeidicalRecord.equals(medicalRecordKey)){
			 map.put("code", 1);
			 map.put("msg", "该病例已存在!");
		  }else{
			  int videoCount = videoMedicalRecordService.save(videoMedicalRecord);
			  if(videoCount>0){
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
	  log.info("getByShareCollectionId");
	  DbcontextHolder.setDbType("imagedb");
	  model.addAttribute("medicalRecordKey", medicalRecordKey);
	  return "readimage/share/shoucangjiashare";
  }
  @RequestMapping(value="/getSaveShareCollection.do", method=RequestMethod.POST)
  @ResponseBody
  public Object getSaveShareCollection(HttpServletRequest request, Model model){
	  log.info("getSaveShareCollection");
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
