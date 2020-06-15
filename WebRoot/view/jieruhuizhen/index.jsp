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
    <link rel="stylesheet" type="text/css" href="plugins/layui/css/layui.css" media="all" />
    <link rel="stylesheet" type="text/css" href="css/faqi_baogao.css" media="all">

    <script type="text/javascript" src="plugins/layui/layui.js"></script>
</head>
<body class="body_bg">
    <div class="layui-fluid">
        <div class="layui-row layui-col-md12 layui-col-space1" style="text-align: center;">
            <div class="layui-btn-group">
                <button class="layui-btn layui-btn-blackn layui-btn-xl" id="faqihuizhen" value="发起会诊">
                    发起会诊
                </button>
                <button class="layui-btn layui-btn-blackn layui-btn-xl" value="响应会诊" id="xiangyinghuizhen">
                    响应会诊
                </button>
            </div>
        </div>
        <div class="layui-col-md12 layui-input-bg">
            <div class="layui-field-box">
                <div class="layui-col-md12 layui-form layui-col-space1" style="margin-top: 10px">
                    <div style="margin-top: 0px;margin-bottom: 15px;margin-left: 10px;">
                        <div class="layui-input-inline">
                            <input type="text" name="username" id="username" placeholder="患者姓名"
                                class="layui-input">
                        </div>
                        <div class="layui-input-inline">
                            <div class="layui-input-inline">
                                <input type="text" readonly="readonly" class="layui-input" id="studytime"
                                    placeholder="检查时间">
                            </div>
                        </div>
                        <div class="layui-input-inline" style="text-align: center;">
                            <select id="modality" name="modality" >
                                 <option value="">检查类型</option>
                            </select>
                        </div>
                        <div class="layui-input-inline" style="text-align: center;">
                            <input type="radio" name="sex" value="M" title="男">
                            <input type="radio" name="sex" value="F" title="女">
                        </div>
                        <div class="layui-input-inline" style="width: 145px;">
                            <button class="layui-btn layui-btn-normal" id="query">查询</button>
                            <button class="layui-btn layui-btn-normal" id="reset">重置</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="layui-row layui-col-md12 layui-col-space1">
            <button class="layui-btn layui-btn-normal" id="upload" style="margin-top: 10px;">
                <i class="layui-icon">&#xe681;</i>上传
            </button>
        </div>
        <div class="layui-row layui-col-md12 layui-col-space1">
            <table class="layui-table" style="margin: 0;" id="bingli" lay-filter="bingli"></table>
            <table class="layui-table" style="margin: 0;" id="bingli1" lay-filter="bingli1"></table>
            <script type="text/html" id="barbingli">
                <a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="yingxiang">影像</a>
                <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="xiangqing">详情</a>
                {{# if(d.state === '1'){ }}
                    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="shanchu">删除</a>
                    <a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="faqihuizhen">发起会诊</a>
                {{# } }}
            </script>
              <script id="commitTime" type="text/html">
                {{# 
                    if(d.commitTime==null){
                        return "";
                    }
                    var a = new Date(d.commitTime);
                   
                    var year = a.getFullYear();

                    var month = a.getMonth() + 1 < 10 ? "0" + (a.getMonth() + 1) : a.getMonth() + 1;
                    var date = a.getDate() < 10 ? "0" + a.getDate() : a.getDate();
                    var hour = a.getHours()< 10 ? "0" + a.getHours() : a.getHours();
                    var minute = a.getMinutes()< 10 ? "0" + a.getMinutes() : a.getMinutes();
                    var second = a.getSeconds()< 10 ? "0" + a.getSeconds() : a.getSeconds();
                    return year + "-" + month + "-" + date+" "+hour+":"+minute+":"+second;
                                    
                    }} 
            </script>
             <script id="acceptTime" type="text/html">
                {{# 
                    if(d.acceptTime==null){
                        return "";
                    }
                    var a = new Date(d.acceptTime);
                   
                    var year = a.getFullYear();

                    var month = a.getMonth() + 1 < 10 ? "0" + (a.getMonth() + 1) : a.getMonth() + 1;
                    var date = a.getDate() < 10 ? "0" + a.getDate() : a.getDate();
                    var hour = a.getHours()< 10 ? "0" + a.getHours() : a.getHours();
                    var minute = a.getMinutes()< 10 ? "0" + a.getMinutes() : a.getMinutes();
                    var second = a.getSeconds()< 10 ? "0" + a.getSeconds() : a.getSeconds();
                    return year + "-" + month + "-" + date+" "+hour+":"+minute+":"+second;
                                    
                    }} 
            </script>
            <script id="studyTime" type="text/html">
                {{# var a = new Date(d.studyTime || new Date);
                    var year = a.getFullYear();

                    var month = a.getMonth() < 9 ? "0" + (0 | a.getMonth()+1) : a.getMonth()+1;
                    var day = a.getDate() < 10 ? "0" + (0 | a.getDate()) : a.getDate();
                    return year + "-" + month+ "-" + day 
                                    
                    }} 
            </script>
            <script id="state" type="text/html">
				{{# if(d.state === '1'){ }}
                    <span style="color: red;">否</span>
                {{#  } else if(d.state === '2'){ }}
                    <span style="color: #4BCD61;">是</span>
                {{#  } }} 
            </script>
        </div>

    </div>
    <script>
    var state;
    var interventionKey;
    layui.config({
        base : "js/"
    }).use([ 'element', 'layer','form', 'table', 'laydate','common' ], function() {
            var layer = layui.layer;
            var laydate = layui.laydate;
            var table = layui.table;
            var element = layui.element;
            var form = layui.form;
            var common = layui.common;
            var $ = layui.jquery;
            $(function(){
                $("#faqihuizhen").addClass("daibanTimefocus");
                initTable();
            })
            $("#faqihuizhen").click(function(data){
                var _this = $(this);
                $(".daibanTimefocus").removeClass("daibanTimefocus");
                _this.addClass("daibanTimefocus");
                initTable();
            })
            $("#xiangyinghuizhen").click(function(data){
                var _this = $(this);
                $(".daibanTimefocus").removeClass("daibanTimefocus");
                _this.addClass("daibanTimefocus");
                DoctorTable();
            })
            function initTable(){
                table.render({
                    elem: '#bingli'
                    ,url:'intervention/getRecordByCreateUser.do'
                    ,method :'post'
                    ,where:{
                        'username':$("#username").val(),
                        'studytime':$("#studytime").val(),
                        'modality':$("#modality").val(),
                        'sex':$('input:radio[name=sex]:checked').val(),
                    }
                    ,cols: [[
                        {type:'numbers'}
                      ,{field:'patientName', width:150, title: '患者姓名'}
                      ,{field:'sex', width:80, title: '性别', sort: true}
                      ,{field:'age', width:80, title: '年龄'}
                      ,{field:'bodypartName', title: '检查部位', width: 100}
                      ,{field:'modalityCode', width:100, title: '图像类型', sort: true}
                      ,{field:'studyTime', width:120, title: '检查时间',  templet:'#studyTime'}
                      ,{field:'state', title: '是否提交', width: 100, templet:'#state'}
                      ,{field:'acceptPersonName', title: '专家姓名', width: 120,templet:'#doctorName'}
                      ,{field:'commitTime', width:150, title: '会诊申请时间', sort: true, templet:'#commitTime'}
                      ,{field:'commitRequest', title: '病情描述', width: 250}
                      ,{fixed: 'right', width: 300, title: '操作', align:'center', toolbar: '#barbingli'}
                    ]]
                    ,page: true
                    ,id:"binglitable"
                });
                table.on('tool(bingli)', function(obj){
                    var data = obj.data;
                    if(obj.event === 'yingxiang'){
                      window.open("http://123.206.29.163:808/?stuuid="+data.studyinstanceuid+"&user=cc&pswd=dd")
                    }else if(obj.event === 'xiangqing'){
                      window.open("intervention/getByImageId.do?medicalRecordKey="+data.medicalRecordKey+"&interventionKey="+data.interventionKey+"&stuuid="+data.studyinstanceuid)
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
		    		          url : 'intervention/delByStuuid.do?stuuid='+data.medicalRecordKey+"&interventionKey="+data.interventionKey,
		    		          type : 'GET',
		    		          async: false,
		    		          success : function(data) {
		    		        	  data = eval("("+data+")");
		    		              if(data.code == 0){
		    		                  top.layer.close(index);
		    		                  common.cmsLaySucMsg("保存成功");
			    		          		//执行重载
			    		      			table.reload('binglitable', {
			    		      		        page: {
			    		      		          curr: 1 //重新从第 1 页开始
			    		      		        }
			    		      		        ,where:{
			    		      		        	'interventionKey':interventionKey
			    		      				},
			    		      		    });
		    		              }else{
		    		                  top.layer.close(index);
		    		                  common.cmsLayErrorMsg(data.msg);
		    		              }
		    		          },error:function(data){
		    		              top.layer.close(index);
		    		          }
		    		      })
					    })
                    }else  if(obj.event ==='faqihuizhen'){
                        if(data.state!=1){
                             common.cmsLayErrorMsg("该病历已经在会诊中");
                             return;
                        }
                        var url = "intervention/redirectGroup.do?interventionKey="+data.interventionKey;
                        common.cmsLayOpen('会诊申请',url,'700px','650px');
                    }
                });
            }
            function DoctorTable(){
                table.render({
                    elem: '#bingli'
                    ,url:'intervention/getRecordByDoctorUser.do'
                    ,method :'post'
                    ,where:{
                        'username':$("#username").val(),
                        'studytime':$("#studytime").val(),
                        'modality':$("#modality").val(),
                        'sex':$('input:radio[name=sex]:checked').val(),
                    }
                    ,cols: [[
                        {type:'numbers'}
                      ,{field:'patientName', width:150, title: '患者姓名'}
                      ,{field:'sex', width:80, title: '性别', sort: true}
                      ,{field:'age', width:80, title: '年龄'}
                      ,{field:'bodypartName', title: '检查部位', width: 100}
                      ,{field:'modalityCode', width:100, title: '图像类型', sort: true}
                      ,{field:'studyTime', width:120, title: '检查时间',  templet:'#studyTime'}
                      ,{field:'state', title: '是否提交', width: 100, templet:'#state'}
                      ,{field:'acceptPersonName', title: '专家姓名', width: 120 }
                      ,{field:'commitTime', width:150, title: '会诊申请时间', sort: true, templet:'#commitTime'}
                      ,{field:'commitRequest', title: '病情描述', width: 250}
                      ,{fixed: 'right', width: 300, title: '操作', align:'center', toolbar: '#barbingli'}
                    ]]
                    ,page: true
                    ,id:"binglitable"
                });
                table.on('tool(bingli)', function(obj){
                    var data = obj.data;
                    if(obj.event === 'yingxiang'){
                      window.open("http://123.206.29.163:808/?stuuid="+data.studyinstanceuid+"&user=cc&pswd=dd")
                    }else if(obj.event === 'xiangqing'){
                      window.open("intervention/getByDoctorImageId.do?medicalRecordKey="+data.medicalRecordKey+"&interventionKey="+data.interventionKey+"&stuuid="+data.studyinstanceuid)
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
		    		          url : 'intervention/delByStuuid.do?stuuid='+data.medicalRecordKey+"&interventionKey="+interventionKey,
		    		          type : 'GET',
		    		          async: false,
		    		          success : function(data) {
		    		        	  data = eval("("+data+")");
		    		              if(data.code == 0){
		    		                  top.layer.close(index);
		    		                  common.cmsLaySucMsg("保存成功");
			    		          		//执行重载
			    		      			table.reload('binglitable', {
			    		      		        page: {
			    		      		          curr: 1 //重新从第 1 页开始
			    		      		        }
			    		      		        ,where:{
			    		      		        	'readimagekey':readimagekey
			    		      				},
			    		      		    });
		    		              }else{
		    		                  top.layer.close(index);
		    		                  common.cmsLayErrorMsg(data.msg);
		    		              }
		    		          },error:function(data){
		    		              top.layer.close(index);
		    		          }
		    		      })
					    })
                    }else  if(obj.event ==='faqihuizhen'){
                        if(data.state!=1){
                             common.cmsLayErrorMsg("该病历已经在会诊中");
                             return;
                        }
                        var url = "intervention/redirectGroup.do?interventionKey="+data.interventionKey;
                        common.cmsLayOpen('选择专家',url,'700px','650px');
                    }
                });
            }
            laydate.render({
                elem : '#studytime'
                /* ,type: 'datetime' */
                ,
                range : "~"
            });
            $("#query").click(function(){
            	var _this = $(".daibanTimefocus");
                var id = _this.attr("id");
            	if(id=="faqihuizhen"){
	        		initTable();
            	}else{
            		DoctorTable();
            	}
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
          	  	var url = "intervention/interventionUpload.do";
                common.cmsLayOpen('上传病历',url,'700px','450px');
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
    </script>
</body>
</html>