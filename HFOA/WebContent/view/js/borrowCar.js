/**
 * @author Administrator
 */
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
$(document)
		.on(
				'pageinit',
				'#BorrowCar',
				function() {
					// 首先判断cookie是否存在
					if ($.cookie("username") == "null"
						|| $.cookie("username") == ""||$.cookie("username") == null) {
						// 如果不存在，跳转到登录界面
						alert("用户信息已失效，请重新登录");
						location.href = "/HFOA/view/Manhour/Login.html?Action=100";
						return;
					} else {
						// 如果存在，什么也不做
					}

					// 向服务器提交数据
					var dto = {
						applyname : $.cookie("realname")
					}
					var index = 0;
					$
							.ajax({
								type : "post",
								url : "/HFOA/ApplyInfo",
								dataType : 'json',
								data : dto,
								success : function(data) {
									// 遍历服务器返回的数据添加到页面中
									if(data.length == 0){
										$("#info").html("暂无借用信息");
									}else{
									$(data)
											.each(
													function() {
														// 从服务器获取当前用户信息
														index++;
														var applyId = this.applyId;
														var state;
														if (this.approveState == "通过") {
															state = 1;
														} else if (this.approveState == "被否决") {
															state = 2;
														} else if (this.approveState == "待审批") {
															state = 0;
														} else {

														}
														var list = $("<li style='vertical-align: middle;' id='"
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
																+ this.approveState
																+ "</p></td><td align='right'><p>"
																+ this.beginTimePlan
																+ "</p>"
																+ "</td></tr></table></a></li>");
														var str = "#option"
																+ index;
														$("#list").append(list);
														$('ul').listview(
																'refresh');
														$("#list").find(
																"li:last")
																.slideDown(300);
														$(str)
																.on(
																		"tap",
																		function() {
																			location.href = "ApplyInfo.html?Action=1&ApplyId="
																					+ applyId
																					+ "&state="
																					+ state;
																		});
													});
									}
								},
								error : function(data) {
									alert("网络错误");
								}
							});
					// 为列表选项添加点击跳转事件

				});

$(document).on('click', '#newCar', function() {
	location.href = "ApplyInfo.html?Action=0";
});