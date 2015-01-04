<%@page import="ben.Constants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>sign up success</title>
</head>
<body>
sign up success, redirect in 3 seconds!
<% response.addHeader("Refresh", "3;url="+Constants.URL_ROOT_PAGE); %>
</body>
</html>