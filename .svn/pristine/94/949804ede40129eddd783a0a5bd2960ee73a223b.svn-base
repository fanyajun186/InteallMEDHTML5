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
	href="plugins/layui/css/layui.css" media="all" />
<link rel="stylesheet" type="text/css" href="css/faqi_baogao.css"
	media="all">
<script type="text/javascript" src="js/data.js"></script>

<script type="text/javascript" src="plugins/layui/layui.js"></script>
</head>
<body class="body_bg">
	<div class="layui-fluid">
		<div class="layui-row layui-col-md12 layui-col-space1"
			style="text-align: center;">
			<div class="layui-btn-group">
				<button class="layui-btn layui-btn-blackn layui-btn-xl"
					id="daichushen">待初审</button>
				<button class="layui-btn layui-btn-blackn layui-btn-xl"
					id="daifushen">待终审</button>
				<button class="layui-btn layui-btn-blackn layui-btn-xl"
					id="yiwancheng">已完成</button>
			</div>
		</div>
		<div class="layui-col-md12 layui-input-bg">

			<div class="layui-field-box">
				<div class="layui-col-md12 layui-form layui-col-space1"
					style="margin-top: 10px">
					<div
						style="margin-top: 0px; margin-bottom: 15px; margin-left: 0px;">
						<div class="layui-input-inline">
							<input type="text" name="username" id="username"
								placeholder="患者姓名" class="layui-input">
						</div>
						<div class="layui-input-inline">
							<div class="layui-input-inline">
								<input type="text" class="layui-input" id="studytime"
									placeholder="检查时间">
							</div>
						</div>
						<div class="layui-input-inline" style="text-align: center;">
							<select id="modality" name="modality">
								<option value="">检查类型</option>
							</select>
						</div>
						<div class="layui-input-inline" style="text-align: center;">
							<select id="bodyName" name="bodyName">
								<option value="">检查部位</option>
							</select>
						</div>
						<div class="layui-input-inline" style="text-align: center;">
							<select id=huizhenState name="huizhenState"
								lay-filter="huizhenState">
								<option value="">会诊状态</option>
								<option value="1">未提交</option>
								<option value="2">被驳回</option>
								<option value="3">待会诊</option>
								<option value="4">初审驳回</option>
								<option value="5">终审驳回</option>
								<option value="6">待初审</option>
								<option value="7">待终审</option>
								<option value="8">已完成</option>
							</select>
						</div>
						<div class="layui-input-inline" style="text-align: center;">
							<input type="radio" name="sex" value="M" title="男"> <input
								type="radio" name="sex" value="F" title="女">
						</div>
						<div class="layui-input-inline" style="width: 145px;">
							<button class="layui-btn layui-btn-normal" id="query">查询</button>
							<button class="layui-btn layui-btn-normal" id="reset">重置</button>
						</div>
						<div class="layui-input-inline" style="width: 145px;">
							<a id="querymore"
								style="padding: 6px; float: right; color: #fff; cursor: pointer;">更多条件<i
								class="layui-icon" id="more" icontype="1">&#xe602;</i></a>
						</div>
					</div>
					<div id="moreSearch"
						style="margin-top: 0px; margin-left: 10px; display: none;">
						<div class="layui-col-md12 layui-form-item">
							<div class="layui-col-md6 layui-input-inline">
								<input type="text" class="layui-input" id="uploadtime"
									placeholder="上传时间" style="width: 84%; margin-top: -1%;">
							</div>
							<button class="layui-btn  layui-btn-xs selectCommonTime"
								title="本周" value="1">本周</button>
							<button class="layui-btn  layui-btn-xs selectCommonTime"
								title="上周" value="2">上周</button>
							<button class="layui-btn  layui-btn-xs selectCommonTime"
								title="本月" value="3">本月</button>
							<button class="layui-btn  layui-btn-xs selectCommonTime"
								title="上月" value="4">上月</button>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="layui-row layui-col-md12 layui-col-space1">
			<table class="layui-table" style="margin: 0;" id="bingli"
				lay-filter="bingli"></table>
			<script type="text/html" id="barbingli">
                <a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="yingxiang">影像</a>
                <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="xiangqing">详情</a>
                <a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="baogao">报告</a>
            </script>
			<script id="studyTime" type="text/html">
                {{# var a = new Date(d.studyTime || new Date);
                    var year = a.getFullYear();

                    var month = a.getMonth() < 9 ? "0" + (0 | a.getMonth()+1) : a.getMonth()+1;
                    var day = a.getDate() < 10 ? "0" + (0 | a.getDate()) : a.getDate();
                    return year + "-" + month+ "-" + day 
                                    
                    }} 
            </script>
			<script id="commitTime" type="text/html">
                {{# 
                    if(d.commitTime==""){
                        return "";
                    }
                    var a = new Date(d.commitTime);
                   
                    var year = a.getFullYear();

                    var month = a.getMonth() + 1 < 10 ? "0" + (a.getMonth() + 1) : a.getMonth() + 1;
                    var date = a.getDate() < 10 ? "0" + a.getDate() : a.getDate();
                    var hour = a.getHours()< 10 ? "0" + a.getHours() : a.getHours();
                    var minute = a.getMinutes()< 10 ? "0" + a.getMinutes() : a.getMinutes();
                    var second = a.getSeconds()< 10 ? "0" + a.getSeconds() : a.getSeconds();
                    return year + "-" + month + "-" + date+" "+hour+":"+minute+":"+second;
                                    
                    }} 
            </script>
			<script id="state" type="text/html">
                {{# if(d.state === '1'){ }}
                    <span style="color: #F581B1;">未提交</span>
                {{#  } else if(d.state === '2'){ }}
                    <span style="color: red;">驳回</span>
                {{#  } else if(d.state === '3'){ }}
                    <span style="color: #F581B1;">待会诊</span>
                {{#  } else if(d.state === '4'){ }}
                    <span style="color: #F581B1;">初审驳回</span>
                {{#  } else if(d.state === '5'){ }}
                    <span style="color: #F581B1;">终审驳回</span>
                {{#  } else if(d.state === '6'){ }}
                    <span style="color: #F581B1;">待初审</span>
                {{#  } else if(d.state === '7'){ }}
                    <span style="color: #F581B1;">待终审</span>
                {{#  } else if(d.state === '8'){ }}
                    <span style="color: #F581B1;">已完成</span>
                {{#  } }}   
                                    
            </script>
		</div>

	</div>
	<script>
		layui
				.config({
					base : "js/"
				})
				.use(
						[ 'element', 'layer', 'common', 'table', 'laydate' ],
						function() {
							var layer = layui.layer;
							var laydate = layui.laydate;
							var table = layui.table;
							var element = layui.element;
							var common = layui.common;
							var form = layui.form;
							var $ = layui.jquery;

							$(function() {
								$("#daichushen").addClass("daibanTimefocus");
								state = "6,6"
								initTable(state);
							})

							function initTable(state) {

								table
										.render({
											elem : '#bingli',
											url : 'consultation/getRecordByPrimaryAudit.do',
											method : 'post',
											where : {
												'username' : $("#username")
														.val(),
												'studytime' : $("#studytime")
														.val(),
												'modality' : $("#modality")
														.val(),
												'sex' : $(
														'input:radio[name=sex]:checked')
														.val(),
												'state' : state,//初始化的时候查询的是待会诊，初审驳回，终审驳回的记录
												'bodyName' : $("#bodyName")
														.val(),//会诊部位
												'huizhenState' : $(
														"#huizhenState").val(),//会诊状态
												'uploadtime' : $("#uploadtime")
														.val()
											//上传时间
											},
											cols : [ [ {
												type : 'numbers'
											}, {
												field : 'patientName',
												width : 150,
												title : '患者姓名',
												sort : true
											}, {
												field : 'sex',
												width : 80,
												title : '性别',
												sort : true
											}, {
												field : 'age',
												width : 80,
												title : '年龄',
												sort : true
											}, {
												field : 'bodypartName',
												title : '检查部位',
												width : 100,
												sort : true
											}, {
												field : 'modalityCode',
												width : 100,
												title : '图像类型',
												sort : true
											}, {
												field : 'studyTime',
												width : 120,
												title : '检查时间',
												templet : '#studyTime',
												sort : true
											}, {
												field : 'state',
												title : '会诊状态',
												width : 100,
												templet : '#state',
												sort : true
											}, {
												field : 'commitTime',
												width : 150,
												title : '会诊申请时间',
												sort : true,
												templet : '#commitTime'
											}, {
												field : 'commitTargetPerson',
												width : 100,
												title : '专家',
												sort : true
											}, {
												field : 'commitRequest',
												title : '病情描述',
												width : 250,
												sort : true
											}, {
												fixed : 'right',
												width : 200,
												title : '操作',
												align : 'center',
												toolbar : '#barbingli'
											} ] ],
											page : true,
											id : "binglitable"
										});
								table
										.on(
												'tool(bingli)',
												function(obj) {
													var data = obj.data;
													if (obj.event === 'yingxiang') {
														window
																.open("http://123.206.29.163:8080/?stuuid="
																		+ data.studyinstanceuid
																		+ "&user=cc&pswd=dd")
													} else if (obj.event === 'baogao') {
														window
																.open("report/redirectReportCheck.do?caseId="
																		+ data.caseId
																		+ "&stuuid="
																		+ data.studyinstanceuid
																		+ "&reportId="
																		+ data.reportId)
													} else if (obj.event === 'xiangqing') {
														window
																.open("consultation/getBycaseId.do?imageId="
																		+ data.imageId
																		+ "&caseId="
																		+ data.caseId
																		+ "&stuuid="
																		+ data.studyinstanceuid)
													}
												});
							}

							laydate.render({
								elem : '#studytime'
								/* ,type: 'datetime' */
								,
								range : "~"
							});
							laydate.render({
								elem : '#uploadtime',
								range : "~"
							});
							$("#query").click(function() {
								initTable(state);

							});
							$("#reset")
									.click(
											function() {
												$("#username").val("");
												$("#studytime").val("");
												$("#modality").val("");
												$('input:radio[name=sex]')[0].checked = false;
												$('input:radio[name=sex]')[1].checked = false;
												$("#bodyName").val("");
												$("#huizhenState").val("");
												$("#uploadtime").val("");
												form.render('select'); //刷新select选择框渲染
												form.render('radio');

												var nowTabId = $(
														".daibanTimefocus")
														.attr("id");
												var state = '';
												if (nowTabId == 'daichushen') {
													state = '6,6';
												} else if (nowTabId == 'daifushen') {
													state = '7,7';
												} else {
													state = '8,8'
												}
												initTable(state);

											});

							$("#daichushen").click(
									function() {
										var _this = $(this);
										$(".daibanTimefocus").removeClass(
												"daibanTimefocus");
										_this.addClass("daibanTimefocus");
										state = '6,6'
										initTable(state);

									})
							$("#daifushen")
									.click(
											function() {
												var _this = $(this);
												$(".daibanTimefocus")
														.removeClass(
																"daibanTimefocus");
												_this
														.addClass("daibanTimefocus");
												state = '7,7'
												table
														.render({
															elem : '#bingli',
															url : 'consultation/getRecordByUltimateAudit.do',
															method : 'post',
															where : {
																'username' : $(
																		"#username")
																		.val(),
																'studytime' : $(
																		"#studytime")
																		.val(),
																'modality' : $(
																		"#modality")
																		.val(),
																'sex' : $(
																		'input:radio[name=sex]:checked')
																		.val(),
																'state' : state
															//初始化的时候查询的是待会诊，初审驳回，终审驳回的记录
															},
															cols : [ [
																	{
																		type : 'numbers'
																	},
																	{
																		field : 'patientName',
																		width : 150,
																		title : '患者姓名',
																		sort : true
																	},
																	{
																		field : 'sex',
																		width : 80,
																		title : '性别',
																		sort : true
																	},
																	{
																		field : 'age',
																		width : 80,
																		title : '年龄',
																		sort : true
																	},
																	{
																		field : 'bodypartName',
																		title : '检查部位',
																		width : 100,
																		sort : true
																	},
																	{
																		field : 'modalityCode',
																		width : 100,
																		title : '图像类型',
																		sort : true
																	},
																	{
																		field : 'studyTime',
																		width : 120,
																		title : '检查时间',
																		templet : '#studyTime',
																		sort : true
																	},
																	{
																		field : 'state',
																		title : '会诊状态',
																		width : 100,
																		templet : '#state',
																		sort : true
																	},
																	{
																		field : 'commitTime',
																		width : 150,
																		title : '会诊申请时间',
																		sort : true,
																		templet : '#commitTime'
																	},
																	{
																		field : 'commitTargetPerson',
																		width : 100,
																		title : '专家',
																		sort : true
																	},
																	{
																		field : 'commitRequest',
																		title : '病情描述',
																		width : 250,
																		sort : true
																	},
																	{
																		fixed : 'right',
																		width : 300,
																		title : '操作',
																		align : 'left',
																		toolbar : '#barbingli'
																	} ] ],
															page : true,
															id : "binglitable"
														});
											})
							$("#yiwancheng").click(
									function() {
										var _this = $(this);
										$(".daibanTimefocus").removeClass(
												"daibanTimefocus");
										_this.addClass("daibanTimefocus");
										state = '8,8'
										initTable(state);
									})
							//会诊部位
							$(function() {
								$
										.ajax({
											url : 'consultation/getbodyPartName.do',
											type : 'GET',
											dataType : "json",
											success : function(data) {
												var list = data.list;
												var html = '';
												for (var i = 0; i < list.length; i++) {
													html += '<option value="'+list[i]+'">'
															+ list[i]
															+ '</option>'
												}
												$("#bodyName").append(html);
												form.render('select');
											}
										})
							})
							//检查类型
							$(function() {
								$
										.ajax({
											url : 'consultation/getmodalityName.do',
											type : 'GET',
											dataType : "json",
											success : function(data) {
												var modalitylist = data.modalitylist;
												var html = '';
												for (var i = 0; i < modalitylist.length; i++) {
													html += '<option value="'+modalitylist[i]+'">'
															+ modalitylist[i]
															+ '</option>'
												}
												$("#modality").append(html);
												form.render('select');
											}
										})
							})
							//更多查询条件
							$("#querymore").click(function() {
								var icontype = $("#more").attr("icontype");
								if (icontype == 1) {
									$("#more").html("&#xe61a;");
									$("#more").attr("icontype", "2");
									$("#moreSearch").css("display", "inline");
								} else {
									$("#more").html("&#xe602;");
									$("#more").attr("icontype", "1");
									$("#moreSearch").css("display", "none");
								}
							})

							$(".selectCommonTime")
									.click(
											function() {

												var _this = $(this);
												var value = _this.attr("value");
												switch (value) {
												case "1":
													$("#uploadtime")
															.val(
																	formatDate(MrYangUtil
																			.getCurrentWeek()[0])
																			+ " ~ "
																			+ formatDate(MrYangUtil
																					.getCurrentWeek()[1]));
													break;
												case "2":
													$("#uploadtime")
															.val(
																	formatDate(MrYangUtil
																			.getPreviousWeek()[0])
																			+ " ~ "
																			+ formatDate(MrYangUtil
																					.getPreviousWeek()[1]));
													break;
												case "3":
													$("#uploadtime")
															.val(
																	formatDate(MrYangUtil
																			.getCurrentMonth()[0])
																			+ " ~ "
																			+ formatDate(MrYangUtil
																					.getCurrentMonth()[1]));
													break;
												case "4":
													$("#uploadtime")
															.val(
																	formatDate(MrYangUtil
																			.getPreviousMonth()[0])
																			+ " ~ "
																			+ formatDate(MrYangUtil
																					.getPreviousMonth()[1]));
													break;
												}
												//initTable(0);
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

