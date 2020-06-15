package com.inteall.image.util;

/**
 * @author 韩明君
 * @date 2018年3月7日 下午3:44:39
 * @version 1.0
 * @parameter
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class BigAntUtil {
  /*
   * To change this license header, choose License Headers in Project
   * Properties. To change this template file, choose Tools | Templates and open
   * the template in the editor.
   */

  // 开发者需要这里----------------
  private String appId	   = "1510556939";
  private String appSecret = "127AD8FE7FD10C9";
  private String dataType  = "json";
  private String restSite  = "http://47.95.20.236:8000";

  private String ssid  = "";
  private String uid   = "";
  private String uname = "";

  public BigAntUtil(String ssid, String uid, String uname) {
	this.ssid = ssid;
	this.uid = uid;
	this.uname = uname;
  }

  /**
   * 验证令牌的详细信息
   */
  public String getTokenInfo(String token) {
	String param = "token=" + token;
	String res = this.sendRest("oauth", "get_token_info", param);
	return res;
  }

  // public String creatToken()

  /**
   * 调用REST接口
   * 
   * @param module
   *          模块 例 User
   * @param method
   *          方法 例 info
   * @param param
   *          参数
   * @return 所代表远程资源的响应结果
   */
  public String sendRest(String module, String method, String param) {
	String url = this.restSite + "/api/" + module + "/" + method + ".html";
	param += "&ssid=" + this.ssid;
	param += "&uid=" + this.uid;
	param += "&uname=" + this.uname;
	param += "&app_id=" + this.appId;
	param += "&data_type=" + this.dataType;
	param += "&authen=" + this.getAuthen();
	System.out.println("url::::" + url);
	System.out.println("请求：：：" + param);
	// 请求数据
	String res = sendPost(url, param);
	// 转成UTF8编码
	try {
	  res = new String(res.getBytes(), "utf8");
	} catch (Exception e) {
	}
	return res;
  }
  /**
   * 向指定 URL 发送POST方法的请求
   * 
   * @param url
   *          发送请求的 URL
   * @param param
   *          请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
   * @return 所代表远程资源的响应结果
   */
  public static String sendPost(String url, String param) {
	PrintWriter out = null;
	BufferedReader in = null;
	String result = "";
	try {
	  URL realUrl = new URL(url);
	  // 打开和URL之间的连接
	  URLConnection conn = realUrl.openConnection();
	  // 设置通用的请求属性
	  conn.setRequestProperty("accept", "*/*");
	  conn.setRequestProperty("connection", "Keep-Alive");
	  conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
	  // 发送POST请求必须设置如下两行
	  conn.setDoOutput(true);
	  conn.setDoInput(true);
	  // 获取URLConnection对象对应的输出流
	  out = new PrintWriter(conn.getOutputStream());
	  // 发送请求参数
	  out.print(param);
	  // flush输出流的缓冲
	  out.flush();
	  // 定义BufferedReader输入流来读取URL的响应
	  in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	  String line;
	  while ((line = in.readLine()) != null) {
		result += line;
	  }
	} catch (Exception e) {
	  System.out.println("发送 POST 请求出现异常！" + e);
	  e.printStackTrace();
	}
	// 使用finally块来关闭输出流、输入流
	finally {
	  try {
		if (out != null) {
		  out.close();
		}
		if (in != null) {
		  in.close();
		}
	  } catch (IOException ex) {
		ex.printStackTrace();
	  }
	}
	return result;
  }

  /**
   * 得到认证码
   * @return 认证码
   */
  public String getAuthen() {
	String text = this.appId + this.appSecret + this.ssid + this.uid;
	return SHA(text, "SHA-256");
  }

  /**
   * 字符串 SHA 加密
   * 
   * @param strSourceText
   * @return
   */
  private String SHA(final String strText, final String strType) {
	// 返回值
	String strResult = null;

	// 是否是有效字符串
	if (strText != null && strText.length() > 0) {
	  try {
		// SHA 加密开始
		// 创建加密对象 并傳入加密類型
		MessageDigest messageDigest = MessageDigest.getInstance(strType);
		// 传入要加密的字符串
		messageDigest.update(strText.getBytes());
		// 得到 byte 類型结果
		byte byteBuffer[] = messageDigest.digest();

		// 將 byte 轉換爲 string
		StringBuffer strHexString = new StringBuffer();
		// 遍歷 byte buffer
		for (int i = 0; i < byteBuffer.length; i++) {
		  String hex = Integer.toHexString(0xff & byteBuffer[i]);
		  if (hex.length() == 1) {
			strHexString.append('0');
		  }
		  strHexString.append(hex);
		}
		// 得到返回結果
		strResult = strHexString.toString();
	  } catch (NoSuchAlgorithmException e) {
		e.printStackTrace();
	  }
	}
	return strResult;
  }

  public String getinfo(String name) {
	String authen = this.getAuthen();
	String info = "ssid=E6300170-E5B0-B072-E18D-74A870934D48&uid=116&uname=电子客服&app_id=1510556939&authen=" + authen
	    + "&user_login=" + name;
	String user = this.sendRest("message", "send", info);
	System.out.println(user);
	return "";
  }
  /**
   * 查看人员信息
   */
  public String get_infouser(String name) {
	  String user = this.get_infouser("user", "get_info", name);
	  System.out.println(user);
	  return user;
  }
  /**
   * 
   */
  public String get_GroupInfoByUserId(String name) {
	  String authen = this.getAuthen();
	  String info = "ssid=E6300170-E5B0-B072-E18D-74A870934D48&uid=116&uname=电子客服&app_id=1510556939&authen=" + authen
			    + "&user_login=" + name;
	  String user = this.get_GroupInfoByUserId("group", "get_info", info,"00504750-87F1-4481-99DB-1C36B50DD8A1");
	  System.out.println(user);
	  return "";
  }
  /**
   * 根据群ID查询成员
   */
  public String get_GroupInfo(String groupid) {
	  String user = this.get_GroupInfo("group", "get_info",  groupid);
	  System.out.println(user);
	  return "";
  }
  /**
   * 用户修改密码
   */
  public String set_password(String name,String password) {
		String msg = this.set_password("user", "set_password",name,password);
		System.out.println(msg);
		return msg;
	  }
  /**
   * 启动汇沟通
   */
  public String get_imstart( String loginname, String valid_code) {
		String msg = this.get_imstart("common", "imstart", "login", "47.95.20.236", "6661", "inteall", loginname,valid_code, "1");
		System.out.println(msg);
		return msg;
	  }
  /**
   * 向群组发送消息
   */
  public String send_group(String userlogin,String group_id,String subject,String content) {
	  String msg = this.send_group("message", "send_group", userlogin, group_id, subject, content);
	  System.out.println(msg);
	  return msg;
  }
  /**
   * 根据登录账号获取userid
   */
  public String get_uid(String user_login) {
	String param="";
	String url = this.restSite + "/api/" + "user" + "/" + "get_uid" + ".html";
	param += "&ssid=" + this.ssid;
	param += "&uid=" + this.uid;
	param += "&uname=" + this.uname;
	param += "&app_id=" + this.appId;
	param += "&data_type=" + this.dataType;
	param += "&authen=" + this.getAuthen();
	param += "&user_login=" + user_login; 
	System.out.println("url::::" + url);
	System.out.println("请求：：：" + param);
	// 请求数据
	String res = sendPost(url, param);
	// 转成UTF8编码
	try {
	  res = new String(res.getBytes(), "utf8");
	} catch (Exception e) {
	}
	System.out.println(res);
	return res;
  }
  /**
   * url消息发送
   */
  public String send_url(String recver_user_id,String url,String title,String desc) {
	  String msg = this.send_url("message", "send_url",  url, uid, desc, recver_user_id, title, "/IMG.CC");
	  System.out.println(msg);
	  return msg;
  }
  public String send_send(String name) {
	  String authen = this.getAuthen();
	  String info = "ssid=E6300170-E5B0-B072-E18D-74A870934D48&uid=116&uname=电子客服&app_id=1510556939&authen=" + authen
			  + "&user_login=" + name;
	  String user = this.send_send("message", "send", info, "116", "168", "通知", "会诊");
	  System.out.println(user);
	  return "";
  }
  /**
   * 
   * @param recver_user_login多个接收者用，隔开
   * @param subject
   * @param content
   * @return
   */
  public String send_user(String recver_user_login,String subject,String content) {
	  String msg = this.send_user("message", "send_user", "service", recver_user_login, subject, content);
	  System.out.println(msg);
	  return msg;
  }
  /**
   * 	用户修改密码
   * @param module	模块 例 User
   * @param method	方法 例 info
   * @param param	参数
   * @param user_login 用户帐号
   * @param user_pwd	修改的密码
   * @return
   */
  public String set_password(String module, String method,String user_login,String user_pwd) {
	  String param="";
	  String url = this.restSite + "/api/" + module + "/" + method + ".html";
	  param += "&ssid=" + this.ssid;
	  param += "&uid=" + this.uid;
	  param += "&uname=" + this.uname;
	  param += "&app_id=" + this.appId;
	  param += "&data_type=" + this.dataType;
	  param += "&authen=" + this.getAuthen();
	  param += "&user_login=" + user_login; 
	  param += "&user_pwd=" + user_pwd;
	  param += "&user_pwd_type=1";
	  System.out.println("url::::" + url);
	  System.out.println("请求：：：" + param);
	  // 请求数据
	  String res = sendPost(url, param);
	  // 转成UTF8编码
	  try {
		  res = new String(res.getBytes(), "utf8");
	  } catch (Exception e) {
	  }
	  return res;
  }
  /**
   * 	url发送消息(自定义)
   * @param module	模块 例 User
   * @param method	方法 例 info
   * @param zurl	链接地址
   * @param userid	发送者 
   * @param recverid	接收者id
   * @param title	标题
   * @param desc	描述
   * @param icon	图标
   * @param target	链接地址
   * @return
   */
  public String send_url(String module, String method, String zurl, String userid,String desc, String recverid, String title,String icon) {
	String param="";
	try {
    	String url = this.restSite + "/api/" + module + "/" + method + ".html";
    	param += "&ssid=" + this.ssid;
    	param += "&uid=" + this.uid;
    	param += "&uname=" + this.uname;
    	param += "&app_id=" + this.appId;
    	param += "&data_type=" + this.dataType;
    	param += "&authen=" + this.getAuthen();
    	param += "&sender=" + userid; 
    	param += "&recver=" + recverid;
    	param += "&title=" + URLEncoder.encode(title,"UTF-8");
    	param += "&desc=" + URLEncoder.encode(desc,"UTF-8");
    	param += "&icon="+ icon;
    	param += "&target="+zurl;
    	param += "&target_type=2";
	
        System.out.println("url::::" + url);
        System.out.println("请求：：：" + param);
        // 请求数据
        String res = sendPost(url, param);
        // 转成UTF8编码
        res = new String(res.getBytes(), "utf8");
        return res;
    } catch (UnsupportedEncodingException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
        return "";
    }
	
	
  }
  /**
   * 	向群组发送消息
   * @param module	模块 例 User
   * @param method	方法 例 info
   * @param param	参数
   * @param userl_ogin 发送者账号
   * @param groupid	接收者(群组：群组id)
   * @param subject	标题
   * @param content	内容
   * @return 
   */
  public String send_group(String module, String method,  String userl_ogin, String groupid, String subject,String content) {
      try {
          String param="";  
    	  String url = this.restSite + "/api/" + module + "/" + method + ".html";
    	  param += "&ssid=" + this.ssid;
    	  param += "&uid=" + this.uid;
    	  param += "&uname=" + this.uname;
    	  param += "&app_id=" + this.appId;
    	  param += "&data_type=" + this.dataType;
    	  param += "&authen=" + this.getAuthen();
    	  param += "&send_user=" + userl_ogin; 
    	  param += "&recver=" + groupid;
    	  param += "&subject=" + URLEncoder.encode(subject,"UTF-8");
    	  param += "&content="+ URLEncoder.encode(content,"UTF-8");
    	  System.out.println("url::::" + url);
    	  System.out.println("请求：：：" + param);
    	  // 请求数据
    	  String res = sendPost(url, param);
    	  // 转成UTF8编码
	  
		  res = new String(res.getBytes(), "utf8");
		  return res;
	  } catch (Exception e) {
	      e.printStackTrace();
	      return "";
	  }
	 
  }
  /**
   * 	普通发送消息根据ID
   * @param module	模块 例 User
   * @param method	方法 例 info
   * @param param	参数
   * @param userid	发送者 
   * @param recverid	接收者id
   * @param subject	标题
   * @param content	内容
   * @return
   */
  public String send_send(String module, String method, String param, String userid, String recverid, String subject,String content) {
        try {
            String url = this.restSite + "/api/" + module + "/" + method + ".html";
            param += "&ssid=" + this.ssid;
            param += "&uid=" + this.uid;
            param += "&uname=" + this.uname;
            param += "&app_id=" + this.appId;
            param += "&data_type=" + this.dataType;
            param += "&authen=" + this.getAuthen();
            param += "&sender=" + userid;
            param += "&recver=" + recverid;
            param += "&subject=" + URLEncoder.encode(subject, "UTF-8");
            param += "&content=" + URLEncoder.encode(content, "UTF-8");
            param += "&content_type=Text/Text";
            param += "&msg_type=0";
            System.out.println("url::::" + url);
            System.out.println("请求：：：" + param);
            // 请求数据
            String res = sendPost(url, param);
            // 转成UTF8编码

            res = new String(res.getBytes(), "utf8");
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        
    }
  /**
   * 	普通发送消息根据账号
   * @param module	模块 例 User
   * @param method	方法 例 info
   * @param user_login	发送者 
   * @param recver_user_login	接收者
   * @param subject	标题
   * @param content	内容
   * @return
   */
  public String send_user(String module, String method, String user_login, String recver_user_login, String subject,String content) {
        String param = "";
        try {
            String url = this.restSite + "/api/" + module + "/" + method + ".html";
            param += "&ssid=" + this.ssid;
            param += "&uid=" + this.uid;
            param += "&uname=" + this.uname;
            param += "&app_id=" + this.appId;
            param += "&data_type=" + this.dataType;
            param += "&authen=" + this.getAuthen();
            param += "&send_user=" + user_login;
            param += "&recver_user=" + recver_user_login;
            param += "&subject=" + URLEncoder.encode(subject, "UTF-8");
            ;
            param += "&content=" + URLEncoder.encode(content, "UTF-8");
            ;
            param += "&content_type=Text/Text";
            param += "&msg_type=0";
            System.out.println("url::::" + url);
            System.out.println("请求：：：" + param);
            // 请求数据
            String res = sendPost(url, param);
            // 转成UTF8编码

            res = new String(res.getBytes(), "utf8");
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
	 
  }
  /**
   *  根据用户编号查询群信息
   * @param module	模块 例 User
   * @param method	方法 例 info
   * @param param	参数
   * @param group_id	群id
   * @return
   */
  public String get_GroupInfoByUserId(String module, String method, String param, String group_id) {
	  String url = this.restSite + "/api/" + module + "/" + method + ".html";
	  param += "&ssid=" + this.ssid;
	  param += "&uid=" + this.uid;
	  param += "&uname=" + this.uname;
	  param += "&app_id=" + this.appId;
	  param += "&data_type=" + this.dataType;
	  param += "&authen=" + this.getAuthen();
	  param += "&group_id=" + group_id;
	  System.out.println("url::::" + url);
	  System.out.println("请求：：：" + param);
	  // 请求数据
	  String res = sendPost(url, param);
	  // 转成UTF8编码
	  try {
		  res = new String(res.getBytes(), "utf8");
	  } catch (Exception e) {
	  }
	  return res;
  }
  /**
   * 查询群信息成员id
   * @param module	模块 例 User
   * @param method	方法 例 info
   * @param param	参数
   * @param group_id	群id
   * @return
   */
  public String get_GroupInfo(String module, String method, String group_id) {
	  String param = "";
	  String url = this.restSite + "/api/" + module + "/" + method + ".html";
	  param += "&ssid=" + this.ssid;
	  param += "&uid=" + this.uid;
	  param += "&uname=" + this.uname;
	  param += "&app_id=" + this.appId;
	  param += "&data_type=" + this.dataType;
	  param += "&authen=" + this.getAuthen();
	  param += "&group_id=" + group_id;
	  System.out.println("url::::" + url);
	  System.out.println("请求：：：" + param);
	  // 请求数据
	  String res = sendPost(url, param);
	  // 转成UTF8编码
	  try {
		  res = new String(res.getBytes(), "utf8");
	  } catch (Exception e) {
	  }
	  return res;
  }
  /**
   * 	根据账号查看人员信息
   * @param module	模块 例 User
   * @param method	方法 例 info
   * @param param	参数
   * @param user_login 用户帐号
   * @return
   */
  public String get_infouser(String module, String method,  String user_login) {
	  String url = this.restSite + "/api/" + module + "/" + method + ".html";
	  String param = "";
	  param += "&ssid=" + this.ssid;
	  param += "&uid=" + this.uid;
	  param += "&uname=" + this.uname;
	  param += "&app_id=" + this.appId;
	  param += "&data_type=" + this.dataType;
	  param += "&authen=" + this.getAuthen();
	  param += "&user_login=" + user_login; 
	  System.out.println("url::::" + url);
	  System.out.println("请求：：：" + param);
	  // 请求数据
	  String res = sendPost(url, param);
	  // 转成UTF8编码
	  try {
		  res = new String(res.getBytes(), "utf8");
	  } catch (Exception e) {
	  }
	  return res;
  }
  /**
   * 启动客户端
   * @param module	模块 例 User
   * @param method	方法 例 info
   * @param param	参数
   * @param user_login 用户帐号
   * @return
   */
  public String get_imstart(String module, String method,  String type,String server,String port,
		  String ssname, String loginname, String valid_code, String valid_type) {
	  String url = this.restSite + "/api/" + module + "/" + method + ".html";
	  String param = "";
	  param += "&ssid=" + this.ssid;
	  param += "&uid=" + this.uid;
	  param += "&uname=" + this.uname;
	  param += "&app_id=" + this.appId;
	  param += "&data_type=" + this.dataType;
	  param += "&authen=" + this.getAuthen();
	  param += "&type=" + type;
	  param += "&server=" + server;
	  param += "&port=" + port;
	  param += "&ssname=" + ssname;
	  param += "&loginname=" + loginname;
	  param += "&valid_code=" + valid_code;
	  param += "&valid_type=" + valid_type;
	  System.out.println("url::::" + url);
	  System.out.println("请求：：：" + param);
	  // 请求数据
	  String res = sendPost(url, param);
	  // 转成UTF8编码
	  try {
		  res = new String(res.getBytes(), "utf8");
	  } catch (Exception e) {
	  }
	  return res;
  }
  public static void main(String[] args) {
	String ssid = "E6300170-E5B0-B072-E18D-74A870934D48";
	String uid = "116";
	String uname = "电子客服";
	BigAntUtil bigAnt = new BigAntUtil(ssid, uid, uname);
	bigAnt.get_imstart("yjm", "");
	/*bigAnt.set_password("yjm","yjm");*/
	/*String url = "http://timer.91veo.com/v1/meeting/join?id=367457&value=4190b0f691b850fc6004a9a653efc5ee&uname=测试用户";
	try {
	  url=URLEncoder.encode(url,"UTF-8");
	} catch (UnsupportedEncodingException e) {
	  // TODO Auto-generated catch block
	  e.printStackTrace();
	}*/
	/*bigAnt.get_uid("hmj");
	bigAnt.send_url("111", url, "读片会通知", "韩明君于2017/12/4创建了一个读片，请你届时参加！");*/
//	bigAnt.send_url("111", "www.baidu.com", "会诊通知","韩明君于2017/12/4创建了一个读片，请你届时参加！");
//	bigAnt.send_user("hmj", "创建读片会", "韩明君于2017/12/4创建了一个读片，请你届时参加！");
//	String a = bigAnt.get_GroupInfo("yjm");
//	System.out.println(a);
	/*String b = bigAnt.SHA("1234", "MD5");
	System.out.println(b);*/
  }
}