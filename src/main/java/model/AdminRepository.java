package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminRepository {
	private Connection bdd;
	
	public AdminRepository(Connection bdd) {
		this.bdd = bdd;
	}

	public Connection getBdd() {
		return bdd;
	}

	public boolean checkConnection(String login, String password){
		try {
			PreparedStatement statement = this.getBdd().prepareStatement("SELECT 1 from admin where login = ? and mdp = ?");
			statement.setString(1, login);
			statement.setString(2, password);	
			ResultSet rs = statement.executeQuery();
	    	
	        if(!rs.next())
	        	return false;	        
	        
    	} catch (SQLException ex) {
        	System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
		return true;
	}
	
	
}
