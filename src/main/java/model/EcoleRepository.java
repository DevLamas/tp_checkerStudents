package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EcoleRepository {
	
	public Ecole find(int id) {
		ConnexionBDD connectBdd = new ConnexionBDD();
		Connection con = connectBdd.connexion();
		ResultSet result = null;
		
		String requete = "Select * from ecole where id = "+ id +";";
		try {
			Statement state = con.createStatement();
			result = state.executeQuery(requete);
			Ecole ecole = buildObjet(result);			
			return ecole;
			
		}catch(Exception e) {
			System.out.println("");
			e.printStackTrace();
			Ecole ecole = new Ecole(0, null, null);
            return ecole;
		}
	}
	
	
	public static void insertData(Ecole e) {
		ConnexionBDD connectBdd = new ConnexionBDD();
		Connection con = connectBdd.connexion();
		String requete = "insert into ecole (admin, nom) values ("+ e.getAdmin().getId() +",'" + e.getNom() +"')";
		
		try{
            PreparedStatement preparedStmt = con.prepareStatement(requete);
            preparedStmt.execute();
        } catch (Exception ex ){
            System.out.println("erreur insert : ");
            ex.printStackTrace();
        }
		
	}
	
	public static void updateData(Ecole e) {
		ConnexionBDD connectBdd = new ConnexionBDD();
		Connection con = connectBdd.connexion();
		String requete = "update ecole set admin = "+e.getAdmin().getId() +", nom = '" + e.getNom() +"' where id = " + e.getId() + ")";
		
		try{
            PreparedStatement preparedStmt = con.prepareStatement(requete);
            preparedStmt.execute();
        } catch (Exception ex ){
            System.out.println("erreur update : ");
            ex.printStackTrace();
        }
		
	}
	
	public static void deleteData(Ecole e) {
		ConnexionBDD connectBdd = new ConnexionBDD();
		Connection con = connectBdd.connexion();
		String requete = "delete from ecole where id = " + e.getId() +";";
		
		try {
            PreparedStatement preparedStmt = con.prepareStatement(requete);
            // execute the preparedstatement
            preparedStmt.execute();

        } catch (Exception ex ){
            System.out.println("Une erreure delete Ecole : ");
            ex.printStackTrace();
        }
	}	
	
	
	
	
	
	protected Ecole buildObjet(ResultSet result) throws SQLException {
		Ecole ecole = new Ecole(0, null, null);
		while (result.next()) {
			int id = result.getInt(1);
			ecole.setId(id);
			String admin = result.getString(2);
			ecole.setAdmin(null);
			String nom = result.getString(3);
			ecole.setNom(nom);
		}
		return ecole;
	}

}
