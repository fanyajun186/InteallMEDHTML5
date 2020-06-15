<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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

    <script type="text/javascript" src="${pageContext.request.contextPath}/plugins/layui/layui.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.4.min.js"></script>
    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/bootstrap3/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/bootstrap3/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/zhishiku.css">
    <script src="${pageContext.request.contextPath}/plugins/bootstrap3/js/jquery-1.11.2.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/bootstrap3/js/bootstrap.min.js"></script>
    
</head>
<body>
<div class="layui-container" style="margin-top: 10px">
	<div class="data_list">
		<div class="data_list_title">
			&nbsp;&nbsp;<img
				src="${pageContext.request.contextPath}/img/zhishiku/show_icon.png" />
			知识库信息
		</div>
		<div>
			<div class="blog_title">
				<h1>
					<strong>${Zhishiku.title }</strong>
				</h1>
			</div>
			<div class="blog_info">
				发布时间：『${Zhishiku.releaseDateStr }
				』&nbsp;&nbsp;知识库类别：${Zhishiku.zhishikuType.typeName}&nbsp;&nbsp;阅读(${Zhishiku.clickHit})
				评论(${Zhishiku.replyHit})
			</div>
			<div class="blog_content">${Zhishiku.content }</div>
			<div class="blog_keyWord">
				<font><strong>关键字：</strong></font>
				<c:choose>
					<c:when test="${keyWords==null}">
                    &nbsp;&nbsp;无
                </c:when>
					<c:otherwise>
						<c:forEach var="keyWord" items="${keyWords }">
                        &nbsp;&nbsp;${keyWord }&nbsp;&nbsp;
                    </c:forEach>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="blog_lastAndNextPage">${pageCode }</div>
		</div>
	</div>
	<div class="data_list">
		<div class="data_list_title">
			&nbsp;&nbsp;<img
				src="${pageContext.request.contextPath}/img/zhishiku/comment_icon.png" />
			评论信息
				<a href="javascript:showOtherComment()"
					style="float: right; padding-right: 40px;color: #fff">显示所有评论</a>
		</div>
		<div class="commentDatas" id="commentDatas">
			
		</div>
	</div>

	<div class="data_list">
		<div class="data_list_title">
			&nbsp;&nbsp;<img
				src="${pageContext.request.contextPath}/img/zhishiku/publish_comment_icon.png" />
			发表评论
		</div>
		<div class="publish_comment">
			<div>
				<textarea style="width: 100%" rows="3" id="content" name="content"
					placeholder="来说两句吧..."></textarea>
			</div>
			<div class="publishButton">
				<button class="layui-btn layui-btn-normal" type="button" onclick="submitData()">发表评论</button>
			</div>
		</div>
	</div>
</div>
</body>
<script type="text/javascript">
function showOtherComment(){
    $('.otherComment').show();
}
var html="";
var commentList = ${commentList};
if(commentList==undefined||commentList.length==0){
	html+="暂无数据"
}
for(var i=0;i<commentList.length;i++){
	if(i<10){
		html+='<div class="comment">'
		html+='<span><font>'+(i+1)+'楼&nbsp;&nbsp;&nbsp;&nbsp;'+commentList[i].commentPersonName+'：</font>'+commentList[i].content+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[&nbsp;'+commentList[i].commentDateStr+'&nbsp;]</span>'
		html+='</div>'  
	}else{
		html+='<div class="otherComment">'
		html+='<div class="comment">'
		html+='<span><font>'+(i+1)+'楼&nbsp;&nbsp;&nbsp;&nbsp;'+commentList[i].commentPersonName+'：</font>'+commentList[i].content+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[&nbsp;'+commentList[i].commentDateStr+'&nbsp;]</span>'
		html+='</div>'      
		html+='</div>'    
	}
}                
$("#commentDatas").append(html)   
function submitData(){
    var content=$("#content").val();
    if(content==null || content==''){
        alert("请输入评论内容！");
    }else{
        $.post("${pageContext.request.contextPath}/zhishikucomment/save.do",{'content':content,'zhishiku.id':'${Zhishiku.id}'},function(data){
            console.log(data)
        	if(data.code==0){
                window.location.reload();
                alert("评论已提交显示！");
            }else{
                alert(result.errorInfo);
            }
        },"json");
    }
}
</script>
</html>