package controller;

import model.*;
import java.io.IOException;
import java.sql.Connection;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class EventListClasseController {
	@FXML
	private Button buttonAddClasse;

	@FXML
	private Button buttonDeleteClasse;

	@FXML
	private Button retourAcceuil;
	

	@FXML
	private ListView<String> listClasse;
	
	
	@FXML
	private void addClasse() throws IOException {
		Stage stage;
		stage = (Stage) buttonAddClasse.getScene().getWindow();
		Parent root;
		root = FXMLLoader.load(getClass().getResource("../view/CreateClasse.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	
	@FXML
	private void deleteClasse() throws IOException {
		Stage stage;
		stage = (Stage) buttonDeleteClasse.getScene().getWindow();
		Parent root;
		root = FXMLLoader.load(getClass().getResource("../view/ClasseDelete.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	
	
	@FXML
	private void retourAccueil() throws IOException {
		Stage stage;
		stage = (Stage) retourAcceuil.getScene().getWindow();
		Parent root;
		root = FXMLLoader.load(getClass().getResource("../view/Admin.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	
	@FXML
	public void initialize() {
		ConnexionBDD conn = new ConnexionBDD();
		Connection bdd = conn.connexion();
		
		
		ClasseRepository classeR = new ClasseRepository(bdd);
		
		listClasse.getItems().setAll(classeR.getClassesLibelle());
	}

}
