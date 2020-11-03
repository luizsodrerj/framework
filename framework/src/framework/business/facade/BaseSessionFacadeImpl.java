package framework.business.facade;

import java.io.Serializable;
import java.util.List;

import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import framework.exceptions.BusinessException;
import framework.persistence.DAOFactory;
import framework.persistence.GenericDAO;
import framework.persistence.TransactionDAO;
import framework.persistence.hibernate.PersistenceManager;
import framework.util.ServiceLocator;

/**
 * <p>Descrição:    Delegate base de negócios. </p>
 * <p>Data:			11/02/2006 </p>
 * 
 * @author Luiz Alberto
 */
public class BaseSessionFacadeImpl implements BaseSessionFacade {
	
    protected static final String	USER_TRANSACTION_JNDI_NAME	= "java:comp/UserTransaction";
    private   static final Log 		logger 						= LogFactory.getLog(BaseSessionFacadeImpl.class);
    protected UserTransaction 		transaction;

	/**
     * Retorna um objeto utilizando os métodos do hibernate a partir de uma chave Integer
     * 
     * @param 	type 	tipo do objeto persistente
     * @param 	id 		id do objeto persistente
     * @return 	um objeto persistente
     * @throws 	BusinessException Em caso de erro
     */
	public Object getObject(Class type, Serializable id) throws BusinessException {
		GenericDAO dao = null;
		
		try {
			DAOFactory factory	= DAOFactory.getDAOFactory(DAOFactory.HIBERNATE_FACTORY); 
			dao 				= (GenericDAO) factory.getDAO("genericDAO");
			
			return dao.get(type, id);
			
		} catch (Exception e) {
			logger.fatal(e.getMessage(), e);
			throw new BusinessException(e);
		} finally {
			close(dao);
		}
	}

	/**
     * Retorna um objeto utilizando os métodos do hibernate a partir de uma chave, 
     * deixando a sessão aberta para lazy objects
     *   
     * @param 	type 	tipo do objeto persistente
     * @param 	id 		id do objeto persistente
     * @return 	um objeto persistente
     * @throws 	BusinessException Em caso de erro
     */
	public Object getLazyObject(Class type, Serializable id) throws BusinessException {
		GenericDAO dao = null;
		
		try {
			DAOFactory factory	= DAOFactory.getDAOFactory(DAOFactory.HIBERNATE_FACTORY); 
			dao 				= (GenericDAO) factory.getDAO("genericDAO");
			
			return dao.get(type, id);
			
		} catch (Exception e) {
			logger.fatal(e.getMessage(), e);
			throw new BusinessException(e);
		}
	}

	public List findLazyAll(Class type) throws BusinessException {
		GenericDAO dao = null;
		
		try {
			DAOFactory factory	= DAOFactory.getDAOFactory(DAOFactory.HIBERNATE_FACTORY); 
			dao 				= (GenericDAO) factory.getDAO("genericDAO");

			return dao.findAll(type);
			
		} catch (Exception e) {
			logger.fatal(e.getMessage(), e);
			throw new BusinessException(e);
		} 
	}

	public List findAll(Class type) throws BusinessException {
		GenericDAO dao = null;
		
		try {
			DAOFactory factory	= DAOFactory.getDAOFactory(DAOFactory.HIBERNATE_FACTORY); 
			dao 				= (GenericDAO) factory.getDAO("genericDAO");
			
			return dao.findAll(type);
		} catch (Exception e) {
			logger.fatal(e.getMessage(), e);
			throw new BusinessException(e);
		} finally {
			close(dao);
		}
	}
	
	public void saveOrUpdateLazyObject(Object persistentObject) throws BusinessException {
		GenericDAO dao = null;
		
		try {
			DAOFactory factory	= DAOFactory.getDAOFactory(DAOFactory.HIBERNATE_FACTORY); 
			dao 				= (GenericDAO) factory.getDAO("genericDAO");

			dao.beginTransaction();
			dao.saveOrUpdate(persistentObject);
			dao.commit();
			
		} catch (Exception e) {
			logger.fatal(e.getMessage(), e);
			
			if (dao != null) {
				rollback(dao);
			}
			throw new BusinessException(e);
		} 
	}

	public void saveOrUpdateObject(Object persistentObject) throws BusinessException {
		beginTransaction();
		GenericDAO dao = null;
		
		try {
			DAOFactory factory	= DAOFactory.getDAOFactory(DAOFactory.HIBERNATE_FACTORY); 
			dao			 		= (GenericDAO) factory.getDAO("genericDAO");
						
			dao.saveOrUpdate(persistentObject);
			commitTransaction();
			
		} catch (Exception e) {
			logger.fatal(e.getMessage(), e);
			rollbackTransaction();
			throw new BusinessException(e);
		} finally {
			close(dao);
		}
	}

