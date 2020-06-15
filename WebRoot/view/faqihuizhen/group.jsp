<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
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

	<link rel="stylesheet" type="text/css" href="../plugins/layui/css/layui.css" media="all" />
	<link rel="stylesheet" type="text/css" href="../css/readimage.css">
	<link rel="stylesheet" type="text/css" href="../css/faqi_baogao.css">
	<script type="text/javascript" src="../js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="../plugins/layui/layui.js"></script>
	<script type="text/javascript" src="../js/jquery.form.min.js"></script>
	<script type="text/javascript" src="../js/jquery.webcam.min.js"></script>
	
	<style type="text/css">
		/* .layui-layer-content{
			background-color: #C4C3C4;
		} */
		.aCss{
			color: #fff;
			cursor: pointer;
		}
		#photoDiv{
			display: none;
			margin-left: 100px;
		}
		#videoDiv{
			display:none;
			margin-left: 100px;
		}
	</style>
	
</head>
<body class="body_bg">
	<div class="layui-fluid">
	<div class="layui-col-md12 layui-form">
	   <div class="layui-form-item">
            <label class="layui-form-label">病情描述</label>
            <div class="layui-input-block">
                <textarea id="bingqingmiaoshu" lay-verify="required" name="bingqingmiaoshu" placeholder="病情描述"
                    class="layui-textarea layui-input"></textarea>
            </div>
        </div>
        <div class="layui-form-item">
        	<label class="layui-form-label">附件</label>
			<div class="layui-input-block" style="height: 120px; overflow: auto; overflow-x: hidden; border: 1px solid #23262e">
				<table id="historyFile" style="width: 100%;" ></table>
			</div>
			<div class="layui-input-block" style="margin-top: 5px;margin-bottom: -10px;">
				<button class="layui-btn layui-btn-xs" lay-filter="uploadFile" id="uploadFile">上传附件</button>
				<!-- <button class="layui-btn layui-btn-xs" lay-filter="uploadPhoto" id="uploadPhoto">拍照上传</button>
				<button class="layui-btn layui-btn-xs" lay-filter="capture" id="capture" style="display: none;">拍照</button> -->
			</div>
			<!-- 拍照 -->
			<div id="photoDiv">
				<canvas id="canvas" width="640" height="480"></canvas>
			</div>
			<div id="videoDiv">
				<video id="video" width="640" height="480" controls></video>
			</div>
			<!-- 文件 -->
			<form action="../consultation/historyFile.do" enctype="multipart/form-data" id="fileForm" method="post" style="display: none;">
				<input type="hidden" id="stuuid" name="stuuid" value="${stuuid}">
				<input type="file" id="fileUpload" name="fileUpload" value="" onchange="submitfile();">
			</form>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">专家组</label>
			<div class="layui-input-block">
				<select lay-filter="group" name="group" id="group" lay-search=""></select>
			</div>
		</div>
		<!-- <div class="layui-form-item">
			<div class="layui-input-block"
				style="height: 120px; overflow: auto; overflow-x: hidden; border: 1px solid #23262e">
				<table id="members" style="width: 100%"></table>
			</div>
		</div> -->
		<div class="layui-form-item layui-form-text">
			<label class="layui-form-label">专家姓名</label>
			<div class="layui-input-block">
			    <select lay-filter="zhuanjiaxingming" name="zhuanjiaxingming" id="zhuanjiaxingming" lay-search=""><option value=''>直接选择或输入选择</option></select>
				<!-- <input type="text" name="zhuanjiaxingming" id="zhuanjiaxingming" readonly="readonly" placeholder="专家姓名" lay-verify="zhuanjiaxingming"  autocomplete="off" class="layui-input only-input" /> -->
				<!-- <textarea id="zhuanjiaxingming" name="zhuanjiaxingming"  placeholder="专家姓名" class="layui-textarea layui-input"></textarea> -->
					
			</div>
		</div>
		<div class="layui-form-item" style="text-align: center;">
			<button class="layui-btn layui-btn-normal" lay-submit="" lay-filter="demo1">立即提交</button>
			<button type="reset" id="reset" class="layui-btn layui-btn-normal">重置</button>
		</div>
	</div>
	</div>
