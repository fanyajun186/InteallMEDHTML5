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
<script type="text/javascript" src="../plugins/layui/layui.js"></script>
<script type="text/javascript" src="../js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="../js/jquery.form.min.js"></script>

</head>
<body class="body_bg">
			<form class="layui-form" style="margin-top: 10px" action="">
			  <div class="layui-form-item">
			    <div class="layui-inline">
			      <label class="layui-form-label"><spite style="color: red">*</spite>用户名</label>
			      <div class="layui-input-inline">
			        <input type="text" name="userName" id="userName"  maxlength="30"  placeholder="用户名"  lay-verify="required|userName" autocomplete="off" class="layui-input">
			        <input type="text" name="sysuserKey" id="sysuserKey" hidden="hidden">
			      </div>
			    </div>
			    <div class="layui-inline">
			      <label class="layui-form-label"><spite style="color: red">*</spite>登录名</label>
			      <div class="layui-input-inline">
			        <input type="text" name="loginName"  disabled="disabled" id="loginName"   maxlength="30"  placeholder="登录名" onblur="checkLoginName();"  lay-verify="required|loginName" autocomplete="off" class="layui-input layui-disabled">
			      </div>
			    </div>
			  </div>
			  <div class="layui-form-item">
			    <div class="layui-inline">
			      <label class="layui-form-label">年龄</label>
			      <div class="layui-input-inline">
			        <input type="text" name="userAge" id="userAge"  maxlength="3"  placeholder="年龄"  autocomplete="off" class="layui-input">
			      </div>
			    </div>
			    <div class="layui-inline">
			      <label class="layui-form-label">性别</label>
			      <div class="layui-input-inline">
			      	<input type="radio" name="userSex" value="0" title="男" checked="">
				  	<input type="radio" name="userSex" value="1" title="女">
			      </div>
			    </div>
			  </div>
			  <div class="layui-form-item">
			    
			  	<div class="layui-inline">
				  	<label class="layui-form-label"><spite style="color: red">*</spite>手机号码</label>
				    <div class="layui-input-inline">
				      <input type="text" name="userPhone" id="userPhone" lay-verify="userPhone" placeholder="手机号码"  maxlength="11" autocomplete="off" class="layui-input">
				    </div>
			    </div>
			  	<div class="layui-inline">
				  	<label class="layui-form-label">邮箱地址</label>
				    <div class="layui-input-inline">
				      <input type="text" name="userEmail" id="userEmail" placeholder="邮箱地址" maxlength="30" autocomplete="off" class="layui-input">
				    </div>
			    </div>
			  </div>
			  <div class="layui-form-item">
			    <div class="layui-inline">
			      <label class="layui-form-label">所属医院</label>
			      <div class="layui-input-inline">
			        <select name="sysHospitalKey" id="sysHospitalKey">
			        </select>
			      </div>
			    </div>
			    <div class="layui-inline">
			      <label class="layui-form-label">所属科室</label>
			      <div class="layui-input-inline">
			        <input type="text" name="deptName" id="deptName"  maxlength="30"  placeholder="所属科室"  autocomplete="off" class="layui-input">
			      </div>
			    </div>
			    <div class="layui-inline">
			      <label class="layui-form-label">电子签名</label>
			      <div class="layui-input-inline">
			        <input type="text" readonly="readonly" name="qmName" id="qmName"  maxlength="30"  placeholder="点击上传电子签名照"  autocomplete="off" class="layui-input">
			     	<input type="hidden" name="qmUrl" id="qmUrl" value="">
			      </div>
			    </div>
			  </div>
			  <div class="layui-form-item" style="text-align: center; padding-top: 10px;">
			      <button class="layui-btn layui-btn-normal" lay-submit="" lay-filter="demo1">保存</button>
			  </div>
			</form>
			<!-- 签名上传 -->
			<form action="uploadQianming.do" enctype="multipart/form-data" id="qianmingform" method="post" style="display: none;">
				<input type="file" id="qianming" name="qianming" value="" onchange="submitqianming();">
			</form>
<script>
var sysUser = ${sysuser};
$(function(){
	//加载医院
	$.ajax({
        url : '../syshospital/getHospital.do',
        type : 'post',
        async: false,
        success : function(data) {
      	  data = eval("("+data+")");
      	  if(data.code==0){
      		  var html="<option  value=''>请选择</option>";
      	  	  for(var i=0;i<data.sysHospitalList.length;i++){
      	  		  html+="<option  value='"+data.sysHospitalList[i].sysHospitalKey+"'>"+data.sysHospitalList[i].hospName+"</option>";
      	  	  }
      	  	  $("#sysHospitalKey").append(html);
      	  }else{
      		  layer.msg("获取医院数据出错，请稍后重试！",{icon: 5});
      	  }
      	  
        },error:function(data){
        	layer.msg("获取医院数据出错，请稍后重试！",{icon: 5});
        }
	});
	 //加载表单信息
	$("#sysuserKey").val(sysUser.sysuserKey);
	$("#userName").val(sysUser.userName);
	$("#loginName").val(sysUser.loginName);
	$("#userAge").val(sysUser.userAge);
	$("input:radio[name='userSex']").eq(sysUser.userSex).attr("checked",'checked');
	$("#userPhone").val(sysUser.userPhone);
	$("#userEmail").val(sysUser.userEmail);
	$("#deptName").val(sysUser.deptName);
	$("#sysHospitalKey").val(sysUser.sysHospitalKey);
	$("#qmName").val(sysUser.qmName);
	$("#qmUrl").val(sysUser.qmUrl);
});
layui.config({
    base : "../js/"
}).use([ 'element', 'layer','form' ,'table', 'laydate','common'], function() {
	var layer = layui.layer;
	var common = layui.common;
	var laydate = layui.laydate;
	var table = layui.table;
	var element = layui.element;
	var form = layui.form;
	var $ = layui.jquery;
	 form.verify({
	      userName: function(value, item){
	          //验证用户名
	          if(!new RegExp("^([\u4e00-\u9fa5]){2,10}$").test(value)){
	              return '用户姓名只能为中文，长度2-7位';
	          }
	      },
	      userPhone:function(value,item){
	    	  //用户手机号
	    	  var regPhone = /^((\d{3}-\d{8}|\d{4}-\d{7,8})|(1[3|5|7|8][0-9]{9}))$/
	    	  if(value==null || value=='' ||  !regPhone.test(value)){
	    		  return '请填写正确的手机号';
	    	  }
	      }
	  });
	  //监听提交
	  form.on('submit(demo1)', function(data){
	      data.field.sysuserKey = sysUser.sysuserKey
	      var userSaveLoading = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
	      $.ajax({
	          url : 'updateByuserId.do',
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
	  
	  $("#qmName").click(function(){
		  $("#qianming").click();
	  })
	
});

function submitqianming(){
	$("#qianmingform").ajaxSubmit({
		type:"post",
    	dataType:"json",
  		success: function (data) {
  			$("#qmName").val(data.qmName);
  			$("#qmUrl").val(data.qmUrl);
   	    }
   	}) 
}

</script>
</body>
</html>

