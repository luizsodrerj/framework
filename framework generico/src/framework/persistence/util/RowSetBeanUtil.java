package framework.persistence.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Processa coleções de objetos a partir de dados 
 * do banco de dados
 * 
 * @author luiz.sodre
 */
public class RowSetBeanUtil {

	private String[] columns;

	
	public <T>T getSingleResult(
			   		Class type,
					Connection connection,
					Object[] params,
					String query
				) {
		List list = getBeanCollection(
						type, 
						connection, 
						params, 
						query
					);
		return (T) (
					!list.isEmpty() ? 
					list.get(0) : 
					null
				   );	
	}	
	
	public <T>T getBeanCollection(
			   		Class type,
					Connection connection,
					Object[] params,
					String query
				) {
		List list = null;
		
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			
			if (params != null && params.length > 0) {
				int index = 0;
				
				for (Object param: params) {
					ps.setObject(++index, param);
				}
			}
			ResultSet result = ps.executeQuery();
		
			list = resultSetToCollection(result, type);
			
			result.close();
			ps.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return (T)list;
	}
	
	/**
	 * Retorna um objeto a partir do banco
	 * 
     * @param   resultSet  ResultSet da query
     * @param   javaClass classe do objeto que será retornado
     * @return  um objeto a partir do banco
     * @throws 	Exception em caso de erro
	 */
	public Object resultSetToUniqueObject(ResultSet resultSet, Class javaClass) throws Exception {
		List collection = resultSetToCollection(resultSet, javaClass);
		
		return !collection.isEmpty() ? collection.get(0) : null;
	}
	
    /**
     * Retorna uma coleção de DTOs a partir do banco
     * 
     * @param   resultSet  ResultSet da query
     * @param   javaClass classe do DTO que populará a coleção
     * @return  uma coleção de DTOs a partir do banco
     * @throws 	Exception em caso de erro
     */
    public List resultSetToCollection(ResultSet resultSet, Class javaClass) throws Exception {
		String 	beanClassName	= javaClass.getName();
		List 	collection 		= new ArrayList();
	
		ResultSetMetaData metaData = resultSet.getMetaData();
		getColumns(metaData);

		while (resultSet.next()) {
			Object bean = newInstance(beanClassName);
			setBeanProperties(bean, resultSet);
			collection.add(bean);
		}
		
		return collection;
    }

	/**
	 * Recupera todas as colunas de uma query e as formata
	 * 
	 * @param pMetaData Metadados da query
     * @throws SQLException Em caso de erro de Banco de Dados
	 */
	private void getColumns(ResultSetMetaData  pMetaData) throws Exception {
        int cols = pMetaData.getColumnCount();
        columns  = new String[cols];

        for (int i = 1; i <= cols; i++) {
            String colName = pMetaData.getColumnName(i).toUpperCase();
            columns[i - 1] = format(colName);
        }
	}

	/**
	 * Retorna um objeto da classe passada como parâmetro
	 * 
	 * @param 	className Nome da classe
	 * @return	Objeto da classe passada como parâmetro
     * @throws  Exception Em caso de erro ao instanciar um objeto
	 */
	private Object newInstance(String className) throws Exception {
        Class     classe  = RowSetBeanUtil.class.getClassLoader().loadClass(className);
        Object    object  = classe.newInstance();
    
        return object;
	}

	/**
	 * Define os dados de um bean de acordo com as colunas de uma query
	 * 
	 * @param object Bean a ser definido
	 * @param resultSet Linha da query
	 * @throws Exception Em caso de erro
	 */
	private void setBeanProperties(Object object, ResultSet resultSet) throws Exception {
        int                     arrayLength = columns.length;
        BeanInfo                beanInfo    = Introspector.getBeanInfo(object.getClass());
        PropertyDescriptor[]    properties  = beanInfo.getPropertyDescriptors();
        
        for (int i = 0; i < arrayLength; i++)  {
            String setMethod    = getSetMethodByColumn(columns[i]);
            
            for (int j = 0; j < properties.length; j++) {
                if (properties[j].getWriteMethod() != null) {
                    String metdName = properties[j].getWriteMethod().getName();
                
                    if (metdName.equals(setMethod)) {
                        Method method   = properties[j].getWriteMethod();
                        invokeSetMethod(method, object, resultSet, i + 1);
                        break;
                    }
                }
            }
        }
	}

    /**
     * Retorna o nome do metodo Set de um bean
     * 
     * @param  object metodo a ser formatado
     * @return metodo Set de um bean
     */
	private String getSetMethodByColumn(Object object) {
		String method	= (String)object;
		String first	= method.substring(0, 1).toUpperCase();

		return new StringBuffer("set").append(first).append(method.substring(1, method.length())).toString();
	}

