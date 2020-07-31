package model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import java.sql.ResultSet;
import java.util.Properties;

public class ConnexionBDD {
	private Connection bdd;
	
	public Connection getBdd() {
		return bdd;
	}

	public static Connection connexion() {
		try {
			Properties prop = new Properties();
			try {
				InputStream input = new FileInputStream("src/main/resources/config.properties");
				prop.load(input);
				
				String url = prop.getProperty("db.url");
	            String user = prop.getProperty("db.user");
	            String passwd = prop.getProperty("db.password");

	            Connection con = DriverManager.getConnection(url,user,passwd);
	            return con;
	            
			} catch (final IOException ex) {
				ex.printStackTrace();
			}
        } catch (SQLException ex) {
        	System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return null;
        }
		return null;
	}
	
}
