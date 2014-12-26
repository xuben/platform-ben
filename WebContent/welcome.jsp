<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:useBean id="user" class="ben.bean.User" scope="session"></jsp:useBean>
welcome, <jsp:getProperty property="name" name="user"/>!
<br/>
<a href="compressed">get compressed data</a>
<br/>
<a href="compressed?disableGzip=true">get uncompressed data</a>
</body>
</html>