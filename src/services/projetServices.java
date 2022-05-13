/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import entities.Projet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import utils.Statics;

/**
 *
 * @author dell
 */
public class projetServices {
    public static projetServices instance = null;
    private ConnectionRequest req;
    public ArrayList<Projet> projets;
    public static boolean resultOK;
    
    public static projetServices getInstance(){
        if(instance==null)
            instance = new projetServices();
         return instance;
        
    }
    public projetServices(){
        req = new ConnectionRequest();
    }
    
    
     public ArrayList<Projet> parseCours(String jsonText){
        try {
            projets=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Projet p = new Projet();
               
                p.setId((int) Float.parseFloat(obj.get("id").toString()));
                p.setNom_projet(obj.get("nom_projet").toString());
             //   p.setDate_debut(obj.get("date_debut").toString());
              //  p.setDate_fin(obj.get("date_fin").toString());
                p.setMontant_demandee((int)Double.parseDouble(obj.get("montant_demandee").toString()));
                p.setMontant_collecte((int)Double.parseDouble(obj.get("montant_collecte").toString()));
                //p.setContent(obj.get("content").toString());
                
               
       
                projets.add(p);
            }
                     
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return projets;
    }
     
      public ArrayList<Projet> getAllCours(){
        String url = Statics.BASE_URL+"/api/projet";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                projets = parseCours(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return projets;
    }
      
      public void AjouterProjet(Projet p){
        String url = Statics.BASE_URL+"/api/add?nom="+p.getNom_projet()+"&montant_demandee="+p.getMontant_demandee()+"&montant_collecte="+p.getMontant_collecte()+"&content="+p.getContent();
        req.setUrl(url);
        req.addResponseListener(a->{
            String str = new String(req.getResponseData());
            System.err.println("data=="+str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
      public boolean deleteProjet(int Id){
        String url = Statics.BASE_URL+"/api/delete/"+Id;
        req.setUrl(url);
         req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               req.removeResponseCodeListener(this);
            }
        });
         NetworkManager.getInstance().addToQueueAndWait(req);
         return resultOK;
    }
      public Projet showProjet(int Id){
          String url = Statics.BASE_URL+"/api/project/"+Id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                projets = parseCours(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return projets.get(0);
       
          
    }
        
        public boolean modifierProjet(Projet p){
        String url = Statics.BASE_URL+"/api/update/"+p.getId()+"?nom="+p.getNom_projet()+"&montant_demandee="+p.getMontant_demandee()+"&montant_collecte="+p.getMontant_collecte()+"&content="+p.getContent();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
              resultOK=req.getResponseCode() == 200;
              req.removeResponseListener(this);
            }
        });
         NetworkManager.getInstance().addToQueueAndWait(req);
         return resultOK;
    }
    
}
