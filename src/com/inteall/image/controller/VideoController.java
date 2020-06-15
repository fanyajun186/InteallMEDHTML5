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
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

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
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.inteall.image.pojo.MedicalRecord;
import com.inteall.image.pojo.SysUser;
import com.inteall.image.pojo.Video;
import com.inteall.image.pojo.VideoMedicalRecord;
import com.inteall.image.pojo.VideoTutorials;
import com.inteall.image.pojo.ZoomManage;
import com.inteall.image.pojo.ZoomManageWithBLOBs;
import com.inteall.image.service.MedicalRecordService;
import com.inteall.image.service.MessageLogService;
import com.inteall.image.service.SysUserService;
import com.inteall.image.service.VideoMedicalRecordService;
import com.inteall.image.service.VideoService;
import com.inteall.image.service.VideoTutorialsService;
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
@RequestMapping("/video")
public class VideoController {
	private static Logger log = Logger.getLogger(VideoController.class.getName());
	@Resource
	private VideoService videoService;
	@Resource
	private VideoTutorialsService videoTutorialsService;
	@Resource
	private ZoomManageService ZoomManageService;
	@Resource
	private MedicalRecordService medicalRecordService;
	@Resource
	private VideoMedicalRecordService videoMedicalRecordService;
	@Resource
	private SysUserService sysuserService;
	private String ssid = "E6300170-E5B0-B072-E18D-74A870934D48";
	private String uid = "116";
	private String uname = "电子客服";
	private BigAntUtil bigAntUtil = new BigAntUtil(ssid, uid, uname);
	
	 @Resource
	 private MessageLogService messageLogService;
	
