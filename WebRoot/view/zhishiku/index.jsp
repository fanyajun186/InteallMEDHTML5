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

    <script type="text/javascript" src="plugins/layui/layui.js"></script>
    <script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/bootstrap3/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/bootstrap3/css/bootstrap-theme.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/zhishiku.css">
	<script src="${pageContext.request.contextPath}/plugins/bootstrap3/js/jquery-1.11.2.min.js"></script>
	<script src="${pageContext.request.contextPath}/plugins/bootstrap3/js/bootstrap.min.js"></script>
</head>
<body>
<div class="layui-container" style="margin-top: 10px">
    <div class="layui-row layui-col-space10">
        <div class="layui-carousel" id="test10">
	        <div carousel-item="" id="carousel">
	        </div>
	   </div>
    </div>
	<div class="layui-row layui-col-space10">
		<div class="layui-col-md9 ">
			<div class="data_list" >
			    <div class="data_list_title" style="background-color: #1E9FFF;color: #fff;">
			                &nbsp;&nbsp;&nbsp;&nbsp;最新文章
			    </div>
			    <div class="datas">
			        <ul id="zhishikuList">
				              
			        </ul>
			    </div>
			</div>
			
			<div id="page"></div>
		</div>
		<div class="layui-col-md3">
		    <div class="layui-form-item">
                    <input type="text" id="articleTitle" name="articleTitle" style="height: 38px; line-height: 1.3; border:1px solid #E5E5E5; border-radius: 2px" placeholder="请输入要查询的标题...">
                    <button type="button" class="layui-btn layui-btn-normal" style="height: 38px; " id="querybytitle">搜索</button>
		    </div>
		    <div id="zhishikuTypeList"></div>
		</div>
	</div>
</div>
</body>
<script type="text/javascript">
var count;
var typeId;
var releaseDateStr;
var laypage;
layui.config({
    base : "js/"
}).use(['laydate','layer','table','common','laypage','carousel','element'], function(){
  var laydate = layui.laydate;
  var layer = layui.layer;
  var table = layui.table;
  var $ = layui.jquery;
  var common = layui.common;
  var carousel = layui.carousel;
  var element = layui.element;
  
  $.ajax({
      url  : 'committee/findShow.do',
      type : 'post',
      async: false,
      success : function(data) {
          data = eval("("+data+")");
          if(data.code == 0){
               var committeeIntroductions = data.committeeIntroductions;
               var html = "";
               if(committeeIntroductions!=undefined){
	               for(var i=0;i<committeeIntroductions.length/5;i++){
	            	    html+='<div>'
	            	    for(var j=0;j<5;j++){
	            	    	if(committeeIntroductions[i*5+j]!=undefined){
	            	    		html+='<a href="javascript:;" function="addTab" topUrl="committee/detail" tabId="weiyuanxiangqing" committeeid="'+committeeIntroductions[i*5+j].committeeid+'" topTitle="委员详情" >'
	                            html+='<img  style="height:100%;width:20%;border:2px solid #adaaaa;" src="${pageContext.request.contextPath}'+committeeIntroductions[i*5+j].committeeimage+'"/>'
	                            html+='</a>'
	            	    	}
	               	    	
	               	    }
	            	    html+='</div>'
	               }
	               $("#carousel").append(html);
               }
          }
      }
  })
  //图片轮播
  carousel.render({
    elem: '#test10'
   	,width: '100%' //设置容器宽度
    ,interval: 5000
  });
  $("a").click(function(event){
      var _this = $(this);
      var fun = _this.attr("function");
      var id = _this.attr("tabId");
      var url = _this.attr("topUrl");
      var title = _this.attr("topTitle");
      var committeeid = _this.attr("committeeid");
      switch(fun){
          case "addTab" :
        	  window.parent.element.tabDelete("bodyTab", id); 
              window.parent.addTab(id,title,url+".do?committeeid="+committeeid)
          break;
      }
  });


  laypage = layui.laypage;
  
 
  getArticleList(1,typeId,releaseDateStr);
  getArticleType();
  
  laypage.render({
      elem: 'page'
      ,first: '首页'
      ,last: '尾页'

      ,count: count //数据总数
      ,jump: function(obj,first){
          //首次不执行
          if(!first){
              getArticleList(obj.curr,typeId,releaseDateStr);
          }
      }
  });
  $(".zhishikuType").click(function(){
	  var _this = $(this);
      var page = _this.attr("page");
      typeId= _this.attr("typeId");
      releaseDateStr = _this.attr("releaseDateStr");
	  getArticleList(page,typeId,releaseDateStr);
		laypage.render({
		    elem: 'page'
		    ,first: '首页'
		    ,last: '尾页'
		
		    ,count: count //数据总数
		    ,jump: function(obj,first){
		        //首次不执行
		        if(!first){
		            getArticleList(obj.curr,typeId,releaseDateStr);
		        }
		    }
		});
  })
  $("#querybytitle").click(function(){
	  getArticleList(1,"","");
	  laypage.render({
          elem: 'page'
          ,first: '首页'
          ,last: '尾页'
      
          ,count: count //数据总数
          ,jump: function(obj,first){
              //首次不执行
              if(!first){
                  getArticleList(obj.curr,typeId,releaseDateStr);
              }
          }
      });
  })
})
function getArticleType(){
	$.ajax({
        url  : 'zhishikuType/list.do',
        type : 'post',
        async: false,
        success : function(data) {
        	data = eval("("+data+")");
            if(data.code == 0){
            	 var parentList = data.parentList;
            	 var childList = data.childList;
                 var html = "";
                 for(var j=0;j<parentList.length;j++){
                	 html+='<div class="data_list" >'
                	 html+='<div class="data_list_title">'
               		 html+='&nbsp;&nbsp;<img src="${pageContext.request.contextPath}/img/zhishiku/byType_icon.png"/>&nbsp;&nbsp;'
               		 html+=parentList[j].typeName
               		 html+='</div>'
               		 html+='<div class="layui-row"  >'
                 
                	 for(var i=0;i<childList.length;i++){
                		 if(parentList[j].id==childList[i].parentId){
                			 html+=' <button style="margin-left:0px;margin-top:7px" class="layui-btn layui-btn-primary zhishikuType" page=1 typeId='+childList[i].id+'>'+childList[i].typeName+'</button>'
                		 }
                     }
                	 html+='</div>' 
               		 html+='</div>' 
                 }
                 
                 $("#zhishikuTypeList").append(html);
                 
            }
        }
	})
}

