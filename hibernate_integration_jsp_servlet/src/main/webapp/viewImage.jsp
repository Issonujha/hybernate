<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.sonujha.hibernate.entity.Files" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Image</title>
</head>
<body>
<%! Files file; String path; String filePath; %>
	<%
		file = (Files) request.getAttribute("file");
		path = (String) request.getAttribute("path");
		filePath = path+file.getFileName();
	%>
	<%	out.println("<img alt="+file.getCaption()+" src="+filePath+" width=300px/>"); %>
	<img alt="<%= file.getCaption() %>" src="<%= filePath %>">
</body>
</html>