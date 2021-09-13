package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DBUtil {

	static String sql = "CREATE MEMORY TABLE Peca (         " +
						"id			INTEGER IDENTITY PRIMARY KEY," +
						"descricao	VARCHAR(500),                " +
						"categoria	VARCHAR(100),                " +
						"tipo		VARCHAR(100),                " +
						"imagem		LONGVARBINARY,               " +
						"status		VARCHAR(70),                 " +
						"preco		DOUBLE                       " +
						")";
	
	static String sqlIns = "INSERT INTO Peca ( " +
							"descricao, " +
							"categoria, " +
							"tipo	,   " +
							"preco	    " +
							") VALUES ("
							+ "'Peca Mostruario',"
							+ "'Joia',"
							+ "'Cordao',"
							+ "10.0"
							+ ")";                                         

	
	public static void main(String[] args) {
		try {
			initDB();
			carga();
			
			System.out.println("Base de Dados criada com sucesso!");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	static void initDB() throws Exception {
		Connection c = null;
		try {
			c = getCon();
			PreparedStatement p = c.prepareStatement(sql);
			p.executeUpdate();
			
		} finally {
			c.close();
		}
	}

	static void carga() throws Exception {
		Connection c = null;
		try {
			c = getCon();
			PreparedStatement p = c.prepareStatement(sqlIns);
			p.executeUpdate();
			
		} finally {
			c.close();
		}
	}
	
	static Connection getCon() throws Exception {
		String path = "/home/tqi_lsodre/Desktop/Sodre/MyWorkspace/framework-labweb/bijus/src/main/resources/dados/bijudb;shutdown=true";
		String url = "jdbc:hsqldb:file:" + path;
		String u = "sa";
		String p = "";
		
		Class.forName("org.hsqldb.jdbcDriver");
		
		return  DriverManager.getConnection(url, u, p);
	}
	
}





