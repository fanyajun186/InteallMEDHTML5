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
	<link rel="stylesheet" type="text/css" href="../plugins/layui/css/layui.css" media="all" />
	<link rel="stylesheet" type="text/css" href="../css/readimage.css" media="all">
	<link rel="stylesheet" type="text/css" href="../css/faqi_baogao.css">
	<script type="text/javascript" src="../plugins/layui/layui.js"></script>
	<script type="text/javascript" src="../js/jquery-1.4.4.min.js"></script>
</head>
<body class="body_bg">
	<div>
		<div class="layui-fluid">
			<div class="layui-row layui-col-space10">
				<div class="layui-col-md12">
					<div class="layui-row grid-demo">
						<div class="layui-col-md12">
							<div class="layui-btn-container">
								<button class="layui-btn layui-btn-normal" id="yingxiang">
									<i class="layui-icon">&#xe64a;</i>影像
								</button>
							</div>
						</div>
					</div>
				</div>
				<div class="layui-col-md12">
					<div class="layui-row grid-demo">
						<div class="layui-col-md12 layui-input-bg">
							<fieldset id="menu_func_div" class="layui-elem-field">
								<legend>病例信息</legend>
								<div class="layui-form-item layui-col-md12">
									<label class="layui-form-label">患者姓名</label>
									<div class="layui-input-inline">
										<input type="text" readonly="readonly" class="layui-input" name="patientname" value="${medicalRecord.patientName}">
									</div>
									<label class="layui-form-label">性别</label>
									<div class="layui-input-inline">
										<input type="text" readonly="readonly" class="layui-input" name="sex" value="${medicalRecord.sex}">
									</div>
									<label class="layui-form-label">年龄</label>
									<div class="layui-input-inline">
										<input type="text" readonly="readonly" class="layui-input" name="age" value="${medicalRecord.age}">
									</div>
								</div>
								<div class="layui-form-item layui-col-md12">
									<label class="layui-form-label">门诊号</label>
									<div class="layui-input-inline">
										<input type="text" readonly="readonly" class="layui-input" name="patientname">
									</div>
									<label class="layui-form-label">检查编号</label>
									<div class="layui-input-inline">
										<input type="text" readonly="readonly" class="layui-input" value="${medicalRecord.studyId}">
									</div>
									<label class="layui-form-label">病人编号</label>
									<div class="layui-input-inline">
										<input type="text" readonly="readonly" class="layui-input" name="patientname" id="patientNumber" value="${medicalRecord.patientKey}">
									</div>
								</div>
								<div class="layui-form-item layui-col-md12">
									<label class="layui-form-label">住院号</label>
									<div class="layui-input-inline">
										<input type="text" readonly="readonly" class="layui-input" name="patientname">
									</div>
									<label class="layui-form-label">床号</label>
									<div class="layui-input-inline">
										<input type="text" readonly="readonly" class="layui-input" name="patientname">
									</div>
									<label class="layui-form-label">送检科室</label>
									<div class="layui-input-inline">
										<input type="text" readonly="readonly" class="layui-input" name="patientname">
									</div>
								</div>
							</fieldset>
						</div>
					</div>
				</div>
				<div class="layui-col-md12">
					<div class="layui-row grid-demo">
						<div class="layui-col-md12 layui-input-bg">
							<fieldset id="menu_func_div" class="layui-elem-field">
								<legend>视频会诊信息</legend>
								<form class="layui-form">
									<div class="layui-form-item">
										<label class="layui-form-label">视频会诊</label>
										<div class="layui-input-inline">
											<input type="checkbox" name="close" lay-skin="switch"
												lay-filter="switchTest" lay-text="是|否">
										</div>
									</div>
									<div id="shipinxinxi" style="display: none">
										<div class="layui-form-item" style="margin-top: 10px">
											<label class="layui-form-label">开始时间</label>
											<div class="layui-input-inline">
												<input type="hidden" id="ReadimageKey" name="ReadimageKey">
												<input type="hidden" id="zoomKey" name="zoomKey">
												<input type="text" readonly="readonly" id="startTime" name="startTime" lay-verify="required" autocomplete="off" class="layui-input"/>
											</div>
											<label class="layui-form-label">预约时长</label>
											<div class="layui-input-inline">
												<select name="yuyueshichang" id="yuyueshichang" lay-verify="required">
													<option value="">请选择</option>
													<option value="30">30分钟</option>
													<option value="60">60分钟</option>
													<option value="90">90分钟</option>
												</select>
											</div>
										</div>
										<div class="layui-form-item">
											<label class="layui-form-label">讨论组</label>
											<div class="layui-input-block">
												<select lay-filter="group" name="group" id="group"></select>
											</div>
										</div>
										<div class="layui-form-item">
											<div class="layui-input-block" style="height: 200px;overflow:auto;overflow-x:hidden;border: 1px solid  #23262e ">
												<table id="members" style="width: 100%"></table>
											</div>
										</div>
										<div class="layui-form-item layui-form-text">
											<label class="layui-form-label">与会人员</label>
											<div class="layui-input-block">
												<textarea name="yuhuirenyuan" readonly="readonly" id="yuhuirenyuan" placeholder="与会人员" lay-verify="required" class="layui-textarea layui-input"></textarea>
											</div>
										</div>
										<div class="layui-form-item">
											<button type="button" class="layui-btn layui-btn-normal" id="joinReadImage">
												<i class="layui-icon">&#xe6ed;</i>参加会议
											</button>
										</div>
									</div>
								</form>
							</fieldset>
						</div>
					</div>
				</div>
				<div class="layui-col-md12">
					<div class="layui-row grid-demo">
						<div class="layui-col-md12 layui-input-bg">
							<fieldset id="menu_func_div" class="layui-elem-field">
								<legend>附件信息</legend>
								<div class="layui-col-md12 layui-col-space1" style="padding: 3px 0px 0px 15px">
										<button class="layui-btn layui-btn-normal" id="upload"><i class="layui-icon">&#xe681;</i>上传</button>
									</div>
								<div class="layui-col-md12">
									<div>
										<ul class="flow-default" id="LAY_demo2"></ul> 
									</div>
								</div>
							</fieldset>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
