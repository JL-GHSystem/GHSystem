/**
 * 
 */
function initLayout(){
	var view = $(window).height();
	
	var header = common.height($(".head"));
	var headerw = common.width($(".head"));
	
	$(".main").height(view - header);
	$(".main").width(headerw - 220);
	
	$(".main").css({
		top: header + "px"
	})
	//$(".logo").width(width($(".navigation")));
	
	var userHeight = common.height($(".userFrame"));
	$(".userFrame").css({"height": "50px"});
	
	$(".userFrame").hover(function(){
		$(this).stop();
		$(this).animate({
			"height": userHeight + "px"
		}, (userHeight-50) * 2.5);
	}, function(){
		$(this).stop();
		$(this).animate({
			"height": "50px"
		}, (userHeight-50) * 2.5);
	});
}

$(document).ready(function(){
	
	initLayout();
	$(window).resize(initLayout);
	
	//cookie读取用户名
	var d = JSON.parse($.cookie("user"));
	$("#darling").html(d.darling);
	
	//退出登录请求
	$(".toolBox").click(function(){
    	window.location.href = "json/logout.do";
	});
	
	//建立Menu菜单
	
	
});