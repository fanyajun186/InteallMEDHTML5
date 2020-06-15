/**
 *@author 韩明君
 *时间: 2018年2月7日 上午10:25:13
 */
package com.inteall.image.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

/**
 * MD5加密解密及字符串对比工具类
 * @author 韩明君
 *时间: 2016年7月5日 下午7:25:13
 */
public class MD5Encryption {
	
	private final static String HEX_NUMS_STR = "0123456789ABCDEF";
	private final static Integer SALT_LENGTH = 16;

	/**
	 * 将16进制字符串转换成数组
	 * 
	 * @return byte[]
	 * @author jacob
	 * */
	public static byte[] hexStringToByte(String hex) {
		/* len为什么是hex.length() / 2 ?
		 * 首先，hex是一个字符串，里面的内容是像16进制那样的char数组
		 * 用2个16进制数字可以表示1个byte，所以要求得这些char[]可以转化成什么样的byte[]，首先可以确定的就是长度为这个char[]的一半
		 */
		int len = (hex.length() / 2);
		byte[] result = new byte[len];
		char[] hexChars = hex.toCharArray();
		for (int i = 0; i < len; i++) {
			int pos = i * 2;
			result[i] = (byte) (HEX_NUMS_STR.indexOf(hexChars[pos]) << 4 | HEX_NUMS_STR
					.indexOf(hexChars[pos + 1]));
		}
		return result;
	}
	
	/**
	 * 将数组转换成16进制字符串
	 * 
	 * @return String
	 * @author jacob
	 *
	 * */
	public static String byteToHexString(byte[] salt){
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < salt.length; i++) {
			String hex = Integer.toHexString(salt[i] & 0xFF);
			if(hex.length() == 1){
				hex = '0' + hex;
			}
			hexString.append(hex.toUpperCase());
		}
		return hexString.toString();
	}
	
	/**
	 * 密码验证
	 * @param passwd 用户输入密码
	 * @param dbPasswd 数据库保存的密码
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public static boolean validPasswd(String passwd, String dbPasswd)
			throws NoSuchAlgorithmException, UnsupportedEncodingException{
		byte[] pwIndb =  hexStringToByte(dbPasswd);
		//定义salt
		byte[] salt = new byte[SALT_LENGTH];
		System.arraycopy(pwIndb, 0, salt, 0, SALT_LENGTH);
		//创建消息摘要对象
		MessageDigest md = MessageDigest.getInstance("MD5");
		//将盐数据传入消息摘要对象
		md.update(salt);
		md.update(passwd.getBytes("UTF-8"));
		byte[] digest = md.digest();
		//声明一个对象接收数据库中的口令消息摘要
		byte[] digestIndb = new byte[pwIndb.length - SALT_LENGTH];
		//获得数据库中口令的摘要
		System.arraycopy(pwIndb, SALT_LENGTH, digestIndb, 0,digestIndb.length);
		//比较根据输入口令生成的消息摘要和数据库中的口令摘要是否相同
		if(Arrays.equals(digest, digestIndb)){
			//口令匹配相同
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 获得md5之后的16进制字符
	 * @param passwd 用户输入密码字符
	 * @return String md5加密后密码字符
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public static String getEncryptedPwd(String passwd)
			throws NoSuchAlgorithmException, UnsupportedEncodingException{
		//拿到一个随机数组，作为盐
		byte[] pwd = null;
		SecureRandom sc= new SecureRandom();
		byte[] salt = new byte[SALT_LENGTH];
		sc.nextBytes(salt);
		
		//声明摘要对象，并生成
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(salt);
		md.update(passwd.getBytes());
		byte[] digest = md.digest();
		
		pwd = new byte[salt.length + digest.length];
		System.arraycopy(salt, 0, pwd, 0, SALT_LENGTH);
		System.arraycopy(digest, 0, pwd, SALT_LENGTH, digest.length);
		return byteToHexString(pwd);
	}
	
	/**
	* 字符串 SHA 加密
	* 
	* @param strSourceText
  	* @return
	*/
	public static String SHA(final String strText, final String strType) {
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
	/**
	 * 
	 * @param psd1 用户输入的，没有加密
	 * @param psd2 数据里存储的，已经加密
	 * @return
	 */
	public static boolean checkPassWord(String psd1,String psd2){
	  boolean flag = false;
	  String psd1_new = SHA(psd1, "MD5");
	  if(psd1_new.equals(psd2)){
		flag = true;
	  }
	  
	  return flag; 
	}
//	public static void main(String[] args) {
//		try {
//			
//			String p = "A4F248CEB9E149FC4E397DC265C8CD03ACF403096750639B8477591D20E81F3F";
//			boolean b = validPasswd("123456",p);
//			System.out.println(b);
//		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
	
}