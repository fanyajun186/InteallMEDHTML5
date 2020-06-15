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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.inteall.image.pojo.MedicalRecord;
import com.inteall.image.pojo.ReadImage;
import com.inteall.image.pojo.ReadImage_Conferee;
import com.inteall.image.pojo.ReadImage_Record;
import com.inteall.image.pojo.SysUser;
import com.inteall.image.pojo.ZoomManage;
import com.inteall.image.pojo.ZoomManageWithBLOBs;
import com.inteall.image.service.MedicalRecordService;
import com.inteall.image.service.MessageLogService;
import com.inteall.image.service.ReadImageService;
import com.inteall.image.service.ReadImage_ConfereeService;
import com.inteall.image.service.ReadImage_RecordService;
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


/**
 * @author 李进刚
 * @date 2018年3月7日 下午2:37:27
 * @version 1.0 
 * @parameter 
 */
 
@Controller
@RequestMapping("/readimage")
public class ReadImageController {
  private static Logger log = Logger.getLogger(ReadImageController.class.getName());
  @Resource
  private ReadImageService readImageService;
  @Resource
  private ZoomManageService ZoomManageService;
  @Resource
  private ReadImage_ConfereeService ReadImage_ConfereeService;
  @Resource
  private MedicalRecordService medicalRecordService;
  @Resource
  private ReadImage_RecordService ReadImage_RecordService;
  @Resource
  private SysUserService sysuserService;
  private String ssid = "E6300170-E5B0-B072-E18D-74A870934D48";
  private String uid = "116";
  private String uname = "电子客服";
  private BigAntUtil bigAntUtil = new BigAntUtil(ssid, uid, uname);
  
  @Resource
  private MessageLogService messageLogService;
  
