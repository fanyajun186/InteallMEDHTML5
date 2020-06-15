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
	href="plugins/layui/css/layui.css" media="all" />
<link rel="stylesheet" type="text/css" href="css/faqi_baogao.css"
	media="all">
<link rel="stylesheet" type="text/css" href="css/quntaolun.css"
	media="all">
<link rel="stylesheet" type="text/css" href="css/fenxiang.css">
<script language="javascript"
	src="plugins/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="plugins/layui/layui.js"></script>
</head>
<body class="body_bg">
	<div class="layui-fluid">
		<div class="layui-col-md12">
			<div class="layui-col-md3">
				<div class="layui-tab layui-tab-card">
					<ul class="layui-tab-title">
						<li class="layui-this">固定群</li>
						<li>个人群</li>
						<li>讨论组</li>
					</ul>
					<div class="layui-tab-content">
						<div class="layui-tab-item layui-show">
							<div class="layui-row  layui-tab-input-bg">
								<table class="layui-table" id="gudingqun" lay-filter="gudingqun">

								</table>
							</div>
						</div>
						<div class="layui-tab-item ">
							<div class="layui-row  layui-tab-input-bg">
								<table class="layui-table" id="gerenqun" lay-filter="gerenqun">
								</table>
							</div>
						</div>
						<div class="layui-tab-item ">
							<div class="layui-row  layui-tab-input-bg">
								<table class="layui-table" id="taolunzu" lay-filter="taolunzu">
								</table>
							</div>
						</div>

					</div>
				</div>
			</div>


			<div class="layui-col-md9">
				<div class="layui-field-box">
					<div
						class="layui-col-md12 layui-form layui-input-bg layui-col-space1">
						<div
							style="margin-top: 15px; margin-bottom: 15px; margin-left: 10px;">
							<div class="layui-input-inline">
								<input type="text" name="username" id="username"
									placeholder="患者姓名" class="layui-input">
							</div>
							<div class="layui-input-inline">
								<div class="layui-input-inline">
									<input type="text" readonly="readonly" class="layui-input"
										id="studytime" placeholder="检查时间">
								</div>
							</div>
							<div class="layui-input-inline" style="text-align: center;">
								<select id="modality" name="modality">
									<option value="">检查类型</option>
								</select>
							</div>
							<div class="layui-input-inline" style="text-align: center;">
								<input type="radio" name="sex" value="M" title="男"> <input
									type="radio" name="sex" value="F" title="女">
							</div>

							<div class="layui-input-inline" style="width: 145px;">
								<button class="layui-btn layui-btn-normal" id="query">查询</button>
								<button class="layui-btn layui-btn-normal" id="reset">重置</button>
							</div>

						</div>
					</div>
				</div>
				<div class="layui-field-box">
					<div class="layui-col-md12 layui-col-space1">
						<button class="layui-btn layui-btn-normal" id="upload"
							style="margin-top: 10px; display: none">
							<i class="layui-icon">&#xe681;</i>上传
						</button>
					</div>
				</div>
				<div class="layui-field-box">
					<div class="layui-col-md12 layui-col-space1">
						<table class="layui-table" id="bingli" lay-filter="bingli"
							style="margin-top: 3px"></table>
						<script type="text/html" id="barbingli">
  							<a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="yingxiang">影像</a>
							<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="xiangqing">详情</a>
  
						</script>
						<script id="time" type="text/html">
    							{{# var a = new Date(d.studyTime || new Date);
									var year = a.getFullYear();

									var month = a.getMonth() < 9 ? "0" + (0 | a.getMonth()+1) : a.getMonth()+1;
									var day = a.getDate() < 10 ? "0" + (0 | a.getDate()) : a.getDate();
        							return year + "-" + month+ "-" + day 
									
								}} 
						</script>
					</div>
				</div>
			</div>

		</div>
	</div>
	<div class="suspend" style="float: left;">
		<dl>
			<dt class="IE6PNG">
				<h1>
					<center style="padding-top: 105px;">
						分<br>享
				</h1>
				</center>
			</dt>
			<dd class="shoucangjia">
				<a href="javascript:;"> <img alt="" title="收藏夹"
					style="width: 50px; height: 50px;" src="img/yuanchengxin_76.png">
				</a>
			</dd>
			<dd class="readimage">
				<a href="javascript:;"> <img alt="" title="读片会"
					style="width: 50px; height: 50px;" src="img/yuanchengxin_67.png">
				</a>
			</dd>
			<dd class="shipinjiaoxue">
				<a href="javascript:;"> <img alt="" title="视频教学"
					style="width: 50px; height: 50px;" src="img/yuanchengxin_73.png"><br>
				</a>
			</dd>
			<dd class="geren">
                <a href="javascript:;"> <img alt="" title="个人"
                    style="width: 50px; height: 50px;" src="img/gerenfenxiang_72.png"><br>
                </a>
            </dd>
		</dl>
	</div>
	<script>
