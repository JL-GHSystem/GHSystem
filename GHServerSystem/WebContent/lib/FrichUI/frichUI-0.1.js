/**
 * 
 */

(function(global, factory){

	factory(global);
	
})(window, function(global){
	/*
	 * 1. common 基础支持层
	 */
	var FRICHUI_ROOT = "FrichUI/";
	
	var reg1 = /\{[^{}]+\}/g;		//匹配字符串，用于model检验
	
	/*
	 * 2. FrichUI 模块层
	 */

	/*
	 * 2.1 样式主题
	 */
	var Theme = function(path, name, file){
		this.path = path;
		this.name = name;
		this.file = file || "frichUI.css";
		
	};
	Theme.prototype = {
		/*
		 * 私有成员声明
		 * @path
		 * @name
		 * @file
		 * 
		 * @link
		 */
		init: function(root){
			var th = this;
			this.link = $("<link>").attr({
				href: root + th.path,
				rel: "stylesheet",
				type: "text/css"
			});
			
			var link = this.link;
			$(document).ready(function(){
				$("head").append(link);
			});
		},
		change: function(root){
			var th = this;
			$(ducment).ready(function(){
				$(th.link).remove();
			})
			th.init(root);
		}
	};
	
	/*
	 * 2.2 FrichUI核心件
	 */
	var FrichUI = function(){
		
	};
	FrichUI.prototype = {
		/*
		 * 私有成员声明
		 * 
		// 全局控制
		root: null,
		
		// 皮肤
		theme: null,
		
		// 工厂制作后的产品集
		entity: null,
		
		//工厂集
		Menu
		
		*/

		/*
		 * 私有成员声明
		 */
		//皮肤1	淡蓝绿(默认)
		FRICHUI_THEME_AQUAMARINE: new Theme("Themes/Aquamarine/frichUI.css", "aquamarine"),
		//皮肤2	暗色
		FRICHUI_THEME_DARK: new Theme("Themes/Dark/frichUI.css", "dark"),
		
		/*
		 * 公共接口定义
		 */
		init: function(root, theme){
			this.root = root || FRICHUI_ROOT;
			
			this.theme = theme || this.FRICHUI_THEME_AQUAMARINE;
			this.theme.init(this.root);
			
			this.entity = new Array();
		},
		
		path: function(root){
			this.root = root || FRICHUI_ROOT;
			
			this.theme.init(this.root);
		},
		
		entitys: function(id){
			var result;
			$.each(this.entity, function(i, item){
				if(item.id == id)
				{
					result = item;
					return;
				}
			});
			return result;
		},
		
		push: function(obj){
			this.entity.push(obj);
		},
		
		istEmpty: function(obj){
			return !this.isEmpty(obj);
		},
		
		isEmpty: function(obj){
			if(typeof(obj) == "undefined" || !obj){
				if(obj == 0) {
					return false;
				}
				return true;
			}
			return false;
		}
	};
	FrichUI.prototype.Theme = Theme;
	
	/*
	 * 2.3 工厂核心件
	 * @数据组件	
	 * 2.3.1表格
	 * 2.3.2菜单、多级无序列表
	 * 2.3.3多级有序列表
	 * @控制组件	
	 * 2.3.5会话组件
	 * 
	 */
	var Factory = function(){

	}
	Factory.prototype = {
		defaul: {
			id: "",
			width: "extend",
			height: "extend"
		},
		id: function(div, options) {
	        var result;
	        if(options.id == "extend") {
	            result = $(div).attr("id");
	            if(!result || typeof(result) == undefined) {
	                console.error("you must set a id for the initialized div or in options");
	            }
	        }
	        else {
	            result = options.id;
	        }
	        options.id = result;
	        return result;
	    },
		weight: function (div, options) {
	        var result;
	        if (options.width == "extend") {
	            result = $(div).width();
	            if (result == 0) {
	                console.error("you must set the width in 'css' or options");
	            }
	            result += "px";
	        }
	        else if (options.width == "auto") {
	            result = "100%";
	        }
	        else {
	            result = options.width;
	        }
	        op.width = result;
	        return result;
		},
		height: function (div, options) {
	        var result;
	        if (options.height == "extend") {
	            result = $(div).height();
	            if (result == 0) {
	                console.error("you must set the height in 'css' or options");
	            }
	            result += "px"
	        }
	        else if (options.height == "auto") {
	            result = "100%";
	        }
	        else {
	            result = options.height;
	        }
	        options.height = result;
	        return result;
		},
	    createFrame: function (frClass) {
	        var frame = $("<div></div>");
	        frame.attr({
	    		class: frClass,
	    	});
	        return frame;
	    },
	    createDiv: function (frClass, value) {
	        var div = $("<div></div>");
	        div.attr({
	    		class: frClass,
	    	});
	        div.append(value);
	        return div;
	    },
	    createOl: function (frClass) {
	        var ol = $("<ol></ol>");
	        ol.attr({
	    		class: frClass,
	    	});
	        return ol;
	    },
	    createUl: function (frClass) {
	        var ul = $("<ul></ul>");
	        ul.attr({
	    		class: frClass,
	    	});
	        return ul;
	    },
	    createLi: function (frClass) {
	        var li = $("<li></li>");
	        li.attr({
	    		class: frClass,
	    	});
	        return li;
	    },
	    createH: function (i, frClass, value) {
	        var h = $("<h"+ i +"></h"+ i +">");

	    	h.attr({
	    		class: frClass,
	    	});
	    	h.append(value);
	        return h;
	    },
	    createI: function (frClass) {
	        var i = $("<i></i>");
	    	i.attr({
	    		class: frClass,
	    	});
	        return i;
	    },
	    createA: function(frClass, value, href){
	    	var a = $("<a></a>");
	    	a.attr({
	    		class: frClass,
	    		href: href
	    	})
	    	a.append(value);
	        return a;
	    },
	    createiFrame:function(frClass,value){
	    	var iF=$("<iFrame></iFrame>");
	    	iF.attr({
	    		class:frClass,
	    	});
	    	iF.append(value);
	    	return iF;
	    },
		initCreate: function(dom, customer){
			options = $.extend(true, {}, this.defaul, customer);
			this.id(dom, options);
			
			return options;
		}
	}
	
	var Entity = function(){
		
	}
	Entity.prototype = {
		
	}
	
	/*
	 * 2.3.1 表格组件
	 */
	var TableFactory = function(){
		Factory.call(this);
		/*
		{
			id: "",					*自动生成，不需要设置，用于hash映射data中某一列
			name: "",
			model: {v},
			sort: 1~max,			*用于排序
			textAlign: "center",
			fixed: false,
			enableWrap: false,
			hide: false,
			emptyDisplay: "",
			boolDisplay: ["true", "false"],
			width: ,				*enableWrap: true时此项必须
			maxHeight: 				*enableWrap: true时此项必须
			
		}
		*/
		this.defaulModel = {
			model: "{v}",
			sort: "last",
			fixed: false,
			hide: false,
			textAlign: "center",
			emptyDisplay: "",
			boolDisplay: ["true", "false"],
			enableWrap: false
		}
		
		this.selectModel = {
			width: 15
		}

		this.serialModel = {
			name: "序号",
			width: 30
		}
		
		this.defaulPagination = {
			total: 0,
			pages: 1,					//当前页基础上显示几页 > 0
			records: 0,
			current: 1,
			rows: 20
		}
		
		this.defaul = $.extend(true, {}, this.defaul, {
			loadHead: true,						//是否加载标题栏
			loadPagination: true,				//是否加载分页栏
			lockHead: true, 					//是否锁定表头
			dividing: 0.4,						//控制浮动出现时机
			enableMuiltSelect: false,
			showMuiltSelect: false,
			enableSingleSelect: false,
			showSingleSelect: false,
			selectModel: this.selectModel,
			enableAutoSerial: false,
			serialModel: this.serialModel,
			enableAjax: false,
			title: "FrichUI Simple",
			models: [],							//列模型 [{},{}]
			//data: null						//数据模型 {v:,c:},{v:,c:}
			pagination: this.defaulPagination,
			ajax: {
				type: "POST",
			    url: null,
			    data: null,
			    beforeSend: function(){
			    	
			    },
			    success: function (data, options) {
			    	
			    },
			    error: function (err) {
			    	
			    }
			}
		});
		/*
		this.runTime = {
			fixedModels: new Array(),
			dataModels: new Array(),
			dataName: new Array()
		}
		*/
		this.createTitleFrame = function(dom, options){
			var title = this.createFrame("FrichUI_Table_Title_Frame");
				
			h5 = this.createH(5, "FrichUI_Table_TitleName", options.title);
			
			title.append(h5);
			
			return title;
		}
		
		this.createContentFrame = function(dom, options){
			var frame = this.createFrame("FrichUI_Table_Frame");
			var house = this.createDiv("FrichUI_Table_House");
			
			//创建content
			var content = this.createDiv("FrichUI_Table_Content");
			
				var table = this.createTable("FrichUI_Table_ContentTable");
				
				var thead = this.createDataTitle(dom, options, false);
				var tbody = this.createDataBody(dom, options, false);
	
				table.append(thead);
				table.append(tbody);
			
			content.append(table);
			house.append(content);

			//创建title
			if(options.lockHead)
			{
				var floatTitle = this.createDiv("FrichUI_Table_FloatTitle");
				
					var table = this.createTable("FrichUI_Table_TitleTable");
					
					var thead = this.createDataTitle(dom, options, false);
		
					table.append(thead);
					
				floatTitle.append(table);
				house.append(floatTitle);
			}
			
			if(this.runTime.fixedModels.length > 0){
				//创建column
				var floatColumn = this.createDiv("FrichUI_Table_FloatColumn");
				
					var table = this.createTable("FrichUI_Table_ColumnTable");
					
					var thead = this.createDataTitle(dom, options, true);
					var tbody = this.createDataBody(dom, options, true);
					
					table.append(thead);
					table.append(tbody);
					
				floatColumn.append(table);
				house.append(floatColumn);
					
				//创建header
				var floatHeader = this.createDiv("FrichUI_Table_FloatHeader");
				
					var table = this.createTable("FrichUI_Table_HeaderTable");
					
					var thead = this.createDataTitle(dom, options, true);
					
					table.append(thead);
					
				floatHeader.append(table);
				house.append(floatHeader);
			}
			
			frame.append(house);
			return frame;
		}
		
		this.createDataBody = function(dom, options, onlyFixed){
			var data = $.extend(true, [], options.data);
			var tbody = this.createTbody();
			
			for(var k=0; k<data.length; k++){

				var tr = this.createTr("FrichUI_Table_r"+ (k+1) +" FrichUI_Table_DataRow");
				var i = 0;
				
				if(onlyFixed){
					if(options.enableSingleSelect){
						var td = this.createTd("FrichUI_Table_RadioColumn");
						var div = this.createDiv("FrichUI_Table_c" + i, "<input type=\"radio\" name=\"radioF\">");
						td.attr({width: options.selectModel.width});
						td.append(div);
						tr.append(td);
						i++;
					}
					
					if(options.enableMuiltSelect){
						var td = this.createTd("FrichUI_Table_CheckColumn");
						var div = this.createDiv("FrichUI_Table_c" + i, "<input type=\"checkbox\" name=\"checkboxF\">");
						td.attr({width: options.selectModel.width});
						td.append(div);
						tr.append(td);
						i++;
					}
				}
				else {
					if(options.enableSingleSelect){
						var td = this.createTd("FrichUI_Table_RadioColumn");
						var div = this.createDiv("FrichUI_Table_c" + i, "<input type=\"radio\" name=\"radioC\">");
						td.attr({width: options.selectModel.width});
						td.append(div);
						tr.append(td);
						i++;
					}
					
					if(options.enableMuiltSelect){
						var td = this.createTd("FrichUI_Table_CheckColumn");
						var div = this.createDiv("FrichUI_Table_c" + i, "<input type=\"checkbox\" name=\"checkboxC\">");
						td.attr({width: options.selectModel.width});
						td.append(div);
						tr.append(td);
						i++;
					}
				}
				
				if(options.enableAutoSerial){
					var td = this.createTd("FrichUI_Table_Serial");
					var div = this.createDiv("FrichUI_Table_c" + i, (k+1));
					td.attr({width: options.serialModel.width});
					td.append(div);
					tr.append(td);
					i++;
				}

				// 创建浮动列
				for(var j=0; j<this.runTime.fixedModels.length; j++) {
					var td = null;

					if(j == this.runTime.fixedModels.length-1){
						td = this.createTd("FrichUI_Table_Floated");
					}
					else {
						td = this.createTd();
					}

					if(frichUI.istEmpty(this.runTime.fixedModels[j].width)){
						td.attr({width: this.runTime.fixedModels[j].width});
					}

					var D = data[k][this.runTime.fixedModels[j].id];
					if(frichUI.isEmpty(D)){
						D = this.runTime.fixedModels[j].emptyDisplay;
					}
					D = this.checkBool(this.runTime.fixedModels[j], D);
					
					var model = this.runTime.fixedModels[j].model;

					model = model.replace(reg2, D);
					
					var div = this.createDiv("FrichUI_Table_c" + i, model);

					td.append(div);
					i++;

					this.setTdCss(this.runTime.fixedModels[j], td);
					tr.append(td);
				}
				if(!onlyFixed){
					// 创建数据列
					for(var j=0; j<this.runTime.dataModels.length; j++) {
						var td = this.createTd();
						
						if(frichUI.istEmpty(this.runTime.dataModels[j].width)){
							td.attr({width: this.runTime.dataModels[j].width});
						}
						
						var D = data[k][this.runTime.dataModels[j].id];
						if(frichUI.isEmpty(D)){
							D = this.runTime.dataModels[j].emptyDisplay;
						}
						D = this.checkBool(this.runTime.dataModels[j], D);
						
						var model = this.runTime.dataModels[j].model;

						model = model.replace(reg2, D);
						
						var div = this.createDiv("FrichUI_Table_c" + i, model);

						td.append(div);
						i++;

						this.setTdCss(this.runTime.dataModels[j], td);
						tr.append(td);
					}
				}
				tbody.append(tr);
			}
			return tbody;
		}
		this.createDataTitle = function(dom, options, onlyFixed){
			var thead = this.createThead();
			
			var tr = this.createTr("FrichUI_Table_r0 FrichUI_Table_TitleRow");
			var i = 0;
			
			if(options.enableSingleSelect){
				var th = this.createTh("FrichUI_Table_RadioColumn");
				var div = this.createDiv("FrichUI_Table_c" + i);
				th.attr({width: options.selectModel.width});
				th.append(div);
				tr.append(th);
				i++;
			}
			
			if(options.enableMuiltSelect){
				var th = this.createTh("FrichUI_Table_CheckColumn");
				var div = this.createDiv("FrichUI_Table_c" + i);
				th.attr({width: options.selectModel.width});
				th.append(div);
				tr.append(th);
				i++;
			}
			
			if(options.enableAutoSerial){
				var th = this.createTh("FrichUI_Table_Serial");
				var div = this.createDiv("FrichUI_Table_c" + i, options.serialModel.name);
				th.attr({width: options.serialModel.width});
				th.append(div);
				tr.append(th);
				i++;
			}
			
			// 创建浮动列
			for(var j=0; j<this.runTime.fixedModels.length; j++) {
				var th = null;
				
				if(j == this.runTime.fixedModels.length-1){
					th = this.createTh("FrichUI_Table_Floated");
				}
				else {
					th = this.createTh();
				}
				
				if(frichUI.istEmpty(this.runTime.fixedModels[j].width)){
					th.attr({width: this.runTime.fixedModels[j].width});
				}
				
				var div;
				if(frichUI.istEmpty(this.runTime.fixedModels[j].name)){
					div = this.createDiv("FrichUI_Table_c" + i, this.runTime.fixedModels[j].name);
				}
				else {
					div = this.createDiv("FrichUI_Table_c" + i, this.runTime.fixedModels[j].id);
				}
				
				th.append(div);
				i++;

				this.setTdCss(this.runTime.fixedModels[j], th);
				tr.append(th);
			}

			// 创建数据列
			if(!onlyFixed) {
				for(var j=0; j<this.runTime.dataModels.length; j++) {
					var th = this.createTh();

					if(frichUI.istEmpty(this.runTime.dataModels[j].width)){
						th.attr({width: this.runTime.dataModels[j].width});
					}
					
					var div;
					if(frichUI.istEmpty(this.runTime.dataModels[j].name)){
						div = this.createDiv("FrichUI_Table_c" + i, this.runTime.dataModels[j].name);
					}
					else {
						div = this.createDiv("FrichUI_Table_c" + i, this.runTime.dataModels[j].id);
					}
					
					th.append(div);
					i++;
					
					this.setTdCss(this.runTime.dataModels[j], th);
					tr.append(th);
				}
				
			}
			
			thead.append(tr);
			return thead;
		}
		
		this.createPaginationFrame = function(dom, options){
			var frame = this.createFrame("FrichUI_Table_Pagination_Frame");
			
			var house = this.createDiv("FrichUI_Table_Pagination_House");
				
			var tips = this.createDiv("FrichUI_Table_Pagination_Tips");
			
			tips.html("当前<a id=\"FrichUI_Table_Pagination_CurP\">"+ options.pagination.current +"</a>" +
					"页/共<a id=\"FrichUI_Table_Pagination_TolP\">"+ options.pagination.total +"</a>页&nbsp;共" +
							"<a id=\"FrichUI_Table_Pagination_TolR\">"+ options.pagination.records +"</a>记录");
			
			house.append(tips);
			if(options.pagination.pages < 1) {
				options.pagination.pages = 1;
			}
			
			var skip = this.createDiv("FrichUI_Table_Pagination_Skip");
			if(options.pagination.total > 1) {
				skip.append("至<input class=\"FrichUI_Table_Pagination_Skip_Input\" type=\"text\" />页"
						+ "<a class=\"FrichUI_Table_Pagination_Skip_Submit\">跳转</a>");
			}
			house.append(skip);
			
			var select = this.createDiv("FrichUI_Table_Pagination_Select");
			if(options.pagination.current == 1) {
				select.append("<a class=\"FrichUI_Table_Pagination_First_undown\"></a>"
						+ "<a class=\"FrichUI_Table_Pagination_Previous_undown\"></a>");
				
			}
			else {
				select.append("<a class=\"FrichUI_Table_Pagination_First\"></a>"
						+ "<a class=\"FrichUI_Table_Pagination_Previous\"></a>");
				
			}
			
			if(options.pagination.total > options.pagination.pages*2 + 5) {
				if(options.pagination.current <= options.pagination.pages + 3){
					for(var i=1; i<=options.pagination.pages*2 + 3; i++){
						if(i == options.pagination.current) {
							select.append(this.createA("FrichUI_Table_Pagination_Page_undown", i));
						}
						else {
							select.append(this.createA("FrichUI_Table_Pagination_Page", i));
						}
					}
					select.append(this.createA("FrichUI_Table_Pagination_Decode", '…'));
					for(var i=options.pagination.total - 1; i<=options.pagination.total; i++){
						select.append(this.createA("FrichUI_Table_Pagination_Page", i));
					}
				}
				else if(options.pagination.current >= (options.pagination.total - (options.pagination.pages + 2))){
					for(var i=1; i<=2; i++){
						select.append(this.createA("FrichUI_Table_Pagination_Page", i));
					}
					select.append(this.createA("FrichUI_Table_Pagination_Decode", '…'));
					for(var i=(options.pagination.total - (options.pagination.pages*2 + 2))
							; i<= options.pagination.total; i++){
						if(i == options.pagination.current) {
							select.append(this.createA("FrichUI_Table_Pagination_Page_undown", i));
						}
						else {
							select.append(this.createA("FrichUI_Table_Pagination_Page", i));
						}
					}
				}
				else {
					for(var i=1; i<=2; i++){
						select.append(this.createA("FrichUI_Table_Pagination_Page", i));
					}
					select.append(this.createA("FrichUI_Table_Pagination_Decode", '…'));
					for(var i = options.pagination.current-1; i<=options.pagination.current+1; i++){
						if(i == options.pagination.current) {
							select.append(this.createA("FrichUI_Table_Pagination_Page_undown", i));
						}
						else {
							select.append(this.createA("FrichUI_Table_Pagination_Page", i));
						}
					}
					select.append(this.createA("FrichUI_Table_Pagination_Decode", '…'));
					for(var i=options.pagination.total - 1; i<=options.pagination.total; i++){
						select.append(this.createA("FrichUI_Table_Pagination_Page", i));
					}
				}
				
			}
			else {
				for(var i=1; i<=options.pagination.total; i++){
					if(i == options.pagination.current) {
						select.append(this.createA("FrichUI_Table_Pagination_Page_undown", i));
					}
					else {
						select.append(this.createA("FrichUI_Table_Pagination_Page", i));
					}
				}
			}

			if(options.pagination.current == options.pagination.total) {
				select.append("<a class=\"FrichUI_Table_Pagination_Next_undown\"></a>"
						+ "<a class=\"FrichUI_Table_Pagination_Last_undown\"></a>");
			}
			else {
				select.append("<a class=\"FrichUI_Table_Pagination_Next\"></a>"
						+ "<a class=\"FrichUI_Table_Pagination_Last\"></a>");
			}
			house.append(select);
			
			frame.append(house);
			return frame;
		}
		
		this.checkBool = function(model, value) {
			if(typeof(value) == "boolean") {
				if(value) {
					return model.boolDisplay[0];
				}
				else {
					return model.boolDisplay[1];
				}
			}
			return value;
		}
		
		this.setTdCss = function(model, col){
			if(model.hide) {
				$(col).hide();
			}
			
			if(model.textAlign != "center") {
				$(col).css({
					"text-align": model.textAlign
				});
			}
		}
	}
	TableFactory.prototype = new Factory();

	TableFactory.prototype.load = function(dom, optoins){
		/*
		 * 创建标题基架
		 */
		var titleFrame = null;
		if(options.loadHead){
			var titleFrame = this.createTitleFrame(dom, options);
			dom.append(titleFrame);
		}
		
		/*
		 * 创建数据表基架
		 */
		var Frame = null;
		if(!$.isEmptyObject(options.data)){
			/*
			 * 初始化model集合
			 * 1. 将model按fixed分成两组
			 * 2. 将model按sort排序
			 */
			var models = $.extend(true, [], options.models);
			this.runTime = {
				fixedModels: new Array(),
				dataModels: new Array(),
				dataName: new Array()
			}
			
			var max = -1;
			var d = -1;
			for(var i=0; i<options.data.length; i++){
				var l = Object.getOwnPropertyNames(options.data[i]).length;
				if(l > max){
					max = l;
					d = i;
				}
			}
			var k = 0;
			for(var i in options.data[d]){
				this.runTime.dataName[k] = i;
				k++;
			}
			//遍历models，与defaulModel继承
			var l = this.runTime.dataName.length;
			for(var j=0; j<l; j++) {
				models[j] = $.extend(true, {}, this.defaulModel, models[j]);
				models[j].id = this.runTime.dataName[j];
				
				if(models[j].fixed){
					if(models[j].sort == "last"){
						this.runTime.fixedModels.push(models[j]);
					}
					else {
						for(var i=0; i<this.runTime.fixedModels; i++){
							if(this.runTime.fixedModels[i].sort > models[j].sort){
								break;
							}
						}
						this.runTime.fixedModels.splice(i, 0, models[j]);
					}
				}
				else {
					if(models[j].sort == "last"){
						this.runTime.dataModels.push(models[j]);
					}
					else {
						for(var i=0; i<this.runTime.dataModels; i++){
							if(this.runTime.dataModels[i].sort > models[j].sort){
								break;
							}
						}
						this.runTime.dataModels.splice(i, 0, models[j]);
					}
				}
			}
			this.runTime.models = models;
			
			Frame = this.createContentFrame(dom, options);
			dom.append(Frame);
		}
		else {
			console.error(" you must set data in options ");
		}

		/*
		 * 创建分页基架
		 */
		var paginationFrame = null;
		if(options.loadPagination){
			var paginationFrame = this.createPaginationFrame(dom, options);
			dom.append(paginationFrame);
		}

		/*
		 * css控制
		 */
		fa = this;
		
		setTimeout(function(){
			/*
			 * 初始化House高度
			 */
			var height = 200;
			if(options.height != "auto") {
				height = options.height;
			}
			if(!options.loadHead){
				$(Frame).find(".FrichUI_Table_House").addClass("FrichUI_Table_No_Title");
				height -= 2;
			}
			else {
				height -= 30;
			}
			
			if(!options.loadPagination){
				$(Frame).find(".FrichUI_Table_House").addClass("FrichUI_Table_No_Pagination");
				height -= 2;
			}
			else {
				height -= 32;
			}

			if(options.height != "auto") {
				$(Frame).find(".FrichUI_Table_House").height(height);
			}
			
			/*
			 * 初始化Title宽度
			 */
			var s = $(Frame).find(".FrichUI_Table_ContentTable thead th");
			var d = $(Frame).find(".FrichUI_Table_FloatHeader thead th");
			var t = $(Frame).find(".FrichUI_Table_FloatTitle thead th");
			$.each(s, function(i, item){
				$(d[i]).children("div").width($(item).children("div").width());
				$(t[i]).children("div").width($(item).children("div").width());
			});
			
			/*
			 * 初始化pagination
			 */
			var fa = $(dom).find(".FrichUI_Table_Pagination_House");
			
			var width = $(dom).find(".FrichUI_Table_Pagination_House").width();
			
			var selectWidth = 300,
				skipWidth = $(dom).find(".FrichUI_Table_Pagination_Skip").width(),
				tipsWidth = $(dom).find(".FrichUI_Table_Pagination_Tips").width();
			
			if(width >= selectWidth + skipWidth + tipsWidth) {
				fa.find(".FrichUI_Table_Pagination_Skip").show();
				fa.find(".FrichUI_Table_Pagination_Tips").show();
				fa.find(".FrichUI_Table_Pagination_Select").show();
			}
			else if(width >= selectWidth + skipWidth) {
				fa.find(".FrichUI_Table_Pagination_Skip").show();
				fa.find(".FrichUI_Table_Pagination_Tips").hide();
				fa.find(".FrichUI_Table_Pagination_Select").show();
			}
			else if(width >= tipsWidth + skipWidth) {
				fa.find(".FrichUI_Table_Pagination_Skip").show();
				fa.find(".FrichUI_Table_Pagination_Tips").show();
				fa.find(".FrichUI_Table_Pagination_Select").hide();
			}
			else if(width >= skipWidth) {
				fa.find(".FrichUI_Table_Pagination_Skip").show();
				fa.find(".FrichUI_Table_Pagination_Tips").hide();
				fa.find(".FrichUI_Table_Pagination_Select").hide();
			}
			else {
				fa.find(".FrichUI_Table_Pagination_Skip").hide();
				fa.find(".FrichUI_Table_Pagination_Tips").hide();
				fa.find(".FrichUI_Table_Pagination_Select").hide();
			}
		}, 300);
		
		tableEntity = new TableEntity(dom, options);
		frichUI.push(tableEntity);
	}
	
	TableFactory.prototype.make = function(dom, customer){
		var options = this.initCreate(dom, customer);
		
		var fa = this;
		if(options.enableAjax) {
			$.ajax({
				type: "POST",
			    url: options.ajax.url,
			    data: {
			    	type: "table",
			    	data: options.ajax.data,
					current: options.pagination.current,
					rows: options.pagination.rows
			    },
			    beforeSend: function(){
			    	options.ajax.beforeSend();
			    },
			    success: function (data) {
			    	options.ajax.success(data, options);
			    	fa.load(dom, options);
			    },
			    error: function (err) {
			    	options.ajax.error(err);
			    }
			});
		}
		else {
			fa.load(dom, options);
		}
		
		
		//根据options设置contentHouse addClass no Pagnation
	}

	/* 表格持久化实体Entity */
	var TableEntity = function(dom, options){
		this.id = options.id;
		this.dom = dom;
		this.data = options.data;
		this.options = options;
		this.openMuiltSelect = function(){};
		this.closeMuiltSelect = function(){};
		this.openSingleSelect = function(){};
		this.closeSingleSelect = function(){};
		this.getValue = function(row, col) {
			return $(this.dom).find(".FrichUI_Table_r" + row + " .FrichUI_Table_c" + col).html();
		}
		this.getSingleValue = function(col) {
			return $(this.dom).find("input[name='radioC']:checked").parents("tr").find(".FrichUI_Table_c" + col).html();
		}
		this.getSingleRow = function() {
			var c = $(this.dom).find("input[name='radioC']:checked").parents("tr");
			if(c.length == 0)	{
				return;
			}
			else {
				var cl = $(c).attr("class").split(' ')[0].replace('FrichUI_Table_r', '');
				return this.getRowValue(parseInt(cl) - 1);
			}
		}
		this.getRowValue = function(row){
			return this.data[row];
		}
		
		this.getMuiltValue = function(col){
			var c = $(this.dom).find("input[name='checkboxC']:checked").parents("tr");
			if(c.length == 0)	{
				return;
			}
			else {
				var d = [];
				$.each(c, function(i, item){
					d.push($(item).find(".FrichUI_Table_c" + col).html());
				});
				return d;
			}
		}
		this.getMuiltRow = function(){
			var fa = this;
			
			var c = $(this.dom).find("input[name='checkboxC']:checked").parents("tr");
			if(c.length == 0)	{
				return;
			}
			else {
				var d = [];
				$.each(c, function(i, item){
					var cl = $(item).attr("class").split(' ')[0].replace('FrichUI_Table_r', '');
					d.push(fa.getRowValue(parseInt(cl) - 1));
				});
				return d;
			}
		}
		
		var te = this;

		if(options.enableMuiltSelect && options.enableSingleSelect) {
			if(options.showSingleSelect) {
				options.showMuiltSelect = false;
			}
		}
		
		if(options.enableMuiltSelect) {
			te.openMuiltSelect = function(){
				if(options.enableSingleSelect) {
					te.closeSingleSelect();
				}
				$(dom).find("input[name=checkboxC]:checked").prop("checked", false);
				$(dom).find(".FrichUI_Table_CheckColumn").fadeIn(500);
				te.isShowedMuiltSelect = true;
			}
			te.closeMuiltSelect = function(){
				$(dom).find(".FrichUI_Table_DataRow_Select").removeClass("FrichUI_Table_DataRow_Select");
				$(dom).find(".FrichUI_Table_CheckColumn").hide();
				te.isShowedMuiltSelect = false;
			}
			te.isShowedMuiltSelect = options.showMuiltSelect;
			if(te.isShowedMuiltSelect) {
				te.openMuiltSelect();
			}
			else {
				te.closeMuiltSelect();
			}
		}
		
		if(options.enableSingleSelect){
			te.openSingleSelect = function(){
				if(options.enableMuiltSelect) {
					te.closeMuiltSelect();
				}
				$(dom).find("input[name=radioC]:checked").prop("checked", false);
				$(dom).find(".FrichUI_Table_RadioColumn").fadeIn(500);
				te.isShowedSingleSelect = true;
			}
			te.closeSingleSelect = function(){
				$(dom).find(".FrichUI_Table_DataRow_Select").removeClass("FrichUI_Table_DataRow_Select");
				$(dom).find(".FrichUI_Table_RadioColumn").hide();
				te.isShowedSingleSelect = false;
			}
			te.isShowedSingleSelect = options.showSingleSelect;
			if(te.isShowedSingleSelect) {
				te.openSingleSelect();
			}
			else {
				te.closeSingleSelect();
			}
		}
		
		$(dom).find(".FrichUI_Table_House").scroll(function(){
			var fa = $(this);
			
			var y = fa.scrollTop();
			var x = fa.scrollLeft();
			
			var Dividing = options.dividing;
			//console.log(x + ";" + y);
			
			var TOP = fa.children(".FrichUI_Table_FloatTitle").height();
			var LEFT = fa.find(".FrichUI_Table_FloatColumn thead th").eq(0).width();
			
			var flot = fa.find(".FrichUI_Table_FloatTitle");
			var floc = fa.find(".FrichUI_Table_FloatColumn");
			var floh = fa.find(".FrichUI_Table_FloatHeader");
			
			flot.css("top", y + "px");
			floc.css("left", x + "px");
			floh.css({
				"left": x + "px",
				"top": y + "px"
			});
			
			if(y > TOP * Dividing) {
				flot.css({
					"visibility": "visible",
					"opacity": 1
				});
			}
			else {
				flot.css({
					"visibility": "hidden",
					"opacity": 0
				});
			}
		
			if(x > LEFT * Dividing) {
				floc.css({
					"visibility": "visible",
					"opacity": 1
				});
			}
			else {
				floc.css({
					"visibility": "hidden",
					"opacity": 0
				});
			}
			
			if(y > TOP * Dividing && x > LEFT * Dividing) {
				floh.css({
					"visibility": "visible",
					"opacity": 1
				});
			}
			else {
				floh.css({
					"visibility": "hidden",
					"opacity": 0
				});
			}
		});
		
		$(dom).find(".FrichUI_Table_DataRow").hover(function(){
			var rowName = $(this).attr("class").split(' ')[0];
			var fd = $(dom).find("." + rowName);
			$(fd).addClass("FrichUI_Table_DataRow_active");
		},function(){
			var rowName = $(this).attr("class").split(' ')[0];
			var fd = $(dom).find("." + rowName);
			$(fd).removeClass("FrichUI_Table_DataRow_active");
		});

		if(options.enableMuiltSelect) {
			$(dom).find(".FrichUI_Table_DataRow").click(function(){
				if(te.isShowedMuiltSelect) {
					var rowName = $(this).attr("class").split(' ')[0];
					var fd = $(dom).find("." + rowName);
					var chk = $(fd).find("input[type=checkbox]");
					if(!$(fd).hasClass("FrichUI_Table_DataRow_Select")){
						$(fd).addClass("FrichUI_Table_DataRow_Select");
						chk.prop("checked", true);
					}else {
						chk.prop("checked", false);
						$(fd).removeClass("FrichUI_Table_DataRow_Select");	
					}
				}
			})
		}
		
		if(options.enableSingleSelect){
			$(dom).find(".FrichUI_Table_DataRow").click(function(){
				if(te.isShowedSingleSelect) {
					var rowName = $(this).attr("class").split(' ')[0];
					var fd = $(dom).find("." + rowName);
					var rad = $(fd).find("input[type=radio]");
					if(!$(fd).hasClass("FrichUI_Table_DataRow_Select")){
						$(dom).find(".FrichUI_Table_DataRow").removeClass("FrichUI_Table_DataRow_Select");
						$(fd).addClass("FrichUI_Table_DataRow_Select");
						rad.prop("checked", true);
					}else {
						rad.prop("checked", false);
						$(fd).removeClass("FrichUI_Table_DataRow_Select");
					}
				}
			})
		}
		
		$(".FrichUI_Table_Pagination_Next").click(function(){
			options.pagination.current++;
			frichUI.Table.make(dom, options);
		})
		
		$(".FrichUI_Table_Pagination_Last").click(function(){
			options.pagination.current = options.pagination.total;
			frichUI.Table.make(dom, options);
		})
		
		$(".FrichUI_Table_Pagination_Page").click(function(){
			options.pagination.current = $(this).html();
			frichUI.Table.make(dom, options);
		})
		
		$(".FrichUI_Table_Pagination_First").click(function(){
			options.pagination.current = 1;
			frichUI.Table.make(dom, options);
		})
		
		$(".FrichUI_Table_Pagination_Previous").click(function(){
			options.pagination.current--;
			frichUI.Table.make(dom, options);
		})	
		
	}
		FrichUI.prototype.Table = new TableFactory();
	
	/*
	 * 2.3.2 菜单组件
	 */
	var MenuFactory = function(){
		Factory.call(this);
		
		this.defaul = $.extend(true, {}, this.defaul, {
			model: "<a href=\"{h}\"><label>{v}</label></a>",
			data: null
		});
		
		this.createOLLI = function(model, data, level){
			var ol = this.createOl("frichUI_Menu_House " + "frichUI_Menu_Level" + level);
			
			var param = model.match(reg1);
			for(var a in param){
				param[a] = param[a].replace(/\{/, '').replace(/\}/, '');
			}
			
			for(var i=0; i<data.length; i++){
				var li = this.createLi("frichUI_Menu_Room");
				
				var str = model;
				for(var j=0; j<param.length; j++){
					var d = data[i][param[j]];
					if(typeof(d) != "undefined" && d){
						var reg = new RegExp("\{"+param[j]+"\}", "g");
						str = str.replace(reg, d);
					}
				}
				
				li.append(str);
				
				if(typeof(data[i].c) != "undefined" && data[i].c && !$.isEmptyObject(data[i].c)){
					li.append(this.createOLLI(model, data[i].c, level+1));
				}
				
				ol.append(li);
			}
			
			return ol;
		};
		
	}
	MenuFactory.prototype = new Factory();

	MenuFactory.prototype.make = function(dom, customer){
		var options = this.initCreate(dom, customer);
		
		/* 创建基架 */
		var frame = this.createFrame('frichUI_Menu_Frame');
		
		frame.append(this.createOLLI(options.model, options.data, 1));
		
		frame.appendTo(dom);
		
		menuEntity = new MenuEntity(frame, options);
		frichUI.push(menuEntity);
		
		return menuEntity;
	};
	
	/* 菜单持久化实体Entity */
	var MenuEntity = function(frame, options){
		this.id = options.id;
		this.frame = frame;
		this.options = options;

		$(frame).find("a").bind('click', function(event){
			var fh = $(this).parent(".frichUI_Menu_Room");
			
			if(!fh.hasClass("frichUI_Menu_Showed"))
			{
				// 设置滑动效果
				fh.css("height", "auto");
				var AutoHeight = fh.height();
				fh.css("height", "35px");
				fh.animate({height: AutoHeight + "px"}, (AutoHeight-35) * 2.5, function(){
					fh.css("height", "auto");
				});
				fh.addClass("frichUI_Menu_Showed");
				
			}
			else if(fh.hasClass("frichUI_Menu_Select"))
			{
				fh.css("height", "auto");
				var AutoHeight = fh.height();
				fh.animate({height: "35px"}, (AutoHeight-35) * 2.5);
				fh.removeClass("frichUI_Menu_Showed");
			}
			else
			{
				var fr = fh.parents(".frichUI_Menu_Select");
				if(fr.length != 0)
				{
					fh.css("height", "auto");
					var AutoHeight = fh.height();
					fh.animate({height: "35px"}, (AutoHeight-30) * 2.5);
					fh.removeClass("frichUI_Menu_Showed");
				}
			}
		});
		
		$(frame).find(".frichUI_Menu_Level1").children(".frichUI_Menu_Room").bind('click', function(event){
			var fa = $(this);

			$(frame).find(".frichUI_Menu_Room").removeClass("frichUI_Menu_Select");
			fa.addClass("frichUI_Menu_Select");
			
		});
	}
	
	FrichUI.prototype.Menu = new MenuFactory();
	/*
	 * 2.3.3 多级列表组件
	 */
	
	
	/*
	 * 2.3.4 表格组件
	 */
	
	
	/*
	 * 2.3.5 会话组件
	 */	
	var DialogFactory = function(){
		Factory.call(this);
		
		this.tools = {
			close: "FrichUI_Dialog_Close",
			full: "FrichUI_Dialog_Full",
			min: "FrichUI_Dialog_Min"
		}
		
		this.icons = {
			info: "FrichUI_Dialog_Info",
			help: "FrichUI_Dialog_Help",
			warnning: "FrichUI_Dialog_Warning",
			success: "FrichUI_Dialog_Success",
			error: "FrichUI_Dialog_Error"
		}
		
		this.control = {
			yes: "FrichUI_Dialog_Yes",
			no: "FrichUI_Dialog_No",
			confirm: "FrichUI_Dialog_Confirm",
			cancle: "FrichUI_Dialog_Cancle"
		}
		
		this.defaul = $.extend(true, {}, this.defaul, {
			type: "info",
			enableCover: true,
			closeonClick: function(){
				alert("点击了关闭");
			},
			yesClick: function(){
				alert("点击了是");
			},
			noClick: function(){
				alert("点击了否");
			},
			confirmClick: function(){
				alert("点击了确定");
			},
			cancleClick: function(){
				alert("点击了取消");
			}
		});
		
		this.infoDefaul = $.extend(true, {}, this.defaul, {
			title: "消息框",
			tools: ["close"],
			icon: "info",
			message: "这是一个消息框",
			control: ["confirm"]
		});

		this.helpDefaul = $.extend(true, {}, this.defaul, {
			title: "询问框",
			tools: ["close"],
			icon: "help",
			message: "这是一个询问框",
			control: ["yes", "no", "cancle"]
		});
		
		this.warnningDefaul = $.extend(true, {}, this.defaul, {
			title: "警告框",
			tools: ["close"],
			icon: "warnning",
			message: "这是一个警告框",
			control: ["confirm", "cancle"]
		});
		
		this.successDefaul = $.extend(true, {}, this.defaul, {
			title: "成功框",
			tools: ["close"],
			icon: "success",
			message: "这是一个成功框",
			control: ["confirm"]
		});
		
		this.errorDefaul = $.extend(true, {}, this.defaul, {
			title: "错误框",
			tools: ["close"],
			icon: "error",
			message: "这是一个错误框",
			control: ["confirm"]
		});
		
		
		this.createTitle = function(options){
			var title = this.createDiv("FrichUI_Dialog_Title");
			
			var h5 = this.createH(5, "FrichUI_Dialog_Name", options.title);
			
			title.append(h5);
			
			var def = {
				close: false,
				min: false,
				full: false
			}
			
			for(var i=0; i<options.tools.length; i++){
				switch(options.tools[i]){
					case "close":
						def.close = true;
						break;
					case "min":
						def.min = true;
						break;
					case "full":
						def.full = true;
						break;
					default: break;
				}
			}
			
			if(def.close){
				var tool = this.createI(this.tools.close);
				title.append(tool);
			}
			if(def.full){
				var tool = this.createI(this.tools.full);
				title.append(tool);
			}
			if(def.min){
				var tool = this.createI(this.tools.min);
				title.append(tool);
			}
			
			return title;
		}
		
		this.createContent = function(options){
			var content;
			if(options.enableCover){
				content = this.createDiv("FrichUI_Dialog_Content");
			}
			else {
				content = this.createDiv("FrichUI_Affair_Content");
			}

			for(var i in this.icons){
				if(i == options.icon){
					var icon = this.createI(this.icons[i]);
					content.append(icon);
				}
			}

			var a = this.createA(undefined, options.message);
			
			content.append(a);
			return content;
		}

		this.createFoot = function(options){
			var foot = this.createDiv("FrichUI_Dialog_Foot");
			
			var def = {
				yes: false,
				no: false,
				confirm: false,
				cancle: false,
			}
			
			for(var i=0; i<options.control.length; i++){
				switch(options.control[i]){
					case "yes":
						def.yes = true;
						break;
					case "no":
						def.no = true;
						break;
					case "confirm":
						def.confirm = true;
						break;
					case "cancle":
						def.cancle = true;
						break;
					default: break;
				}
			}
			
			if(def.cancle){
				var control = this.createA(this.control.cancle, "取消");
				foot.append(control);
			}
			if(def.no){
				var control = this.createA(this.control.no, "否");
				foot.append(control);
			}
			if(def.yes){
				var control = this.createA(this.control.yes, "是");
				foot.append(control);
			}
			if(def.confirm){
				var control = this.createA(this.control.confirm, "确定");
				foot.append(control);
			}
			
			return foot;
		}
		
	}

	DialogFactory.prototype = new Factory();
	
	DialogFactory.prototype.make = function(dom, customer){
		var options = this.initCreate(dom, customer);
		
		switch(options.type){
			case "info":
				options = $.extend(true, {}, this.infoDefaul, options);
				break;
			case "help":
				options = $.extend(true, {}, this.helpDefaul, options);
				break;
			case "warnning":
				options = $.extend(true, {}, this.warnningDefaul, options);
				break;
			case "success":
				options = $.extend(true, {}, this.successDefaul, options);
				break;
			case "error":
				options = $.extend(true, {}, this.errorDefaul, options);
				break;
			default: break;
		}
		
		/* 创建基架 */
		var frame = this.createFrame("FrichUI_Dialog_Frame");
		if(options.isiFrame){
			frame.append(this.createTitle(options));
			
			var iframe = this.createiFrame("FrichUI_Dialog_iFrame");
			frame.append(iframe);
			
			frame.append(this.createFoot(options));
			
		}else{
			frame.append(this.createTitle(options));

			frame.append(this.createContent(options));

			frame.append(this.createFoot(options));
		}
		/* 添加遮罩层 */
		var cover;
		if(options.enableCover){
			cover = this.createFrame("FrichUI_Cover");
			frame.appendTo(cover);
			cover.appendTo($(document).find("body").eq(0));
			
			$(frame).find(".FrichUI_Dialog_Close").bind('click',function(event){
				$(cover).remove();
				$(frame).remove();
			});
			
		}else{
			frame.appendTo($(document).find("body").eq(0));
			
			$(frame).find(".FrichUI_Dialog_Close").bind('click',function(event){
				$(frame).remove();
			});
		}
		
		var body = $(top.document.body);
		frame.appendTo(body);
		
		var cWidth = body.width();
		var cHeight = body.height();
			
		/* 设置长宽 */
		var fWidth = $(frame).width();
		var fHeight = $(frame).height();
		frame.css({
			"top": (cHeight + options.offsetY - fHeight - 4) / 2,
			"left": (cWidth + options.offsetX - fWidth - 4) / 2,
			"opacity": 1,
			"transform": "scale(1)",
			"z-index": options.zIndex + 10
		})
	
		
		
		/*
		$(frame).find(".FrichUI_Dialog_Full").bind('click',function(event){
			if($(frame).find(".FrichUI_Dialog_Full").hasClass("FrichUI_Dialog_FullDone")){
				$(cover).css({
					"left":"0",
					"top":"0",
					"width":"100%",
					"height":"100%",
					"max-width":"100%",
					"min-width":"0"
				});
				$(cover).css({
					"max-width":"100%",
					"min-width":"0"
				});
				$(frame).animate({
					"left":"30%",
					"top":"30%",
					"width":"244px",
					"height":"164px",
				},500);
				$(frame).find(".FrichUI_Dialog_Content").css({
					"height":"30px"
				});
				$(frame).find(".FrichUI_Dialog_Content").css({
					"display":"block"
				});
				$(frame).find(".FrichUI_Dialog_Foot").css({
					"display":"block"
				});
				$(frame).find(".FrichUI_Dialog_Full").removeClass("FrichUI_Dialog_FullDone");
			}else{
				$(cover).css({
					"left":"0",
					"top":"0",
					"width":"100%",
					"height":"100%"
				});
				$(frame).animate({
					"left":"0",
					"top":"0",
					"width":"100%",
					"height":"100%"
				},500);
				$(frame).find(".FrichUI_Dialog_Content").css({
					"height":"80%"
				});
				$(frame).find(".FrichUI_Dialog_Content").css({
					"display":"block"
				});
				$(frame).find(".FrichUI_Dialog_Foot").css({
					"display":"block"
				});
				$(frame).find(".FrichUI_Dialog_Full").addClass("FrichUI_Dialog_FullDone");
				$(frame).find(".FrichUI_Dialog_Min").removeClass("FrichUI_Dialog_MinDone");
			}
		});
		
		$(frame).find(".FrichUI_Dialog_Min").bind('click',function(event){
			if($(frame).find(".FrichUI_Dialog_Min").hasClass("FrichUI_Dialog_MinDone")){
				$(cover).animate({
					"left":"0",
					"top":"0",
					"width":"100%",
					"height":"100%"
				},500);
				$(frame).animate({
					"left":"30%",
					"top":"30%",
					"width":"244px",
					"height":"100px"
				},500);
				$(frame).find(".FrichUI_Dialog_Content").css({
					"display":"block"
				});
				$(frame).find(".FrichUI_Dialog_Foot").css({
					"display":"block"
				});
				$(frame).find(".FrichUI_Dialog_Min").removeClass("FrichUI_Dialog_MinDone");
			}else{
				$(cover).animate({
					"left":"50%",
					"top":"0",
					"width":"150px",
					"height":"40px"
				},500);
				$(frame).animate({
					"left":"50%",
					"top":"0",
					"width":"150px",
					"height":"40px"
				},500);
				$(frame).find(".FrichUI_Dialog_Content").css({
					"display":"none"
				});
				$(frame).find(".FrichUI_Dialog_Foot").css({
					"display":"none"
				});
				$(frame).find(".FrichUI_Dialog_Min").addClass("FrichUI_Dialog_MinDone");
				$(frame).find(".FrichUI_Dialog_Full").removeClass("FrichUI_Dialog_FullDone");
			}
		});*/

		
		/* 创建Dialog实体 */
		return 0;
	};

	FrichUI.prototype.Dialog = new DialogFactory();
	

	
	
	/*
	 * 2.3.6 进度条组件
	 */	
	var ProgressFactory = function(){
		Factory.call(this);
		
		this.defaul = $.extend(true,{},this.defaul,{
			displayNum:true,
			numOffset:"right"
		});
		
		this.createContent = function(options){
			var content = this.createDiv("FrichUI_Progress_Content");
			var entity = this.createDiv("FrichUI_Progress_Entity");
			content.append(entity);
			var progress=this.createDiv("FrichUI_Pro_Progress");
			progress.width(0);
			entity.append(progress);
			if(options.displayNum){
				var num;
				var triangle;
				switch(options.numOffset){
					case "top":
						num = this.createDiv("FrichUI_Pro_Num FrichUI_Pro_Top");
						triangle=this.createDiv("FrichUI_Pro_Top_Triangle");
						break;
					case "bottom":
						num = this.createDiv("FrichUI_Pro_Num FrichUI_Pro_Bottom");
						triangle=this.createDiv("FrichUI_Pro_Bottom_Triangle");
						break;
					case "left":
						num = this.createDiv("FrichUI_Pro_Num FrichUI_Pro_Left");
						break;
					case "center":
						num = this.createDiv("FrichUI_Pro_Num FrichUI_Pro_Center");
						break;
					case "right":
						num = this.createDiv("FrichUI_Pro_Num FrichUI_Pro_Right");
						break;
					default:break;
				}
				progress.append(num);
				progress.append(triangle);
			}
			return content;
		}
		
	}

	ProgressFactory.prototype = new Factory();
	
	ProgressFactory.prototype.make = function(dom, customer){
		var options = this.initCreate(dom, customer);
			/* 创建基架 */
		
		var frame = this.createFrame("FrichUI_Progress_Frame");
			
			frame.append(this.createContent(options));
			
			var c = $($(window).eq(0)[0].parent.document).find('body');
			$(c).append(frame);
			
			
			
			
			/* 注册事件 */
			/* 1.  加载事件 */
			
			frichUI.push(new ProgressEntity(frame, options));
		
		var cover;
		if(options.enableCover){
			cover = this.createFrame("FrichUI_Cover");
			frame.appendTo(cover);
			cover.appendTo($(document).find("body").eq(0));
		}
		
		
		return 0;
	};

	/* 进度条实体Entity */
	var ProgressEntity = function(frame, options){
		this.id = options.id;
		this.frame = frame;
		this.options = options;
		this.max = 100;
		this.progress = 0;
		
		this.setProgress = function(data){
			if(this.progress + data <= this.max){
				this.progress += data;
				var prg = $(this.frame).find(".FrichUI_Pro_Progress");
				var prgN = $(this.frame).find(".FrichUI_Pro_Num");
				prg.css({
					"width": (this.progress + "%")
				});
				prgN.html(this.progress + "%");
			}
		}
		
		this.getProgress = function(){
			return this.progress;
		}
	}
	
	FrichUI.prototype.Progress = new ProgressFactory();
	
	
	
	/*
	 * 2.3.6事务组件
	 */	
	var AffairFactory = function(){
		Factory.call(this);
		
		this.icons = {
			success: "FrichUI_Dialog_Success",
			error: "FrichUI_Dialog_Error"
		}
		
		this.defaul = $.extend(true, {}, this.defaul, {
			type: "info",
			offsetX: 0,
			offsetY: -100,
			zIndex: 1000,
			enableCover: false
		});
		this.successDefaul = $.extend(true, {}, this.defaul, {
			icon: "success",
			message: "这是一个成功框"
		});
		
		this.errorDefaul = $.extend(true, {}, this.defaul, {
			icon: "error",
			message: "这是一个错误框"
		});
			
		this.createContent = function(options){
			var content;
			content = this.createDiv("FrichUI_Affair_Content");
			
			for(var i in this.icons){
				if(i == options.icon){
					var icon = this.createI(this.icons[i]);
					content.append(icon);
				}
			}

			var a = this.createA(undefined, options.message);
			content.append(a);
			return content;
		}
		
		
	}

	AffairFactory.prototype = new Factory();
	
	AffairFactory.prototype.make = function(dom, customer){
		var options = this.initCreate(dom, customer);
		
		switch(options.type){
			case "success":
				options = $.extend(true, {}, this.successDefaul, options);
				break;
			case "error":
				options = $.extend(true, {}, this.errorDefaul, options);
				break;
			default: break;
		}
		
		var frame = this.createFrame("FrichUI_Affair_Frame");

		frame.append(this.createContent(options));
		
		var body = $(top.document.body);
		frame.appendTo(body);
		
		var cWidth = body.width();
		var cHeight = body.height();
			
		/* 设置长宽 */
		var fWidth = $(frame).width();
		var fHeight = $(frame).height();
		frame.css({
			"top": (cHeight + options.offsetY - fHeight - 4) / 2,
			"left": (cWidth + options.offsetX - fWidth - 4) / 2,
			"opacity": 1,
			"transform": "scale(1)",
			"z-index": options.zIndex + 10
		})
			
		
		/* 注册事件 */
		/*1. 2000ms 消失 */
		setTimeout(function(){
			$(frame).fadeOut(500, function(){
				$(frame).remove();
			});
		}, 2000);

		 /*创建Dialog实体 */
		return 0;
	}
	
	FrichUI.prototype.Affair = new AffairFactory();

	
	/*
	 * 2.3.8 form组件
	 */	
	var FormFactory = function(){
		Factory.call(this);		
		
		this.defaul = $.extend(true, {}, this.defaul, {
			model: "<div class='FrichUI_Form_Content'><i class='FrichUI_Form_checkBox FrichUI_Form_checkBox_nocheck'></i><a>{name}</a></div>",
			data: null
		});

		this.createULLI = function(options, model, data, level){
			var child = options.child;
			
			var ul = this.createUl("FrichUI_Form_House " + "FrichUI_Form_Level" + level);
			
			var param = model.match(reg1);
			for(var a in param){
				param[a] = param[a].replace(/\{/, '').replace(/\}/, '');
			}
			
			for(var i=0; i<data.length; i++){
				var li = this.createLi("FrichUI_Form_Room");
				
				var str = model;
				for(var j=0; j<param.length; j++){
					var d = data[i][param[j]];
					if(frichUI.istEmpty(d)){
						var reg = new RegExp("\{"+param[j]+"\}", "g");
						str = str.replace(reg, d);
					}
				}
				
				li.append(str);
				
				if(!$.isEmptyObject(data[i][child])){
					li.append(this.createULLI(options, model, data[i][child], level+1));
				}
				
				ul.append(li);
			}
			
			return ul;
		};
		
	}

	FormFactory.prototype = new Factory();
	
	FormFactory.prototype.make = function(dom, customer){
		var options = this.initCreate(dom, customer);
		
		/* 创建基架 */
		var frame = this.createFrame("FrichUI_Form_Frame");
		
		frame.append(this.createULLI(options, options.model, options.data, 1));
		frame.appendTo(dom);
		formEntity = new FormEntity(frame, options);
		frichUI.push(formEntity);
		
		return formEntity;
	};
	
	/*Form实体*/
	var FormEntity = function(frame,options){
		this.frame= frame;
		this.options=options;
		
		var fa = this;
		$(frame).find(".FrichUI_Form_checkBox").click(function() {
			fa.iterator($(this));
		});
		
		this.iterator = function (i){
			if(i.hasClass("FrichUI_Form_checkBox_checked")) {
				i.parent(".FrichUI_Form_Content").parent(".FrichUI_Form_Room").find(".FrichUI_Form_checkBox")
				.removeClass().addClass("FrichUI_Form_checkBox FrichUI_Form_checkBox_nocheck");
				this.CheckTree($(".FrichUI_Form_Frame"), 1);
			}
			else if (i.hasClass("FrichUI_Form_checkBox_halfcheck")) {
				i.parent(".FrichUI_Form_Content").parent(".FrichUI_Form_Room").find(".FrichUI_Form_checkBox")
				.removeClass().addClass("FrichUI_Form_checkBox FrichUI_Form_checkBox_checked");
				
				this.CheckTree($(".FrichUI_Form_Frame"), 1);
			}
			else if (i.hasClass("FrichUI_Form_checkBox_nocheck")){
				i.parent(".FrichUI_Form_Content").parent(".FrichUI_Form_Room").find(".FrichUI_Form_checkBox")
				.removeClass().addClass("FrichUI_Form_checkBox FrichUI_Form_checkBox_checked");
				
				this.CheckTree($(".FrichUI_Form_Frame"), 1);
			}
		}
		
		this.CheckTree = function (i, level){
			var rooms = $(i).children(".FrichUI_Form_Level" + level).children(".FrichUI_Form_Room");
			
			if(rooms.length > 0) {
				var total = 0;
				var must = false;
				for(var k=0; k<rooms.length; k++) {
					var count = this.CheckTree(rooms[k], level + 1);
					if(count == "half") {
						must = true;
					}
					else {
						total += count;
					}
				}
				if(must) {
					$(i).children(".FrichUI_Form_Content")
					.children("i").removeClass().addClass("FrichUI_Form_checkBox FrichUI_Form_checkBox_halfcheck");
					return "half";
				}
				else if(total == 0) {
					$(i).children(".FrichUI_Form_Content")
					.children("i").removeClass().addClass("FrichUI_Form_checkBox FrichUI_Form_checkBox_nocheck");
					return 0;
				}
				else if(total == rooms.length) {
					$(i).children(".FrichUI_Form_Content")
					.children("i").removeClass().addClass("FrichUI_Form_checkBox FrichUI_Form_checkBox_checked");
					return 1;
				}
				else if(total < rooms.length) {
					$(i).children(".FrichUI_Form_Content")
					.children("i").removeClass().addClass("FrichUI_Form_checkBox FrichUI_Form_checkBox_halfcheck");
					return "half";
				}
			}
			else {
				if($(i).children(".FrichUI_Form_Content")
						.children("i")
						.hasClass("FrichUI_Form_checkBox_checked")){
					return 1;
				}
				else {
					return 0;
				}
			}	
		
		}
		
	}
		
		
		
		
		/*function iterator(i){
			if(i.hasClass("FrichUI_Form_checkBox_checked")) {
				i.parent(".FrichUI_Form_Content").parent(".FrichUI_Form_Room").find(".FrichUI_Form_checkBox")
				.removeClass().addClass("FrichUI_Form_checkBox FrichUI_Form_checkBox_nocheck");
				CheckTree($(".FrichUI_Form_Frame"), 1);
			}
			else if (i.hasClass("FrichUI_Form_checkBox_halfcheck")) {
				i.parent(".FrichUI_Form_Content").parent(".FrichUI_Form_Room").find(".FrichUI_Form_checkBox")
				.removeClass().addClass("FrichUI_Form_checkBox FrichUI_Form_checkBox_checked");
				
				CheckTree($(".FrichUI_Form_Frame"), 1);
			}
			else if (i.hasClass("FrichUI_Form_checkBox_nocheck")){
				i.parent(".FrichUI_Form_Content").parent(".FrichUI_Form_Room").find(".FrichUI_Form_checkBox")
				.removeClass().addClass("FrichUI_Form_checkBox FrichUI_Form_checkBox_checked");
				
				CheckTree($(".FrichUI_Form_Frame"), 1);
			}
		}*/
	
		/* 注册事件 */
	
		/* 创建Dialog实体 */
	
	FrichUI.prototype.Form = new FormFactory();
	
	
	
	/*
	 * 3. 静态定义层
	 */
	
	/*
	 * 4. 开放接口
	 */
	window.F = window.frichUI = new FrichUI();
});