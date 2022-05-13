/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Article;
import com.mycompany.services.ServiceArticle;
import java.util.List;

/**
 *
 * @author YOUSSEF
 */
public class ModArt extends SideMenu{
     ModArt instance;
    //private List<promotion> listprom;
     private List<Article> listart;
     EncodedImage enc;
     private Form form ;
//    private Toolbar tb;
     private Container evenements;
     Form current;
    
    public ModArt (Resources res,Form previous) {
        instance=this;
//        super(BoxLayout.y());
        Toolbar tb = getToolbar();
		tb.setUIID("Toolbar");
                System.out.println("mochkla");
        tb.addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, e-> showOtherForm(res,previous));
//        SpanLabel sp = new SpanLabel();
        List<Article> list = ServiceArticle.getInstance().getAllArt();

       
        for (Article p : list){
            
        setLayout(new FlowLayout(CENTER));
        Container cnt1 = new Container(BoxLayout.y());   
        Container cnt2 = new Container(BoxLayout.x());
        //ontainer cnt = new Container();
//            System.out.println("alllooo------"+article.getNom());
        SpanLabel SLnom = new SpanLabel(p.getTitre());
//     SpanLabel SLdesc = new SpanLabel(p.getDescription());
    //  SpanLabel SLrem = new SpanLabel(p.getAuteur());
        Button SLvoir = new Button("Voir article","voirArt");
        EncodedImage enc = EncodedImage.createFromImage(Image.createImage(400, 400), true);
        String url =  "http://localhost/Image/"+p.getImage();
        ImageViewer img = new ImageViewer(URLImage.createToStorage(enc,url, url,URLImage.RESIZE_SCALE_TO_FILL));
         img.getAllStyles().setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FIT);
        SLvoir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                        new ArtId(p, instance).show();
            }
        });
//        cnt1.getStyle().getBgColor(0x99cccc);
        
        //cnt2.getStyle().setBgColor(0x99CCCC);
        //cnt2.getStyle().setBgTransparency(255);
        //Stroke borderStroke = new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1);
//        cnt2.getStyle().setBorder(RoundBorder.create(). rectangle(true). color(0xffffff). strokeColor(0). strokeOpacity(10));


        cnt2.add(SLnom);
        
        cnt1.add(cnt2);
        cnt1.add(img);
        cnt1.add(SLvoir);
        
        
//        for (Article a : lista){
//         SpanLabel SLnomA = new SpanLabel(a.getNom());   
//         cnt1.add(SLnomA);
//        }
        
        
        add(cnt1);
        //search tbadel 3onwen tool bar
//prepare field
TextField searchField;
searchField = new TextField("", "Rechercher...");
//searchField.getHintLabel().setUIID("Title");
//searchField.setUIID("Title");
//tb.setGlobalToolbar(true);
tb.setTitleComponent(searchField);
//if field content changed
searchField.addDataChangeListener((i1, i2) -> {
String t = searchField.getText();
if(t.length() < 1) {
for(Component cmp : getContentPane()) {
cmp.setHidden(false);
cmp.setVisible(true);
}
} else {
t = t.toLowerCase();
for(Component cmp: getContentPane()) {
//tekhou el val ta3 el champ : champ li 3malt 3lih el recherche type span label (emplacement : container->container->spanlabel )
String val = ((SpanLabel) ((Container)((Container) cmp).getComponentAt(0)).getComponentAt(0)).getText();
System.out.println( val );
boolean show = val != null && val.toLowerCase().indexOf(t) > -1;
cmp.setHidden(!show);
cmp.setVisible(show);
}
}
getContentPane().animateLayout(250);
});
            
        
        }   
    }

    

     @Override
    protected void showOtherForm(Resources res,Form previous) {
        new Home(res,previous).show();
    }

    

   

    

 
    
}
