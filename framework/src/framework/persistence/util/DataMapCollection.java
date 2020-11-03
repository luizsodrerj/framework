package framework.persistence.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Processa coleções de objetos a partir de dados 
 * do banco de dados
 * 
 * @author Luiz Alberto
 */
public class DataMapCollection {

	private String[] columns;

	
	/**
	 * Retorna um objeto a partir do banco
	 * 
     * @param   resultSet  ResultSet da query
     * @param   javaClass classe do objeto que será retornado
     * @return  um objeto a partir do banco
     * @throws 	Exception em caso de erro
	 */
	public Map<String,Object> resultSetToUniqueMap(ResultSet resultSet) throws Exception {
		List<Map<String,Object>> list = getDataMapList(resultSet);
		
		return !list.isEmpty() ? list.get(0) : null;
	}
	
    /**
     * Retorna uma coleção de Mapas de dados partir do banco
     * 
     * @param   resultSet  ResultSet da query
     * @param   javaClass classe do DTO que populará a coleção
     * @return  uma coleção de DTOs a partir do banco
     * @throws 	Exception em caso de erro
     */
    public List<Map<String,Object>> getDataMapList(ResultSet resultSet)  {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();

		try {
			ResultSetMetaData metaData = resultSet.getMetaData();
			setColumns(metaData);

			while (resultSet.next()) {
				Map<String,Object> dados = getDataMap(resultSet);
				list.add(dados);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		return list;
    }

	/**
	 * Recupera todas as colunas de uma query e as formata
	 * 
	 * @param metaData Metadados da query
     * @throws SQLException Em caso de erro de Banco de Dados
	 */
	private void setColumns(ResultSetMetaData  metaData) throws Exception {
        int cols = metaData.getColumnCount();
        columns  = new String[cols];

        for (int i = 1; i <= cols; i++) {
            columns[i - 1] = metaData.getColumnName(i).toUpperCase();
        }
	}

	/**
	 * Define os dados de um mapa de acordo com as colunas de uma query
	 * 
	 * @param resultSet Linha da query
	 * @return mapa de dados
	 * @throws Exception Em caso de erro
	 */
	private Map<String,Object> getDataMap(ResultSet resultSet) throws Exception {
		int 				colsArrayLength = columns.length;
        Map<String,Object> 	dados 			= new HashMap<String, Object>();
        
        for (int i = 1; i <= colsArrayLength; i++)  {
            Object value = resultSet.getObject(i);
            dados.put(columns[i - 1], value);
        }
        
        return dados;
	}
    
}









