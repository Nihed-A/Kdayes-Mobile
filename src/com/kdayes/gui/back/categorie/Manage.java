package com.kdayes.gui.back.categorie;


import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

import com.kdayes.entities.Categorie;
import com.kdayes.services.CategorieService;
import com.kdayes.utils.Statics;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class Manage extends Form {

    

    Categorie currentCategorie;

    Label nomLabel ;
    TextField 
    nomTF  ,elemTF;
    
    
    
    Button manageButton;

    Form previous;

    public Manage(Form previous) {
        super(ShowAll.currentCategorie == null ?  "Ajouter" :  "Modifier", new BoxLayout(BoxLayout.Y_AXIS));
        this.previous = previous;

        
        currentCategorie = ShowAll.currentCategorie;

        addGUIs();
        addActions();

        getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    

    private void addGUIs() {
        
        
        nomLabel = new Label("Nom : ");
        nomLabel.setUIID("labelDefault");
        nomTF = new TextField();
        nomTF.setHint("Tapez le nom");

        

        

        if (currentCategorie == null) {
            
            
            manageButton = new Button("Ajouter");
        } else {
    
            
            nomTF.setText(currentCategorie.getNom());
            
            

            manageButton = new Button("Modifier");
        }
        manageButton.setUIID("buttonMain");

        Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        container.setUIID("containerRounded");

        container.addAll(
            
            nomLabel, nomTF,
            
            manageButton
        );

        this.addAll(container);
    }

    private void addActions() {
        
        if (currentCategorie == null) {
            manageButton.addActionListener(action -> {
                if (controleDeSaisie()) {
                    int responseCode = CategorieService.getInstance().add(
                            new Categorie(
                                    
                                    
                                    nomTF.getText()
                            )
                    );
                    if (responseCode == 200) {
                        Dialog.show("Succés", "Categorie ajouté avec succes", new Command("Ok"));
                        showBackAndRefresh();
                    } else {
                        Dialog.show("Erreur", "Erreur d'ajout de categorie. Code d'erreur : " + responseCode, new Command("Ok"));
                    }
                }
            });
        } else {
            manageButton.addActionListener(action -> {
                if (controleDeSaisie()) {
                    int responseCode = CategorieService.getInstance().edit(
                            new Categorie(
                                    currentCategorie.getId(),
                                    
                                    
                                    nomTF.getText()

                            )
                    );
                    if (responseCode == 200) {
                        Dialog.show("Succés", "Categorie modifié avec succes", new Command("Ok"));
                        showBackAndRefresh();
                    } else {
                        Dialog.show("Erreur", "Erreur de modification de categorie. Code d'erreur : " + responseCode, new Command("Ok"));
                    }
                }
            });
        }
    }

    private void showBackAndRefresh(){
        ((ShowAll) previous).refresh();
        previous.showBack();
    }

    private boolean controleDeSaisie() {

        
        
        if (nomTF.getText().equals("")) {
            Dialog.show("Avertissement", "Veuillez saisir le nom", new Command("Ok"));
            return false;
        }
        
        
        

        

        
             
        return true;
    }
}