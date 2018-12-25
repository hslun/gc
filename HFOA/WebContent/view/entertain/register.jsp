<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>待审核事后登记</title>
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/img/entertainImg/favicon.ico">
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
	href="${pageContext.request.contextPath}/CSS/main.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/CSS/UserManage.css">
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
	
	$(function() {
		//汉化 datagrid 翻页
		$("#studentInfo").datagrid({
		url : '${pageContext.request.contextPath}/entertain/registerDetail?number='+ ${param.number},
		method : 'post',
		//pageSize : 10,
		rownumbers : true,
		singleSelect : true,
		pagination : true,
		//onLoadSuccess:
	});

});
	function fixWidth(percent) {
		return ($(".mdiv").width() - 30) * percent;
	}

	function getUserName(value, row, rowIndex) {
		if (row["student.username"] == undefined) {
			return new Object(row["student"]).username;
		} else {
			return row["student.username"];
		}
	}
	function getRealName(value, row, rowIndex) {
		if (row["student.realname"] == undefined) {
			return new Object(row["student"]).realname;
		} else {
			return row["student.realname"];
		}
	}
	function getDepartment(value, row, rowIndex) {
		if (row["student.department"] == undefined) {
			return new Object(row["student"]).department;
		} else {
			return row["student.department"];
		}
	}
	function getSubScore(value, row, index){
		
		return '<a class="getSubScore" href="#" onclick="subCheck('+index+')">打分</a>';
	}
	var count=0;
	function  countScore(value, row, index){
		if(row.objscore!=null && row.subscore!=null){
			var score1=parseInt(row.objscore);
			var score2=parseInt(row.subscore);
			var count=score1+score2;
			}
			return count;
		}
</script>
</head>
<body>
	<div class="mdiv" style="width: 98%; height: 40%;">
		<table id="studentInfo" class="easyui-datagrid" title="事后审核表单"
			style="width: 100%; height: 95%;">
			<thead>
				<tr>
					<th
						data-options="field:'number',width:fixWidth(0.1),align:'center'">审批单号</th>
					<th 
						data-options="field:'invoiceDate',width:fixWidth(0.15),align:'center'">发票开具日期</th>
					<th
						data-options="field:'invoiceContent',width:fixWidth(0.15),align:'center'">开票内容</th>
					<th 
						data-options="field:'invoiceSum',width:fixWidth(0.1),align:'center'">发票金额</th>
					<th 
						data-options="field:'invoiceNum',width:fixWidth(0.1),align:'center'">发票张数</th>
					<th 
						data-options="field:'invoiceUnit',width:fixWidth(0.2),align:'center'">发票开具单位</th>
					<th
						data-options="field:'paidTime',width:fixWidth(0.1),align:'center'">报销时间</th>
					<th
						data-options="field:'remark',width:fixWidth(0.1),align:'center'">备注</th>
				</tr>
			</thead>
		</table>
		<div style="text-align: center; bottom: 15px; margin-top: 20px;">
				<a href="#" id="btn_ok" class="easyui-linkbutton"
					data-options="iconCls:'icon-save'" style="width: 20%;">通过</a> 
				<a href="#" id="btn_cancel" class="easyui-linkbutton"
					data-options="iconCls:'icon-cancel'" style="width: 20%;">驳回</a>
			</div>
	</div>


</body>
</html>