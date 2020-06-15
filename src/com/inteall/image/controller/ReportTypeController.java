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

import com.inteall.image.pojo.Collection;
import com.inteall.image.pojo.ReportTemplate;
import com.inteall.image.pojo.ReportTemplateWithBLOBs;
import com.inteall.image.pojo.ReportType;
import com.inteall.image.pojo.SysUser;
import com.inteall.image.service.ReportTemplateService;
import com.inteall.image.service.ReportTypeService;
import com.inteall.image.util.DbcontextHolder;
import com.inteall.image.util.UUid;

import net.sf.json.JSONObject;


/** 
* @author 韩明君  
* @date 创建时间：2018年2月7日 下午1:17:38 
* @version 1.0 
* @parameter  
*/
@Controller
@RequestMapping("/reporttype")
public class ReportTypeController {
	private static Logger log = Logger.getLogger(ReportTypeController.class.getName());
	@Resource
	private ReportTypeService reportTypeService;
	@Resource
	private ReportTemplateService reportTemplateService;
	
	/**
	 * 根据报告类型的id查询报告类型信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/getById.do",method=RequestMethod.GET)
	@ResponseBody
	public Object getById(@RequestParam String id){
		log.info("getById");
		Map<String,Object> map = new HashMap<String,Object>();
		DbcontextHolder.setDbType("imagedb");
		
		ReportType ReportType = reportTypeService.getById(id);
		map.put("ReportType", ReportType);
		return map;
	}
	
	/**
	 * 根据报告类型的id查询报告类型信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/getAll.do",method=RequestMethod.GET)
	@ResponseBody
	public Object getAll(@RequestParam String reporttype,@RequestParam String belong,HttpServletRequest request,Model model){
		log.info("getAll");
		Map<String,Object> map = new HashMap<String,Object>();
		DbcontextHolder.setDbType("imagedb");
		
		HttpSession session = request.getSession();
        SysUser sysUser =  (SysUser) session.getAttribute("SysUser");
		HashMap<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("reporttype", reporttype);
        queryMap.put("belong", belong);
        queryMap.put("sysuserKey", sysUser.getSysuserKey());
		
		List<ReportType> ReportTypeList = reportTypeService.getAll(queryMap);
		map.put("报告类型数量", ReportTypeList.size());
		map.put("ReportTypeList", ReportTypeList);
		return map;
	}
	
	/**
	 * 根据报告类型id删除报告类型
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delById.do",method=RequestMethod.GET)
	@ResponseBody
	public Object delById(@RequestParam String id){
		log.info("delById");
		Map<String,Object> map = new HashMap<String,Object>();
		DbcontextHolder.setDbType("imagedb");
		
		int row = reportTypeService.delById(id);
		map.put("删除了的行数：", row);
		return map;
	}
	
	/**
	 * 新增报告类型信息
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/save.do",method=RequestMethod.GET)
	@ResponseBody
	public Object save(HttpServletRequest request,Model model){
		log.info("save");
		Map<String,Object> map = new HashMap<String,Object>();
		DbcontextHolder.setDbType("imagedb");
		
		ReportType ReportType = new ReportType();
		ReportType.setCreatePerson("hmj");
		ReportType.setCreatetime(new Date());
		ReportType.setIsDel("0");
		ReportType.setReportTypeKey(UUid.getUUID());
		
		int row = reportTypeService.save(ReportType);
		
		map.put("新增的行数", row);
		map.put("保存的信息", ReportType);
		return map;
	}
	
	/**
	 * 根据报告类型id修改报告类型信息
	 * @param id
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/updateById.do",method=RequestMethod.GET)
	@ResponseBody
	public Object updateById(@RequestParam String id,HttpServletRequest request,Model model){
		log.info("updateById");
		Map<String,Object> map = new HashMap<String,Object>();
		DbcontextHolder.setDbType("imagedb");
		
		ReportType ReportType = new ReportType();
		ReportType.setModifyPerson("hmj修改");
		ReportType.setModifyTime(new Date());
		ReportType.setIsDel("0");
		ReportType.setReportTypeKey(id);
		
		int row = reportTypeService.updateById(ReportType);
		
		map.put("修改的行数", row);
		map.put("修改的信息", ReportType);
		return map;
	}
	@RequestMapping(value="/updateId.do",method=RequestMethod.GET)
	public void updateId(){
	  ReportTemplateWithBLOBs reportTemplate = new ReportTemplateWithBLOBs();
	  DbcontextHolder.setDbType("imagedb");
	  List<ReportTemplateWithBLOBs> reportlist = reportTemplateService.getAll(reportTemplate);
	  for (int i=0;i<reportlist.size();i++){
		ReportTemplateWithBLOBs r = reportlist.get(i);
		
		//获取模板的上一级节点属于什么类型CT、MR
		ReportType parent = reportTypeService.getById(r.getReportTypeKey());
		
//		String uuid = UUid.getUUID();
//		r.setReportTemplateKey(uuid);
		
		ReportType reportType = new ReportType();
		reportType.setModality(parent.getModality());
		reportType.setName(r.getReportTemplateName());
		reportType.setParentCode(r.getReportTypeKey());
		reportType.setReportTypeKey(r.getReportTemplateKey());
		reportType.setTypeCode(r.getReportTemplateKey());
		reportType.setIsDel("0");
		
		
		
		
		reportTypeService.save(reportType);
		//reportTemplateService.save(r);
	  }
	  
	}
	
	/**
     * 跳转到新增文件夹界面
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value="/redirectAddFolder.do",method=RequestMethod.GET)
    public Object redirectAddFolder(@RequestParam String clicked_id,@RequestParam String reporttype,HttpServletRequest request,Model model){
        log.info("redirectAddFolder");
        DbcontextHolder.setDbType("imagedb");
        if(clicked_id.equals("null")){
            String parentCollectionName="无";
            model.addAttribute("collectionName", parentCollectionName);
        }else{
            ReportType reportType = reportTypeService.getById(clicked_id);
            model.addAttribute("collectionName", reportType.getName());
        }
        model.addAttribute("parentkey", clicked_id);
        model.addAttribute("reporttype", reporttype);
        return "reporttype/addFolder";
    }
    
    /**
     * 插入父节点
     * 创建时间：2018年2月9日-下午3:18:00
     */
    @RequestMapping(value="/insertParentCollection.do",method=RequestMethod.POST)
    @ResponseBody
    public Object insertParentCollection(HttpServletRequest request,Model model){
        DbcontextHolder.setDbType("imagedb");
        Map<String, Object> map = new HashMap<String,Object>();
        //获取当前系统用户
        HttpSession session = request.getSession();
        SysUser sysUser =  (SysUser) session.getAttribute("SysUser");
        
        String json = request.getParameter("json");
        JSONObject returnData = JSONObject.fromObject(json);
        ReportType reportType = new ReportType();
        reportType.setCreatePerson(sysUser.getSysuserKey());
        reportType.setCreatetime(new Date());
        reportType.setIsDel("0");
        reportType.setIsParent("true");
        reportType.setModality(returnData.getString("reporttype"));
        reportType.setName(returnData.getString("collectionName"));
        reportType.setParentCode(returnData.getString("parent_key"));
        reportType.setSysuserKey(sysUser.getSysuserKey());
        reportType.setReportTypeKey(UUid.getUUID());
        
        int i = reportTypeService.save(reportType);
        if(i>0){
            map.put("code", 0);
        }else{
            map.put("code", 1);
        }
        
        return map;
    }
    
