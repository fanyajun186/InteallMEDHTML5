package com.inteall.image.controller;

import java.io.UnsupportedEncodingException;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inteall.image.pojo.ConsultationWithBLOBs;
import com.inteall.image.pojo.MedicalLog;
import com.inteall.image.pojo.MedicalRecord;
import com.inteall.image.pojo.Report;
import com.inteall.image.pojo.ReportTraceWithBLOBs;
import com.inteall.image.pojo.ReportWithBLOBs;
import com.inteall.image.pojo.SysUser;
import com.inteall.image.service.ConsultationService;
import com.inteall.image.service.MedicalLogService;
import com.inteall.image.service.MedicalRecordService;
import com.inteall.image.service.MessageLogService;
import com.inteall.image.service.ReportService;
import com.inteall.image.service.ReportTraceService;
import com.inteall.image.service.SysUserService;
import com.inteall.image.util.BigAntUtil;
import com.inteall.image.util.DbcontextHolder;
import com.inteall.image.util.NoteUtil;
import com.inteall.image.util.UUid;


/** 
* @author 韩明君  
* @date 创建时间：2018年2月7日 下午1:17:38 
* @version 1.0 
* @parameter  
*/
@Controller
@RequestMapping("/report")
public class ReportController {
	private static Logger log = Logger.getLogger(ReportController.class.getName());
	@Resource
	private ReportService reportService;
	@Resource
    private ReportTraceService reportTraceService;
	@Resource
	private ConsultationService consulTationService;
	@Resource
	private MedicalLogService medicalLogService;
	@Resource
	private SysUserService sysuserService;
    @Resource
    private MedicalRecordService medicalRecordService;
    private String ssid = "E6300170-E5B0-B072-E18D-74A870934D48";
    private String uid = "116";
    private String uname = "电子客服";
    private BigAntUtil bigAntUtil = new BigAntUtil(ssid, uid, uname);
	
    @Resource
    private MessageLogService messageLogService;
    
