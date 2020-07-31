package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class EventAddStudentController {
	
	@FXML
	private TextField textNom;
	
	@FXML
	private TextField textPrenom;
	
	@FXML
	private DatePicker textDateN;
	
	@FXML
	private ChoiceBox<String> selectClass;
	
	@FXML
	private TextField textLogin;
	
	@FXML
	private TextField textMotDePasse;
	
	@FXML
	private Button buttonValider;
	
	

}
