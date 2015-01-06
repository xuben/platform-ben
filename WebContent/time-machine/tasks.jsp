<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>list tasks</title>
<script type="text/javascript" src="/PlatformBen/js/ben-ui.js"></script>
</head>
<body>
<script>
var model = new TableModel(
		[{id:"date", name:"时间"},{id:"name", name:"任务名"},{id:"info", name:"任务描述"}
		,{id:"type", name:"类型"},{id:"state", name:"状态"}]);
var table = new Table(model);
table.load("tasks");
</script>
</body>
</html>