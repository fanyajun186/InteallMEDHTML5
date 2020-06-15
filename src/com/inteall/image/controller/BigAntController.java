package com.inteall.image.controller;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.google.gson.Gson;
import com.inteall.image.pojo.BigAnt;
import com.inteall.image.pojo.BigAntUser;
import com.inteall.image.pojo.CollectionMedicalRecord;
import com.inteall.image.pojo.Group;
import com.inteall.image.pojo.GroupMeidicalRecord;
import com.inteall.image.pojo.MedicalRecord;
import com.inteall.image.pojo.ReadImage_Record;
import com.inteall.image.pojo.SysUser;
import com.inteall.image.pojo.VideoMedicalRecord;
import com.inteall.image.service.BigAntService;
import com.inteall.image.service.CollectionMedicalRecordService;
import com.inteall.image.service.MedicalRecordService;
import com.inteall.image.service.ReadImage_RecordService;
import com.inteall.image.service.VideoMedicalRecordService;
import com.inteall.image.util.BigAntUtil;
import com.inteall.image.util.CustomMultipartResolver;
import com.inteall.image.util.DbcontextHolder;
import com.inteall.image.util.UUid;
import com.inteall.image.util.Util;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("bigant")
public class BigAntController {
	@Resource
	private BigAntService bigAntService;
	@Resource
	private VideoMedicalRecordService videoMedicalRecordService;
	@Resource
	private ReadImage_RecordService readImage_RecordService;
	@Resource
	private CollectionMedicalRecordService collectionMedicalRecordService;
	@Resource
	private MedicalRecordService medicalRecordService;
	private String ssid = "E6300170-E5B0-B072-E18D-74A870934D48";
	private String uid = "116";
	private String uname = "电子客服";
	private BigAntUtil bigAntUtil = new BigAntUtil(ssid, uid, uname);
	@RequestMapping(value="/getGroupInfoByUserId.do",method=RequestMethod.POST)
	@ResponseBody
	public Object getGroupInfoByUserId(HttpServletRequest request){
	  	Map<String,Object> map = new HashMap<String,Object>();
	  	//获取当前系统用户
		HttpSession session = request.getSession();
		SysUser sysUser =  (SysUser) session.getAttribute("SysUser");
		
		DbcontextHolder.setDbType("antdbms_inteall");
		//根据当前用户的登录名获取大蚂蚁中的用户信息
		List<Group> groups = bigAntService.getGroupListByLogin(sysUser.getLoginName());
		//切换数据源
		
		if(groups!=null&&groups.size()>0){
		  map.put("code", 0);
		  map.put("groups",groups);
		}else{
		  map.put("code", 1);
		  map.put("msg", "暂无群组信息");
		}
		return map;
	}
	
	@RequestMapping(value="/getMemberByGroupId.do",method=RequestMethod.POST)
	@ResponseBody
	public Object getMemberByGroupId(HttpServletRequest request){
	    String groupId = request.getParameter("groupId");
		Map<String,Object> map = new HashMap<String,Object>();
	  	
		DbcontextHolder.setDbType("antdbms_inteall");
		List<BigAntUser> users = bigAntService.getUserByGroupId(groupId);
		
	  	if(users!=null&&users.size()>0){
		  map.put("code", 0);
		  map.put("users",users);
		}else{
		  map.put("code", 1);
		}
		return map;
	}
	
