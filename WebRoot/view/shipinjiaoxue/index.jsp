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
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="format-detection" content="telephone=no" />

<link rel="stylesheet" type="text/css"
	href="plugins/layui/css/layui.css" media="all" />
<link rel="stylesheet" type="text/css" href="css/readimage.css"
	media="all">
<link rel="stylesheet" type="text/css" href="css/faqi_baogao.css">
<link rel="stylesheet" type="text/css" href="css/quntaolun.css"
	media="all">
<link rel="stylesheet" type="text/css" href="css/fenxiang.css">

<script type="text/javascript" src="plugins/layui/layui.js"></script>
<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
<script language="javascript"
	src="plugins/My97DatePicker/WdatePicker.js"></script>
</head>
<body class="body_bg">
	<div class="layui-fluid">
		<div class="layui-col-md12">
			<div class="layui-col-md3 layui-riCalendar-bg">
				<fieldset class="layui-elem-field">
					<legend style="color: #fff">视频教学日程管理</legend>
					<div class="layui-col-md12 layui-col-space1" style="padding: 10px">
						<button class="layui-btn layui-btn-normal layui-btn-sm"
							id="addVideo">
							<i class="layui-icon">&#xe654;</i>新增
						</button>
						<button class="layui-btn layui-btn-normal layui-btn-sm"
							id="editVideo">
							<i class="layui-icon">&#xe642;</i>编辑
						</button>
						<button class="layui-btn layui-btn-normal layui-btn-sm"
							id="delVideo">
							<i class="layui-icon">&#xe640;</i>删除
						</button>
						<button class="layui-btn layui-btn-normal layui-btn-sm"
							id="joinVideo">
							<i class="layui-icon">&#xe652;</i>参与
						</button>
					</div>
					<div class="layui-col-md12 layui-col-space1">
						<div id="test-n1"></div>
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
			<div class="layui-col-md9"
				style="padding-left: 15px; margin-top: -9px;">
				<div class="layui-tab layui-tab-card layui-col-md12 layui-input-bg"
					lay-filter="docDemoTabBrief">
					<ul class="layui-tab-title">
						<li class="layui-this">病例列表</li>
						<li class="kejian">课件列表</li>
					</ul>
					<div class="layui-tab-content" style="padding: 0px">
						<div class="layui-col-md12 layui-tab-item layui-show">
							<div class="layui-col-md12 layui-form layui-col-space1">
								<div
									style="margin-top: 15px; margin-bottom: 15px; margin-left: 10px;">
									<div class="layui-input-inline">
										<input type="text" name="username" id="username"
											placeholder="患者姓名" class="layui-input">
									</div>
									<div class="layui-input-inline">
										<div class="layui-input-inline">
											<input type="text" readonly="readonly" class="layui-input"
												id="studytime" placeholder="检查时间">
										</div>
									</div>
									<div class="layui-input-inline" style="text-align: center;">
										<select id="modality" name="modality">
											<option value="">检查类型</option>
										</select>
									</div>
									<div class="layui-input-inline" style="text-align: center;">
										<input type="radio" name="sex" value="M" title="男"> <input
											type="radio" name="sex" value="F" title="女">
									</div>

									<div class="layui-input-inline" style="width: 145px;">
										<button class="layui-btn layui-btn-normal" id="query">查询</button>
										<button class="layui-btn layui-btn-normal" id="reset">重置</button>
									</div>
								</div>

							</div>
							<hr style="background-color: #393D49; height: 1px">
							<div class="layui-col-md12">
								<div class="layui-col-md12 layui-col-space1"
									style="padding: 3px 3px 10px 15px">
									<button class="layui-btn layui-btn-normal" id="upload">
										<i class="layui-icon">&#xe681;</i>上传
									</button>
								</div>
								<div class="layui-col-md12">
									<table class="layui-table" lay-filter="bingli" id="bingli"
										style="margin: 0"></table>
									<script type="text/html" id="barbingli">
  									<a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="yingxiang">影像</a>
									<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="xiangqing">详情</a>
									<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="shanchu">删除</a>
							</script>
								</div>
							</div>
						</div>
						<div class="layui-tab-item " style="padding: 10px 10px 10px 10px">
							<button class="layui-btn layui-btn-normal" id="kejianupload">
								<i class="layui-icon">&#xe681;</i>上传
							</button>
							<div class="layui-col-md12">
								<div>
									<ul class="flow-default" id="LAY_demo2"></ul>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- <div class="suspend" style="float: left;">
		<dl>
			<dt class="IE6PNG">
				<h1>
					<center style="padding-top: 105px;">
						分<br>享
				</h1>
				</center>
			</dt>
			<dd class="shoucangjia">
				<a href="javascript:;"> <img alt="" title="收藏夹"
					style="width: 50px; height: 50px;" src="img/yuanchengxin_76.png">
				</a>
			</dd>
			<dd class="quntaolun">
				<a href="javascript:;"> <img alt="" title="群讨论"
					style="width: 50px; height: 50px;" src="img/yuanchengxin_70.png">
				</a>
			</dd>
			<dd class="readimage">
				<a href="javascript:;"> <img alt="" title="读片会"
					style="width: 50px; height: 50px;" src="img/yuanchengxin_67.png"><br>
				</a>
			</dd>
			<dd class="geren">
                <a href="javascript:;"> <img alt="" title="个人"
                    style="width: 50px; height: 50px;" src="img/gerenfenxiang_72.png"><br>
                </a>
            </dd>
		</dl>
	</div> -->
