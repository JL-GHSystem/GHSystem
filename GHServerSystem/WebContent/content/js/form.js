/**
 * 
 */
var error = {
	s1: "不可为空"
}

function checkForm(){
	var a = true;
	$.each($("input.NoEmpty"), function(i, item){
		if($.isEmptyObject($(item).val())) {
			$(item).addClass("error");
			a = false;
			$("p.info").append($(item).prev("label").html() + error.s1 + "<br>");
		}
	})
	return a;
}

$(document).ready(function(){
	F.init("../../../lib/FrichUI/", F.FRICHUI_THEME_DARK);
	
})