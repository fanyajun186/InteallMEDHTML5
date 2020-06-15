package com.inteall.image.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inteall.image.pojo.ZhishikuType;
import com.inteall.image.service.ZhishikuService;
import com.inteall.image.service.ZhishikuTypeService;
import com.inteall.image.util.DbcontextHolder;

/**
 * 管理员知识库类别Controller层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/zhishikuType")
public class ZhishikuTypeController {

	@Resource
	private ZhishikuTypeService zhishikuTypeService;
	
	@Resource
	private ZhishikuService zhishikuService;
	
	/**
	 * 分页查询知识库类别信息
	 * @param page
	 * @param rows
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list.do")
	@ResponseBody
	public Object list(HttpServletResponse response){
		Map<String,Object> map=new HashMap<String,Object>();
		
		DbcontextHolder.setDbType("imagedb");
		List<ZhishikuType> parentList=zhishikuTypeService.parentlist();
		List<ZhishikuType> childList=zhishikuTypeService.childlist();
		map.put("code", 0);
		map.put("parentList", parentList);
		map.put("childList", childList);
		return map;
	}
	
	/**
	 * 添加或者修改知识库类别信息
	 * @param ZhishikuType
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/save.do")
	@ResponseBody
	public Object save(ZhishikuType zhishikuType,HttpServletResponse response)throws Exception{
	    Map<String,Object> map=new HashMap<String,Object>();
	    DbcontextHolder.setDbType("imagedb");
		int resultTotal=0; // 操作的记录条数
		if(zhishikuType.getId()==null){
			resultTotal=zhishikuTypeService.add(zhishikuType);
		}else{
			resultTotal=zhishikuTypeService.update(zhishikuType);
		}
		if(resultTotal>0){
		    map.put("code", 0);
		}else{
		    map.put("code", 1);
		    map.put("msg", "保存出错，请稍后重试或者联系管理员。");
		}
		return map;
	}
	
	/**
	 * 删除知识库类别信息
	 * @param ids
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/delete.do")
	@ResponseBody
	public Object delete(@RequestParam(value="id")int id,HttpServletResponse response)throws Exception{
		DbcontextHolder.setDbType("imagedb");
		Map<String,Object> map=new HashMap<String,Object>();
		if(zhishikuService.getzhishikuByTypeId(id)>0){
		    map.put("code", "1");
			map.put("msg", "知识库类别下有知识库，不能删除！");
		}else{
			zhishikuTypeService.delete(id);		
			map.put("code", "0");
		}
		return map;
	}
	
	/**
     * 分页查询知识库类别信息
     * @param page
     * @param rows
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/getParentByName.do")
    @ResponseBody
    public Object getParentByName(@RequestParam(value="name",required=false)String name,
            @RequestParam(value="page",required=false)int page,
            @RequestParam(value="limit",required=false)int limit,
            HttpServletResponse response){
        Map<String,Object> map=new HashMap<String,Object>();
        
        DbcontextHolder.setDbType("imagedb");
        
        Map<String,Object> querymap=new HashMap<String,Object>();
        querymap.put("name", name);
        int count=zhishikuTypeService.getCountParentByName(querymap);
        querymap.put("curr", (page-1)*limit);
        querymap.put("limit",limit);
        List<ZhishikuType> parentList=zhishikuTypeService.getParentByName(querymap);
        map.put("code", 0);
        map.put("count", count);
        map.put("data", parentList);
        return map;
    }
    /**
     * 分页查询知识库类别信息
     * @param page
     * @param rows
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/getChild.do")
    @ResponseBody
    public Object getChild(@RequestParam(value="name",required=false)String name,
            @RequestParam(value="page",required=false)int page,
            @RequestParam(value="parentId",required=false)int parentId,
            @RequestParam(value="limit",required=false)int limit,
            HttpServletResponse response){
        Map<String,Object> map=new HashMap<String,Object>();
        
        DbcontextHolder.setDbType("imagedb");
        
        Map<String,Object> querymap=new HashMap<String,Object>();
        querymap.put("name", name);
        querymap.put("parentId", parentId);
        int count=zhishikuTypeService.getCountChild(querymap);
        querymap.put("curr", (page-1)*limit);
        querymap.put("limit",limit);
        List<ZhishikuType> parentList=zhishikuTypeService.getChild(querymap);
        map.put("code", 0);
        map.put("count", count);
        map.put("data", parentList);
        return map;
    }
    
    
    @RequestMapping("/getById.do")
    public Object getById(@RequestParam(value="id",required=true)String id,
            HttpServletResponse response,Model model){
        
        DbcontextHolder.setDbType("imagedb");
        
        Map<String,Object> querymap=new HashMap<String,Object>();
        querymap.put("id", id);
        ZhishikuType zhishikuType = zhishikuTypeService.getById(querymap);
        model.addAttribute("zhishikuType", zhishikuType);
        
        return "houtaiguanli/typeedit";
    }
    
    /**
     * 跳转到新增界面
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value="/redirectAdd.do",method=RequestMethod.GET)
    public Object redirectAdd(@RequestParam(value="parentId",required=false) String parentId ,HttpServletRequest request,Model model){
        ZhishikuType zhishikuType = new ZhishikuType();
        if(parentId!=null){
            zhishikuType.setIsParent(0);
            zhishikuType.setParentId(Integer.parseInt(parentId));
        }else{
            zhishikuType.setIsParent(1);
            zhishikuType.setParentId(0);
        }
        model.addAttribute("zhishikuType", zhishikuType);
        
        return "houtaiguanli/typeadd";
    }
}
