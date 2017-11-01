/**
 * 
 */
function searchScheme() {
	var code = F.entitys("table1").getSingleValue(5);
	if(F.istEmpty(code)) {
		F.Table.make($("#table2"), {
			enableAutoSerial: true,
			loadPagination: false,
			enableSingleSelect: true,
			enableMuiltSelect: true,
			showSingleSelect: true,
			enableAjax: true,
			title: "方案管理",
			models: [{
				name: "code",
				hide: true
			},{
				name: "name",
				hide: true
			},{
				name: "mid",
				hide: true
			},{
				name: "方案名"
			},{
				name: "是否营运"
			},{
				name: "非营运类型"
			},{
				name: "运行方向"
			},{
				name: "标准里程"
			},{
				name: "运行时间"
			}],
			ajax: {
				url: "../json/scheme.do",
				data: {
					type: "schemeTable",
					code: code
				},
			    success: function (data, options) {
			    	options.data = data.o;
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
    		message: "请选择线路",
    		reload: false
    	})
	}
}
function searchFence(){
	var code = F.entitys("table2").getSingleValue(3);
	var no = F.entitys("table2").getSingleValue(5);

	if(F.istEmpty(code)) {
		F.Table.make($("#table3"), {
			loadPagination: false,
			enableAjax: true,
			title: "方案调度",
			models: [{
				name: "id",
				hide: true
			},{
				name: "fid",
				hide: true
			},{
				name: "code",
				hide: true
			},{
				name: "绑定序号"
			},{
				name: "调度区域名"
			},{
				name: "围栏名"
			},{
				name: "围栏类型"
			}],
			ajax: {
				url: "../json/scheme.do",
				data: {
					type: "fenceInScheme",
					code: code,
					no: no
				},
			    success: function (data, options) {
			    	options.data = data.o;
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
    		message: "请选择方案",
    		reload: false
    	})
	}
	
}
function addScheme() {
	var code = F.entitys("table1").getSingleValue(5);
	var name = F.entitys("table1").getSingleValue(6);
	if(F.istEmpty(code)){
		F.Dialog.make(undefined, {
			title: "新建方案",
			type: "customer",
			url: "view/form/scheme/add.html",
			frameId: "schemeAdd",
			frameWidth: 640,
			frameHeight: 360,
			data: {
				O_DEPARTCODE: code,
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
				    url: "../json/scheme.do",
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
	else {
    	F.Affair.make(undefined, {
    		type: "error",
    		message: "请选择线路",
    		reload: false
    	})
	}	
}
function updateFence() {
	var code = F.entitys("table2").getSingleValue(3);
	var name = F.entitys("table2").getSingleValue(6);
	if(F.istEmpty(code)) {
		F.Dialog.make(undefined, {
			title: "更改方案调度",
			type: "customer",
			url: "view/form/scheme/select.html",
			frameId: "fenceSelect",
			frameWidth: 640,
			frameHeight: 360,
			data: {
				O_DEPARTCODE: code,
				O_PROGRAMNAME: name
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
				    url: "../json/scheme.do",
				    data: a + "&type=updateFence",
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
    		message: "必须选择一个方案",
    		reload: false
    	})
	}
}
function updateScheme() {
	var code = F.entitys("table2").getSingleRow();
	console.log(code);
	if(F.istEmpty(code)){
		F.Dialog.make(undefined, {
			title: "新建方案",
			type: "customer",
			url: "view/form/scheme/add.html",
			frameId: "schemeAdd",
			frameWidth: 640,
			frameHeight: 360,
			data: code,
			enableCover: true,
			closeClick: function(){
				return 0;
			},
			confirmClick: function(doc, fa){
				var form = $(doc).contents().find("form");
				var a = form.serialize();
				$.ajax({
					type: "POST",
				    url: "../json/scheme.do",
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
    		message: "请选择线路",
    		reload: false
    	})
	}
}
function deleteScheme() {
	F.entitys("table2").openMuiltSelect();
}
function completeScheme() {
	var code = F.entitys("table2").getMuiltValue(3);
	var name = F.entitys("table2").getMuiltValue(6);
	$.ajax({
		url: "../json/scheme.do",
		data: {
			type: "delete",
			O_DEPARTCODE: code,
			O_PROGRAMNAME: name
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
	})
	
	F.entitys("table2").closeMuiltSelect();
	F.entitys("table2").openSingleSelect();
}
function cancleScheme() {
	F.entitys("table2").closeMuiltSelect();
	F.entitys("table2").openSingleSelect();
}
function ajax(){
	
	F.Table.make($("#table1"), {
		enableAutoSerial: true,
		enableSingleSelect: true,
		enableMuiltSelect: true,
		showSingleSelect: true,
		enableAjax: true,
		title: "选择线路",
		models: [{
			name: "id",
			hide: true
		},{
			name: "fid",
			hide: true
		},{
			name: "code",
			hide: true
		},{
			name: "线路名"
		}],
		pagination: {
			rows: 25
		},
		ajax: {
			url: "../json/line.do",
			data: {
				type: "selectLine"
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
	

	F.Table.make($("#table2"), {
		loadPagination: false,
		enableAutoSerial: true,
		enableSingleSelect: true,
		enableMuiltSelect: true,
		showSingleSelect: true,
		title: "方案管理",
		data: null
	});
	
	F.Table.make($("#table3"), {
		loadPagination: false,
		title: "方案调度",
		data: null
	});
}

$(document).ready(function(){
	ajax();
	
	$("#searchS").click(searchScheme);
	$("#searchF").click(searchFence);
	
	$("#addS").click(addScheme);
	
	$("#updateF").click(updateFence);
	$("#updateS").click(updateScheme);
	
	$("#deleteS").click(deleteScheme);
	$("#completeS").click(completeScheme);
	$("#cancleS").click(cancleScheme);
	
});