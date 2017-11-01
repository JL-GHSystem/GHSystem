/**
 * 
 */
var error = {
	s1: "不可为空"
}

function checkForm(){
	var pass = true;
	$("p.info").html("");
	$.each($("input.NoEmpty"), function(i, item){
		if($.isEmptyObject($(item).val())) {
			var id = $(item).attr("name");
			$(item).addClass("error");
			pass = false;
			if($("label#" + id).length != 0) {
				$("p.info").append($("label#" + id).html() + "&nbsp;&nbsp;、&nbsp;&nbsp;");
			}
			else {
				$("p.info").append($("label[for=" + id + "]").html() + "&nbsp;&nbsp;、&nbsp;&nbsp;");
			}
		}
	})
	if(!pass) {
		$("p.info").append(error.s1 + "<br>");
	}
	return pass;
}

$(document).ready(function(){
	F.init("../../../lib/FrichUI/", F.FRICHUI_THEME_DARK);
	
})