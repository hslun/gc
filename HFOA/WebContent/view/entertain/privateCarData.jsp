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

	//批量登记
	var chk_value =[];
	function batchRegist(){
		chk_value =[];
		var rows = $('#llbinfo').datagrid('getSelections');
		var applyman=rows[0].applyMan;
		if(rows.length==0){
			alert("请选择要登记的申请信息！");
		}else{
			for(var i=0;i<rows.length;i++){
				//判断该申请是否已经登记
			if(rows[i].status!="已通过"){
					alert("请先修改未通过的业务状态！");
					return false;
			}else{
				if(rows[i].applyMan!=applyman){
					alert("请选择相同的申请人！");
					return false;
				}else{
				if(rows[i].danhao!=null&&rows[i].danhao!=""){
					alert("已经存在登记过的数据");
					return false;
				}else{
					chk_value.push(rows[i].applyId);
				}
			}
			}
			}
			$('#save').dialog('open').dialog('setTitle', '审核单号');
			var now = new Date();
	        var nowd = new Date().Format("yyyy-MM-dd");
	        var nowd1 = new Date().Format("yyyy-MM-dd hh:mm:ss");
	        $("#sregisttime").textbox("setText", nowd);
	        $("#ssubtime").textbox("setText", nowd1);
		}
		
	}
	// 给查询框赋初值
	function search() {
		var now = new Date();
        var nowd = new Date().Format("yyyy-MM-dd hh:mm:ss");

        var lastmonth = new Date;
        lastmonth.setDate(now.getDate() - 30);
        var ldate = lastmonth.Format("yyyy-MM-dd hh:mm:ss");
        
		$('#searchUser').dialog('open').dialog('setTitle', '查询');
		$("#sdepartment").combobox("setText", "全部");
		$("#status").val("全部");
		$("#sstarttime").textbox("setText", ldate);
		$("#sendtime").textbox("setText", nowd);
	}
	//删除数据
	function del(){
		var row = $('#llbinfo').datagrid('getSelected');
		if (row) {
			var param = {
					ApplyId : row.applyId
			};
			$.post("${pageContext.request.contextPath}/PrivateCar/deleteApplyInfo", param,
					function(result) {
				if (result>0) {
					$("#dlg").dialog("close");
					$('#llbinfo').datagrid('reload');
					$.messager.show({
						title : 'Message',
						msg : '删除成功'
					});
				} else {
					$.messager.show({
						title : 'Error',
						msg : '删除失败'
					});
				}
					});
		} else {
			alert("请选择要删除的数据！");
		}
	}
	// js格式化Date方法
	      //修改日历框的显示格式
        function formatter(date){
		var now=new Date;
            var year = date.getFullYear();
            var month = date.getMonth() + 1;
            var day = date.getDate();
            var hour = now.getHours();
            var minute = now.getMinutes();
            var second = now.getSeconds();
            month = month < 10 ? '0' + month : month;
            day = day < 10 ? '0' + day : day;
            hour = hour < 10 ? '0' + hour : hour;
            minute = minute < 10 ? '0' + minute : minute;
            second = second < 10 ? '0' + second : second;
            return year + "-" + month + "-" + day + " " + hour+":"+minute+":"+second;
        }

        function parser(s){
            var t = Date.parse(s);
            if (!isNaN(t)){
                return new Date(t);
            } else {
                return new Date(s);
            }
        }
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
	
	function loadGrid(url,data){
		//汉化 datagrid 翻页
		$("#llbinfo").datagrid({
			url : url,
			method : 'post',
			queryParams:data,
			pageSize : 20,
			rownumbers : true,
			singleSelect : false,
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

			},'-',{
				text : '删除',
				iconCls : 'icon-remove',
				handler : function() {
					del();
			}},'-',{
				text : '批量登记',
				iconCls : 'icon-edit',
				handler : function() {
					batchRegist();
			}},'-',{
				text : '查询',
				iconCls : 'icon-search',
				handler : function() {
					search();
			}}],
			
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
		//切换状态切换添加页面
		$("#statuss").change(function(){
			if($("#statuss").val()=="已通过"){
				$("#approveT1").show();
				$("#approveT2").show();
				$("#performstatus1").show();
				$("#performstatus2").show();
				$("#pass1").hide();
				$("#pass2").hide();
				$("#sum1").hide();
				$("#sum2").hide();
				$("#register1").hide();
				$("#register2").hide();
				$("#regman1").hide();
				$("#regman2").hide();
				$("#voucher1").hide();
				$("#voucher2").hide();
			}else if($("#statuss").val()=="待审批"){
				$("#approveT1").hide();
				$("#approveT2").hide();
				$("#performstatus1").hide();
				$("#performstatus2").hide();
				$("#sum1").hide();
				$("#sum2").hide();
				$("#register1").hide();
				$("#register2").hide();
				$("#regman1").hide();
				$("#regman2").hide();
				$("#voucher1").hide();
				$("#voucher2").hide();
				$("#pass1").hide();
				$("#pass2").hide();
				$("#subtime1").hide();
				$("#subtime2").hide();
			}else if($("#statuss").val()=="被否决"){
				$("#performstatus1").hide();
				$("#performstatus2").hide();
				$("#approveT1").show();
				$("#approveT2").show();
				$("#sum1").hide();
				$("#sum2").hide();
				$("#register1").hide();
				$("#register2").hide();
				$("#regman1").hide();
				$("#regman2").hide();
				$("#voucher1").hide();
				$("#voucher2").hide();
				$("#pass1").hide();
				$("#pass2").hide();
				$("#subtime1").hide();
				$("#subtime2").hide();
			}else if($("#statuss").val()=="已完成"){
				$("#approveT1").show();
				$("#approveT2").show();
				$("#performstatus1").show();
				$("#performstatus2").show();
				$("#pass1").show();
				$("#pass2").show();
				$("#sum1").show();
				$("#sum2").show();
				$("#register1").show();
				$("#register2").show();
				$("#regman1").show();
				$("#regman2").show();
				$("#voucher1").show();
				$("#voucher2").show();
				$("#subtime1").show();
				$("#subtime2").show(); 
			}
			
		});
		$("#ifpass").change(function(){
			if("已报销"==$(this).val()){
				$("#register1").show();
				$("#register2").show();
				$("#regman1").show();
				$("#regman2").show();
				$("#voucher1").show();
				$("#voucher2").show();
				$("#sum1").show();
				$("#sum2").show();
			}else{
				$("#register1").hide();
				$("#register2").hide();
				$("#regman1").hide();
				$("#regman2").hide();
				$("#voucher1").hide();
				$("#voucher2").hide();
				$("#sum1").show();
				$("#sum2").show();
			}
		});
		//是否执行切换
		$("#ifperform").change(function(){
			if($("#ifperform").val()=="待执行"){
				$("#pass1").hide();
				$("#pass2").hide();
				$("#sum1").hide();
				$("#sum2").hide();
				$("#register1").hide();
				$("#register2").hide();
				$("#regman1").hide();
				$("#regman2").hide();
				$("#voucher1").hide();
				$("#voucher2").hide();
				$("#pass1").hide();
				$("#pass2").hide();
				$("#subtime1").hide();
				$("#subtime2").hide();
			}else if($("#ifperform").val()=="已执行"){
				$("#pass1").show();
				$("#pass2").show();
				$("#sum1").show();
				$("#sum2").show();
				$("#subtime1").show();
				$("#subtime2").show();
			}
		})
		loadGrid('${pageContext.request.contextPath}/PrivateCar/displayAll',null);
		// 确认按钮
		$("#btn_ok").click(function(){
			//待审批
				var department = $("#department").textbox("getText");
				var applyman = $("#applyman").textbox("getText");
				var approveman = $("#approveman").textbox("getText");
				var usercartime = $("#usercartime").textbox("getText");
				var reason = $("#reason").textbox("getText");
				var beginaddress = $("#beginaddress").textbox("getText");
				var passaddress = $("#passaddress").textbox("getText");
				var destination = $("#destination").textbox("getText");
				var singlelength = $("#singlelength").textbox("getText");
				var surelength = $("#surelength").textbox("getText");
				var countlength = $("#countlength").textbox("getText");
				var waymodel = $("#waymodel").val();
				var waydetail = $("#waydetail").textbox("getText");
				var doublelength = $("#doublelength").val();
				var endlength = $("#endlength").textbox("getText");
				var ifbefore = $("#ifbefore").val();
				var status=$("#statuss").val();
				var applytime=$("#applytime").textbox("getText");
			//被否决
				var approvetime=$("#approvetime").textbox("getText");//审批时间
			//已通过未执行
			    var ifperform=$("#ifperform").val();//是否执行
			//已通过已执行
			    var ifpass = $("#ifpass").val();//是否报销
			    var subtime=$("#subtime").textbox("getText");
			//已执行未报销
			var sum=$("#sum").textbox("getText");//报销金额
			//已执行已报销
			var approveman2=$("#approveman2").textbox("getText");//登记人
			var paidtime=$("#paidtime").textbox("getText");//登记时间
			var danhao=$("#vouchernum").textbox("getText");//凭单号
			if(es=="添加"){
				
				var param = {status:status,department:department,applyman:applyman,approveman:approveman,usercartime:usercartime,
						reason:reason,beginaddress:beginaddress,passaddress:passaddress,
						destination:destination,singlelength:singlelength,surelength:surelength,countlength:countlength,
						waymodel:waymodel,doublelength:doublelength,endlength:endlength,ifbefore:ifbefore,waydetail:waydetail,approvetime:approvetime,ifperform:ifperform,ifpass:ifpass,sum:sum,approveman2:approveman2,paidtime:paidtime,danhao:danhao,applytime:applytime,subtime:subtime};
				$.post("${pageContext.request.contextPath}/PrivateCar/SaveNew",param, function(result) {
					$("#dlg").dialog("close");
					$('#llbinfo').datagrid('reload');
				});
				
			}else{
				var row = $('#llbinfo').datagrid('getSelected');
				
				var param = {applyid:row.applyId,status:status,department:department,applyman:applyman,approveman:approveman,usercartime:usercartime,
						reason:reason,beginaddress:beginaddress,passaddress:passaddress,
						destination:destination,singlelength:singlelength,surelength:surelength,countlength:countlength,
						waymodel:waymodel,doublelength:doublelength,endlength:endlength,ifbefore:ifbefore,waydetail:waydetail,approvetime:approvetime,ifperform:ifperform,ifpass:ifpass,sum:sum,approveman2:approveman2,paidtime:paidtime,danhao:danhao,applytime:applytime,subtime:subtime};
				
				$.post("${pageContext.request.contextPath}/PrivateCar/updateNew",param, function(result) {
					$("#dlg").dialog("close");
					$('#llbinfo').datagrid('reload');
				});
			}
			
			
			
		});
		//查询私车公用申请信息
		$("#search_ok").click(
			function() {
				var param = {DepartmentName:$("#sdepartment").combobox("getText"),
							 UserName:$("#smanager").textbox("getText").trim(),
							 StartTime:$("#sstarttime").textbox("getText"),
							 EndTime:$("#sendtime").textbox("getText"),
							 Status:$("#status").val()};
				$("#searchUser").dialog("close");
				loadGrid('${pageContext.request.contextPath}/PrivateCar/displaySearch',param);
			});
		//登记审核单号
		$("#regist_ok").click(
			function() {
				 var applyids=chk_value.toString(); 
				 var param = {
							ApplyIds : applyids,
							registtime:$("#sregisttime").textbox("getText"),
							registman:$("#sapproveman2").textbox("getText"),
							subtime:$("#ssubtime").textbox("getText"),
							vouchernum:$("#svouchernum").textbox("getText"),
							sum:$("#money").textbox("getText")
					};
				$.post("${pageContext.request.contextPath}/PrivateCar/registApplyInfo", param,
						function(result) {
// 					if (result>0) {
						$("#save").dialog("close");
						$('#llbinfo').datagrid('reload');
// 						$.messager.show({
// 							title : 'Message',
// 							msg : '登记成功'
// 						});
// 					} else {
// 						$.messager.show({
// 							title : 'Error',
// 							msg : '登记失败'
// 						});
// 					}
				});
			});
		// 取消按钮
		$("#btn_cancel").click(function() {
			$('#dlg').dialog('close')
		});
		$("#search_cancel").click(function() {
			$('#searchUser').dialog('close')
		});
		$("#regist_cancel").click(function() {
			$('#save').dialog('close')
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
		     var now = new Date();
	        var nowd = new Date().Format("yyyy-MM-dd");
	        var nowd1 = new Date().Format("yyyy-MM-dd hh:mm:ss");
		     
		     $("#subtime1").show();
			$("#subtime2").show();
		     $("#approveT1").show();
			$("#approveT2").show();
			$("#performstatus1").show();
			$("#performstatus2").show();
			$("#pass1").show();
			$("#pass2").show();
			$("#sum1").show();
			$("#sum2").show();
			$("#register1").show();
			$("#register2").show();
			$("#regman1").show();
			$("#regman2").show();
			$("#voucher1").show();
			$("#voucher2").show();
		    $("#department").textbox("setText",row.department);
			$("#applyman").textbox("setText",row.applyMan);
			$("#approveman").textbox("setText",row.approveMan);
			if(row.userCarTime!=""&&row.userCarTime!=null){
				$("#usercartime").textbox("setText",row.userCarTime);
			}else{
				$("#usercartime").textbox("setText", nowd);
			}
			
			$("#reason").textbox("setText",row.reason);
			$("#beginaddress").textbox("setText",row.beginAddress);
			$("#passaddress").textbox("setText",row.passAddress);
			$("#destination").textbox("setText",row.destination);
			$("#singlelength").textbox("setText",row.singleLength);
			$("#surelength").textbox("setText",row.sureLength);
			$("#countlength").textbox("setText",row.countLength);
			$("#waymodel").val(row.wayModel);
			$("#waydetail").textbox("setText",row.wayDetail);
			$("#endlength").textbox("setText",row.endLength);
			$("#ifpass").val(row.ifPass);
			$("#ifperform").val(row.ifPerform);
			if(row.approveTime!=null&&row.approveTime!=""){
				$("#approvetime").textbox("setText",row.approveTime);
			}else{
				 $("#approvetime").textbox("setText", nowd1);
			}
			if(row.applyTime!=null&&row.applyTime!=""){
				$("#applytime").textbox("setText",row.applyTime);
			}else{
				$("#applytime").textbox("setText", nowd1);
			}
			if(row.subTime!=null&&row.subTime!=""){
				$("#subtime").textbox("setText",row.subTime);
			}else{
				 $("#subtime").textbox("setText", nowd1);
			}
			
			$("#ifbefore").val(row.ifBefore);
			$("#statuss").val(row.status);
			$("#doublelength").val(row.doubleLength);
			$("#sum").textbox("setText",row.sum);
			if(row.paidTime!=null&&row.paidTime!=""){
				$("#paidtime").textbox("setText",row.paidTime);
			}else{
				$("#paidtime").textbox("setText", nowd);
			}
			
			$("#approveman2").textbox("setText",row.approveMan2);
			$("#vouchernum").textbox("setText",row.danhao);
			
			
		}else{
	    	alert("请选择要修改的数据！");
	    }
	}
	function add(){
		 es = "添加";
		 var now = new Date();
        var nowd = new Date().Format("yyyy-MM-dd");
        var nowd1 = new Date().Format("yyyy-MM-dd hh:mm:ss");
		 $('#dlg').dialog('open').dialog('setTitle',es);
	     $('#fm').form('clear');
	     $("#usercartime").textbox("setText", nowd);
	     $("#paidtime").textbox("setText", nowd);
	     $("#approvetime").textbox("setText", nowd1);
	     $("#applytime").textbox("setText", nowd1);
	     $("#subtime").textbox("setText", nowd1);
	     $("#statuss").val("待审批");
	     $("#waymodel").val("自定义线路");
	     $("#ifbefore").val("0");
	     $("#ifperform").val("待执行");
	     $("#ifpass").val("未报销");
	     $("#approveT1").hide();
		$("#approveT2").hide();
		$("#performstatus1").hide();
		$("#performstatus2").hide();
		$("#sum1").hide();
		$("#sum2").hide();
		$("#register1").hide();
		$("#register2").hide();
		$("#regman1").hide();
		$("#regman2").hide();
		$("#voucher1").hide();
		$("#voucher2").hide();
		$("#pass1").hide();
		$("#pass2").hide();
		$("#subtime1").hide();
		$("#subtime2").hide();
	}
	
</script>
</head>
<body>
<!-- 批量登记 -->
	<div id="save" class="easyui-dialog"
		style="width: 400px; height: 320px; padding: 10px 20px" closed="true">
		<form id="form" method="post">
			<div style="text-align: center;">
				<table style="margin: auto;" cellspacing="10" >
					<tr>
						<td><label>登记人</label></td>
						<td><input id="sapproveman2" class="easyui-textbox" style="width: 220px;"></input></td>
					</tr>
					<tr>
						<td><label>提交时间</label></td>
						<td><input id="ssubtime" class="easyui-datebox"
						data-options="formatter:formatter,parser:parser" style="width: 220px;"></input></td>
					</tr>
					<tr>
						<td><label>登记时间</label></td>
						<td><input id="sregisttime" class="easyui-datebox"
						style="width: 220px;"></input></td>
					</tr>
					<tr>
						<td><label>凭单号</label></td>
						<td><input id="svouchernum" class="easyui-textbox" style="width: 220px;"></input></td>
					</tr>
					<tr>
						<td><label>报销金额</label></td>
						<td><input id="money" class="easyui-textbox" style="width: 220px;"></input></td>
					</tr>
				</table>
				<div id="cc" class="easyui-calendar"></div>
			</div>
			<div style="text-align: center; bottom: 15px; margin-top: 20px;">
				<a href="#" id="regist_ok" class="easyui-linkbutton"
					data-options="iconCls:'icon-save'" >凭证号登记</a> <a
					href="#" id="regist_cancel" class="easyui-linkbutton"
					data-options="iconCls:'icon-cancel'" >取消</a>
			</div>
		</form>
	</div>
	<!-- 添加修改框 -->
	<div id="dlg" class="easyui-dialog" style="width:700px;height:550px;padding:0px 0px"closed="true" buttons="#dlg-buttons">
        <form id="fm" method="post">
       <div style="text-align:center;">
        		<table style="margin: auto;" cellspacing="10">
					<tr>
						<td><label>申请人</label></td>
						<td><input id="applyman" class="easyui-textbox"
							style="width: 220px;"></input></td>
						<td><label>使用事由</label></td>
						<td><input id="reason" class="easyui-textbox"
							style="width: 220px;"></input></td>
					</tr>
					<tr>
						<td><label>出发地</label></td>
						<td><input id="beginaddress" class="easyui-textbox"
							style="width: 220px;"></input></td>
							<td><label>目的地</label></td>
						<td><input id="destination" class="easyui-textbox"
							style="width: 220px;"></input></td>
					</tr>
					<tr>
						<td><label>部门</label></td>
						<td><input id="department" class="easyui-combobox" 
						data-options="
							url:'${pageContext.request.contextPath}/entertain/getDepartment',
							method:'get',
							valueField:'departmentName',
							textField:'departmentName',
							editable:false,
							panelHeight:'200'
							" style="width: 220px;"></input></td>
						<td><label>申请状态</label></td>
						<td><select id="statuss" style="width:100%">
						<option value="待审批">待审批</option>
						<option value="已通过">已通过</option>
						<option value="被否决">被否决</option>
<!-- 						<option value="待执行">待执行</option> -->
						<option value="已完成">已完成</option>
						</select></td>
						
					</tr>
					<tr>
						<td><label>单程里程</label></td>
						<td><input id="singlelength" class="easyui-textbox"
							style="width: 220px;"></input></td>
							<td><label>计价里程</label></td>
						<td><input id="countlength" class="easyui-textbox"
							style="width: 220px;"></input></td>
						
					</tr>
					<tr>
						<td><label>核定价格</label></td>
						<td><input id="surelength" class="easyui-textbox"
							style="width: 220px;"></input></td>
						<td><label>路线规划</label></td>
						<td><select id="waymodel" style="width: 220px;"><option
									value="预置线路" selected>预置线路</option>
								<option value="自定义线路">自定义线路</option></select></td>
					</tr>
					<tr>
						<td><label>途径地</label></td>
						<td><input id="passaddress" class="easyui-textbox"
							style="width: 220px;"></input></td>
						<td><label>结束里程</label></td>
						<td><input id="endlength" class="easyui-textbox"
							style="width: 220px;"></input></td>	
					</tr>
					<tr>
						<td><label>预置路线</label></td>
						<td><input id="waydetail" class="easyui-textbox"
							style="width: 220px;"></input></td>
						<td><label>审批人</label></td>
						<td><input id="approveman" class="easyui-textbox"
							style="width: 220px;"></input></td>
<!-- 					<tr> -->
<!-- 						<td><label>审核人</label></td> -->
<!-- 						<td><input id="leader" class="easyui-combobox" -->
<%-- 							data-options=" --%>
<%-- 							url:'${pageContext.request.contextPath}/user/getAllLeader', --%>
<!-- 							method:'get', -->
<!-- 							valueField:'id', -->
<!-- 							textField:'code', -->
<!-- 							editable:false, -->
<!-- 							panelHeight:'200' -->
<%-- 							" --%>
<!-- 							style="width: 220px;"></input></td> -->
					</tr>
					<tr>
						<td><label>是否往返</label></td>
						<td><select id="doublelength" style="width: 220px;"><option
									value="1" selected>是</option>
								<option value="0">否</option></select></td>
						<td><label>是否补录</label></td>
						<td><select id="ifbefore" style="width: 220px;"><option
									value="1" selected>是</option>
								<option value="0">否</option></select></td>
					</tr>
					<tr>
						<td><label>申请时间</label></td>
						<td><input id="applytime" class="easyui-datebox"
						data-options="formatter:formatter,parser:parser" style="width: 220px;"></input></td>
						<td><label>用车时间</label></td>
						<td><input id="usercartime" class="easyui-datebox"
						data-options="sharedCalendar:'#cc'" style="width: 220px;"></input></td>
					</tr>
					<tr>
						<td id="performstatus1"  style="display: none"><label>执行状态</label></td>
						<td id="performstatus2"  style="display: none"><select id="ifperform" style="width: 220px;"><option
									value="待执行" selected>待执行</option>
								<option value="已执行">已执行</option></select></td>
						<td id="approveT1" style="display: none"><label>审批时间</label></td>
						<td id="approveT2" style="display: none"><input id="approvetime" class="easyui-datebox"
						data-options="formatter:formatter,parser:parser" style="width: 220px;"></input></td>		
					</tr>
					<tr>
						<td id="subtime1" style="display: none"><label>提交时间</label></td>
						<td id="subtime2" style="display: none"><input id="subtime" class="easyui-datebox"
						data-options="formatter:formatter,parser:parser" style="width: 220px;"></input></td>
						<td id="pass1" style="display: none"><label>是否报销</label></td>
						<td id="pass2" style="display: none"><select id="ifpass" style="width: 220px;"><option
									value="未报销" selected>未报销</option>
								<option value="已报销">已报销</option></select></td>
					</tr>
					<tr>
					<td id="sum1" style="display: none"><label>报销金额</label></td>
						<td id="sum2" style="display: none"><input id="sum" class="easyui-textbox"
							style="width: 220px;"></input></td>
					<td id="register1" style="display: none"><label>登记时间</label></td>
						<td id="register2" style="display: none"><input id="paidtime" class="easyui-datebox"
						data-options="sharedCalendar:'#cc'" style="width: 220px;"></input></td>
					</tr>
					<tr>
					<td id="regman1" style="display: none"><label>登记人</label></td>
						<td id="regman2" style="display: none"><input id="approveman2" class="easyui-textbox"
							style="width: 220px;"></input></td>
					<td id="voucher1" style="display: none"><label>凭单号</label></td>
					<td id="voucher2" style="display: none"><input id="vouchernum" class="easyui-textbox"
							style="width: 220px;"></input></td>
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
		<table id="llbinfo" class="easyui-datagrid" title="私车公用所有申请信息"
			style="width: auto; height:100%;">
			<thead>
				<tr>
				 	<th
						data-options="field:'',width:fixWidth(0.1),align:'center',checkbox:'true'">选项</th>
						<th
						data-options="field:'applyId',width:fixWidth(0.1),align:'center'" hidden="true">审批单号</th>
						<th
						data-options="field:'applyTime',width:fixWidth(0.1),align:'center'">申请时间</th>
						<th
						data-options="field:'subTime',width:fixWidth(0.1),align:'center'" hidden="true">审批单号</th>
						<th
						data-options="field:'department',width:fixWidth(0.1),align:'center'" hidden="true">审批单号</th>
						<th
						data-options="field:'beginAddress',width:fixWidth(0.1),align:'center'" hidden="true">审批单号</th>
						<th
						data-options="field:'passAddress',width:fixWidth(0.1),align:'center'" hidden="true">审批单号</th>
						<th
						data-options="field:'destination',width:fixWidth(0.1),align:'center'" hidden="true">审批单号</th>
						<th
						data-options="field:'singleLength',width:fixWidth(0.1),align:'center'" hidden="true">审批单号</th>
						<th
						data-options="field:'wayDetail',width:fixWidth(0.1),align:'center'" hidden="true">审批单号</th>
						<th
						data-options="field:'wayModel',width:fixWidth(0.1),align:'center'" hidden="true">审批单号</th>
						<th
						data-options="field:'doubleLength',width:fixWidth(0.1),align:'center'" hidden="true">审批单号</th>
						<th
						data-options="field:'endLength',width:fixWidth(0.1),align:'center'" hidden="true">审批单号</th>
						<th
						data-options="field:'ifPass',width:fixWidth(0.1),align:'center'" hidden="true">审批单号</th>
						<th
						data-options="field:'ifBefore',width:fixWidth(0.1),align:'center'" hidden="true">审批单号</th>
						<th
						data-options="field:'ifPerform',width:fixWidth(0.1),align:'center'" hidden="true">审批单号</th>
						<th
						data-options="field:'sum',width:fixWidth(0.1),align:'center'" hidden="true">审批单号</th>
						<th
						data-options="field:'paidTime',width:fixWidth(0.1),align:'center'" hidden="true">审批单号</th>
						<th
						data-options="field:'approveMan2',width:fixWidth(0.1),align:'center'" hidden="true">审批单号</th>
					<th
						data-options="field:'userCarTime',width:fixWidth(0.1),align:'center'">用车时间</th>
					<th 
						data-options="field:'applyMan',width:fixWidth(0.1),align:'center'">申请人</th>
					<th 
						data-options="field:'sureLength',width:fixWidth(0.1),align:'center'">金额</th>
					<th
						data-options="field:'status',width:fixWidth(0.1),align:'center'">状态</th>
					<th 
						data-options="field:'approveTime',width:fixWidth(0.2),align:'center'">审批时间</th> 
					<th
						data-options="field:'approveMan',width:fixWidth(0.1),align:'center'">审批人</th>
					<th
						data-options="field:'danhao',width:fixWidth(0.2),align:'center'">凭单号</th>
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
						<td><input id="sdepartment" class="easyui-combobox" 
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
						<td><label>申请人</label></td>
						<td><input id="smanager" class="easyui-textbox" style="width: 220px;"></input></td>
					</tr>
					<tr>
						<td><label>申请状态</label></td>
						<td><select id="status" style="width:100%">
						<option value="全部">全部</option>
						<option value="待审批">待审批</option>
						<option value="已通过">已通过</option>
						<option value="被否决">被否决</option>
						<option value="待执行">待执行</option>
						<option value="已完成">已完成</option>
						</select></td>
					</tr>
					<tr>
						<td><label>起始时间</label></td>
						<td><input id="sstarttime" class="easyui-datebox"
						data-options="sharedCalendar:'#cc'" style="width: 220px;"></input></td>
					</tr>
					<tr>
						<td><label>结束时间</label></td>
						<td><input id="sendtime" class="easyui-datebox"
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