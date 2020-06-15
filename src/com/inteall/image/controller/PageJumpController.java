package com.inteall.image.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 韩明君
 * @date 2018年3月29日 下午7:44:05
 * @version 1.0 
 * @parameter 
 */
@Controller
public class PageJumpController {
  @RequestMapping(value="/xitongshezhi.do")
  public String xitongshezhi(){
	return "xitongshezhi/index";
  }
  
  @RequestMapping(value="/readimage.do")
  public String readimage(){
	return "readimage/index";
  }
  
  @RequestMapping(value="/quntaolun.do")
  public String quntaolun(){
	return "quntaolun/index";
  }
  
  @RequestMapping(value="/shoucangjia.do")
  public String shoucangjia(){
	return "shoucangjia/index";
  }
  
  @RequestMapping(value="/faqihuizhen.do")
  public String faqihuizhen(){
	return "faqihuizhen/index";
  }
  
  @RequestMapping(value="/xiangyinghuizhen.do")
  public String xiangyinghuizhen(){
	return "xiangyinghuizhen/index";
  }
  
  @RequestMapping(value="/baogaoshenhe.do")
  public String baogaoshenhe(){
	return "baogaoshenhe/index";
  }
  
  
  @RequestMapping(value="/jieruhuizhen.do")
  public String shouyaoshipin(){
	return "jieruhuizhen/index";
  }
  
  @RequestMapping(value="/shipinjiaoxue.do")
  public String shipinjiaoxue(){
	return "shipinjiaoxue/index";
  }
  @RequestMapping(value="/tongjibaobiao.do")
  public String tongjibaobiao(){
    return "tongjibaobiao/index";
  }
  @RequestMapping(value="/fenxiangbingli.do")
  public String persontoperson(){
    return "persontoperson/index";
  }
  @RequestMapping(value="/zhishiku.do")
  public String zhishiku(){
    return "zhishiku/index";
  }
  @RequestMapping(value="/houtaiguanli.do")
  public String houtaiguanli(){
	  
    return "houtaiguanli/index";
  }
}
