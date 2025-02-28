<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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

	<link rel="stylesheet" type="text/css" href="plugins/layui/css/layui.css" media="all" />
	<link rel="stylesheet" type="text/css" href="css/readimage.css" media="all">
	<link rel="stylesheet" type="text/css" href="css/faqi_baogao.css">
	<link rel="stylesheet" type="text/css" href="css/fenxiang.css">

	<script type="text/javascript" src="plugins/layui/layui.js"></script>
	<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
	<script language="javascript" src="plugins/My97DatePicker/WdatePicker.js"></script>
</head>
<body class="body_bg">
	<div class="layui-fluid">
			<div class="layui-col-md12">
					<div class="layui-col-md3 layui-riCalendar-bg">
						<fieldset class="layui-elem-field">
							<legend style="color: #fff">读片会日程管理</legend>
							
							<div class="layui-col-md12 layui-col-space1" style="padding: 10px">
								<button class="layui-btn layui-btn-normal layui-btn-sm" id="addReadImage" >
									<i class="layui-icon">&#xe654;</i>新增
								</button>
								<button class="layui-btn layui-btn-normal layui-btn-sm" id="editReadImage">
									<i class="layui-icon">&#xe642;</i>编辑
								</button>
								<button class="layui-btn layui-btn-normal layui-btn-sm" id="delReadImage">
									<i class="layui-icon">&#xe640;</i>删除
								</button>
								<button class="layui-btn layui-btn-normal layui-btn-sm" id="joinReadImage">
									<i class="layui-icon">&#xe652;</i>参与
								</button>
							</div>

							<div class="">
								<div class="layui-col-md12 layui-col-space1">
									<div id="test-n1"></div>
								</div>
							</div>
						</fieldset>
						<fieldset class="layui-elem-field">
							<legend style="color: #fff">事务列表</legend>	
							<div class="layui-col-md12">
								<ul id="daiban">
								</ul>
							</div>
						</fieldset>
					</div>
					<div class="layui-col-md9">
						<div class="layui-col-md12 layui-col-space1" style="padding: 3px 0px 0px 15px">
							<button class="layui-btn layui-btn-normal" id="upload"><i class="layui-icon">&#xe681;</i>上传</button>
						</div>
							
						<div class="layui-field-box">
							<div class="layui-col-md12">
								<table class="layui-table" lay-data="{id:'medicalRecordKey'}" lay-filter="bingli" id="bingli" style="margin: 0" lay-event="share"></table>
								<script type="text/html" id="barbingli">
  									<a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="yingxiang">影像</a>
									<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="xiangqing">详情</a>
									<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="shanchu">删除</a>
  
								</script>
								<script id="time" type="text/html">
    							{{# var a = new Date(d.studyTime || new Date);
									var year = a.getFullYear();
									var month = a.getMonth() < 9 ? "0" + (0 | a.getMonth()+1) : a.getMonth()+1;
									var day = a.getDate() < 10 ? "0" + (0 | a.getDate()) : a.getDate();
        							return year + "-" + month+ "-" + day 
								}} 
								</script>
							</div>
						</div>
					</div>
			</div>
		</div>
		<div class="suspend" style="float: left;">
			<dl>
				<dt class="IE6PNG"><h1><center style="padding-top: 105px;">分<br>享</h1></center></dt>
				<dd class="shoucangjia">
                                <a href="javascript:;">
	                                    <img alt="" title="收藏夹" style="width: 50px;height: 50px;" src="img/yuanchengxin_76.png"> 
                                </a>
                </dd>
				<dd class="quntaolun">
                                <a href="javascript:;">
                                    <img alt="" title="群讨论" style="width: 50px;height: 50px;" src="img/yuanchengxin_70.png">
                                </a>
                </dd>
				<dd class="shipinjiaoxue">
                                <a href="javascript:;" >
	                                    <img alt="" title="视频教学" style="width: 50px;height: 50px;" src="img/yuanchengxin_73.png"><br>
                                </a>
                </dd>
                <dd class="geren">
	                <a href="javascript:;"> <img alt="" title="个人"
	                    style="width: 50px; height: 50px;" src="img/gerenfenxiang_72.png"><br>
	                </a>
	            </dd>
			</dl>
		</div>
