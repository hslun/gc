<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理</title>
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
	
var es ="新建";
//提交action 的url
var url="";
var idd=1;
//返回json对象的长度
function getJsonObjLength(jsonObj) {
    var Length = 0;
    for (var item in jsonObj) {
        Length++;
    }
    return Length;
}

//格式化json
function formatJson(json){
	var jsobj=eval(json);
	return '{"total":'+jsobj.bingdings.length+',"rows":'+json+"}";
}


//datagrid-add
function  add(){
	 $('#dlg').dialog('open').dialog('setTitle','添加部门');
     $('#fm').form('clear');
}
//datagrid-remove
function  remove(){
    var row = $('#llbinfo').datagrid('getSelected');
    if (row){
        $.messager.confirm('确认对话框','你确定删除这条记录吗？',function(r){
            if (r){
                $.post('${pageContext.request.contextPath}/sysUser/deleteSysUser',{Id:row.id},function(result){
                    if (result=="1"){
                        $('#llbinfo').datagrid('reload');    // reload the user data
                        $.messager.show({
                            title: 'Message',
                            msg: '删除成功'
                        });
                    } else {
                        $.messager.show({    // show error message
                            title: 'Error',
                            msg: '您没有该权限，删除失败'
                        });
                    }
                },'text');
            }
        });
    }else{
    	alert("请选择要删除的数据！");
    }
}
//datagrid-edit
function  edit(){
	
     es="编辑";
	 var row = $('#llbinfo').datagrid('getSelected');
     if (row){
         $('#dlg').dialog('open').dialog('setTitle','编辑部门');
		 //点击edit有字段的话必须 控件必须和字段名称一样 本次不一样 需要手动赋值
         $("#deptname").textbox("setText",row.departmentName); 
         idd=row.id;
	}else{
		alert("请选择要修改的数据！");
	}
}

function search(){
	 $('#searchUser').dialog('open').dialog('setTitle','查找用户');
	 $("#fm").form('clear');
}
//datagrid-save
function save(){
	 $('#fm').form('submit',{
         url: url,
         onSubmit: function(){
             return $(this).form('validate');
         },
         success: function(result){
             var result = eval('('+result+')');
             if (result.success){
                 $('#dlg').dialog('close');        // close the dialog
                 $('#llbinfo').datagrid('reload');    // reload the user data
                 $.messager.show({
                     title: 'Message',
                     msg: '添加成功'
                 });
             } else {
                 $.messager.show({
                     title: 'Error',
                     msg: result.msg
                 });
             }
         }
     });
}

	$(function (){
        //$(param.llbid) 传递过来得履历本id
		//汉化 datagrid 翻页
		loadGrid('${pageContext.request.contextPath}/department/getAllDept',null);
		
	 $("#btn_ok").click(function (){
		 var url="";
		 var dname = $("#deptname").textbox("getText");
		 if(es=="编辑"){
		    url="${pageContext.request.contextPath}/department/updateDept";
		 }else{
			url="${pageContext.request.contextPath}/department/saveDept";
		 }
	    $.post(url,{ID:idd,DepartmentName:dname},function(result){
            
            if (result=="1"){
                $('#dlg').dialog('close');        // close the dialog
                $('#llbinfo').datagrid('reload');    // reload the user data
                $.messager.show({
                    title: 'Message',
                    msg: '操作成功!'
                });
            } else {
                $.messager.show({
                    title: 'Error',
                    msg: '操作失败!'
                });
            }
            es="";
		});
	}); 
        //删除按钮
		$("#btn_cancel").click(function (){
			$('#dlg').dialog('close')
		});

		//查询用户
		$("#search_ok").click(function(){
			 	var Code = $("#scode").textbox("getText");
			 	var DepartmentName = $("#sdepartment").combobox("getText");
				var paramJson = {"Code":Code,"DepartmentName":DepartmentName};
				if(Code==""&&DepartmentName==""){
					alert("请输入查询条件！");
				}else{
					$("#searchUser").dialog("close");
					loadGrid('${pageContext.request.contextPath}/sysUser/searchUser',paramJson);
				}
				
			});
		
		$("#search_cancel").click(function(){
			$("#searchUser").dialog("close");
			
			});
		
	});
	 function loadGrid(url,data){
// 		 alert(url+"~~~~~~~"+data);
		 $("#llbinfo").datagrid({
				url:url,
				queryParams:data,
				method:'post',
				pageSize:20,
			    rownumbers:true,
			    singleSelect:true,
			    pagination:true,
			    toolbar:[{
						text:'新建',
						iconCls:'icon-add',
						handler:function(){
							add();
						}
					},'-',{
						text:'修改',
						iconCls:'icon-edit',
						handler:function(){
							edit();
						}
					}],
			});
			
			var pager = $('#llbinfo').datagrid().datagrid('getPager');
			pager.pagination({
		        beforePageText: "转到",
		        afterPageText: "共{pages}页",
		        displayMsg: '当前显示从{from}到{to}  共{total}记录',
		        onBeforeRefresh: function (pageNumber, pageSize) {
		            $(this).pagination('loading');
		          
		            $(this).pagination('loaded');
		        }
		    });
	 }
	 function fixWidth(percent)     
		{   
	   	 	return ($(".mdiv").width()-30)* percent ;      
		}  
	 function checkIn(value,row,rowIndex){
			return '<a class="preScan1" href="#" iconCls="icon-save" onclick="resetPwd('+ row.id +','+rowIndex+')">重置密码</a>';
		}
	 function privateCar(value,row,rowIndex){
		 if(value=="0"){
			 return '<a class="preScan1" href="#" iconCls="icon-save" onclick="updatePrivateCar('+ row.id +','+rowIndex+')">开通</a>';
		 }else if(value=="1"){
			 return '已开通';
		 }
		}
	 function resetPwd(id,index) {
		 $.messager.confirm('重置密码对话框','你确定重置该用户密码吗？',function(r){
	            if (r){
	                $.post('${pageContext.request.contextPath}/sysUser/ResetPasswordById',{Id:id},function(result){
	                    if (result=="1"){
	                        $('#llbinfo').datagrid('reload');    // reload the user data
	                        $.messager.show({
	                            title: 'Message',
	                            msg: '重置成功'
	                        });
	                    } else {
	                        $.messager.show({    // show error message
	                            title: 'Error',
	                            msg: '您没有该权限，重置失败'
	                        });
	                    }
	                },'text');
	            }
	        });
		}
	 function updatePrivateCar(id,index) {
		 $.messager.confirm('私车权限对话框','你确定开通该用户私车权限吗？',function(r){
	            if (r){
	                $.post('${pageContext.request.contextPath}/sysUser/updatePrivate',{id:id},function(result){
	                    if (result=="1"){
	                        $('#llbinfo').datagrid('reload');    // reload the user data
	                        $.messager.show({
	                            title: 'Message',
	                            msg: '开通成功'
	                        });
	                    } else {
	                        $.messager.show({    // show error message
	                            title: 'Error',
	                            msg: '您没有该权限，开通失败'
	                        });
	                    }
	                },'text');
	            }
	        });
		}
	//导出表单
	function exportExcel(){

	        var rows = $('#llbinfo').datagrid('getRows'); //获取当前页的数据行  
	        var total = "";  
	        for (var i = 0; i < rows.length; i++) {  
	            total += rows[i]['id']+','; //获取指定列  
	        }
	        //编码，到后台再解码，不然直接传到后台乱码
	        var departmentName = encodeURI(encodeURI($("#sdepartment").combobox("getText")));
// 	        alert(total);
	        /* var param = "number="+total;
	        $.post(
					"${pageContext.request.contextPath}/entertain/exportExcel",
					param, function(result) {
			
							}); */
			window.open('${pageContext.request.contextPath}/sysUser/exportExcel?number='+total+'&depart='+departmentName);
		
		}
