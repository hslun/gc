<%@ page language="java" contentType="text/html; utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>密码管理</title>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/img/entertainImg/favicon.ico">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/jqueryui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/jqueryui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/jqueryui/demo/demo.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/CSS/jqueryui/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/CSS/jqueryui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/CSS/jqueryui/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/UserManage.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/PublicStyle.css">
<script type="text/javascript">
	var url = "";
	var inputPassword = ""; //输入的原密码
	var myPassword = ""; //数据库中的密码
	function getJsonObjLength(jsonObj) {
		var Length = 0;
		for ( var item in jsonObj) {
			Length++;
		}
		return Length;
	}

	function formatJson(json) {
		var jsobj = eval(json);
		return '{"total":' + jsobj.bingdings.length + ',"rows":' + json + "}";
	}

	$(function() {

		/* //信息加载
		$.ajax({
			type : "post",
			url : "${pageContext.request.contextPath}/admin/getSelfAdminInfo",
			dataType : "json",
			cache : false,
			success : function(data) {
				$("#ff").form("load", data);
			},
		}); */

		function getCookie(cookie_name)
		{
		    var allcookies = document.cookie;
		    var cookie_pos = allcookies.indexOf(cookie_name);   //索引的长度
		  
		    // 如果找到了索引，就代表cookie存在，
		    // 反之，就说明不存在。
		    if (cookie_pos != -1)
		    {
		        // 把cookie_pos放在值的开始，只要给值加1即可。
		        cookie_pos += cookie_name.length + 1;      //这里容易出问题，所以请大家参考的时候自己好好研究一下
		        var cookie_end = allcookies.indexOf(";", cookie_pos);
		  
		        if (cookie_end == -1)
		        {
		            cookie_end = allcookies.length;
		        }
		  
		        var value = unescape(allcookies.substring(cookie_pos, cookie_end));         //这里就可以得到你想要的cookie的值了。。。
		    }
		    return value;
		}  
		
		//密码修改和提交

		$("#btn_save").click(
						function() {
							if ($("#oldpassword").textbox("getText") == ''|| $("#oldpassword").textbox("getText") == null) {
								alert("请输入原密码");
							} else {
								//原密码与数据库密码进行匹配
								var cookie_val = '${sessionScope.username}'; //test
								var value = $("#oldpassword").textbox("getText");
								$.ajax({
											type : 'post',
											dataType : "json",
											url : '${pageContext.request.contextPath}/matchPassword',
											data : {
												"input" : value,
												"name"  : cookie_val
											},
											cache : false,
											success : function(data) {
												//alert(data);
												inputPassword = data.inputPassword;
												myPassword = data.myPassword;

												if (inputPassword != myPassword) {
													alert('与原密码不一致，重新输入.');
													$("#oldpassword").textbox(
															"setValue", "");
													$("#password").textbox(
															"setValue", "");
													$("#repassword").textbox(
															"setValue", "");

												}

												else {
													//新密码与确认密码进行匹配
													var newpassword = $("#password").textbox("getText");
													var repassword = $("#repassword").textbox("getText");
													if (newpassword == "") {
														alert("请输入新密码！");
														return;
													} else if (newpassword != repassword) {
														alert("请重新输入确认密码");
														$("#repassword").textbox("setValue","");
													} else {
														var param = "parameter="+newpassword+","+cookie_val;

													$.post("${pageContext.request.contextPath}/savePassword",
															param,function(result) {
														//alert(result);
																if (result == "1") {$.messager.show({
																					title : 'Message',
																					msg : '修改密码成功'});
																			} else {
																				$.messager.show({
																							title : 'Error',
																							msg : '修改密码失败'
																				});
																			}
																		});
													}
												}
											},
										});
							}

						});
	});
</script>
</head>
<body>
	<div title="密码管理"
		style="padding: 5px; height: 380px; text-align: center;">
		<form id="ff" method="post" style="width: 98%; height: 500px;">
			<table style="margin: auto; width: 240px;" cellpadding="5"
				cellspacing="5">
				<caption style="font-size: 15px;">密码管理</caption>
				<br>
				<br>
				<tr>
					<td>原&nbsp;密&nbsp;码:</td>
					<td><input id="oldpassword" class="easyui-textbox"
						type="password" name="oldpassword"></input></td>
				</tr>
				<tr>
					<td>新&nbsp;密&nbsp;码:</td>
					<td><input id="password" class="easyui-textbox"
						type="password" name="password" onclick="match()"></input></td>
				</tr>
				<tr>
					<td>确认密码:</td>
					<td><input id="repassword" class="easyui-textbox"
						type="password" name="repassword"></input></td>
				</tr>
			</table>
			<br> <br> <a href="#" id="btn_save"
				class="easyui-linkbutton" data-options="iconCls:'icon-save'"
				style="width: 8%;">修改</a>
		</form>
	</div>
</body>
</html>