	public void saveLazyObject(Object persistentObject) throws BusinessException {
		GenericDAO dao = null;

		try {
			DAOFactory factory	= DAOFactory.getDAOFactory(DAOFactory.HIBERNATE_FACTORY); 
			dao 				= (GenericDAO) factory.getDAO("genericDAO");
			
			dao.beginTransaction();
			dao.save(persistentObject);
			dao.commit();
			
		} catch (Exception e) {
			logger.fatal(e.getMessage(), e);
			
			if (dao != null) {
				rollback(dao);
			}
			throw new BusinessException(e);
		} 
	}

	public void saveObject(Object persistentObject) throws BusinessException {
		beginTransaction();
		GenericDAO dao = null;

		try {
			DAOFactory factory	= DAOFactory.getDAOFactory(DAOFactory.HIBERNATE_FACTORY); 
			dao			 		= (GenericDAO) factory.getDAO("genericDAO");

			dao.save(persistentObject);
			commitTransaction();
			
		} catch (Exception e) {
			logger.fatal(e.getMessage(), e);
			rollbackTransaction();
			throw new BusinessException(e);
		} finally {
			close(dao);
		}
	}

	public void updateLazyObject(Object persistentObject) throws BusinessException {
		GenericDAO dao = null;
		
		try {
			DAOFactory factory	= DAOFactory.getDAOFactory(DAOFactory.HIBERNATE_FACTORY); 
			dao 				= (GenericDAO) factory.getDAO("genericDAO");

			dao.beginTransaction();
			dao.update(persistentObject);
			dao.commit();
			
		} catch (Exception e) {
			logger.fatal(e.getMessage(), e);
			
			if (dao != null) {
				rollback(dao);
			}
			throw new BusinessException(e);
		} 
	}
	
    /**
     * Inicializa a instância de UserTransaction.
     * @throws BusinessException Em caso de erro
     */
    protected void getUserTransaction() {
        ServiceLocator locatorInstance = ServiceLocator.instance();
        transaction = (UserTransaction)locatorInstance.getService(USER_TRANSACTION_JNDI_NAME);
        locatorInstance.removeService(USER_TRANSACTION_JNDI_NAME);
    }


    /**
     * Inicia uma transação.
     * @throws framework.exceptions.BusinessException Em caso de erro ao iniciar a transação.
     */
    protected void beginTransaction() throws BusinessException {
        try {
            if (transaction == null) {
                getUserTransaction();
            }
            transaction.begin();
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }
    
    /**
     * Confirma a transação.
     * @throws framework.exceptions.BusinessException Em caso de erro ao confirmar a transação.
     */
    protected void commitTransaction() throws BusinessException {
        if (transaction != null)  {
            try {
                transaction.commit();
            } catch (Exception e) {
                throw new BusinessException(e);
            }
        }
    }

    /**
     * Desfaz a transação.
     * @throws framework.exceptions.BusinessException Em caso de erro ao desfazer a transação.
     */
    protected void rollbackTransaction() throws BusinessException {
        if (transaction != null)  {
            try {
                transaction.rollback();
            } catch (SystemException e) {
                throw new BusinessException(e);
            }
        }
    }

	/**
	 * Desfaz a transação
	 * @throws BusinessException em caso de erro
	 */
	protected void rollbackHibernateTransaction() throws BusinessException {
		try {
			PersistenceManager.rollback();
		}  catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	/**
	 * Fecha a sessão do Hibernate
	 * 
	 * @throws BusinessException em caso de erro
	 */
	protected void closeSession() throws BusinessException {
	 	try {
	 		PersistenceManager.closeSession();	 		
		} catch (Exception e) {
	 		throw new BusinessException(e);
		}
	}

	/**
	 * Fecha o recurso de conexão ou persistência 
	 * 
	 * @param 	dao DAO
	 * @throws  BusinessException em caso de erro
	 */
	protected void close(TransactionDAO dao) throws BusinessException {
		try {
			if (dao != null) {
				dao.close();	
			}
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	/**
	 * Desfaz uma transação de um TransactionDAO  
	 * 
	 * @param 	dao DAO
	 * @throws  BusinessException em caso de erro
	 */
	protected void rollback(TransactionDAO dao) throws BusinessException {
		try {
			if (dao != null) {
				dao.rollback();	
			}
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

}







