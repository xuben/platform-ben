<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>list tasks</title>
</head>
<body>
<table>
<tr>
<td>时间</td>
<td>任务名</td>
<td>任务描述</td>
<td>类型</td>
<td>状态</td>
</tr>
<c:forEach items="${tasks }" var="task">
<tr>
<td>
${task.date }
</td>
<td>
${task.name }
</td>
<td>
${task.info }
</td>
<td>
${task.type }
</td>
<td>
${task.state }
</td>
</tr>
</c:forEach>
</table>
</body>
</html>