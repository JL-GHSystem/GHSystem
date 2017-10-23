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
		confirmClick: function(doc, fa){
			var form = $(doc).contents().find("form");
			var a = form.serialize();
			$.ajax({
				type: "POST",
			    url: "../json/menu.do",
			    data: a + "&type=add",
			    beforeSend: function(){
			    	
			    },
			    success: function (data) {
			    	if(data.r == "1") {
				    	F.Affair.make(undefined, {
				    		type: "success",
				    		message: data.m,
				    		reload: true
				    	})
			    	}
			    	else {
				    	F.Affair.make(undefined, {
				    		type: "error",
				    		message: data.m,
				    		reload: false
				    	})
			    	}
			    },
			    error: function (err) {
			    	F.Affair.make(undefined, {
			    		type: "error",
			    		message: "连接服务器失败"
			    	})
			    }
			});
			fa.clear();
		},
		cancleClick: function(){
			return 0;
		}
	});
}

function update(){
	var pack = F.entitys("table").getSingleRow();
	if(F.isEmpty(pack)) {
		F.Affair.make(undefined, {
    		type: "error",
    		message: "必须选择一条数据",
    		reload: false
    	});
	}
	else {
		F.Dialog.make(undefined, {
			title: "更新菜单",
			type: "customer",
			url: "view/form/menu/update.html",
			frameId: "menuUpdate",
			frameWidth: 640,
			frameHeight: 360,
			data: pack,
			enableCover: true,
			closeClick: function(){
				return 0;
			},
			confirmClick: function(doc, fa){
				var form = $(doc).contents().find("form");
				var a = form.serialize();
				$.ajax({
					type: "POST",
				    url: "../json/menu.do",
				    data: a + "&type=update",
				    beforeSend: function(){
				    	
				    },
				    success: function (data) {
				    	if(data.r == "1") {
					    	F.Affair.make(undefined, {
					    		type: "success",
					    		message: data.m,
					    		reload: true
					    	})
				    	}
				    	else {
					    	F.Affair.make(undefined, {
					    		type: "error",
					    		message: data.m,
					    		reload: false
					    	})
				    	}
				    },
				    error: function (err) {
				    	F.Affair.make(undefined, {
				    		type: "error",
				    		message: "连接服务器失败",
				    		reload: false
				    	})
				    }
				});
				fa.clear();
			},
			cancleClick: function(){
				return 0;
			}
		});
	}
	
}

function deleted(){
	F.Dialog.make(undefined, {
		title: "删除菜单",
		message: "此操作将会影响所有拥有此权限的用户，确定删除？",
		type: "warnning",
		enableCover: true,
		closeClick: function(){
			
		},
		confirmClick: function(doc, fa){
			$.ajax({
				type: "POST",
			    url: "../json/menu.do",
			    data: {
			    	type: "delete",
			    	O_MENUID: F.entitys("table").getSingleValue(2)
			    },
			    beforeSend: function(){
			    	
			    },
			    success: function (data) {
			    	if(data.r == "1") {
				    	F.Affair.make(undefined, {
				    		type: "success",
				    		message: data.m,
				    		reload: true
				    	})
			    	}
			    	else {
				    	F.Affair.make(undefined, {
				    		type: "error",
				    		message: data.m,
				    		reload: false
				    	})
			    	}
			    },
			    error: function (err) {

			    }
			});
			fa.clear();
		},
		cancleClick: function(){
			
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
	    		title: "菜单管理",
	    		models: [{
	    			name: "id",
	    			hide: true
	    		},{
	    			name: "fid",
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
	    			boolDisplay: ["已启用", "已禁用"]
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