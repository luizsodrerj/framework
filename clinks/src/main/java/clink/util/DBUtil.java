package clink.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.hibernate.ejb.Ejb3Configuration;

import link.entity.Co;
import link.entity.Link;
import link.persistence.PersistenceService;

public class DBUtil {


	public static void main(String[] args) {
		try {
			loadDB();
			
			System.out.println("Base de Dados criada com sucesso!");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void loadDB() throws Exception {
		EntityManagerFactory emf = initJPA();
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
			PersistenceService persis = new PersistenceService(em);
			List<Co>cos   = persis.findAll(Co.class); 
			List<Link>lks = persis.findAll(Link.class);

			System.out.println(lks.size());
			System.out.println(cos.size());
			
			
		} finally {
			em.close();
		}
	}
	
	static EntityManagerFactory initJPA() throws Exception {
		String dbEnvVar = System.getenv("CLINK_DB");
		
		String url = "jdbc:h2:" + dbEnvVar + ";AUTO_SERVER=TRUE;AUTO_SERVER_PORT=9092";
		
		Properties properties = new Properties();
        properties.put("javax.persistence.provider", "org.hibernate.ejb.HibernatePersistence");
        properties.put("javax.persistence.transactionType", "RESOURCE_LOCAL");
        properties.put("javax.persistence.validation.mode", "none");
        properties.put("hibernate.connection.driver_class", "org.h2.Driver");
        properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        properties.put("hibernate.connection.username", "sa");
        properties.put("hibernate.connection.password", "");
        properties.put("hibernate.connection.url", url);
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");

        //Class.forName("org.hibernate.ejb.HibernatePersistence");
        
        Ejb3Configuration cfg = new Ejb3Configuration();
        cfg.addProperties(properties);
        cfg.addAnnotatedClass(Link.class);
        cfg.addAnnotatedClass(Co.class);

        return cfg.buildEntityManagerFactory();
        
//        return Persistence.createEntityManagerFactory("PU",properties);	
	}

	
	
	static Connection getCon() throws Exception {
		String path = "/home/tqi_lsodre/Desktop/Sodre/MyWorkspace/framework-labweb/bijus/src/main/resources/dados/bijudb;shutdown=true";
		String url = "jdbc:hsqldb:file:" + path;
		String u = "sa";
		String p = "";
		
		Class.forName("org.hsqldb.jdbcDriver");
		
		return  DriverManager.getConnection(url, u, p);
	}
	
}





