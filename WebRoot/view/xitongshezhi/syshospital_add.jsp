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
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="format-detection" content="telephone=no" />
<link rel="stylesheet" type="text/css"
	href="../plugins/layui/css/layui.css" media="all" />
<link rel="stylesheet" type="text/css" href="../css/faqi_baogao.css" media="all">
<script type="text/javascript" src="../js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="../plugins/layui/layui.js"></script>
<script type="text/javascript" src="../plugins/address/address.js"></script>
<script type="text/javascript" src="../plugins/address/province.js"></script>
</head>
<body class="body_bg">
 
<form class="layui-form" style="margin-top: 10px" action="">
  <div class="layui-form-item">
    <div class="layui-inline">
      <label class="layui-form-label"><spite style="color: red">*</spite>医院名称</label>
      <div class="layui-input-inline">
        <input type="text" name="hospName" id="hospName"  maxlength="30"  placeholder="医院名称"  lay-verify="required" autocomplete="off" class="layui-input">
      </div>
    </div>
    <div class="layui-inline">
      <label class="layui-form-label">医院简称</label>
      <div class="layui-input-inline">
        <input type="text" name="hospSname" id="hospSname" maxlength="30" placeholder="医院简称" autocomplete="off" class="layui-input">
      </div>
    </div>
  </div>
  
  <div class="layui-form-item">
    <div class="layui-inline">
      <label class="layui-form-label">医院类别</label>
      <div class="layui-input-inline">
        <select name="hospClass" id="hospClass">
	        <option value="">--请选择--</option>
	        <option value="1">综合医院</option>
	        <option value="2">专科医院</option>
	        <option value="3">教学医院</option>
	        <option value="4">诊所</option>
        </select>
      </div>
    </div>
    <div class="layui-inline">
      <label class="layui-form-label">医院等级</label>
      <div class="layui-input-inline">
        <select name="hospLevel" id="hospLevel" >
	        <option value="">--请选择--</option>
	        <option value="1">一级甲等</option>
	        <option value="2">一级乙等</option>
	        <option value="3">一级丙等</option>
	        <option value="4">二级甲等</option>
	        <option value="5">二级乙等</option>
	        <option value="6">二级甲等</option>
	        <option value="7">三级甲等</option>
	        <option value="8">三级乙等</option>
	        <option value="9">三级丙等</option>
        </select>
      </div>
    </div>
  </div>
  
  <div class="layui-form-item">
    <div class="layui-inline">
      <label class="layui-form-label">联系人</label>
      <div class="layui-input-inline">
        <input type="text" name="hospUser" id="hospUser" maxlength="20" placeholder="联系人" autocomplete="off" class="layui-input">
      </div>
    </div>
    <div class="layui-inline">
      <label class="layui-form-label">联系电话</label>
      <div class="layui-input-inline">
        <input type="text" name="hospTel" id="hospTel" maxlength="11" placeholder="联系电话" autocomplete="off" class="layui-input">
      </div>
    </div>
    
  </div>
  
  <div class="layui-form-item">
  	<div class="layui-inline">
	  	<label class="layui-form-label">手机号码</label>
	    <div class="layui-input-inline">
	      <input type="text" name="hospMobile" id="hospMobile" placeholder="手机号码"  maxlength="11" autocomplete="off" class="layui-input">
	    </div>
    </div>
    <div class="layui-inline">
	    <label class="layui-form-label">邮政编码</label>
	    <div class="layui-input-inline">
	      <input type="text" name="hospPostCode" id="hospPostCode" maxlength="6" placeholder="邮政编码" autocomplete="off" class="layui-input">
	    </div>
	</div>
  </div>
  <div class="layui-form-item">
  	<div class="layui-inline">
	  	<label class="layui-form-label">邮箱地址</label>
	    <div class="layui-input-inline">
	      <input type="text" name="hospEmail" id="hospEmail" placeholder="邮箱地址" maxlength="30" autocomplete="off" class="layui-input">
	    </div>
    </div>
    <div class="layui-inline">
	    <label class="layui-form-label">接收转诊</label>
	    <div class="layui-input-inline">
	      <input type="radio" name="isCenter" value="1" title="是" checked="">
	      <input type="radio" name="isCenter" value="0" title="否">
	    </div>
	</div>
  </div>
  
  
  
  <div class="layui-form-item">
    <label class="layui-form-label">地址</label>
    <div class="layui-input-inline" style="width: 165px">
    	<select id="hospAddPr" name="hospAddPr" lay-filter="hospAddPr"></select>   
    </div>
    <div class="layui-input-inline" style="width: 165px">
    	<select id="hospAddCy" name="hospAddCy" lay-filter="hospAddCy"></select>  
    </div>
    <div class="layui-input-inline" style="width: 165px">
    	<select id="hospAddQx" name="hospAddQx" lay-filter="hospAddQx"></select> 
    </div>
  </div>
  <div class="layui-form-item">
  	<div class="layui-input-block" style="width: 516px;margin-left: 102px">
  		<input type="text" name="hospAdd" id="hospAdd" placeholder="详细地址" maxlength="50" autocomplete="off" class="layui-input">
  	</div>
  </div>
  <div class="layui-form-item">
  	<label class="layui-form-label">医院介绍</label>
    <div class="layui-input-inline" style="width: 514px">
      <textarea placeholder="医院介绍" name="hospAbout" id="hospAbout" class="layui-textarea layui-input"></textarea>
    </div>
  </div>
  
  <div class="layui-form-item">
    <div class="layui-input-block">
      <button class="layui-btn layui-btn-normal" lay-submit="" lay-filter="demo1">立即提交</button>
      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
    </div>
  </div>
</form>
          
<script>

var defaults = {
    s1: 'hospAddPr',
    s2: 'hospAddCy',
    s3: 'hospAddQx',
    v1: null,
    v2: null,
    v3: null
};


layui.config({
    base : "../js/"
}).use(['common','form', 'layedit', 'laydate'], function(){
  var form = layui.form
  ,layer = layui.layer
  ,layedit = layui.layedit
  ,laydate = layui.laydate
  ,common = layui.common;
  
  //监听提交
  form.on('submit(demo1)', function(data){
	
      var userSaveLoading = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
      $.ajax({
          url : 'save.do',
          type : 'post',
          async: false,
          data : {"json":JSON.stringify(data.field)},
          success : function(data) {
        	  data = eval("("+data+")");
              if(data.code == 0){
                  top.layer.close(userSaveLoading);
                  common.cmsLaySucMsg("保存成功");
                  var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                  parent.layer.close(index); //再执行关闭                        //刷新父页面
                  parent.location.reload();
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

</body>
</html>