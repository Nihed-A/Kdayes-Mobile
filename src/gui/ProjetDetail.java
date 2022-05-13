/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import gui.HomeForm;
import entities.Projet;
import java.util.ArrayList;
import services.projetServices;

/**
 *
 * @author dell
 */
public class ProjetDetail extends Form{
    Form current;
    ArrayList<Projet> data = new ArrayList<>();

     public ProjetDetail(Projet projet,Form previous){
        current =this;
        Container y = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Label id = new Label(""+ projet.getId());
                Label nom_projet = new Label( projet.getNom_projet());
                Label date_debut = new Label(projet.getDate_debut());
                Label date_fin = new Label(projet.getDate_fin());
                Label montant_demandee = new Label(""+projet.getMontant_demandee());
                Label montant_collecte = new Label("  "+ projet.getMontant_collecte());
                Label content = new Label(projet.getContent());
        
        y.addAll( nom_projet,date_debut,date_fin,montant_demandee,montant_collecte,content);
     
         addAll(y);
     }
           
    
}
