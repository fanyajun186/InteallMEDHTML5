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
	
	<link rel="stylesheet" type="text/css" href="../../plugins/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="../../css/zTreeStyle/demo.css" type="text/css">
	<link rel="stylesheet" href="../../css/zTreeStyle/zTreeStyle.css" type="text/css">

	<link rel="stylesheet" type="text/css" href="../../css/faqi_baogao.css">
	
	<script type="text/javascript" src="../../js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="../../js/jquery.ztree.core.js"></script>
	<script type="text/javascript" src="../../js/jquery.ztree.excheck.js"></script>
	<script type="text/javascript" src="../../plugins/layui/layui.js"></script>
</head>
<body class="body_bg">
	<div class="layui-fluid">
		<div class="layui-col-md12">
			<div class="layui-field-box">
				<fieldset id="menu_func_div" class="layui-elem-field">
					<div class="layui-form-item layui-col-md12">
						<label class="layui-form-label"></label>
						<div class="layui-input-inline">
							<button class="layui-btn layui-btn-normal">
								<i class="layui-icon">&#xe6b2;</i>审核
							</button>
						</div>	
					</div>
					<form class="layui-form layui-col-md12">
						<div class="layui-form-item layui-col-md12">
							<label class="layui-form-label">患者姓名</label>
							<div class="layui-input-inline">
								<input type="text" class="layui-input" name="patientname">
							</div>
							<label class="layui-form-label">性别</label>
							<div class="layui-input-inline">
								<input type="text" class="layui-input" name="patientname">
							</div>
							<label class="layui-form-label">年龄</label>
							<div class="layui-input-inline">
								<input type="text" class="layui-input" name="patientname">
							</div>

						</div>

						<div class="layui-form-item layui-col-md12">
							<label class="layui-form-label">门诊号</label>
							<div class="layui-input-inline">
								<input type="text" class="layui-input" name="patientname">
							</div>
							<label class="layui-form-label">检查编号</label>
							<div class="layui-input-inline">
								<input type="text" class="layui-input" name="patientname">
							</div>
							<label class="layui-form-label">病人编号</label>
							<div class="layui-input-inline">
								<input type="text" class="layui-input" name="patientname">
							</div>

						</div>

						<div class="layui-form-item layui-col-md12">
							<label class="layui-form-label">住院号</label>
							<div class="layui-input-inline">
								<input type="text" class="layui-input" name="patientname">
							</div>
							<label class="layui-form-label">床号</label>
							<div class="layui-input-inline">
								<input type="text" class="layui-input" name="patientname">
							</div>
							<label class="layui-form-label">送检科室</label>
							<div class="layui-input-inline">
								<input type="text" class="layui-input" name="patientname">
							</div>

						</div>

						<div class="layui-form-item layui-col-md12">
							<label class="layui-form-label">送检人</label>
							<div class="layui-input-inline">
								<input type="text" class="layui-input" name="patientname">
							</div>
							<label class="layui-form-label">临床诊断</label>
							<div class="layui-input-inline">
								<input type="text" class="layui-input" name="patientname">
							</div>
							<label class="layui-form-label">检查部位</label>
							<div class="layui-input-inline">
								<input type="text" class="layui-input" name="patientname">
							</div>

						</div>

						<div class="layui-form-item layui-col-md12">
							<label class="layui-form-label">检查名称</label>
							<div class="layui-input-inline">
								<input type="text" class="layui-input" name="patientname">
							</div>
						</div>

						<div class="layui-form-item layui-form-text">
							<label class="layui-form-label">检查方法</label>
							<div class=" layui-col-md10">
								<textarea name="jianchafangfa" id="jianchafangfa"
									placeholder="请输入描述" class="layui-textarea layui-input layui-col-md10" style="height: 40px;color: #fff"></textarea>
							</div>
						</div>

						<div class="layui-form-item layui-form-text">
							<label class="layui-form-label">检查所见</label>
							<div class=" layui-col-md10">
								<textarea id="description" name="description"
									placeholder="请输入描述" class="layui-textarea layui-input layui-col-md10" style="height: 90px;"></textarea>
							</div>
						</div>

						<div class="layui-form-item layui-form-text">
							<label class="layui-form-label">诊断结果</label>
							<div class=" layui-col-md10">
								<textarea id="description" name="description"
									placeholder="请输入描述" class="layui-textarea layui-input layui-col-md10" style="height: 90px;"></textarea>
							</div>
						</div>

						<div class="layui-form-item layui-form-text">
							<label class="layui-form-label">思考分析</label>
							<div class=" layui-col-md10">
								<textarea id="description" name="description"
									placeholder="请输入描述" class="layui-textarea layui-input layui-col-md10" style="height: 90px;color: #fff"></textarea>
							</div>
						</div>

						<div class="layui-form-item layui-form-text">
							<label class="layui-form-label">关键图像</label>
							<div class=" layui-col-md10">
								<textarea id="description" name="description"
									placeholder="请输入描述" class="layui-textarea layui-input layui-col-md10" style="height: 40px;color: #fff"></textarea>
							</div>
						</div>

						<div class="layui-form-item layui-form-text">
							<label class="layui-form-label">检查建议</label>
							<div class=" layui-col-md10">
								<textarea id="description" name="description"
									placeholder="请输入描述" class="layui-textarea layui-input layui-col-md10" style="height: 40px;color: #fff"></textarea>
							</div>
						</div>

						<div class="layui-form-item layui-form-text">
							<label class="layui-form-label">是否审核</label>
							<div class="layui-input-inline">
								<input type="checkbox" name="close" lay-skin="switch" style="color: #fff" lay-filter="switchTest" lay-text="是|否">
							</div>
							<div  id="newDiv" style="display: none">
								<label class="layui-form-label">审核人</label>
								<div class="layui-input-inline">
									<input type="text" name="shenheren" class="layui-input" style="color: #fff"/>
								</div>
							</div>
						</div>

					</form>
				</fieldset>
			</div>

		</div>
	</div>

