package controller;

import java.io.IOException;
import java.sql.Connection;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import model.ConnexionBDD;
import model.Eleve;
import model.EleveRepository;

public class EventListStudentController {
	
	@FXML
	private ListView<String> listStudent;
	
	@FXML
	private Button buttonAddStudent;
	
	@FXML
	public void addStudent() throws IOException{
		Stage stage;
		stage = (Stage) buttonAddStudent.getScene().getWindow();
		Parent root;
		root = FXMLLoader.load(getClass().getResource("../view/AddStudent.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML
	public void initialize() {
		ConnexionBDD conn = new ConnexionBDD();
		Connection bdd = conn.connexion();
		
		
		EleveRepository eleveR = new EleveRepository(bdd);
		
		listStudent.getItems().setAll(eleveR.getElevesLibelle());
	}
}
