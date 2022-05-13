package com.kdayes.gui.back.utilisateur;


import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.kdayes.entities.Utilisateur;
import com.kdayes.services.UtilisateurService;

public class Manage extends Form {


    Utilisateur currentUtilisateur;

    Label emailLabel, rolesLabel, passwordLabel, nomLabel, telephoneLabel, soldeLabel;
    TextField
            emailTF,
            rolesTF,
            passwordTF,
            nomTF,
            telephoneTF,
            soldeTF, elemTF;


    Button manageButton;

    Form previous;

    public Manage(Form previous) {
        super(ShowAll.currentUtilisateur == null ? "Ajouter" : "Modifier", new BoxLayout(BoxLayout.Y_AXIS));
        this.previous = previous;


        currentUtilisateur = ShowAll.currentUtilisateur;

        addGUIs();
        addActions();

        getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }


    private void addGUIs() {


        emailLabel = new Label("Email : ");
        emailLabel.setUIID("labelDefault");
        emailTF = new TextField();
        emailTF.setHint("Enter email");

        rolesLabel = new Label("Roles : ");
        rolesLabel.setUIID("labelDefault");
        rolesTF = new TextField();
        rolesTF.setHint("Enter roles");

        passwordLabel = new Label("Password : ");
        passwordLabel.setUIID("labelDefault");
        passwordTF = new TextField();
        passwordTF.setHint("Enter password");

        nomLabel = new Label("Name : ");
        nomLabel.setUIID("labelDefault");
        nomTF = new TextField();
        nomTF.setHint("Enter name");

        telephoneLabel = new Label("Phone : ");
        telephoneLabel.setUIID("labelDefault");
        telephoneTF = new TextField();
        telephoneTF.setHint("Enter phone");

        soldeLabel = new Label("Solde : ");
        soldeLabel.setUIID("labelDefault");
        soldeTF = new TextField();
        soldeTF.setHint("Enter your credit ");


        if (currentUtilisateur == null) {


            manageButton = new Button("New User");
        } else {


            emailTF.setText(currentUtilisateur.getEmail());
            rolesTF.setText(currentUtilisateur.getRoles());
            passwordTF.setText(currentUtilisateur.getPassword());
            nomTF.setText(currentUtilisateur.getNom());
            telephoneTF.setText(String.valueOf(currentUtilisateur.getTelephone()));
            soldeTF.setText(String.valueOf(currentUtilisateur.getSolde()));


            manageButton = new Button();
            manageButton.setMaterialIcon(FontImage.MATERIAL_MODE_EDIT);
        }
        manageButton.setUIID("buttonMain");

        Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        container.setUIID("containerRounded");

        if (currentUtilisateur == null) {
            container.addAll(
                    emailLabel, emailTF,
                    rolesLabel, rolesTF,
                    passwordLabel, passwordTF,
                    nomLabel, nomTF,
                    telephoneLabel, telephoneTF,
                    soldeLabel, soldeTF,
                    manageButton
            );
        } else {
            container.addAll(
                    emailLabel, emailTF,
                    rolesLabel, rolesTF,
                    nomLabel, nomTF,
                    telephoneLabel, telephoneTF,
                    soldeLabel, soldeTF,
                    manageButton
            );
        }

        this.addAll(container);
    }

    private void addActions() {

        if (currentUtilisateur == null) {
            manageButton.addActionListener(action -> {
                if (controleDeSaisie()) {
                    int responseCode = UtilisateurService.getInstance().add(
                            new Utilisateur(


                                    emailTF.getText(),
                                    rolesTF.getText(),
                                    passwordTF.getText(),
                                    nomTF.getText(),
                                    (int) Float.parseFloat(telephoneTF.getText()),
                                    Float.parseFloat(soldeTF.getText())
                            )
                    );
                    if (responseCode == 200) {
                        Dialog.show("Succés", "Utilisateur ajouté avec succes", new Command("Ok"));
                        showBackAndRefresh();
                    } else {
                        Dialog.show("Erreur", "Erreur d'ajout de utilisateur. Code d'erreur : " + responseCode, new Command("Ok"));
                    }
                }
            });
        } else {
            manageButton.addActionListener(action -> {
                if (controleDeSaisie()) {
                    int responseCode = UtilisateurService.getInstance().edit(
                            new Utilisateur(
                                    currentUtilisateur.getId(),


                                    emailTF.getText(),
                                    rolesTF.getText(),
                                    passwordTF.getText(),
                                    nomTF.getText(),
                                    (int) Float.parseFloat(telephoneTF.getText()),
                                    Float.parseFloat(soldeTF.getText())

                            )
                    );
                    if (responseCode == 200) {
                        Dialog.show("Succés", "Utilisateur modifié avec succes", new Command("Ok"));
                        showBackAndRefresh();
                    } else {
                        Dialog.show("Erreur", "Erreur de modification de utilisateur. Code d'erreur : " + responseCode, new Command("Ok"));
                    }
                }
            });
        }
    }

    private void showBackAndRefresh() {
        ((ShowAll) previous).refresh();
        previous.showBack();
    }

    private boolean controleDeSaisie() {


        if (emailTF.getText().equals("")) {
            Dialog.show("Avertissement", "Veuillez saisir le email", new Command("Ok"));
            return false;
        }


        if (rolesTF.getText().equals("")) {
            Dialog.show("Avertissement", "Veuillez saisir le roles", new Command("Ok"));
            return false;
        }


        if (passwordTF.getText().equals("")) {
            Dialog.show("Avertissement", "Veuillez saisir le password", new Command("Ok"));
            return false;
        }


        if (nomTF.getText().equals("")) {
            Dialog.show("Avertissement", "Veuillez saisir le nom", new Command("Ok"));
            return false;
        }


        if (telephoneTF.getText().equals("")) {
            Dialog.show("Avertissement", "Veuillez saisir le telephone", new Command("Ok"));
            return false;
        }
        try {
            Float.parseFloat(telephoneTF.getText());
        } catch (NumberFormatException e) {
            Dialog.show("Avertissement", telephoneTF.getText() + " n'est pas un nombre valide (telephone)", new Command("Ok"));
            return false;
        }


        if (soldeTF.getText().equals("")) {
            Dialog.show("Avertissement", "Veuillez saisir le solde", new Command("Ok"));
            return false;
        }
        try {
            Float.parseFloat(soldeTF.getText());
        } catch (NumberFormatException e) {
            Dialog.show("Avertissement", soldeTF.getText() + " n'est pas un nombre valide (solde)", new Command("Ok"));
            return false;
        }


        return true;
    }
}