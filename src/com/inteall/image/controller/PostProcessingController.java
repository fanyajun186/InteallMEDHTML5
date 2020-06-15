package com.inteall.image.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.inteall.image.pojo.PostProcessing;
import com.inteall.image.pojo.WPACSImages;
import com.inteall.image.pojo.WPACSSeries;
import com.inteall.image.service.PostProcessingService;
import com.inteall.image.service.WPACSImagesService;
import com.inteall.image.service.WPACSSeriesService;
import com.inteall.image.util.DbcontextHolder;
import com.inteall.image.util.PostProcessingUtil;
import com.inteall.image.util.UUid;

/**
 * @author 李进刚
 * @date 2018年3月19日
 * @version 1.0
 * @parameter
 */

@Controller
@RequestMapping("/postprocessing")
public class PostProcessingController {
  private static Logger         log = Logger.getLogger(PostProcessingController.class.getName());
  
  @Resource
  private PostProcessingService postProcessingService;
  @Resource
  private WPACSSeriesService wpacsSeriesService;
  @Resource
  private WPACSImagesService wpacsImagesService;
  
  @RequestMapping(value = "/deleteByPrimaryKey.do", method = RequestMethod.GET)
  @ResponseBody
  public Object deleteByPrimaryKey(@RequestParam String json, HttpServletRequest request, Model model)
      throws ParseException {
    log.info("deleteByPrimaryKey");
    
    // 输入测试：
    Map<String, String> ParamsJson = new Gson().fromJson("{" + json + "}", new TypeToken<HashMap<String, String>>() {
    }.getType());
    
    String logid = ParamsJson.get("logid");
    int row = postProcessingService.deleteByPrimaryKey(logid);
    
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("删除记录数：", row);
    return map;
  }
  
  @RequestMapping(value = "/insert.do", method = RequestMethod.GET)
  @ResponseBody
  public Object insert(@RequestParam String json, HttpServletRequest request, Model model) throws ParseException {
    log.info("insert");
    
    PostProcessing postProcessing = new PostProcessing();
    
    // 输入测试：http://localhost:8080/postprocessing/processinglog/insert?json='stuuid':'stuuid','srsuid':'srsuid','imguid':'imguid',
    // 'command':'command','param01':'param01','param02':'param02','param03':'param03','param04':'param04','param05':'param05',
    // 'param06':'param06','param07':'param07','param08':'param08','param09':'param09','param10':'param10','param11':'param11',
    // 'param12':'param12','param13':'param13','param14':'param14','param15':'param15','param16':'param16','exefile':'exefile',
    // 'imagefile':'imagefile','outputfile':'outputfile','result':'1','remark1':'remark1','remark2':'remark2','remark3':'remark3',
    // 'remark4':'remark4','remark5':'remark5',''remark6:'remark6'
    Map<String, String> ParamsJson = new Gson().fromJson("{" + json + "}", new TypeToken<HashMap<String, String>>() {
    }.getType());
    postProcessing.setLogid(UUid.getUUID());
    postProcessing.setCreatetime(new Date());
    postProcessing.setStuuid(ParamsJson.get("stuuid"));
    postProcessing.setSrsuid(ParamsJson.get("srsuid"));
    postProcessing.setImguid(ParamsJson.get("imguid"));
    postProcessing.setCommand(ParamsJson.get("command"));
    postProcessing.setParam01(ParamsJson.get("param01"));
    postProcessing.setParam02(ParamsJson.get("param02"));
    postProcessing.setParam03(ParamsJson.get("param03"));
    postProcessing.setParam04(ParamsJson.get("param04"));
    postProcessing.setParam05(ParamsJson.get("param05"));
    postProcessing.setParam06(ParamsJson.get("param06"));
    postProcessing.setParam07(ParamsJson.get("param07"));
    postProcessing.setParam08(ParamsJson.get("param08"));
    postProcessing.setParam09(ParamsJson.get("param09"));
    postProcessing.setParam10(ParamsJson.get("param10"));
    postProcessing.setParam11(ParamsJson.get("param11"));
    postProcessing.setParam12(ParamsJson.get("param12"));
    postProcessing.setParam13(ParamsJson.get("param13"));
    postProcessing.setParam14(ParamsJson.get("param14"));
    postProcessing.setParam15(ParamsJson.get("param15"));
    postProcessing.setParam16(ParamsJson.get("param16"));
    postProcessing.setExefile(ParamsJson.get("exefile"));
    postProcessing.setImagefile(ParamsJson.get("imagefile"));
    postProcessing.setOutputfile(ParamsJson.get("outputfile"));
    postProcessing.setResult(ParamsJson.get("result"));
    postProcessing.setRemark1(ParamsJson.get("remark1"));
    postProcessing.setRemark2(ParamsJson.get("remark2"));
    postProcessing.setRemark3(ParamsJson.get("remark3"));
    postProcessing.setRemark4(ParamsJson.get("remark4"));
    postProcessing.setRemark5(ParamsJson.get("remark5"));
    postProcessing.setRemark6(ParamsJson.get("remark6"));
    
    int row = postProcessingService.insert(postProcessing);
    
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("增加记录数：", row);
    return map;
  }
  
