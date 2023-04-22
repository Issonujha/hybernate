package com.sonujha.hibernate;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
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
    
    /**
     * When User submit the form this will handle the form by getting the parameter and call on required methods.
     * @author SonuKrJha
     * 
     */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		switch (action) {
			case "filesUpload":
				filesUpload(request, response);
				break;
			case "updateInformation":
				updateInformation(request, response);
				break;
			default:
				request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}
	
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		switch (action) {
			case "viewFiles":
				viewFiles(request, response);
				break;
			case "viewFile":
				viewFile(request, response);
				break;
			case "deleteFile":
				deleteFile(request, response);
				break;
			default:
				request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}
	
	
	
	
	private void deleteFile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int fileId = Integer.parseInt(request.getParameter("fileId"));
		Files file = new FilesDAO().viewFile(fileId);
		File fileOnDisk = new File(path+file.getFileName());
		new FilesDAO().deleteFile(fileId);
		if(fileOnDisk.delete()) {
			System.out.println("Deleted Sucessfully.");
		}
		else {
			System.out.println("Some Error Occured While Deleting.");
		}
 		viewFiles(request, response);
	}



	private void viewFile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int fileId = Integer.parseInt(request.getParameter("fileId"));
		Files file = new FilesDAO().viewFile(fileId);
		System.out.println(file);
		request.setAttribute("file", file);
		request.setAttribute("path", path);
		request.getRequestDispatcher("viewImage.jsp").forward(request, response);
	}



	/**
	 * Updating the information
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void updateInformation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int fileId = Integer.parseInt((String) request.getParameter("fileId"));
		String label = (String) request.getParameter("label");
		String caption = (String) request.getParameter("caption");
		Files file = new Files(fileId, label, caption);
		new FilesDAO().updateInformation(file);
		viewFiles(request, response);
	}
	
	
	
	
	/**
	 * Forwarding to viewFiles.jsp by fetching files from DAO and setting the attributes.
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void viewFiles(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		/**
		 * Files getting from FilesDAO
		 */
		List<Files> files = new FilesDAO().getFiles();
		
		
		
		
		
		/**
		 * Setting Attributes files and path
		 */
		request.setAttribute("files", files);
		request.setAttribute("path", path);
		
		
		
		
		/**
		 * forward the request to viewFiles.jsp using getRequestDispatcher method and then forward
		 */
		request.getRequestDispatcher("viewFiles.jsp").forward(request, response);
	}


	
	
	
	
	
	
	
	
	/**
	 * This is used to uploading the files thereafter viewing the files using DAO files using collection Parts
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@SuppressWarnings("static-access")
	private void filesUpload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Collection<Part> parts = request.getParts();
			for(Part part : parts) {
				System.out.println(part.getSubmittedFileName());
				File file = new File(path+part.getSubmittedFileName());
				System.out.println(file.exists());
				if (!file.exists()) {
					new FilesDAO().addFiles(new Files(part.getSubmittedFileName()));
					part.write(file+"");
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			response.sendError(response.SC_INTERNAL_SERVER_ERROR, "Upload failed.");
		}
		viewFiles(request, response);
	}
	
}
