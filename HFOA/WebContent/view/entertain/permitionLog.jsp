<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>已审核招待明细列表</title>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/img/entertainImg/favicon.ico">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/CSS/jqueryui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/CSS/jqueryui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/CSS/jqueryui/demo/demo.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/CSS/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/CSS/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/CSS/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/CSS/PublicStyle.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/CSS/DataImport.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/CSS/main.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/CSS/UserManage.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/CSS/jquery.form.js"></script>
<script type="text/javascript">
	var es = "新建";
	//提交action 的url
	var url = "";
	var idd = 1;
	//返回json对象的长度
	function getJsonObjLength(jsonObj) {
		var Length = 0;
		for ( var item in jsonObj) {
			Length++;
		}
		return Length;
	}

	//格式化json
	function formatJson(json) {
		var jsobj = eval(json);
		return '{"total":' + jsobj.bingdings.length + ',"rows":' + json + "}";
	}

	// 给查询框赋初值
	function search() {
	 	var now = new Date();
        var nowd = new Date().Format("yyyy-MM-dd");

        var lastmonth = new Date;
        lastmonth.setDate(now.getDate() - 30);
        var ldate = lastmonth.Format("yyyy-MM-dd");
        
		$('#searchUser').dialog('open').dialog('setTitle', '查询');
		$("#adepartment").combobox("setText", "全部");
		$("#astarttime").textbox("setText", ldate);
		$("#aendtime").textbox("setText", nowd);
		$("#aentertainobject").combobox("setText", "全部");
	}
	// js格式化Date方法
	
	Date.prototype.Format = function (fmt) { //author: meizz 
	    var o = { 
	        "M+": this.getMonth() + 1, //月份 
	        "d+": this.getDate(), //日 
	        "h+": this.getHours(), //小时 
	        "m+": this.getMinutes(), //分 
	        "s+": this.getSeconds(), //秒 
	        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
	        "S": this.getMilliseconds() //毫秒 
	    };
	    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	    for (var k in o)
	    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	    return fmt;
	}
	
	//重写js方法
	/*
	Date.prototype.Format = function () {
		return this.getFullYear()+"-"+parseInt(this.getMonth()+1)+"-"+this.getDate();
	}
	*/
	
	//datagrid-save
	function save() {
		$('#fm').form('submit', {
			url : url,
			onSubmit : function() {
				return $(this).form('validate');
			},
			success : function(result) {
				var result = eval('(' + result + ')');
				if (result.success) {
					$('#dlg').dialog('close'); // close the dialog
					$('#llbinfo').datagrid('reload'); // reload the user data
					$.messager.show({
						title : 'Message',
						msg : '添加成功'
					});
				} else {
					$.messager.show({
						title : 'Error',
						msg : result.msg
					});
				}
			}
		});
	}
	
	$(function() {
		//$(param.llbid) 传递过来得履历本id
		//汉化 datagrid 翻页
		$("#llbinfo").datagrid({
			url : '${pageContext.request.contextPath}/entertain/wGetAllApproved',
			method : 'post',
			pageSize : 20,
			rownumbers : true,
			singleSelect : true,
			pagination : true,
			toolbar : [ {
				text : '查询',
				iconCls : 'icon-search',
				handler : function() {
					search();
				}

			}, '-', {
				text : '导出表单',
				iconCls : 'icon-llb2',
				handler : function() {
					exportExcel();
				}
			}],
			
			onLoadSuccess:function(data){  
				 $('.preScan').linkbutton({text:'查看明细',plain:true,iconCls:'icon-search'}); 
				 $('.preScan1').linkbutton({text:'凭证号登记',plain:true,iconCls:'icon-save'}); 
			}
		});

		var pager = $('#llbinfo').datagrid().datagrid('getPager');
		pager.pagination({
			beforePageText : "转到",
			afterPageText : "共{pages}页",
			displayMsg : '当前显示从{from}到{to}  共{total}记录',
			onBeforeRefresh : function(pageNumber, pageSize) {
				$(this).pagination('loading');

				$(this).pagination('loaded');
			}
		});

		// 确认按钮
		$("#btn_ok").click(
						function() {});
		// 取消按钮
		$("#btn_cancel").click(function() {
			$('#dlg').dialog('close')
		});
		// 上传取消按钮
		$("#uploadFileCancel").click(function() {
			document.getElementById('fileImport').value = null;
			$('#import-excel').window('close')
		});
		
		//查询用户
		$("#search_ok").click(
						function() {
							var param = "department="+ $("#adepartment").combobox("getText")
									+ "&manager="+ $("#amanager").textbox("getText").trim()
									+ "&startTime=" + $("#astarttime").textbox("getText")
									+ "&endTime="+ $("#aendtime").textbox("getText")
									+ "&entertainObject="+ $("#aentertainobject").combobox("getText").trim();
							$.post(
									"${pageContext.request.contextPath}/entertain/wGetSearchApproved",
									param, function(result) {
										$("#searchUser").dialog("close");
												$("#llbinfo").datagrid("loadData", result);
											});
						});

		$("#search_cancel").click(function() {
			$("#searchUser").dialog("close");

		});
		// 审核通过按钮
		$("#btn_ok").click(
			function() {
							var row = $('#llbinfo').datagrid('getSelected');
							var number = row.number;
							
							var paidTime = $("#upaidtime").textbox("getText").trim();
							var month = $("#uvouchernum2").textbox("getValue").trim();
							var num = $("#uvouchernum3").textbox("getText").trim();
							var year = $("#uvouchernum1").textbox("getValue").trim();
							
							
							
							var voucher = year+month+num;
							var pstvoucher = year+"年"+month+"月 "+"第"+num+"号";
							if(voucher.length==10){
								//alert("success");
								var param = "number="+ number + "&paidTime="+ paidTime +"&voucherNum="+ pstvoucher;		
								$.post(
										"${pageContext.request.contextPath}/register/insertAllVoucherNum",
										param, function(result) {
													$("#dlg").dialog("close");
													alert("事后登记已通过审核！");
													//window.location.reload(); 
													$("#llbinfo").datagrid({
														url : '${pageContext.request.contextPath}/entertain/wGetAllApproved',
														method : 'post',
														pageSize : 20,
														rownumbers : true,
														singleSelect : true,
														pagination : true,
														toolbar : [ {
															text : '查询',
															iconCls : 'icon-search',
															handler : function() {
																search();
															}

														}, '-', {
															text : '导出表单',
															iconCls : 'icon-llb2',
															handler : function() {
																exportExcel();
															}
														}],
														
														onLoadSuccess:function(data){  
															 $('.preScan').linkbutton({text:'查看明细',plain:true,iconCls:'icon-search'}); 
															 $('.preScan1').linkbutton({text:'凭证号登记',plain:true,iconCls:'icon-save'}); 
														}
													});

													var pager = $('#llbinfo').datagrid().datagrid('getPager');
													pager.pagination({
														beforePageText : "转到",
														afterPageText : "共{pages}页",
														displayMsg : '当前显示从{from}到{to}  共{total}记录',
														onBeforeRefresh : function(pageNumber, pageSize) {
															$(this).pagination('loading');

															$(this).pagination('loaded');
														}
													});
										});
							}
							else{
								alert("凭证号格式不正确！");
							}
						});

	});
	
	//导出表单
	function exportExcel(){

	        var rows = $('#llbinfo').datagrid('getRows'); //获取当前页的数据行  
	        var total = "";  
	        for (var i = 0; i < rows.length; i++) {  
	            total += rows[i]['number']+','; //获取指定列  
	        }  
	        //alert(total);
	        /* var param = "number="+total;
	        $.post(
					"${pageContext.request.contextPath}/entertain/exportExcel",
					param, function(result) {
			
							}); */
			window.open('${pageContext.request.contextPath}/entertain/exportExcel?number='+total);
		
		}
	
	
	function fixWidth(percent) {
		return ($(".mdiv").width() - 30) * percent;
	}
	
	// 操作列
	function operation(value,row,rowIndex){
		//console.info(row.id);
		return '<a class="preScan" href="#" iconCls="icon-search" onclick="detail('+ row.id +','+rowIndex+')">查看明细</a>';
	}
	function checkIn(value,row,rowIndex){
		return '<a class="preScan1" href="#" iconCls="icon-save" onclick="check('+ row.id +','+rowIndex+')">凭证号登记</a>';
	}
	// 显示明细表页面
	function detail(id,index) {
		var row = $('#llbinfo').datagrid('getData').rows[index];
		var playOfpath='${pageContext.request.contextPath}/view/entertain/detail.jsp?number='+row.number;//跳转到明细页面
		window.open(playOfpath,'newwindow','fullscreen=yes,channelmode=yes,resizable=yes,menubar=no,status=no,scrollbars=yes,');
	}
	// 凭证号登记
	function check(id,index) {
		var now = new Date();
	    var year = now.getFullYear();       //年
	    var month = now.getMonth() + 1;     //月
	    var day = now.getDate();            //日
		var clock = year + "-";
	    var cmonth="";
	    var cday="";
        
        if(month < 10){
        	cmonth="0"+month;
            clock += month+ "-";}//保持不变
        else{cmonth=month;
        clock += month + "-";}
        
        //if(day < 10)
            //clock += "0";
            
        clock += day;
        
		var row = $('#llbinfo').datagrid('getData').rows[index];
		$('#dlg').dialog('open').dialog('setTitle', '审核单号'+row.number);
		$("#upaidtime").textbox("setText", clock);
		$("#uvouchernum1").textbox("setValue", year);
		$("#uvouchernum2").textbox("setValue", cmonth);
	}
	$('#aentertainobject').combobox({
		filter: function(q, row){
			var opts = $(this).combobox('options');
			return row[opts.textField].indexOf(q) == 0;
		}
	});
