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
</head>
<body class="body_bg">
	<div class="layui-fluid">
			<div class="layui-col-md12">
				<form class="layui-form" action="">
					<div class="layui-input-inline layui-col-md12">
								<label>读片会日期</label>
								<div class="layui-input-inline layui-col-md6" >
									<input type="text" class="layui-input" id="studytime"
										placeholder="读片会日程">
								</div>
					</div>
					<fieldset class="layui-elem-field">
							<label style="color: #fff">读片会时间</label>	
							<div class="layui-col-md12">
								<ul id="daiban">
								</ul>
							</div>
						</fieldset>
					<div class="layui-form-item" style="text-align: center;">
						<button class="layui-btn layui-btn-normal" lay-submit="" lay-filter="demo1">分享</button>
						<button class="layui-btn layui-btn-normal" type="reset">重置</button>
					</div>
				</form>
			</div>
	</div>
</body>
<script>
var readimagekey;
layui.config({
    base : "../js/"
}).use(['common','form', 'layedit', 'laydate' ,'table'], function(){
		var layer = layui.layer;
		var laydate = layui.laydate;
		var form = layui.form;
		var table = layui.table;
		var $ = layui.jquery;
		var common = layui.common;
		 $(function(){
			  $.ajax({
		          url : '../readimage/getReadImageDate.do',
		          type : 'post',
		          async: false,
		          success : function(data) {
			        	  data = eval("("+data+")");
			        	  var specialDates = new Array();
			              if(data.code == 0){
			            	  readImageList = data.readImageList;
			              }else{
			                  common.cmsLayErrorMsg(data.msg);
			              }
		              }
			  })
		 })
		laydate.render({
		    elem: '#studytime'
		    ,change: function(value, date){
				}
				//控件选择完毕后的回调,点击日期、清空、现在、确定均会触发。
			,done: function(value, date, endDate){
				var riqi = value;
        		var html="";
        		if(readImageList!=null){
	        		for(var i=0;i<readImageList.length;i++){
	               		var a = new Date(readImageList[i].startTime || new Date);
	               		var year = a.getFullYear();
					  	var month = a.getMonth() < 9 ? "0" + (0 | a.getMonth()+1) : a.getMonth()+1;
					  	var day = a.getDate() < 10 ? "0" + (0 | a.getDate()) : a.getDate();
	               		var hour = a.getHours() < 10 ? "0" + (0 | a.getHours()) : a.getHours();
						var min = a.getMinutes() < 10 ? "0" + (0 | a.getMinutes()) : a.getMinutes();
						var sec = a.getSeconds() < 10 ? "0" + (0 | a.getSeconds()) : a.getSeconds();
						if((year + "-" + month+ "-" + day)==riqi){
							html+= "<li class='daibanTime' readimage_key='"+readImageList[i].readimageKey+"'>"+hour + ":" + min + ":" + sec+"</li>"
						}
	                }
        		}
        		//清空该日的待办，增加该日的待办
        		$("#daiban").html("");
				$("#daiban").append(html);
				//给具体的时间增加点击事件
				$(".daibanTime").click(function(){
					var _this = $(this);
					$(".daibanTimefocus").removeClass("daibanTimefocus");
					_this.addClass("daibanTimefocus");
					readimagekey = _this.attr("readimage_key");
				});
				}
		  });
		form.on('submit(demo1)', function(data){
			data.field.medicalRecordKey = "${medicalRecordKey}";
			data.field.readimagekey = readimagekey;
			if(readimagekey==undefined){
				common.cmsLayErrorMsg("请选择要分享的读片会日程");
				return false;
			}
			$.ajax({
			       url : 'getSaveShareRreadimage',
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
