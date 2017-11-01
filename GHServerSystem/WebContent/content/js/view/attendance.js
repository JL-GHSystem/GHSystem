/**
 * 
 */
function searchScheme() {
	var code = F.entitys("table1").getSingleValue(5);
	
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
function searchFence(){
	var code = F.entitys("table2").getSingleValue(3);
	var no = F.entitys("table2").getSingleValue(4);
	
	F.Table.make($("#table3"), {
		loadPagination: false,
		enableSingleSelect: true,
		enableMuiltSelect: true,
		showSingleSelect: true,
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
			name: "调度序号"
		},{
			name: "调度名"
		},{
			name: "围栏名"
		},{
			name: "围栏类型"
		}],
		ajax: {
			url: "../json/scheme.do",
			data: {
				type: "fenceTable",
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
function deleteScheme() {
	
}
function ajax(){
	
	F.Table.make($("#table1"), {
		enableAutoSerial: true,
		enableSingleSelect: true,
		enableMuiltSelect: true,
		showSingleSelect: true,
		enableAjax: true,
		title: "选择部门",
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
				type: "schemeInLine"
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
		title: "考勤查询",
		data: null
	});
}

$(document).ready(function(){
	ajax();
	
	$("#searchS").click(searchScheme);
	$("#searchF").click(searchFence);
	
	$("#deleteS").click(deleteScheme);
	
});