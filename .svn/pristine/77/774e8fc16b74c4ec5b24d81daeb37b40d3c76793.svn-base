<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta content="text/html;charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="no cache" />
<meta http-equiv="Expires" content="0" />
<meta name="viewport"
    content="width=device-width, initial-scale=1, maximum-scale=1" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="format-detection" content="telephone=no" />

<link rel="stylesheet" type="text/css"
    href="../plugins/layui/css/layui.css" media="all" />
<link rel="stylesheet" type="text/css" href="../css/readimage.css">
<link rel="stylesheet" type="text/css" href="../css/faqi_baogao.css">
<link rel="stylesheet" href="../plugins/uploadify/uploadify.css" type="text/css"></link>  
<script type="text/javascript" src="../plugins/layui/layui.js"></script>
<script type="text/javascript" src="../plugins/uploadify/js/jquery.min.js"></script>
<script type="text/javascript" src="../plugins/uploadify/js/jquery.ui.widget.js"></script>
<script type="text/javascript" src="../plugins/uploadify/js/jquery.iframe-transport.js"></script>
<script type="text/javascript" src="../plugins/uploadify/js/jquery.fileupload.js"></script>
<link rel="stylesheet" type="text/css" href="../plugins/uploadify/css/jquery.fileupload.css">
<link rel="stylesheet" type="text/css" href="../plugins/uploadify/css/H-ui.css">
</head>
<body class="body_bg">
    <div class="layui-col-md12" style="margin-top: 10px">
             <input hidden="hidden" name="readimagekey" value="${readimagekey}">
            <div class="layui-col-md12">
             	<span class="btn btn-success fileinput-button mr-5">
	       			<span>浏览文件</span>
	       			<input id="fileupload" type="file" name="file" multiple>
   				</span>
   				<div id="progress" class="progress mt-10">
   				<!-- ajax上传成功后返回文件名，供你使用 -->
				<div class="progress-bar"><span class="sr-only"></span>
				</div>
			</div>
            </div>  
            <div class="layui-col-md12">
                <div id="shangchuan"></div><div id ="jd"></div>
            </div>
            <div class="layui-col-md12">
                <table class="layui-table"  id="filelist" style="table-layout:fixed">
                    <thead>
                        <tr>
                            <th width="200px" style="color:white">文件名</th>
                            <th width="50px" style="color:white">大小</th>
                            <th width="50px" style="color:white">上传状态</th>
                        </tr>
                    </thead>
                    <tbody id="filebody"></tbody>
                </table>
            </div>
    </div>
</body>
<script type="text/javascript">
var readimagekey = "${readimagekey}";
$(function(){
	//支持的文件类型正则表达式
	var fileType = /\.(doc?x|xls?x|ppt?x|txt|jpg|zip|rar)$/i;
	var random; 
	//文件上传前触发事件
    $('#fileupload').bind('fileuploadsubmit', function (e, data) {
        data.formData = { "random": random,"readimagekey":readimagekey};  //如果需要额外添加参数可以在这里添加
    });
	$("#fileupload").fileupload({
		url: 'uploaddcm.do',
		dataType: 'json',
		add: function(e, data) { //add表示在选择文件时判断格式是否正确
			random = "upload_"+Math.floor(Math.random() * 1000) + '_' + Math.floor(Math.random() * 1000)  ;
			var html = ""
                html+='<tr>'
                html+='<td width="200px" style="color:white">'+ data.files[0].name +'</td>'
                html+='<td width="50px" style="color:white">'+ (data.files[0].size/1014).toFixed(1) +'kb</td>'
                html+='<td width="50px" style="color:white" id="'+random+'">等待上传</td>'
                html+='</tr>'
            $("#filebody").append(html);
            data.submit();
		},
		done: function(e, data) {   //done为文件上传成功需要做的事情
			if(data.result.code==0){
				$('#'+data.result.random).text('上传成功');
			}else{
				$('#'+data.result.random).html('<span id="uploadError" style="color: red;">上传失败</span>');
			}
		},
		progressall: function(e, data) {  //进度条显示
			$('#progress').show();
			var progress = parseInt(data.loaded / data.total * 100, 10); 
			$('#progress .progress-bar span').css('width', progress + '%');
			if(progress==100){
			var a = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
            parent.layui.table.reload('binglitable', {
                page: {
                  curr: 1 //重新从第 1 页开始
                }
                ,where:{
                    'readimagekey':"${readimagekey}"
                },
            });
			}
		}
	});
});
</script>
</html>