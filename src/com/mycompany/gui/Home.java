/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Article;
import static com.mycompany.services.ServiceArticle.instance;

/**
 *
 * @author YOUSSEF
 */
public class Home extends SideMenu {
   Art instance;
    Form current;
     Resources theme;
   public Home(Resources res,Form previous){
       
       super(BoxLayout.y());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
//        Image profilePic = res.getImage("user-picture.jpg");
//        Image mask = res.getImage("round-mask.png");
//        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
//        Label profilePicLabel = new Label(profilePic, "ProfilePicTitle");
//        profilePicLabel.setMask(mask.createMask());

        Button menuButton = new Button("");
        menuButton.setUIID("Title");
  FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
        
//        Container remainingTasks = BoxLayout.encloseY(
//                        new Label("12", "CenterTitle"),
//                        new Label("remaining tasks", "CenterSubTitle")
//                );
//        remainingTasks.setUIID("RemainingTasks");
//        Container completedTasks = BoxLayout.encloseY(
//                        new Label("32", "CenterTitle"),
//                        new Label("completed tasks", "CenterSubTitle")
//        );
//        completedTasks.setUIID("CompletedTasks");


//        Container titleCmp = BoxLayout.encloseY(
//                        FlowLayout.encloseIn(menuButton));
//        ,
//                        BorderLayout.centerAbsolute(
//                                BoxLayout.encloseY(
//                                    new Label("hadiji youssef", "Title"),
//                                    new Label("Code Name", "SubTitle")
//                                )
//                            ).add(BorderLayout.WEST, profilePicLabel),
//                        GridLayout.encloseIn(2, remainingTasks, completedTasks)
//                );
        
//        FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
//        fab.getAllStyles().setMarginUnit(Style.UNIT_TYPE_PIXELS);
//        fab.getAllStyles().setMargin(BOTTOM, completedTasks.getPreferredH() - fab.getPreferredH() / 2);
//        tb.setTitleComponent(fab.bindFabToContainer(titleCmp, CENTER, BOTTOM));
                       Container titleCmp = BoxLayout.encloseY(
                FlowLayout.encloseIn(menuButton),
                BorderLayout.centerAbsolute(
                        BoxLayout.encloseY(
                                new Label("", "Title"),
                                new Label("", "SubTitle")
                        )
                )
        );    
        //add(new Label("Today", "TodayTitle"));
        tb.setTitleComponent(titleCmp);
        setupSideMenu(res,previous);
//         Button Modifier = new Button();
//        Article p = new Article();
//        Modifier.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent evt) {
//                        if(current != null){
//            current.show();
//            return;
//        }
//        new ModArt(theme,current).show();
//            }
//        });
//        
    
        
        FontImage arrowDown = FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_DOWN, "Label", 3);
        
        addButtonBottom(arrowDown, "Liste des articles", 0xd997f1, true);
        addButtonBottom(arrowDown, "Modifier article", 0x5ae29d, false);
        addButtonBottom(arrowDown, "Supprimer article ", 0x4dc2ff, false);
////        setupSideMenu(res);
    }
//            super(BoxLayout.y());
//        Toolbar tb = getToolbar();
//        tb.setTitleCentered(false);
////        Image profilePic = res.getImage("user-picture.png");
//        Image mask = res.getImage("round-mask.png");
////        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
////        Label profilePicLabel = new Label(profilePic, "ProfilePicTitle");
////        profilePicLabel.setMask(mask.createMask());
//
//        Button menuButton = new Button("");
//        menuButton.setUIID("Title");
//        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
//        menuButton.addActionListener(e -> getToolbar().openSideMenu());
//
//        Container titleCmp = BoxLayout.encloseY(
//                FlowLayout.encloseIn(menuButton),
//                BorderLayout.centerAbsolute(
//                        BoxLayout.encloseY(
//                                new Label("", "Title"),
//                                new Label("", "SubTitle")
//                        )
//                )
//        );
//        tb.setTitleComponent(titleCmp);
//        setupSideMenu(res,previous);
//    
////        super(BoxLayout.y());
//////        Toolbar tb = getToolbar();
//////        tb.setTitleCentered(false);
////////        
////////        
////////
////        Button menuButton = new Button("");
////        menuButton.setUIID("Title");
////        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
////        menuButton.addActionListener(e -> getToolbar().openSideMenu());
//////////
////////        Container titleCmp = BoxLayout.encloseY(
////////                FlowLayout.encloseIn(menuButton),
////////                BorderLayout.centerAbsolute(
////////                        BoxLayout.encloseY(
////////                                new Label("", "Title"),
////////                                new Label("", "SubTitle")
////////                        )
////////                )
////////        );
////////        tb.setTitleComponent(titleCmp);
////        setupSideMenu(res);
//    }

    @Override
    protected void showOtherForm(Resources res,Form previous) {
        new Home(res,previous).show();
    }

   private void addButtonBottom(Image arrowDown, String text, int color, boolean first) {
        MultiButton finishLandingPage = new MultiButton(text);
        Article p = new Article();
        finishLandingPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                        if(current != null){
            current.show();
            return;
        }
        new Art(theme,current).show();
            }
        });
//        Button Modifier = new Button(text);
//        Article p = new Article();
//        Modifier.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent evt) {
//                        if(current != null){
//            current.show();
//            return;
//        }
//        new ModArt(theme,current).show();
//            }
//        });

//        
        
        finishLandingPage.setEmblem(arrowDown);
        finishLandingPage.setUIID("Container");
//        finishLandingPage.setUIIDLine1("TodayEntry");
        finishLandingPage.setIcon(createCircleLine(color, finishLandingPage.getPreferredH(),  first));
        finishLandingPage.setIconUIID("Container");
        add(FlowLayout.encloseIn(finishLandingPage));
        


    }
    
    private Image createCircleLine(int color, int height, boolean first) {
        Image img = Image.createImage(height, height, 0);
        Graphics g = img.getGraphics();
        g.setAntiAliased(true);
        g.setColor(0xcccccc);
        int y = 0;
        if(first) {
            y = height / 6 + 1;
        }
        g.drawLine(height / 2, y, height / 2, height);
        g.drawLine(height / 2 - 1, y, height / 2 - 1, height);
        g.setColor(color);
        g.fillArc(height / 2 - height / 4, height / 6, height / 2, height / 2, 0, 360);
        return img;
    }
}
