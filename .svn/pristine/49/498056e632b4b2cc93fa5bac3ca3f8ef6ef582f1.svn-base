<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta content="text/html;charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="no cache" />
<meta http-equiv="Expires" content="0" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="format-detection" content="telephone=no" />
<link rel="stylesheet" type="text/css" href="../plugins/layui/css/layui.css" media="all" />
<link rel="stylesheet" type="text/css" href="../css/readimage.css" media="all">
	
<link rel="stylesheet" type="text/css" href="../css/faqi_baogao.css">
<link rel="stylesheet" type="text/css" href="../css/home.css">
<script type="text/javascript" src="../plugins/echarts/echarts.min.js"></script>
<script type="text/javascript" src="../plugins/layui/layui.js"></script>
<script type="text/javascript" src="../js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="../plugins/My97DatePicker/WdatePicker.js"></script>
<style type="text/css">
	
	@media (min-width: 768px){ /* >=768的设备  */
		body{
			height:150px;
		}
	}
	@media (min-width: 992px){/*  >=992的设备 */ 
		body{
			height:200px;
		}
	}
	@media (min-width: 1200px){ /* >=1200的设备  */ 
		body{
			height:800px;
		}
		.firstDiv{
			height: 175px;
		}
		.secondDiv{
			height: 85px;
		}
		.thirdDiv{
			height: 400px;
		}
	}
	
	@media (min-width: 2048px){ /* >=2400的设备  */ 
		body{
			height:1000px;
		}
		.firstDiv{
			height: 260px;
			text-align: 260px;
			margin-top: 15px;
		}
		.secondDiv{
			height: 160px;
			text-align: 160px;
		}
		.thirdDiv{
			height: 560px;
		}
	}