  @RequestMapping(value = "/insertSelective.do", method = RequestMethod.GET)
  @ResponseBody
  public Object insertSelective(@RequestParam String json, HttpServletRequest request, Model model)
      throws ParseException {
    log.info("insertSelective");
    
    PostProcessing postProcessing = new PostProcessing();
    
    // 输入测试：
    Map<String, String> ParamsJson = new Gson().fromJson("{" + json + "}", new TypeToken<HashMap<String, String>>() {
    }.getType());
    postProcessing.setLogid(UUid.getUUID());
    postProcessing.setCreatetime(new Date());
    postProcessing.setStuuid(ParamsJson.get("stuuid"));
    postProcessing.setSrsuid(ParamsJson.get("srsuid"));
    postProcessing.setImguid(ParamsJson.get("imguid"));
    postProcessing.setCommand(ParamsJson.get("command"));
    postProcessing.setParam01(ParamsJson.get("param01"));
    postProcessing.setParam02(ParamsJson.get("param02"));
    postProcessing.setParam03(ParamsJson.get("param03"));
    postProcessing.setParam04(ParamsJson.get("param04"));
    postProcessing.setParam05(ParamsJson.get("param05"));
    postProcessing.setParam06(ParamsJson.get("param06"));
    postProcessing.setParam07(ParamsJson.get("param07"));
    postProcessing.setParam08(ParamsJson.get("param08"));
    postProcessing.setParam09(ParamsJson.get("param09"));
    postProcessing.setParam10(ParamsJson.get("param10"));
    postProcessing.setParam11(ParamsJson.get("param11"));
    postProcessing.setParam12(ParamsJson.get("param12"));
    postProcessing.setParam13(ParamsJson.get("param13"));
    postProcessing.setParam14(ParamsJson.get("param14"));
    postProcessing.setParam15(ParamsJson.get("param15"));
    postProcessing.setParam16(ParamsJson.get("param16"));
    postProcessing.setExefile(ParamsJson.get("exefile"));
    postProcessing.setImagefile(ParamsJson.get("imagefile"));
    postProcessing.setOutputfile(ParamsJson.get("outputfile"));
    postProcessing.setResult(ParamsJson.get("result"));
    postProcessing.setRemark1(ParamsJson.get("remark1"));
    postProcessing.setRemark2(ParamsJson.get("remark2"));
    postProcessing.setRemark3(ParamsJson.get("remark3"));
    postProcessing.setRemark4(ParamsJson.get("remark4"));
    postProcessing.setRemark5(ParamsJson.get("remark5"));
    postProcessing.setRemark6(ParamsJson.get("remark6"));
    
    int row = postProcessingService.insertSelective(postProcessing);
    
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("增加记录数：", row);
    return map;
  }
  
  @RequestMapping(value = "/selectByPrimaryKey.do", method = RequestMethod.GET)
  @ResponseBody
  public Object selectByPrimaryKey(@RequestParam String json, HttpServletRequest request, Model model)
      throws ParseException {
    log.info("selectByPrimaryKey");
    
    // 输入测试：http://localhost:8080/InteallMEDHTML5/postprocessing/selectByPrimaryKey?json='logid':'12345678901234567890123456789012'
    Map<String, String> ParamsJson = new Gson().fromJson("{" + json + "}", new TypeToken<HashMap<String, String>>() {
    }.getType());
    
    String logid = ParamsJson.get("logid");
    log.info("logid=" + logid);
    
    PostProcessing postProcessing = postProcessingService.selectByPrimaryKey(logid);
    
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("查询结果：", postProcessing);
    return map;
  }
  
