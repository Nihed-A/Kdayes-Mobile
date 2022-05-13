/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

import java.util.Date;



/**
 *
 * @author YOUSSEF
 */
public class Article {
    int id ;
    String Auteur ;
    String Titre;
    String Contenu ;
    String date;
     private String nomcat;

    public Article(int id, String Auteur, String Titre, String Contenu, String date, String nomcat, String Image, Integer Cat_id) {
        this.id = id;
        this.Auteur = Auteur;
        this.Titre = Titre;
        this.Contenu = Contenu;
        this.date = date;
        this.nomcat = nomcat;
        this.Image = Image;
        this.Cat_id = Cat_id;
    }

    public String getNomcat() {
        return nomcat;
    }

    public void setNomcat(String nomcat) {
        this.nomcat = nomcat;
    }

    public Article(int id, String Auteur, String Titre, String Contenu, String date, String Image, Integer Cat_id) {
        this.id = id;
        this.Auteur = Auteur;
        this.Titre = Titre;
        this.Contenu = Contenu;
        this.date = date;
        this.Image = Image;
        this.Cat_id = Cat_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    String Image ;
    Integer Cat_id ;

    public Article(int id, String Auteur, String Titre, String Contenu, String Image, Integer Cat_id) {
        this.id = id;
        this.Auteur = Auteur;
        this.Titre = Titre;
        this.Contenu = Contenu;
        this.Image = Image;
        this.Cat_id = Cat_id;
    }

    public Article() {
    }

    public Article(String Auteur, String Titre, String Contenu, String Image, Integer Cat_id) {
        this.Auteur = Auteur;
        this.Titre = Titre;
        this.Contenu = Contenu;
       
        this.Image = Image;
        this.Cat_id = Cat_id;
    }

    

    public String getAuteur() {
        return Auteur;
    }

    public void setAuteur(String Auteur) {
        this.Auteur = Auteur;
    }

    public String getTitre() {
        return Titre;
    }

    public void setTitre(String Titre) {
        this.Titre = Titre;
    }

    public String getContenu() {
        return Contenu;
    }

    public void setContenu(String Contenu) {
        this.Contenu = Contenu;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public Integer getCat_id() {
        return Cat_id;
    }

    public void setCat_id(Integer Cat_id) {
        this.Cat_id = Cat_id;
    }

    @Override
    public String toString() {
        return "Article{" + "id=" + id + ", Auteur=" + Auteur + ", Titre=" + Titre + ", Contenu=" + Contenu + ", date=" + date + ", nomcat=" + nomcat + ", Image=" + Image + ", Cat_id=" + Cat_id + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

 
    
  
  
   
    
   
}