    /**
     * 跳转到新增模板界面
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value="/redirectAddModel.do",method=RequestMethod.GET)
    public Object redirectAddModel(@RequestParam String clicked_id,@RequestParam String reporttype,HttpServletRequest request,Model model){
        log.info("redirectAddModel");
        DbcontextHolder.setDbType("imagedb");
        model.addAttribute("reporttype", reporttype);
        model.addAttribute("clicked_id", clicked_id);
        return "reporttype/addModel";
    }
    
    /**
     * 新增模板
     * 创建时间：2018年2月9日-下午3:18:00
     */
    @RequestMapping(value="/insertModel.do",method=RequestMethod.POST)
    @ResponseBody
    public Object insertModel(HttpServletRequest request,Model model){
        log.info("insertModel");
        DbcontextHolder.setDbType("imagedb");
        Map<String, Object> map = new HashMap<String,Object>();
        //获取当前系统用户
        HttpSession session = request.getSession();
        SysUser sysUser =  (SysUser) session.getAttribute("SysUser");
        
        String json = request.getParameter("json");
        JSONObject returnData = JSONObject.fromObject(json);
        
        ReportType reportType = new ReportType();
        reportType.setCreatePerson(sysUser.getSysuserKey());
        reportType.setCreatetime(new Date());
        reportType.setIsDel("0");
        reportType.setIsParent("false");
        reportType.setModality(returnData.getString("reporttype"));
        reportType.setName(returnData.getString("modelName"));
        reportType.setParentCode(returnData.getString("clicked_id"));
        reportType.setSysuserKey(sysUser.getSysuserKey());
        String uuid = UUid.getUUID();
        reportType.setReportTypeKey(uuid);
        
        ReportTemplateWithBLOBs template = new ReportTemplateWithBLOBs();
        template.setCheckView(returnData.getString("jianchansuojian"));
        template.setDiagnosisResult(returnData.getString("zhenduanjieguo"));
        template.setReportTemplateName(returnData.getString("modelName"));
        template.setCreatePerson(sysUser.getSysuserKey());
        template.setCreateTime(new Date());
        template.setIsDel("0");
        template.setReportTemplateKey(uuid);
        template.setReportTypeKey(returnData.getString("clicked_id"));
        
        int i = reportTypeService.save(reportType);
        int n = reportTemplateService.save(template);
        if(i>0&&n>0){
            map.put("code", 0);
        }else{
            map.put("code", 1);
        }
        
        return map;
    }
    
