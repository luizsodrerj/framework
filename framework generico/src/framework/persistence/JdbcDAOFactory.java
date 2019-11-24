package framework.persistence;

import java.sql.Connection;

import framework.exceptions.DAOException;
import framework.util.ServiceLocator;

/**
 * Fábrica de DAOs do tipo JDBC
 *
 * Created on 11/02/2006
 * @author Luiz Alberto
 */
public class JdbcDAOFactory extends DAOFactory {

	/* (non-Javadoc)
	 * @see framework.persistence.DAOFactory#getDAO(java.lang.String)
	 */
	public Object getDAO(String daoName) throws DAOException {
    	try {
			ServiceLocator	locator 	= ServiceLocator.instance(); 
			String 			daoImplName	= locator.getProperty(daoName);
			Class 			daoImpl 	= Class.forName(daoImplName);

			return daoImpl.newInstance();
		} 
    	catch (Exception e) {
			throw new DAOException(e);
		}
	}

	/* (non-Javadoc)
	 * @see framework.persistence.DAOFactory#getDAO(java.lang.String, java.sql.Connection)
	 */
	public Object getDAO(String daoName, Connection connection) throws DAOException {
    	try {
			ServiceLocator	locator 	= ServiceLocator.instance(); 
			String 			daoImplName	= locator.getProperty(daoName);
			Class 			daoImpl 	= Class.forName(daoImplName);
			BaseJdbcDAO 	jdbcDAO 	= (BaseJdbcDAO) daoImpl.newInstance();

			jdbcDAO.setConnection(connection);
			
			return jdbcDAO;
		} 
    	catch (Exception e) {
			throw new DAOException(e);
		}
	}

}





