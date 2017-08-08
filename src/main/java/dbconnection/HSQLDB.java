package dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HSQLDB {
	public static Connection getConnection () throws SQLException{
		 Connection c = DriverManager.getConnection("jdbc:hsqldb:file:hdb/travels", "SA", "");
		 return c;
	}
	private static void init(){
		
	}
	private static void createTable(){
		
		
	}
}
