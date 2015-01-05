<%@page import="ben.Constants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="ben.css"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>sign up</title>
</head>
<body>
<div class="align-center">
<form action="signup" method="post">
username:
<br/>
<input type="text" name="uname" value="${username}"/>
<br/>
password:
<br/>
<input type="password" name="passwd"/>
<br/>
confirm password:
<br/>
<input type="password" name="passwd2"/>
<br/>
email address:
<br/>
<input type="text" name="email" value="${email }"/>
<br/><br/>
<input type="submit" value="sign up"/>
<input type="button" value="back" onclick="window.location.href='<%= Constants.URL_LOGIN_PAGE %>'"/>
</form>
<font color="red">${errorMsg }</font>
</div>
<jsp:include page="<%= Constants.URL_FOOT_PAGE %>"></jsp:include>
</body>
</html>