package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
	
import java.sql.Statement;


public class ClasseRepository {
	
	public ResultSet findAll() {
		ConnexionBDD connectBdd = new ConnexionBDD();
		Connection con = connectBdd.connexion();
		ResultSet result = null;
		
		String requete = "Select * from classe;";
		try {
			Statement state = con.createStatement();
			result = state.executeQuery(requete);
			return result;
			
		}catch(Exception e) {
			System.out.println("");
			e.printStackTrace();
            return result;
		}
	}
	
	public ResultSet find(int id) {
		ConnexionBDD connectBdd = new ConnexionBDD();
		Connection con = connectBdd.connexion();
		ResultSet result = null;
		
		String requete = "Select * from classe where id = "+ id +";";
		try {
			Statement state = con.createStatement();
			result = state.executeQuery(requete);
			
			
			return result;
			
		}catch(Exception e) {
			System.out.println("");
			e.printStackTrace();
            return result;
		}
	}
	
	
	public static void insertData(Classe c) {
		ConnexionBDD connectBdd = new ConnexionBDD();
		Connection con = connectBdd.connexion();
		String requete = "insert into classe (annee, designation,ecole) values ('"+ c.getAnnee() +"','" + c.getDesignation() +"', "+ c.getEcole().getId() +")";
		
		try{
            PreparedStatement preparedStmt = con.prepareStatement(requete);
            preparedStmt.execute();
        } catch (Exception e ){
            System.out.println("erreur insert : ");
            e.printStackTrace();
        }
		
	}
	
	public static void updateData(Classe c) {
		ConnexionBDD connectBdd = new ConnexionBDD();
		Connection con = connectBdd.connexion();
		String requete = "update classe set annee = '"+ c.getAnnee() +"', designation = '" + c.getDesignation() +"', ecole = "+ c.getEcole().getId() +""
				+ " where id = " + c.getId() + ")";
		
		try{
            PreparedStatement preparedStmt = con.prepareStatement(requete);
            preparedStmt.execute();
        } catch (Exception e ){
            System.out.println("erreur update : ");
            e.printStackTrace();
        }
		
	}
	
	public static void deleteData(Classe c) {
		ConnexionBDD connectBdd = new ConnexionBDD();
		Connection con = connectBdd.connexion();
		String requete = "delete from classe where id = " + c.getId() +";";
		
		try {
            PreparedStatement preparedStmt = con.prepareStatement(requete);
            // execute the preparedstatement
            preparedStmt.execute();

        } catch (Exception e ){
            System.out.println("Une erreure delete Classe : ");
            e.printStackTrace();
        }
	}
	
	
	public Classe getClasseById(int id) {
		Classe classe = null;
		ConnexionBDD connectBdd = new ConnexionBDD();
		Connection con = connectBdd.connexion();
		try {
			PreparedStatement statement = con.prepareStatement("SELECT id, annee, designation, ecole.id as ecoleid, ecole.nom as ecolenom from classe join ecole on classe.ecole = ecole.id where classe.id = ?");
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
