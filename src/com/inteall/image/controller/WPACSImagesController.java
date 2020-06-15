package com.inteall.image.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
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

import com.inteall.image.pojo.WPACSImages;
import com.inteall.image.service.WPACSImagesService;
import com.inteall.image.util.DbcontextHolder;

/**
 * @author 李进刚
 * @date 2018年3月21日
 * @version 1.0
 * @parameter
 */

@Controller
@RequestMapping("/WPACSImages")
public class WPACSImagesController {
  private static Logger log = Logger.getLogger(WPACSImagesController.class.getName());
  
  @Resource
  private WPACSImagesService wpacsImagesService;
  
  @RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.GET)
  @ResponseBody
  public Object selectByPrimaryKey(@RequestParam String json, HttpServletRequest request, Model model)
      throws ParseException {
    log.info("selectByPrimaryKey");
    
    // 输入测试：http://localhost:8080/InteallMEDHTML5/WPACSImages/selectByPrimaryKey?json='imguid':'1.2.40.0.13.1.1.192.168.8.28.20171130085925015.51148'
    Map<String, String> ParamsJson = new Gson().fromJson("{" + json + "}", new TypeToken<HashMap<String, String>>() {
    }.getType());
    String imguid = ParamsJson.get("imguid");
    
    DbcontextHolder.setDbType("wpacs");
    WPACSImages wpacsImages = wpacsImagesService.selectByPrimaryKey(imguid);
    DbcontextHolder.setDbType("imagedb");
    
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("查询结果：", wpacsImages);
    return map;
  }
  
  @RequestMapping(value = "/selectBySrsUID", method = RequestMethod.GET)
  @ResponseBody
  public Object selectBySrsUID(@RequestParam String json, HttpServletRequest request, Model model)
      throws ParseException {
    log.info("selectBySrsUID");
    
    // 输入测试：http://localhost:8080/InteallMEDHTML5/WPACSImages/selectBySrsUID?json='srsuid':'1.3.12.2.1107.5.1.4.85519.30000017112000420125000043425'
    Map<String, String> ParamsJson = new Gson().fromJson("{" + json + "}", new TypeToken<HashMap<String, String>>() {
    }.getType());
    String srsuid = ParamsJson.get("srsuid");
    
    DbcontextHolder.setDbType("wpacs");
    List<WPACSImages> wpacsImagesLists = wpacsImagesService.selectBySrsUID(srsuid);
    DbcontextHolder.setDbType("imagedb");
    
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("查询记录数量：", wpacsImagesLists.size());
    map.put("查询结果：", wpacsImagesLists);
    return map;
  }
  
  @RequestMapping(value = "/deleteByExcept", method = RequestMethod.GET)
  @ResponseBody
  public Object deleteByExcept(@RequestParam String json, HttpServletRequest request, Model model)
      throws ParseException {
    log.info("deleteByExcept");
    
    // 输入测试：http://localhost:8080/InteallMEDHTML5/WPACSImages/deleteByExcept?json='stuuid':'2.25.7112547837067607422329631130653168569','srsuid':'2.25.153925993756581119722277070197669746376'
    Map<String, String> ParamsJson = new Gson().fromJson("{" + json + "}", new TypeToken<HashMap<String, String>>() {
    }.getType());
    String sStuuid = ParamsJson.get("stuuid");
    String sSrsuid = ParamsJson.get("srsuid");
    
    WPACSImages wpacsImages = new WPACSImages();
    wpacsImages.setStuuid(sStuuid);
    wpacsImages.setSrsuid(sSrsuid);
    
    DbcontextHolder.setDbType("wpacs");
    Integer iResult = wpacsImagesService.deleteByExcept(wpacsImages);
    DbcontextHolder.setDbType("imagedb");
    
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("删除记录的数量：", iResult);
    map.put("删除记录的条件：", wpacsImages);
    return map;
  }
}
