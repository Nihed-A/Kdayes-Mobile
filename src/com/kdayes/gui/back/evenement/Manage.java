package com.kdayes.gui.back.evenement;


import com.codename1.capture.Capture;
import com.codename1.components.ImageViewer;

import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

import com.kdayes.entities.Categorie;

import com.kdayes.entities.Evenement;
import com.kdayes.services.EvenementService;
import com.kdayes.utils.Statics;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class Manage extends Form {

    
    Resources theme = UIManager.initFirstTheme("/theme");
    String selectedImage;
    boolean imageEdited = false;
    

    Evenement currentEvenement;

    Label imageLabel , nomLabel , categorieLabel , descriptionLabel , dateLabel , lieuLabel , responsableLabel , placesLabel ;
    TextField 
    imageTF , 
    nomTF , 
    categorieTF , 
    descriptionTF , 
    lieuTF , 
    responsableTF , 
    placesTF  ,elemTF;
    PickerComponent dateTF;
    
    public static Categorie selectedCategorie;
    Label selectedCategorieLabel;
    Button selectCategorieButton;
    
    
    ImageViewer imageIV;
    Button selectImageButton;
    
    Button manageButton;

    Form previous;

    public Manage(Form previous) {
        super(ShowAll.currentEvenement == null ?  "Ajouter" :  "Modifier", new BoxLayout(BoxLayout.Y_AXIS));
        this.previous = previous;

        
        selectedCategorie = null;
        
        currentEvenement = ShowAll.currentEvenement;

        addGUIs();
        addActions();

        getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    
    public void refreshCategorie() {
        selectedCategorieLabel.setText(selectedCategorie.getNom());
        selectCategorieButton.setText("Modifier le categorie");
        this.refreshTheme();
    }
    

    private void addGUIs() {
        
        
        
        
        nomLabel = new Label("Nom : ");
        nomLabel.setUIID("labelDefault");
        nomTF = new TextField();
        nomTF.setHint("Tapez le nom");
        
        categorieLabel = new Label("Categorie : ");
        categorieLabel.setUIID("labelDefault");
        categorieTF = new TextField();
        categorieTF.setHint("Tapez le categorie");
        
        descriptionLabel = new Label("Description : ");
        descriptionLabel.setUIID("labelDefault");
        descriptionTF = new TextField();
        descriptionTF.setHint("Tapez le description");
        dateTF = PickerComponent.createDate(null).label("Date");
        
        lieuLabel = new Label("Lieu : ");
        lieuLabel.setUIID("labelDefault");
        lieuTF = new TextField();
        lieuTF.setHint("Tapez le lieu");
        
        responsableLabel = new Label("Responsable : ");
        responsableLabel.setUIID("labelDefault");
        responsableTF = new TextField();
        responsableTF.setHint("Tapez le responsable");
        
        placesLabel = new Label("Places : ");
        placesLabel.setUIID("labelDefault");
        placesTF = new TextField();
        placesTF.setHint("Tapez le places");

        
        categorieLabel = new Label("categorie : ");
        categorieLabel.setUIID("labelDefault");
        selectedCategorieLabel = new Label("null");
        selectCategorieButton = new Button("Choisir categorie");
        selectCategorieButton.addActionListener(l-> new ChooseCategorie(this).show());

        
        imageLabel = new Label("Image : ");
        imageLabel.setUIID("labelDefault");
        selectImageButton = new Button("Ajouter une image");

        if (currentEvenement == null) {
            
            imageIV = new ImageViewer(theme.getImage("default.jpg").fill(1100, 500));
            
            
            manageButton = new Button("Ajouter");
        } else {
    
            
            
            nomTF.setText(currentEvenement.getNom());
            
            descriptionTF.setText(currentEvenement.getDescription());
            dateTF.getPicker().setDate(currentEvenement.getDate());
            lieuTF.setText(currentEvenement.getLieu());
            responsableTF.setText(currentEvenement.getResponsable());
            placesTF.setText(String.valueOf(currentEvenement.getPlaces()));
            selectedCategorie = currentEvenement.getCategorie();

            categorieLabel = new Label("categorie : ");
            categorieLabel.setUIID("labelDefault");
            selectedCategorieLabel = new Label("null");
            selectedCategorieLabel.setText(selectedCategorie.getNom());
            selectCategorieButton.setText("Modifier le categorie");
            
            
            if (currentEvenement.getImage() != null) {
                selectedImage = currentEvenement.getImage();
                String url = Statics.EVENEMENT_IMAGE_URL + currentEvenement.getImage();
                Image image = URLImage.createToStorage(
                        EncodedImage.createFromImage(theme.getImage("default.jpg").fill(1100, 500), false),
                        url,
                        url,
                        URLImage.RESIZE_SCALE
                );
                imageIV = new ImageViewer(image);
            } else {
                imageIV = new ImageViewer(theme.getImage("default.jpg").fill(1100, 500));
            }
            imageIV.setFocusable(false);
            

            manageButton = new Button("Modifier");
        }
        manageButton.setUIID("buttonMain");

        Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        container.setUIID("containerRounded");

        container.addAll(
            imageLabel, imageIV,
            selectImageButton,
            
            nomLabel, nomTF,
            
            descriptionLabel, descriptionTF,
            dateTF,
            lieuLabel, lieuTF,
            responsableLabel, responsableTF,
            placesLabel, placesTF,
            categorieLabel, selectedCategorieLabel, selectCategorieButton,
            manageButton
        );

        this.addAll(container);
    }

    private void addActions() {
        
        selectImageButton.addActionListener(a -> {
            selectedImage = Capture.capturePhoto(900, -1);
            try {
                imageEdited = true;
                imageIV.setImage(Image.createImage(selectedImage));
            } catch (IOException e) {
                e.printStackTrace();
            }
            selectImageButton.setText("Modifier l'image");
        });
        
        if (currentEvenement == null) {
            manageButton.addActionListener(action -> {
                if (controleDeSaisie()) {
                    int responseCode = EvenementService.getInstance().add(
                            new Evenement(
                                    
                                    
                                    selectedImage,
                                    nomTF.getText(),
                                    selectedCategorie,
                                    descriptionTF.getText(),
                                    dateTF.getPicker().getDate(),
                                    lieuTF.getText(),
                                    responsableTF.getText(),
                                    (int) Float.parseFloat(placesTF.getText())
                            )
                    );
                    if (responseCode == 200) {
                        Dialog.show("Succés", "Evenement ajouté avec succes", new Command("Ok"));
                        showBackAndRefresh();
                    } else {
                        Dialog.show("Erreur", "Erreur d'ajout de evenement. Code d'erreur : " + responseCode, new Command("Ok"));
                    }
                }
            });
        } else {
            manageButton.addActionListener(action -> {
                if (controleDeSaisie()) {
                    int responseCode = EvenementService.getInstance().edit(
                            new Evenement(
                                    currentEvenement.getId(),
                                    
                                    
                                    selectedImage,
                                    nomTF.getText(),
                                    selectedCategorie,
                                    descriptionTF.getText(),
                                    dateTF.getPicker().getDate(),
                                    lieuTF.getText(),
                                    responsableTF.getText(),
                                    (int) Float.parseFloat(placesTF.getText())

                            ), imageEdited
                    );
                    if (responseCode == 200) {
                        Dialog.show("Succés", "Evenement modifié avec succes", new Command("Ok"));
                        showBackAndRefresh();
                    } else {
                        Dialog.show("Erreur", "Erreur de modification de evenement. Code d'erreur : " + responseCode, new Command("Ok"));
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
        
        
        
        
        
        
        
        if (descriptionTF.getText().equals("")) {
            Dialog.show("Avertissement", "Veuillez saisir le description", new Command("Ok"));
            return false;
        }
        
        
        
        
        
        
        if (dateTF.getPicker().getDate() == null) {
            Dialog.show("Avertissement", "Veuillez saisir la date", new Command("Ok"));
            return false;
        }
        
        
        if (lieuTF.getText().equals("")) {
            Dialog.show("Avertissement", "Veuillez saisir le lieu", new Command("Ok"));
            return false;
        }
        
        
        
        
        if (responsableTF.getText().equals("")) {
            Dialog.show("Avertissement", "Veuillez saisir le responsable", new Command("Ok"));
            return false;
        }
        
        
        
        
        if (placesTF.getText().equals("")) {
            Dialog.show("Avertissement", "Veuillez saisir le places", new Command("Ok"));
            return false;
        }
        try {
            Float.parseFloat(placesTF.getText());
        } catch (NumberFormatException e) {
            Dialog.show("Avertissement", placesTF.getText() + " n'est pas un nombre valide (places)", new Command("Ok"));
            return false;
        }
        
        
        

        
        if (selectedCategorie == null) {
            Dialog.show("Avertissement", "Veuillez choisir un categorie", new Command("Ok"));
            return false;
        }
        

        
        if (selectedImage == null) {
            Dialog.show("Avertissement", "Veuillez choisir une image", new Command("Ok"));
            return false;
        }
        
             
        return true;
    }
}