    /**
     * 跳转到编辑文件夹界面
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value="/redirectEditFolder.do",method=RequestMethod.GET)
    public Object redirectEditFolder(@RequestParam String clicked_id,@RequestParam String reporttype,HttpServletRequest request,Model model){
        log.info("redirectEditFolder");
        DbcontextHolder.setDbType("imagedb");
        ReportType reportType = reportTypeService.getById(clicked_id);
        model.addAttribute("reportType", reportType);
        return "reporttype/editFolder";
    }
    
    /**
     * 跳转到编辑文件夹界面
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value="/redirectEditModel.do",method=RequestMethod.GET)
    public Object redirectEditModel(@RequestParam String clicked_id,@RequestParam String reporttype,HttpServletRequest request,Model model){
        log.info("redirectEditModel");
        DbcontextHolder.setDbType("imagedb");
        ReportTemplateWithBLOBs reportTemplateWithBLOBs = reportTemplateService.getById(clicked_id);
        model.addAttribute("reportTemplateWithBLOBs", reportTemplateWithBLOBs);
        return "reporttype/editModel";
    }
    
    /**
     * 更新文件夹名称
     * 创建时间：2018年2月9日-下午3:18:00
     */
    @RequestMapping(value="/updateParentCollection.do",method=RequestMethod.POST)
    @ResponseBody
    public Object updateParentCollection(HttpServletRequest request,Model model){
        log.info("updateParentCollection");
        DbcontextHolder.setDbType("imagedb");
        Map<String, Object> map = new HashMap<String,Object>();
        //获取当前系统用户
        HttpSession session = request.getSession();
        SysUser sysUser =  (SysUser) session.getAttribute("SysUser");
        
        String json = request.getParameter("json");
        JSONObject returnData = JSONObject.fromObject(json);
        ReportType reportType = new ReportType();
        reportType.setModifyPerson(sysUser.getSysuserKey());
        reportType.setModifyTime(new Date());
        reportType.setName(returnData.getString("name"));
        reportType.setReportTypeKey(returnData.getString("reportTypeKey"));
        
        int i = reportTypeService.updateById(reportType);
        if(i>0){
            map.put("code", 0);
        }else{
            map.put("code", 1);
        }
        
        return map;
    }
    /**
     * 修改模板
     * 创建时间：2018年2月9日-下午3:18:00
     */
    @RequestMapping(value="/updateModel.do",method=RequestMethod.POST)
    @ResponseBody
    public Object updateModel(HttpServletRequest request,Model model){
        log.info("updateModel");
        DbcontextHolder.setDbType("imagedb");
        Map<String, Object> map = new HashMap<String,Object>();
        //获取当前系统用户
        HttpSession session = request.getSession();
        SysUser sysUser =  (SysUser) session.getAttribute("SysUser");
        
        String json = request.getParameter("json");
        JSONObject returnData = JSONObject.fromObject(json);
        
        ReportType reportType = new ReportType();
        reportType.setModifyPerson(sysUser.getSysuserKey());
        reportType.setModifyTime(new Date());
        reportType.setReportTypeKey(returnData.getString("reportTemplateKey"));
        reportType.setName(returnData.getString("modelName"));
        
        ReportTemplateWithBLOBs template = new ReportTemplateWithBLOBs();
        template.setCheckView(returnData.getString("jianchansuojian"));
        template.setDiagnosisResult(returnData.getString("zhenduanjieguo"));
        template.setReportTemplateName(returnData.getString("modelName"));
        template.setModifyPerson(sysUser.getSysuserKey());
        template.setModifyTime(new Date());
        template.setReportTemplateKey(returnData.getString("reportTemplateKey"));
        
        int n = reportTemplateService.updateById(template);
        if(n>0){
            map.put("code", 0);
        }else{
            map.put("code", 1);
        }
        
        return map;
    }
    