</style>
</head>
<body class="body_bg">
	<div class="layui-fluid">
		<div class="layui-col-md12">
			<div class="layui-col-md3 layui-riCalendar-bg">
			    <div class="layui-row layui-col-md12">
				<fieldset class="layui-elem-field">
					<div class="layui-col-md12 layui-col-space1">
						<div id="test-n1"></div>
					</div>
				</fieldset>
				</div>
				<div class="layui-row layui-col-md12">
					<fieldset class="layui-elem-field">
						<legend style="color: #fff">事务列表</legend>
						<div class="layui-col-md12 layui-form">
							<table lay-filter="daiban" id="daiban" style="width: 100%;text-align: center;">
							</table>
						</div>
					</fieldset>
                </div>
			</div>

			<div class="layui-col-md9 " style="height: 233px;padding-left:20px;">
				<div class="layui-row layui-col-space10 ">
                    <div class="layui-col-md12 ">
                        <div class="layui-row layui-col-space10 ">
							<div class="layui-col-md4 ">
						        <a href="javascript:;" function="addTab" topUrl="faqihuizhen" tabId="faqihuizhen" topTitle="发起会诊" >
							    <div class="inteall_big_box firstDiv" style="background-color: #54ade8">
                                    <div class="inteall_big_img">
                                        <img alt="" src="../img/yuanchengxin_41.png">
                                    </div>
                                    <div class="inteall_big_word">
                                        <span class="inteall_span" id="huizhenshenqingNum">0</span>
                                        <cite class="inteall_cite">会诊申请</cite>
                                    </div>
                                </div>
                                </a>
							</div>
							<div class="layui-col-md4 ">
							    <a href="javascript:;" function="addTab" topUrl="xiangyinghuizhen" tabId="xiangyinghuizhen" topTitle="响应会诊" >
								<div class="inteall_big_box firstDiv" style="background-color: #EC5A64;">
									<div class="inteall_big_img">
                                        <img alt="" src="../img/yuanchengxin_51.png">
                                    </div>
                                    <div class="inteall_big_word">
                                        <span class="inteall_span" id="xiangyinghuizhenNum">0</span>
                                        <cite class="inteall_cite">响应会诊</cite>
                                    </div>
								</div>
                                </a>
							</div>
							 <div class="layui-col-md4">
		                            <a href="javascript:;" function="addTab" topUrl="baogaoshenhe" tabId="baogaoshenhe" topTitle="报告审核" >
		                                  <div class="inteall_big_box firstDiv" style="background-color: #ABD276;">
		                                    <div class="inteall_big_img">
		                                        <img alt="" src="../img/yuanchengxin_44.png">
		                                    </div>
		                                    <div class="inteall_big_word">
		                                        <span class="inteall_span" id="baogaoshenheNum">0</span>
		                                        <cite class="inteall_cite">报告审核</cite>
		                                    </div>
		                                </div>
		                            </a>
		                    </div>
							<div class="layui-col-md4">
                                <a href="javascript:;" function="addTab" topUrl="shipinjiaoxue" tabId="shipinjiaoxue" topTitle="视频教学" >
                                <div class="inteall_big_box inteall_small_img secondDiv" style="background-color: #7eb00a;">
                                    <img alt="" src="../img/yuanchengxin_73.png">
                                    <cite class="inteall_cite">视频教学</cite>
                                </div>
                                </a>
                            </div>
							
                            <div class="layui-col-md4 ">
                                <a href="javascript:;" function="addTab" topUrl="quntaolun" tabId="quntaolun" topTitle="群讨论" >
                                <div class="inteall_big_box inteall_small_img secondDiv"  style="background-color: #49619C">
                                    <img alt="" src="../img/yuanchengxin_70.png">
                                    <cite class="inteall_cite">群讨论</cite>
                                </div>
                                </a>
                            </div>
							<div class="layui-col-md4 ">
							    <a href="javascript:;" function="addTab" topUrl="readimage" tabId="readimage" topTitle="读片会" >
                                <div class="inteall_big_box inteall_small_img secondDiv" style="background-color: #46A9B6;">
                                    <img alt="" src="../img/yuanchengxin_67.png">
                                    <cite class="inteall_cite">读片会</cite>
                                </div>
                                </a>
                            </div>
                            <div class="layui-col-md4 ">
                                <a href="javascript:;" function="addTab" topUrl="shoucangjia" tabId="shoucangjia" topTitle="收藏夹" >
                                <div class="inteall_big_box inteall_small_img secondDiv" style="background-color: #3A8A49">
                                    <img alt="" src="../img/yuanchengxin_76.png"> 
                                    <cite class="inteall_cite">收藏夹</cite>
                                </div>
                                </a>
                            </div>
                            
                            <div class="layui-col-md4">
                                <a href="javascript:;" function="addTab" topUrl="fenxiangbingli" tabId="fenxiangbingli" topTitle="分享病历" >
                                <div class="inteall_big_box inteall_small_img secondDiv" style="background-color: #c14089;">
                                    <img alt="" src="../img/gerenfenxiang_72.png">
                                    <cite class="inteall_cite">分享病例</cite>
                                </div>
                                </a>
                            </div>
                            <div class="layui-col-md4">
                                <a href="javascript:;" function="addTab" topUrl="zhishiku" tabId="zhishiku" topTitle="知识库" >
                                <div class="inteall_big_box inteall_small_img secondDiv" style="background-color: #6f5553;">
                                    <img alt="" src="../img/yuanchengxin_77.png">
                                    <cite class="inteall_cite">知识库</cite>
                                </div>
                                </a>
                            </div>
						</div>
                    </div>
                </div>
                <div class="layui-row layui-col-space10 ">
                    <div class="layui-col-md6 ">
                        <div class="inteall_big_box thirdDiv" style="background-color: #343437" >
                            <div id="pie" style="height: 400px;"></div>
                        </div>
                    </div>
                    <div class="layui-col-md6 ">
                        <div class="inteall_big_box thirdDiv" style="background-color: #343437">
                            <div id="container" style="height: 400px;"></div>
                        </div>
                    </div>
                </div>
			</div>

		</div>
	</div>
