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
	
	public ArrayList<Eleve> getEleveByClasse(Classe classe) {
		ArrayList<Eleve> eleves = new ArrayList();
		try {
			PreparedStatement statement = this.getBdd().prepareStatement("SELECT * from eleve where classe = ?");
			statement.setInt(1, classe.getId());  
			ResultSet rs = statement.executeQuery();
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
	
	
	public ArrayList<Eleve> getEleves() {
		ArrayList<Eleve> eleves = new ArrayList();
		try {
			PreparedStatement statement = this.getBdd().prepareStatement("SELECT eleve.id,eleve.nom, eleve.prenom, eleve.dateN, classe.id as classeid, classe.annee as classeannee, classe.designation as classedesignation, classe.ecole as classeecole, eleve.login, eleve.mdp,ecole.id as ecoleid, ecole.nom as ecolenom  from eleve join classe on classe.id = eleve.classe join ecole on ecole.id = classe.ecole"); 
			ResultSet rs = statement.executeQuery();
			while(rs.next()){
				eleves.add(new Eleve(rs.getInt("id"),rs.getString("nom"),rs.getString("prenom"),rs.getString("dateN"),new Classe(rs.getInt("classeid"),rs.getString("classeannee"),rs.getString("classedesignation"),new Ecole(rs.getInt("ecoleid"),rs.getString("ecolenom"),null)),rs.getString("login"),rs.getString("mdp")));
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
			statement.setString(1, login);	
			ResultSet rs = statement.executeQuery();
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
	
	public int getNewId() {
		int newid = -1;
		try {
			PreparedStatement statement = this.getBdd().prepareStatement("SELECT max(id)+1 as newid from eleve");
			ResultSet rs = statement.executeQuery();
			if(rs.next()){
				newid = rs.getInt("newid");
			}
    	} catch (SQLException ex) {
        	System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
		return newid;
	}
	
	public boolean addEleve(Eleve eleve) {
		try {
			PreparedStatement statement = this.getBdd().prepareStatement("insert into eleve values(?,?,?,?,?,?,?)");
			statement.setInt(1,this.getNewId());
			statement.setString(2, eleve.getNom());
			statement.setString(3, eleve.getPrenom());
			statement.setString(4, eleve.getDateN());
			statement.setInt(5, eleve.getClasse().getId());
			statement.setString(6, eleve.getLogin());
			statement.setString(7, eleve.getPassword());
			
			if(statement.executeUpdate() < 1)
				return false;
			
		} catch (SQLException ex) {
        	System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
		return true;
	}
	
	public boolean updateEleve(Eleve eleve) {
		try {
			PreparedStatement statement = this.getBdd().prepareStatement("update eleve set nom = ?, prenom = ?, dateN = ?, classe = ?, login = ?, mdp = ? where id = ?");
			statement.setString(1, eleve.getNom());
			statement.setString(2, eleve.getPrenom());
			statement.setString(3, eleve.getDateN());
			statement.setInt(4, eleve.getClasse().getId());
			statement.setString(5, eleve.getLogin());
			statement.setString(6, eleve.getPassword());
			statement.setInt(7,eleve.getId());
			
			if(statement.executeUpdate() < 1)
				return false;
			
		} catch (SQLException ex) {
        	System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
		return true;
	}
	
	public boolean deleteEleve(int eleveId) {
		try {
			PreparedStatement statement = this.getBdd().prepareStatement("delete from eleve where id = ?");
			statement.setInt(1, eleveId);
			if(statement.executeUpdate() < 1)
				return false;			
		} catch (SQLException ex) {
        	System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }		
		return true;
	}

	public Connection getBdd() {
		return bdd;
	}

	public EleveRepository(Connection bdd) {
		super();
		this.bdd = bdd;
	}
}
