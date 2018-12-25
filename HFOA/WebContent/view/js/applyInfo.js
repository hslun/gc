/**
 * @author Administrator
 */
// 得到其他页面传过来的参数
function getUrlParam(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); // 构造一个含有目标参数的正则表达式对象
	var r = window.location.search.substr(1).match(reg); // 匹配目标参数
	if (r != null)
		return unescape(r[2]);
	return null; // 返回参数值
}
function initDate() {
	Date.prototype.format = function(format) {
		var o = {
			"M+" : this.getMonth() + 1, // month
			"d+" : this.getDate(), // day
			"h+" : this.getHours(), // hour
			"m+" : this.getMinutes(), // minute
			"s+" : this.getSeconds(), // second
			"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
			"S" : this.getMilliseconds()
		// millisecond
		}
		if (/(y+)/.test(format)) {
			format = format.replace(RegExp.$1, (this.getFullYear() + "")
					.substr(4 - RegExp.$1.length));
		}
		for ( var k in o) {
			if (new RegExp("(" + k + ")").test(format)) {
				format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
						: ("00" + o[k]).substr(("" + o[k]).length));
			}
		}
		return format;
	}
}

function setDateTime() {
	initDate();
	var now = new Date();
	var defaulttime = now.format("yyyy-MM-dd");
	var mytime = now.format("hh:mm:ss");
	$("#beginTimePlan_date").val("" + defaulttime);
	$("#beginTimePlan_time").val("" + mytime);
	$("#endTimePlan_date").val("" + defaulttime);
	$("#endTimePlan_time").val("17:00:00");
}
function judgeSelectCar(carCode) {
	if (carCode == "京HV3507") {
		$("#carType").val("101");
		$("#carType-button span").html("切诺基 京HV3507");
	} else if (carCode == "京NH8845") {
		$("#carType").val("102");
		$("#carType-button span").html("GL8 京NH8845");
	} else if (carCode == "京NTU976") {
		$("#carType").val("103");
		$("#carType-button span").html("新捷达 京NTU976");
	} else if (carCode == "京Q1L932") {
		$("#carType").val("105");
		$("#carType-button span").html("奔驰 京Q1L932");
	} else if (carCode == "京QMQ381") {
		$("#carType").val("106");
		$("#carType-button span").html("奔驰 京QMQ381");
	} else {

	}
}
function judgeSelectCarNum(CarNum) {
	if (CarNum == "101") {
		$("#carType").val("101");
		$("#carType-button span").html("切诺基 京HV3507");
	} else if (CarNum == "102") {
		$("#carType").val("102");
		$("#carType-button span").html("GL8 京NH8845");
	} else if (CarNum == "103") {
		$("#carType").val("103");
		$("#carType-button span").html("新捷达 京NTU976");
	} else if (CarNum == "105") {
		$("#carType").val("105");
		$("#carType-button span").html("奔驰 京Q1L932");
	} else if (CarNum == "106") {
		$("#carType").val("106");
		$("#carType-button span").html("奔驰 京QMQ381");
	} else {
		
	}
}
function resetPageInfo() {
	setDateTime();
	if(getUrlParam("Action") == "0"){
		$("#carType-button span").html("切诺基 京HV3507");
	}else if(getUrlParam("Action") == "3"){
		judgeSelectCarNum(getUrlParam("CarNum"));
	}
	$("#driver").val("" + $.cookie('realname'));
	$("#compareManNum").val("0");
	$("#startAddress").val("海丰");
	$("#endAddress").val("永丰");
	$("#useCarReason").val("");
}

