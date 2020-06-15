package com.inteall.image.controller;

import java.io.File;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import com.inteall.image.pojo.Collection;
import com.inteall.image.pojo.CollectionMedicalRecord;
import com.inteall.image.pojo.MedicalRecord;
import com.inteall.image.pojo.ReportWithBLOBs;
import com.inteall.image.pojo.SysUser;
import com.inteall.image.service.CollectionMedicalRecordService;
import com.inteall.image.service.CollectionService;
import com.inteall.image.service.MedicalRecordService;
import com.inteall.image.service.ReportService;
import com.inteall.image.util.CustomMultipartResolver;
import com.inteall.image.util.DbcontextHolder;
import com.inteall.image.util.UUid;
import com.inteall.image.util.Util;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/collection")
public class CollectionController {
	private Logger logger =Logger.getLogger(CollectionController.class);
	@Resource
	private CollectionService colletionService;
	@Resource
	private CollectionMedicalRecordService collectionMedicalRecordService;
	@Resource
	private MedicalRecordService medicalRecordService;
	@Resource
	private ReportService reportService;
	
	/**
	 * 跳转到新增收藏夹界面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/insertParentCollectionAdd.do",method=RequestMethod.GET)
	public Object insertParentCollectionAdd(@RequestParam String parentkey ,HttpServletRequest request,Model model){
		DbcontextHolder.setDbType("imagedb");
		Collection collectionName=colletionService.getCollectionName(parentkey);
		if(collectionName==null){
			String parentCollectionName="无";
			model.addAttribute("collectionName", parentCollectionName);
		}else{
			model.addAttribute("collectionName", collectionName.getCollection_name());
		}
		model.addAttribute("parentkey", parentkey);
		return "shoucangjia/add";
	}
	/**
	 * 跳转到上传病历界面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/collectionUpload.do",method=RequestMethod.GET)
	public Object collectionUpload(@RequestParam String collectionKey,HttpServletRequest request,Model model){
	  	model.addAttribute("collectionKey", collectionKey);
		return "shoucangjia/upload_progress";
	}
	/**
	 * 收藏夹详情跳转到上传附件界面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/collectionXqUpload.do",method=RequestMethod.GET)
	public Object collectionXqUpload(@RequestParam String stuuid,HttpServletRequest request,Model model){
		model.addAttribute("stuuid", stuuid);
		return "shoucangjia/xiangqing/upload_progress";
	}
	/**
	 * 插入父节点
	 * 创建时间：2018年2月9日-下午3:18:00
	 */
	@RequestMapping(value="/insertParentCollection.do",method=RequestMethod.POST)
	@ResponseBody
	public Object insertParentCollection(HttpServletRequest request,Model model){
		DbcontextHolder.setDbType("imagedb");
		Map<String, Object> map = new HashMap<String,Object>();
		//获取当前系统用户
		HttpSession session = request.getSession();
		SysUser sysUser =  (SysUser) session.getAttribute("SysUser");
		String json = request.getParameter("json");
		JSONObject returnData = JSONObject.fromObject(json);
		Collection collection = new Collection();
		collection.setCollection_key(UUid.getUUID());
		collection.setCollection_name(returnData.getString("collectionName"));
		collection.setParent_key(returnData.getString("parent_key"));
		collection.setIsParent("true");
		collection.setCreate_person(sysUser.getSysuserKey());
		collection.setOrder_person(sysUser.getSysuserKey());
		collection.setCreate_time(new Date());
		collection.setModify_time(new Date());
		collection.setDel_time(new Date());
		collection.setIs_del("0");
		int row = colletionService.insertCollection(collection);
		map.put("行数", row);
		map.put("保存信息", collection);
		return map;
	}
	/**
	 * 跳转编辑页面
	 * @param id
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/getCollectionById.do",method=RequestMethod.GET)
	public Object getById(@RequestParam String id ,HttpServletRequest request,Model model){
		logger.info("getById");
		DbcontextHolder.setDbType("imagedb");
		Collection collection = colletionService.getById(id);
		model.addAttribute("collection", collection);
		return "shoucangjia/edit";
	}
	/**
	 * 查询所有条数
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="getAll.do",method=RequestMethod.GET)
	@ResponseBody
	public Object getAll(HttpServletRequest request,Model model){
		logger.info("getAll");
		DbcontextHolder.setDbType("imagedb");
		HttpSession session = request.getSession();
		SysUser sysUser =  (SysUser) session.getAttribute("SysUser");
		Map<String, Object> map = new HashMap<String,Object>();
		String createId=sysUser.getSysuserKey();
		List<Collection> list = colletionService.getAll(createId);
		map.put("条数", list.size());
		map.put("collectionList", list);
		return map;
	}
	/**
	 * 修改信息
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/updateParentCollection.do",method=RequestMethod.POST)
	@ResponseBody
	public Object update(HttpServletRequest request,Model model){
		logger.info("updateParentCollection");
		DbcontextHolder.setDbType("imagedb");
		Map<String,Object> map = new HashMap<String,Object>();
		//获取当前系统用户
		HttpSession session = request.getSession();
		SysUser sysUser =  (SysUser) session.getAttribute("SysUser");
		String json = request.getParameter("json");
		JSONObject returnData = JSONObject.fromObject(json);
		Collection collection = new Collection();
		collection.setCollection_key(returnData.getString("collectionKey"));
		collection.setCollection_name(returnData.getString("collectionName"));
		collection.setModify_time(new Date());
		collection.setModify_person(sysUser.getSysuserKey());
		int row = colletionService.update(collection);
		map.put("修改的行数", row);
		map.put("修改的患者信息", collection);
		return map;
	}
	/**
	 * 删除信息
	 * @param collectionKey
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/delCollection.do",method=RequestMethod.POST)
	@ResponseBody
	public Object delCollection(@RequestParam String collectionKey,HttpServletRequest request,Model model){
		logger.info("delCollection");
		DbcontextHolder.setDbType("imagedb");
		Map<String,Object> map = new HashMap<String,Object>();
		//获取当前系统用户
		HttpSession session = request.getSession();
		SysUser sysUser =  (SysUser) session.getAttribute("SysUser");
		Collection collection = new Collection();
		collection.setCollection_key(collectionKey);
		collection.setDel_person(sysUser.getSysuserKey());
		collection.setIs_del("1");
		collection.setDel_time(new Date());
		int row = colletionService.delCollection(collection);
		map.put("删除了的行数：", row);
		return map;
	}
	/**
	 * 查询父节点
	 * @param collectionKey
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/getParentKeyCollection.do",method=RequestMethod.POST)
	@ResponseBody
	public Object getParentKeyCollection(@RequestParam String collectionKey,HttpServletRequest request,Model model){
		logger.info("getParentKeyCollection");
		DbcontextHolder.setDbType("imagedb");
		List<Collection> list =colletionService.getParentKeyCollection(collectionKey);
		return list;
	}
	@RequestMapping(value = "/getProgress.do",method=RequestMethod.GET)    
    @ResponseBody    
    public Object initCreateInfo(HttpServletRequest request) {   
        Map<String, Object> map = new HashMap<String, Object>();
        CustomMultipartResolver query = new CustomMultipartResolver();  
        
        map.put("progress", query.getProgress("progress"));
        return map;    
    }   
	/**
	 * 上传dcm
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/uploaddcm.do",method=RequestMethod.POST)
	@ResponseBody
	public Object uploaddcm(HttpServletResponse response,MultipartHttpServletRequest request){
	  	Map<String,Object> map = new HashMap<String,Object>();
	  	DbcontextHolder.setDbType("imagedb");
	  	HttpSession session = request.getSession();
		SysUser sysUser =  (SysUser) session.getAttribute("SysUser");
		String collection_key = request.getParameter("collectionKey");
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
	  	
	  	CollectionMedicalRecord collectionMedicalRecord = new CollectionMedicalRecord();
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
        	collectionMedicalRecord.setMedical_record_key(medicalRecordId);
	  	}else{
	  	  	MedicalRecord r = medicalRecordService.getById(dcm.getString(Tag.StudyInstanceUID));
	  	    collectionMedicalRecord.setMedical_record_key(r.getMedicalRecordKey());
	  	}
    	
	  	collectionMedicalRecord.setCollection_key(collection_key);
	  	collectionMedicalRecord.setCollection_medical_record_key(UUid.getUUID());
    	
	  	collectionMedicalRecord.setCreate_time(new Date());
	  	collectionMedicalRecord.setModify_time(new Date());
	  	collectionMedicalRecord.setModify_time(new Date());
	  	collectionMedicalRecord.setCreate_person(sysUser.getLoginName());
	  	collectionMedicalRecord.setIs_del("0");
	  	try {
  	  	  HashMap<String, String> getParamMap = new HashMap<String, String>();
  	  	  getParamMap.put("collection_key", collection_key);
  	  	  getParamMap.put("stuuid", dcm.getString(Tag.StudyInstanceUID));
	  	  int i = collectionMedicalRecordService.getByStuuid(getParamMap);
	  	  System.out.println(i);
	  	  if(i==0&&dcm.getString(Tag.StudyInstanceUID)!=null){
    		  int readImageRecordCount = collectionMedicalRecordService.insertCollectionMedicalRecord(collectionMedicalRecord);
    		  if(readImageRecordCount>0){
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
    @RequestMapping(value="/getCollectionKey.do",method=RequestMethod.POST)
	@ResponseBody
    public Object getCollectionKey(HttpServletRequest request,HttpServletResponse response){
    	DbcontextHolder.setDbType("imagedb");
    	String collection_key = request.getParameter("collection_key");
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
		queryMap.put("collection_key", collection_key);
		
    	//先查询满足条件的数据有多少条
    	int count = collectionMedicalRecordService.getCount(queryMap);
    	queryMap.put("curr", (page-1)*limit);
		queryMap.put("limit",limit);
    	List<CollectionMedicalRecord> Collection_RecordList = collectionMedicalRecordService.getByCollectionKey(queryMap);
    	Map<String,Object> map = new HashMap<String,Object>();
    	if(Collection_RecordList!=null&&Collection_RecordList.size()>0){
    		map.put("code", 0);
    		map.put("msg", "");
    		map.put("count", count);
    		map.put("data", Collection_RecordList);
    	}else{
    		map.put("code", 1);
    		map.put("msg", "<img style='width:228px;height:228px' src='img/noneicon.png'>");
    	}
		return map;
    }
    /**
	 * 收藏夹详情：根据用户的id查询用户信息
	 * @param id
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value="/getCollectionXqById.do",method=RequestMethod.GET)
	public Object getCollectionXqById(@RequestParam String stuuid,HttpServletRequest request,Model model) throws ParseException{
		DbcontextHolder.setDbType("imagedb");
		MedicalRecord medicalRecord = medicalRecordService.getById(stuuid);
		model.addAttribute("stuuid", stuuid);
		model.addAttribute("medicalRecord", medicalRecord);
		return "shoucangjia/xiangqing/index";
	}
	/**
	   * 根据stuyid删除收藏夹中的病历
	   * 删的是关联表，不是病历表
	   * @param 
	   * @return
	   */
	  @RequestMapping(value="/delByStuuid.do", method=RequestMethod.GET)
	  @ResponseBody
	  public Object delByStuuid(@RequestParam String stuuid,@RequestParam String collectionKey, HttpServletRequest request, Model model) throws ParseException {
		logger.info("delByStuuid");
		HashMap<String, String> getParamMap = new HashMap<String, String>();
		DbcontextHolder.setDbType("imagedb");
		getParamMap.put("stuuid", stuuid);
		getParamMap.put("collection_key", collectionKey);
		int row = collectionMedicalRecordService.delByStuuid(getParamMap);
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
	     * 跳转到报告编辑页面
	     * @param collectionKey
	     * @param stuuid
	     * @param reportId
	     * @param request
	     * @param model
	     * @return
	     */
		@RequestMapping(value="/collectionReportEdit.do",method=RequestMethod.GET)
		public Object collectionReportEdit(@RequestParam String medicalRecordKey,@RequestParam String stuuid,@RequestParam String modalityCode,@RequestParam String reportId,@RequestParam String collectionKey,HttpServletRequest request,Model model){
			logger.info("collectionReportEdit");
		  	DbcontextHolder.setDbType("imagedb");
		  	CollectionMedicalRecord collectionMedicalRecord = collectionMedicalRecordService.getcollectionMedicalRecordById(medicalRecordKey,collectionKey);
		  	MedicalRecord medicalRecord = medicalRecordService.getById(stuuid);
		  	if(reportId!=null&&!reportId.equals("")){
		  	    ReportWithBLOBs reportWithBLOBs = reportService.getById(reportId);
		  	    model.addAttribute("reportWithBLOBs", reportWithBLOBs);
		  	}
		  	model.addAttribute("collectionMedicalRecord", collectionMedicalRecord);
		  	model.addAttribute("collectionKey", collectionKey);
		  	model.addAttribute("medicalRecord", medicalRecord);
		  	model.addAttribute("modalityCode", modalityCode);
			return "shoucangjia/baogao/edit";
		}
		/**
		 * 新增报告信息
		 * @param request
		 * @param model
		 * @return
		 */
		@RequestMapping(value="/collectionReportSave.do",method=RequestMethod.POST)
		@ResponseBody
		@Transactional
		public Object collectionReportSave(HttpServletRequest request,Model model){
			logger.info("collectionReportSave");
			String jianchamingcheng = request.getParameter("jianchamingcheng");
			String jianchafangfa = request.getParameter("jianchafangfa");
			String jianchasuojian = request.getParameter("jianchasuojian");
			String zhenduanjieguo = request.getParameter("zhenduanjieguo");
			String sikaofenxi = request.getParameter("sikaofenxi");
	        String jianchajianyi = request.getParameter("jianchajianyi");
	        String reportId = request.getParameter("reportId");
	        String collection_key = request.getParameter("collection_key");
	        HttpSession session = request.getSession();
	        SysUser sysUser =  (SysUser) session.getAttribute("SysUser");
	        DbcontextHolder.setDbType("imagedb");
			Map<String,Object> map = new HashMap<String,Object>();
			ReportWithBLOBs report = new ReportWithBLOBs();
			report.setCheckMethod(jianchafangfa);
			report.setCheckName(jianchamingcheng);
			report.setCheckView(jianchasuojian);
			report.setDiagnosisResult(zhenduanjieguo);
			report.setThink(sikaofenxi);
			report.setAdvise(jianchajianyi);
			report.setEditUser(sysUser.getSysuserKey());
			report.setIsDel("0");
			CollectionMedicalRecord collectionMedicalRecord = new CollectionMedicalRecord();
			collectionMedicalRecord.setCollection_key(collection_key);
			try {
			    int reportrow;
			    if(reportId.equals("null")||reportId.equals("")){
			        report.setCreatePerson(sysUser.getSysuserKey());
			        report.setCreatetime(new Date());
			        reportId = UUid.getUUID();
			        report.setReportKey(reportId);
			        collectionMedicalRecord.setReport_key(reportId);
			        reportrow = reportService.save(report);
			    }else {
			        report.setModifyPerson(sysUser.getSysuserKey());
			        report.setModifyTime(new Date());
			        report.setReportKey(reportId);
			        collectionMedicalRecord.setReport_key(reportId);
			        reportrow = reportService.updateById(report);
			    }
			    int conrow = collectionMedicalRecordService.updateBycollectionKey(collectionMedicalRecord);
	            if(reportrow>0&&conrow>0){
	                map.put("code", 0);
	                map.put("reportId", reportId);
	            }else{
	                map.put("code", 1);
	                map.put("msg", "报告保存失败");
	            }
	        } catch (Exception e) {
	            map.put("code", 1);
	            map.put("msg", "报告保存失败");
	            // TODO: handle exception
	            e.printStackTrace();
	        }
	        return map;
		}
}
