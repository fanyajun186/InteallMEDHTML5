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
<link rel="stylesheet" href="../css/zTreeStyle/demo.css" type="text/css">
<link rel="stylesheet" href="../css/zTreeStyle/zTreeStyle.css" type="text/css">
<link rel="stylesheet" type="text/css" href="../css/faqi_baogao.css">
<script type="text/javascript" src="../js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="../js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="../js/jquery.ztree.excheck.js"></script>
<script type="text/javascript" src="../plugins/layui/layui.js"></script>
</head>
<body style="background: #393D49; color: #fff">
    <div class="layui-fluid">
        <div class="layui-col-md12">

                <div class="layui-field-box">
                <fieldset id="menu_func_div" class="layui-elem-field">
                   
                
                    <form class="layui-form layui-col-md12">
                        <div class="layui-form-item layui-col-md12">
                            <label class="layui-form-label">患者姓名</label>
                            <div class="layui-input-inline">
                                <input type="text" class="layui-input" readonly="readonly" name="patientname" value="${medicalRecord.patientName}">
                            </div>
                            <label class="layui-form-label">性别</label>
                            <div class="layui-input-inline">
                                <input type="text" class="layui-input" readonly="readonly" name="sex" value="${medicalRecord.sex}">
                            </div>
                            <label class="layui-form-label">年龄</label>
                            <div class="layui-input-inline">
                                <input type="text" class="layui-input" readonly="readonly" name="age" value="${medicalRecord.age}">
                            </div>

                        </div>

                        <div class="layui-form-item layui-col-md12">
                            <label class="layui-form-label">检查编号</label>
                            <div class="layui-input-inline">
                                <input type="text" class="layui-input" readonly="readonly" value="${medicalRecord.studyId}">
                            </div>
                            <label class="layui-form-label">病人编号</label>
                            <div class="layui-input-inline">
                                <input type="text" class="layui-input" readonly="readonly" name="patientKey" id="patientKey" value="${medicalRecord.patientKey}">
                            </div>

                            <label class="layui-form-label">检查部位</label>
                            <div class="layui-input-inline">
                                <input type="text" class="layui-input" readonly="readonly" name="bodypartName" value="${medicalRecord.bodypartName}">
                            </div>

                        </div>

                        <div class="layui-form-item layui-col-md12">
                            <label class="layui-form-label">检查名称</label>
                            <div class="layui-input-inline">
                                <input type="text" class="layui-input" name="jianchamingcheng" value="${reportWithBLOBs.checkName}">
                            </div>
                        </div>

                        <div class="layui-form-item layui-form-text">
                            <label class="layui-form-label">检查方法</label>
                            <div class=" layui-col-md10">
                                <textarea name="jianchafangfa" id="jianchafangfa" readonly="readonly" class="layui-textarea layui-col-md10">${reportWithBLOBs.checkMethod}</textarea>
                            </div>
                        </div>

                        <div class="layui-form-item layui-form-text">
                            <label class="layui-form-label">检查所见</label>
                            <div class=" layui-col-md10">
                                <textarea id="jianchasuojian" name="jianchasuojian" class="layui-textarea layui-col-md10">${reportWithBLOBs.checkView}</textarea>
                            </div>
                        </div>

                        <div class="layui-form-item layui-form-text">
                            <label class="layui-form-label">诊断结果</label>
                            <div class=" layui-col-md10">
                                <textarea id="zhenduanjieguo" name="zhenduanjieguo" class="layui-textarea layui-col-md10">${reportWithBLOBs.diagnosisResult}</textarea>
                            </div>
                        </div>

                        <div class="layui-form-item layui-form-text">
                            <label class="layui-form-label">思考分析</label>
                            <div class=" layui-col-md10">
                                <textarea id="sikaofenxi" name="sikaofenxi" class="layui-textarea layui-col-md10">${reportWithBLOBs.think}</textarea>
                            </div>
                        </div>


                        <div class="layui-form-item layui-form-text">
                            <label class="layui-form-label">检查建议</label>
                            <div class=" layui-col-md10">
                                <textarea id="jianchajianyi" name="jianchajianyi" disabled="disabled" class="layui-textarea layui-col-md10">${reportWithBLOBs.advise}</textarea>
                            </div>
                        </div>

                        <div class="layui-form-item layui-form-text">
                            <label class="layui-form-label">是否审核</label>
                            <div class="layui-input-inline">
                                <input type="checkbox" name="close" lay-skin="switch" 
                                    lay-filter="switchTest" lay-text="是|否" disabled>
                            </div>
                        </div>
                        <div  id="newDiv" class="layui-form-item layui-form-text" style="display: none">
                            <label class="layui-form-label">初审人</label>
                            <div class="layui-input-inline">
                                <input type="text" name="chushenren" id="chushenren" readonly="readonly" class="layui-input" value="${consultationWithBLOBs.primaryAuditName}"/>
                            </div>
                            <label class="layui-form-label">复审人</label>
                            <div class="layui-input-inline">
                                <input type="text" name="fushenren" id="fushenren" readonly="readonly" class="layui-input" value="${consultationWithBLOBs.ultimateAuditName}"/>
                            </div>
                        </div>


                    </form>
                </fieldset>
                </div>

        </div>
    </div>

