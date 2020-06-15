package com.inteall.image.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.inteall.image.pojo.Accessory;
import com.inteall.image.service.AccessoryService;
import com.inteall.image.util.CustomMultipartResolver;
import com.inteall.image.util.DbcontextHolder;
import com.inteall.image.util.UUid;
import com.inteall.image.util.Util;


/** 
* @author 韩明君  
* @date 创建时间：2018年2月7日 下午1:17:38 
* @version 1.0 
* @parameter  
*/
@Controller
@RequestMapping("/accessory")
public class AccessoryController {
	private static Logger log = Logger.getLogger(AccessoryController.class.getName());
	@Resource
	private AccessoryService accessoryService;
	
	/**
	 * 根据附件的id查询附件信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/getaccessoryById.do",method=RequestMethod.GET)
	@ResponseBody
	public Object getaccessoryById(@RequestParam String id){
		log.info("getaccessoryById");
		DbcontextHolder.setDbType("imagedb");
		List<Accessory> getaccessoryByIdList = accessoryService.getById(id);
		return getaccessoryByIdList;
	}
	/**
	 * 查询所有附件信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/getaccessoryAll.do",method=RequestMethod.GET)
	@ResponseBody
	public Object getaccessoryAll(HttpServletRequest request,Model model){
		log.info("getaccessoryAll");
		Accessory accessory = new Accessory();
		DbcontextHolder.setDbType("imagedb");
		List<Accessory> accessoryList = accessoryService.getAll(accessory);
		return accessoryList;
	}
	/**
	 * 根据附件id删除附件
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delaccessoryById.do",method=RequestMethod.POST)
	@ResponseBody
	public Object delById(@RequestParam String id){
		log.info("delaccessoryById");
		Map<String,Object> map = new HashMap<String,Object>();
		DbcontextHolder.setDbType("imagedb");
		
		int row = accessoryService.delById(id);
		map.put("删除了的行数：", row);
		return map;
	}
	
	@RequestMapping(value = "/getProgress.do",method=RequestMethod.GET)    
    @ResponseBody    
    public Object initCreateInfo(HttpServletRequest request) {   
        Map<String, Object> map = new HashMap<String, Object>();
        CustomMultipartResolver query = new CustomMultipartResolver();  
        
        map.put("progress", query.getProgress("progress"));
        return map;    
    } 
	/**
	 * 新增附件信息
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/saveaccessory.do",method=RequestMethod.POST)
	@ResponseBody
	public Object saveaccessory(@RequestParam String stuuid,HttpServletResponse response,MultipartHttpServletRequest request,Model model){
		log.info("saveaccessory");
		Map<String,Object> map = new HashMap<String,Object>();
        DbcontextHolder.setDbType("imagedb");
        Properties prop = Util.readData("path.properties");
        
        SimpleDateFormat ymd = new SimpleDateFormat("yyyyMMdd");
        String savePath = prop.getProperty("accessoryPath").trim()+ymd.format(new Date())+"/";// 获取文件保存路径
        String random = request.getParameter("random");
		MultipartFile mpf = request.getFile("file");
		String myFileName= mpf.getOriginalFilename();
		map.put("filename",myFileName);
		map.put("random",random);
        int AccessoryName =accessoryService.getByAccessoryOldName(myFileName,stuuid);
    	if(AccessoryName<1){
        // 如果名称不为“”,说明该文件存在，否则说明该文件不存在
        if (myFileName.trim() != "") {
            System.out.println(myFileName);
            String fileExtName = myFileName.substring(myFileName.lastIndexOf(".")+1);
            // 重命名上传后的文件名 按照年月日时分秒进行命名
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            //更改文件名称，防止重名
            String newFileName = df.format(new Date()) + "_" + UUid.getUUID()   + "." + fileExtName;
            // 定义上传路径
            try {
              File fileDir = new File(savePath);
              if (!fileDir.exists()) { // 如果不存在 则创建
                  fileDir.mkdirs();
              }
              // 存文件
              File localFile = new File(savePath,newFileName);
              OutputStream os = new FileOutputStream(localFile);  
              InputStream is = mpf.getInputStream();  
              byte buf[] = new byte[1024];// 可以修改 1024 以提高读取速度  
              int length = 0;  
              while ((length = is.read(buf)) > 0)  
              {  
                  os.write(buf, 0, length);  
              }  
              // 关闭流  
              os.flush();  
              os.close();  
              is.close();
              
              Accessory accessory = new Accessory();
              accessory.setAccessoryDesc("");
              accessory.setAccessoryKey(UUid.getUUID());
              accessory.setAccessoryNewname(newFileName);
              accessory.setAccessoryOldname(mpf.getOriginalFilename());
              accessory.setCreatePerson(stuuid);
              accessory.setCreateTime(new Date());
              accessory.setIsDel("0");
              accessory.setPath(savePath);
              
              int row = accessoryService.save(accessory);
              if(row>0){
                  map.put("code", 0);
              }else{
                  map.put("code", 1);
              }
              
          } catch (IllegalStateException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
          } catch (IOException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
          }  
        }
    	}else{
    		map.put("code", 2);
    	}
		return map;
	}
	/**
	 * 新增视频教学课件信息
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/saveAccessoryKe.do",method=RequestMethod.POST)
	@ResponseBody
	public Object saveAccessoryKe(@RequestParam String stuuid,HttpServletResponse response,MultipartHttpServletRequest request,Model model){
		log.info("saveaccessory");
		Map<String,Object> map = new HashMap<String,Object>();
        DbcontextHolder.setDbType("imagedb");
        Properties prop = Util.readData("path.properties");
        SimpleDateFormat ymd = new SimpleDateFormat("yyyyMMdd");
        String savePath = prop.getProperty("accessoryPath").trim()+ymd.format(new Date())+"/";// 获取文件保存路径
        String random = request.getParameter("random");
		MultipartFile mpf = request.getFile("file");
		String myFileName= mpf.getOriginalFilename();
		map.put("filename",myFileName);
		map.put("random",random);
        	int AccessoryName =accessoryService.getByAccessoryOldName(myFileName,stuuid);
        	if(AccessoryName<1){
        		// 如果名称不为“”,说明该文件存在，否则说明该文件不存在
                if (myFileName.trim() != "") {
                    System.out.println(myFileName);
                    String fileExtName = myFileName.substring(myFileName.lastIndexOf(".")+1);
                    String fileName = myFileName.substring(0,myFileName.lastIndexOf("."));
                    //更改文件名称，防止重名
                    String newFileName = fileName + "." + fileExtName;
                    // 定义上传路径
                    try {
                      File fileDir = new File(savePath);
                      if (!fileDir.exists()) { // 如果不存在 则创建
                          fileDir.mkdirs();
                      }
                      // 存文件
                      File localFile = new File(savePath,newFileName);
                      OutputStream os = new FileOutputStream(localFile);
                      InputStream is = mpf.getInputStream();
                      byte buf[] = new byte[1024];// 可以修改 1024 以提高读取速度  
                      int length = 0;  
                      while ((length = is.read(buf)) > 0)  
                      {  
                          os.write(buf, 0, length);  
                      }  
                      // 关闭流  
                      os.flush();  
                      os.close();  
                      is.close();
                      
                      Accessory accessory = new Accessory();
                      accessory.setAccessoryDesc("");
                      accessory.setAccessoryKey(UUid.getUUID());
                      accessory.setAccessoryNewname(newFileName);
                      accessory.setAccessoryOldname(mpf.getOriginalFilename());
                      accessory.setCreatePerson(stuuid);
                      accessory.setCreateTime(new Date());
                      accessory.setIsDel("0");
                      accessory.setPath(savePath);
                      
                      int row = accessoryService.save(accessory);
                      if(row>0){
                          map.put("code", 0);
                      }else{
                          map.put("code", 1);
                      }
                      
                  } catch (IllegalStateException e) {
                      // TODO Auto-generated catch block
                      e.printStackTrace();
                  } catch (IOException e) {
                      // TODO Auto-generated catch block
                      e.printStackTrace();
                  }  
                }
        	}else{
        		map.put("code", 2);
        	}
        		
		return map;
	}
	/**
	 * 根据附件path预览附件
	 * @param path
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/yulanaccessory.do",method=RequestMethod.GET)
	@ResponseBody
	public String  download(@RequestParam String path,HttpServletRequest request, HttpServletResponse response)throws Exception {
		log.info("yulanaccessory");
		DbcontextHolder.setDbType("imagedb");
		File file = new File(path);
        if (!file.exists()) {
            request.setAttribute("error", "附件已删除或不存在");
            //      return "/error";
        }
        InputStream in = null;
        OutputStream os = null;
        try {
        	 String filename=path.substring(path.lastIndexOf("."), path.length());
        	 if(".pdf".equals(filename)){response.setHeader("Content-type","application/pdf");}
        	 else if(".docx".equals(filename)){response.setHeader("Content-type","application/vnd.openxmlformats-officedocument.wordprocessingml.document");}
        	 else if(".doc".equals(filename)){response.setHeader("Content-type","application/msword");}
        	 else if(".wps".equals(filename)){response.setHeader("Content-type","application/vnd.ms-works");}
        	 else if(".xlsx".equals(filename)){response.setHeader("Content-type","application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");}
        	 else if(".xls".equals(filename)){response.setHeader("Content-type","application/vnd.ms-excel");}
        	 else if(".ppt".equals(filename)){response.setHeader("Content-type","application/vnd.ms-powerpoint");}
        	 else if(".pptx".equals(filename)){response.setHeader("Content-type","application/vnd.openxmlformats-officedocument.presentationml.presentation");}
        	 else if(".html".equals(filename)){response.setHeader("Content-type","text/html");}
        	 else if(".txt".equals(filename)){response.setHeader("Content-Disposition","text/plain");}
        	 else if(".gif ".equals(filename)){response.setHeader("Content-type","image/gif");}
        	 else if(".jpeg".equals(filename)){response.setHeader("Content-type","image/jpeg");}
        	 else if(".jpg".equals(filename)){response.setHeader("Content-type","image/jpeg");}
        	 else if(".png".equals(filename)){response.setHeader("Content-type","image/png");}
        	 /*response.setContentType("/vnd.openxmlformats-officedocument.wordprocessingml.document"); // 设置返回内容格式  */        
        	in = new FileInputStream(file);   //用该文件创建一个输入流
            os = response.getOutputStream();  //application创建输出流
            byte[] b = new byte[1024];
            while (in.read(b) != -1) {
                os.write(b);
            }
            in.close();
            os.flush();
            os.close();
        } catch (Exception e) {
            try {
                if (null != in) {
                    in.close();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            try {
                if (null != os) {
                    os.close();
                }
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        return null;
	}
	/**
	 * 根据附件path下载文件
	 * @param path
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/downaccessory.do",method=RequestMethod.GET)
	@ResponseBody
	public HttpServletResponse downaccessory(@RequestParam String path,HttpServletRequest request, HttpServletResponse response)throws Exception {
		log.info("downaccessory");
		try {
            // path是指欲下载的文件的路径。
            File file = new File(path);
            // 取得文件名。
            String filename=new String(URLEncoder.encode(file.getName(),"utf-8"));
            // 取得文件的后缀名。
            String ext = filename.substring(filename.lastIndexOf(".") + 1);
            String fileName = filename.substring(0,filename.lastIndexOf("."));
            //更改文件名称，防止重名
            String newFileName = fileName + "." + ext;
            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
           
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(newFileName.getBytes()));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
	}
	/**
	 * 根据附件id修改附件信息
	 * @param id
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/updateById.do",method=RequestMethod.GET)
	@ResponseBody
	public Object updateById(@RequestParam String id,HttpServletRequest request,Model model){
		log.info("updateById");
		DbcontextHolder.setDbType("imagedb");
		Map<String,Object> map = new HashMap<String,Object>();
		
		Accessory accessory = new Accessory();
		accessory.setAccessoryDesc("miaoshu");
		accessory.setAccessoryKey(id);
		accessory.setAccessoryNewname("ceshixiugai.txt");
		accessory.setAccessoryOldname("原来的名字");
		accessory.setIsDel("0");
		accessory.setModifyPerson("hmj");
		accessory.setModifyTime(new Date());
		int row = accessoryService.updateById(accessory);
		
		map.put("修改的行数", row);
		map.put("修改的信息", accessory);
		return map;
	}
}