  @RequestMapping(value = "/updateByPrimaryKeySelective.do", method = RequestMethod.GET)
  @ResponseBody
  public Object updateByPrimaryKeySelective(@RequestParam String json, HttpServletRequest request, Model model)
      throws ParseException {
    log.info("updateByPrimaryKeySelective");
    
    PostProcessing postProcessing = new PostProcessing();
    
    // 输入测试：
    Map<String, String> ParamsJson = new Gson().fromJson("{" + json + "}", new TypeToken<HashMap<String, String>>() {
    }.getType());
    postProcessing.setLogid(ParamsJson.get("logid"));
    postProcessing.setCreatetime(new Date());
    postProcessing.setStuuid(ParamsJson.get("stuuid"));
    postProcessing.setSrsuid(ParamsJson.get("srsuid"));
    postProcessing.setImguid(ParamsJson.get("imguid"));
    postProcessing.setCommand(ParamsJson.get("command"));
    postProcessing.setParam01(ParamsJson.get("param01"));
    postProcessing.setParam02(ParamsJson.get("param02"));
    postProcessing.setParam03(ParamsJson.get("param03"));
    postProcessing.setParam04(ParamsJson.get("param04"));
    postProcessing.setParam05(ParamsJson.get("param05"));
    postProcessing.setParam06(ParamsJson.get("param06"));
    postProcessing.setParam07(ParamsJson.get("param07"));
    postProcessing.setParam08(ParamsJson.get("param08"));
    postProcessing.setParam09(ParamsJson.get("param09"));
    postProcessing.setParam10(ParamsJson.get("param10"));
    postProcessing.setParam11(ParamsJson.get("param11"));
    postProcessing.setParam12(ParamsJson.get("param12"));
    postProcessing.setParam13(ParamsJson.get("param13"));
    postProcessing.setParam14(ParamsJson.get("param14"));
    postProcessing.setParam15(ParamsJson.get("param15"));
    postProcessing.setParam16(ParamsJson.get("param16"));
    postProcessing.setExefile(ParamsJson.get("exefile"));
    postProcessing.setImagefile(ParamsJson.get("imagefile"));
    postProcessing.setOutputfile(ParamsJson.get("outputfile"));
    postProcessing.setResult(ParamsJson.get("result"));
    postProcessing.setRemark1(ParamsJson.get("remark1"));
    postProcessing.setRemark2(ParamsJson.get("remark2"));
    postProcessing.setRemark3(ParamsJson.get("remark3"));
    postProcessing.setRemark4(ParamsJson.get("remark4"));
    postProcessing.setRemark5(ParamsJson.get("remark5"));
    postProcessing.setRemark6(ParamsJson.get("remark6"));
    
    int row = postProcessingService.updateByPrimaryKeySelective(postProcessing);
    
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("修改记录数：", row);
    return map;
  }
  
