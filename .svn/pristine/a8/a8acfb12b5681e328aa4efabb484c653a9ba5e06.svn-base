package com.inteall.image.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
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
import org.springframework.web.multipart.MultipartFile;

import com.inteall.image.pojo.CommitteeIntroduction;
import com.inteall.image.pojo.SysUser;
import com.inteall.image.service.CommitteeIntroductionService;
import com.inteall.image.util.DbcontextHolder;
import com.inteall.image.util.UUid;

/**
 * @author 韩明君
 * @date 2018年9月10日 上午11:40:06
 * @version 1.0 
 * @parameter 
 */
@Controller
@RequestMapping("/committee")
public class CommitteeIntroductionController {
    private static Logger log = Logger.getLogger(CommitteeIntroductionController.class.getName());
    @Resource
    private CommitteeIntroductionService committeeIntroductionService;
    
    @RequestMapping(value="/getCommittee.do",method=RequestMethod.POST)
    @ResponseBody
    public Object getCommittee(HttpServletRequest request,Model model){
        log.info("getCommittee");
        Map<String,Object> map = new HashMap<String,Object>();
        DbcontextHolder.setDbType("imagedb");
//        HttpSession session = request.getSession();
//        SysUser sysUser =  (SysUser) session.getAttribute("SysUser");
        
        String name = request.getParameter("name");
        int page = Integer.parseInt(request.getParameter("page"));
        int limit = Integer.parseInt(request.getParameter("limit"));
        
        HashMap<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("name", name);
        
        //先查询满足条件的数据有多少条
        int count = committeeIntroductionService.getCountCommittee(queryMap);
        
        queryMap.put("curr", (page-1)*limit);
        queryMap.put("limit",limit);
        
        List<CommitteeIntroduction> committeeIntroductions = committeeIntroductionService.getCommittee(queryMap);
        
        if(count>=0){
            map.put("code", 0);
            map.put("count", count);
            map.put("data", committeeIntroductions);
        }else{
            map.put("code", 1);
            map.put("mgs", "查询委员介绍出错，请稍后重试或者联系管理员！");
        }
        
        
        return map;
    }
    
    @RequestMapping(value="uploadImage")
    @ResponseBody
    public Object uploadImage(@RequestParam("file") MultipartFile file,HttpServletRequest request){
        Map<String,Object> map = new HashMap<String,Object>();
        String oldName = file.getOriginalFilename();
        String path = request.getServletContext().getRealPath("/upload/image");
        
        // 如果名称不为“”,说明该文件存在，否则说明该文件不存在
        if (oldName.trim() != "") {
            System.out.println(oldName);
            String fileExtName = oldName.substring(oldName.lastIndexOf(".")+1);
            // 重命名上传后的文件名 按照年月日时分秒进行命名
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            //更改文件名称，防止重名
            String newFileName = df.format(new Date()) + "_" + UUid.getUUID()   + "." + fileExtName;
            // 定义上传路径
            try {
              File fileDir = new File(path+"/"+sdf.format(new Date()));
              if (!fileDir.exists()) { // 如果不存在 则创建
                  fileDir.mkdirs();
              }
              // 存文件
              File localFile = new File(path+"/"+sdf.format(new Date()),newFileName);
              OutputStream os = new FileOutputStream(localFile);  
              InputStream is = file.getInputStream();  
              byte buf[] = new byte[2048];// 可以修改 1024 以提高读取速度  
              int length = 0;  
              while ((length = is.read(buf)) > 0)  
              {  
                  os.write(buf, 0, length);  
              }  
              // 关闭流  
              os.flush();  
              os.close();  
              is.close();
              map.put("code", 0);
              map.put("msg", "");
              map.put("oldName",oldName);
              map.put("src", "/upload/image"+"/"+sdf.format(new Date())+"/"+newFileName);
          } catch (IllegalStateException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
          } catch (IOException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
          }  
        }
        
        return map;
    }
    
    /**
     * 跳转到新增委员界面
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value="/redirectAdd.do",method=RequestMethod.GET)
    public Object redirectAdd(HttpServletRequest request,Model model){
        log.info("redirectAdd");
        
        return "houtaiguanli/committeeadd";
    }
    
    /**
     * 保存委员信息
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value="/save.do",method=RequestMethod.POST)
    @ResponseBody
    public Object save(CommitteeIntroduction cIntroduction,HttpServletRequest request,Model model){
        log.info("save");
        Map<String,Object> map = new HashMap<String,Object>();
        DbcontextHolder.setDbType("imagedb");
        HttpSession session = request.getSession();
        SysUser sysUser =  (SysUser) session.getAttribute("SysUser");
        
        int count;
        if(cIntroduction.getCommitteeid()==null){
            cIntroduction.setCommitteeid(UUid.getUUID());
            cIntroduction.setCreatetime(new Date());
            cIntroduction.setCreatepersonid(sysUser.getSysuserKey());
            count = committeeIntroductionService.save(cIntroduction);
        }else{
            count = committeeIntroductionService.updateById(cIntroduction);
        }
         
        
        if(count>0){
            map.put("code", 0);
        }else{
            map.put("code", 1);
            map.put("msg", "委员信息保存失败，请稍后重试或者联系管理员!");
        }
        
        return map;
    }
    
    /**
     * 跳转到编辑委员界面
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value="/getById.do",method=RequestMethod.GET)
    public Object getById(HttpServletRequest request,Model model){
        log.info("getById");
        
        DbcontextHolder.setDbType("imagedb");
        
        String id = request.getParameter("id");
        CommitteeIntroduction cIntroduction = committeeIntroductionService.getById(id);
        model.addAttribute("cIntroduction", cIntroduction);
        return "houtaiguanli/committeeedit";
    }
    
    /**
     * 跳转到编辑委员界面
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value="/delById.do",method=RequestMethod.GET)
    @ResponseBody
    public Object delById(HttpServletRequest request,Model model){
        log.info("delById");
        Map<String,Object> map = new HashMap<String,Object>();
        
        DbcontextHolder.setDbType("imagedb");
        
        String id = request.getParameter("id");
        int count = committeeIntroductionService.delById(id);
        if(count>0){
            map.put("code", 0);
        }else{
            map.put("code", 1);
            map.put("msg", "委员信息保存失败，请稍后重试或者联系管理员!");
        }
        
        return map;
    }
    
    @RequestMapping(value="/findShow.do",method=RequestMethod.POST)
    @ResponseBody
    public Object findShow(HttpServletRequest request,Model model){
        log.info("findShow");
        Map<String,Object> map = new HashMap<String,Object>();
        DbcontextHolder.setDbType("imagedb");
        
        
        List<CommitteeIntroduction> committeeIntroductions = committeeIntroductionService.findShow();
        
        if(committeeIntroductions!=null){
            map.put("code", 0);
            map.put("committeeIntroductions", committeeIntroductions);
        }else{
            map.put("code", 1);
            map.put("mgs", "查询委员介绍出错，请稍后重试或者联系管理员！");
        }
        
        return map;
    }
    
    /**
     * 跳转到委员详情界面
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value="/detail.do",method=RequestMethod.GET)
    public Object detail(HttpServletRequest request,Model model){
        log.info("detail");
        
        DbcontextHolder.setDbType("imagedb");
        
        String id = request.getParameter("committeeid");
        CommitteeIntroduction cIntroduction = committeeIntroductionService.getById(id);
        model.addAttribute("cIntroduction", cIntroduction);
        return "houtaiguanli/committeedetail";
    }
}
