function idxof(arr, value){
	var idx = -1;
	if(arr){
		for(var i = 0;i < arr.length;i++){
			if(arr[i] == value){
				idx = i;
				break;
			}
		}
	}
	return idx;
}

//注册过事件监听的全局索引
globalEventsObservers = {};
//canvas引用
cvs = undefined;
//canvas的绘图环境
ctx = undefined;
//canvas位置
cvsPos = undefined;

/**
 * 封装的鼠标事件
 */
function CanvasMouseEvent(event, type, currentTarget, canvasX, canvasY){
	//原始事件数据
	this.rawEvent = event;
	//事件类型
	this.type = type;
	//触发事件的对象
	this.currentTarget = currentTarget;
	//事件相对canvas的全局坐标
	this.canvasX = canvasX;
	this.canvasY = canvasY;
}

function addGlobalEventObserver(type, obj){
	eventObservers = globalEventsObservers[type];
	if(eventObservers == undefined){
		eventObservers = [obj];
		globalEventsObservers[type] = eventObservers;
	}else if(idxof(eventObservers, obj) < 0){
		eventObservers.push(obj);
	}
}

function removeGlobalEventObserver(type, obj){
	eventObservers = globalEventsObservers[type];
	if(eventObservers != undefined){
		var idx = idxof(eventObservers, obj);
		if(idx >= 0){
			eventObservers.splice(idx, 1);
		}
	}
}

function dispatchGlobalEvent(event){
	var type = event.type;
	//计算事件相对canvas的全局坐标
	var canvasX = event.pageX - cvsPos.x;
	var canvasY = event.pageY - cvsPos.y;
	dispatchGlobalEventHelper(event, type, canvasX, canvasY);
	//mouseover和mouseout事件需要借助于mousemove实现
	if(type == "mousemove"){
		dispatchGlobalEventHelper(event, "mouseover", canvasX, canvasY);
		dispatchGlobalEventHelper(event, "mouseout", canvasX, canvasY);
	}
}

function dispatchGlobalEventHelper(event, type, canvasX, canvasY){
	eventObservers = globalEventsObservers[type];
	if(eventObservers != undefined){
		var gameObj;
		for(var i = 0;i < eventObservers.length;i++){
			gameObj = eventObservers[i];
			if(type == "mouseover"){//处理mouseover事件
				if(!gameObj.mouseOvered && gameObj.inBoundary(canvasX, canvasY)){
					gameObj.dispatchEvent(new CanvasMouseEvent(event, type, gameObj, canvasX, canvasY));
				}
			}else if(type == "mouseout"){//处理mouseout事件
				if(gameObj.mouseOvered && !gameObj.inBoundary(canvasX, canvasY)){
					gameObj.dispatchEvent(new CanvasMouseEvent(event, type, gameObj, canvasX, canvasY));
				}
			}else if(gameObj.inBoundary(canvasX, canvasY)){//判断事件是否发生在游戏对象内部
				gameObj.dispatchEvent(new CanvasMouseEvent(event, type, gameObj, canvasX, canvasY));
			}
		}
	}
}

function init(canvas){
//	canvas.onmouseover = dispatchGlobalEvent;
//	canvas.onmouseout = dispatchGlobalEvent;
	canvas.onclick = dispatchGlobalEvent;
	canvas.onmousedown = dispatchGlobalEvent;
	canvas.onmouseup = dispatchGlobalEvent;
	canvas.onmousemove = dispatchGlobalEvent;
	cvs = canvas;
	ctx = canvas.getContext("2d");
	cvsPos = {x:cvs.offsetLeft, y:cvs.offsetTop};
}

function GameObject() {
	this.x = 0;
	this.y = 0;
	this.width = 0;
	this.height = 0;
	this.scaleX = 1;
	this.scaleY = 1;
	this.hAlign = "left";
	this.vAlign = "top";
	this.mouseOvered = false;
	this.eventsObservers = {};
}

