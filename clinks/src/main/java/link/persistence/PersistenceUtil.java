package link.persistence;

import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.ejb.Ejb3Configuration;

import link.entity.Co;
import link.entity.Link;

/**
 * Classe utilizaria para obter o serviço de persistência
 * 
 * @author Luiz Alberto
 *
 */
public final class PersistenceUtil {

	private static EntityManagerFactory entityFactory;
	
	
	/**
	 * Contrutor default
	 */
	private PersistenceUtil() {
	}
	
	/**
	 * Cria um Gerenciador de Entidades JPA
	 * 
	 * @return Gerenciador de Entidades JPA
	 */
	public static EntityManager createEntityManager() {
		try {
			entityFactory = entityFactory == null ? 
							createEntityManagerFactory() : 
							entityFactory;
 
			return entityFactory.createEntityManager();
			
		} catch (Exception e) {
			throw new PersistenceException(e);
		}
	}

	private static EntityManagerFactory createEntityManagerFactory() {
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
        
        Ejb3Configuration cfg = new Ejb3Configuration();
        cfg.addProperties(properties);
        cfg.addAnnotatedClass(Link.class);
        cfg.addAnnotatedClass(Co.class);

        return cfg.buildEntityManagerFactory();
	}
	
	/**
	 * Cria um Gerenciador de Entidades JPA baseado no nome da PersisnceUnit
	 * 
	 * @param persistenceUnit	Nome da PersisnceUnit
	 * @return					Gerenciador de Entidades JPA					
	 */
	public static EntityManager createEntityManager(String persistenceUnit) {
		try {
			entityFactory = entityFactory == null ? 
							Persistence.createEntityManagerFactory(persistenceUnit) : 
							entityFactory;
 
			return entityFactory.createEntityManager();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistenceException(e);
		}
	}

	
}


















