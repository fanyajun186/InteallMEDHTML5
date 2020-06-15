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
	<script language="javascript" src="../plugins/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="../js/jquery.ztree.core.js"></script>
	<script type="text/javascript" src="../js/jquery.ztree.excheck.js"></script>
	<link rel="stylesheet" href="../css/zTreeStyle/zTreeStyle.css"
	type="text/css" />
</head>
<body class="body_bg">
	<div class="layui-fluid">
			<div class="layui-col-md12">
				<form class="layui-form" action="">
					<div class="layui-input-inline layui-col-md12">
								<label>收藏夹</label>
								<div class="layui-col-md12">
								<ul id="treeDemo" class="ztree"></ul>
								</div>
					</div>
					<div class="layui-form-item" style="text-align: center;">
						<button class="layui-btn layui-btn-normal" lay-submit="" lay-filter="demo1">分享</button>
						<button class="layui-btn layui-btn-normal" type="reset">重置</button>
					</div>
				</form>
			</div>
	</div>
</body>
<script>
var collectionKey;
var setting = {
		data : {
			key : {
				name : "collection_name"
			},
			simpleData : {
				enable : true,
				idKey : "collection_key",
				pIdKey : "parent_key",
			}
		},
		callback : {
			onClick : onClick
		}
	};
function onClick(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	zTree.expandNode(treeNode);
	collectionKey= treeNode.collection_key;
}
$(document).ready(function() {
	$.ajax({
		url : "../collection/getAll.do",
		type : "get",
		success : function(data) {
			var json = eval("(" + data + ")");
			$.fn.zTree.init($("#treeDemo"), setting, json.collectionList);
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
		form.on('submit(demo1)', function(data){
			data.field.medicalRecordKey = "${medicalRecordKey}";
			data.field.collectionKey = collectionKey;
			if(collectionKey==undefined){
				common.cmsLayErrorMsg("请选择要分享的文件夹");
				return false;
			}
			$.ajax({
			       url : 'getSaveShareCollection.do',
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
