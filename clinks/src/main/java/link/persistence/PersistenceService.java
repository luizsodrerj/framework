package link.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * Classe utilitaria para servico de persistencia 
 * JPA generico
 * 
 * @author Luiz Alberto
 *
 */
public class PersistenceService {

	/**
	 * Valor default
	 */
	public static final String DEFAULT_PERSISTENCE_UNIT = "DEFAULT_PERSISTENCE_UNIT";

	private EntityManager manager;
	
	
	/**
	 * Construtor default
	 */ 
	public PersistenceService() {
		//this(DEFAULT_PERSISTENCE_UNIT);
	}
	
	/**
	 * Inicializa a classe com o gerenciador de persistencia JPA
	 * 
	 * @param manager gerenciador de persistencia JPA
	 */
	public PersistenceService(EntityManager manager) {
		super();
		this.manager = manager;
	}

	/**
	 * Inicializa a classe com a unidade de persistencia
	 * 
	 * @param persistenceUnitName nome da unidade de persistencia
	 */
//	public PersistenceService(String persistenceUnitName) {
//		super();
//		this.persistenceUnitName = persistenceUnitName;
//	}
	
	public EntityManager getEntityManager() {
		return manager;
	}
	
	/**
	 * Inicia o serviço de persistência
	 * 
	 */
	public void connect() {
		manager = manager == null || !manager.isOpen() ? createEntityManager() : manager;
	}

	/**
	 * Cria um Gerenciador de Persistencia JPA
	 * 
	 * @return Gerenciador de Persistencia JPA
	 */
	private EntityManager createEntityManager() {
		return PersistenceUtil.createEntityManager();
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
	 * @param query		Query contendo a sentença JPA-QL
	 * @param params	Parâmetros para a query
	 * @return			Lista contendo os registros encontrados 
	 */
	public <T> List<T> findByQuery(String query, Object[] params) {
		connect();

		Query q = manager.createQuery(query);
	
		if (params != null && params.length > 0) {
			int length = params.length;
			
			for (int i = 0; i < length; i++) {
				q.setParameter(i + 1, params[i]);
			}
		}
		return q.getResultList();
	}

	/**
	 * Localiza os registros de uma determinado objeto de acordo com os parametros e query passados
	 * 
	 * @param <T>		Tipo parametrizado
	 * @param query		Named Query que contem a sentença JPA-QL
	 * @param params	Parâmetros para a query
	 * @return			Lista contendo os registros encontrados 
	 */
	public <T> List<T> findByNamedQuery(String name, Object[] params) {
		connect();

		Query q = manager.createNamedQuery(name);
	
		if (params != null && params.length > 0) {
			int length = params.length;
			
			for (int i = 0; i < length; i++) {
				q.setParameter(i + 1, params[i]);
			}
		}
		return q.getResultList();
	}

	/**
	 * Deleta uma entidade da base de dados
	 * 
	 * @param entity Entidade a ser deletada
	 */
	public void remove(Object entity) {
		manager.remove(entity);
		manager.flush();
	}

	/**
	 * Persiste uma entidade na base de dados
	 * 
	 * @param entity Entidade a ser persistida
	 */
	public void persist(Object entity) {
		manager.persist(entity);
		manager.flush();
	}

	/**
	 * Persiste uma entidade na base de dados
	 * 
	 * @param entity Entidade a ser persistida
	 */
	public void persistAndCommit(Object entity) {
		try {
			connect();
			beginTransaction();
			manager.persist(entity);
			manager.flush();
			commit();
			
		} catch (Exception e) {
			rollbackTransaction();
			throw new PersistenceException(e);
		} finally {
			close();
		}
	}
	
	
	/**
	 * Atualiza uma entidade na base de dados
	 * 
	 * @param entity Entidade a ser atualizada
	 */
	public void merge(Object entity) {
		manager.merge(entity);
		manager.flush();
	}

	/**
	 * Persiste uma entidade na base de dados
	 * 
	 * @param entity Entidade a ser persistida
	 */
	public void mergeAndCommit(Object entity) {
		try {
			connect();
			beginTransaction();
			manager.merge(entity);
			manager.flush();
			commit();
			
		} catch (Exception e) {
			rollbackTransaction();
			throw new PersistenceException(e);
		} finally {
			close();
		}
	}

	/**
	 * Localiza uma entidade pelo id
	 * 
	 * @param <T>		Tipo parametrizado
	 * @param classe	Classe da entidade a ser localizada
	 * @param id		id da entidade
	 * @return			Entidade encontrada
	 */
	public <T>T findObject(Class<T> classe, Object id) {
		connect();
		
		return  manager.find(classe, id);
	}
	
	/**
	 * Inicia um transação
	 * 
	 */
	public void beginTransaction() {
		connect();
		manager.getTransaction().begin();
	}

	/**
	 * Confirma uma transação
	 * 
	 */
	public void commit() {
		if (manager != null && manager.isOpen()) {
			manager.getTransaction().commit();
		}
	}
	
	/**
	 * Desfaz uma transação
	 * 
	 */
	public void rollbackTransaction() {
		if (manager != null && manager.isOpen()) {
			manager.getTransaction().rollback();
		}
	}

	/**
	 * Finaliza o serviço de persistencia e libera o recurso JPA
	 * 
	 */
	public void close() {
		if (manager != null && manager.isOpen()) {
			manager.close();
		}
	}

}





