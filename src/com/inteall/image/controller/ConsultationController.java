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

import com.ibm.wsdl.util.StringUtils;
import com.inteall.image.pojo.Accessory;
import com.inteall.image.pojo.Consultation;
import com.inteall.image.pojo.ConsultationConferee;
import com.inteall.image.pojo.ConsultationWithBLOBs;
import com.inteall.image.pojo.MedicalLog;
import com.inteall.image.pojo.MedicalRecord;
import com.inteall.image.pojo.Modality;
import com.inteall.image.pojo.SysUser;
import com.inteall.image.pojo.ZoomManage;
import com.inteall.image.pojo.ZoomManageWithBLOBs;
import com.inteall.image.service.AccessoryService;
import com.inteall.image.service.BigAntService;
import com.inteall.image.service.ConsultationConfereeService;
import com.inteall.image.service.ConsultationService;
import com.inteall.image.service.MedicalLogService;
import com.inteall.image.service.MedicalRecordService;
import com.inteall.image.service.MessageLogService;
import com.inteall.image.service.ModalityService;
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
import sun.misc.BASE64Decoder;

/**
 * @author 韩明君
 * @date 2018年2月23日 下午4:58:28
 * @version 1.0 
 * @parameter 
 */

@Controller
@RequestMapping("/consultation")
public class ConsultationController {
  private static Logger log = Logger.getLogger(ConsultationController.class.getName());
  @Resource
  private ConsultationService consulTationService;
  @Resource
  private MedicalRecordService medicalRecordService;
  @Resource
  private SysUserService sysuserService;
  @Resource
  private MedicalLogService medicalLogService;
  @Resource
  private ZoomManageService ZoomManageService;
  @Resource
  private ConsultationConfereeService ConsultationConfereeService;
  @Resource
  private ModalityService modalityService;
  @Resource
  private BigAntService bigAntService;
  private String ssid = "E6300170-E5B0-B072-E18D-74A870934D48";
  private String uid = "116";
  private String uname = "电子客服";
  private BigAntUtil bigAntUtil = new BigAntUtil(ssid, uid, uname);
  
  @Resource
  private MessageLogService messageLogService;
  @Resource
  private AccessoryService accessoryService;
  
