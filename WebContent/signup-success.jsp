<%@page import="ben.Constants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="ben.css"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>sign up success</title>
<script>
function timeHandler(time)
{
	time--;
	if(time >= 0)
	{
		var item = document.getElementById("cd");
		item.innerText = time;
		if(time > 0)
		{
			setTimeout("timeHandler("+time+");", 1000);
		}
	}
}
</script>
</head>
<body>
<div class="center-text-info">
sign up success, redirect in <span id="cd">3</span> seconds!
</div>
<% response.addHeader("Refresh", "4;url="+Constants.URL_ROOT_PAGE); %>
<script>
setTimeout("timeHandler(3);", 1000);
</script>
<jsp:include page="<%= Constants.URL_FOOT_PAGE %>"></jsp:include>
</body>
</html>