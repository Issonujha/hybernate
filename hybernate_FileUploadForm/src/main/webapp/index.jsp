<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Image Upload Form</title>
</head>
<body>

<form action="ImageUpload" method="post" enctype="multipart/form-data">
	Select Images: <input type="file" name="files" multiple/> 
	<input type="submit" value="upload" />
</form>

</body>
</html>