	/**
    * Formata o nome de uma coluna de uma query
    * 
    * @param nomeVar Nome da coluna
    * @return Coluna formatada
    */
    private String format(String nomeVar)  {
        StringBuffer stringHelper = new StringBuffer("");
        stringHelper.append(nomeVar.toLowerCase().substring(0, 1));
        
        if (nomeVar.indexOf("_") != -1) {
            for (int i=1; i < nomeVar.length(); i++) {
                if (nomeVar.charAt(i) == '_') {
                    stringHelper.append(nomeVar.toUpperCase().substring(i+1, i+2));
                    ++i;
                } else {
                    stringHelper.append(nomeVar.toLowerCase().substring(i, i+1));
                }
            }
        } else {
            stringHelper.append(nomeVar.substring(1, nomeVar.length()).toLowerCase());
        }
        return stringHelper.toString();
    }

    /**
     * Invoca o metodo set do DTO correpondente a coluna da query
     * 
     * @param   method      metodo a ser invocado
     * @param   object      objeto cujo metodo sera invocado
     * @param   resultSet   ResultSet da query
     * @param   columnIndex indice da coluna no ResultSet da query
     * @throws  Exception   em caso de erro
     */
    private void invokeSetMethod(Method method, Object object, ResultSet resultSet, int columnIndex) throws Exception {
        Class[] params  = method.getParameterTypes();
        Class   param   = params[0];

        if (param.getName().equals(String.class.getName())) {
            method.invoke(object, new Object[]{resultSet.getString(columnIndex)});
            return;
        } 
        
        if (param.getName().equals(Date.class.getName())) {
            method.invoke(object, new Object[]{resultSet.getDate(columnIndex)});
            return;
        }
        
        invokePrimitiveMethod(param, method, object, resultSet, columnIndex);        
    }

    /**
     * Invoca o metodo set do DTO correpondente a coluna da query,
     * que contenha um possivel parametro de tipo primitivo
     * 
     * @param   param       Classe java do parametro
     * @param   method      metodo a ser invocado
     * @param   object      objeto cujo metodo sera invocado
     * @param   resultSet   ResultSet da query
     * @param   columnIndex indice da coluna no ResultSet da query
     * @throws  Exception   em caso de erro
     */
    private void invokePrimitiveMethod (	Class       param, 
	                                        Method      method, 
	                                        Object      object, 
	                                        ResultSet   resultSet, 
	                                        int         columnIndex ) throws Exception {
        if (param.getName().equals(Long.class.getName())) {
            invokeLongMethod(method, object, resultSet, columnIndex);
            return;
        }
        if (param.getName().equals(long.class.getName())) {
            invokeLongMethod(method, object, resultSet, columnIndex);
            return;
        }
        if (param.getName().equals(Integer.class.getName())) {
            invokeIntegerMethod(method, object, resultSet, columnIndex);
            return;
        }
        if (param.getName().equals(int.class.getName())) {
            invokeIntegerMethod(method, object, resultSet, columnIndex);
            return;
        }
        if (param.getName().equals(Double.class.getName())) {
            invokeDoubleMethod(method, object, resultSet, columnIndex);
            return;
        }
        if (param.getName().equals(double.class.getName())) {
            invokeDoubleMethod(method, object, resultSet, columnIndex);
            return;
        }
    }

    /**
     * Invoca o metodo set do DTO correpondente a coluna da query,
     * que contenha um parametro de tipo Double
     * @param   method      metodo a ser invocado
     * @param   object      objeto cujo metodo sera invocado
     * @param   resultSet   ResultSet da query
     * @param   columnIndex indice da coluna no ResultSet da query
     * @throws  Exception   em caso de erro
     */
    private void invokeDoubleMethod(Method method, Object object, ResultSet resultSet, int columnIndex) throws Exception {
        String value = resultSet.getString(columnIndex);
    
        if (value != null)  {
            method.invoke(object, new Object[]{Double.valueOf(value)});    
        }
    }

    /**
     * Invoca o metodo set do DTO correpondente a coluna da query,
     * que contenha um parametro de tipo Integer
     * 
     * @param   method      metodo a ser invocado
     * @param   object      objeto cujo metodo sera invocado
     * @param   resultSet   ResultSet da query
     * @param   columnIndex indice da coluna no ResultSet da query
     * @throws  Exception   em caso de erro
     */
    private void invokeIntegerMethod(Method method, Object object, ResultSet resultSet, int columnIndex) throws Exception {
        String value = resultSet.getString(columnIndex);
    
        if (value != null)  {
            method.invoke(object, new Object[]{Integer.valueOf(value)});
        }
    }

    /**
     * Invoca o metodo set do DTO correpondente a coluna da query,
     * que contenha um parametro de tipo Long
     * 
     * @param   method      metodo a ser invocado
     * @param   object      objeto cujo metodo sera invocado
     * @param   resultSet   ResultSet da query
     * @param   columnIndex indice da coluna no ResultSet da query
     * @throws  Exception   em caso de erro
     */
    private void invokeLongMethod(Method method, Object object, ResultSet resultSet, int columnIndex) throws Exception {
        String value = resultSet.getString(columnIndex);
    
        if (value != null)  {
            method.invoke(object, new Object[]{Long.valueOf(value)});
        }
    }
    
}









