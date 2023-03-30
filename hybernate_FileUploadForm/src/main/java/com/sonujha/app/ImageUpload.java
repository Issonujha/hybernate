package com.sonujha.app;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;

import java.util.Collection;


@MultipartConfig
public class ImageUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			Collection<Part> parts = request.getParts();
		    for (Part part : parts) {
		    	System.out.println(part.getSubmittedFileName());
		    	part.write("c:/images/"+part.getSubmittedFileName());
		    }
		    
		    response.getWriter().print("The file has been uploaded successfully.");
		} catch (Exception e) {
		    response.sendError(response.SC_INTERNAL_SERVER_ERROR, "Upload failed.");
		}
		
	}

}


