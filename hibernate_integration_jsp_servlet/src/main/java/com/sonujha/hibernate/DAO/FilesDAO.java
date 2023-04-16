package com.sonujha.hibernate.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.sonujha.hibernate.entity.Files;

public class FilesDAO {
	
	SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
												.addAnnotatedClass(Files.class)
												.buildSessionFactory();
	
	
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
	

}
