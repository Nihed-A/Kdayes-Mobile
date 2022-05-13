/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import entities.Projet;
import services.projetServices;

/**
 *
 * @author dell
 */
public class ModifierProjet extends Form{
    Form current;
    public ModifierProjet(Projet p){
        setTitle("Modifier le projet");
       
        
        TextField nom_projet = new TextField(""+p.getNom_projet());
        //TextField date_debut = new TextField("","date_debut");
        //TextField date_fin = new TextField("","date_fin");
        TextField montant_demandee = new TextField(""+p.getMontant_demandee());
        TextField montant_collecte = new TextField(""+p.getMontant_collecte());
        TextField content = new TextField(""+p.getContent());
        
        
        
        
        Button btnU = new Button("Modifier");
        btnU.setUIID("Button");
        btnU.addPointerPressedListener(l->{
         
         p.setNom_projet(nom_projet.getText().toString());         
         p.setMontant_demandee((int)Integer.parseInt(montant_demandee.getText().toString()));
         p.setMontant_collecte((int)Integer.parseInt(montant_collecte.getText().toString()));
         p.setContent(content.getText().toString());
            System.out.println(p);
     if(projetServices.getInstance().modifierProjet(p))
     {
         new HomeForm().show();
     }
     
     });
       
        Label l1= new Label();
        Label l2= new Label();
        Label l3= new Label();
        Label l4= new Label();
        Label l5= new Label();
        
        Container c = BoxLayout.encloseY(
                l1,l2,l3,l4,l5,
                
                new FloatingHint(nom_projet),
                new FloatingHint(montant_demandee),
                new FloatingHint(montant_collecte),
                new FloatingHint(content),
                btnU
        );
                
        
        
        
        add(c);
        show();
    
    }
    
}
