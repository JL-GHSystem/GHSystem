/** 百度地图API功能
 * 1. 围栏展示模块
 * 2. 围栏绘制模块
 */

var styleCircleCSS = {
    strokeColor: "blue",    //边线颜色。
    fillColor: "red",      //填充颜色。当参数为空时，圆形将没有填充效果。
    strokeWeight: 3,       //边线的宽度，以像素为单位。
    strokeOpacity: 0.8,	   //边线透明度，取值范围0 - 1。
    fillOpacity: 0.5,      //填充的透明度，取值范围0 - 1。
    strokeStyle: 'solid' //边线的样式，solid或dashed。
}

var stylePolygonCSS = {
    strokeColor: "blue",    //边线颜色。
    fillColor: "yellow",      //填充颜色。当参数为空时，圆形将没有填充效果。
    strokeWeight: 2,       //边线的宽度，以像素为单位。
    strokeOpacity: 0.8,	   //边线透明度，取值范围0 - 1。
    fillOpacity: 0.5,      //填充的透明度，取值范围0 - 1。
    strokeStyle: 'solid' //边线的样式，solid或dashed。
}

var stylePolylineCSS = {
    strokeColor: "red",    //边线颜色。
    fillColor: "green",      //填充颜色。当参数为空时，圆形将没有填充效果。
    strokeWeight: 3,       //边线的宽度，以像素为单位。
    strokeOpacity: 0.8,	   //边线透明度，取值范围0 - 1。
    fillOpacity: 0.5,      //填充的透明度，取值范围0 - 1。
    strokeStyle: 'solid' //边线的样式，solid或dashed。
}

/** 1. 围栏展示模块 **/
var fenceOverlay = {
    id: "",
    type: "",
    overlay: ""
};

function dataToPoint(data) {
    var pointArray = new Array();
    $.each(data, function (idx, obj) {
        pointArray.push(new BMap.Point(obj.lng, obj.lat));
    });
    return pointArray;
}

function calCenterPoint(data) {
    var center = {
        sum_lng: 0,
        sum_lat: 0
    }
    $.each(data, function (idx, obj) {
        center.sum_lng += obj.lng;
        center.sum_lat += obj.lat;
    });

    center.sum_lng /= data.length;
    center.sum_lat /= data.length;
    return center;
}

function add_line(lineData) {
    var pointArray = dataToPoint(lineData);
    var polyline = new BMap.Polyline(pointArray, stylePolylineCSS);   //创建折线
    map.addOverlay(polyline);   //增加折线

    var centerPoint = calCenterPoint(lineData);
    map.panTo(new BMap.Point(centerPoint.sum_lng, centerPoint.sum_lat));

    fenceOverlay.overlay = polyline;
}
	
function add_polygon(polygonData) {
    var pointArray = dataToPoint(polygonData);
    var polygon = new BMap.Polygon(pointArray, stylePolygonCSS);   //创建多边形
    map.addOverlay(polygon);   //增加多边形

    var centerPoint = calCenterPoint(polygonData);
    map.panTo(new BMap.Point(centerPoint.sum_lng, centerPoint.sum_lat));

    fenceOverlay.overlay = polygon;
}

function add_circle(circleData, range) {
    var focusPoint = new BMap.Point(circleData.lng, circleData.lat);
    var circle = new BMap.Circle(focusPoint, range, styleCircleCSS);
    map.addOverlay(circle);   //增加圆
    map.panTo(focusPoint);
    fenceOverlay.overlay = circle;
}

function clearAll() {
    map.clearOverlays();
}
/** 1. end **/

/** 2. 围栏绘制模块 **/
var isChange = false;
var drawStart = false;
var canDraw = false;
var drawing = false;

var currentOverlay = {
    id: "",
    type: "",
    overlay: ""
};

var overlaycomplete = function (e) {

    currentOverlay.type = drawingManager.getDrawingMode();
    currentOverlay.overlay = e.overlay;

    $(".FenceDrawTools a.Tools").removeClass("Select");
    endDraw();

    currentOverlay.overlay.enableEditing();
};

