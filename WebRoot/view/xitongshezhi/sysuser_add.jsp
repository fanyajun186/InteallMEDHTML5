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
<script type="text/javascript" src="../js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="../plugins/layui/layui.js"></script>
</head>
<body class="body_bg">
 
<form class="layui-form" style="margin-top: 10px" action="">
  <div class="layui-form-item">
    <div class="layui-inline">
      <label class="layui-form-label"><spite style="color: red">*</spite>用户名</label>
      <div class="layui-input-inline">
        <input type="text" name="userName" id="userName"  maxlength="30"  placeholder="用户名"  lay-verify="required|userName" autocomplete="off" class="layui-input">
      </div>
    </div>
    <div class="layui-inline">
      <label class="layui-form-label"><spite style="color: red">*</spite>登录名</label>
      <div class="layui-input-inline">
        <input type="text" name="loginName" id="loginName"  maxlength="30"  placeholder="登录名" onblur="checkLoginName();"  lay-verify="required|loginName" autocomplete="off" class="layui-input">
      </div>
    </div>
  </div>
  
  <div class="layui-form-item">
    <div class="layui-inline">
      <label class="layui-form-label"><spite style="color: red">*</spite>密码</label>
      <div class="layui-input-inline">
        <input type="password" name="password" id="password"  maxlength="30"  placeholder="密码"  lay-verify="required|password" autocomplete="off" class="layui-input">
      </div>
    </div>
    <div class="layui-inline">
      <label class="layui-form-label"><spite style="color: red">*</spite>确认密码</label>
      <div class="layui-input-inline">
        <input type="password" name="password2" id="password2"  maxlength="30"  placeholder="确认密码"  lay-verify="required|password2" autocomplete="off" class="layui-input">
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
	  	<label class="layui-form-label">手机号码</label>
	    <div class="layui-input-inline">
	      <input type="text" name="userPhone" id="userPhone" placeholder="手机号码"  maxlength="11" autocomplete="off" class="layui-input">
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
  </div>
  <div class="layui-form-item">
  	<label class="layui-form-label">所属角色</label>
  	<div class="layui-input-block" id="rolelist">
        
    </div>
  </div>
  <div class="layui-form-item">
    <div class="layui-input-block">
      <button class="layui-btn layui-btn-normal" lay-submit="" lay-filter="demo1">立即提交</button>
      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
    </div>
  </div>
</form>
          
<script>
$(function(){
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
	
	
	$.ajax({
        url : '../sysrole/getRole.do',
        type : 'post',
        async: false,
        success : function(data) {
      	  data = eval("("+data+")");
      	  if(data.code==0){
      		  var html="";
      	  	  for(var i=0;i<data.sysRoleList.length;i++){
      	  		  html+='<input type="checkbox" value="'+data.sysRoleList[i].sysRoleKey+'" name="'+data.sysRoleList[i].roleName+'" title="'+data.sysRoleList[i].roleName+'">'
      	  	  }
      	  	  $("#rolelist").append(html);
      	  }else{
      		  layer.msg("获取角色数据出错，请稍后重试！",{icon: 5});
      	  }
      	  
        },error:function(data){
        	layer.msg("获取角色数据出错，请稍后重试！",{icon: 5});
        }
	});
})
var isExist = false;
function checkLoginName(){
	var loginName = document.getElementById("loginName");
	 $.ajax({
			type : 'GET',
			url : 'checkLoginName.do',
			data : {
				'loginName' : loginName.value
			},
			success : function(data) {
				data = eval("("+data+")");
				if(data.code == "0"&&data.count>0){
					isExist = true;
					layer.msg("用户名已经存在",{icon: 5});
				}else if(data.code == "0"&&data.count==0){
					isExist = false;
				}
			},error:function(data){
	        	layer.msg("校验用户名失败，请稍后重试！",{icon: 5});
	        }
		}); 
}

layui.config({
    base : "../js/"
}).use(['common','form', 'layedit', 'laydate'], function(){
  var form = layui.form
  ,layer = layui.layer
  ,layedit = layui.layedit
  ,laydate = layui.laydate
  ,common = layui.common;
  
  form.verify({
      loginName: function(value, item){
          //验证登陆账号
          if(!new RegExp("^[0-9A-Za-z_]{2,20}$").test(value)){
              return '登陆账号只能为英文、数字、下划线，长度2-20位';
          }
          if(isExist==true){
        	  return '用户名已经存在！';
          }
          //验证登陆账号是否存在

      },
      userName: function(value, item){
          //验证用户名
          if(!new RegExp("^([\u4e00-\u9fa5]){2,10}$").test(value)){
              return '用户姓名只能为中文，长度2-7位';
          }
      },
      password: function(value, item){
          if($("#password").val()!=$("#password2").val()){
        	  return '两次密码不一致，请重新输入';
          }
      },
      password2: function(value, item){
    	  if($("#password").val()!=$("#password2").val()){
        	  return '两次密码不一致，请重新输入';
          }
      }
  });
  
  //监听提交
  form.on('submit(demo1)', function(data){
	  console.log(data);
	  //获取所有选中的角色id
      var sysRole = new Array();
      $.each($('input:checkbox:checked'),function(){
    	  sysRole.push($(this).val());
      });
      data.field.sysRole = sysRole;
      console.log(sysRole);
      if(sysRole.length==0){
    	  common.cmsLayErrorMsg("角色不能为空");
    	  return false;
      }else{
    	  var userSaveLoading = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
          $.ajax({
              url : 'save.do',
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
                      parent.layui.table.reload('sysusertable', {
          		        page: {
          		          curr: 1 //重新从第 1 页开始
          		        }
          		        ,where:{
          		        	'role_name':$("#role_name").val()
          				},
          		      });
                  }else{
                      top.layer.close(userSaveLoading);
                      common.cmsLayErrorMsg(data.msg);
                  }
              },error:function(data){
                  top.layer.close(index);

              }
          });
      }
  });
  
  
});
</script>

</body>
</html>