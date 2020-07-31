package controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.Classe;
import model.ClasseRepository;
import model.ConnexionBDD;

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
		ConnexionBDD conn = new ConnexionBDD();
		Connection bdd = conn.connexion();
		ClasseRepository classeR = new ClasseRepository(bdd);
		ArrayList<Classe> classes = classeR.getClasses();
		for(Classe classe : classes) {
			selectClasse.getItems().add(classe.getId()+" - "+classe.getDesignation()+" "+classe.getAnnee());
		}
		
	}
	
	@FXML
	public void createStudent() {
		String nom = textNom.getText();
		String prenom = textPrenom.getText();
		//String dateN = textDateN.getValue();
		String classe = selectClasse.getValue();
		String login = textLogin.getText();
		String mdp = textMotDePasse.getText();
		//System.out.println(nom + prenom + dateN + classe +  login + mdp );
		
		
	}
	
	

}
