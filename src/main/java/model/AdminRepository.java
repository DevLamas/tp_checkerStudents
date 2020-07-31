package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AdminRepository {
	public AdminRepository() {
		
	}
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

	
	public Admin find(int id) {
		ConnexionBDD connectBdd = new ConnexionBDD();
		Connection con = connectBdd.connexion();
		ResultSet result = null;
		
		String requete = "Select * from admin where id = "+ id +";";
		try {
			Statement state = con.createStatement();
			result = state.executeQuery(requete);
			Admin admin = buildObjet(result);
			return admin;
			
		}catch(Exception e) {
			System.out.println("");
			e.printStackTrace();
			Admin admin = new Admin(0, null, null);
            return admin;
		}
	}
	
	
	public static void insertData(Admin a) {
		ConnexionBDD connectBdd = new ConnexionBDD();
		Connection con = connectBdd.connexion();
		String requete = "insert into admin (login, password) values ('"+ a.getLogin() +"','" + a.getPassword() +"')";
		
		try{
            PreparedStatement preparedStmt = con.prepareStatement(requete);
            preparedStmt.execute();
        } catch (Exception e ){
            System.out.println("erreur insert : ");
            e.printStackTrace();
        }
		
	}
	
	public static void updateData(Admin a) {
		ConnexionBDD connectBdd = new ConnexionBDD();
		Connection con = connectBdd.connexion();
		String requete = "update admin set login = '"+ a.getLogin() +"', password = '" + a.getPassword() +"' where id = " + a.getId() + ")";
		
		try{
            PreparedStatement preparedStmt = con.prepareStatement(requete);
            preparedStmt.execute();
        } catch (Exception e ){
            System.out.println("erreur update : ");
            e.printStackTrace();
        }
		
	}
	
	public static void deleteData(Admin a) {
		ConnexionBDD connectBdd = new ConnexionBDD();
		Connection con = connectBdd.connexion();
		String requete = "delete from admin where id = " + a.getId() +";";
		
		try {
            PreparedStatement preparedStmt = con.prepareStatement(requete);
            preparedStmt.execute();

        } catch (Exception e ){
            System.out.println("Une erreure delete Admin : ");
            e.printStackTrace();
        }
	}
	
	protected Admin buildObjet(ResultSet result) throws SQLException {
		Admin admin = new Admin(0, null, null);
		while (result.next()) {
			int id = result.getInt(1);
			admin.setId(id);
			String login = result.getString(2);
			admin.setLogin(login);
			String password = result.getString(3);
			admin.setPassword(password);
		}
		return admin;
	}
	
	
}
