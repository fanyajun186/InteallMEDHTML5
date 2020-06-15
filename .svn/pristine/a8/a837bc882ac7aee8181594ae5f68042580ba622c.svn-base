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

    <link rel="stylesheet" type="text/css" href="../plugins/layui/css/layui.css" media="all" />

    <script type="text/javascript" src="../plugins/layui/layui.js"></script>
    <script type="text/javascript" src="../js/jquery-1.4.4.min.js"></script>
    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/bootstrap3/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/bootstrap3/css/bootstrap-theme.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/zhishiku.css">
	<script src="${pageContext.request.contextPath}/plugins/bootstrap3/js/jquery-1.11.2.min.js"></script>
	<script src="${pageContext.request.contextPath}/plugins/bootstrap3/js/bootstrap.min.js"></script>
</head>
<body>
<div class="layui-container" style="margin-top: 10px">

	<div class="layui-row layui-col-space10">
		<div class="layui-col-md9 ">
			<div class="data_list" >
			    <div class="data_list_title" style="background-color: #1E9FFF;color: #fff;">
			                &nbsp;&nbsp;&nbsp;&nbsp;最新公告
			    </div>
			    <div class="datas">
			        <ul id="noticeList">
				              
			        </ul>
			    </div>
			</div>
			
			<div id="page"></div>
		</div>
		<div class="layui-col-md3">
		    <div class="layui-form-item">
                    <input type="text" id="noticeName" name="noticeName" style="height: 38px; line-height: 1.3; border:1px solid #E5E5E5; border-radius: 2px" placeholder="请输入要查询的公告标题...">
                    <button type="button" class="layui-btn layui-btn-normal" style="height: 38px; " id="querybytitle">搜索</button>
		    </div>
		</div>
	</div>
</div>
</body>
<script type="text/javascript">
var count;
var laypage;
layui.config({
    base : "../js/"
}).use(['laydate','layer','table','common','laypage'], function(){
  var laydate = layui.laydate;
  var layer = layui.layer;
  var table = layui.table;
  var $ = layui.jquery;
  var common = layui.common;
  laypage = layui.laypage;
  getArticleList(1);
  laypage.render({
      elem: 'page'
      ,first: '首页'
      ,last: '尾页'
      ,count: count //数据总数
      ,jump: function(obj,first){
          //首次不执行
          if(!first){
              getArticleList(obj.curr);
          }
      }
  });
  $("#querybytitle").click(function(){
	  getArticleList(1);
	  laypage.render({
          elem: 'page'
          ,first: '首页'
          ,last: '尾页'
          ,count: count //数据总数
          ,jump: function(obj,first){
              //首次不执行
              if(!first){
                  getArticleList(obj.curr);
              }
          }
      });
  })
})
function getArticleList(page){
    $("#noticeList").children().remove();
    $.ajax({
        url  : '../zhishikuManage/getnoticeList.do',
        type : 'post',
        async: false,
        data :{"page":page,"noticename":$("#noticeName").val()},
        success : function(data) {
            data = eval("("+data+")");
            if(data.code == 0){
                var noticeList = data.noticeList;
                var html = "";
                for(var i=0;i<noticeList.length;i++){
                    html+=' <li style="margin-bottom: 30px">'
                    html+='<h2 style="text-align: center;"><span class="noticeName">'+noticeList[i].noticename+'</span></h2>'
                    html+='<p><h5 class="noticeContent">'+noticeList[i].noticecontent+'</h5></p>'
                    html+='<span class="info">发表于 '+noticeList[i].creatTimeStr+'</span>'
                    html+='</li>'
                    html+='<hr style="border:none;border-top:1px dashed gray;" />'
                }
                $("#noticeList").append(html);
                count = data.count
                if(count==0){
                	$("#noticeList").append('<div style="text-align:center"><img style="width:228px;height:228px" src="${pageContext.request.contextPath}/img/noneicon.png"></div>');
                }
                
            }else{
                common.cmsLayErrorMsg(data.msg);
            }
        }
    })
}
</script>
</html>