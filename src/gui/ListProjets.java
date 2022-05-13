/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import entities.Projet;
import java.util.ArrayList;
import services.projetServices;

/**
 *
 * @author dell
 */
public class ListProjets extends Form{
    Form current;
    ArrayList<Projet> data = new ArrayList<>();

        public ListProjets(Form previous) {
            setTitle("Liste Des Projets");
            current = this;
            data = projetServices.getInstance().getAllCours();
            System.out.print(data);
            Container y = new Container(new BoxLayout(BoxLayout.Y_AXIS));

            for (int i=0;i<data.size();i++){
                Container x = new Container(new BoxLayout(BoxLayout.X_AXIS));
                Container xx = new Container(new BoxLayout(BoxLayout.X_AXIS));
                Projet p = new Projet();


                p.setId(data.get(i).getId());
                p.setNom_projet(data.get(i).getNom_projet());
                p.setDate_debut(data.get(i).getDate_debut());
                p.setDate_fin(data.get(i).getDate_fin());
                p.setMontant_demandee(data.get(i).getMontant_demandee());   
                p.setMontant_collecte(data.get(i).getMontant_collecte());
                p.setContent(data.get(i).getContent());



                Label separation = new Label("----------------------------");
                Label id = new Label(""+ data.get(i).getId());
                Label nom_projet = new Label( data.get(i).getNom_projet());
                Label date_debut = new Label(data.get(i).getDate_debut());
                Label date_fin = new Label(data.get(i).getDate_fin());
                Label montant_demandee = new Label(""+data.get(i).getMontant_demandee());
                Label montant_collecte = new Label("  "+ data.get(i).getMontant_collecte());
                Label content = new Label(data.get(i).getContent());


                Button modifier = new Button("Modifier");
                Button afficher = new Button("afficher");
                Button supp = new Button("Supprimer");
                
                supp.addActionListener(new ActionListener(){
                    @Override
                   public void actionPerformed(ActionEvent evt) {
                        projetServices.getInstance().deleteProjet(p.getId()); 
                        Dialog.show("Success", "Project Deleted Successfully.", "OK", "Cancel");
                        new HomeForm().show();
                   }
                   });
                
                
                
                afficher.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                     new ProjetDetail(p,current).show();

            }
        });
                modifier.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                     new ModifierProjet(p).show();

            }
        });
                
               
               

                y.addAll( id,nom_projet,date_debut,date_fin,montant_demandee,montant_collecte, content, supp, afficher,modifier);
                //xx.addAll(supp,modif);
                y.addAll(xx,separation);
            }

                


            addAll(y);
            getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                        , e-> previous.showBack()); // Revenir vers l'interface précédente
        }
    
}
