/**
 * 
 */
package com.inteall.image.util;

/**
 * @author 李进刚
 * @date 2018年2月27日 下午6:43:57
 * @version 1.0 
 * @parameter 
 * 
 * ZOOM管理url生成程序
 */

import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;

/**
 * @author Administrator
 *
 */
public class ZoomManageUtil {
  public static final String HZJY_KEY	 = "hzjy";
  public static final String HZJY_SECRET = "203bf32eb3c36be98027f0d8f1f90044";

  /*
   * 计算时间戳
   */
  public static String dateToStamp(String s) throws ParseException {
	String res;
	Date date = stringToDatetime(s);
	long ts = date.getTime();
	res = String.valueOf(ts);
	return res;
  }
  
  /*
   * 字符串转Datetime
   */
  public static Date stringToDatetime(String s) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return simpleDateFormat.parse(s);
	  }
  
  /*
   * Datetime转字符串
   */
  public static String datetimeToSstring(Date d) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return simpleDateFormat.format(d);
	  }
  
  /**
   * 组装预约会议的url
   * 
   * @param 预约日期，预约时间，计划时长，与会人数，会议主题
   * @return String sURL
   * @throws ParseException
   */
  public static String spliceCreate_URL(String start_Time, Integer minute,
      Integer meeting_capacity, String topic) throws ParseException {
	String sURL = "http://timer.91veo.com/v1/meeting/create?";
	sURL = sURL + "key=" + HZJY_KEY + "&";
	sURL = sURL + "secret=" + HZJY_SECRET + "&";
	sURL = sURL + "meeting_capacity=" + meeting_capacity.toString() + "&"; // 与会人数
	sURL = sURL + "minute=" + minute.toString() + "&"; // 会议时长
	sURL = sURL + "start_time=" + dateToStamp(start_Time) + "&";
	sURL = sURL + "recording=true&";
	sURL = sURL + "cloud_recording=false&";
	sURL = sURL + "topic=" + topic + "&";
	sURL = sURL + "jbh=true&";
	sURL = sURL + "recording=true";
	System.out.println(sURL);
	return sURL;
  }

  /*
   * 向ZOOM提交命令，获得返回字符串
   */
  public static String send_URL(String sURL) {
	try {
	  URL url = new URL(sURL);
	  // 创建连接
	  HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	  connection.setDoOutput(true);
	  connection.setDoInput(true);
	  connection.setUseCaches(false);
	  connection.setInstanceFollowRedirects(true);
	  connection.setRequestMethod("POST");
	  // 设置请求方式
	  connection.setRequestProperty("Accept", "application/json");
	  // 设置接收数据的格式
	  connection.setRequestProperty("Content-Type", "application/json");
	  // 设置发送数据的格式
	  connection.connect();
	  OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
	  // utf-8编码
	  out.append("");
	  // 参数必须有
	  out.flush();
	  out.close();

	  // 读取响应
	  int length = (int) connection.getContentLength();
	  // 获取长度
	  InputStream is = connection.getInputStream();
	  if (length != -1) {
		byte[] data = new byte[length];
		byte[] temp = new byte[512];
		int readLen = 0;
		int destPos = 0;
		while ((readLen = is.read(temp)) > 0) {
		  System.arraycopy(temp, 0, data, destPos, readLen);
		  destPos += readLen;
		}
		String sResult = new String(data, "UTF-8");
		System.out.println(sResult);
		// utf-8编码
		return sResult;
	  }
	} catch (IOException e) {
	  e.printStackTrace();
	}
	return "Error:0";
  }
  
  /**
   * 组装获取移动端加入会议的信息的url
   * 
   * @param meeting_id,meeting_value
   * @return String sURL
   * @throws 
   */
  public static String spliceGet_URL(String meeting_id, String meeting_value) {
	String sURL = "http://timer.91veo.com/v1/meeting/get?";
	sURL = sURL + "key=" + HZJY_KEY + "&";
	sURL = sURL + "secret=" + HZJY_SECRET + "&";
	sURL = sURL + "id=" + meeting_id + "&";
	sURL = sURL + "value=" + meeting_value;
	return sURL;
  }
  
  /**
   * 组装修改与会人数和会议主题的url
   * 
   * @param 预约日期，预约时间，计划时长，与会人数
   * @return String sURL
   * @throws 
   */
  public static String spliceUpdate_URL(String meeting_id, String meeting_value,
	  String total_number, String topic) {
	String sURL = "http://timer.91veo.com/v1/meeting/update?";
	sURL = sURL + "key=" + HZJY_KEY + "&";
	sURL = sURL + "secret=" + HZJY_SECRET + "&";
	sURL = sURL + "id=" + meeting_id + "&";
	sURL = sURL + "value=" + meeting_value + "&";
	sURL = sURL + "meeting_capacity=" + total_number + "&"; // 与会人数
	sURL = sURL + "recording=true&";
	sURL = sURL + "cloud_recording=false&";
	sURL = sURL + "topic=" + topic + "&"; // 会议主题
	sURL = sURL + "jbh=true";
	sURL = sURL + "recording=true";
	System.out.println(sURL);
	return sURL;
  }
  /**
   * 删除已预约的会议信息的url
   * 
   * @param id，value
   * @return String sURL
   * @throws 
   */
  public static String getCancel_URL(String meeting_id, String meeting_value) {
	String sURL = "http://timer.91veo.com/v1/meeting/delete?";
	sURL = sURL + "key=" + HZJY_KEY + "&";
	sURL = sURL + "secret=" + HZJY_SECRET + "&";
	sURL = sURL + "id=" + meeting_id + "&";
	sURL = sURL + "value=" + meeting_value;
	return sURL;
  }
  
  /**
   * 强制结束已召开的会议的url
   * 
   * @param id，value
   * @return String sURL
   * @throws 
   */
  public static String getEnding_URL(String meeting_id, String meeting_value) {
	String sURL = "http://timer.91veo.com/v1/meeting/end?";
	sURL = sURL + "key=" + HZJY_KEY + "&";
	sURL = sURL + "secret=" + HZJY_SECRET + "&";
	sURL = sURL + "id=" + meeting_id + "&";
	sURL = sURL + "value=" + meeting_value;
	return sURL;
  }
  
  /**
   * 获取创建会议反馈信息中的meeting_id
   * 
   * @param jsonString
   * @return String meeting_id
   * @throws 
   */
  public static String getMeeting_id(String jsonString) {
	JSONObject jsonObject = JSONObject.fromObject(jsonString); 
	return jsonObject.getString("id");
  }
  
  /**
   * 获取创建会议反馈信息中的meeting_value
   * @param jsonString
   * @return String meeting_id
   * @throws 
   */
  public static String getMeeting_value(String jsonString) {
	JSONObject jsonObject = JSONObject.fromObject(jsonString); 
	return jsonObject.getString("value");
  }
  
  /**
   * 组装撤销预约会议的url
   * 
   * @param ID，value
   * @return String sURL
   * @throws 
   */
  public static String spliceDelete_URL(String meeting_id, String meeting_value) {
	String sURL = "http://timer.91veo.com/v1/meeting/delete?";
	sURL = sURL + "key=" + HZJY_KEY + "&";
	sURL = sURL + "secret=" + HZJY_SECRET + "&";
	sURL = sURL + "id=" + meeting_id + "&";
	sURL = sURL + "value=" + meeting_value;
	System.out.println(sURL);
	return sURL;
  }
  
  /**
   * 组装强制终止会议的url
   * 
   * @param ID，value
   * @return String sURL
   * @throws 
   */
  public static String spliceEnd_URL(String meeting_id, String meeting_value) {
	String sURL = "http://timer.91veo.com/v1/meeting/end?";
	sURL = sURL + "key=" + HZJY_KEY + "&";
	sURL = sURL + "secret=" + HZJY_SECRET + "&";
	sURL = sURL + "id=" + meeting_id + "&";
	sURL = sURL + "value=" + meeting_value;
	return sURL;
  }
  public static void main(String[] args) throws ParseException {
	String sURL=ZoomManageUtil.spliceCreate_URL("2018-04-04 09:10:11", 10, 2, "测试");
	/*ZoomManageUtil.spliceUpdate_URL("360172", "a3e58a2aae02c1296758b89b6282c5f0", "10", "会诊测试2");*/
	/*ZoomManageUtil.spliceDelete_URL("360156", "4b05c9d78f391736eca1078359672f41");*/
	/*String meeting_id = "351515";
	String meeting_value = "34d86a844798739623497c0b12719ebe";
	ZoomManageUtil.spliceEnd_URL(meeting_id, meeting_value);*/
	ZoomManageUtil.send_URL(sURL);
}
}