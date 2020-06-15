package com.inteall.image.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.inteall.image.pojo.SysRight;
import com.inteall.image.pojo.SysRole;
import com.inteall.image.pojo.SysUser;
import com.inteall.image.service.LoginService;
import com.inteall.image.service.SysRoleRightService;
import com.inteall.image.service.SysUserRoleService;
import com.inteall.image.util.DbcontextHolder;
import com.inteall.image.util.MD5Encryption;


/**
 * @author 韩明君
 * @date 2018年3月29日 上午10:40:37
 * @version 1.0 
 * @parameter 
 */
@Controller
public class LoginController {
	private Logger logger = Logger.getLogger(LoginController.class);
	@Resource
	private LoginService loginService;
	@Resource
	private SysUserRoleService sysUserRoleService;
	@Resource
	private SysRoleRightService sysRoleRightService;
	
	
	/**
	 * 跳转到主界面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/index.do",method=RequestMethod.GET)
	public Object redirectAdd(HttpServletRequest request,Model model){
		HttpSession session = request.getSession();
		SysUser sysUser =  (SysUser) session.getAttribute("SysUser");
		if(sysUser==null){
		  	return "login";
		}
		request.setAttribute("userid", sysUser.getSysuserKey());
		request.setAttribute("username", sysUser.getUserName());
		return "index";
	}
	
	@RequestMapping(value="/queryMenu.do",method=RequestMethod.POST)
	@ResponseBody
	public Object queryMenu(HttpServletRequest request,Model model){
	  	Map<String,Object> map = new HashMap<String,Object>();
	  	DbcontextHolder.setDbType("imagedb");
		HttpSession session = request.getSession();
		SysUser sysUser =  (SysUser) session.getAttribute("SysUser");
		if(sysUser==null){
		  	return "login";
		}
		String userkey = sysUser.getSysuserKey();
		List<SysRole> roles = sysUserRoleService.getUserRole(userkey);
		List<SysRight> rights = new ArrayList<SysRight>();
		for(int i=0;i<roles.size();i++){
		  	SysRole sysRole = roles.get(i);
		  	rights.addAll( sysRoleRightService.getRoleRight(sysRole.getSysRoleKey()));
		  	
		}
		List<SysRight> tempList = new ArrayList<SysRight>();
		
		for(SysRight r1 : rights){
		  	boolean flag = false;
		  	for(SysRight r2 : tempList){
		  	  	if(r1.getSysRightKey().equals(r2.getSysRightKey())){
		  	  	  	flag = true;
		  	  	  	break;
		  	  	}
		  	}
		  	if(!flag&&(r1.getRightUrl()!=null&&!r1.getRightUrl().equals(""))){
		  	  	tempList.add(r1);
		  	}
		  	
		}
		map.put("code", 0);
  		map.put("msg", "");
  		map.put("right", tempList);
		return map;
	}

	/**
	 * 登录验证
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/loginCheck.do",method=RequestMethod.POST)
	@ResponseBody
	public Object loginCheck(HttpServletRequest request,Model model){
	  	logger.info("loginCheck");
	  	
	  	Map<String,Object> map = new HashMap<String,Object>();
	  	DbcontextHolder.setDbType("imagedb");
		String loginname = request.getParameter("loginname");
		String password = request.getParameter("password");
		SysUser sysUser = loginService.login(loginname);
		if(sysUser==null){
			map.put("code", 1);
			map.put("msg", "用户不存在,请重试！");
		}else{
    		boolean flag = MD5Encryption.checkPassWord(password, sysUser.getPassword());
    		if(flag){
    			HttpSession session = request.getSession();
    			session.setAttribute("SysUser", sysUser);
    			request.setAttribute("username", sysUser.getUserName());
  	    		map.put("code", 0);
  	    		map.put("msg", "");
    				
    		}else{
    		  	map.put("code", 1);
    			map.put("msg", "登录名和密码不一致，请重试！");
    		}
		}
		return map;
	}
	@RequestMapping(value="/loginFind.do",method=RequestMethod.GET)
	public Object loginFind(HttpServletRequest request,Model model){
		return "login";
	}
	@RequestMapping(value="/loginout.do",method=RequestMethod.GET)
	public Object loginout(HttpServletRequest request,Model model){
		request.getSession().removeAttribute("sysUser");
		request.getSession().invalidate();
		return "login";
	}
	@RequestMapping(value="/error_404.do",method=RequestMethod.GET)
    public Object error_404(HttpServletRequest request,Model model){
        return "error/404";
    }
	@RequestMapping(value="/error_500.do",method=RequestMethod.GET)
    public Object error_500(HttpServletRequest request,Model model){
        return "error/500";
    }
}