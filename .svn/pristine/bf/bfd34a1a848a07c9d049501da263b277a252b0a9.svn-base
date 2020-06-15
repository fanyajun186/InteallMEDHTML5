package com.inteall.image.controller;

import java.text.ParseException;
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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.inteall.image.pojo.ZoomManage;
import com.inteall.image.service.ZoomManageService;
import com.inteall.image.util.UUid;
import com.inteall.image.util.ZoomManageUtil;

import net.sf.json.JSONObject;

/**
 * @author 李进刚
 * @date 2018年2月27日 上午11:14:57
 * @version 1.0 
 * @parameter 
 */

@Controller
@RequestMapping("/zoommanage")
public class ZoomManageController {
//  private static Logger log = Logger.getLogger(ZoomManageController.class.getName());
//  
//  @Resource
//  private ZoomManageService zoomManageService;
//  
//  /**
//   * 创建新的视频日程
//   * @param 创建人ID，功能类别，功能ID，预约时间，预约时长，参会人数，参会人员列表，会议主题
//   * @return
//   * 说明：调用zoom的链接，并在数据表中创建新的记录
//   * @throws ParseException 
//   */
//  @RequestMapping(value="/zoomcreate", method=RequestMethod.GET)
//  @ResponseBody
//  public Object zoomcreate(@RequestParam String json, HttpServletRequest request, Model model) throws ParseException {
//	log.info("zoomcreate");
//	// http://localhost:8080/InteallMEDHTML5/zoommanage/zoomcreate?json='create_person':'createpersoncreateperson00000001','function_type':'1','function_id':'functionidfunctionidfunctionid01','start_time':'2018-03-06 14:00:00','minute':20,'meeting_capacity':2,'participants':'p1-p2-p3-p4-p5','topic':'3月6日下午的会议'
//	Map<String, String> createParamMap = new Gson().fromJson("{" + json + "}", new TypeToken<HashMap<String, String>>(){}.getType());  
//	// 输入参数转换json为map
//
//	ZoomManage zoomManage = new ZoomManage();
//	zoomManage.setZoom_key(UUid.getUUID());
//	zoomManage.setState("0");
//	zoomManage.setModify_time(new Date());
//	zoomManage.setCreate_person(createParamMap.get("create_person"));
//	zoomManage.setCreate_time(new Date());
//	zoomManage.setFunction_type(createParamMap.get("function_type"));
//	zoomManage.setFunction_id(createParamMap.get("function_id"));
//	String sParticipants = (createParamMap.get("participants"));
//	Integer iMeeting_capacity = Integer.parseInt(createParamMap.get("meeting_capacity"));
//	Integer iMinute = Integer.parseInt(createParamMap.get("minute"));
//	String sStart_datetime = createParamMap.get("start_time");
//	String sStart_Date = sStart_datetime.substring(1, 10);
//	String sStart_Time = sStart_datetime.substring(12, 19);
//	String sTopic = createParamMap.get("topic");
//	zoomManage.setParticipants(sParticipants);
//	zoomManage.setMeeting_capacity(iMeeting_capacity);
//	zoomManage.setMinute(iMinute);
//	zoomManage.setStart_time(ZoomManageUtil.stringToDatetime(sStart_datetime));
//	zoomManage.setTopic(sTopic);
//	
//	String sCreate_url = ZoomManageUtil.spliceCreate_URL(sStart_Date, sStart_Time, iMinute, iMeeting_capacity, sTopic);
//	zoomManage.setCreate_url(sCreate_url);
//	String sCreate_result = ZoomManageUtil.send_URL(sCreate_url);
//	zoomManage.setCreate_result(sCreate_result);
//	
//	JSONObject jCreate_result = JSONObject.fromObject(sCreate_result);
//	String sStart_url;
//	String sJoin_url;
//	String sError_code;
//	String sID;
//	String sValue;
//	String sEnd_Time;
//	
//	log.info(jCreate_result);
//	if (jCreate_result.has("error")) {
//		// 判断创建会议的反馈信息，是否出现错误代码
//		// 在反馈信息中检测到错误代码
//		zoomManage.setState("8");
//		
//		sStart_url = null;
//		sJoin_url = null;
//		sID = null;
//		sValue = null;
//		sEnd_Time = null;
//		
//		sError_code = jCreate_result.getString("error");
//	}else {
//		zoomManage.setState("1");
//		
//		sStart_url = jCreate_result.getString("start_url");
//		sJoin_url = jCreate_result.getString("join_url");
//		sID = jCreate_result.getString("id");
//		sValue = jCreate_result.getString("value");
//		sEnd_Time = jCreate_result.getString("end_time");
//		
//		sError_code = null;
//	}
//	zoomManage.setStart_url(sStart_url);
//	zoomManage.setJoin_url(sJoin_url);
//	zoomManage.setId(sID);
//	zoomManage.setValue(sValue);
//	zoomManage.setEnd_time(ZoomManageUtil.stringToDatetime(sEnd_Time));
//	zoomManage.setGet_url(null);
//	zoomManage.setGet_result(null);
//	zoomManage.setUid(null);
//	zoomManage.setToken(null);
//	zoomManage.setUpdate_url(null);
//	zoomManage.setUpdate_result(null);
//	zoomManage.setDelete_url(null);
//	zoomManage.setDelete_result(null);
//	zoomManage.setEnd_url(null);
//	zoomManage.setEnd_result(null);
//	zoomManage.setLog_url(null);
//	zoomManage.setLog_result(null);
//	zoomManage.setEnd_timed(null);
//	zoomManage.setMeeting_sum_time(0);
//	zoomManage.setError_code(sError_code);
//	zoomManage.setIs_del("0");
//	zoomManage.setDel_time(null);
//	zoomManage.setZoom_remark1(null);
//	zoomManage.setZoom_remark2(null);
//	zoomManage.setZoom_remark3(null);
//	zoomManage.setZoom_remark4(null);
//	zoomManage.setZoom_remark5(null);
//	zoomManage.setZoom_remark6(null);
//	
//	int row = zoomManageService.zoomcreate(zoomManage);
//	
//	// 返回前台信息
//	Map<String,Object> map = new HashMap<String,Object>();
//	map.put("增加会议日程数量", row);
//	map.put("增加会议日程信息", zoomManage);
//	return map;
//  }
//  
//  /**
//	 * 根据预约后的会议id和value,获取移动端加入会议的信息
//	 * @param id,value
//	 * @return uid,token
//	 */
//  @RequestMapping(value="/zoomget", method=RequestMethod.GET)
//  @ResponseBody
//  public Object zoomget(@RequestParam String json, HttpServletRequest request, Model model) {
//	log.info("zoomget");
//	// http://localhost:8080/InteallMEDHTML5/zoommanage/zoomget?json='zoom_key':'2b12763f46f84808bd4e38b4d6512a8c','id'='316077','value'='c8b2a8da35cc0fb2e4e37a2bcd504669'
//	log.info("json:"+json);
//	
//	Map<String, String> inputJsonMap = new Gson().fromJson("{" + json + "}", new TypeToken<HashMap<String, String>>(){}.getType());  
//	// 输入参数转换json为map
//	log.info("inputJsonMap:");
//	log.info(inputJsonMap);
//	
//	String sZoom_key = inputJsonMap.get("zoom_key");
//	String sID = inputJsonMap.get("id");
//	String sValue = inputJsonMap.get("value");
//	
//	String sGet_url = ZoomManageUtil.spliceGet_URL(sID, sValue);
//	String sGet_Result = ZoomManageUtil.send_URL(sGet_url);
//	log.info("sGet_url:" + sGet_url);
//	log.info("sGet_Result:" + sGet_Result);
//	
//	JSONObject jGet_Result = JSONObject.fromObject(sGet_Result);
//	String sState;
//	String sUID;
//	String sToken;
//	String sError_code;
//	if (jGet_Result.has("error")) {
//		// 反馈错误代码
//		sState = "8";
//		
//		sUID = null;
//		sToken = null;
//		
//		sError_code = jGet_Result.getString("error");
//	}else {
//		// 反馈正确信息
//		sState = "1";
//		
//		sUID = jGet_Result.getString("uid");
//		sToken = jGet_Result.getString("token");
//		
//		sError_code = null;
//	}
//    
//	HashMap<String, String> getParamMap = new HashMap<String, String>();
//	getParamMap.put("zoom_key", sZoom_key);
//	getParamMap.put("state", sState);
//	getParamMap.put("get_url", sGet_url);
//	getParamMap.put("get_result", sGet_Result);
//	getParamMap.put("uid", sUID);
//	getParamMap.put("token", sToken);
//	getParamMap.put("error_code", sError_code);
//	log.info("getParamMap:");
//	log.info(getParamMap);
//	
//	int row = zoomManageService.zoomget(getParamMap);
//	
//	// 返回前台信息
//	Map<String,Object> map = new HashMap<String,Object>();
//	map.put("存储移动端会议信息的数量", row);
//	map.put("存储移动端会议的信息", getParamMap);
//	return map;
//  }
//  
//  /**
//	 * 根据id查询会议日程
//	 * @param zoom_key
//	 * @return
//	 */
//  @RequestMapping(value="/getById", method=RequestMethod.GET)
//  @ResponseBody
//  public Object getById(@RequestParam String zoom_key){
//	log.info("getById");
//	// http://localhost:8080/InteallMEDHTML5/zoommanage/getById?zoom_key=2b12763f46f84808bd4e38b4d6512a8c
//	Map<String,Object> map = new HashMap<String,Object>();
//	
//	log.info("zoom_key:"+zoom_key);
//	ZoomManage zoomManage = zoomManageService.getById(zoom_key);
//	map.put("zoommanage", zoomManage);
//	return map;
//  }
//  
//  /**
//	 * 根据id编辑会议日程
//	 * @param zoom_key,state
//	 * @return
//	 */
//  @RequestMapping(value="/updateStateById", method=RequestMethod.GET)
//  @ResponseBody
//  public Object updateStateById(@RequestParam String json, HttpServletRequest request, Model model){
//	log.info("updateById");
//	// http://localhost:8080/InteallMEDHTML5/zoommanage/updateStateById?json='zoom_key':'2b12763f46f84808bd4e38b4d6512a8c','state':'3'
//	log.info("json:" + json);
//	
//	Map<String, String> inputParamMap = new Gson().fromJson("{" + json + "}", new TypeToken<HashMap<String, String>>(){}.getType());  
//	// 输入参数转换json为map
//	log.info("inputParamMap:");
//	log.info(inputParamMap);
//	
//	HashMap<String, String> paramMap = new HashMap<String, String>();
//	paramMap.put("zoom_key", inputParamMap.get("zoom_key"));
//	paramMap.put("state", inputParamMap.get("state"));
//	log.info("paramMap:");
//	log.info(paramMap);
//	
//	// 返回前台信息
//	Map<String, Object> map = new HashMap<String, Object>();
//	
//	int row = zoomManageService.updateStateById(paramMap);
//	
//	map.put("修改会议日程数量", row);
//	return map;
//  }
//  
//  /**
//	 * 修改预约的会议日程，包括与会人数、会议主题和与会人员列表
//	 * @param zoom_key,id,value,meeting_capacity,topic,participants
//	 * @return uid,token
//	 */
//  @RequestMapping(value="/zoomupdate", method=RequestMethod.GET)
//  @ResponseBody
//  public Object zoomupdate(@RequestParam String json, HttpServletRequest request, Model model) {
//	log.info("zoomupdate");
//	// http://localhost:8080/InteallMEDHTML5/zoommanage/zoomupdate?json='zoom_key':'a2f11b0543f24f1e82498cad6bdcbac6', 'id'='316185','value'='d92fdd83ee3654c75a209e10cb125268','meeting_capacity'='3','topic'='修改后的会议主题','participants'='新的与会人员列表'
//	log.info("json:"+json);
//	
//	Map<String, String> inputJsonMap = new Gson().fromJson("{" + json + "}", new TypeToken<HashMap<String, String>>(){}.getType());  
//	// 输入参数转换json为map
//	log.info("inputJsonMap:");
//	log.info(inputJsonMap);
//	
//	String sID = inputJsonMap.get("id");
//	String sValue = inputJsonMap.get("value");
//	String sMeeting_capacity = inputJsonMap.get("meeting_capacity");
//	String sParticipants = inputJsonMap.get("participants");
//	String sTopic = inputJsonMap.get("topic");
//	
//	String sUpdate_url = ZoomManageUtil.spliceUpdate_URL(sID, sValue, sMeeting_capacity, sTopic);
//	String sUpdate_Result = ZoomManageUtil.send_URL(sUpdate_url);
//	log.info("sUpdate_url: " + sUpdate_url);
//	log.info("sUpdate_Result: " + sUpdate_Result);
//	
//	ZoomManage zoomManage = new ZoomManage();
//	zoomManage.setZoom_key(inputJsonMap.get("zoom_key"));
//	zoomManage.setMeeting_capacity(Integer.parseInt(sMeeting_capacity));
//	zoomManage.setParticipants(sParticipants);
//	zoomManage.setTopic(sTopic);
//	zoomManage.setUpdate_url(sUpdate_url);
//	zoomManage.setUpdate_result(sUpdate_Result);
//	
//	JSONObject jUpdate_Result = JSONObject.fromObject(sUpdate_Result);
//	if (jUpdate_Result.has("error")) {
//		// 反馈错误代码
//		zoomManage.setState("8");
//		
//		zoomManage.setError_code(jUpdate_Result.getString("error"));
//	}else {
//		// 反馈正确信息
//		zoomManage.setState("1");
//		
//		zoomManage.setError_code(null);
//	}
//	log.info(zoomManage);
//	
//	int row = zoomManageService.zoomupdate(zoomManage);
//	
//	// 返回前台信息
//	Map<String,Object> map = new HashMap<String,Object>();
//	map.put("修改会议预约信息的数量", row);
//	map.put("修改会议预约的信息", zoomManage);
//	return map;
//  }
//  
//  /**
//	 * 撤销预约的会议日程，并在表中删除
//	 * @param zoom_key, id, value
//	 * @return uid,token
//	 */
//  @RequestMapping(value="/zoomdelete", method=RequestMethod.GET)
//  @ResponseBody
//  public Object zoomdelete(@RequestParam String json, HttpServletRequest request, Model model) {
//	log.info("zoomdelete");
//	// http://localhost:8080/InteallMEDHTML5/zoommanage/zoomdelete?json='zoom_key':'a2f11b0543f24f1e82498cad6bdcbac6','id'='316185','value'='d92fdd83ee3654c75a209e10cb125268'
//	log.info("json:"+json);
//	
//	Map<String, String> inputJsonMap = new Gson().fromJson("{" + json + "}", new TypeToken<HashMap<String, String>>(){}.getType());  
//	log.info("inputJsonMap:");
//	log.info(inputJsonMap);
//	
//	String sZoom_key = inputJsonMap.get("zoom_key");
//	String sID = inputJsonMap.get("id");
//	String sValue = inputJsonMap.get("value");
//	String sDelete_url = ZoomManageUtil.spliceDelete_URL(sID, sValue);
//	String sDelete_Result = ZoomManageUtil.send_URL(sDelete_url);
//	log.info("sDelete_url:" + sDelete_url);
//	log.info("sDelete_Result:" + sDelete_Result);
//	
//	ZoomManage zoomManage = new ZoomManage();
//	zoomManage.setZoom_key(sZoom_key);
//	zoomManage.setDelete_url(sDelete_url);
//	zoomManage.setDelete_result(sDelete_Result);
//
//	JSONObject jDelete_Result = JSONObject.fromObject(sDelete_Result);
//	if (jDelete_Result.has("error")) {
//		// 反馈错误代码
//	  	zoomManage.setState("8");
//		zoomManage.setError_code(jDelete_Result.getString("error"));
//		zoomManage.setIs_del("0");
//	}else {
//		// 反馈正确信息
//	  	zoomManage.setState("1");
//	  	zoomManage.setError_code(null);
//	  	zoomManage.setIs_del("1");
//	}
//	
//	int row = zoomManageService.zoomdelete(zoomManage);
//	
//	// 返回前台信息
//	Map<String,Object> map = new HashMap<String,Object>();
//	map.put("删除会议预约信息的数量", row);
//	map.put("删除会议预约的信息", zoomManage);
//	return map;
//  }
//  
//  /**
//	 * 强制终止已启动的会议
//	 * @param zoom_key, id, value
//	 * @return
//	 */
//  @RequestMapping(value="/zoomend", method=RequestMethod.GET)
//  @ResponseBody
//  public Object zoomend(@RequestParam String json, HttpServletRequest request, Model model) {
//	log.info("zoomend");
//	// http://localhost:8080/InteallMEDHTML5/zoommanage/zoomend?json='zoom_key':'4856e2087b284b5393ef66e1c6ceeb14','id'='316427','value'='3e8bd49b4ac74d9ee1b63a97861acb22'
//	log.info("json:"+json);
//	
//	Map<String, String> inputJsonMap = new Gson().fromJson("{" + json + "}", new TypeToken<HashMap<String, String>>(){}.getType());  
//	log.info("inputJsonMap:");
//	log.info(inputJsonMap);
//	
//	String sZoom_key = inputJsonMap.get("zoom_key");
//	String sID = inputJsonMap.get("id");
//	String sValue = inputJsonMap.get("value");
//	String sEnd_url = ZoomManageUtil.spliceEnd_URL(sID, sValue);
//	String sEnd_Result = ZoomManageUtil.send_URL(sEnd_url);
//	log.info("sEnd_url:" + sEnd_url);
//	log.info("sEnd_Result:" + sEnd_Result);
//	
//	ZoomManage zoomManage = new ZoomManage();
//	zoomManage.setZoom_key(sZoom_key);
//	zoomManage.setState("3");
//	zoomManage.setEnd_url(sEnd_url);
//	zoomManage.setEnd_result(sEnd_Result);
//
//	JSONObject jEnd_Result = JSONObject.fromObject(sEnd_Result);
//	if (jEnd_Result.has("error")) {
//		// 反馈错误代码
//		zoomManage.setError_code(jEnd_Result.getString("error"));
//	}else {
//		// 反馈正确信息
//	  	zoomManage.setError_code(null);
//	}
//	
//	int row = zoomManageService.zoomend(zoomManage);
//	
//	// 返回前台信息
//	Map<String,Object> map = new HashMap<String,Object>();
//	map.put("强制终止会议的数量", row);
//	map.put("强制终止会议的信息", zoomManage);
//	return map;
//  }
//  /**
//	 * 获取会议日程列表
//	 * @param create_person
//	 * @return
//	 */
//  @RequestMapping(value="/getByUserID", method=RequestMethod.GET)
//  @ResponseBody
//  public Object getByUserID(@RequestParam String json, HttpServletRequest request, Model model){
//	log.info("getByUserID");
//	// 测试：http://localhost:8080/InteallMEDHTML5/zoommanage/getByUserID?json='create_person':'createpersoncreateperson00000001'
//    log.info("json:" + json);
//	Map<String, String> inputJsonMap = new Gson().fromJson("{" + json + "}", new TypeToken<HashMap<String, String>>(){}.getType());  
//	log.info("inputJsonMap:");
//	log.info(inputJsonMap);
//	
//	ZoomManage zoomManage = new ZoomManage();
//	zoomManage.setCreate_person(inputJsonMap.get("create_person"));
//	
//	List<ZoomManage> zoomManageList = zoomManageService.getByUserID(zoomManage);
//    
//	// 返回前台信息
//	Map<String,Object> map = new HashMap<String,Object>();
//	map.put("获取会议日程列表数量", zoomManageList.size());
//	map.put("获取会议日程列表信息", zoomManageList);
//	return map;
//  }
//  
}