</body>
<script type="text/javascript">

    var layedit_zhenduanjieguo;
    var layedit_jianchasuojian;
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
        
        var layedit_jianchafangfa = layedit.build('jianchafangfa',{
             height: 60 //设置编辑器高度
             ,background: "black" //设置编辑器高度
             ,tool: [
                  'strong' //加粗
                  ,'italic' //斜体
                  ,'underline' //下划线
                  ,'del' //删除线
                  ,'|' //分割线
                  ,'left' //左对齐
                  ,'center' //居中对齐
                  ,'right' //右对齐
                  ,'face' //表情
                ]
        }); //建立编辑器
        layedit_jianchasuojian = layedit.build('jianchasuojian',{
            height: 60 //设置编辑器高度
            ,background: "black" //设置编辑器高度
            ,tool: [
                 'strong' //加粗
                 ,'italic' //斜体
                 ,'underline' //下划线
                 ,'del' //删除线
                 ,'|' //分割线
                 ,'left' //左对齐
                 ,'center' //居中对齐
                 ,'right' //右对齐
                 ,'face' //表情
               ]
       }); //建立编辑器
       layedit_zhenduanjieguo = layedit.build('zhenduanjieguo',{
           height: 60 //设置编辑器高度
           ,background: "black" //设置编辑器高度
           ,tool: [
                'strong' //加粗
                ,'italic' //斜体
                ,'underline' //下划线
                ,'del' //删除线
                ,'|' //分割线
                ,'left' //左对齐
                ,'center' //居中对齐
                ,'right' //右对齐
                ,'face' //表情
              ]
      }); //建立编辑器
      var layedit_sikaofenxi = layedit.build('sikaofenxi',{
          height: 60 //设置编辑器高度
          ,background: "black" //设置编辑器高度
          ,tool: [
               'strong' //加粗
               ,'italic' //斜体
               ,'underline' //下划线
               ,'del' //删除线
               ,'|' //分割线
               ,'left' //左对齐
               ,'center' //居中对齐
               ,'right' //右对齐
               ,'face' //表情
             ]
     }); //建立编辑器
     var layedit_jianchajianyi = layedit.build('jianchajianyi',{
         height: 60 //设置编辑器高度
         ,background: "black" //设置编辑器高度
         ,tool: [
              'strong' //加粗
              ,'italic' //斜体
              ,'underline' //下划线
              ,'del' //删除线
              ,'|' //分割线
              ,'left' //左对齐
              ,'center' //居中对齐
              ,'right' //右对齐
              ,'face' //表情
            ]
    }); //建立编辑器
     
});
    
    
</script>
</html>
