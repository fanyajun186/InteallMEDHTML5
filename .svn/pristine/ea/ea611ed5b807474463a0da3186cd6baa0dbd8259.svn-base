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
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="format-detection" content="telephone=no" />

<link rel="stylesheet" type="text/css"
	href="../plugins/layui/css/layui.css" media="all" />
<link rel="stylesheet" type="text/css" href="../css/readimage.css">
<link rel="stylesheet" type="text/css" href="../css/faqi_baogao.css">
<script type="text/javascript" src="../js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="../plugins/layui/layui.js"></script>
</head>
<body class="body_bg">
	<div class="layui-fluid">
		<div class="layui-col-md12 ">
			<form class="layui-form" action="">
				<div class="layui-form-item">
					<label class="layui-form-label">专家组</label>
					<div class="layui-input-block">
						<select lay-filter="group" name="group" id="group"></select>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-input-block"
						style="height: 120px; overflow: auto; overflow-x: hidden; border: 1px solid #23262e">
						<table id="members" style="width: 100%"></table>
					</div>
				</div>
				<div class="layui-form-item layui-form-text">
					<label class="layui-form-label">专家姓名</label>
					<div class="layui-input-block">
						<input type="text" name="zhuanjiaxingming" id="zhuanjiaxingming"
							readonly="readonly" placeholder="专家姓名"
							lay-verify="zhuanjiaxingming" autocomplete="off"
							class="layui-input only-input" />
						<!-- <textarea id="zhuanjiaxingming" name="zhuanjiaxingming"  placeholder="专家姓名" class="layui-textarea layui-input"></textarea> -->

					</div>
				</div>
				<div class="layui-form-item" style="text-align: center;">
					<button class="layui-btn layui-btn-normal" lay-submit=""
						lay-filter="demo1">立即提交</button>
					<button type="reset" id="reset" class="layui-btn layui-btn-normal">重置</button>
				</div>
			</form>
		</div>
	</div>
</body>
<script>
var userLogin;
$("#medicalRecordKey").val("${medicalRecordKey}")
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
}).use(['common','form', 'layedit', 'laydate' ,'table'], function(){
		var layer = layui.layer;
		var laydate = layui.laydate;
		var form = layui.form;
		var table = layui.table;
		var $ = layui.jquery;
		var common = layui.common;
		
		form.on('select(group)', function(data){
            groupId = data.value;
            $.ajax({
               url : '../persontoperson/getMemberByGroupId.do',
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
                            
                            $("#zhuanjiaxingming").val(userName)
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
			data.field.medicalRecordKey = "${medicalRecordKey}";
			if(userLogin==""){
				common.cmsLayErrorMsg("请选择要分享的人");
				return false;
			}
			data.field.userLogin = userLogin;
			$.ajax({
			       url : 'getSaveSharePerson.do',
			       type : 'post',
			       data :{"json":JSON.stringify(data.field)},
			       success : function(data) {
			    	   data = eval("("+data+")");
			              if(data.code == 0){
			                  if(data.msg!=""&&data.msg!=null){
			                	  common.cmsLaySucMsg(data.msg);
			                  }else{
			                	  common.cmsLaySucMsg("保存成功");
			                  }
			              }else{
			                  common.cmsLayErrorMsg(data.msg);
			              }
			       },error:function(data){
			    	   var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		               parent.layer.close(index); //再执行关闭                        //刷新父页面
		               parent.location.reload();
			       }
			   });
		      return false;
		}); 
		
});
</script>
</html>
