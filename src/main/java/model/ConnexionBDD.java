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
	
	public ConnexionBDD() {
		try {
			Properties prop = new Properties();
			try {
				InputStream input = new FileInputStream("src/main/ressources/config.properties");
				prop.load(input);
			} catch (final IOException ex) {
				ex.printStackTrace();
			}
        	this.bdd = DriverManager.getConnection(prop.getProperty("db.url"),prop.getProperty("db.username "),prop.getProperty("db.password "));
        } catch (SQLException ex) {
        	System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
	}
}
