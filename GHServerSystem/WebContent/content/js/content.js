/**
 * 
 */
$(document).ready(function(){
	F.init("../lib/FrichUI/", F.FRICHUI_THEME_DARK);
	
	$("a.delete").click(function(){
		var p = $(this).parent();
		p.find("a").hide();
		p.find("a.complete").fadeIn(500);
		p.find("a.cancle").fadeIn(500);
	});
	
	$("a.complete").click(function(){
		var p = $(this).parent();
		p.find("a").hide();
		p.find("a.add").fadeIn(500);
		p.find("a.update").fadeIn(500);
		p.find("a.delete").fadeIn(500);
		p.find("a.search").fadeIn(500);
		p.find("a.input").fadeIn(500);
		p.find("a.output").fadeIn(500);
		p.find("a.download").fadeIn(500);
		p.find("a.hide").fadeIn(500);
	});
	
	$("a.cancle").click(function(){
		var p = $(this).parent();
		p.find("a").hide();
		p.find("a.add").fadeIn(500);
		p.find("a.update").fadeIn(500);
		p.find("a.delete").fadeIn(500);
		p.find("a.search").fadeIn(500);
		p.find("a.input").fadeIn(500);
		p.find("a.output").fadeIn(500);
		p.find("a.download").fadeIn(500);
		p.find("a.hide").fadeIn(500);
	});
	
	$("a.hide").click(function() {
		var i = $(this).find("i");
		var l = $(this).find("label");
		if(l.html() == "隐藏") {
			$("#selection").hide();
			l.html("展开");
		}
		else {
			$("#selection").fadeIn(500);
			l.html("隐藏");
		}
	})
});






