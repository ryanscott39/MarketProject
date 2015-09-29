package market.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DataAccess {
	public static Connection getConnection() {
		Connection cn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://localhost/northwind", "root", "");
		}
		catch(SQLException ex) {
			System.out.println("Database Connection error: " + ex);
		}
		catch(ClassNotFoundException ex) {
			System.out.println("Class not Found: " + ex);
		}
		return cn;
	}
	

}