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
public class Etudiant extends Abonne {
    
    private String cne;

    public Etudiant(String username, String password, String nom, String prenom, String cin, String cne) {
        super(username, password, nom, prenom, cin);
        this.cne= new String(cne);
    }

    public String getCne() {
        return cne;
    }
    
}
