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

    <link rel="stylesheet" type="text/css" href="plugins/layui/css/layui.css" media="all" />
    <link rel="stylesheet" type="text/css" href="css/faqi_baogao.css" media="all">

    <script type="text/javascript" src="plugins/layui/layui.js"></script>
    <script type="text/javascript" src="js/data.js"></script>
</head>
<body class="body_bg">
    <div class="layui-fluid">
        
        <div class="layui-col-md12 layui-input-bg">

            <div class="layui-field-box">
                <div class="layui-col-md12 layui-form layui-col-space1">
                    <div class="layui-col-md12 layui-form-item">
                        
                        <div class="layui-col-md6 layui-input-inline">
                                <input type="text" class="layui-input" id="selecttime"
                                    placeholder="时间">
                        </div>
                        <div class="layui-input-inline" style="text-align: center;">
                                <input type="radio" name="dayOrMon" lay-filter="dayOrMon" value="day" title="按天" checked="checked">
                                <input type="radio" name="dayOrMon" lay-filter="dayOrMon" value="mon" title="按月">
                            </div>
                        <div class="layui-col-md6 layui-input-inline" style="width: 145px;">
                            <button class="layui-btn layui-btn-normal" id="query">查询</button>
                            <button class="layui-btn layui-btn-normal" id="reset">重置</button>
                        </div>
                    </div>
                    <div class="layui-col-md12 layui-form-item">
                        <button class="layui-btn  layui-btn-xs selectCommonTime" title="本周" value="1">本周</button>
                        <button class="layui-btn  layui-btn-xs selectCommonTime" title="上周" value="2">上周</button>
                        <button class="layui-btn  layui-btn-xs selectCommonTime" title="本月" value="3">本月</button>
                        <button class="layui-btn  layui-btn-xs selectCommonTime" title="上月" value="4">上月</button>
                        <button class="layui-btn  layui-btn-xs selectCommonTime" title="今年" value="5">今年</button>
                        <button class="layui-btn  layui-btn-xs selectCommonTime" title="去年" value="6">去年</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="layui-row layui-col-md12 layui-col-space1">
            <table class="layui-table" style="margin: 0;" id="countTable" lay-filter="countTable"></table>
            
        </div>

    </div>
    <script>
    var state;
    layui.config({
        base : "js/"
    }).use([ 'element', 'layer','form', 'table', 'laydate','common' ], function() {
            var layer = layui.layer;
            var laydate = layui.laydate;
            var table = layui.table;
            var element = layui.element;
            var form = layui.form;
            var common = layui.common;
            var $ = layui.jquery;
            
            function initTable(){
                if($("#selecttime").val()==""||$("#selecttime").val()==null){
                	common.cmsLayErrorMsg("请先选择时间范围！");
                	return;
                }
                table.render({
                    elem: '#countTable'
                    ,url:'count/getCountByUser.do'
                    ,method :'post'
                    ,where:{
                        'selecttime':$("#selecttime").val(),
                        'dayOrMon':$("input[name='dayOrMon']:checked").val()
                    }
                    ,cols: [
                    	[
	                       {type:'numbers',rowspan:"2"}
	                      ,{field:'date', width:110, title: '时间',rowspan:"2",align: 'center'}
	                      ,{field:'huiZhenShenQingCount', width:100, title: '会诊申请', rowspan:"2",align: 'center'}
	                      ,{field:'xiangYingHuiZhenCount', width:100, title: '响应会诊',rowspan:"2",align: 'center'}
	                      ,{align: 'center',title: '报告审核',colspan:"2",align: 'center'}
	                      ,{align: 'center',title: '读片会',colspan:"2",align: 'center'}
	                      ,{align: 'center',title: '视频教学',colspan:"2",align: 'center'}
	                      ,{field: 'qunTaoLunCount', width:100, title: '群讨论',rowspan:"2",align: 'center'}
	                    ],
	                    [
	                       {field:'chuShenCount', title: '初审', width: 100,align: 'center'}
                          ,{field:'fuShenCount', title: '复审',width:100,align: 'center'}
                          ,{field:'createReadImageCount', title: '创建', width: 100,align: 'center'}
                          ,{field:'enterReadImageCount', title: '参与',width:100,align: 'center'}
                          ,{field:'createVideoCount', title: '创建', width: 100,align: 'center'}
                          ,{field:'enterVideoCount', title: '参与',width:100,align: 'center'}
	                    ],
	                    
                    ]
                    ,page: false
                });
            }   
            laydate.render({
                elem : '#selecttime'
                /* ,type: 'datetime' */
                ,range : "~"
            });
            form.on('radio(dayOrMon)', function(data){
            	initTable();
            });  


            $("#query").click(function(){
                initTable();

            });
            $("#reset").click(function(){
                $("#selecttime").val("");
            });
            
            $(".selectCommonTime").click(function(){
            	            	
            	var _this = $(this);
                var value = _this.attr("value");
                switch(value){
                    case "1" :
                    	$("#selecttime").val(formatDate(MrYangUtil.getCurrentWeek()[0]) + " ~ " + formatDate(MrYangUtil.getCurrentWeek()[1]) );
                    	 break;
                    case "2" :
                    	$("#selecttime").val(formatDate(MrYangUtil.getPreviousWeek()[0]) + " ~ " + formatDate(MrYangUtil.getPreviousWeek()[1]) );
                    	 break;
                    case "3" :
                        $("#selecttime").val( formatDate(MrYangUtil.getCurrentMonth()[0]) + " ~ " + formatDate(MrYangUtil.getCurrentMonth()[1]));
                         break;
                    case "4" :
                        $("#selecttime").val( formatDate(MrYangUtil.getPreviousMonth()[0]) + " ~ " + formatDate(MrYangUtil.getPreviousMonth()[1]));
                         break;
                    case "5" :
                        $("#selecttime").val( formatDate(MrYangUtil.getCurrentYear()[0]) + " ~ " + formatDate(MrYangUtil.getCurrentYear()[1]));
                         break;
                    case "6" :
                        $("#selecttime").val( formatDate(MrYangUtil.getPreviousYear()[0]) + " ~ " + formatDate(MrYangUtil.getPreviousYear()[1]));
                         break;
                }
                initTable();
            })
            //格式化日期
            function formatDate(date) {
			    var myyear = date.getFullYear();
			    var mymonth = date.getMonth() + 1;
			    var myweekday = date.getDate();
			    if (mymonth < 10) {
			        mymonth = "0" + mymonth;
			    }
			    if (myweekday < 10) {
			        myweekday = "0" + myweekday;
			    }
			    return (myyear + "-" + mymonth + "-" + myweekday);
			}
            

        });
    </script>
</body>
</html>

