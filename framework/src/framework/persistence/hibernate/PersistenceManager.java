package framework.persistence.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Interceptor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import framework.exceptions.DAOException;
import framework.util.ServiceLocator;


/**
 * 
 * Classe utilit·ria gerenciadora de persistencia
 * de POJOS para o Hibernate
 *  
 * @author	Luiz Alberto
 */
public class PersistenceManager {      
	private static final Log log = LogFactory.getLog(PersistenceManager.class);    
    private static SessionFactory sessionFactory;
    private static final ThreadLocal transaction = new ThreadLocal();
    private static final ThreadLocal session = new ThreadLocal();
    private static final ThreadLocal threadInterceptor = new ThreadLocal();
    private static Configuration configuration;
    
   // Create the initial SessionFactory from the default configuration files
    static {
        try {
            configuration = new Configuration();
            configuration.configure(ServiceLocator.instance().getProperty("hibernate.cfg"));
            sessionFactory = configuration.buildSessionFactory();
            // We could also let Hibernate bind it to JNDI:
            // configuration.configure().buildSessionFactory()
        } catch (Throwable ex) {
            // We have to catch Throwable, otherwise we will miss
            // NoClassDefFoundError and other subclasses of Error
            log.error("Building SessionFactory failed.", ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
     /**
     * Inicia uma nova transa√ß√£o.
     * @throws DAOException	em caso de erro
     */
    public static void beginTransaction() throws DAOException {
        Transaction tx = (Transaction) transaction.get();
        try {
            if (tx == null) {
                log.debug("Starting new database transaction in this thread.");
                tx = getSession().beginTransaction();
                transaction.set(tx);
            }
        } catch (HibernateException ex) {
            throw new DAOException(ex);
        }
    }   

    /**
     * Commit the database transaction.
     * @throws DAOException	em caso de erro
     */
    public static void commit() throws DAOException {
        Transaction tx = (Transaction) transaction.get();
        try {
            if ( tx != null && !tx.wasCommitted()
            && !tx.wasRolledBack() ) {
                log.debug("Committing database transaction of this thread.");
                tx.commit();
            }
            transaction.set(null);
        } catch (HibernateException ex) {
            rollback();
            throw new DAOException(ex);
        }
    }    

    /**
     * Efetua o commit numa transa√ß√£o.
     * @throws DAOException	em caso de erro
     */
    public static void rollback()
    throws DAOException {
        Transaction tx = (Transaction) transaction.get();
        try {
            transaction.set(null);
            if ( tx != null && !tx.wasCommitted() && !tx.wasRolledBack() ) {
                log.debug("Tyring to rollback database transaction of this thread.");
                tx.rollback();
            }
        } catch (HibernateException ex) {
            throw new DAOException(ex);
        } finally {
            closeSession();
        }
    }
    
    /**
     * Retorna a sess√£o local.
     * <p/>
     * Se a sess√£o n√£o estiver aberta, um nova sess√£o √© criada..
     *
     * @return Session do hibernate
     * @throws DAOException	em caso de erro
     */
    public static Session getSession() throws DAOException {
        Session s = (Session) session.get();
        try {
            if (s == null) {
                log.debug("Opening new Session for this thread.");
                if (getInterceptor() != null) {
                    log.debug("Using interceptor: " + getInterceptor().getClass());
                    s = getSessionFactory().openSession(getInterceptor());
                } else {
                    s = getSessionFactory().openSession();
                }
                session.set(s);
            }
            else if (!s.isOpen()) {
                if (getInterceptor() != null) {
                    s = getSessionFactory().openSession(getInterceptor());
                } else {
                    s = getSessionFactory().openSession();
                }
                session.set(s);
			}
        } catch (HibernateException ex) {
            throw new DAOException(ex);
        }
        return s;
    }    

    /**
     * Retorna o SessionFactory.
     *
     * @return SessionFactory
     */
    private static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * Retorna um hibernate Interceptor 
     * @return um hibernate Interceptor
     */
    private static Interceptor getInterceptor() {
        Interceptor interceptor =
                (Interceptor) threadInterceptor.get();
        return interceptor;
    }   
    
    
    /**
     * Finaliza a sess√£o local
     * @throws DAOException	em caso de erro
     */
    public static void closeSession() throws DAOException {
        try {
            Session s = (Session) session.get();
            session.set(null);
            if (s != null && s.isOpen()) {
                log.debug("Closing Session of this thread.");
                s.close();
            }
        } catch (HibernateException ex) {
            throw new DAOException(ex);
        }
    }
    
    
    
    /**
     * Salva um objeto utilizando os mÈtodos do hibernate
     * @param objeto
     * @throws DAOException	em caso de erro
     */
    public static void save(Object objeto) throws DAOException {
        try {
            getSession().save(objeto);
            getSession().flush();
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    /**
     * Salva ou atualiza um objeto utilizando os mÈtodos do hibernate
     * @param objeto 		objeto a ser atualizado
     * @throws DAOException	em caso de erro
     */
    public static void saveOrUpdate(Object objeto) throws DAOException {
        try {
            getSession().saveOrUpdate(objeto);
            getSession().flush();
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    
    /**
     * Salva um objeto utilizando os mÈtodos do hibernate
     * @param objeto objeto a ser atualizado
     * @throws DAOException em caso de erro
     */
    public static void update(Object objeto) throws DAOException {
        try {
            getSession().update(objeto);
            getSession().flush();
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }


    /**
     * Remove um objeto utilizando os mÈtodos do hibernate
     * 
     * @param objeto objeto a ser atualizado
     * @throws DAOException em caso de erro
     */
    public static void delete(Object objeto) throws DAOException {
        try {
            getSession().delete(objeto);
            getSession().flush();
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    /**
     * Retorna uma transa√ß√£o
     * @return uma transaÁ„o
     */
    public static Transaction getTransaction(){
       return (Transaction)transaction.get();
    }
    

}




