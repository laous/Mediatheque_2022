/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Abonne;
import model.Etudiant;
import model.Professeur;
import model.User;

import java.sql.*;
import java.util.LinkedList;


public class AdherentUtile {

    private final Connection con;

    public AdherentUtile() throws SQLException {
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mediatheque", "root", "");
    }

    public Etudiant getEtudiantByCne(String cne) throws SQLException {

        String query = "SELECT * FROM adherent WHERE cne like '"+ cne+"'";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        if (rs.getString("type").equals("etudiant")) {  // Type etudiant
            return new Etudiant(rs.getString("username"), rs.getString("password"), rs.getString("nom"), rs.getString("prenom"), rs.getString("cin"), rs.getString("cne"));
        }
        return null;
    }

    /**
     * Get professeur by CIN
     *
     * @param cin type String
     * @return professeur with cin match
     */
    public Professeur getProfesseurByCin(String cin) throws SQLException {
        String query = "SELECT * FROM adherent WHERE cni like '" + cin +"'";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()){
            if (rs.getString("type").equals("professeur")) {  // Type professeur
                return new Professeur(rs.getString("username"), rs.getString("password"), rs.getString("nom"), rs.getString("prenom"), rs.getString("cin"), rs.getString("cne"));
            }
        }

        return null;
    }

    public LinkedList<Abonne> getAllAbonnes() throws SQLException{
        LinkedList<Abonne> liste = new LinkedList<>();
        String query = "SELECT * FROM adherent";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while(rs.next()){
            if (rs.getString("type").equals("etudiant")) {  // Type etudiant
                liste.add(new Etudiant(rs.getString("username"), rs.getString("password"), rs.getString("nom"), rs.getString("prenom"), rs.getString("cin"), rs.getString("cne")));
            }else{
                liste.add(new Professeur(rs.getString("username"), rs.getString("password"), rs.getString("nom"), rs.getString("prenom"), rs.getString("cin"), rs.getString("cnss")));
            }
        }

        return liste;
    }

    public Abonne getAbonneByCIN(String cin) throws SQLException{
        String query = "SELECT * FROM adherent WHERE cin like '" + cin +"'";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()){
            if (rs.getString("type").equals("professeur")) {  // Type professeur
                return new Professeur(rs.getString("username"), rs.getString("password"), rs.getString("nom"), rs.getString("prenom"), rs.getString("cin"), rs.getString("cnss"));
            }else  if (rs.getString("type").equals("etudiant")){
                return new Etudiant(rs.getString("username"), rs.getString("password"), rs.getString("nom"), rs.getString("prenom"), rs.getString("cin"), rs.getString("cne"));
            }
        }

        return null;

    }


    /**
     * Delete Etudiant by cne
     *
     * @param cne type String
     * @return true if deleted, false if not
     */
    public boolean SupprimerEtudiant(String cne) throws SQLException {
        String query = "DELETE FROM Adherent WHERE cne like '"+cne+"'";
        Statement stmt = con.createStatement();
        int rs = stmt.executeUpdate(query);
        return rs > 0;

    }

    /**
     * Delete Proffeseur by CIN
     *
     * @param cin type string
     * @return true if deleted, false if not
     */
    public boolean SupprimerProfesseur(String cin) throws SQLException {
        String query = "DELETE FROM Adherent WHERE cni like '"+cin+"'";
        Statement stmt = con.createStatement();
        int rs = stmt.executeUpdate(query);
        return rs > 0;
    }

    /**
     * Add new Etudiant
     *
     * @param etudiant type Etudiant
     * @return true if added, false if not
     */
    public boolean AjouterEtudiant(Etudiant etudiant) throws SQLException {
        if(getEtudiantByCne(etudiant.getCne()) != null){
            System.out.println("Etudiant existe");
            return false;
        }
        String query = "INSERT INTO adherent (username,password,nom,prenom,cin,cne,type) VALUES" +
                "('"+etudiant.getUsername()+"','"+etudiant.getPasswrod()+"','"+etudiant.getNom()+"','"+etudiant.getPrenom()+"','"+etudiant.getCin()+
                "','"+etudiant.getCne() +"','etudiant')";
        Statement stmt = con.createStatement();
        int rs = stmt.executeUpdate(query);
        return rs > 0;
    }

    /**
     * Add new Etudiant
     *
     * @param professeur type Professeur
     * @return true if added, false if not
     */
    public boolean AjouterProfesseur(Professeur professeur) throws SQLException {
        if(getProfesseurByCin(professeur.getCin()) != null){
            System.out.println("Etudiant existe");
            return false;
        }
        String query = "INSERT INTO adherent (username,password,nom,prenom,cin,cne,type) VALUES" +
                "('"+professeur.getUsername()+"','"+professeur.getPasswrod()+"','"+professeur.getNom()+"','"+professeur.getPrenom()+"','"+professeur.getCin()+
                "','"+professeur.getCin() +"','professeur')";
        Statement stmt = con.createStatement();
        int rs = stmt.executeUpdate(query);
        return rs > 0;
    }

}
