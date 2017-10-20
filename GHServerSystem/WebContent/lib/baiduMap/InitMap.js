/** 百度地图API功能
 * 1. 比例尺、平移缩放控件模块
 */
var map = new BMap.Map("BaiduMap", { enableMapClick: false });

/** 1. 比例尺、平移缩放控件模块 **/
var point = new BMap.Point(120.757091, 30.768196);
map.centerAndZoom(point, 18);

var top_left_control = new BMap.ScaleControl({ anchor: BMAP_ANCHOR_BOTTOM_LEFT });// 左下角，添加比例尺
var top_left_navigation = new BMap.NavigationControl({ anchor: BMAP_ANCHOR_TOP_RIGHT });  //右上角，添加默认缩放平移控件

map.enableScrollWheelZoom(true);

//添加控件和比例尺
function add_control() {
    map.addControl(top_left_control);
    map.addControl(top_left_navigation);
}

add_control();

/** 1. END **/