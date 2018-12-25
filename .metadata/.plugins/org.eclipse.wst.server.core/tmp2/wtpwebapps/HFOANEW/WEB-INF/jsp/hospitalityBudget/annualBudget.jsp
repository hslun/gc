<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>业务招待费年度预算</title>
<style type="text/css">
.position {
align:left;
margin-left:10%;
}
</style>
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
	
	$(function() {
		//$(param.llbid) 传递过来得履历本id
		//汉化 datagrid 翻页
		$("#llbinfo").datagrid({
			url : '${pageContext.request.contextPath}/entertain/wGetAnnualBudget',
			method : 'post',
			pageSize : 20,
			rownumbers : true,
			singleSelect : true,
			fit: true, //datagrid自适应宽度
            fitColumn: false, //列自适应宽度
            /* editor:{
                type:'textbox'}, */
			toolbar : "#tb",
			
			onLoadSuccess:function(data){ 
				console.log(data);
				//alert(data);
				
			 	// 给combobox赋初值
      			/* var now = new Date();
       			var nowd = new Date().Format("yyyy-MM-dd");
       			var y = nowd.substring(0,4);
				$('#annual').combobox("setText",y);	 */ 
				
				/* var t1= data.rows[0].budgetSum;
				var t2= data.rows[1].budgetSum;
				var t3= data.rows[2].budgetSum;
				var t4= data.rows[3].budgetSum;
				var t5= data.rows[4].budgetSum;
				var t6= data.rows[5].budgetSum;
				var t7= data.rows[6].budgetSum;
				var t8= data.rows[7].budgetSum;
				var t9= data.rows[8].budgetSum;
				var t10=data.rows[9].budgetSum;
				var t11=data.rows[10].budgetSum;
				
				$("#t0").html(t1);
				$("#t1").html(t2);
				$("#t2").html(t3);
				$("#t3").html(t4);
				$("#t4").html(t5);
				$("#t5").html(t6);
				$("#t6").html(t7);
				$("#t7").html(t8);
				$("#t8").html(t9);
				$("#t9").html(t10);
				$("#t10").html(t11);
				
				var c1= data.rows[0].copileTime;
				var c2= data.rows[1].copileTime;
				var c3= data.rows[2].copileTime;
				var c4= data.rows[3].copileTime;
				var c5= data.rows[4].copileTime;
				var c6= data.rows[5].copileTime;
				var c7= data.rows[6].copileTime;
				var c8= data.rows[7].copileTime;
				var c9= data.rows[8].copileTime;
				var c10= data.rows[9].copileTime;
				var c11= data.rows[10].copileTime;
				
				$("#c0").html(c1);
				$("#c1").html(c2);
				$("#c2").html(c3);
				$("#c3").html(c4);
				$("#c4").html(c5);
				$("#c5").html(c6);
				$("#c6").html(c7);
				$("#c7").html(c8);
				$("#c8").html(c9);
				$("#c9").html(c10);
				$("#c10").html(c11); */
				
			}
		});

		// 给combobox赋初值
			$("#annual").combobox({
				onLoadSuccess: function(){
					var now = new Date();
	       			var nowd = new Date().Format("yyyy-MM-dd");
	       			var ny = nowd.substring(0,4);
					$("#annual").combobox('setText',ny);
				}
			});

		
		/* $("#annual").combobox({
			onSelect: function(){
				var year = $("#annual").combobox('getText'); 
				//alert(year);
				var param = "year="+year;
				//alert(year);
				$.post(
						"${pageContext.request.contextPath}/entertain/wGetSearchAnnual",
						param, function(result) {
									//alert(result);
									
							        //$('#annual').combobox("setText",year);
							        
									$("#llbinfo").datagrid("loadData", result);
									var t1= result.rows[0].budgetSum;
									var t2= result.rows[1].budgetSum;
									var t3= result.rows[2].budgetSum;
									var t4= result.rows[3].budgetSum;
									var t5= result.rows[4].budgetSum;
									var t6= result.rows[5].budgetSum;
									var t7= result.rows[6].budgetSum;
									var t8= result.rows[7].budgetSum;
									var t9= result.rows[8].budgetSum;
									var t10= result.rows[9].budgetSum;
									var t11= result.rows[10].budgetSum;
									
									$("#t0").html(t1);
									$("#t1").html(t2);
									$("#t2").html(t3);
									$("#t3").html(t4);
									$("#t4").html(t5);
									$("#t5").html(t6);
									$("#t6").html(t7);
									$("#t7").html(t8);
									$("#t8").html(t9);
									$("#t9").html(t10);
									$("#t10").html(t11);
									
									var c1= result.rows[0].copileTime;
									var c2= result.rows[1].copileTime;
									var c3= result.rows[2].copileTime;
									var c4= result.rows[3].copileTime;
									var c5= result.rows[4].copileTime;
									var c6= result.rows[5].copileTime;
									var c7= result.rows[6].copileTime;
									var c8= result.rows[7].copileTime;
									var c9= result.rows[8].copileTime;
									var c10= result.rows[9].copileTime;
									var c11= result.rows[10].copileTime;
									
									$("#c0").html(c1);
									$("#c1").html(c2);
									$("#c2").html(c3);
									$("#c3").html(c4);
									$("#c4").html(c5);
									$("#c5").html(c6);
									$("#c6").html(c7);
									$("#c7").html(c8);
									$("#c8").html(c9);
									$("#c9").html(c10);
									$("#c10").html(c11);
					});
				 $("#annual").combobox('setText',year);  }
		}); */

	});
	
	
	function fixWidth(percent) {
		return ($(".mdiv").width() - 30) * percent;
	}
	
	$('#aentertainobject').combobox({
		filter: function(q, row){
			var opts = $(this).combobox('options');
			return row[opts.textField].indexOf(q) == 0;
		}
	});
	
	function saveData(){
		var a = document.getElementById("a0").value;
		var b = document.getElementById("a1").value;
		var c = document.getElementById("a2").value;
		var d = document.getElementById("a3").value;
		var e = document.getElementById("a4").value;
		var f = document.getElementById("a5").value;
		var g = document.getElementById("a6").value;
		var h = document.getElementById("a7").value;
		var i = document.getElementById("a8").value;
		var j = document.getElementById("a9").value;
		var k = document.getElementById("a10").value;
		var sy = $('#annual').combobox("getText");//获取combobox中的年份
		var para = "";
		
		if(a==""&&b==""&&c==""&&d==""&&e==""&&f==""&&g==""&&h==""&&i==""&&j==""&&k==""){
			$.messager.alert('提示','请输入调整金额','error');
		}			
		else{
			para ="10:"+a+","+"9:"+b+","+"8:"+c+","+"7:"+d+","+"6:"+e+","+"5:"+f+","+"4:"+g+","+"3:"+h+","+"2:"+i+","+"1:"+j+","+"0:"+k+","+"year:"+sy;
        //alert(para);
        var param="param="+para;
		$.post("${pageContext.request.contextPath}/entertain/wSetAdjust",
			param, function(result) {
			//alert("编制成功！");
			$.messager.alert('提示','编制成功');
			location.reload();//刷新一次页面
			//给combobox赋值
		});
	}}
	 
	// 预算金额列填充textbox
	function textbox(value,row,rowIndex){
		var id = row.id;
		return '<span id="t'+id+'"></span>';
	}  
	
	// 调整金额列填充textbox
	function adjust(value,row,rowIndex){
		return '<input id="a'+row.id+'" class="easyui-textbox" style="width:88px;"></input>';
	}
	// 编制时间列填充textbox
	function copiletime(value,row,rowIndex){
		var id = row.id;
		return '<span id="c'+id+'"></span>';
		//return '<input id="c'+row.id+'" class="easyui-textbox" style="width:88px;" readonly="readonly"></input>';
	}

	// 操作列
	function operation(value,row,rowIndex){
		//console.info(row.id);
		return '<a class="preScan" href="#" iconCls="icon-search" onclick="detail('+ row.id +','+rowIndex+')">查看</a>';
	}
	
