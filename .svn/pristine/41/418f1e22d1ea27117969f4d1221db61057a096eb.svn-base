package com.inteall.image.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
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
/**
 * @author 于津名
 * @date 2018年5月16日 下午1:58:28
 * @version 1.0 
 * @parameter 
 */
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.inteall.image.pojo.Consultation;
import com.inteall.image.pojo.ConsultationConferee;
import com.inteall.image.pojo.ConsultationWithBLOBs;
import com.inteall.image.pojo.Intervention;
import com.inteall.image.pojo.InterventionConferee;
import com.inteall.image.pojo.InterventionWithBLOBs;
import com.inteall.image.pojo.MedicalRecord;
import com.inteall.image.pojo.ReadImage_Record;
import com.inteall.image.pojo.SysUser;
import com.inteall.image.pojo.ZoomManage;
import com.inteall.image.pojo.ZoomManageWithBLOBs;
import com.inteall.image.service.BigAntService;
import com.inteall.image.service.InterventionConfereeService;
import com.inteall.image.service.InterventionService;
import com.inteall.image.service.MedicalRecordService;
import com.inteall.image.service.MessageLogService;
import com.inteall.image.service.SysUserService;
import com.inteall.image.service.ZoomManageService;
import com.inteall.image.util.BigAntUtil;
import com.inteall.image.util.CustomMultipartResolver;
import com.inteall.image.util.DbcontextHolder;
import com.inteall.image.util.NoteUtil;
import com.inteall.image.util.UUid;
import com.inteall.image.util.Util;
import com.inteall.image.util.ZoomManageUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@Controller
@RequestMapping("/intervention")
public class InterventionController {
	private static Logger log = Logger.getLogger(InterventionController.class.getName());
	
	@Resource
	private InterventionService interventionService;
	@Resource
	private InterventionConfereeService interventionConfereeService;
	@Resource
	private MedicalRecordService medicalRecordService;
	@Resource
	private SysUserService sysuserService;
	@Resource
	private ZoomManageService ZoomManageService;
	@Resource
	private BigAntService bigAntService;
	private String ssid = "E6300170-E5B0-B072-E18D-74A870934D48";
	private String uid = "116";
	private String uname = "电子客服";
	private BigAntUtil bigAntUtil = new BigAntUtil(ssid, uid, uname);
	
