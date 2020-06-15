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
<link rel="stylesheet" type="text/css" href="plugins/layui/css/layui.css" media="all" />
<link rel="stylesheet" type="text/css" href="css/faqi_baogao.css" media="all">
<link rel="stylesheet" type="text/css" href="css/quntaolun.css" media="all">
<script type="text/javascript" src="plugins/layui/layui.js"></script>
</head>
<body class="body_bg">
	<div class="layui-col-md12">

		<div class="layui-field-box ">
			<div class="layui-col-md12 layui-tab layui-tab-card" >
				<ul class="layui-tab-title" >
					<li class="layui-this">医院管理</li>
					<li>用户管理</li>
					<li>角色管理</li>
					<li>视频预约管理</li>
					<li>短信日志</li>
				</ul>
				<div class="layui-tab-content" style="padding: 0px">
					<div class="layui-tab-item layui-show">
						<div class="layui-row layui-col-md12 layui-tab-input-bg">
							<div class="layui-form-item">
								<div class="layui-col-md6 layui-input-inline">
									<input type="text" id="hospitalname" name="hospitalname" placeholder="医院名称"
										class="layui-input">
								</div>
								<div class="layui-col-md6">
									<button class="layui-btn layui-btn-normal" id="hospitalquery">查询</button>
									<button class="layui-btn layui-btn-normal" id="hospitalreset">重置</button>
									<button class="layui-btn layui-btn-normal" id="hospitaladd">新增</button>
								</div>
							</div>
						</div>
						<div class="layui-row layui-col-md12 layui-col-space1">
							<table class="layui-table" id="hospital" lay-filter="hospital">
							</table>
							<script type="text/html" id="hospitalbar">
  								<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit">编辑</a>
								<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="del">删除</a>
  
							</script>
						</div>
					</div>
					<div class="layui-tab-item">
						<div class="layui-row layui-col-md12 layui-tab-input-bg">
							<div class="layui-form-item">
								<div class="layui-col-md12 " >
									<div class="layui-input-inline">
										<input type="text" name="login_name" id="login_name" placeholder="登录名"
											class="layui-input">
									</div>
									<div class="layui-input-inline">
										<input type="text" name="user_name" id="user_name" placeholder="用户姓名"
											class="layui-input">
									</div>
									
									<div class="layui-input-inline" style="width: 220px">
										<button class="layui-btn layui-btn-normal" id="sysuserquery">查询</button>
										<button class="layui-btn layui-btn-normal" id="sysuserreset">重置</button>
										<button class="layui-btn layui-btn-normal" id="sysuseradd">新增</button>
									</div>
								</div>
							</div>
						</div>
						<div class="layui-row layui-col-md12 layui-col-space1">
							<table class="layui-table" id="sysuser" lay-filter="sysuser">
							</table>
							<script type="text/html" id="sexTpl">
								<input type="checkbox" name="state" lay-skin="switch" disabled lay-text="男|女" {{ d.userSex == 0 ? 'checked' : '' }}>
							</script>
							<script type="text/html" id="sysuserbar">
  								<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit">编辑</a>
								<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="del">删除</a>
  
							</script>
						</div>
					</div>
					<div class="layui-tab-item">
						<div class="layui-row layui-col-md12 layui-tab-input-bg">
							<div class="layui-form-item">
								<div class="layui-col-md12 " >
									<div class="layui-input-inline">
										<input type="text" name="role_name" id="role_name" placeholder="角色名"
											class="layui-input">
									</div>
									<div class="layui-input-inline" style="width: 220px">
										<button class="layui-btn layui-btn-normal" id="sysrolequery">查询</button>
										<button class="layui-btn layui-btn-normal" id="sysrolereset">重置</button>
										<button class="layui-btn layui-btn-normal" id="sysroleadd">新增</button>
									</div>
								</div>
							</div>
						</div>
						<div class="layui-row layui-col-md12 layui-col-space1">
							<table class="layui-table" id="sysrole" lay-filter="sysrole">
							</table>
							<script type="text/html" id="sysrolebar">
  								<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit">编辑</a>
								<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="del">删除</a>
  
							</script>
							<script id="Time" type="text/html">
    							{{# var a = new Date(d.createTime || new Date);
									var year = a.getFullYear();

									var month = a.getMonth() < 9 ? "0" + (0 | a.getMonth()+1) : a.getMonth()+1;
									var day = a.getDate() < 10 ? "0" + (0 | a.getDate()) : a.getDate();
									var hour = a.getHours() < 10 ? "0" + (0 | a.getHours()) : a.getHours();
									var min = a.getMinutes() < 10 ? "0" + (0 | a.getMinutes()) : a.getMinutes();
									var sec = a.getSeconds() < 10 ? "0" + (0 | a.getSeconds()) : a.getSeconds();
        							return year + "-" + month+ "-" + day + " " + hour + ":" + min + ":" + sec
									
								}} 
							</script>
						</div>
					</div>
					<div class="layui-tab-item">
						<div class="layui-row layui-col-md12 layui-tab-input-bg">
							<div class="layui-form-item">
								<div class="layui-col-md12 " >
									<div class="layui-input-inline">
										<input type="text" readonly="readonly" class="layui-input" id="creatTime"
                                    placeholder="创建时间">
									</div>
									<div class="layui-input-inline" style="width: 220px">
										<button class="layui-btn layui-btn-normal" id="zoomCreatTimequery">查询</button>
									</div>
								</div>
							</div>
						</div>
						<div class="layui-row layui-col-md12 layui-col-space1">
							<table class="layui-table" id="zoom" lay-filter="zoom">
							</table>
							<script type="text/html"  id="createPerson">
							{{# if(d.sysUser.userName!=null && d.sysUser.userName!=undefined){ }}
								{{d.sysUser.userName}}
							{{# }}}
							</script>
							<script type="text/html" id="zoombar">
								<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="del">删除</a>
							</script>
						</div>
					</div>
					<!-- 短信日志 -->
					<div class="layui-tab-item">
						<div class="layui-row layui-col-md12 layui-tab-input-bg">
							<div class="layui-form-item">
								<div class="layui-col-md12 " >
									<div class="layui-input-inline">
										<input type="text" name="name_phone" id="name_phone" placeholder="姓名或手机号" class="layui-input">
									</div>
									<div class="layui-input-inline">	
										<input type="text" class="layui-input" readonly="readonly" id="infoLogtime" placeholder="开始 到 结束">
									</div>
									
									<div class="layui-input-inline" style="width: 220px">
										<button class="layui-btn layui-btn-normal" id="messagesearch">查询</button>
										<button class="layui-btn layui-btn-normal" id="messagereset">重置</button>
									</div>
								</div>
							</div>
						</div>
						<div class="layui-row layui-col-md12 layui-col-space1">
							<table class="layui-table" id="infoLog" lay-filter="infoLog">
							</table>
							<script type="text/html" id="messageedit">
								<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="del">删除</a>
							</script>
						</div>
					</div>
					
				</div>
			</div>
		</div>
	</div>
</body>
<script>
layui.config({
    base : "js/"
}).use([ 'element', 'layer', 'table', 'laydate','common' ], function() {
		var table = layui.table;
		var layer = layui.layer;
		var laydate = layui.laydate;
		var $ = layui.jquery;
		var common = layui.common;
		/*
		*初始化医院列表
		*/
		table.render({
			elem : '#hospital',
			method : 'post',
			url : 'syshospital/getAll.do',
			where:{
				'hospitalname':$("#hospitalname").val()
			},
			cols : [[
				{type:'numbers'}
				,{field:'hospName', width:150, title: '医院名称', sort: true}
				,{field:'hospUser', title: '联系人', width: 80}
		      	,{field:'hospTel', width:150, title: '联系电话', sort: true}
		      	,{field:'hospMobile', width:150, title: '手机号码', sort: true}
		      	,{field:'hospPostCode', width:100, title: '邮政编码', sort: true}
		      	,{field:'hospEmail', width:150, title: '邮箱地址', sort: true}
		      	,{field:'hospAbout', width:150, title: '医院介绍'}
			  	,{fixed: 'right', width: 165, title: '操作', align:'center', toolbar: '#hospitalbar'}
			  ]],
			id:"hospitaltable",
			page : true
		});
		/*
		*监听医院列表中的操作按钮
		*/
		table.on('tool(hospital)', function(obj) {
			var data = obj.data;
			if (obj.event === 'edit') {
				var url = "syshospital/getById.do?id="+data.sysHospitalKey;
	            common.cmsLayOpen('编辑医院信息',url,'700px','620px');
			} else if (obj.event === 'del') {
				top.layer.confirm("是否删除选中数据", {
			        title: "提示",
			        resize: false,
			        btn: ['确定', '取消'],
			        btnAlign: 'c',
			        anim:1,
			        icon: 3
			    },function(index){
	    			$.ajax({
    		          url : 'syshospital/delById.do?id='+data.sysHospitalKey,
    		          type : 'get',
    		          async: false,
    		          success : function(data) {
    		        	  data = eval("("+data+")");
    		              if(data.code == 0){
    		                  top.layer.close(index);
    		                  common.cmsLaySucMsg("保存成功");
    		                  table.reload('hospitaltable', {
    		      		        page: {
    		      		          curr: 1 //重新从第 1 页开始
    		      		        }
    		      		        ,where: {
    		      	        		'hospitalname':$("#hospitalname").val()
    		      		        }
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
			}
		});
		$("#hospitalquery").click(function(){
			//执行重载
		    table.reload('hospitaltable', {
		        page: {
		          curr: 1 //重新从第 1 页开始
		        }
		        ,where: {
	        		'hospitalname':$("#hospitalname").val()
		        }
		    });
			
		});
		$("#hospitalreset").click(function(){
			$("#hospitalname").val("");
		});
		$("#hospitaladd").click(function(){
			var url = "syshospital/redirectAdd.do";
            common.cmsLayOpen('新增医院信息',url,'700px','620px');
		});
		
		
		/*
		*初始化用户列表
		*/
		table.render({
			elem : '#sysuser',
			url : 'sysuser/getAll.do',
			method : 'post',
			where:{
				'user_name':$("#user_name").val(),
				'login_name':$("#login_name").val()
			},
			cols : [[
				{type:'numbers'}
		      ,{field:'userName', width:80, title: '用户名'}
		      ,{field:'loginName', width:80, title: '登录名', sort: true}
		      ,{field:'userSex', width:80, title: '性别', templet:'#sexTpl'}
		      ,{field:'userAge', width:80, title: '年龄'}
		      ,{field:'userEmail', width:150, title: '邮箱'}
		      ,{field:'userPhone', width:150, title: '手机号码'}
			  ,{fixed: 'right', width: 165, title: '操作', align:'center', toolbar: '#sysuserbar'}
			  ]],
		  	id:"sysusertable",
			page : true
			
		});
		/*
		*监听用户列表中的操作按钮
		*/
		table.on('tool(sysuser)', function(obj) {
			var data = obj.data;
			if (obj.event === 'edit') {
				var url = "sysuser/getById.do?id="+data.sysuserKey;
	            common.cmsLayOpen('编辑用户信息',url,'700px','620px');
			} else if (obj.event === 'del') {
				top.layer.confirm("是否删除选中数据", {
			        title: "提示",
			        resize: false,
			        btn: ['确定', '取消'],
			        btnAlign: 'c',
			        anim:1,
			        icon: 3
			    },function(index){
	    			$.ajax({
    		          url : 'sysuser/delById.do?id='+data.sysuserKey,
    		          type : 'get',
    		          async: false,
    		          success : function(data) {
    		        	  data = eval("("+data+")");
    		              if(data.code == 0){
    		                  top.layer.close(index);
    		                  common.cmsLaySucMsg("保存成功");
	    		          		//执行重载
	    		      			table.reload('sysusertable', {
	    		      		        page: {
	    		      		          curr: 1 //重新从第 1 页开始
	    		      		        }
	    		      		        ,where:{
	    		      					'user_name':$("#user_name").val(),
	    		      					'login_name':$("#login_name").val()
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
			}
		});
		$("#sysuserquery").click(function(){
			//执行重载
		    table.reload('sysusertable', {
		        page: {
		          curr: 1 //重新从第 1 页开始
		        }
		        ,where:{
					'user_name':$("#user_name").val(),
					'login_name':$("#login_name").val()
				},
		    });
			
		});
		$("#sysuserreset").click(function(){
			$("#user_name").val("");
			$("#login_name").val("");
		});
		$("#sysuseradd").click(function(){
			var url = "sysuser/redirectAdd.do";
            common.cmsLayOpen('新增用户信息',url,'700px','500px');
		});
		
		
		
		/*
		*初始化角色列表
		*/
		table.render({
			elem : '#sysrole',
			url : 'sysrole/getAll.do',
			method : 'post',
			where:{
				'role_name':$("#role_name").val(),
			},
			cols : [[
				{type:'numbers'}
		      ,{field:'roleName', width:150, title: '角色名'}
		      ,{field:'createPerson', width:80, title: '创建人'}
		      ,{field:'createTime', width:200, title: '创建时间',templet:'#Time'}
			  ,{fixed: 'right', width: 165, title: '操作', align:'center', toolbar: '#sysrolebar'}
			  ]],
		  	id:"sysroletable",
			page : true
			
		});
		/*
		*监听用户列表中的操作按钮
		*/
		table.on('tool(sysrole)', function(obj) {
			var data = obj.data;
			if (obj.event === 'edit') {
				var url = "sysrole/getById.do?id="+data.sysRoleKey;
	            common.cmsLayOpen('编辑角色信息',url,'700px','620px');
			} else if (obj.event === 'del') {
				top.layer.confirm("是否删除选中数据", {
			        title: "提示",
			        resize: false,
			        btn: ['确定', '取消'],
			        btnAlign: 'c',
			        anim:1,
			        icon: 3
			    },function(index){
	    			$.ajax({
    		          url : 'sysrole/delById.do?id='+data.sysRoleKey,
    		          type : 'get',
    		          async: false,
    		          success : function(data) {
    		        	  data = eval("("+data+")");
    		              if(data.code == 0){
    		                  top.layer.close(index);
    		                  common.cmsLaySucMsg("保存成功");
	    		          		//执行重载
	    		      			table.reload('sysroletable', {
	    		      		        page: {
	    		      		          curr: 1 //重新从第 1 页开始
	    		      		        }
	    		      		        ,where:{
	    		      		        	'role_name':$("#role_name").val()
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
			}
		});
		$("#sysrolequery").click(function(){
			//执行重载
		    table.reload('sysroletable', {
		        page: {
		          curr: 1 //重新从第 1 页开始
		        }
		        ,where:{
		        	'role_name':$("#role_name").val()
				},
		    });
			
		});
		$("#sysrolereset").click(function(){
			$("#role_name").val("")
		});
		$("#sysroleadd").click(function(){
			var url = "sysrole/redirectAdd.do";
            common.cmsLayOpen('新增角色信息',url,'700px','500px');
		});
		
		/*
		*初始化视频预约列表
		*/
		table.render({
			elem : '#zoom',
			method : 'post',
			url : 'zoommanage/getZoomAll.do',
			where:{
				'creatTime':$("#creatTime").val(),
			},
			cols : [[
				{type:'numbers'}
				,{field:'sysUser', width:150, title: '创建人',templet:'#createPerson'}
				,{field:'createTimeStr',width:200, title: '创建时间'}
				,{field:'meetingCapacity',width:100, title: '会议参与人数'}
				,{field:'minute',width:120, title: '会议时间长/分'}
				,{field:'startTimeStr',width:200, title: '会议开始时间'}
				,{field:'topic',width:100, title: '会议类型'}
			  	,{fixed: 'right', width: 165, title: '操作', align:'center', toolbar: '#zoombar'}
			  ]],
			id:"zoomtable",
			page : true
		});
		/*
		*视频预约列表中的操作按钮
		*/
		table.on('tool(zoom)', function(obj) {
			var data = obj.data;
			console.log(data);
			 if (obj.event === 'del') {
				top.layer.confirm("是否删除选中数据", {
			        title: "提示",
			        resize: false,
			        btn: ['确定', '取消'],
			        btnAlign: 'c',
			        anim:1,
			        icon: 3
			    },function(index){
	    			$.ajax({
    		          url : 'zoommanage/delZoomById.do?id='+data.zoomKey,
    		          type : 'get',
    		          async: false,
    		          success : function(data) {
    		        	  data = eval("("+data+")");
    		              if(data.code == 0){
    		                  top.layer.close(index);
    		                  common.cmsLaySucMsg("保存成功");
    		                  table.reload('zoomtable', {
    		      		        page: {
    		      		          curr: 1 //重新从第 1 页开始
    		      		        },where:{
    		      					'creatTime':$("#creatTime").val(),
    		      				}
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
			}
		});
		 laydate.render({
             elem : '#creatTime'
             /* ,type: 'datetime' */
             ,
             range : "~"
         });
		 $("#zoomCreatTimequery").click(function(){
				//执行重载
			    table.reload('zoomtable', {
			        page: {
			          curr: 1 //重新从第 1 页开始
			        }
			        ,where: {
			        	'creatTime':$("#creatTime").val()
			        }
			    });
				
			});
		
		
		/* 初始化短信日志列表 */
		laydate.render({
		    elem: '#infoLogtime'
		    /* ,type: 'datetime' */
		    ,range: '~'
		  });
		table.render({
			elem : '#infoLog',
			url : 'syshospital/messageLogList.do',
			method : 'post',
			where:{
				'name_phone':$("#name_phone").val(),
				'infoLogtime':$("#infoLogtime").val()
			},
			cols : [[
				{type:'numbers'}
		      ,{field:'recipient', width:80, title: '接收人'}
			  ,{field:'realName', width:90, title: '真实姓名'}
		      ,{field:'receivePhone', width:120, title: '手机号', sort: true}
		      ,{field:'message', width:450, title: '信息内容',templet:'<div><span title="{{d.message}}">{{d.message}}</span>/div>'}
		      ,{field:'sendType', width:100, title: '信息类型'}
		      ,{field:'stime', width:200, title: '发送时间'}
		      ,{field:'sendState', width:150, title: '发送状态'}
			  ,{fixed: 'right', width: 165, title: '操作', align:'center', toolbar: '#messageedit'}
			  ]],
		  	id:"messagetable",
			page : true
			
		});
		
		$("#messagesearch").click(function(){
			//执行重载
		    table.reload('messagetable', {
		        page: {
		          curr: 1 //重新从第 1 页开始
		        }
		        ,where:{
		        	'name_phone':$("#name_phone").val(),
					'infoLogtime':$("#infoLogtime").val()
				},
		    });
			
		});
		$("#messagereset").click(function(){
			$("#name_phone").val("");
			$("#infoLogtime").val("");
			 table.reload('messagetable', {
			        page: {
			          curr: 1 //重新从第 1 页开始
			        }
			        ,where:{
			        	'name_phone':"",
						'infoLogtime':""
					},
			    });
		});
		
		table.on('tool(infoLog)', function(obj) {
			var data = obj.data;
			if (obj.event === 'del') {
				top.layer.confirm("是否删除选中数据", {
			        title: "提示",
			        resize: false,
			        btn: ['确定', '取消'],
			        btnAlign: 'c',
			        anim:1,
			        icon: 3
			    },function(index){
	    			$.ajax({
    		          type : 'POST',
    		          url : 'syshospital/delMessageInfo.do?infoId='+data.Id,
    		          dataType:'json',
    		          success : function(data) {
    		        	  if(data.result){
    		        		  top.layer.close(index);
    		        		  layer.msg(data.msg,{icon:1}); 
    		        		  table.reload('messagetable', {
	    		      		        page: {
	    		      		          curr: 1 //重新从第 1 页开始
	    		      		        }
	    		      		        ,where:{
	    		      		        	'name_phone':$("#name_phone").val(),
	    		    					'infoLogtime':$("#infoLogtime").val()
	    		      				},
	    		      		    });
    		        	  }else{
    		        		  top.layer.close(index);
    		        		  layer.msg(data.msg,{icon:2}); 
    		        	  }
    		          },error:function(data){
    		              top.layer.close(index);
    		          }
    		      });
	    		});
			}
		});
		
	});
</script>

</html>

