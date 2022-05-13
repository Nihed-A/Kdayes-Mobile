package com.kdayes.gui.back.evenement;

import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.kdayes.entities.Categorie;
import com.kdayes.services.CategorieService;
import java.text.SimpleDateFormat;

import java.util.ArrayList;

public class ChooseCategorie extends Form {

    Form previousForm;

    public ChooseCategorie(Form previous) {
        super("Choisir un categorie", new BoxLayout(BoxLayout.Y_AXIS));

        previousForm = previous;
        addGUIs();

        super.getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    public void refresh() {
        this.removeAll();
        addGUIs();
        this.refreshTheme();
    }

    private void addGUIs() {
        ArrayList<Categorie> listCategories = CategorieService.getInstance().getAll();
        if (listCategories.size() > 0) {
            for (Categorie categories : listCategories) {
                this.add(makeCategorieModel(categories));
            }
        } else {
            this.add(new Label("Aucune donnée"));
        }
    }

    Label label;
    Button chooseBtn;
    Container btnsContainer;

    private Component makeCategorieModel(Categorie categorie) {
        Container categorieModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        categorieModel.setUIID("containerRounded");

        label = new Label("Nom : " + categorie.getNom());

        chooseBtn = new Button("Choisir");
        chooseBtn.addActionListener(l -> {
            Manage.selectedCategorie = categorie;
            ((Manage) previousForm).refreshCategorie();
            previousForm.showBack();
        });

        btnsContainer = new Container(new BorderLayout());
        btnsContainer.setUIID("containerButtons");
        btnsContainer.add(BorderLayout.CENTER, chooseBtn);

        categorieModel.addAll(label, btnsContainer);

        return categorieModel;
    }
}