    /**
     * 报告提交审核
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value="/submitReview.do",method=RequestMethod.GET)
    @ResponseBody
    public Object submitReview(@RequestParam String caseId,@RequestParam String stuuid,@RequestParam String reportId,HttpServletRequest request,Model model){
        log.info("submitReview");
        DbcontextHolder.setDbType("imagedb");
        HttpSession session = request.getSession();
        SysUser sysUser =  (SysUser) session.getAttribute("SysUser");
        
        
        Map<String,Object> map = new HashMap<String,Object>();
        ConsultationWithBLOBs consultationWithBLOBs = new ConsultationWithBLOBs();
        consultationWithBLOBs.setCaseId(caseId);
        consultationWithBLOBs.setState("6");
        consultationWithBLOBs.setReportCommitTime(new Date());
        
        int conrow = consulTationService.updateByCaseId(consultationWithBLOBs);
        ReportWithBLOBs reportWithBLOBs = reportService.getById(reportId);
        ReportTraceWithBLOBs reportTraceWithBLOBs = new ReportTraceWithBLOBs();
        reportTraceWithBLOBs.setAbnormal(reportWithBLOBs.getAbnormal());
        reportTraceWithBLOBs.setAdvise(reportWithBLOBs.getAdvise());
        reportTraceWithBLOBs.setCheckMethod(reportWithBLOBs.getCheckMethod());
        reportTraceWithBLOBs.setCheckName(reportWithBLOBs.getCheckName());
        reportTraceWithBLOBs.setCheckView(reportWithBLOBs.getCheckView());
        reportTraceWithBLOBs.setCreatePerson(reportWithBLOBs.getCreatePerson());
        reportTraceWithBLOBs.setCreatetime(reportWithBLOBs.getCreatetime());
        reportTraceWithBLOBs.setDelPerson(reportWithBLOBs.getDelPerson());
        reportTraceWithBLOBs.setDelTime(reportWithBLOBs.getDelTime());
        reportTraceWithBLOBs.setDiagnosisResult(reportWithBLOBs.getDiagnosisResult());
        reportTraceWithBLOBs.setEditState(reportWithBLOBs.getEditState());
        reportTraceWithBLOBs.setEditUser(reportWithBLOBs.getEditUser());
        reportTraceWithBLOBs.setIsDel(reportWithBLOBs.getIsDel());
        reportTraceWithBLOBs.setKeyImage(reportWithBLOBs.getKeyImage());
        reportTraceWithBLOBs.setMedicalRecordKey(reportWithBLOBs.getMedicalRecordKey());
        reportTraceWithBLOBs.setModifyPerson(reportWithBLOBs.getModifyPerson());
        reportTraceWithBLOBs.setModifyTime(reportWithBLOBs.getModifyTime());
        reportTraceWithBLOBs.setReportKey(UUid.getUUID());
        reportTraceWithBLOBs.setReportRemark1(reportWithBLOBs.getReportRemark1());
        reportTraceWithBLOBs.setReportRemark2(reportWithBLOBs.getReportRemark2());
        reportTraceWithBLOBs.setReportRemark3(reportWithBLOBs.getReportRemark3());
        reportTraceWithBLOBs.setThink(reportWithBLOBs.getThink());
        
        reportTraceService.save(reportTraceWithBLOBs);
        
        Map<String,Object> ma = new HashMap<String,Object>(); 
        if(conrow>0){
        	 MedicalLog medicalLog = new MedicalLog();
 	      	medicalLog.setSyslogKey(UUid.getUUID());
 	      	medicalLog.setLogtime(new Date());
 	      	medicalLog.setLogtype("提交报告");
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
            ConsultationWithBLOBs con = consulTationService.getConsulTationById(caseId);
            SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String content = "【汇智精英】"+sysUser.getUserName()+"于"+dft.format(con.getCommitTime())+"提交了一份会诊报告，请您审核！";
            bigAntUtil.send_user(con.getPrimaryAuditLogin(), "会诊通知", content);
            SysUser s = sysuserService.getByLoginname(con.getPrimaryAuditLogin());
            
            ma.put("message", content);
            ma.put("sendType", "报告审核");
            ma.put("recipient", s.getLoginName());
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
            int row = messageLogService.addMessageInfo(ma);
        }else{
            map.put("code", 1);
            map.put("msg", "报告提交审核失败，请稍后重试或者联系管理员！");
        }
        return map;
    }
    /**
     * 跳转到选择专家界面
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value="/redirectShenheren.do",method=RequestMethod.GET)
    public Object redirectShenheren(@RequestParam String type,HttpServletRequest request,Model model){
        log.info("redirectShenheren");
        model.addAttribute("type", type);
        return "baogao/shenheren";
    }
    @RequestMapping(value="/redirectReportCheck.do",method=RequestMethod.GET)
    public Object redirectReportCheck(@RequestParam String caseId,@RequestParam String stuuid,@RequestParam String reportId,HttpServletRequest request,Model model){
        log.info("redirectReportCheck");
        DbcontextHolder.setDbType("imagedb");
        ConsultationWithBLOBs consultationWithBLOBs = consulTationService.getConsulTationById(caseId);
        MedicalRecord medicalRecord = medicalRecordService.getById(stuuid);
        if(reportId!=null&&!reportId.equals("")){
            ReportWithBLOBs reportWithBLOBs = reportService.getById(reportId);
            model.addAttribute("reportWithBLOBs", reportWithBLOBs);
        }
        model.addAttribute("caseId", caseId);
        model.addAttribute("stuuid", stuuid);
        model.addAttribute("reportId", reportId);
        model.addAttribute("consultationWithBLOBs", consultationWithBLOBs);
        model.addAttribute("medicalRecord", medicalRecord);
        return "baogao/check";
    }
    /**
     * 打印预览
     * @param caseId
     * @param stuuid
     * @param reportId
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value="/redirectPrintPreview.do",method=RequestMethod.GET)
    public Object redirectPrintPreview(@RequestParam String caseId,@RequestParam String stuuid,@RequestParam String reportId,HttpServletRequest request,Model model){
        log.info("redirectPrintPreview");
        DbcontextHolder.setDbType("imagedb");
        ConsultationWithBLOBs consultationWithBLOBs = consulTationService.getConsulTationById(caseId);
        MedicalRecord medicalRecord = medicalRecordService.getById(stuuid);
        if(reportId!=null&&!reportId.equals("")){
            ReportWithBLOBs reportWithBLOBs = reportService.getById(reportId);
            model.addAttribute("reportWithBLOBs", reportWithBLOBs);
        }
        String baogaoName = consultationWithBLOBs.getAcceptPersonLogin();
        String qmUrl = reportService.selqmUrl(baogaoName);//根据用户查询电子签名路径
        
        model.addAttribute("consultationWithBLOBs", consultationWithBLOBs);
        model.addAttribute("stuuid", stuuid);
        model.addAttribute("medicalRecord", medicalRecord);
        model.addAttribute("qmUrl",qmUrl);
        return "baogao/printpreview";
    }
    /**
     * 跳转到报告编辑页面
     * @param caseId
     * @param stuuid
     * @param reportId
     * @param request
     * @param model
     * @return
     */
	@RequestMapping(value="/redirectReportEdit.do",method=RequestMethod.GET)
	public Object redirectReportEdit(@RequestParam String caseId,@RequestParam String stuuid,@RequestParam String modalityCode,@RequestParam String reportId,HttpServletRequest request,Model model){
	  	log.info("redirectReportEdit");
	  	DbcontextHolder.setDbType("imagedb");
	  	ConsultationWithBLOBs consultationWithBLOBs = consulTationService.getConsulTationById(caseId);
	  	MedicalRecord medicalRecord = medicalRecordService.getById(stuuid);
	  	if(reportId!=null&&!reportId.equals("")){
	  	    ReportWithBLOBs reportWithBLOBs = reportService.getById(reportId);
	  	    model.addAttribute("reportWithBLOBs", reportWithBLOBs);
	  	}
	  	model.addAttribute("caseId", caseId);
	    model.addAttribute("stuuid", stuuid);
	    model.addAttribute("reportId", reportId);
	  	model.addAttribute("consultationWithBLOBs", consultationWithBLOBs);
	  	model.addAttribute("medicalRecord", medicalRecord);
	  	model.addAttribute("modalityCode", modalityCode);
		return "baogao/edit";
	}
	@RequestMapping(value="/redirectReportDetail.do",method=RequestMethod.GET)
    public Object redirectReportDetail(@RequestParam String caseId,@RequestParam String stuuid,@RequestParam String reportId,HttpServletRequest request,Model model){
        log.info("redirectReportDetail");
        
        DbcontextHolder.setDbType("imagedb");
        ConsultationWithBLOBs consultationWithBLOBs = consulTationService.getConsulTationById(caseId);
        MedicalRecord medicalRecord = medicalRecordService.getById(stuuid);
        if(reportId!=null&&!reportId.equals("")){
            ReportWithBLOBs reportWithBLOBs = reportService.getById(reportId);
            model.addAttribute("reportWithBLOBs", reportWithBLOBs);
        }
        model.addAttribute("caseId", caseId);
	    model.addAttribute("stuuid", stuuid);
	    model.addAttribute("reportId", reportId);
        model.addAttribute("consultationWithBLOBs", consultationWithBLOBs);
        model.addAttribute("medicalRecord", medicalRecord);
        
        return "baogao/index";
    }
	/**
	 * 根据报告的id查询报告信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/getById.do",method=RequestMethod.GET)
	@ResponseBody
	public Object getById(@RequestParam String id){
		log.info("getById");
		Map<String,Object> map = new HashMap<String,Object>();
		
		Report Report = reportService.getById(id);
		map.put("Report", Report);
		return map;
	}
	
	/**
	 * 根据报告的id查询报告信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/getAll.do",method=RequestMethod.GET)
	@ResponseBody
	public Object getAll(HttpServletRequest request,Model model){
		log.info("getAll");
		Map<String,Object> map = new HashMap<String,Object>();
		
		ReportWithBLOBs Report = new ReportWithBLOBs();
		
		List<ReportWithBLOBs> ReportList = reportService.getAll(Report);
		map.put("报告数量", ReportList.size());
		map.put("ReportList", ReportList);
		return map;
	}
	
	
	
	/**
	 * 新增报告信息
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/save.do",method=RequestMethod.POST)
	@ResponseBody
	@Transactional
	public Object save(HttpServletRequest request,Model model){
		log.info("save");
		String jianchamingcheng = request.getParameter("jianchamingcheng");
		String jianchafangfa = request.getParameter("jianchafangfa");
		String jianchasuojian = request.getParameter("jianchasuojian");
		String zhenduanjieguo = request.getParameter("zhenduanjieguo");
		String sikaofenxi = request.getParameter("sikaofenxi");
        String jianchajianyi = request.getParameter("jianchajianyi");
        String chushenrenName = request.getParameter("chushenrenName");
        String chushenrenLogin = request.getParameter("chushenrenLogin");
        String fushenrenName = request.getParameter("fushenrenName");
        String fushenrenLogin = request.getParameter("fushenrenLogin");
        String reportId = request.getParameter("reportId");
        String caseId = request.getParameter("caseId");
        String shifoushenhe = request.getParameter("shifoushenhe");
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
		
		
		ConsultationWithBLOBs consultationWithBLOBs = new ConsultationWithBLOBs();
		
		consultationWithBLOBs.setPrimaryAuditLogin(chushenrenLogin);
		consultationWithBLOBs.setPrimaryAuditName(chushenrenName);
		consultationWithBLOBs.setUltimateAuditLogin(fushenrenLogin);
        consultationWithBLOBs.setUltimateAuditName(fushenrenName);
        consultationWithBLOBs.setCaseId(caseId);
		try {
		    int reportrow;
		    if(reportId.equals("null")||reportId.equals("")){
		        report.setCreatePerson(sysUser.getSysuserKey());
		        report.setCreatetime(new Date());
		        reportId = UUid.getUUID();
		        report.setReportKey(reportId);
		        consultationWithBLOBs.setReportId(reportId);
		        reportrow = reportService.save(report);
		    }else {
		        report.setModifyPerson(sysUser.getSysuserKey());
		        report.setModifyTime(new Date());
		        report.setReportKey(reportId);
		        reportrow = reportService.updateById(report);
		    }
		    if(shifoushenhe.equals("false")){
		        consultationWithBLOBs.setState("8");
		    }
            int conrow = consulTationService.updateByCaseId(consultationWithBLOBs);
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
	
	/**
	 * 根据报告id修改报告信息
	 * @param id
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/updateById.do",method=RequestMethod.GET)
	@ResponseBody
	public Object updateById(@RequestParam String id,HttpServletRequest request,Model model){
		log.info("updateById");
		Map<String,Object> map = new HashMap<String,Object>();
		
		ReportWithBLOBs Report = new ReportWithBLOBs();
		
		int row = reportService.updateById(Report);
		
		map.put("修改的行数", row);
		map.put("修改的信息", Report);
		return map;
	}
}
