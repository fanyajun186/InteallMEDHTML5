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
	href="../plugins/layui/css/layui.css" media="all" />
<link rel="stylesheet" type="text/css" href="../css/faqi_baogao.css" media="all">
<link rel="stylesheet" type="text/css" href="../css/quntaolun.css" media="all">
<link rel="stylesheet" type="text/css" href="../css/fenxiang.css">
<script language="javascript" src="../plugins/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="../plugins/layui/layui.js"></script>
<script type="text/javascript" src="../js/jquery-1.4.4.min.js"></script>
</head>
<body class="body_bg">
			<form class="layui-form layui-row changePwd">
				<div class="layui-col-xs12 layui-col-sm6 layui-col-md6">
					<div class="layui-form-item">
							<input type="text" name="sysuserKey" id="sysuserKey" hidden="hidden" value="${sysuserKey}">
			  				<label class="layui-input-block" style="color: red">注意:修改密码与汇沟通密码同步修改!</label>
			  		</div>
					<div class="layui-form-item">
						<div class="layui-inline">
						      <label class="layui-form-label">旧密码</label>
						      <div class="layui-input-inline">
						        <input type="password" name="oldpwd" id="oldpwd"  maxlength="30"  placeholder="请输入旧密码"  lay-verify="password" autocomplete="off" class="layui-input">
						      </div>
						 </div>
					</div>
					<div class="layui-form-item">
						<div class="layui-inline">
						      <label class="layui-form-label">新密码</label>
						      <div class="layui-input-inline">
						        <input type="password" name="newpwd" id="newpwd"  maxlength="30"  placeholder="请输入新密码"  lay-verify="password" autocomplete="off" class="layui-input">
						      </div>
						 </div>	
					</div>
					<div class="layui-form-item">
						<div class="layui-inline">
						      <label class="layui-form-label">确认密码</label>
						      <div class="layui-input-inline">
						        <input type="password" name="confirm" id="confirm"  maxlength="30"  placeholder="请输入新密码"  lay-verify="password" autocomplete="off" class="layui-input">
						      </div>
						 </div>	
					</div>
					<div class="layui-form-item">
						<div class="layui-input-block">
							<button class="layui-btn" lay-submit="" lay-filter="changePwd">立即修改</button>
							<button type="reset" class="layui-btn layui-btn-primary">重置</button>
						</div>
					</div>
				</div>
			</form>
<script>
layui.config({
    base : "../js/"
}).use([ 'element', 'layer','form' ,'table', 'laydate','common'], function() {
	var layer = layui.layer;
	var laydate = layui.laydate;
	var table = layui.table;
	var element = layui.element;
	var common = layui.common;
	var form = layui.form;
	var $ = layui.jquery;
	 /* password:function(value, item){
         if($("#password").val()!=$("#password2").val()){
       	  return '两次密码不一致，请重新输入';
         }
     },
     password2:function(value, item){
   	  if($("#password").val()!=$("#password2").val()){
       	  return '两次密码不一致，请重新输入';
         }
     } */
	//监听提交
	  form.on('submit(changePwd)', function(data){
	      var userSaveLoading = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
	      $.ajax({
	          url : 'changePassword.do',
	          type : 'post',
	          async: false,
	          data : {"json":JSON.stringify(data.field)},
	          success : function(data) {
	        	  data = eval("("+data+")");
	              if(data.code == 0){
	                  top.layer.close(userSaveLoading);
	                  common.cmsLaySucMsg("保存成功");
	                  var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
	                  parent.layer.close(index); //再执行关闭                        //刷新父页面
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
</script>
</body>
</html>