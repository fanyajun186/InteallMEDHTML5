package com.inteall.image.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:spring-mvc.xml", "classpath:spring-mybatis.xml"})

public class PostProcessingControllerTest {
  
  @Autowired
  private PostProcessingController postProcessingController;
  
  private MockMvc mockMvc;
  
  @Before
  public void setUp() throws Exception {
    mockMvc = MockMvcBuilders.standaloneSetup(postProcessingController).build();
  }
  
  @Test
  public void testDeleteByPrimaryKey() throws Exception {
    ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.post("/show_user3").param("id", "1"));
    MvcResult mvcResult = resultActions.andReturn();
    String result = mvcResult.getResponse().getContentAsString();
    System.out.println("=====客户端获得反馈数据:" + result);
    // 也可以从response里面取状态码，header,cookies...
    // System.out.println(mvcResult.getResponse().getStatus());
  }
  
  @Test
  public void testInsert() {
    fail("尚未实现"); // TODO
  }
  
  @Test
  public void testInsertSelective() {
    fail("尚未实现"); // TODO
  }
  
  @Test
  public void testSelectByPrimaryKey() {
    fail("尚未实现"); // TODO
  }
  
  @Test
  public void testUpdateByPrimaryKeySelective() {
    fail("尚未实现"); // TODO
  }
  
  @Test
  public void testUpdateByPrimaryKey() {
    fail("尚未实现"); // TODO
  }
  
  @Test
  public void testAddrecord() {
    fail("尚未实现"); // TODO
  }
  
}
