/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author dell
 */
public class HomeForm extends Form{
    Form current;
    public HomeForm(){
        current = this;
        setTitle("Home Admin");
        setLayout(BoxLayout.y());

        Button btnListProjet = new Button("Liste des projets");
        Button btnProjetAjout = new Button("Ajout projet");
        
        
        btnListProjet.addActionListener(e -> new ListProjets(current).show());
        btnProjetAjout.addActionListener(e -> new AjoutProjet(current).show());
     
        addAll(btnListProjet, btnProjetAjout);
    }
    
}
