package model;

public class Ecole {
	private int id;
	private String nom;
	private Admin admin;
	
	public Ecole(int id, String nom, Admin admin) {
		this.id = id;
		this.nom = nom;
		this.admin = admin;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Admin getAdmin() {
		return admin;
	}


	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Ecole [id=" + id + ", nom=" + nom + ", admin=" + admin + "]";
	}
	

}
