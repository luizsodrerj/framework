package framework.persistence.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultSetMap {

	private String[]columns = null;


    public List<Map<String,Object>>executeQuery(
    					Connection connection,
    					Object[] params,
    					String query
    				) {
		List<Map<String,Object>>list = new ArrayList<Map<String,Object>>();
 
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			
			if (params != null && params.length > 0) {
				int index = 0;
				
				for (Object param: params) {
					ps.setObject(++index, param);
				}
			}
			ResultSet result = ps.executeQuery();
			
			list = getDataMapList(result);
			
			result.close();
			ps.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return list;
    }
	
	
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

	private Map<String,Object> getDataMap(ResultSet resultSet) throws Exception {
        Map<String,Object>dados = new HashMap<String,Object>();
        
        for (int i = 1; i <= columns.length; i++)  {
            Object value      = resultSet.getObject(i);
            String columnName = columns[i - 1];
            
            dados.put(columnName, value);
        }
        return dados;
	}
    
	private void setColumns(ResultSetMetaData  metaData) throws Exception {
        int cols = metaData.getColumnCount();
        columns  = new String[cols];

        for (int i = 1; i <= cols; i++) {
            columns[i - 1] = metaData.getColumnName(i).toUpperCase();
        }
	}


}