</script>
</head>
<body>
<div class="mdiv" style="width:100%;height:94%;">
		<table id="llbinfo" class="easyui-datagrid" title="用户列表"
			style="width: auto; height:100%;">
			<thead>
				<tr>
					<th
						data-options="field:'departmentName',width:fixWidth(0.98),align:'center'">部门</th>
				</tr>
			</thead>
		</table>  
	</div>
	<!-- 添加信息对话框 -->
<%-- 	<shiro:hasPermission name="user:edit"> --%>
    <div id="dlg" class="easyui-dialog" style="width:400px;height:150px;padding:0px 0px"closed="true" buttons="#dlg-buttons">
        <!--<div>用户编辑</div>  -->
        <form id="fm" method="post">
       <div style="text-align:center;">
        		<table style="margin:auto;" cellspacing="10">
        			<tr>
        				<td>
        					<label>部门</label>
        				</td>
        				
        				<td>
        					<input id="deptname" name="deptname" class="easyui-textbox " required="true"></input>
        				</td>
        			</tr>
        		</table>
        	</div>
  			<div style="text-align:center;bottom:15px;margin-top:20px;">
        		<a href="#" id="btn_ok" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:20%;">确定</a>
				<a href="#" id="btn_cancel" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" style="width:20%;">取消</a>
        	</div>
        </form>
    </div>
<%-- </shiro:hasPermission> --%>
<%-- <shiro:lacksPermission name="user:edit">  --%>
  
     <div id="dlg" class="easyui-dialog" style="width:300px;height:150px;padding:20px 20px" closed="true" buttons="#dlg-buttons">
       <div class="text" style=" text-align:center;">
      
       	对不起，您没有该权限</br></br>
       	如需获取，请联系系统管理员！
      </div>
    </div> 
<%-- </shiro:lacksPermission> --%>
  <!-- 查询信息对话框 -->
    <div id="searchUser" class="easyui-dialog" style="width:400px;height:200px;padding:10px 20px" closed="true" >
        <form id="sfm" method="post">
       <div style="text-align:center;">
        		<table style="margin:auto;" cellspacing="10">
        			<tr>
        				<td>
        					<label>部门</label>
        				</td>
        				
        				<td>
        					<input id="realname" name="UserDTO.RealName" class="easyui-textbox " required="true"></input>
        				</td>
        			</tr>
        		</table>
        	</div>
  			<div style="text-align:center;bottom:15px;margin-top:20px;">
        		<a href="#" id="search_ok" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:20%;">查询</a>
				<a href="#" id="search_cancel" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" style="width:20%;">取消</a>
        	</div>
        </form>
    </div>
</body>
</html>