var medicalRecordKey;
layui.config({
    base : "js/"
}).use([ 'element', 'layer','form' ,'table', 'laydate','common' ], function() {
	var layer = layui.layer;
	var laydate = layui.laydate;
	var table = layui.table;
	var element = layui.element;
	var common = layui.common;
	var form = layui.form;
	var $ = layui.jquery;
	var group_id;
	laydate.render({
	    elem: '#studytime'
	    /* ,type: 'datetime' */
	    ,range: '~'
	  });
	$(function(){
		$.ajax({
	          url : 'bigant/getGroupInfoByUserId.do',
	          type : 'POST',
	          async: false,
	          success : function(data) {
	        	  data = eval("("+data+")");
	              if(data.code == 0){
	            	  var gudingqun = new Array();
	            	  var gerenqun = new Array();
	            	  var taolunzu = new Array();
	            	  if(data.groups!=''){
	            		  for(var i=0;i<data.groups.length;i++){
	            			  if(data.groups[i].group_type=="1"){
	            				  gudingqun.push(data.groups[i]);
	            			  }
	            			  if(data.groups[i].group_type=="0"){
	            				  gerenqun.push(data.groups[i]);
	            			  }
	            			  if(data.groups[i].group_type=="2"){
	            				  taolunzu.push(data.groups[i]);
	            			  }
	            		  }
	            	  }
	            	 
	            	  table.render({
	          			elem : '#gudingqun',
	          			cols : [[{field:'group_name', event: 'setSign', style:'cursor: pointer;', title: '固定群'}]],
	          			data : gudingqun
	          		  });
	            	  table.render({
	          			elem : '#gerenqun',
	          			cols : [[{field:'group_name',event: 'setSign', style:'cursor: pointer;',  title: '个人群'}]],
	          			data : gerenqun
	          		  });
	            	  table.render({
	          			elem : '#taolunzu',
	          			cols : [[{field:'group_name',event: 'setSign', style:'cursor: pointer;',  title: '讨论组'}]],
	          			data : taolunzu
	          		  });
	            	  
	            	  table.on('tool(gudingqun)', function(obj){
            		    $("#upload").show()
            		    var data = obj.data;
            		    var _this = $(this);
            		    $(".daibanTimefocus").removeClass("daibanTimefocus");
            		    _this.addClass("daibanTimefocus");
            		    if(obj.event === 'setSign'){
            		    	group_id = data.group_id;
            		    	
            		    	table.render({
	   						    elem: '#bingli'
	   						    ,url:'bigant/getRecordByGroupId.do'
	   					    	,method :'post'
	   					    	,where:{
	   					        	'group_id':group_id
	   							}
	   						    ,cols: [[
	   						    	{type:'numbers'}
	   						      ,{field:'patientName', width:150, title: '患者姓名',event: 'medicalRecordKey', sort: true}
	   						      ,{field:'sex', width:80, title: '性别', sort: true,event: 'medicalRecordKey', sort: true}
	   						      ,{field:'age', width:80, title: '年龄',event: 'medicalRecordKey', sort: true}
	   						      ,{field:'bodypartName', title: '检查部位', width: 100,event: 'medicalRecordKey', sort: true}
	   						      ,{field:'modalityCode', width:100, title: '图像类型', sort: true,event: 'medicalRecordKey', sort: true}
	   						      ,{field:'studyTime', width:200, title: '检查时间',  templet:'#time',event: 'medicalRecordKey', sort: true}
	   						      ,{fixed: 'right', width: 120, title: '操作', align:'center', toolbar: '#barbingli',event: 'medicalRecordKey'}
	   						    ]]
	   						    ,page: true
	   						    ,id:"binglitable"
   						  });
            		    }
            		  });
	            	  table.on('tool(gerenqun)', function(obj){
	            		$("#upload").show()
            		    var data = obj.data;
	            		var _this = $(this);
	            		$(".daibanTimefocus").removeClass("daibanTimefocus");
	            		_this.addClass("daibanTimefocus");
            		    if(obj.event === 'setSign'){
            		    	group_id = data.group_id;
            		    	table.render({
	   						    elem: '#bingli'
	   						    ,url:'bigant/getRecordByGroupId.do'
	   					    	,method :'post'
	   					    	,where:{
	   					        	'group_id':group_id
	   							}
	   						    ,cols: [[
	   						    	{type:'numbers'}
	   						      ,{field:'patientName', width:150, title: '患者姓名',event: 'medicalRecordKey', sort: true}
	   						      ,{field:'sex', width:80, title: '性别', sort: true,event: 'medicalRecordKey', sort: true}
	   						      ,{field:'age', width:80, title: '年龄',event: 'medicalRecordKey', sort: true}
	   						      ,{field:'bodypartName', title: '检查部位', width: 100,event: 'medicalRecordKey', sort: true}
	   						      ,{field:'modalityCode', width:100, title: '图像类型', sort: true,event: 'medicalRecordKey', sort: true}
	   						      ,{field:'studyTime', width:200, title: '检查时间',  templet:'#time',event: 'medicalRecordKey', sort: true}
	   						      ,{fixed: 'right', width: 120, title: '操作', align:'center', toolbar: '#barbingli',event: 'medicalRecordKey'}
	   						    ]]
	   						    ,page: true
	   						    ,id:"binglitable"
   						  });
            		    }
            		  });
	            	  table.on('tool(taolunzu)', function(obj){
            		    $("#upload").show()
            		    var data = obj.data;
            		    var _this = $(this);
            		    $(".daibanTimefocus").removeClass("daibanTimefocus");
            		    _this.addClass("daibanTimefocus");
            		    if(obj.event === 'setSign'){
            		    	group_id = data.group_id;
            		    	
            		    	table.render({
	   						    elem: '#bingli'
	   						    ,url:'bigant/getRecordByGroupId.do'
	   					    	,method :'post'
	   					    	,where:{
	   					        	'group_id':group_id,
	   					        	'username':$("#username").val(),
	   								'studytime':$("#studytime").val(),
	   								'modality':$("#modality").val(),
	   								'sex':$("input[name='sex']:checked").val()
	   							}
	   						    ,cols: [[
	   						    	{type:'numbers'}
	   						      ,{field:'patientName', width:150, title: '患者姓名',event: 'medicalRecordKey', sort: true}
	   						      ,{field:'sex', width:80, title: '性别', sort: true,event: 'medicalRecordKey', sort: true}
	   						      ,{field:'age', width:80, title: '年龄',event: 'medicalRecordKey', sort: true}
	   						      ,{field:'bodypartName', title: '检查部位', width: 100,event: 'medicalRecordKey', sort: true}
	   						      ,{field:'modalityCode', width:100, title: '图像类型', sort: true,event: 'medicalRecordKey', sort: true}
	   						      ,{field:'studyTime', width:200, title: '检查时间',  templet:'#time',event: 'medicalRecordKey', sort: true}
	   						      ,{fixed: 'right', width: 120, title: '操作', align:'center', toolbar: '#barbingli',event: 'medicalRecordKey'}
	   						    ]]
	   						    ,page: true
	   						    ,id:"binglitable"
   						  });
            		    }
            		  });
	              }else{
	                  common.cmsLayErrorMsg(data.msg);
	              }
	          }
	      });
	})
	
	table.on('tool(bingli)', function(obj){
		var data = obj.data;
 		if(obj.event === 'yingxiang'){
			//window.open("http://123.206.29.163:8080/?stuuid="+data.studyinstanceuid+"&user=cc&pswd=dd")
		window.open("http://47.92.165.202:1180/netFilm_Med/netFilmMed.do?patientID="+data.patientKey+"&studyUID="+data.studyinstanceuid+"&serverName=INTEALL&username=YWRtaW4=&password=YWRtaW4=")
 		}else if(obj.event === 'xiangqing'){
			window.open("medicalrecord/getqunxqById.do?stuuid="+data.studyinstanceuid)
		}else if(obj.event === 'medicalRecordKey'){
	    	medicalRecordKey=data.medicalRecordKey;
		}
	});
	
	$("#querymore").click(function() {
		if ($("#querymorediv").css("display") == "none") {
			$("#querymorediv").show("slow");
		} else {
			$("#querymorediv").hide("slow");
		}

	})
	
	$("#query").click(function(){
		 if(group_id == undefined ){
		 	common.cmsLayErrorMsg("请先选择群组，再查看病历！");
		 	return;
		 }
		 table.render({
			    elem: '#bingli'
			    ,url:'bigant/getRecordByGroupId.do'
		    	,method :'post'
		    	,where:{
		        	'group_id':group_id,
		        	'username':$("#username").val(),
					'studytime':$("#studytime").val(),
					'modality':$("#modality").val(),
					'sex':$("input[name='sex']:checked").val()
				}
			    ,cols: [[
			    	{type:'numbers'}
			      ,{field:'patientName', width:150, title: '患者姓名',event: 'medicalRecordKey'}
			      ,{field:'sex', width:80, title: '性别', sort: true,event: 'medicalRecordKey'}
			      ,{field:'age', width:80, title: '年龄',event: 'medicalRecordKey'}
			      ,{field:'bodypartName', title: '检查部位', width: 100,event: 'medicalRecordKey'}
			      ,{field:'modalityCode', width:100, title: '图像类型', sort: true,event: 'medicalRecordKey'}
			      ,{field:'studyTime', width:200, title: '检查时间',  templet:'#time',event: 'medicalRecordKey'}
			      ,{fixed: 'right', width: 200, title: '操作', align:'center', toolbar: '#barbingli',event: 'medicalRecordKey'}
			    ]]
			    ,page: true
			    ,id:"binglitable"
		  });

	});
	$("#reset").click(function(){
		$("#username").val("");
		$("#studytime").val("");
		$("#modality").val("");
		$('input:radio[name=sex]')[0].checked = false;
		$('input:radio[name=sex]')[1].checked = false;
		form.render('select'); //刷新select选择框渲染
		form.render('radio');
	});
	
	$("#upload").click(function(){
		if(group_id == undefined ){
		 	common.cmsLayErrorMsg("请先选择群组，再上传病历！");
		 	return;
		}
		var url = "bigant/redirectUpload.do?group_id="+group_id;
		common.cmsLayOpen('上传病历',url,'700px','450px');
  	})
  	  $(".shipinjiaoxue").click(function(){
			  if(medicalRecordKey== undefined ){
				  common.cmsLayErrorMsg("请先选择要分享的病例！");
				  return;
			  }
			  var url = "bigant/getByShareVideoId.do?medicalRecordKey="+medicalRecordKey;
		      common.cmsLayOpen('分享信息',url,'500px','400px');
		})
	 $(".readimage").click(function(){
			  if(medicalRecordKey== undefined ){
				  common.cmsLayErrorMsg("请先选择要分享的病例！");
				  return;
			  }
			  var url = "bigant/getByShareRreadimageId.do?medicalRecordKey="+medicalRecordKey;
		      common.cmsLayOpen('分享信息',url,'500px','400px');
		 })
	$(".shoucangjia").click(function(){
	  if(medicalRecordKey== undefined ){
		  common.cmsLayErrorMsg("请先选择要分享的病例！");
		  return;
	  }
	  var url = "bigant/getByShareCollectionId.do?medicalRecordKey="+medicalRecordKey;
      common.cmsLayOpen('分享信息',url,'500px','400px');
  })
  
  $(".geren").click(function(){
	  if(medicalRecordKey== undefined ){
          common.cmsLayErrorMsg("请先选择要分享的病例！");
          return;
      }
      var url = "persontoperson/getByShareGroupId.do?medicalRecordKey="+medicalRecordKey;
      common.cmsLayOpen('分享信息',url,'500px','400px');
  })
  $(document).ready(function(){
		 $(".suspend").mouseover(function() {
	     $(this).stop();
	     $(this).animate({width: 95}, 400);
	 })
	 $(".suspend").mouseout(function() {
	     $(this).stop();
	     $(this).animate({width: 40}, 400);
	 });
	});
	//检查类型
    $(function(){
    	$.ajax({
	          	url : 'consultation/getmodalityName.do',
	        	type : 'GET',
	        	dataType:"json",
	          	success : function(data) {
	          		var modalitylist = data.modalitylist;
	          		var html = '';
	          		for(var i = 0;i<modalitylist.length;i++){
	          		 	html += '<option value="'+modalitylist[i]+'">'+modalitylist[i]+'</option>'
	          		}
	          		$("#modality").append(html);
	          	    form.render('select'); 
	          	}
		})
    })
});

</script>
</body>
</html>

