package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
	
import java.sql.Statement;
import java.util.ArrayList;


public class ClasseRepository {
	private Connection bdd;
	
	
	
	public ArrayList<Classe> getClassesByEcole(Ecole ecole) {
		ArrayList<Classe> classes = new ArrayList();
		ResultSet result = null;
		
		String requete = "Select * from classe c where c.ecole = " + ecole.getId() +";";
		try {
			Statement state = this.getBdd().createStatement();
			result = state.executeQuery(requete);
			while(result.next()){			
				classes.add(buildObjet(result));
			}
			
			return classes;
			
		}catch(Exception e) {
			System.out.println("");
			e.printStackTrace();
		}
		return classes;
	}
	
	public ArrayList<Classe> getClasses() {
		ArrayList<Classe> classes = new ArrayList();
		ResultSet result = null;
		
		String requete = "Select * from classe;";
		try {
			Statement state = this.getBdd().createStatement();
			result = state.executeQuery(requete);
			while(result.next()){			
				classes.add(buildObjet(result));
			}
			
			return classes;
			
		}catch(Exception e) {
			System.out.println("");
			e.printStackTrace();
		}
		return classes;
	}
				
	
	public ClasseRepository(Connection bdd) {
		super();
		this.bdd = bdd;
	}

	public Classe getClasseById(int id) {
		Classe classe = null;
		try {
			PreparedStatement statement = this.getBdd().prepareStatement("SELECT id, annee, designation, ecole.id as ecoleid, ecole.nom as ecolenom from classe join ecole on classe.ecole = ecole.id where classe.id = ?");
			statement.setInt(1, id);
			
			ResultSet rs = statement.executeQuery();
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
	
	public int getNewId() {
		int newid = -1;
		try {
			PreparedStatement statement = this.getBdd().prepareStatement("SELECT max(id)+1 as newid from classe");
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
	
	public boolean addClasse(Classe classe) {
		try {
			PreparedStatement statement = this.getBdd().prepareStatement("insert into classe values(?,?,?,?)");
			statement.setInt(1,this.getNewId());
			statement.setString(2, classe.getAnnee());
			statement.setString(3, classe.getDesignation());
			statement.setInt(4, classe.getEcole().getId());
			
			if(statement.executeUpdate() < 1)
				return false;
			
		} catch (SQLException ex) {
        	System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
		return true;
	}
	
	public boolean updateClasse(Classe classe) {
		try {
			PreparedStatement statement = this.getBdd().prepareStatement("update classe set annee = ?, designation = ?, ecole = ? where id = ?");
			statement.setString(1, classe.getAnnee());
			statement.setString(2, classe.getDesignation());
			statement.setInt(3, classe.getEcole().getId());
			statement.setInt(4, classe.getId());
			
			if(statement.executeUpdate() < 1)
				return false;
			
		} catch (SQLException ex) {
        	System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
		return true;
	}
	
	public boolean deleteClasse(int classeId) {
		try {
			PreparedStatement statement = this.getBdd().prepareStatement("delete from classe where id = ?");
			statement.setInt(1, classeId);
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
	
	
	
	public ArrayList<String> getClassesLibelle() {
		ArrayList<String> classes = new ArrayList();
		try {
			PreparedStatement statement = this.getBdd().prepareStatement("SELECT * from classe"); 
			ResultSet rs = statement.executeQuery();
			while(rs.next()){
				classes.add(rs.getString("designation")+" "+rs.getString("annee"));
			}
    	} catch (SQLException ex) {
        	System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
		return classes;		
	}
	
	protected Classe buildObjet(ResultSet result) throws SQLException {
		Classe classe = new Classe(0, null, null, null);
		while (result.next()) {
			int id = result.getInt(1);
			classe.setId(id);
			String annee = result.getString(2);
			classe.setAnnee(annee);
			String designation = result.getString(3);
			classe.setDesignation(designation);
			int ecole = result.getInt(4);
			classe.setEcole(null);
		}
		return classe;
	}

}
