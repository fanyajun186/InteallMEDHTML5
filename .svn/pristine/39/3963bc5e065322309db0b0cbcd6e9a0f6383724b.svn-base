<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>Inteall湖北肿瘤影像互联互通平台</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

    <link rel="stylesheet" href="plugins/layui/css/layui.css">
    <link rel="stylesheet" href="css/index.css">
    <script src="plugins/layui/layui.js"></script>
    <script src="js/jquery-1.4.4.min.js"></script>
	<script src="js/index.js"></script>
</head>
<body class="larryTheme-A">
	<div id="menu" class="inteall-menu-div"></div>
    <div class="inteall-layout-admin ">
        <!-- 顶部-->
        <div class="inteall-body" id="larry-body">
            <div class="layui-tab layui-tab-card" id="bodyTab" lay-unauto lay-allowClose="true" lay-filter="bodyTab">
            	<div class="inteall-header-left">
					<img class="inteall-header-logo" id="menuBtn" src="img/u39.png" title="系统菜单">
					<a class="inteall-header-title" >
						北京汇智精英科技有限公司
					</a>
				</div>
                <! -- 选项卡-->
                <ul class="layui-tab-title" id="top_tabs" lay-filter="top_tabs">
                    <li class="layui-this  main-tab" lay-id="1" style="z-index:100;"><i class="layui-icon">&#xe68e;</i> <cite style="font-style: normal;">首页</cite></li>
                </ul>
                <div class="inteall-title-box" id="bodyTab" style="height: 41px;" >
                    <div class="go-left key-press pressKey layui-tab-left"  id="titleLeft" title="滚动至最左侧"><i class="layui-icon">&#xe65a;</i> </div>
                    <div class="go-right key-press pressKey layui-tab-right" id="titleRight" title="滚动至最右侧"><i class="layui-icon">&#xe65b;</i></div>
                </div>
                <div style=" position: absolute;width:260px;top: 0px;right:0px;background: #393D49;height: 40px;color: red;z-index: 999;text-align: left;line-height: 40px">
					<ul class="layui-nav" style="padding: 0">
			            <li class="layui-nav-item" style="line-height: 39px">
			                <a href="javascript:;">常用</a>
			                <dl class="layui-nav-child" style="top: 40px">
			                    <dd data-skin="0" data-type="closethis"><a href="javascript:;">关闭当前</a></dd>
			                    <dd data-skin="1" data-type="closeother"><a href="javascript:;">关闭其他</a></dd>
			                    <dd data-skin="2" data-type="closeall"><a href="javascript:;">关闭全部</a></dd>
			                </dl>
			            </li>
			            <!-- <li class="layui-nav-item" style="line-height: 39px">
			                <a href="javascript:;" >主题</a>
			                <dl class="layui-nav-child" style="top: 40px">
			                    <dd data-skin="0"><a href="javascript:;">默认</a></dd>
			                    <dd data-skin="1"><a href="javascript:;">黑白</a></dd>
			                    <dd data-skin="2"><a href="javascript:;">蓝白</a></dd>
			                </dl>
			            </li> -->
			            <!-- <li class="layui-nav-item" style="line-height: 39px">
			                <a href="javascript:;" ><img alt="" src="img/u10.png"></a>
			            </li> -->
			            <li class="layui-nav-item" style="line-height: 39px">
			                <a href="javascript:;" ><img alt="" src="img/u12.png">${username}</a>
			                <dl class="layui-nav-child" style="top: 40px">
								<dd class="personal" function="addTab" topUrl="personal" tabId="personal" topTitle="个人资料"><a href="javascript:;"><cite>个人资料</cite></a></dd>
								<dd class="password" function="addTab" topUrl="password" tabId="password" topTitle="修改密码"><a href="javascript:;"><cite>修改密码</cite></a></dd>
							</dl>
			            </li>
			            <li class="layui-nav-item" style="line-height: 39px">
			                <a href="javascript:;" class="logout">退出</a>
			            </li>
		            </ul>
				</div>
                <div class="layui-tab-content inteall-content">
               		<div class="layui-tab-item layui-show">
                    <iframe class="inteall-content" id="iframe" src="home/home.do" frameborder="0"></iframe>
                	</div>
            	</div>
            </div>
        </div>
    </div>
   <script type="text/javascript">
   	layui.use(['layer', 'form','element'], function(){
	   var layer = layui.layer,form = layui.form,element=layui.element;
	   
	  	$("#titleLeft").click(function(){
	  		changeTab(1);
	  	})
	  	$("#titleRight").click(function(){
	  		changeTab(2);
	  	})
	   	$(".logout").click(function() {  
		top.layer.confirm("确定要退出吗?", {
	        title: "提示",
	        resize: false,
	        btn: ['确定', '取消'],
	        yes: function(index, layero){
	            //按钮【按钮一】的回调
	        	window.sessionStorage.clear();
	        	location.href ='loginout.do'
	        },
	        btn2: function(index, layero){
	            //按钮【按钮二】的回调
	            
	            //return false 开启该代码可禁止点击该按钮关闭
	          },
	        btnAlign: 'c',
	        anim:1,
	        icon: 3
	    })
    }); 
	  	//点击左右箭头时调整页签位置
	  	function changeTab(type){
	  		var tabCount = Number($("#top_tabs li").length);
	  		var marginSize = "";
	  		if(type==1){
	  			marginSize = "margin-left:1px";
	  		}else{
	  			marginSize = "margin-left:-90%";
	  		}
	  		$("#top_tabs li").each(function(item){
	  			var lay_id = $(this).attr("lay-id");
	  			if(tabCount>=8){
	  				if(item==1){
	  					$(this).attr("style",marginSize);
	  				}
	  			}
	  		})
	  	}
	  	
	  	//点击页签时，变更‘首页’页签的背景颜色
	  	$("#top_tabs li").live('click',function(){
	  		var lay_id = $(this).attr("lay-id");
	  		if(lay_id!='1'){
	  			$("#top_tabs li[lay-id=1]").css("background-color","#23262E");
	  		}else{
	  			$(this).css("background-color","");
		  		$(this).css("background-color","#1E9FF");
	  		}
	  		
	  		var first = '';
	  		$("#top_tabs li").each(function(item){
	  			var now_tabId = $(this).attr("lay-id");
	  			if(item==1){
	  				first = now_tabId;
	  			}
	  			if(lay_id==now_tabId){
	  				if(item<7){
	  					$("#top_tabs li[lay-id="+first+"]").css("margin-left","0px");
	  				}else if(item>=7 && item<11){
	  					$("#top_tabs li[lay-id="+first+"]").css("margin-left","-50%");
	  				}else{
	  					$("#top_tabs li[lay-id="+first+"]").css("margin-left","-90%");
	  				}
	  			}
	  		})
	  	})
	 });
	 </script>
</body>
</html>