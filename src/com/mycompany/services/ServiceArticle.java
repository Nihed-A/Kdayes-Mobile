/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Article;
import com.mycompany.entities.Categorie;
import com.mycompany.utils.Statics;
//import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;



/**
 *
 * @author YOUSSEF
 */
public class ServiceArticle {
    ArrayList<Article> listArt = new ArrayList<>();
    public static ServiceArticle instance;
    
    
    
    
    public static ServiceArticle getInstance(){
    if(instance==null){
        instance = new ServiceArticle();
    }
    return instance;
    }
    
    public ArrayList<Article> liste(String json){
        ArrayList<Article> lista = new ArrayList<>();
        try {
            
            JSONParser j = new JSONParser();
            Map<String, Object> articles = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println("bechir"+json);
            System.out.println("Lista"+articles);
            List<Map<String, Object>> list = (List<Map<String, Object>>) articles.get("root");

            for (Map<String, Object> obj : list) {
                Article a = new Article();
                Categorie c = new Categorie();
                float id = Float.parseFloat(obj.get("id").toString());
                a.setId((int) id);
                a.setTitre(obj.get("titre").toString());
                a.setAuteur(obj.get("auteur").toString());
                
//                float quantite = Float.parseFloat(obj.get("quantite").toString());
//                a.setQuantite((int) quantite);
//                float prix = Float.parseFloat(obj.get("prix").toString());
//                a.setPrix((int) prix);
                a.setContenu(obj.get("contenu").toString());
//                float cat_id = Integer.parseInt(obj.get("categorie").toString());
//                a.setId_categorie((int)cat_id);
                a.setImage(obj.get("image").toString());

//                Map<String, Object> cat = (Map<String, Object>) obj.get("categorie");
////                System.out.println("skan "+cat);
//                String nomC =cat.get("nom").toString();
////                System.out.println("mehdi "+(int)id_c);
//                a.setNomcat(nomC);
//                c.setNom(cat.get("nom").toString());
                
//                Map<String, Object> fab = (Map<String, Object>) obj.get("fabricant");
////                System.out.println("skan "+cat);
//                String nomF = fab.get("nom").toString();
////                System.out.println("mehdi "+(int)id_c);
//                a.setNomfab(nomF);
                
                
//                c.setNom(cat.get("nom").toString());
//                Map<String, Object> cat = (Map<String, Object>) obj.get("nomcat");
//                float id_c = Float.parseFloat(cat.get("id").toString());
//                c.setId((int) id_c);
//                c.setNom(cat.get("nom").toString());
//                a.setId_categorie(c.getId());
                
                
//                float cat_id = Float.parseFloat(obj.get("categorie_id").toString());
//                a.setId_categorie((int) cat_id);
//                float fab_id = Float.parseFloat(obj.get("fabricant_id").toString());
//                a.setId_fabricant((int) fab_id);
//                a.setNomcat(obj.get("categorie").toString());
                
                
                lista.add(a);
            }
     
        } catch (IOException ex) {
            System.out.println("test1");
        }
        System.out.println("test2");
        System.out.println(lista);
    return lista;
    }
    
    
    
    public ArrayList<Article> getAllArt(){
        ConnectionRequest con = new ConnectionRequest();
    String url = Statics.BASE_URL+"/allArtJSON";

        System.out.println("mouch normal"+url);
        
    con.setUrl(url);
    con.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
//        promo = liste(new String(req.getResponseData()));
//        req.removeResponseListener(this);
ServiceArticle as = new ServiceArticle();
listArt = as.liste(new String(con.getResponseData()));
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(con);
    return listArt;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
//        //singleton 
//    public static ServiceArticle instance = null ;
//    
//    public static boolean resultOk = true;
//
//    //initilisation connection request 
//    private ConnectionRequest req;
//    
//    
//    public static ServiceArticle getInstance() {
//        if(instance == null )
//            instance = new ServiceArticle();
//        return instance ;
//    }
//    
//    
//    
//    public ServiceArticle() {
//        req = new ConnectionRequest();
//        
//    }
//     
//    public ArrayList<Article>affichageArt() {
//        ArrayList<Article> result = new ArrayList<>();
//        
//        String url = Statics.BASE_URL+"/allArtJSON";
//        req.setUrl(url);
//        
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                JSONParser jsonp ;
//                jsonp = new JSONParser();
//                
//                try {
//                    Map<String,Object>mapArticle = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
//                    
//                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapArticle.get("root");
//                    
//                    for(Map<String, Object> obj : listOfMaps) {
//                        Article re = new Article();
//                        
//                        //dima id fi codename one float 5outhouha
//                        float id = Float.parseFloat(obj.get("id").toString());
//                        
//                        String auteur = obj.get("auteur").toString();
//                        
//                        String titre = obj.get("titre").toString();
//                        String contenu = obj.get("contenu").toString();
//                        
//                        
//                        re.getId((id));
//                        re.setAuteur(auteur);
//                        re.setTitre(titre);
//                        re.setContenu(contenu);
//                        re.setImage(obj.get("image").toString());
//                        Map<String, Object> cat = (Map<String, Object>) obj.get("categorie");
////                System.out.println("skan "+cat);
//                        String nomC =cat.get("nom").toString();
////                System.out.println("mehdi "+(int)id_c);
//                        re.setNomcat(nomC);
//                        
//                        //Date 
//                        String DateConverter =  obj.get("date").toString().substring(obj.get("date").toString().indexOf("timestamp") + 10 , obj.get("date").toString().lastIndexOf("}"));
//                        
//                        Date currentTime = new Date(Double.valueOf(DateConverter).longValue() * 1000);
//                        
//                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//                        String dateString = formatter.format(currentTime);
//                        re.setDate(dateString);
//                        
//                        //insert data into ArrayList result
//                        result.add(re);
//                       
//                    
//                    }
//                    
//                }catch(Exception ex) {
//                    
//                    ex.printStackTrace();
//                }
//            
//            }
//        });
//        
//      NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
//
//        return result;
//        
//        
//    }
//      //Detail Reclamation bensba l detail n5alihoa lel5r ba3d delete+update
//    
//    public Article DetailArticle( int id , Article reclamation) {
//        
//        String url = Statics.BASE_URL+"/allArticleJSON?"+id;
//        req.setUrl(url);
//        
//        String str  = new String(req.getResponseData());
//        req.addResponseListener(((evt) -> {
//        
//            JSONParser jsonp = new JSONParser();
//            try {
//                
//                Map<String,Object>obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
//                
//                reclamation.setAuteur(obj.get("auteur").toString());
//                
//                reclamation.setTitre(obj.get("titre").toString());
//                reclamation.setContenu(obj.get("contenu").toString());
//                reclamation.setImage(obj.get("image").toString());
//                
//                
//            }catch(IOException ex) {
//                System.out.println("error related to sql :( "+ex.getMessage());
//            }
//            
//            
//            System.out.println("data === "+str);
//            
//            
//            
//        }));
//        
//              NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
//
//              return reclamation;
//        
//        
//    }
//    
//    
//    //Delete 
//    public boolean deleteArt(int id ) {
//        String url = Statics.BASE_URL +"/deleteArt?id="+id;
//        
//        req.setUrl(url);
//        
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                    
//                    req.removeResponseCodeListener(this);
//            }
//        });
//        
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return  resultOk;
//    }
     
}
