/**
 * 
 */
function add(){
	F.Dialog.make(undefined, {
		title: "新建计划",
		type: "customer",
		url: "view/form/running/add.html",
		frameId: "runningAdd",
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
			    url: "../json/run.do",
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

}

function search(){
	var muilt = [];
	$("input[name=O_DIRECTION]").each(function(){
		if($(this).prop("checked")) {
			muilt.push($(this).val());
		}
	});
	if(muilt.length == 0) {
		$("input[name=O_DIRECTION]").each(function(){
			muilt.push($(this).val());
		});
	}
	
	var data = {
		type: "table",
		O_DEPARTID: F.entitys("table1").getSingleValue(3),
		F_DRIVERNAME: $("input[name=F_DRIVERNAME]").val(),
		O_VEHICLECODE: $("input[name=O_VEHICLECODE]").val(),
		O_DATE: $("input[name=O_DATE]").val(),
		O_BC: $("input[name=O_BC]").val(),
		O_TC: $("input[name=O_TC]").val(),
		O_DIRECTION: muilt
	}
	
	F.Table.make($("#table2"), {
		enableAutoSerial: true,
		enableSingleSelect: true,
		showSingleSelect: true,
		enableAjax: true,
		title: "作业计划",
		models: [{
			name: "deid",
			hide: true
		},{
			name: "vecode",
			hide: true
		},{
			name: "drcode",
			hide: true
		},{
			name: "线路"
		},{
			name: "车号"
		},{
			name: "司机"
		},{
			name: "日期"
		},{
			name: "班次"
		},{
			name: "趟次"
		},{
			name: "方向"
		},{
			name: "发车时间"
		},{
			name: "到达时间"
		},{
			name: "乘务员1"
		},{
			name: "乘务员2"
		},{
			name: "乘务员3"
		},],
		ajax: {
			url: "../json/run.do",
			data: data,
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

function deleted(){
	F.entitys("table").openMuiltSelect();
	
}
function complete(){
	var d = F.entitys("table").getMuiltValue(3);
	F.Dialog.make(undefined, {
		title: "删除部门",
		message: "此操作将会影响所有该部门的用户，确定删除？",
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
			rows: 8
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
		enableAutoSerial: true,
		enableSingleSelect: true,
		enableMuiltSelect: true,
		showSingleSelect: true,
		enableAjax: true,
		title: "作业计划",
		models: null,
		data: null
	});	
}

$(document).ready(function(){
	ajax();

	$("#search").click(search);
	
	$("#add").click(add);
/*
	$("#update").click(update);

	$("#delete").click(function(){
		deleted();
	});
	
	$("#complete").click(function(){
		complete();
	});
	
	$("#cancle").click(function(){
		cancle();
	});
	$("#select").click(select);
	*/
});