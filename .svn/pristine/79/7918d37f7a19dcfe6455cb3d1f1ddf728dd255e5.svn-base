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
<script type="text/javascript" src="../js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="../plugins/layui/layui.js"></script>
</head>
<body class="body_bg">
	<div class="layui-col-md12" style="margin-top: 10px">

		<form action="" method="post" id="form-article-add" enctype="multipart/form-data">
			<div class="layui-col-md12">
			    <input hidden="hidden" name="stuuid" value="${stuuid}">
				<button type="button" class="layui-btn layui-btn-normal"
					id="choosefile">选择</button>
				<input type="file" name="fileField" multiple="multiple"
					id="fileField" style="display: none" accept=".jpg,.png,.jpeg,.pdf,.txt,.docx,.doc,.wps,.xls,.xlsx,.ppt,.pptx"/>
				
			</div>
			<div class="layui-col-md12" style="margin-top: 10px">
				<div class="layui-progress "
					lay-showpercent="true" lay-filter="progress">
					<div class="layui-progress-bar layui-bg-red" lay-percent="0%"></div>
				</div>
			</div>
			<div class="layui-col-md12">
				<table class="layui-table" id="filelist" style="table-layout:fixed">
					<thead>
						<tr>
							<th width="200px">文件名</th>
							<th width="50px">大小</th>
							<th width="50px">状态</th>
							<th width="50px">操作</th>
						</tr>
					</thead>
				</table>
			</div>
		</form>


	</div>



</body>
<script type="text/javascript">


layui.config({
    base : "../js/"
}).use(['common','form','element'], function(){
        var layer = layui.layer;
        var form = layui.form;
        var $ = layui.jquery;
        var common = layui.common;
        var element = layui.element;
        
        $("#choosefile").click(function(){
            $("#fileField").trigger("click");
        })
        $("#fileField").change(function(e){
            //e.currentTarget.files 是一个数组，如果支持多个文件，则需要遍历
            var files = e.currentTarget.files;
            var formData = new FormData($("#form-article-add")[0]);
            $.ajax({
                url: "../accessory/saveaccessory.do"
                ,type: "post"
                ,data: formData
                ,contentType: false 
                ,processData: false
                ,xhr: function(){ //获取ajaxSettings中的xhr对象，为它的upload属性绑定progress事件的处理函数    
                    myXhr = $.ajaxSettings.xhr();    
                    if(myXhr.upload){ //检查upload属性是否存在    
                        //绑定progress事件的回调函数    
                        myXhr.upload.addEventListener('progress',progressHandlingFunction, false);     
                    }    
                    return myXhr; //xhr对象返回给jQuery使用  
                }
                ,success: function(data){
                    data = eval("("+data+")");
                    if(data.code==0){
                    	parent.location.reload();
                        var html="";
                        for(var i=0;i<files.length;i++){
                            var isSuccess = false;
                            for(var j=0;j<data.fileNameList.length;j++){
                                if(data.fileNameList[i]==files[i].name){
                                    isSuccess = true;
                                }
                            }
                            
                            html+='<tr>'
                            html+='<td  style="width:200px;text-overflow: ellipsis; -moz-text-overflow: ellipsis;overflow:hidden;text-align:left; white-space: nowrap;  ">'+ files[i].name +'</td>'
                            html+='<td width="100px">'+ (files[i].size/1014).toFixed(1) +'kb</td>'
                            if(isSuccess == true){
                                html+='<td style="width:50px">上传成功</td>'
                                html+='<td style="width:100px"></td>'
                            }else{
                                html+='<td style="width:50px">上传失败</td>'
                                html+='<td style="width:50px">'
                                html+='<button type="button" title="从列表中移除" class="layui-btn layui-btn-mini layui-btn-danger demo-delete">删除</button>'
                                html+='</td>'
                            }
                            
                            html+='</tr>'
                        }
                        $("#filelist").append(html);
                        //删除
                        $("tr").find(".demo-delete").click(function(){
                            $(this).parents("tr").remove();
                        });
                        
                        
                    }else{
                        common.cmsLayErrorMsg(data.msg);
                    }
                }
            });
        });
        function progressHandlingFunction(e){  
            var curr=e.loaded;  
            var total=e.total;  
            process=(curr/total).toFixed(2)*100;  
            element.progress('progress', process+'%');
        }
})  
</script>
</html>