/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import entities.Projet;
import services.projetServices;

/**
 *
 * @author dell
 */
public class AjoutProjet extends Form{
    public AjoutProjet(Form previous) {
        setTitle("Ajouter un nouveau projet");
       
        
        TextField nom_projet = new TextField("","nom_projet");
        //TextField date_debut = new TextField("","date_debut");
        //TextField date_fin = new TextField("","date_fin");
        TextField montant_demandee = new TextField("","montant_demandee");
        TextField montant_collecte = new TextField("","montant_collecte");
        TextField content = new TextField("","content");
       
        
        Button btnValider = new Button("Add");
        
        btnValider.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                
                if ((nom_projet.getText().length()==0)||(montant_demandee.getText().length()==0)||(montant_collecte.getText().length()==0)||(content.getText().length()==0))
                { Dialog.show("Alert", "Please fill all the fields", new Command("OK"));}
                else{
            InfiniteProgress ip = new InfiniteProgress();
            final Dialog idialog = ip.showInfiniteBlocking();
            Projet p = new Projet(
                    String.valueOf(nom_projet.getText()),
                    Integer.parseInt(montant_demandee.getText()),
                    Integer.parseInt(montant_collecte.getText()),
                    String.valueOf(content.getText())
                    );
                    System.err.println("data projet=="+p);
                    projetServices.getInstance().AjouterProjet(p);
                    idialog.dispose();
                    Dialog.show("Succes", "Projet Added", new Command("OK"));
                    
                  
               
            
                   
            }
        }
        });
        addAll(nom_projet,montant_demandee,montant_collecte,content,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());
    }
    
}
