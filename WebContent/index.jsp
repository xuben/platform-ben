<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>index</title>
</head>
<body>
<%
//判断是否已登录
Boolean login = false;
if(session != null){
	login = (Boolean)session.getAttribute("login");
}
if(login != null && login == true){//已登录
	response.sendRedirect("welcome.html");
}else{//未登录
	response.sendRedirect("login.html");
}
%>
</body>
</html>