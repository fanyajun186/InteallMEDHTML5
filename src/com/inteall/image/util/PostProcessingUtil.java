package com.inteall.image.util;

import org.apache.log4j.Logger;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.inteall.image.pojo.WPACSImages;

/**
 * @author 李进刚
 * @date 2018年3月22日 下午12:33:57
 * @version 1.0
 * @parameter
 * 
 *            调用服务器端的后处理ex文件
 */

public class PostProcessingUtil {
  private static Logger log = Logger.getLogger(PostProcessingUtil.class.getName());

  /*
   * 调用后处理程序
   * 
   * @param wpacsImagesLists影像文件列表，sAppid后处理功能序号，sParam1降噪参数
   * 
   * @return 0-成功，1-失败
   */
  public static String callPostProcessing1(List<WPACSImages> wpacsImagesLists, String sAppid, String sParam1) {
	// 调用第一个后处理程序
	log.info("callPostProcessing1");

	// 初始化WPACS的主目录，返回索引文件名
	String sWPACSPath = "D:\\WebPACS\\";
	String sResult = "0";
	// 将文件列表转换为索引文件
	String sIndexFileName = saveListsToFile(wpacsImagesLists, sWPACSPath);
	if (sIndexFileName.length() > 0) {
	  // 索引文件创建成功，调用后处理程序
	  String sRunFile = sWPACSPath + "InteallApp1\\run.bat " + sIndexFileName + " " + sAppid + " " + sParam1;
	  log.info("sRunFile=" + sRunFile);
	  sResult = WindowsExec(sRunFile).toString();
	  log.info("sResult=" + sResult);
	}
	return sResult;
  }

  /*
   * 速度最快:判断是否为整数
   * 
   * @param str 传入的字符串
   * 
   * @return 是整数返回true,否则返回false
   */
  public static boolean isInteger(String str) {
	Pattern pattern = Pattern.compile("[0-9]*");
	Matcher isNum = pattern.matcher(str);
	if (!isNum.matches()) {
	  return false;
	}
	return true;
  }

  /*
   * 字符串保存到文本文件中
   * 
   * @param 文件名和需要保存的字符串
   * 
   * @return 0-成功，1-失败
   */
  public static String saveListsToFile(List<WPACSImages> wpacsImagesLists, String sWPACSPath) {
	String sImageLists = "";
	for (int iCount = 0; iCount < wpacsImagesLists.size(); iCount++) {
	  // 将list转换为字符串，在文件名前面加上绝对路径
	  sImageLists = sImageLists + sWPACSPath + "recv_images\\" + wpacsImagesLists.get(iCount).getFpath() + ";";
	}
	
	// 获得当前时间
	DateFormat dtformat = new SimpleDateFormat("yyyyMMddHHmmss");
	String sformatDate = dtformat.format(new Date());
	
	String sResult = sWPACSPath + "InteallApp1\\index" + sformatDate + ".txt";
	try {
	  // 生成索引文件
	  FileWriter fw = new FileWriter(sResult);
	  fw.write(sImageLists);
	  fw.close();
	} catch (IOException e) {
	  e.printStackTrace();
	  sResult = "";
	}
	// 返回索引文件名
	return sResult;
  }

  public static Integer WindowsExec(String sFileName) {
	// 采用安全方式调用windows程序，确保被调用的程序能够正常结束
	if (sFileName.length() != 0) {
	  // 输入参数不为空
	  try {
		// 根据当前的操作系统，选择不同的dos命令
		String osName = System.getProperty("os.name");
		String[] cmd = new String[3];
		if (osName.equals("Windows NT")) {
		  cmd[0] = "cmd.exe";
		  cmd[1] = "/C";
		  cmd[2] = sFileName;
		} else if (osName.equals("Windows 95")) {
		  cmd[0] = "command.com";
		  cmd[1] = "/C";
		  cmd[2] = sFileName;
		} else if (osName.indexOf("Windows Server") >= 0) {
		  // "Windows Server 2012 R2"
		  cmd[0] = "cmd.exe";
		  cmd[1] = "/C";
		  cmd[2] = sFileName;
		}
		log.info("osName:" + osName);
		Runtime rt = Runtime.getRuntime();
		log.info("Execing:" + cmd[0] + " " + cmd[1] + " " + cmd[2]);
		Process proc = rt.exec(cmd);
		
		// 接收程序运行时的错误信息
		StreamGobbler errorGobbler = new StreamGobbler(proc.getErrorStream(), "ERROR");
		// 接收程序运行时的输出信息
		StreamGobbler outputGobbler = new StreamGobbler(proc.getInputStream(), "OUTPUT");
		
		errorGobbler.start();
		outputGobbler.start();
		
		// 接收运行程序的返回值
		int exitVal = proc.waitFor();
		log.info("ExitValue: " + exitVal);
		return exitVal;
	  } catch (Throwable t) {
		// 出现错误，返回错误
		t.printStackTrace();
		return 2;
	  }
	} else {
	  // 参数为空，返回错误
	  return 1;
	}
  }
  
  public static class StreamGobbler extends Thread {
	// 异步读取命令行输出的线程
	InputStream is;
	String type;
	
	StreamGobbler(InputStream is, String type) {
	  this.is = is;
	  this.type = type;
	}
	
	public void run() {
	  try {
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String line = null;
		while ((line = br.readLine()) != null)
		  log.info(type + ">" + line);
	  	} catch (IOException ioe) {
	  	ioe.printStackTrace();
	  }
	}
  }
}