</script>
</head>
<body>	<!-- 审核登记对话框 -->
	<div id="dlg" class="easyui-dialog"
		style="width: 400px; height: 200px; padding: 0px 0px" closed="true"
		buttons="#dlg-buttons">
		<table align="center" style="margin-top:20px;">
		<tr>
				<td><label>报销时间</label></td>
				<td><input id="upaidtime" class="easyui-textbox" style="width: 160px;"></input></td>
		</tr>
		<tr>
				<td><label>凭证号</label></td>
				<td><input id="uvouchernum1" class="easyui-textbox" style="width: 50px;"></input>年
				<input id="uvouchernum2" class="easyui-textbox" style="width: 35px;"></input>月
				&nbsp;第<input id="uvouchernum3" class="easyui-textbox" style="width: 40px;"></input>号</td>
		</tr>
		</table>
			<div style="text-align: center; bottom: 5px; margin-top: 20px;">
				<a href="#" id="btn_ok" class="easyui-linkbutton"
					data-options="iconCls:'icon-save'" style="width: 20%;">确定</a> 
				<a href="#" id="btn_cancel" class="easyui-linkbutton"
					data-options="iconCls:'icon-cancel'" style="width: 20%;">取消</a>
			</div>
	</div>
	<div class="mdiv" style="width:100%;height:94%;">
		<table id="llbinfo" class="easyui-datagrid" title="已审核招待明细列表"
			style="width: auto; height:100%;">
			<thead>
				<tr>
				 	<th
						data-options="field:'number',width:fixWidth(0.08),align:'center'">审批单号</th>
					<th 
						data-options="field:'department',width:fixWidth(0.08),align:'center'">部门</th>
					<th
						data-options="field:'manager',width:fixWidth(0.06),align:'center'">经办人</th>
					<th
						data-options="field:'approver',width:fixWidth(0.06),align:'center'">审批人</th>
					<th 
						data-options="field:'entertainObject',width:fixWidth(0.18),align:'center'">招待对象</th>
					<th
						data-options="field:'entertainCategory',width:fixWidth(0.06),align:'center'">招待类别</th>
					<th 
						data-options="field:'entertainReason',width:fixWidth(0.1),align:'center'">招待事由</th> 
					<th 
						data-options="field:'entertainNum',width:fixWidth(0.05),align:'center'">招待人数</th>
					<th 
						data-options="field:'accompanyNum',width:fixWidth(0.05),align:'center'">陪同人数</th>
					<th
						data-options="field:'masterBudget',width:fixWidth(0.05),align:'center'">总预算</th>
					<th
						data-options="field:'applyTime',width:fixWidth(0.1),align:'center'">申请时间</th>
					<th
						data-options="field:'paidTime',width:fixWidth(0.1),align:'center'">报销时间</th>
					<th
						data-options="field:'operation',width:fixWidth(0.1),formatter:operation,align:'center'">操作</th>
					<th
						data-options="field:'check',width:fixWidth(0.15),formatter:checkIn,align:'center'">操作</th>
				</tr>
			</thead>
		</table>  
	</div>
