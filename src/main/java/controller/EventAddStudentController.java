package controller;

import java.util.HashMap;
import java.util.List;

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
	private ChoiceBox<String> selectClasse;
	
	@FXML
	private TextField textLogin;
	
	@FXML
	private TextField textMotDePasse;
	
	@FXML
	private Button buttonValider;
	
	
	@FXML
	public void initialize() {
		selectClasse.getItems().add("RIL");
		selectClasse.getItems().add("RISR");
	}
	
	@FXML
	public void createStudent() {
		String nom = textNom.getText();
		String prenom = textPrenom.getText();
		String dateN = textDateN.getValue().toString();
		String classe = selectClasse.getValue();
		String login = textLogin.getText();
		String mdp = textMotDePasse.getText();
		System.out.println(nom + prenom + dateN + classe +  login + mdp );
		
		
	}
	
	

}