	 @Resource
	 private MessageLogService messageLogService;
	/**
	 * 根据用户加载会诊列表
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/getRecordByCreateUser.do",method=RequestMethod.POST)
	@ResponseBody
	public Object getRecordByCreateUser(HttpServletRequest request,Model model){
	  	Map<String,Object> map = new HashMap<String,Object>();
	  	DbcontextHolder.setDbType("imagedb");
	  	HttpSession session = request.getSession();
		SysUser sysUser =  (SysUser) session.getAttribute("SysUser");
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
		queryMap.put("createperson", sysUser.getSysuserKey());
		queryMap.put("commitTagetPerson", sysUser.getLoginName());
		
		//先查询满足条件的数据有多少条
		int count = interventionService.getMedicalCount(queryMap);
		
		queryMap.put("curr", (page-1)*limit);
		queryMap.put("limit",limit);
			List<InterventionWithBLOBs> medicalRecords = interventionService.getRecordByCreateUser(queryMap);
			if(medicalRecords!=null){
				  map.put("code", 0);
				  map.put("data", medicalRecords);
				  map.put("count", count);
				}else{
				  map.put("code", 1);
				  map.put("msg", "查询会诊病历出错，请稍后重试或者联系管理员！");
				}
		return map;
	}
	/**
	 * 根据医生加载会诊列表
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/getRecordByDoctorUser.do",method=RequestMethod.POST)
	@ResponseBody
	public Object getRecordByDoctorUser(HttpServletRequest request,Model model){
		 Map<String,Object> map = new HashMap<String,Object>();
	      DbcontextHolder.setDbType("imagedb");
	      HttpSession session = request.getSession();
	      SysUser sysUser =  (SysUser) session.getAttribute("SysUser");
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
	      queryMap.put("commitTargetPerson", sysUser.getLoginName());
	      //先查询满足条件的数据有多少条
	      int count = interventionService.getCountByDoctorUser(queryMap);
	      queryMap.put("curr", (page-1)*limit);
	      queryMap.put("limit",limit);
	      List<InterventionWithBLOBs> medicalRecords =  interventionService.getRecordByDoctorUser(queryMap);
	      
	      if(medicalRecords!=null){
	        map.put("code", 0);
	        map.put("data", medicalRecords);
	        map.put("count", count);
	      }else{
	        map.put("code", 1);
	        map.put("msg", "查询会诊病历出错，请稍后重试或者联系管理员！");
	      }
	      
		return map;
	}
	/**
	 * intervention会诊详情：根据用户的id查询用户信息
	 * @param id
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value="/getByImageId.do",method=RequestMethod.GET)
	public Object getByImageId(@RequestParam String medicalRecordKey,@RequestParam String interventionKey,@RequestParam String stuuid,HttpServletRequest request,Model model) throws ParseException{
		log.info("getByImageId");
		DbcontextHolder.setDbType("imagedb");
		MedicalRecord medicalRecord = medicalRecordService.getByImageId(medicalRecordKey);
		//根据会诊id获取zoom信息
		ZoomManage zoomManage = ZoomManageService.getZoomKeyByintervention(interventionKey);
		if(zoomManage!=null){
		//根据会诊id获取会诊人员
		InterventionConferee interventionConferee = new InterventionConferee();
		interventionConferee.setinterventionKey(interventionKey);
		List<SysUser> sysUsers = interventionConfereeService.getByInterventionKey(interventionConferee);
		String usernames = "";
		String login = "";
		for(SysUser s:sysUsers){
		  usernames +=s.getUserName()+"," ;
		  login +=s.getLoginName()+",";
		}
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String start_time = dft.format(zoomManage.getStartTime());
		model.addAttribute("yuyueshichang", zoomManage.getMinute());
		model.addAttribute("start_time", start_time);
		model.addAttribute("yuhuirenyuan", usernames);
		model.addAttribute("ReadimageKey", medicalRecordKey);
		model.addAttribute("zoomKey", zoomManage.getZoomKey());
		model.addAttribute("login", login);
		Date d = dft.parse(start_time);
		
		Date nowdate=new Date(); 
		boolean isEdit = false;
		long diff = d.getTime()-nowdate.getTime();
		if(diff/1000/60>15){
			isEdit = true;
		}
		model.addAttribute("isEdit", isEdit);
		}
		model.addAttribute("interventionKey", interventionKey);
		model.addAttribute("stuuid", stuuid);
		model.addAttribute("medicalRecord", medicalRecord);
		return "jieruhuizhen/xiangqing/index";
	}
	/**
	 * intervention会诊详情：根据用户的id查询用户信息
	 * @param id
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value="/getByDoctorImageId.do",method=RequestMethod.GET)
	public Object getByDoctorImageId(@RequestParam String medicalRecordKey,@RequestParam String interventionKey,@RequestParam String stuuid,HttpServletRequest request,Model model) throws ParseException{
		log.info("getByDoctorImageId");
		DbcontextHolder.setDbType("imagedb");
		MedicalRecord medicalRecord = medicalRecordService.getByImageId(medicalRecordKey);
		//根据会诊id获取zoom信息
		ZoomManage zoomManage = ZoomManageService.getZoomKeyByintervention(interventionKey);
		if(zoomManage!=null){
			//根据会诊id获取会诊人员
			InterventionConferee interventionConferee = new InterventionConferee();
			interventionConferee.setinterventionKey(interventionKey);
			List<SysUser> sysUsers = interventionConfereeService.getByInterventionKey(interventionConferee);
			String usernames = "";
			String login = "";
			for(SysUser s:sysUsers){
				usernames +=s.getUserName()+"," ;
				login +=s.getLoginName()+",";
			}
			SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String start_time = dft.format(zoomManage.getStartTime());
			model.addAttribute("yuyueshichang", zoomManage.getMinute());
			model.addAttribute("start_time", start_time);
			model.addAttribute("yuhuirenyuan", usernames);
			model.addAttribute("ReadimageKey", medicalRecordKey);
			model.addAttribute("zoomKey", zoomManage.getZoomKey());
			model.addAttribute("login", login);
			Date d = dft.parse(start_time);
			
			Date nowdate=new Date(); 
			boolean isEdit = false;
			long diff = d.getTime()-nowdate.getTime();
			if(diff/1000/60>15){
				isEdit = true;
			}
			model.addAttribute("isEdit", isEdit);
		}
		model.addAttribute("interventionKey", interventionKey);
		model.addAttribute("stuuid", stuuid);
		model.addAttribute("medicalRecord", medicalRecord);
		return "jieruhuizhen/xiangqing/doctorindex";
	}
	/**
	   * 根据stuyid删除读片会中的病历
	   * 删的是关联表，不是病历表
	   * @param 
	   * @return
	   */
	  @RequestMapping(value="/delByStuuid.do", method=RequestMethod.GET)
	  @ResponseBody
	  public Object delByStuuid(@RequestParam String stuuid,@RequestParam String interventionKey, HttpServletRequest request, Model model) throws ParseException {
		log.info("delByStuuid");
		HashMap<String, String> getParamMap = new HashMap<String, String>();
		DbcontextHolder.setDbType("imagedb");
		getParamMap.put("stuuid", stuuid);
		getParamMap.put("interventionKey", interventionKey);
		int row = interventionService.delByStuuid(getParamMap);
		
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
		 * 跳转到选择专家界面
		 * @param request
		 * @param model
		 * @return
		 */
		@RequestMapping(value="/redirectGroup.do",method=RequestMethod.GET)
		public Object redirectGroup(@RequestParam String interventionKey,HttpServletRequest request,Model model){
		  	log.info("redirectGroup");
		  	model.addAttribute("interventionKey", interventionKey);
			return "jieruhuizhen/group";
		}
		 /**
		   * 提交会诊申请
		   * @param request
		   * @param model
		   * @return
		   */
		  @RequestMapping(value="/submitInterventionKey.do",method=RequestMethod.POST)
		  @ResponseBody
		  public Object submitConsultation(HttpServletRequest request,Model model){
		      String interventionKey = request.getParameter("interventionKey");
		      String groupId = request.getParameter("groupId");
		      String userLogin = request.getParameter("userLogin");
		      String bingqingmiaoshu = request.getParameter("bingqingmiaoshu");
		      String zhuanjiaxingming = request.getParameter("zhuanjiaxingming");
		      Map<String,Object> map = new HashMap<String,Object>();
		      DbcontextHolder.setDbType("imagedb");
		      HttpSession session = request.getSession();
		      SysUser sysUser =  (SysUser) session.getAttribute("SysUser");
		      
		      InterventionWithBLOBs intervention = new InterventionWithBLOBs();
		       
		      if(userLogin==null){
		    	  intervention.setCommitType("2");
		      }else{
		    	  intervention.setCommitType("1");
		          //提交目标医生的登录名
		    	  intervention.setCommitTargetPerson(userLogin);
		      }
		      intervention.setAcceptPersonName(zhuanjiaxingming);
		      intervention.setAcceptPersonLogin(userLogin);
		      intervention.setAcceptTime(new Date());
		      intervention.setCommitRequest(bingqingmiaoshu);
		      intervention.setIsAccept("1");
		      intervention.setState("2");
		      intervention.setinterventionKey(interventionKey);
		      intervention.setGroupId(groupId);
		      intervention.setCommitTime(new Date());
		      int count = interventionService.updateByInterventionKey(intervention);
		     
		      Map<String,Object> ma = new HashMap<String,Object>(); 
		      if(count>0){
		          map.put("code", 0);
		          SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		          String content = "【汇智精英】"+sysUser.getUserName()+"老师，于"+dft.format(new Date())+"上传了一个病历，请您会诊。";
		          bigAntUtil.send_user(userLogin, "会诊通知", content);
		          SysUser s = sysuserService.getByLoginname(userLogin);
		          
		          ma.put("message", content);
	              ma.put("sendType", "会诊通知");
	              ma.put("recipient", s.getLoginName());
	              ma.put("receivePhone", s.getUserPhone());
		          
		          if(s.getUserPhone()!=null){
  	    			try {
						String sendResult = NoteUtil.sendSMSchange(sysUser.getUserName()+"老师，于"+dft.format(new Date())+"上传了一个病历，请您会诊。【汇智精英】", s.getUserPhone());
						ma.put("sendState", sendResult);
  	    			} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
  	    		}
		          
		          int row = messageLogService.addMessageInfo(ma);  
		      }else{
		          map.put("code", 1);
		          map.put("msg", "提交会诊申请时出错，请稍后重试或联系管理员！");
		      } 
			
		      return map;
		  }
		  /**
		   * 接受会诊
		   * @param request
		   * @param model
		   * @return
		   */
		  @RequestMapping(value="/received.do",method=RequestMethod.GET)
		  @ResponseBody
		  public Object received(@RequestParam String interventionKey,HttpServletRequest request,Model model){
		      Map<String,Object> map = new HashMap<String,Object>();
		      DbcontextHolder.setDbType("imagedb");
		      
		      HttpSession session = request.getSession();
		      SysUser sysUser =  (SysUser) session.getAttribute("SysUser");
		      InterventionWithBLOBs intervention = new InterventionWithBLOBs();
		      intervention.setIsAccept("0");
		      intervention.setAcceptPersonLogin(sysUser.getLoginName());
		      intervention.setAcceptPersonName(sysUser.getUserName());
		      intervention.setAcceptTime(new Date());
		      intervention.setinterventionKey(interventionKey);
		      int count = interventionService.updateByInterventionKey(intervention);
		      if(count>0){
		          map.put("code", 0);
		      }else{
		          map.put("code", 1);
		          map.put("msg", "接受失败，请稍后重试或联系管理员！");
		      } 
		      return map;
		  }
		  /**
		     * 增加一条新会诊日程
		     * @param 
		     * @return
		     */
		  @RequestMapping(value="/save.do", method=RequestMethod.POST)
		  @ResponseBody
		  @Transactional(rollbackFor=Exception.class)
		  public Object save(@RequestParam String interventionKey,HttpServletRequest request, Model model){
			log.info("save");
			Map<String, Object> map = new HashMap<>();
			Map<String,Object> ma = new HashMap<String,Object>(); 
			DbcontextHolder.setDbType("imagedb");
			String json = request.getParameter("json");
			JSONObject returnData = JSONObject.fromObject(json);
			String startTime = returnData.getString("startTime");
			String yuyueshichang = returnData.getString("yuyueshichang");
			JSONArray member = returnData.getJSONArray("member");
			SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//获取当前系统用户
			HttpSession session = request.getSession();
			SysUser sysUser =  (SysUser) session.getAttribute("SysUser");
			//1.同过zoom预约视频会议，返回预约结果 
			String zoommsg = null;
			try {
			  String createurl = ZoomManageUtil.spliceCreate_URL(startTime, Integer.parseInt(yuyueshichang), member.size()+1, "会诊");
			  zoommsg = ZoomManageUtil.send_URL(createurl);
			}catch (NumberFormatException e2) {
			  // TODO Auto-generated catch block
			  e2.printStackTrace();
			}catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(zoommsg.equals("Error:0")){
				map.put("code", 1);
				map.put("msg","预约视频信息失败!");
				return map; 
			}
			JSONObject jsonObject = JSONObject.fromObject(zoommsg); 
			String zoom_key = UUid.getUUID();
			ZoomManageWithBLOBs zoomManage = new ZoomManageWithBLOBs();
			if(jsonObject.get("error")==null){
			  	//2.记录zoom信息到数据库
				
				try {
				  zoomManage.setEndTime(dft.parse(jsonObject.getString("end_time")));
				  zoomManage.setStartTime(dft.parse(startTime));
				  zoomManage.setMinute(Integer.parseInt(yuyueshichang));
				} catch (ParseException e2) {
				  // TODO Auto-generated catch block
				  e2.printStackTrace();
				}
				zoomManage.setId(jsonObject.getString("id"));
				zoomManage.setZoomKey(zoom_key);
				zoomManage.setCreatePerson(sysUser.getSysuserKey());
				zoomManage.setCreateTime(new Date());
				zoomManage.setIsDel("0");
				zoomManage.setJoinUrl(jsonObject.getString("join_url"));
				zoomManage.setMeetingCapacity(jsonObject.getInt("meeting_capacity"));
				zoomManage.setStartUrl(jsonObject.getString("start_url"));
				zoomManage.setState("1");//已预约
				zoomManage.setTopic(jsonObject.getString("topic"));
				zoomManage.setValue(jsonObject.getString("value"));
			}else{
				map.put("code", 1);
				map.put("msg", jsonObject.getString("error"));
				return map;
			}
			//3.记录会诊信息
			Intervention intervention = new Intervention();
			intervention.setZoomKey(zoom_key);
			intervention.setinterventionKey(interventionKey);
			//4.记录与会人员
				List<InterventionConferee> conferees = new ArrayList<InterventionConferee>();
				for(int i=0;i<member.size();i++){
				    String loginname = member.getString(i);
				    SysUser sys = sysuserService.login(loginname);
					if(sys==null){//系统中没有这个登录名的用户。即在大蚂蚁中存在，本系统中不存在
					}else{
						InterventionConferee conferee = new InterventionConferee();
						conferee.setinterventionConfereeKey(UUid.getUUID());
						conferee.setinterventionKey(interventionKey);
						conferee.setSysuserKey(sys.getSysuserKey());
						conferees.add(conferee);
					}
				}
				try {
					  int imageCount = interventionService.saveById(intervention);
					  int zoomCount = ZoomManageService.zoomcreate(zoomManage);
				  if(!conferees.isEmpty()){
					  interventionConfereeService.saveList(conferees);
				  }
			  if (imageCount > 0 && zoomCount > 0 ) {
				//会诊创建成功，zoom信息保存成功，与人员保存成功
				//通过汇沟通发送消息
				for(int i=0;i<member.size();i++){
				    String user_login = member.getString(i);
		    		String bigantUid = bigAntUtil.get_uid(user_login);
		    		JSONObject bigant = JSONObject.fromObject(bigantUid);
		    		SysUser s = sysuserService.getByLoginname(user_login);
		    		String url="";
		    		if(s==null){
		    		    String recver_user_login="hmj";
		    		    String subject = "错误通知";
		    		    String content = "登录账号为"+user_login+"用户，在汇沟通中存在的，在本系统中不存在，请创建用户";
		    		  	bigAntUtil.send_user(recver_user_login, subject, content);
		    		  	map.put("msg", "会诊创建成功。通讯录不匹配，有用户，未通知到，请联系管理员！");
		    		}else if(s!=null){
		    			
		    		 	try {
		    		 		url = "http://timer.91veo.com/v1/meeting/join?id="+jsonObject.getString("id")+"&value="+jsonObject.getString("value")+"&uname="+s.getUserName();
		    		 		url=URLEncoder.encode(url,"UTF-8");
		    		 		String desc = "【汇智精英】"+sysUser.getUserName()+"老师，预定"+startTime+"召开会诊交流会，请您届时参加。";
		    	    		bigAntUtil.send_url(bigant.getJSONObject("data").getString(user_login), url, "会诊通知", desc);
		    	    		
		    	    		ma.put("message", desc);
		    	            ma.put("sendType", "会诊通知");
		    	            ma.put("recipient", s.getLoginName());
		    	            ma.put("receivePhone", s.getUserPhone());
		    	    		
		    	    		if(s.getUserPhone()!=null){
		    	    			String sendResult = NoteUtil.sendSMSchange("您好，"+sysUser.getUserName()+"老师，预定"+startTime+"召开会诊交流会，请您届时参加。【汇智精英】", s.getUserPhone());
		    	    			ma.put("sendState", sendResult);
		    	    		}
		    	    		
		    	    		int row = messageLogService.addMessageInfo(ma);
		        		} catch (UnsupportedEncodingException e) {
		        		  // TODO Auto-generated catch block
		        		  e.printStackTrace();
		        		}
		    		}
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
				Date d = sdf.parse(startTime);
				java.util.Date nowdate=new java.util.Date(); 
				map.put("code", 0);
				map.put("isStart", d.before(nowdate));
				map.put("zoomurl", "http://timer.91veo.com/v1/meeting/start?id="+jsonObject.getString("id")+"&value="+jsonObject.getString("value")+"&uname="+sysUser.getUserName());
			  } else {
				map.put("code", 1);
				map.put("msg", "创建会诊失败！");
			  } 
			} catch (Exception e) {
			  // TODO: handle exception
			  e.printStackTrace();
			}
			return map;
		  }
		  /**
		   * 根据主键修改一条会诊日程
		   * @param 
		   * @return
		   */
		  @RequestMapping(value="/updateById.do", method=RequestMethod.POST)
		  @ResponseBody
		  public Object updateById(@RequestParam String interventionKey,HttpServletRequest request, Model model){
			log.info("updateById");
			Map<String, Object> map = new HashMap<>();
			Map<String,Object> ma = new HashMap<String,Object>(); 
			DbcontextHolder.setDbType("imagedb");
			String json = request.getParameter("json");
			JSONObject returnData = JSONObject.fromObject(json);
			String startTime = returnData.getString("startTime");
			String yuyueshichang = returnData.getString("yuyueshichang");
			JSONArray member = returnData.getJSONArray("member");
			SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String zoomKey = returnData.getString("zoomKey");
			//获取当前系统用户
			HttpSession session = request.getSession();
			SysUser sysUser =  (SysUser) session.getAttribute("SysUser");
			//1.同过zoom修改视频会议，返回修改结果 
			String zoommsg = null;
			try {
			    //获取之前预约的zoom会议信息，进行删除，重新预约
			  	ZoomManageWithBLOBs delZoom = ZoomManageService.getById(zoomKey);
			    String delurl = ZoomManageUtil.spliceDelete_URL(delZoom.getId(), delZoom.getValue());
			    ZoomManageUtil.send_URL(delurl);
				String createurl = ZoomManageUtil.spliceCreate_URL(startTime, Integer.parseInt(yuyueshichang), member.size()+1, "会诊");
				zoommsg = ZoomManageUtil.send_URL(createurl);
			}catch (NumberFormatException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(zoommsg.equals("Error:0")){
				map.put("code", 1);
				map.put("msg","预约视频信息失败!");
				return map; 
			}
			JSONObject jsonObject = JSONObject.fromObject(zoommsg); 
			//2.记录zoom信息到数据库
			ZoomManageWithBLOBs zoomManage = new ZoomManageWithBLOBs();
			if(jsonObject.get("error")==null){
			  	//2.记录zoom信息到数据库
				
				try {
				  zoomManage.setEndTime(dft.parse(jsonObject.getString("end_time")));
				  zoomManage.setStartTime(dft.parse(startTime));
				  zoomManage.setMinute(Integer.parseInt(yuyueshichang));
				} catch (ParseException e2) {
				  // TODO Auto-generated catch block
				  e2.printStackTrace();
				}
				zoomManage.setId(jsonObject.getString("id"));
				zoomManage.setZoomKey(zoomKey);
				zoomManage.setCreatePerson(sysUser.getSysuserKey());
				zoomManage.setCreateTime(new Date());
				zoomManage.setIsDel("0");
				zoomManage.setJoinUrl(jsonObject.getString("join_url"));
				zoomManage.setMeetingCapacity(jsonObject.getInt("meeting_capacity"));
				zoomManage.setStartUrl(jsonObject.getString("start_url"));
				zoomManage.setState("1");//已预约
				zoomManage.setTopic(jsonObject.getString("topic"));
				zoomManage.setValue(jsonObject.getString("value"));
			}else{
				map.put("code", 1);
				map.put("msg", jsonObject.getString("error"));
				return map;
			}
			//3.记录会诊信息
				Intervention intervention = new Intervention();
				intervention.setZoomKey(zoomKey);
				intervention.setinterventionKey(interventionKey);
				intervention.setModifyTime(new Date());
				//4.记录与会人员
					List<InterventionConferee> conferees = new ArrayList<InterventionConferee>();
					for(int i=0;i<member.size();i++){
					    String loginname = member.getString(i);
					    SysUser sys = sysuserService.login(loginname);
						if(sys==null){//系统中没有这个登录名的用户。即在大蚂蚁中存在，本系统中不存在
						}else{
							InterventionConferee conferee = new InterventionConferee();
							conferee.setinterventionConfereeKey(UUid.getUUID());
							conferee.setinterventionKey(interventionKey);
							conferee.setSysuserKey(sys.getSysuserKey());
							conferees.add(conferee);
						}
					}
			
			try {
			  int imageCount = interventionService.updateById(intervention);
			  int zoomCount = ZoomManageService.zoomupdate(zoomManage);
			  //先删除之前的与会人员根据interventionKey
			  InterventionConferee interventionConferee = new InterventionConferee();
			  interventionConferee.setinterventionKey(interventionKey);
			  interventionConfereeService.deleteById(interventionConferee);
			  //记录与会人员
			  if(!conferees.isEmpty()){
				  interventionConfereeService.saveList(conferees);
			  }
			  //会诊创建成功，zoom信息保存成功，与人员保存成功
			  // 通过汇沟通发送消息
			  if (imageCount > 0 && zoomCount > 0 ) {
					//会诊创建成功，zoom信息保存成功，与人员保存成功
					//通过汇沟通发送消息
					for(int i=0;i<member.size();i++){
					    String user_login = member.getString(i);
			    		String bigantUid = bigAntUtil.get_uid(user_login);
			    		JSONObject bigant = JSONObject.fromObject(bigantUid);
			    		SysUser s = sysuserService.getByLoginname(user_login);
			    		String url="";
			    		if(s==null){
			    		    String recver_user_login="hmj";
			    		    String subject = "错误通知";
			    		    String content = "登录账号为"+user_login+"用户，在汇沟通中存在的，在本系统中不存在，请创建用户";
			    		  	bigAntUtil.send_user(recver_user_login, subject, content);
			    		  	map.put("msg", "会诊创建成功。通讯录不匹配，有用户，未通知到，请联系管理员！");
			    		}else if(s!=null){
			    			
			    		 	try {
			    		 		url = "http://timer.91veo.com/v1/meeting/join?id="+jsonObject.getString("id")+"&value="+jsonObject.getString("value")+"&uname="+s.getUserName();
			    		 		url=URLEncoder.encode(url,"UTF-8");
			    		 		String desc = "【汇智精英】"+sysUser.getUserName()+"老师，预定"+startTime+"召开会诊交流会，请您届时参加。";
			    	    		bigAntUtil.send_url(bigant.getJSONObject("data").getString(user_login), url, "会诊通知", desc);
			    	    		
			    	    		ma.put("message", desc);
			    	            ma.put("sendType", "会诊通知");
			    	            ma.put("recipient", s.getLoginName());
			    	            ma.put("receivePhone", s.getUserPhone());
			    	    		
			    	    		if(s.getUserPhone()!=null){
			  						String sendResult = NoteUtil.sendSMSchange(sysUser.getUserName()+"老师，预定"+startTime+"召开会诊交流会，请您届时参加。【汇智精英】", s.getUserPhone());
			  						ma.put("sendState", sendResult);
			    	    		}
			    	    		
			    	    		int row = messageLogService.addMessageInfo(ma);
			    		 	} catch (UnsupportedEncodingException e) {
			        		  // TODO Auto-generated catch block
			        		  e.printStackTrace();
			        		}
			    		}
			    		
			    		
					}
		    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
		    		Date d = sdf.parse(startTime);
		    		java.util.Date nowdate = new java.util.Date();
		    		map.put("code", 0);
		    		map.put("isStart", d.before(nowdate));
		    		map.put("zoomurl", "http://timer.91veo.com/v1/meeting/start?id=" + jsonObject.getString("id") + "&value="
		    		    + jsonObject.getString("value") + "&uname=" + sysUser.getUserName());
			  } else {
				map.put("code", 1);
				map.put("msg", "修改会诊失败！");
			  } 
			} catch (Exception e) {
			  // TODO: handle exception
			  e.printStackTrace();
			}
			return map;
		  }
		  /**
		   * 根据主键删除一条会诊日程
		   * @param 
		   * @return
		   */
		  @RequestMapping(value="/delByinterventionKey.do", method=RequestMethod.GET)
		  @ResponseBody
		  @Transactional
		  public Object delByinterventionKey(@RequestParam String interventionKey,HttpServletRequest request, Model model){
			log.info("delByinterventionKey");
			Map<String,Object> map = new HashMap<String,Object>();
			DbcontextHolder.setDbType("imagedb");
			Intervention intervention = new Intervention();
			intervention.setinterventionKey(interventionKey);
			Intervention delReadImage = interventionService.getById(intervention);
			ZoomManageWithBLOBs delZoom = ZoomManageService.getById(delReadImage.getZoomKey());
			String delurl = ZoomManageUtil.spliceDelete_URL(delZoom.getId(), delZoom.getValue());
			ZoomManageUtil.send_URL(delurl);
			
			InterventionConferee interventionConferee = new InterventionConferee();
			interventionConferee.setinterventionKey(interventionKey);
			try {
			  int interventionCount = interventionService.updateById(delReadImage);
			  int zoomCount = ZoomManageService.delById(delReadImage.getZoomKey());
			  interventionConfereeService.deleteById(interventionConferee);
			  if (interventionCount > 0 && zoomCount > 0) {
				map.put("code", 0);
			  } else {
				map.put("code", 1);
				map.put("msg", "删除会诊失败！");
			  } 
			} catch (Exception e) {
			  // TODO: handle exception
			  	map.put("code", 1);
			  	map.put("msg", "删除会诊失败！");
			  	e.printStackTrace();
			    return map;
			}
			return map;
		  }
		/**
		 * 跳转到会诊视频界面
		 * @param request
		 * @param model
		 * @return
		 */
		@RequestMapping(value="/joinReadImage.do",method=RequestMethod.GET)
		@ResponseBody
		public Object joinReadImage(@RequestParam String interventionKey,HttpServletRequest request,Model model){
			Map<String,Object> map = new HashMap<String,Object>();
			
			HttpSession session = request.getSession();
			SysUser sysUser =  (SysUser) session.getAttribute("SysUser");
			String sysuserKey = sysUser.getSysuserKey();
		  	DbcontextHolder.setDbType("imagedb");
		  	ZoomManage zoom = ZoomManageService.getByInterventionZoomKey(interventionKey);
		  	if(sysUser==null){
	  	  		map.put("code", 1);
	    	    map.put("msg", "参与会诊失败，请稍后重试或联系管理员");
	    	    return map;
		  	}
		  	if(zoom!=null){
		  	  	map.put("code", 0);
		  	  	map.put("zoom", zoom);
		  	  	map.put("sysuserKey", sysuserKey);
		  	  	map.put("username", sysUser.getUserName());
		  	}else{
		  	    map.put("code", 1);
		  	    map.put("msg", "参与会诊失败，请稍后重试或联系管理员");
		  	}
		  	
			return map;
		}
		/**
		 * 跳转到上传病历界面
		 * @param request
		 * @param model
		 * @return
		 */
		@RequestMapping(value="/interventionUpload.do",method=RequestMethod.GET)
		public Object interventionUpload(HttpServletRequest request,Model model){

			return "jieruhuizhen/upload_progress";
		}
		@RequestMapping(value = "/getProgress.do",method=RequestMethod.GET)    
	    @ResponseBody    
	    public Object initCreateInfo(HttpServletRequest request) {   
		    Map<String, Object> map = new HashMap<String, Object>();
	        CustomMultipartResolver query = new CustomMultipartResolver();  
	        
	        map.put("progress", query.getProgress("progress"));
	        return map;    
	    }   
		@RequestMapping(value="/uploaddcm.do",method=RequestMethod.POST)
		@Transactional
		@ResponseBody
	    public Object uploaddcm(HttpServletResponse response,MultipartHttpServletRequest request){
		    
	        Map<String, Object> map = new HashMap<String, Object>();
	        DbcontextHolder.setDbType("imagedb");
	        HttpSession session = request.getSession();
	        SysUser sysUser = (SysUser) session.getAttribute("SysUser");
	        String random = request.getParameter("random");
			MultipartFile mpf = request.getFile("file");
			String filename= mpf.getOriginalFilename();
			map.put("filename",filename);
			map.put("random",random);
			// 上传的dcm文件解析为dcm对象
	        DicomObject dcm = dicom(mpf);
	        if (dcm == null) {// 说明不是dcm文件 直接不存储，返回错误
	            map.put("code", 1);
	            return map;
	        }
	        savedcm(request, mpf);// 保存dcm文件图像
	        synchronized(this){
	        // 判断该dcm图像是否上传过
	        int m = medicalRecordService.getCountByStudy(dcm.getString(Tag.StudyInstanceUID));

	         InterventionWithBLOBs interventionWithBLOBs = new InterventionWithBLOBs();
	        if (m == 0) {
	            MedicalRecord medicalRecord = new MedicalRecord();
	            medicalRecord.setAge(dcm.getString(Tag.PatientAge));// 患者年龄
	            // medicalRecord.setBodypartCode(dcm.getString(Tag.));
	            medicalRecord.setBodypartName(dcm.getString(Tag.BodyPartExamined));// 检查部位
	            medicalRecord.setFilmNum(dcm.getString(Tag.NumberOfFilms));// 胶片数量
	            // medicalRecord.setFilmTime(dcm.getString(Tag.));
	            // medicalRecord.setHospital(dcm.getString(Tag.));
	            // medicalRecord.setImageNum(dcm.getString(Tag.));
	            String medicalRecordId = UUid.getUUID();
	            medicalRecord.setMedicalRecordKey(medicalRecordId);
	            medicalRecord.setModalityCode(dcm.getString(Tag.Modality));// 图像类型
	            medicalRecord.setPatientHistory(dcm.getString(Tag.AdditionalPatientHistory));// 额外病史
	            medicalRecord.setPatientKey(dcm.getString(Tag.PatientID));// 病人id
	            medicalRecord.setPatientName(dcm.getString(Tag.PatientName));// 病人姓名
	            // medicalRecord.setPatientType(dcm.getString(Tag.PatientSupportType));//
	            // 病人类型
	            // medicalRecord.setPrintFlag(dcm.getString(Tag.));
	            // medicalRecord.setPrintReport(dcm.getString(Tag.));
	            // medicalRecord.setPrintTime(dcm.getString(Tag.));
	            medicalRecord.setSeriesinstanceuid(dcm.getString(Tag.SeriesInstanceUID));// 序列id
	            medicalRecord.setSeriesNum(dcm.getInt(Tag.SeriesNumber));// 几个序列
	            medicalRecord.setSex(dcm.getString(Tag.PatientSex));// 病人性别
	            // medicalRecord.setState(dcm.getString(Tag.));
	            medicalRecord.setStudyId(dcm.getString(Tag.StudyID));// 检查id
	            medicalRecord.setStudyinstanceuid(dcm.getString(Tag.StudyInstanceUID));// 检查uid
	            medicalRecord.setStudyTime(dcm.getString(Tag.StudyDate));// 检查日期
	            
	            interventionWithBLOBs.setMedicalRecordKey(medicalRecordId);
	            medicalRecordService.save(medicalRecord);
	        } else {
	        	MedicalRecord record = medicalRecordService.getById(dcm.getString(Tag.StudyInstanceUID));
	        	interventionWithBLOBs.setMedicalRecordKey(record.getMedicalRecordKey());
	        }

	        try {
	        		int i=interventionService.getMedicalRecordBystuuid(dcm.getString(Tag.StudyInstanceUID));
	  	  				if(i==0){
	        			interventionWithBLOBs.setinterventionKey(UUid.getUUID());
	  	  				interventionWithBLOBs.setCreatePerson(sysUser.getSysuserKey());
	  	  				interventionWithBLOBs.setCreateTime(new Date());
	  	  				interventionWithBLOBs.setIsDel("0");
	  	  				interventionWithBLOBs.setState("1");
	      		  		int j = interventionService.save(interventionWithBLOBs);
	      		  		if(j>0){
	      		  		  	map.put("code", 0);
	      		  		}else{
	      		  		    map.put("code", 1);
	      		  		    map.put("msg", "上传dcm文件出错，请稍后重试或者联系管理员！");
	      		  		}
	      		  		}else {
	      	                map.put("code", 0);
	      	            }
	  		} catch (Exception e) {
	  			// TODO: handle exception
	  		  	e.printStackTrace();
	  		  	map.put("code", 1);
	  	  		map.put("msg", "上传dcm文件出错，请稍后重试或者联系管理员！");
	  	  		return map;
	  		}
	        return map;
	        }
		   
		}
		public DicomObject savedcm(HttpServletRequest request,MultipartFile mpf){
		    Properties prop = Util.readData("path.properties");
	        String savePath = prop.getProperty("dcmPath").trim();// 获取文件保存路径
	        
	        DicomObject dcm = dicom(mpf);
	        if(dcm==null){
	            return dcm;
	        }
	        String myFileName = mpf.getOriginalFilename();
	        // 如果名称不为“”,说明该文件存在，否则说明该文件不存在
	        if (myFileName.trim() != "") {
	            System.out.println(myFileName);
	            String fileExtName = myFileName.substring(myFileName.lastIndexOf(".")+1);
	            // 重命名上传后的文件名 按照年月日时分秒进行命名
	            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
	            //更改文件名称，防止重名
	            String newFileName = df.format(new Date()) + "_" + UUid.getUUID()   + "." + fileExtName;
	            // 定义上传路径
	            try {
	              File fileDir = new File(savePath);
	              if (!fileDir.exists()) { // 如果不存在 则创建
	                  fileDir.mkdirs();
	              }
	              // 存文件
	              File localFile = new File(savePath,newFileName);
	              OutputStream os = new FileOutputStream(localFile);  
	              InputStream is = mpf.getInputStream();  
	              byte buf[] = new byte[1024];// 可以修改 1024 以提高读取速度  
	              int length = 0;  
	              while ((length = is.read(buf)) > 0)  
	              {  
	                  os.write(buf, 0, length);  
	              }  
	              // 关闭流  
	              os.flush();  
	              os.close();  
	              is.close();
	              
	          } catch (IllegalStateException e) {
	              // TODO Auto-generated catch block
	              e.printStackTrace();
	          } catch (IOException e) {
	              // TODO Auto-generated catch block
	              e.printStackTrace();
	          }  
	        }
	        return dcm;
		}
		/**
	     * dimcom 文件解析
	     * @param args
	     */
	    public  DicomObject dicom(MultipartFile mpf){
	        DicomObject dcmObj = null;
	        DicomInputStream din=null;
	        InputStream inputStream = null;
	        try {
	            inputStream = mpf.getInputStream();
	            din = new DicomInputStream(inputStream);  
	            dcmObj = din.readDicomObject();
	            return dcmObj;
	        } catch (Exception e) {  
	            e.printStackTrace();  
	            return null;
	        } finally{  
	            try {
	                if(din!=null){
	                    din.close();
	                }else{
	                    return null;
	                }
	            } catch (IOException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	        }
	    }
	   

}
