package com.inteall.image.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inteall.image.pojo.Modality;
import com.inteall.image.service.ModalityService;
import com.inteall.image.util.DbcontextHolder;


/** 
* @author 韩明君  
* @date 创建时间：2018年3月1日 下午1:17:38 
* @version 1.0 
* @parameter  
*/
@Controller
@RequestMapping("/modality")
public class ModalityController {
	private static Logger log = Logger.getLogger(ModalityController.class.getName());
	@Resource
	private ModalityService modalityService;
	
	
	/**
	 * 根据查询图像类型信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/getAll.do",method=RequestMethod.GET)
	@ResponseBody
	public Object getAll(HttpServletRequest request,Model model){
		log.info("getAll");
		Map<String,Object> map = new HashMap<String,Object>();
		DbcontextHolder.setDbType("imagedb");
		
		List<Modality> ModalityList = modalityService.getAll();
		map.put("图像类型数量", ModalityList.size());
		map.put("ModalityList", ModalityList);
		return map;
	}
	
	
}
