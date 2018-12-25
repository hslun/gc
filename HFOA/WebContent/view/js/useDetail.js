/**
 * @author Administrator
 */
function getUrlParam(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); // 构造一个含有目标参数的正则表达式对象
	var r = window.location.search.substr(1).match(reg); // 匹配目标参数
	if (r != null)
		return unescape(r[2]);
	return null; // 返回参数值
}
function judgeSelectCar(carCode) {
	if (carCode == "京HV3507") {
		$("#carType-button span").html("切诺基 京HV3507");
	} else if (carCode == "京NH8845") {
		$("#carType-button span").html("GL8 京NH8845");
	} else if (carCode == "京NTU976") {
		$("#carType-button span").html("新捷达 京NTU976");
	} else if (carCode == "京Q1L932") {
		$("#carType-button span").html("奔驰 京Q1L932");
	} else if (carCode == "京QMQ381") {
		$("#carType-button span").html("奔驰 京QMQ381");
	} else {
		
	}
}
function setShowInfo(){
	if(getUrlParam("Action")=="1"){
		
	}else if(getUrlParam("Action")=="2"){
		$("#tr_beginTime").hide();
		$("#tr_endTime").css("display","none");
		$("#tr_lengthBegin").css("display","none");
		$("#tr_lengthEnd").css("display","none");
		$("#tr_accountLength").css("display","none");
		$("#tr_accountRealTime").css("display","none");
//		document.getElementById("tr_accountRealTime").style.visibility="hidden";
//		$("#t").find("#tr_accountRealTime").remove();
//		document.getElementById("t").deleteRow(1);
	}else{
		
	}
}
// 传递过来applyId
$(document)
		.on(
				'pageinit',
				'#UseDetail',
				function() {
					setShowInfo();
					var dto = {
						applyid : getUrlParam("ApplyId")
					}
					$
							.ajax({
								type : "post",
								url : "/HFOA/DetailInfo",
								dataType : 'json',
								data : dto,
								success : function(data) {
									if (data.length == 0) {

									} else {
										$(data).each(
														function() {
															judgeSelectCar(this.carCode);
															$("#applyMan")
																	.val(
																			""
																					+ this.applyMan);
															$("#realApproveMan")
																	.val(
																			""
																					+ this.realApproveMan);
															$("#driver")
																	.val(
																			""
																					+ this.driver);
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
															$(
																	"#beginTimePlan_date")
																	.val(
																			""
																					+ (this.beginTimePlan
																							.split(" "))[0]);
															$(
																	"#beginTimePlan_time")
																	.val(
																			""
																					+ (this.beginTimePlan
																							.split(" "))[1]);
															$(
																	"#endTimePlan_date")
																	.val(
																			""
																					+ (this.endTimePlan
																							.split(" "))[0]);
															$(
																	"#endTimePlan_time")
																	.val(
																			""
																					+ (this.endTimePlan
																							.split(" "))[1]);
															// 借出时间 以及还车时间
														if(getUrlParam("Action")=="2"){
															
														}else{
															
															$("#beginTime_date")
																	.val(
																			""
																					+ (this.beginTime
																							.split(" "))[0]);
															$("#beginTime_time")
																	.val(
																			""
																					+ (this.beginTime
																							.split(" "))[1]);
															$("#endTime_date")
																	.val(
																			""
																					+ (this.endTime
																							.split(" "))[0]);
															$("#endTime_time")
																	.val(
																			""
																					+ (this.endTime
																							.split(" "))[1]);

															$("#lengthBegin")
																	.val(
																			""
																					+ this.lengthBegin);
															$("#lengthEnd")
																	.val(
																			""
																					+ this.lengthEnd);
															$("#accountLength")
																	.val(
																			""
																					+ this.accountLength);
															$(
																	"#accountRealTime")
																	.val(
																			""
																					+ this.accountRealTime);
														}
															$("#useCarReason")
																	.val(
																			""
																					+ this.useCarReason);
														});
									}

								},
								error : function(data){
									alert("网络错误");
								}
							});
				});

$(document).on('click', '#back', function() {
	if(getUrlParam("Action") == "1"){
		// 顶部返回按钮的点击事件
		location.href = "/HFOA/view/BorrowCar/Search.html";
	}else if(getUrlParam("Action") == "2"){
		location.href = "/HFOA/view/BorrowCar/CarStatus.html";
	}else{
		
	}

});