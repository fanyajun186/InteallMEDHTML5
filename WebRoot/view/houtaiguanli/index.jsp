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
<link rel="stylesheet" type="text/css"
	href="plugins/layui/css/layui.css" media="all" />
<link rel="stylesheet" type="text/css" href="css/faqi_baogao.css"
	media="all">
<link rel="stylesheet" type="text/css" href="css/quntaolun.css"
	media="all">
<script type="text/javascript" src="plugins/layui/layui.js"></script>
</head>
<body class="body_bg">
	<div class="layui-col-md12">

		<div class="layui-field-box ">
			<div class="layui-col-md12 layui-tab layui-tab-card" >
				<ul class="layui-tab-title" >
					<li class="layui-this">知识库管理</li>
					<li>分类管理</li>
					<li>评论管理</li>
					<li>委员介绍管理</li>
					<li>公告管理</li>
				</ul>
				<div class="layui-tab-content" style="padding: 0px">
					<div class="layui-tab-item layui-show">
						<div class="layui-row layui-col-md12 layui-tab-input-bg">
							<div class="layui-form-item">
								<div class="layui-col-md6 layui-input-inline">
									<input type="text" id="zhishikuManagename" name="zhishikuManagename" placeholder="标题"
										class="layui-input">
								</div>
								<div class="layui-col-md6">
									<button class="layui-btn layui-btn-normal" id="zhishikuManagequery">查询</button>
									<button class="layui-btn layui-btn-normal" id="zhishikuManageadd">新增</button>
								</div>
							</div>
						</div>
						<div class="layui-row layui-col-md12 layui-col-space1">
							<table class="layui-table" id="zhishikuManage" lay-filter="zhishikuManage">
							</table>
							<script type="text/html" id="zhishikuManagebar">
  								<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit">编辑</a>
								<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="del">删除</a>
							</script>
							<script type="text/html"  id="typeName">
							{{# if(d.zhishikuType.typeName!=null && d.zhishikuType.typeName!=undefined){ }}
								{{d.zhishikuType.typeName}}
							{{# }}}
							</script>
							<script type="text/html"  id="title">
							{{# if(d.title!=null && d.title!=undefined){ }}
								<a target="_blank" style="color:#1E9FFF" href="${pageContext.request.contextPath}/zhishiku/articles/{{d.id}}.do">{{d.title}}</a>
							{{# }}}
							</script>
						</div>
					</div>
					<div class="layui-tab-item">
						<div class="layui-row layui-col-space10">
						    <div class="layui-col-md6" >
							    <div class="layui-form-item">
	                                <div class="layui-col-md12 " >
	                                    <div class="layui-input-inline">
	                                        <input type="text" name="parentTypeName" id="parentTypeName" placeholder="父类名称"
	                                            class="layui-input">
	                                    </div>
	                                    
	                                    <div class="layui-input-inline" style="width: 220px">
	                                        <button class="layui-btn layui-btn-normal" id="parentTypequery">查询</button>
	                                        <button class="layui-btn layui-btn-normal" id="parentTypereset">重置</button>
	                                        <button class="layui-btn layui-btn-normal" id="parentTypeadd">新增</button>
	                                    </div>
	                                </div>
	                            </div>
								<table class="layui-table" id="parenttype" lay-filter="parenttype">
								</table>
								<script type="text/html" id="zhishikuTypebar">
                                    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit">编辑</a>
                                    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="del">删除</a>
                                </script>
					       </div>
					       <div class="layui-col-md6">
                                <div class="layui-form-item">
                                    <div class="layui-col-md12 " >
                                        <div class="layui-input-inline">
                                            <input type="text" name="childTypeName" id="childTypeName" placeholder="子类名称"
                                                class="layui-input">
                                        </div>
                                        
                                        <div class="layui-input-inline" style="width: 220px">
                                            <button class="layui-btn layui-btn-normal" id="childTypequery">查询</button>
                                            <button class="layui-btn layui-btn-normal" id="childTypereset">重置</button>
                                            <button class="layui-btn layui-btn-normal" id="childTypeadd">新增</button>
                                        </div>
                                    </div>
                                </div>
                                <table class="layui-table" id="childtype" lay-filter="childtype">
                                </table>
                                <script type="text/html" id="zhishikuChildTypebar">
                                    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit">编辑</a>
                                    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="del">删除</a>
                                </script>
                            </div>
                        </div>
                    </div>
					<div class="layui-tab-item">
						<div class="layui-row layui-col-md12 layui-tab-input-bg">
							<div class="layui-form-item">
								<div class="layui-col-md12 " >
									<div class="layui-input-inline">
										<input type="text" name="zhishikuManagecontentpl" id="zhishikuManagecontentpl" placeholder="评论内容"
											class="layui-input">
									</div>
									<div class="layui-input-inline" style="width: 220px">
										<button class="layui-btn layui-btn-normal" id="zhishikuManageplquery">查询</button>
									</div>
								</div>
							</div>
						    
                        
						</div>
						<div class="layui-row layui-col-md12 layui-col-space1">
							<table class="layui-table" id="zhishikuManagepl" lay-filter="zhishikuManagepl">
							</table>
							<script type="text/html"  id="zhishikuId">
							{{# if(d.zhishiku!=null && d.zhishiku!=undefined){ }}
							<a target="_blank" style="color:#1E9FFF" href="${pageContext.request.contextPath}/zhishiku/articles/{{d.zhishiku.id}}.do">{{d.zhishiku.title}}</a>
							{{#  } else{ }}
								<font color='red'>该知识库已被删除！</font>
							{{# }}}
							</script>
							<script type="text/html" id="zhishikuManageplbar">
								<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="del">删除</a>
							</script>
						</div>
					</div>
					<div class="layui-tab-item">
                        <div class="layui-row layui-col-md12 layui-tab-input-bg">
                            <div class="layui-form-item">
                                <div class="layui-col-md6 layui-input-inline">
                                    <input type="text" id="committeeName" name="committeeName" placeholder="姓名"
                                        class="layui-input">
                                </div>
                                <div class="layui-col-md6">
                                    <button class="layui-btn layui-btn-normal" id="committeequery">查询</button>
                                    <button class="layui-btn layui-btn-normal" id="committeereset">重置</button>
                                    <button class="layui-btn layui-btn-normal" id="committeeadd">新增</button>
                                </div>
                            </div>
                        </div>
                        <div class="layui-row layui-col-md12 layui-col-space1">
                            <table class="layui-table" id="committee" lay-filter="committee">
                            </table>
                            <script type="text/html" id="committeebar">
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
										<input type="text" name="noticeName" id="noticeName" placeholder="公告名称"
											class="layui-input">
									</div>
									<div class="layui-input-inline" style="width: 220px">
										<button class="layui-btn layui-btn-normal" id="noticeNamequery">查询</button>
										<button class="layui-btn layui-btn-normal" id="noticeadd">新增</button>
									</div>
								</div>
							</div>
						</div>
						<div class="layui-row layui-col-md12 layui-col-space1">
							<table class="layui-table" id="notice" lay-filter="notice">
							</table>
							<script type="text/html" id="noticebar">
								<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit">编辑</a>
								<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="del">删除</a>
							</script>
						</div>
					</div>
				</div>
			</div>

		</div>

	</div>
<script>
layui.config({
    base : "js/"
}).use(['laydate','layer','table','element','common'], function(){
	  var laydate = layui.laydate;
	  var layer = layui.layer;
	  var table = layui.table;
	  var common = layui.common;
	  var $ = layui.jquery;
  	  var element = layui.element; 
  	  var common = layui.common;
  	  var parentId;
  	/*
		*初始化知识库管理列表
		*/
		table.render({
			elem : '#zhishikuManage',
			method : 'post',
			url : 'zhishikuManage/getAll.do',
			where:{
				'zhishikuManagename':$("#zhishikuManagename").val()
			},
			cols : [[
				{type:'numbers'}
				,{field:'title', width:550, title: '标题', sort: true,templet:'#title'}
				,{field:'releaseDateStr',width:250, title: '发布日期'}
				,{field:'releasePersonName',width:100, title: '发布人姓名'}
		      	,{field:'zhishikuType', width:150, title: '类别', sort: true,templet:'#typeName'}
			  	,{fixed: 'right', width: 165, title: '操作', align:'center', toolbar: '#zhishikuManagebar'}
			  ]],
			id:"zhishikuManagetable",
			page : true
		});
  	    /**
  	    *初始化 父节点
  	    */
		table.render({
            elem : '#parenttype',
            method : 'post',
            url : 'zhishikuType/getParentByName.do',
            where:{
                'name':$("#parentTypeName").val()
            },
            cols : [[
                {type:'numbers'}
                ,{field:'typeName', width:200, title: '父类名称',event: 'parentId', sort: true}
                ,{fixed: 'right', width: 200, title: '操作', event: 'parentId',align:'center', toolbar: '#zhishikuTypebar'}
              ]],
            id:"zhishikuTypetable",
            page : true
        });
		table.on('tool(parenttype)', function(obj){
	        var data = obj.data;
	        parentId=data.id 
	        if(obj.event === 'edit'){
	        	var url = "zhishikuType/getById.do?isparent=1&id="+data.id;
                common.cmsLayOpen('编辑信息',url,'400px','220px');
	        }else if(obj.event === 'del'){
	        	top.layer.confirm("您要删除的是父类，对应的子类一会被删除，是否删除？", {
                    title: "提示",
                    resize: false,
                    btn: ['确定', '取消'],
                    btnAlign: 'c',
                    anim:1,
                    icon: 3
                },function(index){
                    $.ajax({
                      url : 'zhishikuType/delete.do?id='+data.id,
                      type : 'get',
                      async: false,
                      success : function(data) {
                          data = eval("("+data+")");
                          if(data.code == 0){
                              top.layer.close(index);
                              common.cmsLaySucMsg("保存成功");
                              table.reload('zhishikuTypetable', {
                                page: {
                                  curr: 1 //重新从第 1 页开始
                                }
                                ,where: {
                                    'name':$("#parentTypeName").val()
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
	        }else if(obj.event === 'parentId'){
	        	table.render({
	                elem : '#childtype',
	                method : 'post',
	                url : 'zhishikuType/getChild.do',
	                where:{
	                    'parentId':obj.data.id,
	                    'name':$("#childTypeName").val()
	                },
	                cols : [[
	                    {type:'numbers'}
	                    ,{field:'typeName', width:200, title: '包含的子类名称', sort: true}
	                    ,{fixed: 'right', width: 200, title: '操作', align:'center', toolbar: '#zhishikuChildTypebar'}
	                  ]],
	                id:"zhishikuChildTypetable",
	                page : true
	            });
	        	table.on('tool(childtype)', function(obj){
	                var data = obj.data;
	                if(obj.event === 'edit'){
	                	var url = "zhishikuType/getById.do?isparent=0&id="+data.id;
	                    common.cmsLayOpen('编辑信息',url,'400px','220px');
	                }else if(obj.event === 'del'){
	                	top.layer.confirm("您确定要删除选择的数据吗？", {
	                        title: "提示",
	                        resize: false,
	                        btn: ['确定', '取消'],
	                        btnAlign: 'c',
	                        anim:1,
	                        icon: 3
	                    },function(index){
	                        $.ajax({
	                          url : 'zhishikuType/delete.do?id='+data.id,
	                          type : 'get',
	                          async: false,
	                          success : function(data) {
	                              data = eval("("+data+")");
	                              if(data.code == 0){
	                                  top.layer.close(index);
	                                  common.cmsLaySucMsg("保存成功");
	                                  table.reload('zhishikuChildTypetable',{
	                                      page: {
	                                          curr: 1 //重新从第 1 页开始
	                                      }
	                                      ,where:{
	                                          'parentId':parentId,
	                                          'name':$("#childTypeName").val()
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
	        }
	    });
		$("#parentTypequery").click(function(){
            //执行重载
            table.reload('zhishikuTypetable', {
                page: {
                  curr: 1 //重新从第 1 页开始
                }
                ,where: {
                	'name':$("#parentTypeName").val()
                }
            });
            
        });
		$("#parentTypereset").click(function(){
			$("#parentTypeName").val("")
		})
		$("#parentTypeadd").click(function(){
			var url = "zhishikuType/redirectAdd.do";
            common.cmsLayOpen('新增信息',url,'400px','220px');
		})
		$("#childTypequery").click(function(){
            //执行重载
			table.reload('zhishikuChildTypetable',{
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                ,where:{
                    'parentId':parentId,
                    'name':$("#childTypeName").val()
                }
            });
            
        });
		$("#childTypereset").click(function(){
            $("#childTypeName").val("")
        })
        $("#childTypeadd").click(function(){
            var url = "zhishikuType/redirectAdd.do?parentId="+parentId;
            common.cmsLayOpen('新增信息',url,'400px','220px');
        })
		/*
		*知识库列表中的操作按钮
		*/
		table.on('tool(zhishikuManage)', function(obj) {
			var data = obj.data;
			if (obj.event === 'edit') {
				window.open("zhishikuManage/modifyzhishiku.do?id="+data.id);
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
    		          url : 'zhishikuManage/delById.do?id='+data.id,
    		          type : 'get',
    		          async: false,
    		          success : function(data) {
    		        	  data = eval("("+data+")");
    		              if(data.code == 0){
    		                  top.layer.close(index);
    		                  common.cmsLaySucMsg("保存成功");
    		                  table.reload('zhishikuManagetable', {
    		      		        page: {
    		      		          curr: 1 //重新从第 1 页开始
    		      		        }
    		      		        ,where: {
    		      	        		'zhishikuManagename':$("#zhishikuManagename").val()
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
		$("#zhishikuManagequery").click(function(){
			//执行重载
		    table.reload('zhishikuManagetable', {
		        page: {
		          curr: 1 //重新从第 1 页开始
		        }
		        ,where: {
	        		'zhishikuManagename':$("#zhishikuManagename").val()
		        }
		    });
			
		});
		/*
		*初始化评论管理列表
		*/
		table.render({
			elem : '#zhishikuManagepl',
			method : 'post',
			url : 'zhishikuManage/getplAll.do',
			where:{
				'zhishikuManagecontentpl':$("#zhishikuManagecontentpl").val()
			},
			cols : [[
				{type:'numbers'}
				,{field:'zhishiku', width:550, title: '标题', sort: true,templet:'#zhishikuId'}
				,{field:'userIp',width:150, title: '用户IP'}
				,{field:'content',width:300, title: '评论内容'}
		      	,{field:'commentDateStr', width:250, title: '评论日期', sort: true}
			  	,{fixed: 'right', width: 165, title: '操作', align:'center', toolbar: '#zhishikuManageplbar'}
			  ]],
			id:"zhishikuManagepltable",
			page : true
		});
		/*
		*评论列表中的操作按钮
		*/
		table.on('tool(zhishikuManagepl)', function(obj) {
			var data = obj.data;
			if (obj.event === 'edit') {
				window.open("zhishikuManage/modifyzhishiku.do?id="+data.id);
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
    		          url : 'zhishikuManage/delplById.do?id='+data.id,
    		          type : 'get',
    		          async: false,
    		          success : function(data) {
    		        	  data = eval("("+data+")");
    		              if(data.code == 0){
    		                  top.layer.close(index);
    		                  common.cmsLaySucMsg("保存成功");
    		                  table.reload('zhishikuManagepltable', {
    		      		        page: {
    		      		          curr: 1 //重新从第 1 页开始
    		      		        }
    		      		        ,where: {
    		      	        		'zhishikuManagecontentpl':$("#zhishikuManagecontentpl").val()
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
		$("#zhishikuManageplquery").click(function(){
			//执行重载
		    table.reload('zhishikuManagepltable', {
		        page: {
		          curr: 1 //重新从第 1 页开始
		        }
		        ,where: {
	        		'zhishikuManagecontentpl':$("#zhishikuManagecontentpl").val()
		        }
		    });
			
		});
		table.render({
            elem : '#committee',
            method : 'post',
            url : 'committee/getCommittee.do',
            where:{
                'name':$("#committeeName").val()
            },
            cols : [[
                {type:'numbers'}
                ,{field:'committeename', width:200, title: '委员姓名', sort: true}
                ,{field:'committeeintroduction', width:200, title: '委员介绍', sort: true}
                ,{field:'committeegoodat', width:200, title: '擅长领域', sort: true}
                ,{fixed: 'right', width: 200, title: '操作', align:'center', toolbar: '#committeebar'}
              ]],
            id:"committeetable",
            page : true
        });
		/*
        *委员会介绍列表中的操作按钮
        */
        table.on('tool(committee)', function(obj) {
            var data = obj.data;
            if (obj.event === 'edit') {
            	var url = "committee/getById.do?id="+data.committeeid;
                common.cmsLayOpen('编辑委员信息',url,'600px','520px');
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
                      url : 'committee/delById.do?id='+data.committeeid,
                      type : 'get',
                      async: false,
                      success : function(data) {
                          data = eval("("+data+")");
                          if(data.code == 0){
                              top.layer.close(index);
                              common.cmsLaySucMsg("保存成功");
                              //执行重载
                              table.reload('committeetable', {
                                  page: {
                                    curr: 1 //重新从第 1 页开始
                                  }
                                  ,where: {
                                      'name':$("#committeeName").val()
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
		$("#committeequery").click(function(){
            //执行重载
            table.reload('committeetable', {
                page: {
                  curr: 1 //重新从第 1 页开始
                }
                ,where: {
                    'name':$("#committeeName").val()
                }
            });
            
        });
		$("#committeereset").click(function(){
            $("#committeeName").val("")
        })
        $("#committeeadd").click(function(){
            var url = "committee/redirectAdd.do";
            common.cmsLayOpen('新增信息',url,'600px','520px');
        })
		/*
		*初始化公告列表
		*/
		table.render({
			elem : '#notice',
			method : 'post',
			url : 'zhishikuManage/getNoticeAll.do',
			where:{
				'noticename':$("#noticeName").val()
			},
			cols : [[
				{type:'numbers'}
				,{field:'noticename', width:150, title: '公告标题', sort: true}
				,{field:'noticecontent',width:550, title: '公告内容'}
				,{field:'creatTimeStr',width:200, title: '发布时间'}
				,{field:'creatperson',width:100, title: '发布人'}
			  	,{fixed: 'right', width: 165, title: '操作', align:'center', toolbar: '#noticebar'}
			  ]],
			id:"noticetable",
			page : true
		});
		/*
		*公告列表中的操作按钮
		*/
		table.on('tool(notice)', function(obj) {
			var data = obj.data;
			if (obj.event === 'edit') {
				var url = "zhishikuManage/modifynotice.do?id="+data.noticeid;
	            common.cmsLayOpen('编辑信息',url,'700px','500px');
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
    		          url : 'zhishikuManage/delNoticeById.do?id='+data.noticeid,
    		          type : 'get',
    		          async: false,
    		          success : function(data) {
    		        	  data = eval("("+data+")");
    		              if(data.code == 0){
    		                  top.layer.close(index);
    		                  common.cmsLaySucMsg("保存成功");
    		                  table.reload('noticetable', {
    		      		        page: {
    		      		          curr: 1 //重新从第 1 页开始
    		      		        }
    		      		        ,where: {
    		      	        		'noticename':$("#noticeName").val()
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
		$("#noticeadd").click(function(){
			var url = "zhishikuManage/noticeAdd.do";
            common.cmsLayOpen('新增信息',url,'700px','500px');
		})
		$("#noticeNamequery").click(function(){
			//执行重载
		    table.reload('noticetable', {
		        page: {
		          curr: 1 //重新从第 1 页开始
		        }
		        ,where: {
	        		'noticename':$("#noticeName").val()
		        }
		    });
			
		});
  $("#zhishikuManageadd").click(function(){
	 window.open("zhishikuManage/writezhishikuindex.do");
	});
});
</script>
</body>
</html>

