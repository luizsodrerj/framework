package pdv.service;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.TransactionPropagationType;
import org.jboss.seam.annotations.Transactional;
import org.jboss.seam.log.Log;



/**
 * Esta classe auxilia na implementação dos metodos básicos de CRUD,
 * para Application Services com suporte a seam. 
 * Deve ser estendida pelos Services da aplicação 
 * 
 * @author Luiz Alberto
 *
 */
@Scope(ScopeType.CONVERSATION)
@Name("appService")
public class ApplicationService implements	Serializable {

	private static final long serialVersionUID = -1741166827675867231L;
	
	public static final String SEARCH_SELECT_COUNT_CLAUSE	= "select count(o) ";
	public static final String SEARCH_SELECT_CLAUSE 		= "select o ";
	public static final String PARAMETERS					= "PARAMETERS";
	public static final String QUERY						= "QUERY";
	
	@In
	protected EntityManager entityManager;

	@Logger
	private Log log;
	
	private Object entity;
	private int count;
	
	
	/**
	 * Atualiza um Entity Bean no banco
	 * 
	 * @param entity Entity Bean
	 */
	@Transactional(TransactionPropagationType.REQUIRED)
	public void update(Object entity) {
		try {
			entityManager.merge(entity);
			entityManager.flush();
			
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
			throw new ApplicationServiceException(e);
		}
	}
	
	/**
	 * Deleta uma entidade da base de dados
	 * 
	 * @param entity Entidade a ser deletada
	 */
	@Transactional(TransactionPropagationType.REQUIRED)
	public void remove(Object entity) {
		try {
			entityManager.remove(entity);
			entityManager.flush();
			
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
			throw new ApplicationServiceException(e);
		}
	}
	
	/**
	 * Persiste um Entity Bean no banco
	 * 
	 * @param entity Entity Bean
	 */
	@Transactional(TransactionPropagationType.REQUIRED)
	public void save(Object entity) {
		try {
			entityManager.persist(entity);
			entityManager.flush();
			
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
			throw new ApplicationServiceException(e);
		}
	}

	/**
	 * Realiza consulta através de QBE (Query By Example)
	 * 
	 * @param <T>			tipo parametrizado
	 * @param entity		Entity Bean de exemplo com parâmetros para query
	 * @param orderList		lista de atributos pelos quais será ordenada a query
	 * @return				lista contendo o resultado da query
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> findByExample(Object entity, List<String>orderList) {
		Criteria criterio	= createCriteria(entity.getClass());
		Example example 	= createExample(entity);
		
		criterio.add(example);

    	if (orderList != null) {
    		for (String propertyName : orderList) {
    			criterio.addOrder(Order.asc(propertyName));
    		}
		}
		return criterio.list();
	}
	
	/**
	 * Retorna example a partir de um Entity Bean
	 * 
	 * @param	entity Entity Bean
	 * @return	example a partir de um Entity Bean
	 */
	private Example createExample(Object entity) {
		Example	example	= Example.create(entity);
		example.ignoreCase();
		example.enableLike(MatchMode.ANYWHERE);
		
		return example;
	}


	/**
	 * Recupera o nome da entidade principal do bean.
	 * 
	 * @return Nome da entidade principal do bean definida na anotação da classe.  
	 */
	public String getEntityName() {
		return entity.getClass().getSimpleName();
	}
	
	/**
	 * Localiza todos os registros de uma determinado objeto de forma ordenada
	 * 
	 * @param <T>		Tipo parametrizado
	 * @param type		Tipo da classe a ser localizada 
	 * @param orderBy	Atributo pelo qual serao ordenados os registros
	 * @return			Lista ordenada contendo todos os registros de uma determinado objeto 
	 */
	public <T> List<T> findAll(Class<T> type, String orderBy) {
		StringBuilder query =	new StringBuilder("select o from ")
								.append(type.getSimpleName())
								.append(" o ");
		
		String order = 	orderBy != null  && !"".equals(orderBy.trim()) ? 
						" order by o." + orderBy : "";
		
		query.append(order);
		
		return findByQuery(query.toString(), null);
	}
	