</body>
<script type="text/javascript">
var userLogin;
var caseId = "${caseId}";
var groupId;
$(function(){
	$.ajax({
       url : '../bigant/getGroupInfoByUserId.do',
       type : 'post',
       async: false,
       success : function(data) {
     	  data = eval("("+data+")");
          if(data.code == 0){
         	  
         	  if(data.groups!=null){
         		  var html="<option  value=''>直接选择或输入选择</option>";
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
}).use(['common','form', 'layedit', 'laydate'], function(){
		var layer = layui.layer;
		var laydate = layui.laydate;
		var form = layui.form;
		var $ = layui.jquery;
		var common = layui.common;
		form.on('select(group)', function(data){
			groupId = data.value;
			$.ajax({
		       url : '../bigant/getMemberByGroupId.do',
		       type : 'post',
		       data :{"groupId":data.value},
		       async: false,
		       success : function(data) {
		       		data = eval("("+data+")");
		       		if(data.code == 0){
		       			$("#zhuanjiaxingming").html("<option value=''>直接选择或输入选择</option>");
		         		if(data.users!=null){
			         		var html="";
			          		for(var i=0;i<data.users.length;i++){
			          			html+= "<option value='"+data.users[i].userLogin+"'>"+data.users[i].userName+"</option>"
			          	  	}
	                        $("#zhuanjiaxingming").append(html);
			         		form.render('select'); //刷新select选择框渲染
		          	  	}
		         		
		        		/* $(".member").dblclick(function(){
		        			var _this = $(this);
							userLogin = _this.attr("userLogin");
							var userName = _this.text();
							
							$("#zhuanjiaxingming").val(userName)
		        		}) */
		       		}else{
		            	common.cmsLayErrorMsg(data.msg);
		        	}
		       },error:function(data){
		     		common.cmsLayErrorMsg(data.msg);
		       }
		   });
		});
		
		
		form.on('submit(demo1)', function(data){
			if(groupId==undefined){
				common.cmsLayErrorMsg("请选择专家组");
				return false;
			}
			if($("#zhuanjiaxingming").val()==undefined||$("#zhuanjiaxingming").val()==""){
                common.cmsLayErrorMsg("请选择专家");
                return false;
            }
			var userSaveLoading = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
			
			$.ajax({
		          url : 'submitConsultation.do',
		          type : 'post',
		          async: false,
		          data :{"caseId":caseId,"groupId":groupId,"userLogin":$("#zhuanjiaxingming").val(),"bingqingmiaoshu":$("#bingqingmiaoshu").val(),"stuuid":"${stuuid}"},
		          success : function(data) {
		        	  data = eval("("+data+")");
		              if(data.code == 0){
		            	  top.layer.close(userSaveLoading);
		            	  common.cmsLaySucMsg("保存成功");
		            	  var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                          parent.layer.close(index); //再执行关闭      
                          parent.location.reload();
		              }else{
		                  top.layer.close(userSaveLoading);
		                  common.cmsLayErrorMsg(data.msg);
		              }
		          },error:function(data){
		              top.layer.close(index);

		          }
		      }); 
		      return false;
		});
		$("#reset").click(function(){
			$("#bingqingmiaoshu").val("");
			$("#group").val("");
			$("#zhuanjiaxingming").val("");
			$("#members").val("");
			form.render('select'); //刷新select选择框渲染
			form.render('radio');
		});
		
		//附件上传
		$("#uploadFile").click(function(){
			$("#fileUpload").click();
		})
		
		
		$("#uploadPhoto").click(function(){
			var btnText = $(this).html();
			if(btnText=="拍照上传"){
				initVideo();
				$("#videoDiv").css("display","inline");
				$("#capture").css("display","inline");
				$(this).html("照片上传");
			}else{
				var canvans = document.getElementById("canvas"); 
				//获取浏览器页面的画布对象                       
			   	//以下开始编 数据   
				var imgData = canvans.toDataURL("image/jpeg", 1.0);
				//将图像转换为base64数据
				var base64Data = imgData.substr(23); 
				//在前端截取22位之后的字符串作为图像数据     
				//删除字符串前的提示信息 "data:image/png;base64,"   
				//开始异步上  
				$.ajax({
					type:"post",
					url:"../consultation/photoFile.do",
					data:{ "img": base64Data},
					dataType:"json",
					async:false,
					success:function(data){
						if(data.result){
							layer.msg(data.msg,{icon:1});
							selAllFile();
							$("#videoDiv").css("display","inline");
							$("#photoDiv").css("display","none");
						}else{
							layer.msg(data.msg,{icon:2});
						}
					}
				});
				
			}
		})
		
		//拍照
		$("#capture").click(function(){
			$("#videoDiv").css("display","none");
			$("#photoDiv").css("display","inline");
			context.drawImage(video, 0, 0, 640, 480); 
		})
		
		//进行初始化摄像头设备
		let video = document.getElementById('video');
		let canvas = document.getElementById('canvas');
		let context = canvas.getContext('2d');
		function initVideo(){
			if (navigator.mediaDevices.getUserMedia || navigator.getUserMedia || navigator.webkitGetUserMedia || navigator.mozGetUserMedia) {
				//调用用户媒体设备, 访问摄像头
			   getUserMedia({video : {width: 1024, height: 768}}, success, error);
			} else {
			   layer.msg('不支持访问用户媒体',{icon:2});
			}
		}
		
		/* 初始化调用摄像头 */
		function getUserMedia(constraints, success, error) {
		      if (navigator.webkitGetUserMedia) {
		        //webkit核心浏览器
		        navigator.webkitGetUserMedia(constraints,success, error)
		      } else if (navigator.mozGetUserMedia) {
		        //firfox浏览器
		        navigator.mozGetUserMedia(constraints, success, error);
		      } else if (navigator.getUserMedia) {
		        //旧版API
		        navigator.getUserMedia(constraints, success, error);
		      }
		}

		function success(stream) {
		    //兼容webkit核心浏览器
		    let CompatibleURL = window.URL || window.webkitURL;
		    //将视频流设置为video元素的源
		    //video.src = CompatibleURL.createObjectURL(stream);
		    video.srcObject = stream;
		    video.play();
		    return true;
		}

		function error(error) {
			$("#uploadPhoto").html("拍照上传");
			$("#videoDiv").css("display","none");
			//$("#photoDiv").css("display","none");
			$("#capture").css("display","none");
			layer.msg('未检查到摄像头设备！',{icon:2});
			
			return false;
		  //console.log(`访问用户媒体设备失败${error.name}, ${error.message}`);
		}
	
		
})
	//附加上传	
	function submitfile(){
		$("#fileForm").ajaxSubmit({
			type:"post",
	    	dataType:"json",
	  		success: function (data) {
	  			if(data.result){
	  				layer.msg(data.msg,{icon:1});
	  			}else{
	  				layer.msg(data.msg,{icon:2});
	  			}
	  			selAllFile();
	   	    }
	   	}) 
	}
	
	//加载附件列表
	$(function(){
		selAllFile();
	})
	
	var stuuid = '${stuuid}';
	//查询病史附件列表
	function selAllFile(){
		$.ajax({
			type:"get",
			data:{"stuuid":stuuid},
			url:"../consultation/selhistoryFile.do",
			dataType:"json",
			async:false,
			success:function(data){
				var list = data.fileList;
				console.log(list);
				var html = "";
				if(list!=undefined){
					for(var i = 0;i<list.length;i++){
						html += '<tr style="border: solid 1px #23262E;height: 30px;font-size: 14px;">'+
							'<td>'+list[i].accessoryOldname+'</td>'+
							'<td><a class="aCss" href="../accessory/yulanaccessory.do?path='+list[i].path+list[i].accessoryNewname+'" target="_blank">预览</a></td>'+
							'<td><a class="aCss" onclick="delFile()">删除</a>'+
							'<input type="hidden" name="fileId" id="fileId" value="'+list[i].accessoryKey+'">'+
							'</td>'+
							'</tr>'
					}
					
				}
				$("#historyFile").html(""); 
				$("#historyFile").append(html);
			}
		});
	}
	
	//删除附件
	function delFile(fileId){
		layer.confirm('确定删除该附件？', {icon: 7}, function(){
			$.ajax({
				type : "get",
				url : "../consultation/delHistoryFile.do",
				data : {"fileId" : $("#fileId").val()},
				dataType:"json",
				async:false,
				success : function(data) {
					if(data.result){
		  				layer.msg(data.msg,{icon:1});
		  				selAllFile();
		  			}else{
		  				layer.msg(data.msg,{icon:2});
		  			}
				},
				
			});
		})
	}

</script>
</html>
