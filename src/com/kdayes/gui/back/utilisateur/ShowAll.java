package com.kdayes.gui.back.utilisateur;

import com.codename1.components.InteractionDialog;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.kdayes.entities.Utilisateur;
import com.kdayes.services.UtilisateurService;

import java.util.ArrayList;

public class ShowAll extends Form {


    public static Utilisateur currentUtilisateur = null;
    Button addBtn;
    Label emailLabel, rolesLabel, passwordLabel, nomLabel, telephoneLabel, soldeLabel;
    Button editBtn, deleteBtn;
    Container btnsContainer;

    public ShowAll(Form previous) {
        super("Utilisateurs", new BoxLayout(BoxLayout.Y_AXIS));

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
        addBtn = new Button("Add user");
        addBtn.setUIID("buttonWhiteCenter");

        this.add(addBtn);

        ArrayList<Utilisateur> listUtilisateurs = UtilisateurService.getInstance().getAll();
        if (listUtilisateurs.size() > 0) {
            for (Utilisateur listUtilisateur : listUtilisateurs) {
                this.add(makeUtilisateurModel(listUtilisateur));
            }
        } else {
            this.add(new Label("Aucune donnée"));
        }
    }

    private void addActions() {
        addBtn.addActionListener(action -> {
            currentUtilisateur = null;
            new Manage(this).show();
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


        btnsContainer = new Container(new BorderLayout());
        btnsContainer.setUIID("containerButtons");

        editBtn = new Button();
        editBtn.setMaterialIcon(FontImage.MATERIAL_MODE_EDIT);
        editBtn.setUIID("containerRoundede");
        editBtn.addActionListener(action -> {
            currentUtilisateur = utilisateur;
            new Manage(this).show();
        });

        deleteBtn = new Button();
        deleteBtn.setMaterialIcon(FontImage.MATERIAL_DELETE);
        deleteBtn.setUIID("containerRoundedd");
        deleteBtn.addActionListener(action -> {
            InteractionDialog dlg = new InteractionDialog("Confirmer la suppression");
            dlg.setLayout(new BorderLayout());
            dlg.add(BorderLayout.CENTER, new Label("Are you sure to delete ?"));
            Button btnClose = new Button("NO");
            btnClose.addActionListener((ee) -> dlg.dispose());
            Button btnConfirm = new Button("YES");
            btnConfirm.addActionListener(actionConf -> {
                int responseCode = UtilisateurService.getInstance().delete(utilisateur.getId());

                if (responseCode == 200) {
                    currentUtilisateur = null;
                    dlg.dispose();
                    utilisateurModel.remove();
                    this.refreshTheme();
                } else {
                    Dialog.show("Erreur", "Erreur de suppression du utilisateur. Code d'erreur : " + responseCode, new Command("Ok"));
                }
            });
            Container btnContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
            btnContainer.addAll(btnClose, btnConfirm);
            dlg.addComponent(BorderLayout.SOUTH, btnContainer);
            dlg.show(800, 800, 0, 0);
        });

        btnsContainer.add(BorderLayout.CENTER, editBtn);
        btnsContainer.add(BorderLayout.EAST, deleteBtn);

        utilisateurModel.addAll(
                emailLabel, rolesLabel, passwordLabel, nomLabel, telephoneLabel, soldeLabel,

                btnsContainer
        );

        return utilisateurModel;
    }
}