    /**
     * 删除模板
     * 创建时间：2018年2月9日-下午3:18:00
     */
    @RequestMapping(value="/delModel.do",method=RequestMethod.GET)
    @ResponseBody
    public Object delModel(@RequestParam String clicked_id,@RequestParam String reporttype,HttpServletRequest request,Model model){
        log.info("delModel");
        DbcontextHolder.setDbType("imagedb");
        Map<String, Object> map = new HashMap<String,Object>();
        
        int i = reportTypeService.delById(clicked_id);
        if(i>0){
            map.put("code", 0);
        }else{
            map.put("code", 1);
        }
        
        return map;
    }
    /**
     * 删除文件夹前判断是否含有子文件夹
     * 创建时间：2018年2月9日-下午3:18:00
     */
    @RequestMapping(value="/hasChildren.do",method=RequestMethod.POST)
    @ResponseBody
    public Object hasChildren(HttpServletRequest request,Model model){
        log.info("hasChildren");
        DbcontextHolder.setDbType("imagedb");
        String clicked_id = request.getParameter("clicked_id");
        Map<String, Object> map = new HashMap<String,Object>();
        
        int i = reportTypeService.hasChildren(clicked_id);
        map.put("code", 0);
        map.put("i", i);
        
        return map;
    }
    /**
     * 删除文件夹
     * 创建时间：2018年2月9日-下午3:18:00
     */
    @RequestMapping(value="/delFolder.do",method=RequestMethod.GET)
    @ResponseBody
    public Object delFolder(@RequestParam String clicked_id,@RequestParam String reporttype,HttpServletRequest request,Model model){
        log.info("delFolder");
        DbcontextHolder.setDbType("imagedb");
        Map<String, Object> map = new HashMap<String,Object>();
        
        int i = reportTypeService.delById(clicked_id);
        if(i>0){
            map.put("code", 0);
        }else{
            map.put("code", 1);
        }
        
        return map;
    }
}
