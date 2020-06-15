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
	<link rel="stylesheet" type="text/css" href="../css/readimage.css" media="all">
	<link rel="stylesheet" type="text/css" href="../css/faqi_baogao.css">

	<script type="text/javascript" src="../plugins/layui/layui.js"></script>
	<script type="text/javascript" src="../js/jquery-1.4.4.min.js"></script>
</head>
<body class="body_bg">
	<div class="layui-form-item" style="margin-top: 15px">
		<button type="button" class="layui-btn layui-btn-normal" id="testList">选择文件</button>
		<button type="button" class="layui-btn layui-btn-normal" id="testListAction">开始上传</button>
	</div>
	<div class="layui-form-item">
		
		<div class="layui-upload-list">
			<table class="layui-table">
				<thead>
					<tr>
						<th>文件名</th>
						<th>大小</th>
						<th>状态</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody id="demoList"></tbody>
			</table>
		</div>
		
	</div>
</body>
<script>
layui.use('upload', function(){
	  var $ = layui.jquery
	  ,upload = layui.upload;
	  var readimagekey = "${readimagekey}";
	  var loading;
	  //多文件列表示例
	  var demoListView = $('#demoList')
	  ,uploadListIns = upload.render({
	    elem: '#testList'
	    ,url: 'uploaddcm.do'
	    ,accept: 'file'
	    ,multiple: true
	    ,auto: false
	    ,exts: 'dcm'//文件类型dcm
	    ,bindAction: '#testListAction'
    	,data: {"readimagekey":readimagekey} //可选项。额外的参数，如：{id: 123, abc: 'xxx'}	
    	,before: function(obj) { //obj参数包含的信息，跟 choose回调完全一致，可参见上文。

        }
	    ,choose: function(obj){   
	      var files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
	      //读取本地文件
	      obj.preview(function(index, file, result){
	        var tr = $(['<tr id="upload-'+ index +'">'
	          ,'<td>'+ file.name +'</td>'
	          ,'<td>'+ (file.size/1014).toFixed(1) +'kb</td>'
	          ,'<td>等待上传</td>'
	          ,'<td>'
	            ,'<button class="layui-btn layui-btn-mini demo-reload layui-hide">重传</button>'
	            ,'<button class="layui-btn layui-btn-mini layui-btn-danger demo-delete">删除</button>'
	          ,'</td>'
	        ,'</tr>'].join(''));
	        
	        //单个重传
	        tr.find('.demo-reload').on('click', function(){
	          obj.upload(index, file);
	        });
	        
	        //删除
	        tr.find('.demo-delete').on('click', function(){
	          delete files[index]; //删除对应的文件
	          tr.remove();
	          uploadListIns.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
	        });
	        
	        demoListView.append(tr);
	      });
	    }
	    ,done: function(data, index, upload){
	      if(data.code == 0){ //上传成功
	        var tr = demoListView.find('tr#upload-'+ index)
	        ,tds = tr.children();
	        tds.eq(2).html('<span style="color: #5FB878;">上传成功</span>');
	        tds.eq(3).html(''); //清空操作
	        
	        var a = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
            parent.layui.table.reload('binglitable', {
		        page: {
		          curr: 1 //重新从第 1 页开始
		        }
		        ,where:{
		        	'readimagekey':readimagekey
				},
		    });
	        
	        return delete this.files[index]; //删除文件队列已经上传成功的文件
	        
	      }
	      this.error(index, upload);
	    }
	    ,error: function(index, upload){
	      var tr = demoListView.find('tr#upload-'+ index)
	      ,tds = tr.children();
	      tds.eq(2).html('<span style="color: #FF5722;">上传失败</span>');
	      tds.eq(3).find('.demo-reload').removeClass('layui-hide'); //显示重传
	    }
	  });
	  
	  
	});
</script>
</html>
