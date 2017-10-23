/**
 * 
 */
function add(){
	F.Dialog.make(undefined, {
		title: "新建角色",
		type: "customer",
		url: "view/form/role/add.html",
		frameId: "roleAdd",
		frameWidth: 640,
		frameHeight: 360,
		data: {},
		enableCover: true,
		closeClick: function(){
			return 0;
		},
		confirmClick: function(doc, fa){
			var rs = doc.contentWindow.select();
			var data = {
		    	type: "add",
		    	O_USERGROUPNAME: rs.name,
		    	O_USERGROUPIDS: rs.id
		    };
			$.ajax({
				type: "POST",
			    url: "../json/role.do",
			    data: data,
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
			title: "修改角色",
			type: "customer",
			url: "view/form/role/update.html",
			frameId: "roleUpdate",
			frameWidth: 640,
			frameHeight: 360,
			data: {
				O_USERGROUPID: pack.o_USERGROUPID,
				O_USERGROUPNAME: pack.o_USERGROUPNAME 
			},
			enableCover: true,
			closeClick: function(){
				return 0;
			},
			confirmClick: function(doc, fa){
				var form = $(doc).contents().find("form");
				var a = form.serialize();
				$.ajax({
					type: "POST",
				    url: "../json/role.do",
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
		title: "删除角色",
		message: "此操作将会影响当前该角色组的用户，确定删除？",
		type: "warnning",
		enableCover: true,
		closeClick: function(){
			
		},
		confirmClick: function(doc, fa){
			$.ajax({
				type: "POST",
			    url: "../json/role.do",
			    data: {
			    	type: "delete",
			    	O_USERGROUPIDS: d
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

function ajax(){
	
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
			name: "部门路径",
			textAlign: "left"
		}],
		ajax: {
			url: "../json/department.do",
			data: null,
			beforeSend: function() {
				
			},
		    success: function (data, options) {
		    	options.data = data.o.data;
		    	var pages = options.pagination.pages;
		    	options.pagination = {
					total: data.o.pagination.total,
					pages: pages,					//当前页基础上显示几页 > 0
					records: data.o.pagination.records,
					current: data.o.pagination.current,
					rows: data.o.pagination.rows
				}
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

$(document).ready(function(){
	ajax();
	
	$("#add").click(add);

	$("#update").click(update);

	$("#delete").click(function(){
		$("#complete").fadeIn(500);
		$("#cancle").fadeIn(500);
		$("#add").hide();
		$("#update").hide();
		$("#delete").hide();
		deleted();
	});
	
	$("#complete").click(function(){
		$("#complete").hide();
		$("#cancle").hide();
		$("#add").fadeIn(500);
		$("#update").fadeIn(500);
		$("#delete").fadeIn(500);
		complete();
	});
	
	$("#cancle").click(function(){
		$("#complete").hide();
		$("#cancle").hide();
		$("#add").fadeIn(500);
		$("#update").fadeIn(500);
		$("#delete").fadeIn(500);
		cancle();
	});
/*
	$("#select").click(select);
	*/
});