<!-- 查询信息对话框 -->
	<div id="searchUser" class="easyui-dialog"
		style="width: 400px; height: 320px; padding: 10px 20px" closed="true">
		<form id="sfm" method="post">
			<div style="text-align: center;">
				<table style="margin: auto;" cellspacing="10">
					<tr>
						<td><label>部门</label></td>
						<td><input id="adepartment" class="easyui-combobox" 
						data-options="
							url:'${pageContext.request.contextPath}/entertain/getDepartment',
							method:'get',
							valueField:'id',
							textField:'departmentName',
							editable:false,
							panelHeight:'200'
							" style="width: 220px;"></input></td>
					</tr>
					<tr>
						<td><label>经办人</label></td>
						<td><input id="amanager" class="easyui-textbox" style="width: 220px;"></input></td>
					</tr>
					<tr>
						<td><label>起始时间</label></td>
						<td><input id="astarttime" class="easyui-datebox"
						data-options="sharedCalendar:'#cc'" style="width: 220px;"></input></td>
					</tr>
					<tr>
						<td><label>结束时间</label></td>
						<td><input id="aendtime" class="easyui-datebox"
						data-options="sharedCalendar:'#cc'" style="width: 220px;"></input></td>
					</tr>
					<tr>
						<td><label>招待对象</label></td>
						<td><input id="aentertainobject" class="easyui-combobox" 
data-options="
							url:'${pageContext.request.contextPath}/entertain/getObject',
							method:'get',
							valueField:'id',
							textField:'oname',
							editable:false,
							panelHeight:'200'
							" style="width: 220px;"></input></td>
					</tr>

				</table>
				<div id="cc" class="easyui-calendar"></div>
			</div>
			<div style="text-align: center; bottom: 15px; margin-top: 20px;">
				<a href="#" id="search_ok" class="easyui-linkbutton"
					data-options="iconCls:'icon-search'" style="width: 20%;">查询</a> <a
					href="#" id="search_cancel" class="easyui-linkbutton"
					data-options="iconCls:'icon-cancel'" style="width: 20%;">取消</a>
			</div>
		</form>
	</div>
</body>
</html>