package framework.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import framework.exceptions.DAOException;
import framework.persistence.util.RowSetBeanUtil;
import framework.util.ServiceLocator;

/**
 * Classe base para Data Access Objects
 * @author  Beto
 * @date    23/05/2004
 */
public abstract class BaseJdbcDAO {
    protected 	Connection        	connection;
    protected	PreparedStatement	statement;
    protected	ResultSet			resultSet;
    private 	ServiceLocator		serviceLocator = ServiceLocator.instance();
    
    /**
     * Inicializa a classe 
     */
    public BaseJdbcDAO() {
        super();
    }
    
	/**
	 * Inicializa a classe com uma conex�o com banco de dados
	 * 
	 * @param connection conex�o com banco de dados
	 */
	public BaseJdbcDAO(Connection connection) {
		super();
		this.connection = connection;
	}
	
    /**
     * Inicializa a classe com possibilidade de conex�o com banco
     * 
     * @param alternativeSource	fonte de dados alternativa
     * @param isJndiName		se o nome da fonte de dados � um nome jndi
     * @throws DAOException		em caso de erro
     */
    public BaseJdbcDAO(String alternativeSource, boolean isJndiName) throws DAOException {
    	try {
        	if (isJndiName) {
        		if (connection == null || connection.isClosed()) {
        			connect(alternativeSource);
        		}
        	} else if (connection == null || connection.isClosed()) {
    			String sourceName = serviceLocator.getProperty("ALTERNATIVE_DATA_SOURCE");
    			connect(sourceName);
        	}
		} catch (Exception e) {
    		throw new DAOException(e.toString());
		}
    }

    /**
     * Inicializa a classe com possibilidade de conex�o com banco
     * @param makeConnect flag da conex�o com banco
     * @throws framework.exceptions.DAOException em caso de erro
     */
    public BaseJdbcDAO(boolean makeConnect) throws DAOException {
        if (makeConnect)  {
            makeConnection();
        }
    }

    /**
     * Prepara uma senten�a sql
     * 
     * @param  sql senten�a sql
     * @throws DAOException em caso de erro
     */
    public void prepareStatement(String sql) throws DAOException {
    	try {
			statement = connection.prepareStatement(sql);
    		
		} catch (Exception e) {
			throw new DAOException(e);
		}
    }

    /**
     * Executa uma query
     * 
     * @throws DAOException em caso de erro
     */
    public void executeQuery() throws DAOException {
    	try {
			resultSet = statement.executeQuery();
    		
		} catch (Exception e) {
			throw new DAOException(e);
		}
    }
    
    /**
     * Retorna uma cole��o de DTOs a partir do banco
     * 
     * @param   resultSet  ResultSet da query
     * @param   javaClass classe do DTO que popular� a cole��o
     * @return  uma cole��o de DTOs a partir do banco
     */
    protected List getResultSetToCollection(ResultSet resultSet, Class javaClass) throws DAOException {

		try {
			RowSetBeanUtil beanUtil = new RowSetBeanUtil();
			
			return beanUtil.resultSetToCollection(resultSet, javaClass);
			
		} catch (Exception e) {
			throw new DAOException(e);
		}
    }

    /**
	 * Inicia uma transa��o 
	 * 
	 * @throws DAOException em caso de erro
	 */
	public void beginTransaction() throws DAOException {
		try {
			if (connection != null) {
				connection.setAutoCommit(false);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	/**
	 * Desfaz uma transa��o 
	 * 
	 * @throws DAOException em caso de erro
	 */
	public void rollback() throws DAOException {
		try {
			if (connection != null) {
				connection.rollback();
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

    /**
     * Efetua o commit numa transação.

	 * @throws DAOException em caso de erro
     */
    public void commit() throws DAOException {
		try {
			if (connection != null) {
				connection.commit();
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	} 
	
    /**
     * Fecha a sess�o ou conex�o do recurso de persist�ncia 
     * 
     * @throws DAOException em caso de erro
     */
    public void close() throws DAOException {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

    /**
     * Conecta-se ao banco de dados
     * @throws DAOException em caso de erro
     */
    protected void makeConnection() throws DAOException {
        try {
            if (connection == null || connection.isClosed()) {
                String sourceName = serviceLocator.getDataSourceName();
                connect(sourceName);
            }
        }
        catch (Exception e) {
            throw new DAOException(e.toString());
        }
    }
    
    /**
     * Conecta-se ao banco de dados
     * 
     * @param sourceName	nome Jndi da fonte de dados
     * @throws DAOException em caso de erro
     */
    protected void connect(String sourceName) throws DAOException {
        try {
			DataSource  source	= (DataSource)serviceLocator.getService(sourceName);
			connection 			= source.getConnection();
		}
        catch (Exception e) {
			throw new DAOException(e.toString());
		}
    }

    /**
     * Libera os statements
     * 
     * @throws DAOException em caso de erro
     */
    protected void releaseStatements() throws DAOException {
        try {
        	if (resultSet != null) {
        		resultSet.close();
        	}
            if (statement != null) {
                statement.close();
            }
        }
        catch (SQLException e) {
            throw new DAOException(e.toString());
        }
    }

    /**
     * Libera todos os recursos da conex�o com banco
     * 
     * @throws DAOException em caso de erro
     */
    protected void releaseAll() throws DAOException {
        try {
        	if (resultSet != null) {
        		resultSet.close();
        	}
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        catch (SQLException e) {
            throw new DAOException(e.toString());
        }
    }

    /**
     * Libera todos os recursos da conex�o com banco 
     * menos o ResultSet
     * 
     * @throws 	DAOException em caso de erro
     */
    protected void releaseWithoutResultSet() throws DAOException {
        try {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        catch (SQLException e) {
            throw new DAOException(e.toString());
        }
    }

    /**
     * Define a conex�o JDBC
     * 
     * @param connection conex�o JDBC definidora
     */
    public void setConnection(Connection connection) {
    	this.connection = connection;
    }
    
    
    /**
     * Retorna a conex�o com banco
     * 
	 * @return a conex�o com banco
	 */
	public Connection getConnection() {
		return connection;
	}
}









