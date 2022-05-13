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
public class Projet  {
    public int id;
    public String nom_projet;
    public String date_debut;
    public String date_fin;
    public int montant_demandee;
    public int montant_collecte;
    public String etat_projet;
    public String content;
    public String image;
    public Projetcategorie categories;
 
     public Projet(){
     }

    public Projet(int id, String nom_projet, String date_debut, String date_fin, int montant_demandee, int montant_collecte, String etat_projet, String content,  String image, Projetcategorie categories ) {
        this.id = id;
        this.nom_projet = nom_projet;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.montant_demandee = montant_demandee;
        this.montant_collecte = montant_collecte;
        this.etat_projet = etat_projet;
        this.content = content;
        this.image= image;
        this.categories=categories;
    }

    public Projet(String nom_projet,  String date_debut, String date_fin, int montant_demandee, int montant_collecte, String etat_projet, String content, String image, Projetcategorie categories ) {
        this.nom_projet = nom_projet; 
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.montant_demandee = montant_demandee;
        this.montant_collecte = montant_collecte;
        this.etat_projet = etat_projet;
        this.content = content;
        this.image= image;
         this.categories=categories;
    }

    public Projet(String valueOf, int parseInt, int valueOf2, String valueOf3) {
        this.nom_projet = valueOf; 
        //this.date_debut = valueOf0;
        //this.date_fin = valueOf1;
        this.montant_demandee = parseInt;
        this.montant_collecte = valueOf2;
        this.content = valueOf3;
    }

   

  

    public int getId() {
        return id;
    }

    public String getNom_projet() {
        return nom_projet;
    }

   
    public Projetcategorie getCategories() {
        return categories;
    }


    public String getDate_debut() {
        return date_debut;
    }

    public String getDate_fin() {
        return date_fin;
    }

    public int getMontant_demandee() {
        return montant_demandee;
    }

    public int getMontant_collecte() {
        return montant_collecte;
    }

    public String getEtat_projet() {
        return etat_projet;
    }

    public String getContent() {
        return content;
    }
     public String getImage() {
        return image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom_projet(String nom_projet) {
        this.nom_projet = nom_projet;
    }

   

    public void setDate_debut(String date_debut) {
        this.date_debut = date_debut;
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }

    public void setMontant_demandee(int montant_demandee) {
        this.montant_demandee = montant_demandee;
    }

    public void setMontant_collecte(int montant_collecte) {
        this.montant_collecte = montant_collecte;
    }

    public void setEtat_projet(String etat_projet) {
        this.etat_projet = etat_projet;
    }

    public void setContent(String content) {
        this.content = content;
    }
      public void setImage(String image) {
        this.image = image;
    }
      
     public void setCategories(Projetcategorie categories) {
        this.categories = categories;
    }  

    @Override
    public String toString() {
        return "Projet{" + "id=" + id + ", nom_projet=" + nom_projet + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", montant_demandee=" + montant_demandee + ", montant_collecte=" + montant_collecte + ", etat_projet=" + etat_projet + ", content=" + content + ", image=" + image +  ", categories=" +categories+ '}';
    }

    
}
