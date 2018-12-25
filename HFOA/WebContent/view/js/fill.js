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
function hideHint() {
	var inputInfo = document.getElementById("inputInfo");
	if ($("#inputInfo").val() == '') {
		$("#inputInfo").css("color", "#999999");
		judgeHintInfo();
	}
}
function judgeHintInfo() {
	if ($("#kind").val() == "出差") {
		$("#inputInfo").val("请输入出差地点");
	} else if ($("#kind").val() == "销售") {
		$("#inputInfo").val("请输入客户名称");
	} else if ($("#kind").val() == "会议") {
		$("#inputInfo").val("请输入会议名称");
	} else if ($("#kind").val() == "试验") {
		$("#inputInfo").val("请输入试验名称");
	} else if ($("#kind").val() == "其他") {
		$("#inputInfo").val("请输入类型描述");
	} else {

	}
}
function showHint() {
	var inputInfo = document.getElementById("inputInfo");
	if (inputInfo.value == "请输入出差地点" || inputInfo.value == "请输入客户名称"
			|| inputInfo.value == "请输入会议名称"
			|| inputInfo.value == "请输入试验名称"
			|| inputInfo.value == "请输入类型描述") {
		$("#inputInfo").val("");
		$("#inputInfo").css("color", "black");
	}
}

function setDateTime() {
	initDate();
	var now = new Date();
	var defaulttime = now.format("yyyy-MM-dd");
	$("#date").val("" + defaulttime);
	
}
function getMyTime(){
	initDate();
	var now = new Date();
	var mytime = now.format("yyyy-MM-dd hh:mm:ss");
	return mytime;
}

function JudgeEmpty() {
	if ($("#date").val() == "" || $("#date").val() == null) {
		alert("请输入日期");
		return false;
	}
	if ($("#inputInfo").val() == "" || inputInfo.value.indexOf("请输入") >= 0) {
		alert("请输入类型信息");
		//jAlert('这是一个可自定义样式的警告对话框', '警告对话框');
		return false;
	} else {
		if ($("#inputInfo").val().length < 2
				|| $("#inputInfo").val().length > 20) {
			alert("类型信息请在2到20个字符之间");
			return false;
		} else {
			return true;
		}
	}
	if ($("#description").val() == "") {
		alert("请输入描述信息");
		return false;
	} else {
		if ($("#description").val().length < 8
				|| $("#description").val().length > 50) {
			alert("描述信息请在8到50个字符之间");
			return false;
		} else {
			return true;
		}
	}
	return true;
}
$(document).on('pageinit', '#fill', function() {
	setDateTime();
	
});

$(document).on('click', '#submit', function() {
	// 输入检查
	
	$("#submit").attr("disabled", "disabled");
	if(!JudgeEmpty()){
		$("#submit").removeAttr("disabled");
		location.href="Fill.html";
	}else{
	var dto = {
		Kind : $("#kind  :selected").val(),
		Day : $("#date").val(),
		Kdescrib : $("#inputInfo").val(),
		FillTime : getMyTime(),
		Describe : $("#description").val()
	}

	$.ajax({
		type : "post",
		url : "/HFOA/web/Fill",
		dataType : 'json',
		data : dto,
		success : function(data) {

			if (data == 1) {
				alert("提交成功!");
				// 更新页面
				setDateTime();
				$("#description").val("");
				$("#submit").removeAttr("disabled");
			} else if (data == 0) {
				alert("提交失败,请重新提交!");
				$("#submit").removeAttr("disabled");
			} else if (data == 3) {
				alert("网页已经过期,请重新登录!");
				$("#submit").removeAttr("disabled");
				location.href = "Login.html?Action=1";
			}
		},
		error : function(data) {
			alert("网络错误,请检查网络!");
		}
	})
	}
});
$(document).on('click', '#reset', function() {

});