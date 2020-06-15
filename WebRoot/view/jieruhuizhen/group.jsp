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

	<link rel="stylesheet" type="text/css" href="../plugins/layui/css/layui.css" media="all" />
	<link rel="stylesheet" type="text/css" href="../css/readimage.css">
	<link rel="stylesheet" type="text/css" href="../css/faqi_baogao.css">
	<script type="text/javascript" src="../js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="../plugins/layui/layui.js"></script>
</head>
<body class="body_bg">
	<div class="layui-fluid">
	<div class="layui-col-md12 layui-form">
	   <div class="layui-form-item">
            <label class="layui-form-label">病情描述</label>
            <div class="layui-input-block">
                <textarea id="bingqingmiaoshu" name="bingqingmiaoshu" placeholder="病情描述"
                    class="layui-textarea layui-input"></textarea>
            </div>
        </div>
		<div class="layui-form-item">
			<label class="layui-form-label">专家组</label>
			<div class="layui-input-block">
				<select lay-filter="group" name="group" id="group"></select>
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block"
				style="height: 200px; overflow: auto; overflow-x: hidden; border: 1px solid #23262e">
				<table id="members" style="width: 100%"></table>
			</div>
		</div>
		<div class="layui-form-item layui-form-text">
			<label class="layui-form-label">专家姓名</label>
			<div class="layui-input-block">
				<textarea id="zhuanjiaxingming" name="zhuanjiaxingming" readonly="readonly" placeholder="专家姓名"
					class="layui-textarea layui-input"></textarea>
			</div>
		</div>
		<div class="layui-form-item" style="text-align: center;">
			<button class="layui-btn layui-btn-normal" lay-submit=""
				lay-filter="demo1">立即提交</button>
			<button type="reset" id="reset" class="layui-btn layui-btn-normal">重置</button>
		</div>
	</div>
	</div>
</body>
<script type="text/javascript">
var userLogin;
var interventionKey = "${interventionKey}";
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
							userLogin = _this.attr("userLogin");
							var userName = _this.text();
							
							$("#zhuanjiaxingming").text(userName)
		        		})
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
				common.cmsLayErrorMsg("请选择专家组或者专家！");
				return false;
			}
			var userSaveLoading = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
			$.ajax({
		          url : 'submitInterventionKey.do',
		          type : 'post',
		          async: false,
		          data :{"interventionKey":interventionKey,"groupId":groupId,"userLogin":userLogin,"bingqingmiaoshu":$("#bingqingmiaoshu").val(),"zhuanjiaxingming":$("#zhuanjiaxingming").val()},
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
})
</script>
</html>
