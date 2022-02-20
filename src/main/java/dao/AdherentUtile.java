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


public class AdherentUtile {

    private final Connection con;

    public AdherentUtile() throws SQLException {
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mediatheque", "root", "");
    }

    public Etudiant getEtudiantByCne(String cne) throws SQLException {

        String query = "SELECT * FROM adherent WHERE cne = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, cne);
        ResultSet rs = ps.executeQuery(query);
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
        String query = "SELECT * FROM Adherent WHERE cni=?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, cin);
        ResultSet rs = ps.executeQuery(query);
        if (rs.getString("type").equals("professeur")) {  // Type professeur
            return new Professeur(rs.getString("username"), rs.getString("password"), rs.getString("nom"), rs.getString("prenom"), rs.getString("cin"), rs.getString("cne"));
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
        String query = "DELETE FROM Adherent WHERE cne=?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, cne);
        ResultSet rs = ps.executeQuery(query);
        while (rs.next()) {
            if (rs.getString("type").equals("etudiant")) return true;
        }
        return false;

    }

    /**
     * Delete Proffeseur by CIN
     *
     * @param cin type string
     * @return true if deleted, false if not
     */
    public boolean SupprimerProfesseur(String cin) throws SQLException {
        String query = "DELETE FROM Adherent WHERE cni=?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, cin);
        ResultSet rs = ps.executeQuery(query);
        while (rs.next()) {
            if (rs.getString("type").equals("professeur")) return true;
        }
        return false;
    }

    /**
     * Add new Etudiant
     *
     * @param etudiant type Etudiant
     * @return true if added, false if not
     */
    public boolean AjouterEtudiant(Etudiant etudiant) throws SQLException {
        String query = "INSERT INTO adherent (username,password,nom,prenom,cin,cne,type) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, etudiant.getUsername());
        ps.setString(2, etudiant.getPasswrod());
        ps.setString(3, etudiant.getNom());
        ps.setString(4, etudiant.getPrenom());
        ps.setString(5, etudiant.getCin());
        ps.setString(6, etudiant.getCne());
        ps.setString(7, "etudiant");
        int rs = ps.executeUpdate(query);
        return rs > 0;
    }

    /**
     * Add new Etudiant
     *
     * @param professeur type Professeur
     * @return true if added, false if not
     */
    public boolean AjouterProfesseur(Professeur professeur) throws SQLException {
        String query = "INSERT INTO adherent (username,password,nom,prenom,cin,cnss,type) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, professeur.getUsername());
        ps.setString(2, professeur.getPasswrod());
        ps.setString(3, professeur.getNom());
        ps.setString(4, professeur.getPrenom());
        ps.setString(5, professeur.getCin());
        ps.setString(6, professeur.getCnss());
        ps.setString(7, "professeur");
        int rs = ps.executeUpdate(query);
        return rs > 0;
    }

}
