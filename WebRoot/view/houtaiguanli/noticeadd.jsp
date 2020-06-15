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
    
</head>
<body class="body_bg">
<form class="layui-form" style="margin-top: 10px" action="">
    <div class="layui-form-item">
		<label class="layui-form-label" style="width: 100px"><spite style="color: red">*</spite>公告标题</label>
		<div class="layui-input-block">
		    <input type="text" name="noticeName" id="noticeName" placeholder="公告标题"  
		    lay-verify="required" autocomplete="off" class="layui-input" >
		</div>
    </div>
	<div class="layui-form-item">
		<label class="layui-form-label" style="width: 100px"><spite style="color: red">*</spite>公告内容</label>
		<div class="layui-input-block">
			<textarea name="noticeContent" id="noticeContent" onkeyup="checkLen(this)" maxlength="100"
			 onpropertychange="if(value.length>100) value=value.substr(0,100)" class="layui-textarea layui-input" style="height: 300px"></textarea>
			 <div>您还可以输入 <span id="count">100</span> 个文字</div>
         </div>
    </div>
    <div class="layui-form-item" style="text-align: center;">
            <button class="layui-btn layui-btn-normal" lay-submit="" lay-filter="demo1">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
    </div>
</form>
</body>
<script type="text/javascript">
/*字数限制*/
function checkLen(obj){
					var maxChars = 100;//最多字符数
					if (obj.value.length > maxChars)
					obj.value = obj.value.substring(0,maxChars);
					var curr = maxChars - obj.value.length;
					document.getElementById("count").innerHTML = curr.toString();
					}
layui.config({
    base : "../js/"
}).use(['common','form', 'layedit', 'laydate'], function(){
    var form = layui.form
    ,layer = layui.layer
    ,layedit = layui.layedit
    ,laydate = layui.laydate
    ,common = layui.common;
    //监听提交
    form.on('submit(demo1)', function(data){
        var userSaveLoading = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        $.ajax({
            url : 'noticesave.do',
            type : 'post',
            async: false,
            data : {"noticeName":$("#noticeName").val(),"noticeContent":$("#noticeContent").val()},
            success : function(data) {
                data = eval("("+data+")");
                if(data.code == 0){
                    top.layer.close(userSaveLoading);
                    common.cmsLaySucMsg("保存成功");
                    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                    parent.layer.close(index); //再执行关闭                        //刷新父页面
                    parent.layui.table.reload('noticetable', {
                        page: {
                          curr: 1 //重新从第 1 页开始
                        }
                        ,where:{
                            'noticeName':""
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