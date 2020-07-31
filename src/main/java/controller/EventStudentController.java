package controller;



import javafx.fxml.FXML;
import javafx.scene.text.Text;
import model.Eleve;

public class EventStudentController {
	
	@FXML
	private Text textNom;
	
	@FXML
	private Text textPrenom;
	
	@FXML
	private Text textAge;
	
	@FXML
	private Text textDateN;
	
	@FXML
	private Text textClasse;
	
	
	@FXML
	private void initialize() {
	}
	
	public void initData(Eleve eleve) {
		this.textNom.setText(eleve.getNom());
		this.textPrenom.setText(eleve.getPrenom());
		
		//this.textAge.setText();
		this.textDateN.setText(eleve.getDateN());
		this.textClasse.setText(eleve.getClasse().getDesignation()+' '+eleve.getClasse().getAnnee().substring(0,4));
	}
}