  /**
	 * 跳转到读片会视频界面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/joinReadImage.do",method=RequestMethod.GET)
	@ResponseBody
	public Object joinReadImage(@RequestParam String readimagekey,HttpServletRequest request,Model model){
		Map<String,Object> map = new HashMap<String,Object>();
		
		HttpSession session = request.getSession();
		SysUser sysUser =  (SysUser) session.getAttribute("SysUser");
		String sysuserKey = sysUser.getSysuserKey();
	  	DbcontextHolder.setDbType("imagedb");
	  	ZoomManage zoom = ZoomManageService.getByReadimageKey(readimagekey);
	  	if(sysUser==null){
  	  		map.put("code", 1);
    	    map.put("msg", "参与读片会失败，请稍后重试或联系管理员");
    	    return map;
	  	}
	  	if(zoom!=null){
	  	  	map.put("code", 0);
	  	  	map.put("zoom", zoom);
	  	  	map.put("sysuserKey", sysuserKey);
	  	  	map.put("username", sysUser.getUserName());
	  	}else{
	  	    map.put("code", 1);
	  	    map.put("msg", "参与读片会失败，请稍后重试或联系管理员");
	  	}
	  	
		return map;
	}
  /**
	 * 跳转到新增读片会界面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/redirectAdd.do",method=RequestMethod.GET)
	public Object redirectAdd(HttpServletRequest request,Model model){
		return "readimage/add";
	}
	/**
	 * 跳转到上传病历界面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/redirectUpload.do",method=RequestMethod.GET)
	public Object redirectUpload(@RequestParam String readimagekey,HttpServletRequest request,Model model){
	  	model.addAttribute("readimagekey", readimagekey);
		return "readimage/upload_progress";
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
        String readimage_key = request.getParameter("readimagekey");
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
        // 判断该dcm图像是否上传过
        int m = medicalRecordService.getCountByStudy(dcm.getString(Tag.StudyInstanceUID));
        ReadImage_Record readImage_Record = new ReadImage_Record();
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

            medicalRecordService.save(medicalRecord);
            readImage_Record.setRecord_key(medicalRecordId);
        } else {
            MedicalRecord r = medicalRecordService.getById(dcm.getString(Tag.StudyInstanceUID));
            readImage_Record.setRecord_key(r.getMedicalRecordKey());
        }

        readImage_Record.setReadimage_key(readimage_key);
        readImage_Record.setReadimage_record_key(UUid.getUUID());

        readImage_Record.setAppend_time(new Date());
        readImage_Record.setSysuser_key(sysUser.getSysuserKey());

        try {
            HashMap<String, String> getParamMap = new HashMap<String, String>();
            getParamMap.put("readimage_key", readimage_key);
            getParamMap.put("stuuid", dcm.getString(Tag.StudyInstanceUID));
            int i = ReadImage_RecordService.getByStuuid(getParamMap);
            System.out.println(i);
            if (i == 0) {
                int readImageRecordCount = ReadImage_RecordService.save(readImage_Record);
                if (readImageRecordCount > 0) {
                    map.put("code", 0);
                } else {
                    map.put("code", 1);
                }
            } else {
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
//	@RequestMapping(value="/uploaddcm",method=RequestMethod.POST)
//	@ResponseBody
//	@Transactional
//	public Object uploaddcm(HttpServletRequest request,HttpServletResponse response){
//	  	Map<String,Object> map = new HashMap<String,Object>();
//	  	DbcontextHolder.setDbType("imagedb");
//	  	HttpSession session = request.getSession();
//		SysUser sysUser =  (SysUser) session.getAttribute("SysUser");
//		String readimage_key = request.getParameter("readimagekey");
//	  	
//		//上传的dcm文件解析为dcm对象
//	  	DicomObject dcm = upload(request,response);
//	  	
//	  	//判断该dcm图像是否上传过
//	  	int m = medicalRecordService.getCountByStudy(dcm.getString(Tag.StudyInstanceUID));
//	  	
//	  	ReadImage_Record readImage_Record = new ReadImage_Record();
//	  	if(m==0){
//        	MedicalRecord medicalRecord = new MedicalRecord();
//        	medicalRecord.setAge(dcm.getString(Tag.PatientAge));// 患者年龄
//        	// medicalRecord.setBodypartCode(dcm.getString(Tag.));
//        	medicalRecord.setBodypartName(dcm.getString(Tag.BodyPartExamined));// 检查部位
//        	medicalRecord.setFilmNum(dcm.getString(Tag.NumberOfFilms));// 胶片数量
//        	// medicalRecord.setFilmTime(dcm.getString(Tag.));
//        	// medicalRecord.setHospital(dcm.getString(Tag.));
//        	// medicalRecord.setImageNum(dcm.getString(Tag.));
//        	String medicalRecordId = UUid.getUUID();
//        	medicalRecord.setMedicalRecordKey(medicalRecordId);
//        	medicalRecord.setModalityCode(dcm.getString(Tag.Modality));// 图像类型
//        	medicalRecord.setPatientHistory(dcm.getString(Tag.AdditionalPatientHistory));// 额外病史
//        	medicalRecord.setPatientKey(dcm.getString(Tag.PatientID));// 病人id
//        	medicalRecord.setPatientName(dcm.getString(Tag.PatientName));// 病人姓名
//  //    	medicalRecord.setPatientType(dcm.getString(Tag.PatientSupportType));// 病人类型
//  //    	medicalRecord.setPrintFlag(dcm.getString(Tag.));
//  //    	medicalRecord.setPrintReport(dcm.getString(Tag.));
//  //    	medicalRecord.setPrintTime(dcm.getString(Tag.));
//        	medicalRecord.setSeriesinstanceuid(dcm.getString(Tag.SeriesInstanceUID));// 序列id
//        	medicalRecord.setSeriesNum(dcm.getInt(Tag.SeriesNumber));// 几个序列
//        	medicalRecord.setSex(dcm.getString(Tag.PatientSex));// 病人性别
//  //    	medicalRecord.setState(dcm.getString(Tag.));
//        	medicalRecord.setStudyId(dcm.getString(Tag.StudyID));// 检查id
//        	medicalRecord.setStudyinstanceuid(dcm.getString(Tag.StudyInstanceUID));// 检查uid
//        	medicalRecord.setStudyTime(dcm.getString(Tag.StudyDate));// 检查日期
//        	
//        	medicalRecordService.save(medicalRecord);
//        	readImage_Record.setRecord_key(medicalRecordId);
//	  	}else{
//	  	  	MedicalRecord r = medicalRecordService.getById(dcm.getString(Tag.StudyInstanceUID));
//	  	  	readImage_Record.setRecord_key(r.getMedicalRecordKey());
//	  	}
//    	
//    	readImage_Record.setReadimage_key(readimage_key);
//    	readImage_Record.setReadimage_record_key(UUid.getUUID());
//    	
//    	readImage_Record.setAppend_time(new Date());
//    	readImage_Record.setSysuser_key(sysUser.getSysuserKey());
//	  		
//	  	try {
//  	  	  HashMap<String, String> getParamMap = new HashMap<String, String>();
//  	  	  getParamMap.put("readimage_key", readimage_key);
//  	  	  getParamMap.put("stuuid", dcm.getString(Tag.StudyInstanceUID));
//	  	  int i = ReadImage_RecordService.getByStuuid(getParamMap);
//	  	  System.out.println(i);
//	  	  if(i==0){
//    		  int readImageRecordCount = ReadImage_RecordService.save(readImage_Record);
//    		  if(readImageRecordCount>0){
//    			map.put("code", 0);
//    		  }else{
//    			map.put("code", 1);
//    		  }
//	  	  }else{
//	  		  map.put("code", 0);
//	  	  }
//		  
//		} catch (Exception e) {
//		  // TODO: handle exception
//		  e.printStackTrace();
//		}
//		return map;
//	}
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
//        		        String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "_" + new Random().nextInt(1000)  + "." + fileExtName;
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
	 * 获取与我有关的读片会日程信息
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/getReadImageDate.do",method=RequestMethod.POST)
	@ResponseBody
	public Object getReadImageDate(HttpServletRequest request,HttpServletResponse reponse,Model model){
		Map<String, Object> map = new HashMap<>();
		DbcontextHolder.setDbType("imagedb");
	  	//获取当前系统用户
		HttpSession session = request.getSession();
		SysUser sysUser =  (SysUser) session.getAttribute("SysUser");
		if(sysUser==null){
			map.put("code", 1);
			map.put("msg", "暂无数据！");
		}
		//1.查询当前用户创建的读片会
		ReadImage readImage = new ReadImage();
		readImage.setCreatePerson(sysUser.getSysuserKey());
		//2.查询当前用户被要请的读片会
		List<ReadImage> readImageList = readImageService.getByConferee(readImage);
		if(readImageList==null||readImageList.size()==0){
		  	map.put("code", 1);
			map.put("msg", "暂无数据！");
		}else{
			map.put("code", 0);
			map.put("readImageList", readImageList);
		}
		return map;
	}
  
  /**
     * 增加一条新读片会日程
     * @param 
     * @return
     */
  @RequestMapping(value="/save.do", method=RequestMethod.POST)
  @ResponseBody
  @Transactional(rollbackFor=Exception.class)
  public Object save(HttpServletRequest request, Model model){
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
	String state="1";
	String zoommsg = null;
	try {
	  String createurl = ZoomManageUtil.spliceCreate_URL(startTime, Integer.parseInt(yuyueshichang), member.size()+1, "读片会");
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

	
	
	
	//3.记录读片会信息
	String readimage_key = UUid.getUUID();
	ReadImage readImage = new ReadImage();
	readImage.setReadimageKey(readimage_key);
	readImage.setTitle(returnData.getString("title"));
	readImage.setState(state);
	readImage.setZoomKey(zoom_key);
	readImage.setCreatePerson(sysUser.getSysuserKey());
	readImage.setCreateTime(new Date());
	try {
	  readImage.setStartTime(dft.parse(startTime));
	} catch (ParseException e1) {
	  // TODO Auto-generated catch block
	  e1.printStackTrace();
	}
	readImage.setIsDel("0");
	//4.记录与会人员
	List<ReadImage_Conferee> conferees = new ArrayList<ReadImage_Conferee>();
	for(int i=0;i<member.size();i++){
	    String loginname = member.getString(i);
	    SysUser sys = sysuserService.login(loginname);
		if(sys==null){//系统中没有这个登录名的用户。即在大蚂蚁中存在，本系统中不存在
		}else{
		  	ReadImage_Conferee conferee = new ReadImage_Conferee();
			conferee.setReadimage_conferee_key(UUid.getUUID());
			conferee.setReadimage_key(readimage_key);
			conferee.setSysuser_key(sys.getSysuserKey());
			conferees.add(conferee);
		}
	}
	try {
	  int imageCount = readImageService.save(readImage);
	  int zoomCount = ZoomManageService.zoomcreate(zoomManage);
	  if(!conferees.isEmpty()){
		 ReadImage_ConfereeService.saveList(conferees);
	  }
	  
	  if (imageCount > 0 && zoomCount > 0 ) {
		//读片会创建成功，zoom信息保存成功，与人员保存成功
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
    		  	map.put("msg", "读片会创建成功。通讯录不匹配，有用户，未通知到，请联系管理员！");
    		}else if(s!=null){
    			
    		 	try {
    		 		url = "http://timer.91veo.com/v1/meeting/join?id="+jsonObject.getString("id")+"&value="+jsonObject.getString("value")+"&uname="+s.getUserName();
    		 		url=URLEncoder.encode(url,"UTF-8");
    		 		String desc = "【汇智精英】"+sysUser.getUserName()+"老师，预定"+startTime+"召开读片交流会，请您届时参加。";
    	    		bigAntUtil.send_url(bigant.getJSONObject("data").getString(user_login), url, "读片会通知", desc);
    	    		
    	    		ma.put("message", desc);
    	            ma.put("sendType", "读片会通知");
    	            ma.put("recipient", s.getLoginName());
    	            ma.put("receivePhone", s.getUserPhone());
    	    		
    	    		if(s.getUserPhone()!=null){
    	    			String sendResult = NoteUtil.sendSMSchange("您好，"+sysUser.getUserName()+"老师，预定"+startTime+"召开读片交流会，请您届时参加。【汇智精英】", s.getUserPhone());
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
		map.put("msg", "创建读片会失败！");
	  } 
	} catch (Exception e) {
	  // TODO: handle exception
	  e.printStackTrace();
	}
	return map;
  }
  
  /**
   * 根据主键修改一条读片会日程
   * @param 
   * @return
   */
  @RequestMapping(value="/updateById.do", method=RequestMethod.POST)
  @ResponseBody
  public Object updateById(HttpServletRequest request, Model model){
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
	String ReadimageKey = returnData.getString("ReadimageKey");
	
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
		String createurl = ZoomManageUtil.spliceCreate_URL(startTime, Integer.parseInt(yuyueshichang), member.size()+1, "读片会");
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
	
	//3.记录读片会信息
	ReadImage readImage = new ReadImage();
	readImage.setReadimageKey(ReadimageKey);
	try {
	  readImage.setStartTime(simpleDateFormat.parse(startTime));
	} catch (ParseException e1) {
	  // TODO Auto-generated catch block
	  e1.printStackTrace();
	}
	try {
	  readImage.setStartTime(dft.parse(startTime));
	} catch (ParseException e1) {
	  // TODO Auto-generated catch block
	  e1.printStackTrace();
	}

	readImage.setZoomKey(zoomKey);
	readImage.setModifyTime(new Date());
	
	//4.记录与会人员
	List<ReadImage_Conferee> conferees = new ArrayList<ReadImage_Conferee>();
	for(int i=0;i<member.size();i++){
	    String loginname = member.getString(i);
	    SysUser sys = sysuserService.login(loginname);
		
		if(sys==null){//系统中没有这个登录名的用户。即在大蚂蚁中存在，本系统中不存在
			
		}else{
		  	ReadImage_Conferee conferee = new ReadImage_Conferee();
			conferee.setReadimage_conferee_key(UUid.getUUID());
			conferee.setReadimage_key(ReadimageKey);
			conferee.setSysuser_key(sys.getSysuserKey());
			conferees.add(conferee);
		}
	}
	
	
	try {
	  int imageCount = readImageService.updateById(readImage);
	  int zoomCount = ZoomManageService.zoomupdate(zoomManage);
	  //先删除之前的与会人员根据readimageid
	  ReadImage_Conferee readImage_Conferee = new ReadImage_Conferee();
	  readImage_Conferee.setReadimage_key(ReadimageKey);
	  ReadImage_ConfereeService.deleteById(readImage_Conferee);
	  //记录与会人员
	  if(!conferees.isEmpty()){
		ReadImage_ConfereeService.saveList(conferees);
	  }
	  //读片会创建成功，zoom信息保存成功，与人员保存成功
	  // 通过汇沟通发送消息
	  if (imageCount > 0 && zoomCount > 0 ) {
			//读片会创建成功，zoom信息保存成功，与人员保存成功
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
	    		  	map.put("msg", "读片会创建成功。通讯录不匹配，有用户，未通知到，请联系管理员！");
	    		}else if(s!=null){
	    			
	    		 	try {
	    		 		url = "http://timer.91veo.com/v1/meeting/join?id="+jsonObject.getString("id")+"&value="+jsonObject.getString("value")+"&uname="+s.getUserName();
	    		 		url=URLEncoder.encode(url,"UTF-8");
	    		 		String desc = "【汇智精英】"+sysUser.getUserName()+"老师，预定"+startTime+"召开读片交流会，请您届时参加。";
	    	    		
	    		 		 ma.put("message", desc);
	    		 		 ma.put("sendType", "读片会通知");
	    	             ma.put("recipient", s.getLoginName());
	    	             ma.put("receivePhone", s.getUserPhone());
	    		 		
	    		 		bigAntUtil.send_url(bigant.getJSONObject("data").getString(user_login), url, "读片会通知", desc);
	    	    		if(s.getUserPhone()!=null){
	    	    			 String sendResult = NoteUtil.sendSMSchange("您好，"+sysUser.getUserName()+"老师，预定"+startTime+"召开读片交流会，请您届时参加。【汇智精英】", s.getUserPhone());
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
		map.put("msg", "修改读片会失败！");
	  } 
	} catch (Exception e) {
	  // TODO: handle exception
	  e.printStackTrace();
	}
	return map;
  }
  
  /**
   * 根据主键删除一条读片会日程
   * @param 
   * @return
   */
  @RequestMapping(value="/delByReadimagekey.do", method=RequestMethod.GET)
  @ResponseBody
  @Transactional
  public Object delByReadimagekey(@RequestParam String readimagekey,HttpServletRequest request, Model model){
	log.info("deleteById");
	Map<String,Object> map = new HashMap<String,Object>();
	
	DbcontextHolder.setDbType("imagedb");
	ReadImage readImage = new ReadImage();
	readImage.setReadimageKey(readimagekey);
	
	
	ReadImage delReadImage = readImageService.getById(readImage);
	delReadImage.setIsDel("1");
	delReadImage.setDelTime(new Date());
	
	ZoomManageWithBLOBs delZoom = ZoomManageService.getById(delReadImage.getZoomKey());
	String delurl = ZoomManageUtil.spliceDelete_URL(delZoom.getId(), delZoom.getValue());
	ZoomManageUtil.send_URL(delurl);
	
	ReadImage_Conferee readImage_Conferee = new ReadImage_Conferee();
	readImage_Conferee.setReadimage_key(readimagekey);
	
	try {
	  int readImageCount = readImageService.updateById(delReadImage);
	  int zoomCount = ZoomManageService.delById(delReadImage.getZoomKey());
	  ReadImage_RecordService.deleteByReadimageKey(readimagekey);
	  ReadImage_ConfereeService.deleteById(readImage_Conferee);
	  if (readImageCount > 0 && zoomCount > 0) {
		map.put("code", 0);
	  } else {
		map.put("code", 1);
		map.put("msg", "删除读片会失败！");
	  } 
	} catch (Exception e) {
	  // TODO: handle exception
	  	map.put("code", 1);
	  	map.put("msg", "删除读片会失败！");
	  	e.printStackTrace();
	    return map;
	}
	return map;
  }
  
  /**
   * 根据主键查询一条读片会日程
   * @param 
   * @return
   */
  @RequestMapping(value="/getById.do", method=RequestMethod.GET)
  public Object getById(@RequestParam String id, HttpServletRequest request, Model model) throws ParseException {
	log.info("getById");
	DbcontextHolder.setDbType("imagedb");
	//根据读片会id获取读片会内容
	ReadImage readImage = new ReadImage();
	readImage.setReadimageKey(id);
	ReadImage readImageResult = readImageService.getById(readImage);
	
	//根据读片会id获取zoom信息
	ZoomManage zoomManage = ZoomManageService.getByReadimageKey(id);
	
	//根据读片会id获取读片会人员
	ReadImage_Conferee readImage_Conferee = new ReadImage_Conferee();
	readImage_Conferee.setReadimage_key(id);
	List<SysUser> sysUsers = ReadImage_ConfereeService.getByReadimageKey(readImage_Conferee);
	String usernames = "";
	String login = "";
	for(SysUser s:sysUsers){
	  usernames +=s.getUserName()+"," ;
	  login +=s.getLoginName()+",";
	}
	SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String start_time = dft.format(readImageResult.getStartTime());
	model.addAttribute("yuyueshichang", zoomManage.getMinute());
	model.addAttribute("start_time", start_time);
	model.addAttribute("yuhuirenyuan", usernames);
	model.addAttribute("ReadimageKey", id);
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
	return "readimage/edit";
  }
  
  /**
   * 根据创建人查询读片会日程列表
   * @param 
   * @return
   */
  @RequestMapping(value="/getByCreater.do", method=RequestMethod.GET)
  @ResponseBody
  public Object getByCreater(@RequestParam String json, HttpServletRequest request, Model model) throws ParseException {
	log.info("getByCreater");
	// http://localhost:8080/InteallMEDHTML5/readimage/getByCreater?json='create_person':'createpersoncreateperson00000001'
	Map<String, String> getParamMap = new Gson().fromJson("{" + json + "}", new TypeToken<HashMap<String, String>>(){}.getType());
	
	ReadImage readImage = new ReadImage();
	readImage.setCreatePerson(getParamMap.get("create_person"));
	readImage.setIsDel("0");
	
	List<ReadImage> readImageList = readImageService.getByCreater(readImage);
	
	Map<String,Object> map = new HashMap<String,Object>();
	map.put("查询读片会日程信息", readImageList.size());
	map.put("查询读片会日程信息", readImageList);
	return map;
  }
  
  /**
   * 根据与会人查询读片会日程列表
   * @param 
   * @return
   */
  @RequestMapping(value="/getByConferee.do", method=RequestMethod.GET)
  @ResponseBody
  public Object getByConferee(@RequestParam String json, HttpServletRequest request, Model model) throws ParseException {
	log.info("getByConferee");
	// http://localhost:8080/InteallMEDHTML5/readimage/getByConferee?json='create_person':'createpersoncreateperson00000001'
	Map<String, String> getParamMap = new Gson().fromJson("{" + json + "}", new TypeToken<HashMap<String, String>>(){}.getType());
	
	ReadImage readImage = new ReadImage();
	readImage.setCreatePerson(getParamMap.get("create_person"));
	
	List<ReadImage> readImageList = readImageService.getByConferee(readImage);
	
	Map<String,Object> map = new HashMap<String,Object>();
	map.put("查询与会人的读片会日程信息", readImageList.size());
	map.put("查询与会人的读片会日程信息", readImageList);
	return map;
  }
}
