package framework.business.facade;

import java.io.Serializable;
import java.util.List;

import framework.exceptions.BusinessException;

/**
 * Interface básica para implementações de Facades
 *
 * Created on Feb 25, 2006
 * @author Luiz Alberto
 */
public interface BaseSessionFacade {

	/**
     * Retorna um objeto utilizando os métodos do hibernate a partir de uma chave Integer
     * 
     * @param 	type 	tipo do objeto persistente
     * @param 	id 		id do objeto persistente
     * @return 	um objeto persistente
     * @throws 	BusinessException Em caso de erro
     */
	public Object getObject(Class type, Serializable id) throws BusinessException;
	
	/**
	 * Retorna uma lista de objetos utilizando os métodos do hibernate a partir da classe persistente
	 * 
	 * @param 	type classe persistente
	 * @return	uma lista de objetos 
	 * @throws	BusinessException em caso de erro
	 */
	public List findAll(Class type) throws BusinessException;
	
	/**
	 * Salva ou atualiza um objeto persistente
	 * 
	 * @param 	persistentObject objeto a ser persistido
	 * @throws	BusinessException em caso de erro
	 */
	public void saveOrUpdateObject(Object persistentObject) throws BusinessException;
	
	/**
	 * Salva um objeto persistente
	 * 
	 * @param	persistentObject objeto a ser persistido
	 * @throws	BusinessException em caso de erro
	 */
	public void saveObject(Object persistentObject) throws BusinessException;
	

	/**
	 * Retorna uma lista de objetos utilizando os métodos do hibernate 
	 * a partir da classe persistente
	 * 
	 * @param 	type classe persistente
	 * @return	uma lista de objetos 
	 * @throws	BusinessException em caso de erro
	 */
	public List findLazyAll(Class type) throws BusinessException;
	
	/**
     * Retorna um objeto utilizando os métodos do hibernate a partir de uma chave, 
     * deixando a sessão aberta para lazy objects
     *   
     * @param 	type 	tipo do objeto persistente
     * @param 	id 		id do objeto persistente
     * @return 	um objeto persistente
     * @throws 	BusinessException Em caso de erro
     */
	public Object getLazyObject(Class type, Serializable id) throws BusinessException;
	
	/**
	 * Salva ou atualiza um objeto persistente deixando a sessão aberta para lazy objects
	 * 
	 * @param persistentObject objeto a persistir
	 * @throws BusinessException Em caso de erro
	 */
	public void saveOrUpdateLazyObject(Object persistentObject) throws BusinessException;
	
	/**
	 * Atualiza um objeto persistente deixando a sessão aberta para lazy objects
	 * 
	 * @param persistentObject objeto a persistir
	 * @throws BusinessException Em caso de erro
	 */
	public void updateLazyObject(Object persistentObject) throws BusinessException;
	
	/**
	 * Salva um objeto persistente deixando a sessão aberta para lazy objects
	 * 
	 * @param persistentObject objeto a persistir
	 * @throws BusinessException Em caso de erro
	 */
	public void saveLazyObject(Object persistentObject) throws BusinessException;

}








