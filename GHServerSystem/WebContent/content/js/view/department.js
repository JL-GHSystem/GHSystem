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


$(document).ready(function(){
	
	$("#add").click(add);

	$("#update").click(update);

	$("#delete").click(deleted);

	$("#select").click(select);
	
	
	F.Table.make($("#table"), {
		enableAutoSerial: true,
		enableSingleSelect: true,
		showSingleSelect: true,
		data: [{
			ad: 1,
			sd: 2,
			cd: 3,
			fd: 4,
			gd: 5,
			hd: 6,
			jd: 7,
			kd: 8
		},{
			ad: 1,
			sd: 2,
			cd: 3,
			fd: 4,
			gd: 5,
			hd: 6,
			jd: 7,
			kd: 8
		},{
			ad: 1,
			sd: 2,
			cd: 3,
			fd: 4,
			gd: 5,
			hd: 6,
			jd: 7,
			kd: 8
		},{
			ad: 1,
			sd: 2,
			cd: 3,
			fd: 4,
			gd: 5,
			hd: 6,
			jd: 7,
			kd: 8
		}]
	});
	
});