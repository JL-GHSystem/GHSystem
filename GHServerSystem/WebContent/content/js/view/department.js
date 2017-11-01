/**
 * 
 */
function add(){
	F.Dialog.make(undefined, {
		title: "新建部门",
		type: "customer",
		url: "view/form/department/add.html",
		frameId: "departmentAdd",
		frameWidth: 560,
		frameHeight: 150,
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
			    url: "../json/department.do",
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
			title: "修改角色",
			type: "customer",
			url: "view/form/department/update.html",
			frameId: "departmentUpdate",
			frameWidth: 560,
			frameHeight: 150,
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
				    url: "../json/department.do",
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
	F.entitys("table").openMuiltSelect();
	
}
function complete(){
	var d = F.entitys("table").getMuiltValue(3);
	F.Dialog.make(undefined, {
		title: "删除部门",
		message: "此操作将会删除该部门下所有的车辆和人员数据，确定删除？",
		type: "warnning",
		enableCover: true,
		closeClick: function(){
			
		},
		confirmClick: function(doc, fa){
			$.ajax({
				type: "POST",
			    url: "../json/department.do",
			    data: {
			    	type: "delete",
			    	ids: d
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

function cancle(){
	F.entitys("table").openSingleSelect();
}

function search() {
	var data = {
		type: "search",
		ODEPARTNAME: $("#ODEPARTNAME").val(),
		OPARENTNAME: $("#OPARENTNAME").val()
	}
	
	F.Table.make($("#table"), {
		enableAutoSerial: true,
		enableSingleSelect: true,
		enableMuiltSelect: true,
		showSingleSelect: true,
		enableAjax: true,
		title: "部门管理",
		models: [{
			name: "id",
			hide: true
		},{
			name: "fid",
			hide: true
		},{
			name: "部门名"
		},{
			name: "上级部门名"
		},{
			name: "部门类型"
		},{
			name: "部门编号"
		},{
			name: "部门路径",
			textAlign: "left"
		}],
		pagination: {
			rows: 12
		},
		ajax: {
			url: "../json/department.do",
			data: data,
			beforeSend: function() {
				
			},
		    success: function (data, options) {
		    	options.data = data.o.data;
		    	options.pagination = $.extend(true, {}, options.pagination, data.o.pagination);
		    },
		    error: function (err) {
		    	F.Affair.make(undefined, {
		    		type: "error",
		    		message: "连接服务器失败",
		    		reload: false
		    	})
		    }
		}
	});
}

function load(){
	search();
}

$(document).ready(function(){
	load();
	
	$("#add").click(add);

	$("#search").click(search);
	
	$("#update").click(update);

	$("#delete").click(deleted);
	
	$("#complete").click(complete);
	
	$("#cancle").click(cancle);
	
});