function initPageInfo() {
	if (getUrlParam("Action") == "0" || getUrlParam("Action") == "3") {
		resetPageInfo();
	} else if (getUrlParam("Action") == "1") {
		if (getUrlParam("ApplyId") == null || getUrlParam("ApplyId") == "") {

		} else {
			// 根据页面传递过来的ApplyId判断页面默认信息
			var applyId = getUrlParam("ApplyId");
			// 向服务器提交数据
			var dto = {
				applyname : $.cookie("realname")
			}
			$
					.ajax({
						type : "post",
						url : "/HFOA/ApplyInfo",
						dataType : 'json',
						data : dto,
						success : function(data) {
							$(data)
									.each(
											function() {
												if (this.applyId == applyId) {
													judgeSelectCar(this.carCode);
													$("#driver").val(
															"" + this.driver);
													$("#compareManNum")
															.val(
																	""
																			+ this.compareManNum);
													$("#startAddress")
															.val(
																	""
																			+ this.startAddress);
													$("#endAddress")
															.val(
																	""
																			+ this.endAddress);
													$("#beginTimePlan_date")
															.val(
																	""
																			+ (this.beginTimePlan
																					.split(" "))[0]);
													$("#beginTimePlan_time")
															.val(
																	""
																			+ (this.beginTimePlan
																					.split(" "))[1]);
													$("#endTimePlan_date")
															.val(
																	""
																			+ this.endTimePlan
																					.split(" ")[0]);
													$("#endTimePlan_time")
															.val(
																	""
																			+ this.endTimePlan
																					.split(" ")[1]);
													$("#useCarReason")
															.val(
																	""
																			+ this.useCarReason);
												} else {

												}
											});
						},
						error : function(data) {
							alert("网络错误");
						}
					});
		}
	} else if (getUrlParam("Action") == "2") {
		if (getUrlParam("ApplyId") == null || getUrlParam("ApplyId") == "") {

		} else {
			setEnable();
			// 根据页面传递过来的ApplyId判断页面默认信息
			var applyId = getUrlParam("ApplyId");
			// 向服务器提交数据
			var dto = {
				userid : $.cookie("userid"),
				username : $.cookie("realname"),
				department : $.cookie("departmentname")
			}
			$
					.ajax({
						type : "post",
						url : "/HFOA/ApproveInfo",
						dataType : 'json',
						data : dto,
						success : function(data) {
							$(data)
									.each(
											function() {
												if (this.applyId == applyId) {
													$("#applyMan").val(
															"" + this.applyMan);
													judgeSelectCar(this.carCode);
													$("#driver").val(
															"" + this.driver);
													$("#compareManNum")
															.val(
																	""
																			+ this.compareManNum);
													$("#startAddress")
															.val(
																	""
																			+ this.startAddress);
													$("#endAddress")
															.val(
																	""
																			+ this.endAddress);
													$("#beginTimePlan_date")
															.val(
																	""
																			+ (this.beginTimePlan
																					.split(" "))[0]);
													$("#beginTimePlan_time")
															.val(
																	""
																			+ (this.beginTimePlan
																					.split(" "))[1]);
													$("#endTimePlan_date")
															.val(
																	""
																			+ this.endTimePlan
																					.split(" ")[0]);
													$("#endTimePlan_time")
															.val(
																	""
																			+ this.endTimePlan
																					.split(" ")[1]);
													$("#useCarReason")
															.val(
																	""
																			+ this.useCarReason);
												} else {

												}
											});
						},
						error : function(data) {
							alert("网络错误");
						}
					});
		}
	}
}
function judgeEmptyInfo() {
	if ($("#approveMan-button span").html() == "") {
		alert("审批人不能为空");
		return false;
	} else if ($("#driver").val() == "") {
		alert("驾驶人不能为空");
		return false;
	} else if ($("#compareManNum").val() == "") {
		alert("同行人数不能为空");
		return false;
	} else if ($("#startAddress").val() == "") {
		alert("出发地不能为空");
		return false;
	} else if ($("#endAddress").val() == "") {
		alert("目的地不能为空");
		return false;
	} else if ($("#beginTimePlan_date").val() == "") {
		alert("预借车日期不能为空");
		return false;
	} else if ($("#beginTimePlan_time").val() == "") {
		alert("预借车时间不能为空");
		return false;
	} else if ($("#endTimePlan_date").val() == "") {
		alert("预还车日期不能为空");
		return false;
	} else if ($("#endTimePlan_time").val() == "") {
		alert("预还车时间不能为空");
		return false;
	} else if ($("#useCarReason").val() == "") {
		alert("借车事由不能为空");
		return false;
	} else if ($("#useCarReason").val().length < 2
			|| $("#useCarReason").val().length > 20) {
		alert("借车事由必须在2到20个字符之间");
		return false;
	} else {
		return true;
	}
}
function setEnable() {
	$("#applyMan").attr("disabled", "disabled");
	$("#driver").attr("disabled", "disabled");
	$("#realApproveMan").attr("disabled", "disabled");
	$("#compareManNum").attr("disabled", "disabled");
	$("#startAddress").attr("disabled", "disabled");
	$("#endAddress").attr("disabled", "disabled");
	$("#beginTimePlan_date").attr("disabled", "disabled");
	$("#beginTimePlan_time").attr("disabled", "disabled");
	$("#endTimePlan_date").attr("disabled", "disabled");
	$("#endTimePlan_time").attr("disabled", "disabled");
	$("#useCarReason").attr("disabled", "disabled");
	$("#carType").attr("disabled", "disabled");
}
function judgeAction() {
	// Action 为 1 时修改或者删除借车申请单
	if (getUrlParam("Action") == "1") {
		$('#applyMan_tr').css('display', 'none');
		$('#realApplyMan_tr').css('display', 'none');
		$('#submit_div').css('display', 'none');
		$('#approve_div').css('display', 'none');
		if (getUrlParam("state") == "0") {
			$("#del_div").css('display', 'none');
		} else if (getUrlParam("state") == "1" || getUrlParam("state") == "2") {
			$('#change_div').css('display', 'none');
		}

		initPageInfo();
	} else if (getUrlParam("Action") == "0" || getUrlParam("Action") == "3") {
		// Action 为0时为新建借车申请单页面
		$('#applyMan_tr').css('display', 'none');
		$('#realApplyMan_tr').css('display', 'none');
		$("#driver").val("" + $.cookie("realname"));
		$('#change_div').css('display', 'none');
		$('#approve_div').css('display', 'none');
		$('#del_div').css('display', 'none');
		initPageInfo();
	} else if (getUrlParam("Action") == "2") {
		$('#approveMan_tr').css('display', 'none');
		$("#realApproveMan").val("" + $.cookie("realname"));
		$('#change_div').css('display', 'none');
		$('#submit_div').css('display', 'none');
		$('#del_div').css('display', 'none');
		initPageInfo();

	} else {

	}
}