</body>
<script>
var videoKey;
var medicalRecordKey;
var flow;
var limit = 999;
layui.config({
    base : "js/"
}).use(['laydate','layer','table','common','element','form','flow'], function(){
	  var laydate = layui.laydate;
	  var layer = layui.layer;
	  var table = layui.table;
	  var $ = layui.jquery;
	  var common = layui.common;
	  var form = layui.form;
	  flow = layui.flow;
	  laydate.render({
		    elem: '#studytime'
		    /* ,type: 'datetime' */
		    ,range: '~'
		  });
	//标记日历中的日期
	  $(function(){
		  $.ajax({
	          url : 'video/getVideoDate.do',
	          type : 'post',
	          async: false,
	          success : function(data) {
	        	  var videoList;
	        	  data = eval("("+data+")");
	        	  var specialDates = new Array();
	              if(data.code == 0){
	            	  videoList = data.videoList;
	              }else{
	                  common.cmsLayErrorMsg(data.msg);
	              }
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
	              //日期控件初始化
	              WdatePicker({
					  eCont:'test-n1',
					  specialDates:specialDates,
					  onpicked:function(dp){//日期点击事件
		        		var riqi = dp.cal.getDateStr();
		        		var html="";
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
									html+= "<li class='daibanTime' voide_key='"+videoList[i].videoKey+"'>"+year + "-"+month + "-"+day + "&nbsp"+hour + ":" + min + ":" + sec+"</li>"
								}
			                }
		        		}
		        		//清空该日的待办，增加该日的待办
		        		$("#daiban").html("");
						$("#daiban").append(html);
						if(html==""||html==null){
							table.render({
								elem: '#bingli'
							    ,url:'videoMedicalRecordController/getRecordVideoKey.do'
						    	,method :'post'
						    	,where:{
						        	'videoKey':""
								}
							    ,cols: [[
							       {type:'numbers'}
							      ,{field:'patientName', width:150, title: '患者姓名',event: 'setSign', sort: true}
							      ,{field:'sex', width:80, title: '性别', sort: true,event: 'setSign', sort: true}
							      ,{field:'age', width:80, title: '年龄',event: 'setSign', sort: true}
							      ,{field:'bodypartName', title: '检查部位', width: 100,event: 'setSign', sort: true}
							      ,{field:'modalityCode', width:100, title: '图像类型', sort: true,event: 'setSign', sort: true}
							      ,{field:'studyTime', width:200, title: '检查时间',  templet:'#time', sort: true}
							      ,{fixed: 'right', width: 170, title: '操作', align:'center', toolbar: '#barbingli',event: 'setSign'}
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
							videoKey = _this.attr("voide_key");
							table.render({
								elem: '#bingli'
							    ,url:'videoMedicalRecordController/getRecordVideoKey.do'
						    	,method :'post'
						    	,where:{
						        	'videoKey':videoKey
								}
							    ,cols: [[
							       {type:'numbers'}
							      ,{field:'patientName', width:150, title: '患者姓名',event: 'setSign', sort: true}
							      ,{field:'sex', width:80, title: '性别', sort: true,event: 'setSign', sort: true}
							      ,{field:'age', width:80, title: '年龄',event: 'setSign', sort: true}
							      ,{field:'bodypartName', title: '检查部位', width: 100,event: 'setSign', sort: true}
							      ,{field:'modalityCode', width:100, title: '图像类型', sort: true,event: 'setSign', sort: true}
							      ,{field:'studyTime', width:200, title: '检查时间',  templet:'#time', sort: true}
							      ,{fixed: 'right', width: 170, title: '操作', align:'center', toolbar: '#barbingli',event: 'setSign'}
							    ]]
							    ,page: true
							    ,id:"binglitable"
							  });
							flow.load({
				                elem: '#LAY_demo2' //指定列表容器
				                ,isAuto: true
				                ,isLazyimg: true
				                ,end:' '//默认没有更多内容 改为空
				                ,done: function(page, next){ //到达临界点（默认滚动触发），触发下一页
				                	   var lis = [];
				                	   //以jQuery的Ajax请求为例，请求下一页数据（注意：page是从2开始返回）  
					                   $.getJSON('accessory/getaccessoryById.do?page='+page+'&id='+videoKey, function(res){ 
					            	  
					                	   $("#LAY_demo2").html("");
					                	   var html='';
					                	   for(var i =0;i<res.length;i++){

				                               html += '<li class="mt10" style="float:left">';
				                               html += '<dl><dt style="margin:10px;text-align:center"><img src="img/menuicon_gray_dupian_xiangqing.png" width="48" height="56" /></dt>';
				                               html += '<dd style="text-align:center;margin:10px;">';
				                               html += '<span style="overflow:hidden;text-overflow: ellipsis;-o-text-overflow: ellipsis; white-space:nowrap;width:80px; height:20px;display:block;">'+res[i].accessoryOldname+'</span></dd>';
				                               html += '<dd style="text-align:center;margin:10px;">';
				                               html += '<a href="accessory/downaccessory.do?path='+res[i].path+''+res[i].accessoryNewname+'" target="_blank" class="layui-btn layui-btn-normal layui-btn-xs" >下载</a>';
				                               html += '<a class="layui-btn layui-btn-normal layui-btn-xs" onclick="del(id)" id='+res[i].accessoryKey+'>删除</a>';
				                               html += '</dd></dl></li>';
					                	   }
					                	   $("#LAY_demo2").append(html);    
					                   }); 
					            }
				       	    }); 
							  table.on('tool(bingli)', function(obj){
							    var data = obj.data;
							    if(obj.event === 'yingxiang'){
							      //window.open("http://123.206.29.163:8080/?stuuid="+data.studyinstanceuid+"&user=cc&pswd=dd")
							    window.open("http://47.92.165.202:1180/netFilm_Med/netFilmMed.do?patientID="+data.patientKey+"&studyUID="+data.studyinstanceuid+"&serverName=INTEALL&username=YWRtaW4=&password=YWRtaW4=")
							    }else if(obj.event === 'xiangqing'){
							      window.open("video/getVideoById.do?stuuid="+data.studyinstanceuid+"&videoKey="+videoKey)
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
					    		          url : 'video/delByStuuid.do?stuuid='+data.medicalRecordKey+"&videoKey="+videoKey,
					    		          type : 'GET',
					    		          async: false,
					    		          success : function(data) {
					    		        	  data = eval("("+data+")");
					    		              if(data.code == 0){
					    		                  top.layer.close(index);
					    		                  common.cmsLaySucMsg("删除成功");
						    		          		//执行重载
						    		      			table.reload('binglitable', {
						    		      		        page: {
						    		      		          curr: 1 //重新从第 1 页开始
						    		      		        }
						    		      		        ,where:{
						    		      		        	'videoKey':videoKey
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
							flow.load({
								    elem: '#LAY_demo2' //指定列表容器
								    ,isAuto: true
								    ,isLazyimg: true
								    ,end:' '//默认没有更多内容 改为空
								    ,done: function(page, next){ //到达临界点（默认滚动触发），触发下一页
								     var lis = [];
								     //以jQuery的Ajax请求为例，请求下一页数据（注意：page是从2开始返回）  
						           $.getJSON('accessory/getaccessoryById.do?page='+page+'&id='+videoKey, function(res){ 
						               if(res.status != 0){
						                   var lis = [];  
						                   layui.each(res, function(index, item){
						                	   var html='';
						                             html += '<li class="mt10" style="float:left">';
						                             html += '<dl><dt style="margin:10px;text-align:center"><img src="img/menuicon_gray_dupian_xiangqing.png" width="48" height="56" /></dt>';
						                             html += '<dd style="text-align:center;margin:10px;">';
						                             html += '<span style="overflow:hidden;text-overflow: ellipsis;-o-text-overflow: ellipsis; white-space:nowrap;width:80px; height:20px;display:block;">'+item.accessoryOldname+'</span></dd>';
						                             html += '<dd style="text-align:center;margin:10px;">';
						                             html += '<a href="accessory/downaccessory.do?path='+item.path+''+item.accessoryNewname+'" target="_blank" class="layui-btn layui-btn-normal layui-btn-xs" >下载</a>';
						                             html += '<a class="layui-btn layui-btn-normal layui-btn-xs" onclick="del(id)" id='+item.accessoryKey+'>删除</a>';
						                             html += '</dd></dl></li>';
						                       lis.push(html);
						                   });
						                   //执行下一页渲染，第二参数为：满足“加载更多”的条件，即后面仍有分页  
						                   //pages为Ajax返回的总页数，只有当前页小于总页数的情况下，才会继续出现加载更多  
						                   next(lis.join(''),limit == res.length);  
						               }
						           });  
								    }
								  });
						})
		        	  }
	        	  })
	          }
		  
	      });
	  })
  
  laydate.render({
    elem: '#shenqingtime'
    /* ,type: 'datetime' */
    ,range: true
  });
	  $(".quntaolun").click(function(){
		  if(medicalRecordKey== undefined ){
			  common.cmsLayErrorMsg("请先选择要分享的病例！");
			  return;
		  }
		  var url = "videoMedicalRecordController/getByShareGroupId.do?medicalRecordKey="+medicalRecordKey;
	      common.cmsLayOpen('分享信息',url,'500px','400px');
	  })
	  $(".readimage").click(function(){
		  if(medicalRecordKey== undefined ){
			  common.cmsLayErrorMsg("请先选择要分享的病例！");
			  return;
		  }
		  var url = "videoMedicalRecordController/getByShareRreadimageId.do?&medicalRecordKey="+medicalRecordKey;
	      common.cmsLayOpen('分享信息',url,'500px','400px');
	  })
	  $(".shoucangjia").click(function(){
		  if(medicalRecordKey== undefined ){
			  common.cmsLayErrorMsg("请先选择要分享的病例！");
			  return;
		  }
		  var url = "videoMedicalRecordController/getByShareCollectionId.do?medicalRecordKey="+medicalRecordKey;
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
  $("#querymore").click(function(){
	  if($("#querymorediv").css("display")=="none"){
		  $("#querymorediv").show("slow");
	  }else{
		  $("#querymorediv").hide("slow");
	  }
	  
  })
  $("#addVideo").click(function(){
	  var url = "video/videoAdd.do";
      common.cmsLayOpen('新增视频教学信息',url,'700px','600px');
  })
  $("#editVideo").click(function(){
	  if(videoKey== undefined ){
		  common.cmsLayErrorMsg("请先选择要编辑的视频教学！");
		  return;
	  }
	   var url = "video/getById.do?id="+videoKey;
      common.cmsLayOpen('编辑视频教学信息',url,'700px','600px'); 
  })
  $("#delVideo").click(function(){
	  if(videoKey== undefined ){
		  common.cmsLayErrorMsg("请先选择要删除的视频教学！");
		  return;
	  }
	  top.layer.confirm("是否删除视频教学", {
	        title: "提示",
	        resize: false,
	        btn: ['确定', '取消'],
	        btnAlign: 'c',
	        anim:1,
	        icon: 3
	    },function(index){
			$.ajax({
	          url : "video/delByVideokey.do?videoKey="+videoKey,
	          type : 'GET',
	          async: false,
	          success : function(data) {
	        	  data = eval("("+data+")");
	              if(data.code == 0){
	                  top.layer.close(index);
	                  common.cmsLaySucMsg("删除成功");
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
	//检查类型
	     $(function(){
	     	$.ajax({
		          	url : 'consultation/getmodalityName.do',
		        	type : 'GET',
		        	dataType:"json",
		          	success : function(data) {
		          		var modalitylist = data.modalitylist;
		          		var html = '';
		          		for(var i = 0;i<modalitylist.length;i++){
		          		 	html += '<option value="'+modalitylist[i]+'">'+modalitylist[i]+'</option>'
		          		}
		          		$("#modality").append(html);
		          	    form.render('select'); 
		          	}
	 		})
	     })
  })
  $("#joinVideo").click(function(){
	  if(videoKey== undefined ){
		  common.cmsLayErrorMsg("请先选择要参与的视频教学！");
		  return;
	  }
	  $.ajax({
          url : "video/joinVideo.do?videoKey="+videoKey,
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
            		  common.cmsLayErrorMsg("视频教学还未开始");
            	  }
                 
              }else{
                  common.cmsLayErrorMsg(data.msg);
              }
          }
      });
	  
  })
   $("#upload").click(function(){
	  if(videoKey== undefined ){
		  common.cmsLayErrorMsg("请在左边选择视频教学日程，再上传病历！");
		  return;
	  }
	  var url = "video/videoUpload.do?videoKey="+videoKey;
      common.cmsLayOpen('上传病历',url,'700px','450px');
  })
  $("#kejianupload").click(function(){
	  if(videoKey== undefined ){
		  common.cmsLayErrorMsg("请先选择视频教学，再上课件！");
		  return;
	  }
	  var url = "video/videoKeJianUpload.do?videoKey="+videoKey;
      common.cmsLayOpen('上传课件',url,'700px','450px');
  })
  $("#query").click(function(){
		 if(videoKey == undefined&&videoKey==null){
		 	common.cmsLayErrorMsg("请先选择收藏夹，再查看病历！");
		 	return;
		 }
		 table.render({
			    elem: '#bingli'
			    ,url:'videoMedicalRecordController/getRecordVideoKey.do'
		    	,method :'post'
		    	,where:{
		        	'videoKey':videoKey,
		        	'username':$("#username").val(),
					'studytime':$("#studytime").val(),
					'modality':$("#modality").val(),
					'sex':$("input[name='sex']:checked").val()
				}
			    ,cols: [[
			    	{type:'numbers'}
			      ,{field:'patientName', width:150, title: '患者姓名',event: 'setSign'}
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

	});
	$("#reset").click(function(){
		$("#username").val("");
		$("#studytime").val("");
		$("#modality").val("");
		$('input:radio[name=sex]')[0].checked = false;
		$('input:radio[name=sex]')[1].checked = false;
		form.render('select'); //刷新select选择框渲染
		form.render('radio');
	});
});
function del(id){
    $.ajax({
        url:"accessory/delaccessoryById.do",
        type:"post",
        async:true,
        cache:true,
        data:{
            id:id
        },
        success:function(data){
        	layer.msg("删除成功", {icon: 6} );
        	flow.load({
                elem: '#LAY_demo2' //指定列表容器
                ,isAuto: true
                ,isLazyimg: true
                ,end:' '//默认没有更多内容 改为空
                ,done: function(page, next){ //到达临界点（默认滚动触发），触发下一页
                	   var lis = [];
                	   //以jQuery的Ajax请求为例，请求下一页数据（注意：page是从2开始返回）  
	                   $.getJSON('accessory/getaccessoryById.do?page='+page+'&id='+videoKey, function(res){ 
	            	  
	                	   $("#LAY_demo2").html("");
	                	   var html='';
	                	   for(var i =0;i<res.length;i++){

                               html += '<li class="mt10" style="float:left">';
                               html += '<dl><dt style="margin:10px;text-align:center"><img src="img/menuicon_gray_dupian_xiangqing.png" width="48" height="56" /></dt>';
                               html += '<dd style="text-align:center;margin:10px;">';
                               html += '<span style="overflow:hidden;text-overflow: ellipsis;-o-text-overflow: ellipsis; white-space:nowrap;width:80px; height:20px;display:block;">'+res[i].accessoryOldname+'</span></dd>';
                               html += '<dd style="text-align:center;margin:10px;">';
                               html += '<a href="accessory/downaccessory.do?path='+res[i].path+''+res[i].accessoryNewname+'" target="_blank" class="layui-btn layui-btn-normal layui-btn-xs" >下载</a>';
                               html += '<a class="layui-btn layui-btn-normal layui-btn-xs" onclick="del(id)" id='+res[i].accessoryKey+'>删除</a>';
                               html += '</dd></dl></li>';
	                	   }
	                	   $("#LAY_demo2").append(html);    
	                   }); 
	            }
       	    });  
         }
        });
    }
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
