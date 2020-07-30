package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClasseRepository {
	private Connection bdd;

	public Classe getClasseById(int id) {
		Classe classe = null;
		try {
			PreparedStatement statement = this.getBdd().prepareStatement("SELECT id, annee, designation, ecole.id as ecoleid, ecole.nom as ecolenom from classe join ecole on classe.ecole = ecole.id where classe.id = ?");
			ResultSet rs = statement.executeQuery();
			statement.setInt(1, id);			
			if(rs.next()){
				 classe = new Classe(rs.getInt("id"), rs.getString("nom"), rs.getString("designation"), new Ecole(rs.getInt("ecoleid"),rs.getString("ecolenom"),null));
			}
    	} catch (SQLException ex) {
        	System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
		return classe;		
	}

	public Connection getBdd() {
		return bdd;
	}
}
