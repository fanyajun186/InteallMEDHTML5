package com.inteall.image.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.inteall.image.pojo.WPACSSeries;
import com.inteall.image.service.WPACSSeriesService;
import com.inteall.image.util.DbcontextHolder;

/**
 * @author 李进刚
 * @date 2018年3月21日
 * @version 1.0
 * @parameter
 */

@Controller
@RequestMapping("/WPACSSeries")
public class WPACSSeriesController {
  private static Logger log = Logger.getLogger(WPACSSeriesController.class.getName());
  
  @Resource
  private WPACSSeriesService wpacsSeriesService;
  
  @RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.GET)
  @ResponseBody
  public Object selectByPrimaryKey(@RequestParam String json, HttpServletRequest request, Model model)
      throws ParseException {
    log.info("selectByPrimaryKey");
    
    // 输入测试：http://localhost:8080/InteallMEDHTML5/WPACSSeries/selectByPrimaryKey?json='srsuid':'1.2.840.113619.2.312.6945.4266682.11921.1481846258.154'
    Map<String, String> ParamsJson = new Gson().fromJson("{" + json + "}", new TypeToken<HashMap<String, String>>() {
    }.getType());
    String srsuid = ParamsJson.get("srsuid");
    
    DbcontextHolder.setDbType("wpacs");
    WPACSSeries wpacsSeries = wpacsSeriesService.selectByPrimaryKey(srsuid);
    DbcontextHolder.setDbType("imagedb");
    
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("查询结果：", wpacsSeries);
    return map;
  }
  
  @RequestMapping(value = "/getMaxSrsuidByStuuid", method = RequestMethod.GET)
  @ResponseBody
  public Object getMaxSrsuidByStuuid(@RequestParam String json, HttpServletRequest request, Model model)
      throws ParseException {
    log.info("getMaxSrsuidByStuuid");
    
    // 输入测试：http://localhost:8080/InteallMEDHTML5/WPACSSeries/getMaxSrsuidByStuuid?json='stuuid':'1.2.840.113619.2.66.2218493297.16438171102090019.20000'
    Map<String, String> ParamsJson = new Gson().fromJson("{" + json + "}", new TypeToken<HashMap<String, String>>() {
    }.getType());
    String stuuid = ParamsJson.get("stuuid");
    
    DbcontextHolder.setDbType("wpacs");
    String MaxSrsid = wpacsSeriesService.getMaxSrsuidByStuuid(stuuid);
    DbcontextHolder.setDbType("imagedb");
    
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("最大序列号：", MaxSrsid);
    return map;
  }
  
  @RequestMapping(value = "/deleteByExcept", method = RequestMethod.GET)
  @ResponseBody
  public Object deleteByExcept(@RequestParam String json, HttpServletRequest request, Model model)
      throws ParseException {
    log.info("deleteByExcept");
    
    // 输入测试：http://localhost:8080/InteallMEDHTML5/WPACSSeries/deleteByExcept?json='stuuid':'2.25.7112547837067607422329631130653168569','srsuid':'2.25.153925993756581119722277070197669746376'
    Map<String, String> ParamsJson = new Gson().fromJson("{" + json + "}", new TypeToken<HashMap<String, String>>() {
    }.getType());
    String sStuuid = ParamsJson.get("stuuid");
    String sSrsuid = ParamsJson.get("srsuid");
    
    WPACSSeries wpacsSeries = new WPACSSeries();
    wpacsSeries.setStuuid(sStuuid);
    wpacsSeries.setSrsuid(sSrsuid);
    
    DbcontextHolder.setDbType("wpacs");
    Integer iResult = wpacsSeriesService.deleteByExcept(wpacsSeries);
    DbcontextHolder.setDbType("imagedb");
    
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("删除记录的数量：", iResult);
    map.put("删除记录的条件：", wpacsSeries);
    return map;
  }
}
