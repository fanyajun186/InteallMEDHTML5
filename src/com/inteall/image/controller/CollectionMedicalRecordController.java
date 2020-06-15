package com.inteall.image.controller;

import java.text.SimpleDateFormat;
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
@RequestMapping("/collectionMedicalRecord")
public class CollectionMedicalRecordController {
	private Logger logger = Logger.getLogger(CollectionMedicalRecordController.class);
	@Resource
	private CollectionMedicalRecordService collectionMedicalRecordService;
	@Resource
	private ReadImage_RecordService readImage_RecordService;
	@Resource
	private VideoMedicalRecordService videoMedicalRecordService;
	@Resource
	private BigAntService bigAntService;
	private String ssid = "E6300170-E5B0-B072-E18D-74A870934D48";
	private String uid = "116";
	private String uname = "电子客服";
	private BigAntUtil bigAntUtil = new BigAntUtil(ssid, uid, uname);
	/**
	 * @author 刘天诚
	 * 查询List
	 * 创建时间：2018年2月27日-上午11:54:22
	 */
	@RequestMapping(value="getMedicalRecordList.do",method=RequestMethod.GET)
	@ResponseBody
	public Object getMedicalRecordList(HttpServletRequest request,Model model){
		logger.info("getMedicalRecordList");
		Map<String, Object> map = new HashMap<String, Object>();
		CollectionMedicalRecord collectionMedicalRecord = new CollectionMedicalRecord();
		collectionMedicalRecord.setCollection_key("5");
		List<CollectionMedicalRecord> list = collectionMedicalRecordService.getMedicalRecordList(collectionMedicalRecord);
		map.put("code", 0);
		map.put("msg", "");
		map.put("count", list.size());
		map.put("data", list);
		return map;
	}
	@RequestMapping(value="/update.do",method=RequestMethod.GET)
	@ResponseBody
	public Object update(@RequestParam String cid,@RequestParam String mid,HttpServletRequest request,Model model){
		logger.info("update");
		Map<String, Object> map = new HashMap<String,Object>();
		CollectionMedicalRecord collectionMedicalRecord = new CollectionMedicalRecord();
		collectionMedicalRecord.setCollection_medical_record_key(cid);
		collectionMedicalRecord.setCollection_key("111112");
		collectionMedicalRecord.setMedical_record_key(mid);
//		collectionMedicalRecord.setCreate_person("a1");
//		collectionMedicalRecord.setCreate_time(new Date());
//		collectionMedicalRecord.setModify_time(new Date());
//		collectionMedicalRecord.setModify_person("a1");
//		collectionMedicalRecord.setIs_del("1");
//		collectionMedicalRecord.setDel_time(new Date());
//		collectionMedicalRecord.setDel_person("a1");
//		collectionMedicalRecord.setCollection_medical_record_remark1("a1");
//		collectionMedicalRecord.setCollection_medical_record_remark2("a1");
//		collectionMedicalRecord.setCollection_medical_record_remark3("a1");

		int row = collectionMedicalRecordService.update(collectionMedicalRecord);
		map.put("修改的行数", row);
		map.put("修改的信息", collectionMedicalRecord);
		return map;
	}
	@RequestMapping(value="/deleteCollectionMedicalRecord.do",method=RequestMethod.GET)
	@ResponseBody
	public Object deleteCollectionMedicalRecord(@RequestParam String cDid,@RequestParam String mDid,HttpServletRequest request,Model model){
		logger.info("deleteCollectionMedicalRecord");
		Map<String, Object> map = new HashMap<String,Object>();
		CollectionMedicalRecord collectionMedicalRecord = new CollectionMedicalRecord();
		collectionMedicalRecord.setCollection_medical_record_key(cDid);
		collectionMedicalRecord.setCollection_key(mDid);
		collectionMedicalRecord.setMedical_record_key("");
		int row = collectionMedicalRecordService.deleteCollectionMedicalRecord(collectionMedicalRecord);
		map.put("修改的行数", row);
		map.put("修改的信息", collectionMedicalRecord);
		return map;
	}
	  /**
	   * 跳转到群讨论分享界面
	   * @param 
	   * @return
	   */
	  @RequestMapping(value="/getByShareGroupId.do", method=RequestMethod.GET)
	  public Object getByShareGroupId(@RequestParam String medicalRecordKey, HttpServletRequest request, Model model){
		  logger.info("getByShareGroupId");
		DbcontextHolder.setDbType("imagedb");
		model.addAttribute("medicalRecordKey", medicalRecordKey);
		return "shoucangjia/share/quntaolunshare";
	  }
	  @RequestMapping(value="/getSaveShareGroup.do", method=RequestMethod.POST)
	  @ResponseBody
	  public Object getSaveShareGroup(HttpServletRequest request, Model model){
		  logger.info("getSaveShareGroup");
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
		logger.info("getByShareRreadimageId");
		DbcontextHolder.setDbType("imagedb");
		model.addAttribute("medicalRecordKey", medicalRecordKey);
		return "shoucangjia/share/dupianhuishare";
	  }
	  @RequestMapping(value="/getSaveShareRreadimage.do", method=RequestMethod.POST)
	  @ResponseBody
	  public Object getSaveShareRreadimage(HttpServletRequest request, Model model){
		  logger.info("getSaveShareRreadimage");
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
	   * 跳转到分享视频教学界面
	   * @param 
	   * @return
	   */
	  @RequestMapping(value="/getByShareVideoId.do", method=RequestMethod.GET)
	  public Object getByShareVideoId(@RequestParam String medicalRecordKey, HttpServletRequest request, Model model){
		logger.info("getByShareVideoId");
		DbcontextHolder.setDbType("imagedb");
		model.addAttribute("medicalRecordKey", medicalRecordKey);
		return "shoucangjia/share/shipinjiaoxueshare";
	  }
	  @RequestMapping(value="/getSaveShareVideo.do", method=RequestMethod.POST)
	  @ResponseBody
	  public Object getSaveShareVideo(HttpServletRequest request, Model model){
		  logger.info("getSaveShareVideo");
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
}