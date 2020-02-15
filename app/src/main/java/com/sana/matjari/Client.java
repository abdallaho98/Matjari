package com.sana.matjari;


public class Client {
    public String nomUtilisateur;
    public String mdp;
    public String nom;
    public String prenom;
    public int numero;
    public String mail;
    public String adresse;

    public Client(String nomUtilisateur, String mdp, String nom, String prenom, int numero, String mail, String adresse) {
        this.nomUtilisateur = nomUtilisateur;
        this.mdp = mdp;
        this.nom = nom;
        this.prenom = prenom;
        this.numero = numero;
        this.mail = mail;
        this.adresse = adresse;
    }
}
