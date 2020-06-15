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
import com.inteall.image.pojo.ReadImage_Conferee;
import com.inteall.image.service.ReadImage_ConfereeService;
import com.inteall.image.util.DbcontextHolder;
import com.inteall.image.util.UUid;

/**
 * @author 李进刚
 * @date 2018年3月9日 上午8:59:42
 * @version 1.0 
 * @parameter 
 */

@Controller
@RequestMapping("/readimage_conferee")
public class ReadImage_ConfereeController {
  private static Logger log = Logger.getLogger(ReadImage_ConfereeController.class.getName());
  @Resource
  private ReadImage_ConfereeService readImage_ConfereeService;
  
  /**
   * 增加一位读片会与会人
   * @param 
   * @return
   */
  @RequestMapping(value="/save.do", method=RequestMethod.GET)
  @ResponseBody
  public Object save(@RequestParam String json, HttpServletRequest request, Model model) throws ParseException {
	log.info("save");
	// http://localhost:8080/InteallMEDHTML5/readimage_conferee/save?json='create_person':'create_person','sysuser_key':'sysuser_key'
	Map<String, String> saveParamMap = new Gson().fromJson("{" + json + "}", new TypeToken<HashMap<String, String>>(){}.getType());
	DbcontextHolder.setDbType("imagedb");
	ReadImage_Conferee readImage_Conferee = new ReadImage_Conferee();
	readImage_Conferee.setReadimage_conferee_key(UUid.getUUID());
	readImage_Conferee.setReadimage_key(saveParamMap.get("readimage_key"));
	readImage_Conferee.setSysuser_key(saveParamMap.get("sysuser_key"));
	readImage_Conferee.setReadimage_remark1(null);
	readImage_Conferee.setReadimage_remark2(null);
	readImage_Conferee.setReadimage_remark3(null);
	
	int row = readImage_ConfereeService.save(readImage_Conferee);
	
	Map<String,Object> map = new HashMap<String,Object>();
	map.put("增加读片会与会人的数量", row);
	map.put("增加读片会与会人信息", readImage_Conferee);
	return map;
  }
  
  /**
   * 删除一位读片会与会人
   * @param 
   * @return
   */
  @RequestMapping(value="/deleteById.do", method=RequestMethod.GET)
  @ResponseBody
  public Object deleteById(@RequestParam String json, HttpServletRequest request, Model model) throws ParseException {
	log.info("deleteById");
	// http://localhost:8080/InteallMEDHTML5/readimage_conferee/deleteById?json='readimage_conferee_key':'readimage_conferee_key'
	Map<String, String> deleteParamMap = new Gson().fromJson("{" + json + "}", new TypeToken<HashMap<String, String>>(){}.getType());
	DbcontextHolder.setDbType("imagedb");
	ReadImage_Conferee readImage_Conferee = new ReadImage_Conferee();
	readImage_Conferee.setReadimage_conferee_key(deleteParamMap.get("readimage_conferee_key"));
	
	int row = readImage_ConfereeService.deleteById(readImage_Conferee);
	
	Map<String,Object> map = new HashMap<String,Object>();
	map.put("删除读片会与会人的数量", row);
	map.put("删除读片会与会人信息", readImage_Conferee);
	return map;
  }
  
  /**
   * 查询一位读片会与会人
   * @param 
   * @return
   */
  @RequestMapping(value="/getById.do", method=RequestMethod.GET)
  @ResponseBody
  public Object getById(@RequestParam String json, HttpServletRequest request, Model model) throws ParseException {
	log.info("getById");
	// http://localhost:8080/InteallMEDHTML5/readimage_conferee/getById?json='readimage_conferee_key':'readimage_conferee_key'
	Map<String, String> getParamMap = new Gson().fromJson("{" + json + "}", new TypeToken<HashMap<String, String>>(){}.getType());
	DbcontextHolder.setDbType("imagedb");
	ReadImage_Conferee readImage_Conferee = new ReadImage_Conferee();
	readImage_Conferee.setReadimage_conferee_key(getParamMap.get("readimage_conferee_key"));
	
	ReadImage_Conferee ReadImage_ConfereeResult = readImage_ConfereeService.getById(readImage_Conferee);
	
	Map<String,Object> map = new HashMap<String,Object>();
	map.put("查询读片会与会人信息", ReadImage_ConfereeResult);
	return map;
  }
  
}