	/**
	 * 跳转到新增教学视频界面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/videoAdd.do",method=RequestMethod.GET)
	public Object videoAdd(HttpServletRequest request,Model model){
		
		return "shipinjiaoxue/add";
	}
	/**
	 * 详情跳转到上传附件界面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/videoXqUpload.do",method=RequestMethod.GET)
	public Object videoXqUpload(@RequestParam String stuuid,HttpServletRequest request,Model model){
		model.addAttribute("stuuid", stuuid);
		return "shipinjiaoxue/xiangqing/upload_progress";
	}
	/**
	 * 跳转到上传课件界面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/videoKeJianUpload.do",method=RequestMethod.GET)
	public Object videoKeJianUpload(@RequestParam String videoKey,HttpServletRequest request,Model model){
		model.addAttribute("videoKey", videoKey);
		return "shipinjiaoxue/kejianupload_progress";
	}
	/**
	 * 跳转到上传病历界面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/videoUpload.do",method=RequestMethod.GET)
	public Object videoUpload(@RequestParam String videoKey,HttpServletRequest request,Model model){
	  	model.addAttribute("videoKey", videoKey);
		return "shipinjiaoxue/upload_progress";
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
        Map<String,Object> map = new HashMap<String,Object>();
	  	DbcontextHolder.setDbType("imagedb");
	  	HttpSession session = request.getSession();
		SysUser sysUser =  (SysUser) session.getAttribute("SysUser");
		String videokey = request.getParameter("videoKey");
		String random = request.getParameter("random");
		MultipartFile mpf = request.getFile("file");
		String filename= mpf.getOriginalFilename();
		map.put("filename",filename);
		map.put("random",random);
		// 上传的dcm文件解析为dcm对象
        DicomObject dcm = upload(request,mpf);
        if (dcm ==null) {
            map.put("code", 1);
            return map;
        }
        synchronized(this){
	  	//判断该dcm图像是否上传过
	  	int m = medicalRecordService.getCountByStudy(dcm.getString(Tag.StudyInstanceUID));
	  	VideoMedicalRecord videoMedicalRecord = new VideoMedicalRecord();
	  	if(m==0){
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
  //    	medicalRecord.setPatientType(dcm.getString(Tag.PatientSupportType));// 病人类型
  //    	medicalRecord.setPrintFlag(dcm.getString(Tag.));
  //    	medicalRecord.setPrintReport(dcm.getString(Tag.));
  //    	medicalRecord.setPrintTime(dcm.getString(Tag.));
        	medicalRecord.setSeriesinstanceuid(dcm.getString(Tag.SeriesInstanceUID));// 序列id
        	medicalRecord.setSeriesNum(dcm.getInt(Tag.SeriesNumber));// 几个序列
        	medicalRecord.setSex(dcm.getString(Tag.PatientSex));// 病人性别
  //    	medicalRecord.setState(dcm.getString(Tag.));
        	medicalRecord.setStudyId(dcm.getString(Tag.StudyID));// 检查id
        	medicalRecord.setStudyinstanceuid(dcm.getString(Tag.StudyInstanceUID));// 检查uid
        	medicalRecord.setStudyTime(dcm.getString(Tag.StudyDate));// 检查日期
        	
        	medicalRecordService.save(medicalRecord);
        	videoMedicalRecord.setRecordKey(medicalRecordId);
	  	}else{
	  	  	MedicalRecord r = medicalRecordService.getById(dcm.getString(Tag.StudyInstanceUID));
	  	 	videoMedicalRecord.setRecordKey(r.getMedicalRecordKey());
	  	}
    	
	  	videoMedicalRecord.setVideoKey(videokey);
	  	videoMedicalRecord.setVideoMedicalRecordKey(UUid.getUUID());
    	
	  	videoMedicalRecord.setAppendTime(new Date());
	  	videoMedicalRecord.setSysuserKey(sysUser.getSysuserKey());
	  		
	  	try {
  	  	  HashMap<String, String> getParamMap = new HashMap<String, String>();
  	  	  getParamMap.put("videokey", videokey);
  	  	  getParamMap.put("stuuid", dcm.getString(Tag.StudyInstanceUID));
	  	  int i = videoMedicalRecordService.getByStuuid(getParamMap);
	  	  System.out.println(i);
	  	  if(i==0&&dcm.getString(Tag.StudyInstanceUID)!=null){
    		  int videoRecordCount = videoMedicalRecordService.save(videoMedicalRecord);
    		  if(videoRecordCount>0){
    			map.put("code", 0);
    		  }else{
    			map.put("code", 1);
    		  }
	  	  }else{
	  		  map.put("code", 0);
	  	  }
		  
		} catch (Exception e) {
		  // TODO: handle exception
		  e.printStackTrace();
		}
		return map;
        }
	}
    private DicomObject upload(HttpServletRequest request,MultipartFile mpf) {
        
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
//	public DicomObject upload(HttpServletRequest request,HttpServletResponse response){
//	  	DicomObject dcm = null ;
//  		// 创建一个通用的多部分解析器
//    	CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
//    	// 判断 request 是否有文件上传,即多部分请求
//    	if (multipartResolver.isMultipart(request)) {
//    		// 转换成多部分request
//    	  	MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
//    	  	// 取得request中的所有文件名
//    	  	Iterator<String> iter = multiRequest.getFileNames();
//    	  	while (iter.hasNext()) {
//    	  	  	// 取得上传文件
//    	  	  	MultipartFile file = multiRequest.getFile(iter.next());
//    	  	  	if (file != null) {
//    	  	  	  	// 取得当前上传文件的文件名称
//    	  	  	  	String myFileName = file.getOriginalFilename();
//    	  	  	  	// 如果名称不为“”,说明该文件存在，否则说明该文件不存在
//    	  	  	  	if (myFileName.trim() != "") {
//    	  	  	  	  	System.out.println(myFileName);
//    	  	  	  	  	String fileExtName = myFileName.substring(myFileName.lastIndexOf(".")+1);
//    	  	  	  	  	// 重命名上传后的文件名 按照年月日时分秒进行命名
//    	  	  	  	  	SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
//        		        //更改文件名称，防止重名
//        		        String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000)+ "_" + new Random().nextInt(1000)  + "." + fileExtName;
//            			// 定义上传路径
//            			try {
//            			  	Properties prop = Util.readData("path.properties");
//            		  	    String savePath = prop.getProperty("dcmPath").trim();// 获取文件保存路径
//            				File fileDir = new File(savePath);
//            			  	if (!fileDir.exists()) { // 如果不存在 则创建
//            			  		fileDir.mkdirs();
//            			  	}
//            			  	// 存文件
//            			  	File localFile = new File(savePath,newFileName);
//            			  	file.transferTo(localFile);
//            			  	
//            			  	dcm = dicom(file);
//            			} catch (IllegalStateException e) {
//                			// TODO Auto-generated catch block
//                			e.printStackTrace();
//            			} catch (IOException e) {
//            				// TODO Auto-generated catch block
//            				e.printStackTrace();
//            			}
//            			
//        		  }
//        		}
//    	  	}
//    	}
//    	return dcm;
//	}
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
	/**
	 * 获取与我有关的视频日程信息
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/getVideoDate.do",method=RequestMethod.POST)
	@ResponseBody
	public Object getVideoDate(HttpServletRequest request,HttpServletResponse reponse,Model model){
		Map<String, Object> map = new HashMap<>();
		DbcontextHolder.setDbType("imagedb");
	  	//获取当前系统用户
		HttpSession session = request.getSession();
		SysUser sysUser =  (SysUser) session.getAttribute("SysUser");
		if(sysUser==null){
			map.put("code", 1);
			map.put("msg", "暂无数据！");
		}
		//1.查询当前用户创建的视频教学
		Video video = new Video();
		video.setCreatePerson(sysUser.getSysuserKey());
		//2.查询当前用户被要请的视频教学
		List<Video> videoList = videoService.getByTutorials(video);
		if(videoList==null||videoList.size()==0){
		  	map.put("code", 1);
			map.put("msg", "暂无数据！");
		}else{	
			map.put("code", 0);
			map.put("videoList", videoList);
		}
		return map;
	}
	/**
	 * 获取与我有关的视频日程信息
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/getVideoDateById.do",method=RequestMethod.POST)
	@ResponseBody
	public Object getVideoDateById(HttpServletRequest request,HttpServletResponse reponse,Model model){
		Map<String, Object> map = new HashMap<>();
		DbcontextHolder.setDbType("imagedb");
		//获取当前系统用户
		HttpSession session = request.getSession();
		SysUser sysUser =  (SysUser) session.getAttribute("SysUser");
		if(sysUser==null){
			map.put("code", 1);
			map.put("msg", "暂无数据！");
		}
		//1.查询当前用户创建的视频教学
		Video video = new Video();
		video.setCreatePerson(sysUser.getSysuserKey());
		//2.查询当前用户被要请的视频教学
		List<Video> videoList = videoService.getByTutorialsById(video);
		if(videoList==null||videoList.size()==0){
			map.put("code", 1);
			map.put("msg", "暂无数据！");
		}else{	
			map.put("code", 0);
			map.put("videoList", videoList);
		}
		return map;
	}
	/**
     * 增加一条新视频教学日程
     * @param 
     * @return
     */
  @RequestMapping(value="/videoSave.do", method=RequestMethod.POST)
  @ResponseBody
  @Transactional(rollbackFor=Exception.class)
  public Object videoSave(HttpServletRequest request, Model model){
	log.info("videoSave");
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
	String state="1";
	String zoommsg = null;
	try {
	  String createurl = ZoomManageUtil.spliceCreate_URL(startTime, Integer.parseInt(yuyueshichang), member.size()+1, "视频教学");
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
	//3.记录视频教学信息
	String video_key = UUid.getUUID();
	Video video = new Video();
	video.setVideoKey(video_key);
	video.setState(state);
	video.setZoomKey(zoom_key);
	video.setCreatePerson(sysUser.getSysuserKey());
	video.setCreateTime(new Date());
	try {
		video.setStartTime(dft.parse(startTime));
	} catch (ParseException e1) {
	  // TODO Auto-generated catch block
	  e1.printStackTrace();
	}
	video.setIsDel("0");
	//4.记录与会人员
	List<VideoTutorials> videoTutorials = new ArrayList<VideoTutorials>();
	for(int i=0;i<member.size();i++){
	    String loginname = member.getString(i);
	    SysUser sys = sysuserService.login(loginname);
		if(sys==null){//系统中没有这个登录名的用户。即在大蚂蚁中存在，本系统中不存在
		}else{
			VideoTutorials tutorials = new VideoTutorials();
			tutorials.setVideoTutorialsKey(UUid.getUUID());
			tutorials.setVideoKey(video_key);
			tutorials.setSysuserKey(sys.getSysuserKey());
			videoTutorials.add(tutorials);
		}
	}
	try {
	  int videoCount = videoService.videoSave(video);
	  int zoomCount = ZoomManageService.zoomcreate(zoomManage);
	  if(!videoTutorials.isEmpty()){
		  videoTutorialsService.saveList(videoTutorials);
	  }
	  if (videoCount > 0 && zoomCount > 0 ) {
		//视频教学创建成功，zoom信息保存成功，与人员保存成功
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
    		  	map.put("msg", "视频教学创建成功。通讯录不匹配，有用户，未通知到，请联系管理员！");
    		}else if(s!=null){
    			
    		 	try {
    		 		url = "http://timer.91veo.com/v1/meeting/join?id="+jsonObject.getString("id")+"&value="+jsonObject.getString("value")+"&uname="+s.getUserName();
    		 		url=URLEncoder.encode(url,"UTF-8");
    		 		String desc = "【汇智精英】"+sysUser.getUserName()+"老师，预定"+startTime+"召开视频教学，请您届时参加。";
    	    		bigAntUtil.send_url(bigant.getJSONObject("data").getString(user_login), url, "视频教学通知", desc);
    	    		
    	    		 ma.put("message", desc);
    	             ma.put("sendType", "视频教学");
    	             ma.put("recipient", s.getLoginName());
    	             ma.put("receivePhone", s.getUserPhone());
    	    		
    	    		if(s.getUserPhone()!=null){
    	    			String sendResult = NoteUtil.sendSMSchange("您好，"+sysUser.getUserName()+"老师，预定"+startTime+"召开视频教学，请您届时参加。【汇智精英】", s.getUserPhone());
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
		map.put("msg", "创建视频教学失败！");
	  } 
	} catch (Exception e) {
	  // TODO: handle exception
	  e.printStackTrace();
	}
	return map;
  }
  /**
   * 根据主键查询一条视频教学日程
   * @param 
   * @return
   */
  @RequestMapping(value="/getById.do", method=RequestMethod.GET)
  public Object getById(@RequestParam String id, HttpServletRequest request, Model model) throws ParseException {
	log.info("getById");
	DbcontextHolder.setDbType("imagedb");
	//根据视频教学id获取视频教学内容
	Video video = new Video();
	video.setVideoKey(id);
	Video videoResult = videoService.getById(video);
	
	//根据视频教学id获取zoom信息
	ZoomManage zoomManage = ZoomManageService.getByVideoKey(id);
	
	//根据视频教学id获取视频教学人员
	VideoTutorials videoTutorials = new VideoTutorials();
	videoTutorials.setVideoKey(id);
	List<SysUser> sysUsers = videoTutorialsService.getByVideoKey(videoTutorials);
	String usernames = "";
	String login = "";
	for(SysUser s:sysUsers){
	  usernames +=s.getUserName()+"," ;
	  login +=s.getLoginName()+",";
	}
	SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String start_time = dft.format(videoResult.getStartTime());
	model.addAttribute("yuyueshichang", zoomManage.getMinute());
	model.addAttribute("start_time", start_time);
	model.addAttribute("yuhuirenyuan", usernames);
	model.addAttribute("videoKey", id);
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
	return "shipinjiaoxue/edit";
  }
  /**
   * 根据主键修改一条视频教学日程
   * @param 
   * @return
   */
  @RequestMapping(value="/videoUpdateById.do", method=RequestMethod.POST)
  @ResponseBody
  public Object videoUpdateById(HttpServletRequest request, Model model){
	log.info("videoUpdateById");
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
	String videoKey = returnData.getString("videoKey");
	
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
		String createurl = ZoomManageUtil.spliceCreate_URL(startTime, Integer.parseInt(yuyueshichang), member.size()+1, "视频教学");
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
	
	//3.记录视频教学信息
	Video video = new Video();
	video.setVideoKey(videoKey);
	try {
		video.setStartTime(simpleDateFormat.parse(startTime));
	} catch (ParseException e1) {
	  // TODO Auto-generated catch block
	  e1.printStackTrace();
	}
	try {
		video.setStartTime(dft.parse(startTime));
	} catch (ParseException e1) {
	  // TODO Auto-generated catch block
	  e1.printStackTrace();
	}

	video.setZoomKey(zoomKey);
	video.setModifyTime(new Date());
	
	//4.记录与会人员
	List<VideoTutorials> tutorials = new ArrayList<VideoTutorials>();
	for(int i=0;i<member.size();i++){
	    String loginname = member.getString(i);
	    SysUser sys = sysuserService.login(loginname);
		
		if(sys==null){//系统中没有这个登录名的用户。即在大蚂蚁中存在，本系统中不存在
			
		}else{
			VideoTutorials 	tutorial = new VideoTutorials();
			tutorial.setVideoTutorialsKey(UUid.getUUID());
			tutorial.setVideoKey(videoKey);
			tutorial.setSysuserKey(sys.getSysuserKey());
			tutorials.add(tutorial);
		}
	}
	
	
	try {
	  int videoCount = videoService.updateById(video);
	  int zoomCount = ZoomManageService.zoomupdate(zoomManage);
	  //先删除之前的与会人员根据videoid
	  VideoTutorials videoTutorials = new VideoTutorials();
	  videoTutorials.setVideoKey(videoKey);
	  videoTutorialsService.deleteById(videoTutorials);
	  //记录与会人员
	  if(!tutorials.isEmpty()){
		  videoTutorialsService.saveList(tutorials);
	  }
	  //视频教学创建成功，zoom信息保存成功，与人员保存成功
	  // 通过汇沟通发送消息
	  if (videoCount > 0 && zoomCount > 0 ) {
			//视频教学创建成功，zoom信息保存成功，与人员保存成功
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
	    		  	map.put("msg", "视频教学创建成功。通讯录不匹配，有用户，未通知到，请联系管理员！");
	    		}else if(s!=null){
	    			
	    		 	try {
	    		 		url = "http://timer.91veo.com/v1/meeting/join?id="+jsonObject.getString("id")+"&value="+jsonObject.getString("value")+"&uname="+s.getUserName();
	    		 		url=URLEncoder.encode(url,"UTF-8");
	    		 		String desc = "【汇智精英】"+sysUser.getUserName()+"老师，预定"+startTime+"召开视频教学，请您届时参加。";
	    	    		bigAntUtil.send_url(bigant.getJSONObject("data").getString(user_login), url, "视频教学通知", desc);
	    	    		
	    	    		 ma.put("message", desc);
	    	             ma.put("sendType", "视频教学");
	    	             ma.put("recipient", s.getLoginName());
	    	             ma.put("receivePhone", s.getUserPhone());
	    	    		
	    	    		if(s.getUserPhone()!=null){
	    	    			String sendResult = NoteUtil.sendSMSchange("您好，"+sysUser.getUserName()+"老师，预定"+startTime+"召开视频教学，请您届时参加。【汇智精英】", s.getUserPhone());
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
		map.put("msg", "修改视频教学失败！");
	  } 
	} catch (Exception e) {
	  // TODO: handle exception
	  e.printStackTrace();
	}
	return map;
  }
  /**
   * 根据主键删除一条视频教学日程
   * @param 
   * @return
   */
  @RequestMapping(value="/delByVideokey.do", method=RequestMethod.GET)
  @ResponseBody
  @Transactional
  public Object delByVideokey(@RequestParam String videoKey,HttpServletRequest request, Model model){
	log.info("delByVideokey");
	Map<String,Object> map = new HashMap<String,Object>();
	
	DbcontextHolder.setDbType("imagedb");
	Video video = new Video();
	video.setVideoKey(videoKey);
	
	
	Video delVideo = videoService.getById(video);
	delVideo.setIsDel("1");
	delVideo.setDelTime(new Date());
	
	ZoomManageWithBLOBs delZoom = ZoomManageService.getById(delVideo.getZoomKey());
	String delurl = ZoomManageUtil.spliceDelete_URL(delZoom.getId(), delZoom.getValue());
	ZoomManageUtil.send_URL(delurl);
	
	VideoTutorials videoTutorials = new VideoTutorials();
	videoTutorials.setVideoKey(videoKey);
	
	try {
	  int videoCount = videoService.updateById(delVideo);
	  int zoomCount = ZoomManageService.delById(delVideo.getZoomKey());
	  videoMedicalRecordService.deleteByVideoKey(videoKey);
	  videoTutorialsService.deleteById(videoTutorials);
	  if (videoCount > 0 && zoomCount > 0) {
		map.put("code", 0);
	  } else {
		map.put("code", 1);
		map.put("msg", "删除视频教学失败！");
	  } 
	} catch (Exception e) {
	  // TODO: handle exception
	  	map.put("code", 1);
	  	map.put("msg", "删除视频教学失败！");
	  	e.printStackTrace();
	    return map;
	}
	return map;
  }
  /**
	 * 跳转到视频教学界面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/joinVideo.do",method=RequestMethod.GET)
	@ResponseBody
	public Object joinVideo(@RequestParam String videoKey,HttpServletRequest request,Model model){
		Map<String,Object> map = new HashMap<String,Object>();
		
		HttpSession session = request.getSession();
		SysUser sysUser =  (SysUser) session.getAttribute("SysUser");
		String sysuserKey = sysUser.getSysuserKey();
	  	DbcontextHolder.setDbType("imagedb");
	  	ZoomManage zoom = ZoomManageService.getByVideoKey(videoKey);
	  	if(sysUser==null){
	  		map.put("code", 1);
	  		map.put("msg", "参与视频教学失败，请稍后重试或联系管理员");
  	    return map;
	  	}
	  	if(zoom!=null){
	  	  	map.put("code", 0);
	  	  	map.put("zoom", zoom);
	  	  	map.put("sysuserKey", sysuserKey);
	  	  	map.put("username", sysUser.getUserName());
	  	}else{
	  	    map.put("code", 1);
	  	    map.put("msg", "参与视频教学失败，请稍后重试或联系管理员");
	  	}
	  	
		return map;
	}
	/**
	 * 视频教学详情：根据用户的id查询用户信息
	 * @param id
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value="/getVideoById.do",method=RequestMethod.GET)
	public Object getVideoById(@RequestParam String stuuid,String videoKey,HttpServletRequest request,Model model) throws ParseException{
		log.info("getVideoById");
		DbcontextHolder.setDbType("imagedb");
		MedicalRecord medicalRecord = medicalRecordService.getById(stuuid);
		//根据读片会id获取读片会内容
		Video video = new Video();
		video.setVideoKey(videoKey);
		Video videoResult = videoService.getById(video);
		
		//根据读片会id获取zoom信息
		ZoomManage zoomManage = ZoomManageService.getByVideoKey(videoKey);
		
		//根据读片会id获取读片会人员
		VideoTutorials videoTutorials = new VideoTutorials();
		videoTutorials.setVideoKey(videoKey);
		List<SysUser> sysUsers = videoTutorialsService.getByVideoKey(videoTutorials);
		String usernames = "";
		String login = "";
		for(SysUser s:sysUsers){
		  usernames +=s.getUserName()+"," ;
		  login +=s.getLoginName()+",";
		}
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String start_time = dft.format(videoResult.getStartTime());
		model.addAttribute("yuyueshichang", zoomManage.getMinute());
		model.addAttribute("start_time", start_time);
		model.addAttribute("yuhuirenyuan", usernames);
		model.addAttribute("videoKey", videoKey);
		model.addAttribute("zoomKey", zoomManage.getZoomKey());
		model.addAttribute("login", login);
		Date d = dft.parse(start_time);
		Date nowdate=new Date(); 
		boolean isEdit = false;
		long diff = d.getTime()-nowdate.getTime();
		if(diff/1000/60>15){
			isEdit = true;
		}
		model.addAttribute("stuuid", stuuid);
		model.addAttribute("isEdit", isEdit);
		model.addAttribute("medicalRecord", medicalRecord);
		return "shipinjiaoxue/xiangqing/index";
	}
	 /**
	   * 根据stuyid删除视频教学中的病历
	   * 删的是关联表，不是病历表
	   * @param 
	   * @return
	   */
	  @RequestMapping(value="/delByStuuid.do", method=RequestMethod.GET)
	  @ResponseBody
	  public Object delByStuuid(@RequestParam String stuuid,@RequestParam String videoKey, HttpServletRequest request, Model model) throws ParseException {
		log.info("delByStuuid");
		HashMap<String, String> getParamMap = new HashMap<String, String>();
		DbcontextHolder.setDbType("imagedb");
		getParamMap.put("stuuid", stuuid);
		getParamMap.put("videoKey", videoKey);
		int row = videoMedicalRecordService.delByStuuid(getParamMap);
		
		Map<String,Object> map = new HashMap<String,Object>();
		if(row>0){
		  map.put("code", 0);
		}else{
		  map.put("code", 1);
		  map.put("msg", "删除失败，请稍后重试！");
		}
		return map;
	  }
}