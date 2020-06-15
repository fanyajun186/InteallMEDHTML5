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
	href="plugins/layui/css/layui.css" media="all" />
<link rel="stylesheet" type="text/css" href="css/faqi_baogao.css"
	media="all">
<link rel="stylesheet" type="text/css" href="css/quntaolun.css"
	media="all">
<link rel="stylesheet" type="text/css" href="css/scj.css">
<script type="text/javascript" src="plugins/layui/layui.js"></script>
<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="js/jquery.ztree.excheck.js"></script>
<script language="javascript"
	src="plugins/My97DatePicker/WdatePicker.js"></script>
<link rel="stylesheet" type="text/css" href="css/fenxiang.css">
<link rel="stylesheet" href="css/zTreeStyle/zTreeStyle.css"
	type="text/css" />
</head>
<body class="body_bg">
	<div class="layui-fluid">
		<div class="layui-col-md12 ">
			<div class="layui-col-md3 layui-scj-bg">
				<fieldset class="layui-elem-field">
					<legend>收藏夹管理</legend>
					<div class="layui-field-box">
						<div class="layui-row layui-col-space10">
							<div class="layui-col-md12">
								<div class="layui-row layui-col-space1">
									<div class="layui-col-md4">
										<button
											class="layui-btn layui-btn-normal layui-btn-sm addFavorite"
											style="margin: 0px">
											<i class="layui-icon">&#xe654;</i>新增
										</button>
									</div>
									<div class="layui-col-md4">
										<button
											class="layui-btn layui-btn-normal layui-btn-sm editFavorite"
											style="margin: 0px">
											<i class="layui-icon">&#xe642;</i>编辑
										</button>
									</div>
									<div class="layui-col-md4">
										<button
											class="layui-btn layui-btn-normal layui-btn-sm delFavorite"
											style="margin: 0px">
											<i class="layui-icon">&#xe640;</i>删除
										</button>
									</div>
								</div>
							</div>
							<div class="layui-col-md12">
								<ul id="treeDemo" class="ztree"></ul>
							</div>
						</div>

					</div>
				</fieldset>
			</div>
			<div class="layui-col-md9">
				<div class="layui-field-box">
					<div
						class="layui-col-md12 layui-form layui-input-bg layui-col-space10">
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
  							<a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="baogao">报告</a>
							<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="shanchu">删除</a>
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
	<!-- <div class="suspend" style="float: left;">
		<dl>
			<dt class="IE6PNG">
				<h1>
					<center style="padding-top: 105px;">
						分<br>享
				</h1>
				</center>
			</dt>
			<dd class="quntaolun">
				<a href="javascript:;"> <img alt="" title="群讨论"
					style="width: 50px; height: 50px;" src="img/yuanchengxin_70.png">
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
	</div> -->
</body>
<SCRIPT type="text/javascript">
var collectionKey=null;
var medicalRecordKey;
var table;
var common;
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
	
	$(document).ready(function() {
		$.ajax({
			url : "collection/getAll.do",
			type : "get",
			success : function(data) {
				var json = eval("(" + data + ")");
				$.fn.zTree.init($("#treeDemo"), setting, json.collectionList);
			}

		});
	});
