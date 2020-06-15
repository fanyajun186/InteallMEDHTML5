<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
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
<link rel="stylesheet" href="../css/zTreeStyle/demo.css" type="text/css">
<link rel="stylesheet" href="../css/zTreeStyle/zTreeStyle.css" type="text/css">
<link rel="stylesheet" type="text/css" href="../css/faqi_baogao.css">

<script type="text/javascript" src="../js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="../js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="../js/jquery.ztree.excheck.js"></script>
<script type="text/javascript" src="../plugins/layui/layui.js"></script>
<script type="text/javascript" src="../plugins/layui/layui.js"></script>
<style type="text/css" media="print">
/*页眉页脚设置*/
    @page
    {
        size: auto;   /* auto is the initial value */
        margin: 0mm 10mm 0mm 10mm;  /* this affects the margin in the printer settings 最关键的还是这个*/
    }
    body
    {
        background-color:#FFFFFF;
        margin: 0mm;  /* this affects the margin on the content before sending to printer */
    }
</style>
</head>
<body style="background:#fff; color: #000000;">
    <div class="layui-fluid">
        <div class="layui-col-md12">
        		<div class="layui-form-item layui-col-md12" style="text-align: left;">
                        <button class="layui-btn layui-btn-normal" onclick="doPrint()">打印</button>
                 </div>
                <div class="layui-field-box">
                    <!--startprint-->
                    <form class="layui-form layui-col-md12" style="width: 759px; height: 50px;">
	                    <div class="layui-form-item layui-col-md12" style="margin-top: 65px;">
	                    		<br>
	                    	<h1 style="text-align: center;">湖北省抗癌联盟协作医院</h1>
	                    	<h1 style="text-align: center;">会诊报告</h1>
	                    	
	                    </div>
                        <div class="layui-form-item layui-col-md12" style="margin-top: 60px;">
                            <label class="layui-form-label" style="width: 119px">患者姓名</label>
                            <div class="layui-input-inline">
                                <input type="text" style="color: #000000;" class="layui-input" readonly="readonly" name="patientname" value="${medicalRecord.patientName}">
                            </div>
                            <label class="layui-form-label" style="width: 119px">患者性别</label>
                            <div class="layui-input-inline">
                                <input type="text" style="color: #000000;" class="layui-input" readonly="readonly" name="sex" value="${medicalRecord.sex}">
                            </div>
                        </div>
                        <div class="layui-form-item layui-col-md12">
                            <label class="layui-form-label" style="width: 119px">患者年龄</label>
                            <div class="layui-input-inline">
                                <input type="text" style="color: #000000;" class="layui-input" readonly="readonly" name="age" value="${medicalRecord.age}">
                            </div>
                            <label class="layui-form-label" style="width: 119px">检查编号</label>
                            <div class="layui-input-inline">
                                <input type="text" style="color: #000000;" class="layui-input" readonly="readonly" value="${medicalRecord.studyId}">
                            </div>
                        </div>
                        <div class="layui-form-item layui-col-md12">
                            <label class="layui-form-label" style="width: 119px">病人编号</label>
                            <div class="layui-input-inline">
                                <input type="text" style="color: #000000;" class="layui-input" readonly="readonly" name="patientKey" id="patientKey" value="${medicalRecord.patientKey}">
                            </div>
                            <label class="layui-form-label" style="width: 119px">检查部位</label>
                            <div class="layui-input-inline">
                                <input type="text" style="color: #000000;" class="layui-input" readonly="readonly" name="bodypartName" value="${medicalRecord.bodypartName}">
                            </div>
                        </div>
                        <div class="layui-form-item layui-col-md12">
                            <label class="layui-form-label" style="width: 119px">检查名称</label>
                            <div class="layui-input-inline">
                                <input class="layui-input" style="color: #000000;" name="jianchamingcheng" id="jianchamingcheng" readonly="readonly" value="${reportWithBLOBs.checkName}">
                            </div>
                        </div>
                        <div class="layui-form-item layui-form-text">
                        	<div style="float: left">
                            		<label class="layui-form-label" style="width: 119px">检查方法</label>
                            </div>
                           	<div style="border: 1px solid;float: left;width: 540px;min-height:100px;">
                              		 ${reportWithBLOBs.checkMethod} 
                            </div>
                        </div>
                        <div class="layui-form-item layui-form-text">
                        	<div style="float: left">
                           			 <label class="layui-form-label" style="width: 119px">检查所见</label>
                            </div>
                            <div style="border: 1px solid;float: left;width: 540px;min-height:200px;">
                            		${reportWithBLOBs.checkView}
                            </div>
                        </div>
                        <div class="layui-form-item layui-form-text">
                        	<div style="float: left">
                            		<label class="layui-form-label" style="width: 119px">诊断结果</label>
                            </div>
                            <div style="border: 1px solid;float: left;width: 540px;min-height:200px;">
                            		${reportWithBLOBs.diagnosisResult}
                            </div>
                        </div>
                        <div class="layui-form-item layui-form-text" style="display:none;">
                            <label class="layui-form-label">是否审核</label>
                            <div class="layui-input-inline">
                                <input type="checkbox" name="close" lay-skin="switch" lay-filter="switchTest" lay-text="是|否" disabled> 
                            </div>
                        </div>
                        <div  id="newDiv1" class="layui-form-item layui-form-text">
                            <label class="layui-form-label" style="width: 119px">报告医师</label>
                            <div class="layui-input-inline" style="width: 141px;">
                                <label class="layui-form-label" style="text-align: left;width: 160px"  name="fushenren" id="fushenren" >${consultationWithBLOBs.acceptPersonName}</label>
                            </div>
                            <label class="layui-form-label" style="width: 119px">电子签名</label>
                            <div class="layui-input-inline" style="width: 141px;">
                            	<c:if test="${empty qmUrl}">
                            		<div style="margin-top: 4px;">暂无电子签名</div> 
                            	</c:if>
                            	<c:if test="${!empty qmUrl}">
	                            	<img alt="" src="../accessory/yulanaccessory.do?path=${qmUrl}" width="100px;" height="60px;">
                            	</c:if>
                            </div>
                        </div>
                        <div id="newDiv" class="layui-form-item layui-form-text" style="display: none;">
                        	<label class="layui-form-label" style="width: 119px">审核医师</label>
                            <div class="layui-input-inline" style="width: 141px;">
                                <label class="layui-form-label" style="text-align: left;width: 160px" name="chushenren" id="chushenren" >${consultationWithBLOBs.primaryAuditName}</label>
                            </div>
                            <label class="layui-form-label" style="width: 119px">复核医师</label>
                            <div class="layui-input-inline" style="width: 141px;">
                                <label class="layui-form-label" style="text-align: left;width: 160px" name="fushenren" id="fushenren" >${consultationWithBLOBs.ultimateAuditName}</label>
                            </div>
                        </div>
                    </form>
					<!--endprint-->  
                </div>
        </div>
    </div>
