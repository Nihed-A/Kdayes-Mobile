package com.kdayes.gui.front.utilisateur;


import com.codename1.components.ToastBar;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.kdayes.MainApp;
import com.kdayes.entities.Utilisateur;
import com.kdayes.services.UtilisateurService;

public class EditProfile extends Form {


    Utilisateur currentUtilisateur;

    Label emailLabel, nomLabel, telephoneLabel, soldeLabel;
    TextField
            emailTF,
            nomTF,
            telephoneTF,
            soldeTF, elemTF;


    Button manageButton;

    Form previous;

    public EditProfile(Form previous) {
        super("Update profile", new BoxLayout(BoxLayout.Y_AXIS));
        this.previous = previous;


        addGUIs();
        addActions();

        getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }


    private void addGUIs() {
        currentUtilisateur = MainApp.getSession();


        emailLabel = new Label("Email : ");
        emailLabel.setUIID("labelDefault");
        emailTF = new TextField();
        emailTF.setHint("Enter email email");

        nomLabel = new Label("Name : ");
        nomLabel.setUIID("labelDefault");
        nomTF = new TextField();
        nomTF.setHint("Enter name");

        telephoneLabel = new Label("Telephone : ");
        telephoneLabel.setUIID("labelDefault");
        telephoneTF = new TextField();
        telephoneTF.setHint("Enter phone");

        soldeLabel = new Label("Solde : ");
        soldeLabel.setUIID("labelDefault");
        soldeTF = new TextField();
        soldeTF.setHint("Enter solde");


        emailTF.setText(currentUtilisateur.getEmail());
        nomTF.setText(currentUtilisateur.getNom());
        telephoneTF.setText(String.valueOf(currentUtilisateur.getTelephone()));
        soldeTF.setText(String.valueOf(currentUtilisateur.getSolde()));


        manageButton = new Button("edit");
        manageButton.setUIID("buttonMain");

        Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        container.setUIID("containerRounded");

        container.addAll(

                emailLabel, emailTF,
                nomLabel, nomTF,
                telephoneLabel, telephoneTF,
                soldeLabel, soldeTF,
                manageButton
        );

        this.addAll(container);
    }

    private void addActions() {

        manageButton.addActionListener(action -> {
            if (controleDeSaisie()) {
                int responseCode = UtilisateurService.getInstance().edit(
                        new Utilisateur(
                                currentUtilisateur.getId(),
                                emailTF.getText(),
                                "ROLE_USER",
                                "",
                                nomTF.getText(),
                                (int) Float.parseFloat(telephoneTF.getText()),
                                Float.parseFloat(soldeTF.getText())

                        )
                );
                if (responseCode == 200) {
                    Dialog.show("Succés", "Profil modified", new Command("Ok"));
                    MainApp.setSession(new Utilisateur(
                            currentUtilisateur.getId(),
                            emailTF.getText(),
                            "ROLE_USER",
                            "",
                            nomTF.getText(),
                            (int) Float.parseFloat(telephoneTF.getText()),
                            Float.parseFloat(soldeTF.getText())
                    ));
                    showBackAndRefresh();
                } else {
                    Dialog.show("Erreur", "Erreur de modification de utilisateur. Code d'erreur : " + responseCode, new Command("Ok"));
                }
            }
        });
    }

       public void functionToast() {
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        ToastBar.getInstance().setPosition(TOP);
                        ToastBar.Status status = ToastBar.getInstance().createStatus();
                        status.setShowProgressIndicator(false);
                        status.setMessage("Welcome");
                        status.setExpires(5000);
                        status.show();
                    }
                },
                2000
        );
    }
    private void showBackAndRefresh() {
        ((MyProfile) previous).refresh();
        previous.showBack();
    }

    private boolean controleDeSaisie() {


        if (emailTF.getText().equals("")) {
            Dialog.show("Avertissement", "Veuillez saisir le email", new Command("Ok"));
            return false;
        }
        if (!emailTF.getText().contains("@")) {
            Dialog.show("Avertissement", "Email invalide", new Command("Ok"));
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