//实例化鼠标绘制工具
    
var drawingManager = null;

function initDrawing() {
    drawingManager = new BMapLib.DrawingManager(map, {
        isOpen: false, //是否开启绘制模式
        enableDrawingTool: false, //是否显示工具栏

        circleOptions: styleCircleCSS, //圆的样式
        polylineOptions: stylePolylineCSS, //线的样式
        polygonOptions: stylePolygonCSS, //多边形的样式
        rectangleOptions: stylePolygonCSS //矩形的样式
    });

    //添加鼠标绘制工具监听事件，用于获取绘制结果
    drawingManager.addEventListener('overlaycomplete', overlaycomplete);

    currentOverlay.type = "";
    currentOverlay.overlay = "";

}

//开始绘图方法
function startDraw(drawType) {
    changeDrawType(drawType);

    drawingManager.open();
}
        
//修改绘图模式
function changeDrawType(drawType) {

    switch (drawType) {
        case 0: drawingManager.setDrawingMode(BMAP_DRAWING_CIRCLE); break;
        case 1: drawingManager.setDrawingMode(BMAP_DRAWING_POLYGON); break;
        case 2: drawingManager.setDrawingMode(BMAP_DRAWING_POLYLINE); break;
        case 3: drawingManager.setDrawingMode(BMAP_DRAWING_RECTANGLE); break;
    }

}

//结束绘图，结束绘图无法再次进行绘图
function endDraw() {
    drawingManager.close();

    canDraw = false;
    drawing = false;
}

//保存绘图
function save(name, descirption) {
    if (typeof (currentOverlay) == "undefined" || currentOverlay == null || currentOverlay.type == "") {
    	F.Affair.make(undefined, {
    		type: "error",
    		message: "未绘制图形或绘图未完成",
    		reload: false
    	})
    }
    else if (isChange) {
        btn_update(name, descirption);
    }
    else {
        btn_add();
    }
}

