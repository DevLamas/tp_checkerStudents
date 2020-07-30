package model;

public class Classe {
	private int id;
	private String annee;
	private String designation;
	private Ecole ecole;
	
	public Classe(int id, String annee, String designation, Ecole ecole) {
		this.id = id;
		this.annee = annee;
		this.designation = designation;
		this.ecole = ecole;
	}

	public String getAnnee() {
		return annee;
	}

	public void setAnnee(String annee) {
		this.annee = annee;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Ecole getEcole() {
		return ecole;
	}

	public void setEcole(Ecole ecole) {
		this.ecole = ecole;
	}

	public int getId() {
		return id;
	}
	
	
}