	/**
	 * Localiza todos os registros de uma determinado objeto de forma ordenada
	 * 
	 * @param <T>		Tipo parametrizado
	 * @param type		Tipo da classe a ser localizada 
	 * @return			Lista ordenada contendo todos os registros de uma determinado objeto 
	 */
	public <T> List<T> findAll(Class<T> type) {
		StringBuilder query =	new StringBuilder("select o from ")
								.append(type.getSimpleName())
								.append(" o ");
		
		return findByQuery(query.toString(), null);
	}

	
	/**
	 * Localiza os registros de uma determinado objeto de acordo com os parametros e query passados
	 * 
	 * @param <T>		Tipo parametrizado
	 * @param jpaql		Query contendo a sentença JPA-QL
	 * @param params	Parâmetros para a query
	 * @return			Lista contendo os registros encontrados 
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> findByQuery(String jpaql, Object[] params) {
		Query query = entityManager.createQuery(jpaql);
	
		setParameters(params, query);
		
		return query.getResultList();
	}

	/**
	 * Localiza os registros de uma determinado objeto de acordo com os parametros e query passados
	 * 
	 * @param <T>			tipo parametrizado
	 * @param jpaql			query contendo a sentença JPA-QL
	 * @param params		parâmetros para a query
	 * @param startIndex	índice inicial da paginação
	 * @param endIndex		índice final da paginação
	 * @return				Lista contendo os registros encontrados
	 */
	public <T> List<T> findByQuery(String jpaql,Object[] params,int startIndex,int endIndex) {
        return executeQuery(jpaql, params, false, startIndex, endIndex);
    }

	/**
	 * Localiza os registros de uma determinado objeto de acordo com os parametros e query passados
	 * 
	 * @param <T>			tipo parametrizado
	 * @param namedQuery	query contendo a sentença JPA-QL
	 * @param params		parâmetros para a query
	 * @param startIndex	índice inicial da paginação
	 * @param endIndex		índice final da paginação
	 * @return				Lista contendo os registros encontrados
	 */
	public <T> List<T> findByNamedQuery(String namedQuery,Object[] params,int startIndex,int endIndex) {
        return executeQuery(namedQuery, params, true, startIndex, endIndex);
    }

    /**
     * Executa uma Query ou NamedQuery de acordo com os 
     * parametros e query passados
     * 
     * @param  ejbqlOrNamedQuery Query ou NamedQuery
     * @param  params			parâmetros para a query
     * @param  isNamed			se é uma NamedQuery
	 * @param  startIndex		índice inicial da paginação
	 * @param  endIndex			índice final da paginação
	 * @return Lista contendo os registros encontrados
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    protected <T> List<T> executeQuery(
    					String ejbqlOrNamedQuery,
    					Object[] params,
    					boolean isNamed,
    					int startIndex,
    					int endIndex) {
    	List result = null;
    	Query query = null;
    	
        if (startIndex < 1) {
            startIndex = 1;
        }
        if ((endIndex - startIndex) < 0) {
            result = new ArrayList();
        } else {
       		query = isNamed ? entityManager.createNamedQuery(ejbqlOrNamedQuery) :	
       				entityManager.createQuery(ejbqlOrNamedQuery); 
        	
        	setParameters(params, query);
        	query.setMaxResults(endIndex - startIndex + 1);
        	query.setFirstResult(startIndex - 1);
        	
        	result = query.getResultList();
        }
        return result;
    }
    
    /**
     * Define os parâmetros da query
     * 
     * @param params parâmetros da query
     * @param query  query aonde os parâmetros serão definidos
     */
	private void setParameters(Object[] params, Query query) {
		if (params != null && params.length > 0) {
			int length = params.length;
			
			for (int i = 0; i < length; i++) {
				query.setParameter(i + 1, params[i]);
			}
		}
	}
    
