/**
 * 
 */
function add(){
	F.Dialog.make(undefined, {
		title: "新建菜单",
		type: "customer",
		url: "view/form/menu/add.html",
		frameId: "menuAdd",
		frameWidth: 640,
		frameHeight: 360,
		data: {},
		enableCover: true,
		closeClick: function(){
			return 0;
		},
		confirmClick: function(form, fa){
			

			fa.clear();
		},
		cancleClick: function(){
			return 0;
		}
	});
}

function update(){
	F.Dialog.make(undefined, {
		title: "更新菜单",
		type: "customer",
		url: "view/form/menu/add.html",
		frameWidth: 640,
		frameHeight: 360,
		data: {},
		enableCover: true,
		closeClick: function(){
			alert("点击了关闭");
		},
		confirmClick: function(){
			alert("点击了确定");
		},
		cancleClick: function(){
			alert("点击了取消");
		}
	});
}

function deleted(){
	F.Dialog.make(undefined, {
		title: "删除菜单",
		type: "customer",
		url: "view/form/menu/add.html",
		frameWidth: 640,
		frameHeight: 360,
		data: {},
		enableCover: true,
		closeClick: function(){
			alert("点击了关闭");
		},
		confirmClick: function(){
			alert("点击了确定");
		},
		cancleClick: function(){
			alert("点击了取消");
		}
	});
}

function ajax(){

	var data = {
		type: "table"
	}
	
	$.ajax({
		type: "POST",
	    url: "../json/menu.do",
	    data: data,
	    beforeSend: function(){
	    	
	    },
	    success: function (data) {
	    	F.Table.make($("#table"), {
				loadPagination: false,
	    		enableAutoSerial: true,
	    		enableSingleSelect: true,
	    		showSingleSelect: true,
	    		models: [{
	    			name: "id",
	    			hide: true
	    		}, {
	    			name: "菜单名"
	    		}, {
	    			name: "上级菜单"
	    		}, {
	    			name: "菜单级别"
	    		}, {
	    			name: "菜单排序",
	    			textAlign: "left"
	    		}, {
	    			name: "菜单映射",
	    			textAlign: "left"
	    		}, {
	    			name: "菜单状态",
	    			boolDisplay: ["已启用", "未启用"]
	    		}],
	    		data: data.o
	    	});
	    },
	    error: function (err) {
	    	
	    }
	});
}

$(document).ready(function(){
	ajax();
	
	$("#add").click(add);

	$("#update").click(update);

	$("#delete").click(deleted);
/*
	$("#select").click(select);
	*/
});