function btn_delete(id) {
	F.Dialog.make(undefined, {
		title: "删除围栏",
		message: "确定删除围栏吗？",
		type: "warnning",
		enableCover: true,
		closeClick: function(){
			
		},
		confirmClick: function(doc, fa){
			$.ajax({
				type: "POST",
			    url: "../json/fence.do",
			    data: {
			    	type: "delete",
			    	O_FENCEID: fenceOverlay.id
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

function btn_add() {
	
	F.Dialog.make(undefined, {
		title: "新建围栏",
		type: "customer",
		url: "view/form/fence/add.html",
		frameId: "fenceAdd",
		frameWidth: 400,
		frameHeight: 100,
		data: {},
		enableCover: true,
		closeClick: function(){
			return 0;
		},
		confirmClick: function(doc, fa){
			var form = $(doc).contents().find("form");
			var name = form.find("input[name=O_FENCENAME]").val();
			var commit = form.find("textarea[name=O_COMMIT]").val();
	    	var points = [];
	    	var ftype;
			var radius;
			
			switch(currentOverlay.type) {
			case "circle":
				points[0] = currentOverlay.overlay.point;
            	radius = currentOverlay.overlay.xa;
            	ftype = 2;
				break;
			case "actangle":
	            for (i = 0; i < currentOverlay.overlay.po.length; i++) {
					points.push(currentOverlay.overlay.po[i]);
	            }
            	ftype = 1;
				break;
			case "polygon":
	            for (i = 0; i < currentOverlay.overlay.po.length; i++) {
					points.push(currentOverlay.overlay.po[i]);
	            }
            	ftype = 3;
				break;
			case "polyline":
	            for (i = 0; i < currentOverlay.overlay.po.length; i++) {
					points.push(currentOverlay.overlay.po[i]);
	            }
            	ftype = 0;
				break;
			}
			
			points = convert.BD2WGSArr(points);
	    	var laPoints = [];
	    	var loPoints = [];
			for(var i=0; i<points.length; i++) {
				laPoints.push(points[i].lat);
				loPoints.push(points[i].lng);
			}
			
			
			$.ajax({
				type: "POST",
			    url: "../json/fence.do",
			    data: {
			    	type: "add",
			    	ftype: ftype,
			    	name: name,
			    	commit: commit,
			    	laPoints: laPoints,
			    	loPoints: loPoints,
			    	radius: radius
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

//修改后保存围栏
function btn_update(name, descirption) {
	
	F.Dialog.make(undefined, {
		title: "更新围栏",
		type: "customer",
		url: "view/form/fence/update.html",
		frameId: "fenceUpdate",
		frameWidth: 400,
		frameHeight: 100,
		data: {
			O_FENCENAME: name,
			O_COMMIT: descirption
		},
		enableCover: true,
		closeClick: function(){
			return 0;
		},
		confirmClick: function(doc, fa){
			var form = $(doc).contents().find("form");
			var name = form.find("input[name=O_FENCENAME]").val();
			var commit = form.find("textarea[name=O_COMMIT]").val();
	    	var points = [];
	    	var ftype;
			var radius;
			
			switch(currentOverlay.type) {
			case "circle":
				points[0] = currentOverlay.overlay.point;
            	radius = currentOverlay.overlay.xa;
            	ftype = 2;
				break;
			case "actangle":
	            for (i = 0; i < currentOverlay.overlay.po.length; i++) {
					points.push(currentOverlay.overlay.po[i]);
	            }
            	ftype = 1;
				break;
			case "polygon":
	            for (i = 0; i < currentOverlay.overlay.po.length; i++) {
					points.push(currentOverlay.overlay.po[i]);
	            }
            	ftype = 3;
				break;
			case "polyline":
	            for (i = 0; i < currentOverlay.overlay.po.length; i++) {
					points.push(currentOverlay.overlay.po[i]);
	            }
            	ftype = 0;
				break;
			}
			
			points = convert.BD2WGSArr(points);
	    	var laPoints = [];
	    	var loPoints = [];
			for(var i=0; i<points.length; i++) {
				laPoints.push(points[i].lat);
				loPoints.push(points[i].lng);
			}
			
			
			$.ajax({
				type: "POST",
			    url: "../json/fence.do",
			    data: {
			    	type: "update",
			    	id: currentOverlay.id,
			    	ftype: ftype,
			    	name: name,
			    	commit: commit,
			    	laPoints: laPoints,
			    	loPoints: loPoints,
			    	radius: radius
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

function clear() {
    map.removeOverlay(currentOverlay.overlay);

    currentOverlay.type = "";
    currentOverlay.overlay = "";
}

/** 2. end **/

/* 
 * 左上角围栏列表显示
 */
var pagnation = {
    total: 1,
    records: 0,
    current: 1,
    rows: 20
}

//加载围栏数据
function load_fence(searchWords) {	
    $.ajax({
        url: "../json/fence.do",
        data: {
        	type: "table",
            current: pagnation.current,
            rows: pagnation.rows,
            search: searchWords
        },
        type: "post",
        success: function (data) {
            $("dd").remove();
            if (typeof (data.o) != undefined && data.o != null) {
            	if(data.o.data.length == 0) {
                    $("#FenceDL").append("<dd class='Error'>暂无数据</dd>");
            	}
            	else {
                    add_fence_all(data.o.data);
                    pagnation = data.o.pagination;
                    $(".FencePageBox .Page").val(pagnation.current);
            	}
            }
            else {
                $("#FenceDL").append("<dd class='Error'>请求数据失败</dd>");
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            $("dd.Load").remove()
            $("#FenceDL").append("<dd class='Error'>网络未连接</dd>");
        },
        beforeSend: function () {
            $("#FenceDL").append("<dd class='Load'><a class='Loading'></a><div>请求数据中……</div></dd>");
        }
    });

}

//往列表中添加一个围栏对象
function add_fence(serial, id, name, type, description) {
    var dd = $("<dd class='FenceContent'></dd>");

    $("<span class='FenceSerial'>" + serial + "</span>").appendTo(dd);
    $("<span class='FenceId'>" + id + "</span>").appendTo(dd);
    $("<span class='FenceName' title='" + name + "'>" + name + "</span>").appendTo(dd);
    $("<span class='FenceType'>" + type + "</span>").appendTo(dd);
    $("<span class='FenceDoing'></span>")
        .append("<a href='javaScript:void(0)' class='FenceDelete'>删除</a>")
        .append("<a href='javaScript:void(0)' class='FenceChange'>修改</a>")
        .appendTo(dd);
    $("<span class='FenceOperation'></span>")
        .append("<a href='javaScript:void(0)' class='FenceCancle'>取消</a>")
        .append("<a href='javaScript:void(0)' class='FenceSave'>保存</a>")
        .appendTo(dd);
    if(typeof(description) == "undefined" && !description) {
        $("<span class='FenceDescription'>&nbsp;</span>").appendTo(dd);
    }
    else {
        $("<span class='FenceDescription' title='" + description + "'>" + description + "</span>").appendTo(dd);
    }

    $("#FenceDL").append(dd);

}

function add_fence_all(data) {
    for(var i = 0; i<data.length; i++)
    {
    	add_fence(i+1, data[i].O_FENCEID, data[i].O_FENCENAME, data[i].O_FENCETYPE, data[i].O_COMMIT);
    }
}

//选择列表中的围栏对象进行删除
function delete_fence() {

    btn_delete();

}

//选择列表中的围栏对象进行修改
function change_fence() {

    fenceOverlay.overlay.enableEditing();

    $(".FenceDrawTools a.Tools").removeClass("Select");
    $(".FenceDrawHead a#New").addClass("Select");

    isChange = true;
    drawStart = true;
    canDraw = false; 
    drawing = false;

    currentOverlay.id = fenceOverlay.id;
    currentOverlay.type = fenceOverlay.type;
    currentOverlay.overlay = fenceOverlay.overlay;

}

//选择围栏时请求围栏图形数据，并显示围栏
function click_fence(id, type) {

    $.ajax({
        type: "post",
        url: "../json/fence.do",
        data: {
        	type: "detail",
        	id: id,
        	o: type
        },
        success: function (data) {
            if(drawStart)
            {
                $(".FenceDrawHead #Close").click();
            }
            else
            {
                clearAll();
            }
            $("dd.Load").remove();
            if (typeof (data.o) != undefined && data.o != null) {
            	show_fence(data.o);
            }
            else {
                $("#FenceDL").append("<dd class='Error'>暂无数据</dd>");
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            $("dd.Load").remove()
            $("#FenceDL").append("<dd class='Error'>网络未连接</dd>");
        },
        beforeSend: function () {
            $("#FenceDL").append("<dd class='Load'><a class='Loading'></a><div>请求数据中……</div></dd>");
        }
    });
}

function show_fence(data) {
    fenceOverlay.id = data.O_FENCEID;
    var points = [];
    for(var i=0; i<data.F_FENCENODES.length; i++) {
    	points.push({
    		lng: data.F_FENCENODES[i].O_LONGITUDE,
    		lat: data.F_FENCENODES[i].O_LATITUDE
    	})
    }
    
    switch (data.O_FENCETYPE)
    {
        case "圆形":
            add_circle(convert.WGS2BD(points[0]), data.O_RADIUS);
            fenceOverlay.type = "circle";
            break;
        case "线形":
        	add_line(convert.WGS2BDArr(points));
            fenceOverlay.type = "polyline";
            break;
        case "多边形":
            add_polygon(convert.WGS2BDArr(points));
            fenceOverlay.type = "polygon";
            break;
        case "矩形":
        	add_polygon(convert.WGS2BDArr(points));
            fenceOverlay.type = "polygon";
            break;
        default: break;
    }

}

$(document).ready(function () {

    load_fence();

    //设置窗口悬浮事件
    //1. 非固定时，滑入时浮入窗口，滑出时浮出窗口
    //2. 双击时固定窗口
    var Show_locked = false;
    var Draw_locked = false;
    $("#FenceShowRoom").hover(function () {

        if (Show_locked) {
            $(this).stop();
            $(this).animate({ top: "20px" }, 600, "swing");
            $(this).css("border-color", "#1ABC9C");
        }

    }, function () {
        if (Show_locked) {
            $(this).stop();
            $(this).animate({ top: "-" + ($(this).height() - 40) + "px" }, 600, "swing");
            $(this).css("border-color", "#DDDDDD");
        }
    });

    $("#FenceDrawRoom").hover(function () {

        if (Draw_locked) {
            $(this).stop();
            $(this).animate({ top: "20px" }, 400, "swing");
            $(this).css("border-color", "#1ABC9C");
        }

    }, function () {
        if (Draw_locked) {
            $(this).stop();
            $(this).animate({ top: "-" + ($(this).height() - 40) + "px" }, 400, "swing");
            $(this).css("border-color", "#DDDDDD");
        }
    });

        
    $("#FenceShowRoom .Locked").click(function () {
        Show_locked = !Show_locked;
    });

    $("#FenceDrawRoom .Locked").click(function () {
        Draw_locked = !Draw_locked;
    });

    //$("#FenceDrawRoom").dblclick(function () {
    //    Draw_locked = !Draw_locked;
    //});

    //$("#FenceShowRoom").dblclick(function () {
    //    Show_locked = !Show_locked;
    //});

    /** 围栏展示控件 **/

    //设置搜索栏事件
    $(".SearchBox").hover(function () {
        $("#Search").stop();
        $("#Search").show();
        $("#Search").animate({ width: "120px" }, 600, "swing");

    }, function () {
        if (!$("#Search").is(":focus")) {
            $("#Search").blur();
        }
    });
    
    $("#SearchSubmit").click(function(){
        if ($("#Search").val() != "" && $("#Search").val() != undefined) {
    		pagnation = {
			    total: 1,
			    records: 0,
			    current: 1,
			    rows: 20
			}
        	load_fence($("#Search").val());
        }
        else {
        	load_fence();
        }
    })

    $("#Search").blur(function () {
        if ($("#Search").val() == "" | $("#Search").val() == undefined) {
            $("#Search").stop();
            $("#Search").animate({ width: "0px" }, 600, "swing", function () {
                $("#Search").hide();
            });
        }
    });

    //设置列表点击事件
    $(document).on("click", ".FenceContent", function () {
        if ($(this).hasClass("Select")) {

            click_fence($(this).children(".FenceId").html(), $(this).children(".FenceType").html());
        }
        else {
            $(".FenceContent.Select").children(".FenceDescription").hide();
            $(".FenceContent.Select").children("span.FenceOperation").hide();
            $(".FenceContent.Select").children("span.FenceDoing").show();
            $(".FenceContent.Select").removeClass("Select");
            $(this).addClass("Select");
            $(this).children(".FenceDescription").show();

            click_fence($(this).children(".FenceId").html(), $(this).children(".FenceType").html());


        }
    });

    $(document).on("click", ".FenceDelete", function (event) {
        var tag = $(this).parent().parent();
        if ($(tag).hasClass("Select")) {
            delete_fence();

            event.stopPropagation();
        }

    });

    $(document).on("click", ".FenceChange", function (event) {
        var tag = $(this).parent().parent();
        if ($(tag).hasClass("Select")) {
            change_fence();

            $(tag).children("span.FenceDoing").hide();
            $(tag).children("span.FenceOperation").show();

            event.stopPropagation();
        }

    });

    $(document).on("click", ".FenceSave", function (event) {
        var tag = $(this).parent().parent();
        if ($(tag).hasClass("Select")) {
            var name = $(tag).children("span.FenceName").html();
            var description = $(tag).children("span.FenceDescription").html();

            save(name, description);

            event.stopPropagation();
        }

    });
    
    $(document).on("click", ".FenceCancle", function (event) {
        var tag = $(this).parent().parent();
        if ($(tag).hasClass("Select")) {
            $(tag).children("span.FenceOperation").hide();
            $(tag).children("span.FenceDoing").show();
            isChange = false;
        }
    });

    $("#First").click(function () {
        pagnation.current = 1;
        $("#SearchSubmit").click();
    });

    $("#Last").click(function () {
        pagnation.current = pagnation.total;
        $("#SearchSubmit").click();
    });

    $("#Previous").click(function () {
        if (pagnation.current > 1) {
            pagnation.current--;
            $("#SearchSubmit").click();
        }
    });

    $("#Next").click(function () {
        if (pagnation.current < pagnation.total) {
            pagnation.current++;
            $("#SearchSubmit").click();
        }
    });

    $("#PageSubmit").click(function () {
        var value = parseInt($(this).prev().val());
        if (value <= pagnation.total && value >= 1) {
            pagnation.current = value;
            $("#SearchSubmit").click();
        }
    });

    /** 围栏展示控件 END **/

    /** 围栏绘图控件 **/

    $(".FenceDrawHead #Close").click(function () {
        $(".FenceDrawHead #New").removeClass("Select");
        $(".FenceDrawTools a.Tools").removeClass("Select");

        if (drawStart)
        {
            canDraw = false;
            drawing = false;
            drawStart = false;

            clearAll();
        }

        if(isChange)
        {
            $(".FenceContent.Select .FenceCancle").click();

            isChange = false;
        }
    });

    $(".FenceDrawHead #New").click(function () {
        clearAll();
        $(".FenceDrawTools a.Tools").removeClass("Select");
        $(this).addClass("Select");
        initDrawing();

        drawStart = true;
        canDraw = true;
        drawing = false;
    });

            
    function drawToolsClick(name, type) {
        if (drawStart && canDraw && !drawing) {
            $(".FenceDrawTools a.Tools").removeClass("Select");
            name.addClass("Select");

            drawing = true;
            startDraw(type);
        }
        else if (drawStart) {
	    	F.Affair.make(undefined, {
	    		type: "error",
	    		message: "一次只能绘制一种形状的一个围栏，必须清除当前围栏才能继续绘图",
	    		reload: false
	    	})
        }
        else {
	    	F.Affair.make(undefined, {
	    		type: "error",
	    		message: "必须新建一个围栏才能开始绘图",
	    		reload: false
	    	})
        }
    }

    $("#Oval").click(function () {
        drawToolsClick($(this), 0);
    });

    $("#Polygon").click(function () {
        drawToolsClick($(this), 1);
    });

    $("#Line").click(function () {
        drawToolsClick($(this), 2);
    });

    $("#Clear").click(function () {
        if (drawStart)
        {
            clearAll();
            initDrawing();

            $(".FenceDrawTools a.Tools").removeClass("Select");

            drawing = false;
            canDraw = true;

        }
    });

    $("#Save").click(function () {
        if (drawStart)
        {
            if (isChange)
            {
                var name = $(".FenceContent.Select").children("span.FenceName").html();
                var description = $(".FenceContent.Select").children("span.FenceDescription").html();

                save(name, description);

                $(".FenceDrawTools a.Tools").removeClass("Select");
            }
            else
            {
                save();
                $(".FenceDrawTools a.Tools").removeClass("Select");
            }
        }
        else
        {
	    	F.Affair.make(undefined, {
	    		type: "error",
	    		message: "必须新建一个围栏才能保存绘图",
	    		reload: false
	    	})
        }
    });
    /** 围栏绘图控件 END **/

})