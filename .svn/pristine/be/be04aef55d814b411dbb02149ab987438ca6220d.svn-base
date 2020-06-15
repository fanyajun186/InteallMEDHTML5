package com.inteall.image.util;

import java.io.InputStream;
import java.security.MessageDigest;
import java.util.Properties;

//import com.inteall.hzpt.base.po.User;

import sun.misc.BASE64Encoder;

/**
 * 通用工具类
 */
public class Util {

	/**
	 * 对字符串进行MD5加密
	 * 
	 * @param str
	 * @return String
	 */
	public static String md5Encryption(String str) {
		String newStr = null;
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			BASE64Encoder base = new BASE64Encoder();
			newStr = base.encode(md5.digest(str.getBytes("UTF-8")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newStr;
	}
	

	/**
	 * 判断字符串是否为空
	 * 
	 * @param str
	 *            字符串
	 * @return true：为空； false：非空
	 */
	public static boolean isNull(String str) {
		if (str != null && !str.trim().equals("")) {
			return false;
		} else {
			return true;
		}
	}
		
	
	 /**  
	  * 读取Config文件工具类
     * 获取整个配置文件中的属性 
     * @param filePath 文件路径，即文件所在包的路径，例如：java/util/config.properties  
     */    
    public static Properties readData(String filePath) {    
        Properties props = new Properties();    
        try {    
            InputStream in = Util.class.getClassLoader().getResourceAsStream(filePath);    
            props.load(in);    
            in.close();    
            return props;    
        } catch (Exception e) {    
            e.printStackTrace();    
            return null;    
        }    
    }    
      
    @SuppressWarnings("unused")
	private static String getRealPath(String filePath) {  
        //获取绝对路径 并截掉路径的”file:/“前缀    
        return Util.class.getResource("/" + filePath).toString().substring(6);  
    }  
    
   
	
}
