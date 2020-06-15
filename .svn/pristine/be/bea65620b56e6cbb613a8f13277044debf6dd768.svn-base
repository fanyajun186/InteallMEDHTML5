var element
layui.config({
    base : "js/"
}).use(['layer','element','common','form', 'layedit', 'laydate'], function(){ //独立版的layer无需执行这一句
	var $ = layui.jquery 
	 ,layer = layui.layer//独立版的layer无需执行这一句
	,common = layui.common
	,layedit = layui.layedit
	,laydate = layui.laydate
	 element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
	$(function(){
		/**
		 * 加载系统菜单
		 */
		$.ajax({
			url:"queryMenu.do",
			type:"post",
			success:function(data){
				var json = eval("("+data+")");
				var html="<table border='0' width='250px' align='center'>"
					+"<tr>" 
					+"<td align='center' style='padding:13px 0px 13px 0px'>" 
					+"<a class='inteall-menu-item' function='addTab' topUrl='home/home.do' tabId='1' topTitle='首页' style='color:#fff'><img  src='img/menuicon_gray_home.png'style='width:54px;height:54px'/></a>"
					+"</td>" 
				if(json.right.length!=undefined){
					for(var i=0;i<json.right.length;i++){
						html+="<td align='center' style='padding:13px 0px 13px 0px'>" 
						html+="<a class='inteall-menu-item' function='addTab' topUrl='"+json.right[i].rightUrl+"' tabId='"+json.right[i].rightUrl+"' topTitle='"+json.right[i].rightName+"' style='color:#fff'><img style='width:54px;height:54px' src='"+json.right[i].urlImg+"'/></a>"
						html+="</td>"
						if(i%2==0){
							html+="</tr><tr>"
						}
					}
				}
				
				html+="</tr></table>";
				$("#menu").append(html);
				
				$(".inteall-menu-item").click(function(event){
					var _this = $(this);
					var fun = _this.attr("function");
					var id = _this.attr("tabId");
					var url = _this.attr("topUrl");
					var title = _this.attr("topTitle");
					switch(fun){
						case "addTab" :
							addTab(id,title,url+".do")
						break;
					}
				});
			}
			
		});
		
	})
	
	/**
	 * 菜单按钮点击，显示菜单
	 */
	$("#menuBtn").click(function(){
		$("#menu").show();
	});
	/**
	 * 鼠标移到菜单外，菜单隐藏
	 */
	$("#menu").mouseleave(function(){
		$("#menu").css("display","none");
	});
	$(".layui-nav-child dd").click(function(id) {
		if ($(this).attr("data-type") == "closethis") {
			 if($("#top_tabs li").length>1 && $("#top_tabs li.layui-this cite").text()!="首页"){
		            $("#top_tabs li").each(function(){
		                if($(this).attr("lay-id") != '' && $(this).hasClass("layui-this")){
		                    element.tabDelete("bodyTab",$(this).attr("lay-id")).init();
		                }
		            });
		        }else{
		            top.layer.msg('首页不能关闭',{icon: 0});
		        }
	    } else if ($(this).attr("data-type") == "closeother") {
	    	if($("#top_tabs li").length>2 && $("#top_tabs li.layui-this cite").text()!="首页"){
	            $("#top_tabs li").each(function(){
	                if($(this).attr("lay-id") != '1' && $(this).attr("lay-id") != '' && !$(this).hasClass("layui-this")){
	                    element.tabDelete("bodyTab",$(this).attr("lay-id")).init();
	                    //此处将当前窗口重新获取放入session，避免一个个删除来回循环造成的不必要工作量
	                }else{
	    	            top.layer.msg('没有可关闭的窗口',{icon: 0});
	    	        }
	            });
	        }else if($("#top_tabs li.layui-this cite").text()=="首页" && $("#top_tabs li").length>1){
	            $("#top_tabs li").each(function(){
	                if($(this).attr("lay-id") != '1' && $(this).attr("lay-id") != '' && !$(this).hasClass("layui-this")){
	                    element.tabDelete("bodyTab",$(this).attr("lay-id")).init();
	                }
	            })
	        }else{
	            top.layer.msg('没有可关闭的窗口',{icon: 0});
	        }
	    }else if ($(this).attr("data-type") == "closeall") {
	    	if($("#top_tabs li").length > 1 ){
	            $("#top_tabs li").each(function(){
	                if($(this).attr("lay-id") != '1' && $(this).attr("lay-id") != ''){
	                    element.tabDelete("bodyTab",$(this).attr("lay-id")).init();
	                }else{
	                	$("#top_tabs li[lay-id=1]").css("background-color","#1E9FFF");
	                }
	            })
	        }else{
	            top.layer.msg('没有可关闭的窗口',{icon: 0});
	        }
	    }
	});
	$(".personal").click(function() { 
		var url = "sysuser/personal.do";
		common.cmsLayOpen('编辑个人信息',[url,'no'],'450px','600px');
    }); 
	$(".password").click(function(event) {  
		var url = "sysuser/password.do";
        common.cmsLayOpen('用户修改密码',[url,'no'],'450px','350px');
    });
	/*$(".logout").click(function() {  
		top.layer.confirm("确定要退出吗?", {
	        title: "提示",
	        resize: false,
	        btn: ['确定', '取消'],
	        yes: function(index, layero){
	            //按钮【按钮一】的回调
	        	console.log(sessionStorage);
	        	window.sessionStorage.clear();
	        },
	        btn2: function(index, layero){
	            //按钮【按钮二】的回调
	            
	            //return false 开启该代码可禁止点击该按钮关闭
	          },
	        btnAlign: 'c',
	        anim:1,
	        icon: 3
	    },function(){
	    	location.href ='loginout.do'
	    })
    }); */
	/* //tab左移
	 $(".layui-tab-left").click(function(){
		 var _left= $(".layui-tab-title").css("left");
	        _left = parseInt(_left.substr(0, _left.length - 2));
	        var _lis = $(".layui-tab-title").find("li");
	        var n = 0;
	        for (var i = 0; i < _lis.length; i++) {
	            n += $(_lis[i]).width()+15;
	        }
	        var abs = Math.abs(_left);
	        //获取右侧区域宽度
	        var _width = $(".layui-body").width();
	        if (n-abs < _width) {
	        	
	        } else {
	            $(".layui-tab-title").css("left", _left - 100);

	        }
	 })
   //tab右移
   $(".layui-tab-right").click(function(){
       var _left= $(".layui-tab-title").css("left");
       _left = parseInt(_left.substring(0, _left.length - 2));
       
       if (_left < 0) {
           $(".layui-tab-title").css("left", _left + 100);
       }
       else {
    	   console.log(1)
           $(".layui-tab-title").css("left", 291);
       }
   });*/
})
/**
 * 新增tab页
 */  
