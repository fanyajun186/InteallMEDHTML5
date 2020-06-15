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
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
    <meta name="apple-mobile-web-app-status-bar-style" content="black" />
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="format-detection" content="telephone=no" />
    
    <link rel="stylesheet" type="text/css" href="../plugins/layui/css/layui.css" media="all" />

    <link rel="stylesheet" type="text/css" href="../css/faqi_baogao.css">
    
    <script type="text/javascript" src="../js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="../plugins/layui/layui.js"></script>
    <style type="text/css">
        .checkbox{
            vertical-align:-2px;
        }
    </style>
</head>
<body class="body_bg">
<form  style="margin-top: 10px">
	<div class="layui-form-item">
		<label class="layui-form-label">评审标准</label>
		<div class="layui-input-block">
			<input type="checkbox" name="xuanxiang" value="1"  class="checkbox">不合格：检查部位错误。
		</div>
		<div class="layui-input-block">
			<input type="checkbox" name="xuanxiang" value="2"  class="checkbox">不合格：多部位检查时部位不全。
		</div>
        <div class="layui-input-block">
			<input type="checkbox" name="xuanxiang" value="3"  class="checkbox">受检者一般信息标识有误或不齐全。
		</div>
		<div class="layui-input-block">
            <input type="checkbox" name="xuanxiang" value="4"  class="checkbox">检查前准备不充分。
        </div>
        <div class="layui-input-block">
            <input type="checkbox" name="xuanxiang" value="5"  class="checkbox">图像有可避免的各类伪影（金属伪影、呼吸伪影、其它伪影）。
        </div>
        <div class="layui-input-block">
            <input type="checkbox" name="xuanxiang" value="6"  class="checkbox">扫描野选择不当。
        </div>
        <div class="layui-input-block">
            <input type="checkbox" name="xuanxiang" value="7"  class="checkbox">检查范围未按规范要求。
        </div>
        <div class="layui-input-block">
            <input type="checkbox" name="xuanxiang" value="8"  class="checkbox">检查参数（序列）选择不当、序列不全。
        </div>
        <div class="layui-input-block">
            <input type="checkbox" name="xuanxiang" value="9"  class="checkbox">窗技术选择不当，图像对比差。
        </div>
        <div class="layui-input-block">
            <input type="checkbox" name="xuanxiang" value="10"  class="checkbox">图像位置不规范，左右不对称、歪斜。
        </div>
        <div class="layui-input-block">
            <input type="checkbox" name="xuanxiang" value="11"  class="checkbox">造影剂剂量、浓度、流率选择不当。
        </div>
        <div class="layui-input-block">
            <input type="checkbox" name="xuanxiang" value="12"  class="checkbox">增强扫描时相不当或延迟不充分。
        </div>
        <div class="layui-input-block">
            <input type="checkbox" name="xuanxiang" value="13"  class="checkbox">检查步骤不规范，不能良好显示病变。
        </div>
        <div class="layui-input-block">
            <input type="checkbox" name="xuanxiang" value="14"  class="checkbox">后处理图像不能良好显示病变或不符临床要求。
        </div>
        <div class="layui-input-block">
            <input type="checkbox" name="xuanxiang" value="15"  class="checkbox">胶片排版顺序、布局或图像大小不规范、不恰当。
        </div>
        <div class="layui-input-block">
            <input type="checkbox" name="xuanxiang" value="16"  class="checkbox">图像（包括后处理图像）未及时发送至PACS。
        </div>
	</div>
	<div class="layui-form-item">
        <label class="layui-form-label">具体说明</label>
        <div class="layui-input-block">
            <textarea id="jutishuoming" name="jutishuoming" style="width: 90%" placeholder="具体说明" class="layui-textarea layui-input"></textarea>
        </div>
    </div>
	<div class="layui-form-item">
        <label class="layui-form-label">评审结果</label>
        <div class="layui-input-block">
            <input type="text" style="width: 200px" name="pingshenjieguo" id="pingshenjieguo" readonly="readonly" class="layui-input" value="优秀">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
        </div>
    </div>
</form>
</body>
<script type="text/javascript">
var commentWithBLOBs="${commentWithBLOBs}";
var number1=0;
var number2=0;
layui.config({
    base : "../js/"
}).use(['common','form', 'layedit','upload','table', 'laydate'], function(){
    var form = layui.form
    ,layer = layui.layer
    ,layedit = layui.layedit
    ,laydate = layui.laydate
    ,table = layui.table
    ,common = layui.common;
    var check_number = "${commentWithBLOBs.imageCommentItem}";
    var imageCommentResult = "${commentWithBLOBs.imageCommentResult}";
    var imageCommentContent = "${commentWithBLOBs.imageCommentContent}";
    if(check_number!=""){
    	var item = check_number.split(",")
        for(var i=0;i<item.length;i++){
            $("input[name='xuanxiang'][value='"+item[i]+"']").attr("checked", true);
            if(item[i]==1||item[i]==2){
                number1++;
            }else{
                number2++;
            }
        }
    }
    
    $("#pingshenjieguo").val(imageCommentResult)
    $("#jutishuoming").val(imageCommentContent)
    
    
    $('[type="checkbox"]').click(function(){
    	if($(this).is(':checked')){
    		if($(this).val()==1||$(this).val()==2){
    			number1++;
    		}else{
    			number2++;
    		}
    	}else{
    		if($(this).val()==1||$(this).val()==2){
    			number1--;
            }else{
            	number2--;
            }
    	}
    	if(number1>0){
    		$("#pingshenjieguo").val("不合格");
    	}else{
    		if(number2>=3){
    			$("#pingshenjieguo").val("不合格")
            }else if(number2==2){
            	$("#pingshenjieguo").val("一般")
            }else if(number2==1){
            	$("#pingshenjieguo").val("良好")
            }else{
            	$("#pingshenjieguo").val("优秀")
            }
    	}
    	
    })
    //监听提交
    form.on('submit(demo1)', function(data){
    	var check_number ="";
    	$('input:checkbox[name=xuanxiang]:checked').each(function(k){
            if(k == 0){
            	check_number = $(this).val();
            }else{
            	check_number += ','+$(this).val();
            }
        })
    	data.field.check_number = check_number;
    	data.field.jutishuoming = $("#jutishuoming").val();
    	data.field.pingshenjieguo = $("#pingshenjieguo").val();
    	data.field.caseId = "${caseId}";
    	data.field.commentKey = "${commentWithBLOBs.commentKey}";
        
    	var userSaveLoading = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
    	$.ajax({
            url : 'saveorupdate.do',
            type : 'post',
            async: false,
            data :data.field,
            success : function(data) {
                data = eval("("+data+")");
                if(data.code == 0){
                    top.layer.close(userSaveLoading);
                    common.cmsLaySucMsg("保存成功");
                }else{
                    top.layer.close(userSaveLoading);
                    common.cmsLayErrorMsg(data.msg);
                }
            },error:function(data){
                top.layer.close(index);
            }
        });
        return false; 
    });
});
</script>
</html>