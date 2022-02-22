/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.*;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.LinkedList;

/**
 *
 * @author rachad
 */
public class EmpruntUtile {
    private final Connection con;
    private final KindleUtile kindleADO = new KindleUtile();
    private final AdherentUtile abonneDAO = new AdherentUtile();



    public  EmpruntUtile() throws SQLException {

        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mediatheque","root","");

    }

    public boolean ajouterEmprunt(Emprunt ep) throws SQLException {
        if(getEmpruntByKindle(kindleADO.getKindleByCode(ep.getCode_kindle()))!=null ){
            System.out.println("Kindle deja emprunte");
            return false;
        }
        if(getEmpruntByAbonne(abonneDAO.getAbonneByCIN(ep.getCode_abonne()))!=null){
            System.out.println("Abonne a deja un kindle");
            return false;
        }
        String query = "INSERT INTO emprunt (code_abonne,code_kindle) VALUES ('"+ep.getCode_abonne()+"','"+ep.getCode_kindle()+"')";
        Statement stmt = con.createStatement();
        String query2="UPDATE kindle SET emprunte = 'Y' WHERE code_kindle LIKE '"+ep.getCode_kindle()+"'";
        stmt.executeUpdate(query2);
        int nbUpdated = stmt.executeUpdate(query);
        return nbUpdated>0;
    }

    public boolean supprimerEmprunt(Emprunt ep) throws SQLException {
        Statement stmt = con.createStatement();
        String query="DELETE FROM emprunt WHERE code_kindle LIKE '"+ep.getCode_kindle()+"' AND code_abonne LIKE '"+ep.getCode_abonne()+"'";
        String query2="UPDATE kindle SET emprunte = 'N' WHERE code_kindle LIKE '"+ep.getCode_kindle()+"'";
        stmt.executeUpdate(query2);
        int nbUpdated = stmt.executeUpdate(query);
        return nbUpdated>0;
    }

    public Emprunt getEmprunt(Abonne ab , Kindle kd) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from emprunt where code_abonne like '"+ ab.getCode_abonne()+"' AND code_kindle LIKE '"+kd.getCode_kindle()+"'");
        while(rs.next()){
            return new Emprunt(rs.getString("code_abonne"), rs.getString("code_kindle"));

        }
        return null;
    }

    public Emprunt getEmpruntByAbonne(Abonne ab) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from emprunt where code_abonne like '"+ ab.getCode_abonne()+"'");

        while (rs.next()) {
            return new Emprunt(rs.getString("code_abonne") ,rs.getString("code_kindle"));
        }
        return null;
    }

    public Emprunt getEmpruntByKindle(Kindle kd) throws SQLException {

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from emprunt where code_kindle like '"+ kd.getCode_kindle()+"'");

        while (rs.next()) {
             return  new Emprunt(rs.getString("code_abonne") ,rs.getString("code_kindle"));
        }
        return null;
    }

    public LinkedList<Emprunt> getAllEmprunts() throws SQLException {

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from emprunt");

        LinkedList<Emprunt> emprunts= new LinkedList<>();

        while (rs.next()) {
            Emprunt ep= new Emprunt(rs.getString("code_abonne") ,rs.getString("code_kindle"));
            emprunts.add(ep);
        }
        return emprunts;
    }
    public boolean emprunter(Kindle kd, Abonne ab) throws SQLException {
        return ajouterEmprunt(new Emprunt(ab.getCode_abonne() , kd.getCode_kindle()));
    }
    public boolean rendre(Emprunt ep) throws SQLException{
        return supprimerEmprunt(ep);
    }

}
