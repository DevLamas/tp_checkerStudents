package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class EventHandlingController {
	
	@FXML
	private Button buttonConnect;
	
	@FXML
	private TextField textFieldUser;
	
	@FXML
	private TextField textFieldPassword;
	
	@FXML
	private Text textError;
	
	@FXML
	private void initialize() {
	}
	
	
	@FXML
	private void checkUserInfo() throws IOException {
		String user = textFieldUser.getText();
		String password = textFieldPassword.getText();
		Stage stage;
		stage = (Stage) buttonConnect.getScene().getWindow();
		Parent root;
		root = FXMLLoader.load(getClass().getResource("../view/Admin.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
			
		textError.setText("Erreur lors de la connexion");
		
	}
	
}
