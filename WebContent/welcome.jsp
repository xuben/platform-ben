<%@page import="ben.Constants"%>
<%@page pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="ben.css"/>
<meta charset="UTF-8">
<title>welcome</title>
</head>
<body>
welcome, ${ user.name }!
<br/>
<a href="compressed">get compressed data</a>
<br/>
<a href="compressed?disableGzip=true">get uncompressed data</a>
<br/>
<a href="logout">退出</a>
<jsp:include page="<%= Constants.URL_FOOT_PAGE %>"></jsp:include>
</body>
</html>