package framework.persistence.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import framework.exceptions.DAOException;
import framework.persistence.QueryUtil;


/**
 * 
 * Classe base para DAOs do tipo Hibernate
 * 
 * @author Luiz Alberto
 */
public abstract class BaseHibernateDAO {
    
    /**
     * Retorna a sessÃ£o local.
     * <p/>
     * Se a sessÃ£o nÃ£o estiver aberta, um nova sessÃ£o Ã© criada..
     *
     * @return Session sessão de hibernate
     * @throws DAOException em caso de erro
     */
    protected static Session getSession() throws DAOException {
        return PersistenceManager.getSession();
    }
    
    /**
     * Retorna  uma transaÃ§Ã£o.
     * 
     * @return Transaction transação
     * @throws DAOException em caso de erro
     */
    protected static Transaction getTransaction() throws DAOException {
         return PersistenceManager.getTransaction();
    }
    
    /**
     * Inicia uma transação
     * 
     * @throws DAOException em caso de erro
     */
    public void beginTransaction() throws DAOException {
         PersistenceManager.beginTransaction();     
    }

    /**
     * Efetua o commit numa transaÃ§Ã£o.
     * 
     * @throws DAOException em caso de erro
     */
    public void commit() throws DAOException {
         PersistenceManager.commit();     
    }
    
    /**
     * Efetua o rollback numa transaÃ§Ã£o.
     * 
     * @throws DAOException em caso de erro
     */
    public void rollback() throws DAOException {
         PersistenceManager.rollback();
    }

    /**
     * Finaliza a sessÃ£o local
     * 
     * @throws DAOException em caso de erro
     */
    public void closeSession() throws DAOException {
    	PersistenceManager.closeSession();
    }

	/**
	 * Fecha a sessão ou conexão do recurso de persistência
	 * 
	 * @throws DAOException em caso de erro
	 */
    public void close() throws DAOException {
    	closeSession();
    }
    
    /**
     * Salva um objeto utilizando os métodos do hibernate
     * 
     * @param object		Objeto persistente
     * @throws DAOException em caso de erro
     */
    public void save(Object object) throws DAOException {
    	try {
			getSession().save(object);
			getSession().flush();
		} catch (Exception e) {
			throw new DAOException(e);
		}
    }
    
    /**
     * Salva ou atualiza um objeto utilizando os métodos do hibernate
     * 
     * @param object		Objeto persistente
     * @throws DAOException em caso de erro
     */
    public void saveOrUpdate(Object object) throws DAOException {
    	try {
			getSession().saveOrUpdate(object);
			getSession().flush();
		} catch (Exception e) {
			throw new DAOException(e);
		}
    }

    /**
     * Atualiza um objeto utilizando os métodos do hibernate
     * 
     * @param object		Objeto persistente
     * @throws DAOException em caso de erro
     */
    public void update(Object object) throws DAOException {
    	try {
			getSession().update(object);
			getSession().flush();
		} catch (Exception e) {
			throw new DAOException(e);
		}
    }

    /**
     * Deleta um objeto utilizando os métodos do hibernate
     * 
     * @param 	objeto Objeto persistente
     * @throws	DAOException em caso de erro
     */
    public void delete(Object objeto) throws DAOException {
    	try {
			getSession().delete(objeto);
			getSession().flush();
		} catch (Exception e) {
			throw new DAOException(e);
		}
    }

    /**
     * Retorna um objeto utilizando os métodos do hibernate a partir de uma chave Integer
     * 
     * @param 	type 	tipo do objeto persistente
     * @param 	id 		id do objeto persistente
     * @return 	um objeto persistente
     * @throws 	DAOException em caso de erro
     */
    public Object get(Class type, Serializable id) throws DAOException {
    	Object objeto = null;
    	try {
			objeto = getSession().get(type, id);
		} catch (Exception e) {
			throw new DAOException(e);
		}
		return objeto;
    }
    
	/**
	 * Retorna uma query do Hibernate
	 * 
	 * @param 	query uma query baseada na linguagem de query do Hibernate 
     * @return 	uma query do Hibernate
     * @throws 	DAOException em caso de erro
	 */
	protected Query createQuery(String query) throws DAOException {
    	try {
    		return getSession().createQuery(query);
		} 
    	catch (Exception e) {
			throw new DAOException(e);
		}
	}

    /**
	 * Retorna uma query do Hibernate pelo nome
	 * 
     * @param   queryName nome da query
     * @return  uma query do Hibernate pelo nome
     * @throws 	DAOException em caso de erro
     */
    protected Query createNamedQuery(String queryName) throws DAOException {
    	try {
            return getSession().getNamedQuery(queryName);
		} 
    	catch (Exception e) {
			throw new DAOException(e);
		}
    }
    
	/**
	 * Cria um criterio de busca do Hibernate
	 * 
	 * @param 	javaClass Classe persistente a qual sera aplicado o criterio
	 * @return	criterio de busca do Hibernate
	 * @throws 	DAOException em caso de erro
	 */
	protected Criteria createCriteria(Class javaClass) throws DAOException {
		return getSession().createCriteria(javaClass);
	}

	/**
	 * Seleciona todos as instancias persistentes de uma Classe persistente
	 * 
	 * @param 	javaClass Classe persistente a qual sera aplicado o criterio
	 * @return	todos as instancias persistentes de uma Classe persistente
	 * @throws 	DAOException em caso de erro
	 */
	public List findAll(Class javaClass) throws DAOException {
    	try {
    		return createCriteria(javaClass).list();
		} 
    	catch (Exception e) {
			throw new DAOException(e);
		}
	}

	
	/**
     * Retorna uma lista de objetos baseados em uma query 
     * 
     * @param	queryKey	chave para retornar a query a partir do cache
     * @return	lista de objetos retornados pela query
     * @throws	DAOException em caso de erro
     */
	public List findByQuery(String queryKey) throws DAOException {
		try {
			String	hql		= QueryUtil.getQuery(queryKey);
			Query	query	= getSession().createQuery(hql);
			
			return query.list();
			
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	/**
     * Retorna uma lista de objetos baseados em uma query 
     * 
     * @param	queryKey	chave para retornar a query a partir do cache
     * @param	params		parâmetros para a query 
     * @return	lista de objetos retornados pela query
     * @throws	DAOException em caso de erro
     */
    public List findByQuery(String queryKey, Object[] params) throws DAOException {
    	try {
        	String 	sql 	= QueryUtil.getQuery(queryKey);
        	Query 	query	= getSession().createQuery(sql);
        	int		length	= params.length;
        	
        	for (int i = 0; i < length; i++) {
				query.setParameter(i, params[i]);
			}
        	return query.list();
        	
		} catch (Exception e) {
			throw new DAOException(e);
		}
    }
	
	
}


	
	
	
	
	
	
	