$(document).on(
		'pageinit',
		'#applyInfo',
		function() {
			// 首先判断cookie是否存在
			if ($.cookie("username") == "null"
				|| $.cookie("username") == ""||$.cookie("username") == null) {
				// 如果不存在，跳转到登录界面
				alert("用户信息已失效，请重新登录");
				if (getUrlParam("Action") == "1"
						|| getUrlParam("Action") == "0") {
					location.href = "/HFOA/view/Manhour/Login.html?Action=100";
				} else if (getUrlParam("Action") == "2") {
					location.href = "/HFOA/view/Manhour/Login.html?Action=101";
				} else if(getUrlParam("Action") == "3"){
					location.href = "/HFOA/view/Manhour/Login.html?Action=102";
				}else{
					
				}
			} else {
				// 如果存在，什么也不做
			}

			// 根据页面传递过来的Action判断显示的内容
			judgeAction();
			var depart = {
				department : $.cookie("departmentname")
			}

			$.ajax({
				type : "post",
				url : "/HFOA/FindLeader",
				dataType : 'json',
				data : depart,
				success : function(data) {
					$(data).each(
							function() {
								$("#approveMan").append(
										"<option>" + this.realName
												+ "</option>");
								$("#approveMan-button span").html(
										"" + this.realName);
							});
				},
				error : function(data) {
					alert("网络错误");
				}
			});

		});

$(document)
		.on(
				'click',
				'#submit',
				function() {
					if (judgeEmptyInfo()) {
						initDate();
						$("#submit").attr("disabled", "disabled");
						var now = new Date();
						var defaulttime = now.format("yyyy-MM-dd hh:mm:ss");
						// 当Action为0时为新建借车申请
						// 提交借车申请单时需要的数据
						var dto = {
							appplyman : $.cookie("realname"),
							applyusername : $.cookie("username"),
							carcode : ($("#carType-button span").html())
									.split(" ")[1],
							approveman : $("#approveMan-button span").html(),
							driverString : $("#driver").val(),
							comparemannum : $("#compareManNum").val(),
							startaddress : $("#startAddress").val(),
							endaddress : $("#endAddress").val(),
							begintimeplan : $("#beginTimePlan_date").val()
									+ " " + $("#beginTimePlan_time").val(),
							endtimeplan : $("#endTimePlan_date").val() + " "
									+ $("#endTimePlan_time").val(),
							usecarreason : $("#useCarReason").val(),
							department : $.cookie("departmentname"),
							applytime : defaulttime,
						}

						$
								.ajax({
									type : "post",
									url : "/HFOA/SaveApplyInfo",
									dataType : 'json',
									data : dto,
									success : function(data) {
										if (data == "1") {
											alert("提交成功");
											location.href = "/HFOA/view/BorrowCar/BorrowCar.html";
										} else if (data == "0") {
											alert("提交失败，请稍后再试");
											$("#submit").removeAttr("disabled");
										} else if (data == "-1") {
											alert("该时间段已经借出");
											$("#submit").removeAttr("disabled");
										} else {

										}
									},
									error : function(data) {
										alert("网络错误，请稍后再试");
										$("#submit").removeAttr("disabled");
									}
								});
					}
				});
