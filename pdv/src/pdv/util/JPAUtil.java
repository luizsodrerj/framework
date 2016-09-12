package pdv.util;

import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.hibernate.ejb.Ejb3Configuration;

import pdv.entity.FormaPagamento;
import pdv.entity.Item;
import pdv.entity.ItemPedido;
import pdv.entity.PedidoVenda;
import pdv.entity.StatusPedido;

public class JPAUtil {

	private static EntityManagerFactory emf;
	private static EntityManager em;
	
	
	public static EntityManager createEntityManager() {
		if (em == null || !em.isOpen()) {
			em = emf.createEntityManager();
		}
		return em; 
	}
	
	@SuppressWarnings("deprecation")
	public static void initPersistence() {
		Properties props = new Properties();
		
		props.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/test"); 
		props.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
		props.put("hibernate.connection.username", "root");
		props.put("hibernate.connection.password", "");
		props.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		props.put("hibernate.hbm2ddl.auto", "update");
		props.put("hibernate.show_sql", "true");
		props.put("hibernate.format_sql", "true");

		Ejb3Configuration cfg = new Ejb3Configuration();
		cfg.addProperties(props);
		
		cfg.addAnnotatedClass(FormaPagamento.class);
		cfg.addAnnotatedClass(Item.class);
		cfg.addAnnotatedClass(ItemPedido.class);
		cfg.addAnnotatedClass(PedidoVenda.class);
		cfg.addAnnotatedClass(StatusPedido.class);
		
		emf = cfg.createEntityManagerFactory();
	}

	public static EntityManager getEntityManager() {
		return em;
	}
	
	public static EntityManagerFactory getEntityManagerFactory() {
		return emf;
	}

	public static void destroyPersistence() {
		if (em != null && em.isOpen()) {
			em.close();
		}
		if (emf != null) {
			emf.close();	
		}
	}
}