	/**
	 * Localiza os registros de uma determinado objeto de acordo com os parametros e query passados
	 * 
	 * @param <T>		Tipo parametrizado
	 * @param name		Named Query que contem a sentença JPA-QL
	 * @param params	Parâmetros para a query
	 * @return			Lista contendo os registros encontrados 
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> findByNamedQuery(String name, Object[] params) {
		Query query = entityManager.createNamedQuery(name);
		setParameters(params, query);

		return query.getResultList();
	}

	/**
	 * Obtém os dados de um unico EntityBean 
	 * de acordo com os parametros e query passados
	 * 
	 * @param name		named query que contem a sentença JPA-QL
	 * @param params	parâmetros para a query
	 * @return			EntityBean encontrado
	 */
	public Object findByNamedQueryOneResult(String name, Object[] params) {
		Query query 	= entityManager.createNamedQuery(name);
		Object result	= null;

		try {
			setParameters(params, query);
			
			result = query.getSingleResult();

		} catch (NoResultException e) {
			result = null;
		}
		return result;
	}

	/**
	 * Obtém os dados de um unico EntityBean 
	 * de acordo com os parametros e query passados
	 * 
	 * @param  name			 named query que contem a sentença JPA-QL
	 * @param  params		 parâmetros para a query
	 * @param  setMaxResults se deve ser forçado numero maximo de registros para 1
	 * @return EntityBean encontrado
	 */
	public Object findByNamedQueryOneResult(String name, Object[] params, boolean setMaxResults) {
		Query query 	= entityManager.createNamedQuery(name);
		Object result	= null;

		try {
			setParameters(params, query);
		
			if (setMaxResults) {
				query.setMaxResults(1);
			}
			result = query.getSingleResult();

		} catch (NoResultException e) {
			result = null;
		}
		return result;
	}

	/**
	 * Obtém os dados de um unico EntityBean 
	 * de acordo com os parametros e query passados
	 * 
	 * @param jpaql		query contendo a sentença JPA-QL
	 * @param params	parâmetros para a query
	 * @return			EntityBean encontrado 
	 */
	public Object findByQueryOneResult(String jpaql, Object[] params) {
		Query query 	= entityManager.createQuery(jpaql);
		Object result	= null;

		try {
			setParameters(params, query);
			
			result = query.getSingleResult();

		} catch (NoResultException e) {
			log.error(e.getLocalizedMessage(), e);
			result = null;
		}
		return result;
	}
	
//	/**
//	 * Deleta uma entidade da base de dados
//	 * 
//	 * @param entity Entidade a ser deletada
//	 */
//	public void remove(Object entity) {
//		entityManager.remove(entity);
//		entityManager.flush();
//	}
//
//	/**
//	 * Persiste uma entidade na base de dados
//	 * 
//	 * @param entity Entidade a ser persistida
//	 */
//	public void persist(Object entity) {
//		entityManager.persist(entity);
//		entityManager.flush();
//	}

//	/**
//	 * Atualiza uma entidade na base de dados
//	 * 
//	 * @param entity Entidade a ser atualizada
//	 */
//	public void merge(Object entity) {
//		entityManager.merge(entity);
//		entityManager.flush();
//	}

	/**
	 * Localiza uma entidade pelo id
	 * 
	 * @param <T>		Tipo parametrizado
	 * @param classe	Classe da entidade a ser localizada
	 * @param id		id da entidade
	 * @return			Entidade encontrada
	 */
	public <T>T findObject(Class<T> classe, Object id) {
		return  entityManager.find(classe, id);
	}


	/**
	 * Define o Entity Bean
	 * 
	 * @param entity Entity Bean
	 */
	public void setEntity(Object entity) {
		this.entity = entity;
	}

	/**
	 * Retorna o total de registros da consulta
	 * 
	 * @return o total de registros da consulta
	 */
	public int getCount() {
		return count;
	}

	/**
	 * Define o total de registros da consulta
	 * 
	 * @param count o total de registros da consulta
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * Retorna query por criterio
	 * 
	 * @param  classe Entity Bean Class
	 * @return query por criterio
	 */
	protected Criteria createCriteria(Class<?> classe) {
		Session session = (Session) entityManager.getDelegate();
		
		return session.createCriteria(classe);
	}
	
	public <T> List<T> findAllByCriteria(Class<T> type) {
		Criteria crit = createCriteria(type);
		
		return crit.list();
	}
	
	/**
	 * Retorna a classe do Entity Bean
	 * 
	 * @return classe do Entity Bean
	 */
	public Class<?> getEntityClass() {
		return entity.getClass();
	}

	/**
	 * Define o Gerenciador de Persistencia
	 * 
	 * @param entityManager Gerenciador de Persistencia
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}