$(document).on('click', '#reset', function() {
	// 重置按钮的点击事件
	resetPageInfo();
});
$(document).on('click', '#back', function() {
	// 顶部返回按钮的点击事件
	if(getUrlParam("Action")=="0" || getUrlParam("Action")=="1"){
		location.href = "/HFOA/view/BorrowCar/BorrowCar.html";
	}else if(getUrlParam("Action")=="2"){
		location.href = "/HFOA/view/BorrowCar/ApproveInfo.html";
	}else if(getUrlParam("Action")=="3"){
		location.href = "/HFOA/view/BorrowCar/CarStatus.html";
	}else{
		
	}
});
$(document)
		.on(
				'click',
				'#change',
				function() {
					// 修改按钮的点击事件
					if (judgeEmptyInfo()) {
						initDate();
						$("#change").attr("disabled", "disabled");
						var now = new Date();
						var defaulttime = now.format("yyyy-MM-dd hh:mm:ss");
						var applyId = getUrlParam("ApplyId");
						// 当Action为0时为新建借车申请
						// 提交借车申请单时需要的数据
						var dto = {
							appplyman : $.cookie("realname"),
							applyusername : $.cookie("username"),
							carcode : ($("#carType-button span").html())
									.split(" ")[1],
							approveman : $("#approveMan").html(),
							driverString : $("#driver").val(),
							comparemannum : $("#compareManNum").val(),
							startaddress : $("#startAddress").val(),
							endaddress : $("#endAddress").val(),
							begintimeplan : $("#beginTimePlan_date").val()
									+ " " + $("#beginTimePlan_time").val(),
							endtimeplan : $("#endTimePlan_date").val() + " "
									+ $("#endTimePlan_time").val(),
							usecarreason : $("#useCarReason").val(),
							department : $.cookie("departmentname"),
							applytime : defaulttime,
							applyid : applyId
						}

						$
								.ajax({
									type : "post",
									url : "/HFOA/ModifyApplyInfo",
									dataType : 'json',
									data : dto,
									success : function(data) {
										if (data == "1") {
											alert("修改成功");
											location.href = "/HFOA/view/BorrowCar/BorrowCar.html";
										} else if (data == "0") {
											alert("修改失败，请稍后再试");
											$("#change").removeAttr("disabled");
										} else {

										}
									},
									error : function(data) {
										alert("网络错误，请稍后再试");
										$("#change").removeAttr("disabled");
									}
								});
					}
				});
$(document).on('click', '.delete', function() {
	// 删除按钮的点击事件
	$("#delete").attr("disabled", "disabled");
	var applyId = getUrlParam("ApplyId");
	var dto = {
		applyid : applyId
	};
	$.ajax({
		type : "post",
		url : "/HFOA/DelApply",
		dataType : 'json',
		data : dto,
		success : function(data) {
			if (data == "1") {
				alert("删除成功");
				location.href = "/HFOA/view/BorrowCar/BorrowCar.html";
			} else if (data == "0") {
				alert("删除失败，请稍后再试");
				$("#delete").removeAttr("disabled");
			} else {

			}
		},
		error : function(data) {
			alert("网络错误，请稍后再试");
		}
	});
});

$(document).on('click', '#pass', function() {
	$("#pass").attr("disabled", "disabled");
	var applyId = getUrlParam("ApplyId");
	var dto = {
		applyid : applyId,
		approveman : $("#realApproveMan").val()
	};
	$.ajax({
		type : "post",
		url : "/HFOA/Approve",
		dataType : 'json',
		data : dto,
		success : function(data) {
			if (data == "1") {
				alert("审批通过");
				location.href = "/HFOA/view/BorrowCar/ApproveInfo.html";
			} else if (data == "0") {
				alert("审批失败，请稍后再试");
				$("#pass").removeAttr("disabled");
			} else {

			}
		},
		error : function(data) {
			alert("网络错误，请稍后再试");
		}
	});
});
$(document).on('click', '#overruled', function() {
	$("#overruled").attr("disabled", "disabled");
	var applyId = getUrlParam("ApplyId");
	var dto = {
		applyid : applyId,
		approveman : $("#realApproveMan").val()
	};
	$.ajax({
		type : "post",
		url : "/HFOA/DenyApprove",
		dataType : 'json',
		data : dto,
		success : function(data) {
			if (data == "1") {
				alert("否决成功");
				location.href = "/HFOA/view/BorrowCar/ApproveInfo.html";
			} else if (data == "0") {
				alert("审批失败，请稍后再试");
				$("#pass").removeAttr("disabled");
			} else {

			}
		},
		error : function(data) {
			alert("网络错误，请稍后再试");
		}
	});
});