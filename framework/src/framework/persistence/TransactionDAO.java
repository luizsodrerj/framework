package framework.persistence;

import framework.exceptions.DAOException;

/**
 * Interface para implementações em DAOs 
 * que desejam controlar transações  
 * 
 * @author Luiz Alberto
 */
public interface TransactionDAO {

	/**
	 * Inicia uma transação 
	 * 
	 * @throws DAOException em caso de erro
	 */
	public void beginTransaction() throws DAOException;

	/**
	 * Desfaz uma transação 
	 * 
	 * @throws DAOException em caso de erro
	 */
	public void rollback() throws DAOException;

    /**
     * Efetua o commit numa transaÃ§Ã£o.

	 * @throws DAOException em caso de erro
     */
    public void commit() throws DAOException; 
	
    /**
     * Fecha a sessão ou conexão do recurso de persistência 
     * 
     * @throws DAOException em caso de erro
     */
    public void close() throws DAOException;
    
}












