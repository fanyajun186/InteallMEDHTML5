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
import com.inteall.image.pojo.WPACSStudys;
import com.inteall.image.service.WPACSStudysService;
import com.inteall.image.util.DbcontextHolder;

/**
 * @author 李进刚
 * @date 2018年3月21日
 * @version 1.0
 * @parameter
 */

@Controller
@RequestMapping("/WPACSStudys")
public class WPACSStudysController {
  private static Logger log = Logger.getLogger(WPACSStudysController.class.getName());
  
  @Resource
  private WPACSStudysService wpacsStudysService;
  
  @RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.GET)
  @ResponseBody
  public Object selectByPrimaryKey(@RequestParam String json, HttpServletRequest request, Model model)
      throws ParseException {
    log.info("selectByPrimaryKey");
    
    // 输入测试：http://localhost:8080/InteallMEDHTML5/WPACSStudys/selectByPrimaryKey?json='stuuid':'1.2.840.113619.2.66.2218493297.16438171102090019.20000'
    Map<String, String> ParamsJson = new Gson().fromJson("{" + json + "}", new TypeToken<HashMap<String, String>>() {
    }.getType());
    String stuuid = ParamsJson.get("stuuid");
    
    DbcontextHolder.setDbType("wpacs");
    WPACSStudys wpacsStudys = wpacsStudysService.selectByPrimaryKey(stuuid);
    DbcontextHolder.setDbType("imagedb");
    
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("查询结果：", wpacsStudys);
    return map;
  }
}
