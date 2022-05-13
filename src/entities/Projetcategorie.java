/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author dell
 */
public class Projetcategorie {
    private int id; 
    private String nom_categorie_projet;

    public Projetcategorie(int id, String nom_categorie_projet) {
        this.id = id;
        this.nom_categorie_projet = nom_categorie_projet;
    }

    public Projetcategorie(String nom_categorie_projet) {
        this.nom_categorie_projet = nom_categorie_projet;
    }

    public int getId() {
        return id;
    }

    public String getNom_categorie_projet() {
        return nom_categorie_projet;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom_categorie_projet(String nom_categorie_projet) {
        this.nom_categorie_projet = nom_categorie_projet;
    }

    @Override
    public String toString() {
        return "Projetcategorie{" + "id=" + id + ", nom_categorie_projet=" + nom_categorie_projet + '}';
    }
    
}
