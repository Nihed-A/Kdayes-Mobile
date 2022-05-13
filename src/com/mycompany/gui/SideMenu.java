/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Form;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author YOUSSEF
 */
    import com.codename1.components.SpanLabel;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.util.Resources;



public abstract class SideMenu extends Form {
    public SideMenu(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }

    public SideMenu(String title) {
        super(title);
    }

    public SideMenu() {
    }

    public SideMenu(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }

    public void setupSideMenu(Resources res,Form previous) {
//         Image profilePic = res.getImage("user-picture.jpg");
//        Image mask = res.getImage("round-mask.png");
//        mask = mask.scaledHeight(mask.getHeight() / 4 * 3);
//        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label("  Hadiji Youssef", "SideMenuTitle");
//        profilePicLabel.setMask(mask.createMask());

        Container sidemenuTop = BorderLayout.center(profilePicLabel);
        sidemenuTop.setUIID("SidemenuTop");
        
        getToolbar().addComponentToSideMenu(sidemenuTop);
        getToolbar().addCommandToSideMenu("  Tableau de bord ", null,  e -> showOtherForm(res));
         getToolbar().addMaterialCommandToSideMenu("  Evenement ", FontImage.MATERIAL_TRENDING_UP,  e -> showOtherForm(res));
        getToolbar().addMaterialCommandToSideMenu("  Projet", FontImage.MATERIAL_ACCESS_TIME,  e -> showOtherForm(res));
        getToolbar().addMaterialCommandToSideMenu("  Reclamation", FontImage.MATERIAL_SETTINGS,  e -> showOtherForm(res));
        getToolbar().addCommandToSideMenu("Blog", null, e -> new Art(res,previous).show());
        
        getToolbar().addMaterialCommandToSideMenu("  Deconnexion", FontImage.MATERIAL_EXIT_TO_APP,  e -> new LoginForm(res).show());
//        Container cnt1 = new Container(BoxLayout.y());
//        SpanLabel test = new SpanLabel("TEST");
//        cnt1.add(test);
//        Container sidemenuTop = BorderLayout.center(cnt1);
//        sidemenuTop.setUIID("SidemenuTop");

//        getToolbar().addComponentToSideMenu(sidemenuTop);
           // getToolbar().addCommandToSideMenu("Promotion", null, e -> new Art(res,previous).show());
//            getToolbar().addCommandToSideMenu("Article", null, e -> new Art(res,previous).show());
            //getToolbar().addCommandToSideMenu("Déconnexion", null, e -> new Login(res,previous).show());

        }
        

    protected abstract void showOtherForm(Resources res,Form previous);
     
    

    private Command showOtherForm(Resources res) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
    

