package com.kdayes.gui.front.evenement;

import com.codename1.components.*;
import com.codename1.ui.*;
import com.codename1.ui.layouts.*;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.*;
import com.codename1.io.*;
import com.kdayes.entities.Evenement;
import com.kdayes.services.EvenementService;
import com.kdayes.utils.Statics;

import java.text.SimpleDateFormat;
import java.io.*;
import java.util.ArrayList;

public class ShowAll extends Form {

    Form previous; 
    
    Resources theme = UIManager.initFirstTheme("/theme");
    
    public static Evenement currentEvenement = null;
    

    TextField searchTF;
    ArrayList<Component> componentModels;
    
    public ShowAll(Form previous) {
        super("Evenements", new BoxLayout(BoxLayout.Y_AXIS));
        this.previous = previous;

        addGUIs();
        addActions();

        super.getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    public void refresh() {
        this.removeAll();
        addGUIs();
        addActions();
        this.refreshTheme();
    }

    private void addGUIs() {
        

        ArrayList<Evenement> listEvenements = EvenementService.getInstance().getAll();
        componentModels = new ArrayList<>();
        
        searchTF = new TextField("", "Chercher evenement par Nom");
        searchTF.addDataChangedListener((d, t) -> {
            if (componentModels.size() > 0) {
                for (Component componentModel : componentModels) {
                    this.removeComponent(componentModel);
                }
            }
            componentModels = new ArrayList<>();
            for (Evenement evenement : listEvenements) {
                if (evenement.getNom().toLowerCase().startsWith(searchTF.getText().toLowerCase())) {
                    Component model = makeEvenementModel(evenement);
                    this.add(model);
                    componentModels.add(model);
                }
            }
            this.revalidate();
        });
        this.add(searchTF);
        
        if (listEvenements.size() > 0) {
            for (Evenement evenement : listEvenements) {
                Component model = makeEvenementModel(evenement);
                this.add(model);
                componentModels.add(model);
            }
        } else {
            this.add(new Label("Aucune donnée"));
        }
    }
    private void addActions() {
        
    }
    Label imageLabel   , nomLabel   , categorieLabel   , descriptionLabel   , dateLabel   , lieuLabel   , responsableLabel   , placesLabel  ;
    
    ImageViewer imageIV;
    

    private Container makeModelWithoutButtons(Evenement evenement) {
        Container evenementModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        evenementModel.setUIID("containerRounded");
        
        
        imageLabel = new Label("Image : " + evenement.getImage());
        imageLabel.setUIID("labelDefault");
        
        nomLabel = new Label("Nom : " + evenement.getNom());
        nomLabel.setUIID("labelDefault");
        
        
        descriptionLabel = new Label("Description : " + evenement.getDescription());
        descriptionLabel.setUIID("labelDefault");
        
        dateLabel = new Label("Date : " + new SimpleDateFormat("dd-MM-yyyy").format(evenement.getDate()));
        dateLabel.setUIID("labelDefault");
        
        lieuLabel = new Label("Lieu : " + evenement.getLieu());
        lieuLabel.setUIID("labelDefault");
        
        responsableLabel = new Label("Responsable : " + evenement.getResponsable());
        responsableLabel.setUIID("labelDefault");
        
        placesLabel = new Label("Places : " + evenement.getPlaces());
        placesLabel.setUIID("labelDefault");
        
        categorieLabel = new Label("Categorie : " + evenement.getCategorie().getNom());
        categorieLabel.setUIID("labelDefault");
        
        if (evenement.getImage() != null) {
            String url = Statics.EVENEMENT_IMAGE_URL + evenement.getImage();
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

        evenementModel.addAll(
                imageIV,
                nomLabel, categorieLabel, descriptionLabel, dateLabel, lieuLabel, responsableLabel, placesLabel
        );

        return evenementModel;
    }
    
    
    Container btnsContainer;

    private Component makeEvenementModel(Evenement evenement) {

        Container evenementModel = makeModelWithoutButtons(evenement);

        btnsContainer = new Container(new BorderLayout());
        btnsContainer.setUIID("containerButtons");
        
        
        Button btnAfficherScreenshot = new Button("Partager");
        btnAfficherScreenshot.addActionListener(listener -> share(evenement));

        btnsContainer.add(BorderLayout.CENTER, btnAfficherScreenshot);
        
        evenementModel.add(btnsContainer);

        return evenementModel;
    }
    
    private void share(Evenement evenement) {
        Form form = new Form(new BoxLayout(BoxLayout.Y_AXIS));
        form.add(new Label("Evenement " + evenement.getNom()));
        form.add(makeModelWithoutButtons(evenement));
        String imageFile = FileSystemStorage.getInstance().getAppHomePath() + "screenshot.png";
        Image screenshot = Image.createImage(
                com.codename1.ui.Display.getInstance().getDisplayWidth(),
                com.codename1.ui.Display.getInstance().getDisplayHeight()
        );
        form.revalidate();
        form.setVisible(true);
        form.paintComponent(screenshot.getGraphics(), true);
        form.removeAll();
        try (OutputStream os = FileSystemStorage.getInstance().openOutputStream(imageFile)) {
            ImageIO.getImageIO().save(screenshot, os, ImageIO.FORMAT_PNG, 1);
        } catch (IOException err) {
            Log.e(err);
        }
        Form screenShotForm = new Form("Partager evenement", new BoxLayout(BoxLayout.Y_AXIS));
        ImageViewer screenshotViewer = new ImageViewer(screenshot.fill(1000, 2000));
        screenshotViewer.setFocusable(false);
        screenshotViewer.setUIID("screenshot");
        ShareButton btnPartager = new ShareButton();
        btnPartager.setText("Partager ");
        btnPartager.setTextPosition(LEFT);
        btnPartager.setImageToShare(imageFile, "image/png");
        btnPartager.setTextToShare(evenement.toString());
        screenShotForm.addAll(screenshotViewer, btnPartager);
        screenShotForm.getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        screenShotForm.show();
    }
    
}