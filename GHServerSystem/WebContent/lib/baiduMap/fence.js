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


function show_fence(data) {
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