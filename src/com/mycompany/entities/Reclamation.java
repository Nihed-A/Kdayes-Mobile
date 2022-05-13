/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author YOUSSEF
 */
public class Reclamation {
    //nemchio taw nchofo entity fi symfony
    
    private int idRec;
    private int idUtl;
    private String obj,rec;
    private String type;

    public int getIdRec() {
        return idRec;
    }

    public void setIdRec(int idRec) {
        this.idRec = idRec;
    }

    public int getIdUtl() {
        return idUtl;
    }

    public void setIdUtl(int idUtl) {
        this.idUtl = idUtl;
    }

    public String getObj() {
        return obj;
    }

    public void setObj(String obj) {
        this.obj = obj;
    }

    public String getRec() {
        return rec;
    }

    public void setRec(String rec) {
        this.rec = rec;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Reclamation(int idRec, int idUtl, String obj, String rec, String type) {
        this.idRec = idRec;
        this.idUtl = idUtl;
        this.obj = obj;
        this.rec = rec;
        this.type = type;
    }

    public Reclamation() {
    }

    public Reclamation(String obj, String rec, String type) {
        this.obj = obj;
        this.rec = rec;
        this.type = type;
    }

    public Reclamation(int idRec, String obj, String rec, String type) {
        this.idRec = idRec;
        this.obj = obj;
        this.rec = rec;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "idRec=" + idRec + ", idUtl=" + idUtl + ", obj=" + obj + ", rec=" + rec + ", type=" + type + '}';
    }
    

    
    
}
