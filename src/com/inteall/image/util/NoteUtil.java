/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inteall.image.util;



import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import com.inteall.image.org.tempuri.WS;
import com.inteall.image.org.tempuri.WSSoap;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author TZN
 */
public class NoteUtil {

    private static String CorpID = "BJZX01675";
    private static String Pwd = "Inteall2012";
//      private static String CorpID="SDK0748";
//      private static String Pwd="SDK0748";
    
    public static void main(String arg[]){
        try {
			NoteUtil.sendSMS("您好，有新病例的影像资料，需要您及时诊断，谢谢！【汇智精英】", "17601618131");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static void sendNote(String msg,String callNum){
//        LinkWS linkWS = new LinkWS();
//        LinkWSSoap soap;
//        soap = (LinkWSSoap) linkWS.getLinkWSSoap();
//        System.out.println(soap.batchSend(CorpID, Pwd, callNum,msg, "",""));
        WS linkWS = new WS();
        WSSoap soap;
        soap = (WSSoap) linkWS.getWSSoap();
        System.out.println(soap.batchSend(CorpID, Pwd, callNum,msg, "",""));
    }
    
    public  static String sendSMS(String strContent,String strmobiles) throws UnsupportedEncodingException  {
		HttpClient client = new HttpClient();
		
//		String url= "http://api.esoftsms.com/msg/HttpBatchSendSM";//应用地址
                String url="http://test.api.esoftsms.com/msg/HttpBatchSendSM";
		String account = "SDK0748";//账号
		String pswd = "SDK0748";//密码
		String mobiles = strmobiles;//手机号码，多个号码使用","分割
		String content = URLEncoder.encode(strContent,"utf-8");;//短信内容
		String needstatus = "true";//是否需要状态报告，需要true，不需要false
		String product = "";//产品ID
		String extno = "";//扩展码
		
		String result="";
		PostMethod post = new PostMethod(url);
		post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8"); 
	  
		NameValuePair[] data = {
				new NameValuePair("account", account),
				new NameValuePair("pswd", pswd),
				new NameValuePair("mobile", mobiles),
				new NameValuePair("msg", content),
				new NameValuePair("needstatus", needstatus),
				new NameValuePair("product", product),
				new NameValuePair("extno", extno) };
		
		try {
			post.setRequestBody(data);
			client.executeMethod(post);
			

			result = new String(post.getResponseBodyAsString());
			

		} catch (IOException e) {
			 
			e.printStackTrace();
		}

		post.releaseConnection();
		
		return result;
		
	}
    
    public  static String sendSMSchange(String strContent,String change) throws UnsupportedEncodingException  {
		HttpClient client = new HttpClient();
		             
//		String url= "http://api.esoftsms.com/msg/HttpVarSM";//应用地址
//                String url="http://test.api.esoftsms.com/msg/HttpVarSM";
                  String url="http://test.api.esoftsms.com/msg/HttpVarSM";
		String account = "SDK0748";//账号
		String pswd = "SDK0748";//密码
//		String mobiles = strmobiles;//手机号码，多个号码使用","分割
		String content = URLEncoder.encode(strContent,"utf-8");;//短信内容
		String needstatus = "true";//是否需要状态报告，需要true，不需要false
		String product = "";//产品ID
		String extno = "";//扩展码
		
		String result="";
		PostMethod post = new PostMethod(url);
		post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8"); 
	  
		NameValuePair[] data = {
				new NameValuePair("account", account.toString()),
				new NameValuePair("pswd", pswd.toString()),
//				new NameValuePair("mobile", mobiles),                                
				new NameValuePair("msg", content),
                                new NameValuePair("params",change),
				new NameValuePair("needstatus", needstatus),
				new NameValuePair("product", product),
				new NameValuePair("extno", extno) };
		
		try {
			post.setRequestBody(data);
			client.executeMethod(post);
			result = new String(post.getResponseBodyAsString());
			

		} catch (IOException e) {
			 
			e.printStackTrace();
		}

		post.releaseConnection();
                System.out.println("dsaf;::::::::::::::::"+change);
               
		System.out.println(":::::::::::::::::::::::::::::::::::::::"+result);
		return result;
		
	}
    
    
}

