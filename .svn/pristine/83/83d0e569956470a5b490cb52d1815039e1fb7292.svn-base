package com.inteall.image.controller;

import java.util.ArrayList;
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

import com.inteall.image.pojo.DateAndCount;
import com.inteall.image.pojo.SysUser;
import com.inteall.image.service.CountService;
import com.inteall.image.util.DbcontextHolder;
import com.inteall.image.util.MyDate;

/**
 * @author 韩明君
 * @date 2018年5月22日 下午2:54:34
 * @version 1.0 
 * @parameter 
 */
@Controller
@RequestMapping("/count")
public class CountController {
    private static Logger log = Logger.getLogger(CountController.class.getName());
    @Resource
    private CountService countService;
    
    /**
     * 初审报告
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value="/getCountByUser.do",method=RequestMethod.POST)
    @ResponseBody
    public Object getCountByUser(HttpServletRequest request,Model model){
        log.info("getCountByUser");
        
        HttpSession session = request.getSession();
        SysUser sysUser =  (SysUser) session.getAttribute("SysUser");
        
        String selecttime = request.getParameter("selecttime");
        String dayOrMon = request.getParameter("dayOrMon");
        String beginDate = null;
        String endDate = null;
        if(selecttime!=null){
            String[] time = selecttime.split("~");
            
            if(time.length==2){
                beginDate = time[0].trim();
                endDate = time[1].trim();
            }
        }
        HashMap<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("beginDate", beginDate);
        queryMap.put("endDate", endDate);
        queryMap.put("createPerson", sysUser.getSysuserKey());
        queryMap.put("sysUserLogin", sysUser.getLoginName());
        Map<String,Object> map = new HashMap<String, Object>();
        DbcontextHolder.setDbType("imagedb");
        
        
        List<DateAndCount> data = new ArrayList<DateAndCount>();
        if(dayOrMon.equals("day")){
            List<String> daylist = MyDate.getEveryday(beginDate,endDate);
            //获取我的会诊申请数量
            List<HashMap<String,Object>> HuiZhenShenQingCount = countService.getHuiZhenShenQingDayCount(queryMap);
            //获取我的响应会诊数量
            List<HashMap<String,Object>> XiangYingHuiZhenCount = countService.getXiangYingHuiZhenDayCount(queryMap);
            //获取我的初审报告审核数量
            List<HashMap<String,Object>> ChuShenCount = countService.getChuShenDayCount(queryMap);
            //获取我的复审报告审核数量
            List<HashMap<String,Object>> FuShenCount = countService.getFuShenDayCount(queryMap);
            //获取我创建的读片会数量
            List<HashMap<String,Object>> CreateReadImageCount = countService.getCreateReadImageDayCount(queryMap);
            //获取我参与的读片会数量
            List<HashMap<String,Object>> EnterReadImageCount = countService.getEnterReadImageDayCount(queryMap);
            //获取我创建的视频教学数量
            List<HashMap<String,Object>> CreateVedioCount = countService.getCreateVideoDayCount(queryMap);
            //获取我参与的视频教学数量
            List<HashMap<String,Object>> EnterVedioCount = countService.getEnterVideoDayCount(queryMap);
            //获取我分享群讨论数量
            List<HashMap<String,Object>> QunTaoLunCount = countService.getQunTaoLunDayCount(queryMap);
            
            
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
                for(HashMap<String,Object> m:CreateReadImageCount){
                    if(m.get("date").equals(d)){
                        dateAndCount.setCreateReadImageCount(Integer.parseInt(m.get("count").toString()));
                    }
                }
                for(HashMap<String,Object> m:EnterReadImageCount){
                    if(m.get("date").equals(d)){
                        dateAndCount.setEnterReadImageCount(Integer.parseInt(m.get("count").toString()));
                    }
                }
                for(HashMap<String,Object> m:CreateVedioCount){
                    if(m.get("date").equals(d)){
                        dateAndCount.setCreateVideoCount(Integer.parseInt(m.get("count").toString()));
                    }
                }
                for(HashMap<String,Object> m:EnterVedioCount){
                    if(m.get("date").equals(d)){
                        dateAndCount.setEnterVideoCount(Integer.parseInt(m.get("count").toString()));
                    }
                }
                for(HashMap<String,Object> m:QunTaoLunCount){
                    if(m.get("date").equals(d)){
                        dateAndCount.setQunTaoLunCount(Integer.parseInt(m.get("count").toString()));
                    }
                }
                data.add(dateAndCount);
            }
            
        }else if(dayOrMon.equals("mon")){
            List<String> daylist = MyDate.getMonthBetween(beginDate,endDate);
            //获取我的会诊申请数量
            List<HashMap<String,Object>> HuiZhenShenQingCount = countService.getHuiZhenShenQingMonCount(queryMap);
            //获取我的响应会诊数量
            List<HashMap<String,Object>> XiangYingHuiZhenCount = countService.getXiangYingHuiZhenMonCount(queryMap);
            //获取我的初审报告审核数量
            List<HashMap<String,Object>> ChuShenCount = countService.getChuShenMonCount(queryMap);
            //获取我的复审报告审核数量
            List<HashMap<String,Object>> FuShenCount = countService.getFuShenMonCount(queryMap);
            //获取我创建的读片会数量
            List<HashMap<String,Object>> CreateReadImageCount = countService.getCreateReadImageMonCount(queryMap);
            //获取我参与的读片会数量
            List<HashMap<String,Object>> EnterReadImageCount = countService.getEnterReadImageMonCount(queryMap);
            //获取我创建的视频教学数量
            List<HashMap<String,Object>> CreateVedioCount = countService.getCreateVideoMonCount(queryMap);
            //获取我参与的视频教学数量
            List<HashMap<String,Object>> EnterVedioCount = countService.getEnterVideoMonCount(queryMap);
            //获取我分享群讨论数量
            List<HashMap<String,Object>> QunTaoLunCount = countService.getQunTaoLunMonCount(queryMap);
            
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
                for(HashMap<String,Object> m:CreateReadImageCount){
                    if(m.get("date").equals(d)){
                        dateAndCount.setCreateReadImageCount(Integer.parseInt(m.get("count").toString()));
                    }
                }
                for(HashMap<String,Object> m:EnterReadImageCount){
                    if(m.get("date").equals(d)){
                        dateAndCount.setEnterReadImageCount(Integer.parseInt(m.get("count").toString()));
                    }
                }
                for(HashMap<String,Object> m:CreateVedioCount){
                    if(m.get("date").equals(d)){
                        dateAndCount.setCreateVideoCount(Integer.parseInt(m.get("count").toString()));
                    }
                }
                for(HashMap<String,Object> m:EnterVedioCount){
                    if(m.get("date").equals(d)){
                        dateAndCount.setEnterVideoCount(Integer.parseInt(m.get("count").toString()));
                    }
                }
                for(HashMap<String,Object> m:QunTaoLunCount){
                    if(m.get("date").equals(d)){
                        dateAndCount.setQunTaoLunCount(Integer.parseInt(m.get("count").toString()));
                    }
                }
                data.add(dateAndCount);
            }
        }
        map.put("code", 0);
        map.put("data", data);
        map.put("count", data.size());
        return map;
    }

}