var stuuid="${stuuid}";
var interventionKey="${interventionKey}";
var member = new Array();
var memberName = new Array();
$("#yuyueshichang").val("${yuyueshichang}");
$("#yuhuirenyuan").val("${yuhuirenyuan}");
$("#startTime").val("${start_time}");
$("#ReadimageKey").val("${ReadimageKey}");
$("#zoomKey").val("${zoomKey}");
member = "${login}".substring(0,"${login}".length-1).split(",");
memberName = "${yuhuirenyuan}".substring(0,"${yuhuirenyuan}".length-1).split(",");
$(function(){
	$.ajax({
       url : '../bigant/getGroupInfoByUserId.do',
       type : 'post',
       async: false,
       success : function(data) {
     	  data = eval("("+data+")");
           if(data.code == 0){
         	  if(data.groups!=null){
         		  var html="<option  value=''>请选择</option>";
	          	  for(var i=0;i<data.groups.length;i++){
	           	  		  html+="<option  value='"+data.groups[i].group_id+"'>"+data.groups[i].group_name+"</option>";
	          	  }
	          	  $("#group").append(html);
           	  }
           }else{
               common.cmsLayErrorMsg(data.msg);
           }
       },error:function(data){
     	  common.cmsLayErrorMsg(data.msg);
       }
   });
});
layui.config({
base : "../js/"
	}).use([ 'form', 'layedit', 'laydate','layer','table','common','flow' ], function() {
		var limit = 999;
		var form = layui.form;
		var flow = layui.flow;
		var laydate = layui.laydate;
		var layer = layui.layer;
		var table = layui.table;
		var common = layui.common;
		laydate.render({
			elem : '#startTime' //指定元素
			,type: 'datetime'
		});
		$(function(){
			if("${zoomKey}"!=""&&"${zoomKey}"!=null){
				$('input[name="close"]').attr('checked','checked');  //改变开关为 开
				form.render('checkbox');
				$("#shipinxinxi").show();
			}else{
				form.on('switch(switchTest)', function(data) {
					if (this.checked == true) {
						$("#shipinxinxi").show();
					} else if (this.checked == false) {
						$("#shipinxinxi").hide();
					}
				});
			}
		})
		form.on('select(group)', function(data){
			$.ajax({
		       url : '../bigant/getMemberByGroupId.do',
		       type : 'post',
		       data :{"groupId":data.value},
		       async: false,
		       success : function(data) {
		       		data = eval("("+data+")");
		       		if(data.code == 0){
		       			$("#members").html("");
		         		if(data.users!=null){
			         		var html="";
			          		for(var i=0;i<data.users.length;i++){
			          			html+= "<tr  style='border:1px solid  #23262e' height='30px' ><td class='member' userLogin='"+data.users[i].userLogin+"'>"+data.users[i].userName+"</td></tr>"
			          	  	}
		          	  	}
		        		$("#members").append(html);
		        		$(".member").dblclick(function(){
		        			var _this = $(this);
							var userLogin = _this.attr("userLogin");
							var index = member.indexOf(userLogin);
							if (index > -1) {
								var a = memberName.indexOf(_this.text());
								memberName.splice(a, 1);
								member.splice(index, 1);
							}else {
								member.push(userLogin)
								memberName.push(_this.text())
							}
							var html = ""
							for(var i=0;i<memberName.length;i++){
								html+=memberName[i]+","	
							}
							
							$("#yuhuirenyuan").text(html)
		        		})
		       		}else{
		            	common.cmsLayErrorMsg(data.msg);
		        	}
		       },error:function(data){
		     		common.cmsLayErrorMsg(data.msg);
		       }
		   });
		});
		 flow.load({
		    elem: '#LAY_demo2' //指定列表容器
		    ,isAuto: true
		    ,isLazyimg: true
		    ,end:' '//默认没有更多内容 改为空
		    ,done: function(page, next){ //到达临界点（默认滚动触发），触发下一页
		     var lis = [];
		     //以jQuery的Ajax请求为例，请求下一页数据（注意：page是从2开始返回）  
             $.getJSON('../accessory/getaccessoryById.do?page='+page+'&id='+stuuid, function(res){ 
                 if(res.status != 0){
                     var lis = [];  
                     layui.each(res, function(index, item){
                    	 var html='';
	                         html += '<li class="mt10" style="float:left">';
	                         html += '<dl><dt style="margin:10px;text-align:center"><img src="../img/menuicon_gray_dupian_xiangqing.png" width="48" height="56" /></dt>';
	                         html += '<dd style="text-align:center;margin:10px;">';
	                         html += '<span style="overflow:hidden;text-overflow: ellipsis;-o-text-overflow: ellipsis; white-space:nowrap;width:80px; height:20px;display:block;">'+item.accessoryOldname+'</span></dd>';
	                         html += '<dd style="text-align:center;margin:10px;">';
	                         html += '<a href="../accessory/yulanaccessory?path='+item.path+''+item.accessoryNewname+'" target="_blank" class="layui-btn layui-btn-normal layui-btn-xs" >预览</a>';
	                         html += '<a class="layui-btn layui-btn-normal layui-btn-xs" onclick="del(id)" id='+item.accessoryKey+'>删除</a>';
	                         html += '</dd></dl></li>';
                         lis.push(html);
                     });
                     //执行下一页渲染，第二参数为：满足“加载更多”的条件，即后面仍有分页  
                     //pages为Ajax返回的总页数，只有当前页小于总页数的情况下，才会继续出现加载更多  
                     next(lis.join(''), limit == res.length);  
                 }
             });  
		    }
		  });
	$("#upload").click(function () {
				var url = "../medicalrecord/bingliUpload.do?stuuid="+stuuid;
				common.cmsLayOpen('上传附件',url,'700px','450px');
	});
	$("#yingxiang").click(function () {
		window.open("http://123.206.29.163:808/?stuuid="+stuuid+"&user=cc&pswd=dd");
	});
	 $("#joinReadImage").click(function(){
		  if(interventionKey== undefined ){
			  common.cmsLayErrorMsg("还没有视频会议！");
			  return;
		  }
		  $.ajax({
	          url : "../intervention/joinReadImage.do?interventionKey="+interventionKey,
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
	            		  common.cmsLayErrorMsg("会诊还未开始");
	            	  }
	                 
	              }else{
	                  common.cmsLayErrorMsg(data.msg);
	              }
	          }
	      });
		  
	  })
});
function del(id){
         $.ajax({
             url:"../accessory/delaccessoryById.do",
             type:"post",
             async:true,
             cache:true,
             data:{
                 id:id
             },
             success:function(data){
                 window.location.reload(true);
             }
         });
	}
</script>
</html>