<script type="text/javascript">
var stuuid="${stuuid}";
    layui.config({
        base : "../js/"
    }).use([ 'form', 'layedit','common', 'laydate' ], function() {
        var layedit = layui.layedit;
        var common = layui.common;
        var form = layui.form;
        //判断是否审核这个是否展开
        if('${consultationWithBLOBs.primaryAuditLogin}'!=''){
            layui.jquery('input[name="close"]').attr('checked', 'checked');  //改变开关为 开
            //重新渲染
            form.render('checkbox');
            $("#newDiv").show();
        }
    }); //建立编辑器
    if("${consultationWithBLOBs.state}"==6){
    	$("#chushen").show();
    	$("#chushen").click(function(){
    		var html = '<div class="layui-form" style="padding-right:20px;padding-top:5px">'+
	    		
	            '<div class="layui-form-item layui-form-text">'+
	                  '<label class="layui-form-label" style="color:black">理由</label>'+
	                  '<div class="layui-input-block">'+
	                  '<textarea id="rejectReason" name="rejectReason"  style="color:black" placeholder="理由" class="layui-textarea layui-input"></textarea>'+
	                  '</div>'+
	             '</div>'+
	             '</div>'
	        layer.open({
	            type: 1 //此处以iframe举例
	            ,title: '审核结果'
	            ,area: ['500px', '300px']
	            ,shade: 0
	            ,maxmin: true
	            ,content: html
	            ,btn: ['通过', '不通过'] //只是为了演示
	            ,btnAlign: 'c' //按钮居中
	            ,yes: function(index, layero){
	            	$.ajax({
                        url : '../consultation/primaryAudit.do',
                        type : 'POST',
                        async: false,
                        data:{"caseId":"${consultationWithBLOBs.caseId}","rejectReason":$("#rejectReason").val(),"ispass":true},
                        success : function(data) {
                            data = eval("("+data+")");
                            if(data.code == 0){
                            	layer.closeAll();
                            	common.cmsLaySucMsg("报告初审通过，报告页面即将关闭！");
                            	setTimeout(function(){
                            		window.opener.location.reload(); 
                                    window.close();
                            	},3000);
                                
                            }else{
                                common.cmsLayErrorMsg(data.msg);
                            }
                        },error:function(data){
                        }
                    });
	                
	            }
	            ,btn2: function(index, layero){
	            	$.ajax({
                        url : '../consultation/primaryAudit.do',
                        type : 'POST',
                        async: false,
                        data:{"caseId":"${consultationWithBLOBs.caseId}","rejectReason":$("#rejectReason").val(),"ispass":false},
                        success : function(data) {
                            data = eval("("+data+")");
                            if(data.code == 0){
                            	
                                common.cmsLaySucMsg("报告终审不通过，报告页面即将关闭！");
                                setTimeout(function(){
                                    window.opener.location.reload(); 
                                    window.close();
                                },3000);
                            }else{
                                common.cmsLayErrorMsg(data.msg);
                            }
                        },error:function(data){
                        }
                    });
	            }
	            ,zIndex: layer.zIndex //重点1
	            ,success: function(layero){
	              layer.setTop(layero); //重点2
	            }
            });
    	})
    }
    if("${consultationWithBLOBs.state}"==7){
        $("#zhongshen").show();
        $("#zhongshen").click(function(){
            var html = '<div class="layui-form" style="padding-right:20px;padding-top:5px">'+
                
                '<div class="layui-form-item layui-form-text">'+
                      '<label class="layui-form-label" style="color:black">理由</label>'+
                      '<div class="layui-input-block">'+
                      '<textarea id="rejectReason" name="rejectReason"  style="color:black" placeholder="理由" class="layui-textarea layui-input"></textarea>'+
                      '</div>'+
                 '</div>'+
                 '</div>'
            layer.open({
                type: 1 //此处以iframe举例
                ,title: '审核结果'
                ,area: ['500px', '300px']
                ,shade: 0
                ,maxmin: true
                ,content: html
                ,btn: ['通过', '不通过'] //只是为了演示
                ,btnAlign: 'c' //按钮居中
                ,yes: function(index, layero){
                    $.ajax({
                        url : '../consultation/ultimateAudit.do',
                        type : 'POST',
                        async: false,
                        data:{"caseId":"${consultationWithBLOBs.caseId}","rejectReason":$("#rejectReason").val(),"ispass":true},
                        success : function(data) {
                            data = eval("("+data+")");
                            if(data.code == 0){
                            	layer.closeAll();
                            	common.cmsLaySucMsg("报告终审通过，报告页面即将关闭！");
                            	setTimeout(function(){
                                    window.opener.location.reload(); 
                                    window.close();
                                },3000);
                            }else{
                                common.cmsLayErrorMsg(data.msg);
                            }
                        },error:function(data){
                        }
                    });
                    
                }
                ,btn2: function(index, layero){
                    $.ajax({
                        url : '../consultation/ultimateAudit.do',
                        type : 'POST',
                        async: false,
                        data:{"caseId":"${consultationWithBLOBs.caseId}","rejectReason":$("#rejectReason").val(),"ispass":false},
                        success : function(data) {
                            data = eval("("+data+")");
                            if(data.code == 0){
                            	common.cmsLaySucMsg("报告终审不通过，报告页面即将关闭！");
                                setTimeout(function(){
                                    window.opener.location.reload(); 
                                    window.close();
                                },3000);
                            }else{
                                common.cmsLayErrorMsg(data.msg);
                            }
                        },error:function(data){
                        }
                    });
                }
                ,zIndex: layer.zIndex //重点1
                ,success: function(layero){
                  layer.setTop(layero); //重点2
                }
            });
        })
    };
