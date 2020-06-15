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
    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/bootstrap3/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/bootstrap3/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/zhishiku.css">
    <script src="${pageContext.request.contextPath}/plugins/bootstrap3/js/jquery-1.11.2.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/bootstrap3/js/bootstrap.min.js"></script>
    
    <script type="text/javascript" src="${pageContext.request.contextPath}/plugins/uploadify/js/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/uploadify/js/jquery.ui.widget.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/uploadify/js/jquery.iframe-transport.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/uploadify/js/jquery.fileupload.js"></script>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/plugins/uploadify/css/jquery.fileupload.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/plugins/uploadify/css/H-ui.css">
    
</head>
<body class="body_bg">
<form class="layui-form" style="margin-top: 10px" action="">
    <div class="layui-form-item">
	    <div class="layui-inline">
	        <label class="layui-form-label" style="width: 100px"><spite style="color: red">*</spite>宣传图</label>
	        <div class="layui-input-block">
	            <input type="hidden" name="committeeimage"  required lay-verify="required" />
	            <span class="layui-btn layui-btn-normal fileinput-button mr-5">
                    <span>浏览文件</span>
                    <input id="fileBtn" type="file" name="file">
                </span>
	            <span id="filename"></span>
	            <span>图片高度最好是200像素(px)</span>
	        </div>
	    </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
			<label class="layui-form-label" style="width: 100px"><spite style="color: red">*</spite>委员姓名</label>
			<div class="layui-input-block">
			    <input type="text" name="committeename" id="committeename" placeholder="委员名称"  
			    lay-verify="required" autocomplete="off" class="layui-input" >
			</div>
		</div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label" style="width: 100px">委员介绍</label>
        <div class="layui-input-block">
            <textarea id="committeeintroduction" lay-verify="required" name="committeeintroduction" placeholder="委员介绍"
                class="layui-textarea layui-input"></textarea>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label" style="width: 100px">擅长领域</label>
        <div class="layui-input-block">
            <textarea id="committeegoodat" lay-verify="required" name="committeegoodat" placeholder="擅长领域"
                class="layui-textarea layui-input"></textarea>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label" style="width: 100px">是否展示</label>
        <div class="layui-input-block">
	      <input name="isshow" title="是" type="radio" value="1" checked="checked">
          <input name="isshow" title="否" type="radio" value="0">
	    </div>

    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-normal" lay-submit="" lay-filter="demo1">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
</body>
<script type="text/javascript">
layui.config({
    base : "../js/"
}).use(['common','form', 'layedit', 'laydate'], function(){
    var form = layui.form
    ,layer = layui.layer
    ,layedit = layui.layedit
    ,laydate = layui.laydate
    ,common = layui.common;
    
    $("#fileBtn").fileupload({
        url: 'uploadImage.do',
        done: function(e, data) {   //done为文件上传成功需要做的事情
        	res = eval("("+data.result+")");
        	if(res.code == 0){
                common.cmsLaySucMsg("上传成功");
            }else{
                common.cmsLaySucMsg("上传失败");
            }
            $("[name=committeeimage]").val(res.src);
            $("#filename").text(res.oldName);
        },
    });
    //监听提交
    form.on('submit(demo1)', function(data){
        var userSaveLoading = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        $.ajax({
            url : 'save.do',
            type : 'post',
            async: false,
            data : data.field,
            success : function(data) {
                data = eval("("+data+")");
                if(data.code == 0){
                    top.layer.close(userSaveLoading);
                    common.cmsLaySucMsg("保存成功");
                    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                    parent.layer.close(index); //再执行关闭                        //刷新父页面
                   	parent.layui.table.reload('committeetable', {
                         page: {
                           curr: 1 //重新从第 1 页开始
                         }
                         ,where:{
                             'name':""
                         },
                    });
                   
                   
                }else{
                    top.layer.close(userSaveLoading);
                    common.cmsLayErrorMsg(data.msg);
                }
            },error:function(data){
                top.layer.close(index);

            }
        },"json");
        return false;
    });
})
</script>
</html>