<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Image Upload Form</title>
</head>
<body>
<form action="ImageUpload?action=filesUpload" method="post" enctype="multipart/form-data">
	Select Images: <input type="file" name="files" multiple/> 
	<input type="submit" value="upload" />
	<h1 id="text"></h1>
</form>
<a href="${pageContext.servletContext.contextPath}/ImageUpload?action=viewFiles"> View Files </a>

</body>
</html>