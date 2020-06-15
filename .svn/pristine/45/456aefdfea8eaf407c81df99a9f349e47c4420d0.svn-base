package com.inteall.image.controller;

import java.util.Date;
import java.util.HashMap;

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

import com.inteall.image.pojo.ConsultationCommentWithBLOBs;
import com.inteall.image.pojo.SysUser;
import com.inteall.image.service.ConsultationCommentService;
import com.inteall.image.util.DbcontextHolder;
import com.inteall.image.util.UUid;

/**
 * @author 韩明君
 * @date 2018年10月9日 上午10:56:39
 * @version 1.0 
 * @parameter 
 */
@Controller
@RequestMapping("/consultationcomment")
public class ConsultationCommentController {
    private static Logger log = Logger.getLogger(ConsultationCommentController.class.getName());
    @Resource
    private ConsultationCommentService consultationCommentService;
    
    @RequestMapping(value="/getImageComment.do",method=RequestMethod.GET)
    public Object getImageComment(@RequestParam String caseId,HttpServletRequest request,Model model){
        log.info("getImageComment");
        DbcontextHolder.setDbType("imagedb");
        ConsultationCommentWithBLOBs commentWithBLOBs = consultationCommentService.getById(caseId);
        model.addAttribute("caseId", caseId);
        model.addAttribute("commentWithBLOBs", commentWithBLOBs);
        return "consultationcomment/imagedetail";
    }
    
    @RequestMapping(value="/redirectImageSaveOrUpdate.do",method=RequestMethod.GET)
    public Object redirectImageSaveOrUpdate(@RequestParam String caseId,HttpServletRequest request,Model model){
        log.info("redirectImageSaveOrUpdate");
        
        DbcontextHolder.setDbType("imagedb");
        ConsultationCommentWithBLOBs commentWithBLOBs = consultationCommentService.getById(caseId);
        model.addAttribute("caseId", caseId);
        model.addAttribute("commentWithBLOBs", commentWithBLOBs);
        return "consultationcomment/imageSaveOrUpdate";
    }
    
    @RequestMapping(value="/saveorupdate.do",method=RequestMethod.POST)
    @ResponseBody
    public Object saveorupdate(HttpServletRequest request,Model model){
        log.info("saveorupdate");
        HttpSession session = request.getSession();
        SysUser sysUser =  (SysUser) session.getAttribute("SysUser");
        
        HashMap<Object, Object> hashMap = new HashMap<>();
        DbcontextHolder.setDbType("imagedb");
        String comment_key = request.getParameter("commentKey");
        String caseId = request.getParameter("caseId");
        String check_number = request.getParameter("check_number");
        String jutishuoming = request.getParameter("jutishuoming");
        String pingshenjieguo = request.getParameter("pingshenjieguo");
        
        ConsultationCommentWithBLOBs query = consultationCommentService.getById(caseId);
        ConsultationCommentWithBLOBs consultationCommentWithBLOBs = new ConsultationCommentWithBLOBs();
        
        consultationCommentWithBLOBs.setCaseId(caseId);
        consultationCommentWithBLOBs.setImageCommentContent(jutishuoming);
        consultationCommentWithBLOBs.setImageCommentItem(check_number);
        consultationCommentWithBLOBs.setImageCommentResult(pingshenjieguo);
        
        int count=0;
        if(query==null){
            consultationCommentWithBLOBs.setCommentKey(UUid.getUUID());
            consultationCommentWithBLOBs.setImageCommentCreatePerson(sysUser.getSysuserKey());
            consultationCommentWithBLOBs.setImageCommentCreateTime(new Date());
            count = consultationCommentService.insert(consultationCommentWithBLOBs);
        }else{
            consultationCommentWithBLOBs.setCommentKey(query.getCommentKey());
            consultationCommentWithBLOBs.setImageCommentUpdatePerson(sysUser.getSysuserKey());
            consultationCommentWithBLOBs.setImageCommentUpdateTime(new Date());
            count = consultationCommentService.updateById(consultationCommentWithBLOBs);
        }
       
        if(count>0){
            hashMap.put("code", 0);
        }else{
            hashMap.put("code", 1);
            hashMap.put("msg", "保存失败");
        }
        
        return hashMap;
    }
    
    @RequestMapping(value="/getReportComment.do",method=RequestMethod.GET)
    public Object getReportComment(@RequestParam String caseId,HttpServletRequest request,Model model){
        log.info("getImageComment");
        DbcontextHolder.setDbType("imagedb");
        ConsultationCommentWithBLOBs commentWithBLOBs = consultationCommentService.getById(caseId);
        model.addAttribute("caseId", caseId);
        model.addAttribute("commentWithBLOBs", commentWithBLOBs);
        return "consultationcomment/reportdetail";
    }
    @RequestMapping(value="/redirectReportSaveOrUpdate.do",method=RequestMethod.GET)
    public Object redirectReportSaveOrUpdate(@RequestParam String caseId,HttpServletRequest request,Model model){
        log.info("redirectReportSaveOrUpdate");
        DbcontextHolder.setDbType("imagedb");
        ConsultationCommentWithBLOBs commentWithBLOBs = consultationCommentService.getById(caseId);
        model.addAttribute("caseId", caseId);
        model.addAttribute("commentWithBLOBs", commentWithBLOBs);
        return "consultationcomment/reportSaveOrUpdate";
    }
    @RequestMapping(value="/reportSaveOrUpdate.do",method=RequestMethod.POST)
    @ResponseBody
    public Object reportSaveOrUpdate(HttpServletRequest request,Model model){
        log.info("saveorupdate");
        HttpSession session = request.getSession();
        SysUser sysUser =  (SysUser) session.getAttribute("SysUser");
        
        HashMap<Object, Object> hashMap = new HashMap<>();
        DbcontextHolder.setDbType("imagedb");
        String caseId = request.getParameter("caseId");
        String check_number = request.getParameter("check_number");
        String jutishuoming = request.getParameter("jutishuoming");
        String pingshenjieguo = request.getParameter("pingshenjieguo");
        
        ConsultationCommentWithBLOBs query = consultationCommentService.getById(caseId);
        ConsultationCommentWithBLOBs consultationCommentWithBLOBs = new ConsultationCommentWithBLOBs();
        
        consultationCommentWithBLOBs.setCaseId(caseId);
        consultationCommentWithBLOBs.setReportCommentContent(jutishuoming);
        consultationCommentWithBLOBs.setReportCommentItem(check_number);
        consultationCommentWithBLOBs.setReportCommentResult(pingshenjieguo);
        
        int count=0;
        if(query==null){
            consultationCommentWithBLOBs.setCommentKey(UUid.getUUID());
            consultationCommentWithBLOBs.setReportCommentCreatePerson(sysUser.getSysuserKey());
            consultationCommentWithBLOBs.setReportCommentCreateTime(new Date());
            count = consultationCommentService.insert(consultationCommentWithBLOBs);
        }else{
            consultationCommentWithBLOBs.setCommentKey(query.getCommentKey());
            consultationCommentWithBLOBs.setReportCommentUpdatePerson(sysUser.getSysuserKey());
            consultationCommentWithBLOBs.setReportCommentUpdateTime(new Date());
            count = consultationCommentService.updateById(consultationCommentWithBLOBs);
        }
       
        if(count>0){
            hashMap.put("code", 0);
        }else{
            hashMap.put("code", 1);
            hashMap.put("msg", "保存失败");
        }
        
        return hashMap;
    }
}
