/**
 * 
 */
var common = {
	height: function (div){
		var realHeight = $(div).height();
		var borderHeight = parseInt($(div).css("border-top-width")) 
		+ parseInt($(div).css("border-bottom-width"));
		var paddingHeight = parseInt($(div).css("padding-top")) 
		+ parseInt($(div).css("padding-bottom"));
		return realHeight + borderHeight + paddingHeight;
	},
	width: function (div){
		var realWidth = $(div).width();
		var borderWidth = parseInt($(div).css("border-left-width")) 
		+ parseInt($(div).css("border-right-width"));
		var paddingWidth = parseInt($(div).css("padding-left")) 
		+ parseInt($(div).css("padding-right"));
		return realWidth + borderWidth + paddingWidth;
	}
}


var H = {
	getAll: function(customer){
		var defaul = {
			type: "POST"			
		}
		options = $.extend(true, {}, defaul, customer);
		
		$.ajax({
			type: options.type,
		    url: options.url,
		    data: options.data,// 要提交的表单
		    beforeSend: function(){
		    	options.before();
		    },
		    success: function (date) {
		    	options.success(date);
		    },
		    error: function (err) {
		    	options.error(err);
		    }
		});
	}
}