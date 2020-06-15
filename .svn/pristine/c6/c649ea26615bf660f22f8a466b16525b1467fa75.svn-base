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
                    <label class="layui-form-label">模板名称</label>
                    <div class="layui-input-block">
                        <input name="modelName" placeholder="模板名称" class="layui-input" value="${reportTemplateWithBLOBs.reportTemplateName}">
                        <input type="hidden" name="reportTemplateKey" value="${reportTemplateWithBLOBs.reportTemplateKey}"  class="layui-input" />
                    </div>
                </div> 
				<div class="layui-form-item" style="margin-top: 10px">
					<label class="layui-form-label">检查所见</label>
					<div class="layui-input-block">
						<textarea name="jianchansuojian" placeholder="检查所见" class="layui-textarea layui-input">${reportTemplateWithBLOBs.checkView}</textarea>
					</div>
				</div> 
				<div class="layui-form-item" style="margin-top: 10px">
					<label class="layui-form-label">诊断结果</label>
					<div class="layui-input-block">
						<textarea name="zhenduanjieguo" placeholder="诊断结果" class="layui-textarea layui-input">${reportTemplateWithBLOBs.diagnosisResult}</textarea>
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
		$.ajax({
	          url : 'updateModel.do',
	          type : 'post',
	          async: false,
	          data :{"json":JSON.stringify(data.field)},
	          success : function(data) {
	        	  data = eval("("+data+")");
	              if(data.code==0){
	                  top.layer.close(userSaveLoading);
                	  common.cmsLaySucMsg("保存成功");
                	  var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                	  parent.layer.close(index); //再执行关闭 
                	  window.parent.refreshTree();
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
