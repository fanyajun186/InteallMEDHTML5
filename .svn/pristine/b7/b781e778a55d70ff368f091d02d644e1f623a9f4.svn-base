<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
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
	<link rel="stylesheet" type="text/css" href="../css/readimage.css">
	<link rel="stylesheet" type="text/css" href="../css/faqi_baogao.css">
	<script type="text/javascript" src="../js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="../plugins/layui/layui.js"></script>
</head>
<body class="body_bg">
	<div class="layui-fluid">
		<div class="layui-col-md12 ">
			<form class="layui-form">
				<div class="layui-form-item layui-form-text">
					<label class="layui-form-label">标题</label>
					<div class="layui-input-block">
						<input id="title" name="title" placeholder="标题" lay-verify="title" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item" style="margin-top: 10px">
					<label class="layui-form-label">开始时间</label>
					<div class="layui-input-inline">
						<input type="text" id="startTime" name="startTime" lay-verify="required" autocomplete="off" class="layui-input" />
					</div>
					<label class="layui-form-label">预约时长</label>
					<div class="layui-input-inline">
						<select name="yuyueshichang" lay-verify="required">
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
						<textarea id="yuhuirenyuan" name="yuhuirenyuan" placeholder="与会人员" lay-verify="required" class="layui-textarea layui-input"></textarea>
					</div>
				</div>
				<div class="layui-form-item" style="text-align: center;">
					<button class="layui-btn layui-btn-normal" lay-submit="" lay-filter="demo1">立即提交</button>
					<button type="reset" class="layui-btn layui-btn-normal">重置</button>
				</div>
			</form>
		</div>
	</div>
</body>
<script>
var member = new Array();
var memberName = new Array();
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

		laydate.render({
			elem : '#startTime' //指定元素
			,type: 'datetime'
		});
		
		
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
		form.on('submit(demo1)', function(data){
			var userSaveLoading = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
			data.field.member = member;
			$.ajax({
		          url : 'save.do',
		          type : 'post',
		          async: false,
		          data :{"json":JSON.stringify(data.field)},
		          success : function(data) {
		        	  data = eval("("+data+")");
		              if(data.code == 0){
		                  top.layer.close(userSaveLoading);
		                  if(data.msg!=""&&data.msg!=null){
		                	  common.cmsLaySucMsg(data.msg);
		                  }else{
		                	  common.cmsLaySucMsg("保存成功");
		                  }
		                if(data.isStart){
	                	  setTimeout(function(){ // 异步，模拟ajax
		    		            	top.layer.confirm("是否立即打开读片会视频", {
		    		              		title: "提示",
		    		              	    resize: false,
		    		              	    btn: ['确定', '取消'],
		    		              	    btnAlign: 'c',
		    		              	    anim:1,
		    		              	    icon: 3,
			    		              	  yes:function(index){
				    		              	    top.layer.close(index);
				    		                	var url = data.zoomurl;
				    		                	var wi = window.open('about:blank', '_blank');
				                	    		wi.location.href = url;
				                	    		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
				                	     		parent.layer.close(index); //再执行关闭      
				                	     		parent.location.reload();
				    		              	    }
				    		            	,btn2: function(index){
				    		            		top.layer.close(index);
				    		            		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
				                	     		parent.layer.close(index); //再执行关闭      
				                	     		parent.location.reload();
				    		            	}
				    		            	,cancel: function(){
				    		            		top.layer.close(index);
				    		            		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
				                	     		parent.layer.close(index); //再执行关闭      
				                	     		parent.location.reload();
				    		            	}
			    		              	})
		                	  	}, 5000)
	                	  	}else{
			                	  var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
				                  parent.layer.close(index); //再执行关闭                        //刷新父页面
				                  parent.location.reload();
			                  };
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
		
})
</script>
</html>
