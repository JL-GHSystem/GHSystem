/**
 * 
 */
function add(){
	alert("add");
}

function update(){
	alert("update");
}

function deleted(){
	alert("deleted");
}

function select(){
	alert("select");
}

function ajax(){

	var data = {
		type: "table"
	}
	
	$.ajax({
		type: "POST",
	    url: "../json/user.do",
	    data: data,
	    beforeSend: function(){
	    	
	    },
	    success: function (data) {
	    	F.Table.make($("#table"), {
				loadPagination: false,
	    		enableAutoSerial: true,
	    		enableSingleSelect: true,
	    		showSingleSelect: true,
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

	$("#select").click(select);
	
});