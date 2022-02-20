/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


public class User {
    private String username;
    private String passwrod;
    private String nom;
    private String prenom;
    private String cin;

    public User(String username, String password, String nom, String prenom, String cin) {
        this.username = new String(username);
        this.passwrod = new String(password);
        this.nom = new String(nom);
        this.prenom = new String(prenom);
        this.cin = new String(cin);
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswrod() {
        return passwrod;
    }

    public String getCin() {
        return cin;
    }

}
   