function addEventListener(type, callback) {
	eventObservers = this.eventsObservers[type];
	if(eventObservers == undefined){
		eventObservers = [callback];
		this.eventsObservers[type] = eventObservers;
		addGlobalEventObserver(type, this);
	}else if(idxof(eventObservers, callback) < 0){
		eventObservers.push(callback);
		addGlobalEventObserver(type, this);
	}
}

function removeEventListener(type, callback){
	eventObservers = this.eventsObservers[type];
	if(eventObservers != undefined){
		var idx = idxof(eventObservers, callback);
		if(idx >= 0){
			eventObservers.splice(idx, 1);
		}
		if(eventObservers.length == 0){
			removeGlobalEventObserver(type, this);
		}
	}
}

function dispatchEvent(event){
	type = event.type;
	if(type == "mouseover"){
		this.mouseOvered = true;
	}else if(type == "mouseout"){
		this.mouseOvered = false;
	}
	eventObservers = this.eventsObservers[type];
	if(eventObservers != undefined){
		for(var i = 0;i < eventObservers.length;i++){
			eventObservers[i](event);
		}
	}
}

function inBoundary(canvasX, canvasY){
	var l = this.left();
	var t = this.top();
	return l < canvasX && l+this.width*this.scaleX > canvasX && 
	t < canvasY && t+this.height*this.scaleY > canvasY;
}

/**
 * 获取游戏对象左边界相对canvas的值,注意返回的是缩放后的值
 * @returns {Number}
 */
function getLeft(){
	switch(this.hAlign){
		case "center":
			return this.x - this.width / 2*this.scaleX;
		case "right":
			return this.x - this.width*this.scaleX;
		default:
			return this.x;
	}
}

/**
 * 获取游戏对象上边界相对canvas的值,注意返回的是缩放后的值
 * @returns {Number}
 */
function getTop(){
	switch(this.vAlign){
		case "center":
			return this.y - this.height / 2*this.scaleY;
		case "bottom":
			return this.y - this.height*this.scaleY;
		default:
			return this.y;
	}
}

/**
 * 获取游戏对象左边界相对自身x属性的值,返回未经缩放的值
 */
function getLocalLeft(){
	switch(this.hAlign){
		case "center":
			return -this.width / 2;
		case "right":
			return -this.width;
		default:
			return 0;
	}
}

/**
 * 获取游戏对象上边界相对自身y属性的值,返回未经缩放的值
 */
function getLocalTop(){
	switch(this.vAlign){
		case "center":
			return -this.height / 2;
		case "bottom":
			return -this.height;
		default:
			return 0;
	}
}

function render(){
	ctx.save();
	ctx.translate(this.x, this.y);
	if(this.scaleX != 1 || this.scaleY != 1){
		ctx.scale(this.scaleX, this.scaleY);
	}
	if(this.onRender){
		this.onRender();
	}
	ctx.restore();
}

GameObject.prototype.addEventListener = addEventListener;
GameObject.prototype.removeEventListener = removeEventListener;
GameObject.prototype.dispatchEvent = dispatchEvent;
GameObject.prototype.inBoundary = inBoundary;
GameObject.prototype.render = render;
GameObject.prototype.left = getLeft;
GameObject.prototype.top = getTop;
GameObject.prototype.localLeft = getLocalLeft;
GameObject.prototype.localTop = getLocalTop;

function onRenderImage(){
	ctx.drawImage(this.img, this.localLeft(), this.localTop(), this.width, this.height);
}

function loadImageComplete(){
	var from = this.from;
	if(from.width == 0){
		from.width = this.width;
	}
	if(from.height == 0){
		from.height = this.height;
	}
	from.render();
}

function loadImage(){
	this.img = new Image();
	this.img.from = this;
	this.img.addEventListener("load", loadImageComplete, false);
	this.img.src = this.src;
}

function GameImage(src){
	GameObject.apply(this, []]);
	this.src = src;
}

GameImage.prototype = new GameObject();
GameImage.prototype.constructor = GameImage;
GameImage.prototype.load = loadImage;
GameImage.prototype.onRender = onRenderImage;
