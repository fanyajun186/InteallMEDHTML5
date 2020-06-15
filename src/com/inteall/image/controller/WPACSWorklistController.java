package com.inteall.image.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.inteall.image.service.WPACSWorklistService;

/**
 * @author 李进刚
 * @date 2018年3月21日
 * @version 1.0
 * @parameter
 */

@Controller
@RequestMapping("/WPACSWorklist")
public class WPACSWorklistController {
  private static Logger log = Logger.getLogger(WPACSWorklistController.class.getName());
  
  @Resource
  private WPACSWorklistService wpacsWorklistService;
  
  
}
