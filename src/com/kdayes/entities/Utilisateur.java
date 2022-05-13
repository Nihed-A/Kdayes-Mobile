package com.kdayes.entities;

public class Utilisateur {

    private int id;
    private String email;
    private String roles;
    private String password;
    private String nom;
    private int telephone;
    private float solde;

    public Utilisateur() {
    }

    public Utilisateur(int id, String email, String roles, String password, String nom, int telephone, float solde) {
        this.id = id;
        this.email = email;
        this.roles = roles;
        this.password = password;
        this.nom = nom;
        this.telephone = telephone;
        this.solde = solde;
    }

    public Utilisateur(String email, String roles, String password, String nom, int telephone, float solde) {
        this.email = email;
        this.roles = roles;
        this.password = password;
        this.nom = nom;
        this.telephone = telephone;
        this.solde = solde;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public float getSolde() {
        return solde;
    }

    public void setSolde(float solde) {
        this.solde = solde;
    }

}