function doPrint() {  
	 $.ajax({
         url : '../medicallog/save.do',
         type : 'POST',
         async: false,
         data:{"stuuid":"${stuuid}"},
         success : function(data) {
             data = eval("("+data+")");
             if(data.code == 1){
             	common.cmsLaySucMsg("插入日志信息错误");
             }
         }
     	});
         if(getExplorer() == "Chrome"){
             pagesetup_null();
         }
         bdhtml=window.document.body.innerHTML;      
         sprnstr="<!--startprint-->";      
         eprnstr="<!--endprint-->";      
         prnhtml=bdhtml.substr(bdhtml.indexOf(sprnstr)+17);      
         prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr)); 
         window.document.body.innerHTML=prnhtml;
         window.print();
}
function pagesetup_null(){
    var hkey_root,hkey_path,hkey_key;
    hkey_root="HKEY_CURRENT_USER";
    hkey_path="\\Software\\Microsoft\\Internet Explorer\\PageSetup\\";
    try{
        var RegWsh = new ActiveXObject("WScript.Shell");
        console.log(RegWsh);
        hkey_key="header";
        RegWsh.RegWrite(hkey_root+hkey_path+hkey_key,"");
        hkey_key="footer";
        RegWsh.RegWrite(hkey_root+hkey_path+hkey_key,"");
    }catch(e){
    }
}

function getExplorer() {
    var explorer = window.navigator.userAgent ;
    //ie 
    if (explorer.indexOf("MSIE") >= 0) {
        return "IE";
    }
    //firefox 
    else if (explorer.indexOf("Firefox") >= 0) {
        return "Firefox";
    }
    //Chrome
    else if(explorer.indexOf("Chrome") >= 0){
        return "Chrome";
    }
    //Opera
    else if(explorer.indexOf("Opera") >= 0){
        return "Opera";
    }
    //Safari
    else if(explorer.indexOf("Safari") >= 0){
        return "Safari";
    }
}

</script>
</html>