function getArticleList(page,typeId,releaseDateStr){
    typeId = typeId;
    $("#zhishikuList").children().remove();
    $.ajax({
        url  : 'zhishiku/getArticleList.do',
        type : 'post',
        async: false,
        data :{"page":page,"typeId":typeId,"releaseDateStr":releaseDateStr,"title":$("#articleTitle").val()},
        success : function(data) {
            data = eval("("+data+")");
            
            if(data.code == 0){
                var zhishikuList = data.zhishikuList;
                var html = "";
                for(var i=0;i<zhishikuList.length;i++){
                    html+=' <li style="margin-bottom: 30px">'
                    html+='<span class="title"><a target="_blank" href="${pageContext.request.contextPath}/zhishiku/articles/'+zhishikuList[i].id+'.do">'+zhishikuList[i].title+'</a></span>'
                    html+='<span class="summary">摘要: '+zhishikuList[i].summary+'...</span>'
                    html+='<span class="img">'
                    
                    for(var j=0;j<zhishikuList[i].imagesList.length;j++){
                        html+='<a target="_blank" href="${pageContext.request.contextPath}/zhishiku/articles/'+zhishikuList[i].id+'.do">'+zhishikuList[i].imagesList[j]+'</a>'
                        html+='&nbsp;&nbsp;'
                    }
                    
                    html+='</span>'
                    html+='<span class="info">发表于 '+zhishikuList[i].releaseDateStr+' 阅读('+zhishikuList[i].clickHit+') 评论('+zhishikuList[i].replyHit+') </span>'
                    html+='</li>'
                    html+='<hr style="border:none;border-top:1px dashed gray;" />'
                }
                $("#zhishikuList").append(html);
                count = data.count
                if(count==0){
                	$("#zhishikuList").append('<div style="text-align:center"><img style="width:228px;height:228px" src="${pageContext.request.contextPath}/img/noneicon.png"></div>');
                }
                
            }else{
                common.cmsLayErrorMsg(data.msg);
            }
        }
    })
}
</script>
</html>