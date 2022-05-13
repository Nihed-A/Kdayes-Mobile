package com.kdayes.entities;

import java.util.Date;

public class Evenement {
    
    private int id;
    private String image;
    private String nom;
    private Categorie categorie;
    private String description;
    private Date date;
    private String lieu;
    private String responsable;
    private int places;
    
    public Evenement() {}

    public Evenement(int id, String image, String nom, Categorie categorie, String description, Date date, String lieu, String responsable, int places) {
        this.id = id;
        this.image = image;
        this.nom = nom;
        this.categorie = categorie;
        this.description = description;
        this.date = date;
        this.lieu = lieu;
        this.responsable = responsable;
        this.places = places;
    }

    public Evenement(String image, String nom, Categorie categorie, String description, Date date, String lieu, String responsable, int places) {
        this.image = image;
        this.nom = nom;
        this.categorie = categorie;
        this.description = description;
        this.date = date;
        this.lieu = lieu;
        this.responsable = responsable;
        this.places = places;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }
    
    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }
    
    public int getPlaces() {
        return places;
    }

    public void setPlaces(int places) {
        this.places = places;
    }
    
    
    @Override
    public String toString() {
        return "Evenement : " +
                "id=" + id
                 + ", Image=" + image
                 + ", Nom=" + nom
                 + ", Categorie=" + categorie
                 + ", Description=" + description
                 + ", Date=" + date
                 + ", Lieu=" + lieu
                 + ", Responsable=" + responsable
                 + ", Places=" + places
                ;
    }
    
}