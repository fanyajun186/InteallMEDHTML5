package com.inteall.image.controller;

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

import com.inteall.image.pojo.BigAntUser;
import com.inteall.image.pojo.MedicalRecord;
import com.inteall.image.pojo.PersonToPerson;
import com.inteall.image.pojo.SysUser;
import com.inteall.image.service.BigAntService;
import com.inteall.image.service.PersonToPersonService;
import com.inteall.image.util.DbcontextHolder;
import com.inteall.image.util.UUid;

import net.sf.json.JSONObject;

/**
 * @author 韩明君
 * @date 2018年8月22日 下午5:25:37
 * @version 1.0 
 * @parameter 
 */
@Controller
@RequestMapping("persontoperson")
public class PersonToPersonController {
    private static Logger log = Logger.getLogger(PersonToPersonController.class.getName());
    @Resource
    private PersonToPersonService personToPersonService;
    @Resource
    private BigAntService bigAntService;
    
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
     * 跳转到个人分享界面
     * 
     * @param
     * @return
     */
    @RequestMapping(value = "/getByShareGroupId.do", method = RequestMethod.GET)
    public Object getByShareGroupId(@RequestParam String medicalRecordKey, HttpServletRequest request, Model model) {
        log.info("getByShareGroupId");
        DbcontextHolder.setDbType("imagedb");
        model.addAttribute("medicalRecordKey", medicalRecordKey);
        return "persontoperson/persontoperson";
    }
  
    @RequestMapping(value="/getSaveSharePerson.do", method=RequestMethod.POST)
    @ResponseBody
    public Object getSaveSharePerson(HttpServletRequest request, Model model) {
        log.info("getSaveSharePerson");
        Map<String, Object> map = new HashMap<String, Object>();
        DbcontextHolder.setDbType("imagedb");
        HttpSession session = request.getSession();
        SysUser sysUser = (SysUser) session.getAttribute("SysUser");
        String json = request.getParameter("json");
        JSONObject returnData = JSONObject.fromObject(json);
        String medicalRecordKey = returnData.getString("medicalRecordKey");
        String userLogin = returnData.getString("userLogin");

        PersonToPerson personToPerson = new PersonToPerson();
        personToPerson.setCreatePerson(sysUser.getSysuserKey());
        personToPerson.setCreateTime(new Date());
        personToPerson.setMedicalRecordKey(medicalRecordKey);
        personToPerson.setPersonToPersonKey(UUid.getUUID());
        personToPerson.setTargetPerson(userLogin);

        int i = personToPersonService.getSaveSharePerson(personToPerson);
        if (i > 0) {
            map.put("code", 0);
        } else {
            map.put("code", 1);
            map.put("msg", "分享病历失败，请稍后重试或联系管理员！");
        }

        return map;
    }
    
    @RequestMapping(value="/getRecordByPerson.do", method=RequestMethod.POST)
    @ResponseBody
    public Object getRecordByPerson(HttpServletRequest request, Model model) {
        log.info("getRecordByPerson");
        Map<String, Object> map = new HashMap<String, Object>();
        DbcontextHolder.setDbType("imagedb");
        HttpSession session = request.getSession();
        SysUser sysUser = (SysUser) session.getAttribute("SysUser");
        
        int page = Integer.parseInt(request.getParameter("page"));
        int limit = Integer.parseInt(request.getParameter("limit"));
        String username = request.getParameter("username");
        String sex = request.getParameter("sex");
        String modality = request.getParameter("modality");
        String studytime = request.getParameter("studytime");
        String state = request.getParameter("state");
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
        queryMap.put("state", state);
        queryMap.put("userLogin", sysUser.getLoginName());
        queryMap.put("userId", sysUser.getSysuserKey() );
        queryMap.put("page", page);
        queryMap.put("limit", limit);
        
        
        //先查询满足条件的数据有多少条
        int count = personToPersonService.getMedicalCount(queryMap);
        
        queryMap.put("curr", (page-1)*limit);
        queryMap.put("limit",limit);
        
        List<PersonToPerson> meidicalRecords = personToPersonService.getRecord(queryMap);
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
    
}
