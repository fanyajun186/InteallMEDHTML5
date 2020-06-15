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
				<div class="layui-form-item" style="margin-top: 10px">
					<label class="layui-form-label">当前收藏夹</label>
					<div class="layui-input-inline">
						<input class="layui-input" readonly="readonly" value="${collectionName}">
					</div>
				</div> 
				<div class="layui-form-item" style="margin-top: 10px">
					<label class="layui-form-label">新建收藏夹</label>
					<div class="layui-input-inline">
						<input type="text" id="collectionName" name="collectionName" lay-verify="required"  class="layui-input" />
						<input type="hidden" id="parent_key" name="parent_key" value="${parentkey}"  class="layui-input" />
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
layui.config({
    base : "../js/"
}).use(['common','form', 'layedit', 'laydate'], function(){
		var layer = layui.layer;
		var laydate = layui.laydate;
		var form = layui.form;
		var $ = layui.jquery;
		var common = layui.common;
$(function(){
	form.on('submit(demo1)', function(data){
		var userSaveLoading = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
		data.field.member = member;
		$.ajax({
	          url : 'insertParentCollection.do',
	          type : 'post',
	          async: false,
	          data :{"json":JSON.stringify(data.field)},
	          success : function(data) {
	              if(data){
	                  top.layer.close(userSaveLoading);
	                  if(data.msg!=""&&data.msg!=null){
	                	  common.cmsLaySucMsg(data.msg);
	                  }else{
	                	  common.cmsLaySucMsg("保存成功");
	                	  window.parent.location.reload();
	                  }
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
	});
})
</script>
</html>
