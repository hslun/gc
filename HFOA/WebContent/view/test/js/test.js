
//获取Url传入的值
var id = getURLParam("id");

//页面加载时
$(document).ready(function (){
	//服务端传值参数
	var dto={
			userName:"张三",
			userCode:"zhangsan"
	};
	//ajax请求
	jQuery.ajax({
		type : "POST",
		url : "/hfga/test/getTest",
		dataType : 'json',
		data : dto,
		success : function(data) {
			/**
			 * //data为服务端返回的数据
			 * //如果服务端返回数据为数组类型，可用each进行遍历（json）
			 */
			/*$(data).each(function(){ 
				//jquery方式给html元素进行赋值
				$(".title").html(this.userName);
				$(".title").val(this.userName);
//				$(document).attr("title",this.theme);
				删除
				$this.remove();
				//遍历data下items元素
				for(var s in this.items){
					//值
					this.items[s].id;
				}
			}); */
			/**
			 * //遍历服务端返回的data数据中的items元素
			 */
			/*var msgs=data.items;
			$(msgs).each(function(){ 
				
			});*/
		}
/*	,
		//返回值为boolean类型时
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			 if(XMLHttpRequest.status==200){
				 alert("success");
				 history.go(-1);
			 }
		}*/

	});
	/**
	 * //html页面中button&html元素中 click事件
	 */
	$(".back_btn").on("click",function(){
		//页面跳转
//		window.open("../***.html","_self");
//		history.go(-1);
//		window.location.href="success.html";

		/**
		 * 获取复选框或单选值
		 */
		/*$('input[name="radiobutton"]:checked').each(function(){
			str = this.value;
		});*/

		
	});
});
//表单验证
$("form").submit(function(e){
	 if($("#test").val()==""){
		 alert('不能为空');
		 //返回文本框光标
		 $("#test").focus();
       return false;
	 }
});

/**
 * d3的使用
 */
//获取test.html中div标签
//var div = d3.select(".select");
//创建标签p,class为user_info,id为userName,style样式
//div.append("p").classed("user_info",true).attr("id","userName").style("float","right").style("display","none");



/**
 * common.js下的ajax使用
 */

/*
  doJsonRequest("/test/getTest",dto,function(data,status){
	if(status=="success"){
		alert(data);
	}else{
		alert("error");
	}
});
*/