	/**
	 * 跳转到上传病历界面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/redirectUpload.do",method=RequestMethod.GET)
	public Object redirectUpload(@RequestParam String group_id,HttpServletRequest request,Model model){
	  	model.addAttribute("group_id", group_id);
		return "quntaolun/upload_progress";
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
	@ResponseBody
	@Transactional
	public Object uploaddcm(HttpServletResponse response,MultipartHttpServletRequest request){
	  	Map<String,Object> map = new HashMap<String,Object>();
	  	DbcontextHolder.setDbType("imagedb");
	  	HttpSession session = request.getSession();
		SysUser sysUser =  (SysUser) session.getAttribute("SysUser");
		String group_id = request.getParameter("group_id");
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
	  	GroupMeidicalRecord groupMeidicalRecord = new GroupMeidicalRecord();
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
        	groupMeidicalRecord.setMedicalRecordKey(medicalRecordId);
	  	}else{
	  	  	MedicalRecord r = medicalRecordService.getById(dcm.getString(Tag.StudyInstanceUID));
	  	  	groupMeidicalRecord.setMedicalRecordKey(r.getMedicalRecordKey());
	  	}
	  	groupMeidicalRecord.setGroupKey(group_id);
	  	groupMeidicalRecord.setGroupMeidicalRecordKey(UUid.getUUID());
    	
	  	groupMeidicalRecord.setCreatePerson(sysUser.getSysuserKey());
	  	groupMeidicalRecord.setCreateTime(new Date());
	  		
	  	try {
  	  	  HashMap<String, String> getParamMap = new HashMap<String, String>();
  	  	  getParamMap.put("group_id", group_id);
  	  	  getParamMap.put("stuuid", dcm.getString(Tag.StudyInstanceUID));
	  	  int i = bigAntService.getByStuuid(getParamMap);
	  	  System.out.println(i);
	  	  if(i==0&&dcm.getString(Tag.StudyInstanceUID)!=null){
    		  int groupMeidicalRecordCount = bigAntService.save(groupMeidicalRecord);
    		  if(groupMeidicalRecordCount>0){
    			map.put("code", 0);
    			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	        String content = "【汇智精英】"+sysUser.getUserName()+"老师，于"+sdf.format(new Date())+"分享了一个病历，请您参与讨论！";
    	        bigAntUtil.send_group("service", group_id, "群讨论通知", content);
    		  }else{
    			map.put("code", 1);
    			map.put("msg", "群讨论上传病历失败！");
    		  }
	  	  }else{
	  		  map.put("code", 0);
	  	  }
	  	  
		} catch (Exception e) {
		  // TODO: handle exception
		  e.printStackTrace();
		  map.put("code", 1);
          map.put("msg", "群讨论上传病历失败！");
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
//    	  	  	// 取得上传文件Suffering
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
    public  DicomObject dicom(MultipartFile file){
    	DicomObject dcmObj = null;
		DicomInputStream din=null;
		InputStream inputStream = null;
		try {
		  	inputStream = file.getInputStream();
    		din = new DicomInputStream(inputStream);  
    		dcmObj = din.readDicomObject();
	    	
    	} catch (IOException e) {  
    		e.printStackTrace();  
    	} finally{  
	    	try {
			  din.close();
			} catch (IOException e) {
			  // TODO Auto-generated catch block
			  e.printStackTrace();
			}  
    	}
    	return dcmObj;
    }
    
    @RequestMapping(value="/getRecordByGroupId.do",method=RequestMethod.POST)
	@ResponseBody
    public Object getRecordByGroupId(HttpServletRequest request,HttpServletResponse response){
    	Map<String,Object> map = new HashMap<String,Object>();
	  	DbcontextHolder.setDbType("imagedb");
		
		
		String group_id = request.getParameter("group_id");
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
		queryMap.put("group_id", group_id);
		
		
		
		//先查询满足条件的数据有多少条
		int count = bigAntService.getMedicalCount(queryMap);
		
		queryMap.put("curr", (page-1)*limit);
		queryMap.put("limit",limit);
		
		List<MedicalRecord> meidicalRecords = bigAntService.getRecordByGroupId(queryMap);
		
		if(meidicalRecords!=null){
		  map.put("code", 0);
		  map.put("data", meidicalRecords);
		  map.put("count", count);
		}else{
		  map.put("code", 1);
		  map.put("msg", "查询群讨论病历出错，请稍后重试或者联系管理员！");
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
		DbcontextHolder.setDbType("imagedb");
		model.addAttribute("medicalRecordKey", medicalRecordKey);
		return "quntaolun/share/shipinjiaoxueshare";
	  }
	  @RequestMapping(value="/getSaveShareVideo", method=RequestMethod.POST)
	  @ResponseBody
	  public Object getSaveShareVideo(HttpServletRequest request, Model model){
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
	   * 跳转到分享读片会界面
	   * @param 
	   * @return
	   */
	  @RequestMapping(value="/getByShareRreadimageId.do", method=RequestMethod.GET)
	  public Object getByShareRreadimageId(@RequestParam String medicalRecordKey, HttpServletRequest request, Model model){
		DbcontextHolder.setDbType("imagedb");
		model.addAttribute("medicalRecordKey", medicalRecordKey);
		return "quntaolun/share/dupianhuishare";
	  }
	  @RequestMapping(value="/getSaveShareRreadimage", method=RequestMethod.POST)
	  @ResponseBody
	  public Object getSaveShareRreadimage(HttpServletRequest request, Model model){
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
		  return "quntaolun/share/shoucangjiashare";
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
