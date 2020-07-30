package model;

import java.sql.*;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import java.sql.ResultSet;

public class ConnexionBDD {
	private Connection bdd;
	
	public ConnexionBDD() {
		try {
        	this.bdd = DriverManager.getConnection("jdbc:mysql://localhost:3306/students_checker","root","");
        } catch (SQLException ex) {
        	System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
	}
}