</body>
<script>
var readimagekey;
var imstart="${imstart}";
layui.config({
    base : "../js/"
}).use(['laydate','element','layer','table','common'], function(){
  var laydate = layui.laydate;
  var layer = layui.layer;
  var table = layui.table;
  var $ = layui.jquery;
  var common = layui.common;
  //判断是否web端打开
  if(!/http(s*):\/\//.test(location.href)){
      layer.alert("请先将项目部署到 localhost下再进行访问");
  }else{
	  if(window.sessionStorage.getItem("showNotice") != "true"){
		  window.open(imstart,"_self");
		  showNotice();
	  }
  }
//公告层
function showNotice(){
	 $.ajax({
         type: 'GET',
         url: '<%=request.getContextPath()%>/zhishikuManage/noticeOne.do',
         dataType:  'json',
         success: function(data){
	      layer.open({
	          type: 1,
	          offset: 'rb',
	          area: ['400px', '300px'],
	          title: "系统公告",
	          shade: 0,
	          id: 'LAY_layuipro',
	          btn: ['查看更多'],
	          btn1: function(index, layero){
	        	  	 var url="<%=request.getContextPath()%>/zhishikuManage/noticehome";
	        	     window.parent.addTab("xitonggonggao","公告",url+".do");
	       		 },
	          move: false,
	          content:'<div style="padding: 20px 50px;color:#000000;"><h2 style="text-align: center;">'+data.noticename+'</h2><p>'+data.noticecontent+'</p></div>',
	          success: function(layero){
	              var btn = layero.find('.layui-layer-btn');
	              btn.css('text-align', 'center');
	              btn.on("click",function(){
	                  tipsShow();
	              });
	          }
	         ,cancel: function(index, layero){
	              tipsShow();
	          }
	      });
         }
	});
  }
  function tipsShow(){
      window.sessionStorage.setItem("showNotice","true");
  }
  $("a").click(function(event){
      var _this = $(this);
      var fun = _this.attr("function");
      var id = _this.attr("tabId");
      var url = _this.attr("topUrl");
      var title = _this.attr("topTitle");
      switch(fun){
          case "addTab" :
        	  window.parent.addTab(id,title,url+".do")
          break;
      }
  });
  //标记日历中的日期
  $(function(){
      $.ajax({
          url : 'getAllDate.do',
          type : 'post',
          async: false,
          success : function(data) {
              data = eval("("+data+")");
              var specialDates = new Array();
              readImageList = data.readImageList;
              if(readImageList!=null){
                  //组装需要标记的日期
                  for(var i=0;i<readImageList.length;i++){
                      var a = new Date(readImageList[i].startTime || new Date);
                      var year = a.getFullYear();
                      var month = a.getMonth() < 9 ? "0" + (0 | a.getMonth()+1) : a.getMonth()+1;
                      var day = a.getDate() < 10 ? "0" + (0 | a.getDate()) : a.getDate();
                      specialDates.push( year + "-" + month+ "-" + day);
                  }
              }
              
              videoList = data.videoList;
              if(videoList!=null){
                  //组装需要标记的日期
                  for(var i=0;i<videoList.length;i++){
                      var a = new Date(videoList[i].startTime || new Date);
                      var year = a.getFullYear();
                      var month = a.getMonth() < 9 ? "0" + (0 | a.getMonth()+1) : a.getMonth()+1;
                      var day = a.getDate() < 10 ? "0" + (0 | a.getDate()) : a.getDate();
                      specialDates.push( year + "-" + month+ "-" + day);
                  }
              }
              consultationList = data.consultationList;
              if(consultationList!=null){
                  //组装需要标记的日期
                  for(var i=0;i<consultationList.length;i++){
                      var a = new Date(consultationList[i].startTime || new Date);
                      var year = a.getFullYear();
                      var month = a.getMonth() < 9 ? "0" + (0 | a.getMonth()+1) : a.getMonth()+1;
                      var day = a.getDate() < 10 ? "0" + (0 | a.getDate()) : a.getDate();
                      specialDates.push( year + "-" + month+ "-" + day);
                  }
              }
              
              //日期控件初始化
              WdatePicker({
                  eCont:'test-n1',
                  specialDates:specialDates,
                  onpicked:function(dp){//日期点击事件
                    var riqi = dp.cal.getDateStr();
                    var html="";
                    if(readImageList!=null){
                        for(var i=0;i<readImageList.length;i++){
                            var a = new Date(readImageList[i].startTime || new Date);
                            var year = a.getFullYear();
                            var month = a.getMonth() < 9 ? "0" + (0 | a.getMonth()+1) : a.getMonth()+1;
                            var day = a.getDate() < 10 ? "0" + (0 | a.getDate()) : a.getDate();
                            var hour = a.getHours() < 10 ? "0" + (0 | a.getHours()) : a.getHours();
                            var min = a.getMinutes() < 10 ? "0" + (0 | a.getMinutes()) : a.getMinutes();
                            var sec = a.getSeconds() < 10 ? "0" + (0 | a.getSeconds()) : a.getSeconds();
                            
                            if((year + "-" + month+ "-" + day)==riqi){
                                html+= "<tr style='height:40px'><td style='width:40%'>"+year + "-"+month + "-"+day + "&nbsp"+hour + ":" + min + ":" + sec+"</td>"+
                                "<td style='width:30%'>读片会</td>"+
                                "<td style='width:30%'><button class='layui-btn layui-btn-xs layui-btn-normal enter' zoom_key='"+readImageList[i].zoomKey+"'>参与</button></td>"
                            }
                        }
                    }
                    if(videoList!=null){
                        for(var i=0;i<videoList.length;i++){
                            var a = new Date(videoList[i].startTime || new Date);
                            var year = a.getFullYear();
                            var month = a.getMonth() < 9 ? "0" + (0 | a.getMonth()+1) : a.getMonth()+1;
                            var day = a.getDate() < 10 ? "0" + (0 | a.getDate()) : a.getDate();
                            var hour = a.getHours() < 10 ? "0" + (0 | a.getHours()) : a.getHours();
                            var min = a.getMinutes() < 10 ? "0" + (0 | a.getMinutes()) : a.getMinutes();
                            var sec = a.getSeconds() < 10 ? "0" + (0 | a.getSeconds()) : a.getSeconds();
                            
                            if((year + "-" + month+ "-" + day)==riqi){
                                html+= "<tr style='height:40px'><td style='width:40%'>"+year + "-"+month + "-"+day + "&nbsp"+hour + ":" + min + ":" + sec+"</td>"+
                                "<td style='width:30%'>视频教学</td>"+
                                "<td style='width:30%'><button class='layui-btn layui-btn-xs layui-btn-normal enter' zoom_key='"+videoList[i].zoomKey+"'>参与</button></td>"
                            }
                        }
                    }
                    if(consultationList!=null){
                        for(var i=0;i<consultationList.length;i++){
                            var a = new Date(consultationList[i].startTime || new Date);
                            var year = a.getFullYear();
                            var month = a.getMonth() < 9 ? "0" + (0 | a.getMonth()+1) : a.getMonth()+1;
                            var day = a.getDate() < 10 ? "0" + (0 | a.getDate()) : a.getDate();
                            var hour = a.getHours() < 10 ? "0" + (0 | a.getHours()) : a.getHours();
                            var min = a.getMinutes() < 10 ? "0" + (0 | a.getMinutes()) : a.getMinutes();
                            var sec = a.getSeconds() < 10 ? "0" + (0 | a.getSeconds()) : a.getSeconds();
                            
                            if((year + "-" + month+ "-" + day)==riqi){
                                html+= "<tr style='height:40px'><td style='width:40%'>"+year + "-"+month + "-"+day + "&nbsp"+hour + ":" + min + ":" + sec+"</td>"+
                                "<td style='width:30%'>视频会诊</td>"+
                                "<td style='width:30%'><button class='layui-btn layui-btn-xs layui-btn-normal enter' zoom_key='"+consultationList[i].zoomKey+"'>参与</button></td>"
                            }
                        }
                    }
                    //清空该日的待办，增加该日的待办
                    $("#daiban").html("");
                    $("#daiban").append(html);
                    
                    //给具体的时间增加点击事件
                    $(".enter").click(function(){
                    	var _this = $(this);
                        zoom_key = _this.attr("zoom_key");
                        $.ajax({
                            url  : 'getEnterUrl.do',
                            type : 'post',
                            data : {"zoom_key":zoom_key},
                            async: false,
                            success : function(data) {
                                data = eval("("+data+")");
                                if(data.code==0){
                                	window.open(data.zoomUrl)
                                }else{
                                	common.cmsLayErrorMsg(data.msg);
                                }
                            }
                        })
                         
                    })
                  }
              })
          }
      
      });
      //查询我的会诊申请，我的报告审核，响应会诊数量
      $.ajax({
          url : 'getAllNumber.do',
          type : 'post',
          async: false,
          success : function(data) {
              data = eval("("+data+")");
              if(data.code==0){
            	  $("#huizhenshenqingNum").text(data.huizhenshenqingNum);
            	  $("#baogaoshenheNum").text(data.baogaoshenheNum);
            	  $("#xiangyinghuizhenNum").text(data.xiangyinghuizhenNum)
              }else{
            	  common.cmsLayErrorMsg("获取待办数量出错，请稍后重试或者联系管理员！");
              }
          }
      })
      
      //获取条形图所需数据
      $.ajax({
          url : 'getLineECharts.do',
          type : 'post',
          async: false,
          success : function(data) {
              data = eval("("+data+")");
              //console.log(data)
              if(data.code==0){
            	  var dom = document.getElementById("container");
                  var myChart = echarts.init(dom);
                  option = null;
                
                  option = {
                      
                      tooltip: {
                          trigger: 'axis',
                          axisPointer: {
                              type: 'cross',
                              crossStyle: {
                                  color: '#FFF'
                              }
                          }
                      },
                      toolbox: {
                          feature: {
                              magicType: {show: true, type: ['line', 'bar']},
                              restore: {show: true},
                              saveAsImage: {show: true}
                          },
                          iconStyle:{
                              normal:{
                                borderColor:'#1E9FFF',//设置颜色
                              }
                          }
                      },
                      legend: {
                          data:['会诊申请','响应会诊','报告初审','报告终审'],
                          textStyle: {
                              color: '#fff'
                          }
                          
                      },
                      xAxis: [
                          {
                              type: 'category',
                              data: data.months,
                              axisLabel: {
                                  show: true,
                                  textStyle: {
                                      color: '#fff'
                                  }
                              },
                              axisPointer: {
                                  type: 'shadow',
                                  show: true,
                                  textStyle: {
                                      color: '#fff'
                                  }
                              }
                          }
                      ],
                      yAxis: [
                          {
                              type: 'value',
                              name: '数量',
                              min: 0,
                              max: 100,
                              interval: 20,
                              axisLabel: {
                                  formatter: '{value}',
                                  show: true,
                                  textStyle: {
                                      color: '#fff'
                                  }
                              }
                          }
                      ],
                      series: [
                          {
                              name:'会诊申请',
                              type:'bar',
                              data:data.huizhenshenqing
                          },
                          {
                              name:'响应会诊',
                              type:'bar',
                              data:data.xiangyinghuizhen
                          },
                          {
                              name:'报告初审',
                              type:'bar',
                              data:data.chushen
                          }
                          ,
                          {
                              name:'报告终审',
                              type:'bar',
                              data:data.fushen
                          }
                      ]
                  };
                  if (option && typeof option === "object") {
                      myChart.setOption(option, true);
                  }
            	  
              }else{
                  common.cmsLayErrorMsg("获取条形图数据出错，请稍后重试或者联系管理员！");
              }
          }
      })
      //获取条形图所需数据
      $.ajax({
          url : 'getPieECharts.do',
          type : 'post',
          async: false,
          success : function(data) {
              data = eval("("+data+")");
              //console.log(data)
              if(data.code==0){
            	  var dom = document.getElementById("pie");
                  var myChart = echarts.init(dom);
                  var app = {};
                  option = null;
                  app.title = '环形图';
                
                  option = {
                      tooltip: {
                          trigger: 'item',
                          formatter: "{a} <br/>{b}: {c} ({d}%)"
                      },
                      legend: {
                          orient: 'vertical',
                          x: 'left',
                          data:['会诊申请','响应会诊','报告初审','报告终审','读片会','群讨论','视频教学'],
                          textStyle: {
                              color: '#fff'
                          }
                      },
                      toolbox: {
                          feature: {
                              restore: {show: true},
                              saveAsImage: {show: true}
                          },
                          iconStyle:{
                              normal:{
                                borderColor:'#1E9FFF',//设置颜色
                              }
                          }
                      },
                      series: [
                          {
                              name:'数据统计',
                              type:'pie',
                              radius: ['50%', '70%'],
                              avoidLabelOverlap: false,
                              label: {
                                  normal: {
                                      show: false,
                                      position: 'center'
                                  },
                                  emphasis: {
                                      show: true,
                                      textStyle: {
                                          fontSize: '30',
                                          fontWeight: 'bold'
                                      }
                                  }
                              },
                              labelLine: {
                                  normal: {
                                      show: false
                                  }
                              },
                              data:[
                                  {value:data.huizhenshenqingcount, name:'会诊申请'},
                                  {value:data.xiangyinghuizhencount, name:'响应会诊'},
                                  {value:data.chushencount, name:'报告初审'},
                                  {value:data.fushencount, name:'报告终审'},
                                  {value:data.readcount, name:'读片会'},
                                  {value:data.quntaoluncount, name:'群讨论'},
                                  {value:data.videocount, name:'视频教学'}
                              ]
                          }
                      ]
                  };
                  if (option && typeof option === "object") {
                      myChart.setOption(option, true);
                  }
                  
              }else{
                  common.cmsLayErrorMsg("获取条形图数据出错，请稍后重试或者联系管理员！");
              }
          }
      })
      
  })
  
});
	
	window.setInterval("test()",1000);
	function test(){
		$("#timeIframe").contents().find("#dpTitle .NavImgll").attr("title","上一年");
		$("#timeIframe").contents().find("#dpTitle .NavImgl").attr("title","上一月");
		$("#timeIframe").contents().find("#dpTitle .NavImgr").attr("title","下一月");
		$("#timeIframe").contents().find("#dpTitle .NavImgrr").attr("title","下一年");
	}
	
	
</script>

</html>
