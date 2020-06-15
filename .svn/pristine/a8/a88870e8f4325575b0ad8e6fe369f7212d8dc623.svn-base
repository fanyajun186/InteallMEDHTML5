package com.inteall.image.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inteall.image.pojo.SysUser;
import com.inteall.image.pojo.Zhishiku;
import com.inteall.image.pojo.ZhishikuComment;
import com.inteall.image.service.ZhishikuCommentService;
import com.inteall.image.service.ZhishikuService;

/**
 * 评论Controller层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/zhishikucomment")
public class ZhishikuCommentController {
    
    @Resource
    private ZhishikuCommentService zhishikucommentService;
    
    @Resource
    private ZhishikuService zhishikuService;
    
    /**
     * 添加或者修改评论
     * @param comment
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/save.do")
    @ResponseBody
    public Object save(ZhishikuComment comment,HttpServletRequest request,HttpServletResponse response)throws Exception{
        Map<String,Object> map = new HashMap<String,Object>();
        HttpSession session = request.getSession();
        SysUser sysUser =  (SysUser) session.getAttribute("SysUser");
        
        int resultTotal=0; // 操作的记录条数
        String userIp=request.getRemoteAddr(); // 获取用户IP
        comment.setUserIp(userIp);
        comment.setCommentPersonId(sysUser.getSysuserKey());
        comment.setCommentPersonName(sysUser.getUserName());
        resultTotal=zhishikucommentService.add(comment);
        // 该知识库的回复次数加1
        Zhishiku zhishiku=zhishikuService.findById(comment.getZhishiku().getId());
        zhishiku.setReplyHit(zhishiku.getReplyHit()+1);
        zhishikuService.update(zhishiku);
        if(resultTotal>0){
            map.put("code", 0);
        }else{
            map.put("code", 1);
            map.put("msg", "评论失败，请稍后重试或者联系管理员！");
        }
        return map;
    }

}
