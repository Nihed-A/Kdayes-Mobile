package com.kdayes.gui;

import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.kdayes.MainApp;
import com.kdayes.entities.Utilisateur;
import com.kdayes.services.UtilisateurService;

public class Login extends Form {

    public static Form loginForm;

    public Login() {
        super("Connexion", new BoxLayout(BoxLayout.Y_AXIS));
        loginForm = this;
        addGUIs();
    }

    private void addGUIs() {

        TextField emailTF = new TextField("");
        emailTF.setHint("Entrez votre mail");

        TextField passwordTF = new TextField("", "Entrez votre pwd", 20, TextField.PASSWORD);

        Button connectBtn = new Button("Connexion");
        connectBtn.setUIID("buttonMain");
        connectBtn.addActionListener(l -> connexion(emailTF.getText(), passwordTF.getText()));

        Label registerLabel = new Label("S'inscrire?");

        Button registerBtn = new Button("sign up");
        registerBtn.setUIID("buttonMain");
        registerBtn.addActionListener(l -> new com.kdayes.gui.Register(this).show());

        this.addAll(emailTF, passwordTF, connectBtn, registerLabel, registerBtn);

        Button backendBtn = new Button("Back");
        backendBtn.setUIID("buttonMain");
        backendBtn.addActionListener(l -> new com.kdayes.gui.back.AccueilBack().show());

        this.add(backendBtn);
    }

    private void connexion(String email, String password) {
        Utilisateur utilisateur = UtilisateurService.getInstance().checkCredentials(email, password);

        if (utilisateur != null) {
            MainApp.setSession(utilisateur);
            if (utilisateur.getRoles().equals("ROLE_ADMIN")) {
                new com.kdayes.gui.back.AccueilBack().show();
            } else {
                new com.kdayes.gui.front.AccueilFront().show();
            }
        } else {
            Dialog.show("Erreur", "unmatching credentials", new Command("Ok"));
        }
    }
}
