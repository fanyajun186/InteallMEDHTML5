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
		<div class="layui-row layui-col-space10">
			<div class="layui-col-md3">
			    <div style="display: block;border-radius: 5px;background-color:#30343e;border: 1px solid  #23262E ">
					<fieldset class="layui-elem-field">
						<legend style="border: none">报告模板</legend>
						<div class="layui-field-box">
							<div class="layui-row">
								<div class="layui-col-md12">
								    <div class="layui-form layui-row" style="text-align: center;">
								        <div class="layui-col-md6">
											<select id="reporttype" lay-filter="reporttype">
					
											</select>
										</div>
										<div class="layui-col-md6">
											<input type="radio" name="belong" lay-filter="belong" value="0" title="公用" checked="">
	                                        <input type="radio" name="belong" lay-filter="belong" value="1" title="私有">
										</div>
								    </div>
									
								</div>
								<div id="privateTool" class="layui-col-md12" style="margin-top: 10px;display: none">
                                    <img alt="" src="../img/1366-03.png" id="addFolder" title="新增文件夹">
                                    <img alt="" src="../img/1366-04.png" id="addModel" title="新增模板">
                                    <img alt="" src="../img/1366-02.png" id="editModel" title="编辑">
                                    <img alt="" src="../img/1366-01.png" id="delModel" title="删除">
                                </div>
								<div class="layui-col-md12">
								    <ul id="treeDemo" class="ztree" style="background-color: rgba(255,255,255,.03);border: 1px solid  #23262E"></ul>
								</div>
							</div>
						</div>
					</fieldset>
				</div>
			</div>
			<div class="layui-col-md9">
				<div class="layui-field-box" style="display: block;border-radius: 5px;background-color:#30343e;border: 1px solid  #23262E ">
				<fieldset id="menu_func_div" class="layui-elem-field">
                    <div class="layui-form-item layui-col-md12" id="toolButton"
                        style="text-align: left;display: none">
                        <button class="layui-btn layui-btn-normal" lay-submit="" lay-filter="demo1">保存</button>
                    </div>
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
								<input type="text" class="layui-input" name="jianchamingcheng" value="${reportWithBLOBs.checkName}" >
							</div>
						</div>
						<div class="layui-form-item layui-form-text">
							<label class="layui-form-label">检查方法</label>
							<div class=" layui-col-md10">
								<textarea name="jianchafangfa" id="jianchafangfa" class="layui-textarea layui-col-md10">${reportWithBLOBs.checkMethod}</textarea>
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
								<textarea id="jianchajianyi" name="jianchajianyi" class="layui-textarea layui-col-md10">${reportWithBLOBs.advise}</textarea>
							</div>
						</div>
					</form>
				</fieldset>
				</div>

			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
    var reportId = "${reportWithBLOBs.reportKey}";
    var layedit_zhenduanjieguo;
    var layedit_jianchasuojian;
    var chushenrenLogin;
    var chushenrenName;
    var fushenrenLogin;
    var fushenrenName;
    var clicked_id = null;//左侧被点击的id
    var clicked_type = true;//左侧被点击的类型  是不是父节点
    var layedit;
    var setting = {
        data: {
            simpleData: {
                enable: true,
                idKey: "reportTypeKey",
                pIdKey: "parentCode",
            }
        },
        callback: {
            onClick: onClick
        }
    };
    function onClick(e,treeId, treeNode) {
        var zTree = $.fn.zTree.getZTreeObj("treeDemo");
        zTree.expandNode(treeNode);
        clicked_id = treeNode.reportTypeKey;
        clicked_type = true; 
        if(!treeNode.isParent){
            clicked_type = false; 
            $.ajax({
                url:"../reportTemplate/getById.do?id="+treeNode.reportTypeKey,
                type:"get",
                success:function(data){
                    data = eval("("+data+")");
                    /* $('#zhenduanjieguo').html(data.ReportTemplate.diagnosisResult);
                    $('#jianchasuojian').html(data.ReportTemplate.checkView); */
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
                   var checkView = $("#jianchasuojian").val()+data.ReportTemplate.checkView;
                   layedit.setContent(layedit_jianchasuojian,checkView)
                   layedit_zhenduanjieguo= layedit.build('zhenduanjieguo',{
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
                  var  diagnosisResult = $("#zhenduanjieguo").val()+data.ReportTemplate.diagnosisResult;
                  layedit.setContent(layedit_zhenduanjieguo,diagnosisResult)
                  
                }
                
            });
        }
    }
	layui.config({
	    base : "../js/"
	}).use([ 'form', 'layedit','common', 'laydate' ], function() {
		layedit = layui.layedit;
		var common = layui.common;
		var form = layui.form;
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
	form.on('submit(demo1)', function(data){
        var userSaveLoading = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        data.field.jianchafangfa = layedit.getContent(layedit_jianchafangfa)
        data.field.sikaofenxi = layedit.getContent(layedit_sikaofenxi)
        data.field.jianchajianyi = layedit.getContent(layedit_jianchajianyi)
        data.field.jianchasuojian = layedit.getContent(layedit_jianchasuojian)
        data.field.zhenduanjieguo = layedit.getContent(layedit_zhenduanjieguo)
        data.field.collection_key = "${collectionKey}";
        data.field.reportId = reportId;
        console.log(data.field)
        $.ajax({
              url : 'collectionReportSave.do',
              type : 'post',
              async: false,
              data :data.field,
              success : function(data) {
                  data = eval("("+data+")");
                  if(data.code == 0){
                      top.layer.close(userSaveLoading);
                      common.cmsLaySucMsg("保存成功");
                      reportId = data.reportId;
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
    $(document).ready(function(){
    	$.ajax({
            url:"../modality/getAll.do",
            type:"get",
            success:function(data){
                var json = eval("("+data+")");
                var list = json.ModalityList
                var html = "<option value='''>请选择报告模板类型</option>";
                for(var i=0;i<list.length;i++){
                    html+= "<option value="+list[i].modalityKey+">"+list[i].modalityName+"</option>"
                }
                $("#reporttype").append(html);
                $("#reporttype").val("${modalityCode}");
                form.render('select');
                refreshTree();
            }
            
        });
    	$("#toolButton").show();
    });
    form.on('select(reporttype)', function(data){
        $.ajax({
            url:"../reporttype/getAll.do?reporttype="+data.value+"&belong="+$('input:radio[name=belong]:checked').val(),
            type:"get",
            success:function(data){
                var json = eval("("+data+")");
                $.fn.zTree.init($("#treeDemo"), setting, json.ReportTypeList);
            }
            
        });
    });
    form.on('radio(belong)', function(data){
    	refreshTree();
    }); 
    $("#addFolder").click(function(){
    	if($("#reporttype").val()==""){
            common.cmsLayErrorMsg("请先选择报告模板类型");
            return;
        }
    	if(clicked_type==false){
    		common.cmsLayErrorMsg("当前选中的报告模板，不能新建文件夹，请选择文件夹。");
    		return;
    	}
    	var url = "../reporttype/redirectAddFolder.do?clicked_id="+clicked_id+"&reporttype="+$("#reporttype").val();
        common.cmsLayOpen('新建模板文件夹',url,'350px','230px');
    })
    $("#addModel").click(function(){
    	if($("#reporttype").val()==""){
            common.cmsLayErrorMsg("请先选择报告模板类型");
            return;
        }
        if(clicked_type==false){
            common.cmsLayErrorMsg("当前选中的报告模板，不能新建报告模板，请选择文件夹。");
            return;
        }
        var url = "../reporttype/redirectAddModel.do?clicked_id="+clicked_id+"&reporttype="+$("#reporttype").val();
        common.cmsLayOpen('新建报告模板',url,'500px','450px');
    })
    $("#editModel").click(function(){
    	if(clicked_id==null){
    		common.cmsLayErrorMsg("请选择要编辑的文件夹或者报告模板。");
            return;
    	}
    	if(clicked_type==false){
    		var url = "../reporttype/redirectEditModel.do?clicked_id="+clicked_id+"&reporttype="+$("#reporttype").val();
            common.cmsLayOpen('编辑报告模板',url,'500px','450px');
        }else{
        	var url = "../reporttype/redirectEditFolder.do?clicked_id="+clicked_id+"&reporttype="+$("#reporttype").val();
            common.cmsLayOpen('编辑模板文件夹',url,'350px','230px');
        }
    })
    $("#delModel").click(function(){
    	if(clicked_id==null){
            common.cmsLayErrorMsg("请选择要删除的文件夹或者报告模板。");
            return;
        }
        if(clicked_type==false){
            var url = "../reporttype/delModel.do?clicked_id="+clicked_id+"&reporttype="+$("#reporttype").val();
            top.layer.confirm("是否删除选中数据", {
                title: "提示",
                resize: false,
                btn: ['确定', '取消'],
                btnAlign: 'c',
                anim:1,
                icon: 3
            },function(index){
            	 $.ajax({
                  url : url,
                  type : 'get',
                  async: false,
                  success : function(data) {
                      data = eval("("+data+")");
                      if(data.code == 0){
                          top.layer.close(index);
                          common.cmsLaySucMsg("保存成功");
                            //执行重载
                            refreshTree();
                      }else{
                          top.layer.close(index);
                          common.cmsLayErrorMsg(data.msg);
                      }
                  },error:function(data){
                      top.layer.close(index);
                  }
              });
            });
        }else{
        	
            var url = "../reporttype/delFolder.do?clicked_id="+clicked_id+"&reporttype="+$("#reporttype").val();
            top.layer.confirm("是否删除选中数据", {
                title: "提示",
                resize: false,
                btn: ['确定', '取消'],
                btnAlign: 'c',
                anim:1,
                icon: 3
            },function(index){
            	$.ajax({
                    url:"../reporttype/hasChildren.do",
                    type:"post",
                    async:true,
                    cache:true,
                    data:{
                        "clicked_id":clicked_id
                    },
                    success:function(data){
                    	data = eval("("+data+")");
                        if(data.code==0){
                            if(data.i>0){
                            	common.cmsLayErrorMsg("请先删除子目录!");
                            }else{
                            	$.ajax({
                                    url : url,
                                    type : 'get',
                                    async: false,
                                    success : function(data) {
                                        data = eval("("+data+")");
                                        if(data.code == 0){
                                            top.layer.close(index);
                                            common.cmsLaySucMsg("保存成功");
                                              //执行重载
                                              refreshTree();
                                        }else{
                                            top.layer.close(index);
                                            common.cmsLayErrorMsg(data.msg);
                                        }
                                    },error:function(data){
                                        top.layer.close(index);
                                    }
                                });
                            }
                            
                        }else{
                        	common.cmsLayErrorMsg("查询是否含有子文件夹时出错，请稍后重试或者联系管理员!");	
                        } 
                    }
                })
                
            });
        }
    })
});
function refreshTree(){
		clicked_id=null;
	    clicked_type = true
	    if($('input:radio[name=belong]:checked').val()==1){
	        $("#privateTool").show();
	    }else{
	        $("#privateTool").hide();
	    }
	    
	    if($("#reporttype").val()==""){
	        return;
	    }
	    $.ajax({
	         url:"../reporttype/getAll.do?reporttype="+$("#reporttype").val()+"&belong="+$('input:radio[name=belong]:checked').val(),
	         type:"get",
	         success:function(data){
	             var json = eval("("+data+")");
	             $.fn.zTree.init($("#treeDemo"), setting, json.ReportTypeList);
	         }
	         
	     });
	}
</script>
</html>
