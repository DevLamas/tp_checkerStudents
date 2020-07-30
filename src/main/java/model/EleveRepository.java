package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EleveRepository {
	private Connection bdd;
	
	public boolean checkConnection(String login, String password){
		try {
			PreparedStatement statement = this.getBdd().prepareStatement("SELECT 1 from eleve where login = ? and mdp = ?");
			ResultSet rs = statement.executeQuery();
			statement.setString(1, login);
			statement.setString(2, password);			
	    	
	        if(!rs.next())
	        	return false;	        
	        
    	} catch (SQLException ex) {
        	System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
		return true;
	}
	
	/*public ArrayList<Eleve> getEleveByClasse() {
		
	}*/

	public Connection getBdd() {
		return bdd;
	}

	public EleveRepository(Connection bdd) {
		super();
		this.bdd = bdd;
	}
}