layui.config({
	    base : "js/"
	}).use([ 'element', 'layer', 'table', 'laydate' , 'common','form'], function() {
		var layer = layui.layer;
		var laydate = layui.laydate;
		table = layui.table;
		var element = layui.element;
		common = layui.common;
		var form = layui.form;
		var $ = layui.jquery;
		table.on('tool(bingli)', function(obj) {
			var data = obj.data;
			console.log(data)
			if (obj.event === 'yingxiang') {
				layer.msg('ID：' + data.id + ' 的查看操作');
			} else if (obj.event === 'shenhe') {
				window.open("detail.html")
			}
		});
		laydate.render({
		    elem: '#studytime'
		    /* ,type: 'datetime' */
		    ,range: '~'
		  });
		$("#querymore").click(function() {
			if ($("#querymorediv").css("display") == "none") {
				$("#querymorediv").show("slow");
			} else {
				$("#querymorediv").hide("slow");
			}

		})
		$(".addFavorite").click(function(){
	            	 var url = "collection/insertParentCollectionAdd.do?parentkey="+collectionKey;
	 				 common.cmsLayOpen('新建收藏夹',url,'350px','230px');
			});
		$(".editFavorite").click(function(){
			if(collectionKey == undefined && collectionKey==null){
				  common.cmsLayErrorMsg("请先选择要编辑的收藏夹！");
				  return;
			  }
			  var url = "collection/getCollectionById.do?id="+collectionKey;
		      common.cmsLayOpen('编辑收藏夹信息',url,'350px','230px');
  		});
		$(".delFavorite").click(function(){
			layer.confirm('你确定删除文件夹?', { 
				btn: ['确定','取消'] //按钮 
				},function () {
					$.ajax({
			             url:"collection/getParentKeyCollection.do",
			             type:"post",
			             async:true,
			             cache:true,
			             data:{
			            	 collectionKey:collectionKey
			             },
			             success:function(data){
			            	  if(data.length>2){
			            		 common.cmsLayErrorMsg("请先删除子目录!");
			            	 }else{
			            		 $.ajax({
			        	             url:"collection/delCollection.do",
			        	             type:"post",
			        	             async:true,
			        	             cache:true,
			        	             data:{
			        	            	 collectionKey:collectionKey
			        	             },
			        	             success:function(data){
			        	            	 if(data != ""&&data != null){
			        	            		 common.cmsLaySucMsg("删除成功");
			        		                 window.location.reload(true);
			        	            	 }else{
			        	            		 common.cmsLaySucMsg("删除失败");
			        		                 window.location.reload(true);
			        	            	 }
			        	             }
			        	         });
			            	 } 
			             }
			         })
				})
		});
		$("#upload").click(function(){
			  if(collectionKey == undefined && collectionKey==null ){
				  common.cmsLayErrorMsg("请先选择收藏夹，再上传病历！");
				  return;
			  }
			  var url = "collection/collectionUpload.do?collectionKey="+collectionKey;
		      common.cmsLayOpen('上传病历',url,'700px','450px');
		 });
		$("#query").click(function(){
			 if(collectionKey == undefined&&collectionKey==null){
			 	common.cmsLayErrorMsg("请先选择收藏夹，再查看病历！");
			 	return;
			 }
			 table.render({
				    elem: '#bingli'
				    ,url:'collection/getCollectionKey.do'
			    	,method :'post'
			    	,where:{
			        	'collection_key':collectionKey,
			        	'username':$("#username").val(),
						'studytime':$("#studytime").val(),
						'modality':$("#modality").val(),
						'sex':$("input[name='sex']:checked").val()
					}
				    ,cols: [[
				    	{type:'numbers'}
				      ,{field:'patientName', width:150, title: '患者姓名',event: 'setSign'}
				      ,{field:'sex', width:80, title: '性别', sort: true,event: 'setSign'}
				      ,{field:'age', width:80, title: '年龄',event: 'setSign'}
				      ,{field:'bodypartName', title: '检查部位', width: 100,event: 'setSign'}
				      ,{field:'modalityCode', width:100, title: '图像类型', sort: true,event: 'setSign'}
				      ,{field:'studyTime', width:200, title: '检查时间',  templet:'#time',event: 'setSign'}
				      ,{fixed: 'right', width: 200, title: '操作', align:'center', toolbar: '#barbingli',event: 'setSign'}
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
		  $(".quntaolun").click(function(){
			  if(medicalRecordKey== undefined ){
				  common.cmsLayErrorMsg("请先选择要分享的病例！");
				  return;
			  }
			  var url = "collectionMedicalRecord/getByShareGroupId.do?medicalRecordKey="+medicalRecordKey;
		      common.cmsLayOpen('分享信息',url,'500px','400px');
		  })
		  $(".shipinjiaoxue").click(function(){
			  if(medicalRecordKey== undefined ){
				  common.cmsLayErrorMsg("请先选择要分享的病例！");
				  return;
			  }
			  var url = "collectionMedicalRecord/getByShareVideoId.do?medicalRecordKey="+medicalRecordKey;
		      common.cmsLayOpen('分享信息',url,'500px','400px');
		  })
		  $(".readimage").click(function(){
			  if(medicalRecordKey== undefined ){
				  common.cmsLayErrorMsg("请先选择要分享的病例！");
				  return;
			  }
			  var url = "collectionMedicalRecord/getByShareRreadimageId.do?medicalRecordKey="+medicalRecordKey;
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
function onClick(e, treeId, treeNode) {
    var zTree = $.fn.zTree.getZTreeObj("treeDemo");
    zTree.expandNode(treeNode);
    collectionKey= treeNode.collection_key;
    $("#upload").show();
    table.render({
                elem: '#bingli'
                ,url:'collection/getCollectionKey.do'
                ,method :'post'
                ,where:{
                    'collection_key':collectionKey
                }
                ,cols: [[
                   {type:'numbers'}
                  ,{field:'patientName', width:150, title: '患者姓名',event: 'setSign'}
                  ,{field:'sex', width:80, title: '性别', sort: true,event: 'setSign'}
                  ,{field:'age', width:80, title: '年龄',event: 'setSign'}
                  ,{field:'bodypartName', title: '检查部位', width: 100,event: 'setSign'}
                  ,{field:'modalityCode', width:100, title: '图像类型', sort: true,event: 'setSign'}
                  ,{field:'studyTime', width:200, title: '检查时间',  templet:'#time',event: 'setSign'}
                  ,{fixed: 'right', width: 200, title: '操作', align:'center', toolbar: '#barbingli',event: 'setSign'}
                ]]
                ,page: true
                ,id:"binglitable"
        });
    table.on('tool(bingli)', function(obj){
        var data = obj.data;
        if(obj.event === 'yingxiang'){
         // window.open("http://123.206.29.163:8080/?stuuid="+data.studyinstanceuid+"&user=cc&pswd=dd")
        window.open("http://47.92.165.202:1180/netFilm_Med/netFilmMed.do?patientID="+data.patientKey+"&studyUID="+data.studyinstanceuid+"&serverName=INTEALL&username=YWRtaW4=&password=YWRtaW4=")
        }else if(obj.event === 'xiangqing'){
          window.open("collection/getCollectionXqById.do?stuuid="+data.studyinstanceuid+"&collectionKey="+collectionKey)
        }else if(obj.event === 'baogao'){
            window.open("collection/collectionReportEdit.do?medicalRecordKey="+data.medicalRecordKey+"&stuuid="+data.studyinstanceuid+"&modalityCode="+data.modalityCode+"&collectionKey="+collectionKey+"&reportId="+data.report_key)
        }else if(obj.event ==='shanchu'){
            top.layer.confirm("是否删除选中数据", {
                title: "提示",
                resize: false,
                btn: ['确定', '取消'],
                btnAlign: 'c',
                anim:1,
                icon: 3
            },function(index){
                $.ajax({
                  url : 'collection/delByStuuid.do?stuuid='+data.medicalRecordKey+"&collectionKey="+collectionKey,
                  type : 'GET',
                  async: false,
                  success : function(data) {
                      data = eval("("+data+")");
                      if(data.code == 0){
                          top.layer.close(index);
                          common.cmsLaySucMsg("删除成功");
                            //执行重载
                            table.reload('binglitable', {
                                page: {
                                  curr: 1 //重新从第 1 页开始
                                }
                                ,where:{
                                    'collectionKey':collectionKey
                                },
                            });
                      }else{
                          top.layer.close(index);
                          common.cmsLayErrorMsg(data.msg);
                      }
                  },error:function(data){
                      top.layer.close(index);
                  }
              });
            }); 
        }else if(obj.event === 'setSign'){
            medicalRecordKey=data.medicalRecordKey;
        }
      });
}
</SCRIPT>
</html>
