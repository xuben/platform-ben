<%@page import="ben.Constants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>sign up</title>
</head>
<body>
<form action="signup" method="post">
username:<input type="text" name="uname" value="${username}"/>
<br/>
password:<input type="password" name="passwd"/>
<br/>
confirm password:<input type="password" name="passwd2"/>
<br/>
email address:<input type="text" name="email" value="${email }"/>
<br/>
<input type="submit" value="sign up"/>
<input type="button" value="back" onclick="window.location.href='<%= Constants.URL_LOGIN_PAGE %>'"/>
</form>
<font color="red">${errorMsg }</font>
</body>
</html>