</body>
<script>
var readimagekey;
var medicalRecordKey;
layui.config({
    base : "js/"
}).use(['laydate','layer','table','common'], function(){
  var laydate = layui.laydate;
  var layer = layui.layer;
  var table = layui.table;
  var $ = layui.jquery;
  var common = layui.common;
  
  //标记日历中的日期
  $(function(){
	  $.ajax({
          url : 'readimage/getReadImageDate.do',
          type : 'post',
          async: false,
          success : function(data) {
        	  var readImageList;
        	  data = eval("("+data+")");
        	  var specialDates = new Array();
              if(data.code == 0){
            	  readImageList = data.readImageList;
              }else{
                  common.cmsLayErrorMsg(data.msg);
              }
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
								html+="<li class='daibanTime' readimage_key='"+readImageList[i].readimageKey+"'>"+year + "-"+month + "-"+day + "&nbsp"+hour + ":" + min + ":" + sec+"&emsp;&emsp;&emsp;&emsp;&emsp;<span class='hyName' title="+readImageList[i].title+" >"+readImageList[i].title+"</span></li>"
							}
		                }
	        		}
	        		//清空该日的待办，增加该日的待办
	        		$("#daiban").html("");
					$("#daiban").append(html);
					if(html==""||html==null){
						table.render({
							elem: '#bingli'
						    ,url:'readimage_record/getByReadImageKey.do'
					    	,method :'post'
					    	,where:{
					        	'readimagekey':""
							}
						    ,cols: [[
						    	{type:'numbers'}
						      ,{field:'patientName', width:150, title: '患者姓名',event: 'setSign', style:'cursor: pointer;'}
						      ,{field:'sex', width:80, title: '性别', sort: true,event: 'setSign'}
						      ,{field:'age', width:80, title: '年龄',event: 'setSign'}
						      ,{field:'bodypartName', title: '检查部位', width: 100,event: 'setSign'}
						      ,{field:'modalityCode', width:100, title: '图像类型', sort: true,event: 'setSign'}
						      ,{field:'studyTime', width:200, title: '检查时间',  templet:'#time',event: 'setSign'}
						      ,{fixed: 'right', width: 200, title: '操作', align:'center', toolbar: '#barbingli',event: 'setSign'}
						    ]]
						    ,page: true
						    ,id:"binglitable"
						  });
	        		}
					//给具体的时间增加点击事件
					$(".daibanTime").click(function(){
						var _this = $(this);
						$(".daibanTimefocus").removeClass("daibanTimefocus");
						_this.addClass("daibanTimefocus");
						readimagekey = _this.attr("readimage_key");
						table.render({
							elem: '#bingli'
						    ,url:'readimage_record/getByReadImageKey.do'
					    	,method :'post'
					    	,where:{
					        	'readimagekey':readimagekey
							}
						    ,cols: [[
						    	{type:'numbers'}
						      ,{field:'patientName', width:150, title: '患者姓名',event: 'setSign', style:'cursor: pointer;'}
						      ,{field:'sex', width:100, title: '性别', sort: true,event: 'setSign'}
						      ,{field:'age', width:100, title: '年龄',event: 'setSign'}
						      ,{field:'bodypartName', title: '检查部位', width: 120,event: 'setSign'}
						      ,{field:'modalityCode', width:100, title: '图像类型', sort: true,event: 'setSign'}
						      ,{field:'studyTime', width:200, title: '检查时间',  templet:'#time',event: 'setSign'}
						      ,{fixed: 'right', width: 200, title: '操作', align:'center', toolbar: '#barbingli',event: 'setSign'}
						    ]]
						    ,page: true
						    ,id:"binglitable"
						  });
						  table.on('tool(bingli)', function(obj){
						    var data = obj.data;
						    if(obj.event === 'yingxiang'){
						      //window.open("http://123.206.29.163:8080/?stuuid="+data.studyinstanceuid+"&user=cc&pswd=dd")
						    	window.open("http://47.92.165.202:1180/netFilm_Med/netFilmMed.do?patientID="+data.patientKey+"&studyUID="+data.studyinstanceuid+"&serverName=INTEALL&username=YWRtaW4=&password=YWRtaW4=")
						    }else if(obj.event === 'xiangqing'){
						      window.open("medicalrecord/getreadimageById.do?stuuid="+data.studyinstanceuid+"&readimagekey="+readimagekey)
						    }else if(obj.event ==='shanchu'){
						    	top.layer.confirm("是否删除选中数据", {
							        title: "提示",
							        resize: false,
							        btn: ['确定', '取消'],
							        btnAlign: 'c',
							        anim:1,
							        icon: 3
							    },function(index){
					    			$.ajax({
				    		          url : 'readimage_record/delByStuuid.do?stuuid='+data.medicalRecordKey+"&readimagekey="+readimagekey,
				    		          type : 'GET',
				    		          async: false,
				    		          success : function(data) {
				    		        	  data = eval("("+data+")");
				    		              if(data.code == 0){
				    		                  top.layer.close(index);
				    		                  common.cmsLaySucMsg("保存成功");
					    		          		//执行重载
					    		      			table.reload('binglitable', {
					    		      		        page: {
					    		      		          curr: 1 //重新从第 1 页开始
					    		      		        }
					    		      		        ,where:{
					    		      		        	'readimagekey':readimagekey
					    		      				},
					    		      		    });
				    		              }else{
				    		                  top.layer.close(index);
				    		                  common.cmsLayErrorMsg(data.msg);
				    		              }
				    		          },error:function(data){
				    		              top.layer.close(index);
				    		          }
				    		      });
					    		});	
						    }else if(obj.event === 'setSign'){
						    	medicalRecordKey=data.medicalRecordKey;
								}
						  });
					})
					
					//鼠标放到会议命长面上时，显示全名
					/*  var img_show = null; // tips提示
				      $('.hyName').hover(function(){
				          var img = "123456";
				          img_show = layer.tips(img, this,{
				              tips:[2, 'rgba(41,41,41,1)']
				              ,area: ['260px']
				          });
				      },function(){
				          layer.close(img_show);
				      }); */
					
	        	  }
        	  })
          }
	  
      });
	  
  })
  
  
  $("#addReadImage").click(function(){
	  var url = "readimage/redirectAdd.do";
      common.cmsLayOpen('新增读片会信息',url,'700px','650px');
  })
  $("#editReadImage").click(function(){
	  if(readimagekey== undefined ){
		  common.cmsLayErrorMsg("请先选择要编辑的图片会！");
		  return;
	  }
	  var url = "readimage/getById.do?id="+readimagekey;
      common.cmsLayOpen('编辑读片会信息',url,'700px','600px');
  })
  $("#delReadImage").click(function(){
	  if(readimagekey== undefined ){
		  common.cmsLayErrorMsg("请先选择要删除的图片会！");
		  return;
	  }
	  top.layer.confirm("是否删除读片会", {
	        title: "提示",
	        resize: false,
	        btn: ['确定', '取消'],
	        btnAlign: 'c',
	        anim:1,
	        icon: 3
	    },function(index){
			$.ajax({
	          url : "readimage/delByReadimagekey.do?readimagekey="+readimagekey,
	          type : 'GET',
	          async: false,
	          success : function(data) {
	        	  data = eval("("+data+")");
	              if(data.code == 0){
	                  top.layer.close(index);
	                  common.cmsLaySucMsg("保存成功");
	                                         //刷新父页面
	                  window.location.reload();
	              }else{
	                  top.layer.close(index);
	                  common.cmsLayErrorMsg(data.msg);
	              }
	          },error:function(data){
	              top.layer.close(index);
	          }
	      });
		});	
  })
  $("#joinReadImage").click(function(){
	  if(readimagekey== undefined ){
		  common.cmsLayErrorMsg("请先选择要参与的图片会！");
		  return;
	  }
	  $.ajax({
          url : "readimage/joinReadImage.do?readimagekey="+readimagekey,
          type : 'GET',
          async: false,
          success : function(data) {
        	  data = eval("("+data+")");
              if(data.code == 0){
            	  if(data.zoom.startTime<new Date){
            		  if(data.sysuserKey==data.zoom.createPerson){
            			  window.open(data.zoom.startUrl);
            		  }else{
            		  window.open(data.zoom.joinUrl+"&uname="+data.username);
            		  }
            	  }else{
            		  common.cmsLayErrorMsg("会议还未开始");
            	  }
                 
              }else{
                  common.cmsLayErrorMsg(data.msg);
              }
          }
      });
  })
  $("#upload").click(function(){
	  if(readimagekey== undefined ){
		  common.cmsLayErrorMsg("请在左边选择读片会日程，再上传病历！");
		  return;
	  }
	  var url = "readimage/redirectUpload.do?readimagekey="+readimagekey;
      common.cmsLayOpen('上传病历',url,'700px','450px');
  })
  $(".quntaolun").click(function(){
	  if(medicalRecordKey== undefined ){
		  common.cmsLayErrorMsg("请先选择要分享的病例！");
		  return;
	  }
	  var url = "readimage_record/getByShareGroupId.do?medicalRecordKey="+medicalRecordKey;
      common.cmsLayOpen('分享信息',url,'500px','400px');
  })
  $(".shipinjiaoxue").click(function(){
	  if(medicalRecordKey== undefined ){
		  common.cmsLayErrorMsg("请先选择要分享的病例！");
		  return;
	  }
	  var url = "readimage_record/getByShareVideoId.do?medicalRecordKey="+medicalRecordKey;
      common.cmsLayOpen('分享信息',url,'500px','400px');
  })
  $(".shoucangjia").click(function(){
	  if(medicalRecordKey== undefined ){
		  common.cmsLayErrorMsg("请先选择要分享的病例！");
		  return;
	  }
	  var url = "readimage_record/getByShareCollectionId.do?medicalRecordKey="+medicalRecordKey;
      common.cmsLayOpen('分享信息',url,'500px','400px');
  })
  $(".geren").click(function(){
      if(medicalRecordKey== undefined ){
          common.cmsLayErrorMsg("请先选择要分享的病例！");
          return;
      }
      var url = "persontoperson/getByShareGroupId.do?medicalRecordKey="+medicalRecordKey;
      common.cmsLayOpen('分享信息',url,'500px','400px');
  })
  laydate.render({
    elem: '#shenqingtime'
    /* ,type: 'datetime' */
    ,range: true
  });
});
$(document).ready(function(){
	 $(".suspend").mouseover(function() {
       $(this).stop();
       $(this).animate({width: 95}, 400);
   })
   $(".suspend").mouseout(function() {
       $(this).stop();
       $(this).animate({width: 40}, 400);
   });
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