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
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
	<meta name="apple-mobile-web-app-status-bar-style" content="black" />
	<meta name="apple-mobile-web-app-capable" content="yes" />
	<meta name="format-detection" content="telephone=no" />
	<link rel="stylesheet" type="text/css" href="../plugins/layui/css/layui.css" media="all" />
	<link rel="stylesheet" type="text/css" href="../css/readimage.css" media="all">
	<link rel="stylesheet" type="text/css" href="../css/faqi_baogao.css">
	<script type="text/javascript" src="../plugins/layui/layui.js"></script>
	<script type="text/javascript" src="../js/jquery-1.4.4.min.js"></script>
</head>
<body class="body_bg">
	<div>
		<div class="layui-fluid">
			<div class="layui-row layui-col-space10">
				<div class="layui-col-md12">
					<div class="layui-row grid-demo">
						<div class="layui-col-md12">
							<div class="layui-btn-container">
								<button class="layui-btn layui-btn-normal" id="yingxiang">
									<i class="layui-icon">&#xe64a;</i>影像
								</button>
							</div>
						</div>
					</div>
				</div>
				<div class="layui-col-md12">
					<div class="layui-row grid-demo">
						<div class="layui-col-md12 layui-input-bg">
							<fieldset id="menu_func_div" class="layui-elem-field">
								<legend>病例信息</legend>
								<div class="layui-form-item layui-col-md12">
									<label class="layui-form-label">患者姓名</label>
									<div class="layui-input-inline">
										<input type="text" readonly="readonly" class="layui-input" name="patientname" value="${medicalRecord.patientName}">
									</div>
									<label class="layui-form-label">性别</label>
									<div class="layui-input-inline">
										<input type="text" readonly="readonly" class="layui-input" name="sex" value="${medicalRecord.sex}">
									</div>
									<label class="layui-form-label">年龄</label>
									<div class="layui-input-inline">
										<input type="text" readonly="readonly" class="layui-input" name="age" value="${medicalRecord.age}">
									</div>
								</div>
								<div class="layui-form-item layui-col-md12">
									<label class="layui-form-label">门诊号</label>
									<div class="layui-input-inline">
										<input type="text" readonly="readonly" class="layui-input" name="patientname">
									</div>
									<label class="layui-form-label">检查编号</label>
									<div class="layui-input-inline">
										<input type="text" readonly="readonly" class="layui-input" value="${medicalRecord.studyId}">
									</div>
									<label class="layui-form-label">病人编号</label>
									<div class="layui-input-inline">
										<input type="text" readonly="readonly" class="layui-input" name="patientname" id="stuuid" value="${medicalRecord.patientKey}">
									</div>
								</div>
								<div class="layui-form-item layui-col-md12">
									<label class="layui-form-label">住院号</label>
									<div class="layui-input-inline">
										<input type="text" readonly="readonly" class="layui-input" name="patientname">
									</div>
									<label class="layui-form-label">床号</label>
									<div class="layui-input-inline">
										<input type="text" readonly="readonly" class="layui-input" name="patientname">
									</div>
									<label class="layui-form-label">送检科室</label>
									<div class="layui-input-inline">
										<input type="text" readonly="readonly" class="layui-input" name="patientname">
									</div>
								</div>
							</fieldset>
						</div>
					</div>
				</div>
				<div class="layui-col-md12">
					<div class="layui-row grid-demo">
						<div class="layui-col-md12 layui-input-bg">
							<fieldset id="menu_func_div" class="layui-elem-field">
								<legend>附件信息</legend>
								<div class="layui-col-md12 layui-col-space1" style="padding: 3px 0px 0px 15px">
										<button class="layui-btn layui-btn-normal" id="upload"><i class="layui-icon">&#xe681;</i>上传</button>
									</div>
								<div class="layui-col-md12">
									<div>
										<ul class="flow-default" id="LAY_demo2"></ul> 
									</div>
								</div>
							</fieldset>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
var stuuid="${stuuid}";
var member = new Array();
var memberName = new Array();
layui.config({
base : "../js/"
}).use([ 'form', 'layedit', 'laydate','layer','table','common','flow' ], function() {
		var limit = 999;
		var form = layui.form;
		var flow = layui.flow;
		var laydate = layui.laydate;
		var layer = layui.layer;
		var table = layui.table;
		var common = layui.common;
		 flow.load({
		    elem: '#LAY_demo2' //指定列表容器
		    ,isAuto: true
		    ,isLazyimg: true
		    ,end:' '//默认没有更多内容 改为空
		    ,done: function(page, next){ //到达临界点（默认滚动触发），触发下一页
		     var lis = [];
		     //以jQuery的Ajax请求为例，请求下一页数据（注意：page是从2开始返回）  
             $.getJSON('../accessory/getaccessoryById.do?page='+page+'&id='+stuuid, function(res){ 
                 if(res.status != 0){
                     var lis = [];  
                     layui.each(res, function(index, item){
                    	 var html='';
	                         html += '<li class="mt10" style="float:left">';
	                         html += '<dl><dt style="margin:10px;text-align:center"><img src="../img/menuicon_gray_dupian_xiangqing.png" width="48" height="56" /></dt>';
	                         html += '<dd style="text-align:center;margin:10px;">';
	                         html += '<span style="overflow:hidden;text-overflow: ellipsis;-o-text-overflow: ellipsis; white-space:nowrap;width:80px; height:20px;display:block;">'+item.accessoryOldname+'</span></dd>';
	                         html += '<dd style="text-align:center;margin:10px;">';
	                         html += '<a href="../accessory/yulanaccessory.do?path='+item.path+''+item.accessoryNewname+'" target="_blank" class="layui-btn layui-btn-normal layui-btn-xs" >预览</a>';
	                         html += '<a class="layui-btn layui-btn-normal layui-btn-xs" onclick="del(id)" id='+item.accessoryKey+'>删除</a>';
	                         html += '</dd></dl></li>';
                         lis.push(html);
                     });
                     //执行下一页渲染，第二参数为：满足“加载更多”的条件，即后面仍有分页  
                     //pages为Ajax返回的总页数，只有当前页小于总页数的情况下，才会继续出现加载更多  
                     next(lis.join(''), limit == res.length);  
                 }
             });  
		    }
		  });
	$("#upload").click(function () {
				var url = "../medicalrecord/bingliUpload.do?stuuid="+stuuid;
				common.cmsLayOpen('上传附件',url,'700px','450px');
	});
	$("#yingxiang").click(function () {
		window.open("http://123.206.29.163:8080/?stuuid="+stuuid+"&user=cc&pswd=dd");
	});
});
function del(id){
         $.ajax({
             url:"../accessory/delaccessoryById.do",
             type:"post",
             async:true,
             cache:true,
             data:{
                 id:id
             },
             success:function(data){
                 window.location.reload(true);
             }
         });
	}
</script>
</html>