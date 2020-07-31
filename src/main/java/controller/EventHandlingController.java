package controller;

import model.*;
import java.io.IOException;
import java.sql.Connection;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EventHandlingController {
	
	@FXML
	private Button buttonConnect;
	
	@FXML
	private TextField textFieldUser;
	
	@FXML
	private TextField textFieldPassword;
	
	@FXML
	private void initialize() {
	}
	
	
	@FXML
	private void checkUserInfo() throws IOException {
		String user = textFieldUser.getText();
		String password = textFieldPassword.getText();
		Stage stage;
		
		ConnexionBDD conn = new ConnexionBDD();
		Connection bdd = conn.connexion();
		AdminRepository adminR = new AdminRepository(bdd);
		Admin admin = adminR.checkConnection(user, password);
		if(admin != null) {
			stage = (Stage) buttonConnect.getScene().getWindow();
			Parent root;
			root = FXMLLoader.load(getClass().getResource("../view/AdminOverview.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		else {
			EleveRepository eleveR = new EleveRepository(bdd);
			Eleve eleve = eleveR.checkConnection(user, password);
			if(eleve != null) {
				stage = (Stage) buttonConnect.getScene().getWindow();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/EleveOverview.fxml"));
				
				Parent root = loader.load();
				EventStudentController controller = loader.<EventStudentController>getController();
				controller.initData(eleve);
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();				
			}
			else {
				
			}
				
		}
		
	}
	
}
