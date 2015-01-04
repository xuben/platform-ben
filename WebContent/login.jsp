<!DOCTYPE html>
<%@page import="ben.Constants"%>
<html>
<head>
<link rel="stylesheet" type="text/css" href="ben.css"/>
<meta charset="UTF-8">
<title>login</title>
</head>
<body>
<div class="align-center">
<form action="login" method="post">
username:<input type="text" name="uname" value="${uname }"/>
<br/>
password:<input type="password" name="passwd"/>
<br/>
authcode:<img src="authcode"/>
<input type="text" name="authcode" style="width:80px;height:20px;"/>
<br/>
<input type="submit" value="login"/>
<input type="button" value="signup" onclick="window.location.href='<%= Constants.URL_SIGNUP_PAGE %>'"/>
</form>
<font color="red">${errorMsg }</font>
</div>
<jsp:include page="<%= Constants.URL_FOOT_PAGE %>"></jsp:include>
</body>
</html>