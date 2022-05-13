package com.kdayes.gui.back.evenement;

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
    Button addBtn;

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
        addBtn = new Button("Ajouter");
        addBtn.setUIID("buttonWhiteCenter");
        this.add(addBtn);
        

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
        addBtn.addActionListener(action -> {
            currentEvenement = null;
            new Manage(this).show();
        });
        
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
    
    Button editBtn, deleteBtn;
    Container btnsContainer;

    private Component makeEvenementModel(Evenement evenement) {

        Container evenementModel = makeModelWithoutButtons(evenement);

        btnsContainer = new Container(new BorderLayout());
        btnsContainer.setUIID("containerButtons");
        
        editBtn = new Button("Modifier");
        editBtn.setUIID("buttonMain");
        editBtn.addActionListener(action -> {
            currentEvenement = evenement;
            new Manage(this).show();
        });

        deleteBtn = new Button("Supprimer");
        deleteBtn.setUIID("buttonDanger");
        deleteBtn.addActionListener(action -> {
            InteractionDialog dlg = new InteractionDialog("Confirmer la suppression");
            dlg.setLayout(new BorderLayout());
            dlg.add(BorderLayout.CENTER, new Label("Voulez vous vraiment supprimer ce evenement ?"));
            Button btnClose = new Button("Annuler");
            btnClose.addActionListener((ee) -> dlg.dispose());
            Button btnConfirm = new Button("Confirmer");
            btnConfirm.addActionListener(actionConf -> {
                int responseCode = EvenementService.getInstance().delete(evenement.getId());

                if (responseCode == 200) {
                    currentEvenement = null;
                    dlg.dispose();
                    evenementModel.remove();
                    this.refreshTheme();
                } else {
                    Dialog.show("Erreur", "Erreur de suppression du evenement. Code d'erreur : " + responseCode, new Command("Ok"));
                }
            });
            Container btnContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
            btnContainer.addAll(btnClose, btnConfirm);
            dlg.addComponent(BorderLayout.SOUTH, btnContainer);
            dlg.show(800, 800, 0, 0);
        });

        btnsContainer.add(BorderLayout.WEST, editBtn);
        btnsContainer.add(BorderLayout.EAST, deleteBtn);
        
        
        evenementModel.add(btnsContainer);

        return evenementModel;
    }
    
}