package com.sonujha.hibernate;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.fileupload.RequestContext;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.FileItem;

import com.sonujha.hibernate.DAO.FilesDAO;
import com.sonujha.hibernate.entity.Files;

/**
 * Servlet implementation class ImageUpload
 */
@MultipartConfig
public class ImageUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public String path = "c:/images/";
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Collection<Part> parts = request.getParts();
			for(Part part : parts) {
				System.out.println(part.getSubmittedFileName());
				new FilesDAO().addFiles(new Files(part.getSubmittedFileName()));
				part.write(path+part.getSubmittedFileName());
			}
			response.getWriter().print("The file has been uploaded successfully.");
		}
		catch (Exception e) {
			e.printStackTrace();
			response.sendError(response.SC_INTERNAL_SERVER_ERROR, "Upload failed.");
		}	
	}
}
