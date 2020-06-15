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
<link rel="stylesheet" href="../css/zTreeStyle/demo.css" type="text/css">
<link rel="stylesheet" href="../css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="../js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="../plugins/layui/layui.js"></script>

<script type="text/javascript" src="../js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="../js/jquery.ztree.excheck.js"></script>
</head>
<body class="body_bg">
 
<form class="layui-form" style="margin-top: 10px" action="">
  <div class="layui-form-item">
    <div class="layui-inline">
      <label class="layui-form-label">角色名</label>
      <div class="layui-input-inline">
        <input type="text" name="roleName" id="roleName"  maxlength="30"  placeholder="角色名"  lay-verify="required|roleName" autocomplete="off" class="layui-input">
      	<input type="text" name="sysRoleKey" id="sysRoleKey" hidden="true">
      </div>
    </div>
  </div>
  <div class="layui-form-item">
    <div class="layui-inline">
      <label class="layui-form-label">授权</label>
      <div class="layui-input-inline">
        <ul id="treeRight" class="ztree"></ul>
      </div>
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
var sysRole = ${sysRole};
var sysRights = ${sysRights};
var zTree;
$(function(){
	$("#roleName").val(sysRole.roleName);
	var setting = {
		check: {
			enable: true,
			
		},
		data: {
			key : {
				name : "rightName"
			},
			simpleData: {
				enable: true,
				idKey: "sysRightKey",
                pIdKey: "rightParent",
			}
		}
		
	};
	$.ajax({
		url:"../sysright/getRight.do",
		type:"get",
		success:function(data){
			var json = eval("("+data+")");
			zTree = $.fn.zTree.init($("#treeRight"), setting, json.sysRightList);
			for(var i=0;i<${sysRights}.length;i++){
				var node = zTree.getNodeByParam("sysRightKey",${sysRights}[i].sysRightKey);
				zTree.checkNode(node, true, false);
			}
		}
		
	});
})

layui.config({
    base : "../js/"
}).use(['common','form', 'layedit', 'laydate'], function(){
  var form = layui.form
  ,layer = layui.layer
  ,layedit = layui.layedit
  ,laydate = layui.laydate
  ,common = layui.common;
  
  form.verify({
      roleName: function(value, item){
          //验证用户名
          if(!new RegExp("^([\u4e00-\u9fa5]){2,10}$").test(value)){
              return '角色名只能为中文，长度2-7位';
          }
      },
  });
  
  //监听提交
  form.on('submit(demo1)', function(data){
	  
	  var nodes=zTree.getCheckedNodes(true);  
	  
	  
      var rights=new Array();  
      if(nodes.length==0){  
          layer.msg("请选择权限！",{icon: 5});
          return false;  
      }  
      for (var i = 0; i < nodes.length; i++) { 
		rights.push(nodes[i].sysRightKey);  
      }  
      var userSaveLoading = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
      $.ajax({
          url : 'updateById.do',
          type : 'post',
          async: false,
          data : {"sysRoleKey":sysRole.sysRoleKey,"roleName":$("#roleName").val(),"rights":JSON.stringify(rights)},
          success : function(data) {
        	  data = eval("("+data+")");
              if(data.code == 0){
                  top.layer.close(userSaveLoading);
                  common.cmsLaySucMsg("保存成功");
                  var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                  parent.layer.close(index); //再执行关闭                        //刷新父页面
                  parent.layui.table.reload('sysroletable', {
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
      return false;
  });
  
  
});
</script>

</body>
</html>