  @RequestMapping(value = "/updateByPrimaryKey.do", method = RequestMethod.GET)
  @ResponseBody
  public Object updateByPrimaryKey(@RequestParam String json, HttpServletRequest request, Model model)
      throws ParseException {
    log.info("updateByPrimaryKey");
    
    PostProcessing postProcessing = new PostProcessing();
    
    // 输入测试：
    Map<String, String> ParamsJson = new Gson().fromJson("{" + json + "}", new TypeToken<HashMap<String, String>>() {
    }.getType());
    postProcessing.setLogid(ParamsJson.get("logid"));
    postProcessing.setCreatetime(new Date());
    postProcessing.setStuuid(ParamsJson.get("stuuid"));
    postProcessing.setSrsuid(ParamsJson.get("srsuid"));
    postProcessing.setImguid(ParamsJson.get("imguid"));
    postProcessing.setCommand(ParamsJson.get("command"));
    postProcessing.setParam01(ParamsJson.get("param01"));
    postProcessing.setParam02(ParamsJson.get("param02"));
    postProcessing.setParam03(ParamsJson.get("param03"));
    postProcessing.setParam04(ParamsJson.get("param04"));
    postProcessing.setParam05(ParamsJson.get("param05"));
    postProcessing.setParam06(ParamsJson.get("param06"));
    postProcessing.setParam07(ParamsJson.get("param07"));
    postProcessing.setParam08(ParamsJson.get("param08"));
    postProcessing.setParam09(ParamsJson.get("param09"));
    postProcessing.setParam10(ParamsJson.get("param10"));
    postProcessing.setParam11(ParamsJson.get("param11"));
    postProcessing.setParam12(ParamsJson.get("param12"));
    postProcessing.setParam13(ParamsJson.get("param13"));
    postProcessing.setParam14(ParamsJson.get("param14"));
    postProcessing.setParam15(ParamsJson.get("param15"));
    postProcessing.setParam16(ParamsJson.get("param16"));
    postProcessing.setExefile(ParamsJson.get("exefile"));
    postProcessing.setImagefile(ParamsJson.get("imagefile"));
    postProcessing.setOutputfile(ParamsJson.get("outputfile"));
    postProcessing.setResult(ParamsJson.get("result"));
    postProcessing.setRemark1(ParamsJson.get("remark1"));
    postProcessing.setRemark2(ParamsJson.get("remark2"));
    postProcessing.setRemark3(ParamsJson.get("remark3"));
    postProcessing.setRemark4(ParamsJson.get("remark4"));
    postProcessing.setRemark5(ParamsJson.get("remark5"));
    postProcessing.setRemark6(ParamsJson.get("remark6"));
    
    int row = postProcessingService.updateByPrimaryKey(postProcessing);
    
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("修改记录数：", row);
    return map;
  }
  /**
   * @author 李进刚
   * @date 2018年3月21日
   * @version 1.0
   * @function 增加一个后处理病历，过程说明：根据检查号，查询最大的序列号，根据序列号，查询影像文件列表，获得文件目录，循环调用后处理程序，
   * @parameter srsuid
   * @return 0-成功，1-失败
   * @throws ParseException
   */
  @RequestMapping(value = "/addrecord.do", method = RequestMethod.GET)
  @ResponseBody
  public Object addrecord(@RequestParam String json, HttpServletRequest request, Model model)
      throws ParseException {
    log.info("addrecord");
    
    // 输入测试：http://localhost:8080/InteallMEDHTML5/postprocessing/addrecord?json='stuuid':'1.3.12.2.1107.5.1.4.85519.30000017112000395439000000457','srsuid':'1.3.12.2.1107.5.1.4.85519.30000017112000420125000043425'
    log.info("json=" + json);
    Map<String, String> ParamsJson = new Gson().fromJson("{" + json + "}", new TypeToken<HashMap<String, String>>() {
    }.getType());
    // 获得传入的参数
    String stuuid = ParamsJson.get("stuuid");
    String srsuid = ParamsJson.get("srsuid");
    log.info("stuuid=" + stuuid);
    log.info("srsuid=" + srsuid);
    
    // 切换数据库
    DbcontextHolder.setDbType("wpacs");
    
    // 获得最大的序列号
    String MaxSrsid = wpacsSeriesService.getMaxSrsuidByStuuid(stuuid);
    log.info("MaxSrsid=" + MaxSrsid);
    // 计算新的序列号
    MaxSrsid = Integer.toString(Integer.parseInt(MaxSrsid) + 1);
    log.info("MaxSrsid + 1 =" + MaxSrsid);
    
    // 获得序列下的所有图像
    List<WPACSImages> wpacsImagesLists = wpacsImagesService.selectBySrsUID(srsuid);
    log.info(wpacsImagesLists);
    // 切换数据库
    DbcontextHolder.setDbType("imagedb");
    
    // 循环处理所有的图像文件
    String sInPath;
    String sInFile;    
    String sOutPath;
    String sOutFile;
    // 定义数据表
    //PostProcessing postProcessing = new PostProcessing();   
    for (int i = 0; i < wpacsImagesLists.size(); i++) {
      sInPath = "C:\\WPACS\\recv_images\\";
      sInFile = wpacsImagesLists.get(i).getImguid();      
      sOutPath = "C:\\PostProcessing\\outfiles\\";
      sOutFile = "file" + Integer.toString(i) + ".txt";
      log.info("sPathName:" + sInPath);
      log.info("sFileName:" + sInFile);
      log.info("sOutPath:" + sOutPath);
      log.info("sOutFile:" + sOutFile);
      // Integer iResult = PostProcessingUtil.runPostProcessingProgram(sInPath, sInFile, MaxSrsid, sOutPath, sOutFile);
      // log.info("iResult:" + iResult.toString());
      
//      postProcessing.setLogid(UUid.getUUID());
//      postProcessing.setCreatetime(new Date());
//      postProcessing.setStuuid(stuuid);
//      postProcessing.setSrsuid(srsuid);
//      postProcessing.setImguid("");
//      postProcessing.setCommand("function1");
//      postProcessing.setParam01("param01");
//      postProcessing.setParam02("param02");
//      postProcessing.setParam03("param03");
//      postProcessing.setParam04("param04");
//      postProcessing.setParam05("param05");
//      postProcessing.setParam06("param06");
//      postProcessing.setParam07("param07");
//      postProcessing.setParam08("param08");
//      postProcessing.setParam09("param09");
//      postProcessing.setParam10("param10");
//      postProcessing.setParam11("param11");
//      postProcessing.setParam12("param12");
//      postProcessing.setParam13("param13");
//      postProcessing.setParam14("param14");
//      postProcessing.setParam15("param15");
//      postProcessing.setParam16("param16");
//      postProcessing.setExefile("function1.exe");
//      postProcessing.setImagefile(sPathName + "" + sFileName);
//      postProcessing.setOutputfile(sOutFile);
//      postProcessing.setResult(sResult);
//      postProcessing.setRemark1("remark1");
//      postProcessing.setRemark2("remark2");
//      postProcessing.setRemark3("remark3");
//      postProcessing.setRemark4("remark4");
//      postProcessing.setRemark5("remark5");
//      postProcessing.setRemark6("remark6");
      
//      postProcessingService.insert(postProcessing);
    }
    // 前台输出
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("MaxSrsid：", MaxSrsid);
    map.put("文件列表：", wpacsImagesLists);
    return map;
  }
  /**
   * @author 李进刚
   * @date 2018年3月28日
   * @version 1.0
   * @function 后处理菜单的第一个功能：简单弥散权重分析功能
   * @parameter stuuid，srsuid，imguid，param1(检查号，序列号，图像号，降噪参数)
   * @return 0-成功，1-参数错误，2-序列为空，3-后处理过程失败，9-其它错误
   * @throws ParseException
   */
  @RequestMapping(value = "/ppfunction1.do", method = RequestMethod.POST)
  @ResponseBody
  public String ppfunction1(HttpServletRequest request, HttpServletResponse response) {
    log.info("ppfunction1");    
    // 获取前端传递的参数
    String sStuuid = request.getParameter("stuuid");
	String sSrsuid = request.getParameter("srsuid");
	String sAppid = request.getParameter("sappid");
	String sParam1 = request.getParameter("param1");
	log.info("sStuuid:" + sStuuid);
	log.info("sSrsuid:" + sSrsuid);
	log.info("sAppid:" + sAppid);
	log.info("sParam1:" + sParam1);
	// 前端js调用的许可和配置
	response.setHeader("Access-Control-Allow-Origin", "*");
	response.setHeader("Access-Control-Allow-Methods", "GET,POST");
	response.setCharacterEncoding("utf-8");
    
	String sResult = "{\"flag\":\"0\",\"Message\":\"success.\"}";
	if (PostProcessingUtil.isInteger(sParam1)) {
	  // 判断降噪参数是整数
	  Integer iParam1 = Integer.valueOf(sParam1);
	  if (iParam1 >= 0 && iParam1 <= 99) {
		//降噪参数在范围内
	    DbcontextHolder.setDbType("wpacs");
	    log.info("切换数据库到wpacs");
	    
	    // 删除序列表中原有的后处理序列
	    WPACSSeries wpacsSeries = new WPACSSeries();
	    wpacsSeries.setStuuid(sStuuid);
	    wpacsSeries.setSrsuid(sSrsuid);
	    Integer iResult = wpacsSeriesService.deleteByExcept(wpacsSeries);
	    log.info("删除序列表中原有的后处理序列：" + iResult.toString());
	    // 删除图像表中原有的后处理图像
	    WPACSImages wpacsImages = new WPACSImages();
	    wpacsImages.setStuuid(sStuuid);
	    wpacsImages.setSrsuid(sSrsuid);
	    iResult = wpacsImagesService.deleteByExcept(wpacsImages);
	    log.info("删除图像表中原有的后处理图像：" + iResult.toString());
	    
	    // 获得序列下的所有图像列表
	    List<WPACSImages> wpacsImagesLists = wpacsImagesService.selectBySrsUID(sSrsuid);
	    log.info("图像表中该序列下图像的数量：" + wpacsImagesLists.size());
	    // log.info(wpacsImagesLists);
	    
	    DbcontextHolder.setDbType("imagedb");
	    log.info("切换数据库到imagedb");
	    
	    String sBack;
	    if (wpacsImagesLists.size() > 0) {
	      log.info("callPostProcessing1");
	      // log.info("wpacsImagesLists:" + wpacsImagesLists);
	      log.info("sAppid:" + sAppid);
	      log.info("sParam1:" + sParam1);
	      // 影像序列不为空，调用后处理程序
	      sBack = PostProcessingUtil.callPostProcessing1(wpacsImagesLists, sAppid, sParam1);
	      
	      // 后处理程序的返回值 {"postprocessing return":" + sBack + "}
	      sResult = "{\"postprocessing return\":\"" + sBack + "\"}";
	    } else {
	      // 序列为空
	      sResult = "{\"flag\":\"2\",\"Message\":\"series is null.\"}";
	    }
	  } else {
		// 降噪参数不在范围内
		sResult = "{\"flag\":\"1\",\"Message\":\"parameters range error.\"}";
	  }
	} else {
	  // 降噪参数不是整数
	  sResult = "{\"flag\":\"1\",\"Message\":\"parameters 2 error.\"}";
	}
    // 给前台的反馈
    return sResult;
  }
}
