<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta content="text/html;charset=UTF-8" />
<title>写知识库</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="no cache" />
<meta http-equiv="Expires" content="0" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="format-detection" content="telephone=no" />
<link rel="stylesheet" type="text/css" href="../plugins/layui/css/layui.css" media="all" />
<link rel="stylesheet" type="text/css" href="../css/faqi_baogao.css" media="all">
<link rel="stylesheet" type="text/css" href="../css/quntaolun.css" media="all">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" charset="utf-8" src="../plugins/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="../plugins/ueditor/ueditor.all.min.js"> </script>
<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="utf-8" src="../plugins/ueditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" src="../plugins/layui/layui.js"></script>
<script type="text/javascript">
$(function(){
	var ZhishikuType = ${ZhishikuType};
	for (var n= 0; n< ZhishikuType.length; n++) {
		var classification_id=ZhishikuType[n].id;
		var classification_name=ZhishikuType[n].typeName;
		$("#classification").append("<option value='"+classification_id+"'>"+classification_name+"</option>");
	}
})
function submitData(){
		var title=$("#title").val();
		var typeId=$('#Subclassification option:selected') .val();
		var content=UE.getEditor('editor').getContent();
		var keyWord=$("#keyWord").val();
		 if(title==null || title==''){
			alert("请输入标题！");
		}else if(typeId==null || typeId==''){
			alert("请选择类别！");
		}else if(content==null || content==''){
			alert("请输入内容！");
		}else{ 
			$.post("${pageContext.request.contextPath}/zhishikuManage/save.do",{'title':title,'content':content,'zhishikuType.id':typeId,'contentNoTag':UE.getEditor('editor').getContentTxt(),'summary':UE.getEditor('editor').getContentTxt().substr(0,155),'keyWord':keyWord},function(result){
				if(result.success){
					alert("知识库发布成功！");
					resetValue();
				}else{
					alert("知识库发布失败！");
				}
			},"json");
			}
	}
	// 重置数据
	function resetValue(){
		$("#title").val("");
		//$("#blogTypeId").combobox("setValue","");
		UE.getEditor('editor').setContent("");
		$("#keyWord").val("");
	}
</script>
</head>
<body style="background: #393D49;">
<div class="layui-container" style="margin-top: 10px">
<div id="p" class="layui-fluid" title="编写知识库" style="padding: 10px">
	<form class="layui-form layui-col-md12">
		<div class="layui-form-item layui-form-text">
          <label class="layui-form-label" style="color: #FFF">标题：</label>
           <div class=" layui-col-md10">
          	   <input type="text" id="title" name="title" placeholder="知识库标题" autocomplete="off" class="layui-input">
           </div>
        </div>
        <div class="layui-form-item layui-form-text">
          <label class="layui-form-label" style="color: #FFF">内容：</label>
           <div class=" layui-col-md10">
               <script id="editor" type="text/plain" style="width:100%;height:500px;"></script>
           </div>
        </div>
        <div class="layui-form-item layui-form-text">
          <label class="layui-form-label" style="color: #FFF">类别：</label>
           <div class=" layui-col-md10">
           <div class="layui-input-inline" style="width:21%;color: #FFF">
             <select id="classification" style="width: 154px" lay-filter="myselect">
				<option value="">请选择父类</option>
			</select>
			</div>
			<div class="layui-input-inline" style="width:21%;color: #FFF">
			<select id="Subclassification" style="width: 154px">
				<option value="">请选择子类</option>
			 </select>
           </div>
          </div>
        </div>
        <div class="layui-form-item layui-form-text">
          <label class="layui-form-label" style="color: #FFF">关键字：</label>
           <div class=" layui-col-md10">
          	   <input type="text" id="keyWord" name="keyWord" style="width:43%;" placeholder="关键字" autocomplete="off" class="layui-input"/>&nbsp;<span style="color: #FFF">(多个关键字中间用空格隔开)</span>
           </div>
        </div>
        <div class="layui-form-item layui-form-text">
          <label class="layui-form-label"></label>
           <div class=" layui-col-md10">
           		<button class="layui-btn layui-btn-normal" type="button" onclick="submitData()">发布知识库</button>
           </div>
        </div>
	</form>
 </div>
</div>
 <script type="text/javascript">
 //实例化编辑器
 //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
 var ue = UE.getEditor('editor');
 layui.config({
     base : "../js/"
 }).use([ 'form', 'layedit','common', 'laydate' ], function() {
     var layedit = layui.layedit;
     var common = layui.common;
     var form = layui.form;
     form.on('select(myselect)', function(data){
    	 if(data.value != ""){
    		 var id=data.value;
	   		  $.ajax({
		                type: 'GET',
		                url: '<%=request.getContextPath()%>/zhishikuManage/findBySubclass.do',
		                data: {isParent:id},
		                dataType:  'json',
		                success: function(data){
		                $("#Subclassification").html("");
		                if(data.length>0){
		                	  $.each(data, function(key, val) {
				                	var option = $("<option>").val(val.id).text(val.typeName);
				                            $("#Subclassification").append(option);
				                            form.render('select');
				                        }); 
				             	 $("#Subclassification").get(0).selectedIndex=0;
		                }else{
		                	var option = $("<option>").val("").text("请选择子类");
		                    $("#Subclassification").append(option);
		                    form.render('select')
		                }
		                }
		        }); 
    	 }else{
    		 $("#Subclassification").html("");
    		 var option = $("<option>").val("").text("请选择子类");
             $("#Subclassification").append(option);
             form.render('select');
    	 }
    	 
    	});
 	});
     
</script>
</body>
</html>

