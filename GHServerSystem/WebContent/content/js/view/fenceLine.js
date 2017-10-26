/**
 * 
 */
function update() {
	var id = F.entitys("table2").getSingleValue(3);
	var fid = F.entitys("table2").getSingleValue(4);
	var cname = F.entitys("table2").getSingleValue(5);
	var name = F.entitys("table2").getSingleValue(6);
	$.ajax({
		type: "POST",
	    url: "../json/fence.do",
	    data: {
	    	type: "fenceInLineDetail",
	    	id: id,
	    	fid: fid
	    },
	    success: function (data) {
	    	F.Dialog.make(undefined, {
			title: "修改围栏绑定数据",
			type: "customer",
			url: "view/form/fenceLine/update.html",
			frameId: "fenceLineUpdate",
			frameWidth: 640,
			frameHeight: 360,
			data: $.extend(true, {}, {O_DEPARTNAME: name, O_FENCENAME: cname}, data.o),
			enableCover: true,
			closeClick: function(){
				return 0;
			},
			confirmClick: function(doc, fa){
				var form = $(doc).contents().find("form");
				var a = form.serialize();
				$.ajax({
					type: "POST",
				    url: "../json/fence.do",
				    data: a + "&type=updateBind",
				    success: function (data) {
				    	
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
	    },
	    error: function (err) {
	    	F.Affair.make(undefined, {
	    		type: "error",
	    		message: "获取围栏绑定数据失败",
	    		reload: false
	    	})
	    }
	});
}

function bind() {
	var id = F.entitys("table1").getSingleValue(3);
	var name = F.entitys("table1").getSingleValue(5);
	if(F.istEmpty(id)) {
		F.Dialog.make(undefined, {
			title: "围栏绑定",
			type: "customer",
			url: "view/form/fenceLine/add.html",
			frameId: "fenceLineAdd",
			frameWidth: 640,
			frameHeight: 360,
			data: {
				O_DEPARTID: id,
				O_DEPARTNAME: name
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
				    url: "../json/fence.do",
				    data: a + "&type=bind",
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
	else {
    	F.Affair.make(undefined, {
    		type: "error",
    		message: "请选择要绑定的线路",
    		reload: false
    	})
	}
}

function unbind() {
	var id = F.entitys("table2").getSingleValue(3);
	var fid = F.entitys("table2").getSingleValue(4);
	
	$.ajax({
		type: "POST",
	    url: "../json/fence.do",
	    data: {
	    	type: "unbind",
	    	id: id,
	    	fid: fid
	    },
	    success: function (data) {
	    	clearAll();
	    	F.Affair.make(undefined, {
	    		type: "success",
	    		message: "解绑成功",
	    		reload: true
	    	})
	    },
	    error: function (err) {
	    	F.Affair.make(undefined, {
	    		type: "error",
	    		message: "解绑失败",
	    		reload: false
	    	})
	    }
	});
	
}

function searchMap(){
	var id = F.entitys("table2").getSingleValue(3);
	
	$.ajax({
		type: "POST",
	    url: "../json/fence.do",
	    data: {
	    	type: "detail",
	    	id: id
	    },
	    success: function (data) {
	    	clearAll();
	    	show_fence(data.o);
	    },
	    error: function (err) {
	    	F.Affair.make(undefined, {
	    		type: "error",
	    		message: "必须选择一条线路来查看围栏对象",
	    		reload: false
	    	})
	    }
	});
	
}

function searchCurrent(){
	var id = F.entitys("table1").getSingleValue(3);
	if(F.istEmpty(id)) {
		F.Table.make($("#table2"), {
			enableAutoSerial: true,
			enableSingleSelect: true,
			enableMuiltSelect: true,
			showSingleSelect: true,
			enableAjax: true,
			title: "调度管理",
			models: [{
				name: "id",
				hide: true
			},{
				name: "fid",
				hide: true
			},{
				name: "调度区域"
			},{
				name: "所属部门"
			},{
				name: "区域类型"
			}],
			pagination: {
				rows: 5
			},
			ajaxType: "fenceInLine",
			ajax: {
				url: "../json/fence.do",
				data: {
					id: id
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
	else {
    	F.Affair.make(undefined, {
    		type: "error",
    		message: "必须选择一条线路来查看围栏对象",
    		reload: false
    	})
	}
}

function searchAll(){
	F.Table.make($("#table2"), {
		enableAutoSerial: true,
		enableSingleSelect: true,
		enableMuiltSelect: true,
		showSingleSelect: true,
		enableAjax: true,
		title: "调度管理",
		models: [{
			name: "id",
			hide: true
		},{
			name: "fid",
			hide: true
		},{
			name: "调度区域"
		},{
			name: "所属线路"
		},{
			name: "区域类型"
		}],
		pagination: {
			rows: 5
		},
		ajaxType: "fenceInLine",
		ajax: {
			url: "../json/fence.do",
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

function ajax(){

	var data = {
		type: "table"
	}
	
	F.Table.make($("#table1"), {
		enableAutoSerial: true,
		enableSingleSelect: true,
		enableMuiltSelect: true,
		showSingleSelect: true,
		enableAjax: true,
		title: "线路管理",
		models: [{
			name: "id",
			hide: true
		},{
			name: "fid",
			hide: true
		},{
			name: "线路名"
		},{
			name: "所属部门名"
		},{
			name: "部门类型"
		},{
			name: "部门路径",
			textAlign: "left"
		}],
		pagination: {
			rows: 10
		},
		ajax: {
			url: "../json/line.do",
			data: data,
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
	searchAll();
}

$(document).ready(function(){
	ajax();
	
	$(".searchA").click(searchAll);
	$(".searchC").click(searchCurrent);
	$(".searchM").click(searchMap);
	
	$("#update").click(update);
	$("#unbind").click(unbind);
	$("#bind").click(bind);
	
/*
	$("#select").click(select);
	*/
});