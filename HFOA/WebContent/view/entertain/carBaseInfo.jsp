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
	var es = "";
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
		$("#acartype").combobox("setText", "全部");
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
	function loadGrid(url,data){
		//汉化 datagrid 翻页
		$("#llbinfo").datagrid({
			url : url,
			method : 'post',
			queryParams:data,
			pageSize : 20,
			rownumbers : true,
			singleSelect : true,
			pagination : true,
			toolbar : [  {
				text : '添加',
				iconCls : 'icon-add',
				handler : function() {
					add();
				}},'-',{
				text : '修改',
				iconCls : 'icon-edit',
				handler : function() {
					edit();
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
	}
	$(function() {
		//$(param.llbid) 传递过来得履历本id
		
		loadGrid('${pageContext.request.contextPath}/CarBaseInfo/display',null);
		// 确认按钮
		$("#btn_ok").click(function(){
			//
			
				var CarCode = $("#carCode").textbox("getText");
				var CarType = $("#carType").textbox("getText");
				var CarNum = $("#carNum").textbox("getText");
				var CarUnit = $("#carUnit").textbox("getText");
				var CarDetailInfo = $("#carDetailInfo").textbox("getText");
				var CarDistance = $("#carDistance").textbox("getText");
				var PeasonNum = $("#peasonNum").textbox("getText");
				var CarInsuranceInfoDetal = $("#carInsuranceInfoDetal").textbox("getText");
				var CarState = $("#carState").textbox("getText");
				var SuspendDay = $("#suspendDay").textbox("getText");
				var CardVale = $("#cardVale").textbox("getText");
			
			if(es=="添加"){
				
				var param = {CarCode:CarCode,CarType:CarType,CarNum:CarNum,CarUnit:CarUnit,
						CarDetailInfo:CarDetailInfo,CarDistance:CarDistance,PeasonNum:PeasonNum,
						CarInsuranceInfoDetal:CarInsuranceInfoDetal,CarState:CarState,SuspendDay:SuspendDay,CardVale:CardVale};
				$.post("${pageContext.request.contextPath}/CarBaseInfo/insert",param, function(result) {
					$("#dlg").dialog("close");
					$('#llbinfo').datagrid('reload');
				});
				
			}else{
				var row = $('#llbinfo').datagrid('getSelected');
				
				var param = {ID:row.id,CarCode:CarCode,CarType:CarType,CarNum:CarNum,CarUnit:CarUnit,
						CarDetailInfo:CarDetailInfo,CarDistance:CarDistance,PeasonNum:PeasonNum,
						CarInsuranceInfoDetal:CarInsuranceInfoDetal,CarState:CarState,SuspendDay:SuspendDay,CardVale:CardVale};
				
				$.post("${pageContext.request.contextPath}/CarBaseInfo/update",param, function(result) {
					$("#dlg").dialog("close");
					$('#llbinfo').datagrid('reload');
				});
			}
			
			
			
		});
		// 取消按钮
		$("#btn_cancel").click(function() {
			$('#dlg').dialog('close')
		});
		// 上传取消按钮
		$("#uploadFileCancel").click(function() {
			document.getElementById('fileImport').value = null;
			$('#import-excel').window('close')
		});
		
	})	
	
	function fixWidth(percent) {
		return ($(".mdiv").width() - 30) * percent;
	}
	
	function edit(){
		es = "修改";
		var row = $('#llbinfo').datagrid('getSelected');
		if (row){
			 $('#dlg').dialog('open').dialog('setTitle',es);
		     $('#fm').form('clear');
		     
		    $("#carCode").textbox("setText",row.carCode);
			$("#carType").textbox("setText",row.carType);
			$("#carNum").textbox("setText",row.carNum);
			$("#carUnit").textbox("setText",row.carUnit);
			$("#carDetailInfo").textbox("setText",row.carDetailInfo);
			$("#carDistance").textbox("setText",row.carDistance);
			$("#peasonNum").textbox("setText",row.peasonNum);
			$("#carInsuranceInfoDetal").textbox("setText",row.carInsuranceInfoDetal);
			$("#carState").textbox("setText",row.carState);
			$("#suspendDay").textbox("setText",row.suspendDay);
			$("#cardVale").textbox("setText",row.cardVale);
		     
		}else{
	    	alert("请选择要修改的数据！");
	    }
	}
	function add(){
		 es = "添加";
		 $('#dlg').dialog('open').dialog('setTitle',es);
	     $('#fm').form('clear');
	}
	
</script>
</head>
<body>	<!-- 审核登记对话框 -->
	<div id="dlg" class="easyui-dialog" style="width:400px;height:500px;padding:0px 0px"closed="true" buttons="#dlg-buttons">
        <!--<div>用户编辑</div>  -->
        <form id="fm" method="post">
       <div style="text-align:center;">
        		<table style="margin:auto;" cellspacing="10">
        			<tr>
						<td><label>车名</label></td>
						<td><input id="carCode" class="easyui-textbox" style="width: 220px;"></input></td>
					</tr>
					<tr>
						<td><label>车型</label></td>
						<td><input id="carType" class="easyui-textbox" style="width: 220px;"></input></td>
					</tr>
					<tr>
						<td><label>车牌号</label></td>
						<td><input id="carNum" class="easyui-textbox" style="width: 220px;"></input></td>
					</tr>
					<tr>
						<td><label>公司</label></td>
						<td><input id="carUnit" class="easyui-textbox" style="width: 220px;"></input></td>
					</tr>
					<tr>
						<td><label>详情</label></td>
						<td><input id="carDetailInfo" class="easyui-textbox" style="width: 220px;"></input></td>
					</tr>
					<tr>
						<td><label>卡金额</label></td>
						<td><input id="cardVale" class="easyui-textbox" style="width: 220px;"></input></td>
					</tr>
					<tr>
						<td><label>里程数</label></td>
						<td><input id="carDistance" class="easyui-textbox" style="width: 220px;"></input></td>
					</tr>
					<tr>
						<td><label>人数</label></td>
						<td><input id="peasonNum" class="easyui-textbox" style="width: 220px;"></input></td>
					</tr>
					<tr>
						<td><label>保险信息</label></td>
						<td><input id="carInsuranceInfoDetal" class="easyui-textbox" style="width: 220px;"></input></td>
					</tr>
					<tr>
						<td><label>状态</label></td>
						<td><input id="carState" class="easyui-textbox" style="width: 220px;"></input></td>
					</tr>
					<tr>
						<td><label>限行日</label></td>
						<td><input id="suspendDay" class="easyui-textbox" style="width: 220px;"></input></td>
					</tr>
        		</table>
        	</div>
  			<div style="text-align:center;bottom:15px;margin-top:20px;">
        		<a href="#" id="btn_ok" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:20%;">确定</a>
				<a href="#" id="btn_cancel" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" style="width:20%;">取消</a>
        	</div>
        </form>
    </div>
	<div class="mdiv" style="width:100%;height:94%;">
		<table id="llbinfo" class="easyui-datagrid" title="公车基本信息"
			style="width: auto; height:100%;">
			<thead>
				<tr>
					<th
						data-options="field:'id',width:fixWidth(0.05),align:'center'" hidden="true">编号</th>
					<th
						data-options="field:'carCode',width:fixWidth(0.08),align:'center'">车名</th>
				 	<th
						data-options="field:'carType',width:fixWidth(0.08),align:'center'">车型</th>
					<th 
						data-options="field:'carNum',width:fixWidth(0.1),align:'center'">车牌号</th>
					<th
						data-options="field:'carUnit',width:fixWidth(0.1),align:'center'">公司</th>
					<th
						data-options="field:'carDetailInfo',width:fixWidth(0.1),align:'center'">详情</th>
					<th
						data-options="field:'cardVale',width:fixWidth(0.08),align:'center'">卡金额</th>
					<th 
						data-options="field:'carDistance',width:fixWidth(0.08),align:'center'">里程数</th>
					<th 
						data-options="field:'peasonNum',width:fixWidth(0.05),align:'center'">人数</th>
					<th 
						data-options="field:'carInsuranceInfoDetal',width:fixWidth(0.15),align:'center'">保险信息</th>
					<th 
						data-options="field:'carState',width:fixWidth(0.08),align:'center'">状态</th>
					<th
						data-options="field:'suspendDay',width:fixWidth(0.08),align:'center'">限行日</th>
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
						<td><label>车名</label></td>
						<td><input id="astarttime" class="easyui-datebox"
						data-options="sharedCalendar:'#cc'" style="width: 220px;"></input></td>
					</tr>
					<tr>
						<td><label>车型</label></td>
						<td><input id="amanager" class="easyui-textbox" style="width: 220px;"></input></td>
					</tr>
					<tr>
						<td><label>车牌号</label></td>
						<td><input id="astarttime" class="easyui-datebox"
						data-options="sharedCalendar:'#cc'" style="width: 220px;"></input></td>
					</tr>
					<tr>
						<td><label>公司</label></td>
						<td><input id="astarttime" class="easyui-datebox"
						data-options="sharedCalendar:'#cc'" style="width: 220px;"></input></td>
					</tr>
					<tr>
						<td><label>购买时间</label></td>
						<td><input id="aendtime" class="easyui-datebox"
						data-options="sharedCalendar:'#cc'" style="width: 220px;"></input></td>
					</tr>
					<tr>
						<td><label>详情</label></td>
						<td><input id="aendtime" class="easyui-datebox"
						data-options="sharedCalendar:'#cc'" style="width: 220px;"></input></td>
					</tr>
					<tr>
						<td><label>里程数</label></td>
						<td><input id="aendtime" class="easyui-datebox"
						data-options="sharedCalendar:'#cc'" style="width: 220px;"></input></td>
					</tr>
					<tr>
						<td><label>人数</label></td>
						<td><input id="aendtime" class="easyui-datebox"
						data-options="sharedCalendar:'#cc'" style="width: 220px;"></input></td>
					</tr>
					<tr>
						<td><label>保险信息</label></td>
						<td><input id="aendtime" class="easyui-datebox"
						data-options="sharedCalendar:'#cc'" style="width: 220px;"></input></td>
					</tr>
					<tr>
						<td><label>状态</label></td>
						<td><input id="aendtime" class="easyui-datebox"
						data-options="sharedCalendar:'#cc'" style="width: 220px;"></input></td>
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