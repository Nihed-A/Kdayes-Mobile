package com.kdayes.gui.front.utilisateur;

import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.kdayes.MainApp;
import com.kdayes.entities.Utilisateur;

public class MyProfile extends Form {


    public static Utilisateur currentUtilisateur = null;
    Button editProfileBtn;
    Label emailLabel, rolesLabel, passwordLabel, nomLabel, telephoneLabel, soldeLabel;

    public MyProfile(Form previous) {
        super("Mon profil", new BoxLayout(BoxLayout.Y_AXIS));

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

        currentUtilisateur = MainApp.getSession();

        editProfileBtn = new Button("Update my profile");
        editProfileBtn.setUIID("buttonWhiteCenter");

        this.add(editProfileBtn);

        this.add(makeUtilisateurModel(currentUtilisateur));
    }

    private void addActions() {
        editProfileBtn.addActionListener(action -> {
            currentUtilisateur = null;
            new EditProfile(this).show();
        });
    }

    private Component makeUtilisateurModel(Utilisateur utilisateur) {
        Container utilisateurModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        utilisateurModel.setUIID("containerRounded");


        emailLabel = new Label("Email : " + utilisateur.getEmail());
        emailLabel.setUIID("labelDefault");

        rolesLabel = new Label("Roles : " + utilisateur.getRoles());
        rolesLabel.setUIID("labelDefault");

        passwordLabel = new Label("Password : " + utilisateur.getPassword());
        passwordLabel.setUIID("labelDefault");

        nomLabel = new Label("Name : " + utilisateur.getNom());
        nomLabel.setUIID("labelDefault");

        telephoneLabel = new Label("Telephone : " + utilisateur.getTelephone());
        telephoneLabel.setUIID("labelDefault");

        soldeLabel = new Label("Solde : " + utilisateur.getSolde());
        soldeLabel.setUIID("labelDefault");


        utilisateurModel.addAll(
                emailLabel, rolesLabel, passwordLabel, nomLabel, telephoneLabel, soldeLabel

        );

        return utilisateurModel;
    }
}