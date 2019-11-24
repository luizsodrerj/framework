package framework.persistence;

import java.io.Serializable;
import java.util.List;

import framework.exceptions.DAOException;

/**
 * Interface para DAOs genéricos
 * 
 * <p>
 * Criada em: 19/02/2006
 * </p>
 * 
 * @author Luiz Alberto
 */
public interface GenericDAO extends TransactionDAO {

	/**
	 * Retorna um objeto utilizando os métodos do hibernate a partir de uma
	 * chave Integer
	 * 
	 * @param type
	 *            tipo do objeto persistente
	 * @param id
	 *            id do objeto persistente
	 * @return um objeto persistente
	 * @throws DAOException
	 *             em caso de erro
	 */
	public Object get(Class type, Serializable id) throws DAOException;

	/**
	 * Salva ou atualiza um objeto utilizando os métodos do hibernate
	 * 
	 * @param object
	 *            Objeto persistente
	 * @throws DAOException
	 *             em caso de erro
	 */
	public void saveOrUpdate(Object object) throws DAOException;

	/**
	 * Atualiza um objeto utilizando os métodos do hibernate
	 * 
	 * @param object
	 *            Objeto persistente
	 * @throws DAOException
	 *             em caso de erro
	 */
	public void update(Object object) throws DAOException;
	
	/**
	 * Salva um objeto utilizando os métodos do hibernate
	 * 
	 * @param object
	 *            Objeto persistente
	 * @throws DAOException
	 *             em caso de erro
	 */
	public void save(Object object) throws DAOException;

	/**
	 * Seleciona todos as instancias persistentes de uma Classe persistente
	 * 
	 * @param javaClass
	 *            Classe persistente a qual sera aplicado o criterio
	 * @return todos as instancias persistentes de uma Classe persistente
	 * @throws DAOException
	 *             em caso de erro
	 */
	public List findAll(Class javaClass) throws DAOException;

    /**
     * Deleta um objeto persistente
     * 
     * @param 	object Objeto a ser deletado 
     * @throws	DAOException em caso de erro
     */
    public void delete(Object object) throws DAOException;
    
    /**
     * Retorna uma lista de objetos baseados em uma query 
     * 
     * @param	queryKey	chave para retornar a query a partir do cache
     * @param	params		parâmetros para a query 
     * @return	lista de objetos retornados pela query
     * @throws	DAOException em caso de erro
     */
    public List findByQuery(String queryKey, Object[] params) throws DAOException;

	/**
     * Retorna uma lista de objetos baseados em uma query 
     * 
     * @param	queryKey	chave para retornar a query a partir do cache
     * @return	lista de objetos retornados pela query
     * @throws	DAOException em caso de erro
     */
	public List findByQuery(String queryKey) throws DAOException;
}








