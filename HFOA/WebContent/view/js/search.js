/**
 * @author Administrator
 */
function initDepartment() {
	var departmentid = $.cookie("departmentid");
	if (departmentid == "1") {
			$("#department").val("101");
			$("#department-button span").html("全部");
		} else if (departmentid == "2") {
			$("#department").val("102");
			$("#department-button span").html("综合办公室");
		} else if (departmentid == "3") {
			$("#department").val("103");
			$("#department-button span").html("财务部");
		} else if (departmentid == "5") {
			$("#department").val("104");
			$("#department-button span").html("通航项目部");
		} else if (departmentid == "6") {
			$("#department").val("105");
			$("#department-button span").html("机电产品部");
		} else if (departmentid == "7") {
			$("#department").val("106");
			$("#department-button span").html("信息技术部");
		} else if (departmentid == "8") {
			$("#department").val("107");
			$("#department-button span").html("无人机项目部");
		} else {
			$("#department").attr("disabled", "disabled");
		}
	}
function judgeCarImg(carCode){
	var imgSrc = '';
	if(carCode == "京NTU976"){
		imgSrc = '../../img/BorrowCarImage/jieda.jpg';
	}else if(carCode == "京HV3507"){
		imgSrc = '../../img/BorrowCarImage/jeep.jpg';
	}else if(carCode == "京Q1L932"||carCode == "京QMQ381"){
		imgSrc = '../../img/BorrowCarImage/benchi_s.jpg';
	}else if(carCode == "京NH8845"){
		imgSrc = '../../img/BorrowCarImage/bieke.jpg';
	}else{
		
	}
	return imgSrc;
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
function getLastMonthYestdy(date) {
	var daysInMonth = new Array([ 0 ], [ 31 ], [ 28 ], [ 31 ], [ 30 ], [ 31 ],
			[ 30 ], [ 31 ], [ 31 ], [ 30 ], [ 31 ], [ 30 ], [ 31 ]);
	var strYear = date.getFullYear();
	var strDay = date.getDate();
	var strMonth = date.getMonth() + 1;
	if (strYear % 4 == 0 && strYear % 100 != 0) {
		daysInMonth[2] = 29;
	}
	if (strMonth - 1 == 0) {
		strYear -= 1;
		strMonth = 12;
	} else {
		strMonth -= 1;
	}
	strDay = daysInMonth[strMonth] >= strDay ? strDay : daysInMonth[strMonth];
	if (strMonth < 10) {
		strMonth = "0" + strMonth;
	}
	if (strDay < 10) {
		strDay = "0" + strDay;
	}
	datastr = strYear + "-" + strMonth + "-" + strDay;
	return datastr;
}
function setDateTime() {
	initDate();
	var now = new Date();
	var defaulttime = now.format("yyyy-MM-dd");
	$("#starttime").val("" + getLastMonthYestdy(now));
	$("#endtime").val("" + defaulttime);
}
function judgeRole() {
	var roleId = $.cookie("roleid");
	if (roleId == "1") {
		$("#department").attr("disabled", "disabled");
		$("#applyMan").attr("disabled", "disabled");
	} else if (roleId == "2") {
		$("#department").attr("disabled", "disabled");
	} else if (roleId == "3") {

	} else {

	}
}

function initPageInfo() {
	initDepartment();
	$("#applyMan").val("" + $.cookie("realname"));
	setDateTime();
	judgeRole();
}
$(document).on('pageinit', '#Search', function() {
	// 首先判断cookie是否存在
	if ($.cookie("username") == "null"
			|| $.cookie("username") == ""||$.cookie("username") == null) {
		// 如果不存在，跳转到登录界面
		
		alert("用户信息已失效，请重新登录");
		location.href = "/HFOA/view/Manhour/Login.html?Action=102";
	} else {
		// 如果存在，什么也不做
	}
	
	initPageInfo();
	$(document).on("pagehide", "#result", function() {
		$("#list").empty();
	});
});

var index = 0;
$(document).on('click', '#submit', function() {
	// 查询按钮的点击事件
	$("#submit").attr("disabled", "disabled");
	var dto = {
		depatment : $("#department-button span").html(),
		starttime : $("#starttime").val(),
		endtime : $("#endtime").val(),
		applyman : $("#applyMan").val(),
		carinfo : $("#carType-button span").html()
	}

	$.ajax({
		type : "post",
		url : "/HFOA/CarInfoDetail",
		dataType : 'json',
		data : dto,
		success : function(data) {
			if (data.length == 0) {
				location.href="#result";
				$("#info").html("暂无数据显示");
				$("#submit").removeAttr("disabled");
			} else {
		//		result = data;
				$.mobile
				.changePage(
						"../../view/BorrowCar/Search.html#result",
						{
							transition : "slideup"
						});
				$("#submit").removeAttr("disabled");
				var list="";
				
				$(data).each(
						function() {
							// 从服务器获取当前用户信息
							index++;
							var applyId = this.applyId;
							list = $("<li style='vertical-align: middle;' id='"
									+ 'option'
									+ index
									+ "' ><a href='#'>"
									+ "<table width='100%'>"
									+ "<tr>"
									+ "<td rowspan='3' style='width: 20%;'><img alt='车辆图片'"
									+ "id='carImg' src='"
									+ judgeCarImg(this.carCode)
									+ "'"
									+ "style='padding-right: 10px;'></img></td>"
									+ "</tr><tr><td align='left'><h3>"
									+ this.carCode
									+ "</h3></td>"
									+ "<td align='right'><h3>"
									+ this.endAddress
									+ "</h3></td></tr><tr><td align='left'>"
									+ "<p>"
									+ this.applyMan
									+ "</p></td><td align='right'><p>"
									+ this.beginTimePlan
									+ "</p>"
									+ "</td></tr></table></a></li>");
							var str = "#option" + index;
							$("#list").append(list);
							$('ul').listview('refresh');
							$("#list").find("li:last").slideDown(300);
							$(str).on("tap",function() {
												location.href = "UseDetail.html?Action=1&ApplyId="
														+ applyId;
											});
						});
			}
		},
		error : function(data) {
			alert("网络错误");
			$("#submit").removeAttr("disabled");
		},
	});
});
$(document).on('click', '#back', function() {
	// 顶部返回按钮的点击事件
	location.href = "/HFOA/view/BorrowCar/Search.html";
});
$(document).on('pageinit', '#result', function() {
	
});
