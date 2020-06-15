package com.inteall.image.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.omg.CORBA.portable.ApplicationException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.inteall.image.pojo.SysRole;
import com.inteall.image.pojo.SysUser;
import com.inteall.image.pojo.SysUserRole;
import com.inteall.image.service.MedicalRecordService;
import com.inteall.image.service.SysUserRoleService;
import com.inteall.image.service.SysUserService;
import com.inteall.image.util.BigAntUtil;
import com.inteall.image.util.DbcontextHolder;
import com.inteall.image.util.MD5Encryption;
import com.inteall.image.util.UUid;
import com.inteall.image.util.Util;

import net.sf.json.JSONObject;


/** 
* @author 韩明君  
* @date 创建时间：2018年2月7日 下午1:17:38 
* @version 1.0 
* @parameter  
*/
@Controller
@RequestMapping("/sysuser")
public class SysUserController {
	private static Logger log = Logger.getLogger(SysUserController.class.getName());
	@Resource
	private SysUserService sysuserService;
	@Resource
	private SysUserRoleService sysUserRoleService;
	@Resource
	private MedicalRecordService medicalRecordService;
	private String ssid = "E6300170-E5B0-B072-E18D-74A870934D48";
	private String uid = "116";
	private String uname = "电子客服";
	private BigAntUtil bigAntUtil = new BigAntUtil(ssid, uid, uname);
	/**
	 * 校验登录名是否重复
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/checkLoginName.do",method=RequestMethod.GET)
	@ResponseBody
	public Object checkLoginName(@RequestParam String loginName,HttpServletRequest request,Model model){
		Map<String,Object> map = new HashMap<String,Object>();
		DbcontextHolder.setDbType("imagedb");
		int i = sysuserService.checkLoginName(loginName);
		
		
		map.put("code", 0);
  		map.put("count", i);
		return map;
	}
	/**
	 * 跳转到添加用户界面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/redirectAdd.do",method=RequestMethod.GET)
	public Object redirectAdd(HttpServletRequest request,Model model){
		return "xitongshezhi/sysuser_add";
	}
	
	/**
	 * 根据用户的id查询用户信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/getById.do",method=RequestMethod.GET)
	public Object getById(@RequestParam String id,HttpServletRequest request,Model model){
		log.info("getById");
		
		Gson gson = new Gson();
		
		SysUser sysuser = sysuserService.getById(id);
		List<SysRole> sysUserRoles = sysUserRoleService.getUserRole(id);
		model.addAttribute("sysuser", gson.toJson(sysuser));
		model.addAttribute("sysUserRoles", gson.toJson(sysUserRoles));
		return "xitongshezhi/sysuser_edit";
	}
	
	/**
	 * 查询用户信息
	 * @return
	 */
	@RequestMapping(value="/getAll.do",method=RequestMethod.POST)
	@ResponseBody
	public Object getAll(HttpServletRequest request,Model model){
		log.info("getAll");
		int page = Integer.parseInt(request.getParameter("page"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		String user_name = request.getParameter("user_name");
		String login_name = request.getParameter("login_name");
		
		Map<String,Object> map = new HashMap<String,Object>();
		DbcontextHolder.setDbType("imagedb");
		SysUser sysUser = new SysUser();
		sysUser.setUserName(user_name);
		sysUser.setLoginName(login_name);
		
		//先查询满足条件的数据有多少条
		int count = sysuserService.getCount(sysUser);
		sysUser.setLimit(limit);
		sysUser.setCurr((page-1)*limit);
		
		List<SysUser> sysuserList = sysuserService.getAll(sysUser);
		if(sysuserList!=null&&sysuserList.size()>0){
    		map.put("code", 0);
    		map.put("msg", "");
    		map.put("count", count);
    		map.put("data", sysuserList);
		}else{
			map.put("code", 1);
			map.put("msg", "<img style='width:228px;height:228px' src='img/noneicon.png'>");
		}
		return map;
	}
	
	/**
	 * 根据用户id删除用户
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delById.do",method=RequestMethod.GET)
	@ResponseBody
	public Object delById(@RequestParam String id){
		log.info("delById");
		Map<String,Object> map = new HashMap<String,Object>();
		DbcontextHolder.setDbType("imagedb");
		SysUser sysUser = new SysUser();
		sysUser.setSysuserKey(id);
		sysUser.setIsDel("1");
		sysUser.setDelTime(new Date());
		
		int count = sysuserService.updateById(sysUser);
		if(count>0){
    		map.put("code", 0);
    		map.put("msg", "删除成功！");
		}else{
			map.put("code", 1);
			map.put("msg", "删除失败！");
		}
		return map;
	}
	
	/**
	 * 新增用户信息
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/save.do",method=RequestMethod.POST)
	@ResponseBody
	@Transactional
	public Object save(HttpServletRequest request,Model model){
		log.info("save");
		Map<String,Object> map = new HashMap<String,Object>();
		DbcontextHolder.setDbType("imagedb");
		
		String json = request.getParameter("json");
		Gson gson = new Gson();
		SysUser sysUser = gson.fromJson(json, SysUser.class);
		String userkey = UUid.getUUID();
		sysUser.setSysuserKey(userkey);
		String password = sysUser.getPassword();
		sysUser.setPassword(MD5Encryption.SHA(password, "MD5"));
		sysUser.setCreateTime(new Date());
		sysUser.setIsDel("0");
		
		//获取选中角色id
		JsonObject returnData = new JsonParser().parse(json).getAsJsonObject();
		JsonArray sysRole = returnData.getAsJsonArray("sysRole");
		
		
		List<SysUserRole> addRoles = new ArrayList<SysUserRole>();
		for(int i=0;i<sysRole.size();i++){
			SysUserRole sysUserRole = new SysUserRole();
			sysUserRole.setSys_user_role_key(UUid.getUUID());
			sysUserRole.setCreate_time(new Date());
			sysUserRole.setSys_role_key(sysRole.get(i).getAsString());
			sysUserRole.setSys_user_key(userkey);
			sysUserRole.setIs_del("0");
			addRoles.add(sysUserRole);
		}
		
		int userCount = sysuserService.save(sysUser);
		int roleCount = sysUserRoleService.addUserRole(addRoles);
		
		if(userCount>0&&roleCount>0){
    		map.put("code", 0);
    		map.put("msg", "");
		}else{
			map.put("code", 1);
			map.put("msg", "保存失败！");
		}
		return map;
	}
	
	/**
	 * 根据用户id修改用户信息
	 * @param id
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/updateById.do",method=RequestMethod.POST)
	@ResponseBody
	@Transactional
	public Object updateById(HttpServletRequest request,Model model){
		log.info("updateById");
		Map<String,Object> map = new HashMap<String,Object>();
		DbcontextHolder.setDbType("imagedb");
		
		String json = request.getParameter("json");
		Gson gson = new Gson();
		SysUser sysUser = gson.fromJson(json, SysUser.class);
		sysUser.setModifyTime(new Date());
		String password = sysUser.getPassword();
		sysUser.setPassword(MD5Encryption.SHA(password, "MD5"));
		
		//获取选中角色id
		JsonObject returnData = new JsonParser().parse(json).getAsJsonObject();
		JsonArray sysRole = returnData.getAsJsonArray("sysRole");
				
				
		List<SysUserRole> addRoles = new ArrayList<SysUserRole>();
		for(int i=0;i<sysRole.size();i++){
		  	SysUserRole sysUserRole = new SysUserRole();
			sysUserRole.setSys_user_role_key(UUid.getUUID());
			sysUserRole.setCreate_time(new Date());
			sysUserRole.setSys_role_key(sysRole.get(i).getAsString());
			sysUserRole.setSys_user_key(sysUser.getSysuserKey());
			sysUserRole.setIs_del("0");
			addRoles.add(sysUserRole);
		}
		
		sysUserRoleService.delUserRole(sysUser.getSysuserKey());
		int userCount = sysuserService.updateById(sysUser);
		int roleCount = sysUserRoleService.addUserRole(addRoles);
		
		if(roleCount>0&&userCount>0){
    		map.put("code", 0);
    		map.put("msg", "");
		}else{
			map.put("code", 1);
			map.put("msg", "保存失败！");
		}
		return map;
	}
	  @RequestMapping(value="/personal.do")
	  public String personal(HttpServletRequest request,Model model){
		  HttpSession session = request.getSession();
		  	SysUser sysUser =  (SysUser) session.getAttribute("SysUser");
		  	String id=sysUser.getSysuserKey();
		 	Gson gson = new Gson();
			SysUser sysuser = sysuserService.getById(id);
			List<SysRole> sysUserRoles = sysUserRoleService.getUserRole(id);
			model.addAttribute("sysuser", gson.toJson(sysuser));
			model.addAttribute("sysUserRoles", gson.toJson(sysUserRoles));
			return "sysuser/personal";
	  }
	  @RequestMapping(value="/password")
	  public String password(HttpServletRequest request,Model model){
		  HttpSession session = request.getSession();
		  	SysUser sysUser =  (SysUser) session.getAttribute("SysUser");
		  	String sysuserKey=sysUser.getSysuserKey();
		  	model.addAttribute("sysuserKey",sysuserKey);
		return "sysuser/password";
	  }
	  /**
		 * 根据用户id修改用户个人信息
		 * @param id
		 * @param request
		 * @param model
		 * @return
		 */
		@RequestMapping(value="/updateByuserId.do",method=RequestMethod.POST)
		@ResponseBody
		@Transactional
		public Object updateByuserId(HttpServletRequest request,Model model){
			log.info("updateByuserId");
			Map<String,Object> map = new HashMap<String,Object>();
			DbcontextHolder.setDbType("imagedb");
			
			String json = request.getParameter("json");
			Gson gson = new Gson();
			SysUser sysUser = gson.fromJson(json, SysUser.class);
			sysUser.setModifyTime(new Date());
			int userCount = sysuserService.updateById(sysUser);
			if(userCount>0){
	    		map.put("code", 0);
	    		map.put("msg", "");
			}else{
				map.put("code", 1);
				map.put("msg", "保存失败！");
			}
			return map;
		}
		/**
		 * 用户修改密码
		 * @param request
		 * @param response
	 	 * @author Harry 
		 * @return 
		 */
		@RequestMapping(value="/changePassword.do",method=RequestMethod.POST)
		@ResponseBody
		private Object changePassword(HttpServletRequest request,HttpServletResponse response) throws IOException{
			log.info("updateByuserId");
			Map<String,Object> map = new HashMap<String,Object>();
			DbcontextHolder.setDbType("imagedb");
			//找到保存到session中的登录用户
			HttpSession session = request.getSession();
			SysUser sysUser =  (SysUser) session.getAttribute("SysUser");
			String json = request.getParameter("json");
			JSONObject returnData = JSONObject.fromObject(json);
			String sysuserKey = returnData.getString("sysuserKey");//id
			String oldpwd = returnData.getString("oldpwd");//旧密码
			String newpwd = returnData.getString("newpwd");//新密码
			String confirm = returnData.getString("confirm");//确认新密码
			
			String pwd = sysUser.getPassword();//得到该客户当前的字符串
			String MDoldpwd = MD5Encryption.SHA(oldpwd, "MD5");
			try{
				if(MDoldpwd.equals(pwd)){ //输入的旧密码与原密码一致
					if(newpwd.equals(confirm)){//判断输入的两个新密码是否一致
						if(!(MD5Encryption.SHA(newpwd, "MD5").equals(pwd))){//如果新密码与原密码不同，执行更新密码操作
							sysUser.setPassword(MD5Encryption.SHA(newpwd, "MD5"));
							SysUser user = new SysUser();
							user.setPassword(MD5Encryption.SHA(newpwd, "MD5"));
							user.setSysuserKey(sysuserKey);
							int i=sysuserService.updatePassword(user);
							bigAntUtil.set_password(sysUser.getLoginName(), newpwd);
							if(i>0){
								map.put("code",0);
								map.put("msg", "密码修改成功");
							}
						}else if(MD5Encryption.SHA(newpwd,"MD5").equals(pwd)){
							map.put("msg", "密码没有改动");
						}
					}else{
						map.put("msg","抱歉，密码输入不一致");
					}
				}else{
					map.put("msg","旧密码输入错误");
				}
			}catch(Exception e){
				request.setAttribute("error", e);
		}
			return map;	
		}
		
		//用户电子签名照上传
		@RequestMapping(value="/uploadQianming.do",method=RequestMethod.POST)
		@ResponseBody
		public Map<String,Object> uploadQianming(@RequestParam("qianming") MultipartFile file){
			
			DbcontextHolder.setDbType("imagedb");
			  
		    Map<String, Object> map = new HashMap<String, Object>();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmssSSS");
			SimpleDateFormat ymd = new SimpleDateFormat("yyyyMMdd");

			Properties prop = Util.readData("path.properties");
			String savePath = prop.getProperty("autograph").trim()+ymd.format(new Date())+"/";// 获取文件保存路径
				
			//String savePath=request.getSession().getServletContext().getRealPath("/video/");
				
			String fileName = file.getOriginalFilename();// 上传文件的名称
			String pre = fileName.substring(fileName.lastIndexOf(".") + 1);// 后缀名
				
					
			File pathFile = new File(savePath);// 建文件夹
			if (!pathFile.exists()) {
				pathFile.mkdirs();
			}
			String newFileName = sdf.format(new Date()) + "_" + file.getOriginalFilename();
			String newFilePath = savePath + newFileName;// 新路径
			File newFile = new File(newFilePath);
			if (newFile.exists()) {
				newFilePath = savePath + newFileName;
				newFile = new File(newFilePath);
			}
			try {
				file.transferTo(newFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			map.put("qmName", fileName);
			map.put("qmUrl", newFilePath);
			
			return map;
		}

}
