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

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/plugins/layui/css/layui.css" media="all" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/faqi_baogao.css" media="all">
    <script type="text/javascript" src="${pageContext.request.contextPath}/plugins/layui/layui.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.4.min.js"></script>
    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/zhishiku.css">
    
</head>
<body class="body_bg">
<div class="layui-container" style="margin-top: 10px">
    <div class="layui-row">
    <div class="layui-col-md4">
        <img id="committeeimage" style="width: 100%;" src="${pageContext.request.contextPath}${cIntroduction.committeeimage }"/>
    </div>
    <div class="layui-col-md8">
	    <div style="margin-top: 20px;text-align: center;color: #1E9FFF">
            <h1>
                <span id="committeename">${cIntroduction.committeename }</span>
            </h1>
		</div>
        <div style="margin-top: 20px">
            <label class="layui-form-label" style="color: #1E9FFF">委员介绍</label>
            <div class="layui-input-block">
                <div id="committeeintroduction">${cIntroduction.committeeintroduction }</div>
            </div>        
        </div>
        
        
         <div style="margin-top: 20px">
            <label class="layui-form-label" style="color: #1E9FFF">擅长领域</label>
            <div class="layui-input-block">
                <div id="committeegoodat">${cIntroduction.committeegoodat }</div>
            </div>        
        </div>
        
    </div>
    </div>    
    <div class="layui-row layui-col-md12" style="margin-top: 20px">
        <div class="data_list" >
            <div class="data_list_title" style="background-color: #1E9FFF;color: #fff;">
                        &nbsp;&nbsp;&nbsp;&nbsp;所有委员列表
            </div>
            
        </div>
        <table class="layui-table" id="committee" lay-filter="committee"></table>
        <span >*点击列表查看委员详情*</span>
    </div>
    
</div>
</body>
<script type="text/javascript">
layui.config({
    base : "../js/"
}).use(['common','form', 'layedit','upload','table', 'laydate'], function(){
    var form = layui.form
    ,layer = layui.layer
    ,layedit = layui.layedit
    ,laydate = layui.laydate
    ,table = layui.table
    ,common = layui.common;
    table.render({
        elem : '#committee',
        method : 'post',
        url : 'getCommittee.do',
        where:{
            'name':$("#committeeName").val()
        },
        cols : [[
            {type:'numbers'}
            ,{field:'committeename',  title: '委员姓名',event: 'detail', sort: true}
            ,{field:'committeeintroduction', title: '委员介绍',event: 'detail', sort: true}
            ,{field:'committeegoodat',  title: '擅长领域', event: 'detail',sort: true}
          ]],
        id:"committeetable",
        page : true
    });
    table.on('tool(committee)', function(obj){
        var data = obj.data;
        if(obj.event === 'detail'){
        	$("#committeeimage").attr("src","${pageContext.request.contextPath}"+data.committeeimage);
            $("#committeename").text(data.committeename);
            $("#committeeintroduction").text(data.committeeintroduction);
            $("#committeegoodat").text(data.committeegoodat);
        }
    })
})
</script>
</html>