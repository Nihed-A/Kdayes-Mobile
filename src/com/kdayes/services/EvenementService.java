package com.kdayes.services;

import com.codename1.components.InfiniteProgress;
import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;

import com.kdayes.entities.Categorie;

import com.kdayes.entities.Evenement;
import com.kdayes.utils.Statics;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EvenementService {

    public static EvenementService instance = null;
    public int resultCode;
    private ConnectionRequest cr;
    private ArrayList<Evenement> listEvenements;
    

    private EvenementService() {
        cr = new ConnectionRequest();
    }

    public static EvenementService getInstance() {
        if (instance == null) {
            instance = new EvenementService();
        }
        return instance;
    }
    
    public ArrayList<Evenement> getAll() {
        listEvenements = new ArrayList<>();

        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "/evenement");
        cr.setHttpMethod("GET");

        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                if (cr.getResponseCode() == 200) {
                    listEvenements = getList();
                }

                cr.removeResponseListener(this);
            }
        });

        try {
            cr.setDisposeOnCompletion(new InfiniteProgress().showInfiniteBlocking());
            NetworkManager.getInstance().addToQueueAndWait(cr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listEvenements;
    }

    private ArrayList<Evenement> getList() {
        try {
            Map<String, Object> parsedJson = new JSONParser().parseJSON(new CharArrayReader(
                    new String(cr.getResponseData()).toCharArray()
            ));
            List<Map<String, Object>> list = (List<Map<String, Object>>) parsedJson.get("root");

            for (Map<String, Object> obj : list) {
                Evenement evenement = new Evenement(
                        (int) Float.parseFloat(obj.get("id").toString()),
                        
                        (String) obj.get("image"),
                        (String) obj.get("nom"),
                        makeCategorie((Map<String, Object>) obj.get("categorie")),
                        (String) obj.get("description"),
                        new SimpleDateFormat("dd-MM-yyyy").parse((String) obj.get("date")),
                        (String) obj.get("lieu"),
                        (String) obj.get("responsable"),
                        (int) Float.parseFloat(obj.get("places").toString())
                        
                );

                listEvenements.add(evenement);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return listEvenements;
    }

    
    public Categorie makeCategorie(Map<String, Object> obj) {
        if (obj == null) {
            return null;
        }
        Categorie categorie = new Categorie();
        categorie.setId((int) Float.parseFloat(obj.get("id").toString()));
        categorie.setNom((String) obj.get("nom"));
        return categorie;
    }
    

    public int add(Evenement evenement) {
        return manage(evenement, false, true);
    }

    public int edit(Evenement evenement, boolean imageEdited) {
        return manage(evenement, true , imageEdited);
    }

    public int manage(Evenement evenement, boolean isEdit, boolean imageEdited) {
        
        MultipartRequest cr = new MultipartRequest();
        cr.setFilename("file", "Evenement.jpg");

        
        cr.setHttpMethod("POST");
        if (isEdit) {
            cr.setUrl(Statics.BASE_URL + "/evenement/edit");
            cr.addArgumentNoEncoding("id", String.valueOf(evenement.getId()));
        } else {
            cr.setUrl(Statics.BASE_URL + "/evenement/add");
        }

        if (imageEdited) {
            try {
                cr.addData("file", evenement.getImage(), "image/jpeg");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            cr.addArgumentNoEncoding("image", evenement.getImage());
        }

        cr.addArgumentNoEncoding("image", evenement.getImage());cr.addArgumentNoEncoding("nom", evenement.getNom());cr.addArgumentNoEncoding("categorie", String.valueOf(evenement.getCategorie().getId()));cr.addArgumentNoEncoding("description", evenement.getDescription());cr.addArgumentNoEncoding("date", new SimpleDateFormat("dd-MM-yyyy").format(evenement.getDate()));cr.addArgumentNoEncoding("lieu", evenement.getLieu());cr.addArgumentNoEncoding("responsable", evenement.getResponsable());cr.addArgumentNoEncoding("places", String.valueOf(evenement.getPlaces()));

        
        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultCode = cr.getResponseCode();
                cr.removeResponseListener(this);
            }
        });
        try {
            cr.setDisposeOnCompletion(new InfiniteProgress().showInfiniteBlocking());
            NetworkManager.getInstance().addToQueueAndWait(cr);
        } catch (Exception ignored) {

        }
        return resultCode;
    }

    public int delete(int evenementId) {
        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "/evenement/delete");
        cr.setHttpMethod("POST");
        cr.addArgument("id", String.valueOf(evenementId));

        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                cr.removeResponseListener(this);
            }
        });

        try {
            cr.setDisposeOnCompletion(new InfiniteProgress().showInfiniteBlocking());
            NetworkManager.getInstance().addToQueueAndWait(cr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cr.getResponseCode();
    }
}
