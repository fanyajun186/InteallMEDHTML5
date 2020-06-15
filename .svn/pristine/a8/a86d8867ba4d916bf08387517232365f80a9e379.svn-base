package com.inteall.image.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.inteall.image.pojo.Consultation;
import com.inteall.image.pojo.DateAndCount;
import com.inteall.image.pojo.ReadImage;
import com.inteall.image.pojo.SysUser;
import com.inteall.image.pojo.Video;
import com.inteall.image.pojo.ZoomManageWithBLOBs;
import com.inteall.image.service.ConsultationService;
import com.inteall.image.service.CountService;
import com.inteall.image.service.ReadImageService;
import com.inteall.image.service.SysUserService;
import com.inteall.image.service.VideoService;
import com.inteall.image.service.ZoomManageService;
import com.inteall.image.util.BigAntUtil;
import com.inteall.image.util.DbcontextHolder;
import com.inteall.image.util.MyDate;

import net.sf.json.JSONObject;

/**
 * @author 韩明君
 * @date 2018年4月26日 上午9:42:11
 * @version 1.0 
 * @parameter 
 */
@Controller
@RequestMapping("/home")
public class HomeController {
    private static Logger log = Logger.getLogger(HomeController.class.getName());
    @Resource
    private ReadImageService readImageService;
    @Resource
    private VideoService videoService;
    @Resource
    private ZoomManageService zoomManageService;
    @Resource
    private ConsultationService consulTationService;
    @Resource
    private CountService countService;
    @Resource
    private SysUserService sysuserService;
    private String ssid = "E6300170-E5B0-B072-E18D-74A870934D48";
    private String uid = "116";
    private String uname = "电子客服";
    private BigAntUtil bigAntUtil = new BigAntUtil(ssid, uid, uname);
    
