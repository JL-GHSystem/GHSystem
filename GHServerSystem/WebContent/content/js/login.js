/**
 * 
 */
/* 样式处理器 */
$(document).ready(function(){	
	
	
});

/* 事件处理器 */
$(document).ready(function(){
	
	$("#submit").click(function(){
		var fa = $(this);
		
		if(!fa.hasClass("loading")){
			var obj = $("#Login").serialize();
			$.ajax({
				type: "POST",
			    url: "login.do",
			    data: obj,// 要提交的表单
			    beforeSend: function(){
			    	fa.removeClass();
			    	fa.addClass("loading");
			    },
			    success: function (date) {
			    	fa.removeClass();
			    	if(date.r == 0){
			    		$("#LoginInfo").html("发生未知错误");
			    	}
			    	else if(date.r == 1){
			    		$("#LoginInfo").html();
				    	fa.html("登录成功");
				    	fa.addClass("success");
				    	window.location.href = "main.html";
				    	$.cookie("user", JSON.stringify(date.o));
			    	}
			    	else if(date.r == 2){
			    		$("#LoginInfo").html(date.m);
			    	}
			    	else if(date.r == 3){
			    		$("#LoginInfo").html(date.m);
			    	}
			    },
			    error: function (err) {
			    	fa.removeClass();
		    		$("#LoginInfo").html("连接服务器失败，请检查网络设置");
			    }
			});
		}
	});
	
	
});