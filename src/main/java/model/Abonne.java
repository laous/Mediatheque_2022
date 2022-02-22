/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;
import java.util.LinkedList;

/**
 *
 * @author rachad
 */
public abstract class Abonne extends User {
    private Date date_abonnement;
    private LinkedList<Document> favoris;
    private LinkedList<Document> tbr;
    
    
    String genererCode(){
        // generer un code avec random
      return null;
    }
    public Abonne(String username , String password,String nom, String prenom, String cin) {
        super(username,password,nom, prenom, cin);
        favoris= new LinkedList<Document>();
        tbr= new LinkedList<Document>();
    }



    public Date getDate_abonnement() {
        return date_abonnement;
    }

    public LinkedList<Document> getFavoris() {
        return favoris;
    }

    public LinkedList<Document> getTbr() {
        return tbr;
    }
    
    public String toString(){
        return "Abonne=[Nom: "+ getNom()+" "+getPrenom()+", CIN:"+getCin()+"]";
    }
    
    
   
    
  
    
}
