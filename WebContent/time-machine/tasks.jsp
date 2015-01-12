<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>list tasks</title>
<script type="text/javascript" src="/PlatformBen/js/ben-ui.js"></script>
<script>
function onAddTask()
{
	var nameInput = document.getElementById("newTaskName");
	var subNameInput = document.getElementById("newTaskSubName");
	if(nameInput && subNameInput){
		var name = nameInput.value;
		var subName = subNameInput.value;
		var src="task/add?name="+name+"&subName="+subName;
		var xmlHttp = createXMLHttpRequest();
		xmlHttp.open("get", src, false);
		xmlHttp.onreadystatechange = function(){
			if(xmlHttp.readyState == 4){
				if(xmlHttp.status == 200){
					//status不应该作为应用层的状态判断,可以像struts那样加个success表示成功
					if(xmlHttp.responseText && xmlHttp.responstText != ""){
						if(table){
							table.addRowData(eval('('+xmlHttp.responseText+')'));
						}
					}else{
						alert("添加失败");
					}
				}
			}
		};
		xmlHttp.send(null);
	}
}

function onEmptyTasks()
{
	var src="tasks/empty";
	var xmlHttp = createXMLHttpRequest();
	xmlHttp.open("post", src, false);
	xmlHttp.onreadystatechange = function(){
		if(xmlHttp.readyState == 4){
			if(xmlHttp.status == 200){
				if(table){
					table.load("tasks");
				}
			}
		}
	};
	xmlHttp.send(null);
}
</script>
</head>
<body>
任务名:<input type="text" id="newTaskName" name="name"/>
子任务名:<input type="text" id="newTaskSubName"name="subName"/>
<input type="button" onclick="onAddTask();" value="新增"/>
<input type="button" onclick="onEmptyTasks();" value="清空"/>
<script>
var model = new TableModel(
		[{id:"date", name:"时间"},{id:"name", name:"任务名"},{id:"subName", name:"子任务名"},{id:"info", name:"任务描述"}
		,{id:"type", name:"类型"},{id:"state", name:"状态"}]);
table = new Table(model);
table.load("tasks");
</script>
</body>
</html>