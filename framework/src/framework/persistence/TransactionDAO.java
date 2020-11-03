package framework.persistence;

import framework.exceptions.DAOException;

/**
 * Interface para implementa��es em DAOs 
 * que desejam controlar transa��es  
 * 
 * @author Luiz Alberto
 */
public interface TransactionDAO {

	/**
	 * Inicia uma transa��o 
	 * 
	 * @throws DAOException em caso de erro
	 */
	public void beginTransaction() throws DAOException;

	/**
	 * Desfaz uma transa��o 
	 * 
	 * @throws DAOException em caso de erro
	 */
	public void rollback() throws DAOException;

    /**
     * Efetua o commit numa transação.

	 * @throws DAOException em caso de erro
     */
    public void commit() throws DAOException; 
	
    /**
     * Fecha a sess�o ou conex�o do recurso de persist�ncia 
     * 
     * @throws DAOException em caso de erro
     */
    public void close() throws DAOException;
    
}