</script>
</head>
<body>
	<div class="mdiv" style="width:100%;height:380px;">
		<table id="llbinfo" class="easyui-datagrid" title="业务招待费年度预算"
			 style="width: auto; height:100%;">
			<thead>
				<tr>
<!-- 					<th  -->
<!-- 						data-options="field:'id',width:fixWidth(0.15),align:'center'">id</th> -->
				 	<th 
						data-options="field:'department',width:fixWidth(0.25),align:'center'">部门</th>
					<th
						data-options="field:'hospitalitybudget',width:fixWidth(0.25),align:'center'">预算金额（万元）</th>
					<th
						data-options="field:'createTime',width:fixWidth(0.25),align:'center'">编制时间</th>
					<th
						data-options="field:'operation',width:fixWidth(0.25),formatter:operation,align:'center'">操作</th>
					
				</tr>
			</thead>
		</table> 
		
		  <div>
			<a href="#" id="button_ok" onclick="saveData()" class="easyui-linkbutton position"
			data-options="iconCls:'icon-save'" style="width: 10%;">提交</a>
	  </div> 
	</div>
	
	<div id="tb" style="padding:5px;height:auto">
<div style="margin-bottom:5px">
<lable>年度</lable>
<input id="annual" class="easyui-combobox" 
						data-options="
							url:'${pageContext.request.contextPath}/entertain/getYear',
							method:'get',
							valueField:'id',
							textField:'year',
							editable:false,
							panelHeight:'60'
							" style="width: 60px;"></input>
</div>
</div>

</body>
</html>