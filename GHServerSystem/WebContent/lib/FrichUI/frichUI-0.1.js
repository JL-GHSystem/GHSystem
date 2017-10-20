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
	
	var reg1 = /\{[^{}]+\}/g;		//匹配字符串，用于menu的model检验
	var reg2 = /\{v}/g;				//匹配字符串，用于table的model检验
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
			$("head").append(link);
		},
		change: function(root){
			var th = this;
			$(th.link).remove();
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
			
			this.theme = theme || this.FRICHUI_THEME_ORANGE;
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
			id: "extend"
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
	    createTable: function(frClass){
	    	var table = $("<table></table>");
	    	table.attr({
	    		class: frClass
	    	})
	        return table;
	    },
	    createThead: function(frClass){
	    	var thead = $("<thead></thead>");
	    	thead.attr({
	    		class: frClass
	    	})
	        return thead;
	    },
	    createTbody: function(frClass){
	    	var tbody = $("<tbody></tbody>");
	    	tbody.attr({
	    		class: frClass
	    	})
	        return tbody;
	    },
	    createTr: function(frClass){
	    	var tr = $("<tr></tr>");
	    	tr.attr({
	    		class: frClass
	    	})
	        return tr;
	    },
	    createTh: function(frClass){
	    	var th = $("<th></th>");
	    	th.attr({
	    		class: frClass
	    	})
	        return th;
	    },
	    createTd: function(frClass){
	    	var td = $("<td></td>");
	    	td.attr({
	    		class: frClass
	    	})
	        return td;
	    },
		initCreate: function(dom, customer){
			options = $.extend(true, {}, this.defaul, customer);
			$(dom).empty();
			
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
			width: ,				*enableWrap: true时此项必须
			maxHeight: 				*enableWrap: true时此项必须
		}
		*/
		this.defaulModel = {
			model: "{v}",
			sort: "last",
			fixed: false,
			textAlign: "center",
			emptyDisplay: "",
			enableWrap: false
		}
		
		this.selectModel = {
			width: 15
		}

		this.serialModel = {
			name: "序号",
			width: 30
		}
		
		this.defaulPage = {
			total: 500,
			records: 10000,
			current: 1
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
			title: "FrichUI Simple",
			models: [],							//列模型 [{},{}]
			//data: null						//数据模型 {v:,c:},{v:,c:}
			pagnition: {
				pages: 1,						//必须大于0
				rows: 20
			},
			page: this.defaulPage
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
					var model = this.runTime.fixedModels[j].model;

					model = model.replace(reg2, D);
					
					var div = this.createDiv("FrichUI_Table_c" + i, model);

					td.append(div);
					i++;

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
						
						var model = this.runTime.dataModels[j].model;

						model = model.replace(reg2, D);
						
						var div = this.createDiv("FrichUI_Table_c" + i, model);

						td.append(div);
						i++;

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
			
			tips.html("当前<a id=\"FrichUI_Table_Pagination_CurP\">"+ options.page.current +"</a>" +
					"页/共<a id=\"FrichUI_Table_Pagination_TolP\">"+ options.page.total +"</a>页&nbsp;共" +
							"<a id=\"FrichUI_Table_Pagination_TolR\">"+ options.page.records +"</a>记录");
			
			house.append(tips);
			/*
			pagnition: {
				pages: 0,						//*未实现
				rows: 20
			},
			Page: {
				total: 500,
				records: 10000,
				current: 1
			}
			*/

			if(options.pagnition.pages < 1) {
				options.pagnition.pages = 1;
			}
			
			var skip = this.createDiv("FrichUI_Table_Pagination_Skip");
			if(options.page.total > 1) {
				skip.append("至<input class=\"FrichUI_Table_Pagination_Skip_Input\" type=\"text\" />页"
						+ "<a class=\"FrichUI_Table_Pagination_Skip_Submit\">跳转</a>");
			}
			house.append(skip);
			
			var select = this.createDiv("FrichUI_Table_Pagination_Select");
			if(options.page.current == 1) {
				select.append("<a class=\"FrichUI_Table_Pagination_First_undown\"></a>"
						+ "<a class=\"FrichUI_Table_Pagination_Previous_undown\"></a>");
				
			}
			else {
				select.append("<a class=\"FrichUI_Table_Pagination_First\"></a>"
						+ "<a class=\"FrichUI_Table_Pagination_Previous\"></a>");
				
			}
			
			if(options.page.total > options.pagnition.pages*2 + 5) {
				if(options.page.current <= options.pagnition.pages + 3){
					for(var i=1; i<=options.pagnition.pages*2 + 3; i++){
						if(i == options.page.current) {
							select.append(this.createA("FrichUI_Table_Pagination_Page_undown", i));
						}
						else {
							select.append(this.createA("FrichUI_Table_Pagination_Page", i));
						}
					}
					select.append(this.createA("FrichUI_Table_Pagination_Decode", '…'));
					for(var i=options.page.total - 1; i<=options.page.total; i++){
						select.append(this.createA("FrichUI_Table_Pagination_Page", i));
					}
				}
				else if(options.page.current >= (options.page.total - (options.pagnition.pages + 2))){
					for(var i=1; i<=2; i++){
						select.append(this.createA("FrichUI_Table_Pagination_Page", i));
					}
					select.append(this.createA("FrichUI_Table_Pagination_Decode", '…'));
					for(var i=(options.page.total - (options.pagnition.pages*2 + 2))
							; i<= options.page.total; i++){
						if(i == options.page.current) {
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
					for(var i = options.page.current-1; i<=options.page.current+1; i++){
						if(i == options.page.current) {
							select.append(this.createA("FrichUI_Table_Pagination_Page_undown", i));
						}
						else {
							select.append(this.createA("FrichUI_Table_Pagination_Page", i));
						}
					}
					select.append(this.createA("FrichUI_Table_Pagination_Decode", '…'));
					for(var i=options.page.total - 1; i<=options.page.total; i++){
						select.append(this.createA("FrichUI_Table_Pagination_Page", i));
					}
				}
				
			}
			else {
				for(var i=1; i<options.page.total; i++){
					select.append("<a class=\"FrichUI_Table_Pagination_Page\">"+ i +"</a>");
				}
			}

			if(options.page.current == options.page.total) {
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
	}
	TableFactory.prototype = new Factory();

	TableFactory.prototype.make = function(dom, customer){
		var options = this.initCreate(dom, customer);

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
			var height = $(dom).height();
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
			
			$(Frame).find(".FrichUI_Table_House").height(height);
			

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
			/*
			if(width > )
			
			*/
		}, 300);
		
		tableEntity = new TableEntity(dom, options);
		frichUI.push(tableEntity);
		//根据options设置contentHouse addClass no Pagnation
	}

	/* 表格持久化实体Entity */
	var TableEntity = function(dom, options){
		this.id = options.id;
		this.openMuiltSelect = function(){};
		this.closeMuiltSelect = function(){};
		this.openSingleSelect = function(){};
		this.closeSingleSelect = function(){};
		
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
				$(dom).find(".FrichUI_Table_CheckColumn").show();
				te.isShowedMuiltSelect = true;
			}
			te.closeMuiltSelect = function(){
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
				$(dom).find(".FrichUI_Table_RadioColumn").show();
				te.isShowedSingleSelect = true;
			}
			te.closeSingleSelect = function(){
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
		
		if(options.showSingleSelect) {
			$(dom).find("input[type=checkbox]").hide();
			options.showMuiltSelect = false;
		}
		else if(options.showMuiltSelect){
			$(dom).find("input[type=radio]").hide();
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
	}
	
	
	FrichUI.prototype.Table = new TableFactory();
	
	/*
	 * 2.3.2 菜单组件
	 */
	var MenuFactory = function(){
		Factory.call(this);
		
		this.defaul = $.extend(true, {}, this.defaul, {
			model: "<a href=\"{h}\"><label>{v}</label></a>",
			child: "c",
			sortId: null,
			data: null
		});
		
		this.createOLLI = function(options, model, data, level){
			var child = options.child;
			
			var ol = this.createOl("FrichUI_Menu_House " + "FrichUI_Menu_Level" + level);
			
			var param = model.match(reg1);
			for(var a in param){
				param[a] = param[a].replace(/\{/, '').replace(/\}/, '');
			}
			
			for(var i=0; i<data.length; i++){
				var li = this.createLi("FrichUI_Menu_Room");
				
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
					li.append(this.createOLLI(options, model, data[i][child], level+1));
				}
				
				ol.append(li);
			}
			
			return ol;
		};
		
		this.sortData = function(bool, options, data){
			for(var i=0; i<data.length-1; i++){
				for(var j=i+1; j<data.length; j++){
					if(data[i][options.sortId] > data[j][options.sortId]) {
						a = data[i];
						data[i] = data[j];
						data[j] = a;
					}
				}
			}
			
			if(bool) {
				for(var i=0; i<data.length; i++){
					data[i][options.child] = this.sortData(bool, options, data[i][options.child]);
				}
			}
			return data;
		}
		
	}
	MenuFactory.prototype = new Factory();

	MenuFactory.prototype.make = function(dom, customer){
		var options = this.initCreate(dom, customer);
		
		if(options.sortId != null) {
			options.data = this.sortData(true, options, options.data);
		}
		/* 创建基架 */
		var frame = this.createFrame('FrichUI_Menu_Frame');
		
		frame.append(this.createOLLI(options, options.model, options.data, 1));
		
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
			var fh = $(this).parent(".FrichUI_Menu_Room");
			
			if(!fh.hasClass("FrichUI_Menu_Showed"))
			{
				// 设置滑动效果
				fh.css("height", "auto");
				var AutoHeight = fh.height();
				fh.css("height", "35px");
				fh.animate({height: AutoHeight + "px"}, (AutoHeight-35) * 2.5, function(){
					fh.css("height", "auto");
				});
				fh.addClass("FrichUI_Menu_Showed");
			}
			else if(fh.hasClass("FrichUI_Menu_Select"))
			{
				fh.css("height", "auto");
				var AutoHeight = fh.height();
				fh.animate({height: "35px"}, (AutoHeight-35) * 2.5);
				fh.removeClass("FrichUI_Menu_Showed");
			}
		});
		
		$(frame).find("li").bind('click', function(event){
			var fa = $(this);

			$(frame).find(".FrichUI_Menu_Room").removeClass("FrichUI_Menu_Select");
			fa.addClass("FrichUI_Menu_Select");
			
			event.stopPropagation();
		});
	}
	
	FrichUI.prototype.Menu = new MenuFactory();
	/*
	 * 2.3.3 多级列表组件
	 */
	
	
	/*
	 * 2.3.4 会话组件
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
			closeClick: function(){
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
		
		/* 添加遮罩层 */
		var cover;
		if(options.enableCover){
			cover = this.createFrame("FrichUI_Cover");
			
			/* 创建基架 */
			var frame = this.createFrame("FrichUI_Dialog_Frame");
			if(options.isiFrame){
				frame.append(this.createTitle(options));
				
				var iframe = this.createiFrame("FrichUI_Dialog_iFrame");
				iframe.src="http://www.runoob.com"
				frame.append(iframe);
				
				frame.append(this.createFoot(options));
				
			}else{
				frame.append(this.createTitle(options));

				frame.append(this.createContent(options));

				frame.append(this.createFoot(options));
			}
			

			frame.appendTo(cover);
			cover.appendTo($(document).find("body").eq(0));
			
			$(frame).animate({
				"width":"244px",
				"height":"164px",
				"opacity":"1"
			},500);
			
			$(frame).find(".FrichUI_Dialog_Close").bind('click',function(event){
				$(cover).remove();
			});
			
			$(frame).find(".FrichUI_Dialog_Full").bind('click',function(event){
				if($(frame).find(".FrichUI_Dialog_Full").hasClass("FrichUI_Dialog_FullDone")){
					$(cover).css({
						"left":"0",
						"top":"0",
						"width":"100%",
						"height":"100%"
					});
					$(frame).animate({
						"left":"30%",
						"top":"30%",
						"width":"244px",
						"height":"164px"
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
			});
		}
		else {
			
			/* 创建基架 */
			var frame = this.createFrame("FrichUI_Dialog_Frame");

			frame.append(this.createContent(options));

			frame.appendTo($(document).find("body").eq(0));
			
			frame.css({
				"min-width":"0"
			});
			
			/* 注册事件 */
			/* 1. 2000ms 消失 */
			setTimeout(function(){
				$(frame).fadeOut(500, function(){
					$(frame).remove();
				});
			}, 2000); 
		}
		
		/* 创建Dialog实体 */
		return 0;
	};

	FrichUI.prototype.Dialog = new DialogFactory();
	
	
	
	/*
	 * 3. 静态定义层
	 */
	
	/*
	 * 4. 开放接口
	 */
	window.F = window.frichUI = new FrichUI();
	
});