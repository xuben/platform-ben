<!DOCTYPE html>
<%@page import="ben.ServletUtil"%>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
</head>
<body>
<form action="login" method="post">
username:<input type="text" name="uname" 
value="<%=ServletUtil.getSessionAttributeWidthDefault(session, "uname", "") %>"
/>
<br/>
password:<input type="password" name="passwd"/>
<br/>
authcode:<img src="authcode"/>
<input type="text" name="authcode" style="width:80px;height:20px;"/>
<br/>
<input type="submit"/>
</form>
<% 
int errorCode = ServletUtil.getSessionIntAttributeWidthDefault(session, "errorCode", 0);
if(errorCode > 0){
	String errorMsg = ServletUtil.getErrorMsg(errorCode);
	out.println("<font color=\"red\">"+errorMsg+"</font>");
}
%>
</body>
</html>