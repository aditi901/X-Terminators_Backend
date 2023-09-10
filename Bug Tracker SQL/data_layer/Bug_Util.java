package data_layer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Bug_Util {
private static Connection con;
	
	public static Connection getConn() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hsbc_db", "root", "give_password");
		} catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return con;
	}
}