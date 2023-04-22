package com.sonujha.hibernate.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.sonujha.hibernate.entity.Files;


/**
 * DAO FILES Create Factory and add and getFiles
 * @author SonuKrJha
 *
 */
public class FilesDAO {
	
	SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
												.addAnnotatedClass(Files.class)
												.buildSessionFactory();
	
	/**
	 * Adding the file to Database
	 * @param files
	 */
	public void addFiles(Files files) {
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			session.save(files);
			session.getTransaction().commit();
		} finally {
			System.out.println("File Submitted Sucessfully");
			System.out.println("Your Files: "+ files);
			session.close();
			factory.close();
		}
	}
	
	
	
	
	/**
	 * Fetching the files
	 * @return
	 */
	public List<Files> getFiles() {
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<Files> files = session.createQuery("from files").getResultList();
			System.out.println(files);
			return files;
		}
		finally {
			session.close();
		}
	}




	public void updateInformation(Files file) {
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			Files getFile = session.get(Files.class, file.getId());
			getFile.setLabel(file.getLabel());
			getFile.setCaption(file.getCaption());
			session.getTransaction().commit();
		} finally {
			session.close();
		}
	}




	public Files viewFile(int fileId) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		Files file = session.get(Files.class, fileId);
		session.getTransaction().commit();
		return file;
	}




	public void deleteFile(int fileId) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		Files file = session.get(Files.class, fileId);
		session.delete(file);
		session.getTransaction().commit();
	}
	

}
