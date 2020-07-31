package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EventAddClasseController {
	
	@FXML
	private Button buttonSave;

	@FXML
	private Button buttonAnnuler;
	
	@FXML
	private TextField fieldDesignation;

	@FXML
	private DatePicker fieldAnnees;

	
	@FXML
	private void retourAllClasses() throws IOException {
		Stage stage;
		stage = (Stage) buttonAnnuler.getScene().getWindow();
		Parent root;
		root = FXMLLoader.load(getClass().getResource("../view/ClasseList.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML
	private void saveClasse() throws IOException {
		
	}

}
