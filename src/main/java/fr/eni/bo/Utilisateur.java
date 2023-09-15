package fr.eni.bo;

import java.util.ArrayList;
import java.util.List;

public class Utilisateur {
	
	// Attributs
	private int noUtilisateur;
	private String pseudo;
	private String nom;
	private String prenom;
	private String email;
	private String telephone;
	private String rue;
	private String codePostal;
	private String ville;
	private String motDePasse;
	private int credit;
	private int administrateur;
	
	private List<ArticleVendu> achat = new ArrayList<ArticleVendu>();
	private List<ArticleVendu> vente = new ArrayList<ArticleVendu>();
	private List<Enchere> encherir = new ArrayList<Enchere>();
	
	
	
	//constructeur
	public Utilisateur(int noUtilisateur, String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String codePostal, String ville, String motDePasse, int credit, int administrateur) {
		super();
		this.noUtilisateur = noUtilisateur;
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.motDePasse = motDePasse;
		this.credit = credit;
		this.administrateur = administrateur;
	}



	public Utilisateur(String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String codePostal, String ville, String motDePasse, int credit, int administrateur) {
		super();
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.motDePasse = motDePasse;
		this.credit = credit;
		this.administrateur = administrateur;
	}

	
	// toString
	@Override
	public String toString() {
		return "Utilisateur [noUtilisateur=" + noUtilisateur + ", pseudo=" + pseudo + ", nom=" + nom + ", prenom="
				+ prenom + ", email=" + email + "]";
	}

	
	
	
	// Getter et Setter

	


	public int getNoUtilisateur() {
		return noUtilisateur;
	}



	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}



	public String getPseudo() {
		return pseudo;
	}



	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}



	public String getNom() {
		return nom;
	}



	public void setNom(String nom) {
		this.nom = nom;
	}



	public String getPrenom() {
		return prenom;
	}



	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getTelephone() {
		return telephone;
	}



	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}



	public String getRue() {
		return rue;
	}



	public void setRue(String rue) {
		this.rue = rue;
	}



	public String getCodePostal() {
		return codePostal;
	}



	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}



	public String getVille() {
		return ville;
	}



	public void setVille(String ville) {
		this.ville = ville;
	}



	public String getMotDePasse() {
		return motDePasse;
	}



	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}



	public int getCredit() {
		return credit;
	}



	public void setCredit(int credit) {
		this.credit = credit;
	}



	public int getAdministrateur() {
		return administrateur;
	}



	public void setAdministrateur(int administrateur) {
		this.administrateur = administrateur;
	}



	public List<ArticleVendu> getAchat() {
		return achat;
	}



	public void setAchat(List<ArticleVendu> achat) {
		this.achat = achat;
	}



	public List<ArticleVendu> getVente() {
		return vente;
	}



	public void setVente(List<ArticleVendu> vente) {
		this.vente = vente;
	}



	public List<Enchere> getEncherir() {
		return encherir;
	}



	public void setEncherir(List<Enchere> encherir) {
		this.encherir = encherir;
	}
	
	
	
	
}
