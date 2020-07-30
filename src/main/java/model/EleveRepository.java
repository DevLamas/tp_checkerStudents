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
	
	public ArrayList<Eleve> getEleveByClasse(Classe classe) {
		ArrayList<Eleve> eleves = new ArrayList();
		try {
			PreparedStatement statement = this.getBdd().prepareStatement("SELECT * from eleve where classe = ?");
			ResultSet rs = statement.executeQuery();
			statement.setInt(1, classe.getId());      
			while(rs.next()){
				eleves.add(new Eleve(rs.getInt("id"),rs.getString("nom"),rs.getString("prenom"),rs.getString("dateN"),classe,rs.getString("login"),rs.getString("mdp")));
			}
    	} catch (SQLException ex) {
        	System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
		return eleves;		
	}
	
	public Eleve getEleveByLogin(String login) {
		Eleve eleve = null;
		try {
			PreparedStatement statement = this.getBdd().prepareStatement("SELECT eleve.id,eleve.nom, eleve.prenom, eleve.dateN, classe.id as classeid, classe.annee as classeannee, classe.designation as classedesignation, classe.ecole as classeecole, eleve.login, eleve.mdp,ecole.id as ecoleid, ecole.nom as ecolenom  from eleve join classe on classe.id = eleve.classe join ecole on ecole.id = classe.ecole where login = ?");
			ResultSet rs = statement.executeQuery();
			statement.setString(1, login);			
			if(rs.next()){
				 eleve = new Eleve(rs.getInt("id"),rs.getString("nom"),rs.getString("prenom"),rs.getString("dateN"),new Classe(rs.getInt("classeid"),rs.getString("classeannee"),rs.getString("classedesignation"),new Ecole(rs.getInt("ecoleid"),rs.getString("ecolenom"),null)),rs.getString("login"),rs.getString("mdp"));
			}
    	} catch (SQLException ex) {
        	System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
		return eleve;		
	}

	public Connection getBdd() {
		return bdd;
	}

	public EleveRepository(Connection bdd) {
		super();
		this.bdd = bdd;
	}
}