function addTab(id,title,url){
	//触发事件
	var active = {
    tabAdd: function(id,title,url){
    	if($('#bodyTab>ul>li[lay-id="'+id+'"]').length==0){
	    	//新增一个Tab项
	    	element.tabAdd('bodyTab', {
	    		title: title //用于演示
	    		,content: '<iframe class="inteall-content" src="'+url+'"></iframe>'
	    		,id: id //实际使用一般是规定好的id，这里以时间戳模拟下
	    	})
	    }
    }
	};
	active['tabAdd'] ? active['tabAdd'].call('',id,title,url) : '';
	element.tabChange('bodyTab', id); 
	
	if(title=='首页'){
		$("#top_tabs li[lay-id=1]").css("background-color","#1E9FFF");//给‘首页’Tab添加背景色蓝色
	}else{
		$("#top_tabs li[lay-id=1]").css("background-color","#23262E");//给‘首页’Tab添加背景色黑色
	}
	
	$("#top_tabs li").each(function(item){
		var lay_id = $(this).attr("lay-id");//每一个tab页签的id
		var firstTab = "";//第一个tab页签的id
		if(item==1){
			firstTAb = lay_id;
		}
		if(lay_id==id){
			if(item<7){
				$("#top_tabs li[lay-id="+firstTAb+"]").attr("style","margin-left:0px");
			}else if(item==7){
				$("#top_tabs li[lay-id="+firstTAb+"]").attr("style","margin-left:-50px");
			}else if(item>7 && item<11){
				$("#top_tabs li[lay-id="+firstTAb+"]").attr("style","margin-left:-50%");
			}else{
				$("#top_tabs li[lay-id="+firstTAb+"]").attr("style","margin-left:-100%");
			}
		}
	})
	
}