    /**
     * 跳转到首页
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value="/home.do",method=RequestMethod.GET)
    public Object home(HttpServletRequest request,Model model){
        log.info("home");
        HttpSession session = request.getSession();
        SysUser sysUser =  (SysUser) session.getAttribute("SysUser");
        String loginname=sysUser.getLoginName();
        String valid_code = sysUser.getPassword();
        String get_imstart = bigAntUtil.get_imstart(loginname, valid_code);
        JSONObject jsonObject = JSONObject.fromObject(get_imstart);
        model.addAttribute("imstart", jsonObject.get("data"));
        return "home/home";
    }
    @RequestMapping(value="/getAllNumber.do",method=RequestMethod.POST)
    @ResponseBody
    public Object getAllNumber(HttpServletRequest request,Model model){
        log.info("getAllNumber");
        Map<String,Object> map = new HashMap<String,Object>();
        DbcontextHolder.setDbType("imagedb");
       
        
        HttpSession session = request.getSession();
        SysUser sysUser =  (SysUser) session.getAttribute("SysUser");
        
        HashMap<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("commit_target_person", sysUser.getLoginName());
        queryMap.put("beginState", 3);
        queryMap.put("endState", 5);
        int xiangyinghuizhenNum = consulTationService.getCountByDoctor(queryMap);
        queryMap.put("beginState", 6);
        queryMap.put("endState", 6);//包括待初审
        queryMap.put("primary_audit_login", sysUser.getLoginName());
        int daichushenNum = consulTationService.getCountByPrimaryAudit(queryMap);
        queryMap.put("beginState", 7);
        queryMap.put("endState", 7);//包括待终审
        queryMap.put("ultimate_audit_login", sysUser.getLoginName());
        int daizhongshenNum = consulTationService.getCountByUltimateAudit(queryMap);
        queryMap.put("createperson", sysUser.getSysuserKey());
        queryMap.put("beginState", 3);
        queryMap.put("endState", 7);//包括待初审
        int huizhenshenqingNum = consulTationService.getMedicalCount(queryMap);
        
        map.put("code", 0);
        map.put("xiangyinghuizhenNum", xiangyinghuizhenNum);
        map.put("baogaoshenheNum", daichushenNum+daizhongshenNum);
        map.put("huizhenshenqingNum", huizhenshenqingNum);
        return map;
    }
    /**
     * 获取参加会议的url
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value="/getEnterUrl.do",method=RequestMethod.POST)
    @ResponseBody
    public Object getEnterUrl(HttpServletRequest request,Model model){
        log.info("getEnterUrl");
        Map<String,Object> map = new HashMap<String,Object>();
        DbcontextHolder.setDbType("imagedb");
        
        String zoom_key = request.getParameter("zoom_key");
        HttpSession session = request.getSession();
        SysUser sysUser =  (SysUser) session.getAttribute("SysUser");
        String sysuserKey = sysUser.getSysuserKey();
        
        ZoomManageWithBLOBs zoomManage = zoomManageService.getById(zoom_key);
        if(zoomManage!=null){
        	if(sysuserKey.equals(zoomManage.getCreatePerson())){
        		map.put("code", 0);
        		map.put("zoomUrl", zoomManage.getStartUrl());
        	}else{
        		map.put("code", 0);
        		map.put("zoomUrl", zoomManage.getJoinUrl()+"&uname="+sysUser.getUserName());
        	}
        }else{
            map.put("code", 1);
            map.put("msg", "获取url出错，请稍后重试或联系管理员！");
        }
        
        
        return map;
    }
    /**
     * 获取所有的视频日程：包括读片会、视频教学、视频会诊
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value="/getAllDate.do",method=RequestMethod.POST)
    @ResponseBody
    public Object getAllDate(HttpServletRequest request,Model model){
        log.info("getAllDate");
        Map<String,Object> map = new HashMap<String,Object>();
        DbcontextHolder.setDbType("imagedb");
        
        HttpSession session = request.getSession();
        SysUser sysUser =  (SysUser) session.getAttribute("SysUser");
        
        //1.查询当前用户创建的读片会
        ReadImage readImage = new ReadImage();
        readImage.setCreatePerson(sysUser.getSysuserKey());
        //2.查询当前用户被要请的读片会
        List<ReadImage> readImageList = readImageService.getByConferee(readImage);
        
        //1.查询当前用户创建的视频教学
        Video video = new Video();
        video.setCreatePerson(sysUser.getSysuserKey());
        //2.查询当前用户被要请的视频教学
        List<Video> videoList = videoService.getByTutorials(video);
        
        //1.查询当前用户创建的视频会诊
        Consultation consultation = new Consultation();
        consultation.setCreatePerson(sysUser.getSysuserKey());
        //2.查询当前用户被要请的视频会诊
        List<Consultation> consultationList = consulTationService.getByConferee(consultation);
        
        map.put("readImageList", readImageList);
        map.put("videoList", videoList);
        map.put("consultationList", consultationList);
        return map;
    }
    
    /**
     * 获取响应会诊、会诊申请、读片会的条形数据
     * @param request
     * @param model
     * @return
     */
    @SuppressWarnings("deprecation")
    @RequestMapping(value="/getLineECharts.do",method=RequestMethod.POST)
    @ResponseBody
    public Object getLineECharts(HttpServletRequest request,Model model){
        log.info("getLineECharts");
        Map<String,Object> map = new HashMap<String,Object>();
        DbcontextHolder.setDbType("imagedb");
        
        HttpSession session = request.getSession();
        SysUser sysUser =  (SysUser) session.getAttribute("SysUser");
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        List<String> months = new ArrayList<String>();
        
        for(int i=0;i<now.getMonth()+1;i++){
            months.add(i+1+"月");
        }
        
        
        String beginDate = sdf.format(now)+"-"+"01-01";
        String endDate = sdf2.format(now);
        HashMap<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("beginDate", beginDate);
        queryMap.put("endDate", endDate);
        queryMap.put("createPerson", sysUser.getSysuserKey());
        queryMap.put("sysUserLogin", sysUser.getLoginName());
        List<DateAndCount> data = new ArrayList<DateAndCount>();
        
        
        List<String> daylist = MyDate.getMonthBetween(beginDate,endDate);
        //获取我的会诊申请数量
        List<HashMap<String,Object>> HuiZhenShenQingCount = countService.getHuiZhenShenQingMonCount(queryMap);
        //获取我的响应会诊数量
        List<HashMap<String,Object>> XiangYingHuiZhenCount = countService.getXiangYingHuiZhenMonCount(queryMap);
        //获取我的初审报告审核数量
        List<HashMap<String,Object>> ChuShenCount = countService.getChuShenMonCount(queryMap);
        //获取我的复审报告审核数量
        List<HashMap<String,Object>> FuShenCount = countService.getFuShenMonCount(queryMap);
        
        
        for(String d:daylist){
            DateAndCount dateAndCount = new DateAndCount();
            dateAndCount.setDate(d);
            for(HashMap<String,Object> m:HuiZhenShenQingCount){
                if(m.get("date").equals(d)){
                    dateAndCount.setHuiZhenShenQingCount(Integer.parseInt(m.get("count").toString()));
                }
            }
            for(HashMap<String,Object> m:XiangYingHuiZhenCount){
                if(m.get("date").equals(d)){
                    dateAndCount.setXiangYingHuiZhenCount(Integer.parseInt(m.get("count").toString()));
                }
            }
            for(HashMap<String,Object> m:ChuShenCount){
                if(m.get("date").equals(d)){
                    dateAndCount.setChuShenCount(Integer.parseInt(m.get("count").toString()));
                }
            }
            for(HashMap<String,Object> m:FuShenCount){
                if(m.get("date").equals(d)){
                    dateAndCount.setFuShenCount(Integer.parseInt(m.get("count").toString()));
                }
            }
            
            data.add(dateAndCount);
        }
        int[] huizhenshenqing = new int[data.size()];
        int[] xiangyinghuizhen = new int[data.size()];
        int[] chushen = new int[data.size()];
        int[] fushen = new int[data.size()];
        for(int i=0;i<data.size();i++){
            huizhenshenqing[i] = data.get(i).getHuiZhenShenQingCount();
            xiangyinghuizhen[i] = data.get(i).getXiangYingHuiZhenCount();
            chushen[i] = data.get(i).getChuShenCount();
            fushen[i] = data.get(i).getFuShenCount();
        }
        
        map.put("code", 0);
        map.put("months", months);
        map.put("huizhenshenqing", huizhenshenqing);
        map.put("xiangyinghuizhen", xiangyinghuizhen);
        map.put("chushen", chushen);
        map.put("fushen", fushen);
        return map;
    }
    /**
     * 获取响应会诊、会诊申请、读片会的条形数据
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value="/getPieECharts.do",method=RequestMethod.POST)
    @ResponseBody
    public Object getPieECharts(HttpServletRequest request,Model model){
        log.info("getPieECharts");
        Map<String,Object> map = new HashMap<String,Object>();
        DbcontextHolder.setDbType("imagedb");
        
        HttpSession session = request.getSession();
        SysUser sysUser =  (SysUser) session.getAttribute("SysUser");
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        
        
        String beginDate = sdf.format(now)+"-"+"01-01";
        String endDate = sdf2.format(now);
        
        HashMap<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("beginDate", beginDate);
        queryMap.put("endDate", endDate);
        queryMap.put("createPerson", sysUser.getSysuserKey());
        queryMap.put("sysUserLogin", sysUser.getLoginName());
        //创建的读片会数量
        int readcount = countService.getReadCountByCreater(queryMap);
        int videocount = countService.getVideoCountByCreater(queryMap);
        int quntaoluncount = countService.getQunCountByCreater(queryMap);
        int huizhenshenqingcount = countService.getHuiZhenShengQingCount(queryMap);
        int xiangyinghuizhencount = countService.getXiangYingHuiZhenCount(queryMap);
        int chushencount = countService.getChuShenCount(queryMap);
        int fushencount = countService.getFuShenCount(queryMap);
        
        map.put("code", 0);
        map.put("readcount", readcount);
        map.put("videocount", videocount);
        map.put("quntaoluncount", quntaoluncount);
        map.put("huizhenshenqingcount", huizhenshenqingcount);
        map.put("xiangyinghuizhencount", xiangyinghuizhencount);
        map.put("chushencount", chushencount);
        map.put("fushencount", fushencount);
        return map;
    }
}
