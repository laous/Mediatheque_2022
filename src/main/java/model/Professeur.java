/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author rachad
 */
public class Professeur extends Abonne {
    
    private String cnss;
    
    public Professeur(String username, String password , String nom, String prenom, String cin, String cnss) {
        super(username, password, nom, prenom, cin);
        this.cnss=new String(cnss);
    }

    public String getCnss() {
        return cnss;
    }
}
