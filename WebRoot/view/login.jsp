<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
	
    <link rel="stylesheet" href="css/login.css">
    <script type="text/javascript" src="plugins/layui/layui.js"></script>
</head>
<body>
<div class="layui-carousel video_mask" id="login_carousel" >
    <div carousel-item>
        <div class="carousel_div1"></div>
        <div class="carousel_div2"></div>
        <div class="carousel_div3"></div>
    </div>
    <div class="login layui-anim layui-anim-up">
        <h1>Inteall</h1></p>
        <h1>湖北肿瘤影像互联互通平台</h1>
        <form class="layui-form" action="" method="post">
            <div class="layui-form-item">
                <input type="text" name="loginname" lay-verify="required" placeholder="请输入账号" autocomplete="off"  value="" class="layui-input">
            </div>
            <div class="layui-form-item">
                <input type="password" name="password" lay-verify="required" placeholder="请输入密码" autocomplete="off" value="" class="layui-input">
            </div>

            <button class="layui-btn login_btn" lay-submit="" lay-filter="login">登录系统</button>
        </form>
    </div>

</div>
</body>
<script>
    layui.config({
        base : "js/"
    }).use(['form','common','carousel'], function () {
        var $ = layui.jquery,
                form = layui.form,
                common = layui.common,
                carousel = layui.carousel;

        /**背景图片轮播*/
        carousel.render({
             elem: '#login_carousel',
             width: '100%',
             height: '100%',
             interval:2000,
             arrow: 'none',
             anim: 'fade',
             indicator:'none'
        });


        /**监听登陆提交*/
        form.on('submit(login)', function (data) {
            //弹出loading
            var loginLoading = top.layer.msg('登陆中，请稍候', {icon: 16, time: false, shade: 0.8});
			
            var ajaxReturnData;
            //登陆验证
            $.ajax({
                url: 'loginCheck.do',
                type: 'POST',
                async: false,
                data: data.field,
                dataType:'json',
                success: function (data) {
                	ajaxReturnData= data
                }
            });
            //登陆成功
            if (ajaxReturnData.code == 0) {
                window.location.href="index.do";
                top.layer.close(loginLoading);
                return false;
            } else {
                top.layer.close(loginLoading);
                common.cmsLayErrorMsg(ajaxReturnData.msg);
                return false;
            }
            return false;
        });

    });

</script>
</html>