</body>
<script type="text/javascript">
	layui.use([ 'form', 'layedit', 'laydate' ], function() {
		var layedit = layui.layedit;
		layedit.build('jianchafangfa',{
			 height: 60 //设置编辑器高度
			 ,background: "black" //设置编辑器高度
			 ,tool: [
				  'strong' //加粗
				  ,'italic' //斜体
				  ,'underline' //下划线
				  ,'del' //删除线
				  ,'|' //分割线
				  ,'left' //左对齐
				  ,'center' //居中对齐
				  ,'right' //右对齐
				  ,'face' //表情
				]
		}); //建立编辑器
	  
		var form = layui.form;
		form.on('switch(switchTest)', function(data) {
			if (this.checked == true) {
				$("#newDiv").show();
			} else if (this.checked == false) {
				$("#newDiv").hide();
			}
		});
	});
	
	var setting = {
		data: {
			simpleData: {
				enable: true,
				idKey: "reportTypeKey",
                pIdKey: "parentCode",
			}
		},
		callback: {
			onClick: onClick
		}
	};
	
	function onClick(e,treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		zTree.expandNode(treeNode);
		if(!treeNode.isParent){
			alert(treeNode.name)
		}
	}
	
	$(document).ready(function(){
		$.ajax({
			url:"../../modality/getAll.do",
			type:"get",
			success:function(data){
				var json = eval("("+data+")");
				var list = json.ModalityList
				var html = "";
				for(var i=0;i<list.length;i++){
					html+= "<option value="+list[i].modalityKey+">"+list[i].modalityName+"</option>"
				}
				$("#reporttype").append(html);
			}
			
		});
		$("#reporttype").change(function(){
			console.log($(this).val());
			$.ajax({
				url:"../../reporttype/getAll.do?reporttype="+$(this).val(),
				type:"get",
				success:function(data){
					var json = eval("("+data+")");
					$.fn.zTree.init($("#treeDemo"), setting, json.ReportTypeList);
				}
				
			});
		})
	});
</script>
</html>
