package controller;

import java.sql.Connection;
import java.time.format.DateTimeFormatter;
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
import model.Eleve;
import model.EleveRepository;

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
			selectClasse.getItems().add(classe.getId()+" "+classe.getDesignation()+" "+classe.getAnnee().substring(0,4));
		}
		
	}
	
	@FXML
	public void createStudent() {
		ConnexionBDD conn = new ConnexionBDD();
		Connection bdd = conn.connexion();
		EleveRepository eleveR = new EleveRepository(bdd);
		Classe classe = new Classe(Integer.parseInt(selectClasse.getValue().split(" ")[0]),"","",null);		
		
		Eleve eleve = new Eleve(0,textNom.getText(),textPrenom.getText(),textDateN.getValue().toString(),classe,textLogin.getText(),textMotDePasse.getText());
		eleveR.addEleve(eleve);
		
	}
	
	

}
