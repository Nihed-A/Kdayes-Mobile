package com.kdayes.gui;


import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.kdayes.entities.Utilisateur;
import com.kdayes.services.UtilisateurService;

public class Register extends Form {


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

    public Register(Form previous) {
        super("Inscription", new BoxLayout(BoxLayout.Y_AXIS));
        this.previous = previous;


        addGUIs();
        addActions();

        getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }


    private void addGUIs() {


        Label loginLabel = new Label("you already have an account?");

        Button loginBtn = new Button("Login");
        loginBtn.setUIID("buttonMain");
        loginBtn.addActionListener(l -> new com.kdayes.gui.Login().show());


        emailLabel = new Label("Email : ");
        emailLabel.setUIID("labelDefault");
        emailTF = new TextField();
        emailTF.setHint("Enter your email");

        rolesLabel = new Label("Roles : ");
        rolesLabel.setUIID("labelDefault");
        rolesTF = new TextField();
        rolesTF.setHint("Enter your role");

        passwordLabel = new Label("Password : ");
        passwordLabel.setUIID("labelDefault");
        passwordTF = new TextField();
        passwordTF.setHint("Enter your password");

        nomLabel = new Label("Name : ");
        nomLabel.setUIID("labelDefault");
        nomTF = new TextField();
        nomTF.setHint("Enter you name");

        telephoneLabel = new Label("Phone : ");
        telephoneLabel.setUIID("labelDefault");
        telephoneTF = new TextField();
        telephoneTF.setHint("Enter your phone");

        soldeLabel = new Label("Solde : ");
        soldeLabel.setUIID("labelDefault");
        soldeTF = new TextField();
        soldeTF.setHint("Enter your current- amount");


        manageButton = new Button("sign up");
        manageButton.setUIID("buttonMain");

        Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        container.setUIID("containerRounded");

        container.addAll(

                emailLabel, emailTF,
                rolesLabel, rolesTF,
                passwordLabel, passwordTF,
                nomLabel, nomTF,
                telephoneLabel, telephoneTF,
                soldeLabel, soldeTF,

                manageButton,
                loginLabel, loginBtn
        );

        this.addAll(container);
    }

    private void addActions() {

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
                    Dialog.show("Success", "registration succedeed", new Command("Ok"));
                    previous.showBack();
                } else if (responseCode == 203) {
                    Dialog.show("Erreur", "Email already exists", new Command("Ok"));
                } else {
                    Dialog.show("Erreur", "Can't add user. Code d'erreur : " + responseCode, new Command("Ok"));
                }
            }
        });
    }

    private boolean controleDeSaisie() {


        if (emailTF.getText().equals("")) {
            Dialog.show("Avertissement", "Enter email please", new Command("Ok"));
            return false;
        }
        if (!emailTF.getText().contains("@")) {
            Dialog.show("Avertissement", "Invalid email", new Command("Ok"));
            return false;
        }


        if (rolesTF.getText().equals("")) {
            Dialog.show("Avertissement", "Enter roles", new Command("Ok"));
            return false;
        }


        if (passwordTF.getText().equals("")) {
            Dialog.show("Avertissement", "Enter valid password", new Command("Ok"));
            return false;
        }


        if (nomTF.getText().equals("")) {
            Dialog.show("Avertissement", "Enter name", new Command("Ok"));
            return false;
        }


        if (telephoneTF.getText().equals("")) {
            Dialog.show("Avertissement", "Enter phone", new Command("Ok"));
            return false;
        }
        try {
            Float.parseFloat(telephoneTF.getText());
        } catch (NumberFormatException e) {
            Dialog.show("Avertissement", telephoneTF.getText() + " it's not a valid number (telephone)", new Command("Ok"));
            return false;
        }


        if (soldeTF.getText().equals("")) {
            Dialog.show("Avertissement", "Enter your credit", new Command("Ok"));
            return false;
        }
        try {
            Float.parseFloat(soldeTF.getText());
        } catch (NumberFormatException e) {
            Dialog.show("Avertissement", soldeTF.getText() + " Not a valid number (solde)", new Command("Ok"));
            return false;
        }


        return true;
    }
}