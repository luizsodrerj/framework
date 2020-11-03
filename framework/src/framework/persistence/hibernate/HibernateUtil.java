package framework.persistence.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * <p>Description: Classe utilitaria para o hibernate</p>
 * @author  Luiz Alberto
 * @date    01/10/2004
 */
public class HibernateUtil {
	private static final SessionFactory sessionFactory;

	
	/**
	 * Construtor privado para evitar instancias
	 * desnecessárias da classe 
	 */
	private HibernateUtil() {
	}
	
	static {
		try {
			sessionFactory = new Configuration().configure().buildSessionFactory();
	    }
	    catch (HibernateException e) {
	    	throw new RuntimeException("Exception building SessionFactory: "+e.getMessage(), e);
	    }
	}

    /**
     * Retorna a factory de Hibernate Sessions
     * @return Factory de Hibernate Sessions
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
  
}










