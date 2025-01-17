package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Admin;

public class EventAdminController {	

	@FXML
	private Button buttonStudent;
	
	@FXML
	private Button buttonClasses;
	
	@FXML
	private Text textTitle;
	
	@FXML
	private void seeClasses() throws IOException{
		Stage stage;
		stage = (Stage) buttonStudent.getScene().getWindow();
		Parent root;
		root = FXMLLoader.load(getClass().getResource("../view/ClasseList.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	
	void initialize() {
		
	}
	
	
	
	@FXML
	private void seeStudent() throws IOException{
		Stage stage;
		stage = (Stage) buttonStudent.getScene().getWindow();
		Parent root;
		root = FXMLLoader.load(getClass().getResource("../view/StudentList.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();				
	}
	
	
	public void initData(Admin admin) {
		
	}

}
