<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.sonujha.hibernate.entity.Files" %> 
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>View All Files</title>
	</head>
<body>
	<%! String form; int fileId; %>
	
	
	
	<h1>Files are available:</h1>
	<table border="2">
		<tr>
		
			<th>Preview</th>
			<th>Information Available</th>
			<th>Update</th>
			<th>Action</th>
			
			
			
			<%
				@SuppressWarnings("unchecked")
				List<Files> files = (List<Files>) request.getAttribute("files");
				String path = (String) request.getAttribute("path");
				for(Files file : files) {
						out.println("<tr><td><img src="+path+file.getFileName()+" width='300px' height='300px'>");
						out.println("<td><ul>"+
									"<li>File Id:"+ file.getId() +"</li>"+
									"<li>File Name:"+ file.getFileName() +"</li>"+
									"<li>File Label:"+ file.getLabel()+"</li>" +
									"<li>File Caption:"+ file.getCaption()+"</li>"+
									"</ul></td>");
						
						
						fileId = file.getId();
						
						
						form = "<form action='ImageUpload' method='post' />"+
								"Label: <input type='text' name='label' /><br /> <br />" +
								"Caption: <input type='text' name='caption'> <br /> <br />"+
								"<input type='hidden' name='fileId' value='"+fileId+"'/>"+
								"<input type='hidden' name='action' value='updateInformation' />"+
								"<input type='submit' value='Update' />"+
								"</form>";
						
						out.println("<td>"+form+"</td>");
						out.println("<td><ul><li><a href='"+ request.getContextPath() +"/ImageUpload?action=viewFile&fileId="+fileId+"'>View</a></li>");
						out.println("<li><a href='"+ request.getContextPath() +"/ImageUpload?action=deleteFile&fileId="+fileId+"' onClick=\"if(!confirm('Are you sure want to delete this file? ')) return false\">Delete</a></li></ul></td></tr>");
				}
			%>
		
		</tr>
	
	</table>

</body>
</html>