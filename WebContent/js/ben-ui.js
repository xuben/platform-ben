/**
 * 目标:类似于jQuery或者ext
 */

/**
 * 道格拉斯的object方法（等同于object.create方法）
 */
function object(o) {
    var F = function () {};
    F.prototype = o;
    return new F();
}

function createXMLHttpRequest(){
	var xmlHttp;
	if(window.ActiveXObject){
		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	}else if(window.XMLHttpRequest){
		xmlHttp = new XMLHttpRequest();
	}
	return xmlHttp;
}

function addEvent(el, type, fn){
	if(el.addEventListener){
		el.addEventListener(type, fn, false);
	}else{//兼容ie6/7/8
		el.attachEvent('on'+type, fn);
	}
}

/**
 * dom元素
 */
function DomItem(){
}
DomItem.prototype.render = function(parentId){//dom元素渲染
	var parent = document.getElementById(parentId);
	if(parent){
		parent.appendChild(this.item);
	}
}

/**
 * 表格行
 */
function TableRow(){
	DomItem.call(this, []);
	this.item = document.createElement("tr");
}
TableRow.prototype = object(DomItem.prototype);
TableRow.prototype.constructor = TableRow;
TableRow.prototype.addCell = function(value){//表格行添加列
	var cell = new TableCell();
	cell.setData(value);
	if(this.item){
		this.item.appendChild(cell.item);
	}
}

/**
 * 表格单元
 */
function TableCell(){
	DomItem.call(this, []);
	this.item = document.createElement("td");
}
TableCell.prototype = object(DomItem.prototype);
TableCell.prototype.constructor = TableCell;
TableCell.prototype.setData = function(value){//设置数据
	if(this.item){
		this.item.innerText = value;
	}
}

/**
 * 可编辑表格行
 */
function EditableTableRow(){
	DomItem.call(this, []);
	this.item = document.createElement("tr");
}
EditableTableRow.prototype = object(DomItem.prototype);
EditableTableRow.prototype.constructor = EditableTableRow;
EditableTableRow.prototype.addCell = function(value){//可编辑表格行添加列
	var cell = new EditableTableCell();
	cell.setData(value);
	if(this.item){
		this.item.appendChild(cell.item);
	}
}

/**
 * 鼠标移过可编辑表格单元
 */
function onEditableTableCellMouseOver(evt){
	var item = evt.target;
	if(item){
		item.style.backgroundColor = '#D1E9E9';
	}
}

/**
 * 鼠标移出可编辑表格单元
 */
function onEditableTableCellMouseOut(evt){
	var item = evt.target;
	if(item){
		item.style.backgroundColor = 'white';
	}
}

/**
 * 可编辑表格单元
 */
function EditableTableCell(){
	TableCell.call(this, []);
	this.item = document.createElement("td");
	addEvent(this.item, "mouseover", onEditableTableCellMouseOver);
	addEvent(this.item, "mouseout", onEditableTableCellMouseOut);
}
EditableTableCell.prototype = object(TableCell.prototype);
EditableTableCell.prototype.constructor = EditableTableCell;

/**
 * 表格模型
 * modelData为id和name的数组
 */
function TableModel(modelData){
	TableRow.call(this, []);
	this.modelData = modelData;
	if(modelData){
		for(var i = 0;i < modelData.length;i++){
			this.addCell(modelData[i].name);
		}
	}
}
TableModel.prototype = object(TableRow.prototype);
TableModel.prototype.constructor = TableModel;

/**
 * 表格
 */
function Table(model){
	this.item = document.createElement("table");
	this.model = model;
	this.setData = setTableData;
	this.load = loadTableData;
	this.item.appendChild(model.item);
	document.body.appendChild(this.item);
}

/**
 * 表格数据加载
 */
function loadTableData(src){
	var item = this;
	var xmlHttp = createXMLHttpRequest();
	xmlHttp.open("get", src, true);
	xmlHttp.onreadystatechange = function(){
		if(xmlHttp.readyState == 4){
			if(xmlHttp.status == 200){
				//字符串转json
				item.setData(eval('('+xmlHttp.responseText+')'));
			}
		}
	};
	xmlHttp.send(null);
}

/**
 * 设置表格数据
 */
function setTableData(data){
	var modelData = this.model.modelData;
	if(data && modelData){
		for(var i = 0;i < data.length;i++){
			//一行的数据
			var rowData = data[i];
			var row = new EditableTableRow();
			for(var j = 0;j < modelData.length;j++){
				var id = modelData[j].id;
				row.addCell(rowData[id]);
			}
			this.item.appendChild(row.item);
		}
	}
}