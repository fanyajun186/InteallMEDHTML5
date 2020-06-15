package com.inteall.image.controller;
import java.util.Date;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inteall.image.pojo.Notice;
import com.inteall.image.pojo.PageBean;
import com.inteall.image.pojo.SysUser;
import com.inteall.image.pojo.Zhishiku;
import com.inteall.image.pojo.ZhishikuComment;
import com.inteall.image.pojo.ZhishikuType;
import com.inteall.image.service.NoticeService;
import com.inteall.image.service.ZhishikuCommentService;
import com.inteall.image.service.ZhishikuService;
import com.inteall.image.service.ZhishikuTypeService;
import com.inteall.image.util.BlogIndex;
import com.inteall.image.util.DbcontextHolder;
import com.inteall.image.util.ResponseUtil;
import com.inteall.image.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author 于津名
 * @date 2018年8月28日 上午10:48:49
 * @version 1.0 
 * @parameter 
 */
@Controller
@RequestMapping("/zhishikuManage")
public class ZhishikuManagerController {
    @Resource
    private ZhishikuService zhishikuService;
    @Resource
    private ZhishikuTypeService zhishikuTypeService;
    @Resource
    private ZhishikuCommentService commentService;
    @Resource
    private NoticeService noticeService;
    // 博客索引
 	private BlogIndex zhishikuIndex=new BlogIndex();
 	private static Logger log = Logger.getLogger(ZhishikuManagerController.class.getName());
    @RequestMapping("/writezhishikuindex.do")
    public Object writezhishikuindex(Model model){
    	log.info("writezhishikuindex");
    	List<ZhishikuType> ZhishikuType=zhishikuTypeService.parentlist();
    	JSONArray json = JSONArray.fromObject(ZhishikuType);
        String html = json.toString();
        model.addAttribute("ZhishikuType",html); 
		return "houtaiguanli/writezhishiku";
    }
    @RequestMapping("/modifyzhishiku.do")
    public Object modifyzhishiku(HttpServletRequest request,Model model){
    	log.info("modifyzhishiku");
    	String id = request.getParameter("id");
    	List<ZhishikuType> ZhishikuType=zhishikuTypeService.parentlist();
    	JSONArray json = JSONArray.fromObject(ZhishikuType);
        String html = json.toString();
        model.addAttribute("ZhishikuType",html); 
    	model.addAttribute("id", id);
		return "houtaiguanli/modifyzhishiku";
    }
    /**
	 * 查询知识库信息
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/getAll.do",method=RequestMethod.POST)
	@ResponseBody
	public Object getAll(HttpServletRequest request,Model model){
		log.info("getAll");
		int page = Integer.parseInt(request.getParameter("page"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		String zhishikuManagename = request.getParameter("zhishikuManagename");
		Map<String,Object> map = new HashMap<String,Object>();
		DbcontextHolder.setDbType("imagedb");
		Zhishiku zhishiku = new Zhishiku();
		zhishiku.setTitle(zhishikuManagename);
		//先查询满足条件的数据有多少条
		int count = zhishikuService.getCount(zhishiku);
		
		//再查当前页中的数据
		zhishiku.setLimit(limit);
		zhishiku.setCurr((page-1)*limit);
		List<Zhishiku> zhishikuList = zhishikuService.getAll(zhishiku);
		if(zhishikuList!=null&&zhishikuList.size()>0){
    		map.put("code", 0);
    		map.put("msg", "");
    		map.put("count", count);
    		map.put("data", zhishikuList);
		}else{
			map.put("code", 1);
			map.put("msg", "<img style='width:228px;height:228px' src='img/noneicon.png'>");
		}
		return map;
	}
	@RequestMapping(value="/findBySubclass.do",method=RequestMethod.GET)
	@ResponseBody
    public  List<ZhishikuType> findBySubclass(HttpServletRequest request, HttpServletResponse response){
    	log.info("findBySubclass");
    	String isParent =request.getParameter("isParent");
    	List<ZhishikuType> SubclassList=zhishikuTypeService.childlistByparentId(isParent);
    	return SubclassList;
    }
	/**
	 * 通过ID查找实体
	 * @param id
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/findById.do")
	public String findById(@RequestParam(value="id")String id,HttpServletResponse response)throws Exception{
		Zhishiku zhishiku=zhishikuService.findById(Integer.parseInt(id));
		JSONObject jsonObject=JSONObject.fromObject(zhishiku);
		ResponseUtil.write(response, jsonObject);
		return null;
	}
    /**
	 * 添加或者修改博客信息
	 * @param zhishiku
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/save.do")
	public String save(Zhishiku zhishiku,HttpServletResponse response,HttpServletRequest request)throws Exception{
		int resultTotal=0; // 操作的记录条数
		HttpSession session = request.getSession();
		SysUser sysUser =  (SysUser) session.getAttribute("SysUser");
		String releasePersonId=sysUser.getSysuserKey();
		String releasePersonName=sysUser.getUserName();
		zhishiku.setReleasePersonId(releasePersonId);
		zhishiku.setReleasePersonName(releasePersonName);
		if(zhishiku.getId()==null){
			resultTotal=zhishikuService.add(zhishiku);
			zhishikuIndex.addIndex(zhishiku); // 添加博客索引
		}else{
			resultTotal=zhishikuService.update(zhishiku);
			zhishikuIndex.updateIndex(zhishiku); // 更新博客索引
		}
		JSONObject result=new JSONObject();
		if(resultTotal>0){
			result.put("success", true);
		}else{
			result.put("success", false);
		}
		ResponseUtil.write(response, result);
		return null;
	}
	/**
	 * 通过ID查找实体
	 * @param id
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/delById.do")
	@ResponseBody
	public Object delById(@RequestParam(value="id")String id,HttpServletResponse response){
		log.info("delById");
		Map<String,Object> map = new HashMap<String,Object>();
		int i =zhishikuService.delById(Integer.parseInt(id));
		if (i > 0) {
			map.put("code", 0);
		  } else {
			map.put("code", 1);
			map.put("msg", "删除失败！");
		  } 
		return map;
	}
	/**
	 * 通过ID查找实体
	 * @param id
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/delplById.do")
	@ResponseBody
	public Object delplById(@RequestParam(value="id")String id,HttpServletResponse response){
		log.info("delplById");
		Map<String,Object> map = new HashMap<String,Object>();
		int i =commentService.delplById(Integer.parseInt(id));
		if (i > 0) {
			map.put("code", 0);
		} else {
			map.put("code", 1);
			map.put("msg", "删除失败！");
		} 
		return map;
	}
	/**
	 * 查询知识库评论信息
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/getplAll.do",method=RequestMethod.POST)
	@ResponseBody
	public Object getplAll(HttpServletRequest request,Model model){
		log.info("getplAll");
		int page = Integer.parseInt(request.getParameter("page"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		String zhishikuManagecontentpl = request.getParameter("zhishikuManagecontentpl");
		Map<String,Object> map = new HashMap<String,Object>();
		DbcontextHolder.setDbType("imagedb");
		ZhishikuComment zhishikuComment = new ZhishikuComment();
		zhishikuComment.setContent(zhishikuManagecontentpl);
		//先查询满足条件的数据有多少条
		int count = commentService.getCount(zhishikuComment);
		
		//再查当前页中的数据
		zhishikuComment.setLimit(limit);
		zhishikuComment.setCurr((page-1)*limit);
		List<ZhishikuComment> commentList = commentService.getplAll(zhishikuComment);
		if(commentList!=null&&commentList.size()>0){
    		map.put("code", 0);
    		map.put("msg", "");
    		map.put("count", count);
    		map.put("data", commentList);
		}else{
			map.put("code", 1);
			map.put("msg", "<img style='width:228px;height:228px' src='img/noneicon.png'>");
		}
		return map;
	}
	/**
	 * 查询知识库信息
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/getNoticeAll.do",method=RequestMethod.POST)
	@ResponseBody
	public Object getNoticeAll(HttpServletRequest request,Model model){
		log.info("getNoticeAll");
		int page = Integer.parseInt(request.getParameter("page"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		String noticeName = request.getParameter("noticename");
		Map<String,Object> map = new HashMap<String,Object>();
		DbcontextHolder.setDbType("imagedb");
		Notice notice = new Notice();
		notice.setNoticename(noticeName);
		//先查询满足条件的数据有多少条
		int count = noticeService.getCount(notice);
		
		//再查当前页中的数据
		notice.setLimit(limit);
		notice.setCurr((page-1)*limit);
		List<Notice> noticeList = noticeService.getNoticeAll(notice);
		if(noticeList!=null&&noticeList.size()>0){
    		map.put("code", 0);
    		map.put("msg", "");
    		map.put("count", count);
    		map.put("data", noticeList);
		}else{
			map.put("code", 1);
			map.put("msg", "<img style='width:228px;height:228px' src='img/noneicon.png'>");
		}
		return map;
	}
	/**
     * 跳转到新增界面
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value="/noticeAdd.do",method=RequestMethod.GET)
    public Object redirectAdd(HttpServletRequest request,Model model){
        
        return "houtaiguanli/noticeadd";
    }
    @RequestMapping("/noticesave.do")
	@ResponseBody
	public Object save(Notice notice,HttpServletRequest request,HttpServletResponse response)throws Exception{
	    Map<String,Object> map=new HashMap<String,Object>();
	    DbcontextHolder.setDbType("imagedb");
	    HttpSession session = request.getSession();
		SysUser sysUser =  (SysUser) session.getAttribute("SysUser");
		String creatName=sysUser.getUserName();
		String noticeName=request.getParameter("noticeName");
		String noticeContent=request.getParameter("noticeContent");
		notice.setNoticename(noticeName);
		notice.setNoticecontent(noticeContent);
		notice.setCreattime(new Date());
		notice.setCreatperson(creatName);
	    int noticeTotal=noticeService.add(notice);
		if(noticeTotal>0){
		    map.put("code", 0);
		}else{
		    map.put("code", 1);
		    map.put("msg", "保存出错，请稍后重试或者联系管理员。");
		}
		return map;
	}
    @RequestMapping("/modifynotice.do")
    public Object modifynotice(HttpServletRequest request,Model model){
    	log.info("modifynotice");
    	String id = request.getParameter("id");
    	Notice notice =noticeService.getnoticeById(id);
    	model.addAttribute("notice", notice);
		return "houtaiguanli/modifynotice";
    }
    @RequestMapping("/noticeupdate.do")
 	@ResponseBody
 	public Object noticeupdate(Notice notice,HttpServletRequest request,HttpServletResponse response)throws Exception{
 	    Map<String,Object> map=new HashMap<String,Object>();
 	    DbcontextHolder.setDbType("imagedb");
 	    HttpSession session = request.getSession();
 		SysUser sysUser =  (SysUser) session.getAttribute("SysUser");
 		String creatName=sysUser.getUserName();
 		String noticeId=request.getParameter("noticeId");
 		String noticeName=request.getParameter("noticeName");
 		String noticeContent=request.getParameter("noticeContent");
 		notice.setNoticeid(noticeId);
 		notice.setNoticename(noticeName);
 		notice.setNoticecontent(noticeContent);
 		notice.setCreattime(new Date());
 		notice.setCreatperson(creatName);
 	    int noticeTotal=noticeService.update(notice);
 		if(noticeTotal>0){
 		    map.put("code", 0);
 		}else{
 		    map.put("code", 1);
 		    map.put("msg", "保存出错，请稍后重试或者联系管理员。");
 		}
 		return map;
 	}
    @RequestMapping("/noticeOne.do")
    @ResponseBody
    public Object noticeOne(HttpServletRequest request,Model model){
    	log.info("noticeOne");
    	Map<String,Object> map = new HashMap<String,Object>();
		DbcontextHolder.setDbType("imagedb");
    	Notice notice =noticeService.noticeOne();
    	if(notice!=null){
    		map.put("code", 0);
    		map.put("msg", "");
    		map.put("data", notice);
		}else{
			map.put("code", 1);
			map.put("msg", "<img style='width:228px;height:228px' src='img/noneicon.png'>");
		}
		return notice;
    }
    @RequestMapping("/delNoticeById.do")
	@ResponseBody
	public Object delNoticeById(@RequestParam(value="id")String id,HttpServletResponse response){
		log.info("delNoticeById");
		Map<String,Object> map = new HashMap<String,Object>();
		int i =noticeService.delNoticeById(Integer.parseInt(id));
		if (i > 0) {
			map.put("code", 0);
		  } else {
			map.put("code", 1);
			map.put("msg", "删除失败！");
		  } 
		return map;
	}
    @RequestMapping("/noticehome.do")
  	public Object noticehome(HttpServletResponse response){
  		log.info("noticehome");
  		
  		return "/gongao/index";
  	}
    /**
     * 公告主页
     * @return
     * @throws Exception
     */
    @RequestMapping("/getnoticeList.do")
    @ResponseBody
    public Object index(@RequestParam(value="page",required=false)String page,
            @RequestParam(value="noticename",required=false)String noticeName,
            HttpServletRequest request,Model model)throws Exception{
        Map<String,Object> map = new HashMap<String,Object>();
        DbcontextHolder.setDbType("imagedb");
        if(StringUtil.isEmpty(page)){
            page="1";
        }
        PageBean pageBean=new PageBean(Integer.parseInt(page),10);
        Map<String,Object> querymap=new HashMap<String,Object>();
        
        querymap.put("noticeName", "%"+noticeName+"%");
        Long count = noticeService.getTotal(querymap);
        
        querymap.put("start", pageBean.getStart());
        querymap.put("size", pageBean.getPageSize());
        List<Notice> noticeList=noticeService.list(querymap);
        map.put("code", 0);
        map.put("noticeList", noticeList);
        map.put("count", count);
        map.put("page", page);
        return map;
    }
}