  /**
   * 终审报告
   * @param request
   * @param model
   * @return
   */
  @RequestMapping(value="/ultimateAudit.do",method=RequestMethod.POST)
  @ResponseBody
  public Object ultimateAudit(HttpServletRequest request,Model model){
      Map<String,Object> map = new HashMap<String,Object>();
      Map<String,Object> ma = new HashMap<String, Object>();//存放短信日志字段
      DbcontextHolder.setDbType("imagedb");
      HttpSession session = request.getSession();
      SysUser sysUser =  (SysUser) session.getAttribute("SysUser");
      String caseId = request.getParameter("caseId");
      String rejectReason = request.getParameter("rejectReason");
      String ispass = request.getParameter("ispass");
      String stuuid = request.getParameter("stuuid");
      
      ConsultationWithBLOBs consultation = new ConsultationWithBLOBs();
      if(ispass.equals("true")){
          consultation.setState("8");
          consultation.setUltimateSuggestion("1");
          consultation.setUltimateTime(new Date());
          MedicalLog medicalLog = new MedicalLog();
	      	medicalLog.setSyslogKey(UUid.getUUID());
	      	medicalLog.setLogtime(new Date());
	      	medicalLog.setLogtype("报告终审完成");
	      	medicalLog.setLoguser(sysUser.getUserName());
	      	medicalLog.setLoguserCode(sysUser.getLoginName());
	      	medicalLog.setCreatePerson(sysUser.getSysuserKey());
	      	medicalLog.setCreateTime(new Date());
	      	medicalLog.setStudyId(stuuid);
	      	medicalLog.setIsDel("0");
	      	int n=medicalLogService.save(medicalLog);
	      	if(n > 0){
	      		map.put("code", 0);
	      	}else{
	      		map.put("code", 1);
	            map.put("msg", "插入日志出错，请稍后重试或者联系管理员！");
	      	}
      }else{
    	  MedicalLog medicalLog = new MedicalLog();
	      	medicalLog.setSyslogKey(UUid.getUUID());
	      	medicalLog.setLogtime(new Date());
	      	medicalLog.setLogtype("报告终审驳回");
	      	medicalLog.setLoguser(sysUser.getUserName());
	      	medicalLog.setLoguserCode(sysUser.getLoginName());
	      	medicalLog.setCreatePerson(sysUser.getSysuserKey());
	      	medicalLog.setCreateTime(new Date());
	      	medicalLog.setStudyId(stuuid);
	      	medicalLog.setIsDel("0");
	      	int n=medicalLogService.save(medicalLog);
	      	if(n > 0){
	      		map.put("code", 0);
	      	}else{
	      		map.put("code", 1);
	            map.put("msg", "插入日志出错，请稍后重试或者联系管理员！");
	      	}
          consultation.setState("5");
          consultation.setUltimateSuggestion("0");
          consultation.setUltimateRejectTime(new Date());
      }
      
      consultation.setUltimateReject(rejectReason);
      consultation.setCaseId(caseId);
      int count = consulTationService.updateByCaseId(consultation);
      ConsultationWithBLOBs con = consulTationService.getConsulTationById(caseId);
      
      if(count>0){
          map.put("code", 0);
          SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          if(ispass.equals("true")){
              
              String content = "【汇智精英】您"+dft.format(con.getCommitTime())+"上传的病历,于"+dft.format(new Date())+"报告审核通过，会诊结束，请查看报告";
              SysUser userinfo = sysuserService.getById(con.getCreatePerson());
              bigAntUtil.send_user(userinfo.getLoginName(), "会诊通知", content);
              
              ma.put("message", content);
              ma.put("sendType", "报告审核");
              ma.put("recipient", userinfo.getLoginName());
              ma.put("receivePhone", userinfo.getUserPhone());
              
              if(userinfo.getUserPhone()!=null){
	    			try {
						String sendResult = NoteUtil.sendSMSchange("您"+dft.format(con.getCommitTime())+"上传的病历,于"+dft.format(new Date())+"报告审核通过，会诊结束，请查看报告。【汇智精英】", userinfo.getUserPhone());
						 ma.put("sendState", sendResult);
	    			} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    		}
          }else{
              String content = "【汇智精英】您"+dft.format(con.getReportCommitTime())+"提交审核的报告，审核未通过，请查看！";
              bigAntUtil.send_user(con.getAcceptPersonLogin(), "会诊通知", content);
              SysUser userinfo = sysuserService.getById(con.getAcceptPersonLogin());
              
              ma.put("message", content);
              ma.put("sendType", "报告审核");
              ma.put("recipient", userinfo.getLoginName());
              ma.put("receivePhone", userinfo.getUserPhone());
              
              if(userinfo.getUserPhone()!=null){
	    			try {
						String sendResult =  NoteUtil.sendSMSchange("您"+dft.format(con.getReportCommitTime())+"提交审核的报告，审核未通过，请查看！【汇智精英】", userinfo.getUserPhone());
						 ma.put("sendState", sendResult);
	    			} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    		}
          }
      }else{
          map.put("code", 1);
          map.put("msg", "驳回失败，请稍后重试或联系管理员！");
      }
      
      int row = messageLogService.addMessageInfo(ma);
      return map;
  }
  /**
   * 初审报告
   * @param request
   * @param model
   * @return
   */
  @RequestMapping(value="/primaryAudit.do",method=RequestMethod.POST)
  @ResponseBody
  public Object primaryAudit(HttpServletRequest request,Model model){
      Map<String,Object> map = new HashMap<String,Object>();
      DbcontextHolder.setDbType("imagedb");
      HttpSession session = request.getSession();
      SysUser sysUser =  (SysUser) session.getAttribute("SysUser");
      
      String caseId = request.getParameter("caseId");
      String rejectReason = request.getParameter("rejectReason");
      String ispass = request.getParameter("ispass");
      String stuuid = request.getParameter("stuuid");
      String fushenren = request.getParameter("fushenren");
      
      ConsultationWithBLOBs consultation = new ConsultationWithBLOBs();
      if(fushenren==""||fushenren==null){
    	  if(ispass.equals("true")){
	    	  consultation.setState("8");
	          consultation.setPrimarySuggestion("1");
	          consultation.setPrimaryTime(new Date());
	          MedicalLog medicalLog = new MedicalLog();
		      	medicalLog.setSyslogKey(UUid.getUUID());
		      	medicalLog.setLogtime(new Date());
		      	medicalLog.setLogtype("报告初审完成");
		      	medicalLog.setLoguser(sysUser.getUserName());
		      	medicalLog.setLoguserCode(sysUser.getLoginName());
		      	medicalLog.setCreatePerson(sysUser.getSysuserKey());
		      	medicalLog.setCreateTime(new Date());
		      	medicalLog.setStudyId(stuuid);
		      	medicalLog.setIsDel("0");
		      	int n=medicalLogService.save(medicalLog);
		      	if(n > 0){
		      		map.put("code", 0);
		      	}else{
		      		map.put("code", 1);
		              map.put("msg", "插入日志出错，请稍后重试或者联系管理员！");
		      	}
    	  }else{
              consultation.setState("4");
              consultation.setPrimarySuggestion("0");
              consultation.setPrimaryRejectTime(new Date());
              MedicalLog medicalLog = new MedicalLog();
    	      	medicalLog.setSyslogKey(UUid.getUUID());
    	      	medicalLog.setLogtime(new Date());
    	      	medicalLog.setLogtype("报告初审驳回");
    	      	medicalLog.setLoguser(sysUser.getUserName());
    	      	medicalLog.setLoguserCode(sysUser.getLoginName());
    	      	medicalLog.setCreatePerson(sysUser.getSysuserKey());
    	      	medicalLog.setCreateTime(new Date());
    	      	medicalLog.setStudyId(stuuid);
    	      	medicalLog.setIsDel("0");
    	      	int n=medicalLogService.save(medicalLog);
    	      	if(n > 0){
    	      		map.put("code", 0);
    	      	}else{
    	      		map.put("code", 1);
    	              map.put("msg", "插入日志出错，请稍后重试或者联系管理员！");
    	      	}
          }
    	  consultation.setPrimaryReject(rejectReason);
	        consultation.setCaseId(caseId);
	        int count = consulTationService.updateByCaseId(consultation);
	        ConsultationWithBLOBs con = consulTationService.getConsulTationById(caseId);
	        Map<String,Object> ma = new HashMap<String, Object>(); 
	        if(count>0){
	            map.put("code", 0);
	            SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	            if(ispass.equals("true")){
	                
	                String content = "【汇智精英】您"+dft.format(con.getCommitTime())+"上传的病历,于"+dft.format(new Date())+"报告审核通过，会诊结束，请查看报告";
	                SysUser userinfo = sysuserService.getById(con.getCreatePerson());
	                bigAntUtil.send_user(userinfo.getLoginName(), "会诊通知", content);
	                  ma.put("message", content);
	                  ma.put("sendType", "初审报告");
	                  ma.put("recipient", sysUser.getUserName());
	                  ma.put("receivePhone",userinfo.getUserPhone());
	                if(userinfo.getUserPhone()!=null){
	  	    			try {
	  	    				String sendResult =NoteUtil.sendSMSchange("您"+dft.format(con.getCommitTime())+"上传的病历,于"+dft.format(new Date())+"报告审核通过，会诊结束，请查看报告。【汇智精英】", userinfo.getUserPhone());
	  						ma.put("sendState", sendResult);
	  	    			} catch (UnsupportedEncodingException e) {
	  						// TODO Auto-generated catch block
	  						e.printStackTrace();
	  					}
	  	    		}
	            }else{
	                String content = "【汇智精英】您"+dft.format(con.getReportCommitTime())+"提交审核的报告，审核未通过，请查看！";
	                bigAntUtil.send_user(con.getAcceptPersonLogin(), "会诊通知", content);
	                SysUser userinfo = sysuserService.getById(con.getCreatePerson());
	                  ma.put("message", content);
	                  ma.put("sendType", "初审报告");
	                  ma.put("recipient", sysUser.getUserName());
	                  ma.put("receivePhone", userinfo.getUserPhone());
	                if(userinfo.getUserPhone()!=null){
	  	    			try {
	  	    				String sendResult =NoteUtil.sendSMSchange("您"+dft.format(con.getReportCommitTime())+"提交审核的报告，审核未通过，请查看！【汇智精英】", userinfo.getUserPhone());
	  	    				ma.put("sendState", sendResult);
	  	    			} catch (UnsupportedEncodingException e) {
	  						// TODO Auto-generated catch block
	  						e.printStackTrace();
	  					}
	  	    		}
	            }
	        }else{
	            map.put("code", 1);
	            map.put("msg", "驳回失败，请稍后重试或联系管理员！");
	        }
      }else{
    	  if(ispass.equals("true")){
              consultation.setState("7");
              consultation.setPrimarySuggestion("1");
              consultation.setPrimaryTime(new Date());
              MedicalLog medicalLog = new MedicalLog();
    	      	medicalLog.setSyslogKey(UUid.getUUID());
    	      	medicalLog.setLogtime(new Date());
    	      	medicalLog.setLogtype("报告初审完成");
    	      	medicalLog.setLoguser(sysUser.getUserName());
    	      	medicalLog.setLoguserCode(sysUser.getLoginName());
    	      	medicalLog.setCreatePerson(sysUser.getSysuserKey());
    	      	medicalLog.setCreateTime(new Date());
    	      	medicalLog.setStudyId(stuuid);
    	      	medicalLog.setIsDel("0");
    	      	int n=medicalLogService.save(medicalLog);
    	      	if(n > 0){
    	      		map.put("code", 0);
    	      	}else{
    	      		map.put("code", 1);
    	              map.put("msg", "插入日志出错，请稍后重试或者联系管理员！");
    	      	}
          }else{
              consultation.setState("4");
              consultation.setPrimarySuggestion("0");
              consultation.setPrimaryRejectTime(new Date());
              MedicalLog medicalLog = new MedicalLog();
    	      	medicalLog.setSyslogKey(UUid.getUUID());
    	      	medicalLog.setLogtime(new Date());
    	      	medicalLog.setLogtype("报告初审驳回");
    	      	medicalLog.setLoguser(sysUser.getUserName());
    	      	medicalLog.setLoguserCode(sysUser.getLoginName());
    	      	medicalLog.setCreatePerson(sysUser.getSysuserKey());
    	      	medicalLog.setCreateTime(new Date());
    	      	medicalLog.setStudyId(stuuid);
    	      	medicalLog.setIsDel("0");
    	      	int n=medicalLogService.save(medicalLog);
    	      	if(n > 0){
    	      		map.put("code", 0);
    	      	}else{
    	      		map.put("code", 1);
    	              map.put("msg", "插入日志出错，请稍后重试或者联系管理员！");
    	      	}
          }
    	  consultation.setPrimaryReject(rejectReason);
          consultation.setCaseId(caseId);
          int count = consulTationService.updateByCaseId(consultation);
          ConsultationWithBLOBs con = consulTationService.getConsulTationById(caseId);
          
          Map<String,Object> ma = new HashMap<String, Object>(); 
          
          if(count>0){
              map.put("code", 0);
              SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
              if(ispass.equals("true")){
                  
                  String content = "【汇智精英】"+sysUser.getUserName()+"于"+dft.format(con.getCommitTime())+"提交了一份会诊报告，请您审核！";
                  bigAntUtil.send_user(con.getUltimateAuditLogin(), "会诊通知", content);
                  SysUser s = sysuserService.getByLoginname(con.getUltimateAuditLogin());
                  
                  ma.put("message", content);
                  ma.put("sendType", "初审报告");
                  ma.put("recipient", sysUser.getUserName());
                  ma.put("receivePhone", s.getUserPhone());
                  
                  if(s.getUserPhone()!=null){
    	    			try {
    						String sendResult = NoteUtil.sendSMSchange(sysUser.getUserName()+"于"+dft.format(con.getCommitTime())+"提交了一份会诊报告，请您审核！【汇智精英】", s.getUserPhone());
    						 ma.put("sendState", sendResult);
    	    			} catch (UnsupportedEncodingException e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					}
    	    		}
              }else{
                  String content = "【汇智精英】您"+dft.format(con.getReportCommitTime())+"提交审核的报告，审核未通过，请查看！";
                  bigAntUtil.send_user(con.getAcceptPersonLogin(), "会诊通知", content);
                  SysUser s = sysuserService.getByLoginname(con.getAcceptPersonLogin());
                  
                  ma.put("message", content);
                  ma.put("sendType", "初审报告");
                  ma.put("recipient", sysUser.getUserName());
                  ma.put("receivePhone", s.getUserPhone());
                  
                  if(s.getUserPhone()!=null){
    	    			try {
    						String sendResult = NoteUtil.sendSMSchange("您"+dft.format(con.getReportCommitTime())+"提交审核的报告，审核未通过，请查看！【汇智精英】", s.getUserPhone());
    						ma.put("sendState", sendResult);
    	    			} catch (UnsupportedEncodingException e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					}
    	    		}
              }
          }else{
              map.put("code", 1);
              map.put("msg", "驳回失败，请稍后重试或联系管理员！");
          } 
          int row = messageLogService.addMessageInfo(ma);
      }
      return map;
  }
  /**
   * 驳回会诊
   * @param request
   * @param model
   * @return
   */
  @RequestMapping(value="/refuse.do",method=RequestMethod.POST)
  @ResponseBody
  public Object refuse(HttpServletRequest request,Model model){
      Map<String,Object> map = new HashMap<String,Object>();
      DbcontextHolder.setDbType("imagedb");
      HttpSession session = request.getSession();
      SysUser sysUser =  (SysUser) session.getAttribute("SysUser");
      String caseId = request.getParameter("caseId");
      String rejectReason = request.getParameter("rejectReason");
      String stuuid = request.getParameter("stuuid");
      
      ConsultationWithBLOBs consultation = new ConsultationWithBLOBs();
      consultation.setIsAccept("1");
      consultation.setAcceptPersonLogin("");
      consultation.setAcceptPersonName("");
      consultation.setState("2");
      consultation.setRejectTime(new Date());
      consultation.setRejectReason(rejectReason);
      consultation.setCaseId(caseId);
      int count = consulTationService.updateByCaseId(consultation);
      ConsultationWithBLOBs con = consulTationService.getConsulTationById(caseId);
      SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     
      Map<String,Object> ma = new HashMap<String,Object>(); 
      if(count>0){
    	  MedicalLog medicalLog = new MedicalLog();
	      	medicalLog.setSyslogKey(UUid.getUUID());
	      	medicalLog.setLogtime(new Date());
	      	medicalLog.setLogtype("驳回会诊");
	      	medicalLog.setLoguser(sysUser.getUserName());
	      	medicalLog.setLoguserCode(sysUser.getLoginName());
	      	medicalLog.setCreatePerson(sysUser.getSysuserKey());
	      	medicalLog.setCreateTime(new Date());
	      	medicalLog.setStudyId(stuuid);
	      	medicalLog.setIsDel("0");
	      	int n=medicalLogService.save(medicalLog);
	      	if(n > 0){
	      		map.put("code", 0);
	      	}else{
	      		map.put("code", 1);
	              map.put("msg", "插入日志出错，请稍后重试或者联系管理员！");
	      	}
          map.put("code", 0);
          String content = "【汇智精英】您于"+dft.format(con.getCommitTime())+"提交的会诊申请被驳回，请查看！";
          SysUser userinfo = sysuserService.getById(con.getCreatePerson());
          bigAntUtil.send_user(userinfo.getLoginName(), "会诊通知", content);
          
		  ma.put("message", content);
          ma.put("sendType", "驳回会诊");
          ma.put("recipient", userinfo.getLoginName());
          ma.put("receivePhone", userinfo.getUserPhone());
          
          if(userinfo.getUserPhone()!=null){
  			try {
  				String sendResult = NoteUtil.sendSMSchange("您于"+dft.format(con.getCommitTime())+"提交的会诊申请被驳回，请查看!【汇智精英】", userinfo.getUserPhone());
  				ma.put("sendState", sendResult);
  			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
  		}
      }else{
          map.put("code", 1);
          map.put("msg", "驳回失败，请稍后重试或联系管理员！");
      } 
      int row = messageLogService.addMessageInfo(ma);
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
  public Object received(@RequestParam String caseId,@RequestParam String stuuid,HttpServletRequest request,Model model){
      Map<String,Object> map = new HashMap<String,Object>();
      DbcontextHolder.setDbType("imagedb");
      
      HttpSession session = request.getSession();
      SysUser sysUser =  (SysUser) session.getAttribute("SysUser");
      ConsultationWithBLOBs consultation = new ConsultationWithBLOBs();
      consultation.setIsAccept("0");
      consultation.setAcceptPersonLogin(sysUser.getLoginName());
      consultation.setAcceptPersonName(sysUser.getUserName());
      consultation.setAcceptTime(new Date());
      consultation.setCaseId(caseId);
      int count = consulTationService.updateByCaseId(consultation);
      if(count>0){
    	  MedicalLog medicalLog = new MedicalLog();
	      	medicalLog.setSyslogKey(UUid.getUUID());
	      	medicalLog.setLogtime(new Date());
	      	medicalLog.setLogtype("接受会诊");
	      	medicalLog.setLoguser(sysUser.getUserName());
	      	medicalLog.setLoguserCode(sysUser.getLoginName());
	      	medicalLog.setCreatePerson(sysUser.getSysuserKey());
	      	medicalLog.setCreateTime(new Date());
	      	medicalLog.setStudyId(stuuid);
	      	medicalLog.setIsDel("0");
	      	int n=medicalLogService.save(medicalLog);
	      	if(n > 0){
	      		map.put("code", 0);
	      	}else{
	      		map.put("code", 1);
	              map.put("msg", "插入日志出错，请稍后重试或者联系管理员！");
	      	}
          map.put("code", 0);
      }else{
          map.put("code", 1);
          map.put("msg", "接受失败，请稍后重试或联系管理员！");
      } 
      return map;
  }
  
  /**
   * 提交会诊申请
   * @param request
   * @param model
   * @return
   */
  @RequestMapping(value="/submitConsultation.do",method=RequestMethod.POST)
  @ResponseBody
  public Object submitConsultation(HttpServletRequest request,Model model){
      String caseId = request.getParameter("caseId");
      String groupId = request.getParameter("groupId");
      String userLogin = request.getParameter("userLogin");
      String bingqingmiaoshu = request.getParameter("bingqingmiaoshu");
      String stuuid = request.getParameter("stuuid");
      
      Map<String,Object> map = new HashMap<String,Object>();
      DbcontextHolder.setDbType("imagedb");
      HttpSession session = request.getSession();
      SysUser sysUser =  (SysUser) session.getAttribute("SysUser");
      
      ConsultationWithBLOBs consultation = new ConsultationWithBLOBs();
       
      if(userLogin==null){
          consultation.setCommitType("2");
      }else{
          consultation.setCommitType("1");
          //提交目标医生的登录名
          consultation.setCommitTargetPerson(userLogin);
      }
      consultation.setCommitRequest(bingqingmiaoshu);
      consultation.setIsAccept("1");
      consultation.setAcceptPersonLogin("");
      consultation.setAcceptPersonName("");
      consultation.setState("3");
      consultation.setCaseId(caseId);
      consultation.setGroupId(groupId);
      consultation.setCommitTime(new Date());
      int count = consulTationService.updateByCaseId(consultation);
      
      Map<String,Object> ma = new HashMap<String,Object>(); 
      if(count>0){
    	  MedicalLog medicalLog = new MedicalLog();
	      	medicalLog.setSyslogKey(UUid.getUUID());
	      	medicalLog.setLogtime(new Date());
	      	medicalLog.setLogtype("发起会诊");
	      	medicalLog.setLoguser(sysUser.getUserName());
	      	medicalLog.setLoguserCode(sysUser.getLoginName());
	      	medicalLog.setCreatePerson(sysUser.getSysuserKey());
	      	medicalLog.setCreateTime(new Date());
	      	medicalLog.setStudyId(stuuid);
	      	medicalLog.setIsDel("0");
	      	int n=medicalLogService.save(medicalLog);
	      	if(n > 0){
	      		map.put("code", 0);
	      	}else{
	      		map.put("code", 1);
	              map.put("msg", "插入日志出错，请稍后重试或者联系管理员！");
	      	}
          map.put("code", 0);
          SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          String content = "【汇智精英】"+sysUser.getUserName()+"老师，于"+dft.format(new Date())+"上传了一个病历，请您会诊。";
          bigAntUtil.send_user(userLogin, "会诊通知", content);
          SysUser s = sysuserService.getByLoginname(userLogin);
          
          ma.put("message", content);
          ma.put("sendType", "发起会诊");
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
      }else{
          map.put("code", 1);
          map.put("msg", "提交会诊申请时出错，请稍后重试或联系管理员！");
      } 
      int row = messageLogService.addMessageInfo(ma);
      return map;
  }
  
  /**
   * 根据审核医生待终审会诊列表
   * @param request
   * @param model
   * @return
   */
  @RequestMapping(value="/getRecordByUltimateAudit.do",method=RequestMethod.POST)
  @ResponseBody
  public Object getRecordByUltimateAudit(HttpServletRequest request,Model model){
      Map<String,Object> map = new HashMap<String,Object>();
      DbcontextHolder.setDbType("imagedb");
      HttpSession session = request.getSession();
      SysUser sysUser =  (SysUser) session.getAttribute("SysUser");
      
      int page = Integer.parseInt(request.getParameter("page"));
      int limit = Integer.parseInt(request.getParameter("limit"));
      String state = request.getParameter("state");
      String username = request.getParameter("username");
      String sex = request.getParameter("sex");
      String modality = request.getParameter("modality");
      String studytime = request.getParameter("studytime");
      String beginDate = null;
      String endDate = null;
      String beginState = null;
      String endState = null;
      
      if(studytime!=null){
          String[] time = studytime.split("~");
          
          if(time.length==2){
              beginDate = time[0];
              endDate = time[1];
          }
      }
      if(state!=null){
          String[] states = state.split(",");
          
          if(states.length==2){
              beginState = states[0];
              endState = states[1];
          }
      }
      
      HashMap<String, Object> queryMap = new HashMap<String, Object>();
      queryMap.put("beginDate", beginDate);
      queryMap.put("endDate", endDate);
      queryMap.put("beginState", beginState);
      queryMap.put("endState", endState);
      queryMap.put("username", username);
      queryMap.put("sex", sex);
      queryMap.put("modality", modality);
      queryMap.put("ultimate_audit_login", sysUser.getLoginName());

          
      
      //先查询满足条件的数据有多少条
      int count = consulTationService.getCountByUltimateAudit(queryMap);
      
      queryMap.put("curr", (page-1)*limit);
      queryMap.put("limit",limit);
      
      List<ConsultationWithBLOBs> meidicalRecords = consulTationService.getRecordByUltimateAudit(queryMap);
      
      if(meidicalRecords!=null){
        map.put("code", 0);
        map.put("data", meidicalRecords);
        map.put("count", count);
      }else{
        map.put("code", 1);
        map.put("msg", "查询会诊病历出错，请稍后重试或者联系管理员！");
      }
      
      
      return map;
  }
  
  
  /**
   * 根据审核医生待初审会诊列表
   * @param request
   * @param model
   * @return
   */
  @RequestMapping(value="/getRecordByPrimaryAudit.do",method=RequestMethod.POST)
  @ResponseBody
  public Object getRecordByPrimaryAudit(HttpServletRequest request,Model model){
      Map<String,Object> map = new HashMap<String,Object>();
      DbcontextHolder.setDbType("imagedb");
      HttpSession session = request.getSession();
      SysUser sysUser =  (SysUser) session.getAttribute("SysUser");
      
      int page = Integer.parseInt(request.getParameter("page"));
      int limit = Integer.parseInt(request.getParameter("limit"));
      String state = request.getParameter("state");
      String username = request.getParameter("username");
      String sex = request.getParameter("sex");
      String modality = request.getParameter("modality");
      String studytime = request.getParameter("studytime");
      String bodyName = request.getParameter("bodyName");
      String huizhenState = request.getParameter("huizhenState");
      String uploadtime = request.getParameter("uploadtime");
      String beginDate = null;
      String endDate = null;
      String beginState = null;
      String endState = null;
      String uploadStart = null;
      String uploadEnd = null;
      
      if(studytime!=null){
          String[] time = studytime.split("~");
          
          if(time.length==2){
              beginDate = time[0];
              endDate = time[1];
          }
      }
  	  if(uploadtime!=null){
		  String[] uploadDate = uploadtime.split("~");
		  if(uploadDate.length==2){
			 uploadStart = uploadDate[0];
			 uploadEnd = uploadDate[1];
		  }
	  }
      if(state!=null){
          String[] states = state.split(",");
          
          if(states.length==2){
              beginState = states[0];
              endState = states[1];
          }
      }
      
      HashMap<String, Object> queryMap = new HashMap<String, Object>();
      queryMap.put("beginDate", beginDate);
      queryMap.put("endDate", endDate);
      queryMap.put("beginState", beginState);
      queryMap.put("endState", endState);
      queryMap.put("username", username);
      queryMap.put("sex", sex);
      queryMap.put("modality", modality);
      queryMap.put("primary_audit_login", sysUser.getLoginName());
      queryMap.put("bodyName",bodyName);
      queryMap.put("huizhenState",huizhenState);
      queryMap.put("uploadStart",uploadStart);
      queryMap.put("uploadEnd",uploadEnd);
          
      
      //先查询满足条件的数据有多少条
      int count = consulTationService.getCountByPrimaryAudit(queryMap);
      
      queryMap.put("curr", (page-1)*limit);
      queryMap.put("limit",limit);
      
      List<ConsultationWithBLOBs> meidicalRecords = consulTationService.getRecordByPrimaryAudit(queryMap);
      
      if(meidicalRecords!=null){
        map.put("code", 0);
        map.put("data", meidicalRecords);
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
  @RequestMapping(value="/getRecordByDoctor.do",method=RequestMethod.POST)
  @ResponseBody
  public Object getRecordByDoctor(HttpServletRequest request,Model model){
      Map<String,Object> map = new HashMap<String,Object>();
      DbcontextHolder.setDbType("imagedb");
      HttpSession session = request.getSession();
      SysUser sysUser =  (SysUser) session.getAttribute("SysUser");
      
      int page = Integer.parseInt(request.getParameter("page"));
      int limit = Integer.parseInt(request.getParameter("limit"));
      String state = request.getParameter("state");
      String username = request.getParameter("username");
      String sex = request.getParameter("sex");
      String modality = request.getParameter("modality");
      String studytime = request.getParameter("studytime");
      String bodyName = request.getParameter("bodyName");
      String huizhenState = request.getParameter("huizhenState");
      String uploadtime = request.getParameter("uploadtime");
      String beginDate = null;
      String endDate = null;
      String beginState = null;
      String endState = null;
      String uploadStart = null;
      String uploadEnd = null;
      
      if(studytime!=null){
          String[] time = studytime.split("~");
          
          if(time.length==2){
              beginDate = time[0];
              endDate = time[1];
          }
      }
      if(uploadtime!=null){
			String[] uploadDate = uploadtime.split("~");
			if(uploadDate.length==2){
				uploadStart = uploadDate[0];
				uploadEnd = uploadDate[1];
			}
		}
      if(state!=null){
          String[] states = state.split(",");
          
          if(states.length==2){
              beginState = states[0];
              endState = states[1];
          }
      }
      
      HashMap<String, Object> queryMap = new HashMap<String, Object>();
      queryMap.put("beginDate", beginDate);
      queryMap.put("endDate", endDate);
      queryMap.put("beginState", beginState);
      queryMap.put("endState", endState);
      queryMap.put("username", username);
      queryMap.put("sex", sex);
      queryMap.put("modality", modality);
      queryMap.put("commit_target_person", sysUser.getLoginName());
      queryMap.put("bodyName",bodyName);
      queryMap.put("huizhenState",huizhenState);
      queryMap.put("uploadStart",uploadStart);
      queryMap.put("uploadEnd",uploadEnd);
          
      
      //先查询满足条件的数据有多少条
      int count = consulTationService.getCountByDoctor(queryMap);
      
      queryMap.put("curr", (page-1)*limit);
      queryMap.put("limit",limit);
      
      List<ConsultationWithBLOBs> meidicalRecords = consulTationService.getRecordByDoctor(queryMap);
      
      if(meidicalRecords!=null){
        map.put("code", 0);
        map.put("data", meidicalRecords);
        map.put("count", count);
      }else{
        map.put("code", 1);
        map.put("msg", "查询会诊病历出错，请稍后重试或者联系管理员！");
      }
      
      
      return map;
  }
  
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
		String state = request.getParameter("state");
		String username = request.getParameter("username");
		String sex = request.getParameter("sex");
		String modality = request.getParameter("modality");
		String studytime = request.getParameter("studytime");
		String bodyName = request.getParameter("bodyName");
		String huizhenState = request.getParameter("huizhenState");
		String uploadtime = request.getParameter("uploadtime");
		String beginDate = null;
		String endDate = null;
		String beginState = null;
		String endState = null;
		String uploadStart = null;
		String uploadEnd = null;
		
		if(studytime!=null){
    		String[] time = studytime.split("~");
    		
    		if(time.length==2){
    			beginDate = time[0];
    			endDate = time[1];
    		}
		}
		if(uploadtime!=null){
			String[] uploadDate = uploadtime.split("~");
			if(uploadDate.length==2){
				uploadStart = uploadDate[0];
				uploadEnd = uploadDate[1];
			}
		}
		if(state!=null){
    		String[] states = state.split(",");
    		
    		if(states.length==2){
    		  	beginState = states[0];
    		  	endState = states[1];
    		}
		}
		HashMap<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("beginDate", beginDate);
		queryMap.put("endDate", endDate);
		queryMap.put("beginState", beginState);
		queryMap.put("endState", endState);
		queryMap.put("username", username);
		queryMap.put("sex", sex);
		queryMap.put("modality", modality);
		queryMap.put("createperson", sysUser.getSysuserKey());
		queryMap.put("bodyName",bodyName);
		queryMap.put("huizhenState",huizhenState);
		queryMap.put("uploadStart",uploadStart);
		queryMap.put("uploadEnd",uploadEnd);
		
		
		//先查询满足条件的数据有多少条
		int count = consulTationService.getMedicalCount(queryMap);
		
		queryMap.put("curr", (page-1)*limit);
		queryMap.put("limit",limit);
		
		List<ConsultationWithBLOBs> meidicalRecords = consulTationService.getRecordByCreateUser(queryMap);
		
		if(meidicalRecords!=null){
		  map.put("code", 0);
		  map.put("data", meidicalRecords);
		  map.put("count", count);
		}else{
		  map.put("code", 1);
		  map.put("msg", "查询会诊病历出错，请稍后重试或者联系管理员！");
		}
		
		return map;
	}
	/**
	 * 发起会诊详情：根据用户的id查询用户信息
	 * @param id
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value="/getByImageId.do",method=RequestMethod.GET)
	public Object getByImageId(@RequestParam String stuuid,@RequestParam String imageId,@RequestParam String caseId,HttpServletRequest request,Model model) throws ParseException{
		log.info("getByImageId");
		DbcontextHolder.setDbType("imagedb");
		Consultation query = new Consultation();
        query.setCaseId(caseId);
        Consultation consultation = consulTationService.getById(query);
		MedicalRecord medicalRecord = medicalRecordService.getByImageId(imageId);
		//根据会诊id获取zoom信息
		ZoomManage zoomManage = ZoomManageService.getByZoomKey(caseId);
		if(zoomManage!=null){
		//根据会诊id获取会诊人员
		ConsultationConferee consultationConferee = new ConsultationConferee();
		consultationConferee.setCaseId(caseId);
		List<SysUser> sysUsers = ConsultationConfereeService.getByCaseIdKey(consultationConferee);
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
		model.addAttribute("ReadimageKey", imageId);
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
		model.addAttribute("caseId", caseId);
		model.addAttribute("stuuid", stuuid);
		model.addAttribute("medicalRecord", medicalRecord);
		model.addAttribute("consultation", consultation);
		return "faqihuizhen/xiangqing/index";
	}
	
	/**
	 * 跳转到选择专家界面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/redirectGroup.do",method=RequestMethod.GET)
	public Object redirectGroup(@RequestParam String caseId,@RequestParam String stuuid,HttpServletRequest request,Model model){
	  	log.info("redirectGroup");
	  	model.addAttribute("stuuid", stuuid);
	  	model.addAttribute("caseId", caseId);
	  	
		return "faqihuizhen/group";
	}
	
	/**
	 * 跳转到上传病历界面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/redirectUpload.do",method=RequestMethod.GET)
	public Object redirectUpload(HttpServletRequest request,Model model){
	  	log.info("redirectUpload");
		return "faqihuizhen/upload_progress";
	}

	@RequestMapping(value = "/getProgress",method=RequestMethod.GET)    
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
    public Object uploaddcm(HttpServletResponse response,MultipartHttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        DbcontextHolder.setDbType("imagedb");
        HttpSession session = request.getSession();
        SysUser sysUser = (SysUser) session.getAttribute("SysUser");
        
        String random = request.getParameter("random");
        MultipartFile mpf = request.getFile("file");
        String filename= mpf.getOriginalFilename();
        map.put("filename",filename);
        map.put("random",random);
        // 先转换成dcm对象
        DicomObject dcm = dicom(mpf);
        if (dcm == null) {// 说明不是dcm文件 直接不存储，返回错误
            map.put("code", 1);
            return map;
        }
        
        synchronized (this) {
            
        
        
        // 判断该dcm图像是否上传过
        int m = medicalRecordService.getCountByStudy(dcm.getString(Tag.StudyInstanceUID));
        ConsultationWithBLOBs consultation = new ConsultationWithBLOBs();
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

            consultation.setImageId(medicalRecordId);
            medicalRecordService.save(medicalRecord);
        } else {

            MedicalRecord record = medicalRecordService.getById(dcm.getString(Tag.StudyInstanceUID));
            consultation.setImageId(record.getMedicalRecordKey());
        }

        // 获取当前用户下该病例的会诊状态
        List<String> states = consulTationService.getStateByStuuid(dcm.getString(Tag.StudyInstanceUID),
                sysUser.getSysuserKey());

        boolean saveDcm = true;// 是否可以上传dcm文件 如果该studyuid 有在会诊过程中则不允许上传dcm文件
        for (String state : states) {// 遍历表中所有该stuyuid病历的状态
            // 1-未提交、2-申请驳回、3-待会诊、4--初审驳回、5-终审驳回、6待初审、7-待终审、8-已完成
            if (state.equals("3") || state.equals("4") || state.equals("5") || state.equals("6") || state.equals("7")) {
                saveDcm = false;
                break;
            }
        }
        
        boolean state3 = false;// 判断是否存在状态为1或2的，是否新增病历
        for (String state : states) {
            // 1-未提交、2-申请驳回、3-待会诊、4--初审驳回、5-终审驳回、6待初审、7-待终审、8-已完成
            if (state.equals("2") || state.equals("1")) {
                state3 = true;
                break;
            }
        }

        if (saveDcm) {
            
            if (!state3) {// 如果不存在未提交或者驳回的情况，则新增一个病历
                consultation.setCaseId(UUid.getUUID());
                consultation.setCreatePerson(sysUser.getSysuserKey());
                consultation.setCreatePersonName(sysUser.getUserName());
                consultation.setCreateTime(new Date());
                consultation.setIsDel("0");
                consultation.setState("1");
                int i = consulTationService.save(consultation);
                if (i > 0) {
                    MedicalLog medicalLog = new MedicalLog();
                    medicalLog.setSyslogKey(UUid.getUUID());
                    medicalLog.setLogtime(new Date());
                    medicalLog.setLogtype("上传病例");
                    medicalLog.setLoguser(sysUser.getUserName());
                    medicalLog.setLoguserCode(sysUser.getLoginName());
                    medicalLog.setCreatePerson(sysUser.getSysuserKey());
                    medicalLog.setCreateTime(new Date());
                    medicalLog.setStudyId(dcm.getString(Tag.StudyInstanceUID));
                    medicalLog.setIsDel("0");
                    int n=medicalLogService.save(medicalLog);
                    if(n > 0){
                        map.put("code", 0);
                    }else{
                        map.put("code", 1);
                        map.put("msg", "插入日志出错，请稍后重试或者联系管理员！");
                    }
                    map.put("code", 0);
                } else {
                    map.put("code", 1);
                    map.put("msg", "上传dcm文件出错，请稍后重试或者联系管理员！");
                }
            } else {// 如果存在未提交或者驳回的情况，则只上传dcm文件
                map.put("code", 0);
            }
            
            
            savedcm(request, mpf);// 保存dcm文件图像

        } else {
            map.put("code", 2);
            map.put("msg", "病历正在会诊中,不允许上传该病历的影像！");
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
//    	  	    dcm = dicom(file);
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
     * 增加一条新会诊日程
     * @param 
     * @return
     */
  @RequestMapping(value="/save.do", method=RequestMethod.POST)
  @ResponseBody
  @Transactional(rollbackFor=Exception.class)
  public Object save(@RequestParam String caseId,HttpServletRequest request, Model model){
	log.info("save");
	Map<String, Object> map = new HashMap<>();
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
	Consultation consultation = new Consultation();
	consultation.setZoomKey(zoom_key);
	consultation.setCaseId(caseId);
	//4.记录与会人员
		List<ConsultationConferee> conferees = new ArrayList<ConsultationConferee>();
		for(int i=0;i<member.size();i++){
		    String loginname = member.getString(i);
		    SysUser sys = sysuserService.login(loginname);
			if(sys==null){//系统中没有这个登录名的用户。即在大蚂蚁中存在，本系统中不存在
			}else{
				ConsultationConferee conferee = new ConsultationConferee();
				conferee.setConsultationConfereeKey(UUid.getUUID());
				conferee.setCaseId(caseId);
				conferee.setSysuserKey(sys.getSysuserKey());
				conferees.add(conferee);
			}
		}
		
		Map<String,Object> ma = new HashMap<String,Object>(); 
		try {
			  int imageCount = consulTationService.saveById(consultation);
			  int zoomCount = ZoomManageService.zoomcreate(zoomManage);
		  if(!conferees.isEmpty()){
			  ConsultationConfereeService.saveList(conferees);
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
                    ma.put("sendType", "视频会议");
                    ma.put("recipient", s.getLoginName());
                    ma.put("receivePhone", s.getUserPhone());
                    
    	    		if(s.getUserPhone()!=null){
    	    			String sendResult = NoteUtil.sendSMSchange("您好，"+sysUser.getUserName()+"老师，预定"+startTime+"召开会诊交流会，请您届时参加。【汇智精英】", s.getUserPhone());
    	    			 ma.put("sendState", sendResult);
    	    		}
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
		
	int row = messageLogService.addMessageInfo(ma);	
	return map;
  }
  /**
   * 根据主键修改一条会诊日程
   * @param 
   * @return
   */
  @RequestMapping(value="/updateById.do", method=RequestMethod.POST)
  @ResponseBody
  public Object updateById(@RequestParam String caseId,HttpServletRequest request, Model model){
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
		Consultation consultation = new Consultation();
		consultation.setZoomKey(zoomKey);
		consultation.setCaseId(caseId);
		consultation.setModifyTime(new Date());
		//4.记录与会人员
			List<ConsultationConferee> conferees = new ArrayList<ConsultationConferee>();
			for(int i=0;i<member.size();i++){
			    String loginname = member.getString(i);
			    SysUser sys = sysuserService.login(loginname);
				if(sys==null){//系统中没有这个登录名的用户。即在大蚂蚁中存在，本系统中不存在
				}else{
					ConsultationConferee conferee = new ConsultationConferee();
					conferee.setConsultationConfereeKey(UUid.getUUID());
					conferee.setCaseId(caseId);
					conferee.setSysuserKey(sys.getSysuserKey());
					conferees.add(conferee);
				}
			}
	try {
	  int imageCount = consulTationService.updateById(consultation);
	  int zoomCount = ZoomManageService.zoomupdate(zoomManage);
	  //先删除之前的与会人员根据caseId
	  ConsultationConferee consultationConferee = new ConsultationConferee();
	  consultationConferee.setCaseId(caseId);
	  ConsultationConfereeService.deleteById(consultationConferee);
	  //记录与会人员
	  if(!conferees.isEmpty()){
		  ConsultationConfereeService.saveList(conferees);
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
	 * 跳转到响应会诊详情界面
	 * @param request
	 * @param model
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value="/getBycaseId.do",method=RequestMethod.GET)
	public Object getBycaseId(@RequestParam String caseId,@RequestParam String imageId,@RequestParam String stuuid,HttpServletRequest request,Model model) throws ParseException{
	  	log.info("getBycaseId");
	  	DbcontextHolder.setDbType("imagedb");
	  	Consultation query = new Consultation();
	  	query.setCaseId(caseId);
	  	Consultation consultation = consulTationService.getById(query);
		MedicalRecord medicalRecord = medicalRecordService.getByImageId(imageId);
				//根据会诊id获取zoom信息
				ZoomManage zoomManage = ZoomManageService.getByZoomKey(caseId);
				if(zoomManage!=null){
				//根据会诊id获取会诊人员
				ConsultationConferee consultationConferee = new ConsultationConferee();
				consultationConferee.setCaseId(caseId);
				List<SysUser> sysUsers = ConsultationConfereeService.getByCaseIdKey(consultationConferee);
				String usernames = "";
				String login = "";
				for(SysUser s:sysUsers){
				  usernames +=s.getUserName()+",";
				  login +=s.getLoginName()+",";
				}
				SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String start_time = dft.format(zoomManage.getStartTime());
				model.addAttribute("yuyueshichang", zoomManage.getMinute());
				model.addAttribute("start_time", start_time);
				model.addAttribute("yuhuirenyuan", usernames);
				model.addAttribute("ReadimageKey", imageId);
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
				}else{
					model.addAttribute("yuhuirenyuan", "");
					model.addAttribute("login", "");
				}
				model.addAttribute("caseId", caseId);
				model.addAttribute("stuuid", stuuid);
				model.addAttribute("medicalRecord", medicalRecord);
				model.addAttribute("consultation",consultation);
		return "xiangyinghuizhen/xiangqing/index";
	}
	/**
	   * 根据主键删除一条会诊日程
	   * @param 
	   * @return
	   */
	  @RequestMapping(value="/delBycaseId.do", method=RequestMethod.GET)
	  @ResponseBody
	  @Transactional
	  public Object delBycaseId(@RequestParam String caseId,HttpServletRequest request, Model model){
		log.info("delBycaseId");
		Map<String,Object> map = new HashMap<String,Object>();
		
		DbcontextHolder.setDbType("imagedb");
		Consultation Consultation = new Consultation();
		Consultation.setCaseId(caseId);
		Consultation delReadImage = consulTationService.getById(Consultation);
		ZoomManageWithBLOBs delZoom = ZoomManageService.getById(delReadImage.getZoomKey());
		String delurl = ZoomManageUtil.spliceDelete_URL(delZoom.getId(), delZoom.getValue());
		ZoomManageUtil.send_URL(delurl);
		
		ConsultationConferee consultationConferee = new ConsultationConferee();
		consultationConferee.setCaseId(caseId);
		try {
		  int consulTationCount = consulTationService.updateById(delReadImage);
		  int zoomCount = ZoomManageService.delById(delReadImage.getZoomKey());
		  ConsultationConfereeService.deleteById(consultationConferee);
		  if (consulTationCount > 0 && zoomCount > 0) {
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
	public Object joinReadImage(@RequestParam String caseId,HttpServletRequest request,Model model){
		Map<String,Object> map = new HashMap<String,Object>();
		
		HttpSession session = request.getSession();
		SysUser sysUser =  (SysUser) session.getAttribute("SysUser");
		String sysuserKey = sysUser.getSysuserKey();
	  	DbcontextHolder.setDbType("imagedb");
	  	ZoomManage zoom = ZoomManageService.getByZoomKey(caseId);
	  	if(sysUser==null){
  	  		map.put("code", 1);
    	    map.put("msg", "参与会诊失败，请稍后重试或联系管理员");
    	    return map;
	  	}
	  	if(zoom!=null){
	  	  	map.put("code", 0);
	  	  	map.put("sysuserKey", sysuserKey);
	  	  	map.put("zoom", zoom);
	  	  	map.put("username", sysUser.getUserName());
	  	}else{
	  	    map.put("code", 1);
	  	    map.put("msg", "参与会诊失败，请稍后重试或联系管理员");
	  	}
	  	
		return map;
	}
	/**
	   * 根据stuyid删除读片会中的病历
	   * 删的是关联表，不是病历表
	   * @param 
	   * @return
	   */
	  @RequestMapping(value="/delByStuuid.do", method=RequestMethod.GET)
	  @ResponseBody
	  public Object delByStuuid(@RequestParam String stuuid,@RequestParam String caseId, HttpServletRequest request, Model model) throws ParseException {
		log.info("delByStuuid");
		HashMap<String, String> getParamMap = new HashMap<String, String>();
		DbcontextHolder.setDbType("imagedb");
		getParamMap.put("stuuid", stuuid);
		getParamMap.put("caseId", caseId);
		int row = consulTationService.delByStuuid(getParamMap);
		
		Map<String,Object> map = new HashMap<String,Object>();
		if(row>0){
		  map.put("code", 0);
		}else{
		  map.put("code", 1);
		  map.put("msg", "删除失败，请稍后重试！");
		}
		return map;
	  }
	  
	  //获取部位
	  @RequestMapping(value="/getbodyPartName.do")
	  @ResponseBody
	  public Map getbodyPartName() {
		  log.info("delByStuuid");
		  DbcontextHolder.setDbType("imagedb");
		  Map<String,Object> map = new HashMap<String, Object>();
		  List list = consulTationService.getbodyPartName();
		  map.put("list", list);
		  return map;
	  }
	  //获取检查类型
	  @RequestMapping(value="/getmodalityName.do")
	  @ResponseBody
	  public Object modalityName() {
		  log.info("modalityName");
		  DbcontextHolder.setDbType("imagedb");
		  Map<String,Object> map = new HashMap<String, Object>();
		  List<Modality> modalitylist=modalityService.getmodalityName();
		  map.put("modalitylist", modalitylist);
		  return map;
	  }
	  
	  //发起会诊时，病史附件上传
	  @RequestMapping(value="/historyFile.do")
	  @ResponseBody
	  public Map<String,Object> historyFile(@RequestParam("fileUpload") MultipartFile file, HttpServletRequest request,@RequestParam("stuuid") String stuuid){
		 
		    DbcontextHolder.setDbType("imagedb");
		  
		    Map<String, Object> map = new HashMap<String, Object>();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");

			Properties prop = Util.readData("path.properties");
			SimpleDateFormat ymd = new SimpleDateFormat("yyyyMMdd");
			String savePath = prop.getProperty("accessoryPath").trim()+ymd.format(new Date())+"/";// 获取文件保存路径
				
			//String savePath=request.getSession().getServletContext().getRealPath("/video/");
				
			String fileName = file.getOriginalFilename();// 上传文件的名称
			String pre = fileName.substring(fileName.lastIndexOf(".") + 1);// 后缀名
				
			File pathFile = new File(savePath);// 建文件夹
			if (!pathFile.exists()) {
				pathFile.mkdirs();
			}
			String newFileName = sdf.format(new Date()) + "_" + file.getOriginalFilename();
			String newFilePath = savePath + newFileName;// 新路径
			File newFile = new File(newFilePath);
			if (newFile.exists()) {
				newFilePath = savePath + newFileName;
				newFile = new File(newFilePath);
			}
			try {
				file.transferTo(newFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//保存文件信息
			HttpSession session = request.getSession();
		    SysUser sysUser =  (SysUser) session.getAttribute("SysUser");
		    String userId = sysUser.getSysuserKey();
			
		    
			Accessory accessory = new Accessory();
            accessory.setAccessoryDesc("");
            accessory.setAccessoryKey(UUid.getUUID());
            accessory.setAccessoryNewname(newFileName);
            accessory.setAccessoryOldname(fileName);
            accessory.setCreatePerson(stuuid);
            accessory.setCreateTime(new Date());
            accessory.setIsDel("0");
            accessory.setPath(savePath);
             
            int row = accessoryService.save(accessory);
			
			if(row>0){
				map.put("result", true);
				map.put("msg", "上传成功！");
			}else {
				map.put("result", false);
				map.put("msg", "上传失败！");
			}
			
			return map;
	  } 
	  
	 //查询病史附件列表
	 @RequestMapping(value="/selhistoryFile.do",method=RequestMethod.GET)
	 @ResponseBody
	 public Map<String, Object> selhistoryFile(HttpServletRequest request,@RequestParam("stuuid") String stuuid) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Accessory> list = accessoryService.getById(stuuid);
		if (list.size() != 0) {
			map.put("fileList", list);
		} else {
			map.put("fileList", null);
		}

		return map;
	 }
	 
	 //删除病史附件
	 @RequestMapping(value="/delHistoryFile.do",method=RequestMethod.GET)
	 @ResponseBody
	 public Map<String,Object> delHistoryFile(@RequestParam("fileId") String id){
	    Map<String, Object> map = new HashMap<String, Object>();
		int row = accessoryService.delById(id);
		if (row>0) {
			map.put("result", true);
			map.put("msg", "删除成功！");
		} else {
			map.put("result", true);
			map.put("msg", "删除失败！");
		}
		
		return map;
	 }
	 
	//拍照上传 
	@RequestMapping(value = "/photoFile.do")
	@ResponseBody
	public Map<String,Object> photoFile(@RequestParam("img") String img,HttpServletRequest request) throws IOException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		BASE64Decoder decoder = new BASE64Decoder();

		Properties prop = Util.readData("path.properties");
		SimpleDateFormat ymd = new SimpleDateFormat("yyyyMMdd");
		String savePath = prop.getProperty("accessoryPath").trim()+ymd.format(new Date())+"/";// 获取文件保存路径
		String newFileName = "photo_" + sdf.format(new Date()) + ".jpeg";

		File fileDir = new File(savePath);
        if (!fileDir.exists()) { // 如果不存在 则创建
            fileDir.mkdirs();
        }
        
		File uploadedFile = new File(savePath, newFileName);
		// Base64解码
		byte[] b = decoder.decodeBuffer(img);
		for (int i = 0; i < b.length; ++i) {
			if (b[i] < 0) {// 调整异常数据
				b[i] += 256;
			}
		}
		// 生成jpeg图片
		OutputStream os = new FileOutputStream(uploadedFile);
		os.write(b);
		os.flush();
		os.close();
		
		//保存文件信息
		Map<String,Object> map = new HashMap<String,Object>();
		HttpSession session = request.getSession();
	    SysUser sysUser =  (SysUser) session.getAttribute("SysUser");
	    String userId = sysUser.getSysuserKey();
		
		map.put("fileUrl", savePath+"/"+newFileName);
		map.put("fileName", newFileName);
		map.put("userId", userId);
		int row = consulTationService.addHistoryFile(map);
		if(row>0){
			map.put("result", true);
			map.put("msg", "上传成功！");
		}else {
			map.put("result", false);
			map.put("msg", "上传失败！");
		}
		return map;
	}
	
	
}
