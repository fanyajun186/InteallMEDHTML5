package com.inteall.image.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inteall.image.pojo.PageBean;
import com.inteall.image.pojo.Zhishiku;
import com.inteall.image.pojo.ZhishikuComment;
import com.inteall.image.service.ZhishikuCommentService;
import com.inteall.image.service.ZhishikuService;
import com.inteall.image.util.DbcontextHolder;
import com.inteall.image.util.StringUtil;

import net.sf.json.JSONArray;

/**
 * @author 韩明君
 * @date 2018年8月27日 上午10:48:49
 * @version 1.0 
 * @parameter 
 */
@Controller
@RequestMapping("/zhishiku")
public class ZhishikuController {
    @Resource
    private ZhishikuService zhishikuService;
    @Resource
    private ZhishikuCommentService zhishikuCommentService;
    /**
     * 请求主页
     * @return
     * @throws Exception
     */
    @RequestMapping("/getArticleList.do")
    @ResponseBody
    public Object index(@RequestParam(value="page",required=false)String page,
            @RequestParam(value="typeId",required=false)String typeId,
            @RequestParam(value="title",required=false)String title,
            @RequestParam(value="releaseDateStr",required=false)String releaseDateStr,
            HttpServletRequest request,Model model)throws Exception{
        Map<String,Object> map = new HashMap<String,Object>();
        DbcontextHolder.setDbType("imagedb");
        if(StringUtil.isEmpty(page)){
            page="1";
        }
        PageBean pageBean=new PageBean(Integer.parseInt(page),10);
        Map<String,Object> querymap=new HashMap<String,Object>();
        
        querymap.put("typeId", typeId);
        querymap.put("releaseDateStr", releaseDateStr);
        querymap.put("title", "%"+title+"%");
        Long count = zhishikuService.getTotal(querymap);
        
        querymap.put("curr", (pageBean.getPage()-1)*pageBean.getPageSize());
        querymap.put("limit", pageBean.getPageSize());
        List<Zhishiku> zhishikuList=zhishikuService.list(querymap);
        for(Zhishiku Zhishiku:zhishikuList){
            List<String> imagesList=Zhishiku.getImagesList();
            String ZhishikuInfo=Zhishiku.getContent();
            Document doc=Jsoup.parse(ZhishikuInfo);
            Elements jpgs=doc.select("img[src$=.jpg]"); //　查找扩展名是jpg的图片
            for(int i=0;i<jpgs.size();i++){
                Element jpg=jpgs.get(i);
                imagesList.add(jpg.toString());
                if(i==2){
                    break;
                }
            }
        }
        map.put("code", 0);
        map.put("zhishikuList", zhishikuList);
        map.put("count", count);
        map.put("page", page);
//        StringBuffer param=new StringBuffer(); // 查询参数
//        if(StringUtil.isNotEmpty(typeId)){
//            param.append("typeId="+typeId+"&");
//        }
//        if(StringUtil.isNotEmpty(releaseDateStr)){
//            param.append("releaseDateStr="+releaseDateStr+"&");
//        }
//        map.put("pageCode",PageUtil.genPagination(request.getContextPath()+"/zhishiku/getArticleList.do", zhishikuService.getTotal(map), Integer.parseInt(page), 10, param.toString()));
//        mav.addObject("mainPage", "foreground/Zhishiku/list.jsp");
//        mav.addObject("pageTitle","Java开源知识库系统");
//        mav.setViewName("mainTemp");
        return map;
    }
    
    /**
     * 请求知识库详细信息
     * @return
     * @throws Exception
     */
    @RequestMapping("/articles/{id}.do")
    public String details(@PathVariable("id") Integer id,HttpServletRequest request,Model model)throws Exception{
        DbcontextHolder.setDbType("imagedb");
        Zhishiku Zhishiku=zhishikuService.findById(id);
        String keyWords=Zhishiku.getKeyWord();
        if(StringUtil.isNotEmpty(keyWords)){
            String arr[]=keyWords.split(" ");
            model.addAttribute("keyWords",StringUtil.filterWhite(Arrays.asList(arr)));           
        }else{
            model.addAttribute("keyWords",null);         
        }
        model.addAttribute("Zhishiku", Zhishiku);
        Zhishiku.setClickHit(Zhishiku.getClickHit()+1); // 知识库点击次数加1
        zhishikuService.update(Zhishiku);
        List<ZhishikuComment> commentList = zhishikuCommentService.list(Zhishiku.getId());
        JSONArray json = JSONArray.fromObject(commentList);
        String html = json.toString();
        html = html.replaceAll("<", "&lt;");
        html = html.replaceAll(">", "&gt;");
        model.addAttribute("commentList",html); 
        model.addAttribute("pageCode", this.genUpAndDownPageCode(zhishikuService.getLastzhishiku(id),zhishikuService.getNextzhishiku(id),request.getServletContext().getContextPath()));
        return "zhishiku/detail";
    }
    
    /**
     * 获取下一篇博客和下一篇博客代码
     * @param lastZhishiku
     * @param nextZhishiku
     * @return
     */
    private String genUpAndDownPageCode(Zhishiku lastZhishiku,Zhishiku nextZhishiku,String projectContext){
        StringBuffer pageCode=new StringBuffer();
        if(lastZhishiku==null || lastZhishiku.getId()==null){
            pageCode.append("<p>上一篇：没有了</p>");
        }else{
            pageCode.append("<p>上一篇：<a href='"+projectContext+"/zhishiku/articles/"+lastZhishiku.getId()+".do'>"+lastZhishiku.getTitle()+"</a></p>");
        }
        if(nextZhishiku==null || nextZhishiku.getId()==null){
            pageCode.append("<p>下一篇：没有了</p>");
        }else{
            pageCode.append("<p>下一篇：<a href='"+projectContext+"/zhishiku/articles/"+nextZhishiku.getId()+".do'>"+nextZhishiku.getTitle()+"</a></p>");
        }
        return pageCode.toString();
    }
}
