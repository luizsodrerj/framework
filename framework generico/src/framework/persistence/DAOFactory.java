package framework.persistence;

import java.sql.Connection;

import framework.exceptions.DAOException;
import framework.persistence.hibernate.HibernateDAOFactory;

/**
 * Fábrica de fábricas DAOs 
 *
 * Created on 11/02/2006
 * @author Luiz Alberto
 */
public abstract class DAOFactory {

	public static final int JDBC_FACTORY 		= 1;
	public static final int HIBERNATE_FACTORY	= 2;
	
	
    /**
     * Retorna a instancia do DAO solicitado.
     * @param 	daoName nome lógico do DAO
     * @return 	Object a instancia do DAO solicitado.
     * @throws  DAOException em caso de erro
     */    
	public abstract Object getDAO(String daoName) throws DAOException;

    /**
     * Retorna a instancia do DAO solicitado.
     * @param 	daoName 	nome lógico do DAO
     * @param 	connection  Conexão com o banco
     * @return 	Object a instancia do DAO solicitado.
     * @throws  DAOException em caso de erro
     */    
	public abstract Object getDAO(String daoName, Connection connection) throws DAOException;

	/**
	 * Retorna uma fábrica de DAOs de acordo com o parâmetro
	 * 
	 * @param 	whichFactory informa qual fábrica será retornada
	 * @return	uma fábrica de DAOs de acordo com o parâmetro
	 */
	public static DAOFactory getDAOFactory(int whichFactory) {
		switch (whichFactory) {

			case JDBC_FACTORY:
				return new JdbcDAOFactory();

			case HIBERNATE_FACTORY:
				return new HibernateDAOFactory();

			default:
				return null;
		}
	}
	
	
}














